package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCMaterialsOutEO;

public interface PCMaterialsOutEODao extends BaseDao<PCMaterialsOutEO>  {
	/**
	 * 根据物资出门申请单id,查询流程实例id
	 * @Title: selectActProcIdById
	 * @param id
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public String selectActProcIdById(String id);
}