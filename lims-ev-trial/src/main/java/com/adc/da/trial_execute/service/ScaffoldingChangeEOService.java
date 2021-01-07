package com.adc.da.trial_execute.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.trial_execute.dao.ScaffoldingChangeEODao;
import com.adc.da.trial_execute.dao.ScaffoldingUserEODao;
import com.adc.da.trial_execute.entity.ScaffoldingChangeEO;
import com.adc.da.trial_execute.entity.ScaffoldingUserEO;
import com.adc.da.trial_execute.page.ScaffoldingChangeEOPage;
import com.adc.da.trial_task.dao.TrialTaskEODao;
import com.adc.da.trial_task.dao.TrialtaskSampleEODao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;



/**
 * 发动机台架变更
 * @author Administrator
 * @date 2019年9月25日
 */
@Service("scaffoldingChangeEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ScaffoldingChangeEOService extends BaseService<ScaffoldingChangeEO, String> {
	
	@Autowired
	private ScaffoldingChangeEODao scaffoldingChangeEODao;
	
	public ScaffoldingChangeEODao getDao() {
		return scaffoldingChangeEODao;
	}
	
	@Autowired
	private TrialtaskSampleEODao trialtaskSampleEODao;
	
    @Autowired
    private OrgEOService orgEOService;
    
    @Autowired
    private ScaffoldingUserEODao scaffoldingUserEODao;
    

    @Autowired
    private TrialTaskEODao trialTaskEODao;
    
	
	/**
	 * 台架变更记录分页查询
	* @Title：page
	* @param page
	* @return
	* @return: List<ScaffoldingChangeEO>
	* @author： ljy  
	* @date： 2019年9月25日
	 */
	public List<ScaffoldingChangeEO> page(ScaffoldingChangeEOPage page){
		//分页查询
		return scaffoldingChangeEODao.queryByPage(page);
	}
	
	/**
	 * 台架变更记录-变更详情
	* @Title：getDetailById
	* @param id
	* @return
	* @return: ScaffoldingChangeEO
	* @author： ljy  
	* @date： 2019年9月25日
	 */
	public ScaffoldingChangeEO getDetailById(String id) {
	    // 变更前关联用户
	    List<ScaffoldingUserEO> scaffoldingBeforeList = new ArrayList<>();
	    //变更后关联用户
	    List<ScaffoldingUserEO> scaffoldingAfterList = new ArrayList<>();
	    //查询
		ScaffoldingChangeEO scaffoldingChangeEO = scaffoldingChangeEODao.selectByPrimaryKey(id);
		if(StringUtils.isNotEmpty(scaffoldingChangeEO)) {
			//循环遍历判断用户为变更前还是变更后
			for (int i = 0; i < scaffoldingChangeEO.getScaffoldingList().size(); i++) {
				//变更前
				if(ConstantUtils.BEFORE.equalsIgnoreCase(
						scaffoldingChangeEO.getScaffoldingList().get(i).getTypeFlag())) {
					scaffoldingBeforeList.add(scaffoldingChangeEO.getScaffoldingList().get(i));
				}else if(ConstantUtils.AFTER.equalsIgnoreCase(
						scaffoldingChangeEO.getScaffoldingList().get(i).getTypeFlag())) {
				//变更后
					scaffoldingAfterList.add(scaffoldingChangeEO.getScaffoldingList().get(i));
				}
			}
		}
		//将数据放入对应属性字段
		scaffoldingChangeEO.setScaffoldingBeforeList(scaffoldingBeforeList);
		scaffoldingChangeEO.setScaffoldingAfterList(scaffoldingAfterList);
		//返回
		return scaffoldingChangeEO;
	}
	
	/**
	 * 初级化查询变更信息
	* @Title：getInitDetail
	* @param trialTaskId
	* @return
	* @return: ScaffoldingChangeEO
	* @author： ljy  
	* @date： 2019年9月25日
	 */
	public ScaffoldingChangeEO getInitDetail(String trialTaskId) {
		//设置返回参数
		ScaffoldingChangeEO eo = null;
		//设置page参数
    	ScaffoldingChangeEOPage page = new ScaffoldingChangeEOPage();
    	//试验任务id
    	page.setTrialtaskId(trialTaskId);
    	//获取最新的一条
    	page.setPage(1);
    	page.setPageSize(1);
    	//查询
		List<ScaffoldingChangeEO> rows =  scaffoldingChangeEODao.queryByPage(page);
		if(CollectionUtils.isNotEmpty(rows)) {
			eo = getDetailById(rows.get(0).getId());
		}
		//如果改eo为null,则证明为第一次变更
		if(StringUtils.isEmpty(eo)) {
			eo = getInitDetailByTrialTask(trialTaskId);
		}
		//返回
		return eo;
	}
	
	
	/**
	 * 根据试验任务id  获取台架初始化信息
	* @Title：getInitDetailByTrialTask
	* @param trialTaskId
	* @return
	* @return: ScaffoldingChangeEO
	* @author： ljy  
	* @date： 2019年9月25日
	 */
	public ScaffoldingChangeEO getInitDetailByTrialTask(String trialTaskId) {
		//定义返回值
		ScaffoldingChangeEO eo = new ScaffoldingChangeEO();
		//查询
		List<TrialtaskSampleEO> list = trialtaskSampleEODao
				.findTrialtaskSampleByTrialtaskId(trialTaskId);
		if(CollectionUtils.isNotEmpty(list)) {
			//台架id
			String scaffoldingId = list.get(0).getScaffoldingId();
			//试验任务id
			eo.setTrialtaskId(trialTaskId);
			//台架id
			eo.setScaffoldingIdAfter(scaffoldingId);
			//台架名称
			eo.setScaffoldingNameAfter(list.get(0).getScaffoldingName());
			//台架下技术人员
			eo.setScaffoldingAfterList(getUserByScaffoldingId(scaffoldingId));
		}
		//返回
		return eo;
	}
	
	
	/**
	 * 根据台架id 获取台架下的技术人员
	* @Title：getUserByScaffoldingId
	* @param scaffoldingId
	* @return
	* @return: List<ScaffoldingUserEO>
	* @author： ljy  
	* @date： 2019年9月25日
	 */
	public List<ScaffoldingUserEO> getUserByScaffoldingId(String scaffoldingId) {
		//变更关联用户
	    List<ScaffoldingUserEO> scaffoldingAfterList = new ArrayList<>();
		if(StringUtils.isNotEmpty(scaffoldingId)) {
			List<UserEO> users = orgEOService.listUserEOByOrgId(scaffoldingId);
            for (int j = 0; j < users.size(); j++) {
                //如果该用户拥有试验员角色,则返回
            	for (int i = 0; i < users.get(j).getRoleEOList().size(); i++) {
            		 if (ConstantUtils.EV_RESTER_ROLEID.equals(
            				 users.get(j).getRoleEOList().get(i).getId())) {
                     	//封装用户信息
                     	ScaffoldingUserEO user = new ScaffoldingUserEO();
                     	user.setUserId(users.get(j).getUsid());
                     	user.setUserName(users.get(j).getUsname());
                     	//填充
                     	scaffoldingAfterList.add(user);
                     }
				}
            }
		}
		//返回
		return scaffoldingAfterList;
	}
	
	/**
	 * 台架变更-新增
	* @Title：save
	* @param scaffoldingChangeEO
	* @return
	* @return: ScaffoldingChangeEO
	* @author： ljy  
	* @date： 2019年9月26日
	 */
	public ScaffoldingChangeEO save(ScaffoldingChangeEO scaffoldingChangeEO) {
		//设置UUID
		scaffoldingChangeEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		scaffoldingChangeEO.setDelFlag("0"); 
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		scaffoldingChangeEO.setCreateBy(UserUtils.getUserId());
		scaffoldingChangeEO.setCreateTime(date);
		//保存
		scaffoldingChangeEODao.insert(scaffoldingChangeEO);
		//保存关联关系
		//变更前人员
		if(CollectionUtils.isNotEmpty(scaffoldingChangeEO.getScaffoldingBeforeList())) {
			saveScaffoldingUser(scaffoldingChangeEO.getScaffoldingBeforeList(),
					ConstantUtils.BEFORE, scaffoldingChangeEO.getId());
		}
		//变更后人员
		if(CollectionUtils.isNotEmpty(scaffoldingChangeEO.getScaffoldingAfterList())) {
			saveScaffoldingUser(scaffoldingChangeEO.getScaffoldingAfterList(),
					ConstantUtils.AFTER, scaffoldingChangeEO.getId());
			//将变更后的反向更新试验申请中的信息
			List<String> participantList = new ArrayList<>();
			if(CollectionUtils.isNotEmpty(scaffoldingChangeEO.getScaffoldingAfterList())) {
				for (int i = 0; i < scaffoldingChangeEO.getScaffoldingAfterList().size(); i++) {
					participantList.add(scaffoldingChangeEO.getScaffoldingAfterList().get(i).getUserId());
				}
	        	String personIds = StringUtils.join(participantList, ConstantUtils.COMMA);
	            TrialTaskEO eo = trialTaskEODao.selectByPrimaryKey(scaffoldingChangeEO.getTrialtaskId());
	            eo.setPersonIds(personIds);
	            trialTaskEODao.updateTrialTask(eo);
	        }
		}
		//返回
		return scaffoldingChangeEO;
	}
	
	
	/**
	 * 保存变更人员关联关系
	* @Title：saveScaffoldingUser
	* @param list
	* @param typeFlag
	* @param scaffoldingChangeId
	* @return: void
	* @author： ljy  
	* @date： 2019年9月26日
	 */
	public void saveScaffoldingUser(List<ScaffoldingUserEO> list, String typeFlag, 
			String scaffoldingChangeId) {
		//循环保存
		for(ScaffoldingUserEO eo : list) {
			//设置UUID
			eo.setId(UUID.randomUUID());
			//设置台架变更id
			eo.setScaffoldingChangeId(scaffoldingChangeId);
			//变更类型(前/后)
			eo.setTypeFlag(typeFlag);
			//保存
			scaffoldingUserEODao.insert(eo);
		}
	}
	
}
