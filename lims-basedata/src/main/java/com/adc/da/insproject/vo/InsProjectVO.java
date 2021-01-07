package com.adc.da.insproject.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.standard.vo.StandardSearchVO;

public class InsProjectVO {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 项目名称
	 */
    private String proName;
    /**
     * 项目代码
     */
    private String proCode;   
    /**
     * 试验方法
     */
    private String trialMethod;
    /**
     * 项目说明
     */
    private String proDesc;

    /**
     * 项目所属节点id
     */
    private String proBelongId;
    /**
     * 负责试验室(组织)
     */
    private String labId;
    /**
     * 所属性能属性
     */
    private String prefAttribute;
    /**
     * 具备能力(是/否)
     */
    private String proAbility;
    /**
     * CNAS项目(是/否)
     */
    private String proCnas;
    /**
     * 操作日期(自动生成 yyyy-MM-dd)
     */
    private String operationDate;
	/**
	 * 样品说明
	 */
    private String sampleDesc;
    /**
          * 项目类型(强检项目,新能源项目,营运货车项目)
     */
    private String proType;
    
    /**
     * 项目树形节点id
     */
    private String proTypeId;
    
    /**
     * 检验标准ids
     */
    private String stdIds;
    /**
     * 关联标准
     */
    private List<StandardSearchVO> standardList = new ArrayList<>();
	
	public List<StandardSearchVO> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<StandardSearchVO> standardList) {
		this.standardList = standardList;
	}
	
	public String getStdIds() {
		return stdIds;
	}
	public void setStdIds(String stdIds) {
		this.stdIds = stdIds;
	}
	public String getLabId() {
		return labId;
	}
	public void setLabId(String labId) {
		this.labId = labId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getTrialMethod() {
		return trialMethod;
	}
	public void setTrialMethod(String trialMethod) {
		this.trialMethod = trialMethod;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProBelongId() {
		return proBelongId;
	}
	public void setProBelongId(String proBelongId) {
		this.proBelongId = proBelongId;
	}
	public String getPrefAttribute() {
		return prefAttribute;
	}
	public void setPrefAttribute(String prefAttribute) {
		this.prefAttribute = prefAttribute;
	}
	public String getProAbility() {
		return proAbility;
	}
	public void setProAbility(String proAbility) {
		this.proAbility = proAbility;
	}
	public String getProCnas() {
		return proCnas;
	}
	public void setProCnas(String proCnas) {
		this.proCnas = proCnas;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getSampleDesc() {
		return sampleDesc;
	}
	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getProTypeId() {
		return proTypeId;
	}
	public void setProTypeId(String proTypeId) {
		this.proTypeId = proTypeId;
	}
	
	
}
