package com.adc.da.trial_execute.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.trial_execute.vo.AttachmentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 点检记录
 * @author Administrator
 * @date 2019年9月11日
 */
public class TrialSpotCheckEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *附件id
     */
    private String fileId;

    private List<String> fileIdArray = new ArrayList<>();
    /**
     *点检类型  1-台架搭建点检
     *		2-试验启动前点检
     *		3-日常点检
     */
    private String type;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *更新者
     */
    private String updateBy;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *删除标记 0-未删除  1-删除
     */ 
    private String delFlag;

    /**
     *备注
     */
    private String remark;

    
//
//    /**
//     * 附件名称
//     */
//    private List<String> attachmentName;
//
//    /**
//     * 附件路径
//     */
//    private List<String> attachmentPath;
    private List<AttachmentVO> attachmentVOList = new ArrayList<>();
    /**
     * 创建人
     */
    private String createName;

	public List<AttachmentVO> getAttachmentVOList() {
		return attachmentVOList;
	}

	public List<String> getFileIdArray() {
		return fileIdArray;
	}

	public void setFileIdArray(List<String> fileIdArray) {
		this.fileIdArray = fileIdArray;
	}

	public void setAttachmentVOList(List<AttachmentVO> attachmentVOList) {
		this.attachmentVOList = attachmentVOList;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTrialtaskId() {
		return trialtaskId;
	}

	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
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


}