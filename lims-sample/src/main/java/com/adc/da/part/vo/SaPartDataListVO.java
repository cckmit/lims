package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/31 16:00
 * @description：${description}
 */
public class SaPartDataListVO {

    @ApiModelProperty(value="null")
    private String id;

    /**
     * 是否删除（0，未删除；1，已删除）
     */
    @ApiModelProperty(value="是否删除（0，未删除；1，已删除）")
    private Short delFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 送样人ID
     */
    @ApiModelProperty(value="送样人ID")
    private String sendPartUserId;

    /**
     * 送样人名称
     */
    @ApiModelProperty(value="送样人名称")
    private String sendPartUserName;

    /**
     * 样品名称
     */
    @ApiModelProperty(value="样品名称")
    private String partName;

    /**
     * 样品数量
     */
    @ApiModelProperty(value="样品数量")
    private String partSampleNumber;

    /**
     * 发动机编号
     */
    @ApiModelProperty(value="发动机编号")
    private String partEngineNo;

    /**
     * 生产厂家
     */
    @ApiModelProperty(value="生产厂家")
    private String producedFactory;

    /**
     * 零部件库房ID
     */
    @ApiModelProperty(value="零部件库房ID")
    private String partDepotId;

    /**
     * 占用库位号
     */
    @ApiModelProperty(value="占用库位号")
    private Long partSpaceNumber;

    /**
     * 样品所在位置
     */
    @ApiModelProperty(value="样品所在位置")
    private String partSpaceLocation;

    /**
     * 领样人id
     */
    @ApiModelProperty(value="领样人id")
    private String getPartUserId;

    /**
     * 领样人名称
     */
    @ApiModelProperty(value="领样人名称")
    private String getPartUserName;

    /**
     * 在架
     */
    @ApiModelProperty(value="在架")
    private Integer inShelf;

    /**
     * 状态 接收:0, 领样：1, 归还:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7
     */
    @ApiModelProperty(value = "状态 接收:0, 领样：1, 归还:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7")
    private Integer status;

    @ApiModelProperty(value = "零部件和委托")
    private List<SaPartDataApplyVO> saPartDataApplyVOList;

    /**
     * 整车编号
     */
    @ApiModelProperty(value = "整车编号")
    private String partNO;

    /**
     * 创建人ID
     *
     */
    @ApiModelProperty(value = "创建人ID")
    private String createBy;
    /**
     * 试验委托编号
     */
    @ApiModelProperty(value = "试验委托编号")
    private String trialApplyNO;
    @ApiModelProperty(value = "试验任务书编号")
    private String trialTaskBookNO;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSendPartUserId() {
        return sendPartUserId;
    }

    public void setSendPartUserId(String sendPartUserId) {
        this.sendPartUserId = sendPartUserId;
    }

    public String getSendPartUserName() {
        return sendPartUserName;
    }

    public void setSendPartUserName(String sendPartUserName) {
        this.sendPartUserName = sendPartUserName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartSampleNumber() {
        return partSampleNumber;
    }

    public void setPartSampleNumber(String partSampleNumber) {
        this.partSampleNumber = partSampleNumber;
    }

    public String getPartEngineNo() {
        return partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }

    public String getProducedFactory() {
        return producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getPartDepotId() {
        return partDepotId;
    }

    public void setPartDepotId(String partDepotId) {
        this.partDepotId = partDepotId;
    }

    public Long getPartSpaceNumber() {
        return partSpaceNumber;
    }

    public void setPartSpaceNumber(Long partSpaceNumber) {
        this.partSpaceNumber = partSpaceNumber;
    }

    public String getPartSpaceLocation() {
        return partSpaceLocation;
    }

    public void setPartSpaceLocation(String partSpaceLocation) {
        this.partSpaceLocation = partSpaceLocation;
    }

    public String getGetPartUserId() {
        return getPartUserId;
    }

    public void setGetPartUserId(String getPartUserId) {
        this.getPartUserId = getPartUserId;
    }

    public String getGetPartUserName() {
        return getPartUserName;
    }

    public void setGetPartUserName(String getPartUserName) {
        this.getPartUserName = getPartUserName;
    }

    public Integer getInShelf() {
        return inShelf;
    }

    public void setInShelf(Integer inShelf) {
        this.inShelf = inShelf;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SaPartDataApplyVO> getSaPartDataApplyVOList() {
        return saPartDataApplyVOList;
    }

    public void setSaPartDataApplyVOList(List<SaPartDataApplyVO> saPartDataApplyVOList) {
        this.saPartDataApplyVOList = saPartDataApplyVOList;
    }

    public String getPartNO() {
        return partNO;
    }

    public void setPartNO(String partNO) {
        this.partNO = partNO;
    }

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTrialTaskBookNO() {
        return trialTaskBookNO;
    }

    public void setTrialTaskBookNO(String trialTaskBookNO) {
        this.trialTaskBookNO = trialTaskBookNO;
    }
}
