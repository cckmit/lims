package com.adc.da.calender.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.calender.entity.PersonCalenderEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TP_PERSON_CALENDER PersonCalenderEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PersonCalenderEODao extends BaseDao<PersonCalenderEO> {

    /**
     * 批量插入数据
     * @param personCalenderEOList
     * @return
     */
    int insertBatch(List<PersonCalenderEO> personCalenderEOList);

}
