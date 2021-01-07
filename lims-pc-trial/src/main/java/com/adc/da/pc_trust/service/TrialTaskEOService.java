package com.adc.da.pc_trust.service;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_items.service.TrialItemsEOService;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_person.service.TrialPersonEOService;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.page.TrialTaskChangeEOPage;
import com.adc.da.pc_trust.page.TrialTaskEOPage;
import com.adc.da.pc_trust.vo.TrialTaskChangeVO;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.SearchFieldUtil;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;

import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;

import cn.hutool.core.date.DateUtil;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_TASK TrialTaskEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcTrialTaskEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialTaskEOService extends BaseService<TrialTaskEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialTaskEOService.class);

    @Autowired
    private TrialTaskEODao dao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private TrialItemsEOService trialItemsEOService;

    @Autowired
    private TrialPersonEOService trialPersonEOService;

    public TrialTaskEODao getDao() {
        return dao;
    }

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private LimsFileService limsFileService;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private OrgEOService orgEOService;

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<TrialTaskEO> queryByPage(TrialTaskEOPage page) throws Exception {
        TrialTaskEOPage search = (TrialTaskEOPage) SearchFieldUtil.pageSet(TrialTaskEOPage.class.getName(), page.getSearchField());
        page.setSearchPhrase(search.getSearchPhrase());
        //获取当前登录用户角色
        String roleIds = UserUtils.getRoleIds();
        //如果当前登录用户为部长/科长/主管/试验工程师管理员, 则需过滤数据
        if(roleIds.contains(ConstantUtils.PV_MINISTER_ROLEID) ||
                roleIds.contains(ConstantUtils.PV_SECTIONCHIEF_ROLEID) ||
                roleIds.contains(ConstantUtils.PV_CHARGE_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_MINISTER_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_SECTIONCHIEF_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_CHARGE_ROLEID)) {
            List<String> orgIds = userEODao.getOrgIdListByUserId(UserUtils.getUserId());
            if(CollectionUtils.isNotEmpty(orgIds)) {
                List<String> userIds = limsFileService.getUserIdsByOrgId(orgIds.get(0));
                page.setCreateByIds(userIds);
            }
        }else if(roleIds.contains(ConstantUtils.SUP_APPOINT_STAFF) ||
                roleIds.contains(ConstantUtils.PV_ROADRESTER_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_RESTER_ROLEID)) {
        		page.setSupPersonIds(UserUtils.getUserId());
        }else{
            //如果当前登录用户为试验工程师, 则需过滤数据, 只显示该用户的数据
            page.setCreateBy(UserUtils.getUserId());
            page.setPersonIds(UserUtils.getUserId());
        }
        List<TrialTaskEO> list = dao.queryByPage(page);
        //设置businessKey
        for(TrialTaskEO trial : list) {
        	List<BaseBusEO> baseBusList = baseBusEODao.selectByBusinessId(trial.getId());
        	if(!baseBusList.isEmpty()) {
        		trial.setBusinessKey(baseBusList.get(0).getId());
        	}
        }
        return list;
    }

    
    /**
     * 新建委托
     * @param trialTaskEO
     * @return
     */
    public int insertSelective(TrialTaskEO trialTaskEO) throws Exception {
        UserEO user = UserUtils.getUser();
        //委托状态：草稿
        trialTaskEO.setTaskStatus("0");
        //当前登录人
        trialTaskEO.setCreateBy(user.getUsid());
        trialTaskEO.setCreateByName(user.getUsname());
        //是否删除
        trialTaskEO.setDelFlag("0");
        //延期天数默认为0
        trialTaskEO.setDelyDays(0);
        //设置版本号为1
        trialTaskEO.setTaskVersion(1);
        //设置编号(区分试验任务)
        trialTaskEO.setTaskNumber(UUID.randomUUID());
        dao.insertSelective(trialTaskEO);
        //插入子表
        personAndItems(trialTaskEO);
        //反向更新主表
        updatePersons(trialTaskEO.getId());
        return 1;
    }

    /**
     * 试验任务申请变更
     * @param taskChangeVO
     * @return
     */
    public int changeTask(TrialTaskChangeVO taskChangeVO) throws Exception {
        UserEO user = UserUtils.getUser();
        //委托状态：提交
        taskChangeVO.setTaskStatus("1");
        //当前登录人
        taskChangeVO.setCreateBy(user.getUsid());
        taskChangeVO.setCreateByName(user.getUsname());
        //是否删除
        taskChangeVO.setDelFlag("0");
        //延期天数默认为0
        taskChangeVO.setDelyDays(0);
        //设置版本号为当前版本加一
        taskChangeVO.setTaskVersion(taskChangeVO.getTaskVersion() + 1);
        //设置流程状态为审批中
        taskChangeVO.setInitStatus("1");
        TrialTaskEO trialTaskEO = new TrialTaskEO();
        BeanUtils.copyProperties(taskChangeVO,trialTaskEO);
        dao.insertSelective(trialTaskEO);
        //插入子表
        personAndItems(trialTaskEO);
        //反向更新主表
        updatePersons(trialTaskEO.getId());
        //启动流程
        submitReliableInitTask(taskChangeVO,trialTaskEO);
        return 1;
    }

    /**
     * 插入人员和检验项目表
     * @param trialTaskEO
     */
    public void personAndItems(TrialTaskEO trialTaskEO) throws Exception {
        //检验项目子表
        if(CollectionUtils.isNotEmpty(trialTaskEO.getTrialItemsEOList())){
            List<TrialItemsEO> trialItemsEOList = trialTaskEO.getTrialItemsEOList();
            trialItemsEOService.batchSave(trialItemsEOList, trialTaskEO);
        }
        //实施人员子表
        if(CollectionUtils.isNotEmpty(trialTaskEO.getTrialPersonEOList())){
            List<TrialPersonEO> trialPersonEOList = trialTaskEO.getTrialPersonEOList();
            trialPersonEOService.batchSave(trialPersonEOList, trialTaskEO.getId());
        }
    }

    
    /**
     * 反向更新试验任务表中的 试验人员ids
     * @Title: updatePerson
     * @param trialTaskId
     * @return void
     * @author: ljy
     * @date: 2019年12月27日
     */
    public void updatePersons(String trialTaskId) {
    	TrialTaskEO trialTaskEO = dao.selectByPrimaryKey(trialTaskId);
    	List<TrialPersonEO> trialPersonEOList = trialTaskEO.getTrialPersonEOList();
    	List<String> personIds = new ArrayList<>();
    	for(TrialPersonEO eo : trialPersonEOList) {
    		String personId = eo.getPersonId();
    		if(StringUtils.isNotEmpty(personId) 
    				&& "0".equals(eo.getPersonStatus())) {
    			personIds.add(personId);
    		}
    	}
    	trialTaskEO.setPersonIds(StringUtils.join(personIds.toArray(), ConstantUtils.COMMA));
    	dao.updateByPrimaryKeySelective(trialTaskEO); 
    }
    
    
    /**
     * 编辑申请
     * @param trialTaskEO
     * @return
     */
    public int updateByPrimaryKeySelective(TrialTaskEO trialTaskEO) throws Exception {
        trialTaskEO.setTaskStatus("0");
        trialTaskEO.setCreateBy(UserUtils.getUserId());
        dao.updateByPrimaryKeySelective(trialTaskEO);
        //删除人员子表，重新添加
        trialItemsEOService.batchDelete(trialTaskEO.getId());
        //删除检验项目子表，重新添加
        trialPersonEOService.batchDelete(trialTaskEO.getId());
        //批量添加人员、检验项目
        personAndItems(trialTaskEO);
        //反向更新主表
        updatePersons(trialTaskEO.getId());
        return 1;
    }

    /**
     * 提交委托申请
     * @param id
     */
    public void applyTask(String id, String status){
        dao.applyTask(id, status);
    }

    /**
     * deleteByPrimaryKey
     * @param id
     */
    public void logicDel(String id) {
        delPersonAndItems(id);
        dao.logicDel(id);
    }

    /**
     * 删除检验项目和人员
     * @param taskId
     */
    public void delPersonAndItems(String taskId){
        //批量删除检验项目
        trialItemsEOService.batchDelete(taskId);
        //批量删除人员
        trialPersonEOService.batchDelete(taskId);
    }


    
   /**
    * 费用请款申请费用自行支付查询
    * @Title: costForCashOut
    * @param page
    * @return
    * @return List<PcAutoPayForEO>
    * @author: ljy
    * @date: 2020年1月9日
    */
    public List<PcAutoPayForEO> costPSQCForCashOut(CostForCashOutPage page) {
        return dao.costPSQCForCashOut(page);
    }
    
    /**
     * 费用请款申请费用自行支付查询
     * @Title: costForCashOut
     * @param page
     * @return
     * @return List<PcAutoPayForEO>
     * @author: ljy
     * @date: 2020年1月9日
     */
    public List<PcAutoPayForEO> costPSQCForCashOutPerson(CostForCashOutPage page) {
        return dao.costPSQCForCashOutPerson(page);
    }

    /**
     * 分页查询变更记录
     * @param page
     * @return
     */
    public List<TrialTaskEO> changeTaskByPage(TrialTaskChangeEOPage page){
        List<TrialTaskEO> list = dao.changeTaskByPage(page);
        return list;
    }

    /**
     * 统计变更记录数量
     * @param page
     * @return
     */
    public Integer changeTaskByCount(TrialTaskChangeEOPage page){
        Integer count = dao.changeTaskByCount(page);
        return count;
    }

    /**
     * 流程启动
     * @param taskChangeVO
     */
    public void submitReliableInitTask(TrialTaskChangeVO taskChangeVO,TrialTaskEO trialTaskEO) throws Exception {
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String businessType = "";
        //启动流程
        String title = "";
        if ("1".equals(flag)){//pv流程
            //启动流程
            title = "PV试验任务申请变更【" + trialTaskEO.getTaskCode() + "】审批流程";
            businessType = ConstantUtils.PC_TRIAL_TASK_CHANGE;
        }else{//cv流程
            //启动流程
            title = "CV试验任务申请变更【" + trialTaskEO.getTaskCode() + "】审批流程";
            businessType = ConstantUtils.CV_TRIAL_TASK_CHANGE;
        }

        //流程实例保存
        String baseBusId = limsFileService.saveBaseBus(
                trialTaskEO.getId(), businessType, title);
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, businessType);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        
        List<RestVariable> variables = new ArrayList<>();
        //设置下一节点审批人
        if(StringUtils.isNotEmpty(taskChangeVO.getExecutive())){
            RestVariable variable = new RestVariable();
            variable.setName("executive");
            variable.setValue(taskChangeVO.getExecutive());
            variables.add(variable);
            processInstanceVO.setVariables(variables);
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
            //链接到试验申请单页
            String link = businessType;
            limsFileService.processSendMessages(splits, title, link, trialTaskEO.getId());
        }
    }

    /**
     * 流程审批
     * @param request
     * @param requestEO
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取试验申请业务信息
        TrialTaskEO trialTaskEO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
            //3-退回
            trialTaskEO.setInitStatus("3");
            dao.updateByPrimaryKeySelective(trialTaskEO);
        }
        //撤回
        else if ("reback".equals(approveCode)) {
            //4-撤回
            trialTaskEO.setInitStatus("4");
            dao.updateByPrimaryKeySelective(trialTaskEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        } else {
            //修改状态为审批中  1-审批中
            trialTaskEO.setInitStatus("1");
            dao.updateByPrimaryKeySelective(trialTaskEO);
        }

        //设置下一节点审批人
//        if(StringUtils.isNotEmpty(requestEO.getNextAssignee())){
//            String role = (String) variables.get("role");
//            if(!"rollback".equals(approveCode)){
//                //同意
//                if("charge".equals(role)){
//                    //主管
//                    //设置科长节点审批人
//                    requestEO.getVariables().put("section", requestEO.getNextAssignee());
//                }else{
//                    //科长  审批结束，没有审批人
//                }
//            }else{
//                //不同意  设置审批人为发起人
//                requestEO.getVariables().put("initiator", requestEO.getNextAssignee());
//            }
//        }

        //设置下一节点审批人
        if(StringUtils.isNotEmpty(requestEO.getNextAssignee())){
            Task task = taskService.createTaskQuery().taskId(requestEO.getTaskId()).singleResult();
            if("主管".equals(task.getName())){
                //设置科长节点审批人
                requestEO.getVariables().put("section", requestEO.getNextAssignee());
            }else if("科长".equals(task.getName())){
                if("rollback".equals(approveCode)){
                    //不同意  设置审批人为发起人
                    requestEO.getVariables().put("initiator", requestEO.getNextAssignee());
                }
            }else if("发起人".equals(task.getName())){
            	//设置主管节点审批人
            	requestEO.getVariables().put("executive", requestEO.getNextAssignee());
            }
        }

        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息.
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if ("0".equals(requestEO.getType())) {
        	//设置节点办理人，覆盖流程图中默认设置的办理人
        	taskService.setAssignee(requestEO.getTaskId(), UserUtils.getUserId());
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        } else if ("1".equals(requestEO.getType())) {
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
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(trialTaskEO)) {
                //已审批  2-已审批
                trialTaskEO.setInitStatus("2");
                dao.updateByPrimaryKeySelective(trialTaskEO);
                //完成给相关人员发送消息
                String title = "PV/CV试验任务申请变更【" + trialTaskEO.getTaskCode() + "】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                                getProcessUsersByTaskId(requestEO.getTaskId()),
                        title, link, trialTaskEO.getId());
            }
        } else {
            //获取试验任务业务信息
            String title = "PV/CV试验任务申请变更【" + trialTaskEO.getTaskCode() + "】审批流程";
            //获取下一节点人
            if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, link, trialTaskEO.getId());
            }
        }
    }


	public void reSubmitUpdate(TrialTaskEO trialTaskEO) throws Exception {
        trialTaskEO.setUpdateBy(UserUtils.getUserId());
        trialTaskEO.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        trialTaskEO.setInitStatus("1");//审批中
        dao.updateByPrimaryKeySelective(trialTaskEO);
        //删除人员子表，重新添加
        trialItemsEOService.batchDelete(trialTaskEO.getId());
        //删除检验项目子表，重新添加
        trialPersonEOService.batchDelete(trialTaskEO.getId());
        //批量添加人员、检验项目
        personAndItems(trialTaskEO);
        //反向更新主表
        updatePersons(trialTaskEO.getId());
	}

    
}
