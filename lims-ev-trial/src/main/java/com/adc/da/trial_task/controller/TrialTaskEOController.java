package com.adc.da.trial_task.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.entity.TrialtaskInsproEO;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.trial_task.page.TrialTaskEOPage;
import com.adc.da.trial_task.service.TrialTaskEOService;
import com.adc.da.trial_task.vo.TrialTaskSearchVO;
import com.adc.da.trial_task.vo.TrialTaskVO;
import com.adc.da.trial_task.vo.TrialtaskInsProjectVO;
import com.adc.da.trial_task.vo.TrialtaskInsproVO;
import com.adc.da.trial_task.vo.TrialtaskSampleVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.WorkflowService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${restPath}/evTrial/trialTask")
@Api(tags = { "EvTrialTask-发动机试验任务模块" })
@SuppressWarnings("all")
public class TrialTaskEOController extends BaseController<TrialTaskEO> {

	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialTaskEOController.class);

	
    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;
    @Autowired
    private TrialTaskEOService trialTaskEOService;
    
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao; 
    
    @Autowired
    private WorkflowService workflowService;

    @Value("${file.path}")
    private String filepath;
    
    @Value("${poSysPath}")
    private String poSysPath;
    

    /**
     * 发动机试验任务保存
    * @Title：saveTrialTask
    * @param trialTaskVO
    * @param taskFile
    * @param operateFile
    * @param trialtaskInsproVOStr
    * @param trialtaskSampleVOStr
    * @return
    * @return: ResponseMessage
    * @author： zhangyinghui 
    * @date： 2019年8月22日
     */
    @BusinessLog(description = "发动机试验任务保存")
    @ApiOperation(value = "|TrialTaskEO|试验任务申请-保存")
    @PostMapping(path = "/saveTrialTask")
    @RequiresPermissions("ev:trialtask:save")
    public ResponseMessage saveTrialTask(TrialTaskVO trialTaskVO,
    									 MultipartFile taskFile, MultipartFile operateFile,
                                         @RequestParam String trialtaskInsproVOStr,
                                         @RequestParam String trialtaskSampleVOStr){

        try {
        	//0-草稿  2-退回  9-撤回  状态 可编辑
        	if(StringUtils.isNotEmpty(trialTaskVO.getTrialStatus()) && 
        			!"0".equals(trialTaskVO.getTrialStatus()) &&
        			!"2".equals(trialTaskVO.getTrialStatus()) &&
        			!"9".equals(trialTaskVO.getTrialStatus())) {
    			return Result.error("-1", "该任务已被提交不可被编辑!");
    		}
        	//校验是否为空
            if(StringUtils.isEmpty(trialTaskVO.getEvNumber())) {
                return Result.error("-1", "试验任务书编号不能为空!");
            }
            if(StringUtils.isEmpty(trialTaskVO.getTitle())) {
                return Result.error("-1", "试验任务名称不能为空!");
            }
    		if(StringUtils.isEmpty(trialTaskVO.getPurpose())) {
    			return Result.error("-1", "试验目的不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getAccording())) {
    			return Result.error("-1", "试验依据不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineModel())) {
    			return Result.error("-1", "发动机型号不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineDevelopStage())) {
    			return Result.error("-1", "发动机开发阶段不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineNumber())) {
    			return Result.error("-1", "发动机编号不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEcuStatus())) {
    			return Result.error("-1", "ECU状态不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEvEmergency())) {
    			return Result.error("-1", "试验紧急程度不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getRequirement())) {
    			return Result.error("-1", "试验要求不能为空");
    		}
    		//转换json 为实体
        	List<TrialtaskInsproVO> trialtaskInsproVOList = JSONObject.parseArray(trialtaskInsproVOStr, TrialtaskInsproVO.class);
        	List<TrialtaskSampleVO> trialtaskSampleVOList = JSONObject.parseArray(trialtaskSampleVOStr, TrialtaskSampleVO.class);
            //保存
        	trialTaskEOService.saveOrUpdateTrialTask(beanMapper.map(trialTaskVO,TrialTaskEO.class),
                    beanMapper.mapList(trialtaskInsproVOList, TrialtaskInsproEO.class),
                    beanMapper.mapList(trialtaskSampleVOList, TrialtaskSampleEO.class),
                    taskFile, operateFile);
            return Result.success("0","试验任务保存成功！", trialTaskVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("exception",e.getMessage());
        }

    }
    
    
    /**
     * 试验任务编号查重
     * @Title: checkNo
     * @param evNumber
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年1月2日
     */
    @ApiOperation(value = "|发动机试验任务-任务编号查重")
    @BusinessLog(description = "发动机试验任务-任务编号查重")
    @GetMapping("/checkNo")
    public ResponseMessage checkNo(String evNumber, String id) {
    	try {
    		if(trialTaskEOService.checkNo(evNumber, id)) {
    			return Result.success("1", "该编码已存在!", evNumber);
    		}else {
    			return Result.success("0", "校验通过!", evNumber);
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "校验失败!");
		}
    }
    
    /**
     * 发动机试验任务-分页查询
    * @Title：page
    * @param page
    * @param searchPhrase
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年9月1日
     */
    @ApiOperation(value = "|发动机试验任务-分页查询")
    @BusinessLog(description = "发动机试验任务-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("ev:trialtask:page")
    @EnableAccess
    public ResponseMessage page(TrialTaskEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
    	try {
    		//查詢
    		List<TrialTaskEO> rows = trialTaskEOService.page(page, searchPhrase);
    		//设置总条数
			Integer rowsCount = trialTaskEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialTaskSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "试验任务查询失败!");
		}
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
    @ApiOperation(value = "|发动机试验任务-详情")
    @BusinessLog(description = "发动机试验任务-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("ev:trialtask:get")
    public  ResponseMessage<TrialTaskVO> getDetailById(@PathVariable(value = "id") String id){
    	try {
			return Result.success("0", "查询成功", beanMapper.map(trialTaskEOService.getDetailById(id), TrialTaskVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    
    /**
     * 提交试验任务流程
    * @Title：submitTrialTask
    * @param
    * @param
    * @param
    * @throws Exception
    * @return: void
    * @author： ljy  
    * @date： 2019年8月31日
     */
    @ApiOperation(value = "|发动机试验任务-流程启动")
    @BusinessLog(description = "发动机试验任务-流程启动")
    @PostMapping("/startProcess")
    @RequiresPermissions("ev:trialtask:startProcess")
    public ResponseMessage submitTrialTask(TrialTaskVO trialTaskVO,
    		MultipartFile taskFile, MultipartFile operateFile,
            @RequestParam String trialtaskInsproVOStr,
            @RequestParam String trialtaskSampleVOStr) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(trialTaskVO.getEvNumber())) {
    			return Result.error("-1", "试验任务书编号不能为空!");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getTitle())) {
    			return Result.error("-1", "试验任务名称不能为空!");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getPurpose())) {
    			return Result.error("-1", "试验目的不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getAccording())) {
    			return Result.error("-1", "试验依据不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineModel())) {
    			return Result.error("-1", "发动机型号不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineDevelopStage())) {
    			return Result.error("-1", "发动机开发阶段不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEngineNumber())) {
    			return Result.error("-1", "发动机编号不能为空");
    		}
    		if(StringUtils.isEmpty(trialTaskVO.getEcuStatus())) {
    			return Result.error("-1", "ECU状态不能为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isEmpty(taskFile) && StringUtils.isEmpty(trialTaskVO.getTaskFileid())) {
    			return Result.error("-1", "任务书不能为空!");
    		}
    		if(StringUtils.isNotEmpty(taskFile)){
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(taskFile.getOriginalFilename());
    			if(!ConstantUtils.FILE_WORD_DOCX.equalsIgnoreCase(fileExtension)) {
        			return Result.error("-1", "任务书只支持上传DOCX格式的文件!");
        		}
    		}
    		
    		//转换json 为实体
    		List<TrialtaskInsproVO> trialtaskInsproVOList = JSONObject.parseArray(trialtaskInsproVOStr, TrialtaskInsproVO.class);
        	List<TrialtaskSampleVO> trialtaskSampleVOList = JSONObject.parseArray(trialtaskSampleVOStr, TrialtaskSampleVO.class);
        	//保存信息 提交流程
    		trialTaskEOService.submitTrialTask(beanMapper.map(trialTaskVO,TrialTaskEO.class),
                    beanMapper.mapList(trialtaskInsproVOList, TrialtaskInsproEO.class),
                    beanMapper.mapList(trialtaskSampleVOList, TrialtaskSampleEO.class),
                    taskFile, operateFile, trialTaskVO.getNextAssignee());
    		return Result.success("0","试验任务流程启动成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "试验任务流程启动失败!");
		}
    }
    
    /**
     * 发动机试验任务-流程审批
    * @Title：approvalProcess
    * @param request
    * @param requestEO
    * @return
    * @return: ResponseMessage<TrialTaskVO>
    * @author： ljy  
    * @date： 2019年9月1日
     */
    @ApiOperation(value = "|发动机试验任务-流程审批")
    @BusinessLog(description = "发动机试验任务-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("ev:trialtask:approvalProcess")
    @EnableAccess
    public ResponseMessage<TrialTaskVO> approvalProcess(HttpServletRequest request,
    		@RequestBody RequestEO requestEO) {
    	try {
    		//校验是否为空
	        if(StringUtils.isEmpty(requestEO)){
	        	return Result.error("-1", "审批信息不可为空");
            }else {
            	if (StringUtils.isEmpty(requestEO.getBaseBusId())){
                    return Result.error( "-1","业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())){
                    return Result.error("-1","任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())){
                    return Result.error("-1","流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())){
                    return Result.error("-1","审批意见不可为空");
                }
	        }
	        trialTaskEOService.approvalProcess(request, requestEO);
	        return Result.success("0", "流程审核成功!");
        }catch(Exception e){
	        logger.error("-1","流程审批失败！");
	        return Result.error("-1","流程审批失败！");
        }
    }

	@ApiOperation(value = "|发动机试验任务-点击“+”查询")
	@BusinessLog(description = "发动机试验任务-点击“+”查询")
	@GetMapping("/list/{id}")
	@RequiresPermissions("ev:trialtask:page")
	public ResponseMessage<List<TrialtaskInsProjectVO>> list(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
		try {
            return Result.success("0", "查询成功", trialTaskEOService.queryTrialProject(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "试验任务查询失败!");
		}
	}
    
    /**
     * 发动机试验任务-导出
    * @Title：exportTrialTask
    * @param response
    * @param request
    * @param trialTaskId
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年9月10日
     */
    @ApiOperation(value = "|发动机试验任务-导出")
    @BusinessLog(description = "发动机试验任务-导出")
    @GetMapping("/exportTrialTask")
    @RequiresPermissions("ev:trialtask:exportTrialTask")
    public  ResponseMessage exportTrialTask(HttpServletResponse response, HttpServletRequest request, String trialTaskId){
    	try {
    		trialTaskEOService.exportTrialTask(response, request, trialTaskId);
			return Result.success("0", "导出成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","导出失败!");
		}
    }
    
    
    /**
     * 打开试验任务书
     * @Title: openEVReportWord
     * @param trialTaskId
     * @param userId
     * @param request
     * @param type
     * @return
     * @throws Exception
     * @return ModelAndView
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|试验任务-pageoffice在线打开文档")
    @BusinessLog(description = "试验任务-pageoffice在线打开文档")
    @GetMapping(path = "/openEVTrialWord")
	public ModelAndView openEVTrialWord(String trialTaskId, Map<String,Object> map,
			HttpServletRequest request, String type) throws Exception{
    	TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
    	TsAttachmentEO attachmentEO = null;
    	//任务书文件
    	if("task".equals(type)) {
    		attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialTaskEO.getTaskFileid());
    	}//任务操作手册
    	else {
    		attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialTaskEO.getOperationFileid());
    	}
		PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
		poCtrl.setServerPage("/poserver.zz");//设置服务页面
		poCtrl.webOpen("file://" + filepath + attachmentEO.getSavePath(),
				OpenModeType.docReadOnly, "");
		map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
		ModelAndView mv = new ModelAndView("EVWord");
		return mv;
	}
    
    
	/**
	 * 添加PageOffice的服务器端授权程序Servlet（必须）
	 * @return
	 */
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
		com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
		poserver.setSysPath(poSysPath);//设置PageOffice注册成功后,license.lic文件存放的目录
		ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
		srb.addUrlMappings("/poserver.zz");
		srb.addUrlMappings("/posetup.exe");
		srb.addUrlMappings("/pageoffice.js");
		srb.addUrlMappings("/jquery.min.js");
		srb.addUrlMappings("/pobstyle.css");
		srb.addUrlMappings("/sealsetup.exe");
        return srb;
    } 
    
	@ApiOperation(value = "流程发起或审批时，查询下一节点审批人")
    @GetMapping("/getNextAssignee")
	public ResponseMessage<List<Map<String, Object>>> getNextAssignee(String prodefkey, String taskId){
		JSONArray result = new JSONArray();
		if(StringUtils.isEmpty(prodefkey)){
			return Result.error("-1","流程标识不能为空");
		}
		try {
			if(StringUtils.isNotEmpty(taskId)) {
				//判断当前节点是否是最后一个节点
				boolean flag = false;
				ActivityImpl currentActivity = workflowService.getActivityImpl(null, taskId);
				List<PvmTransition> outgoingTransitions = currentActivity.getOutgoingTransitions();
				PvmTransition pvmTransition = outgoingTransitions.get(0);
				//下一节点
				PvmActivity destination = pvmTransition.getDestination();
				String type = destination.getProperty("type").toString();
				if("endEvent".equals(type)) {	
					//下一节点直接结束
					flag = true;
				}else if("exclusiveGateway".equals(type)) {
					//下一节点是排他网关
					List<PvmTransition> transitions = destination.getOutgoingTransitions();
					if (transitions != null && !transitions.isEmpty()) {
		                for (PvmTransition transition : transitions) {
		                    String conditionText = transition.getProperty("conditionText").toString();
		                    int startIndex = conditionText.indexOf('\"') + 1;
		                    int endIndex = conditionText.lastIndexOf('\"');
		                    String code = conditionText.substring(startIndex, endIndex);
		                    if (StringUtils.equals("agree", code)) {
		                    	//根据分支条件获取下一节点
		                    	PvmActivity activity = transition.getDestination();
		                    	String t = activity.getProperty("type").toString();
		                    	if("endEvent".equals(t)) {
		                    		flag = true;
		                    	}
		                    }
		                }
					}
				}
				if(flag) {
					return Result.error("-1","当前节点是最后一个节点，无需选择审批人");
				}
			}
			return Result.success(trialTaskEOService.getNextAssignee(prodefkey, taskId));
		} catch (Exception e) {
			return Result.error("-1","查询失败");
		}
	}
	
	@ApiOperation(value = "流程转办后更新试验待办人")
    @PostMapping(path = "/updateTrialAssignee")
	public ResponseMessage updateTrialAssignee(String taskId) {
		try {
			trialTaskEOService.updateTrialAssignee(taskId);
			return Result.success("0", "更新成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "更新失败");
		}
	}
	
}
