package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

public class PipelineNumberEO extends BaseEntity {
	/**
	 * 计数类型
	 * EVReport - 发动机报告编号
	 */
    private String type;
    
    /**
     * 年份
     */
    private String particularYear;
    /**
     * 计数
     */
    private Integer tally;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParticularYear() {
		return particularYear;
	}
	public void setParticularYear(String particularYear) {
		this.particularYear = particularYear;
	}
	public Integer getTally() {
		return tally;
	}
	public void setTally(Integer tally) {
		this.tally = tally;
	}
    
}