package com.adc.da.pc_trust.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.pc_person.entity.TrialPersonEO;

public class PCTrialTaskVO {
	/**
	 * 试验任务书id
	 */
	private String id;
	/**
	 * 人员实体
	 */
	private List<TrialPersonEO> trialPersonEOList = new ArrayList<>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<TrialPersonEO> getTrialPersonEOList() {
		return trialPersonEOList;
	}
	public void setTrialPersonEOList(List<TrialPersonEO> trialPersonEOList) {
		this.trialPersonEOList = trialPersonEOList;
	}
	
	
}
