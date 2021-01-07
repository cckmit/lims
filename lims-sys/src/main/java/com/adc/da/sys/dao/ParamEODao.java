package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.ParamEO;
import org.apache.ibatis.annotations.Param;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【TS_PARAM】 功能【系统参数】
 * @Date 15:16 2019/8/2
 **/
public interface ParamEODao extends BaseDao<ParamEO> {


    /*
     * @Author syxy_zhangyinghui
     * @Description 新增参数信息
     * @Date 14:13 2019/8/5
     * @Param
     * @return
     **/
    int insertParam(ParamEO paramEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 修改参数信息
     * @Date 14:15 2019/8/5
     * @Param
     * @return
     **/
    int updateParam(ParamEO paramEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除参数信息
     * @Date 14:15 2019/8/5
     * @Param
     * @return
     **/
    int  deleteParam(String id);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id查询参数信息
     * @Date 14:15 2019/8/5
     * @Param
     * @return
     **/
    ParamEO getParamById(String id);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据code查询参数信息
     * @Date 14:15 2019/8/5
     * @Param
     * @return
     **/
    ParamEO getParamByCode(String code);

    /**
     * 验证参数编码数据库是否存在
     * @param code
     * @param id
     */
    Integer checkNum(@Param("code") String code, @Param("id") String id);

    /**
     * 根据id修改参数值
     * @param id
     */
    void updateById(@Param("id") String id,@Param("paramValue") String paramValue);
    /**
     * 根据名称查找对应系统参数
     * @param name
     */
    ParamEO getParamByName(@Param("name")String name);


    int updatePreById(ParamEO paramEO);
}
