package com.adc.da.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.dao.QuestionEODao;
import com.adc.da.entity.QuestionEO;


/**
 * 问卷调查
 * @author Administrator
 *
 */
@Service("QuestionEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class QuestionEOService extends BaseService<QuestionEO, String> {
	
	@Autowired
	private QuestionEODao questionEODao;
	
	public QuestionEODao getDao() {
		return questionEODao;
	}
}
