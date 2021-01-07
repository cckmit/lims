package com.adc.da.pc_budget_reimbursement.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.common.utils.ZipUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_reimbursement.entity.PcBudgetReimbursementEO;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialProductEO;
import com.adc.da.pc_budget_reimbursement.page.PcBudgetReimbursementEOPage;
import com.adc.da.pc_budget_reimbursement.service.PcBudgetReimbursementEOService;
import com.adc.da.pc_budget_reimbursement.vo.PcTrialProductInfoVO;
import com.adc.da.pc_budget_reimbursement.vo.PcTrialProductVO;
import com.adc.da.pc_execute.entity.PCBudgetPersonEO;
import com.adc.da.pc_execute.entity.PCBudgetTestEO;
import com.adc.da.pc_execute.service.PCBudgetCostEOService;
import com.adc.da.pc_execute.vo.PCBudgetCostVO;
import com.adc.da.pc_outsource.service.PcOutsourceProjectEOService;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.adc.da.workflow.vo.NextGroupUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.activiti.engine.RuntimeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/pc_budget_reimbursement/pcBudgetReimbursement")
@Api(tags = "PC-费用报销申请")
public class PcBudgetReimbursementEOController extends BaseController<PcBudgetReimbursementEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetReimbursementEOController.class);

    @Autowired
    private PcBudgetReimbursementEOService pcBudgetReimbursementEOService;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;

    @Autowired
    private PCBudgetCostEOService pcBudgetCostEOService;
    
    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private OrgEOService orgEOService;
    
    @Autowired
    private BaseBusEODao baseBusEODao;
    

    @Value("${file.path}")
    private String filepath;

    @ApiOperation(value = "|PcBudgetReimbursementEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:page")
    @EnableAccess
    public ResponseMessage<PageInfo<PcBudgetReimbursementEO>> page(PcBudgetReimbursementEOPage page) throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<PcBudgetReimbursementEO> rows = pcBudgetReimbursementEOService.query(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PcBudgetReimbursementEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:list")
    public ResponseMessage<List<PcBudgetReimbursementEO>> list(PcBudgetReimbursementEOPage page) throws Exception {
        return Result.success(pcBudgetReimbursementEOService.queryByList(page));
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|获取编号")
    @GetMapping("/getCode")
    @EnableAccess
    public ResponseMessage<String> getCode() {
        String code = pcOutsourceProjectEOService.getCode("DFLQREIMBURSEMENT-");
        return Result.success(code);
    }

    @ApiOperation(value = "|PcBudgetReimbursementEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:get")
    @EnableAccess
    public ResponseMessage<PcBudgetReimbursementEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcBudgetReimbursementEOService.find(id));
    }

    @ApiOperation(value = "|PcBudgetReimbursementEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:save")
    @EnableAccess
    public ResponseMessage<PcBudgetReimbursementEO> create(@RequestBody PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
        pcBudgetReimbursementEOService.create(pcBudgetReimbursementEO);
        return Result.success("0", "新增成功", pcBudgetReimbursementEO);
    }

    @ApiOperation(value = "|PcBudgetReimbursementEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:update")
    @EnableAccess
    public ResponseMessage<PcBudgetReimbursementEO> update(@RequestBody PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
        pcBudgetReimbursementEOService.update(pcBudgetReimbursementEO);
        return Result.success("0", "修改成功", pcBudgetReimbursementEO);
    }

    @ApiOperation(value = "|PcBudgetReimbursementEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:delete")
    public ResponseMessage delete(@PathVariable String id) {
        pcBudgetReimbursementEOService.delete(id);
        logger.info("delete from PC_BUDGET_REIMBURSEMENT where id = {}", id);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|启动流程")
    @PostMapping("/activity")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:activity")
    @EnableAccess
    public ResponseMessage activity(@RequestBody PcBudgetReimbursementEO pcBudgetReimbursementEO) throws Exception {
    	//判断下一节点是否有审批人
    	UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = null;
        if("1".equals(flag)){
            //PV
            procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE, ConstantUtils.PC_BUDGET_REIMBURSEMENT_TYPE).getDicTypeName();
        }else{
            //CV
            procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE, ConstantUtils.CV_BUDGET_REIMBURSEMENT_TYPE).getDicTypeName();
        }
    	NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
        	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
        	if(usersByRoleAndOrg.isEmpty())
        		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
    	
        pcBudgetReimbursementEOService.submitActivity(pcBudgetReimbursementEO);
        return Result.success("0", "流程启动成功");
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|流程审批")
    @PostMapping("/approval")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:approval")
    @EnableAccess
    public ResponseMessage approval(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
        //校验是否为空
        ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId", "variables");
        if (x != null) return x;
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        
        //判断下一节点是否有审批人
        NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(null, requestEO.getTaskId(), approveCode);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
        	String specialOrgId = (String)runtimeService.getVariable(requestEO.getProcId(), "specialOrgId");
        	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, specialOrgId);
        	if(usersByRoleAndOrg.isEmpty())
        		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
        
        pcBudgetReimbursementEOService.approvalActivity(request, requestEO);
        if("reback".equals(approveCode)){
            return Result.success("0", "撤回成功");
        }else{
            return Result.success("0", "流程审批成功");
        }
    }

    /**
     * PSQC/性能试验 费用表单页数据-`查询
     *
     * @param trialTaskId
     * @return ResponseMessage<PCBudgetCostVO>
     * @Title: costFormData
     * @date: 2019年12月13日
     */
    @ApiOperation(value = "|PSQC/性能试验费用表单页数据查询")
    @BusinessLog(description = "PSQC/性能试验费用表单页数据-`查询")
    @GetMapping("/costFormData")
    @RequiresPermissions("pc:trialExecute:costFormData")
    public ResponseMessage<PcTrialProductEO> costFormData(String trialTaskId) {
        //先按trialTaskId 分别查三个,没有就查 基础   然后返回, 主表附件等信息 一并返回
        try {
            PCBudgetCostVO vo = pcBudgetCostEOService.costFormData(trialTaskId);
            PcTrialProductEO eo = new PcTrialProductEO();
            if (vo != null) {
                for (PCBudgetPersonEO pcBudgetPersonEO : vo.getPcBudgetPersonList()) {
                    if ("AccommodationCost".equals(pcBudgetPersonEO.getItemsCode())) {
                        eo.setAccommodationApply(pcBudgetPersonEO.getStdPrice());
                    }
                }
                for (PCBudgetTestEO pcBudgetTestEO : vo.getPcBudgetTestList()) {
                    if ("HighSpeedToll".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setHighSpeedApply(pcBudgetTestEO.getStdPrice());
                    }
                    if ("CashPlusPostage".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setRefuelCost(pcBudgetTestEO.getStdPrice());
                    }
                    if ("CashCharge".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setChargeApply(pcBudgetTestEO.getStdPrice());
                    }
                    if ("CashRepairFee".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setMaintainCost(pcBudgetTestEO.getStdPrice());
                    }
                    if ("CashMaintenanceFee".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setUpkeepCost(pcBudgetTestEO.getStdPrice());
                    }
                    if ("TransitionFee".equals(pcBudgetTestEO.getItemsCode())) {
                        eo.setPassWayCost(pcBudgetTestEO.getStdPrice());
                    }
                    if ("InsuranceCost".equals(pcBudgetTestEO.getItemsCode())) {
                        if (StringUtils.isNotBlank(eo.getPcOtherApply())) {
                            int i = Integer.parseInt(eo.getPcOtherApply());
                            int i1 = Integer.parseInt(pcBudgetTestEO.getStdPrice());
                            eo.setPcOtherApply(String.valueOf(i + i1));
                        } else {
                            eo.setPcOtherApply(pcBudgetTestEO.getStdPrice());
                        }
                    }
                    if ("TemporaryCard".equals(pcBudgetTestEO.getItemsCode())) {
                        if (StringUtils.isNotBlank(eo.getPcOtherApply())) {
                            int i = Integer.parseInt(eo.getPcOtherApply());
                            int i1 = Integer.parseInt(pcBudgetTestEO.getStdPrice());
                            eo.setPcOtherApply(String.valueOf(i + i1));
                        } else {
                            eo.setPcOtherApply(pcBudgetTestEO.getStdPrice());
                        }
                    }
                }
            }
            return Result.success("0", "查询成功!", eo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|导出")
    @GetMapping("/export")
    @RequiresPermissions("pc_budget_reimbursement:pcBudgetReimbursement:export")
    public void downloadZip(HttpServletResponse response,
                            @ApiParam(value = "费用报销申请单ID", required = true) @RequestParam("id") String id
    ) {
        // 查询要导出的内容
        PcBudgetReimbursementEO reimbursementEO = pcBudgetReimbursementEOService.find(id);
        if (reimbursementEO != null) {
            // 查询任务书下有多少台架
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                        "费用报销申请.zip", "UTF-8"));
                List<File> fileList = new ArrayList<>();
                // 查询当前登录人的组织机构
                StringBuilder path = new StringBuilder();
                path.append(DateUtils.dateToString(new Date(),
                        "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator));
                String tempPath = this.filepath;
                if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                    tempPath = this.filepath + File.separator;
                }
                String filePath = tempPath + path;
                Map<String, Object> map = new HashMap<>();
                PcTrialProductVO vo = settingTrialProductVO(reimbursementEO);
                map.put("obj", reimbursementEO);
                map.put("vo", vo);
                fileList.add(docUtil.createDoc(map, "trial_workday",
                        filePath + "试验工作日明细表.doc"));
                fileList.add(docUtil.createDoc(map, "trial_product",
                        filePath + "产品试验报销明细账.doc"));
                zipOutputStream.setEncoding("GBK");
                if (CollectionUtils.isEmpty(fileList) == false) {
                    for (int i = 0, size = fileList.size(); i < size; i++) {
                        ZipUtils.compress(fileList.get(i), zipOutputStream, "");
                    }
                }
                // 冲刷输出流
                zipOutputStream.flush();
                zipOutputStream.close();
                if (fileList != null && !fileList.isEmpty()) {
                    for (File file : fileList) {
                        file.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PcTrialProductVO settingTrialProductVO(PcBudgetReimbursementEO reimbursementEO) throws Exception {
        PcTrialProductVO vo = new PcTrialProductVO();
        // 获取出差人信息
        UserEO user = userEOService.getUserWithRoles(reimbursementEO.getCreateBy());
        if (user != null) {
            vo.setBusinessOrg(user.getOrgEOList().get(0).getName());
            // 出差人
            vo.setBusinessPeople(user.getUsname());
            // 出差人编号
            vo.setBusinessCode(user.getUserCode());
        }
        // 出差任务
        vo.setBusinessTask("");
        // 费用报销申请单编号
        vo.setCode(reimbursementEO.getCode());
      //------------流程信息-------------------//
        String actProcId = pcBudgetReimbursementEOService.selectActProcIdById(reimbursementEO.getId());
        String Virifier = "";
        String Approver = "";
        if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
    		 List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
    		 Virifier = list.get(1).getAssigneeName();
    		 Approver = list.get(0).getAssigneeName();
        }
        // 审核人
        vo.setVirifier(Virifier);
        // 批准人
        vo.setApprover(Approver);
        // 是否退款
        vo.setRefund("");
        // 请款人
        vo.setRequestName("");
        // 请款金额
        vo.setRequestAmount("");
        // 当前登录人
        vo.setOnlineUserName(UserUtils.getUser().getUsname());
        settingProductInfo(reimbursementEO, vo);
        return vo;
    }

    private void settingProductInfo(PcBudgetReimbursementEO reimbursementEO, PcTrialProductVO vo) {
        List<PcTrialProductEO> list = reimbursementEO.getPcTrialProductEOList();
        if (list != null) {
            PcTrialProductEO productEO = list.get(list.size() - 1);
            // 过路过桥费
            vo.setPassWayTotal(productEO.getPassWayApply());
            // 停车费
            vo.setParkTotal(productEO.getParkApply());
            // 维修费
            vo.setMaintainTotal(productEO.getMaintainApply());
            // 快递费
            vo.setExpressTotal(productEO.getExpressApply());
            // 租赁费
            vo.setHireTotal(productEO.getHireApply());
            // 其他费用
            vo.setPcOtherTotal(productEO.getPcOtherApply());
            // 投票数
            vo.setPollNmber(productEO.getPollNmber());
            // 加油、加气、充电费
            Integer total = Integer.parseInt(productEO.getAirEntrappingApply())
                    + Integer.parseInt(productEO.getChargeApply()) + Integer.parseInt(productEO.getRefuelApply());
            vo.setRefuelChargeAirEntrappingTotal(total.toString());
            // 金额总计
            Integer all = Integer.parseInt(productEO.getPassWayApply()) + Integer.parseInt(productEO.getParkApply())
                    + Integer.parseInt(productEO.getMaintainApply() + Integer.parseInt(productEO.getExpressApply())
                    + Integer.parseInt(productEO.getHireApply()) + Integer.parseInt(productEO.getPcOtherApply()))
                    + total;
            vo.setAmountTotal(all.toString());
            settingProductInfo(vo, list);
        }
    }

    private void settingProductInfo(PcTrialProductVO vo, List<PcTrialProductEO> list) {
        List<PcTrialProductInfoVO> infoVOList = new ArrayList<>();
        for (PcTrialProductEO eo : list) {
            PcTrialProductInfoVO infoVO = new PcTrialProductInfoVO();
            // 开始时间
            infoVO.setStartMonth(eo.getStartTime().substring(5, 7));
            infoVO.setStartDay(eo.getStartTime().substring(8, 10));
            infoVO.setStartHour(eo.getStartTime().substring(11, 13));
            // 结束时间
            infoVO.setEndMonth(eo.getEndTime().substring(5, 7));
            infoVO.setEndDay(eo.getEndTime().substring(8, 10));
            infoVO.setEndHour(eo.getEndTime().substring(11, 13));
            infoVO.setStartPoint(eo.getStartPoint());
            infoVO.setEndPoint(eo.getEndPoint());
            infoVO.setUnderpanNo(eo.getUnderpanNo());
            infoVO.setPassWayCost(eo.getPassWayApply());
            // 加油、加气、充电费用总计
            Integer total1 = Integer.parseInt(eo.getRefuelApply()) + Integer.parseInt(eo.getChargeApply())
                    + Integer.parseInt(eo.getAirEntrappingApply());
            infoVO.setRefuelChargeAirEntrappingCost(total1.toString());
            infoVO.setParkCost(eo.getParkApply());
            infoVO.setMaintainCost(eo.getMaintainApply());
            infoVO.setExpressCost(eo.getExpressApply());
            infoVO.setHireCost(eo.getHireApply());
            infoVO.setPcOtherCost(eo.getPcOtherApply());
            infoVO.setPollNmber(eo.getPollNmber());
            infoVOList.add(infoVO);
        }
        vo.setPcTrialProductInfoVOList(infoVOList);
    }

}
