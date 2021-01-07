package com.adc.da.equipment.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.equipment.entity.EquipmentBorrowRecordEO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_BORROW_RECORD EquipmentBorrowRecordEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface EquipmentBorrowRecordEODao extends BaseDao<EquipmentBorrowRecordEO> {
    /**
     * 通过设备id和设备借用状态来唯一确定表中记录
     * @param eqId
     * @return
     */
    EquipmentBorrowRecordEO selectBorrowRecordByEqId(String eqId);

    void updateByBorrowEO(EquipmentBorrowRecordEO borrowRecordEO);

    Integer queryCountForEqBorrow(String eqId);
    String getBorrowId(@Param("eqId")String eqId);
}
