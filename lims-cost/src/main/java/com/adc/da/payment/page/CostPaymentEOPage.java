package com.adc.da.payment.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class CostPaymentEOPage extends BasePage {
    /**
     *供应商名称
     */
    private String supName;
    /**
     *结算编号
     */
    private String paymentNo;

    /**
     *结算状态(0:未结算  1结算中   2已结算)
     */
    private String paymentStatus;
    
    /**
     *结算人名字
     */
    private String paymentPerson;
    
    
    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;


	public String getSupName() {
		return supName;
	}


	public void setSupName(String supName) {
		this.supName = supName;
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


	public String getPaymentPerson() {
		return paymentPerson;
	}


	public void setPaymentPerson(String paymentPerson) {
		this.paymentPerson = paymentPerson;
	}


	public List<String> getSearchPhrase() {
		return searchPhrase;
	}


	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
    
    
    
    
    
    
}
