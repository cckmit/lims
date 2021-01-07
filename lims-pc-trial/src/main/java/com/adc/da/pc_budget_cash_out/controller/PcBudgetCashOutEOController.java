package com.adc.da.pc_budget_cash_out.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_cash_out.entity.PcBudgetCashOutEO;
import com.adc.da.pc_budget_cash_out.page.PcBudgetCashOutEOPage;
import com.adc.da.pc_budget_cash_out.service.PcBudgetCashOutEOService;
import com.adc.da.pc_outsource.service.PcOutsourceProjectEOService;
import com.adc.da.sys.annotation.EnableAccess;
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
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.activiti.engine.RuntimeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/pc_budget_cash_out/pcBudgetCashOut")
@Api(tags = "PC-费用请款单")
public class PcBudgetCashOutEOController extends BaseController<PcBudgetCashOutEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetCashOutEOController.class);

    @Autowired
    private PcBudgetCashOutEOService pcBudgetCashOutEOService;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;
    
    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private OrgEOService orgEOService;

    @ApiOperation(value = "|PcBudgetCashOutEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:page")
    public ResponseMessage<PageInfo<PcBudgetCashOutEO>> page(PcBudgetCashOutEOPage page) throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<PcBudgetCashOutEO> rows = pcBudgetCashOutEOService.query(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:list")
    public ResponseMessage<List<PcBudgetCashOutEO>> list(PcBudgetCashOutEOPage page) throws Exception {
        return Result.success(pcBudgetCashOutEOService.queryByList(page));
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:get")
    public ResponseMessage<PcBudgetCashOutEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcBudgetCashOutEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|获取编号")
    @GetMapping("/getCode")
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:get")
    public ResponseMessage<String> getCode() {
        String code = pcOutsourceProjectEOService.getCode("DFLQCASHOUT-");
        return Result.success(code);
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:save")
    public ResponseMessage<PcBudgetCashOutEO> create(@RequestBody PcBudgetCashOutEO pcBudgetCashOutEO) {
        try {
            pcBudgetCashOutEOService.create(pcBudgetCashOutEO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.success("-1", "新增失败", pcBudgetCashOutEO);
        }
        return Result.success("0", "新增成功", pcBudgetCashOutEO);
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:update")
    public ResponseMessage<PcBudgetCashOutEO> update(@RequestBody PcBudgetCashOutEO pcBudgetCashOutEO) {
        try {
            pcBudgetCashOutEOService.update(pcBudgetCashOutEO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.success("-1", "修改失败", pcBudgetCashOutEO);
        }
        return Result.success("0", "修改成功", pcBudgetCashOutEO);
    }

    @ApiOperation(value = "|PcBudgetCashOutEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcBudgetCashOutEOService.delete(id);
        logger.info("delete from PC_BUDGET_CASH_OUT where id = {}", id);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|启动流程")
    @PostMapping("/activity")
    // @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:activity")
    public ResponseMessage activity(@RequestBody PcBudgetCashOutEO pcBudgetCashOutEO) {
        try {
        	//判断下一节点是否有审批人
        	UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
            OrgEO curOrg = curUser.getOrgEOList().get(0);
            String flag = orgEOService.getByOrgId(curOrg.getId());
            String procDefKey = null;
            if("1".equals(flag)){
                //PV
                procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                        ConstantUtils.PROCESS_CODE, ConstantUtils.PC_BUDGET_CASH_OUT_TYPE).getDicTypeName();
            }else{
                //CV
                procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                        ConstantUtils.PROCESS_CODE, ConstantUtils.CV_BUDGET_CASH_OUT_TYPE).getDicTypeName();
            }
        	NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
            String roleId = nextNodeInfo.getRoleId();
            String departId = nextNodeInfo.getDepartId();
            if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
            	if(usersByRoleAndOrg.isEmpty())
            		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
        	
            pcBudgetCashOutEOService.submitActivity(pcBudgetCashOutEO);
            return Result.success("0", "流程启动成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.success("-1", "流程启动失败");
        }
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|流程审批")
    @PostMapping("/approval")
    // @RequiresPermissions("pc_budget_cash_out:pcBudgetCashOut:approval")
    @EnableAccess
    public ResponseMessage approval(HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            //校验是否为空
            ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId", "variables");
            Map variables = requestEO.getVariables();
            String approveCode = (String) variables.get("approve");
            if (x != null) return x;
            
            //判断下一节点是否有审批人
            String condition = (String)requestEO.getVariables().get("approve");
            NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(null, requestEO.getTaskId(), condition);
            String roleId = nextNodeInfo.getRoleId();
            String departId = nextNodeInfo.getDepartId();
            if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            	String specialOrgId = (String)runtimeService.getVariable(requestEO.getProcId(), "specialOrgId");
            	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, specialOrgId);
            	if(usersByRoleAndOrg.isEmpty())
            		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            
            pcBudgetCashOutEOService.approvalActivity(request, requestEO);
            if("reback".equals(approveCode)){
                return Result.success("0", "撤回成功");
            }else{
                return Result.success("0", "流程审批成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.success("-1", "流程审批失败");
        }
    }


}
