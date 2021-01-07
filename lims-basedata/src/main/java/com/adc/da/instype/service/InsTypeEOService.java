package com.adc.da.instype.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.insproject.dao.InsProjectEODao;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.instype.dao.InsTypeEODao;
import com.adc.da.instype.entity.InsTypeEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 基础数据管理模块--检验项目类型
 * @author Administrator
 * @date 2019年7月15日
 */
@Service("insTypeEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InsTypeEOService extends BaseService<InsTypeEO, String>{

	@Autowired
	private InsTypeEODao insTypeEODao;
	
	@Autowired
	private InsProjectEODao insProjectEODao;
	
	public InsTypeEODao getDao() {
		return insTypeEODao;
	}

	/**
	 * 查询所有记录
	* @Title：findALL
	* @return
	* @return: List<InsTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<InsTypeEO> findALL(){
		return insTypeEODao.findAll();
	}
	
	/**
	 * 保存
	* @Title：save
	* @param insTypeEO
	* @return
	* @throws Exception
	* @return: InsTypeEO
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public InsTypeEO save(InsTypeEO insTypeEO) throws Exception {
		//设置UUID
		insTypeEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		insTypeEO.setDelFlag(0); 
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		insTypeEO.setCreateBy(UserUtils.getUser());
		insTypeEO.setCreateTime(date);
		insTypeEO.setUpdateBy(UserUtils.getUser());
		insTypeEO.setUpdateTime(date);
		//父级节点默认parentId  为 -1 
		if(StringUtils.isEmpty(insTypeEO.getInsTypeParentId())) {
			insTypeEO.setInsTypeParentId(ConstantUtils.PARENT_ID);
		}
		//保存
		insTypeEODao.insert(insTypeEO);
		return insTypeEO;
	}
	
	/**
	 * 编辑
	* @Title：edit
	* @param insTypeEO
	* @return
	* @throws Exception
	* @return: InsTypeEO
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public InsTypeEO edit(InsTypeEO insTypeEO) throws Exception {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		insTypeEO.setUpdateBy(UserUtils.getUser());
		insTypeEO.setUpdateTime(date);
		//父级节点默认parentId  为 -1 
		if(StringUtils.isEmpty(insTypeEO.getInsTypeParentId())) {
			insTypeEO.setInsTypeParentId(ConstantUtils.PARENT_ID);
		}
		//保存
		insTypeEODao.updateByPrimaryKeySelective(insTypeEO);
		return insTypeEO;
	}
	
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param id
	* @return: List<InsTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<InsTypeEO> selectChildsById(String id){
		return insTypeEODao.selectChildsById(id);
	}
	
	/**
	 * 根据类型id查询 类型下是否有数据
	 * @Title: selectInsProById
	 * @param id
	 * @return
	 * @return List<InsProjectEO>
	 * @author: ljy
	 * @date: 2019年12月31日
	 */
	public List<InsProjectEO> selectInsProById(String id){
		return insProjectEODao.selectInsProByTypeId(id);
	}
	
}
