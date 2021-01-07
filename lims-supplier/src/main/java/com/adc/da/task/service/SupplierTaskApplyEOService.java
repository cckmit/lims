package com.adc.da.task.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.constant.TaskStatusEnum;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.pc_announcement.dao.AnnPlanEODao;
import com.adc.da.pc_announcement.dao.AnnPlanProjectEODao;
import com.adc.da.pc_drive.dao.PcDriveRecordEODao;
import com.adc.da.pc_execute.service.PCTrialExecuteService;
import com.adc.da.pc_outsource.common.CostSettlementService;
import com.adc.da.pc_parts_apply.entity.PcPartsApplyEO;
import com.adc.da.pc_parts_apply.service.PcPartsApplyEOService;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.sys.dao.*;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.task.dao.SupplierTaskApplyEODao;
import com.adc.da.task.dto.SupplierTaskApplyDTO;
import com.adc.da.task.dto.SupplierTaskFinishInfoDTO;
import com.adc.da.task.dto.SupplierTaskInfoDTO;
import com.adc.da.task.entity.SupplierTaskApplyEO;
import com.adc.da.task.entity.SupplierTaskFinishInfoEO;
import com.adc.da.task.entity.SupplierTaskInfoEO;
import com.adc.da.task.page.SupplierTaskApplyEOPage;
import com.adc.da.task.page.SupplierTaskExeutionEOPage;
import com.adc.da.task.page.SupplierTaskStatisticsPage;
import com.adc.da.task.vo.*;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.*;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_APPLY SupplierTaskApplyEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("supplierTaskApplyEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SupplierTaskApplyEOService extends BaseService<SupplierTaskApplyEO, String> {


    @Value("${mail.address}")
    private String mailAddress;

    @Autowired
    private SupplierTaskApplyEODao dao;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SupplierTaskInfoEOService supplierTaskInfoEOService;

    @Autowired
    private SupplierTaskFinishInfoEOService supplierTaskFinishInfoEOService;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private PcPartsApplyEOService pcPartsApplyEOService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Autowired
    private CostSummaryEODao costSummaryEODao;

    @Autowired
    private PCTrialExecuteService pcTrialExecuteService;

    @Autowired
    private AnnPlanProjectEODao annPlanProjectEODao;

    @Autowired
    private AnnPlanEODao annPlanEODao;

    @Autowired
    private PcDriveRecordEODao pcDriveRecordEODao;
    @Autowired
    private CostSettlementService costSettlementService;
    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private EmailConfig emailConfig;

    @Value("${apply.no}")
    private String applyNo;

    public SupplierTaskApplyEODao getDao() {
        return dao;
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    public List<SupplierTaskApplyPageVO> selectByPage(SupplierTaskApplyEOPage page) {
        Integer rowCount = this.getDao().queryByCount(page);
        page.getPager().setRowCount(rowCount);
        return this.getDao().selectByPage(page);
    }

    /**
     * 删除任务委托及关联任务信息
     *
     * @param id
     */
    public void delete(String id) {
        // 查询任务委托是否存在
        SupplierTaskApplyEO supplierTaskApplyEO = this.getDao().selectByPrimaryKey(id);
        if (supplierTaskApplyEO != null) {
            String userId = UserUtils.getUserId();
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            // 删除任务信息表
            supplierTaskInfoEOService.deleteByApplyId(id);
            // 删除任务委托
            supplierTaskApplyEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
            supplierTaskApplyEO.setUpdateBy(userId);
            supplierTaskApplyEO.setUpdateTime(date);
            this.getDao().updateByPrimaryKeySelective(supplierTaskApplyEO);
        }

    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public SupplierTaskApplyVO selectVOById(String id) {
        SupplierTaskApplyVO supplierTaskApplyVO = this.getDao().selectVOById(id);
        List<SupplierTaskInfoVO> supplierTaskInfoVOS;
        if (supplierTaskApplyVO != null && (supplierTaskInfoVOS = supplierTaskApplyVO.getSupplierTaskInfoVOS()) != null
                && !supplierTaskInfoVOS.isEmpty()) {
            // 附件ID是以，隔开保存的字符串 所以需要截取数组遍历取附件信息
            settingTaskInfoAttachment(supplierTaskInfoVOS);
        }
        return supplierTaskApplyVO;
    }

    /**
     * 外包供应商任务执行|分页查询
     *
     * @param page
     * @return
     */
    public List<SupplierTaskExecutionPageVO> selectTaskExecutionByPage(SupplierTaskExeutionEOPage page) {
        Integer rowCount = this.getDao().selectTaskExecutionByCount(page);
        page.getPager().setRowCount(rowCount);
        return this.getDao().selectTaskExecutionByPage(page);
    }

    /**
     * 通过任务单id查询标准库信息
     *
     * @param id
     * @param status
     * @return
     */
    public List<SupplierTaskInfoVO> getTaskStandard(String id, Integer status) {
        List<SupplierTaskInfoVO> infoVOS = this.getDao().getTaskStandard(id, status);
        if (infoVOS != null && !infoVOS.isEmpty()) {
            // 附件ID是以，隔开保存的字符串 所以需要截取数组遍历取附件信息
            settingTaskInfoAttachment(infoVOS);
        }
        return infoVOS;
    }

    public void settingTaskInfoAttachment(List<SupplierTaskInfoVO> infoVOS) {
        for (SupplierTaskInfoVO vo : infoVOS) {
            vo.setSupplierAttachmentVOList(new ArrayList<SupplierAttachmentVO>());
            // 获取文件名称
            List<TsAttachmentEO> detailEOList = this.getNameById(vo.getAttachmentId());
            if (detailEOList != null && !detailEOList.isEmpty()) {
                for (TsAttachmentEO detailEO : detailEOList) {
                    SupplierAttachmentVO attachmentVO = new SupplierAttachmentVO();
                    attachmentVO.setAttachmentId(detailEO.getId());
                    attachmentVO.setAttachmentName(detailEO.getAttachmentName());
                    vo.getSupplierAttachmentVOList().add(attachmentVO);
                }
            }
        }
    }

    /**
     * 任务执行确认
     *
     * @param supplierTaskApplyDTO
     */
    public void createTaskSure(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        // 获取任务委托
        SupplierTaskApplyEO applyEO = this.getDao().selectByPrimaryKey(supplierTaskApplyDTO.getId());
        if (applyEO != null) {
            // 备注
            applyEO.setRemark(supplierTaskApplyDTO.getRemark());
            // 是否接受委托
            applyEO.setWhetherAccept(supplierTaskApplyDTO.getWhetherAccept());
            applyEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            String userId = UserUtils.getUserId();
            applyEO.setUpdateBy(userId);
            applyEO.setSupplierPeopleId(userId);
            if(0==supplierTaskApplyDTO.getWhetherAccept()) {
                applyEO.setStatus(TaskStatusEnum.UNDERWAY.getValue());
            }else if(1==supplierTaskApplyDTO.getWhetherAccept()){
                applyEO.setStatus(TaskStatusEnum.BACK.getValue());
            }
            // 保存外包供应商写入的备注和是否接受委托内容
            this.getDao().updateByPrimaryKeySelective(applyEO);
            List<SupplierTaskInfoVO> infoDTOList;
            // 判断委托的任务信息是否存在
            if ((infoDTOList = supplierTaskApplyDTO.getSupplierTaskInfoVOS()) != null
                    && !infoDTOList.isEmpty()) {
                for (SupplierTaskInfoVO infoDTO : infoDTOList) {
                    SupplierTaskInfoEO infoEO = supplierTaskInfoEOService.selectByPrimaryKey(
                            infoDTO.getInfoId());
                    infoEO.setTaskImplementerId(infoDTO.getTaskImplementerId());
                    infoEO.setTaskImplementerName(infoDTO.getTaskImplementerName());
                    if(0==supplierTaskApplyDTO.getWhetherAccept()) {
                        infoEO.setTaskStatus(TaskStatusEnum.UNDERWAY.getValue());
                    }else if(1==supplierTaskApplyDTO.getWhetherAccept()){
                        infoEO.setTaskStatus(TaskStatusEnum.BACK.getValue());
                    }
                    //将领料人信息回填到领料单
                    if(StringUtils.isNotEmpty(infoDTO.getPcPartNo())){
                        List<PcPartsApplyEO> pcPartsApplyEOList = pcPartsApplyEOService.queryByApplyNo(infoDTO.getPcPartNo());
                        if(CollectionUtils.isNotEmpty(pcPartsApplyEOList)){
                            UserEO userEO = userEOService.selectByPrimaryKey(infoDTO.getTaskImplementerId());
                            if(StringUtils.isNotEmpty(userEO)){
                                pcPartsApplyEOList.get(0).setReceiveUserName(userEO.getAccount());
                                pcPartsApplyEOList.get(0).setReceiveUserId(userEO.getUsid());
                                pcPartsApplyEOService.updateByPrimaryKeySelective(pcPartsApplyEOList.get(0));
                            }
                        }
                    }
                    supplierTaskInfoEOService.updateByPrimaryKeySelective(infoEO);
                }
            }
        }
    }

    /**
     * 外包供应商任务执行|委托单的任务信息
     *
     * @param id
     * @return
     */
    public SupplierTaskInfoVO getTaskInfo(String id) {
        return supplierTaskInfoEOService.getTaskInfo(id);
     }

     /**
     * upplierTaskApplyEO|任务完成度反馈
     *
     * @param supplierTaskFinishInfoDTO
     */
    public ResponseMessage createFinishInfo(SupplierTaskFinishInfoDTO supplierTaskFinishInfoDTO) throws Exception {
        SupplierTaskInfoEO infoEO = supplierTaskInfoEOService.selectByPrimaryKey(
                supplierTaskFinishInfoDTO.getSupTaskInfoId());
        if (infoEO == null) {
            return Result.success("-1", "保存失败！任务数据不存在");
        }
        SupplierTaskFinishInfoEO map = beanMapper.map(supplierTaskFinishInfoDTO, SupplierTaskFinishInfoEO.class);
        map.setId(UUID.randomUUID10());
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        map.setCreateBy(userId);
        map.setUpdateBy(userId);
        map.setCreateTime(date);
        map.setUpdateTime(date);
        
        if(StringUtils.isNotEmpty(map.getRealPeopleNumber()) ||
        		StringUtils.isNotEmpty(map.getRealWorkHour())) {
        	double realPeopleNumber = Double.parseDouble(map.getRealPeopleNumber());
        	double realWorkHour = Double.parseDouble(map.getRealWorkHour());
        	double totalManDays = realPeopleNumber * realWorkHour;
        	map.setTotalManDays(String.valueOf(totalManDays));
        }
        
        // map.setTaskStatus(TaskStatusEnum.FINISH.getValue());
        // 保存任务完成反馈信息
        supplierTaskFinishInfoEOService.insertSelective(map);          //反馈中存在进行中的状态选项，所以理论上可以反复进行反馈，故此处要判断insert和update
        // 将任务状态修改为填写完成反馈时用户所选状态
        infoEO.setTaskStatus(map.getTaskStatus());
        // 保存任务信息
        supplierTaskInfoEOService.updateByPrimaryKeySelective(infoEO);          //仍然选择进行中不执行更新
        // 查询委托下所有任务，判断任务的状态是否都已完成，如果完成，修改委托状态为已完成。
        SupplierTaskApplyVO supplierTaskApplyVO = this.selectVOById(infoEO.getSupApplyId());
        List<SupplierTaskInfoVO> infoEOList;
        if (supplierTaskApplyVO != null && (infoEOList = supplierTaskApplyVO.getSupplierTaskInfoVOS()) != null
                && !infoEOList.isEmpty()) {
            for (int i = 0; i < infoEOList.size(); i++) {
                if (infoEOList.get(i).getTaskStatus() == TaskStatusEnum.FINISH.getValue()) {
                    //原有的i=infoEOList.size()-1出bug，修改为将i进行加1匹配
                    if ((i+1) == infoEOList.size()) {
                        this.getDao().updateStatus(infoEO.getSupApplyId(), TaskStatusEnum.FINISH.getValue());
                    }
                }else break;
            }
        }
        this.getDao().updateFeddbackTime(infoEO.getSupApplyId(), date);
        return Result.success("0", "保存成功");
    }

    /**
     * 获取委托单编号接口
     *
     * @return
     */
    public String getApplyNo() {
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SUP_APPLY_NO);
        String date = DateUtils.dateToString(new Date(), "yyyy");
        // 如果查不到流水号新增
        if (pipelineNumberEO == null) {
            PipelineNumberEO eo = new PipelineNumberEO();
            // 为了保证查询时的流水号，和保存委托时的流水号相同。未查询到流水号时返回0001，然后将当前流水号+1，保存数据库。
            eo.setTally(2);
            eo.setParticularYear(date);
            eo.setType(ConstantUtils.SUP_APPLY_NO);
            pipelineNumberEODao.insertSelective(eo);
            return applyNo + date + "-0001";
        }
        // 如果当前年份不等于数据库中以保存的年份，重新开始
        if (!date.equals(pipelineNumberEO.getParticularYear())) {
            // 为了保证查询时的流水号，和保存委托时的流水号相同。年份不相等时重新计算流水号，返回0001，然后将当前流水号+1，保存数据库。
            pipelineNumberEO.setTally(2);
            pipelineNumberEO.setParticularYear(date);
            pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
            return applyNo + date + "-0001";
        }
        //最大流水号
        String num = pipelineNumberEO.getTally().toString();
        StringBuilder sb = new StringBuilder(num);
        //自动补全4位
        while (sb.length() < 4) {
            sb.insert(0, "0");
        }
        return applyNo + date + "-" + sb.toString();
    }

    public void createOwnerTaskSure(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        // 获取任务委托
        SupplierTaskApplyEO applyEO = this.getDao().selectByPrimaryKey(supplierTaskApplyDTO.getId());
        if (applyEO != null) {
            applyEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            applyEO.setUpdateBy(UserUtils.getUserId());
            applyEO.setIdea(supplierTaskApplyDTO.getId());
            applyEO.setStatus(TaskStatusEnum.END.getValue());
            // 更新委托意见
            this.getDao().updateByPrimaryKeySelective(applyEO);
            supplierTaskInfoEOService.updateTaskInfoStatus(supplierTaskApplyDTO.getId());
            
            if(StringUtils.isNotEmpty(applyEO.getTrialTaskId())) {
	            //获取车型相关数据
	            SupplierTaskApplyVO supplierTaskApplyVO = this.getDao().selectVOById(applyEO.getId());
	            List<SupplierTaskInfoVO> supplierTaskInfoVOS;
	            Double sum = 0.0;
	            if (supplierTaskApplyVO != null && (supplierTaskInfoVOS = supplierTaskApplyVO.getSupplierTaskInfoVOS()) != null
	                    && !supplierTaskInfoVOS.isEmpty()) {
	                for (SupplierTaskInfoVO supplierTaskInfoVO : supplierTaskInfoVOS){
	                    SupplierTaskFinishInfoVO supplierTaskFinishInfo = supplierTaskFinishInfoEOService.findById(supplierTaskInfoVO.getId());
	                    String unitPrice = supplierTaskInfoVO.getUnitPrice();
	                    String realPeopleNumber = supplierTaskFinishInfo.getRealPeopleNumber();
	                    String realWorkHour = supplierTaskFinishInfo.getRealWorkHour();
	                    if (StringUtils.isNotEmpty(unitPrice) && StringUtils.isNotEmpty(realPeopleNumber)
	                            && StringUtils.isNotEmpty(realWorkHour)){
	                        sum = sum + (Double.valueOf(unitPrice) * Double.valueOf(realPeopleNumber) * Double.valueOf(realWorkHour));
	                    }
	                }
	            }
                //            	走整合后的费用同步方法
                Map<String,Object> params=new HashMap<>();
                params.put("supId",applyEO.getBeApplyForId());
                params.put("trialTaskId",applyEO.getTrialTaskId());
                params.put("applyNo",applyEO.getApplyNo());
                params.put("sum",sum);
                params.put("createBy",applyEO.getCreateBy());
                costSettlementService.saveCostSummary("SupplierTaskApplyList",null,params);
//            	if (costSummary == null){
//            		CostSummaryEO costSummaryEO = new CostSummaryEO();
//            		costSummaryEO.setId(UUID.randomUUID());
//            		costSummaryEO.setDelFlag("0");
//            		costSummaryEO.setTrialTaskId(applyEO.getTrialTaskId());
//            		TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(applyEO.getTrialTaskId());
//            		if (trialTaskEO != null){//数据来源试验任务
//            			costSummaryEO.setDataOrigin("0");
//            			costSummaryEO.setTaskName(trialTaskEO.getTaskCode());
//            			List<TrialPersonEO> personList = pcTrialExecuteService.getPersonList(trialTaskEO.getId());
//            			//试验工程师
//            			String engineer = "";
//            			for (TrialPersonEO trialPersonEO : personList){
//            				if (StringUtils.isNotEmpty(trialPersonEO.getPersonRoleId()) &&
//            						(trialPersonEO.getPersonRoleId().contains("CV_TrialEngineer") ||
//            								trialPersonEO.getPersonRoleId().contains("PV_TrialEngineer"))){
//            					engineer += trialPersonEO.getPersonName() + ",";
//            				}
//            			}
//            			costSummaryEO.setEngineer(engineer);
//            			costSummaryEO.setTaskDesc("辅助劳务费");
//            		}else {//数据来源执行计划
//            			costSummaryEO.setDataOrigin("1");
//            			//认证计划名称
//            			AnnPlanProjectEO annPlanProjectEO = annPlanProjectEODao.selectByPrimaryKey(applyEO.getTrialTaskId());
//            			if (annPlanProjectEO != null){
//            				//负责人(试验工程师)
//            				costSummaryEO.setEngineer(annPlanProjectEO.getPjEngineerName());
//            				//任务简要
//            				costSummaryEO.setTaskDesc(annPlanProjectEO.getPjTaskExplain());
//            				String planId = annPlanProjectEO.getPlanId();
//            				//获取申报项目主信息
//            				AnnPlanEO annPlanEO = annPlanEODao.selectByPrimaryKey(planId);
//            				if (annPlanEO != null){
//            					costSummaryEO.setTaskName(annPlanEO.getPlName());
//            				}
//            			}
//            		}
//            		costSummaryEO.setCarType(carType.toString());
//            		costSummaryEO.setCarIds(carIds.toString());
//            		costSummaryEO.setChassisNo(chassisNo.toString());
//            		costSummaryEO.setCostType("6");
//            		costSummaryEO.setTotalCost(String.valueOf(sum));
//            		costSummaryEO.setPaymentCost(String.valueOf(sum));
//            		costSummaryEO.setTrustNo(applyEO.getApplyNo());
//            		costSummaryEO.setEditStatus("0");
//            		costSummaryEO.setFormKey("SupplierTaskApplyList");
//            		costSummaryEO.setSupId(applyEO.getBeApplyForId());
//            		AbilityVO abilityVO = pcDriveRecordEODao.selectAbilityVO(applyEO.getBeApplyForId());
//            		if (abilityVO != null){
//            			costSummaryEO.setSupName(abilityVO.getSupName());
//            			costSummaryEO.setSupCode(abilityVO.getSupCode());
//            			costSummaryEO.setSupType(abilityVO.getSupType());
//            		}
//            		costSummaryEO.setPaymentStatus("0");
//            		costSummaryEODao.insert(costSummaryEO);
//            	}else {
//            		String carIds1 = costSummary.getCarIds();
//            		if (StringUtils.isNotEmpty(carIds1)){
//            			carIds.add(carIds1);
//            		}
//            		costSummary.setCarIds(carIds.toString());
//            		String carType1 = costSummary.getCarType();
//            		if (StringUtils.isNotEmpty(carType1)){
//            			carType.add(carType1);
//            		}
//            		costSummary.setCarType(carType.toString());
//            		String chassisNo1 = costSummary.getChassisNo();
//            		if (StringUtils.isNotEmpty(chassisNo1)){
//            			chassisNo.add(chassisNo1);
//            		}
//            		costSummary.setChassisNo(chassisNo.toString());
//            		String totalCost = costSummary.getTotalCost();
//            		costSummary.setTotalCost(String.valueOf(Double.valueOf(totalCost) + sum));
//            		costSummary.setPaymentCost(String.valueOf(Double.valueOf(totalCost) + sum));
//            		costSummaryEODao.updateByPrimaryKey(costSummary);
//            	}
            }
        }
    }

    /**
     * |外包供应商|任务统计分页查询
     *
     * @param page
     * @return
     */
    public List<SupplierTaskStatisticsVO> getSupplierTaskStatisticsByPage(SupplierTaskStatisticsPage page) {
        List<SupplierTaskStatisticsVO> voList = supplierTaskInfoEOService.getSupplierTaskStatisticsByPage(page);
        settingStatisticsAttachment(voList);
        return voList;
    }

    /**
     * |外包供应商|任务统计导出
     *
     * @param page
     * @return
     */
    public List<SupplierTaskStatisticsVO> queryListForExcel(SupplierTaskStatisticsPage page) {
        List<SupplierTaskStatisticsVO> voList = supplierTaskInfoEOService.queryListForExcel(page);
        settingStatisticsAttachment(voList);
        return voList;
    }

    public void settingStatisticsAttachment(List<SupplierTaskStatisticsVO> voList) {
        if (voList != null && !voList.isEmpty()) {
            // 附件ID是以，隔开保存的字符串 所以需要截取数组遍历取附件信息
            for (SupplierTaskStatisticsVO vo : voList) {
                vo.setSupplierAttachmentVO(new ArrayList<SupplierAttachmentVO>());
                // 获取文件名称
                List<TsAttachmentEO> detailEOList = this.getNameById(vo.getAttachmentId());
                if (detailEOList != null && !detailEOList.isEmpty()) {
                    for (TsAttachmentEO detailEO : detailEOList) {
                        SupplierAttachmentVO attachmentVO = new SupplierAttachmentVO();
                        attachmentVO.setAttachmentId(detailEO.getId());
                        attachmentVO.setAttachmentName(detailEO.getAttachmentName());
                        vo.getSupplierAttachmentVO().add(attachmentVO);
                    }
                }
            }
        }
    }

    /**
     * 获取文件名称
     *
     * @param attachmentId
     * @return
     */
    private List<TsAttachmentEO> getNameById(String attachmentId) {
        List<TsAttachmentEO> detailEOList = new ArrayList<>();
        if (StringUtils.isNotBlank(attachmentId)) {
            if (attachmentId.indexOf(",") == -1) {
                TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(attachmentId);
                detailEOList.add(eo);
            } else {
                String[] ids = attachmentId.split(",");
                for (String id : ids) {
                    TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(id);
                    detailEOList.add(eo);
                }
            }
        }
        return detailEOList;
    }

    /**
     * 保存任务委托及关联标准
     *
     * @param supplierTaskApplyDTO
     */
    public void create(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        SupplierTaskApplyEO applyEO = beanMapper.map(supplierTaskApplyDTO, SupplierTaskApplyEO.class);
        applyEO.setStatus(TaskStatusEnum.CANCEL.getValue());
        applyEO.setId(UUID.randomUUID10());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        applyEO.setCreateTime(date);
        applyEO.setCreateBy(userId);
        applyEO.setUpdateTime(date);
        applyEO.setUpdateBy(userId);
        applyEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        applyEO.setApplyTime(date);
        //判断流水号
        String applyNo = getApplyNo();
        if(!StringUtils.equals(applyNo, applyEO.getApplyNo())){
            applyEO.setApplyNo(applyNo);
        }
        // 保存任务委托
        this.getDao().insertSelective(applyEO);
        savetaskInfo(supplierTaskApplyDTO, applyEO, date, userId, TaskStatusEnum.CANCEL.getValue());
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SUP_APPLY_NO);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
    }

    /**
     * 保存任务委托及关联标准为草稿状态
     *
     * @param supplierTaskApplyDTO
     */
    public void createDraft(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        SupplierTaskApplyEO applyEO = beanMapper.map(supplierTaskApplyDTO, SupplierTaskApplyEO.class);
        applyEO.setStatus(TaskStatusEnum.DRAFT.getValue());
        applyEO.setId(UUID.randomUUID10());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        applyEO.setCreateTime(date);
        applyEO.setCreateBy(userId);
        applyEO.setUpdateTime(date);
        applyEO.setUpdateBy(userId);
        applyEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        //判断流水号
        String applyNo = getApplyNo();
        if(!StringUtils.equals(applyNo, applyEO.getApplyNo())){
            applyEO.setApplyNo(applyNo);
        }
        // 保存任务委托
        this.getDao().insertSelective(applyEO);
        savetaskInfo(supplierTaskApplyDTO, applyEO, date, userId, TaskStatusEnum.DRAFT.getValue());
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SUP_APPLY_NO);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
    }

    public void savetaskInfo(SupplierTaskApplyDTO supplierTaskApplyDTO, SupplierTaskApplyEO applyEO, String date,
                             String userId, Integer status) throws Exception {
        List<SupplierTaskInfoDTO> infoDTOList = supplierTaskApplyDTO.getSupplierTaskInfoDTOS();
        // 判断委托任务信息是否存在
        saveTaskInfo(applyEO, date, userId, status, infoDTOList);
    }

    public void saveTaskInfo(SupplierTaskApplyEO applyEO, String date, String userId, Integer status,
                             List<SupplierTaskInfoDTO> infoDTOList) throws Exception {
        if (infoDTOList != null && !infoDTOList.isEmpty()) {
            for (SupplierTaskInfoDTO infoDTO : infoDTOList) {
                SupplierTaskInfoEO map = beanMapper.map(infoDTO, SupplierTaskInfoEO.class);
                map.setId(UUID.randomUUID10());
                map.setCreateBy(userId);
                map.setCreateTime(date);
                map.setUpdateBy(userId);
                map.setUpdateTime(date);
                map.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                map.setTaskStatus(status);
                map.setPcPartNo(infoDTO.getPcPartNo());
                // 委托ID
                map.setSupApplyId(applyEO.getId());
                map.setApplyNo(applyEO.getApplyNo());
                supplierTaskInfoEOService.insertSelective(map);
            }
        }
    }

    /**
     * 编辑草稿
     *
     * @param supplierTaskApplyDTO
     */
    public void updateDraft(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        SupplierTaskApplyEO applyEO = beanMapper.map(supplierTaskApplyDTO, SupplierTaskApplyEO.class);
        applyEO.setStatus(TaskStatusEnum.DRAFT.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        applyEO.setCreateTime(date);
        applyEO.setCreateBy(userId);
        applyEO.setUpdateTime(date);
        applyEO.setUpdateBy(userId);
        applyEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        // 保存任务委托
        this.getDao().updateByPrimaryKeySelective(applyEO);
        updatetaskInfo(supplierTaskApplyDTO, applyEO, date, userId, TaskStatusEnum.DRAFT.getValue());
    }

    private void updatetaskInfo(SupplierTaskApplyDTO supplierTaskApplyDTO, SupplierTaskApplyEO applyEO, String date,
                                String userId, Integer status) throws Exception {
        // 删除任务信息表
        supplierTaskInfoEOService.deleteByApplyId(supplierTaskApplyDTO.getId());
        List<SupplierTaskInfoDTO> infoDTOList = supplierTaskApplyDTO.getSupplierTaskInfoDTOS();
        // 判断委托任务信息是否存在
        saveTaskInfo(applyEO, date, userId, status, infoDTOList);
    }

    /**
     * 编辑
     *
     * @param supplierTaskApplyDTO
     */
    public void update(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        SupplierTaskApplyEO applyEO = beanMapper.map(supplierTaskApplyDTO, SupplierTaskApplyEO.class);
        applyEO.setStatus(TaskStatusEnum.CANCEL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        applyEO.setCreateTime(date);
        applyEO.setCreateBy(userId);
        applyEO.setUpdateTime(date);
        applyEO.setUpdateBy(userId);
        applyEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        // 保存任务委托
        this.getDao().updateByPrimaryKeySelective(applyEO);
        updatetaskInfo(supplierTaskApplyDTO, applyEO, date, userId, TaskStatusEnum.CANCEL.getValue());
    }

    public List<SupplierTaskStatisticsVO> getSupplierTaskStatisticsByTrialTaskId(SupplierTaskStatisticsPage page,String trialTaskId) {
        List<SupplierTaskApplyEO> applyEO=  this.getDao().selectByTrialTaskId(trialTaskId);
        List<String> appNo= applyEO.stream().map(supplierTaskApplyEO -> supplierTaskApplyEO.getApplyNo()).collect(Collectors.toList());
        List<SupplierTaskStatisticsVO> taskInfoByApplyNo = supplierTaskInfoEOService.getTaskInfoByApplyNo(page,appNo);
        this.settingStatisticsAttachment(taskInfoByApplyNo);
        return taskInfoByApplyNo;
    }



    /**
     * 启动流程
     *
     * @param supplierTaskApplyDTO
     * @throws Exception
     */
    public void startProcessSupplierTask(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        //更新实体
        update(supplierTaskApplyDTO);
        String pvOrcv = "";
        // 1:乘用车   0：商用车
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if ("1".equals(flag)) {
            pvOrcv = ConstantUtils.PV_SUP_TASKAPPLY;
        } else if ("0".equals(flag)) {
            pvOrcv = ConstantUtils.CV_SUP_TASKAPPLY;
        }
        String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
        //业务主表
        BaseBusEO baseBusEO = new BaseBusEO(
                supplierTaskApplyDTO.getId(), pvOrcv, UserUtils.getUserId(),
                currTime, "供应商项目委托[" + supplierTaskApplyDTO.getApplyNo() + "]流程启动");
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
     * 完成反馈启动流程
     *
     * @param supplierTaskApplyDTO
     * @throws Exception
     */
    public void startProcessFeedBack(SupplierTaskApplyDTO supplierTaskApplyDTO) throws Exception {
        //更新实体
        update(supplierTaskApplyDTO);
        String pvOrcv = "";
        // 1:乘用车   0：商用车
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if ("1".equals(flag)) {
            pvOrcv = ConstantUtils.PV_SUP_TASKAPPLY_Feedback;
        } else if ("0".equals(flag)) {
            pvOrcv = ConstantUtils.CV_SUP_TASKAPPLY_Feedback;
        }
        String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
        //业务主表
        BaseBusEO baseBusEO = new BaseBusEO(
                supplierTaskApplyDTO.getId(), pvOrcv, UserUtils.getUserId(),
                currTime, "供应商项目委托[" + supplierTaskApplyDTO.getApplyNo() + "]流程启动");
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
        int status = 3;
        //退回
        if ("rollback".equals(approveCode)) {
            status = 4;
        }
        //更新流程状态
        dao.updateStatus(baseBusEO.getBusinessId(), status);
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
     * 给下一待办人发送消息通知
     *
     * @param ids
     * @throws IOException
     */
    public void sendRemindToIds(String[] ids, String businessId) throws Exception {    //提走
        if (StringUtils.isNotEmpty(ids)) {
            List<UserEO> userEOList = userEOService.findUserByIds(ids);
            BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(businessId);
            //供应商委托
            SupplierTaskApplyEO supplierTaskApplyDTO = dao.selectByPrimaryKey(baseBusEO.getBusinessId());
            //标题
            String title = "[辅助劳务委托流程" + supplierTaskApplyDTO.getApplyNo() + "]";
            MessageEO messageEO;
            String currTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss");
            for (UserEO userEO : userEOList) {
                // 消息通知
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
                            userEO.getEmail(), "辅助劳务审批流程待办",
                            "<a href='"+mailAddress+token+"'>"+title+"</a> ",token
                    ));
                }
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
                //已审批
                dao.updateStatus(trialProjectEOId, 5);
            }
        }
    }




}
