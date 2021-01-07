package com.adc.da.trial_execute.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adc.da.trial_execute.vo.AttachmentVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.trial_execute.dao.TrialSpotCheckEODao;
import com.adc.da.trial_execute.entity.TrialSpotCheckEO;
import com.adc.da.trial_execute.page.TrialSpotCheckEOPage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 试验点检
 * @author Administrator
 * @date 2019年9月11日
 */

@Service("trialSpotCheckEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialSpotCheckEOService extends BaseService<TrialSpotCheckEO, String> {
	@Autowired
	private TrialSpotCheckEODao trialSpotCheckEODao;
	
	public TrialSpotCheckEODao getDao() {
		return trialSpotCheckEODao;
	}
	
    @Autowired
    private LimsFileService limsFileService;
    
    @Value("${file.path}")
    private String filepath;
    
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
	
	/**
	 * 试验点检-分页查询
	* @Title：page
	* @param page
	* @return
	* @return: List<TrialSpotCheckEO>
	* @author： ljy  
	* @date： 2019年9月11日
	 */
	public List<TrialSpotCheckEO> page(TrialSpotCheckEOPage page){
		//查询
		List<TrialSpotCheckEO> trialSpotCheckEOS = trialSpotCheckEODao.queryByPage(page);
		for (TrialSpotCheckEO trialSpotCheckEO : trialSpotCheckEOS){
			if (trialSpotCheckEO.getFileId() != null){
				String[] fileID = trialSpotCheckEO.getFileId().split(",");
				List<String> fileIDList = Arrays.asList(fileID);
				List<AttachmentVO>  attachmentVOS = trialSpotCheckEODao.selectAttachVO(fileIDList);
				trialSpotCheckEO.setAttachmentVOList(attachmentVOS);
			}
		}
		return trialSpotCheckEOS;
	}
	
	
	/**
	 * 试验点检-详情
	* @Title：getDetailById
	* @param id
	* @return
	* @return: TrialSpotCheckEO
	* @author： ljy  
	* @date： 2019年9月30日
	 */
	public TrialSpotCheckEO getDetailById(String id) throws ParseException {
		//查询
		TrialSpotCheckEO trialSpotCheck = trialSpotCheckEODao.selectByPrimaryKey(id);
		String[] fileID = trialSpotCheck.getFileId().split(",");
		List<String> fileIDList = Arrays.asList(fileID);
		trialSpotCheck.setFileIdArray(fileIDList);
		TrialSpotCheckEO trialSpotCheckEO = trialSpotCheckEODao.selectByTrialSpotCheckEO(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(trialSpotCheck.getCreateTime());
        trialSpotCheckEO.setCreateTime(simpleDateFormat.format(date));
		List<AttachmentVO>  attachmentVOS = trialSpotCheckEODao.selectAttachVO(fileIDList);
		trialSpotCheckEO.setAttachmentVOList(attachmentVOS);
		return trialSpotCheckEO;
	}
	
	
	/**
	 * 试验点检-新增
	* @Title：save
	* @param trialSpotCheckEO
	* @return
	* @throws Exception
	* @return: TrialSpotCheckEO
	* @author： ljy  
	* @date： 2019年9月11日
	 */
	public TrialSpotCheckEO save(TrialSpotCheckEO trialSpotCheckEO) throws Exception {
		//设置UUID
		trialSpotCheckEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		trialSpotCheckEO.setDelFlag("0");
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialSpotCheckEO.setCreateBy(UserUtils.getUserId());
		trialSpotCheckEO.setCreateTime(date);
		trialSpotCheckEO.setUpdateBy(UserUtils.getUserId());
		trialSpotCheckEO.setUpdateTime(date);

		/*//保存附件
		StringBuilder fileIDString = new StringBuilder();
		for (MultipartFile file : files){
			fileIDString.append(limsFileService.saveAttachment(file));
			fileIDString.append(",");
		}
	    //返回附件id
		String fileId = fileIDString.substring(0,fileIDString.length()-1);*/

		//保存附件
		//String fileId = limsFileService.saveAttachment(file);
		//返回附件id
		//trialSpotCheckEO.setFileId(fileId);

		//新增保存
		trialSpotCheckEODao.insert(trialSpotCheckEO);
		return trialSpotCheckEO;
	}
	
	/**
	 * 试验点检-编辑
	* @Title：edit
	* @param trialSpotCheckEO
	* @return
	* @throws Exception
	* @return: TrialSpotCheckEO
	* @author： ljy  
	* @date： 2019年9月11日
	 */
	public TrialSpotCheckEO edit(TrialSpotCheckEO trialSpotCheckEO) throws Exception {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialSpotCheckEO.setUpdateBy(UserUtils.getUserId());
		trialSpotCheckEO.setUpdateTime(date);
		/*//原始文件删除
			//找出原来文件的附件ID字符串
			TrialSpotCheckEO trialSpotCheck = trialSpotCheckEODao.selectByPrimaryKey(trialSpotCheckEO.getId());
			String attachIDs = trialSpotCheck.getFileId();
			String[] attachIDArray = null;
			if (attachIDs != null){
				attachIDArray = attachIDs.split(",");
			}
			if (attachIDArray != null && attachIDArray.length !=0){
				for (String attachID : attachIDArray){
					TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(attachID);
					//附件表删除
					tsAttachmentEODao.deleteByPrimaryKey(attachID);
					//真正文件删除
					FileUtil.deleteFile(filepath + attachmentEO.getSavePath());
				}
			}*/

		//更新保存
		trialSpotCheckEODao.updateByPrimaryKeySelective(trialSpotCheckEO);
		return trialSpotCheckEO;
	}
	
	/**
	 * 点检记录导出
	* @Title：exportTrialSpotCheck
	* @param response
	* @param request
	* @param page
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年9月12日
	 */
	public void exportTrialSpotCheck(HttpServletResponse response,
			HttpServletRequest request, TrialSpotCheckEOPage page) throws IOException {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		createTitle(workbook, sheet);
        //填充数据
		createTrialSpotCheckData(workbook, sheet, page);
        //生成Excel文件名称
        String fileName = ConstantUtils.TRIAL_SPOTCHECK_EXPORT;
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_EXTEND_XLSX;
        //浏览器下载
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        response.setContentType("application/octet-stream");
        //火狐浏览器需特殊处理
        if(agent.contains(ConstantUtils.FIREFOX)) {
        	 response.setCharacterEncoding("utf-8");
             response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + fileExtend);
        }else {
        	response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName) + fileExtend);
        }
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
	}
	
	
	/**
	 * 填充数据
	* @Title：createTrialSpotCheckData
	* @param workbook
	* @param sheet
	* @param type
	* @return: void
	* @author： ljy  
	* @date： 2019年9月12日
	 */
	public void createTrialSpotCheckData(Workbook workbook, Sheet sheet, TrialSpotCheckEOPage page) {
		//填充数据,查询所有数据
		List<TrialSpotCheckEO> rows = trialSpotCheckEODao.findByList(page);
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(TrialSpotCheckEO trialSpotCheckEO : rows) {
        	Row rowAdd = sheet.createRow(rowNum);
        	//点检人
        	rowAdd.createCell(0).setCellValue(trialSpotCheckEO.getCreateName());
        	//点检时间
        	rowAdd.createCell(1).setCellValue(trialSpotCheckEO.getCreateTime());
        	//点检类型
        	String typeValue = "";
        	switch (trialSpotCheckEO.getType()) {
			case "1":
				//台架搭建点检
				typeValue = ConstantUtils.TRIAL_SPOTCHECK_TYPE_BENCH;
				break;
			case "2":
				//试验启动前点检
				typeValue = ConstantUtils.TRIAL_SPOTCHECK_TYPE_STARTUP;
				break;
			case "3":
				//日常点检
				typeValue = ConstantUtils.TRIAL_SPOTCHECK_TYPE_DAILY;
				break;
			default:
				break;
			}
        	//点检类型
        	rowAdd.createCell(2).setCellValue(typeValue);
        	//继续创建下一行
        	rowNum ++;
        }
	}
	
	
	/**
	 * 设置标题栏
	* @Title：createTitle
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年9月12日
	 */
	public void createTitle(Workbook workbook, Sheet sheet) {
		//标题栏
		Row row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,50*256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第一个 点检人
        cell = row.createCell(0);
        cell.setCellValue(ConstantUtils.TRIAL_SPOTCHECK_USER);
        cell.setCellStyle(style);
        //第二个 点检时间
        cell = row.createCell(1);
        cell.setCellValue(ConstantUtils.TRIAL_SPOTCHECK_DATA);
        cell.setCellStyle(style);
        //第三个 点检类型
        cell = row.createCell(2);
        cell.setCellValue(ConstantUtils.TRIAL_SPOTCHECK_TYPE);
        cell.setCellStyle(style);
	}
	
}
