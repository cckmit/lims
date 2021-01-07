package com.adc.da.line.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.line.entity.BmLineCaseInfoEO;
import org.apache.ibatis.annotations.Param;

/**
 * <br>
 * <b>功能：</b>BM_LINE_CASE_INFO BmLineCaseInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BmLineCaseInfoEODao extends BaseDao<BmLineCaseInfoEO> {

    void deleteByCaseId(@Param("id") String id);
}
