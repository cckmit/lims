package com.adc.da.pc_execute.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.pc_execute.entity.*;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;


import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.dao.PCBaseInitDataEODao;
import com.adc.da.pc_execute.dao.PCBudgetCostEODao;
import com.adc.da.pc_execute.dao.PCBudgetPersonEODao;
import com.adc.da.pc_execute.dao.PCBudgetSubsidyEODao;
import com.adc.da.pc_execute.dao.PCBudgetTestEODao;
import com.adc.da.pc_execute.dao.PCTrialLineEODao;
import com.adc.da.pc_execute.vo.PCBudgetCostVO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

import javax.servlet.http.HttpServletRequest;


@Service("PCBudgetCostEOService")
@Transactional(value = "transactionManager", readOnly = false,
propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCBudgetCostEOService extends BaseService<PCBudgetCostEO, String> {
	
	@Autowired
	private PCBudgetCostEODao pcBudgetCostEODao;
	
	public PCBudgetCostEODao getDao() {
		return pcBudgetCostEODao;
	}
	
    @Autowired
    private PCBudgetPersonEODao pcBudgetPersonEODao;

    @Autowired
    private PCBudgetSubsidyEODao pcBudgetSubsidyEODao;

    @Autowired
    private PCBudgetTestEODao pcBudgetTestEODao;

    @Autowired
    private PCBaseInitDataEODao pcBaseInitDataEODao;
    
    @Autowired
    private PCTrialLineEODao pcTrialLineEODao;

    @Autowired
    private OrgEOService orgEOService;

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

	
	/**
	 * 费用表单页数据查询
	 * @Title: costFormData
	 * @return
	 * @return PCBudgetCostVO
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
    public PCBudgetCostVO costFormData(String taskNumber) {
        //先按taskNumber 分别查三个,没有就查 基础   然后返回, 主表附件等信息 一并返回
        PCBudgetCostVO vo = new PCBudgetCostVO();
        PCBudgetCostEO cost = pcBudgetCostEODao.getCostByTrialTaskNumber(taskNumber);
        if (StringUtils.isNotEmpty(cost)) {
            //申请时间(费用)
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            vo.setApplyTime(sdFormat.format(StringToDate(cost.getCreateTime())));
            //申请人id(费用)
            vo.setApplyUserId(cost.getCreateBy());
            //申请人名(费用)
            vo.setApplyUserName(cost.getCreateByName());
            //附件id(费用)
            vo.setAttachId(cost.getAttachId());
            //附件名(费用)
            vo.setAttachName(cost.getAttachName());
            //补贴备注(费用)
            vo.setSubsidyRemark(cost.getSubsidyRemark());
            //流程状态
            vo.setFlowStatus(cost.getFlowStatus());
            //费用合计相关字段----开始----
            vo.setSelfTotal(cost.getSelfTotal());
            vo.setPublicTotal(cost.getPublicTotal());
            vo.setAccoCost(cost.getAccoCost());
            vo.setOilChargeCost(cost.getOilChargeCost());
            vo.setHighRoadCost(cost.getHighRoadCost());
            vo.setRepairCost(cost.getRepairCost());
            vo.setTempCardCost(cost.getTempCardCost());
            vo.setBusniessCost(cost.getBusniessCost());
            vo.setDangerCost(cost.getDangerCost());
            vo.setPubOilChargeCost(cost.getPubOilChargeCost());
            vo.setOutSourceCost(cost.getOutSourceCost());
            vo.setPubRepairCost(cost.getPubRepairCost());
            vo.setTestFieldCost(cost.getTestFieldCost());
            //costType
            vo.setCostType(cost.getCostType());
            //otherCost
            vo.setOtherCost(cost.getOtherCost());
            //transitionCost
            vo.setTransitionCost(cost.getTransitionCost());
            //transportCost
            vo.setTransportCost(cost.getTransportCost());
            //trailerRentCost
            vo.setTrailerRentCost(cost.getTrailerRentCost());
            //parkingCost
            vo.setParkingCost(cost.getParkingCost());
            //roadTestCost
            vo.setRoadTestCost(cost.getRoadTestCost());
            //roadSendCost
            vo.setRoadSendCost(cost.getRoadSendCost());
            //费用合计相关字段----结束----
        }
        //试验任务id
        vo.setTaskNumber(taskNumber);
        vo.setTrialTaskId(cost.getTrialTaskId());
        //PSQC/性能试验--试验人员及住宿预算
        List<PCBudgetPersonEO> pcBudgetPersonList = pcBudgetPersonEODao.findListByTrialTaskNumber(taskNumber);
        if (CollectionUtils.isEmpty(pcBudgetPersonList)) {
            pcBudgetPersonList = getPcBudgetPersonList();
        }
        vo.setPcBudgetPersonList(pcBudgetPersonList);
        //PSQC/性能试验--试验补贴预算
        List<PCBudgetSubsidyEO> pcBudgetSubsidyList = pcBudgetSubsidyEODao.findListByTrialTaskNumber(taskNumber);
        if (CollectionUtils.isEmpty(pcBudgetSubsidyList)) {
            pcBudgetSubsidyList = getPcBudgetSubsidyList(taskNumber);
        }
        vo.setPcBudgetSubsidyList(pcBudgetSubsidyList);
        //PSQC/性能试验--试验费用预算
        List<PCBudgetTestEO> pcBudgetTestList = pcBudgetTestEODao.findListByTrialTaskNumber(taskNumber);
        if (CollectionUtils.isEmpty(pcBudgetTestList)) {
            pcBudgetTestList = getPcBudgetTestList();
        }
        vo.setPcBudgetTestList(pcBudgetTestList);
        return vo;
    }

    /**
     * PSQC/性能试验--试验人员及住宿预算
     *
     * @return List<PCBudgetPersonEO>
     * @Title: getPcBudgetPersonList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public List<PCBudgetPersonEO> getPcBudgetPersonList() {
        //返回定义
        List<PCBudgetPersonEO> pcBudgetPersonList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> personBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_PERSON);
        for (int i = 0; i < personBaseList.size(); i++) {
            PCBudgetPersonEO person = new PCBudgetPersonEO();
            //项目名称
            person.setItemsName(personBaseList.get(i).getItemsName());
            //项目code
            person.setItemsCode(personBaseList.get(i).getItemsCode());
            //单位
            person.setUnit(personBaseList.get(i).getUnit());
            //标准费用
            person.setStdPrice(personBaseList.get(i).getPrice());
            pcBudgetPersonList.add(person);
        }
        return pcBudgetPersonList;
    }


    /**
     * PSQC/性能试验--试验补贴预算
     *
     * @return List<PCBudgetSubsidyEO>
     * @Title: getPcBudgetSubsidyList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public List<PCBudgetSubsidyEO> getPcBudgetSubsidyList(String taskNumber) {
        //返回定义
        List<PCBudgetSubsidyEO> pcBudgetSubsidyList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> subsidyBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_SUBSIDY);
        //查询路线
        List<PCTrialLineEO> list = pcTrialLineEODao.findListByTrialTaskNumber(taskNumber);
        //磨合行驶
        Set<String> grindingInSet = new HashSet<String>();
        //一般公路试验
        Set<String> generalHighwaySet = new HashSet<String>();
        //市区+阻滞试验
        Set<String> urbanBlockSet = new HashSet<String>();
        //高速路试验
        Set<String> highSpeedSet = new HashSet<String>();
        //山区公路试验
        Set<String> mountainRoadSet = new HashSet<String>();
        //(强化)环路试验
        Set<String> ringRoadSet = new HashSet<String>();
        //越野路试试验
        Set<String> crossCountrySet = new HashSet<String>();
        //微丘高速
        Set<String> hillockHighSet = new HashSet<String>();
        //山区重丘高速
        
        Set<String> mountainousHillySet = new HashSet<String>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                switch (list.get(i).getLineType()) {
                    //磨合行驶
                    case ConstantUtils.GRINDINGIN:
                        grindingInSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //一般公路试验
                    case ConstantUtils.GENERALHIGHWAY:
                        generalHighwaySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //市区+阻滞试验
                    case ConstantUtils.URBANBLOCK:
                        urbanBlockSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //高速路试验
                    case ConstantUtils.HIGHSPEED:
                        highSpeedSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //山区公路试验
                    case ConstantUtils.MOUNTAINROAD:
                        mountainRoadSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //(强化)环路试验
                    case ConstantUtils.RINGROAD:
                        ringRoadSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //越野路试试验
                    case ConstantUtils.CROSSCOUNTRY:
                        crossCountrySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //微丘高速
                    case ConstantUtils.HILLOCKHIGH:
                        hillockHighSet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    //山区重丘高速
                    case ConstantUtils.MOUNTAINOUSHILLY:
                        mountainousHillySet.add(list.get(i).getLineStart() + ConstantUtils.HYPHEN + list.get(i).getLineEnd());
                        break;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i < subsidyBaseList.size(); i++) {
            PCBudgetSubsidyEO subsidy = new PCBudgetSubsidyEO();
            //项目名称
            subsidy.setItemsName(subsidyBaseList.get(i).getItemsName());
            //项目code
            subsidy.setItemsCode(subsidyBaseList.get(i).getItemsCode());
            //项目路线
            switch (subsidyBaseList.get(i).getItemsCode()) {
                //磨合行驶
                case ConstantUtils.GRINDINGIN:
                    subsidy.setTrialLine(grindingInSet.toString());
                    break;
                //一般公路试验
                case ConstantUtils.GENERALHIGHWAY:
                    subsidy.setTrialLine(generalHighwaySet.toString());
                    break;
                //市区+阻滞试验
                case ConstantUtils.URBANBLOCK:
                    subsidy.setTrialLine(urbanBlockSet.toString());
                    break;
                //高速路试验
                case ConstantUtils.HIGHSPEED:
                    subsidy.setTrialLine(highSpeedSet.toString());
                    break;
                //山区公路试验
                case ConstantUtils.MOUNTAINROAD:
                    subsidy.setTrialLine(mountainRoadSet.toString());
                    break;
                //(强化)环路试验
                case ConstantUtils.RINGROAD:
                    subsidy.setTrialLine(ringRoadSet.toString());
                    break;
                //越野路试试验
                case ConstantUtils.CROSSCOUNTRY:
                    subsidy.setTrialLine(crossCountrySet.toString());
                    break;
                //微丘高速
                case ConstantUtils.HILLOCKHIGH:
                    subsidy.setTrialLine(hillockHighSet.toString());
                    break;
                //山区重丘高速
                case ConstantUtils.MOUNTAINOUSHILLY:
                    subsidy.setTrialLine(mountainousHillySet.toString());
                    break;
                default:
                    break;
            }
            //标准
            subsidy.setStdPrice(subsidyBaseList.get(i).getPrice());
            pcBudgetSubsidyList.add(subsidy);
        }
        return pcBudgetSubsidyList;
    }


    /**
     * PSQC/性能试验--试验费用预算
     *
     * @return List<PCBudgetTestEO>
     * @Title: getPcBudgetTestList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public List<PCBudgetTestEO> getPcBudgetTestList() {
        //返回定义
        List<PCBudgetTestEO> pcBudgetTestList = new ArrayList<>();
        //查询标准
        List<PCBaseInitDataEO> testBaseList =
                pcBaseInitDataEODao.findListByCodeType(ConstantUtils.PC_BUDGET_TEST);
        for (int i = 0; i < testBaseList.size(); i++) {
            PCBudgetTestEO test = new PCBudgetTestEO();
            //项目名称
            test.setItemsName(testBaseList.get(i).getItemsName());
            //项目code
            test.setItemsCode(testBaseList.get(i).getItemsCode());
            //单位
            test.setUnit(testBaseList.get(i).getUnit());
            //标准费用
            test.setStdPrice(testBaseList.get(i).getPrice());
            pcBudgetTestList.add(test);
        }
        return pcBudgetTestList;
    }

    /**
     * 保存费用表单页数据
     *
     * @param pcBudgetCostVO
     * @return void
     * @Title: saveCostData
     * @author: ljy
     * @throws Exception 
     * @date: 2019年12月23日
     */
    public PCBudgetCostEO costDataSave(PCBudgetCostVO pcBudgetCostVO) throws Exception {
        //PSQC/性能试验--试验人员及住宿预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetPersonList())) {
            savePcBudgetPersonList(pcBudgetCostVO.getPcBudgetPersonList(),
                    pcBudgetCostVO.getTaskNumber());
        }
        //PSQC/性能试验--试验补贴预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetSubsidyList())) {
            savePcBudgetSubsidyList(pcBudgetCostVO.getPcBudgetSubsidyList(),
                    pcBudgetCostVO.getTaskNumber());
        }
        //PSQC/性能试验--试验费用预算
        if (CollectionUtils.isNotEmpty(pcBudgetCostVO.getPcBudgetTestList())) {
            savePcBudgetTestList(pcBudgetCostVO.getPcBudgetTestList(),
                    pcBudgetCostVO.getTaskNumber());
        }
        //PSQC/性能试验--试验费用预算其他信息保存
        return saveCostOtherInfo(pcBudgetCostVO);
        
    }

    /**
     * 保存 PSQC/性能试验--试验人员及住宿预算
     *
     * @param pcBudgetPersonList
     * @return void
     * @Title: savePcBudgetPersonList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public void savePcBudgetPersonList(List<PCBudgetPersonEO> pcBudgetPersonList,
                                       String taskNumber) {
        //先删除
        pcBudgetPersonEODao.deleteByTrialTaskNumber(taskNumber);
        //后新增
        for (PCBudgetPersonEO person : pcBudgetPersonList) {
            person.setId(UUID.randomUUID());
            person.setDelFlag("0");
            person.setTaskNumber(taskNumber);
            pcBudgetPersonEODao.insert(person);
        }
    }

    /**
     * 保存 PSQC/性能试验--试验补贴预算
     *
     * @param pcBudgetSubsidyList
     * @return void
     * @Title: savePcBudgetSubsidyList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public void savePcBudgetSubsidyList(List<PCBudgetSubsidyEO> pcBudgetSubsidyList,
                                        String taskNumber) {
        //先删除
        pcBudgetSubsidyEODao.deleteByTrialTaskNumber(taskNumber);
        //后新增
        for (PCBudgetSubsidyEO subsidy : pcBudgetSubsidyList) {
            subsidy.setId(UUID.randomUUID());
            subsidy.setDelFlag("0");
            subsidy.setTaskNumber(taskNumber);
            pcBudgetSubsidyEODao.insert(subsidy);
        }
    }

    /**
     * 保存PSQC/性能试验-试验费用预算
     *
     * @param pcBudgetTestList
     * @return void
     * @Title: savePcBudgetTestList
     * @author: ljy
     * @date: 2019年12月23日
     */
    public void savePcBudgetTestList(List<PCBudgetTestEO> pcBudgetTestList,
                                     String taskNumber) {
        //先删除
        pcBudgetTestEODao.deleteByTrialTaskNumber(taskNumber);
        //后新增
        for (PCBudgetTestEO test : pcBudgetTestList) {
            test.setId(UUID.randomUUID());
            test.setDelFlag("0");
            test.setTaskNumber(taskNumber);
            pcBudgetTestEODao.insert(test);
        }
    }
	
    
    /**
     * 保存费用其他信息
     * @Title: saveCostOtherInfo
     * @param pcBudgetCostVO
     * @throws Exception
     * @return void
     * @author: ljy
     * @date: 2019年12月24日
     */
    public PCBudgetCostEO saveCostOtherInfo(PCBudgetCostVO pcBudgetCostVO) throws Exception {
    	//先删除
    	pcBudgetCostEODao.deleteByTrialTaskId(pcBudgetCostVO.getTrialTaskId());
    	//根据code删除
    	pcBudgetCostEODao.deleteByTrialTaskNumber(pcBudgetCostVO.getTaskNumber());
    	//后新增
    	PCBudgetCostEO budgetCostEO = new PCBudgetCostEO();
        budgetCostEO.setId(UUID.randomUUID());
        budgetCostEO.setDelFlag("0");
        budgetCostEO.setAttachId(pcBudgetCostVO.getAttachId());
        budgetCostEO.setAttachName(pcBudgetCostVO.getAttachName());
        budgetCostEO.setCreateBy(UserUtils.getUserId());
        budgetCostEO.setCreateByName(UserUtils.getUser().getUsname());
        //设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        budgetCostEO.setCreateTime(date);
        //补贴备注(费用)
        budgetCostEO.setSubsidyRemark(pcBudgetCostVO.getSubsidyRemark());
        //试验任务id
        budgetCostEO.setTrialTaskId(pcBudgetCostVO.getTrialTaskId());
        //流程状态：草稿
        budgetCostEO.setFlowStatus("0");

        budgetCostEO.setSelfTotal(pcBudgetCostVO.getSelfTotal());
        budgetCostEO.setPublicTotal(pcBudgetCostVO.getPublicTotal());
        budgetCostEO.setAccoCost(pcBudgetCostVO.getAccoCost());
        budgetCostEO.setOilChargeCost(pcBudgetCostVO.getOilChargeCost());
        budgetCostEO.setHighRoadCost(pcBudgetCostVO.getHighRoadCost());
        budgetCostEO.setRepairCost(pcBudgetCostVO.getRepairCost());
        budgetCostEO.setTempCardCost(pcBudgetCostVO.getTempCardCost());
        budgetCostEO.setBusniessCost(pcBudgetCostVO.getBusniessCost());
        budgetCostEO.setDangerCost(pcBudgetCostVO.getDangerCost());
        budgetCostEO.setPubOilChargeCost(pcBudgetCostVO.getPubOilChargeCost());
        budgetCostEO.setOutSourceCost(pcBudgetCostVO.getOutSourceCost());
        budgetCostEO.setPubRepairCost(pcBudgetCostVO.getPubRepairCost());
        budgetCostEO.setTestFieldCost(pcBudgetCostVO.getTestFieldCost());
        //试验任务唯一编号
        budgetCostEO.setTaskNumber(pcBudgetCostVO.getTaskNumber());
        //costType
        budgetCostEO.setCostType(pcBudgetCostVO.getCostType());
        //otherCost
        budgetCostEO.setOtherCost(pcBudgetCostVO.getOtherCost());
        //transitionCost
        budgetCostEO.setTransitionCost(pcBudgetCostVO.getTransitionCost());
        //transportCost
        budgetCostEO.setTransportCost(pcBudgetCostVO.getTransportCost());
        //trailerRentCost
        budgetCostEO.setTrailerRentCost(pcBudgetCostVO.getTrailerRentCost());
        //parkingCost
        budgetCostEO.setParkingCost(pcBudgetCostVO.getParkingCost());
        //roadTestCost
        budgetCostEO.setRoadTestCost(pcBudgetCostVO.getRoadTestCost());
        //roadSendCost
        budgetCostEO.setRoadSendCost(pcBudgetCostVO.getRoadSendCost());
        
        //保存
        pcBudgetCostEODao.insert(budgetCostEO);

        return budgetCostEO;
    }

    /**
     * 提交性能试验-费用预算流程
     * @param pcBudgetCostVO
     * @throws Exception
     */
    public void submitCostData(PCBudgetCostVO pcBudgetCostVO) throws Exception {
        //保存业务数据，状态为草稿
        PCBudgetCostEO pcBudgetCostEO = this.costDataSave(pcBudgetCostVO);

        //启动流程
        String businessType = null;//业务类型
        //判断是PV还是EV
        UserEO curUser = userEODao.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if("1".equals(flag)){
            //PV
            businessType = ConstantUtils.PV_BUDGET_PROCESS;
        }else{
            //CV
            businessType = ConstantUtils.CV_BUDGET_PROCESS;
        }

        String title = "费用预算审批流程";

        String baseBusId = limsFileService.saveBaseBus(pcBudgetCostEO.getTaskNumber(), businessType, title);//businessKey

        String procDefKey = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, businessType).getDicTypeName();//processDefinitionKey
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        processInstanceVO.setProcessDefinitionKey(procDefKey);
        
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
    	
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //修改流程状态为审批中  1-审批中
        pcBudgetCostEO.setFlowStatus("1");
        pcBudgetCostEODao.updateByPrimaryKeySelective(pcBudgetCostEO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            //链接到可靠性立项单页
            String link = businessType;
            limsFileService.processSendMessages(splits, title, link, pcBudgetCostEO.getTrialTaskId());
        }
    }

    /**
     * 审批性能试验-费用预算流程
     * @param request
     * @param requestEO
     * @throws Exception
     */
    public void handleCostData(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取可靠性立项单业务信息
        PCBudgetCostEO pcBudgetCostEO = pcBudgetCostEODao.getCostByTrialTaskNumber(baseBusEO.getBusinessId());

        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");

        if ("rollback".equals(approveCode)) {
            //3-退回
            pcBudgetCostEO.setFlowStatus("3");
            pcBudgetCostEODao.updateByPrimaryKeySelective(pcBudgetCostEO);
        } else {
            //1-审批中
            pcBudgetCostEO.setFlowStatus("1");
            pcBudgetCostEODao.updateByPrimaryKeySelective(pcBudgetCostEO);
        }

        //办理
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        activitiTaskRequestVO.setComment(requestEO.getComment());
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        if ("0".equals(requestEO.getType())) {
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
        } else if ("1".equals(requestEO.getType())) {
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }

        //判断流程是否结束，结束的话，变更业务状态
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            //2-已审批
            pcBudgetCostEO.setFlowStatus("2");
            pcBudgetCostEODao.updateByPrimaryKeySelective(pcBudgetCostEO);
            //流程完成后给相关人员发送消息
            String title = "费用预算审批流程完成";
            limsFileService.processSendMessages(limsFileService.getProcessUsersByTaskId(requestEO.getTaskId()),
                    title, baseBusEO.getBusinessType(), pcBudgetCostEO.getId());
        } else {
            //获取试验任务业务信息
            String title = "费用预算审批流程";
            //获取下一节点人
            String userIds = baseBusEODao.fingNextUserId(requestEO.getProcId());
            if (StringUtils.isNotEmpty(userIds)) {
                String[] ids = userIds.split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, baseBusEO.getBusinessType(), pcBudgetCostEO.getId());
            }
        }
    }

    /**
     * 根据trialTaskId获取PCBudgetCostEO
     * @param trialTaskId
     * @return
     */
    public PCBudgetCostEO getCostByTrialTaskId(String trialTaskId) {
        return pcBudgetCostEODao.getCostByTrialTaskId(trialTaskId);
    }

    /**
     * 将字符串时间格式转换成Date时间格式，参数String类型
     * 比如字符串时间："2017-12-15 21:49:03"
     * 转换后的date时间：Fri Dec 15 21:49:03 CST 2017
     * @param datetime 类型为String
     * @return
     */
    public Date StringToDate(String datetime){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 通过businessId获取baseBus
     * @param businessId
     * @return
     */
    public BaseBusEO getBaseBusByBusinessId(String businessId){
        List<BaseBusEO> baseBusEOList = baseBusEODao.selectByBusinessId(businessId);
        return CollectionUtils.isNotEmpty(baseBusEOList) ? baseBusEOList.get(0) : null;
    }
}
