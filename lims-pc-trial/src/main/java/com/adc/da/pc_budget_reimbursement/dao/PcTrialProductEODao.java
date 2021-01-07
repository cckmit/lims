package com.adc.da.pc_budget_reimbursement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialProductEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PRODUCT PcTrialProductEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcTrialProductEODao extends BaseDao<PcTrialProductEO> {

    void deleteByRid(String id);

    List<PcTrialProductEO> selectByRid(String id);
}
