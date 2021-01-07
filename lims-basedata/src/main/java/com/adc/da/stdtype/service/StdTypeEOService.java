package com.adc.da.stdtype.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.standard.dao.StandardEODao;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.stdtype.dao.StdTypeEODao;
import com.adc.da.stdtype.entity.StdTypeEO;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 基础数据管理模块--检验标准类型
 * @author ljy
 * @date 2019年7月12日
 */
@Service("stdTypeEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StdTypeEOService extends BaseService<StdTypeEO, String>{
	
	@Autowired
	private StdTypeEODao stdTypeEODao;
	
	@Autowired
	private StandardEODao standardEODao;
	
	public StdTypeEODao getDao() {
		return stdTypeEODao;
	}
	/**
	 * 获取所有记录
	* @Title：findAll
	* @return
	* @return: List<StdTypeEO>
	* @author： ljy  
	* @date： 2019年7月12日
	 */
	public List<StdTypeEO> findAll(){
		return stdTypeEODao.findAll();
	}
	
	/**
	 * 保存检验标准信息
	* @Title：save
	* @param stdTypeEO
	* @return
	* @throws Exception
	* @return: StdTypeEO
	* @author： ljy  
	* @date： 2019年7月12日
	 */
	public StdTypeEO save(StdTypeEO stdTypeEO) throws Exception {
		//设置UUID
		stdTypeEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		stdTypeEO.setDelFlag(0); 
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		stdTypeEO.setCreateBy(UserUtils.getUser());
		stdTypeEO.setCreateTime(date);
		stdTypeEO.setUpdateBy(UserUtils.getUser());
		stdTypeEO.setUpdateTime(date);
		//父级节点默认parentId  为 -1 
		if(StringUtils.isEmpty(stdTypeEO.getStdTypeParentId())) {
			stdTypeEO.setStdTypeParentId(ConstantUtils.PARENT_ID);
		}
		//保存
		stdTypeEODao.insert(stdTypeEO);
		return stdTypeEO;
	}
	
	/**
	 * 编辑信息
	* @Title：edit
	* @param stdTypeEO
	* @return
	* @throws Exception
	* @return: StdTypeEO
	* @author： ljy  
	* @date： 2019年7月12日
	 */
	public StdTypeEO edit(StdTypeEO stdTypeEO) throws Exception {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		stdTypeEO.setUpdateBy(UserUtils.getUser());
		stdTypeEO.setUpdateTime(date);
		//父级节点默认parentId  为 -1 
		if(StringUtils.isEmpty(stdTypeEO.getStdTypeParentId())) {
			stdTypeEO.setStdTypeParentId(ConstantUtils.PARENT_ID);
		}
		//保存
		stdTypeEODao.updateByPrimaryKeySelective(stdTypeEO);
		return stdTypeEO;
	}
	
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param id
	* @return
	* @return: List<StdTypeEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<StdTypeEO> selectChildsById(String id){
		return stdTypeEODao.selectChildsById(id);
	}
	
	/**
	 * 根据类型id查询 类型下是否有数据
	 * @Title: selectStdById
	 * @param id
	 * @return
	 * @return List<StandardEO>
	 * @author: ljy
	 * @date: 2019年12月31日
	 */
	public List<StandardEO> selectStdById(String id){
		return standardEODao.selectStdByTypeId(id);
	}
}
