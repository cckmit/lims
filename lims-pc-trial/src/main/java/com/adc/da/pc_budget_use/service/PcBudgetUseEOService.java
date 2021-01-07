package com.adc.da.pc_budget_use.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_reimbursement.service.PcBudgetReimbursementEOService;
import com.adc.da.pc_budget_use.dao.PcBudgetUseEODao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseEO;
import com.adc.da.pc_budget_use.entity.PcBudgetUseInfoEO;
import com.adc.da.pc_budget_use.page.PcBudgetUseEOPage;
import com.adc.da.pc_budget_use.vo.TrialTaskVO;
import com.adc.da.pc_outsource.common.PcOutsourceEnum;
import com.adc.da.pc_outsource.service.PcOutsourceProjectEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <br>
 * <b>功能：</b>PC_BUDGET_USE PcBudgetUseEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcBudgetUseEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcBudgetUseEOService extends BaseService<PcBudgetUseEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetUseEOService.class);

    @Autowired
    private PcBudgetUseEODao dao;

    public PcBudgetUseEODao getDao() {
        return dao;
    }

    @Autowired
    private PcBudgetUseInfoEOService pcBudgetUseInfoEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;

    @Autowired
    private PcBudgetReimbursementEOService pcBudgetReimbursementEOService;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private OrgEOService orgEOService;

    public void delete(String id) {
        pcBudgetUseInfoEOService.deleteByBuId(id);
        this.getDao().deleteByPrimaryKey(id);
    }

    public void create(PcBudgetUseEO pcBudgetUseEO) throws Exception {
        pcBudgetUseEO.setId(UUID.randomUUID10());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        pcBudgetUseEO.setCreateBy(userId);
        pcBudgetUseEO.setUpdateBy(userId);
        pcBudgetUseEO.setCreateTime(date);
        pcBudgetUseEO.setUpdateTime(date);
        pcBudgetUseEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        pcBudgetUseEO.setStatus(PcOutsourceEnum.DRAFT.getValue());
        // 通过任务书ID查询实验任务信息，试验开始时间、实验结束时间、试验地点、
        if("0".equals(pcBudgetUseEO.getTaskOrPlan())) {
        	List<TrialTaskVO> list = this.getDao().findTaskByTaskNumber(pcBudgetUseEO.getTaskNumber());
        	if (list.size() > 0) {
        		pcBudgetUseEO.setTrialStartTime(list.get(0).getTrialStartTime());
        		pcBudgetUseEO.setTrialEndTime(list.get(0).getTrialEndTime());
        	}
        }
        // 通过任务书ID查询报销总计
        String total = pcBudgetReimbursementEOService.findTotalByTrialId(pcBudgetUseEO.getTrialId());
        pcBudgetUseEO.setBudgetTotal(total);
        //根据需求增加费用总计字段
        List<PcBudgetUseInfoEO> infoEOList = pcBudgetUseEO.getPcBudgetUseInfoEOList();
        double sum = infoEOList.stream().map(PcBudgetUseInfoEO::getBuSubtotal).map(Double::valueOf).collect(Collectors.summarizingDouble(Double::doubleValue)).getSum();
        pcBudgetUseEO.setTotal(String.valueOf(sum));
        pcBudgetUseEO.setBuProjectName(infoEOList.get(0).getBuProject());
        pcBudgetUseEO.setSupName(infoEOList.get(0).getBuSupplier());
        pcBudgetUseEO.setSupId(infoEOList.get(0).getBuSupplierId());
        this.getDao().insertSelective(pcBudgetUseEO);
        createPcBudgetUseEo(pcBudgetUseEO, date, userId);
    }

    public void update(PcBudgetUseEO pcBudgetUseEO) throws Exception {
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        pcBudgetUseEO.setUpdateTime(date);
        pcBudgetUseEO.setUpdateBy(userId);
        this.getDao().updateByPrimaryKeySelective(pcBudgetUseEO);
        pcBudgetUseInfoEOService.deleteByBuId(pcBudgetUseEO.getId());
        createPcBudgetUseEo(pcBudgetUseEO, date, userId);
    }

    private void createPcBudgetUseEo(PcBudgetUseEO pcBudgetUseEO, String date, String userId) throws Exception {
        if (CollectionUtils.isNotEmpty(pcBudgetUseEO.getPcBudgetUseInfoEOList())) {
            for (PcBudgetUseInfoEO infoEO : pcBudgetUseEO.getPcBudgetUseInfoEOList()) {
                infoEO.setId(UUID.randomUUID10());
                infoEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                infoEO.setCreateTime(date);
                infoEO.setCreateBy(userId);
                infoEO.setUpdateTime(date);
                infoEO.setUpdateBy(userId);
                infoEO.setBuId(pcBudgetUseEO.getId());
                pcBudgetUseInfoEOService.insertSelective(infoEO);
            }
        }
    }

    public void submitActivity(PcBudgetUseEO pcBudgetUseEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetUseEO.setStatus(PcOutsourceEnum.UN_APPROVAL.getValue());
        if (StringUtils.isNotBlank(pcBudgetUseEO.getId())) {
            pcBudgetUseEO.setUpdateTime(date);
            pcBudgetUseEO.setUpdateBy(userId);
            this.getDao().updateByPrimaryKeySelective(pcBudgetUseEO);
            pcBudgetUseInfoEOService.deleteByBuId(pcBudgetUseEO.getId());
            createPcBudgetUseEo(pcBudgetUseEO, date, userId);
        } else {
            pcBudgetUseEO.setId(UUID.randomUUID10());
            pcBudgetUseEO.setCreateBy(userId);
            pcBudgetUseEO.setUpdateBy(userId);
            pcBudgetUseEO.setCreateTime(date);
            pcBudgetUseEO.setUpdateTime(date);
            pcBudgetUseEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            // 通过任务书ID查询实验任务信息，试验开始时间、实验结束时间、试验地点、
            TrialTaskVO trialTaskVO = this.getDao().findTaskByTrialId(pcBudgetUseEO.getTrialId());
            if (trialTaskVO != null) {
                pcBudgetUseEO.setTrialStartTime(trialTaskVO.getTrialStartTime());
                pcBudgetUseEO.setTrialEndTime(trialTaskVO.getTrialEndTime());
            }
            // 通过任务书ID查询报销总计
            String total = pcBudgetReimbursementEOService.findTotalByTrialId(pcBudgetUseEO.getTrialId());
            pcBudgetUseEO.setBudgetTotal(total);
            //根据需求增加费用总计字段
            List<PcBudgetUseInfoEO> infoEOList = pcBudgetUseEO.getPcBudgetUseInfoEOList();
            double sum = infoEOList.stream().map(PcBudgetUseInfoEO::getBuSubtotal).map(Double::valueOf).collect(Collectors.summarizingDouble(Double::doubleValue)).getSum();
            pcBudgetUseEO.setTotal(String.valueOf(sum));
            pcBudgetUseEO.setBuProjectName(infoEOList.get(0).getBuProject());
            pcBudgetUseEO.setSupName(infoEOList.get(0).getBuSupplier());
            pcBudgetUseEO.setSupId(infoEOList.get(0).getBuSupplierId());
            this.getDao().insertSelective(pcBudgetUseEO);
            createPcBudgetUseEo(pcBudgetUseEO, date, userId);
        }
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcBudgetUseEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
        
        if("1".equals(flag)){
            //PV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetUseEO, ConstantUtils.PC_BUDGET_USE_TYPE_FORM,
            		ConstantUtils.PC_BUDGET_USE_TYPE, ConstantUtils.PROCESS_CODE, taskOrPlan + "费用使用申请");
        }else{
            //CV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetUseEO, ConstantUtils.CV_BUDGET_USE_TYPE,
            		ConstantUtils.CV_BUDGET_USE_TYPE, ConstantUtils.PROCESS_CODE, taskOrPlan + "费用使用申请");
        }
    }

    public void approvalActivity(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取业务信息
        PcBudgetUseEO budgetCashOutEO = this.getDao().selectByPrimaryKey(baseBusEO.getBusinessId());
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(budgetCashOutEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
        pcOutsourceProjectEOService.approve(request, requestEO, baseBusEO, taskOrPlan + "费用使用申请", budgetCashOutEO,
                this.getDao());
    }

    public List<PcBudgetUseEO> query(PcBudgetUseEOPage page) throws Exception {
        Integer rowCount = this.queryByCount(page);
        page.getPager().setRowCount(rowCount);
        List<PcBudgetUseEO> rows = this.getDao().queryByPage(page);
        if (CollectionUtils.isNotEmpty(rows)) {
            for (PcBudgetUseEO row : rows) {
                List<BaseBusEO> baseBusEOS = baseBusEODao.selectByBusinessId(row.getId());
                if (CollectionUtils.isNotEmpty(baseBusEOS)) {
                    row.setBusinessKey(baseBusEOS.get(0).getId());
                }
            }
        }
        return rows;
    }
}
