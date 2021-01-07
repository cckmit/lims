package com.adc.da.pc_announcement.service;

import static com.adc.da.sys.service.UserEOService.getCellValueByCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.pc_announcement.VO.AnnPlanProjectVO;
import com.adc.da.pc_announcement.VO.AnnPlanVO;
import com.adc.da.pc_announcement.dao.AnnPlanEODao;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_announcement.page.AnnPlanEOPage;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;


/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN AnnPlanEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("annPlanEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnnPlanEOService extends BaseService<AnnPlanEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanEOService.class);

    @Autowired
    private AnnPlanEODao dao;
    @Autowired
    private UserEOService userEOService;
    @Autowired
    private AnnPlanProjectEOService annPlanProjectEOService;
    @Autowired
    private AnnPlanProgramEOService annPlanProgramEOService;
    @Autowired
    private DicTypeEOService dicTypeEOService;
    @Autowired
    private DicEOService dicEOService;

    public AnnPlanEODao getDao() {
        return dao;
    }

    /**
     * 查询出对应创建人的所有计划
     * @param createBy
     * @return
     */
    public List<AnnPlanEO> getAnnPlanList(String createBy) {
        return dao.getAnnPlanList(createBy);
    }

    /**
     * 新增实验计划
     * @param annPlanEO
     */
    public void insertPlan(AnnPlanEO annPlanEO) {
        //设置删除标记为“未删除”
        annPlanEO.setDelFlag("0");
        dao.insertSelective(annPlanEO);
    }

    /**
     * 查出对应计划的详情
     * @param planID
     * @return
     */
    public AnnPlanVO getAnnPlanMes(String planID) {
        AnnPlanEO annPlan = dao.selectByPrimaryKey(planID);
        List<AnnPlanProjectEO> annPlanProjectEOS = annPlanProjectEOService.getAnnPlanProjectList(planID);
        for (AnnPlanProjectEO annPlanProjectEO : annPlanProjectEOS){
            String supplierID = annPlanProjectEO.getPjSupplier();
            String supplierName = annPlanProjectEOService.getSupplierNameByID(supplierID);
            annPlanProjectEO.setPjSupplierName(supplierName);
        }
        AnnPlanVO annPlanVO = new AnnPlanVO();
        BeanUtils.copyProperties(annPlan,annPlanVO);
        annPlanVO.setAnnPlanProjectList(annPlanProjectEOS);
        return annPlanVO;
    }
    
    /**
     * 解析通用查询关键字
     * @param page
     */
    public void analyzeSearchPhrase(AnnPlanEOPage page) {
    	if(StringUtils.isNotEmpty(page.getSearchPhrase()) && StringUtils.isNotEmpty(page.getSearchPhrase().trim())){
            String searchPhrase = page.getSearchPhrase().trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<Map<String, Object>> list = new ArrayList<>();
            while (dateMatcher.find()) {
            	Map<String, Object> map = new HashMap<>();
            	//通用查询关键字
                String search = dateMatcher.group();
                map.put("search", search);
                //根据search获取申报来源关键字
                List<String> sourceKeys = dicEOService.getTypeCodesBySearch("planSource", search);
                map.put("sourceKeys", sourceKeys);
                //根据search获取计划状态关键字
                List<String> statusKeys = dicEOService.getTypeCodesBySearch("statePlan", search);
                map.put("statusKeys", statusKeys);
                list.add(map);
            }
            page.setSearchMap(list);
        }
    }

    /**
     * 查询对应创建人的计划列表
     * @param page
     * @return
     */
    public List<AnnPlanEO> getAnnPlanPage(AnnPlanEOPage page) {
        return dao.getAnnPlanPage(page);
    }

    /**
     * 查询对应创建人的计划总条数
     * @param page
     * @return
     */
    public Integer queryForAnnPlanCount(AnnPlanEOPage page) {
        return dao.queryForAnnPlanCount(page);
    }

    /**
     * 查询出分配给对应工程师的计划
     * @param page
     * @return
     */
    public List<AnnPlanEO> findAnnPlanForEngineer(AnnPlanEOPage page,String userID) {
        page.setPlCreateBy(userID);
        return dao.findAnnPlanForEngineer(page);
    }
    /**
     * 查询出分配给对应工程师的计划总条数
     * @param page
     * @return
     */
    public Integer queryForEngineerAnnPlanCount(AnnPlanEOPage page,String userID) {
        page.setPlCreateBy(userID);
        return dao.queryForEngineerAnnPlanCount(page);
    }

    /**
     * 根据用户真实姓名来查出所有的用户
     * @param name
     * @return
     */
    public List<UserEO> getUserListByName(String name) {
        return  userEOService.getUserByUserName(name);
    }

    /**
     * 删除计划，包括删除计划本身，以及计划所关联的申报项目，以及申报项目所关联的检验方案
     * 计划，申报项目，检验方案，依次是一对多关系。从下往上依次删除，避免出错
     * @param planID
     */
    public void delPlan(String planID) {
        //申报项目ID数组
        List<String> projectIdList = annPlanProjectEOService.getIDListByPlanID(planID);
        //检验方案的ID数组
        List<String> programIdList = new ArrayList<>();
        if (projectIdList.size() != 0){
            programIdList = annPlanProgramEOService.getIdListByPjIDList(projectIdList);
        }
        //先物理删除所有相关的检验方案
        if (programIdList.size() != 0){
            annPlanProgramEOService.delByIdList(projectIdList);
        }
        //物理删除所有相关的申报项目
        if (projectIdList.size() != 0){
            annPlanProjectEOService.delByIdList(projectIdList);
        }
        //逻辑删除试验计划
        AnnPlanEO annPlanEO = new AnnPlanEO();
        annPlanEO.setId(planID);
        annPlanEO.setDelFlag("1");
        dao.updateByPrimaryKeySelective(annPlanEO);
    }

    /**
     * 批量导入公告试验计划
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage importAnnPlan(MultipartFile file) throws Exception {
         ResponseMessage result = new ResponseMessage();
        if (file.isEmpty()) {
            logger.info("文件不存在，导入失败......", new FileNotFoundException());
            result = Result.error("-1", "文件不存在，导入失败");
            return result;
        }
        FileInputStream fis = (FileInputStream) file.getInputStream();
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        //记录错误信息。
        StringBuilder errorMess = new StringBuilder();
        List<AnnPlanProjectEO> annPlanProjectList = new ArrayList<>();
        //类别的数据字典
        List<DicTypeEO> projectPlanCategory = dicTypeEOService.getDicTypeByDictionaryCode("ProjectPlanCategory");
        //从第二行开始解析
        outLoop:for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                //车型
                String pjModel = getCellValueByCell(row.getCell(0));
                //类别
                String pjType = getCellValueByCell(row.getCell(1));
                //任务来源
                String pjTaskSource = getCellValueByCell(row.getCell(2));
                //立项审批表
                String pjApprovalForm = getCellValueByCell(row.getCell(3));
                //试验工程师
                String pjEngineerName = getCellValueByCell(row.getCell(4));
                //公告批次
                String pjAnnBatch = getCellValueByCell(row.getCell(5));
                //车辆计划到达时间
                String pjCarTimePlan = getCellValueByCell(row.getCell(6));
                //车辆实际到达时间
                String pjCarTimeActual = getCellValueByCell(row.getCell(7));
                //技术参数计划获取时间
                String pjParamTimePlan = getCellValueByCell(row.getCell(8));
                //技术参数实际获取时间
                String pjParamTimeActual = getCellValueByCell(row.getCell(9));
                if (StringUtils.isEmpty(pjModel)) {
                    errorMess.append("导入失败！第" +  (i + 1) + "行车型不能为空!<br> ");
                }
                if (StringUtils.isEmpty(pjType)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行类别不能为空!<br> ");
                }
                if (StringUtils.isEmpty(pjEngineerName)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行试验工程师不能为空!<br> ");
                }else {
                    boolean check = false;
                    List<UserEO> userList = userEOService.getUserByCode("CV_TrialEngineer");
                    for (UserEO user : userList){
                        if (pjEngineerName.trim().equals(user.getUsname())){
                            check = true;
                        }
                    }
                    if (!check){
                        errorMess.append("导入失败！第" +  (i + 1) + "行试验工程师填写不正确!<br> ");
                    }
                }
                boolean flag = false;
                if (StringUtils.isNotEmpty(pjType)){//填写了类别就验证，不正确就提示错误
                    for (DicTypeEO dicTypeEO : projectPlanCategory){
                        if (pjType.trim().equals(dicTypeEO.getDicTypeName())){
                            pjType = dicTypeEO.getDicTypeCode();
                            flag = true;
                        }
                    }
                }
                if (!flag){
                    errorMess.append("导入失败！第" +  (i + 1) + "行类别填写错误!<br> ");
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (StringUtils.isNotEmpty(pjCarTimePlan)){//填写了车辆计划到达时间就验证，不正确就提示错误
                    try {
                        Date date1 = simpleDateFormat.parse(pjCarTimePlan.trim());
                        pjCarTimePlan = simpleDateFormat.format(date1);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行车辆计划到达时间填写不正确!<br> ");
                    }
                }
                if (StringUtils.isNotEmpty(pjCarTimeActual)){//填写了车辆实际到达时间就验证，不正确就提示错误
                    try {
                        Date date2 = simpleDateFormat.parse(pjCarTimeActual.trim());
                        pjCarTimeActual = simpleDateFormat.format(date2);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行车辆实际到达时间填写不正确!<br> ");
                    }
                }
                if (StringUtils.isNotEmpty(pjParamTimePlan)){//填写了技术参数计划获取时间就验证，不正确就提示错误
                    try {
                        Date date3 = simpleDateFormat.parse(pjParamTimePlan.trim());
                        pjParamTimePlan = simpleDateFormat.format(date3);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行技术参数计划获取时间填写不正确!<br> ");
                    }
                }
                if (StringUtils.isNotEmpty(pjParamTimeActual)){//填写了技术参数实际获取时间就验证，不正确就提示错误
                    try {
                        Date date4 = simpleDateFormat.parse(pjParamTimeActual.trim());
                        pjParamTimeActual = simpleDateFormat.format(date4);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行技术参数实际获取时间填写不正确!<br> ");
                    }
                }
                AnnPlanProjectEO annPlanProjectEO = new AnnPlanProjectEO();
                annPlanProjectEO.setPjModel(pjModel);
                annPlanProjectEO.setPjType(pjType);
                annPlanProjectEO.setPjTaskSource(pjTaskSource);
                annPlanProjectEO.setPjApprovalForm(pjApprovalForm);
                annPlanProjectEO.setPjEngineerName(pjEngineerName);
                annPlanProjectEO.setPjAnnBatch(pjAnnBatch);
                annPlanProjectEO.setPjCarTimePlan(pjCarTimePlan);
                annPlanProjectEO.setPjCarTimeActual(pjCarTimeActual);
                annPlanProjectEO.setPjParamTimePlan(pjParamTimePlan);
                annPlanProjectEO.setPjParamTimeActual(pjParamTimeActual);
                annPlanProjectList.add(annPlanProjectEO);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        if (errorMess.length() == 0){
            result = Result.success("0", "批量导入成功",annPlanProjectList);
            return result;
        }else {
            //导入失败--提示错误信息
            result = Result.error("-1", errorMess.toString());
            return result;
        }
    }

    public void checkBatchExport(HttpServletResponse response, HttpServletRequest request, String id) throws IOException {
        //工作空间
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置标题栏
        createTitle(workbook, sheet);
        //填充数据
        createAnnPlan(workbook, sheet,id);
        //生成Excel文件名称
        //获取当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + "公告试验计划导出";
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_EXTEND_XLSX;
        //浏览器下载
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        response.setContentType("application/octet-stream");
        //火狐浏览器需特殊处理
        if(agent.contains(ConstantUtils.FIREFOX)) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + fileExtend);
        }else {
            response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName) + fileExtend);
        }
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }
    /**
     * 公告试验计划导出
     * 填充表格标题栏
     * @param workbook
     * @param sheet
     */
    private void createTitle(Workbook workbook, Sheet sheet) {
        //公告试验计划导出表头
        //标题栏
        Row row = sheet.createRow(0);
        //设置列宽
        sheet.setColumnWidth(0,20*256);
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,20*256);
        sheet.setColumnWidth(4,20*256);
        sheet.setColumnWidth(5,20*256);
        sheet.setColumnWidth(6,20*256);
        sheet.setColumnWidth(7,20*256);
        sheet.setColumnWidth(8,20*256);
        sheet.setColumnWidth(9,20*256);
        sheet.setColumnWidth(10,20*256);
        sheet.setColumnWidth(11,20*256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第0个 车型
        cell = row.createCell(0);
        cell.setCellValue("车型");
        cell.setCellStyle(style);
        //第1个 类别
        cell = row.createCell(1);
        cell.setCellValue("类别");
        cell.setCellStyle(style);
        //第2个 任务来源
        cell = row.createCell(2);
        cell.setCellValue("任务来源");
        cell.setCellStyle(style);
        //第3个 立项审批表
        cell = row.createCell(3);
        cell.setCellValue("立项审批表");
        cell.setCellStyle(style);
        //第4个 试验工程师
        cell = row.createCell(4);
        cell.setCellValue("试验工程师");
        cell.setCellStyle(style);
        //第5个 公告批次
        cell = row.createCell(5);
        cell.setCellValue("公告批次");
        cell.setCellStyle(style);
        //第6个 车辆计划到达时间
        cell = row.createCell(6);
        cell.setCellValue("车辆计划到达时间");
        cell.setCellStyle(style);
        //第7个 车辆实际到达时间
        cell = row.createCell(7);
        cell.setCellValue("车辆实际到达时间");
        cell.setCellStyle(style);
        //第8个 技术参数计划获取时间
        cell = row.createCell(8);
        cell.setCellValue("技术参数计划获取时间");
        cell.setCellStyle(style);
        //第9个 技术参数实际获取时间
        cell = row.createCell(9);
        cell.setCellValue("技术参数实际获取时间");
        cell.setCellStyle(style);
        //第10个 状态
        cell = row.createCell(10);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        //第11个 供应商
        cell = row.createCell(11);
        cell.setCellValue("供应商");
        cell.setCellStyle(style);
    }
    public void createAnnPlan(Workbook workbook, Sheet sheet,String id) {
        List<AnnPlanProjectEO> annPlanProjectList = annPlanProjectEOService.getAnnPlanProjectList(id);
        int row = 1;
        for (AnnPlanProjectEO annPlanProject : annPlanProjectList){
            Row rowAdd = sheet.createRow(row);
            rowAdd.createCell(0).setCellValue(annPlanProject.getPjModel());//车型
            String pjType = annPlanProject.getPjType();
            if (StringUtils.isNotEmpty(pjType)){
                List<DicTypeEO> projectPlanCategory = dicTypeEOService.getDicTypeByDictionaryCode("ProjectPlanCategory");
                for (DicTypeEO dicTypeEO : projectPlanCategory){
                    if (pjType.equals(dicTypeEO.getDicTypeCode())){
                        pjType = dicTypeEO.getDicTypeName();
                    }
                }
            }
            rowAdd.createCell(1).setCellValue(pjType);//类别
            rowAdd.createCell(2).setCellValue(annPlanProject.getPjTaskSource());//任务来源
            rowAdd.createCell(3).setCellValue(annPlanProject.getPjApprovalForm());//立项审批表
            rowAdd.createCell(4).setCellValue(annPlanProject.getPjEngineerName());//试验工程师
            rowAdd.createCell(5).setCellValue(annPlanProject.getPjAnnBatch());//公告批次
            rowAdd.createCell(6).setCellValue(annPlanProject.getPjCarTimePlan());//车辆计划到达时间
            rowAdd.createCell(7).setCellValue(annPlanProject.getPjCarTimeActual());//车辆实际到达时间
            rowAdd.createCell(8).setCellValue(annPlanProject.getPjParamTimePlan());//技术参数计划获取时间
            rowAdd.createCell(9).setCellValue(annPlanProject.getPjParamTimeActual());//技术参数实际获取时间
            String pjStatus = annPlanProject.getPjStatus();
            if (StringUtils.isNotEmpty(pjStatus)){
                List<DicTypeEO> statePlan = dicTypeEOService.getDicTypeByDictionaryCode("statePlan");
                for (DicTypeEO dicTypeEO : statePlan){
                    if (pjStatus.equals(dicTypeEO.getDicTypeCode())){
                        pjStatus = dicTypeEO.getDicTypeName();
                    }
                }
            }
            rowAdd.createCell(10).setCellValue(pjStatus);//状态
            rowAdd.createCell(11).setCellValue(annPlanProject.getPjSupplier());//供应商
            //继续创建下一行
            row ++;
        }
    }

    /**
     * 查询出工程师对应的计划详情
     * @param planId
     * @param userID
     * @return
     */
    public AnnPlanVO getEngineerAnnPlanMes(String planId, String userID) {
        AnnPlanEO annPlan = dao.selectByPrimaryKey(planId);
        List<AnnPlanProjectEO> annPlanProjectEOS = annPlanProjectEOService.getEngineerAnnPlanProjectList(planId,userID);
        AnnPlanVO annPlanVO = new AnnPlanVO();
        BeanUtils.copyProperties(annPlan,annPlanVO);
        annPlanVO.setAnnPlanProjectList(annPlanProjectEOS);
        return annPlanVO;
    }

    /**
     * 判断在上传报告后，是否对计划的状态变化为“已完成”
     * @param planId
     */
    public void updatePlanStatusToFinish(String planId) {
        List<AnnPlanProjectEO> projectList = annPlanProjectEOService.getAnnPlanProjectList(planId);
        Boolean flag = true;
        for (AnnPlanProjectEO annPlanProjectEO : projectList){
            if (!"2".equals(annPlanProjectEO.getPjStatus()) && !"3".equals(annPlanProjectEO.getPjStatus())){
                flag = false;
            }
        }
        //所有申报项目都变成“已完成”，“已取消”。将计划状态变成“已完成”
        if (flag){
            AnnPlanEO annPlanEO = new AnnPlanEO();
            annPlanEO.setId(planId);
            annPlanEO.setPlStatus("2");
            dao.updateByPrimaryKeySelective(annPlanEO);
        }
    }

    /**
     * CV主管查询对应计划的申报项目列表
     * @param planId
     * @return
     */
    public List<AnnPlanProjectEO> getAnnPlanProjectList(String planId) {
        List<AnnPlanProjectEO> annPlanProjectEOS = annPlanProjectEOService.getAnnPlanProjectList(planId);
        for (AnnPlanProjectEO annPlanProjectEO : annPlanProjectEOS){
            String supplierID = annPlanProjectEO.getPjSupplier();
            String supplierName = annPlanProjectEOService.getSupplierNameByID(supplierID);
            annPlanProjectEO.setPjSupplierName(supplierName);
        }
        return annPlanProjectEOS;
    }

    /**
     * 工程师查询对应计划的申报项目List
     * @param planId
     * @param userID
     * @return
     */
    public List<AnnPlanProjectEO> getEngineerAnnPlanProList(String planId, String userID) {
        List<AnnPlanProjectEO> annPlanProjectEOS = new ArrayList<>();
        annPlanProjectEOS = annPlanProjectEOService.getEngineerAnnPlanProjectList(planId,userID);
        for (AnnPlanProjectEO annPlanProjectEO : annPlanProjectEOS){
            String supplierID = annPlanProjectEO.getPjSupplier();
            String supplierName = annPlanProjectEOService.getSupplierNameByID(supplierID);
            annPlanProjectEO.setPjSupplierName(supplierName);
        }
        return annPlanProjectEOS;
    }
}
