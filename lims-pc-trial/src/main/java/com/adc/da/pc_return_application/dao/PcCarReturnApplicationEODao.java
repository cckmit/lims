package com.adc.da.pc_return_application.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_return_application.entity.PcCarReturnApplicationEO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * <br>
 * <b>功能：</b>PC_CAR_RETURN_APPLICATION PcCarReturnApplicationEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcCarReturnApplicationEODao extends BaseDao<PcCarReturnApplicationEO> {

    void changeStatus(@Param("id") String id, @Param("status") String status);

}
