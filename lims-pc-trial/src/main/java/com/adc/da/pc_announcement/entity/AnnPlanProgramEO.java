package com.adc.da.pc_announcement.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>ANN_PLAN_PROGRAM AnnPlanProgramEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-01-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AnnPlanProgramEO extends BaseEntity {

    @ApiModelProperty("价格")
    private String tpTotalPrice;
    @ApiModelProperty("系数")
    private String tpCoefficient;
    @ApiModelProperty("单价")
    private String tpUnitPrice;
    @ApiModelProperty("项目说明")
    private String tpPjDescription;
    @ApiModelProperty("配置说明")
    private String tpConfigExplain;
    @ApiModelProperty("实测项目数")
    private String tpActualPjAmount;
    @ApiModelProperty("检验报告编号")
    private String tpTestReportNo;
    @ApiModelProperty("产品型号")
    private String tpProductType;
    @ApiModelProperty("产品ID")
    private String tpProductId;
    @ApiModelProperty("申请检验类别")
    private String tpTestType;
    @ApiModelProperty("检验依据")
    private String tpTestBasis;
    @ApiModelProperty("检验项目ID")
    private String tpTestPjId;
    @ApiModelProperty("检验项目名称")
    private String tpTestPjName;
    @ApiModelProperty("检验项目编号")
    private String tpTestPjNo;
    @ApiModelProperty("申报项目Id")
    private String projectId;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("费用类型")
    private String tpCostType;
    @ApiModelProperty("项目类型")
    private String tpPjType;
    @ApiModelProperty("供应商项目id")
    private String supManagerId;
    @ApiModelProperty("供应商项目名称")
    private String supName;
    //20200604需求变更
    /*
    *折扣
     */
    @ApiModelProperty("折扣")
    private String discount;
    //20200730现场申报项目可以设置延期申报
    @ApiModelProperty("项目是否延期申报:0:非延期申报；1：延期申报")
    private String ifDelay;
    @ApiModelProperty("项目是否在实际新增的:0：非实际新增；1：实际新增")
    private String ifActualAdd;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>tpTotalPrice -> tp_total_price</li>
     * <li>tpCoefficient -> tp_coefficient</li>
     * <li>tpUnitPrice -> tp_unit_price</li>
     * <li>tpPjDescription -> tp_pj_description</li>
     * <li>tpConfigExplain -> tp_config_explain</li>
     * <li>tpActualPjAmount -> tp_actual_pj_amount</li>
     * <li>tpTestReportNo -> tp_test_report_no</li>
     * <li>tpProductType -> tp_product_type</li>
     * <li>tpProductId -> tp_product_id</li>
     * <li>tpTestType -> tp_test_type</li>
     * <li>tpTestBasis -> tp_test_basis</li>
     * <li>tpTestPjId -> tp_test_pj_id</li>
     * <li>tpTestPjName -> tp_test_pj_name</li>
     * <li>tpTestPjNo -> tp_test_pj_no</li>
     * <li>projectId -> project_id</li>
     * <li>id -> id</li>
     * <li>tpCostType -> tp_cost_type</li>
     * <li>tpPjType -> tp_pj_type</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "tpTotalPrice": return "tp_total_price";
            case "tpCoefficient": return "tp_coefficient";
            case "tpUnitPrice": return "tp_unit_price";
            case "tpPjDescription": return "tp_pj_description";
            case "tpConfigExplain": return "tp_config_explain";
            case "tpActualPjAmount": return "tp_actual_pj_amount";
            case "tpTestReportNo": return "tp_test_report_no";
            case "tpProductType": return "tp_product_type";
            case "tpProductId": return "tp_product_id";
            case "tpTestType": return "tp_test_type";
            case "tpTestBasis": return "tp_test_basis";
            case "tpTestPjId": return "tp_test_pj_id";
            case "tpTestPjName": return "tp_test_pj_name";
            case "tpTestPjNo": return "tp_test_pj_no";
            case "projectId": return "project_id";
            case "id": return "id";
            case "tpCostType": return "tp_cost_type";
            case "tpPjType": return "tp_pj_type";
            case "supManagerId": return "sup_Manager_Id";
            case "supName": return "sup_Name";
            case "discount": return "discount";
            case "ifDelay": return "if_Delay";
            case "ifActualAdd": return "if_Actual_Add";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>tp_total_price -> tpTotalPrice</li>
     * <li>tp_coefficient -> tpCoefficient</li>
     * <li>tp_unit_price -> tpUnitPrice</li>
     * <li>tp_pj_description -> tpPjDescription</li>
     * <li>tp_config_explain -> tpConfigExplain</li>
     * <li>tp_actual_pj_amount -> tpActualPjAmount</li>
     * <li>tp_test_report_no -> tpTestReportNo</li>
     * <li>tp_product_type -> tpProductType</li>
     * <li>tp_product_id -> tpProductId</li>
     * <li>tp_test_type -> tpTestType</li>
     * <li>tp_test_basis -> tpTestBasis</li>
     * <li>tp_test_pj_id -> tpTestPjId</li>
     * <li>tp_test_pj_name -> tpTestPjName</li>
     * <li>tp_test_pj_no -> tpTestPjNo</li>
     * <li>project_id -> projectId</li>
     * <li>id -> id</li>
     * <li>tp_cost_type -> tpCostType</li>
     * <li>tp_pj_type -> tpPjType</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "tp_total_price": return "tpTotalPrice";
            case "tp_coefficient": return "tpCoefficient";
            case "tp_unit_price": return "tpUnitPrice";
            case "tp_pj_description": return "tpPjDescription";
            case "tp_config_explain": return "tpConfigExplain";
            case "tp_actual_pj_amount": return "tpActualPjAmount";
            case "tp_test_report_no": return "tpTestReportNo";
            case "tp_product_type": return "tpProductType";
            case "tp_product_id": return "tpProductId";
            case "tp_test_type": return "tpTestType";
            case "tp_test_basis": return "tpTestBasis";
            case "tp_test_pj_id": return "tpTestPjId";
            case "tp_test_pj_name": return "tpTestPjName";
            case "tp_test_pj_no": return "tpTestPjNo";
            case "project_id": return "projectId";
            case "id": return "id";
            case "tp_cost_type": return "tpCostType";
            case "tp_pj_type": return "tpPjType";
            case "sup_Manager_Id": return "supManagerId";
            case "sup_Name": return "supName";
            case "discount": return "discount";
            case "if_Delay": return "ifDelay";
            case "if_Actual_Add": return "ifActualAdd";
            default: return null;
        }
    }
    
    public String getTpTotalPrice() {
        return this.tpTotalPrice;
    }

    public void setTpTotalPrice(String tpTotalPrice) {
        this.tpTotalPrice = tpTotalPrice;
    }

    public String getTpCoefficient() {
        return this.tpCoefficient;
    }

    public void setTpCoefficient(String tpCoefficient) {
        this.tpCoefficient = tpCoefficient;
    }

    public String getTpUnitPrice() {
        return this.tpUnitPrice;
    }

    public void setTpUnitPrice(String tpUnitPrice) {
        this.tpUnitPrice = tpUnitPrice;
    }

    public String getTpPjDescription() {
        return this.tpPjDescription;
    }

    public void setTpPjDescription(String tpPjDescription) {
        this.tpPjDescription = tpPjDescription;
    }

    public String getTpConfigExplain() {
        return this.tpConfigExplain;
    }

    public void setTpConfigExplain(String tpConfigExplain) {
        this.tpConfigExplain = tpConfigExplain;
    }

    public String getTpActualPjAmount() {
        return this.tpActualPjAmount;
    }

    public void setTpActualPjAmount(String tpActualPjAmount) {
        this.tpActualPjAmount = tpActualPjAmount;
    }

    public String getTpTestReportNo() {
        return this.tpTestReportNo;
    }

    public void setTpTestReportNo(String tpTestReportNo) {
        this.tpTestReportNo = tpTestReportNo;
    }

    public String getTpProductType() {
        return this.tpProductType;
    }

    public void setTpProductType(String tpProductType) {
        this.tpProductType = tpProductType;
    }

    public String getTpProductId() {
        return this.tpProductId;
    }

    public void setTpProductId(String tpProductId) {
        this.tpProductId = tpProductId;
    }

    public String getTpTestType() {
        return this.tpTestType;
    }

    public void setTpTestType(String tpTestType) {
        this.tpTestType = tpTestType;
    }

    public String getTpTestBasis() {
        return this.tpTestBasis;
    }

    public void setTpTestBasis(String tpTestBasis) {
        this.tpTestBasis = tpTestBasis;
    }

    public String getTpTestPjId() {
        return this.tpTestPjId;
    }

    public void setTpTestPjId(String tpTestPjId) {
        this.tpTestPjId = tpTestPjId;
    }

    public String getTpTestPjName() {
        return this.tpTestPjName;
    }

    public void setTpTestPjName(String tpTestPjName) {
        this.tpTestPjName = tpTestPjName;
    }

    public String getTpTestPjNo() {
        return this.tpTestPjNo;
    }

    public void setTpTestPjNo(String tpTestPjNo) {
        this.tpTestPjNo = tpTestPjNo;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpCostType() {
        return this.tpCostType;
    }

    public void setTpCostType(String tpCostType) {
        this.tpCostType = tpCostType;
    }

    public String getTpPjType() {
        return this.tpPjType;
    }

    public void setTpPjType(String tpPjType) {
        this.tpPjType = tpPjType;
    }

    public String getSupManagerId() {
        return supManagerId;
    }

    public void setSupManagerId(String supManagerId) {
        this.supManagerId = supManagerId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIfDelay() {
        return ifDelay;
    }

    public void setIfDelay(String ifDelay) {
        this.ifDelay = ifDelay;
    }

    public String getIfActualAdd() {
        return ifActualAdd;
    }

    public void setIfActualAdd(String ifActualAdd) {
        this.ifActualAdd = ifActualAdd;
    }
}
