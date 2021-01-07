package com.adc.da.pc_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.vo.PCTrialExecuteListVO;
import com.adc.da.pc_trust.entity.TrialTaskEO;


public interface PCTrialExecuteDao extends BaseDao<TrialTaskEO> {
	/**
	 * PC试验执行 点击"+"查询
	 * @Title: selectListById
	 * @param id
	 * @return
	 * @return List<PCTrialExecuteListVO>
	 * @author: ljy
	 * @date: 2019年10月31日
	 */
	public List<PCTrialExecuteListVO> selectListById(String id);
}
