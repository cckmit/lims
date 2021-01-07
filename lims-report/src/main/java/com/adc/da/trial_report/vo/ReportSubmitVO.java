package com.adc.da.trial_report.vo;

import io.swagger.annotations.ApiModelProperty;

public class ReportSubmitVO {

	@ApiModelProperty(" 报告id ")
	private String reportId;
	
	@ApiModelProperty(" 报告类型 ")
	private String reportType;
	
	@ApiModelProperty(" 下一节点审批人 ")
	private String nextAssignee;
	
	@ApiModelProperty(" 流程实例id ")
	private String procId;
	
	@ApiModelProperty(" 流程业务id ")
	private String baseBusId;
	
	@ApiModelProperty(" 节点id ")
	private String taskId;
	
	@ApiModelProperty(" 类型 ")
	private String type;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getNextAssignee() {
		return nextAssignee;
	}

	public void setNextAssignee(String nextAssignee) {
		this.nextAssignee = nextAssignee;
	}

	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}

	public String getBaseBusId() {
		return baseBusId;
	}

	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
