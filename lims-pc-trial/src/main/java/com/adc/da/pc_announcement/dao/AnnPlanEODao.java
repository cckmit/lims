package com.adc.da.pc_announcement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.page.AnnPlanEOPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN AnnPlanEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AnnPlanEODao extends BaseDao<AnnPlanEO> {
    /**
     * 查询出对应创建人的所有计划
     * @param createBy
     * @return
     */
    List<AnnPlanEO> getAnnPlanList(String createBy);

    /**
     * 查询出对应创建人的所有计划分页
     * @param page
     * @return
     */
    List<AnnPlanEO> getAnnPlanPage(AnnPlanEOPage page);

    /**
     * 查询出对应创建人的所有计划条数
     * @param page
     * @return
     */
    Integer queryForAnnPlanCount(AnnPlanEOPage page);

    /**
     * 查询对应工程师所分配的计划
     * @param page
     * @return
     */
    List<AnnPlanEO> findAnnPlanForEngineer(AnnPlanEOPage page);
    /**
     * 查询对应工程师所分配的计划总条数
     * @param page
     * @return
     */
    Integer queryForEngineerAnnPlanCount(AnnPlanEOPage page);
}
