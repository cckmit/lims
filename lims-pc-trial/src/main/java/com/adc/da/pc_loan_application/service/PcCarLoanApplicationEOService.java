package com.adc.da.pc_loan_application.service;

import com.adc.da.acttask.service.ActProcessService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;
import com.adc.da.pc_loan_application.page.PcCarLoanApplicationEOPage;
import com.adc.da.pc_loan_application.vo.PcCarLoanApplicationVO;
import com.adc.da.pc_loan_application.vo.PcCarLoanFindVo;
import com.adc.da.pc_loan_application.vo.PcDisPlayVo;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.adc.da.base.service.BaseService;
import com.adc.da.pc_loan_application.dao.PcCarLoanApplicationEODao;
import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>PC_CAR_LOAN_APPLICATION PcCarLoanApplicationEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcCarLoanApplicationEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class    PcCarLoanApplicationEOService extends BaseService<PcCarLoanApplicationEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcCarLoanApplicationEOService.class);

    @Autowired
    private PcCarLoanApplicationEODao dao;
    @Autowired
    private PcCarLoanInformationEOService pcCarLoanInformationEOService;

    @Autowired
    private DocUtil docUtil;


    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private DicTypeEOService dicTypeEOService;
    @Autowired
    private DicEOService dicEOService;

    private String currTime;

    @Autowired
    private UserEOService userEOService;


    @Autowired
    private OrgEOService orgEOService;

    public PcCarLoanApplicationEODao getDao() {
        return dao;
    }

    public List<PcCarLoanFindVo> findPcCarLoanApplication(String trialTaskID){
        return dao.findPcCarLoanApplication(trialTaskID);

    }


//    public String findPcCarIDByTaskID(String task_id) {
//        return dao.findPcCarIDByTaskID(task_id);
//    }



    /**
     * 导出
     * @param pcCarLoanFindVo
     */
    public void exportDoc(HttpServletResponse response, HttpServletRequest request, PcCarLoanFindVo pcCarLoanFindVo ){
        String experimentalType = pcCarLoanFindVo.getExperimentalType();
        String vehicleType = pcCarLoanFindVo.getVehicleType();
        String listType = pcCarLoanFindVo.getListType();


        DicTypeEO borrowTheCarAlone1 = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId("borrowTheCarAlone", listType);
        if (borrowTheCarAlone1 != null) {
            pcCarLoanFindVo.setListType(borrowTheCarAlone1.getDicTypeName());
        }


        DictionaryEO vehicleType1 = dicEOService.getDictionaryByDicCode(vehicleType);
        if (vehicleType1 != null) {
            pcCarLoanFindVo.setVehicleType(vehicleType1.getDictionaryName());
        }

        DictionaryEO experimentalType1 = dicEOService.getDictionaryByDicCode(experimentalType);
        if (experimentalType1 != null) {
            pcCarLoanFindVo.setExperimentalType(experimentalType1.getDictionaryName());
        }

        Map<String, Object> dataMap = new HashMap();
        dataMap.put("loanCarCode", pcCarLoanFindVo.getLoanCarCode() == null ? "" : pcCarLoanFindVo.getLoanCarCode());
        dataMap.put("listType", pcCarLoanFindVo.getListType()==null?"":pcCarLoanFindVo.getListType());
        dataMap.put("applicant", pcCarLoanFindVo.getApplicant()==null?"":pcCarLoanFindVo.getApplicant());
        dataMap.put("department", pcCarLoanFindVo.getDepartment()==null?"":pcCarLoanFindVo.getDepartment());
        dataMap.put("contactInfo", pcCarLoanFindVo.getContactInfo()==null?"":pcCarLoanFindVo.getContactInfo());
        dataMap.put("purpose", pcCarLoanFindVo.getPurpose()==null?"":pcCarLoanFindVo.getPurpose());
        dataMap.put("itemCategory", pcCarLoanFindVo.getItemCategory()==null?"":pcCarLoanFindVo.getItemCategory());
        dataMap.put("experimentalArea", pcCarLoanFindVo.getExperimentalArea()==null?"":pcCarLoanFindVo.getExperimentalArea());
        List<DicTypeEO> typeOfTest = dicTypeEOService.getDicTypeByDictionaryCode("typeOfTest");
        String experimental = pcCarLoanFindVo.getExperimentalType();
        for (DicTypeEO dicTypeEO : typeOfTest){
            if (dicTypeEO.getDicTypeCode().trim().equals(experimental.trim())){
                experimental = dicTypeEO.getDicTypeName();
            }
        }
        dataMap.put("experimentalType", experimental==null?"":experimental);
        dataMap.put("startDate", pcCarLoanFindVo.getStartDate()==null?"":pcCarLoanFindVo.getStartDate());
        dataMap.put("endDate", pcCarLoanFindVo.getEndDate()==null?"":pcCarLoanFindVo.getEndDate());
        List<DicTypeEO> vehicleTypeS = dicTypeEOService.getDicTypeByDictionaryCode("vehicleType");
        String vehicle = pcCarLoanFindVo.getVehicleType();
        for (DicTypeEO dicTypeEO : vehicleTypeS){
            if (dicTypeEO.getDicTypeCode().trim().equals(vehicle.trim())){
                vehicle = dicTypeEO.getDicTypeName();
            }
        }
        dataMap.put("vehicleType", vehicle==null?"":vehicle);
        dataMap.put("experimentalRoute", pcCarLoanFindVo.getExperimentalRoute()==null?"":pcCarLoanFindVo.getExperimentalRoute());
        dataMap.put("projectNumber", pcCarLoanFindVo.getProjectNumber()==null?"":pcCarLoanFindVo.getProjectNumber());
        List<PcCarLoanInformationEO> list = pcCarLoanFindVo.getList();
        ArrayList<PcCarLoanInformationEO> listS = new ArrayList<>();
        for (PcCarLoanInformationEO pcCarLoanInformationEO : list){
            String prototypeStatus = pcCarLoanInformationEO.getPrototypeStatus();
            if (StringUtils.isNotEmpty(prototypeStatus)){
                if (prototypeStatus.trim().equals("0")){
                    prototypeStatus = "退样";
                }else if (prototypeStatus.trim().equals("1")){
                    prototypeStatus = "接收";
                }else if (prototypeStatus.trim().equals("2")){
                    prototypeStatus = "领样";
                }else if (prototypeStatus.trim().equals("3")){
                    prototypeStatus = "在库";
                }else if (prototypeStatus.trim().equals("4")){
                    prototypeStatus = "报废";
                }else if (prototypeStatus.trim().equals("5")){
                    prototypeStatus = "待收样";
                }else {
                    prototypeStatus = "封存";
                }
            }
            pcCarLoanInformationEO.setPrototypeStatus(prototypeStatus);
            listS.add(pcCarLoanInformationEO);
        }
        dataMap.put("dataList", listS);


        String fileName = "借车申请单"+pcCarLoanFindVo.getLoanCarCode();
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;

        //填充数据 并导出
        docUtil.createDoc(dataMap, "application", response, request,
                fileName, fileExtend);
    }




    /**
     * 新增
     * @param pcCarLoanApplicationEO
     * @return
     * @throws Exception
     */
    public int insertSelective(PcCarLoanApplicationEO pcCarLoanApplicationEO) throws Exception {
        return dao.insertSelective(pcCarLoanApplicationEO);
    }

    /**
     * 编辑
     * @param pcCarLoanApplicationEO
     * @return
     */
    public int updateByPrimaryKeySelective(PcCarLoanApplicationEO pcCarLoanApplicationEO){
        return dao.updateByPrimaryKeySelective(pcCarLoanApplicationEO);
    }


    /**
     * 启动流程
     * @param pcCarLoanApplicationVO
     * @throws Exception
     */
    public String startProcess(PcCarLoanApplicationVO pcCarLoanApplicationVO) throws Exception {
        PcCarLoanApplicationVO pcCarVo = updatePcCarVo(pcCarLoanApplicationVO, '1');
        PcCarLoanApplicationEO pcCarLoanApplicationEO = pcCarVo.getPcCarLoanApplicationEO();
        String pvOrcv = "";
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if(StringUtils.isNotEmpty(flag)){
            if ("1".equals(flag)){
                pvOrcv = ConstantUtils.PC_CAR_LOAN_FROM_TYPE;
            }else if("0".equals(flag)){
                pvOrcv = ConstantUtils.CV_CAR_LOAN_FROM_TYPE;
            }
        }
        String taskOrPlan = pcCarLoanApplicationEO.getTaskOrPlan();
        String title = "试验任务-借车申请流程";
        if (StringUtils.isNotEmpty(taskOrPlan) && "1".equals(taskOrPlan)){
            title = "执行计划-借车申请流程";
        }
        return actProcessService.startProcess(pcCarLoanApplicationEO.getId(),pvOrcv,title);
    }


    /**
     * 审批流程
     * @param requestEO
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO) throws Exception {
        actProcessService.approvalProcess(request, requestEO, baseBusEO, dao);

    }


    /**
     * 判断流程是否结束，结束的话，变更业务状态
     * @param procId
     * @param pcCarLoanId
     */
    public void isFinishied(String procId, String pcCarLoanId) {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(pcCarLoanId)) {
                //已审批
                dao.changeStatus(pcCarLoanId, "5");
            }
        }
    }

    /**
     * 代码冗余，分装，判断是新增还是修改
     *
     * @param pcCarLoanApplicationVO 需要处理的数据
     * @return
     * @throws Exception
     */
    public PcCarLoanApplicationVO updatePcCarVo(PcCarLoanApplicationVO pcCarLoanApplicationVO, char flag) throws Exception {
        PcCarLoanApplicationEO pcCarLoanApplicationEO = pcCarLoanApplicationVO.getPcCarLoanApplicationEO();
        List<PcCarLoanInformationEO> list = pcCarLoanApplicationVO.getList();
        //将流程状态设置为审批中
        switch (flag) {
            case '0':
                pcCarLoanApplicationEO.setProcessStatus("0");
                break;
            case '1':
                pcCarLoanApplicationEO.setProcessStatus("1");
                break;
        }
        //判断是新增提交还是编辑提交
        if (pcCarLoanApplicationEO.getId() == null || pcCarLoanApplicationEO.getId().equals("")) {

            insertSelective(pcCarLoanApplicationEO);

        } else {

            updateByPrimaryKeySelective(pcCarLoanApplicationEO);

        }
//        String pcCarID = findPcCarIDByTaskID(pcCarLoanApplicationEO.getTaskId());
        String pcCarID = pcCarLoanApplicationEO.getId();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLoadApplicationId(pcCarID);
        }
        pcCarLoanInformationEOService.insertPcCarLoadInformationEO(list);

        return pcCarLoanApplicationVO;
    }

    public PcCarLoanFindVo findPcCarDataById(String id) {
        return dao.findPcCarDataById(id);
    }

    public void deleteTwoByPrimaryKey(String id) {
        pcCarLoanInformationEOService.deleteLoanInformation(id);
        dao.deleteByPrimaryKey(id);
    }

    /**
     * 分页查询借车单类型、申请人、申请部门、借用车辆底盘号、借车用途
     * @param page
     * @return
     */
    public List<PcDisPlayVo>  findPcDisPlayVo(PcCarLoanApplicationEOPage page) {
        List<PcCarLoanApplicationEO> list;
        List<PcDisPlayVo> result = new ArrayList<>();
        try {
            list = queryByPage(page);
            for (PcCarLoanApplicationEO eo : list) {
                PcDisPlayVo disPlayVo = new PcDisPlayVo();
                StringBuilder chassisCode = new StringBuilder();
                disPlayVo.setApplicant(eo.getApplicant());
                disPlayVo.setApplicantId(eo.getApplicantId());
                disPlayVo.setDepartment(eo.getDepartment());
                disPlayVo.setDepartmentId(eo.getDepartmentId());
                disPlayVo.setId(eo.getId());
                disPlayVo.setListType(eo.getListType());
                disPlayVo.setPurpose(eo.getPurpose());
                disPlayVo.setProcessStatus(eo.getProcessStatus());
                disPlayVo.setLoanCarCode(eo.getLoanCarCode());
                List<PcCarLoanInformationEO> InfoEOs = pcCarLoanInformationEOService.findAllEOByLoanID(eo.getId());
                for (PcCarLoanInformationEO infoEO : InfoEOs) {
                    String code = infoEO.getChassisCode();
                    if ( code != null && !"".equals(code)){
                        chassisCode.append(infoEO.getChassisCode() + ",");
                    }
                }
                String string = chassisCode.toString();
                eo.setChassisCode(string);
                updateByPrimaryKeySelective(eo);
                if (!"".equals(string)) {
                    disPlayVo.setChassisCode(chassisCode.deleteCharAt(chassisCode.lastIndexOf(",")).toString());
                }

                result.add(disPlayVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询借车单的数量和生成借车单编号
     */
    public String findPcCarIDByTaskID(String taskId) {
        currTime = DateUtils.dateToString(new Date(), "yyyy-MM");
        Integer count = dao.findLoanCarCodeBy(taskId);
        StringBuilder sb = new StringBuilder();
        sb.append("JCD-");
        sb.append(currTime).append("-");
        if (count < 10) {
            sb.append("000");
        } else if (count < 100) {
            sb.append("00");
        } else if (count < 1000) {
            sb.append("0");
        }
        sb.append(count+1);
        return sb.toString();
    }

    /**
     * 模糊分页查询
     */
    public List<PcCarLoanApplicationEO> fuzzyQuery(PcCarLoanApplicationEOPage page) {
        String applicant = page.getApplicant();
        String department = page.getDepartment();
        String purpose = page.getPurpose();
        String chassisCode = page.getChassisCode();
        if (applicant != null) {
            page.setApplicant("%" + applicant + "%");
            page.setApplicantOperator("like");
        }
        if (department != null) {
            page.setDepartment("%" + department + "%");
            page.setDepartmentOperator("like");

        }
        if (purpose != null) {
            page.setPurpose("%" + purpose + "%");
            page.setPurposeOperator("like");
        }
        if (chassisCode != null) {
            page.setChassisCode("%" + chassisCode + "%");
            page.setChassisCodeOperator("like");
        }
        List<PcCarLoanApplicationEO> list = null;
        try {
            list = queryByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 根据借车单编号获取借车单类型
     */
    public String findListTypeByTaskIdAndLoanCarCode(String trialTaskID, String loanCarCode){
        return dao.findListTypeByTaskIdAndLoanCarCode(trialTaskID, loanCarCode);
    }
}
