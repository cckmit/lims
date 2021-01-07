package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCMaterialsOutInfoEO;

public interface PCMaterialsOutInfoEODao extends BaseDao<PCMaterialsOutInfoEO> {
	/**
	 * 根据物资出门申请单id删除信息
	 * @Title: deleteByMaterialsOutId
	 * @param materialsOutId
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public void deleteByMaterialsOutId(String materialsOutId);
}