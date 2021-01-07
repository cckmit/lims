package com.adc.da.sys.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.DicTypeEO;
import org.apache.ibatis.annotations.Param;

/**
 * 字典类型Dao层, 具体sql在xml中
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface DicTypeEODao extends BaseDao<DicTypeEO> {

    /**
     * 获取字典类型
     *
     * @param id 字典类型id
     * @return 字典类型详情
     */
    public DicTypeEO getDicTypeEOById(String id);

    /**
     * 批量删除
     *
     * @param ids 字典类型组
     */
    public void deleteDicTypeByIdInBatch(List<String> ids);

    DicTypeEO getDicTypeEOByCode(String dicTypeCode);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据数据字典id查询类型
     * @Date 11:03 2019/8/2
     * @Param
     * @return
     **/
    public List<DicTypeEO> getDicTypeByDicId(String dicId);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据数据字典code和数据类型id查询数据字典类型
     * @Date 9:32 2019/8/23
     * @Param
     * @return
     **/
    public DicTypeEO getDicTypeByDicCodeAndDicTypeId(@Param("dictionaryCode") String dictionaryCode,
    		@Param("dicTypeCode") String dicTypeCode);
}
