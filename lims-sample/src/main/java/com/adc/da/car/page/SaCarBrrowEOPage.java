package com.adc.da.car.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

import io.swagger.annotations.ApiModelProperty;
/**
 * 借车单
 * @author zhangfeng
 *
 */
public class SaCarBrrowEOPage extends BasePage {
	
	
	/**
	 * 借车单id
	 */
	
	@ApiModelProperty(value = "借车单id")
    private String brrowId;
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
     * 借车单编号
     */
    @ApiModelProperty(value = "借车单编号")
    private String brrowNum;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String applicant;
    
    /**
     * 完成时间
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
    private List<String> searchPhrase;;
    

    
    
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

	public String getBrrowId() {
		return brrowId;
	}

	public void setBrrowId(String brrowId) {
		this.brrowId = brrowId;
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

	public String getBrrowNum() {
		return brrowNum;
	}

	public void setBrrowNum(String brrowNum) {
		this.brrowNum = brrowNum;
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
