package com.adc.da.task.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.task.entity.SupplierTaskInfoEO;
import com.adc.da.task.page.SupplierTaskStatisticsPage;
import com.adc.da.task.vo.SupplierTaskInfoVO;
import com.adc.da.task.vo.SupplierTaskStatisticsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_INFO SupplierTaskInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SupplierTaskInfoEODao extends BaseDao<SupplierTaskInfoEO> {

    /**
     * 通过任务委托ID删除任务信息
     *
     * @param id
     * @param userId
     * @param date
     */
    void deleteByApplyId(@Param("id") String id);

    /**
     * 外包供应商任务执行|委托单的任务信息
     *
     * @param id
     * @return
     */
    SupplierTaskInfoVO getTaskInfo(@Param("id") String id);

    /**
     * |外包供应商|任务统计分页查询
     * @param page
     * @return
     */
    List<SupplierTaskStatisticsVO> getSupplierTaskStatisticsByPage(SupplierTaskStatisticsPage page);

    /**
     * |外包供应商|任务统计分页查询数量
     * @param page
     * @return
     */
    Integer getSupplierTaskStatisticsByCount(SupplierTaskStatisticsPage page);

    /**
     * |外包供应商|任务统计导出
     * @param page
     * @return
     */
    List<SupplierTaskStatisticsVO> queryListForExcel(SupplierTaskStatisticsPage page);

    /**
     * 更新状态
     * @param applyId
     */
    void updateTaskInfoStatus(String applyId);

    List<SupplierTaskStatisticsVO> getTaskInfoByApplyNos(Map<String, Object> params);

    Integer getTaskInfoByApplyNosCount(Map<String, Object> params);
}
