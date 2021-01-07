package com.adc.da.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConstantUtils {

	private ConstantUtils() {
	    throw new IllegalStateException("Utility class");
	}

	/**
	 * 返回状态---成功
	 */
	public static final String RETURN_SUCCESS = "0";
	/**
	 * 返回状态---失败
	 */
	public static final String RETURN_ERROR = "-1";
	/**
	 * 返回状态---数据有误
	 */
	public static final String RETURN_MISS = "-2";
	/**
	 * 匹配除了空格以外的任意字符
	 */
	public static final String REGEX_EXCEPT_BLANK = "[^\\s]+";
	/**
	 * 树形最父级节点设置 parentId 为 -1
	 */
	public static final String PARENT_ID = "-1";
	/**
	 * 检验标准状态(中文)--实施中
	 */
	public static final String CH_STD_STATUS_VALID = "实施中";
	/**
	 * 检验标准状态(英文)--实施中
	 */
	public static final String EN_STD_STATUS_VALID = "valid";

	/**
	 * 检验标准状态(中文)--作废
	 */
	public static final String CH_STD_STATUS_INVALID = "作废";

	/**
	 * 检验标准状态(英文)--作废
	 */
	public static final String EN_STD_STATUS_INVALID = "invalid";

	/**
	 * 分割符 英文逗号
	 */
	public static final String COMMA = ",";

	/**
	 * 分割符 英文点
	 */
	public static final String SPOT = ".";

	/**
	 * Excel文件后缀xlsx
	 */
	public static final String FILE_EXTEND_XLSX = "xlsx";

	/**
	 * Word文件后缀docx
	 */
	public static final String FILE_WORD_DOCX = "docx";
	/**
	 * Word文件后缀doc
	 */
	public static final String FILE_WORD_DOC = "doc";

	/**
	 * Excel文件后缀xls
	 */
	public static final String FILE_EXTEND_XLS = "xls";

	/**
	 * 检验项目导出---项目代码
	 */
	public static final String INSPRO_EXPORT_CODE = "项目代码";
	/**
	 * 检验项目导出---项目名称
	 */
	public static final String INSPRO_EXPORT_NAME = "项目名称";
	/**
	 * 检验项目导出---检验依据(检验标准)
	 */
	public static final String INSPRO_EXPORT_STANDARD = "检验依据";

	/**
	 * 检验项目导入---项目代码
	 */
	public static final String INSPRO_IMPORT_CODE = "proCode";
	/**
	 * 检验项目导入---项目名称
	 */
	public static final String INSPRO_IMPORT_NAME = "proName";
	/**
	 * 检验项目导入---检验依据(检验标准)
	 */
	public static final String INSPRO_IMPORT_STANDARD = "stdNo";
	
	/**
	 * 检验项目导入---试验方法
	 */
	public static final String INSPRO_IMPORT_TRIALMETHOD = "trialMethod";
	
	
	/**
	 * 检验项目导入---项目说明
	 */
	public static final String INSPRO_IMPORT_PRODESC = "proDesc";
	
	
	/**
	 * 检验项目导入---第一类型
	 */
	public static final String INSPRO_IMPORT_ONETYPE = "oneType";
	
	/**
	 * 检验项目导入---第二类型
	 */
	public static final String INSPRO_IMPORT_TWOTYPE = "twoType";
	
	/**
	 * 检验项目导入---第三类型
	 */
	public static final String INSPRO_IMPORT_THREETYPE = "threeType";
	
	
	

	/**
	 * 检验项目导出
	 */
	public static final String INSPRO_EXPORT = "检验项目导出";
	/**
	 * 文件后缀pdf
	 */
	public static final String FILE_EXTEND_PDF = "pdf";

	/**
	 * 浏览器 firefox
	 */
	public static final String FIREFOX = "firefox";

	/**
	 * 检验项目--强检项目
	 */
	public static final String INSPROJECT_QJ = "QJ";

	/**
	 * 检验项目--新能源项目
	 */
	public static final String INSPROJECT_XNY = "XNY";

	/**
	 * 检验项目导入---行
	 */
	public static final String ROW_NUM = "rowNum";

	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式 yyyyMMdd
	 */
	public static final String DATE_FORMAT2 = "yyyyMMdd";

	/**
	 * 日期格式 yyyy
	 */
	public static final String DATE_FORMAT3 = "yyyy";

	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static final String DATE_FORMAT4 = "yyyy-MM-dd";
	/**
	 * 水印后缀
	 */
	public static final String WATERMARK = "_watermark";

	/**
	 * 内部费用库导出
	 */
	public static final String INTERNALCOST_EXPORT = "内部费用库导出";

	/**
	 * 内部费用库导出-试验项目
	 */
	public static final String INTERNALCOST_INSPRONAME = "试验项目";

	/**
	 * 内部费用库导出-试验项目编号
	 */
	public static final String INTERNALCOST_INSPROCODE = "试验项目编号";

	/**
	 * 内部费用库导出-负责组
	 */
	public static final String INTERNALCOST_INSPROORG = "负责组";

	/**
	 * 内部费用库导出-检验标准
	 */
	public static final String INTERNALCOST_STDNO = "检验标准";

	/**
	 * 内部费用库导出-标准名称
	 */
	public static final String INTERNALCOST_STDNAME = "标准名称";

	/**
	 * 内部费用库导出-单价
	 */
	public static final String INTERNALCOST_PRICE = "单价";

	/**
	 * 内部费用库导出-单位
	 */
	public static final String INTERNALCOST_UNIT = "单位";

	/**
	 * 内部费用库导出-备注
	 */
	public static final String INTERNALCOST_REMARKS = "备注";

	/**
	 * 发动机报告编号前缀
	 */
	public static final String EV_REPORT_NO_FSB = "FSB";

	/**
	 * 发动机报告编号前缀
	 */
	public static final String EV_REPORT_NO_PV = "PV-";

	/**
	 * 发动机报告编号字段补0
	 */
	public static final String EV_REPORT_NO_RULE = "%04d";
	/**
	 * 发动机公章code
	 */
	public static final String EV_REPORT_STAMP = "EV_Report_Stamp";
	/**
	 * 流程定义code
	 */
	public static final String PROCESS_CODE = "ProcessKey";
	/**
	 * 发动机报告流程类型
	 */
	public static final String EV_REPORT_BUSINESS_TYPE = "EV_Report";
	/**
	 * PV流程类型
	 */
	public static final String PV_REPORT_BUSINESS_TYPE = "PV_REPORT_PROCDEFID";
	/**
	 * CV流程类型
	 */
	public static final String CV_REPORT_BUSINESS_TYPE = "CV_REPORT_PROCDEFID";
	/**
	 * 发动机流水号code
	 */
	public static final String EV_REPORT_NO = "EV_REPORT_NO";

	/**
	 * 乘用车报告流水号code
	 */
	public static final String PV_REPORT_NO = "PV_REPORT_NO";

	/**
	 * 商用车报告流水号code
	 */
	public static final String CV_REPORT_NO = "CV_REPORT_NO";
	/**
	 * PV路试路送委托流程
	 */
	public static final String PV_SUP_BUSINESS_TYPE = "PVSup";
	/**
	 * CV路试路送委托流程
	 */
	public static final String CV_SUP_BUSINESS_TYPE = "CVSup";
	/**
	 * CV路试路送委托反馈流程
	 */
	public static final String CV_SUP_ROADTEST_Feedback = "CVSupRoadTestFeedback";
	/**
	 * PV路试路送委托反馈流程
	 */
	public static final String PV_SUP_ROADTEST_Feedback = "PVSupRoadTestFeedback";
	/**
	 * CV辅助劳务流程
	 */
	public static final String CV_SUP_TASKAPPLY = "CVSupTaskApply";
	/**
	 * PV辅助劳务流程
	 */
	public static final String PV_SUP_TASKAPPLY = "PVSupTaskApply";
	/**
	 * CV辅助劳务反馈流程
	 */
	public static final String CV_SUP_TASKAPPLY_Feedback = "CVSupTaskApplyFeedback";
	/**
	 * PV辅助劳务反馈流程
	 */
	public static final String PV_SUP_TASKAPPLY_Feedback = "PVSupTaskApplyFeedback";
	/**
	 * CV-认证试验生成委托单流程
	 */
	public static final String CV_ANN_PLAN = "CVAnnPlan";
	/**
	 * PV-认证试验生成委托单流程
	 */
	public static final String PV_ANN_PLAN = "PVAnnPlan";
	/**
	 * 发动机报告数据类型 - 试验数据 reportData
	 */
	public static final String EV_REPORT_DATA = "reportData";
	/**
	 * 发动机报告数据类型 - 报告PDF书 - reportPDF
	 */
	public static final String EV_REPORT_DATA_PDF = "reportPDF";
	/**
	 * 发动机报告归档状态  0-未归档(默认)/取消归档
	 */
	public static final String EV_REPORT_ARCHIVE_NOT = "0";
	/**
	 * 发动机报告归档状态  1-已归档(纸质归档+电子归档)
	 */
	public static final String EV_REPORT_ARCHIVE_ALL = "1";
	/**
	 * 发动机报告归档状态  2-纸质归档
	 */
	public static final String EV_REPORT_ARCHIVE_PAPER = "2";
	/**
	 * 发动机报告归档状态  3-电子归档
	 */
	public static final String EV_REPORT_ARCHIVE_ELECTRON = "3";
	/**
	 * 发动机报告归档状态  是否归档-是
	 */
	public static final String EV_REPORT_ARCHIVE_YES = "Y";
	/**
	 * 发动机报告归档状态  是否归档-否
	 */
	public static final String EV_REPORT_ARCHIVE_NO = "N";

	public static final String AT_TASKBOOK = "AT_TASKBOOK";
	public static final String AT_TRIAL_REPORT = "AT_TRIAL_REPORT";
	public static final String AT_TRIAL_SUMMARY = "AT_TRIAL_SUMMARY";
	public static final String AT_DRIVE_RECORD = "AT_DRIVE_RECORD";
	public static final String AT_CHECK = "AT_CHECK";
	public static final String AT_TORQUE_RECORD = "AT_TORQUE_RECORD";
	public static final String AT_HUB_TEMPERATURE = "AT_HUB_TEMPERATURE";
	public static final String AT_LUQIAO_TICKET = "AT_LUQIAO_TICKET";
	public static final String AT_REPAIR_UPDOWN = "AT_REPAIR_UPDOWN";
	public static final String AT_FOURWHEEL_POSITION = "AT_FOURWHEEL_POSITION";

	public static  final String SUP_CODE_NUM = "SUP_CODE_NUM";

	/**
	 * 供应商委托编号
	 */
	public static  final String SUP_APPLY_NO = "SUP_APPLY_NO";

	/**
	 * 委外立项单编号
	 */
	public static  final String PC_OUTSOURCE = "PC_OUTSOURCE";

	/**
	 * 供应商委托编号
	 */
	public static  final String BM_LINE_NO = "BM_LINE_NO";

	/**
	 * 整车
	 */
	public static  final String SA_CAR_SAMPLE = "SA_CAR_SAMPLE";
	/**
	 * 零部件
	 */
	public static  final String SA_PART_SAMPLE = "SA_PART_SAMPLE";

	/**
	 * 发动机试验任务流程类型
	 */
	public static final String EV_TRIALTASK_BUSINESS_TYPE = "EV_Trial";
	/**
	 * pc委外立项单
	 */
	public static final String PC_OUTSOURCE_PROJECT_TYPE = "PcOutsourceProject";
	/**
	 * pc委外立项单
	 */
	public static final String CV_OUTSOURCE_PROJECT_TYPE = "CvOutsourceProject";
	/**
	 * pc费用请款单
	 */
	public static final String PC_BUDGET_CASH_OUT_TYPE = "PcBudgetCashOut";
	/**
	 * pc费用请款单
	 */
	public static final String CV_BUDGET_CASH_OUT_TYPE = "CvBudgetCashOut";
	/**
	 * pc费用报销申请单
	 */
	public static final String PC_BUDGET_REIMBURSEMENT_TYPE = "PcBudgetReimbursement";
	/**
	 * pc费用报销申请单
	 */
	public static final String CV_BUDGET_REIMBURSEMENT_TYPE = "CvBudgetReimbursement";
	/**
	 * pc费用使用申请
	 */
	public static final String PC_BUDGET_USE_TYPE = "PcBudgetUse";
	/**
	 * pc费用使用申请
	 */
	public static final String CV_BUDGET_USE_TYPE = "CvBudgetUse";
	/**
	 * pc委外立项单类型
	 */
	public static final String PC_OUTSOURCE_PROJECT_TYPE_FORM = "PcOutsourceProjectForm";
	/**
	 * pc费用请款单类型
	 */
	public static final String PC_BUDGET_CASH_OUT_TYPE_FORM = "PcBudgetCashOutForm";
	/**
	 * pc费用报销申请单类型
	 */
	public static final String PC_BUDGET_REIMBURSEMENT_TYPE_FORM = "PcBudgetReimbursementForm";
	/**
	 * pc费用使用申请类型
	 */
	public static final String PC_BUDGET_USE_TYPE_FORM = "PcBudgetUseForm";
	/**
	 * 发动机部部长角色id
	 */
	public static final String EV_MINISTER_ROLEID = "QRRM6U3G2K";
	/**
	 * 发动机部长角色code
	 */
	public static final String EV_MINISTER_ROLE_CODE = "EV_Minister";

	/**
	 * 发动机部科长角色id
	 */
	public static final String EV_SECTIONCHIEF_ROLEID = "XJVPP98MXT";
	/**
	 * 发动机科长觉得code
	 */
	public static final String EV_SECTIONCHIEF_ROLE_CODE = "EV_SectionChief";

	/**
	 * 发动机部主管角色id
	 */
	public static final String EV_CHARGE_ROLEID = "ZLRKHFJ2G3";
	/**
	 * 发动机主管角色code
	 */
	public static final String EV_CHARGE_ROLE_CODE = "EV_Charge";
	
	/**
	 * admin1
	 */
	public static final String ADMIN_ROLEID = "YWYC7ME75K";
	
	/**
	 * 发动机部试验工程师管理员角色id
	 */
	public static final String EV_TASKMANAGER_ROLEID = "J3QUJGJSUT";

	/**
	 * 发动机部试验工程师管理员角色CODE
	 */
	public static final String EV_TASKMANAGER_ROLE_CODE = "EV_TaskManager";
	
	/**
	 * 发动机部试验工程师角色id
	 */
	public static final String EV_TRIALENGINEER_ROLEID = "4KUQ2UD9XS";

	/**
	 * 发动机部试验员id
	 */
	public static final String EV_RESTER_ROLEID = "DWYPPCD5J9";
	
	/**
	 * 发动机部班长id
	 */
	public static final String EV_MONITOR_ROLEID = "HNK6AFAEU4";

	/**
	 * 试验点检记录导出
	 */
	public static final String TRIAL_SPOTCHECK_EXPORT = "试验点检记录导出";

	/**
	 * 试验点检记录导出 -- 点检人
	 */
	public static final String TRIAL_SPOTCHECK_USER = "点检人";

	/**
	 * 试验点检记录导出  -- 点检时间
	 */
	public static final String TRIAL_SPOTCHECK_DATA = "点检时间";

	/**
	 * 试验点检记录导出 -- 点检类型
	 */
	public static final String TRIAL_SPOTCHECK_TYPE = "点检类型";

	/**
	 * 试验点检记录导出 -- 点检类型:台架搭建点检
	 */
	public static final String TRIAL_SPOTCHECK_TYPE_BENCH = "台架搭建点检";
	/**
	 * 试验点检记录导出 -- 点检类型:试验启动前点检
	 */
	public static final String TRIAL_SPOTCHECK_TYPE_STARTUP = "试验启动前点检";
	/**
	 * 试验点检记录导出 -- 点检类型:日常点检
	 */
	public static final String TRIAL_SPOTCHECK_TYPE_DAILY = "日常点检";
	/**
	 * 试验数据记录导出单位: rpm
	 */
	public static final String TRIALDATA_RPM = "rpm";
	/**
	 * 试验数据记录导出单位: Nm
	 */
	public static final String TRIALDATA_NM = "Nm";
	/**
	 * 试验数据记录导出单位: kW
	 */
	public static final String TRIALDATA_KW = "kW";

	/**
	 * 试验数据记录导出单位: kg/h
	 */
	public static final String TRIALDATA_KGH = "kg/h";

	/**
	 * 试验数据记录导出单位: g/kWh
	 */
	public static final String TRIALDATA_GKWH = "g/kWh";

	/**
	 * 试验数据记录导出单位: l/min
	 */
	public static final String TRIALDATA_LMIN = "L/min";
	/**
	 * 试验数据记录导出单位:degC
	 */
	public static final String TRIALDATA_DEGC = "degC";
	/**
	 * 试验数据记录导出单位: kPa
	 */
	public static final String TRIALDATA_KPA = "kPa";
	/**
	 * 试验数据记录导出单位: mbar
	 */
	public static final String TRIALDATA_MBAR = "mbar";

	/**
	 * 试验数据记录导出单位: %
	 */
	public static final String TRIALDATA_PERCENT = "%";
	/**
	 * 试验数据记录导出--累计台时 h
	 */
	public static final String TRIALDATA_TOTAL_HOURS = "累计台时 h";

	/**
	 * 试验数据记录导出--工况时间
	 */
	public static final String TRIALDATA_OPERATION_TIME = "工况时间";

	/**
	 * 试验数据记录导出 ---  转速rpm
	 */
	public static final String TRIALDATA_SPEED = "转速";
	public static final String TRIALDATA_SPEED_EN = "SPEED";
	/**
	 * 试验数据记录导出 ---扭矩Nm
	 */
	public static final String TRIALDATA_TORQUE = "扭矩";
	public static final String TRIALDATA_TORQUE_EN = "TORQUE";
	/**
	 * 试验数据记录导出--功率kW
	 */
	public static final String TRIALDATA_P = "功率";
	public static final String TRIALDATA_P_EN = "P";
	/**
	 * 试验数据记录导出--修正扭矩Nm
	 */
	public static final String TRIALDATA_T_EC = "修正扭矩";
	public static final String TRIALDATA_T_EC_EN = "T_EC";

	/**
	 * 试验数据记录导出--修正功率kW
	 */
	public static final String TRIALDATA_P_EC = "修正功率";
	public static final String TRIALDATA_P_EC_EN = "P_EC";

	/**
	 * 试验数据记录导出--燃油油耗量kg/h
	 */
	public static final String TRIALDATA_FB_RATE = "燃油油耗量";
	public static final String TRIALDATA_FB_RATE_EN = "FB_RATE";

	/**
	 * 试验数据记录导出--燃油消耗率g/kWh
	 */
	public static final String TRIALDATA_FUELCOSP = "燃油消耗率";
	public static final String TRIALDATA_FUELCOSP_EN = "FUELCOSP";

	/**
	 * 试验数据记录导出--活塞漏气量l/min
	 */
	public static final String TRIALDATA_BLOW_VAL = "活塞漏气量";
	public static final String TRIALDATA_BLOW_VAL_EN = "BLOW_VAL";
	/**
	 * 试验数据记录导出--出水温度degC
	 */
	public static final String TRIALDATA_TWO = "出水温度";
	public static final String TRIALDATA_TWO_EN = "TWO";

	/**
	 * 试验数据记录导出--出水压力kPa
	 */
	public static final String TRIALDATA_PWO = "出水压力";
	public static final String TRIALDATA_PWO_EN = "PWO";

	/**
	 * 试验数据记录导出--进水温度degC
	 */
	public static final String TRIALDATA_TWI = "进水温度";
	public static final String TRIALDATA_TWI_EN = "TWI";

	/**
	 * 试验数据记录导出--进水压力kPa
	 */
	public static final String TRIALDATA_PWI = "进水压力";
	public static final String TRIALDATA_PWI_EN = "PWI";

	/**
	 * 试验数据记录导出--排气温度degC
	 */
	public static final String TRIALDATA_T_EXH = "排气温度";
	public static final String TRIALDATA_T_EXH_EN = "T_EXH";

	/**
	 * 试验数据记录导出--排气背力kPa
	 */
	public static final String TRIALDATA_P_EXH = "排气背力";
	public static final String TRIALDATA_P_EXH_EN = "P_EXH";

	/**
	 * 试验数据记录导出--燃油温度degC
	 */
	public static final String TRIALDATA_T_FUEL = "燃油温度";
	public static final String TRIALDATA_T_FUEL_EN = "T_FUEL";
	/**
	 * 试验数据记录导出--燃油压力kPa
	 */
	public static final String TRIALDATA_P_FUEL = "燃油压力";
	public static final String TRIALDATA_P_FUEL_EN = "P_FUEL";
	/**
	 * 试验数据记录导出--进气温度degC
	 */
	public static final String TRIALDATA_T_INLET = "进气温度";
	public static final String TRIALDATA_T_INLET_EN = "T_inlet";
	/**
	 * 试验数据记录导出--进气压力kPa
	 */
	public static final String TRIALDATA_P_INLET = "进气压力";
	public static final String TRIALDATA_P_INLET_EN = "P_inlet";
	/**
	 * 试验数据记录导出--机油温度degC
	 */
	public static final String TRIALDATA_T_OIL = "机油温度";
	public static final String TRIALDATA_T_OIL_EN = "T_OIL";
	/**
	 * 试验数据记录导出--机油压力kPa
	 */
	public static final String TRIALDATA_P_OIL = "机油压力";
	public static final String TRIALDATA_P_OIL_EN = "P_OIL";
	/**
	 * 试验数据记录导出--曲轴箱压力kPa
	 */
	public static final String TRIALDATA_P_CRANK = "曲轴箱压力";
	public static final String TRIALDATA_P_CRANK_EN = "P_Crank";
	/**
	 * 试验数据记录导出--大气压力mbar
	 */
	public static final String TRIALDATA_P_AIR = "大气压力";
	public static final String TRIALDATA_P_AIR_EN = "P_AIR";

	/**
	 * 试验数据记录导出--大气温度degC
	 */
	public static final String TRIALDATA_T_AIR = "大气温度";
	public static final String TRIALDATA_T_AIR_EN = "T_AIR";

	/**
	 * 试验数据记录导出--大气湿度%
	 */
	public static final String TRIALDATA_PHI = "大气湿度";
	public static final String TRIALDATA_PHI_EN = "PHI";

	/**
	 * 试验数据记录导出--涡前排温degC
	 */
	public static final String TRIALDATA_T_EXH_WQ = "涡前排温degC";
	public static final String TRIALDATA_T_EXH_WQ_EN = "T_EXH_WQ";
	/**
	 * 试验数据记录导出--涡前排压kPa
	 */
	public static final String TRIALDATA_P_EXH_WQ = "涡前排压kPa";
	public static final String TRIALDATA_P_EXH_WQ_EN = "P_EXH_WQ";
	/**
	 * 试验数据记录导出--催后排温degC
	 */
	public static final String TRIALDATA_T_EXH_CH = "催后排温";
	public static final String TRIALDATA_T_EXH_CH_EN = "T_EXH_CH";
	/**
	 * 试验数据记录导出--催后排压kPa
	 */
	public static final String TRIALDATA_P_EXH_CH = "催后排压kPa";
	public static final String TRIALDATA_P_EXH_CH_EN = "P_EXH_CH";
	/**
	 * 试验数据记录导出--一缸排温degC
	 */
	public static final String TRIALDATA_T_EX_C_ONE = "一缸排温";
	public static final String TRIALDATA_T_EX_C_ONE_EN = "T_EX_C01";
	/**
	 * 试验数据记录导出--二缸排温degC
	 */
	public static final String TRIALDATA_T_EX_C_TWO = "二缸排温";
	public static final String TRIALDATA_T_EX_C_TWO_EN = "T_EX_C02";
	/**
	 * 试验数据记录导出--三缸排温degC
	 */
	public static final String TRIALDATA_T_EX_C_THREE = "三缸排温";
	public static final String TRIALDATA_T_EX_C_THREE_EN = "T_EX_C03";
	/**
	 * 试验数据记录导出--四缸排温degC
	 */
	public static final String TRIALDATA_T_EX_C_FOUR = "四缸排温";
	public static final String TRIALDATA_T_EX_C_FOUR_EN = "T_EX_C04";
	/**
	 * 试验数据记录导出--中冷进气温degC
	 */
	public static final String TRIALDATA_T_IC_I = "中冷进气温";
	public static final String TRIALDATA_T_IC_I_EN = "T_IC_I";
	/**
	 * 试验数据记录导出--中冷进气压kPa
	 */
	public static final String TRIALDATA_P_IC_I = "中冷进气压";
	public static final String TRIALDATA_P_IC_I_EN = "P_IC_I";
	/**
	 * 试验数据记录导出--中冷出气温degC
	 */
	public static final String TRIALDATA_T_IC_O = "中冷出气温";
	public static final String TRIALDATA_T_IC_O_EN = "T_IC_O";
	/**
	 * 试验数据记录导出--中冷出气压kPa
	 */
	public static final String TRIALDATA_P_IC_O = "中冷出气压";
	public static final String TRIALDATA_P_IC_O_EN = "P_IC_O";
	/**
	 * 试验数据记录导出--油门开度%
	 */
	public static final String TRIALDATA_ALPHA = "油门开度";
	public static final String TRIALDATA_ALPHA_EN = "ALPHA";
	/**
	 * 试验数据记录导出
	 */
	public static final String TRIALDATA_EXPORT = "试验数据记录导出";
	/**
	 * 试验问题记录导出
	 */
	public static final String TRIALPROBLEM_EXPORT = "试验问题记录导出";

	/**
	 * 试验问题记录导出--序号
	 */
	public static final String TRIALPROBLEM_NO = "序号";

	/**
	 * 试验问题记录导出--试验任务书编号
	 */
	public static final String TRIALPROBLEM_EVNUMBER = "试验任务书编号";

	/**
	 * 试验问题记录导出--试验任务书名称
	 */
	public static final String TRIALPROBLEM_EVTITLE = "试验任务书名称";

	/**
	 * 试验问题记录导出--发动机型号
	 */
	public static final String TRIALPROBLEM_ENGINE_MODEL = "发动机型号";

	/**
	 * 试验问题记录导出--发动机开发阶段
	 */
	public static final String TRIALPROBLEM_ENGINE_DEVELOP_STAGE = "发动机开发阶段";

	/**
	 *试验问题记录导出--发动机编号
	 */
	public static final String TRIALPROBLEM_ENGINE_NUMBER = "发动机编号";

	/**
	 * 试验问题记录导出--试验项目
	 */
	public static final String TRIALPROBLEM_INSPRO_NAMES = "试验项目";

	/**
	 * 试验问题记录导出--台架编号
	 */
	public static final String TRIALPROBLEM_ORG_NAMES = "台架编号";
	/**
	 * 试验问题记录导出--试验负责人
	 */
	public static final String TRIALPROBLEM_TRIAL_ENGINEER = "试验负责人";

	/**
	 * 试验问题记录导出--试验运行时长/(小时)
	 */
	public static final String TRIALPROBLEM_RUN_HOURS = "试验运行时长/(小时)";
	/**
	 * 试验问题记录导出--出现故障日期
	 */
	public static final String TRIALPROBLEM_FAULT_TIME = "出现故障日期";
	/**
	 * 试验问题记录导出--零件使用时长/(小时)
	 */
	public static final String TRIALPROBLEM_PART_USE_HOURS = "零件使用时长/(小时)";
	/**
	 * 试验问题记录导出--故障停机时长(小时)
	 */
	public static final String TRIALPROBLEM_FAULT_STOP_HOURS = "故障停机时长(小时)";
	/**
	 * 试验问题记录导出--该故障件在本试验项目累计出现故障次数
	 */
	public static final String TRIALPROBLEM_FAULT_NUM = "该故障件在本试验项目累计出现故障次数";
	/**
	 * 试验问题记录导出--故障类型
	 */
	public static final String TRIALPROBLEM_FAULT_TYPE = "故障类型";
	/**
	 * 试验问题记录导出--故障总成部件名称
	 */
	public static final String TRIALPROBLEM_FAULT_PART_NAME = "故障总成部件名称";
	/**
	 * 试验问题记录导出--故障件图号
	 */
	public static final String TRIALPROBLEM_FAULT_PART_PICNO = "故障件图号";
	/**
	 * 试验问题记录导出--ECU软件状态
	 */
	public static final String TRIALPROBLEM_ECU_STATUS = "ECU软件状态";
	/**
	 * 试验问题记录导出--台架搭建状态
	 */
	public static final String TRIALPROBLEM_SCAFFOLDING_STATUS = "台架搭建状态";
	/**
	 * 试验问题记录导出--故障描述
	 */
	public static final String TRIALPROBLEM_FAULT_DESCRIPTION = "故障描述";
	/**
	 * 试验问题记录导出---故障录入人
	 */
	public static final String TRIALPROBLEM_CREATEBY_NAME = "故障录入人";
	/**
	 * 试验问题记录导出---故障录入时间
	 */
	public static final String TRIALPROBLEM_CREATE_TIME = "故障录入时间";

	/**
	 * 试验问题记录导出--原因分析
	 */
	public static final String TRIALPROBLEM_REASON = "原因分析";
	/**
	 * 试验问题记录导出--故障等级
						A:致命故障
						B:严重故障
						C:一般故障
						D:轻微故障
	 */
	public static final String TRIALPROBLEM_FAULT_LEVEL = "故障等级\r\n" +
														  "A:致命故障\r\n" +
														  "B:严重故障\r\n" +
														  "C:一般故障\r\n" +
														  "D:轻微故障";
	/**
	 * 试验问题记录导出--临时措施及时间
	 */
	public static final String TRIALPROBLEM_TEMP_MEASURES = "临时措施及时间";
	/**
	 * 试验问题记录导出--临时措施负责人
	 */
	public static final String TRIALPROBLEM_TEMP_MEASURES_PERSON = "临时措施负责人";
	/**
	 * 试验问题记录导出--临时措施整改效果确认及时间(增加"待确认")
	 */
	public static final String TRIALPROBLEM_TEMP_MEASURES_RESULT = "临时措施整改效果确认及时间";
	/**
	 * 试验问题记录导出--长期解决措施及时间
	 */
	public static final String TRIALPROBLEM_PERM_MEASURES_DATE = "长期解决措施及时间";
	/**
	 * 试验问题记录导出--长期措施计划完成时间(日期)
	 */
	public static final String TRIALPROBLEM_PERM_MEASURES_FINISHDATE = "长期措施计划完成时间(日期)";
	/**
	 * 试验问题记录导出---长期解决措施效果确认结果及时间(正常、无效、待确认)
	 */
	public static final String TRIALPROBLEM_PERM_MEASURES_RESULT = "长期解决措施效果确认结果及时间(正常、无效、待确认)";
	/**
	 * 试验问题记录导出---长期措施负责人
	 */
	public static final String TRIALPROBLEM_PERM_MEASURES_PERSON = "长期措施负责人";
	/**
	 * 试验问题记录导出---是否移交横向科室
	 */
	public static final String TRIALPROBLEM_IS_TRANSFER = "是否移交横向科室";
	/**
	 * 试验问题记录导出--对策责任科室
	 */
	public static final String TRIALPROBLEM_RESPONSIBLE_DEPART = "对策责任科室";
	/**
	 * 试验问题记录导出--责任人
	 */
	public static final String TRIALPROBLEM_RESPONSIBLE_PERSON = "责任人";
	/**
	 * 试验问题记录导出--是否申请关闭
	 */
	public static final String TRIALPROBLEM_IS_CLOSE = "是否申请关闭";
	/**
	 * 试验问题记录导出--问题关闭时间
	 */
	public static final String TRIALPROBLEM_CLOSE_DATE = "问题关闭时间";
	/**
	 * 前
	 */
	public static final String BEFORE = "before";
	/**
	 * 后
	 */
	public static final String AFTER = "after";

	/**
	 * YES
	 */
	public static final String YES = "Yes";
	/**
	 * NO
	 */
	public static final String NO = "No";
	/**
	 * UTF-8
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 委外试验委托单pv
	 */
	public static final String PV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE = "Pv_Out_Entrust";

	/**
	 * 委外试验委托单cv
	 */
	public static final String CV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE = "Cv_Out_Entrust";


	public static ConcurrentMap<String, String> DELAYMAILMAP = new ConcurrentHashMap<String, String>();
	/**
	 * 试验人员及住宿预算
	 */
	public static final String PC_BUDGET_PERSON = "PCBudgetPerson";
	/**
	 * 试验补贴预算
	 */
	public static final String PC_BUDGET_SUBSIDY = "PCBudgetSubsidy";
	/**
	 * 试验费用预算
	 */
	public static final String PC_BUDGET_TEST = "PCBudgetTest";

	/**
	 * PV性能试验-费用预算流程
	 */
	public static final String PV_BUDGET_PROCESS = "PVBudgetProcess";

	/**
	 * CV性能试验-费用预算流程
	 */
	public static final String CV_BUDGET_PROCESS = "CVBudgetProcess";

	/**
	 * PV 可靠性立项单
	 */
	public static final String PV_RELIABLE_INITTASK_TYPE = "PV_Reliable_InitTask";
	/**
	 * CV 可靠性立项单
	 */
	public static final String CV_RELIABLE_INITTASK_TYPE = "CV_Reliable_InitTask";
	/**
	 * pc 可靠性立项单编号
	 */
	public static final String PC_RELIABLE_INITTASK_NO = "PC_RELIABLE_INITTASK_NO";

	/**
	 * 可靠性立项单编号前缀
	 */
	public static final String PC_RELIABLE_INITTASK_NO_KKXLX = "KKXLX";

	/**
	 * PV试验工程师角色id
	 */
	public static final String PV_TRIALENGINEER_ROLEID = "SSVZATEBLH";
	/**
	 * PV主管角色id
	 */
	public static final String PV_CHARGE_ROLEID = "S8K2K68R6C";
	/**
	 * PV科长id
	 */
	public static final String PV_SECTIONCHIEF_ROLEID = "7REP5V9SB5";
	/**
	 * PV部长角色id
	 */
	public static final String PV_MINISTER_ROLEID = "SUG6VQZ3R3";
	/**
	 * PV道路试验员
	 */
	public static final String PV_ROADRESTER_ROLEID = "J9AZ67NFHG";
	
	
	public static final String SUP_APPOINT_STAFF = "6KYNLTZP85";
	
	/**
	 * CV试验工程师角色id
	 */
	public static final String CV_TRIALENGINEER_ROLEID = "65PTRDCK3S";
	/**
	 * CV主管角色id
	 */
	public static final String CV_CHARGE_ROLEID = "6GMXW2BEXZ";
	/**
	 * CV科长角色id
	 */
	public static final String CV_SECTIONCHIEF_ROLEID = "ZAXHNDTMCR";
	/**
	 * CV部长角色id
	 */
	public static final String CV_MINISTER_ROLEID = "V5ENRMSD5R";
	/**
	 * CV试验技能员
	 */
	public static final String CV_RESTER_ROLEID = "M3YMX76KP6";
	
	
	/**
	 * EV
	 */
	public static final String EV = "EV";
	/**
	 * PV
	 */
	public static final String PV = "PV";
	/**
	 * CV
	 */
	public static final String CV = "CV";
	/**
	 * 磨合行驶
	 */
	public static final String GRINDINGIN = "GrindingIn";
	/**
	 * 一般公路试验
	 */
	public static final String GENERALHIGHWAY = "GeneralHighway";
	/**
	 * 市区+阻滞试验
	 */
	public static final String URBANBLOCK = "UrbanBlock";
	/**
	 * 高速路试验
	 */
	public static final String HIGHSPEED = "HighSpeed";
	/**
	 * 山区公路试验
	 */
	public static final String MOUNTAINROAD = "MountainRoad";
	/**
	 * (强化)环路试验
	 */
	public static final String RINGROAD = "RingRoad";
	/**
	 * 越野路试试验
	 */
	public static final String CROSSCOUNTRY = "CrossCountry";
	/**
	 * 微丘高速
	 */
	public static final String HILLOCKHIGH = "HillockHigh";
	/**
	 * 山区重丘高速
	 */
	public static final String MOUNTAINOUSHILLY = "MountainousHilly";
	/**
	 * 符号  -
	 */
	public static final String HYPHEN = "-";
	/**
	 * pv借车申请单
	 */
	public static final String PC_CAR_LOAN_FROM_TYPE = "PvCarLoanFrom";
	/**
	 * cv借车申请单
	 */
	public static final String CV_CAR_LOAN_FROM_TYPE = "CvCarLoanFrom";

    /**
     * pv领料单
     */
	public static final String PV_PARTS_APPLY = "PvPartsApply";

    /**
     * pv领料单
     */
    public static final String CV_PARTS_APPLY = "CvPartsApply";

	/**
	 * 委外试验委托单编号
	 */
	public static final String OUT_TRUST_NO = "OUT_TRUST_NO";
	/**
	 * 整车出门条编号
	 */
	public static final String PC_CLCMT_NO = "CLCMT-";
	/**
	 * pc  整车出门条编号
	 */
	public static final String PC_VEHICLE_NO = "PC_VEHICLE_NO";
	/**
	 * PV 整车出门条
	 */
	public static final String PV_VEHICLE_OUT_TYPE = "PV_Vehicle_Out";
	/**
	 * CV 整车出门条
	 */
	public static final String CV_VEHICLE_OUT_TYPE = "CV_Vehicle_Out";
	/**
	 * PC 整车出门条
	 */
	public static final String PC_VEHICLE_OUT_BUSINESS_TYPE = "PC_Vehicle_Out";
	/**
	 * 物资出门条编号
	 */
	public static final String PC_WZCMT_NO = "WZCMT-";
	/**
	 * pc   物资出门条编号
	 */
	public static final String PC_MATERIALS_NO = "PC_MATERIALS_NO";
	/**
	 * PV 物资出门条
	 */
	public static final String PV_MATERIALS_OUT_TYPE = "PV_Materials_Out";
	/**
	 * CV 物资出门条
	 */
	public static final String CV_MATERIALS_OUT_TYPE = "CV_Materials_Out";
	/**
	 * PC 物资出门条
	 */
	public static final String PC_MATERIALS_OUT_BUSINESS_TYPE = "PC_Materials_Out";

	/**
	 * pv还车单
	 */
	public static final String PV_CAR_RETURN = "PvCarReturn";

	/**
	 * pv还车单
	 */
	public static final String CV_CAR_RETURN = "CvCarReturn";

	/**
	 * 公章code
	 */
	public static final String STAMP = "Stamp";

	/**
	 * 领料单编号头
	 */
	public static final String PC_PARTS_APPLY_CODE = "LLD";

	/**
	 * 领料单流水号code
	 */
	public static final String PC_PARTS_APPLY = "PC_PARTS_APPLY";

	/**
	 * 存储移动端设备登录的设备ID，用来发送个推
	 */
	public static Map<String, String> CLIENT_MAP = new ConcurrentHashMap();

    /**
     * 用户登录记录
     */
    public static Map<String, String> LOGIN_USER_MAP = new ConcurrentHashMap();

	/**
	 * 乘用车品质保证部  1  PV
	 */
	public static String PV_ORG_ID = "A2TUTPXC5Q";

	/**
	 * 商用车品质保证部  0  CV
	 */
	public static String CV_ORG_ID = "GGZS3PQ7XM";
	
	/**
	 * 发动机部  2 EV
	 */
	public static String EV_ORG_ID = "8R63YCEUW2";
	
    /**
     * 设备管理借用流程类型
     */
    public static final String Equipment_Borrow_TYPE = "EquipmentBorrow";
    /**
     * 设备管理归还流程类型
     */
    public static final String Equipment_Return_TYPE = "EquipmentReturn";

    public static final String PC_CAR_RETURN_CODE = "PC_CAR_RETURN_CODE";

	/**
	 * 用户问题
	 */
    public static final String QUESTION_USER_CODE = "QuestionUser";

	/**
	 * pv试验申请变更
	 */
	public static final String PC_TRIAL_TASK_CHANGE = "PCTrialTaskChange";
    /**
     * cv试验申请变更
     */
    public static final String CV_TRIAL_TASK_CHANGE = "CVTrialTaskChange";
	
	/**
	 * pc QCDD编号
	 */
	public static final String PC_QCDD_NO = "PC_QCDD_NO";
	
	/**
	 * QCDD编号字段补0
	 */
	public static final String PC_QCDD_NO_RULE = "%04d";
	
	/**
	 * QCDD编号
	 */
	public static final String PC_QCDD = "QCDD";
	/**
	 * PV QCDD流程类型
	 */
	public static final String PV_QCDD_TYPE = "PV_QCDD";
	/**
	 * CV QCDD流程类型
	 */
	public static final String CV_QCDD_TYPE = "CV_QCDD";

    /**
     * PV费用结算单流程
     */
    public static final String Pc_Cost_Payment_TYPE = "PcCostPayment";
    /**
     * CV费用结算单流程
     */
    public static final String Cv_Cost_Payment_TYPE = "CvCostPayment";
    /**
     * 结算单详情
     */
    public static final String Cost_Payment_TYPE = "Cost_Payment_TYPE";

}
