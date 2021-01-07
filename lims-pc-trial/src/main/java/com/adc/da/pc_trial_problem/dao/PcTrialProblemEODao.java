package com.adc.da.pc_trial_problem.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_trial_problem.entity.PcTrialProblemEO;
import com.adc.da.pc_trial_problem.entity.TpmOrgEO;
import com.adc.da.pc_trial_problem.entity.TpmUserEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PROBLEM PcTrialProblemEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-01-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcTrialProblemEODao extends BaseDao<PcTrialProblemEO> {

    List<TpmUserEO> findUserFromTpm(String departName);
    List<TpmUserEO> findUserFromTpmNew();
    List<TpmOrgEO> findOrgFromTpm();
    
}
