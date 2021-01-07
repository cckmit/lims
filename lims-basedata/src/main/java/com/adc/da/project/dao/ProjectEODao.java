package com.adc.da.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.project.entity.ProjectEO;

/**
 * 基础数据模块--项目管理
 * @author Administrator
 * @date 2019年7月10日
 */
public interface ProjectEODao extends BaseDao<ProjectEO>{
	/**
	 * 获取所有记录
	* @Title：findAll
	* @return
	* @return: List<ProjectEO>
	* @author： ljy  
	* @date： 2019年7月10日
	 */
	public List<ProjectEO> findAll();
	
	/**
	 * 查询当前项目是否有子节点
	* @Title：selectChildsById
	* @param id
	* @return
	* @return: List<ProjectEO>
	* @author： ljy  
	* @date： 2019年7月11日
	 */
	public List<ProjectEO> selectChildsById(String proParentId);
	/**
	 * 查询所有编码,用于校验编码唯一性
	 * @Title: checkNo
	 * @param id
	 * @return
	 * @return List<String>
	 * @author: ljy
	 * @date: 2019年12月11日
	 */
	public List<String> checkNo(@Param("id") String id);
}
