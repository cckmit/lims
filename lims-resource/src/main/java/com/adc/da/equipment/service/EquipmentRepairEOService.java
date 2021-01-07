package com.adc.da.equipment.service;

import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.equipment.page.EquipmentRepairEOPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.equipment.dao.EquipmentRepairEODao;
import com.adc.da.equipment.entity.EquipmentRepairEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_REPAIR EquipmentRepairEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentRepairEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentRepairEOService extends BaseService<EquipmentRepairEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentRepairEOService.class);

    @Autowired
    private EquipmentRepairEODao dao;

    @Autowired
    private EquipmentEOService equipmentEOService;

    public EquipmentRepairEODao getDao() {
        return dao;
    }

    /**
     * 根据维修设备原有的状态，来确定维修后更改的状态
     * 异常、停用状态下维修后，将状态改为正常。 正常、超期未检状态下维修后，不改变状态
     * @param eqId
     */
    public void updateEqStatusByEqId(String eqId) throws Exception {
        //根据设备Id得到对应设备的实体类
        EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
        //得到目前设备的设备状态
        String eqStatus = equipmentEO.getEqStatus();
        //对状态进行判断,当设备状态为异常和停用时，将状态改为正常
        if ("1".equals(eqStatus) || "2".equals(eqStatus)){
            equipmentEOService.updateEquStatusById(eqId,"0");
        }
    }

    /**
     * 查出对应设备的维修记录
     * @param page
     * @return
     */
    public List<EquipmentRepairEO> getRepairRecordByPage(EquipmentRepairEOPage page) {
        return dao.getRepairRecordByPage(page);
    }
    /**
     * 查出对应设备的维修记录条数
     * @param page
     * @return
     */
    public Integer queryCountForRepairRecord(EquipmentRepairEOPage page) {
        return dao.queryCountForRepairRecord(page);
    }
}
