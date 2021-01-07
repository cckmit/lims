package com.adc.da.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.entity.QuestionUserEO;

public interface QuestionUserEODao extends BaseDao<QuestionUserEO> {
	/**
	 * 根据主题id,查询用户答题信息
	 * @Title: selectByTopicsId
	 * @param topicsId
	 * @return
	 * @return List<QuestionUserEO>
	 * @author: ljy
	 * @date: 2020年2月26日
	 */
    public List<QuestionUserEO> selectByTopicsId(String topicsId);
    
    /**
     * 查看答题详情
     * @Title: selectByUserId
     * @param topicsId
     * @param userId
     * @return
     * @return QuestionUserEO
     * @author: ljy
     * @date: 2020年3月30日
     */
    public QuestionUserEO selectByUserId(@Param("topicsId") String topicsId,
    		@Param("userId") String userId);
}