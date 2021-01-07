package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCQCDDSupEO;

public interface PCQCDDSupEODao extends BaseDao<PCQCDDSupEO> {
	
	/**
	 * 根据qcdd主键删除供应商数据
	 * @Title: deleteByQcddId
	 * @param qcddId
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月4日
	 */
    public void deleteByQcddId(String qcddId);
}