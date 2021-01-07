package com.adc.da.pc_drive.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_drive.entity.RoadLineRealityEO;

import java.util.List;

public interface RoadLineRealityEODao extends BaseDao<RoadLineRealityEO> {
    List<RoadLineRealityEO> selectRoadLineRealityEoByDriveRecordId(String id);
    void updateRoadLineRealityEo(RoadLineRealityEO realityEO);
}
