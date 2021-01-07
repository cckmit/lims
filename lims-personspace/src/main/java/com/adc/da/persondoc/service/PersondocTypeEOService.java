package com.adc.da.persondoc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.persondoc.dao.PersondocTypeEODao;
import com.adc.da.persondoc.entity.PersondocTypeEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>TP_PERSONDOC_TYPE PersondocTypeEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("persondocTypeEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersondocTypeEOService extends BaseService<PersondocTypeEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersondocTypeEOService.class);

    @Autowired
    private PersondocTypeEODao dao;

    public PersondocTypeEODao getDao() {
        return dao;
    }

    public List<PersondocTypeEO> getAllTypes(String id){
        return dao.getsontype(id);
    }

    /**
     * 判断节点下是否有子类型
     * @param id
     * @return
     */
    public int querySonById(String id){
        return dao.querySonById(id);
    }

    /**
     * 判断code是否已存在
     * @param code
     * @return
     */
    public int queryCountByCode(String code){
        return dao.queryCountByCode(code);
    }

}
