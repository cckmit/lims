package com.adc.da.pc_loan_application.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import com.adc.da.pc_loan_application.vo.PcCarLoanApplicationVO;
import com.adc.da.pc_loan_application.vo.PcCarLoanFindVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_CAR_LOAN_APPLICATION PcCarLoanApplicationEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcCarLoanApplicationEODao extends BaseDao<PcCarLoanApplicationEO> {

    /**
     *根据taskid查询所有的信息列表
     * @param trialTaskID
     * @return
     */
    List<PcCarLoanFindVo> findPcCarLoanApplication(String trialTaskID);




    String findPcCarIDByTaskID(String task_id);

    /**
     * 变更流程状态
     *
     * @param id
     * @return
     */
    void changeStatus(@Param("id") String id, @Param("status") String status);

    /**
     * 根据主表主键查询主表和附表的数据
     * @param id
     * @return
     */
    PcCarLoanFindVo findPcCarDataById(String id);

    Integer findLoanCarCodeBy(String taskId);

    /**
     * 根据借车单编号获取借车单类型
     */
    String findListTypeByTaskIdAndLoanCarCode(String trialTaskID, String loanCarCode);

}
