package com.adc.da.roadcost.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.roadcost.entity.RoadCostEo;
import org.apache.ibatis.annotations.Param;

public interface RoadCostDao extends BaseDao<RoadCostEo> {

    String getPriceByHorsePowerAndCarTypeAndRoadLine(@Param("horsePower") String horsePower,
                                                     @Param("driveCarType") String driveCarType,
                                                     @Param("roadLine") String roadLine);
}
