package com.adc.da.trial_report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PDFStampUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.seal.dao.BmSealEODao;
import com.adc.da.seal.entity.BmSealEO;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.util.DocWriterUtils;
import com.adc.da.trial_report.dao.TrialReportEODao;
import com.adc.da.trial_report.entity.ReportArchivefileEO;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.page.TrialReportEOPage;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPaddings;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfBrushes;
import com.spire.pdf.graphics.PdfImage;
import com.spire.pdf.graphics.PdfTrueTypeFont;
import com.spire.pdf.grid.PdfGrid;

/**
 * 试验管理-试验报告模块
 *
 * @author Administrator
 * @date 2019年8月20日
 */
@Service("trialReportEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialReportEOService extends BaseService<TrialReportEO, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrialReportEOService.class);

    @Autowired
    private TrialReportEODao trialReportEODao;

    public TrialReportEODao getDao() {
        return trialReportEODao;
    }

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private DicTypeEODao dicTypeEODao;
    
    @Autowired
    private LimsFileService limsFileService;

    @Value("${mail.address}")
    private String mailAddr;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;

    @Autowired
    private BmSealEODao bmSealEODao;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private TrialReportEOService trialReportEOService;
    
    @Autowired
    private UserEOService userEOService;
    
    @Autowired
    private OrgEOService orgEOService;

    @Value("${watermark.name}")
    private String waterMarkName;


    /**
     * 发动机试验报告分页查询
     *
     * @param page
     * @param searchPhrase
     * @return
     * @throws Exception
     * @Title：page
     * @return: List<TrialReportEO>
     * @author： ljy
     * @date： 2019年8月28日
     */
    public List<TrialReportEO> getReportEntityPage(TrialReportEOPage page, String searchPhrase) throws Exception {
        //通用查询的参数不为空即为通用查询
        if (StringUtils.isNotEmpty(searchPhrase) &&
                StringUtils.isNotEmpty(searchPhrase.trim())) {
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
        if (roleIds.contains(ConstantUtils.EV_TRIALENGINEER_ROLEID) ||
        		roleIds.contains(ConstantUtils.PV_TRIALENGINEER_ROLEID) ||	
        		roleIds.contains(ConstantUtils.CV_TRIALENGINEER_ROLEID)) {
            page.setCreateBy(UserUtils.getUserId());
        }
        //如果当前登录用户为部长/科长/主管/试验工程师管理员, 则需过滤数据
        if (roleIds.contains(ConstantUtils.EV_MINISTER_ROLEID) ||
                roleIds.contains(ConstantUtils.EV_SECTIONCHIEF_ROLEID) ||
                roleIds.contains(ConstantUtils.EV_CHARGE_ROLEID) ||
                roleIds.contains(ConstantUtils.EV_TASKMANAGER_ROLEID) ||
                
                roleIds.contains(ConstantUtils.PV_MINISTER_ROLEID) ||
                roleIds.contains(ConstantUtils.PV_SECTIONCHIEF_ROLEID) ||
                roleIds.contains(ConstantUtils.PV_CHARGE_ROLEID) ||
                
                roleIds.contains(ConstantUtils.CV_MINISTER_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_SECTIONCHIEF_ROLEID) ||
                roleIds.contains(ConstantUtils.CV_CHARGE_ROLEID)) {
            List<String> orgIds = userEODao.getOrgIdListByUserId(UserUtils.getUserId());
            if (CollectionUtils.isNotEmpty(orgIds)) {
                List<String> userIds = limsFileService.getUserIdsByOrgId(orgIds.get(0));
                page.setCreateByIds(userIds);
            }
        }
        //查询
        return trialReportEODao.queryByPage(page);
    }


    /**
     * 试验报告保存
     *
     * @param trialReportEO
     * @param fileReport
     * @param --filePDF
     * @return
     * @throws Exception
     * @Title：save
     * @return: TrialReportEO
     * @author： ljy
     * @date： 2019年8月20日
     */
    public TrialReportEO saveReportEntity(TrialReportEO trialReportEO,
                              MultipartFile fileReport, MultipartFile file) throws Exception {
        //设置UUID
        trialReportEO.setId(UUID.randomUUID());
        //删除标记  0 未删除;  1删除
        trialReportEO.setDelFlag("0");
        //设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        trialReportEO.setCreateBy(UserUtils.getUserId());
        trialReportEO.setCreateTime(date);
        trialReportEO.setUpdateBy(UserUtils.getUserId());
        trialReportEO.setUpdateTime(date);
        //报告归档默认0-未归档
        trialReportEO.setArchiveStatus("0");
        //报告状态默认 0-无报告
        trialReportEO.setReportStatus("0");
        //报告编号
        //trialReportEO.setReportNo(getReportNo());
        //文件上传
        if (StringUtils.isNotEmpty(fileReport) && fileReport.getSize() > 0) {
            //保存附件
            String fileReportId = limsFileService.saveAttachment(fileReport);
            //返回附件id
            trialReportEO.setReportFileid(fileReportId);
            //报告状态默认 1-编辑报告
            trialReportEO.setReportStatus("1");
        }
        //返回的附件id
        String fileId = "";
        if (StringUtils.isNotEmpty(file) && file.getSize() > 0) {
            //保存附件
            fileId = limsFileService.saveAttachment(file);
            //返回附件id
            trialReportEO.setAccessoryFileid(fileId);
        }
        //保存
        trialReportEODao.insert(trialReportEO);
        return trialReportEO;
    }


    /**
     * 试验报告编辑
     *
     * @param trialReportEO
     * @param fileReport
     * @param file
     * @return
     * @throws Exception
     * @Title：edit
     * @return: TrialReportEO
     * @author： ljy
     * @date： 2019年8月20日
     */
    public TrialReportEO editReportEntity(TrialReportEO trialReportEO,
                              MultipartFile fileReport, MultipartFile file) throws Exception {
        //设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        trialReportEO.setUpdateBy(UserUtils.getUserId());
        trialReportEO.setUpdateTime(date);
        //报告状态默认 0-无报告
        trialReportEO.setReportStatus("0");
        //返回检验项目名称集合,用于通用查询
        //文件上传
        if (StringUtils.isNotEmpty(fileReport) && fileReport.getSize() > 0) {
            //原始文件删除
            if (StringUtils.isNotEmpty(trialReportEO.getReportFileid())) {
                TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid());
                //附件表删除
                tsAttachmentEODao.deleteByPrimaryKey(trialReportEO.getReportFileid());
                //真正文件删除
                FileUtil.deleteFile(filepath + attachmentEO.getSavePath());
            }
            //保存附件
            String fileReportId = limsFileService.saveAttachment(fileReport);
            //返回附件id
            trialReportEO.setReportFileid(fileReportId);
            //报告状态默认 1-编辑报告
            trialReportEO.setReportStatus("1");
        }
        //返回附件id
        String fileId = "";
        if (StringUtils.isNotEmpty(file) && file.getSize() > 0) {
            //原始文件删除
            if (StringUtils.isNotEmpty(trialReportEO.getAccessoryFileid())) {
                TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getAccessoryFileid());
                //附件表删除
                tsAttachmentEODao.deleteByPrimaryKey(trialReportEO.getAccessoryFileid());
                //真正文件删除
                FileUtil.deleteFile(filepath + attachmentEO.getSavePath());
                //将报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)中的附件删除
                reportArchivefileEOService.deleteByReportId(trialReportEO.getId());
            }
            //保存附件
            fileId = limsFileService.saveAttachment(file);
            //返回附件id
            trialReportEO.setAccessoryFileid(fileId);
        }
        //保存
        trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
        return trialReportEO;
    }

    /**
     * 删除报告
     *
     * @param id 报告id
     * @Title：deleteReport
     * @return: void
     * @author： ljy
     * @date： 2019年8月26日
     */
    public void deleteReportEntity(String id) {
        //根据id获取报告信息
        TrialReportEO trialReportEO = trialReportEODao.selectByPrimaryKey(id);
        //查询报告模板信息
        TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid());
        //附件表删除
        tsAttachmentEODao.deleteByPrimaryKey(trialReportEO.getReportFileid());
        //真正文件删除
        FileUtil.deleteFile(filepath + attachmentEO.getSavePath());
        trialReportEO.setReportFileid(null);
        //删除附件信息
        if (StringUtils.isNotEmpty(trialReportEO.getAccessoryFileid())) {
            TsAttachmentEO accessoryFile = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getAccessoryFileid());
            //附件表删除
            tsAttachmentEODao.deleteByPrimaryKey(trialReportEO.getAccessoryFileid());
            //真正文件删除
            FileUtil.deleteFile(filepath + accessoryFile.getSavePath());
            trialReportEO.setAccessoryFileid(null);
            //将报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)中的附件删除
            reportArchivefileEOService.deleteByReportId(id);
        }
        //更新报告信息
        //报告状态默认 0-无报告
        trialReportEO.setReportStatus("0");
        trialReportEO.setDelFlag("1");
        trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
    }

    /**
     * PDF加签章
     * @Title：PDFAddStamp
     * @param path
     * @param waterMarkPath
     * @return: void
     * @author： ljy
     * @date： 2019年9月4日
     * @throws Exception
     */
    public void PDFAddStamp(String path, String waterMarkPath) throws Exception {
        //获取公章
        //BmSealEO seal = bmSealEODao.findSealCode(ConstantUtils.EV_REPORT_STAMP);
        //if(StringUtils.isNotEmpty(seal)) {
//            String stampPath = filepath + seal.getSealStyle();
            //float x = Float.parseFloat(seal.getSealAxesx());
            //float y = Float.parseFloat(seal.getSealAxesy());
            //加签章
            //PDFStampUtil.PDFAddStamp(filepath + path, stampPath, x, y);
            //PDFStampUtil.PDFAddStamp(filepath + waterMarkPath, stampPath, x, y);
            //原PDF加水印
            PDFStampUtil.waterMark(filepath + path, filepath + waterMarkPath, waterMarkName);
        //}
    }

    /**
     * 启动报告流程
     *
     * @param
     * @param userId
     * @throws Exception
     * @Title：startReportProcess
     * @return: void
     * @author： ljy
     * @date： 2019年8月26日
     */
    public void startReportEntityProcess(TrialReportEO trialReportEO, String userId, String reportType, String nextAssignee) throws Exception {
        /**
         * businessId : 业务id
         * businessKey : BASEBUS保存后产成的主键id
         * ProcessKey : 数据字典的key-->ProcessKey
         * businessType : 数据字典 自定义的类型 --> EV_Report
         */
    	String businessType = "";
    	if(ConstantUtils.EV.equalsIgnoreCase(reportType)) {
    		businessType = ConstantUtils.EV_REPORT_BUSINESS_TYPE;
    	}else {
    		UserEO curUser = userEOService.getUserWithRoles(userId);
            OrgEO curOrg = curUser.getOrgEOList().get(0);
            String flag = orgEOService.getByOrgId(curOrg.getId());
            if("1".equals(flag)){
                //PV
                businessType = ConstantUtils.PV_REPORT_BUSINESS_TYPE;
            }else{
                //CV
            	businessType = ConstantUtils.CV_REPORT_BUSINESS_TYPE;
            }
    	}
        //保存业务BASEBUS
        String title = "报告【" + trialReportEO.getReportNo() + "】审批流程";
        String baseBusId = limsFileService.saveBaseBus(trialReportEO.getId(),
        		businessType, title);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(userId);
        processInstanceVO.setBusinessKey(baseBusId);
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, businessType);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        
        List<RestVariable> variables = new ArrayList<>();
        //设置下一节点审批人
        if(StringUtils.isNotEmpty(nextAssignee)) {    		
        	RestVariable rv = new RestVariable();
        	rv.setName("zg");
        	rv.setValue(nextAssignee);
        	variables.add(rv);
    	}
        //设置组织机构
        String orgId = userEODao.getOrgIdByUserId(userId);
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
        
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //6-待校对
        trialReportEO.setReportStatus("6");
        //更新者-->后期将作为发起人在报告查询列表中显示
        trialReportEO.setUpdateBy(userId);
        //当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        //更新时间-->后期将作为试验结束时间在报告查询列表中显示
        trialReportEO.setUpdateTime(date);
        //更新报告状态
        trialReportEODao.updateByPrimaryKeySelective(trialReportEO);

        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            String link = ConstantUtils.EV_REPORT_BUSINESS_TYPE;
            limsFileService.processSendMessages(splits, title, link, trialReportEO.getId());
        }

    }



    /**
     * 根据业务id 获取PDF路径
     *
     * @param businessId
     * @return
     * @Title：getPDFPath
     * @return: String
     * @author： ljy
     * @date： 2019年9月20日
     */
    public String getReportPDFPath(String businessId, String isWaterMark) {
        //定义返回值
        String pdfPath = "";
        //根据业务id 获取信息
        TrialReportEO report = trialReportEODao.selectByPrimaryKey(businessId);
        if (StringUtils.isNotEmpty(report) && StringUtils.isNotEmpty(StringUtils.isNotEmpty(report))) {
            //获取附件信息
            TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(report.getPdfFileid());
            if (StringUtils.isNotEmpty(attach) && ConstantUtils.NO.equals(isWaterMark)) {
                //返回路径
                pdfPath = attach.getSavePath();
            } else if (StringUtils.isNotEmpty(attach) && ConstantUtils.YES.equals(isWaterMark)) {
                //返回路径
                pdfPath = attach.getWaterMarkPath();
            }
        }
        return pdfPath;
    }


    public ResponseMessage delTrialData(String archiveFileId) {
        try {
            ReportArchivefileEO reportArchivefileEO = reportArchivefileEOService.selectByPrimaryKey(archiveFileId);
            if (reportArchivefileEO != null) {
                // 删除服务器文件，先查询附件信息
                TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(reportArchivefileEO.getReportFileid());
                if (eo != null) {
                    String tempPath = this.filepath;
                    if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                        tempPath = this.filepath + File.separator;
                    }
                    String fullPath = tempPath + eo.getSavePath();
                    File file = new File(fullPath);
                    file.delete();
                }
                // 通过ID删除EV_TRIALREPORT_ARCHIVEFILE数据
                reportArchivefileEOService.deleteByPrimaryKey(archiveFileId);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.success("-1", "删除失败");
        }
    }
    
    /**
     * 通过businessId获取baseBus
     * @param businessId
     * @return
     */
    public BaseBusEO getBaseBusByBusinessId(String businessId){
    	List<BaseBusEO> baseBusEOList = baseBusEODao.selectByBusinessId(businessId);
        return CollectionUtils.isNotEmpty(baseBusEOList) ? baseBusEOList.get(0) : null;
    }

    /**
     * ev、pv、cv报告流程审批结束后添加审批人签章：节点名称-审批人名称-审批人签章
     * @param baseBusId
     * @throws Exception 
     */
	public void addAssigneeStamp(String businessKey) throws Exception {
		//节点信息
		List<ActivitiTaskVO> taskList = getAgreeTaskList(businessKey);
		BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(businessKey);
		
		//获取报告文件路径
		TrialReportEO trialReportEO = this.selectByPrimaryKey(baseBusEO.getBusinessId());
		TsAttachmentEO reportFile = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
		String filePath = filepath + reportFile.getWaterMarkPath();
		filePath = filePath.replaceAll("\\\\", "/");
		
		//字体
		BaseFont baseFont = BaseFont.createFont("/font/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font font = new Font(baseFont, 15f, Font.BOLD);
		
		//创建新的pdf文件，在里面创建表格，然后将报告文件和这个pdf文件进行合并
		String tampFilePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + trialReportEO.getId() + "_temp.pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(tampFilePath));
		document.open();
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(80);
		table.getDefaultCell().setBorder(0);
		
		for (int aw = 0; aw < taskList.size(); aw++) {
			//节点名称
			String taskName = taskList.get(aw).getName();
			PdfPCell cell = new PdfPCell(new Paragraph(taskName, font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.GRAY);
			table.addCell(cell);
			//审批人姓名
			String assigneeName = taskList.get(aw).getAssigneeName();
			cell = new PdfPCell(new Paragraph(assigneeName, font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.GRAY);
			table.addCell(cell);
			//审批人签章
			String imagePath = "";
			UserEO userEO = userEODao.selectByPrimaryKey(taskList.get(aw).getAssignee());
			if(StringUtils.isNotEmpty(userEO.getSeal())){
				TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(userEO.getSeal());
				if (StringUtils.isNotEmpty(attach)) {
					imagePath = filepath + attach.getSavePath();
					imagePath = imagePath.replaceAll("\\\\", "/");
				}
			}
			if(StringUtils.isNotEmpty(imagePath)){				
				Image image = Image.getInstance(imagePath);
				image.scaleToFit(80, 40);
				cell = new PdfPCell(image);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderColor(BaseColor.GRAY);
				cell.setPadding(5);
				table.addCell(cell);
			}else{
				cell = new PdfPCell(new Paragraph("", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderColor(BaseColor.GRAY);
				cell.setPadding(5);
				table.addCell(cell);
			}
		}
		document.add(table);
		document.close();
		
		//添加水印
		String waterFilePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + trialReportEO.getId() + "_wm.pdf";
		PDFStampUtil.waterMark(tampFilePath, waterFilePath, waterMarkName);
		
		//将报告文件和刚创建的表格文件合并
		//合并后的文件
		String mergeFilePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + trialReportEO.getId() + "_merge.pdf";
		Document doc = new Document();
		PdfCopy copy = new PdfCopy(doc, new FileOutputStream(mergeFilePath));
		doc.open();
		PdfReader reader1 = new PdfReader(filePath);
		PdfReader reader2 = new PdfReader(waterFilePath);
		copy.addDocument(reader1);
		copy.addDocument(reader2);
		doc.close();
		reader1.close();
		reader2.close();
		//删除报告文件、表格文件、水印文件
		new File(filePath).delete();
		new File(tampFilePath).delete();
		new File(waterFilePath).delete();
		//将合并后的文件重命名为报告文件
		new File(mergeFilePath).renameTo(new File(filePath));
	}

	public List<ActivitiTaskVO> getAgreeTaskList(String businessKey){
		List<ActivitiTaskVO> tasks = activitiTaskService.getProcessRecords(null, businessKey);
		List<ActivitiTaskVO> result = new ArrayList<>();
		for(int i = 2; i >= 0; i--){
			result.add(tasks.get(i));
		}
		return result;
	}
	
}