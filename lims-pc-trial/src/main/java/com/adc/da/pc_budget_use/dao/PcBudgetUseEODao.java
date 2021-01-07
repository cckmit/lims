package com.adc.da.pc_budget_use.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseEO;
import com.adc.da.pc_budget_use.vo.TrialTaskVO;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>PC_BUDGET_USE PcBudgetUseEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcBudgetUseEODao extends BaseDao<PcBudgetUseEO> {

    TrialTaskVO findTaskByTrialId(String trialId);

    List<TrialTaskVO> findTaskByTaskNumber(String taskNumber);

    int updateByPrimaryKeySelective(PcBudgetUseEO pcBudgetUseEO);

    int insertSelective(PcBudgetUseEO pcBudgetUseEO);
}
