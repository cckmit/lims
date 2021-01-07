package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;

import java.util.List;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【TS_PARAM】 功能【系统参数表】
 * @Date 15:23 2019/8/2
 **/
public class ParamEOPage extends BasePage {


    /*
     * @Author syxy_zhangyinghui
     * @Description 【主键id】
     * @Date 16:19 2019/8/2
     **/
    private String id;
    private String idOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建人用户id】
     * @Date 16:19 2019/8/2
     **/
    private String usId;
    private String usIdOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建人的组织机构id】
     * @Date 16:19 2019/8/2
     **/
    private String orgId;
    private String orgIdOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建时间】
     * @Date 16:20 2019/8/2
     **/
    private String createDate;
    private String createDateOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数代码】
     * @Date 16:20 2019/8/2
     **/
    private String paramCode;
    private String paramCodeOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数名称】
     * @Date 16:20 2019/8/2
     **/
    private String paramName;
    private String paramNameOperation = "=";

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数值】
     * @Date 16:20 2019/8/2
     **/
    private String paramValue;
    private String paramValueOperation = "=";
    /*
     * @Author syxy_zhangyinghui
     * @Description 【删除状态】
     * @Date 16:20 2019/8/2
     **/
    private String del;
    private String delOperation = "=";


    //通用查询条件
    private List<String> searchPhrase;
    private String searchPhraseOperation = "=";

    private String createDateStart;
    private String createDateStartOperation = "=";

    private String createDateEnd;
    private String createDateEndOperation = "=";

    private String paramType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getUsIdOperation() {
        return usIdOperation;
    }

    public void setUsIdOperation(String usIdOperation) {
        this.usIdOperation = usIdOperation;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgIdOperation() {
        return orgIdOperation;
    }

    public void setOrgIdOperation(String orgIdOperation) {
        this.orgIdOperation = orgIdOperation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateOperation() {
        return createDateOperation;
    }

    public void setCreateDateOperation(String createDateOperation) {
        this.createDateOperation = createDateOperation;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamCodeOperation() {
        return paramCodeOperation;
    }

    public void setParamCodeOperation(String paramCodeOperation) {
        this.paramCodeOperation = paramCodeOperation;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamNameOperation() {
        return paramNameOperation;
    }

    public void setParamNameOperation(String paramNameOperation) {
        this.paramNameOperation = paramNameOperation;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamValueOperation() {
        return paramValueOperation;
    }

    public void setParamValueOperation(String paramValueOperation) {
        this.paramValueOperation = paramValueOperation;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getDelOperation() {
        return delOperation;
    }

    public void setDelOperation(String delOperation) {
        this.delOperation = delOperation;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getSearchPhraseOperation() {
        return searchPhraseOperation;
    }

    public void setSearchPhraseOperation(String searchPhraseOperation) {
        this.searchPhraseOperation = searchPhraseOperation;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateStartOperation() {
        return createDateStartOperation;
    }

    public void setCreateDateStartOperation(String createDateStartOperation) {
        this.createDateStartOperation = createDateStartOperation;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getCreateDateEndOperation() {
        return createDateEndOperation;
    }

    public void setCreateDateEndOperation(String createDateEndOperation) {
        this.createDateEndOperation = createDateEndOperation;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
