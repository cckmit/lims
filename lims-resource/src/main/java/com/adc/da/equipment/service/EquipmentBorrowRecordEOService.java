package com.adc.da.equipment.service;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.VO.BorrowRecordVO;
import com.adc.da.equipment.VO.EquipmentBorrowRecordVO;
import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.equipment.dao.EquipmentBorrowRecordEODao;
import com.adc.da.equipment.entity.EquipmentBorrowRecordEO;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT_BORROW_RECORD EquipmentBorrowRecordEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentBorrowRecordEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentBorrowRecordEOService extends BaseService<EquipmentBorrowRecordEO, Void> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentBorrowRecordEOService.class);

    @Autowired
    private EquipmentBorrowRecordEODao dao;
    @Autowired
    private EquipmentEOService equipmentEOService;
    @Autowired
    private UserEOService userEOService;
    @Autowired
    private LimsFileService limsFileService;
    @Autowired
    private DicTypeEODao dicTypeEODao;
    @Autowired
    private ActivitiProcessService activitiProcessService;
    @Autowired
    private BaseBusEODao baseBusEODao;
    @Autowired
    private UserEODao userEODao;
    @Autowired
    private ActivitiTaskService activitiTaskService;

    public EquipmentBorrowRecordEODao getDao() {
        return dao;
    }

    /**
     * 设备借用添加流程
     * @param equipmentBorrowRecordEO
     */
    public void equipmentBorrow(EquipmentBorrowRecordEO equipmentBorrowRecordEO) throws Exception {
        //先插入到借用表中。
        equipmentBorrowRecordEO.setBrwIsbrw("1");//借用流程发起管理还未审批
        equipmentBorrowRecordEO.setProcessState("0");//此数据处于流程中
        equipmentBorrowRecordEO.setId(UUID.randomUUID());
        dao.insertSelective(equipmentBorrowRecordEO);//借用数据先保存
        //修改对应设备的使用状态
        //对应的设备Id
        String eqId = equipmentBorrowRecordEO.getEqId();
        //对应借用人的组织机构
        String org = equipmentEOService.getOrgNameByUserId(equipmentBorrowRecordEO.getBrwBorrowerId());
        //根据设备id，在设备表中改变对应设备的使用状态为 使用--“1”
        equipmentEOService.updateUseStatusById(eqId,"1",equipmentBorrowRecordEO.getBrwBorrowerName(),org);
        //修改设备信息为流程审批中
        EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
        equipmentEO.setEqProcedureState("1");
        equipmentEOService.updateByPrimaryKey(equipmentEO);
        //启动流程
        //保存业务BASEBUS
        String title = "设备借用【"+ equipmentEO.getEqNo() +"】审批流程";
        String baseBusId = limsFileService.saveBaseBus(equipmentBorrowRecordEO.getId(),
                ConstantUtils.Equipment_Borrow_TYPE, title);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE,ConstantUtils.Equipment_Borrow_TYPE);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if(StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            Set<String> userArray = new HashSet<String>(Arrays.asList(splits));
            //循环设置当前待办人的id和name
            List<String> userNames = new ArrayList<>();
            for (String usid : userArray) {
                UserEO user = userEODao.selectByPrimaryKey(usid);
                if(StringUtils.isNotEmpty(user) &&
                        StringUtils.isNotEmpty(user.getUsname())) {
                    userNames.add(user.getUsname());
                }
            }
            //链接到设备借用表单页
            String link = ConstantUtils.Equipment_Borrow_TYPE;
            limsFileService.processSendMessages(splits, title, link, equipmentBorrowRecordEO.getId());
        }
    }
    //借用流程的审批
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取借用信息
        EquipmentBorrowRecordEO equipmentBorrowRecordEO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
        String eqId = equipmentBorrowRecordEO.getEqId();
        EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //设备管理员不同意
        if("rollback".equals(approveCode)){
            //不同意
            //将设备修改为空闲状态
            equipmentEO.setEqUseStatus("0");
            //不同意审批  修改借用表中此数据管理员不同意审批
            equipmentBorrowRecordEO.setBrwIsbrw("1");
            //管理员不同意，流程结束。修改设备信息流程状态为无流程
            equipmentEO.setEqProcedureState("0");
        }else{
            //同意审批  修改借用表中此数据管理员同意审批
            equipmentBorrowRecordEO.setBrwIsbrw("0");
            //修改数据为已借出
            equipmentBorrowRecordEO.setIfLend("0");
            //管理员同意，流程结束。修改设备信息展示归还按钮
            equipmentEO.setEqProcedureState("2");
        }
        equipmentBorrowRecordEO.setProcessState("1");//同意不同意，修改此数据不处于流程中
        dao.updateByBorrowEO(equipmentBorrowRecordEO);
        equipmentEOService.updateByPrimaryKey(equipmentEO);
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if("0".equals(requestEO.getType())){
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        }else if("1".equals(requestEO.getType())){
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(equipmentBorrowRecordEO)) {
                //完成给相关人员发送消息
                String title = "设备借用【"+ equipmentEO.getEqNo() +"】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                                getProcessUsersByTaskId(requestEO.getTaskId()),
                        title, link, equipmentBorrowRecordEO.getId());
            }
        }
    }

    /**
     * 设备归还
     * @param equipmentBorrowRecordEO
     * @param userId
     */
    public ResponseMessage<EquipmentBorrowRecordEO>  equipmentReturn(EquipmentBorrowRecordEO equipmentBorrowRecordEO, String userId) throws Exception {
        //根据归还设备的id，和数据库表中设备归还状态的标记。来唯一确定一条记录
        EquipmentBorrowRecordEO borrowRecordEO = dao.selectBorrowRecordByEqId(equipmentBorrowRecordEO.getEqId());
        //首先判断，该仪器设备是否被归还人借用。也就是必须要符合“谁借谁还的逻辑”
        if(borrowRecordEO.getBrwBorrowerId().trim().equals(userId.trim())){
            //将记录查询在对应的借用归还表里面
            borrowRecordEO.setBrwReturnTime(equipmentBorrowRecordEO.getBrwReturnTime());
            borrowRecordEO.setProcessState("0");//此数据处于流程中
            dao.updateByBorrowEO(borrowRecordEO);
            String eqId = equipmentBorrowRecordEO.getEqId();
            EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
            //修改设备信息为流程审批中
            equipmentEO.setEqProcedureState("1");
            equipmentEOService.updateByPrimaryKey(equipmentEO);
            //归还发起流程
            //保存业务BASEBUS
            String title = "设备归还【"+ equipmentEO.getEqNo() +"】审批流程";
            String baseBusId = limsFileService.saveBaseBus(borrowRecordEO.getId(),
                    ConstantUtils.Equipment_Return_TYPE, title);
            //流程实例保存
            ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
            processInstanceVO.setInitiator(UserUtils.getUserId());
            processInstanceVO.setBusinessKey(baseBusId);
            //根据数据字典获取流程定义id
            DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                    ConstantUtils.PROCESS_CODE,ConstantUtils.Equipment_Return_TYPE);
            processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
            //启动流程
            ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
            //根据流程实例ID,获取流程当前所有办理人
            String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
            //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
            if(StringUtils.isNotEmpty(ids)) {
                String[] splits = ids.split(ConstantUtils.COMMA);
                Set<String> userArray = new HashSet<String>(Arrays.asList(splits));
                //循环设置当前待办人的id和name
                List<String> userNames = new ArrayList<>();
                for (String usid : userArray) {
                    UserEO user = userEODao.selectByPrimaryKey(usid);
                    if (StringUtils.isNotEmpty(user) &&
                            StringUtils.isNotEmpty(user.getUsname())) {
                        userNames.add(user.getUsname());
                    }
                }
                //链接到设备归还表单页
                String link = ConstantUtils.Equipment_Return_TYPE;
                limsFileService.processSendMessages(splits, title, link, equipmentBorrowRecordEO.getId());
            }
        return Result.success("0","归还流程发起成功!");
        }else{
            return Result.error("-1", "归还流程发起失败!当前用户不是该设备借用人！");
        }
    }

    //归还流程的审批
    public void approvalEquipmentReturnProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取借用信息
        EquipmentBorrowRecordEO equipmentBorrowRecordEO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
        String eqId = equipmentBorrowRecordEO.getEqId();
        EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String)variables.get("approve");
        //设备管理员不同意
        if("rollback".equals(approveCode)){
            //不同意---设备使用状态依旧为借用状态，借用数据置空归还时间
            equipmentBorrowRecordEO.setBrwReturnTime("");
            //管理员不同意，流程结束。修改设备信息出现归还按钮
            equipmentEO.setEqProcedureState("2");
        }else{
            //同意审批
            //将设备修改为空闲状态
            equipmentEO.setEqUseStatus("0");
            //管理员同意，流程结束。修改设备信息流程状态为无流程
            equipmentEO.setEqProcedureState("0");
            //修改数据为已归还
            equipmentBorrowRecordEO.setIfLend("1");
        }
        equipmentBorrowRecordEO.setProcessState("1");//同意不同意流程，此数据不处于流程中
        dao.updateByBorrowEO(equipmentBorrowRecordEO);
        equipmentEOService.updateByPrimaryKey(equipmentEO);
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if("0".equals(requestEO.getType())){
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        }else if("1".equals(requestEO.getType())){
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if(baseBusEODao.isFinishied(requestEO.getProcId())==1) {
            if(StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(equipmentBorrowRecordEO)) {
                //完成给相关人员发送消息
                String title = "设备归还【"+ equipmentEO.getEqNo() +"】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                                getProcessUsersByTaskId(requestEO.getTaskId()),
                        title, link, equipmentBorrowRecordEO.getId());
            }
        }
    }

    /**
     * 判断设备是否已经被借出
     * @param equipmentBorrowRecordEO
     * @return
     */
    public Integer queryCountForEqBorrow(EquipmentBorrowRecordEO equipmentBorrowRecordEO) {
        return dao.queryCountForEqBorrow(equipmentBorrowRecordEO.getEqId());
    }

    /**
     * 根据设备id获取现在处于流程中借用信息的id
     */
    public String getBorrowId(String eqId){
        return dao.getBorrowId(eqId);
    }
    /**
     * 查询借出设备的相关信息。可用于查出借出人
     * @param eqId
     * @return
     */
    public EquipmentBorrowRecordEO selectBorrowRecordByEqId(String eqId) {
        //根据归还设备的id，和数据库表中设备归还状态的标记。来唯一确定一条记录
        EquipmentBorrowRecordEO borrowRecordEO = dao.selectBorrowRecordByEqId(eqId);
        return borrowRecordEO;
    }

    /**
     * 查出借用设备人的ID，真实姓名，部门
     * @param equipmentBorrowRecordEO
     * @return
     */
    public EquipmentBorrowRecordVO getBorrowerMes(EquipmentBorrowRecordEO equipmentBorrowRecordEO) throws Exception {
        String userID = equipmentBorrowRecordEO.getBrwBorrowerId();
        UserEO userEO = userEOService.selectByPrimaryKey(userID);
        EquipmentBorrowRecordVO equipmentBorrowRecordVO = new EquipmentBorrowRecordVO();
        equipmentBorrowRecordVO.setBorrowerId(userID);
        equipmentBorrowRecordVO.setBorrowerName(userEO.getUsname());
        equipmentBorrowRecordVO.setBorrowerDepartment(userEO.getOrgName());
        return equipmentBorrowRecordVO;

    }

    /**
     * 根据流程id获取借用信息
     */
    public BorrowRecordVO getBorrowRecordVO(String id) throws Exception {
        EquipmentBorrowRecordEO equipmentBorrowRecordEO = dao.selectByPrimaryKey(id);
        String eqId = equipmentBorrowRecordEO.getEqId();
        EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
        BorrowRecordVO borrowRecordVO = new BorrowRecordVO();
        borrowRecordVO.setBrwBorrowerId(equipmentBorrowRecordEO.getBrwBorrowerId());
        borrowRecordVO.setBrwBorrowerName(equipmentBorrowRecordEO.getBrwBorrowerName());
        borrowRecordVO.setBrwBorrowerTime(equipmentBorrowRecordEO.getBrwBorrowerTime());
        borrowRecordVO.setExpectReturnTime(equipmentBorrowRecordEO.getExpectReturnTime());
        borrowRecordVO.setBrwReturnTime(equipmentBorrowRecordEO.getBrwReturnTime());
        borrowRecordVO.setEqNo(equipmentEO.getEqNo());
        return borrowRecordVO;
    }
}
