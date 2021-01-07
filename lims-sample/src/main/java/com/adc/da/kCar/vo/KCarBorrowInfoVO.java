package com.adc.da.kCar.vo;

public class KCarBorrowInfoVO {
	  /*
	  *借车日期
	  */
	 private String LOANDATE;
	 /*
	  *预计归还日期
	  */
	 private String RESTOREDATE;
	 /*
	  *预计归还时间
	  */
	 private String LOAN_MAN;
	 /*
	  *外借人员
	  */
	 private String PURPOSE;
	 /*
	  *借车用途
	  */
	 private String RESTDATE;
	 
	 
	public String getLOANDATE() {
		return LOANDATE;
	}
	public void setLOANDATE(String lOANDATE) {
		LOANDATE = lOANDATE;
	}
	public String getRESTOREDATE() {
		return RESTOREDATE;
	}
	public void setRESTOREDATE(String rESTOREDATE) {
		RESTOREDATE = rESTOREDATE;
	}
	public String getLOAN_MAN() {
		return LOAN_MAN;
	}
	public void setLOAN_MAN(String lOAN_MAN) {
		LOAN_MAN = lOAN_MAN;
	}
	public String getPURPOSE() {
		return PURPOSE;
	}
	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}
	public String getRESTDATE() {
		return RESTDATE;
	}
	public void setRESTDATE(String rESTDATE) {
		RESTDATE = rESTDATE;
	}
	 
	 
	
}
