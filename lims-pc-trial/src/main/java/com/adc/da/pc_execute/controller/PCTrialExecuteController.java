package com.adc.da.pc_execute.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adc.da.pc_execute.entity.PCBudgetCostEO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;

import org.activiti.engine.RuntimeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.entity.PCTrialLineEO;
import com.adc.da.pc_execute.service.PCBudgetCostEOService;
import com.adc.da.pc_execute.service.PCReliableInitTaskEOService;
import com.adc.da.pc_execute.service.PCTrialExecuteService;
import com.adc.da.pc_execute.service.PCTrialLineEOService;
import com.adc.da.pc_execute.vo.PCBudgetCostVO;
import com.adc.da.pc_execute.vo.PCTrialExecuteListVO;
import com.adc.da.pc_execute.vo.PCTrialLineSearchVO;
import com.adc.da.pc_execute.vo.PCTrialLineVO;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_trust.vo.PCTrialTaskVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/${restPath}/pcTrial/trialExecute")
@Api(tags = "PcTrialTask-PV/CV试验执行模块")
@SuppressWarnings("all")
public class PCTrialExecuteController extends BaseController{
	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(PCTrialExecuteController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private PCTrialExecuteService pcTrialExecuteService;
    
    @Autowired
    private PCBudgetCostEOService pcBudgetCostEOService;
    
    @Autowired
    private PCTrialLineEOService pcTrialLineEOService;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private OrgEOService orgEOService;
    
    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    /**
     * 试验人员管理-修改
     * @Title: personManage
     * @param pcTrialTaskVO
     * @return void
     * @author: ljy
     * @date: 2019年10月28日
     */
    @ApiOperation(value = "|TrialTaskEO|试验人员管理修改")
    @BusinessLog(description = "PC试验任务-试验人员管理修改")
    @PutMapping("/updatePersonManage")
    @RequiresPermissions("pc:trialExecute:updatePersonManage")
    public ResponseMessage updatePersonManage(@RequestBody PCTrialTaskVO pcTrialTaskVO){
    	try {
    		if(StringUtils.isEmpty(pcTrialTaskVO) || 
    				StringUtils.isEmpty(pcTrialTaskVO.getId())) {
    			return Result.error("-1", "试验任务id不能为空!");
    		}
    		pcTrialExecuteService.updatePersonManage(pcTrialTaskVO);
    		return Result.success("0", "人员管理修改成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","人员管理修改失败!");
		}
    }
    
    
    /**
     * 试验人员管理-查询
     * @Title: getPersonManage
     * @param id
     * @return
     * @return PCTrialTaskVO
     * @author: ljy
     * @date: 2019年10月28日
     */
    @ApiOperation(value = "|TrialTaskEO|试验人员管理查询")
    @BusinessLog(description = "PC试验任务-试验人员管理查询")
    @GetMapping("/getPersonManage")
    @RequiresPermissions("pc:trialExecute:getPersonManage")
    public ResponseMessage<PCTrialTaskVO> getPersonManage(String id){
    	try {
    		return Result.success("0", "人员管理查询成功!", 
    				pcTrialExecuteService.getPersonManage(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","人员管理查询失败!");
		}
    }
    
    
    /**
     * PC试验执行-激活的试验人员查询
     * @Title: getPersonList
     * @param id
     * @return
     * @return ResponseMessage<List<TrialPersonEO>>
     * @author: ljy
     * @date: 2020年5月26日
     */
    @ApiOperation(value = "|TrialTaskEO|激活的试验人员查询")
    @BusinessLog(description = "PC试验执行-激活的试验人员查询")
    @GetMapping("/getPersonList")
    @RequiresPermissions("pc:trialExecute:getPersonManage")
    public ResponseMessage<List<TrialPersonEO>> getPersonList(String id){
    	try {
    		return Result.success("0", "人员管理查询成功!", 
    				pcTrialExecuteService.getPersonList(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","人员管理查询失败!");
		}
    }
    
    
    
    /**
     * PC试验执行 点击"+"查询
     * @Title: itemsList
     * @param id
     * @return
     * @return ResponseMessage<List<PCTrialExecuteListVO>>
     * @author: ljy
     * @date: 2019年10月31日
     */
    @ApiOperation(value = "|PC试验任务执行-点击'+'查询")
    @BusinessLog(description = "PC验任务执行-点击'+'查询")
    @GetMapping("/list/{id}")
    @RequiresPermissions("pc_trust:trialTask:list")
    public ResponseMessage<List<PCTrialExecuteListVO>> itemsList(
    		@PathVariable(value = "id") String id){
    	try {
            return Result.success(pcTrialExecuteService.selectListById(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "试验任务查询失败!");
        }
    }
    
    
  /**
   *PSQC/性能试验路线策划数据-查询
   * @Title: pcTrialLineList
   * @param taskNumber
   * @return
   * @return ResponseMessage<List<PCTrialLineVO>>
   * @author: ljy
   * @date: 2019年12月13日
   */
    @ApiOperation(value = "|PSQC/性能试验路线策划数据查询")
    @BusinessLog(description = "PSQC/性能试验路线策划数据-查询")
    @GetMapping("/pcTrialLineList")
    @RequiresPermissions("pc:trialExecute:pcTrialLineList")
    public ResponseMessage<List<PCTrialLineVO>> pcTrialLineList(String taskNumber) {
        try {
            List<PCTrialLineEO> list = pcTrialLineEOService.findListByTrialTaskNumber(taskNumber);
            return Result.success("0", "查询成功!", beanMapper.mapList(list, PCTrialLineVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }


    /**
     * PSQC/性能试验路线策划数据-保存
     * @Title: pcTrialLineSave
     * @param pcTrialLineSearchVO
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年12月13日
     */
    @ApiOperation(value = "|PSQC/性能试验保存路线策划数据")
    @BusinessLog(description = "PSQC/性能试验路线策划数据-保存")
    @PostMapping("/pcTrialLineSave")
    @RequiresPermissions("pc:trialExecute:pcTrialLineSave")
    public ResponseMessage pcTrialLineSave(
            @RequestBody PCTrialLineSearchVO pcTrialLineSearchVO) {
        try {
            if (StringUtils.isEmpty(pcTrialLineSearchVO.getTaskNumber())) {
                return Result.error("-1", "试验任务唯一code不可为空!");
            }
            pcTrialLineEOService.save(pcTrialLineSearchVO);
            return Result.success("0", "保存成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }

    /**
     *PSQC/性能试验 费用表单页数据-`查询
     * @Title: costFormData
     * @param initTaskId
     * @return
     * @return ResponseMessage<PCBudgetCostVO>
     * @author: ljy
     * @date: 2019年12月13日
     */
    @ApiOperation(value = "|PSQC/性能试验费用表单页数据查询")
    @BusinessLog(description = "PSQC/性能试验费用表单页数据-`查询")
    @GetMapping("/costFormData")
    @RequiresPermissions("pc:trialExecute:costFormData")
    public ResponseMessage<PCBudgetCostVO> costFormData(String taskNumber) {
        //先按taskNumber 分别查三个,没有就查 基础   然后返回, 主表附件等信息 一并返回
        try {
            PCBudgetCostVO vo = pcBudgetCostEOService.costFormData(taskNumber);
            return Result.success("0", "查询成功!", vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }


    /**
     * PSQC/性能试验费用表单页数据-保存
     * @Title: costDataSave
     * @param pcBudgetCostVO
     * @return
     * @return ResponseMessage<PCBudgetCostVO>
     * @author: ljy
     * @date: 2019年12月13日
     */
    @ApiOperation(value = "|PSQC/性能试验费用表单页数据保存")
    @BusinessLog(description = "PSQC/性能试验费用表单页数据-保存")
    @PostMapping("/saveCostData")
    @RequiresPermissions("pc:trialExecute:saveCostData")
    public ResponseMessage<PCBudgetCostVO> saveCostData(
            @RequestBody PCBudgetCostVO pcBudgetCostVO) {
        try {
            if (StringUtils.isEmpty(pcBudgetCostVO.getTaskNumber())) {
                return Result.error("-1", "试验任务唯一code不可为空!");
            }
            pcBudgetCostEOService.costDataSave(pcBudgetCostVO);
            return Result.success("0", "保存成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }

    @ApiOperation(value = "|PSQC/性能试验费用表单页数据提交")
    @BusinessLog(description = "PSQC/性能试验费用表单页数据-提交")
    @PostMapping("/submitCostData")
    //@RequiresPermissions("pc:trialExecute:submitCostData")
    public ResponseMessage<PCBudgetCostVO> submitCostData(
            @RequestBody PCBudgetCostVO pcBudgetCostVO,HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(pcBudgetCostVO.getTaskNumber())) {
                return Result.error("-1", "试验任务id不可为空!");
            }
 /*           //根据baseBus是否为空判断是流程发起还是退回、撤回后重新提交
              //废弃这样退回重新提交的方式，同意退回直接调用审批接口
            BaseBusEO baseBus = pcBudgetCostEOService.getBaseBusByBusinessId(pcBudgetCostVO.getTaskNumber());
            boolean reSubmit = baseBus != null ? true : false;
           if(reSubmit) {
                //重新提交直接进行 流程审批逻辑  RequestEO:重新提交需要传该参数
                RequestEO eo = new RequestEO();
       //         eo.setBaseBusId(pcBudgetCostVO.getTaskNumber());
                eo.setBaseBusId(baseBus.getId());
                eo.setTaskId("3160016");   //pcBudgetCostVO.getTrialTaskId()前台没传
                eo.setType("0");         //前台对接
       //         eo.setNextAssignee(reportSubmitVO.getNextAssignee());   //再看看
                eo.setProcId("3160001");   //前台待办proInstId
                Map variables = new HashMap<>();
                variables.put("approve", "agree");
                eo.setVariables(variables);
                pcBudgetCostEOService.handleCostData(request, eo);
            }
            else {  */
                //判断下一节点是否有审批人
                UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
                OrgEO curOrg = curUser.getOrgEOList().get(0);
                String flag = orgEOService.getByOrgId(curOrg.getId());
                String procDefKey = null;
                if ("1".equals(flag)) {
                    //PV
                    String businessType = ConstantUtils.PV_BUDGET_PROCESS;
                    procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                            ConstantUtils.PROCESS_CODE, businessType).getDicTypeName();
                } else {
                    //CV
                    String businessType = ConstantUtils.CV_BUDGET_PROCESS;
                    procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                            ConstantUtils.PROCESS_CODE, businessType).getDicTypeName();
                }
                NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
                String roleId = nextNodeInfo.getRoleId();
                String departId = nextNodeInfo.getDepartId();
                if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
                    List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
                    if (usersByRoleAndOrg.isEmpty())
                        return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
                }
                pcBudgetCostEOService.submitCostData(pcBudgetCostVO);
//            }
            return Result.success("0", "提交成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "提交失败!");
        }
    }

    @ApiOperation(value = "性能试验-费用预算-流程审批")
    @BusinessLog(description = "性能试验-费用预算-流程审批")
    @PostMapping("/handleCostData")
    @EnableAccess
    //@RequiresPermissions("pc:trialExecute:handleCostData")
    public ResponseMessage handleCostData(HttpServletRequest request, @RequestBody RequestEO requestEO){
        try {
            //校验是否为空
            if (StringUtils.isEmpty(requestEO)) {
                return Result.error("-1", "审批信息不可为空");
            } else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-1", "任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-1", "流程实例Id不可为空");
                }
/*                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-1", "审批意见不可为空");
                }*/
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
            pcBudgetCostEOService.handleCostData(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "流程审批失败！");
        }
    }

    @ApiOperation(value = "判断性能试验-费用预算流程是否已完成审批")
    @GetMapping("/isCompleted")
    @RequiresPermissions("pc:trialExecute:isCompleted")
    public ResponseMessage<String> isCompleted(String trialTaskId) {
        try {
            PCBudgetCostEO cost = pcBudgetCostEOService.getCostByTrialTaskId(trialTaskId);
            String r = "y";
            if(cost != null){
                if("1".equals(cost.getFlowStatus()) || "3".equals(cost.getFlowStatus())){
                    //存在状态为审批中、退回的流程
                    r = "n";
                }
            }else{
            	r = "n";
            }
            return Result.success("0", "查询成功!", r);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
}
