package com.adc.da.part.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 9:33
 * @description：零部件库房dto
 */
public class SaPartSpaceDTO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 库位关联零部件库房id
     */
    @ApiModelProperty(value = "库位关联零部件库房id")
    private String partDepotId;

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
     * 条形码
     */
    @ApiModelProperty(value="条形码")
    private String barcode;

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
