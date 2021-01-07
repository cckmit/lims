package com.adc.da.trial_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.entity.TrialCostEO;
import com.adc.da.trial_execute.page.TrialCostEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>EV_TRIAL_COST TrialCostEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface TrialCostEODao extends BaseDao<TrialCostEO> {

//    列表查询
    List<TrialCostEO> queryByVOList(TrialCostEOPage page);

    int queryByVOCount(TrialCostEOPage page);

    List<TrialCostEO> queryByVOPage(TrialCostEOPage page);



}
