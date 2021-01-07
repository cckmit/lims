package com.adc.da.insproject.dao;

import java.util.List;

import com.adc.da.insproject.page.InsProjectEOPage;
import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.insproject.entity.InsProjectEO;

/**
 *  基础数据模块--检验项目管理
 * @author Administrator
 * @date 2019年7月17日
 */
public interface InsProjectEODao extends BaseDao<InsProjectEO> {
	/**
     * 获取检验项目列表
    * @Title：list
    * @param insProjectSearchVO
    * @return
    * @return: ResponseMessage<List<InsProjectSearchVO>>
    * @author： ljy  
    * @date： 2019年7月18日
     */
	public List<InsProjectEO> findAll();
	
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param proBelongId 所属项目id
	* @return
	* @return: List<InsProjectEO>
	* @author： ljy  
	* @date： 2019年7月18日
	 */
	public List<InsProjectEO> selectChildsById(String proBelongId);
	/**
	 * 根据检验项目id查询检验项目是否关联过试验任务
	* @Title：checkTrialTaskById
	* @param id
	* @return
	* @return: int
	* @author： ljy  
	* @date： 2019年9月23日
	 */
	public int checkTrialTaskById(String id);
	
	/**
	 * 根据名称和类型查询检验项目，只取第一条
	 * @param name
	 * @param type
	 * @return
	 */
	public InsProjectEO getByNameAndType(@Param("name")String name, @Param("type")String type);

	/**
	 * 根据类型id查询 类型下是否有数据
	 * @Title: selectInsProByTypeId
	 * @param proTypeId
	 * @return
	 * @return List<InsProjectEO>
	 * @author: ljy
	 * @date: 2019年12月31日
	 */
	public List<InsProjectEO> selectInsProByTypeId(String proTypeId);

    List<InsProjectEO> queryByPageForPcTask(InsProjectEOPage page);

	Integer queryByCountForPcTask(InsProjectEOPage page);
}
