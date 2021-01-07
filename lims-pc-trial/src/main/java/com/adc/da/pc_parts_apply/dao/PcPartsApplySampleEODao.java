package com.adc.da.pc_parts_apply.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.pc_parts_apply.entity.PcPartsApplySampleEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_PARTS_APPLY_SAMPLE PcPartsApplySampleEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcPartsApplySampleEODao extends BaseDao<PcPartsApplySampleEO> {

    List<PcPartsApplySampleEO> queryByApplyId(String applyId);

    void delByApplyId(String applyId);
}
