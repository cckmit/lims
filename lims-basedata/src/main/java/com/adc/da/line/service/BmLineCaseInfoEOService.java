package com.adc.da.line.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.line.dao.BmLineCaseInfoEODao;
import com.adc.da.line.entity.BmLineCaseInfoEO;


/**
 *
 * <br>
 * <b>功能：</b>BM_LINE_CASE_INFO BmLineCaseInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("bmLineCaseInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BmLineCaseInfoEOService extends BaseService<BmLineCaseInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BmLineCaseInfoEOService.class);

    @Autowired
    private BmLineCaseInfoEODao dao;

    public BmLineCaseInfoEODao getDao() {
        return dao;
    }

    public void deleteByCaseId(String id) {
        this.getDao().deleteByCaseId(id);
    }
}
