package com.adc.da.pc_person.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_person.entity.TrialPersonEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PERSON TrialPersonEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface TrialPersonEODao extends BaseDao<TrialPersonEO> {

    /**
     * 批量删除
     * @param taskId
     */
    void batchDelete(String taskId);

    /**
     * 格局任务id查询人员
     * @param taskId
     * @return
     */
    List<TrialPersonEO> selectByTaskId(String taskId);

    /**
     * 格局任务id查询激活的人员
     * @param taskId
     * @return
     */
    List<TrialPersonEO> queryByTaskId(String taskId);


    /**
     * 格局任务id集合查询激活的人员
     * @return
     */
    List<String> selectByKeyArray(List<String> userName);
}
