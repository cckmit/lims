package com.adc.da.pc_items.service;

import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.TrialtaskSample;
import com.adc.da.car.service.SaCarDataService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_person.controller.TrialPersonEOController;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_person.service.TrialPersonEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_items.dao.TrialItemsEODao;
import com.adc.da.pc_items.entity.TrialItemsEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_ITEMS TrialItemsEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("trialItemsEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialItemsEOService extends BaseService<TrialItemsEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialItemsEOService.class);

    private String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd");

    @Autowired
    private TrialItemsEODao dao;

    public TrialItemsEODao getDao() {
        return dao;
    }

    @Autowired
    private SaCarDataService saCarDataService;

    @Autowired
    private SaCarDataDAO sacCarDataDao;

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Autowired
    private TrialPersonEOService trialPersonEOService;

    /**
     * 批量保存
     * @param trialItemsEOList
     * @param trialTaskEO
     */
    public void batchSave(List<TrialItemsEO> trialItemsEOList, TrialTaskEO trialTaskEO) throws Exception {
        TrialtaskSample trialtaskSample = new TrialtaskSample();
        String sampleNo = "";
        String taskCode = trialTaskEO.getTaskCode();
        int i = 1;
        for(TrialItemsEO trialItemsEO: trialItemsEOList) {
            sampleNo = saCarDataService.getApplyNo(trialTaskEO.getTaskCode());
            //检验项目
            trialItemsEO.setTrialTaskId(trialTaskEO.getId());
            trialItemsEO.setCreateBy(UserUtils.getUserId());
            trialItemsEO.setCreateTime(currDate);
            trialItemsEO.setSampleNo(sampleNo);
            String trialNum = taskCode + String.format("%03d", i++);
            trialItemsEO.setTrialNum(trialNum);
            dao.insertSelective(trialItemsEO);
            //样品关联表
            trialtaskSample.setId(UUID.randomUUID());
            trialtaskSample.setSampleNo(sampleNo);
            trialtaskSample.setTrialtaskId(trialTaskEO.getId());
            trialtaskSample.setItemsId(trialItemsEO.getId());
            if(StringUtils.isNotBlank(trialItemsEO.getCarId())){   //整车
                String carId = trialItemsEO.getCarId();
                String chassis = trialItemsEO.getChassisNumber();
                String[] splitCar = carId.split(",");
                String[] splitChassis = chassis.split(",");
                if(splitCar.length == splitChassis.length) {
                    for (int a =0;a<splitCar.length;a++) {
                        trialtaskSample.setSampleType("0");
                        trialtaskSample.setSampleId(splitCar[a]);
                        trialtaskSample.setSampleChassisNumber(splitChassis[a]);
                        sacCarDataDao.insertTrialtaskSample(trialtaskSample);
                    }
                }
            }
            if(StringUtils.isNotBlank(trialItemsEO.getPartId())){  //零部件
                String partId = trialItemsEO.getPartId();
                String partEngineNo = trialItemsEO.getPartEngineNo();
                String[] splitPart = partId.split(",");
                String[] splitEngineNo = partEngineNo.split(",");
                if(splitPart.length == splitEngineNo.length) {
                    for (int a =0;i<splitPart.length;i++) {
                        trialtaskSample.setSampleType("1");
                        trialtaskSample.setSampleId(splitPart[a]);
                        trialtaskSample.setSampleEngineType(splitEngineNo[a]);
                        sacCarDataDao.insertTrialtaskSample(trialtaskSample);
                    }
                }
            }
//          trialtaskSample.setSampleId(trialItemsEO.getSampleId());
 //           sacCarDataDao.insertTrialtaskSample(trialtaskSample);
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
     * 删除报告
     * @param reportId
     */
    public void delReport(String reportId){
        dao.delReport(reportId);
    }

    /**
     * genju taskid
     * 查询检验项目
     * @param taskId
     * @return
     */
    public List<TrialItemsEO> selectByTaskId(String taskId) throws Exception {
        //判断试验任务类型
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(taskId);
        String taskType = trialTaskEO.getTaskType();

        List<TrialPersonEO> trialPersonEOList = trialPersonEOService.selectByTaskId(taskId);

        List<TrialItemsEO> trialItemsEOList = dao.selectByTaskId(taskId);
/*        for(TrialItemsEO trialItemsEO : trialItemsEOList){    //set工程师
            trialItemsEO.setTrialEngineer("");
            for(TrialPersonEO trialPersonEO : trialPersonEOList){
                if("0".equals(taskType)){
                    //可靠性
//                    if(trialItemsEO.getSampleId() != null && trialItemsEO.getSampleId().equals(trialPersonEO.getSampleId())){
                    if(trialItemsEO.getCarId() != null){
                        trialItemsEO.setTrialEngineer(trialItemsEO.getTrialEngineer() + trialPersonEO.getPersonName()+", ");
                    }
                }else if("3".equals(taskType)){
                    //台架
                    if(trialItemsEO.getBenchOrgId() != null && trialItemsEO.getBenchOrgId().equals(trialPersonEO.getBenchOrgId())){
                        trialItemsEO.setTrialEngineer(trialItemsEO.getTrialEngineer() + trialPersonEO.getPersonName()+", ");
                    }
                }else{
                    trialItemsEO.setTrialEngineer(trialItemsEO.getTrialEngineer() + trialPersonEO.getPersonName()+", ");
                }
            }
        }*/
        return trialItemsEOList;
    }
    /**
     * 根据任务id集合查询该任务下车辆
     * @return
     */
    public List<TrialItemsEO> getCarByTaskId(String id) {
        List<TrialItemsEO>  carList=dao.selectByTaskId(id);
        return carList;
    }
}
