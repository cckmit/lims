package com.adc.da.pc_announcement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_announcement.dao.AnnPlanAttachEODao;
import com.adc.da.pc_announcement.entity.AnnPlanAttachEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_ATTACH AnnPlanAttachEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-30 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("annPlanAttachEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnnPlanAttachEOService extends BaseService<AnnPlanAttachEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanAttachEOService.class);

    @Autowired
    private AnnPlanAttachEODao dao;

    public AnnPlanAttachEODao getDao() {
        return dao;
    }

    /**
     * 根据计划ID获取相关的所有委托单
     */
    public List<AnnPlanAttachEO> selectByPlId(String annPlId){
        return dao.selectByPlId(annPlId);
    }
}
