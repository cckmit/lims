package com.adc.da.insproject.entity;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.sys.entity.UserEO;

public class InsProjectEO extends BaseEntity {
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
	 * 项目名称
	 */
    private String proName;
    /**
     * 项目代码
     */
    private String proCode;
  /**
   * 项目树形节点id
   */
    private String proTypeId;
    /**
     * 项目所属节点id
     */
    private String proBelongId;
    /**
     * 负责试验室(组织)
     */
    private String labId;
    private String labName;
    /**
     * 所属性能属性
     */
    private String prefAttribute;
    /**
     * 具备能力(是/否)
     */
    private String proAbility;
    /**
     * CNAS项目(是/否)
     */
    private String proCNAS;
    /**
     * 操作日期(自动生成 yyyy-MM-dd)
     */
    private String operationDate;
    /**
     * 试验方法
     */
    private String trialMethod;
    /**
     * 项目说明
     */
    private String proDesc;
	/**
	 * 样品说明
	 */
    private String sampleDesc;
    /**
          * 项目类型(强检项目,新能源项目)
     */
    private String proType;
    
    /**
     * 	检验标准ids
     */
    private String stdIds;

	/**
     * 关联标准
     */
    private List<StandardEO> standardList = new ArrayList<>();

    public String getStdIds() {
		return stdIds;
	}

	public void setStdIds(String stdIds) {
		this.stdIds = stdIds;
	}

	public String getLabName() {
    	return labName;
    }
    
    public void setLabName(String labName) {
    	this.labName = labName;
    }

	public List<StandardEO> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<StandardEO> standardList) {
		this.standardList = standardList;
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

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

    public String getProBelongId() {
        return proBelongId;
    }

    public void setProBelongId(String proBelongId) {
        this.proBelongId = proBelongId;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getPrefAttribute() {
        return prefAttribute;
    }

    public void setPrefAttribute(String prefAttribute) {
        this.prefAttribute = prefAttribute;
    }

    public String getProAbility() {
        return proAbility;
    }
    
    public void setProAbility(String proAbility) {
        this.proAbility = proAbility;
    }

    public String getProCNAS() {
		return proCNAS;
	}

	public void setProCNAS(String proCNAS) {
		this.proCNAS = proCNAS;
	}

	public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getTrialMethod() {
        return trialMethod;
    }

    public void setTrialMethod(String trialMethod) {
        this.trialMethod = trialMethod;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getSampleDesc() {
        return sampleDesc;
    }

    public void setSampleDesc(String sampleDesc) {
        this.sampleDesc = sampleDesc;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
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