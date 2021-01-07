package com.adc.da.TimeUtil;

import com.adc.da.equipment.entity.EquipmentCheckEO;
import com.adc.da.equipment.service.EquipmentCheckEOService;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.materiel.entity.MaterielEO;
import com.adc.da.materiel.service.MaterielEOService;
import com.adc.da.util.utils.StringUtils;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class ResSchedulingConfig {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ResSchedulingConfig.class);

    @Autowired
    private MaterielEOService materielEOService;
    
    @Autowired
    private EquipmentEOService equipmentEOService;
    
    @Autowired
    private EquipmentCheckEOService equipmentCheckEOService;

    
    /**
     * 每天凌晨1点10分，查询3天后到期的物料，发送消息给物料管理员
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void sendInvalidMaterielToManager() {
    	//查询物料表所有未删除记录的数据的有效期以及生产日期
        List<MaterielEO> materielEOS = materielEOService.queryValidity();
        for (MaterielEO materielEO : materielEOS){
            Double termOfValidity = materielEO.getTermOfValidity();//有效期
            String manufactureDate = materielEO.getManufactureDate();//生产日期
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            if (StringUtils.isNotEmpty(manufactureDate) && termOfValidity != null){
                try {
                    date = format.parse(manufactureDate+" 00:00:00");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.MONTH,termOfValidity.intValue());//生产日期加有效期
                    Date time = cal.getTime();//两者相加之后的日期
                    Date newDate = new Date();//当前时间
                    int diff1 = 1000*60*60*24*3;//3天的毫秒值
                    int diff2 = 1000*60*60*24*4;//4天的毫秒值
                    //到达前3天消息提醒物料管理员
                    if ((newDate.getTime() - time.getTime()) >= diff1 && (newDate.getTime() - time.getTime()) <= diff2){
                        //消息提醒物料管理员
                    	String sendUser = "系统通知"; 
                		String title = "物料【" + materielEO.getMaterielCode() + "】即将到达有效期";
                		String materielId = materielEO.getId();
                		materielEOService.sendMessageToRole("Materiel_Manager", "", sendUser, title, materielId);
                    }
                    //当前时间超过有效期
                    if ((newDate.getTime() - time.getTime()) >= 0 && (newDate.getTime() - time.getTime()) <= 1000*60*60*24*1){
                        //物料变为报废状态  并插入报废记录
                        materielEOService.scrapByScheduling(materielEO);
                    }

                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
    

    
    /**
     * 每天凌晨1点20分，查询今天到达核检预警的设备，发送消息给设备管理员
     * @throws Exception 
     */
    @Scheduled(cron = "0 20 1 * * ?")
    public void sendOverdueEquipmentMessageToManager() throws Exception {
    	//查询核检表中，所有到达核检预警的信息
        List<EquipmentCheckEO> equipmentCheckEOS = equipmentCheckEOService.findUnCheckRecord();
        for(EquipmentCheckEO equipmentCheckEO : equipmentCheckEOS) {
    		
    		//计算预警日期
    		String warnTime = equipmentCheckEO.getEqCkWarnTime();
    		if(StringUtil.isNullOrEmpty(warnTime)) {
    			continue;
    		}
    		int warnDays = Integer.valueOf(warnTime);
    		//计划核检日期
            String planCheckTimePlan = equipmentCheckEO.getEqCkCheckTimePlan();
            if (StringUtils.isEmpty(planCheckTimePlan)){
            	continue;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            try {
                date = format.parse(planCheckTimePlan+" 00:00:00");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                Date time = cal.getTime();//两者相加之后的日期
                Date newDate = new Date();//当前时间
                int diff1 = 1000*60*60*24*(warnDays-1);//计算预警日期的毫秒值
                int diff2 = 1000*60*60*24*warnDays;//计算预警日期的毫秒值
                //到达前N天消息提醒设备管理员
                if ((time.getTime() - newDate.getTime()) >= diff1 && (time.getTime() - newDate.getTime()) <= diff2){
                    //消息提醒设备管理员
                	String sendUser = "系统通知"; 
            		String title = "设备【" + equipmentCheckEO.getEqNo() + "】"+warnTime+"天后到达核检日期";
            		String materielId = equipmentCheckEO.getEqId();
            		materielEOService.sendMessageToRole("Equipment_Manager", "", sendUser, title, materielId);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    
    /**
     * 每天凌晨23点55分，查询今天超期未检的设备，修改其设备状态为超期未检
     * @throws Exception 
     */
    @Scheduled(cron = "0 55 23 * * ?")
    public void changeStateOfOverdueEquipment() throws Exception {
    	//查询核检表中，所有到达超期未检的设备信息，修改状态
        equipmentEOService.changeStateOfOverdueEquipment();
    	
    }

}
