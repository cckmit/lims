package com.adc.da.roadtestcost.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.roadtestcost.entity.RoadTestCostEo;
import org.apache.ibatis.annotations.Param;

public interface RoadTestCostDao extends BaseDao<RoadTestCostEo> {

    String getPriceByHorsePowerAndCarTypeAndRoadLine(@Param("horsePower") String horsePower,
                                                     @Param("driveCarType") String driveCarType,
                                                     @Param("roadLine") String roadLine);

}
