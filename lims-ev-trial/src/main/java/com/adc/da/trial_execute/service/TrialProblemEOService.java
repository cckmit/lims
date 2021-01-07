package com.adc.da.trial_execute.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.trial_execute.dao.TrialProblemEODao;
import com.adc.da.trial_execute.entity.TrialProblemEO;
import com.adc.da.trial_execute.page.TrialProblemEOPage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.UUID;

/**
 * 试验问题记录
 * @author Administrator
 * @date 2019年9月18日
 */
@Service("trialProblemEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialProblemEOService extends BaseService<TrialProblemEO, String> {

	@Autowired
	private TrialProblemEODao trialProblemEODao;
	
	
	@Autowired
	private DicTypeEOService dicTypeEOService;
	
	public TrialProblemEODao getDao() {
		return trialProblemEODao;
	}
	
	/**
	 * 试验问题记录-分页查询
	* @Title：page
	* @param page
	* @return
	* @return: List<TrialProblemEO>
	* @author： ljy  
	* @date： 2019年9月18日
	 */
	public List<TrialProblemEO> page(TrialProblemEOPage page){
		//查询
		return trialProblemEODao.queryByPage(page);
	}
	
	
	/**
	 * 试验问题记录-新增
	* @Title：save
	* @param trialProblemEO
	* @return
	* @return: TrialProblemEO
	* @author： ljy  
	* @date： 2019年9月18日
	 */
	public TrialProblemEO save(TrialProblemEO trialProblemEO) {
		//设置UUID
		trialProblemEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		trialProblemEO.setDelFlag("0");
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialProblemEO.setCreateBy(UserUtils.getUserId());
		trialProblemEO.setCreateTime(date);
		trialProblemEO.setUpdateBy(UserUtils.getUserId());
		trialProblemEO.setUpdateTime(date);
		//保存
		trialProblemEODao.insert(trialProblemEO);
		return trialProblemEO;
	}
	
	
	/**
	 * 试验问题记录-编辑
	* @Title：edit
	* @param trialProblemEO
	* @return
	* @return: TrialProblemEO
	* @author： ljy  
	* @date： 2019年9月18日
	 */
	public TrialProblemEO edit(TrialProblemEO trialProblemEO) {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialProblemEO.setUpdateBy(UserUtils.getUserId());
		trialProblemEO.setUpdateTime(date);
		//更新
		trialProblemEODao.updateByPrimaryKeySelective(trialProblemEO);
		return trialProblemEO;
	}
	
	/**
	 * 试验问题记录-导出
	* @Title：exportTrialProblem
	* @param page
	* @param response
	* @param request
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年9月19日
	 */
	public void exportTrialProblem(TrialProblemEOPage page,
    		HttpServletResponse response, HttpServletRequest request) throws IOException {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		createTitle(workbook, sheet);
        //填充数据
		createTrialProblem(workbook, sheet, page);
		String fileName = ConstantUtils.TRIALPROBLEM_EXPORT;
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
	 * 创建标题栏
	* @Title：createTitle
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年9月19日
	 */
	public void createTitle(Workbook workbook, Sheet sheet) {
		//标题栏
		Row row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，
        sheet.setColumnWidth(1,20*256);
        // 冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);  
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //序号
        cell = row.createCell(0);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_NO);
        cell.setCellStyle(style);
        //试验任务书编号
        cell = row.createCell(1);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_EVNUMBER);
        cell.setCellStyle(style);
        //试验任务书名称
        cell = row.createCell(2);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_EVTITLE);
        cell.setCellStyle(style);
        //发动机型号
        cell = row.createCell(3);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_ENGINE_MODEL);
        cell.setCellStyle(style);
        //发动机开发阶段
        cell = row.createCell(4);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_ENGINE_DEVELOP_STAGE);
        cell.setCellStyle(style);
        //发动机编号
        cell = row.createCell(5);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_ENGINE_NUMBER);
        cell.setCellStyle(style);
        //试验项目
        cell = row.createCell(6);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_INSPRO_NAMES);
        cell.setCellStyle(style);
        //台架编号
        cell = row.createCell(7);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_ORG_NAMES);
        cell.setCellStyle(style);
        //试验负责人
        cell = row.createCell(8);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_TRIAL_ENGINEER);
        cell.setCellStyle(style);
        //试验运行时长/(小时)
        cell = row.createCell(9);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_RUN_HOURS);
        cell.setCellStyle(style);
        //出现故障日期
        cell = row.createCell(10);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_TIME);
        cell.setCellStyle(style);
        //零件使用时长/(小时)
        cell = row.createCell(11);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_PART_USE_HOURS);
        cell.setCellStyle(style);
        //故障停机时长(小时)
        cell = row.createCell(12);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_STOP_HOURS);
        cell.setCellStyle(style);
        //该故障件在本试验项目累计出现故障次数
        cell = row.createCell(13);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_NUM);
        cell.setCellStyle(style);
        //故障类型
        cell = row.createCell(14);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_TYPE);
        cell.setCellStyle(style);
        //故障总成部件名称
        cell = row.createCell(15);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_PART_NAME);
        cell.setCellStyle(style);
        //故障件图号
        cell = row.createCell(16);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_PART_PICNO);
        cell.setCellStyle(style);
        //ECU软件状态
        cell = row.createCell(17);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_ECU_STATUS);
        cell.setCellStyle(style);
        //台架搭建状态
        cell = row.createCell(18);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_SCAFFOLDING_STATUS);
        cell.setCellStyle(style);
        //故障描述
        cell = row.createCell(19);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_DESCRIPTION);
        cell.setCellStyle(style);
        //故障录入人
        cell = row.createCell(20);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_CREATEBY_NAME);
        cell.setCellStyle(style);
        //故障录入时间
        cell = row.createCell(21);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_CREATE_TIME);
        cell.setCellStyle(style);
        ///-------------------------
        //原因分析
        cell = row.createCell(22);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_REASON);
        cell.setCellStyle(style);
        //故障等级
        cell = row.createCell(23);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_FAULT_LEVEL);
        cell.setCellStyle(style);
        //临时措施及时间
        cell = row.createCell(24);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_TEMP_MEASURES);
        cell.setCellStyle(style);
        //临时措施负责人
        cell = row.createCell(25);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_TEMP_MEASURES_PERSON);
        cell.setCellStyle(style);
        //临时措施整改效果确认及时间
        cell = row.createCell(26);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_TEMP_MEASURES_RESULT);
        cell.setCellStyle(style);
        //长期解决措施及时间
        cell = row.createCell(27);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_PERM_MEASURES_DATE);
        cell.setCellStyle(style);
        //长期措施计划完成时间(日期)
        cell = row.createCell(28);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_PERM_MEASURES_FINISHDATE);
        cell.setCellStyle(style);
        //长期解决措施效果确认结果及时间(正常、无效、待确认)
        cell = row.createCell(29);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_PERM_MEASURES_RESULT);
        cell.setCellStyle(style);
        //长期措施负责人
        cell = row.createCell(30);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_PERM_MEASURES_PERSON);
        cell.setCellStyle(style);
        //是否移交横向科室
        cell = row.createCell(31);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_IS_TRANSFER);
        cell.setCellStyle(style);
        //对策责任科室
        cell = row.createCell(32);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_RESPONSIBLE_DEPART);
        cell.setCellStyle(style);
        //责任人
        cell = row.createCell(33);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_RESPONSIBLE_PERSON);
        cell.setCellStyle(style);
        //是否申请关闭
        cell = row.createCell(34);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_IS_CLOSE);
        cell.setCellStyle(style);
        //问题关闭时间
        cell = row.createCell(35);
        cell.setCellValue(ConstantUtils.TRIALPROBLEM_CLOSE_DATE);
        cell.setCellStyle(style);
        
	}
	
	
	/**
	 * 填充数据
	* @Title：createTrialProblem
	* @param workbook
	* @param sheet
	* @param page
	* @return: void
	* @author： ljy  
	* @date： 2019年9月19日
	 */
	public void createTrialProblem(Workbook workbook, Sheet sheet, TrialProblemEOPage page) {
		//填充数据,查询所有数据
		List<TrialProblemEO> rows = trialProblemEODao.findByList(page);
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(int i = 0; i < rows.size(); i++) {
        	Row rowAdd = sheet.createRow(rowNum);
        	//序号
        	rowAdd.createCell(0).setCellValue(i + 1);
        	//试验任务书编号  eVNumber
        	rowAdd.createCell(1).setCellValue(rows.get(i).geteVNumber());
        	//试验任务书名称 eVTitle
        	rowAdd.createCell(2).setCellValue(rows.get(i).geteVTitle());
        	//发动机型号  engineModel
        	rowAdd.createCell(3).setCellValue(rows.get(i).getEngineModel());
        	//发动机开发阶段  engineDevelopStage  
        	String engineDevelopStage = "";
        	List<DicTypeEO> list = dicTypeEOService.getDicTypeByDictionaryCode("engineDevelopStage");
        	for (DicTypeEO dicTypeEO : list){
        	    if (dicTypeEO.getDicTypeCode().equals(rows.get(i).getEngineDevelopStage())){
        	    	engineDevelopStage = dicTypeEO.getDicTypeName();
        	    }
        	}
        	rowAdd.createCell(4).setCellValue(engineDevelopStage);
        	//发动机编号  engineNumber
        	rowAdd.createCell(5).setCellValue(rows.get(i).getEngineNumber());
        	//试验项目  insProNames
        	rowAdd.createCell(6).setCellValue(rows.get(i).getInsProNames());
        	//台架编号   orgNames
        	rowAdd.createCell(7).setCellValue(rows.get(i).getOrgNames());
        	//试验负责人  trialEngineer
        	rowAdd.createCell(8).setCellValue(rows.get(i).getTrialEngineer());
        	//试验运行时长/(小时) runHours
        	rowAdd.createCell(9).setCellValue(rows.get(i).getRunHours());
        	//出现故障日期   faultTime
        	rowAdd.createCell(10).setCellValue(rows.get(i).getFaultTime());
        	//零件使用时长/(小时) partUseHours
        	rowAdd.createCell(11).setCellValue(rows.get(i).getPartUseHours());
        	//故障停机时长(小时) faultStopHours
        	rowAdd.createCell(12).setCellValue(rows.get(i).getFaultStopHours());
        	//该故障件在本试验项目累计出现故障次数  faultNum
        	rowAdd.createCell(13).setCellValue(rows.get(i).getFaultNum());
        	//故障类型  faultType    evFaultType
        	String evFaultType = "";
        	List<DicTypeEO> list1 = dicTypeEOService.getDicTypeByDictionaryCode("evFaultType");
        	for (DicTypeEO dicTypeEO : list1){
        	    if (dicTypeEO.getDicTypeCode().equals(rows.get(i).getFaultType())){
        	    	evFaultType = dicTypeEO.getDicTypeName();
        	    }
        	}
        	rowAdd.createCell(14).setCellValue(evFaultType);
        	//故障总成部件名称   faultPartName
        	rowAdd.createCell(15).setCellValue(rows.get(i).getFaultPartName());
        	//故障件图号  faulePartPicno
        	rowAdd.createCell(16).setCellValue(rows.get(i).getFaulePartPicno());
        	//ECU软件状态  ECUStatus
        	rowAdd.createCell(17).setCellValue(rows.get(i).getECUStatus());
        	//台架搭建状态 scaffoldingStatus
        	rowAdd.createCell(18).setCellValue(rows.get(i).getScaffoldingStatus());
        	//故障描述  faultDescription
        	rowAdd.createCell(19).setCellValue(rows.get(i).getFaultDescription());
        	//故障录入人 createByName
        	rowAdd.createCell(20).setCellValue(rows.get(i).getCreateByName());
        	//故障录入时间  createTime
        	rowAdd.createCell(21).setCellValue(rows.get(i).getCreateTime());
        	//继续创建下一行
        	rowNum ++;
        }
	}

	public void updatePicture(String id,String pictureName,String pictureId){
        TrialProblemEO trialProblemEO = new TrialProblemEO();
        trialProblemEO.setId(id);
        trialProblemEO.setPictureName(pictureName);
        trialProblemEO.setPictureId(pictureId);
        trialProblemEODao.updatePicture(trialProblemEO);
    }
	
	
}



