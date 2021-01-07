package com.adc.da.pc_budget_reimbursement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_reimbursement.entity.PcBudgetReimbursementEO;

/**
 *
 * <br>
 * <b>功能：</b>PC_BUDGET_REIMBURSEMENT PcBudgetReimbursementEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcBudgetReimbursementEODao extends BaseDao<PcBudgetReimbursementEO> {

    String findTotalByTrialId(String trialId);

    int updateByPrimaryKeySelective(PcBudgetReimbursementEO pcBudgetReimbursementEO);

    int insertSelective(PcBudgetReimbursementEO pcBudgetReimbursementEO);
    /**
     * 根据业务id 获取流程相关信息
     * @Title: selectActProcIdById
     * @param id
     * @return
     * @return String
     * @author: ljy
     * @date: 2020年4月22日
     */
    String selectActProcIdById(String id);
}
