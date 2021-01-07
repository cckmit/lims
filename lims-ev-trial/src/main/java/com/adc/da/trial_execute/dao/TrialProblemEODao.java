package com.adc.da.trial_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.entity.TrialProblemEO;
import com.adc.da.trial_execute.page.TrialProblemEOPage;

public interface TrialProblemEODao extends BaseDao<TrialProblemEO> {
	/**
	 * 试验问题记录 查询(不分页)
	* @Title：findByList
	* @param page
	* @return
	* @return: List<TrialProblemEO>
	* @author： ljy  
	* @date： 2019年9月18日
	 */
	public List<TrialProblemEO> findByList(TrialProblemEOPage page);

	void updatePicture(TrialProblemEO trialProblemEO);
}