package com.adc.da.equipment.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.equipment.entity.EquipmentScrapRecordEO;
import com.adc.da.equipment.page.EquipmentScrapRecordEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_SCRAP_RECORD EquipmentScrapRecordEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface EquipmentScrapRecordEODao extends BaseDao<EquipmentScrapRecordEO> {

    Integer queryCountForEqScrap(String eqId);

    EquipmentScrapRecordEO selectScrapRecordByEqId(String eqId);

    void updateByScrapEO(EquipmentScrapRecordEO scrapRecordEO);

    /**
     * 查询该设备所有的借用归还记录
     * @param page
     * @return
     */
    List<EquipmentScrapRecordEO> getScrapRecordByPage(EquipmentScrapRecordEOPage page);
    /**
     * 查询该设备所有的借用归还条数
     * @param page
     * @return
     */
    Integer queryCountForScrapRecord(EquipmentScrapRecordEOPage page);
}
