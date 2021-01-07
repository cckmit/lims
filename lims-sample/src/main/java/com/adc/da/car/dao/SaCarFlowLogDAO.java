package com.adc.da.car.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.car.entity.SaCarFlowLogEO;
import com.adc.da.car.vo.SaCarFlowLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
public interface SaCarFlowLogDAO extends BaseDao<SaCarFlowLogEO> {

    /**
     * 删除流转日志
     *
     * @param id
     */
    void deleteByCarId(@Param("id") String id);

    /**
     * 通过整车id获取退样日志
     *
     * @param id
     * @param label
     * @return
     */
    List<SaCarFlowLogVO> selectByCarId(@Param("id") String id, @Param("label") String label);
}