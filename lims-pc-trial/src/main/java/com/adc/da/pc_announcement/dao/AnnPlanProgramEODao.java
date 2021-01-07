package com.adc.da.pc_announcement.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_announcement.VO.SupProjectVO;
import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_PROGRAM AnnPlanProgramEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-25 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AnnPlanProgramEODao extends BaseDao<AnnPlanProgramEO> {
    /**
     * 查出所有申报项目对应的检验方案Id数组
     * @param projectIdList
     * @return
     */
    List<String> getIdListByPjIDList(List<String> projectIdList);

    /**
     * 通过ID数组，物理删除检验方案
     * @param programIdList
     */
    void delByIdList(List<String> programIdList);
    
    /**
	 * 根据项目id查找方案列表
	 * @param projectId
	 * @return
	 */
	List<AnnPlanProgramEO> findProgramByProjectId(String projectId);

    /**
     * 通过Id数组来查出所有的检验方案
     * @param idListByPjIDList
     * @return
     */
    List<AnnPlanProgramEO> selectByIDList(List<String> idListByPjIDList);

    /**
     * 找出对应供应商，对应检验项目
     * @param supplierID
     * @param projectCode
     * @return
     */
    List<SupProjectVO> getSupProject(@Param("supplierID") String supplierID, @Param("projectCode") String projectCode);
}
