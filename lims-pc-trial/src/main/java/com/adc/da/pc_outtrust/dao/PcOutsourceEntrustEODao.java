package com.adc.da.pc_outtrust.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_outtrust.entity.PcOutsourceEntrustEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>PC_OUTSOURCE_ENTRUST PcOutsourceEntrustEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcOutsourceEntrustEODao extends BaseDao<PcOutsourceEntrustEO> {

    /**
     * 变更流程状态
     *
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") String id, @Param("status") String status);

    /**
     * 获取basebusId
     *
     * @param id
     * @return
     */
    String findActProcId(String id);

    /**
     * pc委外立项＆pc可靠性立项获取编号集合
     */
    List<String> queryCodeList();

}
