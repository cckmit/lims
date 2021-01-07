package com.adc.da.part.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.part.entity.SaPartFlowLogEO;
import com.adc.da.part.vo.SaPartFlowLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 16:01
 * @description：${description}
 */
public interface SaPartFlowLogDAO extends BaseDao<SaPartFlowLogEO> {

    SaPartFlowLogEO selectByPrimaryKey(String id);

    /**
     * 通过序列ID删除日志
     *
     * @param id
     */
    void deleteBySequenceId(@Param("id") String id);

    /**
     * 查询退样打印单
     *
     * @param id
     * @return
     */
    List<SaPartFlowLogVO> selectByPartId(@Param("id") String id, @Param("label") String label);
}