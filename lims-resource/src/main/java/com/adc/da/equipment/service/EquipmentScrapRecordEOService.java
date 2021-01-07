package com.adc.da.equipment.service;

import com.adc.da.equipment.VO.EquipmentScrapRecordVO;
import com.adc.da.equipment.page.EquipmentScrapRecordEOPage;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.equipment.dao.EquipmentScrapRecordEODao;
import com.adc.da.equipment.entity.EquipmentScrapRecordEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_SCRAP_RECORD EquipmentScrapRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentScrapRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentScrapRecordEOService extends BaseService<EquipmentScrapRecordEO, Void> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentScrapRecordEOService.class);

    @Autowired
    private EquipmentScrapRecordEODao dao;
    @Autowired
    private EquipmentEOService equipmentEOService;
    @Autowired
    private UserEOService userEOService;

    public EquipmentScrapRecordEODao getDao() {
        return dao;
    }

    public Integer queryCountForEqScrap(EquipmentScrapRecordEO equipmentScrapRecordEO) {
        return  dao.queryCountForEqScrap(equipmentScrapRecordEO.getEqId());
    }

    /**
     * 设备报废
     * @param equipmentScrapRecordEO
     */
    public void equipmentScrap(EquipmentScrapRecordEO equipmentScrapRecordEO) {
        //先插入到报废表中。报废的时候将该设备的状态改变为“报废”
        equipmentScrapRecordEO.setScrEquipmentStatus("0");
        equipmentScrapRecordEO.setId(UUID.randomUUID());
        dao.insertSelective(equipmentScrapRecordEO);
        //对应的设备Id
        String eqId = equipmentScrapRecordEO.getEqId();
        //根据设备id，在设备表中改变对应设备的使用状态为 报废--“2”，将使用人/使用单位设置为借用人/借用人单位
        equipmentEOService.updateUseStatusById(eqId,"2",equipmentScrapRecordEO.getScrApplicantName(),equipmentScrapRecordEO.getScrApplicantDepartment());
    }

    /**
     * 设备还原
     * @param equipmentScrapRecordEO
     * @param userId
     * @return
     */
    public ResponseMessage<EquipmentScrapRecordEO> equipmentRecover(EquipmentScrapRecordEO equipmentScrapRecordEO, String userId) {
        //根据还原设备的id，和数据库表中设备还原状态的标记。来唯一确定一条记录
        EquipmentScrapRecordEO scrapRecordEO = dao.selectScrapRecordByEqId(equipmentScrapRecordEO.getEqId());
        //首先判断，该仪器设备是否被归还人报废。也就是必须要符合“谁报废谁还原的逻辑”
        if(scrapRecordEO.getScrApplicantId().trim().equals(userId.trim())){
            //将记录查询在对应的报废还原表里面
            scrapRecordEO.setScrRecoverReason(equipmentScrapRecordEO.getScrRecoverReason());
            scrapRecordEO.setScrRecoverTime(equipmentScrapRecordEO.getScrRecoverTime());
            scrapRecordEO.setScrEquipmentStatus("1");
            dao.updateByScrapEO(scrapRecordEO);
            //将设备表中的对应设备状态进行更新
            equipmentEOService.updateUseStatusById(scrapRecordEO.getEqId(),"0","","");
            return Result.success("0","还原成功!",equipmentScrapRecordEO);
        }else{
            return Result.error("-1", "还原失败!当前用户不是该设备报废人！");
        }
    }

    /**
     * 查出该设备所有的借用归还记录
     * @param page
     * @return
     */
    public List<EquipmentScrapRecordEO> getScrapRecordByPage(EquipmentScrapRecordEOPage page) {
        return dao.getScrapRecordByPage(page);
    }
    /**
     * 查出该设备所有的借用归还记录条数
     * @param page
     * @return
     */
    public Integer queryCountForScrapRecord(EquipmentScrapRecordEOPage page) {
        return dao.queryCountForScrapRecord(page);
    }

    /**
     * 查出报废人的部分信息
     * @param equipmentScrapRecordEO
     * @return
     */
    public EquipmentScrapRecordVO getScraperMes(EquipmentScrapRecordEO equipmentScrapRecordEO) throws Exception {
        String userID = equipmentScrapRecordEO.getScrApplicantId();
        UserEO userEO = userEOService.selectByPrimaryKey(userID);
        EquipmentScrapRecordVO equipmentScrapRecordVO = new EquipmentScrapRecordVO();
        equipmentScrapRecordVO.setBorrowerId(userID);
        equipmentScrapRecordVO.setBorrowerName(userEO.getUsname());
        equipmentScrapRecordVO.setBorrowerDepartment(userEO.getOrgName());
        return equipmentScrapRecordVO;
    }
}
