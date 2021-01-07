package com.adc.da.stdtype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.stdtype.entity.StdTypeEO;

/**
 * 基础数据管理模块--检验标准类型
 * @author ljy
 * @date 2019年7月12日
 */
public interface StdTypeEODao extends BaseDao<StdTypeEO> {
	/**
	 * 获取所有记录
	* @Title：findAll
	* @return
	* @return: List<StdTypeEO>
	* @author： ljy  
	* @date： 2019年7月12日
	 */
	public List<StdTypeEO> findAll();
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param id
	* @return
	* @return: List<StdTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<StdTypeEO> selectChildsById(String stdTypeParentId);
	
	/**
	 * 根据标准类型名称查询标准类型id(导入)
	 * @Title: selectIdByName
	 * @param stdTypeName
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2020年9月8日
	 */
	public String selectIdByName(@Param("stdTypeName") String stdTypeName);
}
