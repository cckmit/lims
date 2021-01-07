package com.adc.da.pc_outsource.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_outsource.entity.PCOutsourceSupProEO;

public interface PCOutsourceSupProEODao extends BaseDao<PCOutsourceSupProEO> {
	
	/**
	 * 根据委外立项单id 获取 检验项目列表
	 * @Title: findSupProListByOutsourceId
	 * @param outsourceId
	 * @return
	 * @return List<PCOutsourceSupProEO>
	 * @author: ljy
	 * @date: 2020年3月12日
	 */
	public List<PCOutsourceSupProEO> findSupProListByOutsourceId(String outsourceId);
	
	/**
	 * 根据委外立项单id 删除 检验项目列表
	 * @Title: deleteByOutSourceId
	 * @param outsourceId
	 * @return void
	 * @author: ljy
	 * @date: 2020年3月12日
	 */
	public void deleteByOutSourceId(String outsourceId);
	
	/**
	 * 根据委外立项单id 获取 检验项目列表(价格汇总后的)
	 * @Title: selectSupProList
	 * @param outsourceId
	 * @return
	 * @return List<PCOutsourceSupProEO>
	 * @author: ljy
	 * @date: 2020年3月12日
	 */
	public List<PCOutsourceSupProEO>  selectSupProList(String outsourceId);
}
