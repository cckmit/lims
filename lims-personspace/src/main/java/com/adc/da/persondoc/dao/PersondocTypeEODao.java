package com.adc.da.persondoc.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.persondoc.entity.PersondocTypeEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TP_PERSONDOC_TYPE PersondocTypeEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PersondocTypeEODao extends BaseDao<PersondocTypeEO> {

    List<PersondocTypeEO> getsontype(String id);

    /**
     * 判断类型是否有子类型
     * @param id
     * @return
     */
    int querySonById(String id);

    /**
     * 判断code是否已存在
     * @param code
     * @return
     */
    int queryCountByCode(String code);

}
