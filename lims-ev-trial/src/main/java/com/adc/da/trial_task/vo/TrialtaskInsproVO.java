package com.adc.da.trial_task.vo;

import io.swagger.annotations.ApiModelProperty;

public class TrialtaskInsproVO {

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
