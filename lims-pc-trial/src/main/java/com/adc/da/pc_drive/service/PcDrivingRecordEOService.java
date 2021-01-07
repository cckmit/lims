package com.adc.da.pc_drive.service;

import com.adc.da.pc_drive.vo.PcCurveVo;
import com.adc.da.pc_drive.vo.PcDrivingRecourdVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_drive.dao.PcDrivingRecordEODao;
import com.adc.da.pc_drive.entity.PcDrivingRecordEO;

import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * <b>功能：</b>PC_DRIVING_RECORD PcDrivingRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcDrivingRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcDrivingRecordEOService extends BaseService<PcDrivingRecordEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcDrivingRecordEOService.class);

    @Autowired
    private PcDrivingRecordEODao dao;

    public PcDrivingRecordEODao getDao() {
        return dao;
    }

    public PcDrivingRecourdVo findDrivingRecord(String trialTaskID,String status) {
        PcDrivingRecourdVo drivingRecord = null;
        if (status.equals("0")) {
            drivingRecord = dao.findDrivingRecord(trialTaskID);

        }else if (status.equals("2")){
            drivingRecord = dao.findDrivingRecordByTaskId(trialTaskID);
        }
        if (drivingRecord != null && (drivingRecord.getPcTrialLineVos().contains(null) || drivingRecord.getPcTrialLineVos().size() == 0)) {
            drivingRecord.setPcTrialLineVos(null);
        }
        return drivingRecord;


    }

    /*标准曲线*/
    public List<PcCurveVo> findStandardCurve(String PcTrialTaskID) {
        List<PcCurveVo> standardCurve = dao.findStandardCurve(PcTrialTaskID);
        if (standardCurve.contains(null)||standardCurve.isEmpty()) {
            standardCurve.add(new PcCurveVo("", ""));
        }
        return standardCurve;
    }

    /*实际曲线*/
    public List<PcCurveVo> findActualCurve(String PcTrialTaskID) {
        List<PcCurveVo> actualCurve = dao.findActualCurve(PcTrialTaskID);
        if (actualCurve.contains(null)||actualCurve.isEmpty()) {
            actualCurve.add(new PcCurveVo("", ""));
        }
        return actualCurve;
    }


}
