package com.adc.da.pc_drive.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_drive.entity.PcDrivingRecordEO;
import com.adc.da.pc_drive.vo.PcCurveVo;
import com.adc.da.pc_drive.vo.PcDrivingRecourdVo;
import com.adc.da.pc_drive.vo.PcTrialLineVo;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_DRIVING_RECORD PcDrivingRecordEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcDrivingRecordEODao extends BaseDao<PcDrivingRecordEO> {

    PcDrivingRecourdVo findDrivingRecord(String trialTaskID);

    /**
     * psqc状态下查询
     * @param trialTaskID
     * @return
     */
    PcDrivingRecourdVo findDrivingRecordByTaskId(String trialTaskID);

    List<PcCurveVo> findStandardCurve(String PcTrialTaskID);
    List<PcCurveVo> findActualCurve(String PcTrialTaskID);



}
