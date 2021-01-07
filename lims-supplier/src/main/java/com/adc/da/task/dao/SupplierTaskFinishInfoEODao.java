package com.adc.da.task.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.task.entity.SupplierTaskFinishInfoEO;
import com.adc.da.task.vo.SupplierTaskFinishInfoVO;

/**
 *
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_FINISH_INFO SupplierTaskFinishInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SupplierTaskFinishInfoEODao extends BaseDao<SupplierTaskFinishInfoEO> {

    SupplierTaskFinishInfoVO findById(String id);

}
