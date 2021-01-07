package com.adc.da.pc_budget_reimbursement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialWorkdayEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_WORKDAY PcTrialWorkdayEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcTrialWorkdayEODao extends BaseDao<PcTrialWorkdayEO> {

    void deleteByRid(String id);

    List<PcTrialWorkdayEO> selectByRid(String id);
}
