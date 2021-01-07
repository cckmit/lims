package com.adc.da.instype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.instype.entity.InsTypeEO;

/**
 * 基础数据管理模块--检验项目类型
 * @author Administrator
 * @date 2019年7月15日
 */
public interface InsTypeEODao extends BaseDao<InsTypeEO>{
	/**
	 * 查询所有记录
	* @Title：findAll
	* @return
	* @return: List<InsTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<InsTypeEO> findAll();
	
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param insTypeParentId
	* @return
	* @return: List<InsTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<InsTypeEO> selectChildsById(String insTypeParentId);
	
	/**
	 * 根据类型名称查询id
	 * @Title: selectByTypeName
	 * @param insTypeName
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2020年3月24日
	 */
	public String selectByTypeName(String insTypeName);
	
	/**
	 * 根据类型名称查询类型list
	 * @Title: findByTypeName
	 * @param insTypeName
	 * @return
	 * @return List<InsTypeEO>
	 * @author: ljy
	 * @date: 2020年9月8日
	 */
	public List<InsTypeEO> findByTypeName(@Param("insTypeName") String insTypeName);
	
	/**
	 * 根据多个类型名称查询id
	 * @Title: findIdByName
	 * @param oneTypeName
	 * @param twoTypeName
	 * @param threeTypeName
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2020年9月9日
	 */
	public String findIdByName(@Param("oneTypeName") String oneTypeName, 
			@Param("twoTypeName") String twoTypeName, @Param("threeTypeName") String threeTypeName);
	
}
