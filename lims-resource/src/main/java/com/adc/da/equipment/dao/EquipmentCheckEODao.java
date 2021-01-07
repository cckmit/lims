package com.adc.da.equipment.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.equipment.entity.EquipmentCheckEO;
import com.adc.da.equipment.page.EquipmentCheckEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_CHECK EquipmentCheckEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface EquipmentCheckEODao extends BaseDao<EquipmentCheckEO> {

    /**
     * 查出对应设备Id的核检计划
     * @param eqId
     * @return
     */
    List<EquipmentCheckEO> findPlanCheck(String eqId);

    /**
     *  查出对应设备Id的核检记录
     * @param eqId
     * @return
     */
    List<EquipmentCheckEO> findCheckRecord(String eqId);

    /**
     *  查出待提醒的 所有未核检的计划
     * @param
     * @return
     */
    List<EquipmentCheckEO> findUnCheckRecord();

    /**
     * 分页查询出当前设备的所有核检记录
     * @param page
     * @return
     */
    List<EquipmentCheckEO> findCheckRecordByPage(EquipmentCheckEOPage page);

    /**
     * 在查出当前设备的所有核检记录中。查询出对应记录的所有的条数
     * @param page
     * @return
     */
    Integer queryForCheckRecordByPage(EquipmentCheckEOPage page);
}
