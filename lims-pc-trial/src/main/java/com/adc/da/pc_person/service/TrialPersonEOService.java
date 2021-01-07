package com.adc.da.pc_person.service;

import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_person.dao.TrialPersonEODao;
import com.adc.da.pc_person.entity.TrialPersonEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PERSON TrialPersonEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("trialPersonEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialPersonEOService extends BaseService<TrialPersonEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialPersonEOService.class);

    @Autowired
    private TrialPersonEODao dao;

    public TrialPersonEODao getDao() {
        return dao;
    }

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Autowired
    private MessageEOService messageEOService;

    /**
     * 保存实体
     * @param trialPersonEOList
     * @param taskId
     */
    public void batchSave(List<TrialPersonEO> trialPersonEOList, String taskId) throws Exception {
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(taskId);
        MessageEO messageEO;
        String sendTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
        for(TrialPersonEO trialPersonEO:trialPersonEOList){
            trialPersonEO.setTrialTaskId(trialTaskEO.getId());
            trialPersonEO.setDelFlag("0");
            trialPersonEO.setCreateBy(UserUtils.getUserId());
            trialPersonEO.setUpdateBy(UserUtils.getUserId());
            trialPersonEO.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss.SSS"));   //加入毫秒因为样车带入使用人的逻辑中用此字段排序取第一个
            dao.insertSelective(trialPersonEO);
            messageEO = new MessageEO(UUID.randomUUID(),"0","PCTrust",sendTime,UserUtils.getUserId(),"您被任务书编号为：【"+trialTaskEO.getTaskCode()+"】的试验申请选为试验人员！",trialPersonEO.getPersonId(),trialTaskEO.getId());
            messageEOService.insertSelective(messageEO);
            //发消息通知
            messageEOService.sendMessage(messageEO);
        }
    }

    /**
     * 批量删除
     * @param taskId
     */
    public void batchDelete(String taskId){
        dao.batchDelete(taskId);
    }

    /**
     * 根据taskId查询激活的人员
     */
    public List<TrialPersonEO> selectByTaskId(String taskId){
        return dao.queryByTaskId(taskId);
    }




}
