package com.adc.da.common;

import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.util.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PrimaryGenerater {

	@Autowired
	private PipelineNumberEODao pipelineNumberEODao;

	/**
	 * EV发动机编号
   * FBS0001PV-年
   * */
	public static synchronized String getEVreportNumber(Integer code, String year){
        String reportNo = ConstantUtils.EV_REPORT_NO_FSB + String.format(ConstantUtils.EV_REPORT_NO_RULE, code + 1) 
        			+ ConstantUtils.EV_REPORT_NO_PV + year;
        return reportNo;
	}

  /**
   * PC可靠性立项单编号
   * CV/PV-KKXLX-0001-年
   * */
	public static synchronized String getPCReliableInitTaskNumber(Integer code, String year, String pvorcv){
      String pcReliableInitTaskNo = pvorcv + "-" + ConstantUtils.PC_RELIABLE_INITTASK_NO_KKXLX +
    		  String.format(ConstantUtils.EV_REPORT_NO_RULE, code + 1) 
      			+ "-" + year;
      return pcReliableInitTaskNo;
	}

	/**
	 * PC整车出门单编号
   * CLCMT-2019-01-0001
   * */
	public static synchronized String getPCVehicleNoNumber(Integer code, String year){
		String currMonth = DateUtils.dateToString(new Date(), "yyyy-MM-dd").substring(5, 7);
		String vehicleNo = ConstantUtils.PC_CLCMT_NO + year + "-" + currMonth + "-"
        		+ String.format(ConstantUtils.EV_REPORT_NO_RULE, code + 1);
        return vehicleNo;
	}
	
	/**
	 * PC物资出门单编号
   * WZCMT-2019-01-0001
   * */
	public static synchronized String getPCMaterialsNoNumber(Integer code, String year){
		String currMonth = DateUtils.dateToString(new Date(), "yyyy-MM-dd").substring(5, 7);
		String vehicleNo = ConstantUtils.PC_WZCMT_NO + year + "-" + currMonth + "-"
        		+ String.format(ConstantUtils.EV_REPORT_NO_RULE, code + 1);
        return vehicleNo;
	}
	
	
	/**
	 * 获取委托编号
	 *
	 * @return
	 */
	public String findTrustCode() throws Exception {
		String currYear = DateUtils.dateToString(new Date(), "yyyy");
		PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SUP_CODE_NUM);
		if (!currYear.equals(pipelineNumberEO.getParticularYear())) {
			pipelineNumberEO.setTally(1);
			pipelineNumberEO.setParticularYear(currYear);
			pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
			return "SYFZWTD" + currYear + "0001";
		}
		//最大流水号
		String num = pipelineNumberEO.getTally().toString();
		//自动补全4位
		while (num.length() < 4) {
			num = "0" + num;
		}
		return "SYFZWTD" + currYear + num;
	}

	/**
	 * 获取委托编号
	 * @param code  对应报告编号流水key
	 * @param numCode   pv or cv
	 * @return
	 * @throws Exception
	 */
	public String findReportNo(String code, String numCode) throws Exception {
		String currYear = DateUtils.dateToString(new Date(), "yyyy");
		PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(code);
		if (!currYear.equals(pipelineNumberEO.getParticularYear())) {
			pipelineNumberEO.setParticularYear(currYear);
			pipelineNumberEO.setTally(2);
			pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
			return "SYBG-" + numCode + "-0001-" + currYear;
		}
		//最大流水号
		String num = pipelineNumberEO.getTally().toString();
		pipelineNumberEO.setTally(Integer.parseInt(num)+1);
		//更新最大流水号
		pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
		//自动补全4位
		while (num.length() < 4) {
			num = "0" + num;
		}
		return "SYBG-" + numCode + "-" + num + "-" + currYear;
	}

	/**
	 * 委外试验委托单编号。
	 * @param pCode
	 */
	public String queryOutTrustCode(String pCode){
		String currYear = DateUtils.dateToString(new Date(), "yyyy");
		PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(pCode);
		if (!currYear.equals(pipelineNumberEO.getParticularYear())) {
			pipelineNumberEO.setParticularYear(currYear);
			pipelineNumberEO.setTally(2);
			pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
			return "WWSYWT" + currYear + "0001";
		}
		//最大流水号
		String num = pipelineNumberEO.getTally().toString();
		//自动补全4位
		while (num.length() < 4) {
			num = "0" + num;
		}
		return "WWSYWT" + currYear + num;
	}

	public String queryLLDCode(String lldCode, String pCode){
		String currYearMonth = DateUtils.dateToString(new Date(), "yyyy-MM");
		PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(pCode);
		if (!currYearMonth.equals(pipelineNumberEO.getParticularYear())) {
			pipelineNumberEO.setParticularYear(currYearMonth);
			pipelineNumberEO.setTally(2);
			pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
			return lldCode + "-" + currYearMonth + "-0001";
		}
		//最大流水号
		String num = pipelineNumberEO.getTally().toString();
		//自动补全4位
		while (num.length() < 4) {
			num = "0" + num;
		}
		return lldCode + "-" + currYearMonth + "-" + num;
	}


	/**
	 * QCDD编号
   * QCDD-0001-年份
   * */
	public static synchronized String getPCQCDDNo(Integer code, String year){
        String QCDDNo = ConstantUtils.PC_QCDD + "-" 
        		+ String.format(ConstantUtils.PC_QCDD_NO_RULE, code + 1) 
        		+ "-" + year;
        return QCDDNo;
	}
}
