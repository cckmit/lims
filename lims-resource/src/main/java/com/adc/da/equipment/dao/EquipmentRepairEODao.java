package com.adc.da.equipment.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.equipment.entity.EquipmentRepairEO;
import com.adc.da.equipment.page.EquipmentRepairEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_REPAIR EquipmentRepairEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface EquipmentRepairEODao extends BaseDao<EquipmentRepairEO> {
    /**
     * 查出对应设备的维修记录
     * @param page
     * @return
     */
    List<EquipmentRepairEO> getRepairRecordByPage(EquipmentRepairEOPage page);
    /**
     * 查出对应设备的维修记录条数
     * @param page
     * @return
     */
    Integer queryCountForRepairRecord(EquipmentRepairEOPage page);
}
