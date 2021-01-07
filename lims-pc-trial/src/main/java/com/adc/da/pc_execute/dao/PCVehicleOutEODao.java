package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCVehicleOutEO;

/**
 * PC整车出门申请单
 * @author Administrator
 * @date 2019年11月26日
 */
public interface PCVehicleOutEODao extends BaseDao<PCVehicleOutEO> {
	/**
	 * 根据整车出门申请单id,查询流程实例id
	 * @Title: selectActProcIdById
	 * @param id
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public String selectActProcIdById(String id);
}