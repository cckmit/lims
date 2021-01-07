package com.adc.da.pc_budget_reimbursement.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_reimbursement.dao.PcBudgetReimbursementEODao;
import com.adc.da.pc_budget_reimbursement.entity.PcBudgetReimbursementEO;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialProductEO;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialWorkdayEO;
import com.adc.da.pc_budget_reimbursement.page.PcBudgetReimbursementEOPage;
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


/**
 * <br>
 * <b>功能：</b>PC_BUDGET_REIMBURSEMENT PcBudgetReimbursementEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcBudgetReimbursementEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcBudgetReimbursementEOService extends BaseService<PcBudgetReimbursementEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetReimbursementEOService.class);

    @Autowired
    private PcBudgetReimbursementEODao dao;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private OrgEOService orgEOService;

    public PcBudgetReimbursementEODao getDao() {
        return dao;
    }

    @Autowired
    private PcTrialProductEOService pcTrialProductEOService;

    @Autowired
    private PcTrialWorkdayEOService pcTrialWorkdayEOService;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    public void delete(String id) {
        pcTrialProductEOService.deleteByRid(id);
        pcTrialWorkdayEOService.deleteByRid(id);
        this.getDao().deleteByPrimaryKey(id);
    }

    public void submitActivity(PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetReimbursementEO.setStatus(PcOutsourceEnum.UN_APPROVAL.getValue());
        if (StringUtils.isNotBlank(pcBudgetReimbursementEO.getId())) {
            // 修改，提交
            pcBudgetReimbursementEO.setUpdateTime(date);
            pcBudgetReimbursementEO.setUpdateBy(userId);
            this.getDao().updateByPrimaryKeySelective(pcBudgetReimbursementEO);
            // 删除子表信息，再新增
            settingUpdateEo(pcBudgetReimbursementEO, userId, date);
        } else {
            // 新增，提交
            settingEo(pcBudgetReimbursementEO, userId, date);
        }
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcBudgetReimbursementEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
		
        if("1".equals(flag)){
            //PV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetReimbursementEO, ConstantUtils.PC_BUDGET_REIMBURSEMENT_TYPE_FORM,
            		ConstantUtils.PC_BUDGET_REIMBURSEMENT_TYPE, ConstantUtils.PROCESS_CODE, taskOrPlan + "费用报销申请单");
        }else{
            //CV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetReimbursementEO, ConstantUtils.CV_BUDGET_REIMBURSEMENT_TYPE,
            		ConstantUtils.CV_BUDGET_REIMBURSEMENT_TYPE, ConstantUtils.PROCESS_CODE, taskOrPlan + "费用报销申请单");
        }
    }

    public void approvalActivity(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取业务信息
        PcBudgetReimbursementEO budgetCashOutEO = this.getDao().selectByPrimaryKey(baseBusEO.getBusinessId());
        //0-试验任务；1-执行计划
        String taskOrPlan = "";
        if("1".equals(budgetCashOutEO.getTaskOrPlan())) {
        	taskOrPlan = "执行计划-";
        }else {
        	taskOrPlan = "试验任务-";
        }
        pcOutsourceProjectEOService.approve(request, requestEO, baseBusEO, taskOrPlan + "费用报销申请单", budgetCashOutEO,
                this.getDao());
    }

    public void create(PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetReimbursementEO.setStatus(PcOutsourceEnum.DRAFT.getValue());
        settingEo(pcBudgetReimbursementEO, userId, date);
    }

    private void settingEo(PcBudgetReimbursementEO pcBudgetReimbursementEO, String userId, String date) throws Exception {
        createEo(pcBudgetReimbursementEO, userId, date);
        // 保存产品试验报销明细
        boolean b = false;
        if (CollectionUtils.isNotEmpty(pcBudgetReimbursementEO.getPcTrialProductEOList())) {
            b = true;
            Double total = 0.00;
            for (PcTrialProductEO productEO : pcBudgetReimbursementEO.getPcTrialProductEOList()) {
                total += Double.parseDouble(productEO.getSubtotal());
            }
            pcBudgetReimbursementEO.setBudgetTotal(total.toString());
        }
        this.getDao().insertSelective(pcBudgetReimbursementEO);
        // 保存试验工作日明细
        if (CollectionUtils.isNotEmpty(pcBudgetReimbursementEO.getPcTrialWorkdayEOList())) {
            for (PcTrialWorkdayEO workdayEO : pcBudgetReimbursementEO.getPcTrialWorkdayEOList()) {
                workdayEO.setRId(pcBudgetReimbursementEO.getId());
                createEo(workdayEO, userId, date);
                pcTrialWorkdayEOService.insertSelective(workdayEO);
            }
        }
        if (b) {
            for (PcTrialProductEO productEO : pcBudgetReimbursementEO.getPcTrialProductEOList()) {
                productEO.setrId(pcBudgetReimbursementEO.getId());
                createEo(productEO, userId, date);
                pcTrialProductEOService.insertSelective(productEO);
            }
        }
    }

    private void createEo(Object eo, String userId, String date) throws Exception {
        Class<? extends Object> eoClass = eo.getClass();
        eoClass.getDeclaredMethod("setId", String.class).invoke(eo, UUID.randomUUID10());
        eoClass.getDeclaredMethod("setCreateBy", String.class).invoke(eo, userId);
        eoClass.getDeclaredMethod("setCreateTime", String.class).invoke(eo, date);
        eoClass.getDeclaredMethod("setUpdateBy", String.class).invoke(eo, userId);
        eoClass.getDeclaredMethod("setUpdateTime", String.class).invoke(eo, date);
        eoClass.getDeclaredMethod("setDelFlag", Integer.class).invoke(eo, DeleteFlagEnum.NORMAL.getValue());
    }

    public void update(PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetReimbursementEO.setUpdateBy(userId);
        pcBudgetReimbursementEO.setUpdateTime(date);
        this.getDao().updateByPrimaryKeySelective(pcBudgetReimbursementEO);
        // 删除子表信息，再新增
        settingUpdateEo(pcBudgetReimbursementEO, userId, date);
    }

    private void settingUpdateEo(PcBudgetReimbursementEO pcBudgetReimbursementEO, String userId, String date) throws Exception {
        // 删除工作日明细
        pcTrialWorkdayEOService.deleteByRid(pcBudgetReimbursementEO.getId());
        if (CollectionUtils.isNotEmpty(pcBudgetReimbursementEO.getPcTrialWorkdayEOList())) {
            for (PcTrialWorkdayEO workdayEO : pcBudgetReimbursementEO.getPcTrialWorkdayEOList()) {
                createEo(workdayEO, userId, date);
                workdayEO.setRId(pcBudgetReimbursementEO.getId());
                pcTrialWorkdayEOService.insertSelective(workdayEO);
            }
        }
        // 删除产品报销明细
        pcTrialProductEOService.deleteByRid(pcBudgetReimbursementEO.getId());
        if (CollectionUtils.isNotEmpty(pcBudgetReimbursementEO.getPcTrialProductEOList())) {
            for (PcTrialProductEO productEO : pcBudgetReimbursementEO.getPcTrialProductEOList()) {
                createEo(productEO, userId, date);
                productEO.setRId(pcBudgetReimbursementEO.getId());
                pcTrialProductEOService.insertSelective(productEO);
            }
        }
    }

    public PcBudgetReimbursementEO find(String id) {
        PcBudgetReimbursementEO reimbursementEO = this.getDao().selectByPrimaryKey(id);
        if (reimbursementEO != null) {
            // 查询工作日明细
            List<PcTrialWorkdayEO> workdayEOList = pcTrialWorkdayEOService.selectByRid(id);
            reimbursementEO.setPcTrialWorkdayEOList(workdayEOList);
            // 查询产品报销明细
            List<PcTrialProductEO> productEOList = pcTrialProductEOService.selectByRid(id);
            reimbursementEO.setPcTrialProductEOList(productEOList);
        }
        return reimbursementEO;
    }

    public String findTotalByTrialId(String trialId) {
        return this.getDao().findTotalByTrialId(trialId);
    }

    public List<PcBudgetReimbursementEO> query(PcBudgetReimbursementEOPage page) throws Exception {
        Integer rowCount = this.queryByCount(page);
        page.getPager().setRowCount(rowCount);
        List<PcBudgetReimbursementEO> rows = this.getDao().queryByPage(page);
        if (CollectionUtils.isNotEmpty(rows)) {
            for (PcBudgetReimbursementEO row : rows) {
                List<BaseBusEO> baseBusEOS = baseBusEODao.selectByBusinessId(row.getId());
                if (CollectionUtils.isNotEmpty(baseBusEOS)) {
                    row.setBusinessKey(baseBusEOS.get(0).getId());
                }
            }
        }
        return rows;
    }
    
    /**
     * 根据业务id 获取流程相关信息
     * @Title: selectActProcIdById
     * @param id
     * @return
     * @return String
     * @author: ljy
     * @date: 2020年4月22日
     */
    public String selectActProcIdById(String id) {
    	return dao.selectActProcIdById(id);
    }
}
