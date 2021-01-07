package com.adc.da.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.dao.QuestionUserEODao;
import com.adc.da.entity.QuestionUserEO;

/**
 * 问卷调查
 * @author Administrator
 *
 */
@Service("QuestionUserEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class QuestionUserEOService extends BaseService<QuestionUserEO, String> {
	@Autowired
	private QuestionUserEODao questionUserEODao;
	
	public QuestionUserEODao getDao() {
		return questionUserEODao;
	}
}
