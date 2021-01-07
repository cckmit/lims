package com.adc.da.pc_execute.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/18 9:01
 * @description：
 */
public class CostForCashOutPage {

    @ApiModelProperty("试验任务书ID")
    private String trialTaskId;

    @ApiModelProperty("费用code")
    private String code;

    @JsonIgnore
    private List<String> codeList;

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
