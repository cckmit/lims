package com.adc.da.pc_outsource.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_outsource.entity.PcOutsourceProjectEO;
/**
 *
 * <br>
 * <b>功能：</b>PC_OUTSOURCE_PROJECT PcOutsourceProjectEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcOutsourceProjectEODao extends BaseDao<PcOutsourceProjectEO> {

    int updateByPrimaryKeySelective(PcOutsourceProjectEO pcOutsourceProjectEO);

    int insertSelective(PcOutsourceProjectEO pcOutsourceProjectEO);

    String selectActProcIdById(String id);
}
