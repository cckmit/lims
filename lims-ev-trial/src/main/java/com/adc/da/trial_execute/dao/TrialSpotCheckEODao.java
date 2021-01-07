package com.adc.da.trial_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_execute.entity.TrialSpotCheckEO;
import com.adc.da.trial_execute.page.TrialSpotCheckEOPage;
import com.adc.da.trial_execute.vo.AttachmentVO;

public interface TrialSpotCheckEODao extends BaseDao<TrialSpotCheckEO> {
	/**
	 *查询所有记录 不分页
	* @Title：findByList
	* @param
	* @return
	* @return: List<TrialSpotCheckEO>
	* @author： ljy  
	* @date： 2019年9月12日
	 */
	public List<TrialSpotCheckEO> findByList(TrialSpotCheckEOPage page);

	TrialSpotCheckEO selectByTrialSpotCheckEO(String id);

	List<AttachmentVO> selectAttachVO(List<String> fileIDList);
}