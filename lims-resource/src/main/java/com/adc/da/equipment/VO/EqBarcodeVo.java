package com.adc.da.equipment.VO;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 *导出核检记录的实体类
 */
public class EqBarcodeVo extends BaseEntity {
    @ApiModelProperty(" 设备名称 ")
    private String eqName;
    @ApiModelProperty(" 设备编号 ")
    private String eqNo;
    @ApiModelProperty(" 供应商 ")
    private String eqSupplier;
    @ApiModelProperty(" 固定资产编号 ")
    private String eqAssetsNo;
    @ApiModelProperty("条形码")
    private String barCode;

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqNo() {
        return eqNo;
    }

    public void setEqNo(String eqNo) {
        this.eqNo = eqNo;
    }

    public String getEqSupplier() {
        return eqSupplier;
    }

    public void setEqSupplier(String eqSupplier) {
        this.eqSupplier = eqSupplier;
    }

    public String getEqAssetsNo() {
        return eqAssetsNo;
    }

    public void setEqAssetsNo(String eqAssetsNo) {
        this.eqAssetsNo = eqAssetsNo;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
