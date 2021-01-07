package com.adc.da.acttask.dao;

import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.base.dao.BaseDao;

import java.util.List;


public interface ActTaskEODao extends BaseDao<ActTaskEO> {

    /**
     * 待办分页查询
     * @param page
     * @return
     */
    List<ActTaskEO> ruTaskPage(ActTaskEOPage page);

    /**
     * 已办分页查询
     * @param page
     * @return
     */
    List<ActTaskEO> hiTaskPage(ActTaskEOPage page);

    /**
     * 跟踪分页查询
     * @param page
     * @return
     */
    List<ActTaskEO> trackTaskPage(ActTaskEOPage page);

    /**
     * 待办总数
     * @param page
     * @return
     */
    int queryRuCount(ActTaskEOPage page);

    /**
     * 已办总数
     * @param page
     * @return
     */
    int queryHiCount(ActTaskEOPage page);

    /**
     * 跟踪总数
     * @param page
     * @return
     */
    int queryTrackCount(ActTaskEOPage page);

    /**
     * 根据basebusId查询任务id
     * @param baseBusId
     * @return
     */
    String queryTaskId(String baseBusId);

    /**
     * 根据basebusId查询任务id
     * @param baseBusId
     * @return
     */
    String queryProcId(String baseBusId);




}
