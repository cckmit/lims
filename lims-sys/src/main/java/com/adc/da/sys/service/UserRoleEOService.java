package com.adc.da.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.UserRoleEODao;
import com.adc.da.sys.entity.UserRoleEO;

/**
 * <br>
 * <b>功能：</b>TR_USER_ROLE UserRoleEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-07 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>
 *
 * 疑似废弃代码
 * date 2018-08-17
 *
 * @author comments created by Lee Kwanho
 * @see UserRoleEODao
 * @see com.adc.da.sys.controller.UserEOController
 * @deprecated
 */
@Deprecated
@Service("userRoleEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class UserRoleEOService extends BaseService<UserRoleEO, Integer> {

    /**
     *
     * @see UserRoleEODao
     */
    @Autowired
    private UserRoleEODao dao;

    public UserRoleEODao getDao() {
        return dao;
    }

}
