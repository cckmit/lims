package com.adc.da.internalcost.vo;

public class InternalCostVO {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 检验项目id
	 */
    private String insproId;
    /**
     * 单价
     */
    private String costPrice;
    /**
     * 单位
     */
    private String costUnit;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 检验标准编号
     */
    private String costStdNos;
	/**
	 * 检验标准名称
	 */
    private String costStdNames;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInsproId() {
        return insproId;
    }

    public void setInsproId(String insproId) {
        this.insproId = insproId;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

 
    public String getCostStdNos() {
        return costStdNos;
    }

    public void setCostStdNos(String costStdNos) {
        this.costStdNos = costStdNos;
    }

    public String getCostStdNames() {
        return costStdNames;
    }

   
    public void setCostStdNames(String costStdNames) {
        this.costStdNames = costStdNames;
    }

}