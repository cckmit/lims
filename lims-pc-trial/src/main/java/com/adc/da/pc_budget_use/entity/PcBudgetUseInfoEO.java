package com.adc.da.pc_budget_use.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_BUDGET_USE_INFO PcBudgetUseInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetUseInfoEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private PcBudgetUseInfoEO pcBudgetUseInfoEO;
    @ApiModelProperty("费用使用申请单id")
    private String buId;
    @ApiModelProperty("项目")
    private String buProject;
    @ApiModelProperty("内容")
    private String buContent;
    @ApiModelProperty("单价")
    private String buUnitPrice;
    @ApiModelProperty("数量")
    private String buCount;
    @ApiModelProperty("小计")
    private String buSubtotal;
    @ApiModelProperty("服务方")
    private String buSupplier;
    //-----------20200603需求变更添加字段
    @ApiModelProperty("服务方ID")
    private String buSupplierId;
    @ApiModelProperty("项目ID")
    private String buProjectId;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>buId -> bu_id</li>
     * <li>buProject -> bu_project</li>
     * <li>buContent -> bu_content</li>
     * <li>buUnitPrice -> bu_unit_price</li>
     * <li>buCount -> bu_count</li>
     * <li>buSubtotal -> bu_subtotal</li>
     * <li>buSupplier -> bu_supplier</li>
     * <li>attachmentId -> attachment_id</li>
     * <li>attachmentName -> attachment_name</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id":
                return "id";
            case "delFlag":
                return "del_flag";
            case "createTime":
                return "create_time";
            case "createBy":
                return "create_by";
            case "updateTime":
                return "update_time";
            case "updateBy":
                return "update_by";
            case "buId":
                return "bu_id";
            case "buProject":
                return "bu_project";
            case "buContent":
                return "bu_content";
            case "buUnitPrice":
                return "bu_unit_price";
            case "buCount":
                return "bu_count";
            case "buSubtotal":
                return "bu_subtotal";
            case "buSupplier":
                return "bu_supplier";
            case "buSupplierId":
                return "bu_Supplier_Id";
            case "buProjectId":
                return "bu_Project_Id";

            default:
                return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>bu_id -> buId</li>
     * <li>bu_project -> buProject</li>
     * <li>bu_content -> buContent</li>
     * <li>bu_unit_price -> buUnitPrice</li>
     * <li>bu_count -> buCount</li>
     * <li>bu_subtotal -> buSubtotal</li>
     * <li>bu_supplier -> buSupplier</li>
     * <li>attachment_id -> attachmentId</li>
     * <li>attachment_name -> attachmentName</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id":
                return "id";
            case "del_flag":
                return "delFlag";
            case "create_time":
                return "createTime";
            case "create_by":
                return "createBy";
            case "update_time":
                return "updateTime";
            case "update_by":
                return "updateBy";
            case "bu_id":
                return "buId";
            case "bu_project":
                return "buProject";
            case "bu_content":
                return "buContent";
            case "bu_unit_price":
                return "buUnitPrice";
            case "bu_count":
                return "buCount";
            case "bu_subtotal":
                return "buSubtotal";
            case "bu_supplier":
                return "buSupplier";
            case "bu_Supplier_Id":
                return "buSupplierId";
            case "bu_Project_Id":
                return "buProjectId";
            default:
                return null;
        }
    }

    /**
     *
     **/
    public String getId() {
        return this.id;
    }

    /**
     *
     **/
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**
     *
     **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     *
     **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     *
     **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     *
     **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     *
     **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     *
     **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     *
     **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     *
     **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     *
     **/
    public String getBuId() {
        return this.buId;
    }

    /**
     *
     **/
    public void setBuId(String buId) {
        this.buId = buId;
    }

    /**
     *
     **/
    public String getBuProject() {
        return this.buProject;
    }

    /**
     *
     **/
    public void setBuProject(String buProject) {
        this.buProject = buProject;
    }

    /**
     *
     **/
    public String getBuContent() {
        return this.buContent;
    }

    /**
     *
     **/
    public void setBuContent(String buContent) {
        this.buContent = buContent;
    }

    /**
     *
     **/
    public String getBuUnitPrice() {
        return this.buUnitPrice;
    }

    /**
     *
     **/
    public void setBuUnitPrice(String buUnitPrice) {
        this.buUnitPrice = buUnitPrice;
    }

    /**
     *
     **/
    public String getBuCount() {
        return this.buCount;
    }

    /**
     *
     **/
    public void setBuCount(String buCount) {
        this.buCount = buCount;
    }

    /**
     *
     **/
    public String getBuSubtotal() {
        return this.buSubtotal;
    }

    /**
     *
     **/
    public void setBuSubtotal(String buSubtotal) {
        this.buSubtotal = buSubtotal;
    }

    /**
     *
     **/
    public String getBuSupplier() {
        return this.buSupplier;
    }

    /**
     *
     **/
    public void setBuSupplier(String buSupplier) {
        this.buSupplier = buSupplier;
    }


    public PcBudgetUseInfoEO getPcBudgetUseInfoEO() {
        return pcBudgetUseInfoEO;
    }

    public void setPcBudgetUseInfoEO(PcBudgetUseInfoEO pcBudgetUseInfoEO) {
        this.pcBudgetUseInfoEO = pcBudgetUseInfoEO;
    }

    public String getBuSupplierId() {
        return buSupplierId;
    }

    public void setBuSupplierId(String buSupplierId) {
        this.buSupplierId = buSupplierId;
    }

    public String getBuProjectId() {
        return buProjectId;
    }

    public void setBuProjectId(String buProjectId) {
        this.buProjectId = buProjectId;
    }
}
