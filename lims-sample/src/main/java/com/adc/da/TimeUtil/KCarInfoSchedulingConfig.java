package com.adc.da.TimeUtil;

import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.kCar.dao.KBorrowInfoEODao;
import com.adc.da.kCar.entity.KBorrowInfoEO;
import com.adc.da.kCar.vo.KCarBorrowInfoVO;
import com.adc.da.kCar.vo.KCarInfoVO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axis.client.Call;

@Configuration
public class KCarInfoSchedulingConfig {

    private static final Logger logger = LoggerFactory.getLogger(KCarInfoSchedulingConfig.class);

    @Autowired
    private SaCarDataDAO saCarDataDAO;
    
    @Autowired
    private KBorrowInfoEODao KBorrowInfoEODao;

    @Value("${kInfoUrl}")
    private String kInfoUrl;

    /**
     * 每天凌晨2点获取K库的整车信息以及相关借用归还记录
     */
    //每小时
    //@Scheduled(cron = "0 0 */1 * * ?")
    @Scheduled(cron = "0 0 2 * * ?")
    @Async
    public void getKCarInfo(){
        try {
            String ref = sendWebservice(kInfoUrl);
            JSONObject json_test = JSONObject.fromObject(ref);
            if(!"1".equals(json_test.getString("Code"))) {return;}
            List<KCarInfoVO> list = JSON.parseArray(json_test.getString("Data"), KCarInfoVO.class);
            for (KCarInfoVO kCarInfoVO : list){
                //获取底盘号
                String batholithNo = kCarInfoVO.getBATHOLITHNO();
                //根据发动机编号或者底盘号查询整车
                SaCarDataEO saCarData = saCarDataDAO.selectByChassisNumber(batholithNo);
                //证明存在对应整车
                if (StringUtils.isNotEmpty(saCarData)){
                	saCarData.setCarStatusE(kCarInfoVO.getLSTATUS());//k库车辆状态
                	saCarData.setCarType(kCarInfoVO.getCONFIGNAME());//车辆代码
                	saCarData.setChassisNumber(kCarInfoVO.getBATHOLITHNO());//底盘号
                	saCarData.setCarEngineNo(kCarInfoVO.getEGNO());//发动机号
                	saCarData.setCarCondition(kCarInfoVO.getRESTOREINFO());//车况
                	saCarData.setMotorNumber(kCarInfoVO.getMOTORNUM());//电机号
                	saCarData.setBatteryPack(kCarInfoVO.getBATTERYPN());//电池包总成
                	saCarData.setCarVin(kCarInfoVO.getVIN());//vin码
                    saCarDataDAO.updateByPrimaryKeySelective(saCarData);
                    
                    //借车记录存储
                    if(CollectionUtils.isNotEmpty(kCarInfoVO.getBorrowInfo())) {
                    	saveKCarBorrowInfo(saCarData.getChassisNumber(), saCarData.getId(), 
                    			kCarInfoVO.getBorrowInfo());
                    }
                    
                }else {//不存在此整车,数据库新增
                    SaCarDataEO saCarDataEO = new SaCarDataEO();
                    saCarDataEO.setCarStatusE(kCarInfoVO.getLSTATUS());//k库车辆状态
                    saCarDataEO.setCarType(kCarInfoVO.getCONFIGNAME());//车辆代码
                    saCarDataEO.setChassisNumber(kCarInfoVO.getBATHOLITHNO());//底盘号
                    saCarDataEO.setCarEngineNo(kCarInfoVO.getEGNO());//发动机号
                    saCarDataEO.setCarCondition(kCarInfoVO.getRESTOREINFO());//车况
                    saCarDataEO.setMotorNumber(kCarInfoVO.getMOTORNUM());//电机号
                    saCarDataEO.setBatteryPack(kCarInfoVO.getBATTERYPN());//电池包总成
                    saCarDataEO.setCarVin(kCarInfoVO.getVIN());//vin码
                    saCarDataEO.setDelFlag(0);
                    saCarDataEO.setId(UUID.randomUUID());
                    saCarDataDAO.insertSelective(saCarDataEO);
                    
                    //借车记录存储
                    if(CollectionUtils.isNotEmpty(kCarInfoVO.getBorrowInfo())) {
                    	saveKCarBorrowInfo(saCarDataEO.getChassisNumber(), saCarDataEO.getId(), 
                    			kCarInfoVO.getBorrowInfo());
                    }
                }

            }

        } catch (Exception e) {
            logger.error("请求接口报错",e.getMessage());
        }
    }
    
    
    // 远程webService接口拿到用户那边数据
    public static String sendWebservice(String soapUrl) {
        String soapaction = "http://tempuri.org/"; // 域名，这是在server定义的
        String operationName = "SelectKCJXX";// 调用方法名
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String ret = "";
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(soapUrl);
            call.setOperationName(new QName(soapaction, operationName)); // 设置要调用哪个方法
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapaction + operationName);
            call.setEncodingStyle("UTF-8");
            ret = (String) call.invoke(new Object[]{""});// 调用方法
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return ret;
    }
    
    
    /**
     * 保存借车信息
     * @Title: saveKCarBorrowInfo
     * @param chassisNumber
     * @param carId
     * @param borrowInfoList
     * @return void
     * @author: ljy
     * @date: 2020年4月21日
     */
    public void saveKCarBorrowInfo(String chassisNumber, String carId,
    		List<KCarBorrowInfoVO> borrowInfoList) {
    	//先删除后保存
    	KBorrowInfoEODao.deleteByChassisNumber(chassisNumber);
    	for(KCarBorrowInfoVO vo : borrowInfoList) {
    		KBorrowInfoEO eo = new KBorrowInfoEO();
    		//借车时间
    		eo.setBorrowDate(vo.getLOANDATE());
    		//借车用途
    		eo.setBorrowUse(vo.getPURPOSE());
    		//外借人员
    		eo.setBorrowUser(vo.getLOAN_MAN());
    		//整车id
    		eo.setCarId(carId);
    		//id
    		eo.setId(UUID.randomUUID());
    		//预计归还日期
    		eo.setPlanRevertDate(vo.getRESTOREDATE());
    		//实际归还日期
    		eo.setRealRevertDate(vo.getRESTDATE());
    		//底盘号
    		eo.setChassisNumber(chassisNumber);
    		KBorrowInfoEODao.insertSelective(eo);
    	}
    }
    
}
