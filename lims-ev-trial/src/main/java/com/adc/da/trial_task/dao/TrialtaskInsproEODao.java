package com.adc.da.trial_task.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.trial_task.entity.TrialtaskInsproEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrialtaskInsproEODao extends BaseDao<TrialtaskInsproEO> {

    /*
     * @Author syxy_zhangyinghui
     * @Description 添加试验任务检验项目信息
     * @Date 16:09 2019/8/20
     * @Param
     * @return
     **/
    int insertTrialtaskInspro(TrialtaskInsproEO trialtaskInsproEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务id删除试验任务检验项目信息
     * @Date 16:14 2019/8/20
     * @Param
     * @return
     **/
    int deleteTrialtaskInsproByTrialTaskId(String trialtaskId);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据试验任务id查询试验任务检验项目信息
     * @Date 16:17 2019/8/20
     * @Param
     * @return
     **/
    List<TrialtaskInsproEO> findTrialtaskInsproByTrialtaskId(String trialtaskId);

    /**
     * 根据reportId查询
     * @Title: selectByReportId
     * @param reportId
     * @return
     * @return List<TrialtaskInsproEO>
     * @author: ljy
     * @date: 2019年10月28日
     */
    List<TrialtaskInsproEO> selectByReportId(String reportId);
}
