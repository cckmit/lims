package com.adc.da.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.supplier.entity.SupProjectEO;
import com.adc.da.supplier.page.SupTestProjectVOPage;


/**
 *
 * <br>
 * <b>功能：</b>SUP_PROJECT ProjectEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SupProjectEODao extends BaseDao<SupProjectEO> {


    /**
     * 批量删除
     * @param supId
     */
    public void batchDelete(String supId);

    /**
     * 供应商能力
     * @param supId
     * @return
     */
    public List<SupProjectEO> findListBySupId(String supId);
    /**
     * 获取供应商检验项目
     * @Title: selectSupProjectByPage
     * @return
     * @return List<SupTestProjectVO>
     * @author: ljy
     * @date: 2020年3月10日
     */
    public List<SupProjectEO> selectSupProjectByPage(SupTestProjectVOPage page);
    /**
     * 获取供应商检验项目总数
     * @Title: selectSupProjectByCount
     * @param page
     * @return
     * @return int
     * @author: ljy
     * @date: 2020年3月10日
     */
    public int selectSupProjectByCount(SupTestProjectVOPage page);
    
    /**
     * 批量加入
     * @Title: insertBatchSelective
     * @param supProjectList
     * @return void
     * @author: ljy
     * @date: 2020年3月25日
     */
    public void insertBatchSelective(@Param("supProjectList") List<SupProjectEO> supProjectList);
}
