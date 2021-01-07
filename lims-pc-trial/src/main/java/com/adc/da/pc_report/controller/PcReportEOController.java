package com.adc.da.pc_report.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.web.BaseController;
import com.adc.da.car.page.SaCarReturnEOPage;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_report.page.ReportPage;
import com.adc.da.pc_report.service.PcReportEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.trial_report.dto.TrialScheduleDto;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.page.TrialReportEOPage;
import com.adc.da.trial_report.service.ReportArchivefileEOService;
import com.adc.da.trial_report.service.TrialReportEOService;
import com.adc.da.trial_report.vo.ReportSubmitVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/${restPath}/pc_report/report")
@Api(tags = "pc_report-报告模块")
@SuppressWarnings("all")
public class PcReportEOController extends BaseController {

    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PcReportEOController.class);

    @Autowired
    private TrialReportEOService trialReportEOService;

    @Autowired
    private PcReportEOService pcReportEOService;
    
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
    
    @Autowired
    private LimsFileService limsFileService;

    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;
    
    @Value("${file.path}")
    private String filepath;
    
    @Value("${poSysPath}")
    private String poSysPath;
    
    @Autowired
    private BeanMapper beanMapper;


	@BusinessLog(description = "pc报告查询")
	@ApiOperation("|PcReportEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pcreporteo:pcreporteo:page")
    public ResponseMessage<PageInfo<TrialReportEO>> page(TrialReportEOPage page, String searchPhrase) {
        try {
            List<TrialReportEO> trialReportEOList = trialReportEOService.getReportEntityPage(page, searchPhrase);
            page.getPager().setRowCount(trialReportEOService.queryByCount(page));
            return Result.success("0", "查询成功!", getPageInfo(page.getPager(), trialReportEOList));
        } catch (Exception e) {
            return Result.error("-1", e.getMessage());
        }
    }

	@BusinessLog(description = "pc试验执行-编辑报告")
    @ApiOperation("|PcReportEO|新增报告")
    @PostMapping("/save")
    @RequiresPermissions("pcreporteo:pcreporteo:save")
    public ResponseMessage<TrialReportEO> save(TrialReportEO reportEO, MultipartFile fileReport, MultipartFile file) {
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
            reportEO = pcReportEOService.save(reportEO, fileReport, file);
            return Result.success("0", "新增报告成功!", reportEO);
        } catch (Exception e) {
        	logger.error("-1",e.getMessage());
            return Result.error("-1", "新增报告失败!");
        }
    }

	@BusinessLog(description = "pc试验执行-删除报告")
    @ApiOperation("|PcReportEO|删除报告")
    @PutMapping("/delReport")
    @RequiresPermissions("pcreporteo:pcreporteo:delReport")
    public ResponseMessage<PageInfo<TrialReportEO>> delReport(String reportId) {
        try {
            pcReportEOService.delReport(reportId);
            return Result.success("0", "删除报告成功");
        } catch (Exception e) {
            return Result.error("-1", e.getMessage());
        }
    }

	@BusinessLog(description = "pc试验执行-删除报告")
    @ApiOperation("报告审批")
    @PutMapping("/approvalReport")
    @RequiresPermissions("pcreporteo:pcreporteo:approvalReport")
	@EnableAccess
    public ResponseMessage<String> approvalReport(HttpServletRequest request, @RequestBody RequestEO requestEO){
        try {
            pcReportEOService.approvalReport(request, requestEO);
            return Result.success("0","审批成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.error("-1","审批失败!");
        }
    }

    
    /**
     * pageoffice 在线打开Word文档(逻辑改为：pageOffice里只有保存操作，保存之后在外面提交，提交时选择下一节点审批人。by xwb)
    * @Title：openPCReportWord
    * @param reportId
    * @param request
    * @param map
    * @return
    * @throws Exception
    * @return: ModelAndView
    * @author： ljy  
    * @date： 2019年10月31日
     */
    @ApiOperation(value = "|试验报告-pageoffice在线打开文档")
    @BusinessLog(description = "试验报告-pageoffice在线打开文档")
    @GetMapping(path = "/openPCReportWord")
	public ModelAndView openPCReportWord(String reportId, HttpServletRequest request, Map<String,Object> map) throws Exception{
    	TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportId);
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
    * @date： 2019年10月31日
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
		TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportId);
		
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
            trialReportEOService.updateByPrimaryKeySelective(trialReportEO);
		} else {
			// 保存word
			fs.saveToFile(filepath + filePath);
			fs.close();
		}
		
	}
    
    @ApiOperation(value = "试验报告-提交流程或退回重新提交")
    @BusinessLog(description = "试验报告-提交流程或退回重新提交")
    @PostMapping("/submitReport")
    @RequiresPermissions("pcreporteo:pcreporteo:submitReport")
    public ResponseMessage submitReport(@RequestBody ReportSubmitVO reportSubmitVO, HttpServletRequest request) {
    	try {
    		//报告实体类
    		TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportSubmitVO.getReportId());
    		
    		//将报告涉及的附件保存至报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)
            reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA_PDF, 
            		trialReportEO.getReportNo(), trialReportEO.getPdfFileid(), trialReportEO);
    		
    		//根据baseBus是否为空判断是流程发起还是退回、撤回后重新提交
    		BaseBusEO baseBus = trialReportEOService.getBaseBusByBusinessId(reportSubmitVO.getReportId());
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
		      	approvalReport(request, requestEO);
    		}else {
    			//启动发动机报告流程
    			String userId = UserUtils.getUserId();
		      	trialReportEOService.startReportEntityProcess(trialReportEO, userId, reportSubmitVO.getReportType(), reportSubmitVO.getNextAssignee());
    		}
	        return Result.success("0", "流程提交成功!");
        }catch(Exception e){
	        logger.error(e.getMessage(), e);
	        return Result.error("-1","流程提交失败！");
        }
    }
    
	@ApiOperation(value = "|任务执行--进度")
	@BusinessLog(description = "任务执行--进度")
	@PostMapping("/schedule")
	@RequiresPermissions("pcreporteo:pcreporteo:schedule")
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
			pcReportEOService.createScedule(trialScheduleDto);
			return Result.success("0", "保存成功");
		}catch(Exception e){
			logger.error(e.getMessage());
			return Result.error("-1","保存失败！");
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
	/**
	 * 获取报告列表数据
	 * 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "|完成报告分页查询")
    @GetMapping("getList")
	public ResponseMessage<PageInfo<ReportPage>> getEndOfReports(HttpServletRequest request,ReportPage page,String TaskId,String trialtaskInsproid,
			@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
		try {
				List<ReportPage> rows = pcReportEOService.getReportPage(page, searchPhrase);
				for (ReportPage reportPage : rows) {
					reportPage.setTrialTaskId(TaskId);
					reportPage.setTrialtaskInsproid(trialtaskInsproid);
				}
				Integer count = pcReportEOService.queryByCount(page);
	            page.getPager().setRowCount(count);
	            return Result.success("0", "查询成功!", beanMapper.mapPage(getPageInfo(page.getPager(), rows), ReportPage.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
	}
	/**
	 * 试验报告列表超链接查看报告
	 * @param reportId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "|试验报告查看")
    @GetMapping(path = "/viewReportWord")
	public ModelAndView openReportWord(String reportId,HttpServletRequest request,Map<String,Object> map) throws Exception{
    	TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportId);
    	//获取文档路径
    	TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid());
		PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
		poCtrl.setServerPage("/poserver.zz");//设置服务页面
		poCtrl.webOpen("file://" + filepath + attachmentEO.getSavePath(),
				OpenModeType.docAdmin, "");
		map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
		ModelAndView mv = new ModelAndView("EVWord");
		return mv;
	}
	/**
	 * 引用报告
	 * @param reportEO
	 * @param fileReport
	 * @param file
	 * @return
	 */
	@ApiOperation("|quoteReportEO|引用报告")
    @PostMapping("/quote")
    public ResponseMessage<TrialReportEO> quote(String reportId,String taskId,String trialtaskInsproid) {
        try {
        	
            pcReportEOService.quoteReport(reportId,taskId,trialtaskInsproid);
            return Result.success("0", "引用报告成功!");
        } catch (Exception e) {
        	logger.error("-1",e.getMessage());
            return Result.error("-1", "引用报告失败!");
        }
    }
}
