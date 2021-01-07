package com.adc.da.car.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 9:33
 * @description：整车库房dto
 */
public class CarDepotDTO {

    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 库房名称
     */
    @ApiModelProperty(value = "库房名称", required = true)
    private String depotName;

    /**
     * 库房编号
     */
    @ApiModelProperty(value = "库房编号", required = true)
    private String depotNumber;

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
}
