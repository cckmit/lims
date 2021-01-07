package com.adc.da.pc_execute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_execute.dao.PCBudgetSubsidyEODao;
import com.adc.da.pc_execute.entity.PCBudgetSubsidyEO;



/**
 * PV/CV试验执行模块--可靠性立项单(试验补贴预算)
 * @author Administrator
 * @date 2019年10月23日
 */
@SuppressWarnings("all")
@Service("PCBudgetSubsidyEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCBudgetSubsidyEOService extends BaseService<PCBudgetSubsidyEO, String> {
	
	@Autowired
	private PCBudgetSubsidyEODao pcBudgetSubsidyEODao;
	
	public PCBudgetSubsidyEODao getDao() {
		return pcBudgetSubsidyEODao;
	}
	
}
