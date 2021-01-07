package com.adc.da.trial_execute.service;


import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.trial_execute.dao.TrialDataDetailEODao;
import com.adc.da.trial_execute.entity.TrialDataDetailEO;
import com.adc.da.trial_execute.page.TrialDataDetailEOPage;
import com.adc.da.util.utils.Encodes;

/**
 * 试验数据记录
 * @author Administrator
 * @date 2019年9月16日
 */
@Service("trialDataDetailEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialDataDetailEOService extends BaseService<TrialDataDetailEO, String> {
	@Autowired
	private TrialDataDetailEODao trialDataDetailEODao;
	
	public TrialDataDetailEODao getDao() {
		return trialDataDetailEODao;
	}
	
	/**
	 * 试验数据详情-分页查询
	* @Title：page
	* @param page
	* @return
	* @return: List<TrialDataDetailEO>
	* @author： ljy  
	* @date： 2019年9月16日
	 */
	public List<TrialDataDetailEO> page(TrialDataDetailEOPage page){
		//查询
		return trialDataDetailEODao.queryByPage(page);
	}

	/**
	 *  试验数据详情-导出
	* @Title：exportTrialDataDetail
	* @param map
	* @return: void
	* @author： ljy  
	* @date： 2019年9月16日
	 * @throws Exception 
	 */
	public void exportTrialDataDetail(TrialDataDetailEOPage page,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		createTitle(workbook, sheet);
        //填充数据
		createTrialDataDetail(workbook, sheet, page);
        //生成Excel文件名称
        String fileName = ConstantUtils.TRIALDATA_EXPORT;
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
	* @Title：createTrialDataDetail
	* @param workbook
	* @param sheet
	* @param page
	* @return: void
	* @author： ljy  
	* @date： 2019年9月17日
	 */
	public void createTrialDataDetail(Workbook workbook, Sheet sheet, 
			TrialDataDetailEOPage page) {
		//填充数据,查询所有数据
		List<TrialDataDetailEO> rows = trialDataDetailEODao.findByList(page);
		//新增数据行，并且设置单元格数据
        int rowNum = 3;
        for(TrialDataDetailEO eo : rows) {
        	Row rowAdd = sheet.createRow(rowNum);
        	//累计台时  TOTAL_HOURS
        	rowAdd.createCell(0).setCellValue(eo.getTotalHours());
        	//工况时间 OPERATION_TIME
        	rowAdd.createCell(1).setCellValue(eo.getOperationTime());
        	//转速rpm	SPEED
        	rowAdd.createCell(2).setCellValue(eo.getSpeed());
        	//扭矩Nm		TORQUE
        	rowAdd.createCell(3).setCellValue(eo.getTorque());
        	//功率kW  	P
        	rowAdd.createCell(4).setCellValue(eo.getP());
        	//修正扭矩Nm	T_EC
        	rowAdd.createCell(5).setCellValue(eo.gettEc());
        	//修正功率kW	P_EC
        	rowAdd.createCell(6).setCellValue(eo.getpEc());
        	//燃油油耗量kg/h	FB_RATE
        	rowAdd.createCell(7).setCellValue(eo.getFbRate());
        	//燃油消耗率g/kWh	FUELCOSP
        	rowAdd.createCell(8).setCellValue(eo.getFuelcosp());
        	//活塞漏气量l/min	BLOW_VAL
        	rowAdd.createCell(9).setCellValue(eo.getBlowVal());
        	//出水温度degC	TWO
        	rowAdd.createCell(10).setCellValue(eo.getTwo());
        	//出水压力kPa	PWO
        	rowAdd.createCell(11).setCellValue(eo.getPwo());
        	//进水温度degC	TWI
        	rowAdd.createCell(12).setCellValue(eo.getTwi());
        	//进水压力kPa	PWI
        	rowAdd.createCell(13).setCellValue(eo.getPwi());
        	//排气温度degC	T_EXH
        	rowAdd.createCell(14).setCellValue(eo.gettExh());
        	//排气背力kPa	P_EXH
        	rowAdd.createCell(15).setCellValue(eo.getpExh());
        	//燃油温度degC	T_FUEL
        	rowAdd.createCell(16).setCellValue(eo.gettFuel());
        	//燃油压力kPa	P_FUEL
        	rowAdd.createCell(17).setCellValue(eo.getpFuel());
        	//进气温度degC	T_INLET
        	rowAdd.createCell(18).setCellValue(eo.gettInlet());
        	//进气压力kPa	P_INLET
        	rowAdd.createCell(19).setCellValue(eo.getpInlet());
        	//机油温度degC	T_OIL
        	rowAdd.createCell(20).setCellValue(eo.gettOil());
        	//机油压力kPa	P_OIL
        	rowAdd.createCell(21).setCellValue(eo.getpOil());
        	//曲轴箱压力kPa	P_CRANK
        	rowAdd.createCell(22).setCellValue(eo.getpCrank());
        	//大气压力mbar	P_AIR
        	rowAdd.createCell(23).setCellValue(eo.getpAir());
        	//大气温度degC	T_AIR
        	rowAdd.createCell(24).setCellValue(eo.gettAir());
        	//大气湿度%	PHI
        	rowAdd.createCell(25).setCellValue(eo.getPhi());
        	//涡前排温degC	T_EXH_WQ
        	rowAdd.createCell(26).setCellValue(eo.gettExhWq());
        	//涡前排压kPa	P_EXH_WQ
        	rowAdd.createCell(27).setCellValue(eo.getpExhWq());
        	//催后排温degC	T_EXH_CH
        	rowAdd.createCell(28).setCellValue(eo.gettExhCh());
        	//催后排压kPa	P_EXH_CH
        	rowAdd.createCell(29).setCellValue(eo.getpExhCh());
        	//一缸排温degC	T_EX_C_ONE
        	rowAdd.createCell(30).setCellValue(eo.gettExcCOne());
        	//二缸排温degC	T_EX_C_TWO
        	rowAdd.createCell(31).setCellValue(eo.gettExcCTwo());
        	//三缸排温degC	T_EX_C_THREE
        	rowAdd.createCell(32).setCellValue(eo.gettExcCThree());
        	//四缸排温degC	T_EX_C_FOUR
        	rowAdd.createCell(33).setCellValue(eo.gettExcCFour());
        	//中冷进气温degC	T_IC_I
        	rowAdd.createCell(34).setCellValue(eo.gettIcI());
        	//中冷进气压kPa	P_IC_I
        	rowAdd.createCell(35).setCellValue(eo.getpIcI());
        	//中冷出气温degC	T_IC_O
        	rowAdd.createCell(36).setCellValue(eo.gettIcO());
        	//中冷出气压kPa	P_IC_O
        	rowAdd.createCell(37).setCellValue(eo.getpIcO());
        	//油门开度%	ALPHA
        	rowAdd.createCell(38).setCellValue(eo.getAlpha());
        	//继续创建下一行
        	rowNum ++;
        }
	}
	
	
	/**
	 * 创建标题栏
	* @Title：createTitle
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年9月17日
	 */
	public void createTitle(Workbook workbook, Sheet sheet) {
		//标题栏
		Row row1 = sheet.createRow(0);
		Row row2 = sheet.createRow(1);
		Row row3 = sheet.createRow(2);
        //设置列宽，setColumnWidth的第二个参数要乘以256，
        sheet.setColumnWidth(1,20*256);
        // 冻结前三行
        sheet.createFreezePane(0, 3, 0, 3);  
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第一个  累计台时 TOTAL_HOURS
        cell = row1.createCell(0);
        cell.setCellValue(ConstantUtils.TRIALDATA_TOTAL_HOURS);
        cell.setCellStyle(style);
        // 合并行(4个参数，分别为起始行，结束行，起始列，结束列)
        CellRangeAddress region = new CellRangeAddress(0, 2, 0, 0);
        sheet.addMergedRegion(region);
        
        //第二个 工况时间  OPERATION_TIME
        cell = row1.createCell(1);
        cell.setCellValue(ConstantUtils.TRIALDATA_OPERATION_TIME);
        cell.setCellStyle(style);
        // 合并行(4个参数，分别为起始行，结束行，起始列，结束列)
        CellRangeAddress region1 = new CellRangeAddress(0, 2, 1, 1);
        sheet.addMergedRegion(region1);
        //-------------------------------第一行 英文-----------------------------------//
        
        //第三个 转速rpm  SPEED
        cell = row1.createCell(2);
        cell.setCellValue(ConstantUtils.TRIALDATA_SPEED_EN);
        cell.setCellStyle(style);
        //第四个 扭矩Nm TORQUE
        cell = row1.createCell(3);
        cell.setCellValue(ConstantUtils.TRIALDATA_TORQUE_EN);
        cell.setCellStyle(style);
        //第五个 功率kW P
        cell = row1.createCell(4);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EN);
        cell.setCellStyle(style);
        //第六个 修正扭矩Nm	T_EC
        cell = row1.createCell(5);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EC_EN);
        cell.setCellStyle(style);
        //第七个 修正功率kW	P_EC
        cell = row1.createCell(6);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EC_EN);
        cell.setCellStyle(style);
        //第八个 燃油油耗量kg/h	FB_RATE
        cell = row1.createCell(7);
        cell.setCellValue(ConstantUtils.TRIALDATA_FB_RATE_EN);
        cell.setCellStyle(style);
        //第九个 燃油消耗率g/kWh	FUELCOSP
        cell = row1.createCell(8);
        cell.setCellValue(ConstantUtils.TRIALDATA_FUELCOSP_EN);
        cell.setCellStyle(style);
        //第十个 活塞漏气量l/min	BLOW_VAL
        cell = row1.createCell(9);
        cell.setCellValue(ConstantUtils.TRIALDATA_BLOW_VAL_EN);
        cell.setCellStyle(style);
        //第十一个 出水温度degC	TWO
        cell = row1.createCell(10);
        cell.setCellValue(ConstantUtils.TRIALDATA_TWO_EN);
        cell.setCellStyle(style);
        //第十二个 出水压力kPa	PWO
        cell = row1.createCell(11);
        cell.setCellValue(ConstantUtils.TRIALDATA_PWO_EN);
        cell.setCellStyle(style);
        //第十三个 进水温度degC	TWI
        cell = row1.createCell(12);
        cell.setCellValue(ConstantUtils.TRIALDATA_TWI_EN);
        cell.setCellStyle(style);
        //第十四个 进水压力kPa	PWI
        cell = row1.createCell(13);
        cell.setCellValue(ConstantUtils.TRIALDATA_PWI_EN);
        cell.setCellStyle(style);
        //第十五个 排气温度degC	T_EXH
        cell = row1.createCell(14);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH_EN);
        cell.setCellStyle(style);
        //第十六个 /排气背力kPa	P_EXH
        cell = row1.createCell(15);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH_EN);
        cell.setCellStyle(style);
        //第十七个 燃油温度degC	T_FUEL
        cell = row1.createCell(16);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_FUEL_EN);
        cell.setCellStyle(style);
        //第十八个 燃油压力kPa	P_FUEL
        cell = row1.createCell(17);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_FUEL_EN);
        cell.setCellStyle(style);
        //第十九个 进气温度degC	T_INLET
        cell = row1.createCell(18);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_INLET_EN);
        cell.setCellStyle(style);
        //第二十个 进气压力kPa	P_INLET
        cell = row1.createCell(19);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_INLET_EN);
        cell.setCellStyle(style);
        //第二十一个 机油温度degC	T_OIL
        cell = row1.createCell(20);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_OIL_EN);
        cell.setCellStyle(style);
        //第二十二个 机油压力kPa	P_OIL
        cell = row1.createCell(21);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_OIL_EN);
        cell.setCellStyle(style);
        //第二十三个 曲轴箱压力kPa	P_CRANK
        cell = row1.createCell(22);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_CRANK_EN);
        cell.setCellStyle(style);
        //第二十四个 大气压力mbar	P_AIR
        cell = row1.createCell(23);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_AIR_EN);
        cell.setCellStyle(style);
        //第二十五个 大气温度degC	T_AIR
        cell = row1.createCell(24);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_AIR_EN);
        cell.setCellStyle(style);
        //第二十六个 大气湿度%	PHI
        cell = row1.createCell(25);
        cell.setCellValue(ConstantUtils.TRIALDATA_PHI_EN);
        cell.setCellStyle(style);
        //第二十七个 涡前排温degC	T_EXH_WQ
        cell = row1.createCell(26);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH_WQ_EN);
        cell.setCellStyle(style);
        //第二十八个 涡前排压kPa	P_EXH_WQ
        cell = row1.createCell(27);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH_WQ_EN);
        cell.setCellStyle(style);
        //第二十九个 催后排温degC	T_EXH_CH
        cell = row1.createCell(28);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH_CH_EN);
        cell.setCellStyle(style);
        //第三十个 催后排压kPa	P_EXH_CH
        cell = row1.createCell(29);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH_CH_EN);
        cell.setCellStyle(style);
        //第三十一个 一缸排温degC	T_EX_C_ONE
        cell = row1.createCell(30);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_ONE_EN);
        cell.setCellStyle(style);
        //第三十二个 二缸排温degC	T_EX_C_TWO
        cell = row1.createCell(31);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_TWO_EN);
        cell.setCellStyle(style);
        //第三十三个 三缸排温degC	T_EX_C_THREE
        cell = row1.createCell(32);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_THREE_EN);
        cell.setCellStyle(style);
        //第三十四个 四缸排温degC	T_EX_C_FOUR
        cell = row1.createCell(33);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_FOUR_EN);
        cell.setCellStyle(style);
        //第三十五个 中冷进气温degC	T_IC_I
        cell = row1.createCell(34);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_IC_I_EN);
        cell.setCellStyle(style);
        //第三十六个 中冷进气压kPa	P_IC_I
        cell = row1.createCell(35);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_IC_I_EN);
        cell.setCellStyle(style);
        //第三十七个 中冷出气温degC	T_IC_O
        cell = row1.createCell(36);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_IC_O_EN);
        cell.setCellStyle(style);
        //第三十八个 中冷出气压kPa	P_IC_O
        cell = row1.createCell(37);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_IC_O_EN);
        cell.setCellStyle(style);
        //第三十九个 油门开度%	ALPHA
        cell = row1.createCell(38);
        cell.setCellValue(ConstantUtils.TRIALDATA_ALPHA_EN);
        cell.setCellStyle(style);
        
        //-----------------------------第二行 中文----------------------------------//
        
        //第三个 转速rpm  SPEED
        cell = row2.createCell(2);
        cell.setCellValue(ConstantUtils.TRIALDATA_SPEED);
        cell.setCellStyle(style);
        //第四个 扭矩Nm TORQUE
        cell = row2.createCell(3);
        cell.setCellValue(ConstantUtils.TRIALDATA_TORQUE);
        cell.setCellStyle(style);
        //第五个 功率kW P
        cell = row2.createCell(4);
        cell.setCellValue(ConstantUtils.TRIALDATA_P);
        cell.setCellStyle(style);
        //第六个 修正扭矩Nm	T_EC
        cell = row2.createCell(5);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EC);
        cell.setCellStyle(style);
        //第七个 修正功率kW	P_EC
        cell = row2.createCell(6);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EC);
        cell.setCellStyle(style);
        //第八个 燃油油耗量kg/h	FB_RATE
        cell = row2.createCell(7);
        cell.setCellValue(ConstantUtils.TRIALDATA_FB_RATE);
        cell.setCellStyle(style);
        //第九个 燃油消耗率g/kWh	FUELCOSP
        cell = row2.createCell(8);
        cell.setCellValue(ConstantUtils.TRIALDATA_FUELCOSP);
        cell.setCellStyle(style);
        //第十个 活塞漏气量l/min	BLOW_VAL
        cell = row2.createCell(9);
        cell.setCellValue(ConstantUtils.TRIALDATA_BLOW_VAL);
        cell.setCellStyle(style);
        //第十一个 出水温度degC	TWO
        cell = row2.createCell(10);
        cell.setCellValue(ConstantUtils.TRIALDATA_TWO);
        cell.setCellStyle(style);
        //第十二个 出水压力kPa	PWO
        cell = row2.createCell(11);
        cell.setCellValue(ConstantUtils.TRIALDATA_PWO);
        cell.setCellStyle(style);
        //第十三个 进水温度degC	TWI
        cell = row2.createCell(12);
        cell.setCellValue(ConstantUtils.TRIALDATA_TWI);
        cell.setCellStyle(style);
        //第十四个 进水压力kPa	PWI
        cell = row2.createCell(13);
        cell.setCellValue(ConstantUtils.TRIALDATA_PWI);
        cell.setCellStyle(style);
        //第十五个 排气温度degC	T_EXH
        cell = row2.createCell(14);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH);
        cell.setCellStyle(style);
        //第十六个 /排气背力kPa	P_EXH
        cell = row2.createCell(15);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH);
        cell.setCellStyle(style);
        //第十七个 燃油温度degC	T_FUEL
        cell = row2.createCell(16);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_FUEL);
        cell.setCellStyle(style);
        //第十八个 燃油压力kPa	P_FUEL
        cell = row2.createCell(17);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_FUEL);
        cell.setCellStyle(style);
        //第十九个 进气温度degC	T_INLET
        cell = row2.createCell(18);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_INLET);
        cell.setCellStyle(style);
        //第二十个 进气压力kPa	P_INLET
        cell = row2.createCell(19);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_INLET);
        cell.setCellStyle(style);
        //第二十一个 机油温度degC	T_OIL
        cell = row2.createCell(20);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_OIL);
        cell.setCellStyle(style);
        //第二十二个 机油压力kPa	P_OIL
        cell = row2.createCell(21);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_OIL);
        cell.setCellStyle(style);
        //第二十三个 曲轴箱压力kPa	P_CRANK
        cell = row2.createCell(22);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_CRANK);
        cell.setCellStyle(style);
        //第二十四个 大气压力mbar	P_AIR
        cell = row2.createCell(23);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_AIR);
        cell.setCellStyle(style);
        //第二十五个 大气温度degC	T_AIR
        cell = row2.createCell(24);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_AIR);
        cell.setCellStyle(style);
        //第二十六个 大气湿度%	PHI
        cell = row2.createCell(25);
        cell.setCellValue(ConstantUtils.TRIALDATA_PHI);
        cell.setCellStyle(style);
        //第二十七个 涡前排温degC	T_EXH_WQ
        cell = row2.createCell(26);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH_WQ);
        cell.setCellStyle(style);
        //第二十八个 涡前排压kPa	P_EXH_WQ
        cell = row2.createCell(27);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH_WQ);
        cell.setCellStyle(style);
        //第二十九个 催后排温degC	T_EXH_CH
        cell = row2.createCell(28);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EXH_CH);
        cell.setCellStyle(style);
        //第三十个 催后排压kPa	P_EXH_CH
        cell = row2.createCell(29);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_EXH_CH);
        cell.setCellStyle(style);
        //第三十一个 一缸排温degC	T_EX_C_ONE
        cell = row2.createCell(30);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_ONE);
        cell.setCellStyle(style);
        //第三十二个 二缸排温degC	T_EX_C_TWO
        cell = row2.createCell(31);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_TWO);
        cell.setCellStyle(style);
        //第三十三个 三缸排温degC	T_EX_C_THREE
        cell = row2.createCell(32);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_THREE);
        cell.setCellStyle(style);
        //第三十四个 四缸排温degC	T_EX_C_FOUR
        cell = row2.createCell(33);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_EX_C_FOUR);
        cell.setCellStyle(style);
        //第三十五个 中冷进气温degC	T_IC_I
        cell = row2.createCell(34);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_IC_I);
        cell.setCellStyle(style);
        //第三十六个 中冷进气压kPa	P_IC_I
        cell = row2.createCell(35);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_IC_I);
        cell.setCellStyle(style);
        //第三十七个 中冷出气温degC	T_IC_O
        cell = row2.createCell(36);
        cell.setCellValue(ConstantUtils.TRIALDATA_T_IC_O);
        cell.setCellStyle(style);
        //第三十八个 中冷出气压kPa	P_IC_O
        cell = row2.createCell(37);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_IC_O);
        cell.setCellStyle(style);
        //第三十九个 油门开度%	ALPHA
        cell = row2.createCell(38);
        cell.setCellValue(ConstantUtils.TRIALDATA_ALPHA);
        cell.setCellStyle(style);
        
        //------------------------第三行 单位---------------------------//
        
        //第三个 转速rpm  SPEED
        cell = row3.createCell(2);
        cell.setCellValue(ConstantUtils.TRIALDATA_RPM);
        cell.setCellStyle(style);
        //第四个 扭矩Nm TORQUE
        cell = row3.createCell(3);
        cell.setCellValue(ConstantUtils.TRIALDATA_NM);
        cell.setCellStyle(style);
        //第五个 功率kW P
        cell = row3.createCell(4);
        cell.setCellValue(ConstantUtils.TRIALDATA_KW);
        cell.setCellStyle(style);
        //第六个 修正扭矩Nm	T_EC
        cell = row3.createCell(5);
        cell.setCellValue(ConstantUtils.TRIALDATA_NM);
        cell.setCellStyle(style);
        //第七个 修正功率kW	P_EC
        cell = row3.createCell(6);
        cell.setCellValue(ConstantUtils.TRIALDATA_KW);
        cell.setCellStyle(style);
        //第八个 燃油油耗量kg/h	FB_RATE
        cell = row3.createCell(7);
        cell.setCellValue(ConstantUtils.TRIALDATA_KGH);
        cell.setCellStyle(style);
        //第九个 燃油消耗率g/kWh	FUELCOSP
        cell = row3.createCell(8);
        cell.setCellValue(ConstantUtils.TRIALDATA_GKWH);
        cell.setCellStyle(style);
        //第十个 活塞漏气量l/min	BLOW_VAL
        cell = row3.createCell(9);
        cell.setCellValue(ConstantUtils.TRIALDATA_LMIN);
        cell.setCellStyle(style);
        //第十一个 出水温度degC	TWO
        cell = row3.createCell(10);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第十二个 出水压力kPa	PWO
        cell = row3.createCell(11);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第十三个 进水温度degC	TWI
        cell = row3.createCell(12);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第十四个 进水压力kPa	PWI
        cell = row3.createCell(13);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第十五个 排气温度degC	T_EXH
        cell = row3.createCell(14);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第十六个 /排气背力kPa	P_EXH
        cell = row3.createCell(15);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第十七个 燃油温度degC	T_FUEL
        cell = row3.createCell(16);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第十八个 燃油压力kPa	P_FUEL
        cell = row3.createCell(17);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第十九个 进气温度degC	T_INLET
        cell = row3.createCell(18);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第二十个 进气压力kPa	P_INLET
        cell = row3.createCell(19);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第二十一个 机油温度degC	T_OIL
        cell = row3.createCell(20);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第二十二个 机油压力kPa	P_OIL
        cell = row3.createCell(21);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第二十三个 曲轴箱压力kPa	P_CRANK
        cell = row3.createCell(22);
        cell.setCellValue(ConstantUtils.TRIALDATA_P_CRANK);
        cell.setCellStyle(style);
        //第二十四个 大气压力mbar	P_AIR
        cell = row3.createCell(23);
        cell.setCellValue(ConstantUtils.TRIALDATA_MBAR);
        cell.setCellStyle(style);
        //第二十五个 大气温度degC	T_AIR
        cell = row3.createCell(24);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第二十六个 大气湿度%	PHI
        cell = row3.createCell(25);
        cell.setCellValue(ConstantUtils.TRIALDATA_PERCENT);
        cell.setCellStyle(style);
        //第二十七个 涡前排温degC	T_EXH_WQ
        cell = row3.createCell(26);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第二十八个 涡前排压kPa	P_EXH_WQ
        cell = row3.createCell(27);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第二十九个 催后排温degC	T_EXH_CH
        cell = row3.createCell(28);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十个 催后排压kPa	P_EXH_CH
        cell = row3.createCell(29);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第三十一个 一缸排温degC	T_EX_C_ONE
        cell = row3.createCell(30);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十二个 二缸排温degC	T_EX_C_TWO
        cell = row3.createCell(31);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十三个 三缸排温degC	T_EX_C_THREE
        cell = row3.createCell(32);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十四个 四缸排温degC	T_EX_C_FOUR
        cell = row3.createCell(33);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十五个 中冷进气温degC	T_IC_I
        cell = row3.createCell(34);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十六个 中冷进气压kPa	P_IC_I
        cell = row3.createCell(35);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第三十七个 中冷出气温degC	T_IC_O
        cell = row3.createCell(36);
        cell.setCellValue(ConstantUtils.TRIALDATA_DEGC);
        cell.setCellStyle(style);
        //第三十八个 中冷出气压kPa	P_IC_O
        cell = row3.createCell(37);
        cell.setCellValue(ConstantUtils.TRIALDATA_KPA);
        cell.setCellStyle(style);
        //第三十九个 油门开度%	ALPHA
        cell = row3.createCell(38);
        cell.setCellValue(ConstantUtils.TRIALDATA_PERCENT);
        cell.setCellStyle(style);
	}
	
}
