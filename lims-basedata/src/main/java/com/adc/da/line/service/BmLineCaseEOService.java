package com.adc.da.line.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.line.dao.BmLineCaseEODao;
import com.adc.da.line.entity.BmLineCaseEO;
import com.adc.da.line.entity.BmLineCaseInfoEO;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * <br>
 * <b>功能：</b>BM_LINE_CASE BmLineCaseEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("bmLineCaseEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BmLineCaseEOService extends BaseService<BmLineCaseEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BmLineCaseEOService.class);

    @Autowired
    private BmLineCaseEODao dao;

    public BmLineCaseEODao getDao() {
        return dao;
    }

    @Autowired
    private BmLineCaseInfoEOService bmLineCaseInfoEOService;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Value("${line.no}")
    private String lineNo;

    public void create(BmLineCaseEO bmLineCaseEO) throws Exception {
        bmLineCaseEO.setId(UUID.randomUUID10());
        bmLineCaseEO.setUpdateBy(bmLineCaseEO.getCreateBy());
        bmLineCaseEO.setUpdateTime(bmLineCaseEO.getCreateTime());
        bmLineCaseEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        this.getDao().insertSelective(bmLineCaseEO);
        createCaseInfo(bmLineCaseEO);
    }

    public void update(BmLineCaseEO bmLineCaseEO) throws Exception {
        this.getDao().updateByPrimaryKeySelective(bmLineCaseEO);
        bmLineCaseInfoEOService.deleteByCaseId(bmLineCaseEO.getId());
        createCaseInfo(bmLineCaseEO);
    }

    public void createCaseInfo(BmLineCaseEO bmLineCaseEO) throws Exception {
        if (CollectionUtils.isEmpty(bmLineCaseEO.getBmLineCaseInfoEOList()) == false) {
            for (BmLineCaseInfoEO infoEO : bmLineCaseEO.getBmLineCaseInfoEOList()) {
                infoEO.setId(UUID.randomUUID10());
                infoEO.setUpdateBy(bmLineCaseEO.getCreateBy());
                infoEO.setUpdateTime(bmLineCaseEO.getCreateTime());
                infoEO.setCreateBy(bmLineCaseEO.getCreateBy());
                infoEO.setCreateTime(bmLineCaseEO.getCreateTime());
                infoEO.setCaseId(bmLineCaseEO.getId());
                infoEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                bmLineCaseInfoEOService.insertSelective(infoEO);
            }
        }
    }

    /**
     * 获取委托单编号接口
     *
     * @return
     */
    public String getLineNo() {
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.BM_LINE_NO);
        String date = DateUtils.dateToString(new Date(), "yyyy");
        // 如果查不到流水号新增
        if (pipelineNumberEO == null) {
            PipelineNumberEO eo = new PipelineNumberEO();
            // 为了保证查询时的流水号，和保存委托时的流水号相同。未查询到流水号时返回0001，然后将当前流水号+1，保存数据库。
            eo.setTally(2);
            eo.setParticularYear(date);
            eo.setType(ConstantUtils.BM_LINE_NO);
            pipelineNumberEODao.insertSelective(eo);
            return lineNo + date + "-0001";
        }
        // 如果当前年份不等于数据库中以保存的年份，重新开始
        if (!date.equals(pipelineNumberEO.getParticularYear())) {
            // 为了保证查询时的流水号，和保存委托时的流水号相同。年份不相等时重新计算流水号，返回0001，然后将当前流水号+1，保存数据库。
            pipelineNumberEO.setTally(2);
            pipelineNumberEO.setParticularYear(date);
            pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
            return lineNo + date + "-0001";
        }
        //最大流水号
        String num = pipelineNumberEO.getTally().toString();
        StringBuilder sb = new StringBuilder(num);
        //自动补全4位
        while (sb.length() < 4) {
            sb.insert(0, "0");
        }
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
        return lineNo + date + "-" + sb.toString();
    }
}
