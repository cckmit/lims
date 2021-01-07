package com.adc.da.materiel.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.materiel.entity.BorrowRecordEO;
/**
 *
 * <br>
 * <b>功能：</b>RES_BORROW_RECORD BorrowRecordEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BorrowRecordEODao extends BaseDao<BorrowRecordEO> {
    //根据当前用户id以及物料id获取用户应还的物料数量
    Double selectReturnedNums(String materielId,String userId);
    //根据用户id以及物料id查询用户借用数据
    BorrowRecordEO selectReturned(String materielId,String userId);
}
