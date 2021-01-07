package com.adc.da.supplier.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.supplier.entity.AbilityEO;
/**
 *
 * <br>
 * <b>功能：</b>SUP_ABILITY AbilityEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AbilityEODao extends BaseDao<AbilityEO> {

    /**
     * 根据code查询id
     * @param code
     * @return
     */
    String findSupByCode(String code);

}
