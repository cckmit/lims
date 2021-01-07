package com.adc.da.pc_execute.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.dao.PCTrialLineEODao;
import com.adc.da.pc_execute.entity.PCTrialLineEO;
import com.adc.da.pc_execute.vo.PCTrialLineSearchVO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;


@SuppressWarnings("all")
@Service("PCTrialLineEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCTrialLineEOService extends BaseService<PCTrialLineEO, String> {

	@Autowired
	private PCTrialLineEODao pcTrialLineEODao;
	
	public PCTrialLineEODao getDao() {
		return pcTrialLineEODao;
	}
	
	
	/**
	 *获取可靠性立项单中的 路线策划数据
	 * @Title: findByList
	 * @param initTaskId
	 * @return
	 * @return List<PCTrialLineEO>
	 * @author: ljy
	 * @date: 2019年10月21日
	 */
	public List<PCTrialLineEO> findByList(String initTaskId){
		return pcTrialLineEODao.findByList(initTaskId);
	}
	
	
	/**
	 * 保存PSQC/性能试验/可靠性立项单中的 路线策划数据
	 * @Title: save
	 * @param pcTrialLineSearchVO
	 * @return void
	 * @author: ljy
	 * @date: 2019年10月21日
	 */
	public void save(PCTrialLineSearchVO pcTrialLineSearchVO) {
		if(CollectionUtils.isNotEmpty(pcTrialLineSearchVO.getPCTrialLineList())) {
			//先删
			pcTrialLineEODao.deleteByInitTaskId(pcTrialLineSearchVO.getInitTaskId());
			pcTrialLineEODao.deleteByTrialTaskId(pcTrialLineSearchVO.getTrialTaskId());
			//根据code删除
			pcTrialLineEODao.deleteByTaskNumber(pcTrialLineSearchVO.getTaskNumber());
			//后增
			for(PCTrialLineEO eo : pcTrialLineSearchVO.getPCTrialLineList()) {
				//设置时间
				String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
				eo.setId(UUID.randomUUID());
				eo.setDelFlag("0");
				eo.setCreateBy(UserUtils.getUserId());
				eo.setCreateTime(date);
				eo.setUpdateBy(UserUtils.getUserId());
				eo.setUpdateTime(date);
				//设置可靠性立项单id
				eo.setInitTaskId(pcTrialLineSearchVO.getInitTaskId());
				//设置试验任务id
				eo.setTrialTaskId(pcTrialLineSearchVO.getTrialTaskId());
				//试验任务唯一编号
				eo.setTaskNumber(pcTrialLineSearchVO.getTaskNumber());
				pcTrialLineEODao.insert(eo);
			}
		}
	}
	
	/**
	 * 根据试验任务id,查询PSQC/性能试验的试验人员及住宿预算
	 * @Title: findListByTrialTaskId
	 * @param trialTaskId
	 * @return
	 * @return List<PCTrialLineEO>
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
	public List<PCTrialLineEO> findListByTrialTaskId(String trialTaskId){
		return pcTrialLineEODao.findListByTrialTaskId(trialTaskId);
	}
	
	
	/**
	 * 根据试验任务唯一code,查询PSQC/性能试验的试验人员及住宿预算
	 * @Title: findListByTrialTaskId
	 * @param trialTaskId
	 * @return
	 * @return List<PCTrialLineEO>
	 * @author: ljy
	 * @date: 2020年2月28日
	 */
	public List<PCTrialLineEO> findListByTrialTaskNumber(String taskNumber){
		return pcTrialLineEODao.findListByTrialTaskNumber(taskNumber);
	}
	
}
