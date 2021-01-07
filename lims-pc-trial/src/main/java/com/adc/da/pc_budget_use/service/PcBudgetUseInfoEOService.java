package com.adc.da.pc_budget_use.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_budget_use.dao.PcBudgetUseInfoEODao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseInfoEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <br>
 * <b>功能：</b>PC_BUDGET_USE_INFO PcBudgetUseInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcBudgetUseInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcBudgetUseInfoEOService extends BaseService<PcBudgetUseInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetUseInfoEOService.class);

    @Autowired
    private PcBudgetUseInfoEODao dao;

    public PcBudgetUseInfoEODao getDao() {
        return dao;
    }

    public void deleteByBuId(String id) {
        this.getDao().deleteByBuId(id);
    }

    
    public String selectCostByBuIdAndSupId(String buId, String supId) {
    	return this.getDao().selectCostByBuIdAndSupId(buId, supId);
    }

}
