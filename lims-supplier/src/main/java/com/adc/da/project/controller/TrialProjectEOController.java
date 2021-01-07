package com.adc.da.project.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_drive.entity.RoadLineRealityEO;
import com.adc.da.pc_drive.service.PcDriveRecordEOService;
import com.adc.da.pc_drive.vo.PlanMileage;
import com.adc.da.project.entity.OperationPerson;
import com.adc.da.project.vo.TrialProjectCarVO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import javax.servlet.http.HttpServletRequest;

import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.project.entity.TrialProjectEO;
import com.adc.da.project.page.TrialProjectEOPage;
import com.adc.da.project.service.TrialProjectEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/${restPath}/project/trialProject")
@Api(tags = "Sup-供应商项目管理")
public class TrialProjectEOController extends BaseController<TrialProjectEO>{

    private static final Logger logger = LoggerFactory.getLogger(TrialProjectEOController.class);

    @Autowired
    private TrialProjectEOService trialProjectEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private PrimaryGenerater primaryGenerater;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private com.adc.da.pc_execute.service.PVCVService PVCVService;

    @Autowired
    private PcDriveRecordEOService pcDriveRecordEOService;

    @Autowired
    private OrgEOService orgEOService;


    @BusinessLog(description = "供应商项目管理甲方分页查询")
	@ApiOperation(value = "|TrialProjectEO|甲方分页查询")
    @GetMapping("/page")
    @RequiresPermissions("project:trialProject:page")
    public ResponseMessage<PageInfo<TrialProjectEO>> page(TrialProjectEOPage page, String searchField) throws Exception {
	    try{
            List<TrialProjectEO> rows = trialProjectEOService.queryByPage(page, searchField);
            page.getPager().setRowCount(trialProjectEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch (Exception e){
	        logger.error("-1","查询失败！");
	        return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理供应商分页查询")
    @ApiOperation(value = "|TrialProjectEO|供应商分页查询")
    @GetMapping("/pageForSup")
    @RequiresPermissions("project:trialProject:pageForSup")
    public ResponseMessage<PageInfo<TrialProjectEO>> pageForSup(TrialProjectEOPage page, String searchField) throws Exception {
        try{
            List<TrialProjectEO> rows = trialProjectEOService.queryByPageForSup(page, searchField);
            page.getPager().setRowCount(trialProjectEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch (Exception e){
            logger.error("-1","查询失败！");
            return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理列表查询")
	@ApiOperation(value = "|TrialProjectEO|查询")
    @GetMapping("")
    @RequiresPermissions("project:trialProject:list")
    public ResponseMessage<List<TrialProjectEO>> list(TrialProjectEOPage page) throws Exception {
        return Result.success(trialProjectEOService.queryByList(page));
	}

    @BusinessLog(description = "供应商项目管理详情")
    @ApiOperation(value = "|TrialProjectEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("project:trialProject:get")
    public ResponseMessage<TrialProjectEO> find(@PathVariable String id) throws Exception {
        TrialProjectEO trialProjectEO = trialProjectEOService.selectByPrimaryKey(id);
        List<OperationPerson> operationPersonList = new ArrayList<>();
        if(!StringUtils.isEmpty(trialProjectEO.getOperationId())){
            String [] ids = trialProjectEO.getOperationId().split(",");
            String [] name = trialProjectEO.getOperationName().split(",");
            for (int i = 0;i<ids.length;i++) {
                OperationPerson operationPerson = new OperationPerson();
                operationPerson.setId(ids[i]);
                operationPerson.setName(name[i]);
                operationPersonList.add(operationPerson);
            }
        }
        trialProjectEO.setOperationList(operationPersonList);
        return Result.success(trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理新增")
    @ApiOperation(value = "|TrialProjectEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("project:trialProject:save")
    public ResponseMessage<TrialProjectEO> create(@RequestBody TrialProjectEO trialProjectEO) throws Exception {
        trialProjectEOService.saveTrialProjectEO(trialProjectEO);
        return Result.success("0","新增成功",trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理启动流程")
    @ApiOperation(value = "|TrialProjectEO|启动供应商项目委托流程")
    @PostMapping("/startProcess")
//    @RequiresPermissions("project:trialProject:startProcess")
    public ResponseMessage<TrialProjectEO> startProcess(@RequestBody TrialProjectEO trialProjectEO) throws Exception {
        TrialProjectEO projectEO = trialProjectEOService.selectByPrimaryKey(trialProjectEO.getId());
        if(projectEO!=null){
            List<TrialProjectEO> projectEOS = trialProjectEOService.getTrialProjectEOByCarIdAndPcId(projectEO.getCarId(), projectEO.getPcId());
            if(projectEOS.size()>0){
                return Result.error("-1","该车辆正在使用中");
            }
        }
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = "";
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_SUP_BUSINESS_TYPE);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_SUP_BUSINESS_TYPE);
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
        trialProjectEOService.startProcess(trialProjectEO);
        return Result.success("0","工作流启动成功！",trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理审批流程")
    @ApiOperation(value = "|TrialProjectEO|审批供应商项目委托流程")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("project:trialProject:approvalProcess")
    @EnableAccess
    public ResponseMessage<TrialProjectEO> approvalProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
	    try {
	        if(StringUtils.isNotEmpty(requestEO)){
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "缺少业务Id");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-2", "缺少任务Id");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-3", "缺少流程实例Id");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-4", "缺少审批意见");
                }
                //判断下一节点是否有审批人
                String nextNodeInfo = PVCVService.getNextNodeInfo(requestEO);
                if ("-1".equals(nextNodeInfo)){
                    return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
                }
                BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
                trialProjectEOService.approvalProcess(request,  requestEO, baseBusEO);
                trialProjectEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
                return Result.success("0","审批成功");
            }else{
                logger.error("-1","流程审批失败！");
                return Result.error("-1","流程审批失败！");
            }
        }catch(Exception e){
	        logger.error("-1","流程审批失败！");
	        return Result.error("-1","流程审批失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理撤回流程")
    @ApiOperation(value = "|TrialProjectEO|撤回供应商项目委托流程")
    @PostMapping("/reCallProcess")
    @RequiresPermissions("project:trialProject:reCallProcess")
    public ResponseMessage<TrialProjectEO> reCallProcess(HttpServletRequest request,  @RequestBody RequestEO requestEO) throws Exception {
        try {
            if(StringUtils.isNotEmpty(requestEO)){
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "缺少业务Id");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-2", "缺少任务Id");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-3", "缺少流程实例Id");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-4", "缺少审批意见");
                }
                //业务总表
                BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
                trialProjectEOService.approvalProcess(request,  requestEO, baseBusEO);
                return Result.success("0","撤回成功！");
            }else{
                logger.error("-1","流程撤回失败！");
                return Result.error("-1","流程撤回失败！");
            }
        }catch(Exception e){
            logger.error("-1","流程撤回失败！");
            return Result.error("-1","流程撤回失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理修改")
    @ApiOperation(value = "|TrialProjectEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("project:trialProject:update")
    public ResponseMessage<TrialProjectEO> update(@RequestBody TrialProjectEO trialProjectEO) throws Exception {
        trialProjectEOService.updateByPrimaryKeySelective(trialProjectEO);
        return Result.success("0","操作成功",trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理完成反馈")
    @ApiOperation(value = "|TrialProjectEO|完成反馈")
    @PutMapping("/feedBack")
    @RequiresPermissions("project:trialProject:feedBack")
    public ResponseMessage<TrialProjectEO> feedBack(@RequestBody TrialProjectEO trialProjectEO) throws Exception {
	    if(StringUtils.isNotEmpty(trialProjectEO) && StringUtils.isNotEmpty(trialProjectEO.getId())){
            trialProjectEOService.feedBack(trialProjectEO);
            for (RoadLineRealityEO lineRealityEO: trialProjectEO.getRoadLineRealityEOList()) {
                pcDriveRecordEOService.insertOrUpdateRoadReality(lineRealityEO);
            }
        }
        return Result.success("0","操作成功",trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理确认项目")
    @ApiOperation(value = "|TrialProjectEO|供应商确认项目")
    @GetMapping("/supConfirm")
    @RequiresPermissions("project:trialProject:supConfirm")
    public ResponseMessage<TrialProjectEO> supConfirm(String id, String status, String remark,
                                                      String operationId,String operationName) throws Exception {
	    if(StringUtils.isEmpty(id)){
	        return Result.error("-1","缺少主键！");
        }
        trialProjectEOService.supConfirm(id, status, remark,operationId,operationName);
        return Result.success("0","操作成功");
    }

    @BusinessLog(description = "供应商项目管理删除")
    @ApiOperation(value = "|TrialProjectEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("project:trialProject:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        trialProjectEOService.deleteFlag(id);
        return Result.success("0","删除成功！");
    }

    @BusinessLog(description = "供应商项目管理获取流水号")
    @ApiOperation(value = "|TrialProjectEO|获取流水号")
    @GetMapping("/findTrustCode")
    @RequiresPermissions("project:trialProject:findTrustCode")
    public ResponseMessage<String> findTrustCode()  {
	    try {
            String numCode = primaryGenerater.findTrustCode();
            return Result.success("0", "获取编号成功", numCode);
        }catch (Exception e){
	        logger.error(e.getMessage() , e);
	        return  Result.error("-1","获取编码失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理上传附件")
    @ApiOperation(value = "|TrialProjectEO|上传文件")
    @PostMapping("/upLoadAT")
    @RequiresPermissions("project:trialProject:upLoadAT")
    public ResponseMessage<T> upLoadAT(MultipartFile file)  {
        try {
            if(file.getSize()==0){
                return Result.error("请上传附件！");
            }
            return trialProjectEOService.saveAT(file);
        }catch (Exception e){
            logger.error(e.getMessage() , e);
            return  Result.error("-1","上传附件失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理查看流程图")
    @ApiOperation(value = "|TrialProjectEO|查看流程图")
    @GetMapping("/procImage")
//    @RequiresPermissions("project:trialProject:procImage")
    public ResponseMessage<String> procImage(String id)  {
        try {
            return Result.success("0","获取成功",trialProjectEOService.procImage(id));
        }catch (Exception e){
            logger.error(e.getMessage() , e);
            return  Result.error("-1","获取信息失败！");
        }
    }

    @BusinessLog(description = "供应商项目管理提交给供应商确认")
    @ApiOperation(value = "|TrialProjectEO|提交给供应商确认")
    @PutMapping("/toChangeStatus")
    @RequiresPermissions("project:trialProject:toChangeStatus")
    public ResponseMessage<String> feedBack(String id,String status) throws Exception {
        TrialProjectEO trialProjectEO = trialProjectEOService.selectByPrimaryKey(id);
        if("-1".equals(trialProjectEO.getStatus())) {
            trialProjectEOService.updateStatus(id, status);
            return Result.success("0", "提交成功");
        }else{
            logger.error("不是草稿状态，不能提交确认！");
            return Result.error("-1","不是草稿状态，不能提交确认！");
        }
    }

    /**
     * 根据车辆型号查询路送路试计划里程
     * @param taskOrPlan  z执行认证 还是PV/CV
     * @param carId  车辆ID
     * @return
     */
    @ApiOperation(value = "| 根据车辆型号查询路送路试计划里程")
    @GetMapping("/queryRoadAndRoadTestPlanMileage")
    public ResponseMessage queryRoadAndRoadTestPlanMileageByCarTypeAndChassisCode(String carId,String taskOrPlan,String trialProjectId){
        //由于一辆车只对应一条行车记录表单，先用底盘号查询系统是否已存在表单
/*        boolean exsitsDriveRocord = pcDriveRecordEOService.isExsitsDriveRocord(carId,taskOrPlan);    //需求变更
        if(exsitsDriveRocord){
            return Result.success("-1","这辆车已经关联行车记录表单,请选择其他车辆！");
        }*/
        try {
            TrialProjectEO trialProjectEO = trialProjectEOService.selectByPrimaryKey(trialProjectId);
            if(!trialProjectEO.getCarId().contains(carId)){
                return Result.error("-1","车辆与路送路试不匹配");
            }
            PlanMileage planMileage= trialProjectEOService.getPlanMileages(carId,taskOrPlan,trialProjectEO);
//            PlanMileage planMileage = trialProjectEOService.getPlanMileage(carId); 此代码逻辑错误 bug修改
            return Result.success(planMileage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("-1","服务器异常");
        }
    }


    @ApiOperation(value = "| 根据任务ID查询路试路送委托单的车")
    @GetMapping("/queryRoadAndRoadTestCarType")
    public ResponseMessage queryRoadAndRoadTestCarType(String pcId){
        List<TrialProjectCarVO> trialProjectCarVOS= trialProjectEOService.selectCarType(pcId);
        if(CollectionUtils.isEmpty(trialProjectCarVOS)){
            return Result.success("-1","没有查到相关路试路送相关的车或者路试路送表单未确认");
        }

        return Result.success(trialProjectCarVOS);
    }


    @BusinessLog(description = "供应商项目管理完成反馈启动流程")
    @ApiOperation(value = "|TrialProjectEO|完成反馈启动流程")
    @PostMapping("/startProcessFeedBack")
//    @RequiresPermissions("project:trialProject:startProcessFeedBack")
    public ResponseMessage<TrialProjectEO> startProcessFeedBack(@RequestBody TrialProjectEO trialProjectEO) throws Exception {
        //修改为根据发起人组织机构决定pv/cv流程
//        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        UserEO curUser = userEOService.getUserWithRoles(trialProjectEO.getCreateById());   //完成反馈由供应商主管提交，但实际情况就是等同于由一开始的创建人提交
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = "";
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_SUP_ROADTEST_Feedback);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_SUP_ROADTEST_Feedback);
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
        trialProjectEOService.startProcessFeedBack(trialProjectEO);
        return Result.success("0","工作流启动成功！",trialProjectEO);
    }

    @BusinessLog(description = "供应商项目管理完成反馈审批流程")
    @ApiOperation(value = "|TrialProjectEO|审批供应商项目完成反馈审批流程")
    @PostMapping("/approvalProcessFeedBack")
//    @RequiresPermissions("project:trialProject:approvalProcessFeedBack")
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
                trialProjectEOService.approvalProcess(request,  requestEO, baseBusEO);
                trialProjectEOService.isFinishiedFeedBack(requestEO.getProcId(), baseBusEO.getBusinessId());
                return Result.success("0","审批成功");
            }else{
                logger.error("-1","流程审批失败！");
                return Result.error("-1","流程审批失败！");
            }
        }catch(Exception e){
            logger.error("-1","流程审批失败！");
            return Result.error("-1","流程审批失败！");
        }
    }

}
