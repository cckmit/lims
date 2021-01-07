package com.adc.da.internalcost.dao;

import com.adc.da.internalcost.entity.PcBudgetSettleStatusEO;
import com.adc.da.internalcost.page.CostStatisticsPage;
import com.adc.da.internalcost.vo.PCBudgetSubsidyVO;
import com.adc.da.internalcost.vo.PcBudgetUseInfoVO;
import com.adc.da.internalcost.vo.PcTrialProductVO;
import com.adc.da.internalcost.vo.TrialTaskCostBudgetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/15 14:39
 * @description：
 */
public interface CostStatisticsDao {

    List<TrialTaskCostBudgetVO> getTrialTaskBudget(CostStatisticsPage page);

    Integer getTrialTaskBudgetCount(CostStatisticsPage page);

    String selectSampleByTrialId(String id);

    List<TrialTaskCostBudgetVO> getBudgetByTaskId(@Param("taskId") String initId);

    List<PcTrialProductVO> getReimbursement(String id);

    List<PcBudgetUseInfoVO> getBudgetUseInfo(String id);

    String getStatusByTrialId(@Param("type") String type, @Param("id") String id);

    void insertSettleStatus(PcBudgetSettleStatusEO eo);

    List<TrialTaskCostBudgetVO> getBudgetPersonByTaskId(String id);

    List<PCBudgetSubsidyVO> getSubsidyByTaskId(String trialTaskId);
}
