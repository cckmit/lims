package com.adc.da.project.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.project.entity.TrialProjectEO;
import com.adc.da.project.vo.TrialProjectCarVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SUP_TRIAL_PROJECT TrialProjectEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface TrialProjectEODao extends BaseDao<TrialProjectEO> {

    void updateStatus(@Param("id") String id, @Param("status") String status);

    /**
     * 逻辑删除
     * @param id
     */
    void deleteFlag(String id);

    /**
     * 根据车型和底盘号获取路送路试委托单
     * @param carType
     * @param chassisCode
     * @return
     */
    TrialProjectEO getTrialProjectEOByCarTypeAndChassisCode(String carId);

    List<TrialProjectCarVO> selectCarTypeByPcId(String pcId);

    List<TrialProjectEO> getTrialProjectEOByCarIdAndPcId(@Param("carId") String carId,@Param("pcId") String pcId);
}
