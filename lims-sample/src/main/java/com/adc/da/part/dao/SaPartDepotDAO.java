package com.adc.da.part.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.entity.SaPartDepotEO;
import com.adc.da.part.page.SaPartDepotEOPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 16:24
 * @description：${description}
 */
public interface SaPartDepotDAO extends BaseDao<SaPartDepotEO> {

    /**
     * 获取详情
     *
     * @param id
     * @param partSpaceStatus
     * @return
     */
    SaPartDepotEO selectByParam(@Param("id") String id, @Param("partSpaceStatus") int partSpaceStatus);

    /**
     * 删除子级
     *
     * @param id
     * @param userId
     * @param date
     */
    void deleteByParentId(@Param("id") String id, @Param("userId") String userId, @Param("date") String date);

    /**
     * 新增校验库房编号是否存在
     *
     * @param depotNumber
     * @return
     */
    List<SaPartDepotEO> queryByDepotNuhmber(@Param("depotNumber") String depotNumber);

    /**
     * 删除
     *
     * @param id
     * @param userId
     * @param date
     */
    void deleteById(@Param("id") String id, @Param("userId") String userId, @Param("date") String date);

    /**
     * 分页查询库位
     *
     * @param page
     * @return
     */
    List<SaPartDepotEO> getPartSpacePage(SaPartDepotEOPage page);

    /**
     * 校验货架号是否存在
     *
     * @param shelfNumber
     * @return
     */
    List<SaPartDepotEO> queryByShelfNumber(@Param("shelfNumber") Integer shelfNumber);

    /**
     * 校验货架号是否存在
     *
     * @param shelfNumber
     * @return
     */
    List<SaPartDepotEO> queryByShelfNumberAndPart(@Param("shelfNumber") Integer shelfNumber,@Param("partDepotId") String partDepotId);

    /**
     * 通过id查询子级的剩余库位数
     *
     * @param id
     * @return
     */
    Integer queryUnUsePartSpaceNumber(@Param("id") String id);

    /**
     * 获取库位状态
     *
     * @param partDepotId
     * @return
     */
    List<SaPartDepotEO> getPartSpaceStatus(@Param("partDepotId") String partDepotId);

    SaPartDataEO selectByParkNameAndRowNumber(@Param("parkName") String parkName, @Param("rowNumber") String rowNumber);

    /**
     * 获取库位
     * @param partDepotId
     * @return
     */
    List<SaPartDepotEO> getPart(@Param("partDepotId") String partDepotId);

    /**
     * 根据partDepotId、第几区、第几行、第几个货架获取库位
     */
    SaPartDepotEO getPartSpace(@Param("partDepotId") String partDepotId, @Param("areaNumber")  Integer areaNumber,
                               @Param("rowNumber") Integer rowNumber, @Param("shelfNumber") Integer shelfNumber);

}