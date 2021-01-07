package com.adc.da.task.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.task.entity.SupplierTaskApplyEO;
import com.adc.da.task.page.SupplierTaskApplyEOPage;
import com.adc.da.task.page.SupplierTaskExeutionEOPage;
import com.adc.da.task.vo.SupplierTaskApplyPageVO;
import com.adc.da.task.vo.SupplierTaskApplyVO;
import com.adc.da.task.vo.SupplierTaskExecutionPageVO;
import com.adc.da.task.vo.SupplierTaskInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_APPLY SupplierTaskApplyEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SupplierTaskApplyEODao extends BaseDao<SupplierTaskApplyEO> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<SupplierTaskApplyPageVO> selectByPage(SupplierTaskApplyEOPage page);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SupplierTaskApplyVO selectVOById(@Param("id") String id);

    /**
     * 外包供应商任务执行|分页查询
     *
     * @param page
     * @return
     */
    List<SupplierTaskExecutionPageVO> selectTaskExecutionByPage(SupplierTaskExeutionEOPage page);

    /**
     * 查询任务执行条数
     *
     * @param page
     * @return
     */
    Integer selectTaskExecutionByCount(SupplierTaskExeutionEOPage page);

    /**
     * 通过任务单id查询标准库信息
     *
     * @param id
     * @param status
     * @return
     */
    List<SupplierTaskInfoVO> getTaskStandard(@Param("id") String id, @Param("status") Integer status);

    /**
     * 更新委托状态
     *
     * @param businessId
     * @param status
     */
    void updateStatus(@Param("businessId") String businessId, @Param("status") Integer status);

    /**
     * 更新反馈时间
     *
     * @param businessId
     * @param feddbackTime
     */
    void updateFeddbackTime(@Param("businessId") String businessId, @Param("feddbackTime") String feddbackTime);

    List<SupplierTaskApplyEO> selectByTrialTaskId(@Param("trialTaskId")String trialTaskId);
}
