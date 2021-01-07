package com.adc.da.part.page;

import com.adc.da.base.page.BasePage;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/6 11:48
 * @description：${description}
 */
public class SaPartFlowLogPage extends BasePage {

    @ApiModelProperty(value = "零部件ID")
    private String partId;

    /**
     * 零部件ID
     * @return
     */
    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }
}
