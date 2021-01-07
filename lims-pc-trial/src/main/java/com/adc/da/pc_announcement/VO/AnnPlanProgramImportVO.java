package com.adc.da.pc_announcement.VO;

import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;

import io.swagger.annotations.ApiModelProperty;

public class AnnPlanProgramImportVO extends AnnPlanProgramEO {

	@ApiModelProperty("序号重复标识 0-不重复 1-重复")
    private String duplicateFlag;

	public String getDuplicateFlag() {
		return duplicateFlag;
	}

	public void setDuplicateFlag(String duplicateFlag) {
		this.duplicateFlag = duplicateFlag;
	}
	
	
}
