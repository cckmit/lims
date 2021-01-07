package com.adc.da.trial_report.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.CustomXWPFDocument;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.insproject.dao.InsProjectEODao;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.util.DocWriterUtils;
import com.adc.da.sys.util.InsertPicByJacob;
import com.adc.da.sys.util.JacobWordBean;
import com.adc.da.trial_report.dao.TrialReportEODao;
import com.adc.da.trial_report.dto.TrialScheduleDto;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.entity.ReportArchivefileEO;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_task.dao.TrialTaskEODao;
import com.adc.da.trial_task.dao.TrialtaskInsproEODao;
import com.adc.da.trial_task.dao.TrialtaskSampleEODao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.entity.TrialtaskInsproEO;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;

/**
 * EV-发动机试验管理-试验报告模块
 *
 * @author Administrator
 * @date 2019年8月20日
 */
@Service("EVTrialReportEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EVTrialReportEOService extends TrialReportEOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrialReportEOService.class);

    @Autowired
    private TrialReportEODao trialReportEODao;

    public TrialReportEODao getDao() {
        return trialReportEODao;
    }

    @Value("${file.path}")
    private String filepath;

    @Value("${watermark.name}")
    private String waterMarkName;
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private LimsFileService limsFileService;

    @Value("${mail.address}")
    private String mailAddr;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private InsProjectEODao insProjectEODao;

    @Autowired
    private TrialtaskInsproEODao trialtaskInsproEODao;

    @Autowired
    private TrialtaskSampleEODao trialtaskSampleEODao;

    @Autowired
    private SaCarDataDAO saCarDataDao;

    @Autowired
    private TrialTaskEODao trialTaskEODao;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private UserEODao userEODao;

    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;

    @Autowired
    private TrialReportProcessEOService trialReportProcessEOService;

    @Autowired
    private TrialReportEOService trialReportEOService;
    
    @Autowired
    private TaskService taskService;

    private static String EV_Createby = "EV_Createby_pic";
    private static String EV_Charge = "EV_Charge_pic";
    private static String EV_SectionChief = "EV_SectionChief_pic";
    private static String EV_Minister = "EV_Minister_pic";
    
    

    private static String EV_Createby_Date = "EV_Createby_Date_txt";
    private static String EV_Charge_Date = "EV_Charge_Date_txt";
    private static String EV_SectionChief_Date = "EV_SectionChief_Date_txt";
    private static String EV_Minister_Date = "EV_Minister_Date_txt";
    
    
    public static List<String> EVList = new ArrayList<String>();
    static {
    	EVList.add(EV_Createby); //编写
    	//EVList.add(EV_Charge); //主管 --校对
    	EVList.add(EV_SectionChief); //科长 --审核
    	EVList.add(EV_Minister);//部长 --批准
    	
    	EVList.add(EV_Createby_Date); //编写
    	//EVList.add(EV_Charge_Date); //主管 --校对
    	EVList.add(EV_SectionChief_Date); //科长 --审核
    	EVList.add(EV_Minister_Date);//部长 --批准
    }
    
    
    
    
    
    /**
     * 报告数据填充
     *
     * @param trialReportEO
     * @param insProNames
     * @throws IOException
     * @Title：reportDocFillData
     * @return: void
     * @author： ljy
     * @date： 2019年9月9日
     */
    public void reportDocFillData(TrialReportEO trialReportEO, String insProNames) throws IOException {
        //当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        //根据试验任务id获取试验任务信息
        TrialTaskEO eo = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId());
        //拼接报告填充数据后的路径
        String path = eo.getEvNumber() + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + ".docx";
        //根据附件id , 获取模板路径
        String multipartFile = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getReportFileid()).getSavePath();
//        //根据试验任务id,获取关联样品信息
//        Map<String, String> map = getSampleInfo(trialReportEO.getTrialtaskId());
        //填充数据
        DocWriterUtils docWriterUtils = new DocWriterUtils();
        //建立数据map
        Map<String, String> dataMap = new HashMap<String, String>();
        //报告编号
        dataMap.put("${EV_REPORT_NO}", trialReportEO.getReportNo());
//        //产品名称(样品名称)
//        dataMap.put("${EV_SAMPLE_NAME}", map.get("name"));
//        //产品型号(样品型号)
//        dataMap.put("${EV_SAMPLE_ENGINE_TYPE}", map.get("type"));
//        //试验项目
//        dataMap.put("${EV_TRIALTASK_INSPROJET}", insProNames);
//        //编号(样品编号)
//        dataMap.put("${EV_SAMPLE_NO}", map.get("sampleNo"));
//        //生产日期(样品生产日期)
//        dataMap.put("${EV_SAMPLE_PRODUCE_DATE}", map.get("produceDate"));
//        //试验日期
//        dataMap.put("${EV_TRIALTASK_DATE}", eo.getCreateTime());
//        //参与人员
//        dataMap.put("${EV_PARTICIPANTS}", getParticipants(map.get("scaffoldingId")));
//        //试验地点(试验台架)
//        dataMap.put("${EV_TRIALTASK_ADDRESS}", map.get("scaffoldingName"));
//        //试验依据
//        dataMap.put("${EV_TRIALTASK_ACCORDING}", eo.getAccording());
//        //试验目的
//        dataMap.put("${EV_TRIALTASK_PURPOSE}", eo.getPurpose());
//        //试验任务书编号
//        dataMap.put("${EV_TRIALTASK_NO}", eo.getEvNumber());
        //填充数据
        docWriterUtils.fillDataToWord(filepath + multipartFile, filepath + path, dataMap);
        //将填充完数据的报告数据存储至附件
        TsAttachmentEO attachment = new TsAttachmentEO();
        attachment.setAttachmentName(trialReportEO.getReportNo());
        attachment.setId(UUID.randomUUID());
        attachment.setAttachmentType(ConstantUtils.FILE_WORD_DOCX);
        attachment.setSavePath(path);
        //删除标记  0 未删除;  1删除
        attachment.setDelFlag(0);
        attachment.setCreateBy(UserUtils.getUserId());
        attachment.setCreateTime(date);
        attachment.setUpdateBy(UserUtils.getUserId());
        attachment.setUpdateTime(date);
        tsAttachmentEODao.insert(attachment);
        //将新的id, 更新至报告
        trialReportEO.setReportFileid(attachment.getId());
        trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
    }

    /**
     * 根据组织机构id(台架), 获取该机构下试验技术员
     *
     * @param scaffoldingIds
     * @return
     * @Title：getParticipants
     * @return: String
     * @author： ljy
     * @date： 2019年9月9日
     */
    public String getParticipants(String scaffoldingIds) {
        List<String> participantList = new ArrayList<>();
        //分割逗号
        String[] splits = scaffoldingIds.split(ConstantUtils.COMMA);
        //将string 数组转成list
        List<String> splitsArray = Arrays.asList(splits);
        //循环查询每个组织id下的技术人员
        for (int i = 0; i < splitsArray.size(); i++) {
            List<UserEO> users = orgEOService.listUserEOByOrgId(splitsArray.get(i));
            for (int j = 0; j < users.size(); j++) {
                //如果该用户拥有试验员角色,则返回
        		for (int k = 0; k < users.get(j).getRoleEOList().size(); k++) {
					if(ConstantUtils.EV_RESTER_ROLEID.equals(
							users.get(j).getRoleEOList().get(k).getId())) {
						participantList.add(users.get(j).getUsid());
					}
				}
            }
        }
        //返回
        return StringUtils.join(participantList, ConstantUtils.COMMA);
    }

    /**
     * 试验报告保存
     *
     * @param trialReportEO
     * @param fileReport
     * @param --filePDF
     * @return
     * @throws Exception
     * @Title：save
     * @return: TrialReportEO
     * @author： ljy
     * @date： 2019年8月20日
     */
    public TrialReportEO save(TrialReportEO trialReportEO,
                              MultipartFile fileReport, MultipartFile file) throws Exception {

        trialReportEO = saveReportEntity(trialReportEO, fileReport, file);
        //报告类型
        trialReportEO.setReportType(ConstantUtils.EV);
        //报告编号
        trialReportEO.setReportNo(getReportNo());
        //返回检验项目名称集合,用于通用查询
        String insProNames = "";
        if(StringUtils.isNotEmpty(trialReportEO.getTrialtaskInsproids())) {
        	insProNames = StringUtils.join(insProNames(trialReportEO.getTrialtaskInsproids()),
                    ConstantUtils.COMMA);
        }
        trialReportEO.setInsProNames(insProNames);
        //返回样品VIN码集合,用于通用查询
        String VINCodes = StringUtils.join(VINCode(trialReportEO.getTrialtaskId()),
                ConstantUtils.COMMA);
        trialReportEO.setVINCode(VINCodes);
        TrialTaskEO taskEO = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId());
        //委托人
        trialReportEO.setOriginator(userEODao.selectByPrimaryKey(taskEO.getCreateBy()).getUsname());
        //委托编号
        trialReportEO.setTaskCode(taskEO.getEvNumber());
        //更新
        updateByPrimaryKeySelective(trialReportEO);
        //---------2020.01.19 与杨工确认, 暂不填充报告-------------//
        if (StringUtils.isNotEmpty(fileReport) && fileReport.getSize() > 0) {
//            //---------------进行报告模板的数据填充--------------//
            reportDocFillData(trialReportEO, insProNames);
        }
        if (StringUtils.isNotEmpty(file) && file.getSize() > 0) {
            //将报告涉及的附件保存至报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)
            reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA,
                    trialReportEO.getReportNo(), trialReportEO.getAccessoryFileid(), trialReportEO);
        }
        //--------将试验任务中的试验任务书及试验操作规范同步至附件汇总表
        trialTaskFileToAchive(trialReportEO);
        //保存试验报告和检验项目关联
        if (StringUtils.isNotEmpty(trialReportEO.getTrialtaskInsproids())) {
            updateTrialtaskInsproByInsProId(trialReportEO.getTrialtaskInsproids(),
                    trialReportEO.getId());
        }

        return trialReportEO;
    }

    /**
     * 保存试验报告和检验项目关联
     *
     * @param insproids
     * @param reportId
     * @return void
     * @Title: updateTrialtaskInsproByInsProId
     * @author: ljy
     * @date: 2019年10月28日
     */
    public void updateTrialtaskInsproByInsProId(String insproids, String reportId) {
        String[] splits = insproids.split(ConstantUtils.COMMA);
        List<String> splitsArray = Arrays.asList(splits);
        //关联实体
        for (int i = 0; i < splitsArray.size(); i++) {
            //反向更新EV_TRIALTASK_INSPRO中的REPORT_STATUS,保持一致
            TrialtaskInsproEO trialtaskInsproEO = trialtaskInsproEODao.selectByPrimaryKey(splitsArray.get(i));
            trialtaskInsproEO.setReportStatus("1");
            trialtaskInsproEO.setReportId(reportId);
            trialtaskInsproEODao.updateByPrimaryKeySelective(trialtaskInsproEO);
        }
    }


    /**
     * 将试验任务中的试验任务书及试验操作规范同步至附件汇总表
     *
     * @param trialReportEO
     * @Title：trialTaskFileToAchive
     * @return: void
     * @author： ljy
     * @date： 2019年9月10日
     */
    public void trialTaskFileToAchive(TrialReportEO trialReportEO) {
        //--------将试验任务中的试验任务书及试验操作规范同步至附件汇总表
        TrialTaskEO taskEO = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId());
        if (StringUtils.isNotEmpty(taskEO)) {
            //试验任务书
            if (StringUtils.isNotEmpty(taskEO.getTaskFileid())) {
                reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA,
                        taskEO.getEvNumber(),
                        taskEO.getTaskFileid(), trialReportEO);
            }
            //试验操作规范
            if (StringUtils.isNotEmpty(taskEO.getOperationFileid())) {
                reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA,
                        taskEO.getEvNumber(),
                        taskEO.getOperationFileid(), trialReportEO);
            }
        }
    }

    /**
     * 获取报告选择的检验项目信息
     *
     * @param trialtaskId
     * @return
     * @Title：getInsProInfo
     * @return: Map<String ,   Object>
     * @author： ljy
     * @date： 2019年9月5日
     */
    public List<TrialtaskInsproEO> getInsProInfo(String trialtaskId) {
        //根据试验任务id,获取关联的所有检验项目
        return trialtaskInsproEODao.findTrialtaskInsproByTrialtaskId(trialtaskId);
    }


    /**
     * 删除报告
     *
     * @param id 报告id
     * @Title：deleteReport
     * @return: void
     * @author： ljy
     * @date： 2019年8月26日
     */
    public void deleteReport(String id) {
        //反向更新EV_TRIALTASK_INSPRO中的REPORT_STATUS,保持一致
        List<TrialtaskInsproEO> reportInsProList = trialtaskInsproEODao.selectByReportId(id);
        updateTrialTaskInsProStatus(reportInsProList, "0", "", "");
        deleteReportEntity(id);
    }


    /**
     * 获取报告编号
     *
     * @return
     * @Title：getReportNo
     * @return: String
     * @author： ljy
     * @date： 2019年8月21日
     */
    public String getReportNo() {
        String reportNo = "";
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //获取流水号相关信息
        PipelineNumberEO pNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.EV_REPORT_NO);
        //如果数据库没有改类型code,则新增一个
        if (StringUtils.isEmpty(pNumberEO)) {
            pNumberEO = new PipelineNumberEO();
            pNumberEO.setType(ConstantUtils.EV_REPORT_NO);
            pNumberEO.setTally(0);
            pNumberEO.setParticularYear(dateYear);
            pipelineNumberEODao.insert(pNumberEO);
        }
        //判断数据取出来的最后一个业务单号是不是当年的,如果是当年的 直接+1
        if (dateYear.equals(pNumberEO.getParticularYear())) {
            //获取流水号
            reportNo = PrimaryGenerater.getEVreportNumber(pNumberEO.getTally(), pNumberEO.getParticularYear());
            //更新计数
            pNumberEO.setTally(pNumberEO.getTally() + 1);
        } else { //不是当年的,从0开始
            //获取流水号
            reportNo = PrimaryGenerater.getEVreportNumber(0, dateYear);
            //更新计数及新的年份
            pNumberEO.setTally(1);
            pNumberEO.setParticularYear(dateYear);
        }
        //更新数据库
        pipelineNumberEODao.updateByPrimaryKeySelective(pNumberEO);
        return reportNo;
    }

    /**
     * 根据试验检验项目id,获取关联检验项目名称集合
     *
     * @param trialtaskInsproids 试验检验项目id
     * @return
     * @Title：insProNames
     * @return: List<String>
     * @author： ljy
     * @date： 2019年8月28日
     */
    public List<String> insProNames(String trialtaskInsproids) {
        List<String> insProNameList = new ArrayList<>();
        //根据试验任务id,获取关联集合
        List<String> trialtaskInsproList = Arrays.asList(trialtaskInsproids.split(ConstantUtils.COMMA));
        for (int i = 0; i < trialtaskInsproList.size(); i++) {
        	TrialtaskInsproEO eo = trialtaskInsproEODao.selectByPrimaryKey(trialtaskInsproList.get(i));
            InsProjectEO pro = null;
            if(StringUtils.isNotEmpty(eo)) {
        		pro = insProjectEODao.selectByPrimaryKey(eo.getInsproId());
        	}
            //获取检验项目名称
            String insProName = "";
            if (StringUtils.isNotEmpty(pro)) {
                insProName = pro.getProName();
            }
            insProNameList.add(insProName);
        }
        return insProNameList;
    }


    /**
     * 根据试验任务id,获取关联样品 VIN码集合
     *
     * @param trialTaskId 试验任务id
     * @return
     * @Title：VINCode
     * @return: List<String>
     * @author： ljy
     * @date： 2019年8月28日
     */
    public List<String> VINCode(String trialTaskId) {
        List<String> VINCodeList = new ArrayList<>();
        //根据试验任务id,获取样品关联集合
        List<TrialtaskSampleEO> trialtaskSampleList = trialtaskSampleEODao.
                findTrialtaskSampleByTrialtaskId(trialTaskId);
        for (int i = 0; i < trialtaskSampleList.size(); i++) {
            //循环获取样品信息
            SaCarDataEO sample = saCarDataDao.selectByPrimaryKey(
                    trialtaskSampleList.get(i).getSampleId());
            //获取样品VIN码
            String VINCode = "";
            if (StringUtils.isNotEmpty(sample)) {
                VINCode = sample.getCarVin();
            }
            VINCodeList.add(VINCode);
        }
        return VINCodeList;
    }

    /**
     * 根据试验任务id,获取关联样品信息
     *
     * @param trialTaskId
     * @return
     * @Title：getSampleInfo
     * @return: Map<String ,   String>
     * @author： ljy
     * @date： 2019年9月9日
     */
    public Map<String, String> getSampleInfo(String trialTaskId) {
        //样品名称集合
        List<String> nameList = new ArrayList<>();
        //样品型号集合
        List<String> typeList = new ArrayList<>();
        //样品生产日期集合
        List<String> produceDateList = new ArrayList<>();
        //样品编号集合
        List<String> sampleNoList = new ArrayList<>();
        //负责台架名称
        List<String> scaffoldingIdList = new ArrayList<>();
        //负责台架id
        List<String> scaffoldingNameList = new ArrayList<>();
        //根据试验任务id,获取样品关联集合
        List<TrialtaskSampleEO> trialtaskSampleList = trialtaskSampleEODao.
                findTrialtaskSampleByTrialtaskId(trialTaskId);
        for (int i = 0; i < trialtaskSampleList.size(); i++) {
            //负责台架id
            scaffoldingIdList.add(trialtaskSampleList.get(i).getScaffoldingId());
            //负责台架名称
            scaffoldingNameList.add(trialtaskSampleList.get(i).getScaffoldingName());
            //循环获取样品信息
            SaCarDataEO sample = saCarDataDao.selectByPrimaryKey(
                    trialtaskSampleList.get(i).getSampleId());
            //获取样品
            String name = "";
            String type = "";
            String produceDate = "";
            String sampleNo = "";
            if (StringUtils.isNotEmpty(sample)) {
                //样品名称
                name = sample.getCarName();
                nameList.add(name);
                //样品型号
                type = sample.getCarEngineType();
                typeList.add(type);
                //样品生产日期
                produceDate = sample.getProducedTime();
                produceDateList.add(produceDate);
                //样品编号
                sampleNo = sample.getCarEngineNo();
                sampleNoList.add(sampleNo);
            }
        }
        //将集合形成map返回
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", StringUtils.join(nameList, ConstantUtils.COMMA));
        map.put("type", StringUtils.join(typeList, ConstantUtils.COMMA));
        map.put("produceDate", StringUtils.join(produceDateList, ConstantUtils.COMMA));
        map.put("sampleNo", StringUtils.join(sampleNoList, ConstantUtils.COMMA));
        map.put("scaffoldingId", StringUtils.join(scaffoldingIdList, ConstantUtils.COMMA));
        map.put("scaffoldingName", StringUtils.join(scaffoldingNameList, ConstantUtils.COMMA));
        return map;
    }


    /**
     * 发动机报告审核流程
     *
     * @param request
     * @param requestEO
     * @throws Exception
     * @Title：approvalProcess
     * @return: void
     * @author： ljy
     * @date： 2019年8月27日
     */
    @SuppressWarnings("unchecked")
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取试验任务业务信息
        TrialReportEO trialReportEO = trialReportEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
//原始插签章部分不需要了
//        //插签章
//        TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
//        //报告的pdf
//        String pdfPath = filepath + tsAttachmentEO.getSavePath();
//        pdfPath = pdfPath.replaceAll("\\\\", "/");
//        UserEO userEO = UserUtils.getUser();
//        if (StringUtils.isEmpty(UserUtils.getUserId())) {
//            userEO = userEODao.selectByPrimaryKey(request.getParameter("userId"));
//        }
//        if (StringUtils.isNotEmpty(userEO.getSeal())) {
//            TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(userEO.getSeal());
//            //用户签章
//            if (StringUtils.isNotEmpty(attach)) {
//                String imagePath = filepath + attach.getSavePath();
//                if ("7".equals(trialReportEO.getReportStatus())) {
//                    DocWriterUtils.signPDF(pdfPath, imagePath, "核:");
//                } else if ("8".equals(trialReportEO.getReportStatus())) {
//                    DocWriterUtils.signPDF(pdfPath, imagePath, "准:");
//                } else if ("4".equals(trialReportEO.getReportStatus())) {
//                    DocWriterUtils.signPDF(pdfPath, imagePath, "写:");
//                }
//            }
//        }
        //链接
        String link = baseBusEO.getBusinessType();
        //退回
        if ("rollback".equals(approveCode)) {
            //4-退回
            trialReportEO.setReportStatus("4");
            //更新报告状态
            trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
            //退回给相关人员 发送消息通知、工作日历、邮件
            String title = "报告【" + trialReportEO.getReportNo() + "】退回";
            limsFileService.processSendMessages(limsFileService.
                            getProcessUsersByTaskId(requestEO.getTaskId()),
                    title, link, trialReportEO.getId());
        } else if ("reback".equals(approveCode)) {
            //5-撤回
            trialReportEO.setReportStatus("5");
            //更新报告状态
            trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        } else {
            //流程通过状态加1
            trialReportEO.setReportStatus(String.valueOf(Integer.parseInt(trialReportEO.getReportStatus()) + 1));
            //更新报告状态
            trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
        }
        
        //设置下一节点审批人
        if(StringUtils.isNotEmpty(requestEO.getNextAssignee())){
        	Task task = taskService.createTaskQuery().taskId(requestEO.getTaskId()).singleResult();
        	if("发动机主管".equals(task.getName())){
        		//设置发动机科长节点审批人
        		requestEO.getVariables().put("kz", requestEO.getNextAssignee());
        	}else if("发动机科长".equals(task.getName())){
        		//设置发动机部长节点审批人
        		requestEO.getVariables().put("bz", requestEO.getNextAssignee());
        	}else if("发起人".equals(task.getName())){
        		//设置发动机部长节点审批人
        		requestEO.getVariables().put("zg", requestEO.getNextAssignee());
        	}
        }
        
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        if (StringUtils.isEmpty(UserUtils.getUserId())) {
            activitiTaskRequestVO.setAssignee(request.getParameter("userId"));
        } else {
            activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        }
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if ("0".equals(requestEO.getType())) {
        	//设置节点办理人，覆盖流程图中默认设置的办理人
        	taskService.setAssignee(requestEO.getTaskId(), UserUtils.getUserId());
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        } else if ("1".equals(requestEO.getType())) {
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            //取消认领，避免completeCandidateTask方法中对节点进行认领时报错
            taskService.unclaim(requestEO.getTaskId());
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }


        //判断流程是否结束，结束的话，变更业务状态
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(trialReportEO)) {
                //3-档案室
                trialReportEO.setReportStatus("3");
                //更新报告状态
                trialReportEODao.updateByPrimaryKeySelective(trialReportEO);
//                //获取文件路径
//                TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(trialReportEO.getPdfFileid());
//                //PDF 加签章 PDF及水印PDF都要加签章
//                trialReportEOService.PDFAddStamp(eo.getSavePath(), eo.getWaterMarkPath());
//                //流程走完再添加电子签章
//                trialReportEOService.addAssigneeStamp(requestEO.getBaseBusId());
                
                //流程走完后, 将审批人签名导入Word, 并将Word重新转成新的PDF  并加公司公章及水印
                writeWordAndToPDF(trialReportEO);
                
                //当前日期
                String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
                //反向更新EV_TRIALTASK_INSPRO中的REPORT_STATUS,保持一致
                List<TrialtaskInsproEO> reportInsProList = trialtaskInsproEODao.selectByReportId(trialReportEO.getId());
                updateTrialTaskInsProStatus(reportInsProList, "3", date, trialReportEO.getId());
                //反向更新试验状态
                TrialTaskEO trialTask = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId());
                //报告结束  8-试验完成
                trialTask.setTrialStatus("8");
                trialTask.setFinishdateDelydays(0);
                //0:未延期
                trialTask.setDelyFlag("0");
                trialTaskEODao.updateTrialTask(trialTask);
                //反向更新试验整体状态
                List<TrialReportEO> reportList = trialReportEODao.
                        queryReportByTrialTaskId(trialReportEO.getTrialtaskId());
                //建立是否更新标识
                boolean isFinish = false;
                //循环查询看改该试验委托下 所有检验项目的报告是否都以完成
                for (int i = 0; i < reportList.size(); i++) {
                    //3-档案室
                    if (!"3".equals(reportList.get(i).getReportStatus())) {
                        isFinish = false;
                        break;
                    }
                    isFinish = true;
                }
                if (isFinish) {
                    //当所有报告到档案室时反向更新试验状态为已完成
                    TrialTaskEO trialTaskEO = trialTaskEODao.
                            selectByPrimaryKey(trialReportEO.getTrialtaskId());
                    //1-已完成
                    trialTaskEO.setTrialReportStatus("1");
                    trialTaskEODao.updateTrialTask(trialTaskEO);
                }
            }
        } else {
            //获取报告业务信息
            String title = "报告【" + trialReportEO.getReportNo() + "】审批流程";
            //获取下一节点人
            if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, link, trialReportEO.getId());
            }
        }
    }

    
    public static String String2Date(String strDate) throws ParseException {
    	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
    	Date strtodate = formatter1.parse(strDate);
    	String dateString = formatter.format(strtodate);
    	return dateString;
    }
    
    
    public void writeWordAndToPDF(TrialReportEO trialReportEO) throws Exception {
    	//将签章插入Word
    	
    	//流程信息
		String actProcId = trialReportEODao.selectActProcIdById(trialReportEO.getId());
		
		String CREATEBY = trialReportEO.getCreateBy();
		String CREATEBY_PATH = getSealPath(CREATEBY);
		String CREATEBY_DATE = String2Date(trialReportEO.getCreateTime());
    	String EV_CHARGE = "";
    	String EV_SECTIONCHIEF = "";
    	String EV_MINISTER = "";
    	
    	String EV_CHARGE_PATH = "";
    	String EV_SECTIONCHIEF_PATH = "";
    	String EV_MINISTER_PATH = "";
    	
    	String EV_CHARGE_DATE = "";
    	String EV_SECTIONCHIEF_DATE = "";
    	String EV_MINISTER_DATE = "";
    	
    	if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1) {
   		 	List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
   		 	//主管  --校对
   		 	EV_CHARGE = list.get(2).getAssignee();
   		 	EV_CHARGE_PATH = getSealPath(EV_CHARGE);
   		 	EV_CHARGE_DATE = String2Date(list.get(2).getFinishTimeStr());
   		 	//科长  --审核
   		 	EV_SECTIONCHIEF = list.get(1).getAssignee();
   		 	EV_SECTIONCHIEF_PATH = getSealPath(EV_SECTIONCHIEF);
   		 	EV_SECTIONCHIEF_DATE = String2Date(list.get(1).getFinishTimeStr());
   		 	//部长  --批准
   		 	EV_MINISTER = list.get(0).getAssignee();
   		 	EV_MINISTER_PATH = getSealPath(EV_MINISTER);
   		 	EV_MINISTER_DATE = String2Date(list.get(0).getFinishTimeStr());
        }
		
        Map<String, String> map = new HashMap<String, String>();
        //编写
        map.put(EV_Createby, CREATEBY_PATH);
        //主管  --校对
        map.put(EV_Charge, EV_CHARGE_PATH);
        //科长  --审核
        map.put(EV_SectionChief, EV_SECTIONCHIEF_PATH);
        //部长  --批准
        map.put(EV_Minister, EV_MINISTER_PATH);

        //编写
        map.put(EV_Createby_Date, CREATEBY_DATE);
        //主管  --校对
        map.put(EV_Charge_Date, EV_CHARGE_DATE);
        //科长  --审核
        map.put(EV_SectionChief_Date, EV_SECTIONCHIEF_DATE);
        //部长  --批准
        map.put(EV_Minister_Date, EV_MINISTER_DATE);
        
        TsAttachmentEO attachment = tsAttachmentEODao
        .selectByPrimaryKey(trialReportEO.getReportFileid());
        
        String taskNo = trialTaskEODao.selectByPrimaryKey(trialReportEO.getTrialtaskId()).getEvNumber();
        
        //String docNewPath = taskNo + "/" + trialReportEO.getReportNo() +  "/" + trialReportEO.getReportNo() + UUID.randomUUID() + ".docx";
        InsertPicByJacob jacob = new InsertPicByJacob();
        JacobWordBean bean = new JacobWordBean(false);
        boolean flag = bean.insertMapByJacob(filepath + attachment.getSavePath(), "", map);
        
        if(flag) {
        	String pdfNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + ".pdf";
        	String waterMarkNewPath = taskNo + "/" + trialReportEO.getReportNo() + "/" + UUID.randomUUID() + "_watermark.pdf";
        	//将Word转成 PDF
        	if(jacob.convertWord(filepath + attachment.getSavePath(), filepath + pdfNewPath)) {
        		TsAttachmentEO pdf = tsAttachmentEODao
        				.selectByPrimaryKey(trialReportEO.getPdfFileid());
        		pdf.setSavePath(pdfNewPath);
        		pdf.setWaterMarkPath(waterMarkNewPath);
        		tsAttachmentEODao.updateByPrimaryKey(pdf);
        		//PDF 加签章 PDF及水印PDF都要加签章
        		trialReportEOService.PDFAddStamp(pdfNewPath, waterMarkNewPath);
        	}
        }
        
    }
    
    
    
    public String getSealPath(String usid) {
    	String imagePath = "";
    	UserEO userEO = userEODao.selectByPrimaryKey(usid);
		if(StringUtils.isNotEmpty(userEO.getSeal())){
			TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(userEO.getSeal());
			if (StringUtils.isNotEmpty(attach)) {
				imagePath = filepath + attach.getSavePath();
//				imagePath = imagePath.replaceAll("\\\\", "/");
                imagePath = imagePath.replaceAll("/", "\\\\");
			}
		}
		return imagePath;
    }
    
    
    
    /**
     * 反向更新EV_TRIALTASK_INSPRO中的REPORT_STATUS,保持一致
     *
     * @param --ids
     * @param status
     * @param finishDate
     * @Title：updateTrialTaskInsProStatus
     * @return: void
     * @author： ljy
     * @date： 2019年9月3日
     */
    public void updateTrialTaskInsProStatus(List<TrialtaskInsproEO> reportInsProList,
                                            String status, String finishDate, String reportId) {
        //循环获取所有涉及的试验检验项目信息
        for (TrialtaskInsproEO trialtaskInsproEO : reportInsProList) {
            //报告状态
            trialtaskInsproEO.setReportStatus(status);
            //报告结束时间
            trialtaskInsproEO.setReportFinishTime(finishDate);
            //报告id
            trialtaskInsproEO.setReportId(reportId);
            //更新
            trialtaskInsproEODao.updateByPrimaryKeySelective(trialtaskInsproEO);
        }

    }

    /**
     * 任务执行--进度
     *
     * @param trialScheduleDto
     */
    public void createScedule(TrialScheduleDto trialScheduleDto) {
        TrialReportProcessEO eo = trialReportProcessEOService.saveSceduleDto(trialScheduleDto);
        String status = eo.getTrialprojectStatus();
        // 当进度试验状态选择进行中、已取消、已暂停时，修改任务信息状态为进行中 5
        TrialtaskInsproEO trialtaskInsproEO = trialtaskInsproEODao.selectByPrimaryKey(
                trialScheduleDto.getTrialtaskInsproId());
        if ("0".equals(status) || "1".equals(status) || "2".equals(status)) {
            trialTaskEODao.updateTrialTaskStatus(5, trialtaskInsproEO.getTrialtaskId());
        }
        // 当进度试验状态选择已结束时，修改任务信息状态为已结束 6
        if ("3".equals(status)) {
            trialTaskEODao.updateTrialTaskStatus(6, trialtaskInsproEO.getTrialtaskId());
        }
    }

    public ResponseMessage delTrialData(String archiveFileId) {
        try {
            ReportArchivefileEO reportArchivefileEO = reportArchivefileEOService.selectByPrimaryKey(archiveFileId);
            if (reportArchivefileEO != null) {
                // 删除服务器文件，先查询附件信息
                TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(reportArchivefileEO.getReportFileid());
                if (eo != null) {
                    String tempPath = this.filepath;
                    if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                        tempPath = this.filepath + File.separator;
                    }
                    String fullPath = tempPath + eo.getSavePath();
                    File file = new File(fullPath);
                    file.delete();
                }
                // 通过ID删除EV_TRIALREPORT_ARCHIVEFILE数据
                reportArchivefileEOService.deleteByPrimaryKey(archiveFileId);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Result.success("-1", "删除失败");
        }
    }

}