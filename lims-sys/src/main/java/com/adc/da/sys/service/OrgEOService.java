package com.adc.da.sys.service;

import java.util.HashSet;
import java.util.List;

import com.adc.da.sys.entity.UserEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.UUID;

/**
 * <br>
 * <b>功能：</b>TS_MENU OrgEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>
 * date 2018-08-16
 *
 * @author comments created by Lee Kwanho
 * @see OrgEODao
 */
@Service("orgEOService")
@Transactional(value = "transactionManager",
    readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OrgEOService extends BaseService<OrgEO, String> {
    
    /**
     * Dao层自动装配
     *
     * @see OrgEODao
     */
    @Autowired
    private OrgEODao dao;
    
    @Override
    public OrgEODao getDao() {
        return dao;
    }
    
    /**
     * 获取组织列表，用组织名称和id
     *
     * @param orgName 组织名称
     * @return 组织列表
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<OrgEO> listOrgEOByOrgName(String orgName,String orgCode,String orgType) {
        return dao.listOrgEOByOrgName(orgName,orgCode,orgType);
    }
    
    
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<OrgEO> getChildren(String orgId) {
        return dao.getChildren(orgId,null);
    }
    
    /**
     * 获取组织信息，用组织名和父组织id ，用于新增组织前的校验
     *
     * @param orgName  组织名
     * @param parentId 父id
     * @return 组织信息
     */
    @Transactional(readOnly = true)
    public OrgEO getOrgEOByNameAndPid(String orgName, String parentId) {
        return dao.getOrgEOByNameAndPid(orgName, parentId);
    }
    
    /**
     * 获取组织信息，用父id
     *
     * @param parentId 父组织id
     * @return 组织信息
     */
    @Transactional(readOnly = true)
    public List<OrgEO> getOrgEOByPid(String parentId) {
        return dao.getOrgEOByPid(parentId);
    }
    
    /**
     * 新增组织信息 ， 生成10位uuid ， 删除标识符
     *
     * @param orgEO 组织信息
     * @return 生成后的信息
     */
    public OrgEO save(OrgEO orgEO) {
        orgEO.setId(UUID.randomUUID10());
        orgEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        orgEO.setIsShow(0);
        dao.insert(orgEO);
        return orgEO;
    }
    
    /**
     * 组织机构详情
     *
     * @param id 组织id
     * @return 组织详情
     */
    @Transactional(readOnly = true)
    public OrgEO getOrgEOById(String id) {
        return dao.getOrgEOById(id);
    }

    /**
     * 组织机构
     *
     * @param OrgCode 组织code
     * @return 组织列表
     */
    @Transactional(readOnly = true)
    public List<OrgEO> getOrgEOByOrgCode(String OrgCode){
        return dao.getOrgEOByOrgCode(OrgCode);
    }
    
    /**
     * 删除组织机构， 逻辑删除
     *
     * @param id 组织id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        dao.deleteLogic(id);
        
    }
    
    /**
     * 查询组织所属用户信息
     *
     * @param id 组织id
     * @return 用户信息
     * @author Lee Kwanho 李坤澔
     * date 2018-08-31
     **/
    @Transactional(readOnly = true)
    public List<UserEO> listUserEOByOrgId(String id) {
        return dao.listUserEOByOrgId(id);
    }
    
    /**
     * 获取子机构id，主要用于删除组织与用户关联
     *
     * @param id
     * @return
     * @author Lee Kwanho 李坤澔
     * date 2019-02-13
     **/
    public List<String> getSubOrgId(String id) {
        return dao.getSubOrgId(id);
        
    }
    
    /**
     * 获取父机构信息
     * @Title: getByOrgId
     * @param id
     * @return
     * @return List<OrgEO>
     * @author: ljy
     * @date: 2020年1月9日
     */
    public String getByOrgId(String id) {
    	if(ConstantUtils.PV_ORG_ID.equals(id)) {
    		return "1";
    	}else if(ConstantUtils.CV_ORG_ID.equals(id)) {
    		return "0";
    	}else if(ConstantUtils.EV_ORG_ID.equals(id)) {
    		return "2";
    	}else{
    		List<String> list = dao.getByOrgId(id);
    		if(list.contains(ConstantUtils.PV_ORG_ID)) {
    			return "1";
    		}else if(list.contains(ConstantUtils.CV_ORG_ID)){
    			return "0";
    		}else {
    			return "2";
    		}
    	}
    }

    /**
     * 获取父级是东风柳汽的部门信息
     */
    public String getOrgName(){
        List<OrgEO> orgEOS = dao.selectChildByParent();
        HashSet<String> nameS = new HashSet<>();
        if (CollectionUtils.isNotEmpty(orgEOS)){
            for (OrgEO o : orgEOS){
                nameS.add(o.getName());
            }
        }
        return nameS.toString();
    }
}
