package com.adc.da.pc_parts_apply.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_parts_apply.entity.PcPartsApplyEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_PARTS_APPLY PcPartsApplyEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcPartsApplyEODao extends BaseDao<PcPartsApplyEO> {

    void changeStatus(@Param("id") String id, @Param("status") String status);

    List<PcPartsApplyEO> queryByApplyNo(String applyNo);

}
