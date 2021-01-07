package com.adc.da.pc_trust.page;

import com.adc.da.base.page.BasePage;
import io.swagger.annotations.ApiModelProperty;

public class TrialTaskChangeEOPage extends BasePage {

    /**
     * 试验任务编号唯一标识
     */
    @ApiModelProperty(value = "试验任务编号唯一标识")
    private String taskNumber;

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    
}
