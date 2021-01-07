package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCQCDDInfoEO;

public interface PCQCDDInfoEODao extends BaseDao<PCQCDDInfoEO> {
	/**
	 * 根据QCDD单id,查询流程实例id
	 * @Title: selectActProcIdById
	 * @param id
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2020年6月4日
	 */
    public String selectActProcIdById(String id);
}