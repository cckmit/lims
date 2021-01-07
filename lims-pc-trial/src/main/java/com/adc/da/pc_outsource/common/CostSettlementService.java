package com.adc.da.pc_outsource.common;

import com.adc.da.common.WebUtil;
import com.adc.da.pc_announcement.dao.AnnPlanEODao;
import com.adc.da.pc_announcement.dao.AnnPlanProjectEODao;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_budget_use.dao.PcBudgetUseInfoEODao;
import com.adc.da.pc_budget_use.entity.PcBudgetUseEO;
import com.adc.da.pc_budget_use.entity.PcBudgetUseInfoEO;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_drive.entity.RoadLineEO;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_drive.vo.TrialProjectVO;
import com.adc.da.pc_execute.service.PCTrialExecuteService;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_outtrust.dao.PcOutsourceEntrustEODao;
import com.adc.da.pc_outtrust.entity.PcOutsourceEntrustEO;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

//这个service专门用来处理费用结算的逻辑

@Service
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CostSettlementService {

    @Autowired
    private CostSummaryEODao costSummaryEODao;

    @Autowired
    private PcDriveRecordEODao pcDriveRecordEODao;

    @Autowired
    private PcBudgetUseInfoEODao pcBudgetUseInfoEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private AnnPlanEODao annPlanEODao;

    @Autowired
    private PcOutsourceEntrustEODao pcOutsourceEntrustEODao;

    @Autowired
    private TrialTaskEODao trialTaskEODao;

    @Autowired
    private AnnPlanProjectEODao annPlanProjectEODao;

    @Autowired
    private PCTrialExecuteService pcTrialExecuteService;

    @Autowired
    private UserEOService userEOService;

    /**
     * @param formKey
     * @param maps
     * @param params
     * @throws Exception
     */
    //提取出公共的费用结算方法
    public void saveCostSummary(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        //根据传输的内容进行不同的判断 走不同的业务逻辑
        //根据formKey获取调用方的类型
        /**
         *formKey用于跳转对应列表页
         * RoadTestList 路送路试统计
         * pcOutsourceEntrustList  委外试验委托单
         * annPlanProjectList  执行计划的实际费用key
         * PcBudgetUseList  费用使用表单(维修费/燃料费)
         * SupplierTaskApplyList  辅助劳务
         */
        switch (formKey) {
            case "annPlanProjectList": {
                //公告试验费   公告管理编辑时提取出的费用结算逻辑 执行计划
                saveAnnPlanProjectCostSummary(formKey, maps, params);
                break;
            }
            case "RoadTestList": {
                //路送路试费
                saveRoadTestCostSummary(formKey, maps, params);
                break;
            }
            case "PcBudgetUseList": {
                //费用使用表单(维修费-对公/场地燃料费)
                savePcBudgetUseCostSummary(formKey, maps, params);
                break;
            }
            case "SupplierTaskApplyList": {
                //辅助劳务费/外包劳务费
                saveSupplierTaskApplyCost(formKey, maps, params);
                break;
            }
            case "pcOutsourceEntrustList": {
                //委外试验委托单
                savePcOutsourceEntrustCostSummary(formKey, maps, params);
                break;
            }
        }
    }

    /**
     * 辅助劳务费/外包劳务费 添加
     *
     * @param formKey 目标来源
     * @param maps
     * @param params  supId 供应商id
     *                trialTaskId 任务id
     *                applyNo 任务编号
     *                sum 需要添加金额
     * @throws Exception
     */
    public void saveSupplierTaskApplyCost(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        String supId = (String) params.get("supId");
        String trialTaskId = (String) params.get("trialTaskId");
        String applyNo = (String) params.get("applyNo");
        String createBy = (String) params.get("createBy");
        Double sum = (Double) params.get("sum");
        CostSummaryEO costSummary = new CostSummaryEO();
        CostSummaryEO costSummaryEO = null;
        //获取车型相关数据
        TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(trialTaskId);
        if (trialTaskEO != null) {//数据来源试验任务
            //根据需求此处的结算简述写死为辅助劳务费
            costSummary.setTaskDesc("辅助劳务费");
            setCarInfo(costSummary, trialTaskEO);
            setSubmitterInfo(costSummary,createBy);
            costSummaryEO = costSummaryEODao.getCostSummaryBySupId(supId, trialTaskId, formKey);
        } else {
            //数据来源执行计划 来自实验认证的数据
            AnnPlanProjectEO annProject = annPlanProjectEODao.selectByPrimaryKey(trialTaskId);
            saveAnnPlan(costSummary, annProject);
            costSummaryEO = costSummaryEODao.getCostSummaryBySupId(supId, annProject.getPlanId(), formKey);
        }
        //判断是否存在该结算数据
        if (costSummaryEO == null) {
            costSummary = new CostSummaryEO();
            costSummary.setId(UUID.randomUUID());
            costSummary.setDelFlag("0");
            costSummary.setCostType("6");
            costSummary.setTotalCost(String.valueOf(sum));
            costSummary.setPaymentCost(String.valueOf(sum));
            //委托任务单编号
            costSummary.setTrustNo(applyNo);
            //编辑状态(驳回1/激活0)
            costSummary.setEditStatus("0");
            costSummary.setFormKey(formKey);
            //添加供应商信息
            AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(supId);
            costSummary.setSupId(supId);
            saveAbilityVO(costSummary, abilityVO);
            costSummary.setPaymentStatus("0");
            costSummaryEODao.insert(costSummary);
        } else {
            costSummary = costSummaryEO;
            String totalCost = costSummary.getTotalCost();
            //已经存在该结算单的时候进行累加
            costSummary.setTotalCost(String.valueOf(Double.valueOf(totalCost) + sum));
            costSummary.setPaymentCost(String.valueOf(Double.valueOf(totalCost) + sum));
            costSummaryEODao.updateByPrimaryKey(costSummary);
        }
    }

    /**
     * @param formKey
     * @param maps    空
     * @param params  trialProjectVO TrialProjectVO 原数据类
     *                driverRecordId
     *                trialId  任务id
     *                carId  车辆id
     * @throws Exception
     */
    public void saveRoadTestCostSummary(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        TrialProjectVO trialProjectVO = (TrialProjectVO) params.get("trialProjectVO");
        String driverRecordId = (String) params.get("driverRecordId");
        String trialId = (String) params.get("trialId");
        TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(trialId);
        CostSummaryEO costSummary = costSummaryEODao.getCostSummaryBySupId(trialProjectVO.getSupid(), trialTaskEO.getId(), formKey);
        if (costSummary != null) {
            //获取行车记录条目算费用金额
            List<RoadLineEO> roadLineEOS = pcDriveRecordEODao.selectRoadLineEoByDriveRecordId(driverRecordId);
            double cost = 0.0;
            for (RoadLineEO roadLineEO : roadLineEOS) {
                if (StringUtils.isNotEmpty(roadLineEO.getCountPrice())) {
                    cost += Double.valueOf(roadLineEO.getCountPrice());
                }
            }
            String totalCost = costSummary.getTotalCost();
            costSummary.setTotalCost(String.valueOf(cost + Double.valueOf(totalCost)));
            costSummary.setPaymentCost(String.valueOf(cost + Double.valueOf(totalCost)));
            costSummaryEODao.updateByPrimaryKeySelective(costSummary);
        } else {
            //创建费用统计表
            costSummary = new CostSummaryEO();
            String id = UUID.randomUUID();
            costSummary.setId(id);
            costSummary.setDelFlag("0");
            //设置提交人
            setSubmitterInfo(costSummary,trialProjectVO.getCreateById());
            if (trialTaskEO != null) {
                //任务简要
                costSummary.setTaskDesc("路送路试费");
                setCarInfo(costSummary, trialTaskEO);
                //委托任务单编号
                costSummary.setTrustNo(trialProjectVO.getTrustcode());
                //添加供应商信息
                AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(trialProjectVO.getSupid());
                costSummary.setSupId(trialProjectVO.getSupid());
                saveAbilityVO(costSummary, abilityVO);
            } else {
                //数据来源认证试验
                AnnPlanProjectEO annProject = annPlanProjectEODao.selectByPrimaryKey(trialId);
                saveAnnPlan(costSummary, annProject);
            }
            //费用类型
            costSummary.setCostType("7");
            //获取行车记录条目算费用金额
            List<RoadLineEO> roadLineEOS = pcDriveRecordEODao.selectRoadLineEoByDriveRecordId(driverRecordId);
            double cost = 0.0;
            for (RoadLineEO roadLineEO : roadLineEOS) {
                if (StringUtils.isNotEmpty(roadLineEO.getCountPrice())) {
                    cost += Double.valueOf(roadLineEO.getCountPrice());
                }
            }
            //设置金额
            costSummary.setTotalCost(String.valueOf(cost));
            costSummary.setPaymentCost(String.valueOf(cost));
            //编辑状态
            costSummary.setEditStatus("0");
            //formKey用于跳转对应列表页
            costSummary.setFormKey(formKey);
            costSummary.setPaymentStatus("0");
            costSummaryEODao.insert(costSummary);
        }
    }

    /**
     * 委外试验委托单 添加方式
     * 委外试验委托单中只有试验任务执行委外申请委托单能申请
     * @param formKey 来源关键字
     * @param maps    原数据 此类型传空
     * @param params  trialProjectEOId 主id
     */
    public void savePcOutsourceEntrustCostSummary(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        String trialProjectEOId = (String) params.get("trialProjectEOId");
        PcOutsourceEntrustEO pcOutsourceEntrustEO = pcOutsourceEntrustEODao.selectByPrimaryKey(trialProjectEOId);
        if (StringUtils.isNotEmpty(pcOutsourceEntrustEO.getSupId())) {
            TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(pcOutsourceEntrustEO.getTrialTaskId());
            CostSummaryEO costSummary = costSummaryEODao.getCostSummaryBySupId(pcOutsourceEntrustEO.getSupId(), trialTaskEO.getId(), formKey);
            if (costSummary != null) {
                double cost = Double.parseDouble(costSummary.getTotalCost()) + Double.parseDouble(pcOutsourceEntrustEO.getPlanCost());
                costSummary.setTotalCost(String.valueOf(cost));
                costSummary.setPaymentCost(String.valueOf(cost));
                costSummaryEODao.updateByPrimaryKeySelective(costSummary);
            } else {
                AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(pcOutsourceEntrustEO.getSupId());
                costSummary = new CostSummaryEO();
                String id = UUID.randomUUID();
                costSummary.setId(id);
                costSummary.setDelFlag("0");
                //此处需要判断试验任务类型 如果是可靠性的话任务类型是场地费，非可靠性是委外试验费
                if("0".equals(trialTaskEO.getTaskType())){
                    costSummary.setCostType("4");
                    costSummary.setTaskDesc("场地费");
                }else{
                    costSummary.setCostType("2");
                    costSummary.setTaskDesc("研发委外试验");
                }
                costSummary.setTrustNo(pcOutsourceEntrustEO.getEntrustCode());
                costSummary.setItemNo(pcOutsourceEntrustEO.getOpCode());
                costSummary.setPaymentStatus("0");
                //提交人信息
                setSubmitterInfo(costSummary,pcOutsourceEntrustEO.getCreateBy());
                //供应商信息
                costSummary.setSupId(abilityVO.getId());
                saveAbilityVO(costSummary, abilityVO);
                costSummary.setFormKey(formKey);
                //添加车辆信息以及任务信息
                setCarInfo(costSummary, trialTaskEO);
                //加入费用信息
                costSummary.setPaymentCost(pcOutsourceEntrustEO.getPlanCost());
                costSummary.setTotalCost(pcOutsourceEntrustEO.getPlanCost());
                costSummaryEODao.insertSelective(costSummary);
            }
        }

    }

    /**
     * 公告试验费 处理方案
     *
     * @param formKey
     * @param maps    原数据
     * @param params
     */
    public void saveAnnPlanProjectCostSummary(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        //转换公告数据集
        List<AnnPlanProjectEO> annPlanProjectEOS = WebUtil.mapsToObjects(maps, AnnPlanProjectEO.class);
        for (AnnPlanProjectEO annPlanProjectEO : annPlanProjectEOS) {
            //当项目状态为已完成时同步实际费用到费用统计中
            String pjStatus = annPlanProjectEO.getPjStatus();
            String pjStatusName = "";
            if (StringUtils.isNotEmpty(pjStatus)) {
                DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("applyProjectType", pjStatus);
                pjStatusName = dic != null ? dic.getDicTypeName() : "";
            }
            //同步费用
            if (StringUtils.isNotEmpty(pjStatusName) && "已完成".equals(pjStatusName.trim())) {
                //公告还需要按照详情id查询到对应的费用结算数据
                CostSummaryEO costSummary = costSummaryEODao.getCostSummaryBySupIdAndDetialId(annPlanProjectEO.getPjSupplier(), annPlanProjectEO.getPlanId(),annPlanProjectEO.getId(), formKey);
                if (costSummary == null) {//没有相关费用信息
                    costSummary = new CostSummaryEO();
                    String id = UUID.randomUUID();
                    costSummary.setId(id);
                    costSummary.setDelFlag("0");
                    //添加公告信息
                    saveAnnPlan(costSummary, annPlanProjectEO);
                    costSummary.setEditStatus("0");
                    costSummary.setCostType("2");
                    costSummary.setFormKey(formKey);
                    //添加供应商信息
                    AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(annPlanProjectEO.getPjSupplier());
                    costSummary.setSupId(abilityVO.getId());
                    saveAbilityVO(costSummary, abilityVO);
                    if (StringUtils.isNotEmpty(annPlanProjectEO.getSumActualCost())) {
                        costSummary.setTotalCost(annPlanProjectEO.getSumActualCost());
                        costSummary.setPaymentCost(annPlanProjectEO.getSumActualCost());
                        costSummary.setPaymentStatus("0");
                        costSummaryEODao.insert(costSummary);
                    }
                }
                //公告的费用不需要进行叠加
//                else {//有相关供应商费用信息
//                    if (!costSummary.getDetialId().contains(annPlanProjectEO.getId())) {
//                        //计算总费用金额
//                        String sum = String.valueOf(Double.valueOf(costSummary.getTotalCost()) +
//                                Double.valueOf(annPlanProjectEO.getSumActualCost()));
//                        costSummary.setTotalCost(sum);
//                        costSummary.setPaymentCost(sum);
//                        costSummaryEODao.updateByPrimaryKeySelective(costSummary);
//                    }
//                }
            }
        }
    }

    /**
     * 费用结算整合后 维修费-对公 - 3  场地燃料费 - 5
     *
     * @param formKey 跳转页面对应关键字   PcBudgetUseList
     * @param maps    源数据  设置为空
     * @param params  dataOrigin 数据来源(“0”是PV/CV,“1”是认证)
     *                use PcBudgetUseEO
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void savePcBudgetUseCostSummary(String formKey, List<Map<String, Object>> maps, Map<String, Object> params) throws Exception {
        String dataOrigin = (String) params.get("dataOrigin");
        PcBudgetUseEO use = (PcBudgetUseEO) params.get("use");
        List<PcBudgetUseInfoEO> useInfoEOList = use.getPcBudgetUseInfoEOList();
        String trialId = use.getTrialId();
        CostSummaryEO costSummary = new CostSummaryEO();
        costSummary.setDataOrigin(dataOrigin);
        costSummary.setFormKey(formKey);
        //任务id
        costSummary.setTrialTaskId(trialId);
        costSummary.setDelFlag("0");
        //结算状态(0:未结算  1结算中   2已结算)
        costSummary.setPaymentStatus("0");
        costSummary.setTrustNo(use.getCode());
        costSummary.setEditStatus("0");
        setSubmitterInfo(costSummary,use.getCreateBy());
        //认证任务
        if ("1".equals(dataOrigin)) {
            AnnPlanProjectEO annProject = annPlanProjectEODao.selectByPrimaryKey(trialId);
            saveAnnPlan(costSummary, annProject);
        }
        if ("0".equals(dataOrigin)) {
            TrialTaskEO trialTaskEO = trialTaskEODao.selectByPrimaryKey(trialId);
            //获取负责人信息
            List<TrialPersonEO> personList = pcTrialExecuteService.getPersonList(trialId);
            if (personList.size() > 0) {
                costSummary.setEngineer(personList.get(0).getPersonName());
                costSummary.setEngineerId(personList.get(0).getPersonId());
            }
            costSummary.setTaskName(trialTaskEO.getTaskCode());
            //添加车辆信息
            setCarInfo(costSummary, trialTaskEO);
        }
        for (PcBudgetUseInfoEO useInfo : useInfoEOList) {
            AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(useInfo.getBuSupplierId());
            costSummary.setSupId(abilityVO.getId());
            saveAbilityVO(costSummary, abilityVO);
            if ("2".equals(abilityVO.getSupType())) {
                costSummary.setCostType("3");
                if ("0".equals(dataOrigin)) {
                    costSummary.setTaskDesc("维修费");
                }
            }
            else if ("6".equals(abilityVO.getSupType())) {
                costSummary.setCostType("5");
                if ("0".equals(dataOrigin)) {
                    costSummary.setTaskDesc("场地燃料费");
                }
            }else{
                throw new Exception("供应商类别错误");
            }
            CostSummaryEO costSummaryEO = costSummaryEODao.getCostSummaryBySupId(abilityVO.getId(), trialId, formKey);
            //当已存在该供应商的信息时进行修改 认证无需修改其他，只要增加费用金额
            if (costSummaryEO != null) {
                costSummary = costSummaryEO;
                Double sum = Double.valueOf(costSummary.getTotalCost()) + Double.valueOf(useInfo.getBuSubtotal());
                costSummary.setTotalCost(sum.toString());
                costSummary.setPaymentCost(sum.toString());
                costSummaryEODao.updateByPrimaryKeySelective(costSummary);
            } else {
                costSummary.setId(UUID.randomUUID());
                costSummary.setTotalCost(useInfo.getBuSubtotal());
                costSummary.setPaymentCost(useInfo.getBuSubtotal());
                costSummaryEODao.insert(costSummary);
            }
        }
    }

    /**
     * 根据实验认证的id获取信息并且加入费用结算表单
     *
     * @param costSummary      费用结算表单
     * @param annPlanProjectEO 认证信息
     */
    public void saveAnnPlan(CostSummaryEO costSummary, AnnPlanProjectEO annPlanProjectEO) throws Exception {
        //认证计划名称
        costSummary.setDataOrigin("1");
        costSummary.setTrialTaskId(annPlanProjectEO.getPlanId());
        costSummary.setCarType(annPlanProjectEO.getPjModel());
        costSummary.setEngineer(annPlanProjectEO.getPjEngineerName());
        costSummary.setEngineerId(annPlanProjectEO.getPjEngineerId());
        costSummary.setTaskDesc(annPlanProjectEO.getPjTaskExplain());
        costSummary.setDetialId(annPlanProjectEO.getId());
        //获取申报项目主信息
        AnnPlanEO annPlanEO = annPlanEODao.selectByPrimaryKey(annPlanProjectEO.getPlanId());
        if (annPlanEO != null) {
            //实验认证的任务名称
            costSummary.setTaskName(annPlanEO.getPlName());
            setSubmitterInfo(costSummary,annPlanEO.getPlCreateBy());
        }
    }

    /**
     * 根据供应商id获取供应商信息加入费用结算中
     *
     * @param costSummary 费用结算的表单
     * @param abilityVO   供应商
     */
    public void saveAbilityVO(CostSummaryEO costSummary, AbilityVO abilityVO) {
        if (abilityVO != null) {
            costSummary.setSupName(abilityVO.getSupName());
            costSummary.setSupCode(abilityVO.getSupCode());
            costSummary.setSupType(abilityVO.getSupType());
        }
    }

    /**
     * 设置车辆信息
     *
     * @param costSummary
     * @param trialTaskEO
     */
    public void setCarInfo(CostSummaryEO costSummary, TrialTaskEO trialTaskEO) throws Exception {
        costSummary.setDataOrigin("0");
        costSummary.setTrialTaskId(trialTaskEO.getId());
        //PV/CV试验任务编号
        costSummary.setTaskName(trialTaskEO.getTaskCode());
        List<TrialPersonEO> personList = pcTrialExecuteService.getPersonList(trialTaskEO.getId());
        //试验工程师 根据需求负责人更换为首个订单的负责人
        if (personList.size() > 0) {
            costSummary.setEngineer(personList.get(0).getPersonName());
            costSummary.setEngineerId(personList.get(0).getPersonId());
        }
        List<TrialItemsEO> items = trialTaskEO.getTrialItemsEOList();
        Set<String> carIds = new HashSet<>();
        Set<String> carTypes = new HashSet<>();
        Set<String> chassisNos = new HashSet<>();
        for (TrialItemsEO itemsEO : items) {
            //判断费用表中是否已经存在该id 如果不存在并且id不为空的时候进行添加
            if(StringUtils.isNotEmpty(itemsEO.getCarId())){
                if (StringUtils.isEmpty(costSummary.getCarIds())||!costSummary.getCarIds().contains(itemsEO.getCarId())) {
                    carIds.add(itemsEO.getCarId());
                }
            }
            //判断费用表中是否已经存在该车型 如果不存在并且车型不为空的时候进行添加
            if(StringUtils.isNotEmpty(itemsEO.getVehicleType())){
                if (StringUtils.isEmpty(costSummary.getCarType())||!costSummary.getCarType().contains(itemsEO.getVehicleType())) {
                    carTypes.add(itemsEO.getVehicleType());
                }
            }
            //判断费用表中是否已经存在该底盘号 如果不存在并且底盘号不为空的时候进行添加
            if(StringUtils.isNotEmpty(itemsEO.getChassisNumber())){
                if (StringUtils.isEmpty(costSummary.getChassisNo())||!costSummary.getChassisNo().contains(itemsEO.getChassisNumber()) ) {
                    chassisNos.add(itemsEO.getChassisNumber());
                }
            }
        }
        if (!carIds.isEmpty()) {
            costSummary.setCarIds(String.join(",", carIds));
        }
        if (!carTypes.isEmpty()) {
            costSummary.setCarType(String.join(",", carTypes));
        }
        if (!chassisNos.isEmpty()) {
            costSummary.setChassisNo(String.join(",", chassisNos));
        }
    }
    public void setSubmitterInfo(CostSummaryEO costSummary,String userId) throws Exception {
        UserEO userEO = userEOService.selectByPrimaryKey(userId);
        if(userEO!=null){
            costSummary.setSubmitterId(userEO.getUsid());
            costSummary.setSubmitter(userEO.getUsname());
        }
    }

}
