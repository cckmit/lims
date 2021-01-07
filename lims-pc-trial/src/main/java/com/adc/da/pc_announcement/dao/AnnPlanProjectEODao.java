package com.adc.da.pc_announcement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_PROJECT AnnPlanProjectEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AnnPlanProjectEODao extends BaseDao<AnnPlanProjectEO> {
    /**
     * 根据计划Id，查出所有关联的申报项目
     * @param planID
     * @return
     */
    List<AnnPlanProjectEO> selectListByPlanID(@Param("planID") String planID);

    /**
     * 根据计划Id，查出所有关联的申报项目ID
     * @param planID
     * @return
     */
    List<String> getIDListByPlanId(@Param("planID") String planID);

    /**
     * 通过ID主键数组，删除对应的数据
     * @param projectIdList
     */
    void delByIdList(@Param("ids") List<String> projectIdList);

    /**
     * 查出对应计划，对应工程师的所有申报项目
     * @param planID
     * @param engineerID
     * @return
     */
    List<AnnPlanProjectEO> getEngineerAnnPlanProjectList(@Param("planId") String planID,@Param("engineerId") String engineerID);

    String getSupplierNameByID(@Param("supplierID") String supplierID);
}
