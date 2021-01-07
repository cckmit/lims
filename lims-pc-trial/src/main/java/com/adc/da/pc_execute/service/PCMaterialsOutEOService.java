package com.adc.da.pc_execute.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PDFStampUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.dao.PCMaterialsOutEODao;
import com.adc.da.pc_execute.dao.PCMaterialsOutInfoEODao;
import com.adc.da.pc_execute.entity.PCMaterialsOutEO;
import com.adc.da.pc_execute.entity.PCMaterialsOutInfoEO;
import com.adc.da.pc_execute.page.PCMaterialsOutEOPage;
import com.adc.da.seal.dao.BmSealEODao;
import com.adc.da.seal.entity.BmSealEO;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
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
 * PC物资出门申请单
 * @author Administrator
 * @date 2019年11月27日
 */
@Service("PCMaterialsOutEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCMaterialsOutEOService extends BaseService<PCMaterialsOutEO, String> {
	
	@Autowired
	private PCMaterialsOutEODao pcMaterialsOutEODao;
	
	public PCMaterialsOutEODao getDao() {
		return pcMaterialsOutEODao;
	}
	
	@Autowired
	private PipelineNumberEODao pipelineNumberEODao;
	
	@Autowired
	private PCMaterialsOutInfoEODao pcMaterialsOutInfoEODao;
	
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private BaseBusEODao baseBusEODao;
    
    @Autowired
    private DocUtil docUtil;
	
    @Autowired
    private BmSealEODao bmSealEODao;
    
    @Value("${file.path}")
    private String filepath;
    
	@Autowired
    private DicTypeEODao dicTypeEODao;
    
    @Autowired
    private LimsFileService limsFileService;
    
    @Autowired
    private ActivitiProcessService activitiProcessService;
    

    @Autowired
    private UserEODao userEODao;
    
	/**
	 * 物资出门申请单-分页查询
	 * @Title: page
	 * @param page
	 * @param searchPhrase
	 * @return
	 * @throws Exception
	 * @return List<PCMaterialsOutEO>
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public List<PCMaterialsOutEO> page(PCMaterialsOutEOPage page, String searchPhrase) throws Exception{
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
			page = (PCMaterialsOutEOPage) SearchFieldUtil
					.pageSet(page.getClass().getName(), searchPhrase);
        }
		//查询
		return pcMaterialsOutEODao.queryByPage(page);
	}
	
	
	/**
	 * 保存物资出门单信息
	 * @Title: save
	 * @param pcMaterialsOutEO
	 * @return
	 * @return PCMaterialsOutEO
	 * @author: ljy
	 * @throws Exception 
	 * @date: 2019年11月27日
	 */
	public PCMaterialsOutEO save(PCMaterialsOutEO pcMaterialsOutEO) throws Exception {
		//设置UUID
		pcMaterialsOutEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4);
		pcMaterialsOutEO.setCreateTime(date);
		pcMaterialsOutEO.setCreateBy(UserUtils.getUserId());
		pcMaterialsOutEO.setCreateByName(UserUtils.getUser().getUsname());
		pcMaterialsOutEO.setUpdateBy(UserUtils.getUserId());
		pcMaterialsOutEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		pcMaterialsOutEO.setDelFlag("0");
		//状态 0-草稿
		pcMaterialsOutEO.setMaterialsStatus("0");
		//保存
		pcMaterialsOutEODao.insert(pcMaterialsOutEO);
		
		//保存详情
		if(CollectionUtils.isNotEmpty(pcMaterialsOutEO.getInfoList())) {
			for (int i = 0; i < pcMaterialsOutEO.getInfoList().size(); i++) {
				savePCMaterialsOutInfo(pcMaterialsOutEO.getInfoList().get(i),
						pcMaterialsOutEO.getId());
			}
		}
		
		return pcMaterialsOutEO;
	}
	
	
    /**
     * 物资出门申请单-编辑
     * @Title: edit
     * @param pcMaterialsOutVO
     * @return
     * @return ResponseMessage<PCMaterialsOutVO>
     * @author: ljy
     * @date: 2019年11月27日
     */
	public PCMaterialsOutEO edit(PCMaterialsOutEO pcMaterialsOutEO) {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4);
		pcMaterialsOutEO.setUpdateBy(UserUtils.getUserId());
		pcMaterialsOutEO.setUpdateTime(date);
		//保存
		pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
		//先删除
		pcMaterialsOutInfoEODao.deleteByMaterialsOutId(pcMaterialsOutEO.getId());
		//保存详情
		if(CollectionUtils.isNotEmpty(pcMaterialsOutEO.getInfoList())) {
			for (int i = 0; i < pcMaterialsOutEO.getInfoList().size(); i++) {
				savePCMaterialsOutInfo(pcMaterialsOutEO.getInfoList().get(i),
						pcMaterialsOutEO.getId());
			}
		}
		return pcMaterialsOutEO;
	}
	
	
	/**
	 * 保存物资出门单详细信息
	 * @Title: savePCMaterialsOutInfo
	 * @param pcMaterialsOutInfoEO
	 * @param pcMaterialsOutId
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public void savePCMaterialsOutInfo(PCMaterialsOutInfoEO pcMaterialsOutInfoEO,
			String pcMaterialsOutId) {
		PCMaterialsOutInfoEO info = new PCMaterialsOutInfoEO();
		//设置UUID
		info.setId(UUID.randomUUID());
		//删除标记 0 未删除;  1 删除
		info.setDelFlag("0");
		//物资名称
		info.setMaterialsName(pcMaterialsOutInfoEO.getMaterialsName());
		//资出门申请单id
		info.setMaterialsOutId(pcMaterialsOutId);
		//物资型号
		info.setMaterialsType(pcMaterialsOutInfoEO.getMaterialsType());
		//数量
		info.setNumbers(pcMaterialsOutInfoEO.getNumbers());
		//备注
		info.setRemark(pcMaterialsOutInfoEO.getRemark());
		//零部件样品id
		info.setSampleId(pcMaterialsOutInfoEO.getSampleId());
		pcMaterialsOutInfoEODao.insert(info);
	}
	
	
	/**
	 * 物资出门申请单-删除
	 * @Title: deleteById
	 * @param id
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月28日
	 */
	public void deleteById(String id) {
		//先删除详情
		pcMaterialsOutInfoEODao.deleteByMaterialsOutId(id);
		//后删除主信息
		pcMaterialsOutEODao.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 物资出门申请单-导出
	 * @Title: exportPCMaterialsOut
	 * @param response
	 * @param request
	 * @param id
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月28日
	 */
	public void exportPCMaterialsOut(HttpServletResponse response,
			HttpServletRequest request, String id) {
		//获取物资出门信息
		PCMaterialsOutEO eo = pcMaterialsOutEODao.selectByPrimaryKey(id);
		//封装数据
    	Map<String, Object> stringObjectMap = new HashMap<>();
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
    	//物资使用地
    	stringObjectMap.put("MATERIALS_USE_PLACE", eo.getMaterialsUsePlace());
    	//处理内容
    	stringObjectMap.put("DEAL_CONTENT", eo.getDealContent());
    	//日期
    	stringObjectMap.put("CREATE_TIME", eo.getCreateTime());
    	//物件信息
    	stringObjectMap.put("infoList", eo.getInfoList());
    	//申请人
    	stringObjectMap.put("CREATE_BY_NAME", eo.getCreateByName());
    	//------------流程信息-------------------//
        String actProcId = pcMaterialsOutEODao.selectActProcIdById(id);
    	String APPROVER_NAME = "";
    	if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
   		 	List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
   		 	APPROVER_NAME = list.get(0).getAssigneeName();
        }
    	//审批人
    	stringObjectMap.put("APPROVER_NAME", APPROVER_NAME);
    	//章
    	String STAMP_IMG = "";
    	//获取公章
        BmSealEO seal = bmSealEODao.findSealCode(ConstantUtils.STAMP);
        if(StringUtils.isNotEmpty(seal) &&
        		StringUtils.isNotEmpty(seal.getSealStyle())) {
        	String stampPath = filepath + seal.getSealStyle();
        	File stampFile = new File(stampPath);
        	//判断文件是否存在
        	if(stampFile.exists()) {
        		STAMP_IMG = PDFStampUtil.getImgStr(stampPath);
        	}
        }
    	stringObjectMap.put("STAMP_IMG", STAMP_IMG);
    	String fileName = eo.getCreateByName() + "【" + eo.getMaterialsOutNo() + "】";
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(stringObjectMap, "PCMaterialsOutWord", response, request, 
        		fileName, fileExtend);
	}
	
	
	
	/**
	 * 物资出门单-流程启动
	 * @Title: submitVehicleOut
	 * @param pcMaterialsOutEO
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月28日
	 */
	public void submitMaterialsOut(PCMaterialsOutEO pcMaterialsOutEO, String flag) throws Exception {
		if(StringUtils.isNotEmpty(flag)) {
			pcMaterialsOutEO.setDeliveryUnit(flag);
        }
		//先保存信息,后提交流程
		if(StringUtils.isNotEmpty(pcMaterialsOutEO.getId())) {
			edit(pcMaterialsOutEO);
		}else {
			save(pcMaterialsOutEO);
		}
		//启动流程
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcMaterialsOutEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
		//保存业务BASEBUS
    	String title = taskOrPlan + "物资出门单【"+ pcMaterialsOutEO.getMaterialsOutNo() +"】审批流程";
    	String pvorcv = "";
    	if("1".equals(pcMaterialsOutEO.getDeliveryUnit())) {
    		pvorcv = ConstantUtils.PV_MATERIALS_OUT_TYPE;
    	}else {
    		pvorcv = ConstantUtils.CV_MATERIALS_OUT_TYPE;
    	}
    	String baseBusId = limsFileService.saveBaseBus(
    			pcMaterialsOutEO.getId(), pvorcv, title);
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
        //修改物资出门单状态为审批中  1-审批中
        pcMaterialsOutEO.setMaterialsStatus("1");
        pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            String link = ConstantUtils.PC_VEHICLE_OUT_BUSINESS_TYPE;
            limsFileService.processSendMessages(splits, title, link, pcMaterialsOutEO.getId());
        }
	}
	
	
	
	/**
	 * 物资出门单---流程审批
	 * @Title: approvalProcess
	 * @param request
	 * @param requestEO
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月28日
	 */
	public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
    	//获取业务与流程关联表信息
    	BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取物资出门单业务信息
    	PCMaterialsOutEO pcMaterialsOutEO = pcMaterialsOutEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
    	Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //退回
        if("rollback".equals(approveCode)){
        	//3-退回
        	pcMaterialsOutEO.setMaterialsStatus("3");
        	pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
        }
        //撤回
        else if("reback".equals(approveCode)){
        	//4-撤回
        	pcMaterialsOutEO.setMaterialsStatus("4");
        	pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve","rollback");
        }else {
        	//修改可靠性立项单状态为审批中  1-审批中
        	pcMaterialsOutEO.setMaterialsStatus("1");
        	pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
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
  		if("1".equals(pcMaterialsOutEO.getTaskOrPlan())) {
  			taskOrPlan = "执行计划-";
  		}else {
  			taskOrPlan = "试验任务-";
  		}
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(pcMaterialsOutEO)) {
            	//已审批  2-已审批
            	pcMaterialsOutEO.setMaterialsStatus("2");
            	pcMaterialsOutEODao.updateByPrimaryKeySelective(pcMaterialsOutEO);
            	//完成给相关人员发送消息
            	String title = taskOrPlan + "物资出门单【"+ pcMaterialsOutEO.getMaterialsOutNo() +"】审批流程完成";
            	limsFileService.processSendMessages(limsFileService.
                		getProcessUsersByTaskId(requestEO.getTaskId()), 
                		title, link, pcMaterialsOutEO.getId());
            }
        }else{
        	//获取试验任务业务信息
        	String title = taskOrPlan + "物资出门单【"+ pcMaterialsOutEO.getMaterialsOutNo() +"】审批流程";
        	//获取下一节点人
        	if(StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
        		String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
            	limsFileService.processSendMessages(ids, title, link, pcMaterialsOutEO.getId());
        	}
        }
    }
	
	
	
	
	
	/**
	 * 获取物资出门'编号
	 * @Title: getMaterialsOutNo
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public String getMaterialsOutNo() {
		String materialsOutNo = "";
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //获取流水号相关信息
        PipelineNumberEO pNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_MATERIALS_NO);
        //如果数据库没有改类型code,则新增一个
        if(StringUtils.isEmpty(pNumberEO)) {
        	pNumberEO = new PipelineNumberEO();
        	pNumberEO.setType(ConstantUtils.PC_MATERIALS_NO);
        	pNumberEO.setTally(0);
        	pNumberEO.setParticularYear(dateYear);
        	pipelineNumberEODao.insert(pNumberEO);
        }
        //判断数据取出来的最后一个业务单号是不是当年的,如果是当年的 直接+1
        if (dateYear.equals(pNumberEO.getParticularYear())) {
            //获取流水号
        	materialsOutNo = PrimaryGenerater.getPCMaterialsNoNumber(
            		pNumberEO.getTally(), pNumberEO.getParticularYear());
            //更新计数
            pNumberEO.setTally(pNumberEO.getTally() + 1);
        } else { //不是当年的,从0开始
            //获取流水号
        	materialsOutNo = PrimaryGenerater.getPCMaterialsNoNumber(0, dateYear);
            //更新计数及新的年份
            pNumberEO.setTally(1);
            pNumberEO.setParticularYear(dateYear);
        }
        //更新数据库
        pipelineNumberEODao.updateByPrimaryKeySelective(pNumberEO);
        return materialsOutNo;
	}
	
}
