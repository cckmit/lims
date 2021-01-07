package com.adc.da.summary.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class CostSummaryEOPage extends BasePage {
    /**
     *结算状态
     */
    private String paymentStatus;
    
    /**
     *结算编号
     */
    private String paymentNo;
    
    /**
     *供应商名字
     */
    private String supName;
    
    /**
     *编辑状态(驳回/激活)
     */
    private String editStatus;
    
    /**
     *PV/CV试验任务编号,认证计划名称
     */
    private String taskName;

    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;
    
    /**
     *费用类型
     *自行支付/报账 - 1
     *委外/公告试验费 - 2
     *维修费-对公 - 3
     *场地费 - 4
     *场地燃料费 - 5
     *辅助劳务费/外包劳务费 - 6
     *路送路试费 - 7
     */
    private String costType;
    
    /**
     *结算日期
     */
    private String paymentDate;
    private String paymentDate1;
    private String paymentDate2;
    
    /**
     *负责人(试验工程师)
     */
    private String engineer;

    /**
     *底盘号(多个用","隔开)
     */
    private String chassisNo;
    
    
    
    
	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getPaymentDate1() {
		return paymentDate1;
	}

	public void setPaymentDate1(String paymentDate1) {
		this.paymentDate1 = paymentDate1;
	}

	public String getPaymentDate2() {
		return paymentDate2;
	}

	public void setPaymentDate2(String paymentDate2) {
		this.paymentDate2 = paymentDate2;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getEngineer() {
		return engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
    
    
    
    
}
