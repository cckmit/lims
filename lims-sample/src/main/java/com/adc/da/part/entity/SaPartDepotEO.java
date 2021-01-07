package com.adc.da.part.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/9 16:24
 * @description：${description}
 */
@ApiModel(value="com.adc.da.part.entity.SaPartDepotEO")
public class SaPartDepotEO extends BaseEntity implements Serializable {

    private String id;

    /**
    * 零部件库房名称
    */
    @ApiModelProperty(value="零部件库房名称")
    private String depotName;

    /**
    * 零部件库房编号
    */
    @ApiModelProperty(value="零部件库房编号")
    private String depotNumber;

    /**
    * 第几区
    */
    @ApiModelProperty(value="第几区")
    private Integer areaNumber;

    /**
    * 第几行
    */
    @ApiModelProperty(value="第几行")
    private Integer rowNumber;

    /**
    * 第几个货架
    */
    @ApiModelProperty(value="第几个货架")
    private Integer shelfNumber;

    /**
    * 层数
    */
    @ApiModelProperty(value="层数")
    private Integer layerNumber;

    /**
    * 已被占用的位置
    */
    @ApiModelProperty(value="已被占用的位置")
    private String usedPartSpace;

    /**
     * 剩余库位数
     */
    @ApiModelProperty(value = "剩余库位数")
    private Integer unUsePartSpaceNumber;

    /**
    * （0，未删除；1，已删除）
    */
    @ApiModelProperty(value="（0，未删除；1，已删除）")
    private Integer delFlag;

    /**
    * 条形码
    */
    @ApiModelProperty(value="条形码")
    private String barcode;

    /**
    * （0，库房；1，存放位置）
    */
    @ApiModelProperty(value="（0，库房；1，存放位置）")
    private Integer partSpaceStatus;

    /**
    * 存放位置关联库房ID
    */
    @ApiModelProperty(value="存放位置关联库房ID")
    private String partDepotId;

    /**
    * 创建人
    */
    @ApiModelProperty(value="创建人")
    private String createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
    * 更新人
    */
    @ApiModelProperty(value="更新人")
    private String updateBy;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotNumber() {
        return depotNumber;
    }

    public void setDepotNumber(String depotNumber) {
        this.depotNumber = depotNumber;
    }

    public Integer getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(Integer areaNumber) {
        this.areaNumber = areaNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(Integer shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Integer getLayerNumber() {
        return layerNumber;
    }

    public void setLayerNumber(Integer layerNumber) {
        this.layerNumber = layerNumber;
    }

    public String getUsedPartSpace() {
        return usedPartSpace;
    }

    public void setUsedPartSpace(String usedPartSpace) {
        this.usedPartSpace = usedPartSpace;
    }

    public Integer getUnUsePartSpaceNumber() {
        return unUsePartSpaceNumber;
    }

    public void setUnUsePartSpaceNumber(Integer unUsePartSpaceNumber) {
        this.unUsePartSpaceNumber = unUsePartSpaceNumber;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getPartSpaceStatus() {
        return partSpaceStatus;
    }

    public void setPartSpaceStatus(Integer partSpaceStatus) {
        this.partSpaceStatus = partSpaceStatus;
    }

    public String getPartDepotId() {
        return partDepotId;
    }

    public void setPartDepotId(String partDepotId) {
        this.partDepotId = partDepotId;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", depotName=").append(depotName);
        sb.append(", depotNumber=").append(depotNumber);
        sb.append(", areaNumber=").append(areaNumber);
        sb.append(", rowNumber=").append(rowNumber);
        sb.append(", shelfNumber=").append(shelfNumber);
        sb.append(", layerNumber=").append(layerNumber);
        sb.append(", usedPartSpace=").append(usedPartSpace);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", barcode=").append(barcode);
        sb.append(", partSpaceStatus=").append(partSpaceStatus);
        sb.append(", partDepotId=").append(partDepotId);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}