package com.adc.da.pc_outtrust.service;

import com.adc.da.acttask.service.ActProcessService;
import com.adc.da.base.service.BaseService;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_drive.vo.AbilityVO;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_outsource.common.CostSettlementService;
import com.adc.da.pc_outtrust.dao.PcOutsourceEntrustEODao;
import com.adc.da.pc_outtrust.entity.PcOutsourceEntrustEO;
import com.adc.da.pc_report.service.PcReportEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <br>
 * <b>功能：</b>PC_OUTSOURCE_ENTRUST PcOutsourceEntrustEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcOutsourceEntrustEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcOutsourceEntrustEOService extends BaseService<PcOutsourceEntrustEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcOutsourceEntrustEOService.class);

    @Autowired
    private PcOutsourceEntrustEODao dao;

    public PcOutsourceEntrustEODao getDao() {
        return dao;
    }

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private EmailConfig emailConfig;

    @Value("${mail.address}")
    private String mailAddress;

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private PrimaryGenerater primaryGenerater;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private TrialTaskEOService trialTaskEOService;


    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private PcReportEOService pcReportEOService;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
    @Autowired
    private CostSettlementService costSettlementService;
    /**
     * xinzeng
     *
     * @param pcOutsourceEntrustEO
     * @return
     * @throws Exception
     */
    public synchronized int insertSelective(PcOutsourceEntrustEO pcOutsourceEntrustEO, String status) throws Exception {
        String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
        pcOutsourceEntrustEO.setEntrustStatus(status);
        pcOutsourceEntrustEO.setCreateBy(UserUtils.getUserId());
        pcOutsourceEntrustEO.setCreateByName(UserUtils.getUser().getUsname());
        pcOutsourceEntrustEO.setCreateTime(currDate);
        String trialTaskId = pcOutsourceEntrustEO.getTrialTaskId();
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
        String taskNumber = trialTaskEO.getTaskNumber();
        //方便试验申请变更的查询
        pcOutsourceEntrustEO.setTrialCode(taskNumber);
        //判断编号是否已存在
        String outTrustCode = primaryGenerater.queryOutTrustCode(ConstantUtils.OUT_TRUST_NO);
        if (!StringUtils.equals(outTrustCode, pcOutsourceEntrustEO.getEntrustCode())) {
            pcOutsourceEntrustEO.setEntrustStatus(outTrustCode);
        }
        //更新流水号
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.OUT_TRUST_NO);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
        return dao.insertSelective(pcOutsourceEntrustEO);
    }

    /**
     * 获取流水号
     *
     * @return
     */
    public String queryOutTrustCode() {
        return primaryGenerater.queryOutTrustCode(ConstantUtils.OUT_TRUST_NO);
    }

    /**
     * 编辑
     *
     * @param pcOutsourceEntrustEO
     * @return
     */
    public int updateByPrimaryKeySelective(PcOutsourceEntrustEO pcOutsourceEntrustEO) {
        String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
        pcOutsourceEntrustEO.setUpdateBy(UserUtils.getUserId());
        pcOutsourceEntrustEO.setUpdateTime(currDate);
        return dao.updateByPrimaryKeySelective(pcOutsourceEntrustEO);
    }

    /**
     * 启动流程
     *
     * @param pcOutsourceEntrustEO
     * @throws Exception
     */
    public String startProcess(PcOutsourceEntrustEO pcOutsourceEntrustEO) throws Exception {
        String trialTaskId = pcOutsourceEntrustEO.getTrialTaskId();
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
        String taskNumber = trialTaskEO.getTaskNumber();
        //方便试验申请变更的查询
        pcOutsourceEntrustEO.setTrialCode(taskNumber);
        //将流程状态设置为审批中
        pcOutsourceEntrustEO.setEntrustStatus("1");
        //判断是新增提交还是编辑提交
        if (StringUtils.isEmpty(pcOutsourceEntrustEO.getId())) {
            insertSelective(pcOutsourceEntrustEO, "1");
        } else {
            updateByPrimaryKeySelective(pcOutsourceEntrustEO);
        }
        String pvOrcv = "";
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if("1".equals(flag)){
            //PV
            pvOrcv = ConstantUtils.PV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE;
        }else{
            //CV
            pvOrcv = ConstantUtils.CV_PC_OUTSOURCE_ENTRUST_BUSINESS_TYPE;
        }
        return actProcessService.startProcess(pcOutsourceEntrustEO.getId(), pvOrcv, "委外试验委托单[" + pcOutsourceEntrustEO.getEntrustCode() + "]审批流程");
    }

    /**
     * 审批流程
     *
     * @param requestEO
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO) throws Exception {
        //审批意见
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
            dao.updateStatus(baseBusEO.getBusinessId(), "4");
        }
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        activitiTaskRequestVO.setComment(requestEO.getComment());
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if ("0".equals(requestEO.getType())) {
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        } else if ("1".equals(requestEO.getType())) {
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //获取下一节点人
        String ids = baseBusEODao.fingNextUserId(requestEO.getProcId());
        if (com.adc.da.util.utils.StringUtils.isNotEmpty(ids)) {
            try {
                //发送消息通知、工作日历、邮件
                sendRemindToIds(ids.split(","), requestEO.getBaseBusId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 判断流程是否结束，结束的话，变更业务状态
     *
     * @param procId
     * @param trialProjectEOId
     */
    public void isFinishied(String procId, String trialProjectEOId) throws Exception {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(trialProjectEOId)) {
                //已审批
                dao.updateStatus(trialProjectEOId, "5");

                //走整合后的费用同步
                Map<String,Object> params=new HashMap<>();
                params.put("trialProjectEOId",trialProjectEOId);
                costSettlementService.saveCostSummary("pcOutsourceEntrustList",null,params);
                //同步费用
//                PcOutsourceEntrustEO pcOutsourceEntrustEO = dao.selectByPrimaryKey(trialProjectEOId);
//                TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(pcOutsourceEntrustEO.getTrialTaskId());
//                //如果存在供应商，则将总费用累加
//                if(StringUtils.isNotEmpty(pcOutsourceEntrustEO.getSupId())){
//                    CostSummaryEO costSummary = costSummaryEOService.getCostSummaryEOBySupId(pcOutsourceEntrustEO.getSupId(),trialTaskEO.getId(),"pcOutsourceEntrustList");
//                    if(costSummary != null){
//                        double cost = Double.parseDouble(costSummary.getTotalCost()) + Double.parseDouble(pcOutsourceEntrustEO.getPlanCost());
//                        costSummary.setTotalCost(String.valueOf(cost));
//                        costSummaryEOService.updateByPrimaryKeySelective(costSummary);
//                        return;
//                    }
//                    AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(pcOutsourceEntrustEO.getSupId());
//                    CostSummaryEO costSummaryEO = new CostSummaryEO();
//                    String id = UUID.randomUUID();
//                    costSummaryEO.setId(id);
//                    costSummaryEO.setDelFlag("0");
//                    costSummaryEO.setTrialTaskId(trialTaskEO.getId());
//                    costSummaryEO.setTaskName(pcOutsourceEntrustEO.getTrialTaskCode());
//                    costSummaryEO.setEngineer(pcOutsourceEntrustEO.getCreateByName());
//                    costSummaryEO.setCostType("2");
//                    costSummaryEO.setTrustNo(pcOutsourceEntrustEO.getEntrustCode());
//                    costSummaryEO.setItemNo(pcOutsourceEntrustEO.getOpCode());
//                    costSummaryEO.setPaymentStatus("0");
//                    //供应商信息
//                    costSummaryEO.setSupId(abilityVO.getId());
//                    costSummaryEO.setSupName(abilityVO.getSupName());
//                    costSummaryEO.setSupCode(abilityVO.getSupCode());
//                    String supType = abilityVO.getSupType();
//                    costSummaryEO.setSupType(supType);
//                    String supTypeName = "";
//                    //供应商类型如果是场地的同步场地费用到费用统计中
//                    if (StringUtils.isNotEmpty(supType)){
//                    	DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("supplierType", supType);
//                    	supTypeName = dic != null ? dic.getDicTypeName() : "";
//                    }
//                    if (StringUtils.isNotEmpty(supTypeName) && "场地供应商".equals(supTypeName.trim())){
//                    	costSummaryEO.setCostType("4");
//                    }
//                    costSummaryEO.setFormKey("pcOutsourceEntrustList");
//                    //设置车辆信息
//                    setCarInfo(costSummaryEO,trialTaskEO);
//                    //不是认证试验才同步费用
//                    if(!"5".equals(trialTaskEO.getTaskType())){
//                    	costSummaryEO.setTotalCost(pcOutsourceEntrustEO.getPlanCost());
//                    	costSummaryEO.setDataOrigin("0");
//                    }else {
//                    	costSummaryEO.setDataOrigin("1");
//                    }
//                    costSummaryEOService.insertSelective(costSummaryEO);
//                }
            }
        }
    }

//    /**
//     * 设置车辆信息
//     * @param costSummaryEO
//     * @param trialTaskEO
//     */
//    private void setCarInfo(CostSummaryEO costSummaryEO, TrialTaskEO trialTaskEO) {
//        StringBuilder carType = new StringBuilder();
//        StringBuilder carIds = new StringBuilder();
//        StringBuilder chassisNo = new StringBuilder();
//        List<TrialItemsEO> trialItemsEOList = trialTaskEO.getTrialItemsEOList();
//        if(!CollectionUtils.isEmpty(trialItemsEOList)){
//            for (TrialItemsEO trialItemsEO : trialItemsEOList) {
//                if(trialItemsEO.getSaCarDataEO() != null){
//                    if(StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO().getCarType())){
//                        carType.append(trialItemsEO.getSaCarDataEO().getCarType()).append(",");
//                    }
//                    if(StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO().getId())){
//                        carIds.append(trialItemsEO.getSaCarDataEO().getId()).append(",");
//                    }
//                    if(StringUtils.isNotEmpty(trialItemsEO.getSaCarDataEO().getChassisNumber())){
//                        chassisNo.append(trialItemsEO.getSaCarDataEO().getChassisNumber()).append(",");
//                    }
//                }
//            }
//            if(StringUtils.isNotEmpty(carType.toString())){
//                carType.toString().substring(0,carType.lastIndexOf(","));
//            }
//            if(StringUtils.isNotEmpty(carIds.toString())){
//                carIds.toString().substring(0,carIds.lastIndexOf(","));
//            }
//            if(StringUtils.isNotEmpty(chassisNo.toString())){
//                chassisNo.toString().substring(0,chassisNo.lastIndexOf(","));
//            }
//        }
//        costSummaryEO.setCarType(carType.toString());
//        costSummaryEO.setCarIds(carIds.toString());
//        costSummaryEO.setChassisNo(chassisNo.toString());
//
//    }

    /**
     * 给下一待办人发送消息通知
     *
     * @param ids
     * @throws IOException
     */
    public void sendRemindToIds(String[] ids, String businessId) throws Exception {
        if (com.adc.da.util.utils.StringUtils.isNotEmpty(ids)) {
            List<UserEO> userEOList = userEOService.findUserByIds(ids);
            BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(businessId);
            //供应商委托
            PcOutsourceEntrustEO pcOutsourceEntrustEO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
            //标题
            String title = "委外试验委托单[" + pcOutsourceEntrustEO.getEntrustCode() + "]审批流程";
            String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
            MessageEO messageEO;
            for (UserEO userEO : userEOList) {
                //消息通知
                messageEO = new MessageEO(UUID.randomUUID(), "0", baseBusEO.getBusinessType(), currTime, UserUtils.getUser().getUsname(), title, userEO.getUsid(), businessId);
                //发送消息通知
                messageEOService.sendMessage(messageEO);
                //批量保存工作日历
                personCalenderEOService.insertSelective(new PersonCalenderEO(UUID.randomUUID(), currTime, title, userEO.getUsid(), "流程待办"));
                //批量保存消息通知
                messageEOService.insertSelective(messageEO);
                //生成邮件的为由标识
                String token = UUID.randomUUID();
                ConstantUtils.DELAYMAILMAP.put(token, userEO.getUsid());
                //发送邮件
                if (StringUtils.isNotEmpty(userEO.getEmail())) {
                    emailConfig.sendMailHtml(new MailUtils(
                            userEO.getEmail(), "委外试验委托单审批流程待办",
                            "<a href='" + mailAddress + token + "'>" + title + "</a> ", token
                    ));
                }
            }

        }
    }

    /**
     * 导出
     *
     * @param pcOutsourceEntrustEO
     */
    public String exportDoc(HttpServletResponse response, HttpServletRequest request, PcOutsourceEntrustEO pcOutsourceEntrustEO) {
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("entrustCode", pcOutsourceEntrustEO.getEntrustCode() == null ? "" : pcOutsourceEntrustEO.getEntrustCode());
        dataMap.put("entrustProject", pcOutsourceEntrustEO.getEntrustProject() == null ? "" : pcOutsourceEntrustEO.getEntrustProject());
        dataMap.put("createByName", pcOutsourceEntrustEO.getCreateByName() == null ? "" : pcOutsourceEntrustEO.getCreateByName());
        dataMap.put("supManagerPhone", pcOutsourceEntrustEO.getSupManagerPhone() == null ? "" : pcOutsourceEntrustEO.getSupManagerPhone());
        dataMap.put("trialTaskCode", pcOutsourceEntrustEO.getTrialTaskCode() == null ? "" : pcOutsourceEntrustEO.getTrialTaskCode());
        dataMap.put("taskCode", pcOutsourceEntrustEO.getTaskCode() == null ? "" : pcOutsourceEntrustEO.getTaskCode());
        dataMap.put("opCode", pcOutsourceEntrustEO.getOpCode() == null ? "" : pcOutsourceEntrustEO.getOpCode());
        dataMap.put("supName", pcOutsourceEntrustEO.getSupName() == null ? "" : pcOutsourceEntrustEO.getSupName());
        dataMap.put("planReportFinishDate", pcOutsourceEntrustEO.getPlanReportFinishDate() == null ? "" : pcOutsourceEntrustEO.getPlanReportFinishDate());
        dataMap.put("planCost", pcOutsourceEntrustEO.getPlanCost() == null ? "" : pcOutsourceEntrustEO.getPlanCost());
        dataMap.put("entrustCompanyName", pcOutsourceEntrustEO.getEntrustCompanyName() == null ? "" : pcOutsourceEntrustEO.getEntrustCompanyName());
        dataMap.put("zipCode", pcOutsourceEntrustEO.getZipCode() == null ? "" : pcOutsourceEntrustEO.getZipCode());
        dataMap.put("entrustCompanyAddr", pcOutsourceEntrustEO.getEntrustCompanyAddr() == null ? "" : pcOutsourceEntrustEO.getEntrustCompanyAddr());
        dataMap.put("telPhone", pcOutsourceEntrustEO.getTelPhone() == null ? "" : pcOutsourceEntrustEO.getTelPhone());
        dataMap.put("productName", pcOutsourceEntrustEO.getProductName() == null ? "" : pcOutsourceEntrustEO.getProductName());
        dataMap.put("logo", pcOutsourceEntrustEO.getLogo() == null ? "" : pcOutsourceEntrustEO.getLogo());
        dataMap.put("requireFinishDate", pcOutsourceEntrustEO.getRequireFinishDate() == null ? "" : pcOutsourceEntrustEO.getRequireFinishDate());
        dataMap.put("createByPhone", pcOutsourceEntrustEO.getCreateByPhone() == null ? "" : pcOutsourceEntrustEO.getCreateByPhone());
        dataMap.put("supAddr", pcOutsourceEntrustEO.getSupAddr() == null ? "" : pcOutsourceEntrustEO.getSupAddr());
        dataMap.put("supManagerName", pcOutsourceEntrustEO.getSupManagerName() == null ? "" : pcOutsourceEntrustEO.getSupManagerName());
        //以下是复选框参数，先默认不选
        dataMap.put("publicDoc", "0");
        dataMap.put("epDoc", "0");
        dataMap.put("cccDoc", "0");
        dataMap.put("outDoc", "0");
        dataMap.put("trustDoc", "0");
        dataMap.put("oilDoc", "0");
        dataMap.put("devDoc", "0");
        dataMap.put("othersType", "0");
        dataMap.put("sampling", "0");
        dataMap.put("sendSample", "0");
        dataMap.put("gbStd", "0");
        dataMap.put("industryStd", "0");
        dataMap.put("enterpriseStd", "0");
        dataMap.put("abroadStd", "0");
        dataMap.put("othersStd", "0");
        //委托实验类型：包含类型、样品、标准，逗号分隔
        if (StringUtils.isNotEmpty(pcOutsourceEntrustEO.getEntrustType())) {
            String[] entrustTypeArray = pcOutsourceEntrustEO.getEntrustType().split(",");
            for (String entrust : entrustTypeArray) {
                switch (entrust) {
                    case "1":
                        dataMap.put("publicDoc", "1");
                        continue;
                    case "2":
                        dataMap.put("epDoc", "1");
                        continue;
                    case "3":
                        dataMap.put("cccDoc", "1");
                        continue;
                    case "4":
                        dataMap.put("outDoc", "1");
                        continue;
                    case "5":
                        dataMap.put("trustDoc", "1");
                        continue;
                    case "6":
                        dataMap.put("oilDoc", "1");
                        continue;
                    case "7":
                        dataMap.put("devDoc", "1");
                        continue;
                    case "8":
                        dataMap.put("othersType", "1");
                        continue;
                }
            }
        }
        //来样类型
        if (StringUtils.isNotEmpty(pcOutsourceEntrustEO.getSampleType())) {
            String[] sampleTypeArray = pcOutsourceEntrustEO.getSampleType().split(",");
            for (String sampleType : sampleTypeArray) {
                switch (sampleType) {
                    case "9":
                        dataMap.put("sampling", "1");
                        continue;
                    case "A":
                        dataMap.put("sendSample", "1");
                        continue;
                }
            }
        }
        //检验依据要求
        if (StringUtils.isNotEmpty(pcOutsourceEntrustEO.getAccRequire())) {
            String[] accArray = pcOutsourceEntrustEO.getAccRequire().split(",");
            for (String acc : accArray) {
                switch (acc) {
                    case "B":
                        dataMap.put("gbStd", "1");
                        continue;
                    case "C":
                        dataMap.put("industryStd", "1");
                        continue;
                    case "D":
                        dataMap.put("enterpriseStd", "1");
                        continue;
                    case "E":
                        dataMap.put("abroadStd", "1");
                        continue;
                    case "F":
                        dataMap.put("othersStd", "1");
                        continue;

                }
            }
        }
        dataMap.put("kezhang", "");
        dataMap.put("zhuguan", "");
        //流程审批结束，获取审批人员
        String actProcId = findActProcId(pcOutsourceEntrustEO.getId());
        if (StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
            List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
            dataMap.put("kezhang", list.get(0).getAssigneeName());
            dataMap.put("zhuguan", list.get(1).getAssigneeName());
        }
//      String fileName = "委外试验委托单-" + pcOutsourceEntrustEO.getEntrustCode();
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
//        docUtil.createDoc(dataMap, "PCTrustDoc", response, request,fileName, fileExtend);


        //根据附件id , 获取模板路径
        if(com.adc.da.util.utils.StringUtils.isNotBlank(pcOutsourceEntrustEO.getFileId())) {
            String path = tsAttachmentEODao.selectByPrimaryKey(pcOutsourceEntrustEO.getFileId()).getSavePath();
            return filepath + path;
        }
        String NewPath = "pcOutsourceEntrust/" + pcOutsourceEntrustEO.getTaskCode() + "/" + UUID.randomUUID()+fileExtend;
        File file = new File(filepath+"pcOutsourceEntrust/"+pcOutsourceEntrustEO.getTaskCode());
        if(!file.exists()) file.mkdir();

        docUtil.createDoc(dataMap,"PCTrustDoc",filepath + NewPath);

        if (com.adc.da.util.utils.StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {   //签章
            Map<String, String> pathMap = new HashMap<>();
            pathMap.put("PC_Minister_pic", (String)dataMap.get("kezhang"));
            pathMap.put("PC_SectionChief_pic", (String)dataMap.get("zhuguan"));
            try {
                pcReportEOService.writeWordAndToPDF(pathMap, filepath + NewPath);

                //将填充完数据的报告数据存储至附件
                TsAttachmentEO attachment = new TsAttachmentEO();
                attachment.setAttachmentName(pcOutsourceEntrustEO.getEntrustProject());
                attachment.setId(UUID.randomUUID());
                attachment.setAttachmentType(ConstantUtils.FILE_WORD_DOCX);
                attachment.setSavePath(NewPath);
                attachment.setDelFlag(0);
                attachment.setCreateBy(UserUtils.getUserId());
                attachment.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                attachment.setUpdateBy(UserUtils.getUserId());
                attachment.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                tsAttachmentEODao.insert(attachment);

                pcOutsourceEntrustEO.setFileId(attachment.getId());
                pcOutsourceEntrustEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
                this.updateByPrimaryKeySelective(pcOutsourceEntrustEO);
            }catch (Exception e){
                e.printStackTrace();
                logger.info("委外试验插入签章失败");
            }
        }
        return filepath + NewPath;
    }

    /**
     * 获取basebusId
     *
     * @param id
     * @return
     */
    public String findActProcId(String id) {
        return dao.findActProcId(id);
    }

    /**
     * pc委外立项＆pc可靠性立项获取编号集合
     */
    public List<String> queryCodeList() {
        return dao.queryCodeList();
    }

}
