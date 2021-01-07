package com.adc.da.trial_report.service;

import java.util.Date;
import java.util.List;

import com.adc.da.trial_report.page.TrialReportProcessEOPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.trial_report.dao.TrialReportProcessEODao;
import com.adc.da.trial_report.dto.TrialScheduleDto;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;


@Service("TrialReportProcessEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialReportProcessEOService extends BaseService<TrialReportProcessEO,String> {
	
	@Autowired
	private TrialReportProcessEODao trialReportProcessEODao;
	
	public TrialReportProcessEODao getDao() {
		return trialReportProcessEODao;
	}
	

    /**
     * 任务执行--进度日志
     *
     * @param trialtaskInsproId
     * @return
     */
    public List<TrialScfeduleVO> selectScheduleList(String trialtaskInsproId) {
        return trialReportProcessEODao.selectScheduleList(trialtaskInsproId);
    }

    /**
     * 任务执行--查询当前进度
     *
     * @param trialtaskInsproId
     * @return
     */
    public TrialScfeduleVO selectSchedule(String trialtaskInsproId) {
        List<TrialScfeduleVO> trialScfeduleVOS = trialReportProcessEODao.selectScheduleList(trialtaskInsproId);
        if (trialScfeduleVOS == null || trialScfeduleVOS.isEmpty()) {
            return new TrialScfeduleVO();
        }
        return trialScfeduleVOS.get(0);
    }

    
    
    /**
     * 任务执行--进度
     *
     * @param trialScheduleDto
     */
    public TrialReportProcessEO saveSceduleDto(TrialScheduleDto trialScheduleDto) {
        TrialReportProcessEO eo = new TrialReportProcessEO();
        eo.setId(UUID.randomUUID10());
        eo.setTrialtaskInsproId(trialScheduleDto.getTrialtaskInsproId());
        eo.setTrialprojectLabId(trialScheduleDto.getTrialLocation());
        eo.setTrialprojectRate(trialScheduleDto.getFinishRate());
        String status = trialScheduleDto.getTaskStatus();
        eo.setTrialprojectStatus(status);
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        eo.setCreateBy(userId);
        eo.setCreateTime(date);
        eo.setUpdateBy(userId);
        eo.setUpdateTime(date);
        eo.setDelFlag(String.valueOf(DeleteFlagEnum.NORMAL.getValue()));
        eo.setRemark(trialScheduleDto.getRemarks());
        eo.setFeedback(trialScheduleDto.getFeedback());
        trialReportProcessEODao.insertSelective(eo);
        return eo;
    }

    /**
     * 分页查询进度日志
     *
     * @param page
     * @return
     */
    public List<TrialScfeduleVO> getTrialReportProcessByPage(TrialReportProcessEOPage page) {
        Integer rowCount = trialReportProcessEODao.getTrialReportProcessByCount(page);
        page.getPager().setRowCount(rowCount);
        return trialReportProcessEODao.getTrialReportProcessByPage(page);
    }

}
