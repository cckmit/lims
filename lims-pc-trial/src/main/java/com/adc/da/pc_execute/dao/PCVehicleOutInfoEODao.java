package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCVehicleOutInfoEO;

/**
 * PC整车出门申请单详情
 * @author Administrator
 * @date 2019年11月26日
 */
public interface PCVehicleOutInfoEODao extends BaseDao<PCVehicleOutInfoEO> {
	/**
	 * 根据整车出门单id 删除详情数据
	 * @Title: deleteByVehicleOutId
	 * @param vehicleOutId
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
	public void deleteByVehicleOutId(String vehicleOutId);
}