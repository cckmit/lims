package com.adc.da.sys.dao;

import java.util.List;
import java.util.Map;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserEO;
import org.apache.ibatis.annotations.Param;

/**
 * 组织机构Dao层, 具体sql在xml中
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface OrgEODao extends BaseDao<OrgEO> {
    /*
     * @Author syxy_zhangyinghui
     * @Description 查询组织列表，用组织名称查询
     * @Date 9:26 2019/7/31      * @return 组织名称

     * @Param [name]组织名称
     * @return java.util.List<com.adc.da.sys.entity.OrgEO>
     **/
    List<OrgEO> listOrgEOByOrgName(@Param("orgName") String name,@Param("orgCode") String orgCode,@Param("orgType") String orgType);

    /**
     * 获取组织信息，用父id和组织名称，用于新增校验
     *
     * @param name     名称
     * @param parentId 父id
     * @return 组织EO
     */
    OrgEO getOrgEOByNameAndPid(String name, String parentId);

    /**
     * 组织列表，用父id
     *
     * @param parentId 父id，组织id
     * @return 组织信息
     */
    List<OrgEO> getOrgEOByPid(String parentId);

    /**
     * 获取组织信息，用id
     *
     * @param id 组织id
     * @return 组织信息
     */
    OrgEO getOrgEOById(String id);

    /**
     * 查询组织，用组织代码查询
     *
     * @param OrgCode 组织代码
     * @return 组织列表
     */
    List<OrgEO> getOrgEOByOrgCode(String OrgCode);

    /**
     * 逻辑删除组织信息
     *
     * @param id 组织id
     */
    void deleteLogic(String id);

    /**
     * 查询组织所属用户
     *
     * @param orgId 组织id
     * @return 组织列表
     * @author Lee Kwanho 李坤澔
     * date 2018-08-31
     **/
    List<UserEO> listUserEOByOrgId(@Param("orgId") String orgId);

    /**
     * 获取子组织机构
     *
     * @param parentId
     * @return
     * @author Lee Kwanho 李坤澔
     * date 2019-02-13
     **/
    List<String> getSubOrgId(@Param("orgId") String parentId);

    /*
     * @Author syxy_zhangyinghui
     * @Description //<!--根据组织机构查询组织机构信息-->
     * @Date 18:04 2019/7/23
     * @Param
     * @return
     **/
    List<OrgEO> getOrgByOrgName(@Param("orgName") String name);

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据一个或者两个组织机构名称查询组织机构
     *                 如果组织机构传入的group有值，那么筛选，如果是section 那么group 必须传
     *                  如果是department传值那么section必须穿
     * @Date 17:56 2019/7/23
     * @Param
     * @return
     **/
    List<OrgEO> getOrgByTwoOrgName(Map<String, Object> params);

    List<OrgEO> getOrgByOrgNameAndOrgId(@Param("orgId") String id,@Param("orgName") String name);

    String findOrgByUsId(String usId);

    /**
     * 递归获取所有下级部门
     * @param id 组织机构id
     * @param self 得到的列表中是否包含自身 n-不包含 默认包含
     * @return
     */
    public List<OrgEO> getChildren(@Param("id")String id, @Param("self")String self);
    
    /**
     * 获取该用户的部门信息
     * @param userId
     * @return
     */
    public OrgEO getByUserId(String userId);
    
    /**
     * 获取当前部门父级信息
     * @Title: getByOrgId
     * @param id
     * @return
     * @return List<OrgEO>
     * @author: ljy
     * @date: 2020年1月9日
     */
    public List<String> getByOrgId(@Param("id") String id);

    /**
     * 获取父级是东风柳汽的部门信息
     */
    public List<OrgEO> selectChildByParent();

}
