package com.adc.da.pc_drive.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adc.da.pc_drive.dao.RoadLineRealityEODao;
import com.adc.da.pc_drive.entity.RoadLineRealityEO;
import com.adc.da.pc_outsource.common.CostSettlementService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_announcement.dao.AnnPlanEODao;
import com.adc.da.pc_announcement.dao.AnnPlanProjectEODao;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_drive.entity.PcDriveRecordEO;
import com.adc.da.pc_drive.entity.RoadLineEO;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_drive.vo.TrialProjectVO;
import com.adc.da.pc_drive.vo.TrustRelated;
import com.adc.da.pc_execute.service.PCTrialExecuteService;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.roadcost.service.RoadCostService;
import com.adc.da.roadtestcost.service.RoadTestCostService;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.supRoadTest.dao.RoadTestEODao;
import com.adc.da.supRoadTest.entity.RoadTestEO;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.util.utils.UUID;


/**
 *
 * <br>
 * <b>功能：</b>PC_DRIVE_RECORD PcDriveRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-07-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcDriveRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcDriveRecordEOService extends BaseService<PcDriveRecordEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcDriveRecordEOService.class);

    @Autowired
    private PcDriveRecordEODao dao;

    public PcDriveRecordEODao getDao() {
        return dao;
    }

    @Autowired
    private RoadCostService roadCostService;

    @Autowired
    private RoadTestCostService roadTestCostService;

    @Autowired
    private TrialTaskEODao trialTaskEODao;

    @Autowired
    private AnnPlanProjectEODao annPlanProjectEODao;

    @Autowired
    private AnnPlanEODao annPlanEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private RoadTestEODao roadTestEODao;

    @Autowired
    private PCTrialExecuteService pcTrialExecuteService;

    @Autowired
    private CostSummaryEODao costSummaryEODao;
    @Autowired
    private CostSettlementService costSettlementService;
    @Autowired
    private RoadLineRealityEODao roadLineRealityEoDao;

    /**
     * 插入行车记录
     * @param roadLineEO
     */
    public void insertRoadLineEO(RoadLineEO roadLineEO,PcDriveRecordEO pcDriveRecordEO) {
        //暂时将计算金额的
        calculatePrice(roadLineEO,pcDriveRecordEO);
        roadLineEO.setDriverRecordId(pcDriveRecordEO.getId());
        dao.insertRoadLineEo(roadLineEO);
    }

    /**
     * 设置价格
     */
    public void calculatePrice(RoadLineEO roadLineEO,PcDriveRecordEO pcDriveRecordEO){
        //路送总价计算
        if("CarType,LineRoad".equals(roadLineEO.getDrType())){
            //先查询这条的单价
            String  roadPrice=null;
//            String  roadPrice = roadCostService.getPriceByHorsePowerAndCarTypeAndRoadLine(pcDriveRecordEO.getHorsePower(),
//                    roadLineEO.getDriveCarType(),roadLineEO.getRoadLine());
            if(!StringUtils.isEmpty(roadPrice)){
                double unitPrice = Double.parseDouble(roadPrice);
                roadLineEO.setCountPrice(String.valueOf(unitPrice * (Double.parseDouble(roadLineEO.getMilePlus()))));
            }else{
                roadLineEO.setCountPrice("0");
            }
            //计算总价
        }
        //路试总价计算
        if("RoadTestCarType,LineRoad".equals(roadLineEO.getDrType())){
            String roadTestPrice =null;
//            String roadTestPrice = roadTestCostService.getPriceByHorsePowerAndCarTypeAndRoadLine(pcDriveRecordEO.getHorsePower(),
//                    roadLineEO.getDriveCarType(),roadLineEO.getTestConditions());
            if(!StringUtils.isEmpty(roadTestPrice)){
                double unitPrice = Double.parseDouble(roadTestPrice);
                roadLineEO.setCountPrice(String.valueOf(unitPrice * (Double.parseDouble(roadLineEO.getMilePlus()))));
            }else {
                roadLineEO.setCountPrice("0");
            }
        }
    }

    /**
     * 更新行车记录
     * @param roadLineEO
     */
    public void updateRoadLineEO(RoadLineEO roadLineEO) throws Exception {
        dao.updateRoadLineEo(roadLineEO);
        //根据行车记录表单id获取行车记录表单的提交状态
        String driverRecordId = roadLineEO.getDriverRecordId();
        PcDriveRecordEO pcDriveRecordEO = dao.selectByPrimaryKey(driverRecordId);
        //行车记录表单提交状态
        String driveState = pcDriveRecordEO.getDriveState();
        //状态1为行车记录已经提交
        if (StringUtils.isNotEmpty(driveState) && "1".equals(driveState)){
            //提交之后需要将信息保存到路送路试统计表中
            //整车id
            String carId = pcDriveRecordEO.getCarId();
            //试验委托id/执行计划id
            String trialId = pcDriveRecordEO.getTrialId();
            //路送路试委托单id
            String trialProjectId = pcDriveRecordEO.getTrialProjectId();
            //获取路送路试单信息
            TrialProjectVO trialProjectVO = dao.selectTrialProjectVO(trialProjectId);
            //创建路送路试统计表
            RoadTestEO roadTestEO = new RoadTestEO();
            String id = UUID.randomUUID();
            roadTestEO.setId(id);
            //设置试验任务ID/执行计划ID
            roadTestEO.setPcId(trialId);
            //设置路送路试单id
            roadTestEO.setTrialProjectId(trialProjectId);
            //设置车辆id
            roadTestEO.setCarId(carId);
            if (trialProjectVO != null){
                //设置试验任务书编号
                roadTestEO.setTaskBookCode(trialProjectVO.getTaskbookcode());
                //设置路送路试单编号
                roadTestEO.setTrialProjectCode(trialProjectVO.getTrustcode());
                //设置供应商id
                roadTestEO.setSupId(trialProjectVO.getSupid());
                //设置供应商名称
                roadTestEO.setSupName(trialProjectVO.getSupname());
                //获取供应商code
                AbilityVO abilityVO = dao.selectAbilityVO(trialProjectVO.getSupid());
                if (abilityVO != null){
                    //设置供应商code
                    roadTestEO.setSupCode(abilityVO.getSupCode());
                }
                //设置底盘号
                roadTestEO.setChassisCode(trialProjectVO.getChassiscode());
                //设置车型号
                roadTestEO.setVehicleType(trialProjectVO.getVehicletype());
                //设置车辆类型
                roadTestEO.setCarType(trialProjectVO.getCarType());
                //设置燃料类型
                roadTestEO.setFuelType(trialProjectVO.getFuelType());
                //设置马力
                roadTestEO.setTower(trialProjectVO.getTower());
            }
            //任务名称--试验任务名/申报项目名称
            TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(trialId);
            if (trialTaskEO == null){//试验任务不存在，代表是执行计划的行车记录
                //执行计划的行车记录，任务名称取申报项目名
                AnnPlanProjectEO annPlanProjectEO = annPlanProjectEODao.selectByPrimaryKey(trialId);
                if (annPlanProjectEO != null){
                    String planId = annPlanProjectEO.getPlanId();
                    //获取申报项目主信息
                    AnnPlanEO annPlanEO = annPlanEODao.selectByPrimaryKey(planId);
                    if (annPlanEO != null){
                        roadTestEO.setTaskBookName(annPlanEO.getPlName());
                    }
                }
            }else {//是试验任务就直接取任务名称
                roadTestEO.setTaskBookName(trialTaskEO.getTaskName());
            }
            //设置行车记录表单ID
            roadTestEO.setTrialId(driverRecordId);
            //设置行车记录ID
            roadTestEO.setRoadLineId(roadLineEO.getId());
            //设置行车记录中的载重
            roadTestEO.setLoad(roadLineEO.getLoad());
            //设置行车记录中的是否带上装/挂
            roadTestEO.setCarState(roadLineEO.getCarState());
            //设置行车记录中的委托类型
            roadTestEO.setDrType(roadLineEO.getDrType());
            //设置行车记录中的行车路况
            roadTestEO.setTestConditions(roadLineEO.getTestConditions());
            //设置行车记录中的里程
            roadTestEO.setMilePlus(roadLineEO.getMilePlus());
            //设置行车记录中的里程费用
            roadTestEO.setCountPrice(roadLineEO.getCountPrice());
            //设置行车记录中的试验状态
            roadTestEO.setTrialState(roadLineEO.getTrialState());
            //试验状态名称
            String trialStateName = "";
            //根据试验状态决定：停工天数/异地路试天数/停工补贴金额/异地路送补贴金额
            DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("roadTestState", roadLineEO.getTrialState());
            trialStateName = dic != null ? dic.getDicTypeName() : "";
            //停工天数
            String daysOff = "0";
            //出差/异地路试天数
            String evectionDays = "0";
            //停工补贴金额
            String subsidyOff = "0";
            //出差/异地路送补贴金额
            String evectionSubsidy = "0";
            if (StringUtils.isNotEmpty(trialStateName) && "本地路试".equals(trialStateName.trim())){
                daysOff = "0";
                evectionDays = "0";
                subsidyOff = "0";
                evectionSubsidy = "0";
            }else if (StringUtils.isNotEmpty(trialStateName) && "路试停工".equals(trialStateName.trim())){
                daysOff = "1";
                evectionDays = "0";
                subsidyOff = "100";
                evectionSubsidy = "0";
            }else if (StringUtils.isNotEmpty(trialStateName) && "路试出差".equals(trialStateName.trim())){
                daysOff = "0";
                evectionDays = "1";
                subsidyOff = "0";
                evectionSubsidy = "150";
            }else if (StringUtils.isNotEmpty(trialStateName) && "路试出差停工".equals(trialStateName.trim())){
                daysOff = "1";
                evectionDays = "1";
                subsidyOff = "100";
                evectionSubsidy = "150";
            }else if (StringUtils.isNotEmpty(trialStateName) && "正常路送".equals(trialStateName.trim())){
                daysOff = "0";
                evectionDays = "0";
                subsidyOff = "0";
                evectionSubsidy = "0";
            }else if (StringUtils.isNotEmpty(trialStateName) && "路送停工".equals(trialStateName.trim())){
                daysOff = "1";
                evectionDays = "0";
                subsidyOff = "250";
                evectionSubsidy = "0";
            }else if (StringUtils.isNotEmpty(trialStateName) && "异地路送".equals(trialStateName.trim())){
                daysOff = "0";
                evectionDays = "1";
                subsidyOff = "0";
                evectionSubsidy = "250";
            }
            //设置停工天数
            roadTestEO.setDaysOff(daysOff);
            //设置出差/异地路试天数
            roadTestEO.setEvectionDays(evectionDays);
            //设置停工补贴金额
            roadTestEO.setSubsidyOff(subsidyOff);
            //设置出差/异地路送补贴金额
            roadTestEO.setEvectionSubsidy(evectionSubsidy);
            Double countPrice = Double.valueOf(roadLineEO.getCountPrice());
            Double daysOffDouble = Double.valueOf(daysOff);
            Double evectionDaysDouble = Double.valueOf(evectionDays);
            Double subsidyOffDouble = Double.valueOf(subsidyOff);
            Double evectionSubsidyDouble = Double.valueOf(evectionSubsidy);
            //设置费用总计
            Double total = countPrice + (daysOffDouble * subsidyOffDouble) + (evectionDaysDouble * evectionSubsidyDouble);
            roadTestEO.setTotal(String.valueOf(total));
            roadTestEODao.insert(roadTestEO);
            //同步费用统计
//            synchroCost(driverRecordId,trialId,carId,trialProjectVO,trialTaskEO);
            //修改费用同步为 整合后的方法
            Map<String,Object> params=new HashMap<>();
            params.put("driverRecordId",driverRecordId);
            params.put("trialId",trialId);
            params.put("carId",carId);
            params.put("trialProjectVO",trialProjectVO);
            costSettlementService.saveCostSummary("RoadTestList",null,params);
        }
    }

    /**
     * 查询行车路线
     * @param id
     * @return
     */
    public List<RoadLineEO> selectRoadLineEoByDriveRecordId(String id) {
        return dao.selectRoadLineEoByDriveRecordId(id);
    }
    /**
     * 根据trial_project_id查询 PC_DRIVE_RECORD
     * @param projectId
     * @return
     */
    public PcDriveRecordEO selectByProjectId(String projectId){
          return dao.selectByProjectId(projectId);
    }


    /**
     * 获取相关方名称
     * @param taskNum
     * @return
     */
    public TrustRelated getTrustByTaskNum(String taskNum) {
        return dao.getTrustByTaskNum(taskNum);
    }

    /**
     * 判断是否存在相同底盘号的行车记录
     * @param carId
     * @return
     */
    public boolean isExsitsDriveRocord(String carId,String taskOrPlan){
        Integer count = dao.getDriveRecordsCountByChassisCode(carId,taskOrPlan);
        return count >=1 ;
    }

    /**
     * 将行车记录提交时费用同步到费用结算表中
     * driverRecordId 行车记录表单id
     * trialId  试验委托单id/执行计划id
     * carId  车辆id
     * trialProjectVO  路送路试委托单信息
     * trialTaskEO  试验委托信息
     *
     */
//    public void synchroCost(String driverRecordId,String trialId,String carId,
//                            TrialProjectVO trialProjectVO,TrialTaskEO trialTaskEO) throws Exception {
//        CostSummaryEO costSummary = costSummaryEODao.getCostSummaryEOBySupId(trialProjectVO.getSupid(),trialId,"RoadTestList");
//        new Object();
//        if (costSummary == null){
//            //创建费用统计表
//            CostSummaryEO costSummaryEO = new CostSummaryEO();
//            String id = UUID.randomUUID();
//            costSummaryEO.setId(id);
//            costSummaryEO.setDelFlag("0");
//            costSummaryEO.setTrialTaskId(trialId);
//            if (trialTaskEO != null){
//                //数据来源PV/CV
//                costSummaryEO.setDataOrigin("0");
//                //PV/CV试验任务编号
//                costSummaryEO.setTaskName(trialTaskEO.getTaskCode());
//                //任务简要
//                costSummaryEO.setTaskDesc("路送路试费");
//                List<TrialPersonEO> personList = pcTrialExecuteService.getPersonList(trialTaskEO.getId());
//                //试验工程师
//                String engineer = "";
//                for (TrialPersonEO trialPersonEO : personList){
//                    if (StringUtils.isNotEmpty(trialPersonEO.getPersonRoleName()) && trialPersonEO.getPersonRoleName()
//                            .contains("试验工程师")){
//                        engineer += trialPersonEO.getPersonName() + ",";
//                    }
//                }
//                costSummaryEO.setEngineer(engineer);
//            }else {
//                //数据来源认证试验
//                costSummaryEO.setDataOrigin("1");
//                //认证计划名称
//                AnnPlanProjectEO annPlanProjectEO = annPlanProjectEODao.selectByPrimaryKey(trialId);
//                if (annPlanProjectEO != null){
//                    //负责人(试验工程师)
//                    costSummaryEO.setEngineer(annPlanProjectEO.getPjEngineerName());
//                    //任务简要
//                    costSummaryEO.setTaskDesc(annPlanProjectEO.getPjTaskExplain());
//                    String planId = annPlanProjectEO.getPlanId();
//                    //获取申报项目主信息
//                    AnnPlanEO annPlanEO = annPlanEODao.selectByPrimaryKey(planId);
//                    if (annPlanEO != null){
//                        costSummaryEO.setTaskName(annPlanEO.getPlName());
//                    }
//                }
//            }
//            if (trialProjectVO != null){
//                //设置车型
//                costSummaryEO.setCarType(trialProjectVO.getCarType());
//                //车辆IDS
//                costSummaryEO.setCarIds(carId);
//                //底盘号
//                costSummaryEO.setChassisNo(trialProjectVO.getChassiscode());
//                //委托任务单编号
//                costSummaryEO.setTrustNo(trialProjectVO.getTrustcode());
//                //供应商id
//                costSummaryEO.setSupId(trialProjectVO.getSupid());
//                //供应商名字
//                costSummaryEO.setSupName(trialProjectVO.getSupname());
//                //获取供应商code
//                AbilityVO abilityVO = dao.selectAbilityVO(trialProjectVO.getSupid());
//                if (abilityVO != null){
//                    //设置供应商code
//                    costSummaryEO.setSupCode(abilityVO.getSupCode());
//                    //供应商类型
//                    costSummaryEO.setSupType(abilityVO.getSupType());
//                }
//            }
//            //费用类型
//            costSummaryEO.setCostType("7");
//            //获取行车记录条目算费用金额
//            List<RoadLineEO> roadLineEOS = dao.selectRoadLineEoByDriveRecordId(driverRecordId);
//            double cost = 0.0;
//            for(RoadLineEO roadLineEO : roadLineEOS){
//                if (StringUtils.isNotEmpty(roadLineEO.getCountPrice())){
//                    cost += Double.valueOf(roadLineEO.getCountPrice());
//                }
//            }
//            //设置金额
//            costSummaryEO.setTotalCost(String.valueOf(cost));
//            costSummaryEO.setPaymentCost(String.valueOf(cost));
//            //编辑状态
//            costSummaryEO.setEditStatus("0");
//            //formKey用于跳转对应列表页
//            costSummaryEO.setFormKey("RoadTestList");
//            costSummaryEO.setPaymentStatus("0");
//            costSummaryEODao.insert(costSummaryEO);
//        }else {//系统存在同一委托单同一供应商的单子
//            if (trialProjectVO != null){
//                if (!costSummary.getCarIds().contains(carId)){
//                    costSummary.setCarIds(costSummary.getCarIds() + "," + carId);
//                    costSummary.setCarType(costSummary.getCarType() + "," + trialProjectVO.getCarType());
//                    costSummary.setChassisNo(costSummary.getChassisNo() + "," + trialProjectVO.getChassiscode());
//                    costSummary.setTrustNo(costSummary.getTrustNo() + "," + trialProjectVO.getTrustcode());
//                }
//            }
//            //获取行车记录条目算费用金额
//            List<RoadLineEO> roadLineEOS = dao.selectRoadLineEoByDriveRecordId(driverRecordId);
//            double cost = 0.0;
//            for(RoadLineEO roadLineEO : roadLineEOS){
//                if (StringUtils.isNotEmpty(roadLineEO.getCountPrice())){
//                    cost += Double.valueOf(roadLineEO.getCountPrice());
//                }
//            }
//            String totalCost = costSummary.getTotalCost();
//            costSummary.setTotalCost(String.valueOf(cost + Double.valueOf(totalCost)));
//            costSummary.setPaymentCost(String.valueOf(cost + Double.valueOf(totalCost)));
//            costSummaryEODao.updateByPrimaryKeySelective(costSummary);
//        }
//    }




    public RoadLineEO getRoadLine(String id) {
        return dao.getRoadLineEOById(id);
    }

    public List<RoadLineRealityEO> selectRoadLineRealityEoByDriveRecordId(String driverRecordId){
        return roadLineRealityEoDao.selectRoadLineRealityEoByDriveRecordId(driverRecordId);
    }

    public void insertOrUpdateRoadReality(RoadLineRealityEO realityEO){
        if(StringUtils.isEmpty(realityEO.getId())){
            realityEO.setDelFlag("0");
            roadLineRealityEoDao.insertSelective(realityEO);
        }else{
            roadLineRealityEoDao.updateRoadLineRealityEo(realityEO);
        }
    }

}