package com.adc.da.task.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.ExportDataUtil;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.project.entity.TrialProjectEO;
import com.adc.da.supplier.dao.AbilityEODao;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.task.dto.SupplierTaskApplyDTO;
import com.adc.da.task.dto.SupplierTaskFinishInfoDTO;
import com.adc.da.task.entity.SupplierTaskApplyEO;
import com.adc.da.task.page.SupplierTaskApplyEOPage;
import com.adc.da.task.page.SupplierTaskExeutionEOPage;
import com.adc.da.task.page.SupplierTaskStatisticsPage;
import com.adc.da.task.service.SupplierTaskApplyEOService;
import com.adc.da.task.service.SupplierTaskFinishInfoEOService;
import com.adc.da.task.vo.*;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/supplier/supplierTaskApply")
@Api(tags = "Sup-供应商任务委托")
public class SupplierTaskApplyEOController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierTaskApplyEOController.class);

    @Autowired
    private SupplierTaskApplyEOService supplierTaskApplyEOService;

    @Autowired
    private SupplierTaskFinishInfoEOService supplierTaskFinishInfoEOService;

    @Autowired
    private OrgEODao orgEODao;

    @Autowired
    private AbilityEODao abilityEODao;

    @Autowired
    private com.adc.da.pc_execute.service.PVCVService PVCVService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;


    @ApiOperation(value = "|SupplierTaskApplyEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("supplier:supplierTaskApply:page")
    public ResponseMessage<PageInfo> page(SupplierTaskApplyEOPage page) {
        page.setCreateBy(UserUtils.getUserId());
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<SupplierTaskApplyPageVO> taskApplyListVOList = supplierTaskApplyEOService.selectByPage(page);
        return Result.success(getPageInfo(page.getPager(), taskApplyListVOList));
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("supplier:supplierTaskApply:get")
    public ResponseMessage<SupplierTaskApplyVO> find(@PathVariable String id) {
        return Result.success(supplierTaskApplyEOService.selectVOById(id));
    }

    @ApiOperation(value = "|SupplierTaskFinishInfoVO|供应商任务完成信息详情")
    @GetMapping("findSupplierTaskFinishInfo/{id}")
    @RequiresPermissions("supplierTaskFinishInfo:findSupplierTaskFinishInfo:get")
    public ResponseMessage<SupplierTaskFinishInfoVO> findSupplierTaskFinishInfo(@PathVariable String id){
        return Result.success(supplierTaskFinishInfoEOService.findById(id));
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supplier:supplierTaskApply:save")
    public ResponseMessage create(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.create(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|新增草稿")
    @PostMapping("/draft")
    @RequiresPermissions("supplier:supplierTaskApply:save")
    public ResponseMessage draft(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.createDraft(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|编辑草稿")
    @PutMapping("/draft")
    @RequiresPermissions("supplier:supplierTaskApply:edit")
    public ResponseMessage updateDraft(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.updateDraft(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|编辑")
    @PutMapping("")
    @RequiresPermissions("supplier:supplierTaskApply:edit")
    public ResponseMessage update(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.update(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("supplier:supplierTaskApply:delete")
    public ResponseMessage delete(@PathVariable String id) {
        supplierTaskApplyEOService.delete(id);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|任务执行确认")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, value = "/ownerTaskSure")
    @RequiresPermissions("supplier:supplierTaskApply:ownerTaskSure")
    public ResponseMessage ownerTaskSure(
            @RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.createOwnerTaskSure(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|外包供应商任务执行|分页查询")
    @GetMapping("/task")
    @RequiresPermissions("supplier:supplierTaskExecution:page")
    public ResponseMessage<PageInfo> taskExecution(SupplierTaskExeutionEOPage page) throws Exception {
        UserEO userEO = UserUtils.getUser();
        String orgCode = orgEODao.findOrgByUsId(userEO.getUsid());
        //如果进入此接口，且不是供应商的人员则supid匹配不上，为了不让他们看到数据
        //将supid设置为不存在的数据
        page.setBeApplyForId("XXX");
        //供应商id
        if(StringUtils.isNotEmpty(orgCode)) {
            String supId = abilityEODao.findSupByCode(orgCode);
            //添加条件
            if(StringUtils.isNotEmpty(supId)) {
                page.setBeApplyForId(supId);
            }
        }
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        return Result.success(getPageInfo(page.getPager(), supplierTaskApplyEOService.selectTaskExecutionByPage(page)));
    }

    @ApiOperation(value = "|外包供应商任务执行|委托单的标准List")
    @GetMapping("/task/{id}")
    @RequiresPermissions("supplier:supplierTaskApply:list")
    public ResponseMessage<List<SupplierTaskInfoVO>> getTask(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id,
            @ApiParam(value = "status") @RequestParam(required = false, value = "status") Integer status
    ) {
        return Result.success(supplierTaskApplyEOService.getTaskStandard(id, status));
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|供应商任务执行确认")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, value = "/taskSure")
    @RequiresPermissions("supplier:supplierTaskApply:taskSure")
    public ResponseMessage taskSure(
            @RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        supplierTaskApplyEOService.createTaskSure(supplierTaskApplyDTO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|外包供应商任务执行|委托单的任务信息")
    @GetMapping("/taskInfo/{id}")
    @RequiresPermissions("supplier:supplierTaskApply:info")
    public ResponseMessage<SupplierTaskInfoVO> getTaskInfo(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        return Result.success(supplierTaskApplyEOService.getTaskInfo(id));
    }

    @ApiOperation(value = "|SupplierTaskApplyEO|任务完成度反馈")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, value = "/finishInfo")
    @RequiresPermissions("supplier:supplierTaskApply:taskSure")
    public ResponseMessage createFinishInfo(
            @RequestBody SupplierTaskFinishInfoDTO supplierTaskFinishInfoDTO) throws Exception {
        return supplierTaskApplyEOService.createFinishInfo(supplierTaskFinishInfoDTO);
    }

    @ApiOperation(value = "|获取委托单编号接口")
    @GetMapping("/applyNo")
    @RequiresPermissions("supplier:supplierTaskApply:applyNo")
    public ResponseMessage<String> getApplyNo() {
        return Result.success(supplierTaskApplyEOService.getApplyNo());
    }

    @ApiOperation(value = "|外包供应商|任务统计分页查询")
    @GetMapping("/statistics")
    @RequiresPermissions("supplier:supplierTaskApply:statistics")
    public ResponseMessage<PageInfo> statistics(SupplierTaskStatisticsPage page) {
        //判断是否存在trialTaskId 如果存在走另外一套查询  根据需求变更
        /*if(StringUtils.isNotBlank(trialTaskId)){
            List<SupplierTaskStatisticsVO> supplierTaskStatisticsByTrialTaskId = supplierTaskApplyEOService.getSupplierTaskStatisticsByTrialTaskId(page, trialTaskId);
            return Result.success(getPageInfo(page.getPager(),supplierTaskStatisticsByTrialTaskId));
        }*/
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<SupplierTaskStatisticsVO> taskApplyListVOList = supplierTaskApplyEOService.getSupplierTaskStatisticsByPage(page);
        return Result.success(getPageInfo(page.getPager(), taskApplyListVOList));
    }

    @ApiOperation(value = "|外包供应商|任务统计导出")
    @GetMapping("/exportFile")
    @RequiresPermissions("supplier:supplierTaskApply:exportFile")
    public ResponseMessage exportFile(SupplierTaskStatisticsPage page, HttpServletResponse response) {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<SupplierTaskStatisticsVO> taskApplyListVOList = supplierTaskApplyEOService.queryListForExcel(page);
        if (taskApplyListVOList != null && !taskApplyListVOList.isEmpty()) {
            ExportDataUtil.exportStatisticsData(taskApplyListVOList, response);
        }
        return Result.success("0", "导出成功");
    }


    @ApiOperation(value = "辅助劳务委托启动流程")
    @PostMapping("/submitTask")
    public ResponseMessage startProcess(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = "";
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_SUP_TASKAPPLY);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_SUP_TASKAPPLY);
            procDefKey = dicTypeEO.getDicTypeName();
        }
        NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
            if(usersByRoleAndOrg.isEmpty())
                return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
        supplierTaskApplyEOService.startProcessSupplierTask(supplierTaskApplyDTO);
        return Result.success("0","工作流启动成功！");
    }



    @ApiOperation(value = "辅助劳务完成反馈启动流程")
    @PutMapping("/submitTaskFeedBack")
    public ResponseMessage startProcessFeedBack(@RequestBody SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = "";
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_SUP_TASKAPPLY_Feedback);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_SUP_TASKAPPLY_Feedback);
            procDefKey = dicTypeEO.getDicTypeName();
        }
        NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
            if(usersByRoleAndOrg.isEmpty())
                return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
        supplierTaskApplyEOService.startProcessFeedBack(supplierTaskApplyDTO);
        return Result.success("0","工作流启动成功！");
    }



    @ApiOperation(value = "辅助劳务委托审批流程")
    @PostMapping("/approvalProcess")
    @EnableAccess
    public ResponseMessage<TrialProjectEO> approvalProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
        try {
            if(StringUtils.isNotEmpty(requestEO)){
                ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId", "variables","taskId","procId");     //反射检验空
                if (x != null) return x;
                //判断下一节点是否有审批人
                String nextNodeInfo = PVCVService.getNextNodeInfo(requestEO);
                if ("-1".equals(nextNodeInfo)){
                    return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
                }
                BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
                supplierTaskApplyEOService.approvalProcess(request,  requestEO, baseBusEO);
                supplierTaskApplyEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
                return Result.success("0","审批成功");
            }else{
                return Result.error("-1","流程审批失败！");
            }
        }catch(Exception e){
            return Result.error("-1","流程审批失败！");
        }
    }


    @ApiOperation(value = "辅助劳务完成反馈审批流程")
    @PostMapping("/approvalProcessFeedBack")
    @EnableAccess
    public ResponseMessage<TrialProjectEO> approvalProcessFeedBack(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
        try {
            if(StringUtils.isNotEmpty(requestEO)){
                ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId", "variables","taskId","procId");     //反射检验空
                if (x != null) return x;
                //判断下一节点是否有审批人
                String nextNodeInfo = PVCVService.getNextNodeInfo(requestEO);
                if ("-1".equals(nextNodeInfo)){
                    return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
                }
                BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
                supplierTaskApplyEOService.approvalProcess(request,  requestEO, baseBusEO);
                supplierTaskApplyEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
                return Result.success("0","审批成功");
            }else{
                return Result.error("-1","流程审批失败！");
            }
        }catch(Exception e){
            return Result.error("-1","流程审批失败！");
        }
    }




}
