package com.adc.da.trial_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.vo.TrialExecuteListVO;
import com.adc.da.trial_task.entity.TrialTaskEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/5 15:22
 * @description：${description}
 */
public interface TrialExecuteEODao extends BaseDao<TrialTaskEO> {

    /**
     * 发动机试验任务执行-点击“+”查询
     *
     * @param id
     * @return
     */
    List<TrialExecuteListVO> selectListById(@Param("id") String id);
}
