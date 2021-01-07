package com.adc.da.trial_task.dao;

import com.adc.da.trial_task.entity.TrialtaskSampleEO;

import java.util.List;

public interface TrialtaskSampleEODao {

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增试验任务样品信息
     * @Date 15:43 2019/8/20
     * @Param
     * @return
     **/
    int insertTrialtaskSample(TrialtaskSampleEO trialtaskSampleEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务id删除试验任务样品信息
     * @Date 16:48 2019/8/20
     * @Param
     * @return
     **/
    int deleteTrialtaskSampleByTrialtaskId(String trailtaskId);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务id查询试验任务样品信息
     * @Date 16:49 2019/8/20
     * @Param
     * @return
     **/
    List<TrialtaskSampleEO> findTrialtaskSampleByTrialtaskId(String trailtaskId);
}
