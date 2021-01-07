package com.adc.da.pc_budget_cash_out.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_cash_out.dao.PcBudgetCashOutEODao;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_budget_cash_out.entity.PcBudgetCashOutEO;
import com.adc.da.pc_budget_cash_out.page.PcBudgetCashOutEOPage;
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
 * <b>功能：</b>PC_BUDGET_CASH_OUT PcBudgetCashOutEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcBudgetCashOutEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcBudgetCashOutEOService extends BaseService<PcBudgetCashOutEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetCashOutEOService.class);

    @Autowired
    private PcBudgetCashOutEODao dao;

    public PcBudgetCashOutEODao getDao() {
        return dao;
    }

    @Autowired
    private PcAutoPayForEOService pcAutoPayForEOService;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;
    
    @Autowired
    private UserEOService userEOService;
    @Autowired
    private OrgEOService orgEOService;

    public void create(PcBudgetCashOutEO pcBudgetCashOutEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetCashOutEO.setStatus(PcOutsourceEnum.DRAFT.getValue());
        insertPcBudgetCashOut(pcBudgetCashOutEO, userId, date);
        // 新增子表信息
        if (CollectionUtils.isNotEmpty(pcBudgetCashOutEO.getAutoPayForEOList())) {
            createPcAutoPayFor(pcBudgetCashOutEO, userId, date);
        }
    }

    private void insertPcBudgetCashOut(PcBudgetCashOutEO pcBudgetCashOutEO, String userId, String date) {
        pcBudgetCashOutEO.setId(UUID.randomUUID10());
        pcBudgetCashOutEO.setCreateBy(userId);
        pcBudgetCashOutEO.setCreateTime(date);
        pcBudgetCashOutEO.setUpdateBy(userId);
        pcBudgetCashOutEO.setUpdateTime(date);
        pcBudgetCashOutEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        pcBudgetCashOutEO.setApplyDate(date);
        this.getDao().insertSelective(pcBudgetCashOutEO);
    }

    public void update(PcBudgetCashOutEO pcBudgetCashOutEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetCashOutEO.setUpdateBy(userId);
        pcBudgetCashOutEO.setUpdateTime(date);
        this.getDao().updateByPrimaryKeySelective(pcBudgetCashOutEO);
        // 删除子表信息
        pcAutoPayForEOService.deleteByBcoId(pcBudgetCashOutEO.getId());
        if (CollectionUtils.isNotEmpty(pcBudgetCashOutEO.getAutoPayForEOList())) {
            createPcAutoPayFor(pcBudgetCashOutEO, userId, date);
        }
    }

    public void createPcAutoPayFor(PcBudgetCashOutEO pcBudgetCashOutEO, String userId, String date) throws Exception {
        for (PcAutoPayForEO pcAutoPayForEO : pcBudgetCashOutEO.getAutoPayForEOList()) {
            pcAutoPayForEO.setId(UUID.randomUUID10());
            pcAutoPayForEO.setBcoId(pcBudgetCashOutEO.getId());
            pcAutoPayForEO.setCreateBy(userId);
            pcAutoPayForEO.setCreateTime(date);
            pcAutoPayForEO.setUpdateBy(userId);
            pcAutoPayForEO.setUpdateTime(date);
            pcAutoPayForEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            pcAutoPayForEOService.insertSelective(pcAutoPayForEO);
        }
    }

    public void delete(String id) {
        pcAutoPayForEOService.deleteByBcoId(id);
        this.getDao().deleteByPrimaryKey(id);
    }

    public void submitActivity(PcBudgetCashOutEO pcBudgetCashOutEO) throws Exception {
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcBudgetCashOutEO.setStatus(PcOutsourceEnum.UN_APPROVAL.getValue());
        if (StringUtils.isNotBlank(pcBudgetCashOutEO.getId())) {
            pcBudgetCashOutEO.setUpdateTime(date);
            pcBudgetCashOutEO.setUpdateBy(userId);
            this.getDao().updateByPrimaryKeySelective(pcBudgetCashOutEO);
            // 删除子表信息
            pcAutoPayForEOService.deleteByBcoId(pcBudgetCashOutEO.getId());
            // 新增子表信息
            if (CollectionUtils.isNotEmpty(pcBudgetCashOutEO.getAutoPayForEOList())) {
                createPcAutoPayFor(pcBudgetCashOutEO, userId, date);
            }
        } else {
            insertPcBudgetCashOut(pcBudgetCashOutEO, userId, date);
            // 新增子表信息
            if (CollectionUtils.isNotEmpty(pcBudgetCashOutEO.getAutoPayForEOList())) {
                createPcAutoPayFor(pcBudgetCashOutEO, userId, date);
            }
        }
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String title = "试验任务-费用请款单";
        String taskOrPlan = pcBudgetCashOutEO.getTaskOrPlan();
        if (StringUtils.isNotEmpty(taskOrPlan) && "1".equals(taskOrPlan)){
            title = "执行计划-费用请款单";
        }
        if("1".equals(flag)){
            //PV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetCashOutEO, ConstantUtils.PC_BUDGET_CASH_OUT_TYPE_FORM,
            		ConstantUtils.PC_BUDGET_CASH_OUT_TYPE, ConstantUtils.PROCESS_CODE, title);
        }else{
            //CV
            pcOutsourceProjectEOService.submit(this.getDao(), pcBudgetCashOutEO, ConstantUtils.CV_BUDGET_CASH_OUT_TYPE,
            		ConstantUtils.CV_BUDGET_CASH_OUT_TYPE, ConstantUtils.PROCESS_CODE, title);
        }
    }

    public void approvalActivity(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取业务信息
        PcBudgetCashOutEO budgetCashOutEO = this.getDao().selectByPrimaryKey(baseBusEO.getBusinessId());
        String title = "试验任务-费用请款单";
        String taskOrPlan = budgetCashOutEO.getTaskOrPlan();
        if (StringUtils.isNotEmpty(taskOrPlan) && "1".equals(taskOrPlan)){
            title = "执行计划-费用请款单";
        }
        pcOutsourceProjectEOService.approve(request, requestEO, baseBusEO, title, budgetCashOutEO,
                this.getDao());
    }

    /**
     * @param page
     * @return
     */
    public List<PcBudgetCashOutEO> query(PcBudgetCashOutEOPage page) throws Exception {
        Integer rowCount = this.queryByCount(page);
        page.getPager().setRowCount(rowCount);
        List<PcBudgetCashOutEO> rows = this.getDao().queryByPage(page);
        if (CollectionUtils.isNotEmpty(rows)) {
            for (PcBudgetCashOutEO row : rows) {
                List<BaseBusEO> baseBusEOS = baseBusEODao.selectByBusinessId(row.getId());
                if (CollectionUtils.isNotEmpty(baseBusEOS)) {
                    row.setBusinessKey(baseBusEOS.get(0).getId());
                }
            }
        }
        return rows;
    }
}
