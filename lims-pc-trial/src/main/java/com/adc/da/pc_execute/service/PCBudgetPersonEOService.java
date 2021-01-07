package com.adc.da.pc_execute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_execute.dao.PCBudgetPersonEODao;
import com.adc.da.pc_execute.entity.PCBudgetPersonEO;

/**
 * PV/CV试验执行模块--可靠性立项单(PC试验人员及住宿预算)
 * @author Administrator
 * @date 2019年10月23日
 */
@SuppressWarnings("all")
@Service("PCBudgetPersonEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCBudgetPersonEOService extends BaseService<PCBudgetPersonEO, String> {
	
	@Autowired
	private PCBudgetPersonEODao pcBudgetPersonEODao;
	
	public PCBudgetPersonEODao getDao() {
		return pcBudgetPersonEODao;
	}
}
