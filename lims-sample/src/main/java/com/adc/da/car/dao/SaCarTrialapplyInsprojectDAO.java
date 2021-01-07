package com.adc.da.car.dao;

import com.adc.da.car.vo.TrialtaskSampleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/15 9:10
 * @description：${description}
 */
public interface SaCarTrialapplyInsprojectDAO {

    /**
     * 获取试验委托和整车编号
     *
     * @param id
     * @return
     */
    List<TrialtaskSampleVO> selectListByCarId(@Param("id")String id);

    /**
     * 通过carID删除关联关系
     *
     * @param id
     */
    void deleteByCarId(@Param("id")String id);
}