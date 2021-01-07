package com.adc.da.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.project.dao.ItemsDetailsEODao;
import com.adc.da.project.entity.ItemsDetailsEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SUP_ITEMS_DETAILS ItemsDetailsEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("itemsDetailsEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ItemsDetailsEOService extends BaseService<ItemsDetailsEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(ItemsDetailsEOService.class);

    @Autowired
    private ItemsDetailsEODao dao;

    public ItemsDetailsEODao getDao() {
        return dao;
    }

    /**
     * 根据委托id查询
     * @param trialId
     * @return
     */
    public List<ItemsDetailsEO> selectByTrialId(String trialId){
        return  dao.selectByTrialId(trialId);
    }

    /**
     * 根据委托id删除
     * @param trialId
     * @return
     */
    public int deleteByTrialId(String trialId){
        return  dao.deleteByTrialId(trialId);
    }

}
