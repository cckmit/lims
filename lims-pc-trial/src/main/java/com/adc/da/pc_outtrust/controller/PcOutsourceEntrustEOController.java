package com.adc.da.pc_outtrust.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_outtrust.entity.PcOutsourceEntrustEO;
import com.adc.da.pc_outtrust.page.PcOutsourceEntrustEOPage;
import com.adc.da.pc_outtrust.service.PcOutsourceEntrustEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.BaseBusEO;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/pc_outtrust/pcOutsourceEntrust")
@Api(tags = "|PcOutsourceEntrustEO-委外试验委托单|")
public class PcOutsourceEntrustEOController extends BaseController<PcOutsourceEntrustEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcOutsourceEntrustEOController.class);

    @Autowired
    private PcOutsourceEntrustEOService pcOutsourceEntrustEOService;
    @Autowired
    private BaseBusEODao baseBusEODao;
    @Autowired
    private TrialTaskEOService trialTaskEOService;
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

    @BusinessLog(description = "pc委外试验委托分页查询")
    @ApiOperation(value = "|PcOutsourceEntrustEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:page")
    public ResponseMessage<PageInfo<PcOutsourceEntrustEO>> page(PcOutsourceEntrustEOPage page) throws Exception {
        //添加根据trialTaskId，supId获取对应数据 根据需求变更
        List<PcOutsourceEntrustEO> rows = pcOutsourceEntrustEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "pc委外试验委托列表查询")
    @ApiOperation(value = "|PcOutsourceEntrustEO|查询")
    @GetMapping("")
//    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:list")
    public ResponseMessage<List<PcOutsourceEntrustEO>> list(PcOutsourceEntrustEOPage page) throws Exception {
        return Result.success(pcOutsourceEntrustEOService.queryByList(page));
    }

    @BusinessLog(description = "pc委外试验委托详情")
    @ApiOperation(value = "|PcOutsourceEntrustEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:get")
    public ResponseMessage<PcOutsourceEntrustEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcOutsourceEntrustEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc委外试验委托新增")
    @ApiOperation(value = "|PcOutsourceEntrustEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:save")
    public ResponseMessage<PcOutsourceEntrustEO> create(@RequestBody PcOutsourceEntrustEO pcOutsourceEntrustEO) throws Exception {
        // 0:草稿状态
        pcOutsourceEntrustEOService.insertSelective(pcOutsourceEntrustEO, "0");
        return Result.success("0", "操作成功！", pcOutsourceEntrustEO);
    }

    @BusinessLog(description = "pc委外试验委托启动流程")
    @ApiOperation(value = "|PcOutsourceEntrustEO|启动流程")
    @PostMapping("/startProcess")
    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:startProcess")
    public ResponseMessage<PcOutsourceEntrustEO> startProcess(@RequestBody PcOutsourceEntrustEO pcOutsourceEntrustEO) {
        try {
        	//判断下一节点是否有审批人
        	UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
            OrgEO curOrg = curUser.getOrgEOList().get(0);
            String flag = orgEOService.getByOrgId(curOrg.getId());
            String procDefKey = null;
            if("1".equals(flag)){
                //PV
                procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                        ConstantUtils.PROCESS_CODE, ConstantUtils.PV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE).getDicTypeName();
            }else{
                //CV
                procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                        ConstantUtils.PROCESS_CODE, ConstantUtils.CV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE).getDicTypeName();
            }
            NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
            String roleId = nextNodeInfo.getRoleId();
            String departId = nextNodeInfo.getDepartId();
            if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
            	if(usersByRoleAndOrg.isEmpty())
            		return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
        	
            pcOutsourceEntrustEOService.startProcess(pcOutsourceEntrustEO);
            return Result.success("0", "操作成功！", pcOutsourceEntrustEO);
        } catch (Exception e) {
            logger.error("-1", e.getStackTrace());
            return Result.error("-1", "操作失败！");
        }
    }

    @BusinessLog(description = "pc委外试验委托审批流程")
    @ApiOperation(value = "|PcOutsourceEntrustEO|审批流程")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:approvalProcess")
    @EnableAccess
    public ResponseMessage<RequestEO> approvalProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            if (StringUtils.isNotEmpty(requestEO)) {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "缺少业务Id");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-4", "缺少审批意见");
                }
                
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
                
                BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
                pcOutsourceEntrustEOService.approvalProcess(request, requestEO, baseBusEO);
                pcOutsourceEntrustEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
                return Result.success("0", "审批成功");
            } else {
                logger.error("-1", "流程审批失败！");
                return Result.error("-1", "流程审批失败！");
            }
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }
    }


    @BusinessLog(description = "pc委外试验委托编辑")
    @ApiOperation(value = "|PcOutsourceEntrustEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:update")
    public ResponseMessage<PcOutsourceEntrustEO> update(@RequestBody PcOutsourceEntrustEO pcOutsourceEntrustEO) throws Exception {
        pcOutsourceEntrustEOService.updateByPrimaryKeySelective(pcOutsourceEntrustEO);
        return Result.success("0", "操作成功！", pcOutsourceEntrustEO);
    }

    @BusinessLog(description = "pc委外试验委托删除")
    @ApiOperation(value = "|PcOutsourceEntrustEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcOutsourceEntrustEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_OUTSOURCE_ENTRUST where id = {}", id);
        return Result.success("0", "操作成功！");
    }

    @BusinessLog(description = "pc委外试验委托导出")
    @ApiOperation(value = "|PcOutsourceEntrustEO|导出")
    @GetMapping("/exportDoc")
//    @RequiresPermissions("pc_outtrust:pcOutsourceEntrust:exportDoc")
    public ResponseMessage<String> exportDoc(String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            PcOutsourceEntrustEO pcOutsourceEntrustEO = pcOutsourceEntrustEOService.selectByPrimaryKey(id);
            pcOutsourceEntrustEOService.exportDoc(response, request, pcOutsourceEntrustEO);
            return Result.success("0", "导出成功！");
        } catch (Exception e) {
            return Result.error("-1", "导出失败！");
        }
    }

    @BusinessLog(description = "pc委外试验委托获取最新流水号")
    @ApiOperation(value = "|PcOutsourceEntrustEO|获取最新编号")
    @GetMapping("/queryOutTrustCode")
    public ResponseMessage<String> queryOutTrustCode() {
        String outTrustCode = pcOutsourceEntrustEOService.queryOutTrustCode();
        return Result.success("0", "操作成功！", outTrustCode);
    }

    @BusinessLog(description = "pc委外试验委托获取任务来源编号")
    @ApiOperation(value = "|PcOutsourceEntrustEO|获取任务来源编号")
    @GetMapping("/findTaskTrustCode")
    public ResponseMessage<String> findTaskTrustCode(String taskId) {
        try {
            TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(taskId);
            return Result.success("0", "操作成功！", trialTaskEO.getTaskCode());
        } catch (Exception e) {
            logger.error("-1", e.getStackTrace());
            return Result.error("-1", "获取任务来源编号失败！");
        }
    }

    @BusinessLog(description = "pc委外立项＆pc可靠性立项获取编号集合")
    @ApiOperation(value = "|PcOutsourceEntrustEO|获取编号集合")
    @GetMapping("/getInitCodeAndOpCodeList")
    public ResponseMessage<List<String>> getInitCodeAndOpCodeList() {
        List<String> code = pcOutsourceEntrustEOService.queryCodeList();
        return Result.success("0", "操作成功！", code);
    }
}
