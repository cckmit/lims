package com.adc.da.trial_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.entity.TrialDataDetailEO;
import com.adc.da.trial_execute.page.TrialDataDetailEOPage;


public interface TrialDataDetailEODao extends BaseDao<TrialDataDetailEO> {
	/**
	 * 根据试验数据id进行删除
	* @Title：deleteByTrialdataId
	* @param trialdataId
	* @return: void
	* @author： ljy  
	* @date： 2019年9月16日
	 */
    public void deleteByTrialdataId(String trialdataId);
    
    /**
     * 查询所有
    * @Title：findByList
    * @param page
    * @return
    * @return: List<TrialDataDetailEO>
    * @author： ljy  
    * @date： 2019年9月16日
     */
    public List<TrialDataDetailEO> findByList(TrialDataDetailEOPage page);
}