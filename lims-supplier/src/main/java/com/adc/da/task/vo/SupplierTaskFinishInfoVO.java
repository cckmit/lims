package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 陈璇
 * @Description SupplierTaskFinishInfoVO
 * @Date 2020/01/16 13:46
 */
@Data
@ToString
public class SupplierTaskFinishInfoVO {

    private String id;

    @ApiModelProperty("是否异常（0，是；1，否）")
    private Integer whetherException;

    @ApiModelProperty("异常描述")
    private String exceptionDescription;

    @ApiModelProperty("状态（0，草稿；1，审批中,；2，进行中；3，已完成,；4，已退回；5，已取消）")
    private Integer taskStatus;

    @ApiModelProperty("实际人数")
    private String realPeopleNumber;

    @ApiModelProperty("实际工时")
    private String realWorkHour;

    @ApiModelProperty("任务开始时间")
    private String taskStartTime;

    @ApiModelProperty("任务结束时间")
    private String taskEndTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("附件ID")
    private String attachmentId;

    public String getRealPeopleNumber() {
        return realPeopleNumber;
    }

    public void setRealPeopleNumber(String realPeopleNumber) {
        this.realPeopleNumber = realPeopleNumber;
    }

    public String getRealWorkHour() {
        return realWorkHour;
    }

    public void setRealWorkHour(String realWorkHour) {
        this.realWorkHour = realWorkHour;
    }
}
