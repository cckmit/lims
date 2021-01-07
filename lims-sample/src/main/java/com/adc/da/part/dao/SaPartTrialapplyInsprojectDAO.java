package com.adc.da.part.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.part.entity.SaPartTrialapplyInsprojectEO;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 16:02
 * @description：${description}
 */
public interface SaPartTrialapplyInsprojectDAO extends BaseDao<SaPartTrialapplyInsprojectEO> {

    SaPartTrialapplyInsprojectEO selectByPrimaryKey(String id);

    /**
     * 通过零部件ID删除关联关系
     *
     * @param id
     */
    void deteleByPartId(@Param("id") String id);
}