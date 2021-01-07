package com.adc.da.seal.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.seal.entity.BmSealEO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * <br>
 * <b>功能：</b>BM_SEAL BmSealEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BmSealEODao extends BaseDao<BmSealEO> {

    public int updateSealStatus(@Param("id") String id, @Param("status") String status);

    int countSealCode(String sealCode);

    BmSealEO findSealCode(String sealCode);

}
