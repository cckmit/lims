package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.sys.entity.DicTypeEO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据字典对应VO
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.DicEORestController
 */
public class DictionaryVO {
    /**
     * 数据字典id
     */
    private String id;

    /**
     * 字典编码
     */
    @ApiModelProperty("数据字典编码")
    private String dictionaryCode;

    /**
     * 字典名称
     */
    private String dictionaryName;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 字典类型列表
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
