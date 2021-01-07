package com.adc.da.payment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.payment.vo.CostPaymentVO;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.NextGroupUserVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.payment.dao.CostPaymentEODao;
import com.adc.da.payment.entity.CostPaymentEO;
import com.adc.da.payment.page.CostPaymentEOPage;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;



@Service("CostPaymentEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CostPaymentEOService extends BaseService<CostPaymentEO, String> {

	@Autowired
	private CostPaymentEODao costPaymentEODao;
	
	public CostPaymentEODao getDao() {
		return costPaymentEODao;
	}
	
	@Autowired
	private CostSummaryEODao costSummaryEODao;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private LimsFileService limsFileService;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private RuntimeService runtimeService;
    
    
    /**
     * 结算单-分页查询
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<CostSummaryVO>>
     * @author: ljy
     * @date: 2020年8月14日
     */
	public List<CostPaymentEO> page(CostPaymentEOPage page, String searchPhrase){
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
		return costPaymentEODao.queryByPage(page);
	}
	
	
	/**
	 * 结算单-保存
	 * @Title: save
	 * @param costPaymentEO
	 * @return
	 * @return CostPaymentEO
	 * @author: ljy
	 * @throws Exception 
	 * @date: 2020年8月14日
	 */
	public CostPaymentEO save(CostPaymentEO costPaymentEO) throws Exception {
		//设置UUID
		costPaymentEO.setId(UUID.randomUUID());
		//删除标记 0 未删除;  1 删除
		costPaymentEO.setDelFlag("0");
		//结算人
		costPaymentEO.setPaymentPersonId(UserUtils.getUser().getUsid());
		//结算人
		costPaymentEO.setPaymentPerson(UserUtils.getUser().getUsname());
		//0:未结算
		costPaymentEO.setPaymentStatus("0");
		//保存
		costPaymentEODao.insert(costPaymentEO);
		
		//反向更新费用预算
		for (CostSummaryEO summary : costPaymentEO.getSummaryList()) {
			summary.setPaymentId(costPaymentEO.getId());
			summary.setPaymentNo(costPaymentEO.getPaymentNo());
			summary.setPaymentPerson(costPaymentEO.getPaymentPerson());
			summary.setPaymentPersonId(costPaymentEO.getPaymentPersonId());
			//1结算中
			summary.setPaymentStatus("1");
			costSummaryEODao.updateByPrimaryKeySelective(summary);
		}
		
		return costPaymentEO;
	}
	
	
	/**
	 * 结算单-编辑
	 * @Title: update
	 * @param costPaymentEO
	 * @return
	 * @return CostPaymentEO
	 * @author: ljy
	 * @date: 2020年8月21日
	 */
	public CostPaymentEO update(CostPaymentEO costPaymentEO) {
		//更新结算单
		costPaymentEODao.updateByPrimaryKeySelective(costPaymentEO);
		//反向更新费用预算
		for (CostSummaryEO summary : costPaymentEO.getSummaryList()) {
			summary.setPaymentId(costPaymentEO.getId());
			summary.setPaymentNo(costPaymentEO.getPaymentNo());
			summary.setPaymentPerson(costPaymentEO.getPaymentPerson());
			summary.setPaymentPersonId(costPaymentEO.getPaymentPersonId());
			//1结算中
			summary.setPaymentStatus("1");
			costSummaryEODao.updateByPrimaryKeySelective(summary);
		}
		return costPaymentEO;
	}

    /**
     * 启动流程时, 判断下一个节点是否有人
     * @Title: getStartNextNodeInfo
     * @param pv
     * @param cv
     * @return
     * @throws Exception
     * @return JSONObject
     * @date: 2020年4月1日
     */
    public JSONObject getStartNextNodeInfo(String pv, String cv) throws Exception {
        JSONObject jsonObj = new JSONObject();
        //判断下一节点是否有审批人
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = null;

        String pvorcv = "";
        if ("1".equals(flag)) {
            pvorcv = pv;
        } else {
            pvorcv = cv;
        }
        procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvorcv).getDicTypeName();
        //设置返回默认值
        jsonObj.put("flag", flag);
        NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
            List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
            if(usersByRoleAndOrg.isEmpty()) {
                jsonObj.put("isSuccess", "-1");
                return jsonObj;
            }
        }
        jsonObj.put("isSuccess", "0");
        return jsonObj;
    }

    
    
	/**
	 * 流程过程中 判断下个节点是否有人
	 * @Title: getNextNodeInfo
	 * @param requestEO
	 * @return
	 * @return String
	 * @date: 2020年4月1日
	 */
	public String getNextNodeInfo(RequestEO requestEO) {
		//判断下一节点是否有审批人
		String condition = (String)requestEO.getVariables().get("approve");
	    NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(null, requestEO.getTaskId(), condition);
	    String roleId = nextNodeInfo.getRoleId();
	    String departId = nextNodeInfo.getDepartId();
	    if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
	    	String specialOrgId = (String)runtimeService.getVariable(requestEO.getProcId(), "specialOrgId");
	    	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, specialOrgId);
	    	if(usersByRoleAndOrg.isEmpty()) {
	    		return "-1";
	    	}
	    }
	    return "0";
	}
	
    
    public void submitActivity(CostPaymentEO costPaymentVO, String flag) throws Exception {
        String id = costPaymentVO.getId();
        if (StringUtils.isNotEmpty(id)){
        	update(costPaymentVO);
        }else {
            save(costPaymentVO);
        }
        //启动流程
        //保存业务BASEBUS
        String title = "费用结算单【"+ costPaymentVO.getPaymentNo() +"】审批流程";
        String pvorcv = "";
        if("1".equals(flag)) {
            pvorcv = ConstantUtils.Pc_Cost_Payment_TYPE;
        }else {
            pvorcv = ConstantUtils.Cv_Cost_Payment_TYPE;
        }
        String baseBusId = limsFileService.saveBaseBus(
                costPaymentVO.getId(), pvorcv, title);
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
        //修改费用结算单状态为结算中  1-结算中
        costPaymentVO.setPaymentStatus("1");
        costPaymentEODao.updateByPrimaryKeySelective(costPaymentVO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            String link = ConstantUtils.Cost_Payment_TYPE;
            limsFileService.processSendMessages(splits, title, link, costPaymentVO.getId());
        }
    }
    
    
    
    /**
     * 费用结算单流程审批
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
    	//获取业务与流程关联表信息
    	BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取费用结算业务信息
    	CostPaymentEO costPaymentEO = costPaymentEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
    	Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //退回
        if("rollback".equals(approveCode)){
        	//3-退回
        	costPaymentEO.setPaymentStatus("3");
        	costPaymentEODao.updateByPrimaryKeySelective(costPaymentEO);
        }
        //撤回
        else if("reback".equals(approveCode)){
        	//4-撤回
        	costPaymentEO.setPaymentStatus("4");
        	costPaymentEODao.updateByPrimaryKeySelective(costPaymentEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve","rollback");
        }else {
        	//修改费用结算单状态为结算中  1-结算中
        	costPaymentEO.setPaymentStatus("1");
        	costPaymentEODao.updateByPrimaryKeySelective(costPaymentEO);
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
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        }else if("1".equals(requestEO.getType())){
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(costPaymentEO)) {
            	//已审批  2-已结算
            	costPaymentEO.setPaymentStatus("2");
            	//设置时间
        		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        		//结算日期
        		costPaymentEO.setPaymentDate(date);
            	costPaymentEODao.updateByPrimaryKeySelective(costPaymentEO);
            	//反向更新费用状态
            	for (CostSummaryEO summary : costPaymentEO.getSummaryList()) {
        			summary.setPaymentId(costPaymentEO.getId());
        			summary.setPaymentNo(costPaymentEO.getPaymentNo());
        			summary.setPaymentPerson(costPaymentEO.getPaymentPerson());
        			summary.setPaymentPersonId(costPaymentEO.getPaymentPersonId());
        			//2已结算
        			summary.setPaymentStatus("2");
        			//结算日期
        			summary.setPaymentDate(date);
        			costSummaryEODao.updateByPrimaryKeySelective(summary);
        		}
            	
            	//完成给相关人员发送消息
            	String title = "费用结算单【"+ costPaymentEO.getPaymentNo() +"】审批流程完成";
            	limsFileService.processSendMessages(limsFileService.
                		getProcessUsersByTaskId(requestEO.getTaskId()), 
                		title, link, costPaymentEO.getId());
            }
        }else{
        	//获取试验任务业务信息
        	String title = "费用结算单【"+ costPaymentEO.getPaymentNo() +"】审批流程";
        	//获取下一节点人
        	if(StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
        		String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
            	limsFileService.processSendMessages(ids, title, link, costPaymentEO.getId());
        	}
        }
    }
	
    
    
    
    
    
    
    
    
    
    
}
