package com.adc.da.pc_drive.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_drive.entity.PcDriveRecordEO;
import com.adc.da.pc_drive.entity.RoadLineEO;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_drive.vo.TrialProjectVO;
import com.adc.da.pc_drive.vo.TrustRelated;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>PC_DRIVE_RECORD PcDriveRecordEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-07-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcDriveRecordEODao extends BaseDao<PcDriveRecordEO> {

    void insertRoadLineEo(RoadLineEO roadLineEO);

    void updateRoadLineEo(RoadLineEO roadLineEO);

    List<RoadLineEO> selectRoadLineEoByDriveRecordId(String id);

    PcDriveRecordEO selectByProjectId(String projectId);

    TrustRelated getTrustByTaskNum(String taskNum);


    Integer getDriveRecordsCountByChassisCode(@Param("carId") String carId, @Param("taskOrPlan") String taskOrPlan);

    //获取路送路试委托单信息
    TrialProjectVO selectTrialProjectVO(@Param("id") String trialProjectId);
    //获取供应商相关信息
    AbilityVO selectAbilityVO(@Param("id") String id);

    //获取行车记录路线
    RoadLineEO getRoadLineEOById(String id);

}

