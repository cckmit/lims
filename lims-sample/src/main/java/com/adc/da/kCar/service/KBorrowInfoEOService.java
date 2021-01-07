package com.adc.da.kCar.service;

import com.adc.da.base.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.kCar.dao.KBorrowInfoEODao;
import com.adc.da.kCar.entity.KBorrowInfoEO;


/**
 *
 * <br>
 * <b>功能：</b>K_BORROW_INFO KBorrowInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-04-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("kBorrowInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class KBorrowInfoEOService extends BaseService<KBorrowInfoEO, String> {

    @Autowired
    private KBorrowInfoEODao kBorrowInfoEODao;

    public KBorrowInfoEODao getDao() {
        return kBorrowInfoEODao;
    }
}
