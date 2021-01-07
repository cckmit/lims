package com.adc.da.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.project.dao.ProjectEODao;
import com.adc.da.project.entity.ProjectEO;
import com.adc.da.project.page.ProjectEOPage;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 基础数据模块--项目管理
 * @author Administrator
 * @date 2019年7月8日
 */
@Service("proEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ProjectEOService extends BaseService<ProjectEO, String>{
	@Autowired
	private ProjectEODao projectEODao;

	public ProjectEODao getDao() {
		return projectEODao;
	}
	
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    /**
	     *获取所有记录
	 * @param proVo 实体
	 * @param searchPhrase  查询字段
	 * @return: List<ProjectEO>
	 * @author： ljy  
	 * @date： 2019年7月10日
	  */
	public List<ProjectEO> page(ProjectEOPage page, String searchPhrase){
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
		//--------------单条件查询-------------//
		
		
		//查询
        return projectEODao.queryByPage(page);
	}

	/**
	 * 保存 项目信息
	* @Title：save
	* @param pEo
	* @return
	* @throws Exception
	* @return: ProjectEO
	* @author： ljy  
	* @date： 2019年7月11日
	 */
	public ProjectEO save(ProjectEO pEo) throws Exception {
		//设置UUID
		pEo.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		pEo.setCreateTime(date);
		pEo.setCreateBy(UserUtils.getUser());
		pEo.setUpdateBy(UserUtils.getUser());
		pEo.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		pEo.setDelFlag(0);
		//父级节点默认parentId  为 -1 
		//去除树形
		//保存数据
		projectEODao.insert(pEo);
		return pEo;
	}
	/**
	 * 编辑项目信息
	* @Title：edit
	* @param projectEO
	* @return
	* @throws Exception
	* @return: ProjectEO
	* @author： ljy  
	* @date： 2019年7月11日
	 */
	public ProjectEO edit(ProjectEO projectEO) throws Exception {
		//设置更新信息
		String updateTime = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		projectEO.setUpdateBy(UserUtils.getUser());
		projectEO.setUpdateTime(updateTime);
		//父级节点默认parentId  为 -1 
		//更新
		projectEODao.updateByPrimaryKeySelective(projectEO);
		return projectEO;
	}
	
	/**
	 * 查询当前项目是否有子节点
	* @Title：selectChildsById
	* @param id
	* @return
	* @return: List<ProjectEO>
	* @author： ljy  
	* @date： 2019年7月11日
	 */
	public List<ProjectEO> selectChildsById(String id) {
		return projectEODao.selectChildsById(id);
	}
    
    /**
     * 项目列表查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<List<ProjectSearchVO>>
    * @author： ljy  
    * @date： 2019年8月20日
     */
	public List<ProjectEO> findByList(){
		return projectEODao.findAll();
	}
	
	
	/**
	 * 校验编码唯一性
	 * @Title: checkNo
	 * @param id
	 * @param num
	 * @return
	 * @return boolean
	 * @author: ljy
	 * @date: 2019年12月11日
	 */
	public boolean checkNo(String id, String num) {
		//获取所有编码集合
		List<String> numList = projectEODao.checkNo(id);
		return numList.contains(num);
	}

	
}
