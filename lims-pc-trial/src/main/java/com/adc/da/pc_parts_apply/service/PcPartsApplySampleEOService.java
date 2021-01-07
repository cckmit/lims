package com.adc.da.pc_parts_apply.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_parts_apply.dao.PcPartsApplySampleEODao;
import com.adc.da.pc_parts_apply.entity.PcPartsApplySampleEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>PC_PARTS_APPLY_SAMPLE PcPartsApplySampleEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcPartsApplySampleEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcPartsApplySampleEOService extends BaseService<PcPartsApplySampleEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcPartsApplySampleEOService.class);

    @Autowired
    private PcPartsApplySampleEODao dao;

    public PcPartsApplySampleEODao getDao() {
        return dao;
    }

    public List<PcPartsApplySampleEO> queryByApplyId(String applyId){
        return dao.queryByApplyId(applyId);
    }

}
