package com.adc.da.trial_task.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.vo.TrialtaskInsProjectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrialTaskEODao extends BaseDao<TrialTaskEO> {


    /*
     * @Author syxy_zhangyinghui
     * @Description 新增试验任务信息
     * @Date 11:47 2019/8/20
     * @Param
     * @return
     **/
    int insertTrialTask(TrialTaskEO trialTaskEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 修改试验任务信息
     * @Date 11:48 2019/8/20
     * @Param
     * @return
     **/
    int updateTrialTask(TrialTaskEO trialTaskEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务主键id进行逻辑删除 ==修改删除状态
     * @Date 11:52 2019/8/20
     * @Param
     * @return
     **/
    int tombstoneTrialTask(String id);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务主键id进行物理删除 == 直接从表中删除
     * @Date 11:53 2019/8/20
     * @Param
     * @return
     **/
    int actualDeleteTrialTask(String id);

    List<TrialTaskEO> findAllList();

    /**
     * 试验任务编号查重
     * @Title: checkNo
     * @param id
     * @return
     * @return List<String>
     * @author: ljy
     * @date: 2020年1月2日
     */
    List<String> checkNo(@Param("id") String id);

    /**
     * 发动机试验任务-点击“+”查询
     *
     * @param id
     * @return
     */
    List<TrialtaskInsProjectVO> queryTrialProject(@Param("id") String id);

    /**
     * 根据试验任务id,查询流程实例id
     *
     * @param id
     * @return
     * @Title：selectActProcIdById
     * @return: String
     * @author： ljy
     * @date： 2019年9月10日
     */
    String selectActProcIdById(String id);

    void updateTrialTaskStatus(@Param("i") Integer i, @Param("trialtaskId") String trialtaskId);
}
