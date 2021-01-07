package com.adc.da.part.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/29 15:59
 * @description：${description}
 */
public class SaPartDataDTO{
    @ApiModelProperty(value="null")
    private String id;

    /**
    * 创建人ID
    */
    @ApiModelProperty(value="创建人ID")
    private String createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
    * 项目ID
    */
    @ApiModelProperty(value="项目ID")
    private String bmProjectId;

    /**
    * 送样人ID
    */
    @ApiModelProperty(value="送样人ID")
    private String sendPartUserId;

    /**
    * 样品名称
    */
    @ApiModelProperty(value="样品名称")
    private String partName;

    /**
    * 样品数量
    */
    @ApiModelProperty(value="样品数量")
    private Integer partSampleNumber;

    /**
    * 试验区域
    */
    @ApiModelProperty(value="试验区域")
    private String trialLocation;

    /**
    * 发动机编号
    */
    @ApiModelProperty(value="发动机编号")
    private String partEngineNo;

    /**
    * 零部件阶段
    */
    @ApiModelProperty(value="零部件阶段")
    private Integer partStage;

    /**
    * 接收人ID
    */
    @ApiModelProperty(value="接收人ID")
    private String receivedUserId;

    /**
    * 生产厂家
    */
    @ApiModelProperty(value="生产厂家")
    private String producedFactory;

    /**
    * 零部件状态
    */
    @ApiModelProperty(value="零部件状态")
    private String partStatus;

    /**
    * 接受日期
    */
    @ApiModelProperty(value="接受日期")
    private String receivedTime;

    /**
    * 零部件库房ID
    */
    @ApiModelProperty(value="零部件库房ID")
    private String partDepotId;

    /**
    * 占用库位号
    */
    @ApiModelProperty(value="占用库位号")
    private Integer partSpaceNumber;

    /**
    * 样品所在地
    */
    @ApiModelProperty(value="样品所在地")
    private String partLocation;

    /**
    * 零部件所在地外场管理责任人ID
    */
    @ApiModelProperty(value="零部件所在地外场管理责任人ID")
    private String partLocationManagerId;

    /**
    * 考核件清单ID(TS_ATTACHMENT表ID)
    */
    @ApiModelProperty(value="考核件清单ID(TS_ATTACHMENT表ID)")
    private String assessAttachmentId;

    /**
     * 装机报告考核件清单ID(TS_ATTACHMENT表ID)
     */
    @ApiModelProperty(value="装机报告考核件清单ID(TS_ATTACHMENT表ID)")
    private String reportAssessAttachmentId;

    /**
    * 是否退样
    */
    @ApiModelProperty(value="是否退样")
    private String whetherReturn;

    /**
    * 实验类型
    */
    @ApiModelProperty(value="实验类型")
    private String trialType;

    /**
     * 所在位置
     */
    @ApiModelProperty(value="所在位置")
    private String partSpaceLocation;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 试验委托编号
     */
    @ApiModelProperty(value="试验委托编号")
    private String trialApplyNO;

    /**
     * 样品编号
     */
    @ApiModelProperty(value="样品编号")
    private String sampleNO;

    /**
     * 样品序列
     */
    @ApiModelProperty(value="样品序列号")
    private List<String> sampleSequence;

    /**
     * 接收：0 领样：1 在库：2 退样：3 报废：4 封存：5 拆机：6 待接收：7
     */
    @ApiModelProperty(value="状态")
    private Integer status;

    @ApiModelProperty("试验任务书编号")
    private String trialTaskBookNO;
    @ApiModelProperty("报废单号")
    private String scrapNumber;
    @ApiModelProperty("报废日期")
    private String scrapDate;
    @ApiModelProperty("结算日期")
    private String settlementDate;
    @ApiModelProperty("结算单号")
    private String settlementNumber;
    @ApiModelProperty("归还日期")
    private String returnDate;
    @ApiModelProperty("领用日期")
    private String borrowDate;
    @ApiModelProperty("领用数量")
    private String borrowQuantity;
    @ApiModelProperty("领用人姓名")
    private String borrowerName;
    @ApiModelProperty("领用人id")
    private String borrowerId;
    @ApiModelProperty("领用单号")
    private String borrowNumbwe;
    @ApiModelProperty("SQE/检验员姓名")
    private String sqeName;
    @ApiModelProperty("SQE/检验员id")
    private String sqeId;
    @ApiModelProperty("检验单号")
    private String testNumber;
    @ApiModelProperty("实际到货数量")
    private String actualArrivalQuantity;
    @ApiModelProperty("实际到货时间")
    private String actualArrivalTime;
    @ApiModelProperty("生产库管员姓名")
    private String produceAdminName;
    @ApiModelProperty("生产库管员id")
    private String produceAdmin;
    @ApiModelProperty("处理意见")
    private String dealOpinion;
    @ApiModelProperty("要求到货时间")
    private String requestArrivalTime;
    @ApiModelProperty("类别")
    private String partType;
    @ApiModelProperty("样件图号")
    private String partDrawNumber;
    @ApiModelProperty("样件计划单号")
    private String partPlanNumber;

    @ApiModelProperty("判断样品数量是否被修改 (0.是；1.否)")
    private Integer editStatus;

    @ApiModelProperty("规格型号")
    private String specificationType;

    @ApiModelProperty("归还人")
    private String returnUserName ;
    @ApiModelProperty("归还人id")
    private String returnUserId ;
    @ApiModelProperty("是否报废（0，否；1，是）")
    private Integer whetherScrap;

    private String partNO;

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBmProjectId() {
        return bmProjectId;
    }

    public void setBmProjectId(String bmProjectId) {
        this.bmProjectId = bmProjectId;
    }

    public String getSendPartUserId() {
        return sendPartUserId;
    }

    public void setSendPartUserId(String sendPartUserId) {
        this.sendPartUserId = sendPartUserId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartSampleNumber() {
        return partSampleNumber;
    }

    public void setPartSampleNumber(Integer partSampleNumber) {
        this.partSampleNumber = partSampleNumber;
    }

    public String getTrialLocation() {
        return trialLocation;
    }

    public void setTrialLocation(String trialLocation) {
        this.trialLocation = trialLocation;
    }

    public String getPartEngineNo() {
        return partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }

    public Integer getPartStage() {
        return partStage;
    }

    public void setPartStage(Integer partStage) {
        this.partStage = partStage;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public String getProducedFactory() {
        return producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getPartDepotId() {
        return partDepotId;
    }

    public void setPartDepotId(String partDepotId) {
        this.partDepotId = partDepotId;
    }

    public Integer getPartSpaceNumber() {
        return partSpaceNumber;
    }

    public void setPartSpaceNumber(Integer partSpaceNumber) {
        this.partSpaceNumber = partSpaceNumber;
    }

    public String getPartLocation() {
        return partLocation;
    }

    public void setPartLocation(String partLocation) {
        this.partLocation = partLocation;
    }

    public String getPartLocationManagerId() {
        return partLocationManagerId;
    }

    public void setPartLocationManagerId(String partLocationManagerId) {
        this.partLocationManagerId = partLocationManagerId;
    }

    public String getAssessAttachmentId() {
        return assessAttachmentId;
    }

    public void setAssessAttachmentId(String assessAttachmentId) {
        this.assessAttachmentId = assessAttachmentId;
    }

    public String getWhetherReturn() {
        return whetherReturn;
    }

    public void setWhetherReturn(String whetherReturn) {
        this.whetherReturn = whetherReturn;
    }

    public String getTrialType() {
        return trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    public String getPartSpaceLocation() {
        return partSpaceLocation;
    }

    public void setPartSpaceLocation(String partSpaceLocation) {
        this.partSpaceLocation = partSpaceLocation;
    }

    public String getReportAssessAttachmentId() {
        return reportAssessAttachmentId;
    }

    public void setReportAssessAttachmentId(String reportAssessAttachmentId) {
        this.reportAssessAttachmentId = reportAssessAttachmentId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }

    public String getSampleNO() {
        return sampleNO;
    }

    public void setSampleNO(String sampleNO) {
        this.sampleNO = sampleNO;
    }

    public List<String> getSampleSequence() {
        return sampleSequence;
    }

    public void setSampleSequence(List<String> sampleSequence) {
        this.sampleSequence = sampleSequence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getScrapNumber() {
        return scrapNumber;
    }

    public void setScrapNumber(String scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public String getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(String scrapDate) {
        this.scrapDate = scrapDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementNumber() {
        return settlementNumber;
    }

    public void setSettlementNumber(String settlementNumber) {
        this.settlementNumber = settlementNumber;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowQuantity() {
        return borrowQuantity;
    }

    public void setBorrowQuantity(String borrowQuantity) {
        this.borrowQuantity = borrowQuantity;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowNumbwe() {
        return borrowNumbwe;
    }

    public void setBorrowNumbwe(String borrowNumbwe) {
        this.borrowNumbwe = borrowNumbwe;
    }

    public String getSqeName() {
        return sqeName;
    }

    public void setSqeName(String sqeName) {
        this.sqeName = sqeName;
    }

    public String getSqeId() {
        return sqeId;
    }

    public void setSqeId(String sqeId) {
        this.sqeId = sqeId;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getActualArrivalQuantity() {
        return actualArrivalQuantity;
    }

    public void setActualArrivalQuantity(String actualArrivalQuantity) {
        this.actualArrivalQuantity = actualArrivalQuantity;
    }

    public String getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(String actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getProduceAdminName() {
        return produceAdminName;
    }

    public void setProduceAdminName(String produceAdminName) {
        this.produceAdminName = produceAdminName;
    }

    public String getProduceAdmin() {
        return produceAdmin;
    }

    public void setProduceAdmin(String produceAdmin) {
        this.produceAdmin = produceAdmin;
    }

    public String getDealOpinion() {
        return dealOpinion;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public String getRequestArrivalTime() {
        return requestArrivalTime;
    }

    public void setRequestArrivalTime(String requestArrivalTime) {
        this.requestArrivalTime = requestArrivalTime;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartDrawNumber() {
        return partDrawNumber;
    }

    public void setPartDrawNumber(String partDrawNumber) {
        this.partDrawNumber = partDrawNumber;
    }

    public String getPartPlanNumber() {
        return partPlanNumber;
    }

    public void setPartPlanNumber(String partPlanNumber) {
        this.partPlanNumber = partPlanNumber;
    }

    public String getTrialTaskBookNO() {
        return trialTaskBookNO;
    }

    public void setTrialTaskBookNO(String trialTaskBookNO) {
        this.trialTaskBookNO = trialTaskBookNO;
    }

    public Integer getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
    }

    public String getPartNO() {
        return partNO;
    }

    public void setPartNO(String partNO) {
        this.partNO = partNO;
    }

    public String getReturnUserName() {
        return returnUserName;
    }

    public void setReturnUserName(String returnUserName) {
        this.returnUserName = returnUserName;
    }

    public String getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(String returnUserId) {
        this.returnUserId = returnUserId;
    }

    public Integer getWhetherScrap() {
        return whetherScrap;
    }

    public void setWhetherScrap(Integer whetherScrap) {
        this.whetherScrap = whetherScrap;
    }
}