package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;

public class DicTypeEOPage extends BasePage {
    private String id;

    private String idOperator = "=";

    private String dicTypeCode;

    private String dicTypeCodeOperator = "=";

    private String dicTypeName;

    private String dicTypeNameOperator = "=";

    private String dicId;

    private String dicIdOperator = "=";

    private Integer delFlag;

    private String delFlagOperator = "=";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getDicTypeCode() {
        return dicTypeCode;
    }

    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }

    public String getDicTypeCodeOperator() {
        return dicTypeCodeOperator;
    }

    public void setDicTypeCodeOperator(String dicTypeCodeOperator) {
        this.dicTypeCodeOperator = dicTypeCodeOperator;
    }

    public String getDicTypeName() {
        return dicTypeName;
    }

    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName;
    }

    public String getDicTypeNameOperator() {
        return dicTypeNameOperator;
    }

    public void setDicTypeNameOperator(String dicTypeNameOperator) {
        this.dicTypeNameOperator = dicTypeNameOperator;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getDicIdOperator() {
        return dicIdOperator;
    }

    public void setDicIdOperator(String dicIdOperator) {
        this.dicIdOperator = dicIdOperator;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

}
