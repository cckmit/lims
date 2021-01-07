package com.adc.da.pc_report.service;

import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.acttask.service.ActProcessService;
import com.adc.da.car.page.SaCarReturnEOPage;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_items.service.TrialItemsEOService;
import com.adc.da.pc_report.dao.PcReportEODao;
import com.adc.da.pc_report.page.ReportPage;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.page.TrialTaskEOPage;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.util.DocWriterUtils;
import com.adc.da.sys.util.InsertPicByJacob;
import com.adc.da.sys.util.JacobWordBean;
import com.adc.da.trial_report.dao.TrialReportEODao;
import com.adc.da.trial_report.dto.TrialScheduleDto;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.service.TrialReportEOService;
import com.adc.da.trial_report.service.TrialReportProcessEOService;
import com.adc.da.util.SearchFieldUtil;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcReportEOService {
	
	@Autowired
    private TrialReportEODao trialReportEODao;

    @Autowired
    private TrialReportEOService trialReportEOService;

    @Autowired
    private TrialTaskEODao trialTaskEOService;

    @Autowired
    private PrimaryGenerater primaryGenerater;

    @Autowired
    private TrialItemsEOService trialItemsEOService;

    @Autowired
    private TrialReportProcessEOService trialReportProcessEOService;

    @Autowired
    private PcReportEODao dao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Value("${file.path}")
    private String filepath;

    @Value("${watermark.name}")
    private String waterMarkName;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private TrialTaskEODao trialTaskEODao;

    @Autowired
    private ActProcessService actProcessService;
    
    @Autowired
    private TaskService taskService;
    
    private String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");


    /**
     * 新增报告
     *
     * @param reportEO
     * @param fileReport
     * @param file
     */
    public TrialReportEO save(TrialReportEO reportEO, MultipartFile fileReport, MultipartFile file) throws Exception {
        //委托id
        String taskId = reportEO.getTrialtaskId();
        TrialTaskEO trialTaskEO = new TrialTaskEO();
        //获取最新流水号
        if (StringUtils.isNotEmpty(taskId)) {
            trialTaskEO = trialTaskEOService.selectByPrimaryKey(taskId);
            String pvOrcv = trialTaskEO.getPvOrcv();
            //CV
            if ("0".equals(pvOrcv)) {
                reportEO.setReportType("CV");
                reportEO.setReportNo(primaryGenerater.findReportNo(ConstantUtils.CV_REPORT_NO, "CV"));
            } else if ("1".equals(pvOrcv)) { //PV
                reportEO.setReportType("PV");
                reportEO.setReportNo(primaryGenerater.findReportNo(ConstantUtils.PV_REPORT_NO, "PV"));
            }
            //委托人
            reportEO.setOriginator(userEODao.selectByPrimaryKey(trialTaskEO.getCreateBy()).getUsname());
            //委托编号
            reportEO.setTaskCode(trialTaskEO.getTaskCode());
        }
        //保存报告实体
        reportEO = trialReportEOService.saveReportEntity(reportEO, fileReport, file);
        //检验项目
        if (StringUtils.isNotEmpty(reportEO.getTrialtaskInsproids())) {
            String[] itemsIds = reportEO.getTrialtaskInsproids().split(",");
            for(String itemsId : itemsIds){
            	TrialItemsEO trialItemsEO = new TrialItemsEO();
	            trialItemsEO = trialItemsEOService.selectByPrimaryKey(itemsId);
	            //回填报告id
	            trialItemsEO.setReportId(reportEO.getId());
	            //提取检验项目，vin码
	            reportEO.setInsProNames(trialItemsEO.getInsproName());
	            reportEO.setVINCode(trialItemsEO.getVinCode());
	            trialItemsEOService.updateByPrimaryKeySelective(trialItemsEO);
            }
        }
        trialReportEOService.updateByPrimaryKeySelective(reportEO);
        //对报告模板插入数据
        //---------2020.01.19 与杨工确认, 暂不填充报告-------------//
        //fillDataToWord(reportEO, trialItemsEO, trialTaskEO);
        return reportEO;
    }

    /**
     * 对报告模板插入数据
     *
     * @param reportEO
     */
    public void fillDataToWord(TrialReportEO reportEO, TrialItemsEO trialItemsEO, 
    		TrialTaskEO trialTaskEO) throws Exception {
    	//生命插入数据对象
        Map dataMap = new HashMap();
        //报告编号
        dataMap.put("${REPORT_NO}", StringUtils.isNotEmpty(reportEO.getReportNo())?reportEO.getReportNo():"");
        //样品名
        if("0".equals(trialItemsEO.getSampleType()))
        dataMap.put("${SAMPLE_NAME}", StringUtils.isNotEmpty(trialItemsEO.getCarName())?trialItemsEO.getCarName():"");
        else if("1".equals(trialItemsEO.getSampleType()))
        dataMap.put("${SAMPLE_NAME}", StringUtils.isNotEmpty(trialItemsEO.getPartName())?trialItemsEO.getPartName():"");
        //车型
        dataMap.put("${VEHICLE_TYPE}", StringUtils.isNotEmpty(trialItemsEO.getVehicleType())?trialItemsEO.getVehicleType():"");
        //检验项目名
        dataMap.put("${INSPRO_NAME}", StringUtils.isNotEmpty(trialItemsEO.getInsproName())?trialItemsEO.getInsproName():"");
        //检验依据
        dataMap.put("${ACCORDING}", StringUtils.isNotEmpty(trialTaskEO.getAccording())?trialTaskEO.getAccording():"");
        //样车编号
        dataMap.put("${SAMPLE_NO}", StringUtils.isNotEmpty(trialItemsEO.getSampleNo())?trialItemsEO.getSampleNo():"");
        //发动机号
        dataMap.put("${ENGINE_NUM}", StringUtils.isNotEmpty(trialItemsEO.getPartEngineNo())?trialItemsEO.getPartEngineNo():"");
        dataMap.put("${PRODUCED_TIME}","");
        dataMap.put("${TYRE_TYPE}","");
        if(StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO())) {
            //生产时间
            dataMap.put("${PRODUCED_TIME}", StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO().getProducedTime()) ? trialItemsEO.getSaCarDataEO().getProducedTime() : "");
            //轮胎型号
            dataMap.put("${TYRE_TYPE}", StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO().getTyreType()) ? trialItemsEO.getSaCarDataEO().getTyreType() : "");
        }
        //插入到模板
        //拼接报告填充数据后的路径
        String path = trialTaskEO.getTaskCode() + "/" + reportEO.getReportNo() + "/" + UUID.randomUUID() + ".docx";
        //根据附件id , 获取模板路径
        String multipartFile = tsAttachmentEODao.selectByPrimaryKey(reportEO.getReportFileid()).getSavePath();
        DocWriterUtils docWriterUtils = new DocWriterUtils();
        docWriterUtils.fillDataToWord(filepath+multipartFile, filepath+path, dataMap);
        //将填充完数据的报告数据存储至附件
        TsAttachmentEO attachment = new TsAttachmentEO();
        attachment.setAttachmentName(reportEO.getReportNo());
        attachment.setId(UUID.randomUUID());
        attachment.setAttachmentType(ConstantUtils.FILE_WORD_DOCX);
        attachment.setSavePath(path);
        //删除标记  0 未删除;  1删除
        attachment.setDelFlag(0);
        attachment.setCreateBy(UserUtils.getUserId());
        attachment.setCreateTime(currDate);
        attachment.setUpdateBy(UserUtils.getUserId());
        attachment.setUpdateTime(currDate);
        tsAttachmentEODao.insert(attachment);
        //将新的id, 更新至报告
        reportEO.setReportFileid(attachment.getId());
        reportEO.setUpdateTime("");
        trialReportEOService.updateByPrimaryKeySelective(reportEO);
    }
    
    
    /**
     * 删除报告
     *
     * @param reportId
     */
    public void delReport(String reportId) {
        //删除检验项目中的报告id
        trialItemsEOService.delReport(reportId);
        //删除报告的数据
        trialReportEOService.deleteReportEntity(reportId);
    }


    /**
     * 任务执行--进度
     *
     * @param trialScheduleDto
     * @throws Exception
     */
    public void createScedule(TrialScheduleDto trialScheduleDto) throws Exception {
        TrialReportProcessEO eo = trialReportProcessEOService.saveSceduleDto(trialScheduleDto);

        //需求变更：此处原逻辑PC进度条为每个实验项目的进度，现PC变更为整个task的进度
/*        String status = eo.getTrialprojectStatus();
        // 当进度试验状态选择进行中、已取消、已暂停时，修改任务信息状态为进行中 5
        TrialItemsEO trialItems = trialItemsEOService.selectByPrimaryKey(
                trialScheduleDto.getTrialtaskInsproId());
        if ("0".equals(status) || "1".equals(status) || "2".equals(status)) {
            trialItems.setItemsStatus("5");
        }
        // 当进度试验状态选择已结束时，修改任务信息状态为已结束 6
        if ("3".equals(status)) {
            trialItems.setItemsStatus("6");
        }
        trialItemsEOService.updateByPrimaryKeySelective(trialItems);*/
    }

    /**
     * 审批报告
     *
     * @param request
     * @param requestEO
     * @return
     */
    @SuppressWarnings("unchecked")
    public void approvalReport(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取试验任务业务信息
        TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //链接
        String link = baseBusEO.getBusinessType();
        //退回
        if ("rollback".equals(approveCode)) {
            //4-退回
            trialReportEO.setReportStatus("4");
            //更新报告状态
            trialReportEOService.updateByPrimaryKeySelective(trialReportEO);
            //退回给相关人员 发送消息通知、工作日历、邮件
            String title = "报告【" + trialReportEO.getReportNo() + "】退回";
            actProcessService.sendRemindToIds(baseBusEO.getCreateBy().split(","), baseBusEO);
        } else if ("reback".equals(approveCode)) {
            //5-撤回
            trialReportEO.setReportStatus("5");
            //更新报告状态
            trialReportEOService.updateByPrimaryKeySelective(trialReportEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        } else {
            //流程通过状态加1
            trialReportEO.setReportStatus(String.valueOf(Integer.parseInt(trialReportEO.getReportStatus()) + 1));
            //更新报告状态
            trialReportEOService.updateByPrimaryKeySelective(trialReportEO);
        }
        
        //设置下一节点审批人
        if(StringUtils.isNotEmpty(requestEO.getNextAssignee())){
        	Task task = taskService.createTaskQuery().taskId(requestEO.getTaskId()).singleResult();
        	// PV-试验报告审批流程：开始->pv主管->pv科长->pv部长->结束
    		// CV-试验报告审批流程：开始->cv主管->cv科长->cv部长->结束
			if("PV主管".equals(task.getName()) || "试验中心主管".equals(task.getName())){
				//设置pv科长或cv科长节点审批人
				requestEO.getVariables().put("kz", requestEO.getNextAssignee());
			}else if("PV科长".equals(task.getName()) || "试验中心科长".equals(task.getName())){
				//设置pv部长或cv部长节点审批人
				requestEO.getVariables().put("bz", requestEO.getNextAssignee());
			}else if("发起人重新提交".equals(task.getName())){
				//设置pv主管或cv主管节点审批人
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
        if (StringUtils.isEmpty(UserUtils.getUserId())) {
            activitiTaskRequestVO.setAssignee(request.getParameter("userId"));
        } else {
            activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        }
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

        //判断流程是否结束，结束的话，变更业务状态
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(trialReportEO)) {
                //3-档案室
                trialReportEO.setReportStatus("3");
                trialReportEO.setUpdateTime(currDate);
                //更新报告状态
                trialReportEOService.updateByPrimaryKeySelective(trialReportEO);

                //获取文件路径
    //            TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
                //PDF 加签章 PDF及水印PDF都要加签章
    //            trialReportEOService.PDFAddStamp(eo.getSavePath(), eo.getWaterMarkPath());
                //全部审批完成后，将所有审批人的签章加到pdf最后面
    //            trialReportEOService.addAssigneeStamp(requestEO.getBaseBusId());

                //流程走完后, 将审批人签名导入Word, 并将Word重新转成新的PDF  并加公司公章及水印
                writeWordAndToPDF(trialReportEO);

                //当前日期
                String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
                //反向更新试验状态
                TrialTaskEO trialTask = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId());
                //报告结束  8-试验完成
                trialTask.setTaskStatus("8");
                trialTask.setDelyDays(0);
                trialTask.setDelyType("0");
                trialTaskEODao.updateByPrimaryKeySelective(trialTask);
            }
        } else {
            //获取报告业务信息
            String title = "报告【" + trialReportEO.getReportNo() + "】审批流程";
            //获取下一节点人
            if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                actProcessService.sendRemindToIds(ids, baseBusEO);
            }
        }
    }


    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    public List<ActTaskEO> reportTaskPage(ActTaskEOPage page, String searchField) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TrialTaskEOPage search = (TrialTaskEOPage) SearchFieldUtil.pageSet(TrialTaskEOPage.class.getName(), searchField);
        page.setSearchPhrase(search.getSearchPhrase());
        page.setCurrUserId(UserUtils.getUserId());
        return dao.reportTaskPage(page);
    }

    /**
     * 总数查询
     *
     * @param page
     * @return
     */
    public int reportTaskCount(ActTaskEOPage page) {
        return dao.reportTaskCount(page);
    }

    /**
     * 查询
     * @param page
     * @param searchPhrase
     * @return
     */
	public List<ReportPage> getReportPage(ReportPage page, String searchPhrase) {
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
		//--------------单条件查询-------------//
		
		List<ReportPage> lists = dao.queryReportByPage(page);
		//查询
		return lists;
	}

	/**
	 * 查询总数
	 * @param page
	 * @return
	 */
	public int queryByCount(ReportPage page) {
		return dao.queryCount(page);
	}

	/**
	 * 引用报告
	 * @param reportEO
	 * @return
	 */
	public Integer saveReport(TrialReportEO reportEO) {
		
		return trialReportEODao.insert(reportEO);
	}

	public void quoteReport(String reportId, String taskId, String trialtaskInsproid) throws Exception {
		TrialTaskEO trialTaskEO = new TrialTaskEO();//试验委托
    	TrialItemsEO trialItemsEO = new TrialItemsEO();//检验项目
    	TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportId);
    	TrialReportEO reportEO = new TrialReportEO();
    	if(StringUtils.isNotEmpty(taskId)) {
    		trialTaskEO = trialTaskEOService.selectByPrimaryKey(taskId);
    		String pvOrcv = trialTaskEO.getPvOrcv();
            //CV
            if ("0".equals(pvOrcv)) {
                reportEO.setReportType("CV");
                reportEO.setReportNo(primaryGenerater.findReportNo(ConstantUtils.CV_REPORT_NO, "CV"));
            } else if ("1".equals(pvOrcv)) { //PV
                reportEO.setReportType("PV");
                reportEO.setReportNo(primaryGenerater.findReportNo(ConstantUtils.PV_REPORT_NO, "PV"));
            }
            //委托人
            reportEO.setOriginator(userEODao.selectByPrimaryKey(trialTaskEO.getCreateBy()).getUsname());
            //委托编号
            reportEO.setTaskCode(trialTaskEO.getTaskCode());
    	}
    	reportEO.setId(UUID.randomUUID());
    	reportEO.setTrialtaskId(taskId);
    	reportEO.setPdfFileid(trialReportEO.getPdfFileid());
    	reportEO.setCreateBy(trialReportEO.getCreateBy());
    	reportEO.setReportFileid(trialReportEO.getReportFileid());
    	reportEO.setReportStatus(trialReportEO.getReportStatus());
    	reportEO.setDelFlag("0");
    	reportEO.setReportNo(trialReportEO.getReportNo());
    	reportEO.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        this.saveReport(reportEO);
        if(StringUtils.isNotEmpty(trialtaskInsproid)) {
    		trialItemsEO = trialItemsEOService.selectByPrimaryKey(trialtaskInsproid);
    		//回填报告id
            trialItemsEO.setReportId(reportEO.getId());
            //提取检验项目，vin码
            reportEO.setInsProNames(trialItemsEO.getInsproName());
            reportEO.setVINCode(trialItemsEO.getVinCode());
            trialItemsEOService.updateByPrimaryKeySelective(trialItemsEO);
    	}
        trialReportEOService.updateByPrimaryKeySelective(reportEO);
        
	}




//PC报告加签章转pdf水印
    public void writeWordAndToPDF(TrialReportEO trialReportEO) throws Exception {
        //将签章插入Word

        //流程信息
        String actProcId = trialReportEODao.selectActProcIdById(trialReportEO.getId());

        String PC_CHARGE = "";
        String PC_SECTIONCHIEF = "";
        String PC_MINISTER = "";

        String PC_CHARGE_PATH = "";
        String PC_SECTIONCHIEF_PATH = "";
        String PC_MINISTER_PATH = "";

        if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
            List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
            //主管  --校对
            PC_CHARGE = list.get(2).getAssignee();
            PC_CHARGE_PATH = getSealPath(PC_CHARGE);
            //科长  --审核
            PC_SECTIONCHIEF = list.get(1).getAssignee();
            PC_SECTIONCHIEF_PATH = getSealPath(PC_SECTIONCHIEF);
            //部长  --批准
            PC_MINISTER = list.get(0).getAssignee();
            PC_MINISTER_PATH = getSealPath(PC_MINISTER);
        }

        Map<String, String> map = new HashMap<String, String>();
        //编写
        map.put("PC_Createby_pic", getSealPath(trialReportEO.getCreateBy()));
        //主管  --校对
        map.put("PC_Charge_pic", PC_CHARGE_PATH);
        //科长  --审核
        map.put("PC_SectionChief_pic", PC_SECTIONCHIEF_PATH);
        //部长  --批准
        map.put("PC_Minister_pic", PC_MINISTER_PATH);

        TsAttachmentEO attachment = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid());

        String taskNo = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId()).getTaskCode();

        InsertPicByJacob jacob = new InsertPicByJacob();
        JacobWordBean bean = new JacobWordBean(false);
        boolean flag = bean.insertMapByJacob(filepath + attachment.getSavePath(), "", map);

        if(flag) {
            String pdfNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + ".pdf";
            String waterMarkNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + "_watermark.pdf";
            //将Word转成PDF
            if(jacob.convertWord(filepath + attachment.getSavePath(), filepath + pdfNewPath)) {
                TsAttachmentEO pdf = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
                pdf.setSavePath(pdfNewPath);
                pdf.setWaterMarkPath(waterMarkNewPath);
                tsAttachmentEODao.updateByPrimaryKey(pdf);
                //PDF 加签章 PDF及水印PDF都要加签章
                trialReportEOService.PDFAddStamp(pdfNewPath, waterMarkNewPath);
            }
        }

    }


    //委外申请签章
    public void writeWordAndToPDF(Map<String, String> paramMap,String savePath) throws Exception {
        //将签章插入Word
/*

        if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
            List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
            //主管  --校对
            PC_CHARGE = list.get(2).getAssignee();
            PC_CHARGE_PATH = getSealPath(PC_CHARGE);
            //科长  --审核
            PC_SECTIONCHIEF = list.get(1).getAssignee();
            PC_SECTIONCHIEF_PATH = getSealPath(PC_SECTIONCHIEF);
            //部长  --批准
            PC_MINISTER = list.get(0).getAssignee();
            PC_MINISTER_PATH = getSealPath(PC_MINISTER);
        }
*/
        String sectionChief = paramMap.get("PC_SectionChief_pic");
        String minister = paramMap.get("PC_Minister_pic");
        paramMap.put("PC_SectionChief_pic",getSealPath(sectionChief));
        paramMap.put("PC_Minister_pic",getSealPath(minister));
        InsertPicByJacob jacob = new InsertPicByJacob();
        JacobWordBean bean = new JacobWordBean(false);
        boolean flag = bean.insertMapByJacob(savePath, "", paramMap);

/*        if(flag) {
            String pdfNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + ".pdf";
            String waterMarkNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + "_watermark.pdf";
            //将Word转成PDF
            if(jacob.convertWord(filepath + attachment.getSavePath(), filepath + pdfNewPath)) {
                TsAttachmentEO pdf = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
                pdf.setSavePath(pdfNewPath);
                pdf.setWaterMarkPath(waterMarkNewPath);
                tsAttachmentEODao.updateByPrimaryKey(pdf);
                //PDF 加签章 PDF及水印PDF都要加签章
                trialReportEOService.PDFAddStamp(pdfNewPath, waterMarkNewPath);
            }
        }*/

    }


    public String getSealPath(String usid) {
        String imagePath = "";
        UserEO userEO = userEODao.selectByPrimaryKey(usid);
        if(StringUtils.isNotEmpty(userEO.getSeal())){
            TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(userEO.getSeal());
            if (StringUtils.isNotEmpty(attach)) {
                imagePath = filepath + attach.getSavePath();
                imagePath = imagePath.replaceAll("/", "\\\\");
            }
        }
        return imagePath;
    }


    //test
/*    public static void  main(String args[]){
        Map<String, String> map = new HashMap<String, String>();
        //编写   "D:/12345.jpg"
        map.put("PC_Createby_pic", "D:"+File.separator+"12345.jpg");
        //主管  --校对
        map.put("PC_Charge_pic", "D:"+File.separator+"12345.jpg");
        //科长  --审核
        map.put("PC_SectionChief_pic", "D:"+File.separator+"12345.jpg");
        //部长  --批准
        map.put("PC_Minister_pic", "D:"+File.separator+"12345.jpg");
        JacobWordBean a = new JacobWordBean(false);
        a.insertMapByJacob("D:/111.docx","",map);
    }*/


}
