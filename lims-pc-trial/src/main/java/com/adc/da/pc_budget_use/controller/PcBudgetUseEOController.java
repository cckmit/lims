package com.adc.da.pc_budget_use.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_use.entity.PcBudgetUseEO;
import com.adc.da.pc_budget_use.page.PcBudgetUseEOPage;
import com.adc.da.pc_budget_use.service.PcBudgetUseEOService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/pc_budget_use/pcBudgetUse")
@Api(tags = "PC-费用使用申请")
public class PcBudgetUseEOController extends BaseController<PcBudgetUseEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcBudgetUseEOController.class);

    @Autowired
    private PcBudgetUseEOService pcBudgetUseEOService;

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

    @ApiOperation(value = "|PcBudgetUseEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_budget_use:pcBudgetUse:page")
    @EnableAccess
    public ResponseMessage<PageInfo<PcBudgetUseEO>> page(PcBudgetUseEOPage page) throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<PcBudgetUseEO> rows = pcBudgetUseEOService.query(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PcBudgetUseEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_budget_use:pcBudgetUse:list")
    @EnableAccess
    public ResponseMessage<List<PcBudgetUseEO>> list(PcBudgetUseEOPage page) throws Exception {
        return Result.success(pcBudgetUseEOService.queryByList(page));
    }

    @ApiOperation(value = "|PcBudgetUseEO|获取编号")
    @GetMapping("/getCode")
    @EnableAccess
    public ResponseMessage<String> getCode() {
        String code = pcOutsourceProjectEOService.getCode("DFLQUSE-");
        return Result.success(code);
    }

    @ApiOperation(value = "|PcBudgetUseEO|详情")
    @GetMapping("/{id}")
    // @RequiresPermissions("pc_budget_use:pcBudgetUse:get")
    public ResponseMessage<PcBudgetUseEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcBudgetUseEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PcBudgetUseEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    // @RequiresPermissions("pc_budget_use:pcBudgetUse:save")
    @EnableAccess
    public ResponseMessage<PcBudgetUseEO> create(@RequestBody PcBudgetUseEO pcBudgetUseEO) {
        try {
            pcBudgetUseEOService.create(pcBudgetUseEO);
            return Result.success("0", "新增成功", pcBudgetUseEO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.success("-1", "新增失败", pcBudgetUseEO);
        }
    }

    @ApiOperation(value = "|PcBudgetUseEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_budget_use:pcBudgetUse:update")
    @EnableAccess
    public ResponseMessage<PcBudgetUseEO> update(@RequestBody PcBudgetUseEO pcBudgetUseEO) throws Exception {
        pcBudgetUseEOService.update(pcBudgetUseEO);
        return Result.success("0", "保存成功", pcBudgetUseEO);
    }

    @ApiOperation(value = "|PcBudgetUseEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_budget_use:pcBudgetUse:delete")
    public ResponseMessage delete(@PathVariable String id) {
        pcBudgetUseEOService.delete(id);
        logger.info("delete from PC_BUDGET_USE where id = {}", id);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|PcBudgetUseEO|启动流程")
    @PostMapping("/activity")
    @RequiresPermissions("pc_budget_use:PcBudgetUseEO:activity")
    @EnableAccess
    public ResponseMessage activity(@RequestBody PcBudgetUseEO pcBudgetUseEO) throws Exception {
    	//判断下一节点是否有审批人
    	UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = null;
        if("1".equals(flag)){
            //PV
            procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE, ConstantUtils.PC_BUDGET_USE_TYPE).getDicTypeName();
        }else{
            //CV
            procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE, ConstantUtils.CV_BUDGET_USE_TYPE).getDicTypeName();
        }
    	NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
        	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
        	if(usersByRoleAndOrg.isEmpty())
        		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
    	
        pcBudgetUseEOService.submitActivity(pcBudgetUseEO);
        return Result.success("0", "流程启动成功");
    }

    @ApiOperation(value = "|PcBudgetUseEO|流程审批")
    @PostMapping("/approval")
    @RequiresPermissions("pc_budget_use:PcBudgetUseEO:approval")
    @EnableAccess
    public ResponseMessage approval(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
        //校验是否为空
        ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId", "taskId", "variables");
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
        
        pcBudgetUseEOService.approvalActivity(request, requestEO);
        if("reback".equals(approveCode)){
            return Result.success("0", "撤回成功");
        }else{
            return Result.success("0", "流程审批成功");
        }
    }
}
