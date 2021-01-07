package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.BaseEntity;

/**
 * <p>字段列表：</p>
 * <li>id -> id</li>
 * <li>dicTypeCode -> dtypecode</li>
 * <li>dicTypeName -> dtypename</li>
 * <li>dicId -> dictionaryid</li>
 * <li>delFlag -> del</li>
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.DicTypeEORestController
 */
public class DicTypeEO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3658632939727891047L;

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

    private DictionaryEO dictionaryEO;

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

    public DictionaryEO getDictionaryEO() {
        return dictionaryEO;
    }

    public void setDictionaryEO(DictionaryEO dictionaryEO) {
        this.dictionaryEO = dictionaryEO;
    }
}
