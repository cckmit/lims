package com.adc.da.pc_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCBaseInitDataEO;

public interface PCBaseInitDataEODao extends BaseDao<PCBaseInitDataEO> {
	/**
	 * 根据类型code查询
	 * @Title: findListByCodeType
	 * @param codeType
	 * @return
	 * @return List<PCBaseInitDataEO>
	 * @author: ljy
	 * @date: 2019年10月23日
	 */
    public List<PCBaseInitDataEO> findListByCodeType(String codeType);
}