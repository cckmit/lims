package com.adc.da.kCar.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.kCar.entity.KBorrowInfoEO;
/**
 *
 * <br>
 * <b>功能：</b>K_BORROW_INFO KBorrowInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-04-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface KBorrowInfoEODao extends BaseDao<KBorrowInfoEO> {
	/**
	 * 根据底盘号删除整车信息
	 * @Title: deleteByChassisNumber
	 * @param chassisNumber
	 * @return void
	 * @author: ljy
	 * @date: 2020年4月21日
	 */
	public void deleteByChassisNumber(String chassisNumber);
}
