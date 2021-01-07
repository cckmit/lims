package com.adc.da.trial_execute.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.trial_execute.dao.TrialDataDetailEODao;
import com.adc.da.trial_execute.dao.TrialDataEODao;
import com.adc.da.trial_execute.entity.TrialDataDetailEO;
import com.adc.da.trial_execute.entity.TrialDataEO;
import com.adc.da.trial_execute.page.TrialDataEOPage;
import com.adc.da.util.Utils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;

/**
 * 试验数据记录
 * @author Administrator
 * @date 2019年9月11日
 */
@Service("trialDataEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialDataEOService extends BaseService<TrialDataEO, String> {

	@Autowired
	private TrialDataEODao trialDataEODao;
	
	public TrialDataEODao getDao() {
		return trialDataEODao;
	}
	
	@Autowired
	private TrialDataDetailEODao trialDataDetailEODao;
	
	/**
	 * 试验数据记录-分页查询
	* @Title：page
	* @param page
	* @return
	* @return: List<TrialDataEO>
	* @author： ljy  
	* @date： 2019年9月12日
	 */
	public List<TrialDataEO> page(TrialDataEOPage page){
		//查询
		return trialDataEODao.queryByPage(page);
	}
	
	
	/**
	 * 试验数据记录-新增
	* @Title：save
	* @param trialDataEO
	* @param file
	* @return
	* @return: TrialDataEO
	* @author： ljy  
	* @date： 2019年9月12日
	 * @throws IOException 
	 */
	public TrialDataEO save(TrialDataEO trialDataEO, MultipartFile file) throws IOException {
		//设置UUID
		trialDataEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		trialDataEO.setDelFlag("0");
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialDataEO.setCreateBy(UserUtils.getUserId());
		trialDataEO.setCreateTime(date);
		trialDataEO.setUpdateBy(UserUtils.getUserId());
		trialDataEO.setUpdateTime(date);
		//解析Excel文件,将数据添加至数据库
		saveDataDetial(file, trialDataEO);
		//保存
		trialDataEODao.insert(trialDataEO);
		//返回
		return trialDataEO;
	}
	
	/**
	 * 试验数据记录-编辑
	* @Title：edit
	* @param trialDataEO
	* @param file
	* @return
	* @throws IOException
	* @return: TrialDataEO
	* @author： ljy  
	* @date： 2019年9月16日
	 */
	public TrialDataEO edit(TrialDataEO trialDataEO, MultipartFile file) throws IOException {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		trialDataEO.setUpdateBy(UserUtils.getUserId());
		trialDataEO.setUpdateTime(date);
		//进行原有数据删除
		trialDataDetailEODao.deleteByTrialdataId(trialDataEO.getId());
		//解析Excel文件,将数据添加至数据库
		saveDataDetial(file, trialDataEO);
		//保存
		trialDataEODao.updateByPrimaryKeySelective(trialDataEO);
		//返回
		return trialDataEO;
	}
	
	/**
	 * 解析Excel数据, 将数据保存至数据库
	* @Title：saveDataDetial
	* @param file
	* @param trialDataEO
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年9月16日
	 */
	public void saveDataDetial(MultipartFile file, TrialDataEO trialDataEO) throws IOException {
		Workbook workbook = Utils.getWorkbook(file);
		//我们通过 getSheetAt(0) 指定表格索引来获取对应表格,注意表格索引从 0 开始！
		int sheetNum=0;
		// FSheet 代表 Excel 文件中的一张表格,
		Sheet sheet = workbook.getSheetAt(sheetNum);
		int firstRowNum  = sheet.getFirstRowNum();
		//获得当前sheet的结束行
		int lastRowNum = sheet.getLastRowNum();
		//循环除了前4行的所有行
		for(int rowNum = firstRowNum + 4;rowNum <= lastRowNum; rowNum++){
			//获得当前行
			Row row = sheet.getRow(rowNum);
			//实体数据
			TrialDataDetailEO trialDataDetailEO = new TrialDataDetailEO();
			//主键
			trialDataDetailEO.setId(UUID.randomUUID());
			//创建者
			trialDataDetailEO.setCreateBy(trialDataEO.getCreateBy());
			//创建时间
			trialDataDetailEO.setCreateTime(trialDataEO.getCreateTime());
			//删除标记 0未删除   1删除
			trialDataDetailEO.setDelFlag("0");
			//试验数据id
			trialDataDetailEO.setTrialdataId(trialDataEO.getId());
			//试验任务id
			trialDataDetailEO.setTrialtaskId(trialDataEO.getTrialtaskId());
			//累计台时  TOTAL_HOURS
			trialDataDetailEO.setTotalHours(Utils.getCellValue(row.getCell(0)));
			//工况时间 OPERATION_TIME
			trialDataDetailEO.setOperationTime(Utils.getCellValue(row.getCell(1)));
			//转速rpm	SPEED
			trialDataDetailEO.setSpeed(Utils.getCellValue(row.getCell(2)));
			//扭矩Nm		TORQUE
			trialDataDetailEO.setTorque(Utils.getCellValue(row.getCell(3)));
			//功率kW	P
			trialDataDetailEO.setP(Utils.getCellValue(row.getCell(4)));
			//修正扭矩Nm	T_EC
			trialDataDetailEO.settEc(Utils.getCellValue(row.getCell(5)));
			//修正功率kW	P_EC
			trialDataDetailEO.setpEc(Utils.getCellValue(row.getCell(6)));
			//燃油油耗量kg/h	FB_RATE
			trialDataDetailEO.setFbRate(Utils.getCellValue(row.getCell(7)));
			//燃油消耗率g/kWh	FUELCOSP
			trialDataDetailEO.setFuelcosp(Utils.getCellValue(row.getCell(8)));
			//活塞漏气量l/min	BLOW_VAL
			trialDataDetailEO.setBlowVal(Utils.getCellValue(row.getCell(9)));
			//出水温度degC	TWO
			trialDataDetailEO.setTwo(Utils.getCellValue(row.getCell(10)));
			//出水压力kPa	PWO
			trialDataDetailEO.setPwo(Utils.getCellValue(row.getCell(11)));
			//进水温度degC	TWI
			trialDataDetailEO.setTwi(Utils.getCellValue(row.getCell(12)));
			//进水压力kPa	PWI
			trialDataDetailEO.setPwi(Utils.getCellValue(row.getCell(13)));
			//排气温度degC	T_EXH
			trialDataDetailEO.settExh(Utils.getCellValue(row.getCell(14)));
			//排气背力kPa	P_EXH
			trialDataDetailEO.setpExh(Utils.getCellValue(row.getCell(15)));
			//燃油温度degC	T_FUEL
			trialDataDetailEO.settFuel(Utils.getCellValue(row.getCell(16)));
			//燃油压力kPa	P_FUEL
			trialDataDetailEO.setpFuel(Utils.getCellValue(row.getCell(17)));
			//进气温度degC	T_INLET
			trialDataDetailEO.settInlet(Utils.getCellValue(row.getCell(18)));
			//进气压力kPa	P_INLET
			trialDataDetailEO.setpInlet(Utils.getCellValue(row.getCell(19)));
			//机油温度degC	T_OIL
			trialDataDetailEO.settOil(Utils.getCellValue(row.getCell(20)));
			//机油压力kPa	P_OIL
			trialDataDetailEO.setpOil(Utils.getCellValue(row.getCell(21)));
			//曲轴箱压力kPa	P_CRANK
			trialDataDetailEO.setpCrank(Utils.getCellValue(row.getCell(22)));
			//大气压力mbar	P_AIR
			trialDataDetailEO.setpAir(Utils.getCellValue(row.getCell(23)));
			//大气温度degC	T_AIR
			trialDataDetailEO.settAir(Utils.getCellValue(row.getCell(24)));
			//大气湿度%	PHI
			trialDataDetailEO.setPhi(Utils.getCellValue(row.getCell(25)));
			//涡前排温degC	T_EXH_WQ
			trialDataDetailEO.settExhWq(Utils.getCellValue(row.getCell(26)));
			//涡前排压kPa	P_EXH_WQ
			trialDataDetailEO.setpExhWq(Utils.getCellValue(row.getCell(27)));
			//催后排温degC	T_EXH_CH
			trialDataDetailEO.settExhCh(Utils.getCellValue(row.getCell(28)));
			//催后排压kPa	P_EXH_CH
			trialDataDetailEO.setpExhCh(Utils.getCellValue(row.getCell(29)));
			//一缸排温degC	T_EX_C_ONE
			trialDataDetailEO.settExcCOne(Utils.getCellValue(row.getCell(30)));
			//二缸排温degC	T_EX_C_TWO
			trialDataDetailEO.settExcCTwo(Utils.getCellValue(row.getCell(31)));
			//三缸排温degC	T_EX_C_THREE
			trialDataDetailEO.settExcCThree(Utils.getCellValue(row.getCell(32)));
			//四缸排温degC	T_EX_C_FOUR
			trialDataDetailEO.settExcCFour(Utils.getCellValue(row.getCell(33)));
			//中冷进气温degC	T_IC_I
			trialDataDetailEO.settIcI(Utils.getCellValue(row.getCell(34)));
			//中冷进气压kPa	P_IC_I
			trialDataDetailEO.setpIcI(Utils.getCellValue(row.getCell(35)));
			//中冷出气温degC	T_IC_O
			trialDataDetailEO.settIcO(Utils.getCellValue(row.getCell(36)));
			//中冷出气压kPa	P_IC_O
			trialDataDetailEO.setpIcO(Utils.getCellValue(row.getCell(37)));
			//油门开度%	ALPHA
			trialDataDetailEO.setAlpha(Utils.getCellValue(row.getCell(38)));
			//保存至数据库
			trialDataDetailEODao.insert(trialDataDetailEO);
		}
	}
	
}
