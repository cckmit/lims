package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>字段列表：</p>
 * <li>id -> id</li>
 * <li>dictionaryCode -> dictionarycode</li>
 * <li>dictionaryName -> dictionaryname</li>
 * <li>delFlag -> del</li>
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.DicEORestController
 */
public class DictionaryEO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3658632939727891047L;

    /**
     * 数据字典id
     */
    @ApiModelProperty("id")
    private String id;

    /**
     * 数据字典编码
     */
    @ApiModelProperty("数据字典编码")
    private String dictionaryCode;

    /**
     * 数据字典名称
     */
    private String dictionaryName;

    /**
     * 删除标识符
     */
    private Integer delFlag;

    /**
     * 数据字典类型列表
     */
    private List<DicTypeEO> dicTypeEOList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public List<DicTypeEO> getDicTypeEOList() {
        return dicTypeEOList;
    }

    public void setDicTypeEOList(List<DicTypeEO> dicTypeEOList) {
        this.dicTypeEOList = dicTypeEOList;
    }
}
