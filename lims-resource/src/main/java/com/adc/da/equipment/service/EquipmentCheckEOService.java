package com.adc.da.equipment.service;

import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.page.EquipmentCheckEOPage;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.equipment.dao.EquipmentCheckEODao;
import com.adc.da.equipment.entity.EquipmentCheckEO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_CHECK EquipmentCheckEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentCheckEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentCheckEOService extends BaseService<EquipmentCheckEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentCheckEOService.class);

    @Autowired
    private EquipmentCheckEODao dao;
    @Autowired
    private UserEOService userEOService;
    public EquipmentCheckEODao getDao() {
        return dao;
    }

    /**
     * 对接受到的EquipmentCheckEO List进行批量新增操作
     * @param equipmentCheckEOList
     */
    public Boolean  savePlanCheck(List<EquipmentCheckEO> equipmentCheckEOList) {
        int num = 0;
        for (EquipmentCheckEO equipmentCheckEO:equipmentCheckEOList){
            //先校验前台传过来的数据。当前台三个必填项都不为空时，才插入
            if(equipmentCheckEO.getEqCkCheckTimePlan()!=null &&!"".equals(equipmentCheckEO.getEqCkCheckTimePlan())  && equipmentCheckEO.getEqCkPlanOwner()!=null
                &&!"".equals(equipmentCheckEO.getEqCkPlanOwner()) && equipmentCheckEO.getEqCkWarnTime()!=null &&!"".equals(equipmentCheckEO.getEqCkWarnTime())){
                num++;
                if ("".equals(equipmentCheckEO.getId())|| equipmentCheckEO.getId()==null){
                    String id = UUID.randomUserId();
                    equipmentCheckEO.setId(id);
                    equipmentCheckEO.setCreateTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT));
                    equipmentCheckEO.setCreateBy(UserUtils.getUserId());
                    equipmentCheckEO.setIsFinishFlag("0");
                    equipmentCheckEO.setDelFlag("0");
                    dao.insertSelective(equipmentCheckEO);
                }else{
                    dao.updateByPrimaryKeySelective(equipmentCheckEO);
                }

            }
        }
        if (num == 0){
            return false;
        }
        return true;
    }

    /**
     * 更新核检计划
     * @param equipmentCheckEO
     */
    public void updateCheckPlanRecord(EquipmentCheckEO equipmentCheckEO){
        EquipmentCheckEO equipmentCheck = new EquipmentCheckEO();
        equipmentCheck.setId(equipmentCheckEO.getId());
        equipmentCheck.setEqCkCheckTimePlan(equipmentCheckEO.getEqCkCheckTimePlan());
        equipmentCheck.setEqCkWarnTime(equipmentCheckEO.getEqCkWarnTime());
        equipmentCheck.setEqCkPlanOwner(equipmentCheckEO.getEqCkPlanOwner());
        if (!"".equals(equipmentCheckEO.getEqCkAttachId()) || equipmentCheckEO.getEqCkAttachId()!=null){
            equipmentCheck.setEqCkPlanAttachId(equipmentCheckEO.getEqCkPlanAttachId());
        }
        if (!"".equals(equipmentCheckEO.getEqCkAttachName()) || equipmentCheckEO.getEqCkAttachName()!=null ){
            equipmentCheck.setEqCkPlanAttachName(equipmentCheckEO.getEqCkPlanAttachName());
        }
        dao.updateByPrimaryKeySelective(equipmentCheck);
    }
    /**
     * 对单个核检计划进行逻辑删除
     * @param equipmentCheckEO
     */
    public void delPlanCheck(EquipmentCheckEO equipmentCheckEO) {
        equipmentCheckEO.setDelFlag("1");
        dao.updateByPrimaryKeySelective(equipmentCheckEO);
    }

    /**
     * 查出对应设备Id的核检计划
     * @param eqId
     * @return
     */
    public List<EquipmentCheckEO> findPlanCheck(String eqId) throws Exception {
        List<EquipmentCheckEO> equipmentCheckEOS = dao.findPlanCheck(eqId);
        List<EquipmentCheckEO> equipmentCheckEOList = new ArrayList<>();
        for (EquipmentCheckEO equipmentCheckEO : equipmentCheckEOS){
            String userId = equipmentCheckEO.getEqCkPlanOwner();
            if(userId == null){
                continue;
            }
            UserEO user = userEOService.selectByPrimaryKey(userId);
            if (user == null){
                continue;
            }
            String userName = user.getUsname();
            equipmentCheckEO.setEqCkPlanOwner(userId);
            equipmentCheckEO.setEqCkPlanOwnerName(userName);
            equipmentCheckEOList.add(equipmentCheckEO);
        }
        return equipmentCheckEOList;
    }

    /**
     *  查出对应设备Id的核检记录
     * @param eqId
     * @return
     */
    public List<EquipmentCheckEO> findCheckRecord(String eqId) throws Exception {
        List<EquipmentCheckEO> equipmentCheckEOS = dao.findCheckRecord(eqId);
        for (EquipmentCheckEO equipmentCheckEO : equipmentCheckEOS){
            String userId = equipmentCheckEO.getEqCkPlanOwner();
            UserEO user = userEOService.selectByPrimaryKey(userId);
            String userName = user.getUsname();
            equipmentCheckEO.setEqCkPlanOwner(userId);
            equipmentCheckEO.setEqCkPlanOwnerName(userName);
        }
        return equipmentCheckEOS;
    }

    /**
     * 实施核检计划
     * @param equipmentCheckEO
     */
    public void updateCheckPlan(EquipmentCheckEO equipmentCheckEO) {
        equipmentCheckEO.setUpdateBy(UserUtils.getUserId());
        equipmentCheckEO.setUpdateTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT));
        equipmentCheckEO.setIsFinishFlag("1");
        equipmentCheckEO.setDelFlag("0");
        dao.updateByPrimaryKeySelective(equipmentCheckEO);
    }

    /**
     *  查出待提醒的 所有未核检的计划
     * @param
     * @return
     */
    public List<EquipmentCheckEO> findUnCheckRecord(){
    	return dao.findUnCheckRecord();
    }

    /**
     * 分页查询出对应设备的所以核检记录
     * @param page
     * @return
     */
    public List<EquipmentCheckEO> findCheckRecordByPage(EquipmentCheckEOPage page) throws Exception {
        List<EquipmentCheckEO> equipmentCheckEOS = dao.findCheckRecordByPage(page);
        for (EquipmentCheckEO equipmentCheckEO : equipmentCheckEOS){
            String userId = equipmentCheckEO.getEqCkPlanOwner();
            UserEO user = userEOService.selectByPrimaryKey(userId);
            String userName = user.getUsname();
            equipmentCheckEO.setEqCkPlanOwner(userName);
        }
        return equipmentCheckEOS;
    }

    /**
     * 在查出当前设备的所有核检记录中。查询出对应记录的所有的条数
     * @param page
     * @return
     */
    public Integer queryForCheckRecordByPage(EquipmentCheckEOPage page) {
        return  dao.queryForCheckRecordByPage(page);
    }

    /**
     * 根据信息，插入新的核检计划
     * @param equipmentCheckEO
     */
    public void insertNewCheckPlan(EquipmentCheckEO equipmentCheckEO) throws ParseException {
        //完成日期
        String finishDateString = equipmentCheckEO.getEqCkCheckTimeActual();
        //有效期
        int validity = equipmentCheckEO.getEqCkCycle().intValue();
        //单位
        String unit = equipmentCheckEO.getEqCkCycleUnit();
        //新的计划检定日期为，完成日期 + 有效期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date finishDate = format.parse(finishDateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(finishDate);
        if ("年".equals(unit)){
            calendar.add(Calendar.YEAR,validity);
        }
        if ("月".equals(unit)){
            calendar.add(Calendar.MONTH,validity);
        }
        if ("天".equals(unit)){
            calendar.add(Calendar.DATE,validity);
        }
        EquipmentCheckEO newEquipmentCheckEO = new EquipmentCheckEO();
        newEquipmentCheckEO.setId(UUID.randomUserId());
        newEquipmentCheckEO.setEqId(equipmentCheckEO.getEqId());
        newEquipmentCheckEO.setEqCkPlanOwner(equipmentCheckEO.getEqCkPlanOwner());
        newEquipmentCheckEO.setEqCkWarnTime(equipmentCheckEO.getEqCkWarnTime());
        newEquipmentCheckEO.setEqCkCheckTimePlan(format.format(calendar.getTime()));
        newEquipmentCheckEO.setDelFlag("0");
        newEquipmentCheckEO.setIsFinishFlag("0");
        newEquipmentCheckEO.setCreateBy(UserUtils.getUserId());
        newEquipmentCheckEO.setCreateTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT));
        newEquipmentCheckEO.setEqCkType(equipmentCheckEO.getEqCkType());
        //插入新的核检计划
        dao.insertSelective(newEquipmentCheckEO);
    }
}
