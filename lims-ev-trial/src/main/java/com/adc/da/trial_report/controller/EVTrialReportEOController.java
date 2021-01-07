package com.adc.da.trial_report.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.trial_report.dto.TrialScheduleDto;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.page.TrialReportEOPage;
import com.adc.da.trial_report.service.EVTrialReportEOService;
import com.adc.da.trial_report.service.ReportArchivefileEOService;
import com.adc.da.trial_report.service.TrialReportEOService;
import com.adc.da.trial_report.vo.ReportSubmitVO;
import com.adc.da.trial_report.vo.TrialReportSearchVO;
import com.adc.da.trial_report.vo.TrialReportVO;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.trial_task.entity.TrialtaskInsproEO;
import com.adc.da.trial_task.vo.TrialtaskInsproVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * EV-发动机试验管理-试验报告模块
 * @author Administrator
 * @date 2019年8月20日
 */
@RestController
@RequestMapping("/${restPath}/evReport/trialReport")
@Api(tags = "EVTrialReport-发动机试验报告模块")
@SuppressWarnings("all")
public class EVTrialReportEOController extends BaseController<TrialReportEO>{

	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(EVTrialReportEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Value("${file.path}")
    private String filepath;
    
    @Value("${poSysPath}")
    private String poSysPath;
    
    
    @Autowired
    private EVTrialReportEOService evTrialReportEOService;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao; 
    
    @Autowired
    private LimsFileService limsFileService;
    
    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;

    /**
     * 发动机试验报告分页查询
    * @Title：page
    * @param page
    * @param searchPhrase
    * @return
    * @return: ResponseMessage<PageInfo<TrialReportSearchVO>>
    * @author： ljy  
    * @date： 2019年8月28日
     */
    @ApiOperation(value = "|试验报告-分页查询")
    @BusinessLog(description = "试验报告-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("report:trialReport:page")
    public ResponseMessage<PageInfo<TrialReportSearchVO>> page(
    		TrialReportEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<TrialReportEO> rows =  evTrialReportEOService.getReportEntityPage(page, searchPhrase);
			//设置总条数
			Integer rowsCount = evTrialReportEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialReportSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    /**
     * 试验报告新增
    * @Title：save
    * @param trialReportVO
    * @param fileReport
    * @param filePDF
    * @return
    * @return: ResponseMessage<TrialReportVO>
    * @author： ljy  
    * @date： 2019年8月20日
     */
    @ApiOperation(value = "|试验报告-新增")
    @BusinessLog(description = "试验报告-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("report:trialReport:save")
    public ResponseMessage<TrialReportVO> save(TrialReportVO trialReportVO,
    		MultipartFile fileReport, MultipartFile file) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(fileReport)) {
    			return Result.error("-1", "报告模板不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(fileReport)) {
    			//文件后缀
    			String fileReportExtension = FileUtil.getFileExtension(fileReport.getOriginalFilename());
    			if(!ConstantUtils.FILE_WORD_DOCX.equalsIgnoreCase(fileReportExtension)) {
    				return Result.error("-1", "报告模板只支持上传DOCX格式的文件!");
        		}
    		}
    		TrialReportEO trialReportEO = evTrialReportEOService.save(beanMapper.map(trialReportVO, TrialReportEO.class), fileReport, file);
    		return Result.success("0","新增成功!",beanMapper.map(trialReportEO, TrialReportVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
       
    }
    
    /**
     * 根据试验任务id获取报告选择的检验项目信息
    * @Title：getInsProInfo
    * @param trialtaskId
    * @return: void
    * @author： ljy  
    * @date： 2019年9月5日
     */
    @ApiOperation(value = "|试验报告-获取报告选择的检验项目信息")
    @BusinessLog(description = "试验报告-获取报告选择的检验项目信息")
    @GetMapping(path = "/getInsProInfo")
    public ResponseMessage<List<TrialtaskInsproVO>> getInsProInfo(String trialtaskId) {
    	try {
    		List<TrialtaskInsproEO> list = evTrialReportEOService.getInsProInfo(trialtaskId);
    		return Result.success("0","查询成功!", beanMapper.mapList(list, TrialtaskInsproVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    /**
     * 删除报告
    * @Title：deleteReport
    * @param id 报告id
    * @return: void
    * @author： ljy  
    * @date： 2019年8月26日
     */
    @ApiOperation(value = "|试验报告-删除")
    @BusinessLog(description = "试验报告-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("report:trialReport:detele")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		evTrialReportEOService.deleteReport(id);
			return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }	

    /**
     * pageoffice 在线打开Word文档(逻辑改为：pageOffice里只有保存操作，保存之后在外面提交，提交时选择下一节点审批人。by xwb)
    * @Title：openEVReportWord
    * @param reportId
    * @param request
    * @param map
    * @return
    * @throws Exception
    * @return: ModelAndView
    * @author： ljy  
    * @date： 2019年8月22日
     */
    @ApiOperation(value = "|试验报告-pageoffice在线打开文档")
    @BusinessLog(description = "试验报告-pageoffice在线打开文档")
    @GetMapping(path = "/openEVReportWord")
	public ModelAndView openEVReportWord(String reportId, HttpServletRequest request, Map<String,Object> map) throws Exception{
    	TrialReportEO trialReportEO = evTrialReportEOService.selectByPrimaryKey(reportId);
    	//获取文档路径
    	TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid());
		PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
		poCtrl.setServerPage("/poserver.zz");//设置服务页面
		//poCtrl.addCustomToolButton("保存","Save",1);//添加自定义保存按钮
		poCtrl.addCustomToolButton("保存","ReportSubmit",2);//添加自定义提交按钮
		//打开word
		poCtrl.setSaveFilePage("saveWord?reportId=" + trialReportEO.getId());
		poCtrl.webOpen("file://" + filepath + attachmentEO.getSavePath(),
				OpenModeType.docAdmin, "");
		map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
		ModelAndView mv = new ModelAndView("EVWord");
		return mv;
	}
    
    /**
     * pageoffice 点击保存,进行文档保存
    * @Title：saveFile
    * @param request
    * @param response
    * @throws Exception
    * @return: void
    * @author： ljy  
    * @date： 2019年8月22日
     */
    @ApiOperation(value = "|试验报告-pageoffice保存文档")
    @BusinessLog(description = "试验报告-pageoffice保存文档")
    @PostMapping(path = "/saveWord")
    public void saveFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//获取文件信息
    	FileSaver fs = new FileSaver(request, response);
    	//获取文件后缀
    	String fileExtName = fs.getFileExtName();
    	//获取报告实体信息
    	String reportId = request.getParameter("reportId");
    	TrialReportEO trialReportEO = evTrialReportEOService.selectByPrimaryKey(reportId);
    	
    	//获取报告模板保存路径
    	String filePath = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid()).getSavePath();
    	
    	if(".pdf".equals(fileExtName)) {
    		if(StringUtils.isNotEmpty(trialReportEO.getPdfFileid())){
    			//如果存在pdf文件，删掉
    			String delPdfPath = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid()).getSavePath();
    			File file = new File(filepath + delPdfPath);
    			file.delete();
    			reportArchivefileEOService.deleteByFileId(trialReportEO.getPdfFileid());
    		}
    		// 保存pdf
    		String pdfPath = fs.getFileName();
    		fs.saveToFile(filepath + pdfPath);
    		fs.close();
    		//保存至附件表 
    		String pdfFileId = limsFileService.saveAttachment(fs, trialReportEO.getReportNo());
    		//更新报告
    		trialReportEO.setPdfFileid(pdfFileId);
    		evTrialReportEOService.updateByPrimaryKeySelective(trialReportEO);
    	} else {
    		// 保存word
    		fs.saveToFile(filepath + filePath);
    		fs.close();
    	}
    	
    }
    
    @ApiOperation(value = "试验报告-提交流程或退回重新提交")
    @BusinessLog(description = "试验报告-提交流程或退回重新提交")
    @PostMapping("/submitReport")
    @RequiresPermissions("report:trialReport:submitReport")
    public ResponseMessage submitReport(@RequestBody ReportSubmitVO reportSubmitVO, HttpServletRequest request) {
    	try {
    		//报告实体类
    		TrialReportEO trialReportEO = evTrialReportEOService.selectByPrimaryKey(reportSubmitVO.getReportId());
    		
    		//将报告涉及的附件保存至报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)
    		reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA_PDF, 
    				trialReportEO.getReportNo(), trialReportEO.getPdfFileid(), trialReportEO);
    		
    		//根据baseBus是否为空判断是流程发起还是退回、撤回后重新提交
    		BaseBusEO baseBus = evTrialReportEOService.getBaseBusByBusinessId(reportSubmitVO.getReportId());
    		boolean reSubmit = baseBus != null ? true : false;
    		
    		if(reSubmit) {
		      	//重新提交直接进行 流程审批逻辑  RequestEO:重新提交需要传该参数
		      	RequestEO requestEO = new RequestEO();
		      	requestEO.setBaseBusId(reportSubmitVO.getBaseBusId());
		      	requestEO.setProcId(reportSubmitVO.getProcId());
		      	requestEO.setTaskId(reportSubmitVO.getTaskId());
		      	requestEO.setType(reportSubmitVO.getType());
		      	requestEO.setNextAssignee(reportSubmitVO.getNextAssignee());
		      	Map variables = new HashMap<>();
		      	variables.put("approve", "agree");
		      	requestEO.setVariables(variables);
		      	approvalProcess(request, requestEO);
    		}else {
    			//启动发动机报告流程
    			String userId = UserUtils.getUserId();
		      	evTrialReportEOService.startReportEntityProcess(trialReportEO, userId, reportSubmitVO.getReportType(), reportSubmitVO.getNextAssignee());
    		}
	        return Result.success("0", "流程提交成功!");
        }catch(Exception e){
	        logger.error(e.getMessage(), e);
	        return Result.error("-1","流程提交失败！");
        }
    }
    
    /**
     * 发动机报告审核流程
    * @Title：approvalProcess
    * @param request
    * @param requestEO
    * @return
    * @return: ResponseMessage<TrialReportVO>
    * @author： ljy  
    * @date： 2019年8月27日
     */
    @ApiOperation(value = "|试验报告-流程审批")
    @BusinessLog(description = "试验报告-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("report:trialReport:approvalProcess")
    @EnableAccess
    public ResponseMessage<TrialReportVO> approvalProcess(HttpServletRequest request,
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
				/*
				 * if (StringUtils.isEmpty(requestEO.getVariables())){ return
				 * Result.error("-1","审批意见不可为空"); }
				 */
	        }
	        evTrialReportEOService.approvalProcess(request,  requestEO);
	        return Result.success("0", "流程审核成功!");
        }catch(Exception e){
	        logger.error("-1","流程审批失败！");
	        return Result.error("-1","流程审批失败！");
        }
    }
    
    
    /**
     * 试验报告--获取PDF路径
    * @Title：getPDFPath
    * @param businessId 业务主键id
    * @param isWaterMark 是否是水印
    * @return
    * @return: ResponseMessage<String>
    * @author： ljy  
    * @date： 2019年9月20日
     */
    @ApiOperation(value = "|试验报告--获取PDF路径")
    @GetMapping("/getPDFPath")
    public ResponseMessage<String> getPDFPath(String businessId, String isWaterMark) {
        try {
        	//根据业务id返回路径
            return Result.success("0", "查询成功!", evTrialReportEOService.getReportPDFPath(businessId, isWaterMark));
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("-1","查询失败！");
        }
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

	@ApiOperation(value = "|任务执行--进度")
	@BusinessLog(description = "任务执行--进度")
	@PostMapping("/schedule")
	@RequiresPermissions("report:trialReport:schedule")
	public ResponseMessage schedule(@RequestBody TrialScheduleDto trialScheduleDto) {
		try {
			if (trialScheduleDto == null) {
				return Result.error("-1","保存失败！保存数据不能为空");
			}
			if (StringUtils.isBlank(trialScheduleDto.getFinishRate())) {
				return Result.error("-1","保存失败！完成率数据不能为空");
			}
			if (StringUtils.isBlank(trialScheduleDto.getTaskStatus())) {
				return Result.error("-1","保存失败！任务状态数据不能为空");
			}
			if (StringUtils.isBlank(trialScheduleDto.getTrialLocation())) {
				return Result.error("-1","保存失败！试验地点数据不能为空");
			}
			evTrialReportEOService.createScedule(trialScheduleDto);
			return Result.success("0", "保存成功");
		}catch(Exception e){
			logger.error(e.getMessage());
			return Result.error("-1","保存失败！");
		}
	}

	
}
