package com.adc.da.pc_announcement.page;

import java.util.List;
import java.util.Map;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>ANN_PLAN AnnPlanEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AnnPlanEOPage extends BasePage {

    private String delFlag;
    private String delFlagOperator = "=";
    private String plUpdateTime;
    private String plUpdateTimeOperator = "=";
    private String plUpdateBy;
    private String plUpdateByOperator = "=";
    private String plStatus;
    private String plStatusOperator = "=";
    private String plAttachmentName;
    private String plAttachmentNameOperator = "=";
    private String plAttachmentId;
    private String plAttachmentIdOperator = "=";
    private String plCreateDate;
    private String plCreateDateOperator = "=";
    private String plCreateName;
    private String plCreateNameOperator = "=";
    private String plCreateBy;
    private String plCreateByOperator = "=";
    private String plNo;
    private String plNoOperator = "=";
    private String plSource;
    private String plSourceOperator = "=";
    private String plName;
    private String plNameOperator = "=";
    private String id;
    private String idOperator = "=";
    
    //前台传过来的通用查询条件
    private String searchPhrase;
    //解析后的通用查询条件
    private List<Map<String, Object>> searchMap;    

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getPlUpdateTime() {
        return this.plUpdateTime;
    }

    public void setPlUpdateTime(String plUpdateTime) {
        this.plUpdateTime = plUpdateTime;
    }

    public String getPlUpdateTimeOperator() {
        return this.plUpdateTimeOperator;
    }

    public void setPlUpdateTimeOperator(String plUpdateTimeOperator) {
        this.plUpdateTimeOperator = plUpdateTimeOperator;
    }

    public String getPlUpdateBy() {
        return this.plUpdateBy;
    }

    public void setPlUpdateBy(String plUpdateBy) {
        this.plUpdateBy = plUpdateBy;
    }

    public String getPlUpdateByOperator() {
        return this.plUpdateByOperator;
    }

    public void setPlUpdateByOperator(String plUpdateByOperator) {
        this.plUpdateByOperator = plUpdateByOperator;
    }

    public String getPlStatus() {
        return this.plStatus;
    }

    public void setPlStatus(String plStatus) {
        this.plStatus = plStatus;
    }

    public String getPlStatusOperator() {
        return this.plStatusOperator;
    }

    public void setPlStatusOperator(String plStatusOperator) {
        this.plStatusOperator = plStatusOperator;
    }

    public String getPlAttachmentName() {
        return this.plAttachmentName;
    }

    public void setPlAttachmentName(String plAttachmentName) {
        this.plAttachmentName = plAttachmentName;
    }

    public String getPlAttachmentNameOperator() {
        return this.plAttachmentNameOperator;
    }

    public void setPlAttachmentNameOperator(String plAttachmentNameOperator) {
        this.plAttachmentNameOperator = plAttachmentNameOperator;
    }

    public String getPlAttachmentId() {
        return this.plAttachmentId;
    }

    public void setPlAttachmentId(String plAttachmentId) {
        this.plAttachmentId = plAttachmentId;
    }

    public String getPlAttachmentIdOperator() {
        return this.plAttachmentIdOperator;
    }

    public void setPlAttachmentIdOperator(String plAttachmentIdOperator) {
        this.plAttachmentIdOperator = plAttachmentIdOperator;
    }

    public String getPlCreateDate() {
        return this.plCreateDate;
    }

    public void setPlCreateDate(String plCreateDate) {
        this.plCreateDate = plCreateDate;
    }

    public String getPlCreateDateOperator() {
        return this.plCreateDateOperator;
    }

    public void setPlCreateDateOperator(String plCreateDateOperator) {
        this.plCreateDateOperator = plCreateDateOperator;
    }

    public String getPlCreateName() {
        return this.plCreateName;
    }

    public void setPlCreateName(String plCreateName) {
        this.plCreateName = plCreateName;
    }

    public String getPlCreateNameOperator() {
        return this.plCreateNameOperator;
    }

    public void setPlCreateNameOperator(String plCreateNameOperator) {
        this.plCreateNameOperator = plCreateNameOperator;
    }

    public String getPlCreateBy() {
        return this.plCreateBy;
    }

    public void setPlCreateBy(String plCreateBy) {
        this.plCreateBy = plCreateBy;
    }

    public String getPlCreateByOperator() {
        return this.plCreateByOperator;
    }

    public void setPlCreateByOperator(String plCreateByOperator) {
        this.plCreateByOperator = plCreateByOperator;
    }

    public String getPlNo() {
        return this.plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getPlNoOperator() {
        return this.plNoOperator;
    }

    public void setPlNoOperator(String plNoOperator) {
        this.plNoOperator = plNoOperator;
    }

    public String getPlSource() {
        return this.plSource;
    }

    public void setPlSource(String plSource) {
        this.plSource = plSource;
    }

    public String getPlSourceOperator() {
        return this.plSourceOperator;
    }

    public void setPlSourceOperator(String plSourceOperator) {
        this.plSourceOperator = plSourceOperator;
    }

    public String getPlName() {
        return this.plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public String getPlNameOperator() {
        return this.plNameOperator;
    }

    public void setPlNameOperator(String plNameOperator) {
        this.plNameOperator = plNameOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public List<Map<String, Object>> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(List<Map<String, Object>> searchMap) {
		this.searchMap = searchMap;
	}
    
    

}
