package com.adc.da.project.service;

import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.pc_drive.vo.PlanMileage;
import com.adc.da.pc_trust.dao.TrialTaskEODao;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.project.entity.ItemsDetailsEO;
import com.adc.da.project.page.TrialProjectEOPage;
import com.adc.da.project.vo.TrialProjectCarVO;
import com.adc.da.supplier.dao.AbilityEODao;
import com.adc.da.sys.dao.*;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.*;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;

import javax.servlet.http.HttpServletRequest;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.project.dao.TrialProjectEODao;
import com.adc.da.project.entity.TrialProjectEO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <br>
 * <b>功能：</b>SUP_TRIAL_PROJECT TrialProjectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("trialProjectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialProjectEOService extends BaseService<TrialProjectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialProjectEOService.class);

    @Value("${mail.address}")
    private String mailAddress;

    @Autowired
    private TrialProjectEODao dao;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private ItemsDetailsEOService itemsDetailsEOService;

    @Autowired
    private TsAttachmentEODao tsDao;

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private AbilityEODao abilityEODao;

    @Autowired
    private OrgEODao orgEODao;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private PrimaryGenerater primaryGenerater;
    
    @Autowired
    private TrialTaskEODao trialTaskEODao;
    

    public TrialProjectEODao getDao() {
        return dao;
    }

    /**
     * 逻辑删除
     *
     * @param id
     */
    public void deleteFlag(String id) {
        dao.deleteFlag(id);
    }


    /**
     * 分页数据
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public TrialProjectEOPage pageSet(TrialProjectEOPage page, String searchPhrase) {
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<String>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
        return page;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<TrialProjectEO> queryByPage(TrialProjectEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page.setCreateById(UserUtils.getUserId());
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 供应商分页查询
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<TrialProjectEO> queryByPageForSup(TrialProjectEOPage page, String searchPhrase) throws Exception {
        UserEO userEO = UserUtils.getUser();
        if (StringUtils.isEmpty(userEO)) {
            throw new Exception("用户未登陆，请登录！");
        }
        //如果进入此接口，且不是供应商的人员则supid匹配不上，为了不让他们看到数据
        //将supid设置为不存在的数据
        page.setSupid("XXX");
        //组织机构编码
        String orgCode = orgEODao.findOrgByUsId(userEO.getUsid());
        //供应商id
        if(StringUtils.isNotEmpty(orgCode)) {
            String supId = abilityEODao.findSupByCode(orgCode);
            //添加条件
            if(StringUtils.isNotEmpty(supId)) {
                page.setSupid(supId);
            }
        }
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 变更委托状态
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, String status) {
        dao.updateStatus(id, status);
    }

    /**
     * 保存实体
     *
     * @param trialProjectEO
     */
    public synchronized void saveTrialProjectEO(TrialProjectEO trialProjectEO) throws Exception {
        //判断流水号是否已被占用
        String codeNum = primaryGenerater.findTrustCode();
        if (!codeNum.equals(trialProjectEO.getTrustcode())) {
            trialProjectEO.setTrustcode(codeNum);
        }
        //发起人、发起时间
        String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
        trialProjectEO.setCreateDate(currDate);
        trialProjectEO.setCreateById(UserUtils.getUserId());
        //设置删除状态为未删除
        trialProjectEO.setDelFlag("0");
        //委托为草稿状态
        trialProjectEO.setStatus("-1");
        dao.insertSelective(trialProjectEO);
        //判断检验项目是否为空
        if (CollectionUtils.isNotEmpty(trialProjectEO.getItemsDetailsEOList())) {
            for (ItemsDetailsEO itemsDetailsEO : trialProjectEO.getItemsDetailsEOList()) {
                itemsDetailsEO.setSupTrialId(trialProjectEO.getId());
                itemsDetailsEOService.insertSelective(itemsDetailsEO);
                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
                
                //任务开始时间
                Date startDate = format1.parse(itemsDetailsEO.getTestStartDate());
                if(StringUtils.isNotEmpty(trialProjectEO.getTaskstartdate())) {
                	Date startTrialDate = format1.parse(trialProjectEO.getTaskstartdate());
                	if(startTrialDate.before(startDate)) {
                		trialProjectEO.setTaskstartdate(trialProjectEO.getTaskstartdate());
                	}else {
                		trialProjectEO.setTaskstartdate(itemsDetailsEO.getTestStartDate());
                	}
                }else {
                	trialProjectEO.setTaskstartdate(itemsDetailsEO.getTestStartDate());
                }
                
                //任务结束时间
                Date endDate = format1.parse(itemsDetailsEO.getTestEndDate());
                if(StringUtils.isNotEmpty(trialProjectEO.getTaskenddate())) {
                	Date endTrialDate = format1.parse(trialProjectEO.getTaskenddate());
                	if(endTrialDate.before(endDate)) {
                		trialProjectEO.setTaskenddate(itemsDetailsEO.getTestEndDate());
                	}else {
                		trialProjectEO.setTaskenddate(trialProjectEO.getTaskenddate());
                	}
                }else {
                	trialProjectEO.setTaskenddate(itemsDetailsEO.getTestEndDate());
                }
            }
        }
        dao.updateByPrimaryKeySelective(trialProjectEO);
        //更新流水号
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SUP_CODE_NUM);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
    }

    /**
     * 编辑
     * @param trialProjectEO
     * @return
     * @throws Exception
     */
    public int updateByPrimaryKeySelective(TrialProjectEO trialProjectEO) throws Exception {
        //删除子项目
        itemsDetailsEOService.deleteByTrialId(trialProjectEO.getId());
        List<ItemsDetailsEO> itemsDetailsEOList = trialProjectEO.getItemsDetailsEOList();
        //新建子项目
        for (ItemsDetailsEO itemsDetailsEO : itemsDetailsEOList) {
            itemsDetailsEO.setSupTrialId(trialProjectEO.getId());
            itemsDetailsEOService.insertSelective(itemsDetailsEO);
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
            
          //任务开始时间
            Date startDate = format1.parse(itemsDetailsEO.getTestStartDate());
            if(StringUtils.isNotEmpty(trialProjectEO.getTaskstartdate())) {
            	Date startTrialDate = format1.parse(trialProjectEO.getTaskstartdate());
            	if(startTrialDate.before(startDate)) {
            		trialProjectEO.setTaskstartdate(trialProjectEO.getTaskstartdate());
            	}else {
            		trialProjectEO.setTaskstartdate(itemsDetailsEO.getTestStartDate());
            	}
            }else {
            	trialProjectEO.setTaskstartdate(itemsDetailsEO.getTestStartDate());
            }
            
            //任务结束时间
            Date endDate = format1.parse(itemsDetailsEO.getTestEndDate());
            if(StringUtils.isNotEmpty(trialProjectEO.getTaskstartdate()) && StringUtils.isNotEmpty(trialProjectEO.getTaskenddate())) {
            	Date endTrialDate = format1.parse(trialProjectEO.getTaskenddate());
            	if(endTrialDate.before(endDate)) {
            		trialProjectEO.setTaskenddate(trialProjectEO.getTaskenddate());
            	}else {
            		trialProjectEO.setTaskenddate(itemsDetailsEO.getTestEndDate());
            	}
            }else {
            	trialProjectEO.setTaskenddate(itemsDetailsEO.getTestEndDate());
            }
            
        }
        return dao.updateByPrimaryKeySelective(trialProjectEO);
    }

    /**
     * 给下一待办人发送消息通知
     *
     * @param ids
     * @throws IOException
     */
    public void sendRemindToIds(String[] ids, String businessId) throws Exception {
        if (StringUtils.isNotEmpty(ids)) {
            List<UserEO> userEOList = userEOService.findUserByIds(ids);
            BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(businessId);
            //供应商委托
            TrialProjectEO trialProjectEO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
            //标题
            String title = "[供应商项目委托流程" + trialProjectEO.getTrustcode() + "]";
            MessageEO messageEO;
            String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
            for (UserEO userEO : userEOList) {
                //消息通知
                messageEO = new MessageEO(UUID.randomUUID(), "0", baseBusEO.getBusinessType(), currTime, UserUtils.getUser().getUsname(), title, userEO.getUsid(), baseBusEO.getBusinessId());
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
                            userEO.getEmail(), "供应商审批流程待办",
                            "<a href='"+mailAddress+token+"'>"+title+"</a> ",token
                    ));
                }
            }

        }
    }

    /**
     * 启动流程
     *
     * @param trialProjectEO
     * @throws Exception
     */
    public void startProcess(TrialProjectEO trialProjectEO) throws Exception {
        //更新实体
        trialProjectEO.setStatus("3");//审批中
        updateByPrimaryKeySelective(trialProjectEO);
        String pvOrcv = "";
        // 1:乘用车   0：商用车
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if ("1".equals(flag)) {
            pvOrcv = ConstantUtils.PV_SUP_BUSINESS_TYPE;
        } else if ("0".equals(flag)) {
            pvOrcv = ConstantUtils.CV_SUP_BUSINESS_TYPE;
        }
        String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
        //业务主表
        BaseBusEO baseBusEO = new BaseBusEO(
                trialProjectEO.getId(), pvOrcv, UserUtils.getUserId(),
                currTime, "供应商项目委托[" + trialProjectEO.getTrustcode() + "]流程启动");
        baseBusEODao.insertBaseBus(baseBusEO);
        ProcessInstanceCreateRequestVO processInstanceCreateRequestVO = new ProcessInstanceCreateRequestVO();
        processInstanceCreateRequestVO.setInitiator(UserUtils.getUserId());
        processInstanceCreateRequestVO.setBusinessKey(baseBusEO.getId());
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvOrcv);
        processInstanceCreateRequestVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
        rv.setName("specialOrgId");
        rv.setValue(orgId);
        variables.add(rv);
        processInstanceCreateRequestVO.setVariables(variables);
        //启动流程
        ActivitiProcessInstanceVO activitiProcessInstanceVO = activitiProcessService.startProcess(processInstanceCreateRequestVO);
        //流程实例ID
        String ids = baseBusEODao.fingNextUserId(activitiProcessInstanceVO.getProcessInstanceId());
        //当前待办人ids
        if (StringUtils.isNotEmpty(ids)) {
            try {
                //发送消息、工作日历、邮件
                sendRemindToIds(ids.split(","), baseBusEO.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 审批流程
     *
     * @param request
     * @param
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO) throws Exception {
        //审批意见
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //变更业务状态审批中
//        String status = "3";
        //退回
        if ("rollback".equals(approveCode)) {
            String status = "4";
            dao.updateStatus(baseBusEO.getBusinessId(), status);
        }
        //更新流程状态
//        dao.updateStatus(baseBusEO.getBusinessId(), status);
        //封装流程VO
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
        if (StringUtils.isNotEmpty(ids)) {
            try {
                //发送消息通知、工作日历、邮件
                sendRemindToIds(ids.split(","), requestEO.getBaseBusId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 路试路送完成反馈启动流程
     * @param trialProjectEO
     * @throws Exception
     */
    public void startProcessFeedBack(TrialProjectEO trialProjectEO) throws Exception {
        //更新实体
        trialProjectEO.setStatus("31");//审批中
        dao.updateByPrimaryKeySelective(trialProjectEO);
        String pvOrcv = "";
        // 1:乘用车   0：商用车
//        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        UserEO curUser = userEOService.getUserWithRoles(trialProjectEO.getCreateById());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if ("1".equals(flag)) {
            pvOrcv = ConstantUtils.PV_SUP_ROADTEST_Feedback;
        } else if ("0".equals(flag)) {
            pvOrcv = ConstantUtils.CV_SUP_ROADTEST_Feedback;
        }
        String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
        //业务主表
        BaseBusEO baseBusEO = new BaseBusEO(
//                trialProjectEO.getId(), pvOrcv, UserUtils.getUserId(),
                trialProjectEO.getId(), pvOrcv, trialProjectEO.getCreateById(),
                currTime, "供应商项目委托[" + trialProjectEO.getTrustcode() + "]完成反馈流程启动");
        baseBusEODao.insertBaseBus(baseBusEO);
        ProcessInstanceCreateRequestVO processInstanceCreateRequestVO = new ProcessInstanceCreateRequestVO();
//        processInstanceCreateRequestVO.setInitiator(UserUtils.getUserId());
        processInstanceCreateRequestVO.setInitiator(trialProjectEO.getCreateById());
        processInstanceCreateRequestVO.setBusinessKey(baseBusEO.getId());
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvOrcv);
        processInstanceCreateRequestVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
//        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        String orgId = userEODao.getOrgIdByUserId(trialProjectEO.getCreateById());
        RestVariable rv = new RestVariable();
        rv.setName("specialOrgId");
        rv.setValue(orgId);
        variables.add(rv);
        processInstanceCreateRequestVO.setVariables(variables);
        //启动流程
        ActivitiProcessInstanceVO activitiProcessInstanceVO = activitiProcessService.startProcess(processInstanceCreateRequestVO);
        //流程实例ID
        String ids = baseBusEODao.fingNextUserId(activitiProcessInstanceVO.getProcessInstanceId());
        //当前待办人ids
        if (StringUtils.isNotEmpty(ids)) {
            try {
                //发送消息、工作日历、邮件
                sendRemindToIds(ids.split(","), baseBusEO.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    /**
     * 判断流程是否结束，结束的话，变更业务状态
     * @param procId
     * @param trialProjectEOId
     */
    public void isFinishied(String procId, String trialProjectEOId) {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (StringUtils.isNotEmpty(trialProjectEOId)) {
                //路试路送申请已审批
                dao.updateStatus(trialProjectEOId, "0");
            }
        }
    }
    public void isFinishiedFeedBack(String procId, String trialProjectEOId) {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (StringUtils.isNotEmpty(trialProjectEOId)) {
                //路试路送完成反馈申请已审批
                dao.updateStatus(trialProjectEOId, "7");
            }
        }
    }


        /**
         * 供应商完成反馈（需求介入流程，方法废弃）
         *
         * @param trialProjectEO
         */
        public void feedBack (TrialProjectEO trialProjectEO){
            //状态变更为完成反馈
            trialProjectEO.setStatus("7");
            dao.updateByPrimaryKeySelective(trialProjectEO);
        }

        /**
         * 供应商确认
         *
         * @param id
         * @param status
         * @param remark
         */
        public void supConfirm (String id, String status, String remark,String operationId,String operationName) throws Exception {
            TrialProjectEO trialProjectEO = dao.selectByPrimaryKey(id);
            //状态
            trialProjectEO.setStatus(status);
            //确认人
            trialProjectEO.setConfirmPerson(UserUtils.getUser().getUsname());
            trialProjectEO.setOperationId(operationId);
            trialProjectEO.setOperationName(operationName);
            //备注
            trialProjectEO.setRemark(remark);
            dao.updateByPrimaryKeySelective(trialProjectEO);
            //反向更新司机ids
            if(StringUtils.isNotEmpty(trialProjectEO.getPcId())) {
            	//获取试验任务
            	TrialTaskEO	trialTask = trialTaskEODao.selectByPrimaryKey(trialProjectEO.getPcId());
            	trialTask.setSupPersonIds(operationId);
            	trialTaskEODao.updateByPrimaryKeySelective(trialTask);
            }
        }

        /**
         * 保存附件
         *
         * @param file
         * @return
         */
        public ResponseMessage saveAT (MultipartFile file) throws IOException {
            String fileName = file.getOriginalFilename();
            String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
            String extension = FileUtil.getFileExtension(file.getOriginalFilename());
            String fileSize = file.getSize() / 1024 + "KB";
            //文件流
            InputStream is = file.getInputStream();
            //返回路径
            String path = this.iFileStore.storeFile(is, extension, "");
            //附件表
            TsAttachmentEO tsAttachmentEO = new TsAttachmentEO();
            tsAttachmentEO.setAttachmentName(fileName);
            tsAttachmentEO.setAttachmentSize(fileSize);
            tsAttachmentEO.setAttachmentType(extension);
            tsAttachmentEO.setCreateTime(date);
            tsAttachmentEO.setCreateBy(UserUtils.getUserId());
            tsAttachmentEO.setSavePath(path);
            tsAttachmentEO.setDelFlag(0);
            tsDao.insertSelective(tsAttachmentEO);
            return Result.success("0", "上传成功！", new FileField(fileName, path));
        }

    /**
     * 根据车型和底盘号获取路送路试委托单
     * @param carId
     * @return
     */
    public TrialProjectEO getTrialProjectEOByCarTypeAndChassisCode(String carId) {
        return dao.getTrialProjectEOByCarTypeAndChassisCode(carId);
    }

    public List<TrialProjectCarVO> selectCarType(String pcId) {
        return dao.selectCarTypeByPcId(pcId);
    }

    public List<TrialProjectEO> getTrialProjectEOByCarIdAndPcId(String carId, String pcId) {
        return getDao().getTrialProjectEOByCarIdAndPcId(carId,pcId);
    }

    /**
         * 文件内部类用于返回文件名，路径
         */
        class FileField {
            String name;
            String path;

            public FileField(String name, String path) {
                this.name = name;
                this.path = path;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }

        /**
         * 获取basebusID
         *
         * @param id
         * @return
         */
        public String procImage (String id) throws Exception {
            List<BaseBusEO> baseBusEOList = baseBusEODao.selectByBusinessId(id);
            if (CollectionUtils.isNotEmpty(baseBusEOList)) {
                return baseBusEOList.get(0).getId();
            }
            throw new Exception("ERROR : 找不到流程信息");
        }

    /**
     * 查询路试，路送计划里程
     * @return
     * @param carId
     */
//    public PlanMileage getPlanMileage(String carId) {
//        TrialProjectEO trialProjectEO = getTrialProjectEOByCarTypeAndChassisCode(carId);
//        PlanMileage planMileage = new PlanMileage();
//        Double road = 0.0;//路送计划里程
//        Double roadTest = 0.0;//路试计划里程
//        if(trialProjectEO != null){
//            //拿出所有的路送和路试
//            List<ItemsDetailsEO> itemsDetailsEOList = trialProjectEO.getItemsDetailsEOList();
//            if(!CollectionUtils.isEmpty(itemsDetailsEOList)){
//                for (ItemsDetailsEO itemsDetailsEO : itemsDetailsEOList) {
//                    if("CarType,LineRoad".equals(itemsDetailsEO.getTrustType()) && !StringUtils.isEmpty(itemsDetailsEO.getPlanMileage())){
//                        road += Double.parseDouble(itemsDetailsEO.getPlanMileage());
//                    }
//                    if("RoadTestCarType,LineRoad".equals(itemsDetailsEO.getTrustType()) && !StringUtils.isEmpty(itemsDetailsEO.getPlanMileage())){
//                        roadTest += Double.parseDouble(itemsDetailsEO.getPlanMileage());
//                    }
//
//                }
//                planMileage.setRoadMileage(road);
//                planMileage.setRoadTestMileage(roadTest);
//                planMileage.setOperationId(trialProjectEO.getOperationId());
//                planMileage.setHorsePower(trialProjectEO.getTower());
//                planMileage.setTaskCode(trialProjectEO.getTaskbookcode());
//                planMileage.setEntrustRelated(trialProjectEO.getSupname());
//
//            }
//
//        }
//        return planMileage;
//    }
    public PlanMileage getPlanMileages(String carId,String taskOrPlan,TrialProjectEO trialProjectEO){
        PlanMileage planMileage=new PlanMileage();
        Double road = 0.0;//路送计划里程
        Double roadTest = 0.0;//路试计划里程
        if(trialProjectEO != null){
            //拿出所有的路送和路试
            List<ItemsDetailsEO> itemsDetailsEOList = trialProjectEO.getItemsDetailsEOList();
            if(!CollectionUtils.isEmpty(itemsDetailsEOList)){
                for (ItemsDetailsEO itemsDetailsEO : itemsDetailsEOList) {
                    if("CarType,LineRoad".equals(itemsDetailsEO.getTrustType()) && !StringUtils.isEmpty(itemsDetailsEO.getPlanMileage())){
                        road += Double.parseDouble(itemsDetailsEO.getPlanMileage());
                    }
                    if("RoadTestCarType,LineRoad".equals(itemsDetailsEO.getTrustType()) && !StringUtils.isEmpty(itemsDetailsEO.getPlanMileage())){
                        roadTest += Double.parseDouble(itemsDetailsEO.getPlanMileage());
                    }

                }
                planMileage.setRoadMileage(road);
                planMileage.setRoadTestMileage(roadTest);
                planMileage.setOperationId(trialProjectEO.getOperationId());
                planMileage.setHorsePower(trialProjectEO.getTower());
                planMileage.setTaskCode(trialProjectEO.getTaskbookcode());
                planMileage.setEntrustRelated(trialProjectEO.getSupname());

            }
        }
        return planMileage;
    }

    }
