package com.adc.da.equipment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.equipment.dao.EquipmentStatusFlagEODao;
import com.adc.da.equipment.entity.EquipmentStatusFlagEO;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_STATUS_FLAG EquipmentStatusFlagEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentStatusFlagEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentStatusFlagEOService extends BaseService<EquipmentStatusFlagEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentStatusFlagEOService.class);

    @Autowired
    private EquipmentStatusFlagEODao dao;

    public EquipmentStatusFlagEODao getDao() {
        return dao;
    }

}
