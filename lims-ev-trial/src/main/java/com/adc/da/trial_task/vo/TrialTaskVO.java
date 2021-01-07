package com.adc.da.trial_task.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TrialTaskVO {
    @ApiModelProperty("主键id")
    private String id;
    /*
     * @Author syxy_zhangyinghui
     *  * @Date 16:38 2019/8/19
     **/
    @ApiModelProperty("发动机试验任务书编号")
    private String evNumber;
    /*
     * @Author syxy_zhangyinghui
     *  * @Date 16:38 2019/8/19
     **/
    @ApiModelProperty("试验任务书附件id")
    private String taskFileid;
    /*
     * @Author syxy_zhangyinghui
     *  * @Date 16:43 2019/8/19
     **/
    @ApiModelProperty("计划完成日期")
    private String finishTime;
    /*
     * @Author syxy_zhangyinghui
     *  * @Date 16:43 2019/8/19
     **/
    @ApiModelProperty("任务名称")
    private String title;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:44
     **/
    @ApiModelProperty("根据")
    private String reason;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("依据")
    private String according;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("目的")
    private String purpose;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("发动机型号")
    private String engineModel;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("发动机开发阶段")
    private String engineDevelopStage;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("发动机编号")
    private String engineNumber;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:45
     **/
    @ApiModelProperty("ECU状态")
    private String ecuStatus;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:48
     **/
    @ApiModelProperty("操作规范附件id")
    private String operationFileid;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:48
     **/
    @ApiModelProperty("当前待办人ID")
    private String currentWaitUserid;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:48
     **/
    @ApiModelProperty("试验状态")
    private String trialStatus;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:49
     **/
    @ApiModelProperty("计划完成延期天数")
    private String finishdateDelydays;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:49
     **/
    @ApiModelProperty("当前待办人名称")
    private String currentWaitUsername;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:49
     **/
    @ApiModelProperty("发起人")
    private String createBy;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:49
     **/
    @ApiModelProperty("发起时间")
    private String createTime;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:50
     **/
    @ApiModelProperty("更新人")
    private String updateBy;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:50
     **/
    @ApiModelProperty("更新时间")
    private String updateTime;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:51
     **/
    @ApiModelProperty("删除状态")
    private String delFlag;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:52
     **/
    @ApiModelProperty("备注")
    private String remark;


    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/20 9:48
     * 试验任务检验项目信息
     **/
    private List<TrialtaskInsproVO> trialtaskInsproEOList = new ArrayList<>();

    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/20 9:49
     * 试验任务样品信息
     **/
    private List<TrialtaskSampleVO> trialtaskSampleEOList = new ArrayList<>();

    @ApiModelProperty("试验任务书附件名称")
    private String taskFileName;
    
    @ApiModelProperty("操作规范附件名")
    private String operationFileName;
    
    
    @ApiModelProperty("紧急程度")
    private String evEmergency;
    
    @ApiModelProperty("试验要求")
    private String requirement;
    
    @ApiModelProperty("下一节点审批人")
    private String nextAssignee;
    
    public String getEvEmergency() {
		return evEmergency;
	}

	public void setEvEmergency(String evEmergency) {
		this.evEmergency = evEmergency;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
    public String getTaskFileName() {
		return taskFileName;
	}

	public void setTaskFileName(String taskFileName) {
		this.taskFileName = taskFileName;
	}

	public String getOperationFileName() {
		return operationFileName;
	}

	public void setOperationFileName(String operationFileName) {
		this.operationFileName = operationFileName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvNumber() {
        return evNumber;
    }

    public void setEvNumber(String evNumber) {
        this.evNumber = evNumber;
    }

    public String getTaskFileid() {
        return taskFileid;
    }

    public void setTaskFileid(String taskFileid) {
        this.taskFileid = taskFileid;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAccording() {
        return according;
    }

    public void setAccording(String according) {
        this.according = according;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	public String getEngineDevelopStage() {
        return engineDevelopStage;
    }

    public void setEngineDevelopStage(String engineDevelopStage) {
        this.engineDevelopStage = engineDevelopStage;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getEcuStatus() {
        return ecuStatus;
    }

    public void setEcuStatus(String ecuStatus) {
        this.ecuStatus = ecuStatus;
    }

    public String getOperationFileid() {
        return operationFileid;
    }

    public void setOperationFileid(String operationFileid) {
        this.operationFileid = operationFileid;
    }

    public String getCurrentWaitUserid() {
        return currentWaitUserid;
    }

    public void setCurrentWaitUserid(String currentWaitUserid) {
        this.currentWaitUserid = currentWaitUserid;
    }

    public String getTrialStatus() {
        return trialStatus;
    }

    public void setTrialStatus(String trialStatus) {
        this.trialStatus = trialStatus;
    }

    public String getFinishdateDelydays() {
        return finishdateDelydays;
    }

    public void setFinishdateDelydays(String finishdateDelydays) {
        this.finishdateDelydays = finishdateDelydays;
    }

    public String getCurrentWaitUsername() {
        return currentWaitUsername;
    }

    public void setCurrentWaitUsername(String currentWaitUsername) {
        this.currentWaitUsername = currentWaitUsername;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public List<TrialtaskInsproVO> getTrialtaskInsproEOList() {
		return trialtaskInsproEOList;
	}

	public void setTrialtaskInsproEOList(List<TrialtaskInsproVO> trialtaskInsproEOList) {
		this.trialtaskInsproEOList = trialtaskInsproEOList;
	}

	public List<TrialtaskSampleVO> getTrialtaskSampleEOList() {
		return trialtaskSampleEOList;
	}

	public void setTrialtaskSampleEOList(List<TrialtaskSampleVO> trialtaskSampleEOList) {
		this.trialtaskSampleEOList = trialtaskSampleEOList;
	}

	public String getNextAssignee() {
		return nextAssignee;
	}

	public void setNextAssignee(String nextAssignee) {
		this.nextAssignee = nextAssignee;
	}
	
    
}
