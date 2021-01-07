package com.adc.da.project.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.project.entity.ItemsDetailsEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SUP_ITEMS_DETAILS ItemsDetailsEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface ItemsDetailsEODao extends BaseDao<ItemsDetailsEO> {

    /**
     * 根据委托id查询
     * @param trialId
     * @return
     */
    List<ItemsDetailsEO> selectByTrialId(String trialId);

    /**
     * 根据委托id删除
     * @param trialId
     * @return
     */
    int deleteByTrialId(String trialId);

}
