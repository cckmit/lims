package com.adc.da.pc_loan_application.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_loan_application.service.PcCarLoanInformationEOService;
import com.adc.da.pc_loan_application.vo.PcCarLoanApplicationVO;
import com.adc.da.pc_loan_application.vo.PcCarLoanFindVo;
import com.adc.da.pc_loan_application.vo.PcDisPlayVo;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import com.adc.da.pc_loan_application.page.PcCarLoanApplicationEOPage;
import com.adc.da.pc_loan_application.service.PcCarLoanApplicationEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/${restPath}/pc_loan_application/pcCarLoanApplication")
@Api(description = "|PcCarLoanApplicationEO|借车申请单-主表")
public class PcCarLoanApplicationEOController extends BaseController<PcCarLoanApplicationEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcCarLoanApplicationEOController.class);

    @Autowired
    private PcCarLoanApplicationEOService pcCarLoanApplicationEOService;
    @Autowired
    private BaseBusEODao baseBusEODao;
    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private com.adc.da.pc_execute.service.PVCVService PVCVService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @BusinessLog(description = "借车申请--分页模糊查询")
    @ApiOperation(value = "|PcCarReturnApplicationEO|分页模糊查询")
    @GetMapping("/page")

    //    @RequiresPermissions("pc_return_application:pcCarReturnApplication:page")
    public ResponseMessage<PageInfo<PcCarLoanApplicationEO>> page(PcCarLoanApplicationEOPage page) throws Exception {
        List<PcCarLoanApplicationEO> rows = pcCarLoanApplicationEOService.fuzzyQuery(page);
        return Result.success("0", "查询成功！", beanMapper.mapPage(getPageInfo(page.getPager(), rows), PcCarLoanApplicationEO.class));
    }

    @BusinessLog(description = "借车申请--删除")
    @ApiOperation(value = "|PcCarReturnApplicationEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcCarLoanApplicationEOService.deleteTwoByPrimaryKey(id);
        logger.info("delete from PC_CAR_LOAN_APPLICATION where id = {}", id);
        return Result.success("0", "操作成功！", true);
    }


    @ApiOperation(value = "|PcCarLoanApplicationEO|确定")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("pc_loan_application:pcCarLoanApplication:save")
    public ResponseMessage<PcCarLoanApplicationVO> create(@RequestBody PcCarLoanApplicationVO pcCarLoanApplicationVO) throws Exception {
        PcCarLoanApplicationVO pcCarVo = pcCarLoanApplicationEOService.updatePcCarVo(pcCarLoanApplicationVO, '0');
        return Result.success("0", "保存成功", pcCarVo);
    }


    @ApiOperation(value = "|PcCarLoanApplicationEO|详情")
    @GetMapping("/{trialTaskID}")
    public ResponseMessage<List<PcCarLoanFindVo>> find(@PathVariable String trialTaskID) throws Exception {
        return Result.success(pcCarLoanApplicationEOService.findPcCarLoanApplication(trialTaskID));
    }


    @ApiOperation(value = "|PcCarLoanApplicationEO|根据主键查询数据")
    @GetMapping("/primary/{id}")
    public ResponseMessage<PcCarLoanFindVo> findById(@PathVariable String id) throws Exception {
        return Result.success(pcCarLoanApplicationEOService.findPcCarDataById(id));
    }
//    @ApiOperation(value = "|PcCarLoanApplicationEO|根据taskId查询")
//    @GetMapping("/taskID/{task_id}")
//    public ResponseMessage<PcCarLoanApplicationVO> findByTaskId(@Pat hVariable String task_id) throws Exception {
//        return Result.success(pcCarLoanApplicationEOService.findPcCarByTaskID(task_id));
//    }

    @ApiOperation(value = "|PcCarLoanApplicationEO|导出")
    @GetMapping("/exportDoc")
    public ResponseMessage<String> exportDoc(String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            PcCarLoanFindVo pcCarLoanFindVo = pcCarLoanApplicationEOService.findPcCarDataById(id);
            if(StringUtils.isEmpty(pcCarLoanFindVo)) {
                return Result.error("-1","导出失败！");
            }
            pcCarLoanApplicationEOService.exportDoc(response, request, pcCarLoanFindVo);
            return Result.success("0", "导出成功！");
        } catch (Exception e) {
            logger.error("-1", e.getStackTrace());
            return Result.error("-1", "导出失败！");
        }
    }


    @ApiOperation(value = "|PcCarLoanApplicationEO |启动流程")
    @PostMapping("/startProcess")
    @RequiresPermissions("pc_car_loan:pcCarLoanApplication:startProcess")
    public ResponseMessage<PcCarLoanApplicationVO> startProcess(@RequestBody PcCarLoanApplicationVO pcCarLoanApplicationVO) throws Exception {
        //判断下一节点是否有审批人
        String procDefKey = null;
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_CAR_LOAN_FROM_TYPE);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PC_CAR_LOAN_FROM_TYPE);
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
        pcCarLoanApplicationEOService.startProcess(pcCarLoanApplicationVO);
        return Result.success("0", "提交成功", pcCarLoanApplicationVO);
    }

    @ApiOperation(value = "|PcCarLoanApplicationEO|审批流程")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pc_car_loan:pcCarLoanApplication:startProcess")
    @EnableAccess
    public ResponseMessage<RequestEO> approvalProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {


        try {
            if (StringUtils.isNotEmpty(requestEO)) {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "缺少业务Id");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-2", "缺少任务Id");
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
                pcCarLoanApplicationEOService.approvalProcess(request, requestEO, baseBusEO);
                pcCarLoanApplicationEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
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

    @ApiOperation(value = "根据实验任务id获取借车申请单编号")
    @GetMapping("/loanCarCode/{trialTaskID}")
    public ResponseMessage<String> findPcCarIDByTaskID(@PathVariable String trialTaskID) throws Exception {
        return Result.success(pcCarLoanApplicationEOService.findPcCarIDByTaskID(trialTaskID));
    }

    @ApiOperation(value = "根据借车单编号获取借车类型")
    @GetMapping("/loanCarCode/{trialTaskID}/{loanCarCode}")
    public ResponseMessage<String> findPcCarIDByTaskID(@PathVariable String trialTaskID, @PathVariable String loanCarCode) throws Exception {

        return Result.success(pcCarLoanApplicationEOService.findListTypeByTaskIdAndLoanCarCode(trialTaskID, loanCarCode));
    }
}
