package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/8 11:56
 * @description：零部件库房vo
 */
public class SaPartDepotVO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 库房名称
     */
    @ApiModelProperty(value = "库房名称")
    private String depotName;

    /**
     * 库房编号
     */
    @ApiModelProperty(value = "库房编号")
    private String depotNumber;

    /**
     * 未使用车位数
     */
    @ApiModelProperty(value = "未使用车位数")
    private String unUsePartSpaceNumber;

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

    public String getUnUsePartSpaceNumber() {
        return unUsePartSpaceNumber;
    }

    public void setUnUsePartSpaceNumber(String unUsePartSpaceNumber) {
        this.unUsePartSpaceNumber = unUsePartSpaceNumber;
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
}
