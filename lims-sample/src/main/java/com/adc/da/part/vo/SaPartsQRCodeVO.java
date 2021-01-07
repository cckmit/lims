package com.adc.da.part.vo;


public class SaPartsQRCodeVO {
    /**
     * 领部件名称
     */
    private String sampleName;

    /**
     * 供应商
     */
    private String manufacturer;

    /**
     * 零部件号
     */
    private String partNumber;

    /**
     * 零部件序列号
     */
    private String sampleNumber;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 所属项目
     */
    private String subordinateItems;

    /**
     * 检验委托合同编号
     */
    private String commissionNumber;

    /**
     * 检验项目
     */
    private String ccTestProject;
    /**
     * 试验任务书编号
     */
    private String trialTaskBookNO;

    public String getTrialTaskBookNO() {
        return trialTaskBookNO;
    }

    public void setTrialTaskBookNO(String trialTaskBookNO) {
        this.trialTaskBookNO = trialTaskBookNO;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSubordinateItems() {
        return subordinateItems;
    }

    public void setSubordinateItems(String subordinateItems) {
        this.subordinateItems = subordinateItems;
    }

    public String getCommissionNumber() {
        return commissionNumber;
    }

    public void setCommissionNumber(String commissionNumber) {
        this.commissionNumber = commissionNumber;
    }

    public String getCcTestProject() {
        return ccTestProject;
    }

    public void setCcTestProject(String ccTestProject) {
        this.ccTestProject = ccTestProject;
    }


}
