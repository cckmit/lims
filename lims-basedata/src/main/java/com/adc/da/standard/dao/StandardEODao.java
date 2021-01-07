package com.adc.da.standard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.standard.entity.StandardEO;
/**
 * 基础数据模块--检验标准管理
 * @author Administrator
 * @date 2019年7月15日
 */
public interface StandardEODao extends BaseDao<StandardEO>{
	/**
	 * 获取所有记录
	* @Title：findAll
	* @return
	* @return: List<StandardEO>
	* @author： ljy  
	* @date： 2019年7月19日
	 */
	public List<String> findAll();
	/**
	 * 根据检验标准号查询检验标准id
	* @Title：selectByStdNo
	* @return
	* @return: String
	* @author： ljy  
	* @date： 2019年7月23日
	 */
	public String selectByStdNo(String stdNo);
	
	/**
	 * 检验标准-校验标准号唯一性
	 * @Title: checkNo
	 * @param id
	 * @return
	 * @return List<String>
	 * @author: ljy
	 * @date: 2019年12月30日
	 */
	public List<String> checkNo(@Param("id") String id);
	
	/**
	 * 根据类型id查询 类型下是否有数据
	 * @Title: selectStdByTypeId
	 * @return
	 * @return List<StandardEO>
	 * @author: ljy
	 * @date: 2019年12月31日
	 */
	public List<StandardEO> selectStdByTypeId(String stdTypeId);
}
