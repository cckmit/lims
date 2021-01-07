package com.adc.da.trial_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.entity.TrialConnectEO;
import com.adc.da.trial_execute.page.TrialConnectEOPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>EV_TRIAL_CONNECT TrialConnectEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface TrialConnectEODao extends BaseDao<TrialConnectEO> {

    /**
     * 通过实验任务id，查询交接班记录列表
     *
     * @param page
     * @return
     */
    List<TrialConnectEO> selectListByTaskId(TrialConnectEOPage page);

    List<String> selectOrgIds(@Param("taskId") String taskId);

    List<String> selectOrgIdsByUserId(@Param("userId") String userId);
    /**
     * 根据试验任务id,查询台架状态
    * @Title：selectByTrialTaskId
    * @param trialtaskId
    * @return
    * @return: List<String>
    * @author： ljy  
    * @date： 2019年9月24日
     */
    List<String> selectByTrialTaskId(String trialtaskId);

    List<String> selectTrialTaskIds(@Param("taskId") String taskId);
}
