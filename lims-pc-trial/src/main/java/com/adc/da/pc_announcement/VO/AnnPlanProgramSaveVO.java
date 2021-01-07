package com.adc.da.pc_announcement.VO;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;

public class AnnPlanProgramSaveVO {

	private String projectId;
	
	private List<AnnPlanProgramEO> programList = new ArrayList<>();

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public List<AnnPlanProgramEO> getProgramList() {
		return programList;
	}

	public void setProgramList(List<AnnPlanProgramEO> programList) {
		this.programList = programList;
	}
	
	
}
