package com.adc.da.pc_execute.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_execute.dao.PCBudgetTestEODao;
import com.adc.da.pc_execute.entity.PCBudgetTestEO;



/**
 * PV/CV试验执行模块--可靠性立项单(试验费用预算)
 * @author Administrator
 * @date 2019年10月23日
 */
public class PCBudgetTestEOService extends BaseService<PCBudgetTestEO, String> {
	
	@Autowired
	private PCBudgetTestEODao pcBudgetTestEODao;
	
	public PCBudgetTestEODao getDao() {
		return pcBudgetTestEODao;
	}
}
