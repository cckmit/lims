package com.adc.da.materiel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.materiel.dao.BorrowRecordEODao;
import com.adc.da.materiel.entity.BorrowRecordEO;


/**
 *
 * <br>
 * <b>功能：</b>RES_BORROW_RECORD BorrowRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("borrowRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BorrowRecordEOService extends BaseService<BorrowRecordEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecordEOService.class);

    @Autowired
    private BorrowRecordEODao dao;

    public BorrowRecordEODao getDao() {
        return dao;
    }
    //根据当前用户id以及物料id获取用户应还的物料数量
    public Double selectReturnedNums(String materielId,String userId){
        return dao.selectReturnedNums(materielId,userId);
    }
    //根据用户id以及物料id查询用户借用数据
    public BorrowRecordEO getReturned(String materielId,String userId){
        return dao.selectReturned(materielId,userId);
    }
}
