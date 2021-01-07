package com.adc.da.pc_budget_reimbursement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_budget_reimbursement.dao.PcTrialProductEODao;
import com.adc.da.pc_budget_reimbursement.entity.PcTrialProductEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PRODUCT PcTrialProductEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcTrialProductEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcTrialProductEOService extends BaseService<PcTrialProductEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcTrialProductEOService.class);

    @Autowired
    private PcTrialProductEODao dao;

    public PcTrialProductEODao getDao() {
        return dao;
    }

    public void deleteByRid(String id) {
        this.getDao().deleteByRid(id);
    }

    public List<PcTrialProductEO> selectByRid(String id) {
        return this.getDao().selectByRid(id);
    }
}
