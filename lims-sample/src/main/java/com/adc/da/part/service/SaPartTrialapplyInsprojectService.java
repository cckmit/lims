package com.adc.da.part.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.part.dao.SaPartTrialapplyInsprojectDAO;
import com.adc.da.part.entity.SaPartTrialapplyInsprojectEO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/29 16:02
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaPartTrialapplyInsprojectService extends BaseService<SaPartTrialapplyInsprojectEO, String> {

    @Resource
    private SaPartTrialapplyInsprojectDAO saPartTrialapplyInsprojectDAO;

    @Override
    public SaPartTrialapplyInsprojectDAO getDao() {
        return saPartTrialapplyInsprojectDAO;
    }

    /**
     * 通过零部件ID删除关联关系
     * @param id
     */
    public void deteleByPartId(String id) {
        saPartTrialapplyInsprojectDAO.deteleByPartId(id);
    }
}
