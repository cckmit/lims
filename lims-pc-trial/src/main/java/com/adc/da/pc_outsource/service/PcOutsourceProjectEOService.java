package com.adc.da.pc_outsource.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_announcement.service.AnnPlanEOService;
import com.adc.da.pc_announcement.service.AnnPlanProjectEOService;
import com.adc.da.pc_budget_use.dao.PcBudgetUseEODao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseEO;
import com.adc.da.pc_budget_use.entity.PcBudgetUseInfoEO;
import com.adc.da.pc_budget_use.page.PcBudgetUseEOPage;
import com.adc.da.pc_budget_use.service.PcBudgetUseEOService;
import com.adc.da.pc_budget_use.service.PcBudgetUseInfoEOService;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_execute.service.PCTrialExecuteService;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_outsource.VO.PCOutsourceSupProVO;
import com.adc.da.pc_outsource.common.CostSettlementService;
import com.adc.da.pc_outsource.common.PcOutsourceEnum;
import com.adc.da.pc_outsource.dao.PCOutsourceSupProEODao;
import com.adc.da.pc_outsource.dao.PcOutsourceProjectEODao;
import com.adc.da.pc_outsource.entity.PCOutsourceSupProEO;
import com.adc.da.pc_outsource.entity.PcOutsourceProjectEO;
import com.adc.da.pc_outsource.page.PcOutsourceProjectEOPage;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_report.service.PcReportEOService;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.summary.service.CostSummaryEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
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

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * <br>
 * <b>功能：</b>PC_OUTSOURCE_PROJECT PcOutsourceProjectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcOutsourceProjectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcOutsourceProjectEOService extends BaseService<PcOutsourceProjectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcOutsourceProjectEOService.class);

    @Autowired
    private PcOutsourceProjectEODao dao;

    public PcOutsourceProjectEODao getDao() {
        return dao;
    }

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private PersonCalenderEOService calenderEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;
    
    @Autowired
    private PCOutsourceSupProEODao PCOutsourceSupProEODao;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private EmailConfig emailConfig;

    @Value("${mail.address}")
    private String mailAddr;

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private AnnPlanProjectEOService annPlanProjectEOService;
    
    @Autowired
    private AnnPlanEOService annPlanEOService;
    
    @Autowired
    private PcBudgetUseEOService pcBudgetUseEOService;
    
    @Autowired
    private PcDriveRecordEODao pcDriveRecordEODao;
    
    @Autowired
    private PcBudgetUseInfoEOService pcBudgetUseInfoEOService;
    
    @Autowired
    private CostSummaryEOService costSummaryEOService;
    
    @Autowired
    private  TrialTaskEODao trialTaskeoDao;
    
    @Autowired
    private PCTrialExecuteService pcTrialExecuteService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PcReportEOService pcReportEOService;

    @Autowired
    private CostSettlementService costSettlementService;
    
    public PcOutsourceProjectEO find(String id) {
        PcOutsourceProjectEO outsourceProjectEO = this.getDao().selectByPrimaryKey(id);
        if (outsourceProjectEO != null) {
            if (StringUtils.isNotBlank(outsourceProjectEO.getFileId())) {
                String[] ids = outsourceProjectEO.getFileId().split(",");
                StringBuilder sb = new StringBuilder();
                for (String fileId : ids) {
                    TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(fileId);
                    if (eo != null) {
                        sb.append(eo.getAttachmentName()).append(",");
                    }
                }
                outsourceProjectEO.setFileName(sb.toString().substring(0, sb.length() - 1));
            }
            if (StringUtils.isNotBlank(outsourceProjectEO.getOpFileId())) {
                TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(outsourceProjectEO.getOpFileId());
                if (eo != null) {
                    outsourceProjectEO.setOpFileName(eo.getAttachmentName());
                }
            }
        }
        return outsourceProjectEO;
    }

    
	/**
	 * 根据委外立项单id 获取 检验项目列表
	 * @Title: findSupProListByOutsourceId
	 * @param outsourceId
	 * @return
	 * @return List<PCOutsourceSupProEO>
	 * @author: ljy
	 * @date: 2020年3月12日
	 */
    public List<PCOutsourceSupProEO> findSupProject(String outsourceId){
    	return PCOutsourceSupProEODao.findSupProListByOutsourceId(outsourceId);
    }
    
    /**
     * 根据委外立项单id 删除 检验项目列表
     * @Title: deleteByOutSourceId
     * @param outSourceId
     * @return void
     * @author: ljy
     * @date: 2020年3月12日
     */
    public void deleteByOutSourceId(String outSourceId) {
    	PCOutsourceSupProEODao.deleteByOutSourceId(outSourceId);
    }
    
    
    /**
     * 保存关联 供应商检验项目
     * @Title: saveSupProject
     * @param outSourceId
     * @param list
     * @return void
     * @author: ljy
     * @date: 2020年3月12日
     */
    public void saveSupProject(String outSourceId, List<List<PCOutsourceSupProEO>> list) {
    	//先删除
    	PCOutsourceSupProEODao.deleteByOutSourceId(outSourceId);
    	//后新增
    	for (int i = 0; i < list.size(); i++) {
    		String groupFlag = UUID.randomUUID();
    		for (int j = 0; j < list.get(i).size(); j++) {
    			PCOutsourceSupProEO supProject = new PCOutsourceSupProEO();
    			supProject.setDelFlag("0");
    			supProject.setId(UUID.randomUUID10());
    			//设置委外立项单id
    			supProject.setOutsourceId(outSourceId);
    			//供应商id
    			supProject.setSupId(list.get(i).get(j).getSupId());
    			//供应商名称
    			supProject.setSupName(list.get(i).get(j).getSupName());
    			//试验项目
    			supProject.setTestproName(list.get(i).get(j).getTestproName());
    			//折后价格
    			supProject.setDiscountPrice(list.get(i).get(j).getDiscountPrice());
    			//标识付
    			supProject.setGroupFlag(groupFlag);
    			//保存
    			PCOutsourceSupProEODao.insert(supProject);
			}
		}
    }
    
    
    public void submitActivity(PcOutsourceProjectEO outsourceProjectEO, String flag) throws Exception {
    	if(StringUtils.isNotEmpty(flag)) {
    		outsourceProjectEO.setPvOrCv(flag);
        }
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(outsourceProjectEO.getId())) {
            // 判断ID是否为空或""，否进行修改操作
            outsourceProjectEO.setUpdateTime(date);
            outsourceProjectEO.setUpdateBy(userId);
            outsourceProjectEO.setStatus(PcOutsourceEnum.UN_APPROVAL.getValue());
            this.getDao().updateByPrimaryKeySelective(outsourceProjectEO);
            outsourceProjectEO = this.getDao().selectByPrimaryKey(outsourceProjectEO.getId());
        } else {
            // 是进行新增操作
            outsourceProjectEO.setId(UUID.randomUUID10());
            outsourceProjectEO.setCreateTime(date);
            outsourceProjectEO.setCreateBy(userId);
            outsourceProjectEO.setUpdateTime(date);
            outsourceProjectEO.setUpdateBy(userId);
            // 删除标识（0，未删除；1.已删除）
            outsourceProjectEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            // 状态（0,草稿；1，待审批；2已审批；3，撤回中；4，已撤回；5，已完成）
            outsourceProjectEO.setStatus(PcOutsourceEnum.UN_APPROVAL.getValue());
            outsourceProjectEO.setCode(getCode("DFLQOUTSOURCE-"));
            this.getDao().insertSelective(outsourceProjectEO);
        }
        //启动流程
        if ("0".equals(outsourceProjectEO.getPvOrCv())) {
            submit(this.getDao(), outsourceProjectEO, ConstantUtils.CV_OUTSOURCE_PROJECT_TYPE,
                    ConstantUtils.CV_OUTSOURCE_PROJECT_TYPE, ConstantUtils.PROCESS_CODE, "委外立项单");
        } else {
            submit(this.getDao(), outsourceProjectEO, ConstantUtils.PC_OUTSOURCE_PROJECT_TYPE_FORM,
                    ConstantUtils.PC_OUTSOURCE_PROJECT_TYPE, ConstantUtils.PROCESS_CODE, "委外立项单");
        }
    }

    public String getCode(String outsource) {
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_OUTSOURCE);
        String date = DateUtils.dateToString(new Date(), "yyyy");
        // 如果查不到流水号新增
        if (pipelineNumberEO == null) {
            PipelineNumberEO eo = new PipelineNumberEO();
            // 为了保证查询时的流水号，和保存委托时的流水号相同。未查询到流水号时返回0001，然后将当前流水号+1，保存数据库。
            eo.setTally(2);
            eo.setParticularYear(date);
            eo.setType(ConstantUtils.PC_OUTSOURCE);
            pipelineNumberEODao.insertSelective(eo);
            return outsource + date + "-0001";
        }
        // 如果当前年份不等于数据库中以保存的年份，重新开始
        if (!date.equals(pipelineNumberEO.getParticularYear())) {
            // 为了保证查询时的流水号，和保存委托时的流水号相同。年份不相等时重新计算流水号，返回0001，然后将当前流水号+1，保存数据库。
            pipelineNumberEO.setTally(2);
            pipelineNumberEO.setParticularYear(date);
            pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
            return outsource + date + "-0001";
        }
        //最大流水号
        String num = pipelineNumberEO.getTally().toString();
        StringBuilder sb = new StringBuilder(num);
        //自动补全4位
        while (sb.length() < 4) {
            sb.insert(0, "0");
        }
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
        return outsource + date + "-" + sb.toString();
    }

    public void submit(Object dao, Object eo, String from, String type, String key, String project) throws Exception {
        // 获取eo类的Class对象
        Class<? extends Object> eoClass = eo.getClass();
        String title = project + "【" + eoClass.getDeclaredMethod("getCode").invoke(eo).toString() + "】审批流程";
        //保存业务BASEBUS
        BaseBusEO baseBusEO = new BaseBusEO();
        //设置UUID
        baseBusEO.setId(UUID.randomUUID());
        baseBusEO.setCreateBy(UserUtils.getUserId());
        baseBusEO.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        baseBusEO.setBusinessId(eoClass.getDeclaredMethod("getId").invoke(eo).toString());
        baseBusEO.setTitle(title);
        baseBusEO.setBusinessType(from);
        //保存BASEBUS
        baseBusEODao.insertBaseBus(baseBusEO);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusEO.getId());
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(key, type);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
        
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            Set<String> userArray = new HashSet<>(Arrays.asList(splits));
            //循环设置当前待办人的id和name
            List<String> userNames = new ArrayList<>();
            for (String usid : userArray) {
                UserEO user = userEODao.selectByPrimaryKey(usid);
                if (StringUtils.isNotEmpty(user) && StringUtils.isNotBlank(user.getUsname())) {
                    userNames.add(user.getUsname());
                }
            }
            //将代办人id  name 设置至实体, 并更新
            Method userId = eoClass.getDeclaredMethod("setCurrentWaitUserId", String.class);
            userId.invoke(eo, StringUtils.join(userArray, ConstantUtils.COMMA));
            Method userName = eoClass.getDeclaredMethod("setCurrentWaitUserName", String.class);
            userName.invoke(eo, StringUtils.join(userNames, ConstantUtils.COMMA));
            Class<? extends Object> daoClass = dao.getClass();
            Method update = daoClass.getDeclaredMethod("updateByPrimaryKeySelective", eoClass);
            update.invoke(dao, eo);
            //链接到试验审批表单页
            processSendMessages(splits, title, from, eoClass.getDeclaredMethod("getId").invoke(eo).toString());
        }
    }

    /**
     * 给待办人发送消息/邮件/工作日历
     *
     * @param splits     用户IDS
     * @param title      标题
     * @param link       链接
     * @param businessId 业务id
     * @Title：processSendMessages
     * @return: void
     */
    public void processSendMessages(String[] splits, String title, String link, String businessId) throws Exception {
        //当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        List<String> userArray = Arrays.asList(splits);
        for (int i = 0; i < userArray.size(); i++) {
            //发送消息
        	 UserEO user = userEODao.selectByPrimaryKey(userArray.get(i));
            //报告较为特殊, 打开的链接为PDF
            MessageEO messageEO = new MessageEO(UUID.randomUUID(), "0", link, date, UserUtils.getUser().getUsname(),
                    title, userArray.get(i), businessId);
            //发送消息通知
            messageEOService.sendMessage(messageEO);
            messageEOService.insertSelective(messageEO);
            //工作日历
            calenderEOService.insertSelective(new PersonCalenderEO(UUID.randomUUID(), date, title, userArray.get(i), ""));
            //发送邮件,邮件实体建立
            MailUtils email = new MailUtils();
            String token = UUID.randomUUID();
            email.setToken(token);
            if (StringUtils.isNotEmpty(user)) {
                ConstantUtils.DELAYMAILMAP.put(token, user.getUsid());
                if (StringUtils.isNotEmpty(user.getEmail())) {
                    email.setReceiver(user.getEmail());
                    email.setSubject(title);
                    email.setContent("<a href='" + mailAddr + token + "'>" + title + "</a> ");
                    //发送邮件
                    emailConfig.sendMailHtml(email);
                }
            }
        }

    }

    public void approvalActivity(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取业务信息
        PcOutsourceProjectEO outsourceProjectEO = this.getDao().selectByPrimaryKey(baseBusEO.getBusinessId());
        //todo 判断审批人角色，保存到委外立项单entity，用于导出

        approve(request, requestEO, baseBusEO, "委外立项单", outsourceProjectEO, this.getDao());
    }

    public void approve(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO, String project,
                        Object eo, Object dao)
            throws Exception {
        Class<? extends Object> eoClass = eo.getClass();
        Class<? extends Object> daoClass = dao.getClass();
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
            //2-退回
            eoClass.getDeclaredMethod("setStatus", String.class).invoke(
                    eo, PcOutsourceEnum.ROLLBACK.getValue());
            daoClass.getDeclaredMethod("updateByPrimaryKeySelective", eoClass).invoke(dao, eo);
        }
        //撤回
        if ("reback".equals(approveCode)) {
            //9-撤回
            eoClass.getDeclaredMethod("setStatus", String.class).invoke(
                    eo, PcOutsourceEnum.RECALL_ED.getValue());
            daoClass.getDeclaredMethod("updateByPrimaryKeySelective", eoClass).invoke(dao, eo);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        }
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //下面针对费用使用流程根据不同的费用额跳转不同流程
        Task task = taskService.createTaskQuery().taskId(requestEO.getTaskId()).singleResult();
        String taskDefinitionKey = task.getTaskDefinitionKey();
        if (!"reback".equals(approveCode) && StringUtils.isNotEmpty(taskDefinitionKey) && "executive".equals(taskDefinitionKey)){
            Double sun = 0.00;
            if(project.contains("费用使用申请")){
                String id = eoClass.getDeclaredMethod("getId").invoke(eo).toString();
                //计算费用使用的金额
                PcBudgetUseEO pcBudgetUseEO = pcBudgetUseEOService.selectByPrimaryKey(id);
                List<PcBudgetUseInfoEO> pcBudgetUseInfoEOList = pcBudgetUseEO.getPcBudgetUseInfoEOList();
                for (PcBudgetUseInfoEO s : pcBudgetUseInfoEOList){
                    String buSubtotal = s.getBuSubtotal();
                    if (StringUtils.isNotEmpty(buSubtotal)){
                        sun =  sun + Double.valueOf(buSubtotal);
                    }
                }
            }
            if (sun < 500.00){
                requestEO.getVariables().put("approve", "lessx");
            }else if (500.00 < sun && sun < 3000.00){
                requestEO.getVariables().put("approve", "xbetweeny");
            }else {
                requestEO.getVariables().put("approve", "greatery");
            }
        }
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        UserEO user1 = UserUtils.getUser();
        activitiTaskRequestVO.setAssignee(user1.getUsid());
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
        //撤回
        if (!"reback".equals(approveCode)) {
            //消息链接
            String link = baseBusEO.getBusinessType();
            //判断流程是否结束，结束的话，变更业务状态
            if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
                if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(eo)) {
                    //已审批  3-已审批
                    eoClass.getDeclaredMethod("setStatus", String.class).invoke(
                            eo, PcOutsourceEnum.APPROVAL_ED.getValue());
                    //更新当前代办人
                    eoClass.getDeclaredMethod("setCurrentWaitUserId", String.class).invoke(eo, "");
                    eoClass.getDeclaredMethod("setCurrentWaitUserName", String.class).invoke(eo, "");
                    daoClass.getDeclaredMethod("updateByPrimaryKeySelective", eoClass).invoke(dao, eo);
                    //完成给相关人员发送消息
                    String title = project + "【" + eoClass.getDeclaredMethod("getCode").invoke(eo).toString() + "】审批流程完成";
                    processSendMessages(getProcessUsersByTaskId(requestEO.getTaskId()), title, link,
                            eoClass.getDeclaredMethod("getId").toString());
                    
                    if(project.contains("费用使用申请")) {
                    	//-----同步费用管理 废弃原有的费用同步
//                    	synCostSummary(project, eo);
                        //走整合后的费用同步
                        Map<String,Object> params=new HashMap<>();
                        if(project.contains("执行计划")){
                            params.put("dataOrigin","1");
                        }else{
                            params.put("dataOrigin","0");
                        }
                        params.put("use",eo);
                        costSettlementService.saveCostSummary("PcBudgetUseList",null,params);
                    }
                }
            } else {
                //获取试验任务业务信息
                String title = project + "【" + eoClass.getDeclaredMethod("getCode").invoke(eo).toString() + "】审批流程";
                //获取下一节点人
                if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                    String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                    //更新当前代办人
                    Set<String> userArray = new HashSet<>(Arrays.asList(ids));
                    List<String> userNames = new ArrayList<>();
                    for (Iterator<String> iterator = userArray.iterator(); iterator.hasNext(); ) {
                        UserEO user = userEODao.selectByPrimaryKey(iterator.next());
                        if (StringUtils.isNotEmpty(user) && StringUtils.isNotBlank(user.getUsname())) {
                            userNames.add(user.getUsname());
                        }
                    }
                    eoClass.getDeclaredMethod("setCurrentWaitUserId", String.class).invoke(eo,
                            baseBusEODao.fingNextUserId(requestEO.getProcId()));
                    eoClass.getDeclaredMethod("setCurrentWaitUserName", String.class).invoke(eo,
                            StringUtils.join(userNames, ConstantUtils.COMMA));
                    if(!"rollback".equals(approveCode)){
                        eoClass.getDeclaredMethod("setStatus", String.class).invoke(eo,
                                PcOutsourceEnum.UN_APPROVAL.getValue());
                    }
                    daoClass.getDeclaredMethod("updateByPrimaryKeySelective", eoClass).invoke(dao, eo);
                    //发送消息通知、工作日历、邮件
                    processSendMessages(ids, title, link, eoClass.getDeclaredMethod("getId").invoke(eo).toString());
                }
            }
        }

    }

	
    /**
     * 同步费用管理
     * @Title: synCostSummary
     * @param project
     * @param eo
     * @throws Exception
     * @return void
     * @author: ljy
     * @date: 2020年8月20日
     */
    /*public void synCostSummary(String project, Object eo) throws Exception {
    	PcBudgetUseEO eoClass = (PcBudgetUseEO)eo;
    	//试验任务id
    	String trialTaskId = eoClass.getTrialId();
    	String useId = eoClass.getId();
    	String useNo = eoClass.getCode();
    	PcBudgetUseEO use = pcBudgetUseEOService.selectByPrimaryKey(useId);
    	List<PcBudgetUseInfoEO> useInfoList = use.getPcBudgetUseInfoEOList();
    	//数据来源(“0”是PV/CV,“1”是认证)
    	if(project.contains("执行计划")) {
    		AnnPlanProjectEO annProject = annPlanProjectEOService.selectByPrimaryKey(trialTaskId);
    		AnnPlanEO annPlan = annPlanEOService.selectByPrimaryKey(annProject.getPlanId());
			saveCostSummary("0", useInfoList, trialTaskId, useId, annPlan.getPlName(),
					annProject.getPjModel(), annProject.getPjEngineerName(),
					annProject.getPjTaskExplain(), useNo, "", "");
    	}else {
    		TrialTaskEO trialTaskEO = trialTaskeoDao.selectByPrimaryKey(trialTaskId);
    		//数据来源(“0”是PV/CV,“1”是认证)
    		List<TrialPersonEO> personList = pcTrialExecuteService.getPersonList(trialTaskId);
    		//试验工程师
    		Set<String> engineer = new HashSet<String>();
    		for (TrialPersonEO person : personList) {
				if(StringUtils.isNotEmpty(person.getPersonRoleId()) && 
					(person.getPersonRoleId().contains("CV_TrialEngineer") ||
					 person.getPersonRoleId().contains("PV_TrialEngineer"))) {
					engineer.add(person.getPersonName());
				}
			}
    		//车ids
    		Set<String> carIds = new HashSet<String>();
    		//车型
    		Set<String> carTypes = new HashSet<String>();
    		List<TrialItemsEO> items = trialTaskEO.getTrialItemsEOList();
    		for (TrialItemsEO item : items) {
    			//车ids
    			if(StringUtils.isNotEmpty(item.getCarId())) {
    				carIds.add(item.getCarId());
    			}
    			//车型
    			if(StringUtils.isNotEmpty(item.getSampleType())) {
    				carTypes.add(item.getSampleType());
    			}
			}
    		saveCostSummary("1", useInfoList, trialTaskId, useId, trialTaskEO.getTaskCode(),
    				String.join(",", carTypes), String.join(",", engineer), "", useNo, "", 
    				String.join(",", carIds));
    	}
    }*/
    
    
	/**
	 * 保存费用结算信息
	 * @Title: saveCostSummary
	 * @param dataOrigin   数据来源
	 * @param useInfoList  费用子表详细信息
	 * @param trialTaskId  试验任务id/ 认证试验任务id
	 * @param useId        费用使用id
	 * @param taskName     试验任务编号/认证试验名称
	 * @param carType      车型
	 * @param engineer     工程师
	 * @param taskDesc     试验任务说明
	 * @param trustNo      委外立项单编号
	 * @param chassisNos   底盘号
	 * @param carIds       车ids
	 * @throws Exception  
	 * @return void
	 * @author: ljy
	 * @date: 2020年8月20日
	 */
    /*public void saveCostSummary(String dataOrigin, List<PcBudgetUseInfoEO> useInfoList, String trialTaskId,
    		String useId, String taskName, String carType, String engineer, String taskDesc,
    		String trustNo, String chassisNos, String carIds) throws Exception {
    	//筛选出所有的供应商
		Map<String, String> map = new HashMap<String, String>();
		for(PcBudgetUseInfoEO useInfo : useInfoList) {
			if(StringUtils.isNotEmpty(useInfo.getBuSupplierId())) {
				AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(useInfo.getBuSupplierId());
				//维修类供应商
				if("2".equals(abilityVO.getSupType())) {
					//费用类型   维修费-对公 - 3
					map.put(useInfo.getBuSupplierId(), "3");
					if("0".equals(dataOrigin)) {
						taskDesc = "维修费";
					}
				}
				//场地燃料供应商
				if("6".equals(abilityVO.getSupType())) {
					//费用类型  场地燃料费 - 5
					map.put(useInfo.getBuSupplierId(), "5");
					if("0".equals(dataOrigin)) {
						taskDesc = "场地燃料费";
					}
				}

			}

		}
		for(Map.Entry<String, String> entry : map.entrySet()){
			CostSummaryEO costSummary = new CostSummaryEO();
        	costSummary.setId(UUID.randomUUID());
        	costSummary.setDelFlag("0");
        	//认证任务id
        	costSummary.setTrialTaskId(trialTaskId);
        	//数据来源(“0”是PV/CV,“1”是认证)
        	costSummary.setDataOrigin(dataOrigin);
        	//执行计划名称
    		costSummary.setTaskName(taskName);
    		//车型(多个用","隔开)
    		costSummary.setCarType(carType);
    		//车辆ids
    		costSummary.setCarIds(carIds);
    		//底盘号
    		costSummary.setChassisNo(chassisNos);
    		//负责人(试验工程师)
    		costSummary.setEngineer(engineer);
    		//任务简要
    		costSummary.setTaskDesc(taskDesc);
    		//编辑状态(驳回1/激活0)
    		costSummary.setEditStatus("0");
    		//费用类型
    		costSummary.setCostType(entry.getValue());
    		//formKey用于跳转对应列表页
    		costSummary.setFormKey("PcBudgetUseList");
    		//费用金额
    		String cost = pcBudgetUseInfoEOService.selectCostByBuIdAndSupId(useId, entry.getKey());
    		costSummary.setTotalCost(cost);
    		//委托单任务编号
    		costSummary.setTrustNo(trustNo);
    		//供应商id
    		costSummary.setSupId(entry.getKey());
    		//供应商名字
    		AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(entry.getKey());
    		costSummary.setSupName(abilityVO.getSupName());
    		//供应商code
    		costSummary.setSupCode(abilityVO.getSupCode());
    		//供应商类型
    		costSummary.setSupType(entry.getValue());
    		//结算金额
    		costSummary.setPaymentCost(cost);
    		//结算状态(0:未结算  1结算中   2已结算)
    		costSummary.setPaymentStatus("0");
    		costSummaryEOService.insert(costSummary);
		}
    }*/
    
    
    
    
    
    
    
    
    private String[] getProcessUsersByTaskId(String taskId) {
        //退回给相关人员发送消息
        List<ActivitiTaskVO> taskList = activitiTaskService.getProcessingRecords(taskId);
        Set<String> set = new HashSet<>();
        for (ActivitiTaskVO vo : taskList) {
            if (StringUtils.isNotEmpty(vo.getCurrentUsersIds())) {
                String[] arr = vo.getCurrentUsersIds().split(ConstantUtils.COMMA);
                for (String str : arr) {
                    set.add(str);
                }
            }
        }
        //返回
        return set.toArray(new String[set.size()]);
    }

    public List<PcOutsourceProjectEO> query(PcOutsourceProjectEOPage page) throws Exception {
        Integer rowCount = this.queryByCount(page);
        page.getPager().setRowCount(rowCount);
        List<PcOutsourceProjectEO> rows = this.getDao().queryByPage(page);
        if (CollectionUtils.isNotEmpty(rows)) {
            for (PcOutsourceProjectEO row : rows) {
                List<BaseBusEO> baseBusEOS = baseBusEODao.selectByBusinessId(row.getId());
                if (CollectionUtils.isNotEmpty(baseBusEOS)) {
                    row.setBusinessKey(baseBusEOS.get(0).getId());
                }
            }
        }
        return rows;
    }

    public String exportFile(HttpServletRequest request, HttpServletResponse response, String id) {
        // 查询要导出的内容
        PcOutsourceProjectEO outsourceProjectEO = this.find(id);
        if (outsourceProjectEO != null) {
        	outsourceProjectEO.setInsType("委外试验");
            //------------流程信息-------------------//
            String actProcId = this.getDao().selectActProcIdById(id);
            Map<String, Object> stringObjectMap = new HashMap<>();
            if (StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
                List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
                stringObjectMap.put("SECTIONCHIEF", list.get(1).getAssigneeName());
                //批准--部长
                stringObjectMap.put("MINISTER", list.get(0).getAssigneeName());
            }
            stringObjectMap.put("object", outsourceProjectEO);
            // 文件后后缀
            String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
            //填充数据 并导出   //**此处需求变更：由模板填数据走流下载  变更为 先生成本地的doc文件然后将此文件加入签章 **//
//          docUtil.createDoc(stringObjectMap, "outsource", response, request,"委外立项申请单", fileExtend);

            //根据附件id , 获取模板路径
            if(StringUtils.isNotBlank(outsourceProjectEO.getFileId())) {
                String path = tsAttachmentEODao.selectByPrimaryKey(outsourceProjectEO.getFileId()).getSavePath();
                return filepath + path;
            }
            String NewPath = "pcOutsourceProject/" + outsourceProjectEO.getCode() + "/" + UUID.randomUUID()+fileExtend;
            File file = new File(filepath+"pcOutsourceProject/"+outsourceProjectEO.getCode());
            if(!file.exists()) file.mkdir();

            docUtil.createDoc(stringObjectMap,"outsource",filepath + NewPath);

            if (StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {   //签章
                Map<String, String> pathMap = new HashMap<>();
                pathMap.put("PC_SectionChief_pic", (String)stringObjectMap.get("SECTIONCHIEF"));  //科长
                pathMap.put("PC_Minister_pic", (String)stringObjectMap.get("MINISTER"));    //部长
                try {
                    pcReportEOService.writeWordAndToPDF(pathMap, filepath + NewPath);

                    //将填充完数据的报告数据存储至附件
                    TsAttachmentEO attachment = new TsAttachmentEO();
                    attachment.setAttachmentName(outsourceProjectEO.getInsProject());
                    attachment.setId(UUID.randomUUID());
                    attachment.setAttachmentType(ConstantUtils.FILE_WORD_DOCX);
                    attachment.setSavePath(NewPath);
                    attachment.setDelFlag(0);
                    attachment.setCreateBy(UserUtils.getUserId());
                    attachment.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                    attachment.setUpdateBy(UserUtils.getUserId());
                    attachment.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                    tsAttachmentEODao.insert(attachment);

                    outsourceProjectEO.setFileId(attachment.getId());
                    outsourceProjectEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                    this.updateByPrimaryKeySelective(outsourceProjectEO);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.info("委外立项插入签章失败");
                }
            }
            return filepath + NewPath;
        }
            return "";
    }
    
    /**
     *  根据委外立项单id 获取 检验项目列表(价格汇总后的)
     * @Title: choseSup
     * @param id
     * @return
     * @return List<PCOutsourceSupProEO>
     * @author: ljy
     * @date: 2020年3月12日
     */
    public List<PCOutsourceSupProEO> choseSup(String id) {
    	return PCOutsourceSupProEODao.selectSupProList(id);
    }
    
}
