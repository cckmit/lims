package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_drive.entity.PcDrivingRecordEO;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_execute.vo.PcReliableInitTaskRiskVO;
import com.adc.da.pc_execute.vo.PersonAccommodationCostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * PV/CV试验执行模块--可靠性立项单
 * @author Administrator
 * @date 2019年10月23日
 */
public interface PCReliableInitTaskEODao extends BaseDao<PCReliableInitTaskEO> {
	/**
	 * 根据可靠性立项单id,查询流程实例id
	 * @Title: selectActProcIdById
	 * @param id
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年10月25日
	 */
    public String selectActProcIdById(String id);

	/**
	 * 费用请款申请费用自行支付查询
	 * @param page
	 * @return
	 */
    List<PcAutoPayForEO> costForCashOut(CostForCashOutPage page);

	List<PcAutoPayForEO> costForCashOutPerson(CostForCashOutPage page);

	/**
	 * 通过可靠性立项单ID查询行车记录卡信息
	 * @param initTaskId
	 * @return
	 */
    List<PcDrivingRecordEO> riskSubsidy(String initTaskId);

	List<PersonAccommodationCostVO> personAccommodationCost(String initTaskId);

	PcReliableInitTaskRiskVO findReliableInitRisk(String id);

	void createReliableInitTaskRiskVo(PcReliableInitTaskRiskVO vo);

    void updateReliableInitTaskRiskVo(PcReliableInitTaskRiskVO vo);

	/**
	 * 根据试验任务编号查询可靠性立项单
	 * @param trialTaskId
	 * @return
	 */
	List<PCReliableInitTaskEO> findOne(String trialTaskId);

	/**
	 * 修改试验编号
	 * @param id
	 * @return
	 */
	int modifyTrialTaskId(@Param("id") String id,@Param("trialTaskId") String trialTaskId);

}