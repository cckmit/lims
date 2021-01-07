package com.adc.da.supplier.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>SUP_SUPPLIER_TASK_STANDARD SupSupplierTaskStandardEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupSupplierTaskStandardEO extends BaseEntity {

    /**
     *
     */
    private String id;

    /**
     * 类别
     */
    private String type;

    /**
     * 项目
     */
    private String project;

    /**
     * 工作内容
     */
    private String jobContent;

    /**
     * 标准人数
     */
    private String peopleNumber;

    /**
     * 标准工时
     */
    private String workHour;

    /**
     * 标准工时单位
     */
    private String workHourUnit;

    /**
     * 输出物
     */
    private String outputContent;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 删除状态 1删除 0存在
     */
    private Integer delFlag;
    
    /**
     * 单价
     */
    private String unitPrice;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>type -> type</li>
     * <li>project -> project</li>
     * <li>jobContent -> job_content</li>
     * <li>peopleNumber -> people_number</li>
     * <li>workHour -> work_hour</li>
     * <li>workHourUnit -> work_hour_unit</li>
     * <li>outputContent -> output_content</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>delFlag -> del_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        switch (fieldName) {
            case "id":
                return "id";
            case "type":
                return "type";
            case "project":
                return "project";
            case "jobContent":
                return "job_content";
            case "peopleNumber":
                return "people_number";
            case "workHour":
                return "work_hour";
            case "workHourUnit":
                return "work_hour_unit";
            case "outputContent":
                return "output_content";
            case "createTime":
                return "create_time";
            case "createBy":
                return "create_by";
            case "updateTime":
                return "update_time";
            case "updateBy":
                return "update_by";
            case "delFlag":
                return "del_flag";
            default:
                return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>type -> type</li>
     * <li>project -> project</li>
     * <li>job_content -> jobContent</li>
     * <li>people_number -> peopleNumber</li>
     * <li>work_hour -> workHour</li>
     * <li>work_hour_unit -> workHourUnit</li>
     * <li>output_content -> outputContent</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>del_flag -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) {
            return null;
        }
        switch (columnName) {
            case "id":
                return "id";
            case "type":
                return "type";
            case "project":
                return "project";
            case "job_content":
                return "jobContent";
            case "people_number":
                return "peopleNumber";
            case "work_hour":
                return "workHour";
            case "work_hour_unit":
                return "workHourUnit";
            case "output_content":
                return "outputContent";
            case "create_time":
                return "createTime";
            case "create_by":
                return "createBy";
            case "update_time":
                return "updateTime";
            case "update_by":
                return "updateBy";
            case "del_flag":
                return "delFlag";
            default:
                return null;
        }
    }

    
    
    
    public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getType() {
        return this.type;
    }

    /**  **/
    public void setType(String type) {
        this.type = type;
    }

    /**  **/
    public String getProject() {
        return this.project;
    }

    /**  **/
    public void setProject(String project) {
        this.project = project;
    }

    /**  **/
    public String getJobContent() {
        return this.jobContent;
    }

    /**  **/
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    /**  **/
    public String getPeopleNumber() {
        return this.peopleNumber;
    }

    /**  **/
    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**  **/
    public String getWorkHour() {
        return this.workHour;
    }

    /**  **/
    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    /**  **/
    public String getWorkHourUnit() {
        return this.workHourUnit;
    }

    /**  **/
    public void setWorkHourUnit(String workHourUnit) {
        this.workHourUnit = workHourUnit;
    }

    /**  **/
    public String getOutputContent() {
        return this.outputContent;
    }

    /**  **/
    public void setOutputContent(String outputContent) {
        this.outputContent = outputContent;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
