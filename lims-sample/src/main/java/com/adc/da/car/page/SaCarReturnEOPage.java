package com.adc.da.car.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

import io.swagger.annotations.ApiModelProperty;

public class SaCarReturnEOPage extends BasePage {
	
	

	/**
	 * 还车单id
	 */
	
	@ApiModelProperty(value = "还车单id")
    private String returnId;
	/**
	 * 任务单id
	 */
	
	@ApiModelProperty(value = "任务单id")
    private String taskId;
	/**
	 * 任务单编号
	 */
	
	@ApiModelProperty(value = "任务单编号")
    private String taskNum;

    /**
     * 还车单编号
     */
    @ApiModelProperty(value = "归还单编号")
    private String returnNum;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String applicant;
    
    /**
     * 归还时间
     */
    @ApiModelProperty(value = "完成时间")
    private String endTime;
    
    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间1")
    private String endTime1;
    
    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间2")
    private String endTime2;
    
    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;
    
    
    
    

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}

	public String getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    
}
