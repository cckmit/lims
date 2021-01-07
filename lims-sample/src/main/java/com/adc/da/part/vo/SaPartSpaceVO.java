package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 8:41
 * @description：车位vo
 */
public class SaPartSpaceVO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 车位关联停车场id
     */
    @ApiModelProperty(value = "车位关联停车场id")
    private String partDepotId;

    /**
     * 第几区
     */
    @ApiModelProperty(value = "第几区")
    private Integer areaNumber;

    /**
     * 第几行
     */
    @ApiModelProperty(value = "第几行")
    private Integer rowNumber;

    /**
     * 第几个货架
     */
    @ApiModelProperty(value = "第几个货架")
    private Integer shelfNumber;

    /**
     * 层数
     */
    @ApiModelProperty(value = "层数")
    private Integer layerNumber;

    /**
     * 已占用车位
     */
    @ApiModelProperty(value = "已占用车位")
    private String usedPartSpace;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty("")
    private List<SaPartSpaceVO> saPartSpaceVOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartDepotId() {
        return partDepotId;
    }

    public void setPartDepotId(String partDepotId) {
        this.partDepotId = partDepotId;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public List<SaPartSpaceVO> getSaPartSpaceVOList() {
        return saPartSpaceVOList;
    }

    public void setSaPartSpaceVOList(List<SaPartSpaceVO> saPartSpaceVOList) {
        this.saPartSpaceVOList = saPartSpaceVOList;
    }
}
