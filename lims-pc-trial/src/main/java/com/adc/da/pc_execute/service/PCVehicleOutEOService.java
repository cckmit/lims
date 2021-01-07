package com.adc.da.pc_execute.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.dao.PCVehicleOutEODao;
import com.adc.da.pc_execute.dao.PCVehicleOutInfoEODao;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.entity.PCVehicleOutEO;
import com.adc.da.pc_execute.entity.PCVehicleOutInfoEO;
import com.adc.da.pc_execute.page.PCVehicleOutEOPage;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.SearchFieldUtil;
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


/**
 * PC整车出门申请单
 * @author Administrator
 * @date 2019年11月26日
 */
@Service("PCVehicleOutEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCVehicleOutEOService extends BaseService<PCVehicleOutEO, String> {
	
	@Autowired
	private PCVehicleOutEODao pcVehicleOutEODao;
	
	public PCVehicleOutEODao getDao() {
		return pcVehicleOutEODao;
	}
	
	@Autowired
	private PipelineNumberEODao pipelineNumberEODao;
	
	@Autowired
	private PCVehicleOutInfoEODao pcVehicleOutInfoEODao;
	
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private BaseBusEODao baseBusEODao;
    
    @Autowired
    private DocUtil docUtil;
	
	@Autowired
	private UserEODao userEODao;
    
	@Autowired
    private DicTypeEODao dicTypeEODao;
    
    @Autowired
    private LimsFileService limsFileService;
    
    @Autowired
    private ActivitiProcessService activitiProcessService;
    
    @Autowired
    private OrgEODao orgEODao;
    
	/**
	 * 整车出门单-分页查询
	 * @Title: page
	 * @param page
	 * @param searchPhrase
	 * @return
	 * @throws Exception
	 * @return List<PCVehicleOutEO>
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
	public List<PCVehicleOutEO> page(PCVehicleOutEOPage page, String searchPhrase) throws Exception{
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
			page = (PCVehicleOutEOPage) SearchFieldUtil
					.pageSet(page.getClass().getName(), searchPhrase);
        }
		//查询
		return pcVehicleOutEODao.queryByPage(page);
	}
	
	
	/**
	 *  整车出门单-保存
	 * @Title: save
	 * @param pcVehicleOutEO
	 * @return
	 * @return PCVehicleOutEO
	 * @author: ljy
	 * @throws Exception 
	 * @date: 2019年11月26日
	 */
	public PCVehicleOutEO save(PCVehicleOutEO pcVehicleOutEO) throws Exception {
		//设置UUID
		pcVehicleOutEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4);
		pcVehicleOutEO.setCreateTime(date);
		pcVehicleOutEO.setCreateBy(UserUtils.getUserId());
		pcVehicleOutEO.setUpdateBy(UserUtils.getUserId());
		pcVehicleOutEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		pcVehicleOutEO.setDelFlag("0");
		//状态 0-草稿
		pcVehicleOutEO.setVehicleStatus("0");
		//编号
		pcVehicleOutEO.setVehicleNo(getVehicleNo());
		//保存
		pcVehicleOutEODao.insert(pcVehicleOutEO);
		//保存详情
		if(CollectionUtils.isNotEmpty(pcVehicleOutEO.getInfoList())) {
			for (int i = 0; i < pcVehicleOutEO.getInfoList().size(); i++) {
				savePCVehicleOutInfo(pcVehicleOutEO.getInfoList().get(i), pcVehicleOutEO.getId());
			}
		}
		return pcVehicleOutEO;
	}
	
	
	/**
	 * 保存整车出门单详情
	 * @Title: savePCVehicleOutInfo
	 * @param pcVehicleOutInfoEO
	 * @param vehicleOutId
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public void savePCVehicleOutInfo(PCVehicleOutInfoEO pcVehicleOutInfoEO, String vehicleOutId) {
		PCVehicleOutInfoEO info = new PCVehicleOutInfoEO();
		//设置UUID
		info.setId(UUID.randomUUID());
		//删除标记 0 未删除;  1 删除
		info.setDelFlag("0");
		info.setVehicleOutId(vehicleOutId);
		//底盘号
		info.setChassisNumber(pcVehicleOutInfoEO.getChassisNumber());
		//司机id
		info.setDriverId(pcVehicleOutInfoEO.getDriverId());
		//司机名称
		info.setDriverName(pcVehicleOutInfoEO.getDriverName());
		//运输终点
		info.setEndPoint(pcVehicleOutInfoEO.getEndPoint());
		//借车单号
		info.setLoanNumber(pcVehicleOutInfoEO.getLoanNumber());
		//数量
		info.setNumbers(pcVehicleOutInfoEO.getNumbers());
		//备注
		info.setRemark(pcVehicleOutInfoEO.getRemark());
		//样品id
		info.setSampleId(pcVehicleOutInfoEO.getSampleId());
		//运输起点
		info.setStartPoint(pcVehicleOutInfoEO.getStartPoint());
		//单位
		info.setUnit(pcVehicleOutInfoEO.getUnit());
		//车型
		info.setVehicleType(pcVehicleOutInfoEO.getVehicleType());
		pcVehicleOutInfoEODao.insert(info);
	}
	
	
	/**
	 *  整车出门单-编辑
	 * @Title: edit
	 * @param pcVehicleOutEO
	 * @return
	 * @return PCVehicleOutEO
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
	public PCVehicleOutEO edit(PCVehicleOutEO pcVehicleOutEO) {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		pcVehicleOutEO.setUpdateBy(UserUtils.getUserId());
		pcVehicleOutEO.setUpdateTime(date);
		//保存
		pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
		//先删除  后保存
		pcVehicleOutInfoEODao.deleteByVehicleOutId(pcVehicleOutEO.getId());
		//保存详情
		if(CollectionUtils.isNotEmpty(pcVehicleOutEO.getInfoList())) {
			for (int i = 0; i < pcVehicleOutEO.getInfoList().size(); i++) {
				savePCVehicleOutInfo(pcVehicleOutEO.getInfoList().get(i), pcVehicleOutEO.getId());
			}
		}
		return pcVehicleOutEO;
	}
	
	
    /**
     * 整车出门单-删除
     * @Title: deleteById
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年11月26日
     */
	public void deleteById(String id) {
		//先删除详情
		pcVehicleOutInfoEODao.deleteByVehicleOutId(id);
		//后删除主信息
		pcVehicleOutEODao.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 整车出门单-导出
	 * @Title: exportPCVehicleOut
	 * @param response
	 * @param request
	 * @param id
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public void exportPCVehicleOut(HttpServletResponse response,
			HttpServletRequest request, String id) {
		//获取整车出门信息
		PCVehicleOutEO eo = pcVehicleOutEODao.selectByPrimaryKey(id);
		//封装数据
    	Map<String, Object> stringObjectMap = new HashMap<>();
    	//编号
    	stringObjectMap.put("VEHICLE_NO", eo.getVehicleNo());
    	//提货单位
    	String DELIVERY_UNIT = "";
    	switch (eo.getDeliveryUnit()) {
		case "0":
			DELIVERY_UNIT = "商用车品质保障部";
			break;
		case "1":
			DELIVERY_UNIT = "乘用车品质保证部";
			break;
		default:
			break;
		}
    	stringObjectMap.put("DELIVERY_UNIT", DELIVERY_UNIT);
    	//日期
    	stringObjectMap.put("CREATE_TIME", eo.getCreateTime());
    	//物件信息
    	stringObjectMap.put("infoList", eo.getInfoList());
    	//经办人
    	stringObjectMap.put("CREATE_BY_NAME", eo.getCreateByName());
    	//------------流程信息-------------------//
        String actProcId = pcVehicleOutEODao.selectActProcIdById(id);
    	String APPROVER_NAME = "";
    	String ORG_NAME = "";
    	if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
   		 	List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
   		 	APPROVER_NAME = list.get(0).getAssigneeName();
   		 	List<String> orgIds  = userEODao.getOrgIdListByUserId(list.get(0).getAssignee());
   		 	if(CollectionUtils.isNotEmpty(orgIds)) {
   		 		OrgEO org = orgEODao.getOrgEOById(orgIds.get(0));
   		 		ORG_NAME = org.getName();
   		 	} 
        }
    	//签发人
    	stringObjectMap.put("APPROVER_NAME", APPROVER_NAME);
    	//签发部门
    	stringObjectMap.put("ORG_NAME", ORG_NAME);
    	String fileName = eo.getCreateByName() + "【" + eo.getVehicleNo() + "】";
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(stringObjectMap, "PCVehicleOutWord", response, request, 
        		fileName, fileExtend);
	}
	
	
	/**
	 * 整车出门单-流程启动
	 * @Title: submitVehicleOut
	 * @param pcVehicleOutEO
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public void submitVehicleOut(PCVehicleOutEO pcVehicleOutEO, String flag) throws Exception {
		if(StringUtils.isNotEmpty(flag)) {
			pcVehicleOutEO.setDeliveryUnit(flag);
        }
		//先保存信息,后提交流程
		if(StringUtils.isNotEmpty(pcVehicleOutEO.getId())) {
			edit(pcVehicleOutEO);
		}else {
			save(pcVehicleOutEO);
		}
		//启动流程
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcVehicleOutEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
		//保存业务BASEBUS
    	String title = taskOrPlan + "整车出门单【"+ pcVehicleOutEO.getVehicleNo() +"】审批流程";
    	String pvorcv = "";
    	if("1".equals(pcVehicleOutEO.getDeliveryUnit())) {
    		pvorcv = ConstantUtils.PV_VEHICLE_OUT_TYPE;
    	}else {
    		pvorcv = ConstantUtils.CV_VEHICLE_OUT_TYPE;
    	}
    	String baseBusId = limsFileService.saveBaseBus(
    			pcVehicleOutEO.getId(), pvorcv, title);
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
        //修改整车出门单状态为审批中  1-审批中
        pcVehicleOutEO.setVehicleStatus("1");
        pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            String link = ConstantUtils.PC_VEHICLE_OUT_BUSINESS_TYPE;
            limsFileService.processSendMessages(splits, title, link, pcVehicleOutEO.getId());
        }
	}
	
	
	
    /**
     * 整车出门单-流程审批
     * @Title: approvalProcess
     * @param request
     * @param requestEO
     * @return
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @author: ljy
     * @date: 2019年11月27日
     */
	public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
    	//获取业务与流程关联表信息
    	BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取整车出门单业务信息
        PCVehicleOutEO pcVehicleOutEO = pcVehicleOutEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
    	Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //退回
        if("rollback".equals(approveCode)){
        	//3-退回
        	pcVehicleOutEO.setVehicleStatus("3");
        	pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
        }
        //撤回
        else if("reback".equals(approveCode)){
        	//4-撤回
        	pcVehicleOutEO.setVehicleStatus("4");
        	pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve","rollback");
        }else {
        	//修改可靠性立项单状态为审批中  1-审批中
        	pcVehicleOutEO.setVehicleStatus("1");
        	pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
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
        
        //0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcVehicleOutEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
        
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(pcVehicleOutEO)) {
            	//已审批  2-已审批
            	pcVehicleOutEO.setVehicleStatus("2");
            	pcVehicleOutEODao.updateByPrimaryKeySelective(pcVehicleOutEO);
            	//完成给相关人员发送消息
            	String title = taskOrPlan + "整车出门单【"+ pcVehicleOutEO.getVehicleNo() +"】审批流程完成";
            	limsFileService.processSendMessages(limsFileService.
                		getProcessUsersByTaskId(requestEO.getTaskId()), 
                		title, link, pcVehicleOutEO.getId());
            }
        }else{
        	//获取试验任务业务信息
        	String title = taskOrPlan + "整车出门单【"+ pcVehicleOutEO.getVehicleNo() +"】审批流程";
        	//获取下一节点人
        	if(StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
        		String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
            	limsFileService.processSendMessages(ids, title, link, pcVehicleOutEO.getId());
        	}
        }
    }
	
	
	/**
	 * 获取整车出门单编号
	 * @Title: getVehicleNo
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
	public String getVehicleNo() {
		String vehicleNo = "";
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //获取流水号相关信息
        PipelineNumberEO pNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_VEHICLE_NO);
        //如果数据库没有改类型code,则新增一个
        if(StringUtils.isEmpty(pNumberEO)) {
        	pNumberEO = new PipelineNumberEO();
        	pNumberEO.setType(ConstantUtils.PC_VEHICLE_NO);
        	pNumberEO.setTally(0);
        	pNumberEO.setParticularYear(dateYear);
        	pipelineNumberEODao.insert(pNumberEO);
        }
        //判断数据取出来的最后一个业务单号是不是当年的,如果是当年的 直接+1
        if (dateYear.equals(pNumberEO.getParticularYear())) {
            //获取流水号
        	vehicleNo = PrimaryGenerater.getPCVehicleNoNumber(
            		pNumberEO.getTally(), pNumberEO.getParticularYear());
            //更新计数
            pNumberEO.setTally(pNumberEO.getTally() + 1);
        } else { //不是当年的,从0开始
            //获取流水号
        	vehicleNo = PrimaryGenerater.getPCVehicleNoNumber(0, dateYear);
            //更新计数及新的年份
            pNumberEO.setTally(1);
            pNumberEO.setParticularYear(dateYear);
        }
        //更新数据库
        pipelineNumberEODao.updateByPrimaryKeySelective(pNumberEO);
        return vehicleNo;
	}
	
}
