package com.adc.da.pc_execute.service;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_drive.entity.PcDrivingRecordEO;
import com.adc.da.pc_execute.dao.PCBaseInitDataEODao;
import com.adc.da.pc_execute.dao.PCBudgetPersonEODao;
import com.adc.da.pc_execute.dao.PCBudgetSubsidyEODao;
import com.adc.da.pc_execute.dao.PCBudgetTestEODao;
import com.adc.da.pc_execute.dao.PCReliableInitTaskEODao;
import com.adc.da.pc_execute.dao.PCTrialLineEODao;
import com.adc.da.pc_execute.entity.PCBaseInitDataEO;
import com.adc.da.pc_execute.entity.PCBudgetPersonEO;
import com.adc.da.pc_execute.entity.PCBudgetSubsidyEO;
import com.adc.da.pc_execute.entity.PCBudgetTestEO;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.entity.PCTrialLineEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_execute.vo.PCBudgetCostVO;
import com.adc.da.pc_execute.vo.PcReliableInitTaskRiskVO;
import com.adc.da.pc_execute.vo.PersonAccommodationCostVO;
import com.adc.da.pc_execute.vo.RiskSubsidyCostVO;
import com.adc.da.pc_execute.vo.TrialCostVO;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import org.activiti.engine.HistoryService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * PV/CV试验执行模块--可靠性立项单
 *
 * @author Administrator
 * @date 2019年10月23日
 */
@SuppressWarnings("all")
@Service("PCReliableInitTaskEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCReliableInitTaskEOService extends BaseService<PCReliableInitTaskEO, String> {

    @Autowired
    private PCReliableInitTaskEODao pcReliableInitTaskEODao;

    public PCReliableInitTaskEODao getDao() {
        return pcReliableInitTaskEODao;
    }

    @Autowired
    private PCBudgetPersonEODao pcBudgetPersonEODao;

    @Autowired
    private PCBudgetSubsidyEODao pcBudgetSubsidyEODao;

    @Autowired
    private PCBudgetTestEODao pcBudgetTestEODao;

    @Autowired
    private PCBaseInitDataEODao pcBaseInitDataEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private LimsFileService limsFileService;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private PCTrialLineEODao pcTrialLineEODao;

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    public String getInitCode(String pvorcv) {
        if ("1".equals(pvorcv)) {
            pvorcv = "PV";
        } else {
            pvorcv = "CV";
        }
        String initCode = "";
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //获取流水号相关信息
        PipelineNumberEO pNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_RELIABLE_INITTASK_NO);
        //如果数据库没有改类型code,则新增一个
        if (StringUtils.isEmpty(pNumberEO)) {
            pNumberEO = new PipelineNumberEO();
            pNumberEO.setType(ConstantUtils.PC_RELIABLE_INITTASK_NO);
            pNumberEO.setTally(0);
            pNumberEO.setParticularYear(dateYear);
            pipelineNumberEODao.insert(pNumberEO);
        }
        //判断数据取出来的最后一个业务单号是不是当年的,如果是当年的 直接+1
        if (dateYear.equals(pNumberEO.getParticularYear())) {
            //获取流水号
            initCode = PrimaryGenerater.getPCReliableInitTaskNumber(
                    pNumberEO.getTally(), pNumberEO.getParticularYear(), pvorcv);
            //更新计数
            pNumberEO.setTally(pNumberEO.getTally() + 1);
        } else { //不是当年的,从0开始
            //获取流水号
            initCode = PrimaryGenerater.getPCReliableInitTaskNumber(0, dateYear, pvorcv);
            //更新计数及新的年份
            pNumberEO.setTally(1);
            pNumberEO.setParticularYear(dateYear);
        }
        //更新数据库
        pipelineNumberEODao.updateByPrimaryKeySelective(pNumberEO);
        return initCode;
    }

    /**
     * 可靠性立项单-保存
     *
     * @param pcReliableInitTaskEO
     * @return PCReliableInitTaskEO
     * @Title: save
     * @author: ljy
     * @date: 2019年10月23日
     */
    public PCReliableInitTaskEO save(PCReliableInitTaskEO pcReliableInitTaskEO) {
        //设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        pcReliableInitTaskEO.setCreateTime(date);
        pcReliableInitTaskEO.setCreateBy(UserUtils.getUserId());
        pcReliableInitTaskEO.setUpdateBy(UserUtils.getUserId());
        pcReliableInitTaskEO.setUpdateTime(date);
        //删除标记 0 未删除;  1 删除
        pcReliableInitTaskEO.setDelFlag("0");
        pcReliableInitTaskEO.setInitCode(getInitCode(pcReliableInitTaskEO.getPvorcv()));
        //0-草稿  1-审批中   2-已审批   3-退回  4-撤回
        pcReliableInitTaskEO.setInitStatus("0");
        pcReliableInitTaskEODao.insert(pcReliableInitTaskEO);
        return pcReliableInitTaskEO;
    }

    /**
     * 可靠性立项单-更新
     *
     * @param pcReliableInitTaskEO
     * @return PCReliableInitTaskEO
     * @Title: edit
     * @author: ljy
     * @date: 2019年10月24日
     */
    public PCReliableInitTaskEO edit(PCReliableInitTaskEO pcReliableInitTaskEO) {
        //设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        pcReliableInitTaskEO.setUpdateBy(UserUtils.getUserId());
        pcReliableInitTaskEO.setUpdateTime(date);
        pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
        return pcReliableInitTaskEO;
    }


    /**
     * 可靠性立项单-删除
     *
     * @param id
     * @param initStatus
     * @return void
     * @Title: deleteById
     * @author: ljy
     * @date: 2019年10月24日
     */
    public void deleteById(String id, String initStatus) {
        //草稿状态直接删除
        if ("0".equals(initStatus)) {
            pcReliableInitTaskEODao.deleteByPrimaryKey(id);
        }
        //退回或者撤回,需要删除流程
        else if ("3".equals(initStatus) || "4".equals(initStatus)) {
            //流程相关删除
            String procInstId = pcReliableInitTaskEODao.selectActProcIdById(id);
            historyService.deleteHistoricProcessInstance(procInstId);
            //业务删除
            pcReliableInitTaskEODao.deleteByPrimaryKey(id);
        }
    }


    /**
     * 可靠性立项单-流程启动
     *
     * @param pcReliableInitTaskEO
     * @return void
     * @throws Exception
     * @Title: submitReliableInitTask
     * @author: ljy
     * @date: 2019年10月24日
     */
    public void submitReliableInitTask(PCReliableInitTaskEO pcReliableInitTaskEO, String flag) throws Exception {
        //查询数据库是否有该数据
        PCReliableInitTaskEO eo = pcReliableInitTaskEODao.
                selectByPrimaryKey(pcReliableInitTaskEO.getId());
        if(StringUtils.isNotEmpty(flag)) {
        	pcReliableInitTaskEO.setPvorcv(flag);
        }
        //存在即更新, 否则新增
        if (StringUtils.isNotEmpty(eo)) {
            edit(pcReliableInitTaskEO);
        } else {
            save(pcReliableInitTaskEO);
        }
        //启动流程
        //保存业务BASEBUS
        //获取试验申请类型，修改标题
        String trialTaskId = pcReliableInitTaskEO.getTrialTaskId();
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
        String taskType = trialTaskEO.getTaskType();
        DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("testTaskType", taskType);
        String taskTypeName = dic != null ? dic.getDicTypeName() : "";
        String title = taskTypeName + "立项单【" + pcReliableInitTaskEO.getInitCode() + "】审批流程";
        String pvorcv = "";
        if ("1".equals(pcReliableInitTaskEO.getPvorcv())) {
            pvorcv = ConstantUtils.PV_RELIABLE_INITTASK_TYPE;
        } else {
            pvorcv = ConstantUtils.CV_RELIABLE_INITTASK_TYPE;
        }
        String baseBusId = limsFileService.saveBaseBus(
                pcReliableInitTaskEO.getId(), pvorcv, title);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
        
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvorcv);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //修改可靠性立项单状态为审批中  1-审批中
        pcReliableInitTaskEO.setInitStatus("1");
        pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            Set<String> userArray = new HashSet<String>(Arrays.asList(splits));
            //循环设置当前待办人的id和name
            List<String> userNames = new ArrayList<>();
            for (String usid : userArray) {
                UserEO user = userEODao.selectByPrimaryKey(usid);
                if (StringUtils.isNotEmpty(user.getUsname())) {
                    userNames.add(user.getUsname());
                }
            }
            //链接到可靠性立项单页
            String link = pvorcv;
            limsFileService.processSendMessages(splits, title, link, pcReliableInitTaskEO.getId());
        }

    }


    /**
     * 可靠性立项单-流程审批
     *
     * @param request
     * @param requestEO
     * @return void
     * @throws Exception
     * @Title: approvalProcess
     * @author: ljy
     * @date: 2019年10月24日
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取可靠性立项单业务信息
        PCReliableInitTaskEO pcReliableInitTaskEO = pcReliableInitTaskEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取试验申请类型，修改标题
        String trialTaskId = pcReliableInitTaskEO.getTrialTaskId();
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
        String taskType = trialTaskEO.getTaskType();
        DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("testTaskType", taskType);
        String taskTypeName = dic != null ? dic.getDicTypeName() : "";
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
            //3-退回
            pcReliableInitTaskEO.setInitStatus("3");
            pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
        }
        //撤回
        else if ("reback".equals(approveCode)) {
            //4-撤回
            pcReliableInitTaskEO.setInitStatus("4");
            pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        } else {
            //修改可靠性立项单状态为审批中  1-审批中
            pcReliableInitTaskEO.setInitStatus("1");
            pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
        }
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if ("0".equals(requestEO.getType())) {
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        } else if ("1".equals(requestEO.getType())) {
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(pcReliableInitTaskEO)) {
                //已审批  2-已审批
                pcReliableInitTaskEO.setInitStatus("2");
                pcReliableInitTaskEODao.updateByPrimaryKeySelective(pcReliableInitTaskEO);
                //完成给相关人员发送消息
                String title = taskTypeName + "立项单【" + pcReliableInitTaskEO.getInitCode() + "】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                                getProcessUsersByTaskId(requestEO.getTaskId()),
                        title, link, pcReliableInitTaskEO.getId());
            }
        } else {
            //获取试验任务业务信息
            String title = taskTypeName + "立项单【" + pcReliableInitTaskEO.getInitCode() + "】审批流程";
            //获取下一节点人
            if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, link, pcReliableInitTaskEO.getId());
            }
        }
    }


    /**
     * 可靠性立项单导出
     *
     * @param response
     * @param request
     * @param reliableInitTaskId
     * @return void
     * @Title: exportPCReliableInitTask
     * @author: ljy
     * @date: 2019年10月25日
     */
    public void exportPCReliableInitTask(HttpServletResponse response,
                                         HttpServletRequest request, String reliableInitTaskId) {
        PCReliableInitTaskEO pcReliableInitTaskEO =
                pcReliableInitTaskEODao.selectByPrimaryKey(reliableInitTaskId);
        //封装数据
        Map<String, Object> stringObjectMap = new HashMap<>();
        //项目名称
        stringObjectMap.put("TASK_NAME", pcReliableInitTaskEO.getTaskName());
        //编号
        stringObjectMap.put("INIT_CODE", pcReliableInitTaskEO.getInitCode());
        //项目类别
        //*1-委外试验(检测所)
        //*2-外包试验(供应商)
        //*3-自行试验
        String TASK_TYPE = "";
        switch (pcReliableInitTaskEO.getTaskType()) {
            case "1":
                TASK_TYPE = "委外试验";
                break;
            case "2":
                TASK_TYPE = "外包试验";
                break;
            case "3":
                TASK_TYPE = "自行试验";
                break;
            default:
                break;
        }
        stringObjectMap.put("TASK_TYPE", TASK_TYPE);
        //填表日期
        stringObjectMap.put("CREATE_TIME", pcReliableInitTaskEO.getCreateTime());
        //负责部门
        String CHARGE_ORG_NAME = "";
        switch (pcReliableInitTaskEO.getChargeOrgName()) {
            case "0":
                CHARGE_ORG_NAME = "商用车品质保障部";
                break;
            case "1":
                CHARGE_ORG_NAME = "乘用车品质保障部";
                break;
            default:
                break;
        }
        stringObjectMap.put("CHARGE_ORG_NAME", CHARGE_ORG_NAME);
        //项目负责人
        stringObjectMap.put("CHARGE_USER_NAME", pcReliableInitTaskEO.getChargeUserName());
        //产品型号(车型）
        stringObjectMap.put("PRODUCT_TYPE", pcReliableInitTaskEO.getProductType());
        //底盘号
        stringObjectMap.put("CHASSIS_CODE", pcReliableInitTaskEO.getChassisCode());
        //试验样车状态说明（新车/旧车）
        String SAMPLE_STATUS = "";
        switch (pcReliableInitTaskEO.getSampleStatus()) {
            case "1":
                SAMPLE_STATUS = "新车";
                break;
            case "2":
                SAMPLE_STATUS = "旧车";
                break;
            default:
                break;
        }
        stringObjectMap.put("SAMPLE_STATUS", SAMPLE_STATUS);
        //试验说明（全新试验/搭载试验）
        String TASK_EXPLAIN = "";
        switch (pcReliableInitTaskEO.getTaskExplain()) {
            case "1":
                TASK_EXPLAIN = "全新试验";
                break;
            case "2":
                TASK_EXPLAIN = "搭载试验";
                break;
            default:
                break;
        }
        stringObjectMap.put("TASK_EXPLAIN", TASK_EXPLAIN);
        //项目简介
        stringObjectMap.put("TASK_SYNOPSIS", pcReliableInitTaskEO.getTaskSynopsis());
        //项目来源及要求
        stringObjectMap.put("TASK_FROM_REQUIRE", pcReliableInitTaskEO.getTaskFromRequire());
        //需请款费用小计
        stringObjectMap.put("SELF_TOTAL", pcReliableInitTaskEO.getSelfTotal());
        //项目费用预算总计
        stringObjectMap.put("PUBLIC_TOTAL", pcReliableInitTaskEO.getPublicTotal());
        //住宿费
        stringObjectMap.put("ACCO_COST", pcReliableInitTaskEO.getAccoCost());
        //路桥费
        stringObjectMap.put("HIGH_ROAD_COST", pcReliableInitTaskEO.getHighRoadCost());
        //自行支付修理费
        stringObjectMap.put("REPAIR_COST", pcReliableInitTaskEO.getRepairCost());
        //自行支付燃料费
        stringObjectMap.put("OIL_CHARGE_COST", pcReliableInitTaskEO.getOilChargeCost());
        //临牌保险费
        stringObjectMap.put("TEMP_CARD_COST", pcReliableInitTaskEO.getTempCardCost());
        //出差补贴
        stringObjectMap.put("BUSINESS_COST", pcReliableInitTaskEO.getBusniessCost());
        //风险补贴
        stringObjectMap.put("DANGER_COST", pcReliableInitTaskEO.getDangerCost());
        //对公支付燃油费/充电费
        stringObjectMap.put("PUB_OIL_CHARGE_COST", pcReliableInitTaskEO.getPubOilChargeCost());
        //委外费
        stringObjectMap.put("OUT_SOURCE_COST", pcReliableInitTaskEO.getOutSourceCost());
        //对公支付修理/保养费
        stringObjectMap.put("PUB_REPAIR_COST", pcReliableInitTaskEO.getPubOilChargeCost());
        //试验场场地
        stringObjectMap.put("TEST_FIELD_COST", pcReliableInitTaskEO.getTestFieldCost());
        //项目周期及实施计划
        stringObjectMap.put("TASK_CYCLE_PLAN", pcReliableInitTaskEO.getTaskCyclePlan());

        //------------流程信息-------------------//
        String actProcId = pcReliableInitTaskEODao.selectActProcIdById(reliableInitTaskId);
        String SECTIONCHIEF = "";
        String MINISTER = "";
        if (StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
            List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
            SECTIONCHIEF = list.get(1).getAssigneeName();
            MINISTER = list.get(0).getAssigneeName();
        }
        //审核--科长
        stringObjectMap.put("SECTIONCHIEF", SECTIONCHIEF);
        //批准--部长
        stringObjectMap.put("MINISTER", MINISTER);
        String fileName = pcReliableInitTaskEO.getTaskName()
                + "【" + pcReliableInitTaskEO.getInitCode() + "】";
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(stringObjectMap, "PCReliableInitTaskWord", response, request,
                fileName, fileExtend);
    }


    /**
     * 费用表单页数据查询
     *
     * @param initTaskId
     * @return PCBudgetCostVO
     * @Title: formList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public PCBudgetCostVO costFormData(String initTaskId) {
        //先按initTaskId 分别查三个,没有就查 基础   然后返回, 主表附件等信息 一并返回
        PCBudgetCostVO vo = new PCBudgetCostVO();
        PCReliableInitTaskEO pcReliableInitTaskEO = pcReliableInitTaskEODao.selectByPrimaryKey(initTaskId);
        if (StringUtils.isNotEmpty(pcReliableInitTaskEO)) {
            //申请时间(费用)
            vo.setApplyTime(pcReliableInitTaskEO.getApplyTime());
            //申请人id(费用)
            vo.setApplyUserId(pcReliableInitTaskEO.getApplyUserId());
            //申请人名(费用)
            vo.setApplyUserName(pcReliableInitTaskEO.getApplyUserName());
            //附件id(费用)
            vo.setAttachId(pcReliableInitTaskEO.getAttachId());
            //附件名(费用)
            vo.setAttachName(pcReliableInitTaskEO.getAttachName());
            //补贴备注(费用)
            vo.setSubsidyRemark(pcReliableInitTaskEO.getSubsidyRemark());
        }
        //可靠性立项单id
        vo.setInitTaskId(initTaskId);
        //可靠性立项--试验人员及住宿预算
        List<PCBudgetPersonEO> pcBudgetPersonList = pcBudgetPersonEODao.findListByInitTaskId(initTaskId);
        if (CollectionUtils.isEmpty(pcBudgetPersonList)) {
            pcBudgetPersonList = getPcBudgetPersonList();
        }
        vo.setPcBudgetPersonList(pcBudgetPersonList);
        //可靠性立项--试验补贴预算
        List<PCBudgetSubsidyEO> pcBudgetSubsidyList = pcBudgetSubsidyEODao.findListByInitTaskId(initTaskId);
        if (CollectionUtils.isEmpty(pcBudgetSubsidyList)) {
            pcBudgetSubsidyList = getPcBudgetSubsidyList(initTaskId);
        }
        vo.setPcBudgetSubsidyList(pcBudgetSubsidyList);
        //可靠性立项--试验费用预算
        List<PCBudgetTestEO> pcBudgetTestList = pcBudgetTestEODao.findListByInitTaskId(initTaskId);
        if (CollectionUtils.isEmpty(pcBudgetTestList)) {
            pcBudgetTestList = getPcBudgetTestList();
        }
        vo.setPcBudgetTestList(pcBudgetTestList);
        return vo;
    }

    /**
     * 可靠性立项--试验人员及住宿预算
     *
     * @return List<PCBudgetPersonEO>
     * @Title: getPcBudgetPersonList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public List<PCBudgetPersonEO> getPcBudgetPersonList() {
        //返回定义
        List<PCBudgetPersonEO> pcBudgetPersonList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> personBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_PERSON);
        for (int i = 0; i < personBaseList.size(); i++) {
            PCBudgetPersonEO person = new PCBudgetPersonEO();
            //项目名称
            person.setItemsName(personBaseList.get(i).getItemsName());
            //项目code
            person.setItemsCode(personBaseList.get(i).getItemsCode());
            //单位
            person.setUnit(personBaseList.get(i).getUnit());
            //标准费用
            person.setStdPrice(personBaseList.get(i).getPrice());
            pcBudgetPersonList.add(person);
        }
        return pcBudgetPersonList;
    }


    /**
     * 可靠性立项--试验补贴预算
     *
     * @return List<PCBudgetSubsidyEO>
     * @Title: getPcBudgetSubsidyList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public List<PCBudgetSubsidyEO> getPcBudgetSubsidyList(String initTaskId) {
        //返回定义
        List<PCBudgetSubsidyEO> pcBudgetSubsidyList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> subsidyBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_SUBSIDY);
        //查询路线
        List<PCTrialLineEO> list = pcTrialLineEODao.findByList(initTaskId);
        //磨合行驶
        Set<String> grindingInSet = new HashSet<String>();
        //一般公路试验
        Set<String> generalHighwaySet = new HashSet<String>();
        //市区+阻滞试验
        Set<String> urbanBlockSet = new HashSet<String>();
        //高速路试验
        Set<String> highSpeedSet = new HashSet<String>();
        //山区公路试验
        Set<String> mountainRoadSet = new HashSet<String>();
        //(强化)环路试验
        Set<String> ringRoadSet = new HashSet<String>();
        //越野路试试验
        Set<String> crossCountrySet = new HashSet<String>();
        //微丘高速
        Set<String> hillockHighSet = new HashSet<String>();
        //山区重丘高速
        Set<String> mountainousHillySet = new HashSet<String>();


        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                switch (list.get(i).getLineType()) {
                    //磨合行驶
                    case ConstantUtils.GRINDINGIN:
                        grindingInSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //一般公路试验
                    case ConstantUtils.GENERALHIGHWAY:
                        generalHighwaySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //市区+阻滞试验
                    case ConstantUtils.URBANBLOCK:
                        urbanBlockSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //高速路试验
                    case ConstantUtils.HIGHSPEED:
                        highSpeedSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //山区公路试验
                    case ConstantUtils.MOUNTAINROAD:
                        mountainRoadSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //(强化)环路试验
                    case ConstantUtils.RINGROAD:
                        ringRoadSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //越野路试试验
                    case ConstantUtils.CROSSCOUNTRY:
                        crossCountrySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //微丘高速
                    case ConstantUtils.HILLOCKHIGH:
                        hillockHighSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //山区重丘高速
                    case ConstantUtils.MOUNTAINOUSHILLY:
                        mountainousHillySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i < subsidyBaseList.size(); i++) {
            PCBudgetSubsidyEO subsidy = new PCBudgetSubsidyEO();
            //项目名称
            subsidy.setItemsName(subsidyBaseList.get(i).getItemsName());
            //项目code
            subsidy.setItemsCode(subsidyBaseList.get(i).getItemsCode());
            //项目路线
            switch (subsidyBaseList.get(i).getItemsCode()) {
                //磨合行驶
                case ConstantUtils.GRINDINGIN:
                    subsidy.setTrialLine(grindingInSet.toString());
                    break;
                //一般公路试验
                case ConstantUtils.GENERALHIGHWAY:
                    subsidy.setTrialLine(generalHighwaySet.toString());
                    break;
                //市区+阻滞试验
                case ConstantUtils.URBANBLOCK:
                    subsidy.setTrialLine(urbanBlockSet.toString());
                    break;
                //高速路试验
                case ConstantUtils.HIGHSPEED:
                    subsidy.setTrialLine(highSpeedSet.toString());
                    break;
                //山区公路试验
                case ConstantUtils.MOUNTAINROAD:
                    subsidy.setTrialLine(mountainRoadSet.toString());
                    break;
                //(强化)环路试验
                case ConstantUtils.RINGROAD:
                    subsidy.setTrialLine(ringRoadSet.toString());
                    break;
                //越野路试试验
                case ConstantUtils.CROSSCOUNTRY:
                    subsidy.setTrialLine(crossCountrySet.toString());
                    break;
                //微丘高速
                case ConstantUtils.HILLOCKHIGH:
                    subsidy.setTrialLine(hillockHighSet.toString());
                    break;
                //山区重丘高速
                case ConstantUtils.MOUNTAINOUSHILLY:
                    subsidy.setTrialLine(mountainousHillySet.toString());
                    break;
                default:
                    break;
            }
            //标准
            subsidy.setStdPrice(subsidyBaseList.get(i).getPrice());
            pcBudgetSubsidyList.add(subsidy);
        }
        return pcBudgetSubsidyList;
    }


    /**
     * 可靠性立项--试验费用预算
     *
     * @return List<PCBudgetTestEO>
     * @Title: getPcBudgetTestList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public List<PCBudgetTestEO> getPcBudgetTestList() {
        //返回定义
        List<PCBudgetTestEO> pcBudgetTestList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> testBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_TEST);
        for (int i = 0; i < testBaseList.size(); i++) {
            PCBudgetTestEO test = new PCBudgetTestEO();
            //项目名称
            test.setItemsName(testBaseList.get(i).getItemsName());
            //项目code
            test.setItemsCode(testBaseList.get(i).getItemsCode());
            //单位
            test.setUnit(testBaseList.get(i).getUnit());
            //标准费用
            test.setStdPrice(testBaseList.get(i).getPrice());
            pcBudgetTestList.add(test);
        }
        return pcBudgetTestList;
    }

    /**
     * 保存费用表单页数据
     *
     * @param pcBudgetCostVO
     * @return void
     * @Title: saveCostData
     * @author: ljy
     * @date: 2019年10月23日
     */
    public void costDataSave(PCBudgetCostVO pcBudgetCostVO) {
        //可靠性立项--试验人员及住宿预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetPersonList())) {
            savePcBudgetPersonList(pcBudgetCostVO.getPcBudgetPersonList(),
                    pcBudgetCostVO.getInitTaskId());
        }
        //可靠性立项--试验补贴预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetSubsidyList())) {
            savePcBudgetSubsidyList(pcBudgetCostVO.getPcBudgetSubsidyList(),
                    pcBudgetCostVO.getInitTaskId());
        }
        //可靠性立项--试验费用预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetTestList())) {
            savePcBudgetTestList(pcBudgetCostVO.getPcBudgetTestList(),
                    pcBudgetCostVO.getInitTaskId());
        }
    }

    /**
     * 保存 可靠性立项--试验人员及住宿预算
     *
     * @param pcBudgetPersonList
     * @return void
     * @Title: savePcBudgetPersonList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public void savePcBudgetPersonList(List<PCBudgetPersonEO> pcBudgetPersonList,
                                       String initTaskId) {
        //先删除
        pcBudgetPersonEODao.deleteByInitTaskId(initTaskId);
        //后新增
        for (PCBudgetPersonEO person : pcBudgetPersonList) {
            person.setId(UUID.randomUUID());
            person.setDelFlag("0");
            person.setInitTaskId(initTaskId);
            pcBudgetPersonEODao.insert(person);
        }
    }

    /**
     * 保存 可靠性立项--试验补贴预算
     *
     * @param pcBudgetSubsidyList
     * @return void
     * @Title: savePcBudgetSubsidyList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public void savePcBudgetSubsidyList(List<PCBudgetSubsidyEO> pcBudgetSubsidyList,
                                        String initTaskId) {
        //先删除
        pcBudgetSubsidyEODao.deleteByInitTaskId(initTaskId);
        //后新增
        for (PCBudgetSubsidyEO subsidy : pcBudgetSubsidyList) {
            subsidy.setId(UUID.randomUUID());
            subsidy.setDelFlag("0");
            subsidy.setInitTaskId(initTaskId);
            pcBudgetSubsidyEODao.insert(subsidy);
        }
    }

    /**
     * 保存 可靠性立项--试验费用预算
     *
     * @param pcBudgetTestList
     * @return void
     * @Title: savePcBudgetTestList
     * @author: ljy
     * @date: 2019年10月23日
     */
    public void savePcBudgetTestList(List<PCBudgetTestEO> pcBudgetTestList,
                                     String initTaskId) {
        //先删除
        pcBudgetTestEODao.deleteByInitTaskId(initTaskId);
        //后新增
        for (PCBudgetTestEO test : pcBudgetTestList) {
            test.setId(UUID.randomUUID());
            test.setDelFlag("0");
            test.setInitTaskId(initTaskId);
            pcBudgetTestEODao.insert(test);
        }
    }

    /**
     * 费用请款申请费用自行支付查询
     *
     * @param table
     * @param page
     * @return
     */
    public List<PcAutoPayForEO> costForCashOut(CostForCashOutPage page) {
        return this.getDao().costForCashOut(page);
    }

    public List<PcAutoPayForEO> costForCashOutPerson(CostForCashOutPage page) {
        return this.getDao().costForCashOutPerson(page);
    }

    public List<RiskSubsidyCostVO> riskSubsidy(String initTaskId) {
        List<RiskSubsidyCostVO> riskSubsidyCostVOS = new ArrayList<>();
        // 查询行车记录卡信息
        List<PcDrivingRecordEO> recordEOList = this.getDao().riskSubsidy(initTaskId);
        if (CollectionUtils.isNotEmpty(recordEOList)) {
            for (int i = 0; i < recordEOList.size(); i++) {
                RiskSubsidyCostVO vo = new RiskSubsidyCostVO();
                vo.setId(recordEOList.get(i).getId());
                vo.setLineType(recordEOList.get(i).getLineType());
                vo.setSingleMileage(recordEOList.get(i).getSingleMileage());
                riskSubsidyCostVOS.add(vo);
            }
        }
        // 查询路线策划信息
        List<PCTrialLineEO> list = pcTrialLineEODao.findByList(initTaskId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (CollectionUtils.isNotEmpty(riskSubsidyCostVOS)) {
                    if (i < riskSubsidyCostVOS.size()) {
                        riskSubsidyCostVOS.get(i).setDriveDays(list.get(i).getDriveDays());
                        Integer days = Integer.parseInt(list.get(i).getDriveDays());
                        Integer total = Integer.parseInt(list.get(i).getOneDayKm());
                        riskSubsidyCostVOS.get(i).setTotalMileage(String.valueOf(days * total));
                    } else {
//                        这样写会报错，没有看懂为什么这样写，所以注释了
//                        riskSubsidyCostVOS.get(i).setDriveDays("");
//                        riskSubsidyCostVOS.get(i).setTotalMileage("");
                    }
                } else {
                    RiskSubsidyCostVO riskSubsidyCostVO = new RiskSubsidyCostVO();
                    riskSubsidyCostVO.setDriveDays(list.get(i).getDriveDays());
                    Integer days = 0;
                    if(list.get(i).getDriveDays() != null){
                        if(list.get(i).getDriveDays().indexOf(".") == -1){
                            days = Integer.parseInt(list.get(i).getDriveDays());
                        }else{
                            days = Integer.parseInt(list.get(i).getDriveDays().substring(0,list.get(i).getDriveDays().indexOf(".")));
                        }
                    }
                    Integer total = Integer.parseInt(list.get(i).getOneDayKm());
                    riskSubsidyCostVO.setTotalMileage(String.valueOf(days * total));
                    riskSubsidyCostVOS.add(riskSubsidyCostVO);
                }
            }
        }
        //可靠性立项--试验补贴预算
        List<PCBudgetSubsidyEO> pcBudgetSubsidyList = pcBudgetSubsidyEODao.findListByInitTaskId(initTaskId);
        if (CollectionUtils.isNotEmpty(pcBudgetSubsidyList)) {
            for (int i = 0; i < riskSubsidyCostVOS.size(); i++) {
                for (int i1 = 0; i1 < pcBudgetSubsidyList.size(); i1++) {
                    if (pcBudgetSubsidyList.get(i1).getItemsCode().equals(riskSubsidyCostVOS.get(i).getLineType())) {
                        riskSubsidyCostVOS.get(i).setStdPrice(pcBudgetSubsidyList.get(i1).getStdPrice());
                    }
                }
            }
        }
        for (RiskSubsidyCostVO riskSubsidyCostVO : riskSubsidyCostVOS) {
            if (StringUtils.isNotBlank(riskSubsidyCostVO.getStdPrice()) &&
                    StringUtils.isNotBlank(riskSubsidyCostVO.getSingleMileage())) {
                Integer price = Integer.parseInt(riskSubsidyCostVO.getStdPrice());
                Integer single = Integer.parseInt(riskSubsidyCostVO.getSingleMileage());
                // 每天实际试验项目补助额
                riskSubsidyCostVO.setAllSubsidy(String.valueOf(price * single));
                // 每天试验项目补助额
                Integer total = Integer.valueOf(riskSubsidyCostVO.getTotalMileage());
                Integer days = Integer.valueOf(riskSubsidyCostVO.getDriveDays());
                riskSubsidyCostVO.setDaySubsidy(String.valueOf(price * total / days));
                // 偏差值
                Integer value = (single - total / days) / (total / days);
                riskSubsidyCostVO.setDeviationValue(value.toString());
                // 风险评估
                PcReliableInitTaskRiskVO reliableInitTaskRiskVO = this.getDao().findReliableInitRisk(riskSubsidyCostVO.getId());
                if (reliableInitTaskRiskVO != null) {
                    riskSubsidyCostVO.setRiskAssessment(reliableInitTaskRiskVO.getAssessment());
                    riskSubsidyCostVO.setRemarks(reliableInitTaskRiskVO.getRemarks());
                    riskSubsidyCostVO.setRiskId(reliableInitTaskRiskVO.getId());
                } else {
                    PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                    vo.setDriveRecordId(riskSubsidyCostVO.getId());
                    if (value > 0.05) {
                        riskSubsidyCostVO.setRiskAssessment("1");
                        vo.setAssessment("1");
                    } else if (value > -0.05 && value < 0.05) {
                        riskSubsidyCostVO.setRiskAssessment("0");
                        vo.setAssessment("0");
                    } else {
                        riskSubsidyCostVO.setRiskAssessment("-1");
                        vo.setAssessment("-1");
                    }
                    String id = create(vo);
                    riskSubsidyCostVO.setRiskId(id);
                }
            } else {
                PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                vo.setDriveRecordId(riskSubsidyCostVO.getId());
                vo.setAssessment("1");
                String id = create(vo);
                riskSubsidyCostVO.setRiskId(id);
            }
        }
        return riskSubsidyCostVOS;
    }

    /**
     * 通过可靠性立项单ID查询试验跟踪信息
     *
     * @param initTaskId
     * @return
     */
    public List<TrialCostVO> trialCost(String initTaskId) {
        List<TrialCostVO> trialCostVOS = new ArrayList<>();
        // 查询费用预算信息
        CostForCashOutPage page = getCostForCashOutPage(initTaskId, "trial");
        List<PcAutoPayForEO> budgetList = this.getDao().costForCashOut(page);
        if (CollectionUtils.isNotEmpty(budgetList)) {
            for (PcAutoPayForEO pcAutoPayForEO : budgetList) {
                TrialCostVO vo = new TrialCostVO();
                vo.setId(pcAutoPayForEO.getId());
                vo.setProject(pcAutoPayForEO.getBudgetName());
                vo.setCostPrice(pcAutoPayForEO.getStdPrice());
                vo.setUnitPrice(pcAutoPayForEO.getUnit());
                vo.setEstimatedAmount(pcAutoPayForEO.getCashAmount());
                trialCostVOS.add(vo);
            }
            for (TrialCostVO trialCostVO : trialCostVOS) {
                if (StringUtils.isNotBlank(trialCostVO.getRealAmount()) &&
                        StringUtils.isNotBlank(trialCostVO.getEstimatedAmount())) {
                    // 实际累计金额
                    Integer realAmount = Integer.valueOf(trialCostVO.getRealAmount());
                    // 预计金额
                    Integer amount = Integer.valueOf(trialCostVO.getEstimatedAmount());
                    // 偏差值
                    Integer value = (realAmount - amount) / amount;
                    trialCostVO.setDeviationValue(value.toString());
                    // 风险评估
                    PcReliableInitTaskRiskVO reliableInitTaskRiskVO = this.getDao().findReliableInitRisk(trialCostVO.getId());
                    if (reliableInitTaskRiskVO != null) {
                        trialCostVO.setRiskAssessment(reliableInitTaskRiskVO.getAssessment());
                        trialCostVO.setRemarks(reliableInitTaskRiskVO.getRemarks());
                        trialCostVO.setRiskId(reliableInitTaskRiskVO.getId());
                    } else {
                        PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                        vo.setDriveRecordId(trialCostVO.getId());
                        if (value > 0.05) {
                            trialCostVO.setRiskAssessment("1");
                            vo.setAssessment("1");
                        } else if (value > -0.05 && value < 0.05) {
                            trialCostVO.setRiskAssessment("0");
                            vo.setAssessment("0");
                        } else {
                            trialCostVO.setRiskAssessment("-1");
                            vo.setAssessment("-1");
                        }
                        String id = create(vo);
                        trialCostVO.setRiskId(id);
                    }
                } else {
                    PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                    vo.setDriveRecordId(trialCostVO.getId());
                    vo.setAssessment("1");
                    String id = create(vo);
                    trialCostVO.setRiskId(id);
                }
            }
        }
        return trialCostVOS;
    }

    public List<PersonAccommodationCostVO> personAccommodationCost(String initTaskId) {
        List<PersonAccommodationCostVO> personAccommodationCostVOS = new ArrayList<>();
        // 查询费用预算信息
        CostForCashOutPage page = getCostForCashOutPage(initTaskId, "person");
        List<PcAutoPayForEO> person = this.getDao().costForCashOutPerson(page);
        if (CollectionUtils.isNotEmpty(person)) {
            for (PcAutoPayForEO pcAutoPayForEO : person) {
                PersonAccommodationCostVO vo = new PersonAccommodationCostVO();
                vo.setId(pcAutoPayForEO.getId());
                vo.setProject(pcAutoPayForEO.getBudgetName());
                vo.setCostPrice(pcAutoPayForEO.getStdPrice());
                vo.setUnitPrice(pcAutoPayForEO.getUnit());
                vo.setPlanDays(pcAutoPayForEO.getPlanDayNum());
                vo.setRealDays("");
                vo.setRealCost("");
                vo.setPlanCost(pcAutoPayForEO.getCashAmount());
                personAccommodationCostVOS.add(vo);
            }
            for (PersonAccommodationCostVO trialCostVO : personAccommodationCostVOS) {
                if (StringUtils.isNotBlank(trialCostVO.getRealCost()) &&
                        StringUtils.isNotBlank(trialCostVO.getPlanCost())) {
                    // 实际费用
                    Integer realAmount = Integer.valueOf(trialCostVO.getRealCost());
                    // 预计费用
                    Integer amount = Integer.valueOf(trialCostVO.getPlanCost());
                    // 偏差值
                    Integer value = (realAmount - amount) / amount;
                    trialCostVO.setDeviationValue(value.toString());
                    // 风险评估
                    PcReliableInitTaskRiskVO reliableInitTaskRiskVO = this.getDao().findReliableInitRisk(trialCostVO.getId());
                    if (reliableInitTaskRiskVO != null) {
                        trialCostVO.setRiskAssessment(reliableInitTaskRiskVO.getAssessment());
                        trialCostVO.setRemarks(reliableInitTaskRiskVO.getRemarks());
                        trialCostVO.setRiskId(reliableInitTaskRiskVO.getId());
                    } else {
                        PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                        vo.setDriveRecordId(trialCostVO.getId());
                        if (value > 0.05) {
                            trialCostVO.setRiskAssessment("1");
                            vo.setAssessment("1");
                        } else if (value > -0.05 && value < 0.05) {
                            trialCostVO.setRiskAssessment("0");
                            vo.setAssessment("0");
                        } else {
                            trialCostVO.setRiskAssessment("-1");
                            vo.setAssessment("-1");
                        }
                        String id = create(vo);
                        trialCostVO.setRiskId(id);
                    }
                } else {
                    PcReliableInitTaskRiskVO vo = new PcReliableInitTaskRiskVO();
                    vo.setDriveRecordId(trialCostVO.getId());
                    vo.setAssessment("1");
                    String id = create(vo);
                    trialCostVO.setRiskId(id);
                }
            }
        }
        return personAccommodationCostVOS;
    }

    private CostForCashOutPage getCostForCashOutPage(String initTaskId, String type) {
        PCReliableInitTaskEO pcReliableInitTaskEO = this.getDao().selectByPrimaryKey(initTaskId);
        CostForCashOutPage page = new CostForCashOutPage();
        page.setTrialTaskId(pcReliableInitTaskEO.getTrialTaskId());
        List<String> list = new ArrayList<>();
        if (type.equals("person")) {
            list.add("CashPlusPostage");
            list.add("CashCharge");
            list.add("CashRepairFee");
            list.add("CashMaintenanceFee");
            list.add("TransitionFee");
            list.add("HighSpeedToll");
        } else if (type.equals("trial")) {
            list.add("TravelAllowance");
            list.add("AccommodationCost");
        }
        page.setCodeList(list);
        return page;
    }

    public String create(PcReliableInitTaskRiskVO vo) {
        String uuid10 = UUID.randomUUID10();
        vo.setId(uuid10);
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        vo.setCreateBy(userId);
        vo.setCreateTime(date);
        vo.setUpdateBy(userId);
        vo.setUpdateTime(date);
        vo.setDelFlag(String.valueOf(DeleteFlagEnum.NORMAL.getValue()));
        this.getDao().createReliableInitTaskRiskVo(vo);
        return uuid10;
    }

    public void update(PcReliableInitTaskRiskVO vo) {
        this.getDao().updateReliableInitTaskRiskVo(vo);
    }

    public PCReliableInitTaskEO findOne(String trialTaskId) throws Exception {
        List<PCReliableInitTaskEO> one = this.getDao().findOne(trialTaskId);
        if (one.size() == 0){//证明这个试验申请没有可靠性立项单
            TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
            //试验人员id
            String personIds = trialTaskEO.getPersonIds();
            String[] split = personIds.split("\\,");
            String uId = split[0];
            UserEO user = userEODao.getUserById(uId);
            PCReliableInitTaskEO pcReliableInitTaskEO = new PCReliableInitTaskEO();
            pcReliableInitTaskEO.setChargeUserName(user.getUsname());
            return pcReliableInitTaskEO;
        } else if (one.size() > 1 || one.size() == 1){
            return one.get(0);
        }
        return null;
    }

}
