package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.sys.entity.DictionaryEO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 数据字典Dao层，具体sql在xml中
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface DicEODao extends BaseDao<DictionaryEO> {

    /**
     * 获取字典信息
     *
     * @param id 字典id
     * @return 字典信息
     */
    public DictionaryEO getDictionaryEOById(String id);

    /**
     * 获取字典信息，用字典编码
     *
     * @param dictionaryCode 字典编码
     * @return 字典信息
     */
    public DictionaryEO getDictionaryEOByDicCode(String dictionaryCode);

    /**
     * 获取字典信息，用字典名称
     *
     * @param dictionaryName 字典名称
     * @return 字典信息
     */
    public DictionaryEO getDictionaryEOByDicName(String dictionaryName);

    /**
     * 删除数据字典
     *
     * @param id 字典id
     */
    public void deleteDic(String id);

    /**
     * 删除数字字典类型
     *
     * @param id 字典id
     */
    public void deleteDicAndDicType(String id);

    /**
     * 根据通用查询条件获得数据字典的详细信息
     * @param basePage
     * @return
     */
    public List<DictionaryEO> getDictionaryEOBySearchPhrase(BasePage basePage);

    /**
     * 根据通用查询条件查询数据字典详细信息的总数
     * @param basePage
     * @return
     */
    public Integer getDictionaryEOCountBySearchPhrase(BasePage basePage);

    /**
     * 通用查询时输入关键字查询关联数据字典的列（如：设备状态），将类型中包含该关键字的类型code全部查出来，方便后续的查询<br/>
     * 例如：输入“常”，查询设备状态列（可选项有：正常、异常、停用、超期未检），此方法会得到“正常”、“异常”的类型code，从而后续查询时将会查询出设备状态为“正常”或“异常”的记录
     * @param dicCode
     * @param search
     * @return
     */
	public List<String> getTypeCodesBySearch(String dicCode, String search);

}
