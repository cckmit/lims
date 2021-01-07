package com.adc.da.sys.vo;

/**
 * 字典类型对应VO
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.DicTypeEORestController
 */
public class DicTypeVO {
    /**
     * 字典类型id
     */
    private String id;

    /**
     * 字典类型编码
     */
    private String dicTypeCode;

    /**
     * 字典类型名称
     */
    private String dicTypeName;

    /**
     * 字典id
     */
    private String dicId;

    /**
     * 删除标识
     */
    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDicTypeCode() {
        return dicTypeCode;
    }

    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }

    public String getDicTypeName() {
        return dicTypeName;
    }

    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
