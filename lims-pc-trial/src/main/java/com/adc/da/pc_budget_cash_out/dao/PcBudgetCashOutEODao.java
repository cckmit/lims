package com.adc.da.pc_budget_cash_out.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_cash_out.entity.PcBudgetCashOutEO;

/**
 *
 * <br>
 * <b>功能：</b>PC_BUDGET_CASH_OUT PcBudgetCashOutEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcBudgetCashOutEODao extends BaseDao<PcBudgetCashOutEO> {

    int updateByPrimaryKeySelective(PcBudgetCashOutEO pcBudgetCashOutEO);

}
