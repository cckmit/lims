package com.adc.da.trial_task.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.RoleEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.trial_task.dao.TrialTaskEODao;
import com.adc.da.trial_task.dao.TrialtaskInsproEODao;
import com.adc.da.trial_task.dao.TrialtaskSampleEODao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.entity.TrialtaskInsproEO;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.trial_task.page.TrialTaskEOPage;
import com.adc.da.trial_task.vo.TrialtaskInsProjectVO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import com.alibaba.fastjson.JSONObject;

@Service("trialTaskEOService")
@Transactional(value = "transactionManager", readOnly = false,
        rollbackFor = Throwable.class)
public class TrialTaskEOService extends BaseService<TrialTaskEO,String> {

    @Autowired
    private TrialTaskEODao trialTaskEODao;
    @Autowired
    private TrialtaskInsproEODao insproEODao;
    @Autowired
    private TrialtaskSampleEODao sampleEODao;
    
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
    private TsAttachmentEODao tsAttachmentEODao;
    
    @Autowired
    private OrgEOService orgEOService;
    
    @Autowired
    private RoleEOService roleEOService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private HistoryService historyService;
    
    @Value("${file.path}")
    private String filepath;


    public TrialTaskEODao getDao(){
        return  trialTaskEODao;
    }

    @Autowired
    private DocUtil docUtil;
    
    @Autowired
    private TrialtaskSampleEODao trialtaskSampleEODao;
    
    @Autowired
    private SaCarDataDAO saCarDataDao;
    
    
    /*
     * @Author syxy_zhangyinghui
     * @Description 保存或修改试验任务信息
     * @Date 14:04 2019/8/20
     * @Param
     * @return
     **/
    public void saveOrUpdateTrialTask(TrialTaskEO trialTaskEO, List<TrialtaskInsproEO> trialtaskInsproEOList,
                                        List<TrialtaskSampleEO> trialtaskSampleEOList,
                                        MultipartFile taskFile, MultipartFile operateFile) throws Exception{
        //获取当前登录用户
        UserEO user = UserUtils.getUser();
        String userId = user.getUsid();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        if(StringUtils.isEmpty(trialTaskEO.getId())){
            //新增操作
            trialTaskEO.setId(UUID.randomUUID10());
            //设置删除、0 正常  1 删除
            trialTaskEO.setDelFlag("0");
            //任务状态  0-草稿,1-审批中,2-退回,3-已审批,4-已取消,5-试验中,6-试验结束,7-报告审批中,8-试验完成
            trialTaskEO.setTrialStatus("0");
            //设置延期时间 默认为0  未延期
            trialTaskEO.setFinishdateDelydays(0);
            //创建人为当前登录人
            trialTaskEO.setCreateBy(userId);
            trialTaskEO.setCreateTime(currentTime);
            if(StringUtils.isNotEmpty(taskFile)) {
            	 //任务书附件iD
                trialTaskEO.setTaskFileid(limsFileService.saveAttachment(taskFile));
            }
            if(StringUtils.isNotEmpty(operateFile)) {
            	 //试验规范附件id
                trialTaskEO.setOperationFileid(limsFileService.saveAttachment(operateFile));
            }
            //未完成
            trialTaskEO.setTrialReportStatus("0");
            
            int num = trialTaskEODao.insertTrialTask(trialTaskEO);
            if(num>0){
                String id=trialTaskEO.getId();
                //新增试验任务检验项目信息
                this.saveTrialtaskInspro(id,trialtaskInsproEOList);
                //新增试验任务样品信息
                this.saveTrialtaskSample(id,trialtaskSampleEOList);
            }else{
                throw new Exception("添加试验任务失败！");
            }
        }else{
            //修改操作
            trialTaskEO.setUpdateBy(userId);
            trialTaskEO.setUpdateTime(currentTime);
            if(StringUtils.isNotEmpty(taskFile)) {
            	if(StringUtils.isNotEmpty(trialTaskEO.getTaskFileid())) {
            		TsAttachmentEO accessoryFile = tsAttachmentEODao.selectByPrimaryKey(trialTaskEO.getTaskFileid());
        			//附件表删除
        			tsAttachmentEODao.deleteByPrimaryKey(trialTaskEO.getTaskFileid());
        			//真正文件删除
        			FileUtil.deleteFile(filepath + accessoryFile.getSavePath());
            	}
           	    //任务书附件iD
                trialTaskEO.setTaskFileid(limsFileService.saveAttachment(taskFile));
            }
            if(StringUtils.isNotEmpty(operateFile)) {
            	if(StringUtils.isNotEmpty(trialTaskEO.getOperationFileid())) {
            		TsAttachmentEO accessoryFile = tsAttachmentEODao.selectByPrimaryKey(trialTaskEO.getOperationFileid());
        			//附件表删除
        			tsAttachmentEODao.deleteByPrimaryKey(trialTaskEO.getOperationFileid());
        			//真正文件删除
        			FileUtil.deleteFile(filepath + accessoryFile.getSavePath());
            	}
           	    //试验规范附件id
                trialTaskEO.setOperationFileid(limsFileService.saveAttachment(operateFile));
            }
            int num = trialTaskEODao.updateTrialTask(trialTaskEO);
            if(num>0){
                String id=trialTaskEO.getId();
                //更新试验任务检验项目信息
                this.saveTrialtaskInspro(id,trialtaskInsproEOList);
                //更新试验任务样品信息
                this.saveTrialtaskSample(id,trialtaskSampleEOList);
            }else{
                throw new Exception("修改试验任务失败！");
            }
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 保存试验任务的检验项目信息
     * @Date 16:36 2019/8/20
     * @Param 
     * @return 
     **/
    private void saveTrialtaskInspro(String trialTaskId,
                                       List<TrialtaskInsproEO> trialtaskInsproEOList) throws Exception{
        //根据trialTaskId查询检验项目若有全部删除重新添加
        List<TrialtaskInsproEO> insProList = insproEODao.findTrialtaskInsproByTrialtaskId(trialTaskId);
        if(CollectionUtils.isNotEmpty(insProList)){
           insproEODao.deleteTrialtaskInsproByTrialTaskId(trialTaskId);
        }

        for (TrialtaskInsproEO trialtaskInsproEO : trialtaskInsproEOList) {
        	trialtaskInsproEO.setId(UUID.randomUUID());
            trialtaskInsproEO.setTrialtaskId(trialTaskId);
            //0-无报告(默认)
            trialtaskInsproEO.setReportStatus("0");
            int num = insproEODao.insertTrialtaskInspro(trialtaskInsproEO);
            if(num<=0){
                throw new Exception("添加试验任务检验项目失败！");
            }
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 保存试验任务的样品信息
     * @Date 16:51 2019/8/20
     * @Param 
     * @return 
     **/
    private void saveTrialtaskSample(String trialTaskId,
                                     List<TrialtaskSampleEO> trialtaskSampleEOList) throws Exception{
        List<TrialtaskSampleEO> sampleList = sampleEODao.findTrialtaskSampleByTrialtaskId(trialTaskId);
        if(StringUtils.isNotEmpty(sampleList)){
            sampleEODao.deleteTrialtaskSampleByTrialtaskId(trialTaskId);
        }
        List<String> participantList = new ArrayList<>();
        for (TrialtaskSampleEO trialtaskSampleEO : trialtaskSampleEOList) {
        	trialtaskSampleEO.setId(UUID.randomUUID());
            trialtaskSampleEO.setTrialtaskId(trialTaskId);
            int num = sampleEODao.insertTrialtaskSample(trialtaskSampleEO);
            if(num<=0){
                throw new Exception("添加试验任务样品失败！");
            }else {
            	//根据台架id,查询台架下的人员
//            	List<UserEO> users = orgEOService
//            			.listUserEOByOrgId(trialtaskSampleEO.getScaffoldingId());
//            	for (int j = 0; j < users.size(); j++) {
//            		//如果该用户拥有试验员角色,则返回
//            		for (int i = 0; i < users.get(j).getRoleEOList().size(); i++) {
//						if(ConstantUtils.EV_RESTER_ROLEID.equals(users.get(j).getRoleEOList().get(i).getId())) {
//							participantList.add(users.get(j).getUsid());
//						}
//					}
//                }
            	
            	//============2020.05.15变更,新增班长角色,班长进行分配,原初始化台架下人员逻辑↑去除==============//
            	//根据当前台架id,查询上级组织机构信息
            	OrgEO org = orgEOService.getOrgEOById(trialtaskSampleEO.getScaffoldingId());
            	List<UserEO> users = orgEOService
            			.listUserEOByOrgId(org.getParentId());
            	for (int j = 0; j < users.size(); j++) {
            		//如果该用户拥有发动机班长角色,则返回
            		for (int i = 0; i < users.get(j).getRoleEOList().size(); i++) {
						if(ConstantUtils.EV_MONITOR_ROLEID.equals(users.get(j).getRoleEOList().get(i).getId())) {
							participantList.add(users.get(j).getUsid());
						}
					}
                }
            	
            }
        }
        //反向更新试验申请中的信息
        if(CollectionUtils.isNotEmpty(participantList)) {
        	String personIds = StringUtils.join(participantList, ConstantUtils.COMMA);
            TrialTaskEO eo = trialTaskEODao.selectByPrimaryKey(trialTaskId);
            eo.setPersonIds(personIds);
            trialTaskEODao.updateTrialTask(eo);
        }
        
    }

    /**
     * 提交试验任务流程
    * @Title：submitTrialTask
    * @param trialTaskEO
    * @param trialtaskInsproEOList
    * @param trialtaskSampleEOList
    * @throws Exception
    * @return: void
    * @author： ljy  
    * @date： 2019年8月31日
     */
    public void submitTrialTask(TrialTaskEO trialTaskEO, List<TrialtaskInsproEO> trialtaskInsproEOList,
            List<TrialtaskSampleEO> trialtaskSampleEOList,
            MultipartFile taskFile, MultipartFile operateFile, String nextAssignee) throws Exception {
    	//先保存信息,后提交流程
    	saveOrUpdateTrialTask(trialTaskEO, trialtaskInsproEOList, trialtaskSampleEOList, taskFile, operateFile);
    	//启动流程
    	//保存业务BASEBUS
    	String title = "试验任务【"+ trialTaskEO.getEvNumber() +"】审批流程";
    	String baseBusId = limsFileService.saveBaseBus(trialTaskEO.getId(), 
    				ConstantUtils.EV_TRIALTASK_BUSINESS_TYPE, title);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
        		ConstantUtils.PROCESS_CODE,ConstantUtils.EV_TRIALTASK_BUSINESS_TYPE);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        
        List<RestVariable> variables = new ArrayList<>();
        //设置发动机主管节点办理人
        if(StringUtils.isNotEmpty(nextAssignee)){        	
        	RestVariable rv = new RestVariable();
        	rv.setName("zg");
        	rv.setValue(nextAssignee);
        	variables.add(rv);
        }
        //设置组织机构
        
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
    	
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //修改试验申请状态为审批中  1-审批中
        trialTaskEO.setTrialStatus("1");
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if(StringUtils.isNotEmpty(ids)) {
        	String[] splits = ids.split(ConstantUtils.COMMA);
        	Set<String> userArray = new HashSet<String>(Arrays.asList(splits));
        	//循环设置当前待办人的id和name
        	List<String> userNames = new ArrayList<>();
        	for (String usid : userArray) {
        		UserEO user = userEODao.selectByPrimaryKey(usid);
    			if(StringUtils.isNotEmpty(user) &&
    					StringUtils.isNotEmpty(user.getUsname())) {
    				userNames.add(user.getUsname());
    			}
        	}
        	//将代办人id  name 设置至实体, 并更新
        	trialTaskEO.setCurrentWaitUserid(String.join(ConstantUtils.COMMA, userArray));
        	trialTaskEO.setCurrentWaitUsername(StringUtils.
        			join(userNames, ConstantUtils.COMMA));
        	trialTaskEODao.updateTrialTask(trialTaskEO);
        	//链接到试验审批表单页
    		String link = ConstantUtils.EV_TRIALTASK_BUSINESS_TYPE;
    		limsFileService.processSendMessages(splits, title, link, trialTaskEO.getId());
        }
    }

    /**
     * 试验任务审批流程
    * @Title：approvalProcess
    * @param request
    * @param requestEO
    * @throws Exception
    * @return: void
    * @author： ljy  
    * @date： 2019年8月31日
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
    	//获取业务与流程关联表信息
    	BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取试验任务业务信息
        TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
    	Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //退回
        if("rollback".equals(approveCode)){
        	//2-退回
        	trialTaskEO.setTrialStatus("2");
        	trialTaskEODao.updateTrialTask(trialTaskEO);
        }
        //撤回
        else if("reback".equals(approveCode)){
        	//9-撤回
        	trialTaskEO.setTrialStatus("9");
        	trialTaskEODao.updateTrialTask(trialTaskEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve","rollback");
        }else {
        	trialTaskEO.setTrialStatus("1");
        	trialTaskEODao.updateTrialTask(trialTaskEO);
        }
        
        //设置下一节点审批人
        if(StringUtils.isNotEmpty(requestEO.getNextAssignee())){
        	Task task = taskService.createTaskQuery().taskId(requestEO.getTaskId()).singleResult();
        	if("发动机主管".equals(task.getName())){
        		//设置发动机科长节点审批人
        		requestEO.getVariables().put("kz", requestEO.getNextAssignee());
        	}else if("发起人".equals(task.getName())){
        		//设置发动机主管节点审批人
        		requestEO.getVariables().put("zg", requestEO.getNextAssignee());
        	}
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
        if("0".equals(requestEO.getType())){
        	//设置节点办理人，覆盖流程图中默认设置的办理人
        	taskService.setAssignee(requestEO.getTaskId(), UserUtils.getUserId());
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        }else if("1".equals(requestEO.getType())){
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            //取消认领，避免completeCandidateTask方法中对节点进行认领时报错
            taskService.unclaim(requestEO.getTaskId());
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(trialTaskEO)) {
            	//已审批  3-已审批
            	trialTaskEO.setTrialStatus("3");
            	//更新当前代办人
            	trialTaskEO.setCurrentWaitUserid("");
            	trialTaskEO.setCurrentWaitUsername("");
            	trialTaskEODao.updateTrialTask(trialTaskEO);
            	//完成给相关人员发送消息
            	String title = "试验任务【"+ trialTaskEO.getEvNumber() +"】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                		getProcessUsersByTaskId(requestEO.getTaskId()), 
                		title, link, trialTaskEO.getId());
            }
        }else{
        	//获取试验任务业务信息
        	String title = "试验任务【"+ trialTaskEO.getEvNumber() +"】审批流程";
        	//获取下一节点人
        	if(StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
        		String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
        		//更新当前代办人
        		List<String> userArray = Arrays.asList(ids);
        		List<String> userNames = new ArrayList<>();
            	for (int i = 0; i < userArray.size(); i++) {
            		UserEO user = userEODao.selectByPrimaryKey(userArray.get(i));
        			if(StringUtils.isNotEmpty(user) &&
        					StringUtils.isNotEmpty(user.getUsname())) {
        				userNames.add(user.getUsname());
        			}
            	}
            	trialTaskEO.setCurrentWaitUserid(baseBusEODao.fingNextUserId(requestEO.getProcId()));
            	trialTaskEO.setCurrentWaitUsername(StringUtils.
            			join(userNames, ConstantUtils.COMMA));
            	//修改试验申请状态为审批中  1-审批中
                //trialTaskEO.setTrialStatus("1");
            	trialTaskEODao.updateTrialTask(trialTaskEO);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, link, trialTaskEO.getId());
        	}
        }
    }
    
    /**
     * 试验任务分页查询
    * @Title：page
    * @param page
    * @param searchPhrase
    * @return
    * @return: List<TrialTaskEO>
    * @author： ljy  
    * @date： 2019年9月1日
     * @throws Exception 
     */
    public List<TrialTaskEO> page(TrialTaskEOPage page, String searchPhrase) throws Exception{
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            //将查询条件循环放入list中
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
		//--------单条件查询-------//
		//获取当前登录用户角色
		String roleIds = UserUtils.getRoleIds();
		//如果当前登录用户为试验工程师, 则需过滤数据, 只显示该用户的数据
//		if(roleIds.contains(ConstantUtils.EV_TRIALENGINEER_ROLEID)) {
//			page.setCreateBy(UserUtils.getUserId());
//		}
		if(roleIds.contains(ConstantUtils.ADMIN_ROLEID)){
			//查询全部
			return trialTaskEODao.queryByPage(page);
		}
		//如果当前登录用户为部长/科长/主管/试验工程师管理员, 则需过滤数据
		else if(roleIds.contains(ConstantUtils.EV_MINISTER_ROLEID) ||
		   roleIds.contains(ConstantUtils.EV_SECTIONCHIEF_ROLEID) ||
		   roleIds.contains(ConstantUtils.EV_CHARGE_ROLEID) ||
		   roleIds.contains(ConstantUtils.EV_TASKMANAGER_ROLEID)) {
			List<String> orgIds = userEODao.getOrgIdListByUserId(UserUtils.getUserId());
			if(CollectionUtils.isNotEmpty(orgIds)) {
				List<String> userIds = new ArrayList<String>();
 				List<OrgEO> orgList = orgEOService.getChildren(orgIds.get(0));
 				for (int i = 0; i < orgList.size(); i++) {
 					userIds.addAll(limsFileService.getUserIdsByOrgId(orgList.get(i).getId()));
				}
				page.setCreateByIds(userIds);
			}
		}else { //个人
			page.setCreateBy(UserUtils.getUserId());
		}
		//查询
		return trialTaskEODao.queryByPage(page);
    }

    
    public List<TrialTaskEO> findAllList(){
        return trialTaskEODao.findAllList();
    }
    
    /**
     * 试验任务编号查重
     * @Title: checkNo
     * @param evNumber
     * @param id
     * @return
     * @return boolean
     * @author: ljy
     * @date: 2020年1月2日
     */
    public boolean checkNo(String evNumber, String id) {
    	List<String> numList = trialTaskEODao.checkNo(id);
    	return numList.contains(evNumber);
    }
    
    /**
     * 发动机试验任务-详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<TrialTaskVO>
    * @author： ljy  
    * @date： 2019年9月4日
     */
    public TrialTaskEO getDetailById(String id) {
    	//根据id查询详情
    	TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(id);
    	//查询任务书附件名
    	if(StringUtils.isNotEmpty(trialTaskEO) &&
    			StringUtils.isNotEmpty(trialTaskEO.getTaskFileid())) {
    		TsAttachmentEO attachmentEO = tsAttachmentEODao.
    				selectByPrimaryKey(trialTaskEO.getTaskFileid());
    		trialTaskEO.setTaskFileName(attachmentEO.getAttachmentName());
    	}
    	//查询操作规范附件名
    	if(StringUtils.isNotEmpty(trialTaskEO) &&
    			StringUtils.isNotEmpty(trialTaskEO.getOperationFileid())) {
    		TsAttachmentEO attachmentEO = tsAttachmentEODao.
    				selectByPrimaryKey(trialTaskEO.getOperationFileid());
    		trialTaskEO.setOperationFileName(attachmentEO.getAttachmentName());
    	}
    	//返回
    	return trialTaskEO;
    }

    /**
     * 发动机试验任务-点击“+”查询
     * @param id
     * @return
     */
    public List<TrialtaskInsProjectVO> queryTrialProject(String id) {
        return this.getDao().queryTrialProject(id);
    }
    /**
     * 发动机试验任务导出
    * @Title：exportTrialTask
    * @param response
    * @param trialTaskId
    * @return: void
    * @author： ljy  
    * @date： 2019年9月10日
     */
    public void exportTrialTask(HttpServletResponse response,HttpServletRequest request, String trialTaskId) {
    	//获取试验信息
    	TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(trialTaskId);
    	//获取样品信息
    	Map<String, String> map = getSampleInfo(trialTaskId);
    	//封装数据
    	Map<String, Object> stringObjectMap = new HashMap<>();
    	//试验编号
    	stringObjectMap.put("EV_TRIALTASK_NO", trialTaskEO.getEvNumber());
    	//紧急程度  1 特急 2 紧急 3 一般
        stringObjectMap.put("emergencyCheck", trialTaskEO.getEvEmergency());
        //任务名称
        stringObjectMap.put("EV_TRIALTASK_NAME", trialTaskEO.getTitle());
        //根据
        stringObjectMap.put("EV_TRIALTASK_REASON", trialTaskEO.getReason());
        //完成试验日期
        stringObjectMap.put("EV_TRIALTASK_FINISHTIME", trialTaskEO.getFinishTime());
        //目的
        stringObjectMap.put("EV_TRIALTASK_PURPOSE", trialTaskEO.getPurpose());
        //试验要求
        stringObjectMap.put("EV_REQUIREMENT", trialTaskEO.getRequirement());
        //机型
        stringObjectMap.put("EV_SAMPLE_ENGINE_TYPE", map.get("type"));
        //发动机编号
        stringObjectMap.put("EV_SAMPLE_NO", map.get("sampleNo"));
        //设计
        String EV_COMPILER = userEODao.selectByPrimaryKey(trialTaskEO.getCreateBy()).getUsname();
        stringObjectMap.put("EV_COMPILER", EV_COMPILER);
        //------------流程信息-------------------//
        String actProcId = trialTaskEODao.selectActProcIdById(trialTaskId);
        String EV_CHECKER = "";
        String EV_APPROVER = "";
        String EV_APPROVAL_DATE = "";
        if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
    		 List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
    		 EV_CHECKER = list.get(1).getAssigneeName();
    		 EV_APPROVER = list.get(0).getAssigneeName();
    		 EV_APPROVAL_DATE = list.get(0).getFinishTimeStr();
        }
        //审核--主管
        stringObjectMap.put("EV_CHECKER", EV_CHECKER);
        //批准--科长
        stringObjectMap.put("EV_APPROVER", EV_APPROVER);
        //批准日期
        stringObjectMap.put("EV_APPROVAL_DATE", EV_APPROVAL_DATE);
        //定义文件名称
        String fileName = trialTaskEO.getTitle() + "【" + trialTaskEO.getEvNumber() + "】";
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(stringObjectMap, "EVTrialTaskWord", response, request, 
        		fileName, fileExtend);
    }
    
    
    /**
     * 根据试验任务id,获取样品信息
    * @Title：getSampleInfo
    * @param trialTaskId
    * @return
    * @return: Map<String,String>
    * @author： ljy  
    * @date： 2019年9月10日
     */
    public Map<String, String> getSampleInfo(String trialTaskId){
    	//样品型号集合
    	List<String> typeList = new ArrayList<>();
    	//样品编号集合
    	List<String> sampleNoList = new ArrayList<>();
    	//根据试验任务id,获取样品关联集合
    	List<TrialtaskSampleEO> trialtaskSampleList = trialtaskSampleEODao.
    			findTrialtaskSampleByTrialtaskId(trialTaskId);
    	for (int i = 0; i < trialtaskSampleList.size(); i++) {
    		//循环获取样品信息
    		SaCarDataEO sample = saCarDataDao.selectByPrimaryKey(
    				trialtaskSampleList.get(i).getSampleId());
    		//获取样品
    		String type = "";
    		String sampleNo = "";
    		if(StringUtils.isNotEmpty(sample)) {
    			//样品型号
    			type = sample.getCarEngineType();
    			typeList.add(type);
    			//样品编号
    			sampleNo = sample.getCarEngineNo();
        		sampleNoList.add(sampleNo);
    		}
		}
    	//将集合形成map返回
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("type", StringUtils.join(typeList,ConstantUtils.COMMA));
    	map.put("sampleNo", StringUtils.join(sampleNoList,ConstantUtils.COMMA));
    	return map;
    }

    /**
     * 发动机试验任务-流程发起或审批时，查询下一节点审批人
     * @param prodefkey
     * @param taskId
     * @return
     * @throws Exception 
     */
	public List<Map<String, Object>> getNextAssignee(String prodefkey, String taskId) throws Exception {
		List<Map<String, Object>> result = null;
		// EV-试验任务审批流程：开始->发动机主管->发动机科长->结束
		String evTrialDic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
        		ConstantUtils.PROCESS_CODE,ConstantUtils.EV_TRIALTASK_BUSINESS_TYPE).getDicTypeName();
		// EV-试验报告审批流程：开始->发动机主管->发动机科长->发动机部长->结束
		String evReportDic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
				ConstantUtils.PROCESS_CODE,ConstantUtils.EV_REPORT_BUSINESS_TYPE).getDicTypeName();
		// PV-试验报告审批流程：开始->PV主管->PV科长->PV部长->结束
		String pvReportDic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
				ConstantUtils.PROCESS_CODE,ConstantUtils.PV_REPORT_BUSINESS_TYPE).getDicTypeName();
		// CV-试验报告审批流程：开始->试验中心主管->试验中心科长->试验中心部长->结束
		String cvReportDic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
				ConstantUtils.PROCESS_CODE,ConstantUtils.CV_REPORT_BUSINESS_TYPE).getDicTypeName();
		// PV/CV试验变更流程：开始->主管->科长->结束
		String trialTaskChangeDic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
				ConstantUtils.PROCESS_CODE,ConstantUtils.PC_TRIAL_TASK_CHANGE).getDicTypeName();
		
		//获取orgId
		String orgId = null;
		if(StringUtils.isEmpty(taskId)) {
			//taskId为空，说明是流程尚未启动，根据当前登录人获取组织机构id
			String userId = UserUtils.getUserId();
			orgId = userEODao.getOrgIdByUserId(userId);
		}else {
			//从流程变量中获取orgId
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			orgId = (String) runtimeService.getVariable(task.getProcessInstanceId(), "specialOrgId");
		}
		
		if(evTrialDic.equals(prodefkey)){
			// EV-试验任务审批流程
			if(StringUtils.isEmpty(taskId)){
				//taskId为空，说明是流程尚未启动，查询发动机主管
				result = userEOService.getUsersByRoleAndOrg("ZLRKHFJ2G3", orgId);
			}else{
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				String taskName = task.getName();//当前节点的名称
				if("发动机主管".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("XJVPP98MXT", orgId);//查询发动机科长
				}else if("发起人".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("ZLRKHFJ2G3", orgId);//查询发动机主管
				}
			}
		}else if(evReportDic.equals(prodefkey)){
			// EV-试验报告审批流程
			if(StringUtils.isEmpty(taskId)){
				//taskId为空，说明是流程尚未启动，查询发动机主管
				result = userEOService.getUsersByRoleAndOrg("ZLRKHFJ2G3", orgId);
			}else{
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				String taskName = task.getName();//当前节点的名称
				if("发动机主管".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("XJVPP98MXT", orgId);//查询发动机科长
				}else if("发动机科长".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("QRRM6U3G2K", orgId);//查询发动机部长
				}else if("发起人".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("ZLRKHFJ2G3", orgId);//查询发动机主管
				}
			}
		}else if(pvReportDic.equals(prodefkey)){
			// PV-试验报告审批流程
			if(StringUtils.isEmpty(taskId)){
				//taskId为空，说明是流程尚未启动，查询PV主管
				result = userEOService.getUsersByRoleAndOrg("S8K2K68R6C", orgId);
			}else{
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				String taskName = task.getName();//当前节点的名称
				if("PV主管".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("7REP5V9SB5", orgId);//查询PV科长
				}else if("PV科长".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("SUG6VQZ3R3", orgId);//查询PV部长
				}else if("发起人重新提交".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("S8K2K68R6C", orgId);//查询PV主管
				}
			}
		}else if(cvReportDic.equals(prodefkey)){
			// CV-试验报告审批流程
			if(StringUtils.isEmpty(taskId)){
				//taskId为空，说明是流程尚未启动，查询试验中心主管
				result = userEOService.getUsersByRoleAndOrg("6GMXW2BEXZ", orgId);
			}else{
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				String taskName = task.getName();//当前节点的名称
				if("试验中心主管".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("ZAXHNDTMCR", orgId);//查询试验中心科长
				}else if("试验中心科长".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("V5ENRMSD5R", orgId);//查询试验中心部长
				}else if("发起人重新提交".equals(taskName)){
					result = userEOService.getUsersByRoleAndOrg("6GMXW2BEXZ", orgId);//查询试验中心主管
				}
			}
		}else if(trialTaskChangeDic.equals(prodefkey)) {
			//PV/CV试验变更流程
			if(StringUtils.isEmpty(taskId)){
				//taskId为空，说明是流程尚未启动，查询试验中心主管或PV主管
				//根据当前登录人判断是试验中心还是PV
				UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
	            OrgEO curOrg = curUser.getOrgEOList().get(0);
	            String flag = orgEOService.getByOrgId(curOrg.getId());
	            if("1".equals(flag)){
	            	result = userEOService.getUsersByRoleAndOrg("S8K2K68R6C", orgId);//PV主管
	            }else {
	            	result = userEOService.getUsersByRoleAndOrg("6GMXW2BEXZ", orgId);//试验中心主管
	            }
			}else{
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				//根据发起人判断是试验中心还是PV
				HistoricProcessInstance procInst = 
						historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
				String startUserId = procInst.getStartUserId();
				UserEO curUser = userEOService.getUserWithRoles(startUserId);
	            OrgEO curOrg = curUser.getOrgEOList().get(0);
	            String flag = orgEOService.getByOrgId(curOrg.getId());
				String taskName = task.getName();//当前节点的名称
				if("主管".equals(taskName)){
					if("1".equals(flag)){
						result = userEOService.getUsersByRoleAndOrg("7REP5V9SB5", orgId);//查询PV科长						
					}else {
						result = userEOService.getUsersByRoleAndOrg("ZAXHNDTMCR", orgId);//查询试验中心科长												
					}
				}else if("发起人".equals(taskName)){
					if("1".equals(flag)){
						result = userEOService.getUsersByRoleAndOrg("S8K2K68R6C", orgId);//查询PV主管
					}else {
						result = userEOService.getUsersByRoleAndOrg("6GMXW2BEXZ", orgId);//查询试验中心主管
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据roleId获取用户列表
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getUsersByRoleId(String roleId) throws Exception{
		List<Map<String, Object>> result = new ArrayList<>();
		List<UserRoleEO> userRoleList = roleEOService.getUserRoleListByRoleId(roleId);
		List<UserVO> userList = roleEOService.findUserList(userRoleList);
		if(userList != null && !userList.isEmpty()){
			result = userList.stream().map(userVO -> {
				JSONObject json = new JSONObject();
				json.put("usid", userVO.getUsid());
				json.put("username", userVO.getUsname());
				return json;
			}).collect(Collectors.toList());
		}
		return result;
	}

	public void updateTrialAssignee(String taskId) throws Exception {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String proInsId = task.getProcessInstanceId();//流程实例id
		ProcessInstance proIns = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
		String businessKey = proIns.getBusinessKey();
		BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(businessKey);
		String trialTaskId = baseBusEO.getBusinessId();//试验id
		TrialTaskEO trialTaskEO = this.selectByPrimaryKey(trialTaskId);
		if(trialTaskEO != null) {			
			String ids = baseBusEODao.fingNextUserId(proInsId);
			String[] splits = ids.split(ConstantUtils.COMMA);
			List<String> userArray = Arrays.asList(splits);
			List<String> userNames = new ArrayList<>();
			for (int i = 0; i < userArray.size(); i++) {
				UserEO user = userEODao.selectByPrimaryKey(userArray.get(i));
				if(StringUtils.isNotEmpty(user) &&
						StringUtils.isNotEmpty(user.getUsname())) {
					userNames.add(user.getUsname());
				}
			}
			trialTaskEO.setCurrentWaitUserid(ids);
			trialTaskEO.setCurrentWaitUsername(StringUtils.join(userNames, ConstantUtils.COMMA));
			trialTaskEODao.updateTrialTask(trialTaskEO);
		}
	}
	
}
