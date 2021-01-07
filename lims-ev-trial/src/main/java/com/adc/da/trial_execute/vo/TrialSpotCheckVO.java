package com.adc.da.trial_execute.vo;

import com.adc.da.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 点检记录
 * @author Administrator
 * @date 2019年9月11日
 */
public class TrialSpotCheckVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *附件id
     */
    private String fileId;

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
     * 附件名称
     */
    private List<String> attachmentName;

    /**
     * 附件路径
     */
    private List<String> attachmentPath;
	private List<AttachmentVO> attachmentVOList = new ArrayList<>();
    /**
     * 创建人
     */
    private String createName;




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

	public List<AttachmentVO> getAttachmentVOList() {
		return attachmentVOList;
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


}