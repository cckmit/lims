package com.adc.da.part.dao;

import com.adc.da.part.entity.SaPartSequenceEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/5 15:20
 * @description：${description}
 */
public interface SaPartSequenceDAO {
    int insertSelective(SaPartSequenceEO record);

    SaPartSequenceEO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaPartSequenceEO record);

    /**
     * 通过零部件ID查询序列
     *
     * @param id
     * @return
     */
    List<SaPartSequenceEO> selectByPartDataId(@Param("id") String id);

    /**
     * 通过零部件ID和状态查询序列
     *
     * @param id
     * @return
     */
    List<SaPartSequenceEO> selectByPartDataIdAndStatus(@Param("id") String id,@Param("partStatus") Integer partStatus);

    void deleteById(@Param("id") String id);
    /**
     * 查询当前序列号状态的个数
     */
    int selectByStatus(@Param("partId") String partId,@Param("status") Integer status);
}