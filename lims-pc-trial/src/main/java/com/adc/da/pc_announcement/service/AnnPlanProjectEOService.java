package com.adc.da.pc_announcement.service;

import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.WebUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_announcement.VO.*;
import com.adc.da.pc_announcement.entity.AnnPlanAttachEO;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_outsource.common.CostSettlementService;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_announcement.dao.AnnPlanProjectEODao;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;

import java.io.File;
import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_PROJECT AnnPlanProjectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("annPlanProjectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnnPlanProjectEOService extends BaseService<AnnPlanProjectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanProjectEOService.class);

    @Autowired
    private AnnPlanProjectEODao dao;
    @Autowired
    private AnnPlanEOService annPlanEOService;
    @Autowired
    private AnnPlanProgramEOService annPlanProgramEOService;
    @Autowired
    private AnnPlanAttachEOService annPlanAttachEOService;
    @Value("${file.path}")
    private String filepath;
    @Autowired
    private DocUtil docUtil;
    @Autowired
    private DicTypeEOService dicTypeEOService;
    @Autowired
    private DicTypeEODao dicTypeEODao;
    @Autowired
    private CostSummaryEODao costSummaryEODao;
    @Autowired
    private PcDriveRecordEODao pcDriveRecordEODao;

    @Autowired
    private CostSettlementService costSettlementService;


    public AnnPlanProjectEODao getDao() {
        return dao;
    }

    /**
     * 新增申报项目
     * @param annPlanProjectVOList
     * @param planID
     */
    public void insertProject(List<AnnPlanProjectVO> annPlanProjectVOList, String planID) {
        for(AnnPlanProjectVO annPlanProjectVO : annPlanProjectVOList){
            AnnPlanProjectEO annPlanProjectEO = new AnnPlanProjectEO();
            BeanUtils.copyProperties(annPlanProjectVO,annPlanProjectEO);
            annPlanProjectEO.setPlanId(planID);
            annPlanProjectEO.setId(UUID.randomUUID());
            annPlanProjectEO.setPjStatus("0");
            dao.insertSelective(annPlanProjectEO);
        }
    }

    /**
     * 查出一个计划的所有申报项目
     * @param planID
     * @return
     */
    public List<AnnPlanProjectEO> getAnnPlanProjectList(String planID) {
        return dao.selectListByPlanID(planID);
    }

    /**
     * 查出一个计划的所有申报项目的ID
     * @param planID
     * @return
     */
    public List<String> getIDListByPlanID(String planID) {
        return dao.getIDListByPlanId(planID);
    }

    /**
     * 通过ID主键数组，删除对应的数据
     * @param projectIdList
     */
    public void delByIdList(List<String> projectIdList) {
        dao.delByIdList(projectIdList);
    }

    /**
     * 通过VO的list，来更新相关的申报项目
     * @param annPlanProjectList
     */
    public void updateListByList(List<AnnPlanProjectVO> annPlanProjectList) {
        for (AnnPlanProjectVO annPlanProjectVO : annPlanProjectList){
            AnnPlanProjectEO annPlanProjectEO = new AnnPlanProjectEO();
            BeanUtils.copyProperties(annPlanProjectVO,annPlanProjectEO);
            if (annPlanProjectVO.getId() != null && !"".equals(annPlanProjectVO.getId())){
                dao.updateByPrimaryKeySelective(annPlanProjectEO);
            }else {
                annPlanProjectEO.setId(UUID.randomUUID());
                annPlanProjectEO.setPjStatus("0");
                dao.insertSelective(annPlanProjectEO);
            }

        }

    }

    /**
     * 查出对应计划的，对应工程师的申报项目列表
     * @param planID
     * @param engineerID
     * @return
     */
    public List<AnnPlanProjectEO> getEngineerAnnPlanProjectList(String planID,String engineerID) {
        return dao.getEngineerAnnPlanProjectList(planID,engineerID);
    }
    /**
     * 对申报项目的状态变更
     */
    public void updeatProjectStatus(String projectId,String status){
        AnnPlanProjectEO annPlanProjectEO = new AnnPlanProjectEO();
        annPlanProjectEO.setId(projectId);
        annPlanProjectEO.setPjStatus(status);
        dao.updateByPrimaryKeySelective(annPlanProjectEO);
    }

    /**
     * 费用预算，实际费用查看。里面包含了计划的表头,检验方案的相关信息
     * @param projectId
     * @return
     */
    public AnnPlanAndProjectProgramVO getProjectMes(String supId, String projectId) throws Exception {
        AnnPlanAndProjectProgramVO annPlanAndProjectProgramVO = new AnnPlanAndProjectProgramVO();
        //得到对应的申报项目
        AnnPlanProjectEO annPlanProjectEO = dao.selectByPrimaryKey(projectId);
        String planId = annPlanProjectEO.getPlanId();
        //得到对应的试验计划
        AnnPlanEO annPlanEO = annPlanEOService.selectByPrimaryKey(planId);
        //得到对应的试验方案
        List<String> projectIdList = new ArrayList<>();
        projectIdList.add(projectId);
        List<String> idListByPjIDList = annPlanProgramEOService.getIdListByPjIDList(projectIdList);
        if (idListByPjIDList.size() != 0){
            List<AnnPlanProgramEO> annPlanProgramEOS = annPlanProgramEOService.getProgramMesByIds(idListByPjIDList);
            //当供应商id传入时才设置自动匹配
            if (StringUtils.isNotEmpty(supId)){
                for(AnnPlanProgramEO annPlanProgramEO : annPlanProgramEOS){
                    //获取项目代号
                    String tpTestPjNo = annPlanProgramEO.getTpTestPjNo();
                    //2020-07-14现场变更，需要项目代号唯一对应时直接匹配
                    List<SupProjectVO> supProject = annPlanProgramEOService.getSupProject(supId, tpTestPjNo);
                    if (supProject != null && supProject.size() == 1){
                        SupProjectVO supProjectVO = supProject.get(0);
                        annPlanProgramEO.setSupName(supProjectVO.getTestProject());
                        annPlanProgramEO.setSupManagerId(supProjectVO.getProjectCode());
                        annPlanProgramEO.setTpUnitPrice(supProjectVO.getSupPrice());
                        String supDiscountPrice = supProjectVO.getSupDiscountPrice();
                        String tpActualPjAmount = annPlanProgramEO.getTpActualPjAmount();
                        Double price = Double.valueOf(supDiscountPrice) * Double.valueOf(tpActualPjAmount);
                        annPlanProgramEO.setTpTotalPrice(String.valueOf(price));
                        annPlanProgramEO.setDiscount(supProjectVO.getSupDiscount());
                    }
                }
            }
            annPlanAndProjectProgramVO.setAnnPlanProgramEOList(annPlanProgramEOS);
        }
        //将上面得到的信息，set到VO中
        annPlanAndProjectProgramVO.setAnnPlanEO(annPlanEO);
        List<AnnPlanProjectEO> annPlanProjectEOS = new ArrayList<>();
        annPlanProjectEOS.add(annPlanProjectEO);
        annPlanAndProjectProgramVO.setAnnPlanProjectEOList(annPlanProjectEOS);
        return annPlanAndProjectProgramVO;

    }

    /**
     * 更新申报项目
     * @param annPlanProjectEOList
     */
    public void updateEngineerProject(List<AnnPlanProjectEO> annPlanProjectEOList) throws Exception {
        for(AnnPlanProjectEO annPlanProjectEO : annPlanProjectEOList){
            //这样做是为了前台的空串，覆盖了数据库的数据
            dao.updateByPrimaryKeySelective(annPlanProjectEO);
            annPlanEOService.updatePlanStatusToFinish(annPlanProjectEO.getPlanId());
            //当项目状态为已完成时同步实际费用到费用统计中
//            String pjStatus = annPlanProjectEO.getPjStatus();
//            String pjStatusName = "";
//            if (org.apache.commons.lang.StringUtils.isNotEmpty(pjStatus)){
//                DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("applyProjectType", pjStatus);
//                pjStatusName = dic != null ? dic.getDicTypeName() : "";
//            }
//            //同步费用
//            if (StringUtils.isNotEmpty(pjStatusName) && "已完成".equals(pjStatusName.trim())){
//                CostSummaryEO costSummary = costSummaryEODao.getCostSummaryEOBySupId(annPlanProjectEO.getPjSupplier(),annPlanProjectEO.getPlanId(), "annPlanProjectList");
//                if (costSummary == null){//没有相关费用信息
//                    CostSummaryEO costSummaryEO = new CostSummaryEO();
//                    String id = UUID.randomUUID();
//                    costSummaryEO.setId(id);
//                    costSummaryEO.setDelFlag("0");
//                    costSummaryEO.setTrialTaskId(annPlanProjectEO.getPlanId());
//                    costSummaryEO.setDataOrigin("1");
//                    AnnPlanEO annPlanEO = annPlanEOService.selectByPrimaryKey(annPlanProjectEO.getPlanId());
//                    if (annPlanEO != null){
//                        costSummaryEO.setTaskName(annPlanEO.getPlName());
//                    }
//                    costSummaryEO.setCarType(annPlanProjectEO.getPjModel());
//                    costSummaryEO.setEngineer(annPlanProjectEO.getPjEngineerName());
//                    costSummaryEO.setCostType("2");
//                    costSummaryEO.setTotalCost(annPlanProjectEO.getSumActualCost());
//                    costSummaryEO.setTaskDesc(annPlanProjectEO.getPjTaskExplain());
//                    costSummaryEO.setEditStatus("0");
//                    costSummaryEO.setFormKey("annPlanProjectList");
//                    costSummaryEO.setDetialId(annPlanProjectEO.getId());
//                    costSummaryEO.setSupId(annPlanProjectEO.getPjSupplier());
//                    costSummaryEO.setSupName(annPlanProjectEO.getPjSupplierName());
//                    AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(annPlanProjectEO.getPjSupplier());
//                    costSummaryEO.setSupType(abilityVO.getSupType());
//                    costSummaryEO.setSupCode(abilityVO.getSupCode());
//                    costSummaryEO.setPaymentCost(annPlanProjectEO.getSumActualCost());
//                    costSummaryEO.setPaymentStatus("0");
//                    costSummaryEODao.insert(costSummaryEO);
//                }else{//有相关供应商费用信息
//                    if (!costSummary.getDetialId().contains(annPlanProjectEO.getId())){
//                        String carType = costSummary.getCarType();
//                        if (!carType.contains(annPlanProjectEO.getPjModel())){
//                            carType = carType + "," + annPlanProjectEO.getPjModel();
//                        }
//                        costSummary.setCarType(carType);
//                        String engineer = costSummary.getEngineer();
//                        if (!engineer.contains(annPlanProjectEO.getPjEngineerName())){
//                            engineer = engineer + "," + annPlanProjectEO.getPjEngineerName();
//                        }
//                        costSummary.setEngineer(engineer);
//                        String sum = String.valueOf(Double.valueOf(costSummary.getTotalCost()) +
//                                Double.valueOf(annPlanProjectEO.getSumActualCost()));
//                        costSummary.setTotalCost(sum);
//                        costSummary.setPaymentCost(sum);
//                        costSummaryEODao.updateByPrimaryKeySelective(costSummary);
//                    }
//                }
//            }
        }
        //整合后的费用统计方法
        List<Map<String, Object>> maps = WebUtil.objectsToMaps(annPlanProjectEOList);
        costSettlementService.saveCostSummary("annPlanProjectList",maps,null);
    }

    /**
     * 保存报告ID，报告名称.同时更新申报项目状态为“已完成”
     * 同时对计划的所有申报项目的状态进行判断。如果都为“已完成”或者“已取消”，则变更计划状态为“已完成”
     * @param annPlanProjectEO
     */
    public void saveReport(AnnPlanProjectEO annPlanProjectEO) {
        AnnPlanProjectEO annPlanProject = new AnnPlanProjectEO();
        annPlanProject.setId(annPlanProjectEO.getId());
        annPlanProject.setPjReportName(annPlanProjectEO.getPjReportId());
        annPlanProject.setPjReportName(annPlanProjectEO.getPjReportName());
        annPlanProject.setPjStatus("2");
        dao.updateByPrimaryKeySelective(annPlanProject);
    }

    /**
     * 取消申报项目，同时填充备注
     * @param annPlanProjectEO
     */
    public void updateReportToCancel(AnnPlanProjectEO annPlanProjectEO) {
        AnnPlanProjectEO annPlanProject = new AnnPlanProjectEO();
        annPlanProject.setId(annPlanProjectEO.getId());
        annPlanProject.setPjStatus("3");
        annPlanProject.setPjRemakes(annPlanProjectEO.getPjRemakes());
        dao.updateByPrimaryKeySelective(annPlanProject);
    }

    /**
     * 更新预算费用
     * @param annPlanAndProjectProgramVO
     */
    public void updateBudgetCost(AnnPlanAndProjectProgramVO annPlanAndProjectProgramVO) throws Exception {
        AnnPlanProjectEO annPlanProjectEO = annPlanAndProjectProgramVO.getAnnPlanProjectEOList().get(0);
        List<AnnPlanProgramEO> annPlanProgramEOS = annPlanAndProjectProgramVO.getAnnPlanProgramEOList();
        dao.updateByPrimaryKeySelective(annPlanProjectEO);
        for (AnnPlanProgramEO annPlanProgramEO : annPlanProgramEOS){
            annPlanProgramEOService.updateByPrimaryKeySelective(annPlanProgramEO);
        }

    }

    /**
     * 更新实际费用
     * @param annPlanAndProjectProgramVO
     */
    public void updateActualCost(AnnPlanAndProjectProgramVO annPlanAndProjectProgramVO) throws Exception {
        //修改费用和
        AnnPlanProjectEO annPlanProjectEO = annPlanAndProjectProgramVO.getAnnPlanProjectEOList().get(0);
        //实际费用现在允许可以编辑，需要保存
        List<AnnPlanProgramEO> annPlanProgramEOList = annPlanAndProjectProgramVO.getAnnPlanProgramEOList();
        for(AnnPlanProgramEO annPlanProgramEO : annPlanProgramEOList){
            if (StringUtils.isNotEmpty(annPlanProgramEO.getId())){
                annPlanProgramEOService.updateByPrimaryKeySelective(annPlanProgramEO);
            }else {
                annPlanProgramEOService.insert(annPlanProgramEO);
            }
        }
        dao.updateByPrimaryKeySelective(annPlanProjectEO);
    }
    /**
     * 生成委托单并保存到文件夹
     * @parem delegateDataVO
     */
    public String createDelegate(DelegateDataVO delegateDataVO) throws Exception {
        Map<String, Object> dataMap = new HashMap();
        ////委托确认单编号
        dataMap.put("entrustCode", StringUtils.isNotEmpty(delegateDataVO.getPickingCode())?delegateDataVO.getPickingCode():"");
        //任务来源编号
        dataMap.put("trialTaskCode", StringUtils.isNotEmpty(delegateDataVO.getTorCode())?delegateDataVO.getTorCode():"");
        //立项审批表编号
        dataMap.put("opCode", StringUtils.isNotEmpty(delegateDataVO.getPjApprovalFormNo())?delegateDataVO.getPjApprovalFormNo():"");
        //费用预算
        dataMap.put("planCost", StringUtils.isNotEmpty(delegateDataVO.getCostBudget())?delegateDataVO.getCostBudget():"");
        //委托单位名称
        dataMap.put("entrustCompanyName", StringUtils.isNotEmpty(delegateDataVO.getOrgName())?delegateDataVO.getOrgName():"");
        //邮  编
        dataMap.put("zipCode", StringUtils.isNotEmpty(delegateDataVO.getPostcode())?delegateDataVO.getPostcode():"");
        //委托单位地址
        dataMap.put("entrustCompanyAddr", StringUtils.isNotEmpty(delegateDataVO.getAddress())?delegateDataVO.getAddress():"");
        //电  话
        dataMap.put("telPhone", StringUtils.isNotEmpty(delegateDataVO.getTelephone())?delegateDataVO.getTelephone():"");
        //产品名称
        dataMap.put("productName", StringUtils.isNotEmpty(delegateDataVO.getProductName())?delegateDataVO.getProductName():"");
        //商  标
        dataMap.put("logo", StringUtils.isNotEmpty(delegateDataVO.getLogo())?delegateDataVO.getLogo():"");
        //委托项目
        dataMap.put("entrustProject", StringUtils.isNotEmpty(delegateDataVO.getEntrustedProject())?delegateDataVO.getEntrustedProject():"");
        //以下是复选框参数，先默认不选
        dataMap.put("publicDoc","0");
        dataMap.put("epDoc","0");
        dataMap.put("cccDoc","0");
        dataMap.put("outDoc","0");
        dataMap.put("trustDoc","0");
        dataMap.put("oilDoc","0");
        dataMap.put("devDoc","0");
        dataMap.put("othersType","0");
        dataMap.put("sampling","0");
        dataMap.put("sendSample","0");
        dataMap.put("gbStd","0");
        dataMap.put("industryStd","0");
        dataMap.put("enterpriseStd","0");
        dataMap.put("abroadStd","0");
        dataMap.put("othersStd","0");
        //委托检验类别
        String entrustedType = delegateDataVO.getEntrustedType();
        if (StringUtils.isNotEmpty(entrustedType)){
            String[] entrustTypeArray = entrustedType.split(",");
            for (String entrust : entrustTypeArray) {
                //设置委托检验类别
                switch (entrust) {
                    case "1":
                        dataMap.put("publicDoc", "1");//公告检验
                        continue;
                    case "2":
                        dataMap.put("epDoc", "1");//环保检验
                        continue;
                    case "3":
                        dataMap.put("cccDoc", "1");//3C检验
                        continue;
                    case "4":
                        dataMap.put("outDoc", "1");//出口检验
                        continue;
                    case "5":
                        dataMap.put("trustDoc", "1");//委托检验
                        continue;
                    case "6":
                        dataMap.put("oilDoc", "1");//交通部油耗检验
                        continue;
                    case "7":
                        dataMap.put("devDoc", "1");//开发试验
                        continue;
                    case "8":
                        dataMap.put("othersType", "1");//其它
                        continue;
                }
            }
        }
        String sampleType = delegateDataVO.getSampleType();
        if(StringUtils.isNotEmpty(sampleType)) {
            String[] sampleTypeArray = sampleType.split(",");
            for (String sType : sampleTypeArray) {
                switch (sType) {
                    case "9":
                        dataMap.put("sampling", "1");//抽样
                        continue;
                    case "A":
                        dataMap.put("sendSample", "1");//送样
                        continue;
                }
            }
        }
        String commissionRequirements = delegateDataVO.getCommissionRequirements();
        if(org.apache.commons.lang.StringUtils.isNotEmpty(commissionRequirements)){
            String[] accArray = commissionRequirements.split(",");
            for(String acc : accArray){
                //委托检验依据及要求
                switch (acc){
                    case "B" :
                        dataMap.put("gbStd","1");//国家标准
                        continue;
                    case "C" :
                        dataMap.put("industryStd","1");//行业标准
                        continue;
                    case "D" :
                        dataMap.put("enterpriseStd","1");//企业标准
                        continue;
                    case "E" :
                        dataMap.put("abroadStd","1");//国外标准
                        continue;
                    case "F" :
                        dataMap.put("othersStd","1");//其它（附说明）
                        continue;

                }
            }
        }
        //委托检验要求完成时间
        dataMap.put("requireFinishDate", StringUtils.isNotEmpty(delegateDataVO.getCompletionTime())?delegateDataVO.getCompletionTime():"");
        //委托联系人
        dataMap.put("createByName", StringUtils.isNotEmpty(delegateDataVO.getEntrustedContact())?delegateDataVO.getEntrustedContact():"");
        //手机
        dataMap.put("createByPhone", StringUtils.isNotEmpty(delegateDataVO.getMobilePhone())?delegateDataVO.getMobilePhone():"");
        //委托方审核
        dataMap.put("zhuguan", "");
        //委托方批准
        dataMap.put("kezhang", "");
        //检测单位
        dataMap.put("supName", StringUtils.isNotEmpty(delegateDataVO.getDetectionUnit())?delegateDataVO.getDetectionUnit():"");
        //任务编号
        dataMap.put("taskCode", StringUtils.isNotEmpty(delegateDataVO.getTaskNumber())?delegateDataVO.getTaskNumber():"");
        //单位地址
        dataMap.put("supAddr", StringUtils.isNotEmpty(delegateDataVO.getUnitAddress())?delegateDataVO.getUnitAddress():"");
        //计划报告完成时间
        dataMap.put("planReportFinishDate", StringUtils.isNotEmpty(delegateDataVO.getPlanCompletionTime())?delegateDataVO.getPlanCompletionTime():"");
        //联系人
        dataMap.put("supManagerName", StringUtils.isNotEmpty(delegateDataVO.getContacts())?delegateDataVO.getContacts():"");
        //手机
        dataMap.put("supManagerPhone", StringUtils.isNotEmpty(delegateDataVO.getHandset())?delegateDataVO.getHandset():"");
        String[] idS = delegateDataVO.getIdS();//申报项目选中的申报项目id
        String plNo = delegateDataVO.getPlNo();//申报来源编号
        String plSource = delegateDataVO.getPlSource();//申报来源
        List<AnnPlanProjectExportVO> annPlanProjectExportVOList = new ArrayList<>();//申报项目list
        for (String id : idS){
            AnnPlanProjectEO annPlanProjectEO = dao.selectByPrimaryKey(id);//获取申报项目
            //判断所有的预算费用是否为空
            boolean flag =  false;
            Double pjYsSyfy = 0.0;//试验费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsSyfy())){
                pjYsSyfy = Double.valueOf(annPlanProjectEO.getPjYsSyfy());
                flag = true;
            }
            Double pjYsDxsyfy = 0.0;//定型试验费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsDxsyfy())){
                pjYsDxsyfy = Double.valueOf(annPlanProjectEO.getPjYsDxsyfy());
                flag = true;
            }
            Double pjYsQjsyfy = 0.0;//强检试验费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsQjsyfy())){
                pjYsQjsyfy = Double.valueOf(annPlanProjectEO.getPjYsQjsyfy());
                flag = true;
            }
            Double pjYsTjb = 0.0;//统计表/视图（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsTjb())){
                pjYsTjb = Double.valueOf(annPlanProjectEO.getPjYsTjb());
                flag = true;
            }
            Double pjYsZycdfy = 0.0;//租用场地费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsZycdfy())){
                pjYsZycdfy = Double.valueOf(annPlanProjectEO.getPjYsZycdfy());
                flag = true;
            }
            Double pjYsXnyfy = 0.0;//新能源费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsXnyfy())){
                pjYsXnyfy = Double.valueOf(annPlanProjectEO.getPjYsXnyfy());
                flag = true;
            }
            Double pjYsHbfy = 0.0;//环保费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsHbfy())){
                pjYsHbfy = Double.valueOf(annPlanProjectEO.getPjYsHbfy());
                flag = true;
            }
            Double pjYs3cfy = 0.0;//3C费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYs3cfy())){
                pjYs3cfy = Double.valueOf(annPlanProjectEO.getPjYs3cfy());
                flag = true;
            }
            Double pjYsJtbyh = 0.0;//交通部油耗（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsJtbyh())){
                pjYsJtbyh = Double.valueOf(annPlanProjectEO.getPjYsJtbyh());
                flag = true;
            }
            Double pjYs3csbf = 0.0;//3C申报费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYs3csbf())){
                pjYs3csbf = Double.valueOf(annPlanProjectEO.getPjYs3csbf());
                flag = true;
            }
            Double pjYsRzmdxm = 0.0;//认证摸底项目（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsRzmdxm())){
                pjYsRzmdxm = Double.valueOf(annPlanProjectEO.getPjYsRzmdxm());
                flag = true;
            }
            Double pjYsWtsy = 0.0;//委托试验（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsWtsy())){
                pjYsWtsy = Double.valueOf(annPlanProjectEO.getPjYsWtsy());
                flag = true;
            }
            Double pjYsYyclrl = 0.0;//营运车辆燃料（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsYyclrl())){
                pjYsYyclrl = Double.valueOf(annPlanProjectEO.getPjYsYyclrl());
                flag = true;
            }
            Double pjYsYyclaq = 0.0;//营运车辆安全（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsYyclaq())){
                pjYsYyclaq = Double.valueOf(annPlanProjectEO.getPjYsYyclaq());
                flag = true;
            }
            Double pjYsCkrzxm = 0.0;//出口认证项目（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsCkrzxm())){
                pjYsCkrzxm = Double.valueOf(annPlanProjectEO.getPjYsCkrzxm());
                flag = true;
            }
            Double pjYsSyzbjd = 0.0;//试验准备阶段（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsSyzbjd())){
                pjYsSyzbjd = Double.valueOf(annPlanProjectEO.getPjYsSyzbjd());
                flag = true;
            }
            Double pjYsSyjsfz = 0.0;//试验驾驶辅助（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsSyjsfz())){
                pjYsSyjsfz = Double.valueOf(annPlanProjectEO.getPjYsSyjsfz());
                flag = true;
            }
            Double pjYsQt1 = 0.0;//其他1（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsQt1())){
                pjYsQt1 = Double.valueOf(annPlanProjectEO.getPjYsQt1());
                flag = true;
            }
            Double pjYsBz1 = 0.0;//备注1（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsBz1())){
                pjYsBz1 = Double.valueOf(annPlanProjectEO.getPjYsBz1());
                flag = true;
            }
            Double pjYsQt2 = 0.0;//其他2（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsQt2())){
                pjYsQt2 = Double.valueOf(annPlanProjectEO.getPjYsQt2());
                flag = true;
            }
            Double pjYsBz2 = 0.0;//备注2（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsBz2())){
                pjYsBz2 = Double.valueOf(annPlanProjectEO.getPjYsBz2());
                flag = true;
            }
            Double pjYsJyf = 0.0;//加油费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsJyf())){
                pjYsJyf = Double.valueOf(annPlanProjectEO.getPjYsJyf());
                flag = true;
            }
            Double pjYsJqf = 0.0;//加气费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsJqf())){
                pjYsJqf = Double.valueOf(annPlanProjectEO.getPjYsJqf());
                flag = true;
            };
            Double pjYsCdf = 0.0;//充电费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsCdf())){
                pjYsCdf = Double.valueOf(annPlanProjectEO.getPjYsCdf());
                flag = true;
            }
            Double pjYsWxf = 0.0;//维修费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsWxf())){
                pjYsWxf = Double.valueOf(annPlanProjectEO.getPjYsWxf());
                flag = true;
            }
            Double pjYsZxf = 0.0;//装卸费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsZxf())){
                pjYsZxf = Double.valueOf(annPlanProjectEO.getPjYsZxf());
                flag = true;
            }
            Double pjYsTcf = 0.0;//停车费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsTcf())){
                pjYsTcf = Double.valueOf(annPlanProjectEO.getPjYsTcf());
                flag = true;
            }
            Double pjYsKdf = 0.0;//快递/物流费（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsKdf())){
                pjYsKdf = Double.valueOf(annPlanProjectEO.getPjYsKdf());
                flag = true;
            }
            Double pjYsWblso = 0.0;//外包路送（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsWblso())){
                pjYsWblso = Double.valueOf(annPlanProjectEO.getPjYsWblso());
                flag = true;
            }
            Double pjYsWblsh = 0.0;//外包路试（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsWblsh())){
                pjYsWblsh = Double.valueOf(annPlanProjectEO.getPjYsWblsh());
                flag = true;
            }
            Double pjYsQtQtfy = 0.0;//其他-其他费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsQtQtfy())){
                pjYsQtQtfy = Double.valueOf(annPlanProjectEO.getPjYsQtQtfy());
                flag = true;
            }
            Double pjYsBzQtfy = 0.0;//备注-其他费用（预算）
            if (StringUtils.isNotEmpty(annPlanProjectEO.getPjYsBzQtfy())){
                pjYsBzQtfy = Double.valueOf(annPlanProjectEO.getPjYsBzQtfy());
                flag = true;
            }
            Double sum = 0.0;//所有费用之和
            sum = pjYsSyfy + pjYsDxsyfy + pjYsQjsyfy + pjYsTjb + pjYsZycdfy + pjYsXnyfy + pjYsHbfy + pjYs3cfy + pjYsJtbyh
                    + pjYs3csbf + pjYsRzmdxm + pjYsWtsy + pjYsYyclrl + pjYsYyclaq + pjYsCkrzxm + pjYsSyzbjd + pjYsSyjsfz + pjYsQt1
                    + pjYsBz1 + pjYsQt2 + pjYsBz2 + pjYsJyf + pjYsJqf + pjYsCdf + pjYsWxf + pjYsZxf
                    + pjYsTcf + pjYsKdf + pjYsWblso + pjYsWblsh + pjYsQtQtfy + pjYsBzQtfy;
            String totalBudget = String.valueOf(sum);//费用预算
            //当所有费用为空时设置费用预算为空
            if (!flag){
                totalBudget = "";
            }
            AnnPlanProjectExportVO annPlanProjectExportVO = new AnnPlanProjectExportVO();
            annPlanProjectExportVO.setPjModel(StringUtils.isNotEmpty(annPlanProjectEO.getPjModel())?annPlanProjectEO.getPjModel() : "");//车型
            annPlanProjectExportVO.setPjApprovalForm(StringUtils.isNotEmpty(annPlanProjectEO.getPjApprovalForm())?annPlanProjectEO.getPjApprovalForm() : ""); //立项申请表
            if (StringUtils.isNotEmpty(plSource)){
                List<DicTypeEO> announcementTest = dicTypeEOService.getDicTypeByDictionaryCode("announcementTest");
                for (DicTypeEO dic : announcementTest) {
                    if (plSource.equals(dic.getDicTypeCode())){
                        plSource = dic.getDicTypeName();
                    }
                }
            }else{
                plSource = "";
            }
            annPlanProjectExportVO.setPlSource(plSource); //申报来源
            annPlanProjectExportVO.setPlNo(StringUtils.isNotEmpty(plNo)?plNo : ""); //申报来源编号
            annPlanProjectExportVO.setBudget(totalBudget); //预算
            annPlanProjectExportVOList.add(annPlanProjectExportVO);
        }
        dataMap.put("infoList",annPlanProjectExportVOList);
        StringBuilder path = new StringBuilder();
        path.append(DateUtils.dateToString(new Date(),
                "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator));
        String tempPath = this.filepath;
        if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
            tempPath = this.filepath + File.separator;
        }
        String filePath = tempPath + path + delegateDataVO.getPickingCode() + "委托单.doc";
        docUtil.createDoc(dataMap, "AnnPlanDoc",
                filePath);
        AnnPlanAttachEO annPlanAttachEO = new AnnPlanAttachEO();
        annPlanAttachEO.setId(UUID.randomUUID());
        annPlanAttachEO.setAnnPlId(delegateDataVO.getPlId());//计划id
        annPlanAttachEO.setAnnFileName(delegateDataVO.getPickingCode() + "委托单.doc");
        annPlanAttachEO.setAnnAbsolutePath(filePath);
        annPlanAttachEO.setAnnRelativePath(path + delegateDataVO.getPickingCode() + "委托单.doc");
        annPlanAttachEO.setAnnCreateBy(UserUtils.getUserId());
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4);
        annPlanAttachEO.setAnnCreateTime(date);
        annPlanAttachEOService.insert(annPlanAttachEO);//生成委托记录插入计划委托表中
        String msg = "委托单生成成功!";
        return msg;
    }

    public String getSupplierNameByID(String supplierID) {
        return dao.getSupplierNameByID(supplierID);
    }
}
