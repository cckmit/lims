package com.adc.da.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.entity.QuestionEO;

public interface QuestionEODao extends BaseDao<QuestionEO> {
	/**
	 * 根据主题id 查询问题列表
	 * @Title: selectByTopicsId
	 * @param topicsId
	 * @return
	 * @return List<QuestionEO>
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public List<QuestionEO> selectByTopicsId(String topicsId);
	
	/**
	 * 根据主题id 删除问题
	 * @Title: deleteByTopicsId
	 * @param topicsId
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public void deleteByTopicsId(String topicsId);
	
	/**
	 * 根据主题id 删除问题(逻辑删除)
	 * @Title: deleteQuestion
	 * @param topicsId
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public void deleteQuestion(String topicsId);
}