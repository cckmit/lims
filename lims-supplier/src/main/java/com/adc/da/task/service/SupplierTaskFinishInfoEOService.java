package com.adc.da.task.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.task.dao.SupplierTaskFinishInfoEODao;
import com.adc.da.task.entity.SupplierTaskFinishInfoEO;
import com.adc.da.task.vo.SupplierTaskFinishInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_FINISH_INFO SupplierTaskFinishInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("supplierTaskFinishInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SupplierTaskFinishInfoEOService extends BaseService<SupplierTaskFinishInfoEO, String> {

    @Autowired
    private SupplierTaskFinishInfoEODao dao;

    public SupplierTaskFinishInfoEODao getDao() {
        return dao;
    }

    public SupplierTaskFinishInfoVO findById(String id){
        return dao.findById(id);
    }

}
