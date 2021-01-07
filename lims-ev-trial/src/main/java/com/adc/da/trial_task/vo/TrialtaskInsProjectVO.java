package com.adc.da.trial_task.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/5 10:12
 * @description：${description}
 */
public class TrialtaskInsProjectVO {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("项目名称")
    private String proName;

    @ApiModelProperty("项目代码")
    private String proCode;

    @ApiModelProperty("负责试验室")
    private String chargeLaboratory;

    @ApiModelProperty("检验标准")
    private String insproStandard;

    @ApiModelProperty("试验工程师")
    private String createName;

    @ApiModelProperty("计划开始时间")
    private String createTime;

    @ApiModelProperty("计划结束时间")
    private String finishDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getChargeLaboratory() {
        return chargeLaboratory;
    }

    public void setChargeLaboratory(String chargeLaboratory) {
        this.chargeLaboratory = chargeLaboratory;
    }

    public String getInsproStandard() {
        return insproStandard;
    }

    public void setInsproStandard(String insproStandard) {
        this.insproStandard = insproStandard;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
