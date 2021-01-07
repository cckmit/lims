package com.adc.da.summary.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.summary.entity.CostSummaryEO;
import org.apache.ibatis.annotations.Param;

public interface CostSummaryEODao extends BaseDao<CostSummaryEO> {

    CostSummaryEO getCostSummaryEOBySupId(@Param("supId") String supId,@Param("trialTaskId")String trialTaskId,@Param("formKey")String formKey);
    CostSummaryEO getCostSummaryBySupId(@Param("supId") String supId,@Param("trialTaskId")String trialTaskId,@Param("formKey")String formKey);
    CostSummaryEO getCostSummaryBySupIdAndDetialId(@Param("supId") String supId,@Param("trialTaskId")String trialTaskId,@Param("detialId")String detialId,@Param("formKey")String formKey);
}
