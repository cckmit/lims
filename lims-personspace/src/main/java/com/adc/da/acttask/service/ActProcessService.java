package com.adc.da.acttask.service;

import com.adc.da.calender.activeMQ.ProducerMQ;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ActProcessService {


    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ActProcessService.class);

    @Value("${mail.address}")
    private String mailAddress;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private ProducerMQ producerMQ;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private EmailConfig emailConfig;
    
    @Autowired
    private UserEODao userEODao;

    String currTime;

    public String startProcess(String businessId, String procDefKey, String title) throws Exception {
        try {
            currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            //业务主表
            BaseBusEO baseBusEO = new BaseBusEO(
                    businessId, procDefKey, UserUtils.getUserId(),
                    currTime, title);
            baseBusEODao.insertBaseBus(baseBusEO);
            ProcessInstanceCreateRequestVO processInstanceCreateRequestVO = new ProcessInstanceCreateRequestVO();
            processInstanceCreateRequestVO.setInitiator(UserUtils.getUserId());
            processInstanceCreateRequestVO.setBusinessKey(baseBusEO.getId());
            //根据数据字典获取流程定义id
            DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE, procDefKey);
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
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(ids)) {
                //发送消息、工作日历、邮件
                sendRemindToIds(ids.split(","), baseBusEO);
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return "0";
    }

    /**
     * 审批流程
     * @param requestEO
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO, Object dao) throws Exception {
        //反射DAO
        Class clazz = Class.forName(dao.getClass().getName());
        Method method = clazz.getMethod("changeStatus",java.lang.String.class,java.lang.String.class);
        //用来回退提交，将草稿状态变更为审批中
        method.invoke(dao, baseBusEO.getBusinessId(), "1");
        //审批意见
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
             method.invoke(dao, baseBusEO.getBusinessId(), "4");
        }
        //撤回
        if ("reback".equals(approveCode)) {
            method.invoke(clazz.newInstance(), baseBusEO.getBusinessId(),"6");
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
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
                sendRemindToIds(ids.split(","), baseBusEO);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 给下一待办人发送消息通知
     *
     * @param ids
     * @throws IOException
     */
    public void sendRemindToIds(String[] ids, BaseBusEO baseBusEO) throws Exception {
        String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        if (com.adc.da.util.utils.StringUtils.isNotEmpty(ids)) {
            List<UserEO> userEOList = userEOService.findUserByIds(ids);
            MessageEO messageEO;
            for (UserEO userEO : userEOList) {
                //消息通知
                messageEO = new MessageEO(UUID.randomUUID(), "0", baseBusEO.getBusinessType(), currTime, UserUtils.getUser().getUsname(), baseBusEO.getTitle(), userEO.getUsid(), baseBusEO.getBusinessId());
                //发送消息通知
                messageEOService.sendMessage(messageEO);
                //批量保存工作日历
                personCalenderEOService.insertSelective(new PersonCalenderEO(UUID.randomUUID(), currTime, baseBusEO.getTitle(), userEO.getUsid(), "流程待办"));
                //批量保存消息通知
                messageEOService.insertSelective(messageEO);
                //生成邮件的为由标识
                String token = UUID.randomUUID();
                ConstantUtils.DELAYMAILMAP.put(token, userEO.getUsid());
                //发送邮件
                try {
                    if (StringUtils.isNotEmpty(userEO.getEmail())) {
                        emailConfig.sendMailHtml(new MailUtils(
                                userEO.getEmail(), "东风柳汽Lims系统流程待办任务",
                                "<a href='" + mailAddress + token + "'>" + baseBusEO.getTitle() + "</a> ", token
                        ));
                    }
                }catch(Exception e){
                    logger.error("-1",e.getStackTrace());
                }
            }

        }
    }

}
