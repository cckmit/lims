package com.adc.da.payment.entity;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.summary.entity.CostSummaryEO;

public class CostPaymentEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记   1删除, 0存在
     */
    private String delFlag;


    /**
     *basebusId
     */
    private String basebusId;

    /**
     *数据来源(商用车/乘用车)
     */
    private String dataOrigin;

    /**
     *供应商id
     */
    private String supId;

    /**
     *供应商名称
     */
    private String supName;

    /**
     *供应商code
     */
    private String supCode;

    /**
     *供应商类型
     */
    private String supType;

    /**
     *结算内容简述
     */
    private String paymentDesc;

    /**
     *结算日期
     */
    private String paymentDate;

    /**
     *结算编号
     */
    private String paymentNo;

    /**
     *结算状态(0:未结算  1结算中   2已结算  3退回  4撤回)
     */
    private String paymentStatus;

    /**
     *结算人id
     */
    private String paymentPersonId;

    /**
     *结算人名字
     */
    private String paymentPerson;

    
    /**
     * 费用预算
     */
    private List<CostSummaryEO> summaryList = new ArrayList<CostSummaryEO>();
    
    
    
	public List<CostSummaryEO> getSummaryList() {
		return summaryList;
	}

	public void setSummaryList(List<CostSummaryEO> summaryList) {
		this.summaryList = summaryList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getBasebusId() {
		return basebusId;
	}

	public void setBasebusId(String basebusId) {
		this.basebusId = basebusId;
	}

	public String getDataOrigin() {
		return dataOrigin;
	}

	public void setDataOrigin(String dataOrigin) {
		this.dataOrigin = dataOrigin;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupCode() {
		return supCode;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public String getPaymentDesc() {
		return paymentDesc;
	}

	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentPersonId() {
		return paymentPersonId;
	}

	public void setPaymentPersonId(String paymentPersonId) {
		this.paymentPersonId = paymentPersonId;
	}

	public String getPaymentPerson() {
		return paymentPerson;
	}

	public void setPaymentPerson(String paymentPerson) {
		this.paymentPerson = paymentPerson;
	}

    
    
    
    
    
}