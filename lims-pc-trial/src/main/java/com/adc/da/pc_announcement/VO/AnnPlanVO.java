package com.adc.da.pc_announcement.VO;

import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class AnnPlanVO {
    @ApiModelProperty("修改时间")
    private String plUpdateTime;
    @ApiModelProperty("修改人ID")
    private String plUpdateBy;
    @ApiModelProperty("计划状态  0：未开始 1：进行中 2：已完成")
    private String plStatus;
    @ApiModelProperty("附件名称")
    private String plAttachmentName;
    @ApiModelProperty("附件ID")
    private String plAttachmentId;
    @ApiModelProperty("创建日期")
    private String plCreateDate;
    @ApiModelProperty("创建人姓名")
    private String plCreateName;
    @ApiModelProperty("创建人ID")
    private String plCreateBy;
    @ApiModelProperty("申报来源编号")
    private String plNo;
    @ApiModelProperty("计划名称")
    private String plSource;
    @ApiModelProperty("计划名称")
    private String plName;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("申报项目列表")
    private List<AnnPlanProjectEO> annPlanProjectList = new ArrayList<>();

    @ApiModelProperty("任务说明")
    private String pjTaskExplain;
    
    
    
    public String getPjTaskExplain() {
		return pjTaskExplain;
	}

	public void setPjTaskExplain(String pjTaskExplain) {
		this.pjTaskExplain = pjTaskExplain;
	}

	public String getPlUpdateTime() {
        return plUpdateTime;
    }

    public void setPlUpdateTime(String plUpdateTime) {
        this.plUpdateTime = plUpdateTime;
    }

    public String getPlUpdateBy() {
        return plUpdateBy;
    }

    public void setPlUpdateBy(String plUpdateBy) {
        this.plUpdateBy = plUpdateBy;
    }

    public String getPlStatus() {
        return plStatus;
    }

    public void setPlStatus(String plStatus) {
        this.plStatus = plStatus;
    }

    public String getPlAttachmentName() {
        return plAttachmentName;
    }

    public void setPlAttachmentName(String plAttachmentName) {
        this.plAttachmentName = plAttachmentName;
    }

    public String getPlAttachmentId() {
        return plAttachmentId;
    }

    public void setPlAttachmentId(String plAttachmentId) {
        this.plAttachmentId = plAttachmentId;
    }

    public String getPlCreateDate() {
        return plCreateDate;
    }

    public void setPlCreateDate(String plCreateDate) {
        this.plCreateDate = plCreateDate;
    }

    public String getPlCreateName() {
        return plCreateName;
    }

    public void setPlCreateName(String plCreateName) {
        this.plCreateName = plCreateName;
    }

    public String getPlCreateBy() {
        return plCreateBy;
    }

    public void setPlCreateBy(String plCreateBy) {
        this.plCreateBy = plCreateBy;
    }

    public String getPlNo() {
        return plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getPlSource() {
        return plSource;
    }

    public void setPlSource(String plSource) {
        this.plSource = plSource;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AnnPlanProjectEO> getAnnPlanProjectList() {
        return annPlanProjectList;
    }

    public void setAnnPlanProjectList(List<AnnPlanProjectEO> annPlanProjectList) {
        this.annPlanProjectList = annPlanProjectList;
    }
}
