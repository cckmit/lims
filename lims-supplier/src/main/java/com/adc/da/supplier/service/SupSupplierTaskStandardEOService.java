package com.adc.da.supplier.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.supplier.dao.SupSupplierTaskStandardEODao;
import com.adc.da.supplier.entity.SupSupplierTaskStandardEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_STANDARD SupSupplierTaskStandardEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("supSupplierTaskStandardEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SupSupplierTaskStandardEOService extends BaseService<SupSupplierTaskStandardEO, String> {

    @Autowired
    private SupSupplierTaskStandardEODao dao;

    public SupSupplierTaskStandardEODao getDao() {
        return dao;
    }

}
