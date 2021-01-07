package com.adc.da.trial_task.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【CC_TRIAL_TASK】 功能【试验任务表】
 * @Date 16:35 2019/8/19
 **/
public class TrialTaskEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8379820813415372836L;

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
     *  0-草稿,1-审批中,2-退回,3-已审批,4-已取消(暂使用不到),5-试验中,6-试验结束,7-报告审批中,8-试验完成,9-撤回
     **/
    @ApiModelProperty("试验状态")
    private String trialStatus;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:49
     **/
    @ApiModelProperty("计划完成延期天数")
    private Integer finishdateDelydays;
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
    
    /**
     * 0 未完成  1 已完成
     */
    @ApiModelProperty("试验整体状态")
    private String trialReportStatus;


    /**
     * 0:未延期   1：预警   2：延期
     */
    @ApiModelProperty("延期状态")
    private String delyFlag;
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
    private List<TrialtaskInsproEO> trialtaskInsproEOList = new ArrayList<>();

    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/20 9:49
     * 试验任务样品信息
     **/
    private List<TrialtaskSampleEO> trialtaskSampleEOList = new ArrayList<>();
    
    /**
     * 发起人名称
     */
    @ApiModelProperty("发起人名称")
    private String createByName;
    
    /**
     * 发起人部门id
     */
    @ApiModelProperty("发起人部门id")
    private String createByDepartId;
    /**
     * 发起人部门名称
     */
    @ApiModelProperty("发起人部门名称")
    private String createByDepartName;

    @ApiModelProperty("试验任务书附件名称")
    private String taskFileName;
    
    @ApiModelProperty("操作规范附件名")
    private String operationFileName;
    
    /**
     * actProcId 用于传流程图数据
     */
    private String actProcId;
    
    /**
     * 紧急程度 1 特急 2 紧急 3 一般
     */
    @ApiModelProperty("紧急程度") 
    private String evEmergency;
    
    @ApiModelProperty("试验要求")
    private String requirement;
    /**
     * baseBusId
     */
    private String baseBusId;
    /**
     * 台架对应人员ids
     */
    private String personIds;
    
    
    @ApiModelProperty(value = "台架号")
    private String scaffoldingName;
    
	public String getPersonIds() {
		return personIds;
	}
	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}
	public String getBaseBusId() {
		return baseBusId;
	}
	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}
    
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
	
	public String getActProcId() {
		return actProcId;
	}

	public void setActProcId(String actProcId) {
		this.actProcId = actProcId;
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

    public String getTrialReportStatus() {
		return trialReportStatus;
	}

	public void setTrialReportStatus(String trialReportStatus) {
		this.trialReportStatus = trialReportStatus;
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

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByDepartId() {
		return createByDepartId;
	}

	public void setCreateByDepartId(String createByDepartId) {
		this.createByDepartId = createByDepartId;
	}

	public String getCreateByDepartName() {
		return createByDepartName;
	}

	public void setCreateByDepartName(String createByDepartName) {
		this.createByDepartName = createByDepartName;
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

    public Integer getFinishdateDelydays() {
        return finishdateDelydays;
    }

    public void setFinishdateDelydays(Integer finishdateDelydays) {
        this.finishdateDelydays = finishdateDelydays;
    }

    public String getDelyFlag() {
        return delyFlag;
    }

    public void setDelyFlag(String delyFlag) {
        this.delyFlag = delyFlag;
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

    public List<TrialtaskInsproEO> getTrialtaskInsproEOList() {
        return trialtaskInsproEOList;
    }

    public void setTrialtaskInsproEOList(List<TrialtaskInsproEO> trialtaskInsproEOList) {
        this.trialtaskInsproEOList = trialtaskInsproEOList;
    }

    public List<TrialtaskSampleEO> getTrialtaskSampleEOList() {
        return trialtaskSampleEOList;
    }

    public void setTrialtaskSampleEOList(List<TrialtaskSampleEO> trialtaskSampleEOList) {
        this.trialtaskSampleEOList = trialtaskSampleEOList;
    }
	public String getScaffoldingName() {
		return scaffoldingName;
	}
	public void setScaffoldingName(String scaffoldingName) {
		this.scaffoldingName = scaffoldingName;
	}
    
    
}
