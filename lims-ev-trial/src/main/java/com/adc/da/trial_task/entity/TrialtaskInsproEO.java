package com.adc.da.trial_task.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/*
 * @Author syxy_zhangyinghui
 * @Description 表名【EV_TRIALTASK_INSPRO】 功能【EV试验任务检验项目关联表】
 * @Date 17:00 2019/8/19
 **/
public class TrialtaskInsproEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1443170421200316192L;

    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:55
     **/
    @ApiModelProperty("主键id")
    private String id;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:56
     **/
    @ApiModelProperty("检验项目id")
    private String insproId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:56
     **/
    @ApiModelProperty("试验任务书id")
    private String trialtaskId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:57
     * 0-无报告(默认)1-编辑报告2-审核报告3-档案室4-退回5-撤回
     **/
    @ApiModelProperty("报告状态")
    private String reportStatus;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:57
     **/
    @ApiModelProperty("报告完成时间")
    private String reportFinishTime;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 16:57
     **/
    @ApiModelProperty("检验标准")
    private String insproStandard;

    @ApiModelProperty("检验项目名")
    private String insproName;
    
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("报告id")
    private String reportId;

    
    
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getInsproName() {
		return insproName;
	}

	public void setInsproName(String insproName) {
		this.insproName = insproName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getInsproId() {
        return insproId;
    }

    public void setInsproId(String insproId) {
        this.insproId = insproId;
    }


    public String getTrialtaskId() {
        return trialtaskId;
    }

    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }


    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }


    public String getReportFinishTime() {
        return reportFinishTime;
    }

    public void setReportFinishTime(String reportFinishTime) {
        this.reportFinishTime = reportFinishTime;
    }


    public String getInsproStandard() {
        return insproStandard;
    }

    public void setInsproStandard(String insproStandard) {
        this.insproStandard = insproStandard;
    }
}
