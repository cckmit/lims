package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.BaseBusEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseBusEODao extends BaseDao<BaseBusEO> {


    /*
     * @Author syxy_zhangyinghui
     * @Description 添加业务流程关联信息
     * @Date 9:38 2019/8/23
     * @Param
     * @return
     **/
    int insertBaseBus(BaseBusEO baseBusEO);

    /**
     * 判断流程是否结束
     * @param id
     * @return
     */
    int isFinishied(String id);

    /**
     * 获取下一节点待办人ids
     * @param procId
     * @return
     */
    String fingNextUserId(String procId);

    /**
     * 获取下一节点待办人names
     * @param procId
     * @return
     */
    String fingNextUserNames(String procId);

    /**
     * 根据具体业务主键查询basebus
     * @param id
     * @return
     */
    List<BaseBusEO> selectByBusinessId(String id);

    /**
     * 收藏
     * @param id
     * @return
     */
    int collectionStatus(@Param("id") String id, @Param("status") String status);
}
