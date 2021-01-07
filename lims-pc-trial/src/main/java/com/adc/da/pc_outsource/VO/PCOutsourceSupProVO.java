package com.adc.da.pc_outsource.VO;

public class PCOutsourceSupProVO {
	/**
     *委外立项单id
     */
    private String outsourceId;

    /**
     *供应商 id
     */
    private String supId;

    /**
     *供应商
     */
    private String supName;

    /**
     *供应商折后价
     */
    private String discountPrice;

    /**
     *检验项目名称
     */
    private String testproName;
    

	public String getOutsourceId() {
		return outsourceId;
	}

	public void setOutsourceId(String outsourceId) {
		this.outsourceId = outsourceId;
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

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getTestproName() {
		return testproName;
	}

	public void setTestproName(String testproName) {
		this.testproName = testproName;
	}
    
    
    
}
