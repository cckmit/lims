package com.adc.da.trial_task.vo;

import io.swagger.annotations.ApiModelProperty;

public class TrialtaskSampleVO {

    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:00
     **/
    @ApiModelProperty("主键id")
    private String id;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:01
     **/
    @ApiModelProperty("试验任务书id")
    private String trialtaskId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:04
     **/
    @ApiModelProperty("样品id")
    private String sampleId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:04
     * car-整车   part-零部件
     **/
    @ApiModelProperty("样品类型")
    private String sampleType;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("负责台架id")
    private String scaffoldingId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("发动机型号")
    private String sampleEngineType;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("样品生产日期")
    private String sampleProduceTime;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("样品编号")
    private String sampleNo;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/20 10:39
     * 负责台架信息  ----关联组织机构
     **/

    @ApiModelProperty("台架名称")
    private String scaffoldingName;
    
    @ApiModelProperty("备注")
    private String remark;
    
    
    public String getScaffoldingName() {
		return scaffoldingName;
	}

	public void setScaffoldingName(String scaffoldingName) {
		this.scaffoldingName = scaffoldingName;
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

    public String getSampleId() {
        return sampleId;
    }

    public String getTrialtaskId() {
		return trialtaskId;
	}

	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
	}

	public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getScaffoldingId() {
        return scaffoldingId;
    }

    public void setScaffoldingId(String scaffoldingId) {
        this.scaffoldingId = scaffoldingId;
    }

    public String getSampleEngineType() {
        return sampleEngineType;
    }

    public void setSampleEngineType(String sampleEngineType) {
        this.sampleEngineType = sampleEngineType;
    }

    public String getSampleProduceTime() {
        return sampleProduceTime;
    }

    public void setSampleProduceTime(String sampleProduceTime) {
        this.sampleProduceTime = sampleProduceTime;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

}
