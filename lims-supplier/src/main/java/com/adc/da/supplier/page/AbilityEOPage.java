package com.adc.da.supplier.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SUP_ABILITY AbilityEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AbilityEOPage extends BasePage {

    private String supName;
    private String supNameOperator = "=";
    private String id;
    private String idOperator = "=";
    private String supType;
    private String removeType;  //此处增加了需求（当此字段不为空的时候，查询中过滤此供应商类型）
    private String supTypeOperator = "=";
    private String supQualifications;
    private String supQualificationsOperator = "=";
    private String supIndate;
    private String supIndate1;
    private String supIndate2;
    private String supIndateOperator = "=";
    private String supChangelog;
    private String supChangelogOperator = "=";
    private String supContractCode;
    private String supContractCodeOperator = "=";
    private String supConStartdate;
    private String supConStartdate1;
    private String supConStartdate2;
    private String supConStartdateOperator = "=";
    private String supConEnddate;
    private String supConEnddate1;
    private String supConEnddate2;
    private String supConEnddateOperator = "=";
    private String supManagerName;
    private String supManagerNameOperator = "=";
    private String supTelephone;
    private String supTelephoneOperator = "=";
    private String supMail;
    private String supMailOperator = "=";
    private String supManagerId;
    private String supManagerIdOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    private List<String> searchPhrase;

    public String getSupName() {
        return this.supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupNameOperator() {
        return this.supNameOperator;
    }

    public void setSupNameOperator(String supNameOperator) {
        this.supNameOperator = supNameOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getSupType() {
        return this.supType;
    }

    public void setSupType(String supType) {
        this.supType = supType;
    }

    public String getSupTypeOperator() {
        return this.supTypeOperator;
    }

    public void setSupTypeOperator(String supTypeOperator) {
        this.supTypeOperator = supTypeOperator;
    }

    public String getSupQualifications() {
        return this.supQualifications;
    }

    public void setSupQualifications(String supQualifications) {
        this.supQualifications = supQualifications;
    }

    public String getSupQualificationsOperator() {
        return this.supQualificationsOperator;
    }

    public void setSupQualificationsOperator(String supQualificationsOperator) {
        this.supQualificationsOperator = supQualificationsOperator;
    }

    public String getSupIndate() {
        return this.supIndate;
    }

    public void setSupIndate(String supIndate) {
        this.supIndate = supIndate;
    }

    public String getSupIndate1() {
        return this.supIndate1;
    }

    public void setSupIndate1(String supIndate1) {
        this.supIndate1 = supIndate1;
    }

    public String getSupIndate2() {
        return this.supIndate2;
    }

    public void setSupIndate2(String supIndate2) {
        this.supIndate2 = supIndate2;
    }

    public String getSupIndateOperator() {
        return this.supIndateOperator;
    }

    public void setSupIndateOperator(String supIndateOperator) {
        this.supIndateOperator = supIndateOperator;
    }

    public String getSupChangelog() {
        return this.supChangelog;
    }

    public void setSupChangelog(String supChangelog) {
        this.supChangelog = supChangelog;
    }

    public String getSupChangelogOperator() {
        return this.supChangelogOperator;
    }

    public void setSupChangelogOperator(String supChangelogOperator) {
        this.supChangelogOperator = supChangelogOperator;
    }

    public String getSupContractCode() {
        return this.supContractCode;
    }

    public void setSupContractCode(String supContractCode) {
        this.supContractCode = supContractCode;
    }

    public String getSupContractCodeOperator() {
        return this.supContractCodeOperator;
    }

    public void setSupContractCodeOperator(String supContractCodeOperator) {
        this.supContractCodeOperator = supContractCodeOperator;
    }

    public String getSupConStartdate() {
        return this.supConStartdate;
    }

    public void setSupConStartdate(String supConStartdate) {
        this.supConStartdate = supConStartdate;
    }

    public String getSupConStartdate1() {
        return this.supConStartdate1;
    }

    public void setSupConStartdate1(String supConStartdate1) {
        this.supConStartdate1 = supConStartdate1;
    }

    public String getSupConStartdate2() {
        return this.supConStartdate2;
    }

    public void setSupConStartdate2(String supConStartdate2) {
        this.supConStartdate2 = supConStartdate2;
    }

    public String getSupConStartdateOperator() {
        return this.supConStartdateOperator;
    }

    public void setSupConStartdateOperator(String supConStartdateOperator) {
        this.supConStartdateOperator = supConStartdateOperator;
    }

    public String getSupConEnddate() {
        return this.supConEnddate;
    }

    public void setSupConEnddate(String supConEnddate) {
        this.supConEnddate = supConEnddate;
    }

    public String getSupConEnddate1() {
        return this.supConEnddate1;
    }

    public void setSupConEnddate1(String supConEnddate1) {
        this.supConEnddate1 = supConEnddate1;
    }

    public String getSupConEnddate2() {
        return this.supConEnddate2;
    }

    public void setSupConEnddate2(String supConEnddate2) {
        this.supConEnddate2 = supConEnddate2;
    }

    public String getSupConEnddateOperator() {
        return this.supConEnddateOperator;
    }

    public void setSupConEnddateOperator(String supConEnddateOperator) {
        this.supConEnddateOperator = supConEnddateOperator;
    }

    public String getSupManagerName() {
        return this.supManagerName;
    }

    public void setSupManagerName(String supManagerName) {
        this.supManagerName = supManagerName;
    }

    public String getSupManagerNameOperator() {
        return this.supManagerNameOperator;
    }

    public void setSupManagerNameOperator(String supManagerNameOperator) {
        this.supManagerNameOperator = supManagerNameOperator;
    }

    public String getSupTelephone() {
        return this.supTelephone;
    }

    public void setSupTelephone(String supTelephone) {
        this.supTelephone = supTelephone;
    }

    public String getSupTelephoneOperator() {
        return this.supTelephoneOperator;
    }

    public void setSupTelephoneOperator(String supTelephoneOperator) {
        this.supTelephoneOperator = supTelephoneOperator;
    }

    public String getSupMail() {
        return this.supMail;
    }

    public void setSupMail(String supMail) {
        this.supMail = supMail;
    }

    public String getSupMailOperator() {
        return this.supMailOperator;
    }

    public void setSupMailOperator(String supMailOperator) {
        this.supMailOperator = supMailOperator;
    }

    public String getSupManagerId() {
        return this.supManagerId;
    }

    public void setSupManagerId(String supManagerId) {
        this.supManagerId = supManagerId;
    }

    public String getSupManagerIdOperator() {
        return this.supManagerIdOperator;
    }

    public void setSupManagerIdOperator(String supManagerIdOperator) {
        this.supManagerIdOperator = supManagerIdOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getRemoveType() {
        return removeType;
    }

    public void setRemoveType(String removeType) {
        this.removeType = removeType;
    }
}
