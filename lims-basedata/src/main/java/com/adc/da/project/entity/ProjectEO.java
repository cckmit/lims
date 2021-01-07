package com.adc.da.project.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.sys.entity.UserEO;
/**
 * 项目管理实体
 * 数据库表 BM_PROJECT
 * @author Administrator
 * @date 2019年7月10日
 */
public class ProjectEO extends BaseEntity{
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
	private String name;
	/**
	 * 项目编号
	 */
	private String num;
	/**
	 * 项目经理id
	 */
	private String projectManagerId;
	private String projectManagerName;
	/**
	 * 产品经理id
	 */
	private String produceManagerId;
	private String produceManagerName;
	/**
	 * 项目预算员id
	 */
	private String budgeterId;
	private String budgeterName;
	/**
	 * 项目总监id
	 */
	private String chiefId;
	private String chiefName;
	/**
	 * 项目跟踪员id
	 */
	private String trackerId;
	private String trackerName;
	/**
	 * 项目试验策划
	 */
	private String testPlanId;
	private String testPlanName;
	/**
	 * 项目开始时间
	 */
	private String startDate;
	/**
	 * 项目描述
	 */
	private String proDescribe;
	/**
	 * 所属项目(上级项目)
	 */
	private String proParentId;
	/**
	 * 项目阶段(具体含义待补充)
	 */
	private String PMDate;
	private String PPDate;
	private String PDDate;
	private String PFDate;
	private String KEDate;
	private String DEDate;
	private String DFDate;
	private String BFDate;
	private String LFDate;
	private String VFFDate;
	private String PVSDate;
	private String OSDate; //0S
	
	
	//------getter  setter ------//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getTestPlanId() {
		return testPlanId;
	}
	public void setTestPlanId(String testPlanId) {
		this.testPlanId = testPlanId;
	}
	public String getTestPlanName() {
		return testPlanName;
	}
	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getProDescribe() {
		return proDescribe;
	}
	public void setProDescribe(String proDescribe) {
		this.proDescribe = proDescribe;
	}
	public String getProParentId() {
		return proParentId;
	}
	public void setProParentId(String proParentId) {
		this.proParentId = proParentId;
	}
	public String getPMDate() {
		return PMDate;
	}
	public void setPMDate(String pMDate) {
		PMDate = pMDate;
	}
	public String getPPDate() {
		return PPDate;
	}
	public void setPPDate(String pPDate) {
		PPDate = pPDate;
	}
	public String getPDDate() {
		return PDDate;
	}
	public void setPDDate(String pDDate) {
		PDDate = pDDate;
	}
	public String getPFDate() {
		return PFDate;
	}
	public void setPFDate(String pFDate) {
		PFDate = pFDate;
	}
	public String getKEDate() {
		return KEDate;
	}
	public void setKEDate(String kEDate) {
		KEDate = kEDate;
	}
	public String getDEDate() {
		return DEDate;
	}
	public void setDEDate(String dEDate) {
		DEDate = dEDate;
	}
	public String getDFDate() {
		return DFDate;
	}
	public void setDFDate(String dFDate) {
		DFDate = dFDate;
	}
	public String getBFDate() {
		return BFDate;
	}
	public void setBFDate(String bFDate) {
		BFDate = bFDate;
	}
	public String getLFDate() {
		return LFDate;
	}
	public void setLFDate(String lFDate) {
		LFDate = lFDate;
	}
	public String getVFFDate() {
		return VFFDate;
	}
	public void setVFFDate(String vFFDate) {
		VFFDate = vFFDate;
	}
	public String getPVSDate() {
		return PVSDate;
	}
	public void setPVSDate(String pVSDate) {
		PVSDate = pVSDate;
	}
	public String getOSDate() {
		return OSDate;
	}
	public void setOSDate(String oSDate) {
		OSDate = oSDate;
	}
	public String getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	public String getProduceManagerId() {
		return produceManagerId;
	}
	public void setProduceManagerId(String produceManagerId) {
		this.produceManagerId = produceManagerId;
	}
	public String getBudgeterId() {
		return budgeterId;
	}
	public void setBudgeterId(String budgeterId) {
		this.budgeterId = budgeterId;
	}
	public String getChiefId() {
		return chiefId;
	}
	public void setChiefId(String chiefId) {
		this.chiefId = chiefId;
	}
	public String getTrackerId() {
		return trackerId;
	}
	public void setTrackerId(String trackerId) {
		this.trackerId = trackerId;
	}
	public String getProjectManagerName() {
		return projectManagerName;
	}
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	public String getProduceManagerName() {
		return produceManagerName;
	}
	public void setProduceManagerName(String produceManagerName) {
		this.produceManagerName = produceManagerName;
	}
	public String getBudgeterName() {
		return budgeterName;
	}
	public void setBudgeterName(String budgeterName) {
		this.budgeterName = budgeterName;
	}
	public String getChiefName() {
		return chiefName;
	}
	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}
	public String getTrackerName() {
		return trackerName;
	}
	public void setTrackerName(String trackerName) {
		this.trackerName = trackerName;
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
