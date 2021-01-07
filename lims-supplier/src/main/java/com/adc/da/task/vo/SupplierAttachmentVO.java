package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/2 16:22
 * @description：外包供应商任务统计列表完成凭证附件vo
 */
public class SupplierAttachmentVO {

    @ApiModelProperty("附件ID")
    private String attachmentId;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
