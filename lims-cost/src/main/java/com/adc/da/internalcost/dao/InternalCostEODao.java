package com.adc.da.internalcost.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.internalcost.entity.InternalCostEO;

/**
 * 费用管理模块--内部费用库
 * @author ljy
 * @date 2019年8月5日
 */
public interface InternalCostEODao extends BaseDao<InternalCostEO> {
	public List<InternalCostEO> findAll();
}
