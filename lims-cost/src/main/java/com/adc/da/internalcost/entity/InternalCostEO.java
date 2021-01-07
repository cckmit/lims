package com.adc.da.internalcost.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.sys.entity.UserEO;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部费用库信息表
 */
public class InternalCostEO extends BaseEntity {
	/**
	 * 主键
	 */
	private String id;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private UserEO createBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新人
     */
    private UserEO updateBy;
	/**
	 * 检验项目id
	 */
    private String insproId;
    /**
     * 单价
     */
    private String costPrice;
    /**
     * 单位
     */
    private String costUnit;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 检验标准编号
     */
    private String costStdNos;
	/**
	 * 检验标准名称
	 */
    private String costStdNames;

    //___________检索返回参数___________//
    /**
     * 负责组
     */
    private String labName;
	 /**
	  * 试验项目
	  */
    private String proName;
    /**
     * 试验项目编号
     */
    private String proCode;
	/**
     * 关联标准
     */
    private List<StandardEO> standardList = new ArrayList<>();

    public String getInsproId() {
        return insproId;
    }

    public void setInsproId(String insproId) {
        this.insproId = insproId;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getCostStdNos() {
        return costStdNos;
    }

    public void setCostStdNos(String costStdNos) {
        this.costStdNos = costStdNos;
    }

    public String getCostStdNames() {
        return costStdNames;
    }


    public void setCostStdNames(String costStdNames) {
        this.costStdNames = costStdNames;
    }

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public List<StandardEO> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<StandardEO> standardList) {
		this.standardList = standardList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public UserEO getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserEO createBy) {
		this.createBy = createBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public UserEO getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(UserEO updateBy) {
		this.updateBy = updateBy;
	}
}
