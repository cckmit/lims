package com.adc.da.pc_execute.service;

import java.util.ArrayList;
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
import com.adc.da.pc_execute.dao.PCBaseInitDataEODao;
import com.adc.da.pc_execute.entity.PCBaseInitDataEO;
import com.adc.da.pc_execute.page.PCBaseInitDataEOPage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;


/**
 * PV/CV试验执行模块--初始化数据
 * @author Administrator
 * @date 2019年10月18日
 */
@SuppressWarnings("all")
@Service("PCBaseInitDataEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCBaseInitDataEOService extends BaseService<PCBaseInitDataEO, String> {
	
	@Autowired
	private PCBaseInitDataEODao pcBaseInitDataDao;
	
	public PCBaseInitDataEODao getDao() {
		return pcBaseInitDataDao;
	}
	
	
	/**
    * PV/CV初始化数据分页查询
    * @Title: page
    * @param page
    * @param searchPhrase
    * @return
    * @return ResponseMessage<PageInfo<PCBaseInitDataVO>>
    * @author: ljy
    * @date: 2019年10月18日
    */
	public List<PCBaseInitDataEO> page(PCBaseInitDataEOPage page, String searchPhrase){
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
		return pcBaseInitDataDao.queryByPage(page);
	}
	
	
	/**
     * PV/CV初始化数据-新增
     * @Title: save
     * @param pcBaseInitDataVO
     * @return
     * @return ResponseMessage<PCBaseInitDataVO>
     * @author: ljy
     * @date: 2019年10月21日
     */
	public PCBaseInitDataEO save(PCBaseInitDataEO pcBaseInitDataEO) {
		//设置UUID
		pcBaseInitDataEO.setId(UUID.randomUUID());
		//删除标记 0 未删除;  1 删除
		pcBaseInitDataEO.setDelFlag("0");
		//保存
		pcBaseInitDataDao.insert(pcBaseInitDataEO);
		return pcBaseInitDataEO;
	}
	
	
	/**
     * PV/CV初始化数据-更新
     * @Title: save
     * @param pcBaseInitDataVO
     * @return
     * @return ResponseMessage<PCBaseInitDataVO>
     * @author: ljy
     * @date: 2019年10月21日
     */
	public PCBaseInitDataEO edit(PCBaseInitDataEO pcBaseInitDataEO) {
		//更新
		pcBaseInitDataDao.updateByPrimaryKeySelective(pcBaseInitDataEO);
		return pcBaseInitDataEO;
	}
	
}
