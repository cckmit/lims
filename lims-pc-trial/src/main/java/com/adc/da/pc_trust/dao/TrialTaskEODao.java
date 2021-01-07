package com.adc.da.pc_trust.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.page.TrialTaskChangeEOPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_TASK TrialTaskEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Repository("pcTrialTaskEODao")
public interface TrialTaskEODao extends BaseDao<TrialTaskEO> {

    /**
     * 提交申请
     * @param id
     */
    void applyTask(@Param("id") String id, @Param("status") String status);

    List<TrialTaskEO> findAllList();

    /**
     * 逻辑删除
     * @param id
     */
    void logicDel(String id);

    
	/**
	 * 费用请款申请费用自行支付查询
	 * @Title: costForCashOut
	 * @param page
	 * @return
	 * @return List<PcAutoPayForEO>
	 * @author: ljy
	 * @date: 2020年1月9日
	 */
    List<PcAutoPayForEO> costPSQCForCashOut(CostForCashOutPage page);
    /**
     * 费用请款申请费用自行支付查询
     * @Title: costForCashOutPerson
     * @param page
     * @return
     * @return List<PcAutoPayForEO>
     * @author: ljy
     * @date: 2020年1月9日
     */
	List<PcAutoPayForEO> costPSQCForCashOutPerson(CostForCashOutPage page);

	/**
	 * 分页查询变更记录
	 * @param page
	 * @return
	 */
	List<TrialTaskEO> changeTaskByPage(TrialTaskChangeEOPage page);

	/**
	 * 统计变更记录数量
	 * @param page
	 * @return
	 */
	int changeTaskByCount(TrialTaskChangeEOPage page);

	/**
	 * 根据试验编号查询最新的试验申请
	 * @param taskNumber
	 * @return
	 */
	TrialTaskEO getNewTaskByTaskNumber(String taskNumber);
    
}
