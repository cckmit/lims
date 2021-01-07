package com.adc.da.car.vo;


import io.swagger.annotations.ApiModelProperty;

public class CarBarcodeVO {

    @ApiModelProperty(value = "样品名称")
    private String carName;             //样品名称

    @ApiModelProperty("所属项目")
    private String bmProjectName;       //所属项目

    @ApiModelProperty("检验项目")
    private String inspectProjectName;            //检验项目

    @ApiModelProperty("VIN码")
    private String carVin;              //VIN码

    @ApiModelProperty("样车编号")
    private String carNO;           //样车编号

    @ApiModelProperty("条形码")
    private String barCode;                  //条形码

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBmProjectName() {
        return bmProjectName;
    }

    public void setBmProjectName(String bmProjectName) {
        this.bmProjectName = bmProjectName;
    }

    public String getInspectProjectName() {
        return inspectProjectName;
    }

    public void setInspectProjectName(String inspectProjectName) {
        this.inspectProjectName = inspectProjectName;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getCarNO() {
        return carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
