package com.adc.da.pc_items.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_items.entity.TrialItemsEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_ITEMS TrialItemsEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface TrialItemsEODao extends BaseDao<TrialItemsEO> {

    /**
     * 批量删除
     * @param taskId
     */
    void batchDelete(String taskId);

    /**
     * 格局任务id查询检验项目
     * @param taskId
     * @return
     */
    List<TrialItemsEO> selectByTaskId(String taskId);

    /**
     * 删除报告
     * @param reportId
     */
    void delReport(String reportId);


}
