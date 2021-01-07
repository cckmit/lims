package com.adc.da.insprostd.entity;

import com.adc.da.base.entity.BaseEntity;

public class InsProStdEO extends BaseEntity {
	/**
	 * 检验项目id
	 */
    private String proId;
    /**
     *	 检验标准ID
     */
    private String stdId;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }
}