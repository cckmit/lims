package com.adc.da.calender.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.calender.activeMQ.ProducerMQ;
import com.adc.da.calender.dao.PersonCalenderEODao;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.util.utils.DateUtils;


/**
 *
 * <br>
 * <b>功能：</b>TP_PERSON_CALENDER PersonCalenderEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("personCalenderEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonCalenderEOService extends BaseService<PersonCalenderEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersonCalenderEOService.class);

    @Autowired
    private PersonCalenderEODao dao;
    
    @Autowired
    private ProducerMQ producerMQ;

    public PersonCalenderEODao getDao() {
        return dao;
    }
    
    @Override
    public int insertSelective(PersonCalenderEO personCalenderEO) throws Exception {
    	int count = dao.insertSelective(personCalenderEO);
    	long currTime = new Date().getTime();
        MessageEO messageEO = new MessageEO();
        if(StringUtils.isNotEmpty(personCalenderEO.getStartremind())){
            messageEO.setBusinessId(personCalenderEO.getId());
            messageEO.setCcCreateById(personCalenderEO.getCcCreateById());
            messageEO.setSenduser(UserUtils.getUser().getUsname());
            messageEO.setIsread("0");
            messageEO.setSendlink("CALENDAR");
            messageEO.setTitle(personCalenderEO.getTitle());
            //开始时间-开始前时间-当前时间---------------------消息延时时间
            producerMQ.delaySend(ProducerMQ.ONEQUEUE, messageEO,DateUtils.parseDate(personCalenderEO.getStarttime(),"yyyy-MM-dd hh:mm:ss").getTime()-Long.parseLong(personCalenderEO.getStartremind())-currTime);
        }
        if(StringUtils.isNotEmpty(personCalenderEO.getEndremind())){
            messageEO = new MessageEO();
            messageEO.setBusinessId(personCalenderEO.getId());
            //创建人
            messageEO.setCcCreateById(personCalenderEO.getCcCreateById());
            messageEO.setSenduser(UserUtils.getUser().getUsname());
            messageEO.setIsread("0");
            messageEO.setSendlink("CALENDAR");
            messageEO.setTitle(personCalenderEO.getTitle());
            //开始时间-开始前时间-当前时间---------------------消息延时时间
            producerMQ.delaySend(ProducerMQ.ONEQUEUE, messageEO,DateUtils.parseDate(personCalenderEO.getEndtime(),"yyyy-MM-dd hh:mm:ss").getTime()-Long.parseLong(personCalenderEO.getEndremind())-currTime);
        }
        return count;
    }

    public int insertBatch(List<PersonCalenderEO> personCalenderEOList){
        return dao.insertBatch(personCalenderEOList);
    }

}
