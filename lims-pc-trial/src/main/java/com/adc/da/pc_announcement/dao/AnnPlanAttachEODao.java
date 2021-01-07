package com.adc.da.pc_announcement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_announcement.entity.AnnPlanAttachEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_ATTACH AnnPlanAttachEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-30 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AnnPlanAttachEODao extends BaseDao<AnnPlanAttachEO> {
    List<AnnPlanAttachEO> selectByPlId(@Param("annPlId") String annPlId);
}
