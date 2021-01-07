package com.adc.da.pc_budget_cash_out.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_budget_cash_out.dao.PcAutoPayForEODao;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;


/**
 *
 * <br>
 * <b>功能：</b>PC_AUTO_PAY_FOR PcAutoPayForEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcAutoPayForEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcAutoPayForEOService extends BaseService<PcAutoPayForEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcAutoPayForEOService.class);

    @Autowired
    private PcAutoPayForEODao dao;

    public PcAutoPayForEODao getDao() {
        return dao;
    }

    public void deleteByBcoId(String id) {
        this.getDao().deleteByBcoId(id);
    }
}
