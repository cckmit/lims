package com.adc.da.persondoc.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.persondoc.entity.PersondocEO;
/**
 *
 * <br>
 * <b>功能：</b>TP_PERSONDOC PersondocEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PersondocEODao extends BaseDao<PersondocEO> {

    int queryDocByTypeId(String typeId);

}
