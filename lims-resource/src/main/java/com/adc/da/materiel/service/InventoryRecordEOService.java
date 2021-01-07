package com.adc.da.materiel.service;

import com.adc.da.util.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.materiel.dao.InventoryRecordEODao;
import com.adc.da.materiel.entity.InventoryRecordEO;


/**
 *
 * <br>
 * <b>功能：</b>RES_INVENTORY_RECORD InventoryRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("inventoryRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InventoryRecordEOService extends BaseService<InventoryRecordEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(InventoryRecordEOService.class);

    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private InventoryRecordEODao dao;

    public InventoryRecordEODao getDao() {
        return dao;
    }

}
