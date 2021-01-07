package com.adc.da.pc_budget_use.dao;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseInfoEO;

/**
 * <br>
 * <b>功能：</b>PC_BUDGET_USE_INFO PcBudgetUseInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcBudgetUseInfoEODao extends BaseDao<PcBudgetUseInfoEO> {

    void deleteByBuId(String id);
    String selectCostByBuIdAndSupId(@Param("buId") String buId, @Param("supId") String supId);
    String selectCostBySupIdAndTrialId(@Param("trialId") String trialId, @Param("supId") String supId);
}
