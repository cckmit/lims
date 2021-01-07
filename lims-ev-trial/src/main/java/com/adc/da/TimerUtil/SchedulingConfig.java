package com.adc.da.TimerUtil;

import com.adc.da.sys.dao.ParamEODao;
import com.adc.da.sys.entity.ParamEO;
import com.adc.da.trial_task.dao.TrialTaskEODao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Configuration
public class SchedulingConfig {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ParamEODao paramEODao;

    @Autowired
    private TrialTaskEODao trialTaskEODao;

    @Scheduled(cron = "0 0 1 * * ?")
    public void SchedulingTrialTaskStatus() throws Exception {
        long currentTimes = new Date().getTime();
        int warnDays = Integer.parseInt(getWarnDays("WARN_DAYS"));
       List<TrialTaskEO> trialTaskEOList = trialTaskEODao.findAllList();
        for(TrialTaskEO trialTaskEO : trialTaskEOList){
            long finishTimes = simpleDateFormat.parse(trialTaskEO.getFinishTime()).getTime();
            long days = (finishTimes - currentTimes)/(1000*60*60*24);
            if(days>warnDays){//未延期
                trialTaskEO.setDelyFlag("0");
            }
            if(days>0 && days<warnDays){//预警
                trialTaskEO.setDelyFlag("1");
            }
            if(days<0){//已延期
                trialTaskEO.setDelyFlag("2");
                //延期天数
                trialTaskEO.setFinishdateDelydays((int) -days);
            }
            trialTaskEODao.updateTrialTask(trialTaskEO);
        }

    }

    /**
     * 获取预警参数
     * @param code
     * @return
     */
    String getWarnDays(String code){
        ParamEO paramEO = paramEODao.getParamByCode(code);
        if(com.adc.da.util.utils.StringUtils.isEmpty(paramEO)){
            //默认三天
            return "3";
        }
        return paramEO.getParamValue();
    }

}
