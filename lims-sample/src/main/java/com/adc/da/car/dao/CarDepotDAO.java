package com.adc.da.car.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.car.entity.SaCarDepotEO;
import com.adc.da.car.page.CarDepotEOPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/8 11:45
 * @description：${description}
 */
public interface CarDepotDAO extends BaseDao<SaCarDepotEO> {

    /**
     * 校验库房编号是否存在
     *
     * @param depotNumber
     * @return
     */
    List<SaCarDepotEO> queryByDepotNuhmber(@Param("depotNumber") String depotNumber);

    /**
     * 分页查询车位
     *
     * @param page
     * @return
     */
    List<SaCarDepotEO> getCarSpacePage(CarDepotEOPage page);

    /**
     * 新增车位校验行号是否存在
     *
     * @param rowNumber
     * @return
     */
    List<SaCarDepotEO> queryByRowNumber(@Param("carDepotId") String carDepotId, @Param("rowNumber") Integer rowNumber);

    /**
     * 通过id获取详情
     *
     * @param id
     * @param carSpaceStatus
     * @return
     */
    SaCarDepotEO selectByParam(@Param("id") String id, @Param("carSpaceStatus") Integer carSpaceStatus);

    /**
     * 删除
     *
     * @param id
     * @param userId
     * @param date
     */
    void deletePartByParentId(@Param("id") String id, @Param("userId") String userId, @Param("date") String date);

    /**
     * 删除
     *
     * @param id
     * @param userId
     * @param date
     */
    void deleteById(@Param("id") String id, @Param("userId") String userId, @Param("date") String date);

    /**
     * 通过库房ID查询库房下未使用的车位
     *
     * @param id
     * @return
     */
    Integer queryUnUseCarSpaceNumber(@Param("id") String id);

    /**
     * 获取库位状态
     *
     * @param carDepotId
     * @return
     */
    List<SaCarDepotEO> getPartSpaceStatus(@Param("carDepotId") String carDepotId);

    /**
     * @param parkName
     * @param rowNumber
     * @return
     */
    SaCarDepotEO selectByParkNameAndRowNumber(@Param("parkName") String parkName, @Param("rowNumber") String rowNumber);

    /**
     * 查询数量
     * @param page
     * @return
     */
    Integer getCarSpaceByCount(CarDepotEOPage page);
}
