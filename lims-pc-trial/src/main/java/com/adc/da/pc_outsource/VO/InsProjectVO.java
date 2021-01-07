package com.adc.da.pc_outsource.VO;

import io.swagger.annotations.ApiModelProperty;

public class InsProjectVO {

    @ApiModelProperty("检验项目ID")
    private String insId;
    @ApiModelProperty("项目名称")
    private String insProject;
    @ApiModelProperty("项目类型")
    private String insType;

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public String getInsProject() {
        return insProject;
    }

    public void setInsProject(String insProject) {
        this.insProject = insProject;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }
}
