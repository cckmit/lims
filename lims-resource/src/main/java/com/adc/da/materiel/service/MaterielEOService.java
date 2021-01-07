package com.adc.da.materiel.service;

import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.materiel.entity.BorrowRecordEO;
import com.adc.da.materiel.page.MaterielEOPage;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.Encodes;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.materiel.entity.InventoryRecordEO;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.adc.da.base.service.BaseService;
import com.adc.da.materiel.dao.MaterielEODao;
import com.adc.da.materiel.entity.MaterielEO;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


/**
 *物料管理
 * 2019/11/19
 * lcx
 */
@Service("materielEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MaterielEOService extends BaseService<MaterielEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(MaterielEOService.class);

    @Autowired
    private MaterielEODao dao;

    public MaterielEODao getDao() {
        return dao;
    }

    @Autowired
    private InventoryRecordEOService inventoryRecordEOService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private BorrowRecordEOService borrowRecordEOService;

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    @Autowired
    private DicEOService dicEOService;

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;


    /**
     * 查询出入库的list
     * @param page
     * @return
     */
    public List<MaterielEO> getInboundPage(MaterielEOPage page) {
        List<MaterielEO> materielEOList = dao.getInventoryByPage(page);
        List<MaterielEO> materielEOS = new ArrayList<>();
        for(MaterielEO materielEO : materielEOList){
            for (InventoryRecordEO inventoryRecordEO : materielEO.getInventoryRecordEOList()){
                MaterielEO materielEO1 = new MaterielEO();
                BeanUtils.copyProperties(materielEO,materielEO1);
                materielEO1.setSampleQuantityOfBorrow(inventoryRecordEO.getSampleQuantity());
                materielEO1.setTotalPrice(inventoryRecordEO.getTotalPrice());
                materielEO1.setOutInTime(inventoryRecordEO.getOutInTime());
                String userName = null;
                try {
                    userName = getUserName(inventoryRecordEO.getBorrowerReturnee());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                materielEO1.setBorrowerReturnee(userName);
                materielEO1.setOperationType(inventoryRecordEO.getOperationType());
                materielEOS.add(materielEO1);
            }
        }
        return materielEOS;
    }

    /**
     * 查询出入库列表总数
     * @param page
     * @return
     */
    public Integer queryForInboundCount(MaterielEOPage page) {
        Integer count = dao.queryForCount(page);
        return count;
    }

    /**
     * 查询出，出库列表总数
     * @param page
     * @return
     */
    public Integer queryForOutboundCount(MaterielEOPage page) {
        Integer count = dao.queryForOutCount(page);
        return count;
    }

    /**
     * 查询出，出库list
     * @param page
     * @return
     */
    public List<MaterielEO> getOutboundPage(MaterielEOPage page) {
        List<MaterielEO> materielEOList = dao.getOutventoryByPage(page);
        List<MaterielEO> materielEOS = new ArrayList<>();
        for(MaterielEO materielEO : materielEOList){
            for (InventoryRecordEO inventoryRecordEO : materielEO.getInventoryRecordEOList()){
                MaterielEO materielEO1 = new MaterielEO();
                BeanUtils.copyProperties(materielEO,materielEO1);
                materielEO1.setSampleQuantityOfBorrow(inventoryRecordEO.getSampleQuantity());
                materielEO1.setTotalPrice(inventoryRecordEO.getTotalPrice());
                materielEO1.setOutInTime(inventoryRecordEO.getOutInTime());
                String userName = null;
                try {
                    userName = getUserName(inventoryRecordEO.getBorrowerReturnee());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                materielEO1.setBorrowerReturnee(userName);
                materielEO1.setOperationType(inventoryRecordEO.getOperationType());
                materielEOS.add(materielEO1);
            }
        }
        return materielEOS;
    }

    /**
     * 批量导入物料
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage importMateriel(MultipartFile file) throws Exception {
        ResponseMessage result = new ResponseMessage();
        if (file.isEmpty()) {
            logger.info("文件不存在，导入失败......", new FileNotFoundException());
            result = Result.error("-1", "文件不存在，导入失败");
            return result;
        }
        FileInputStream fis = (FileInputStream) file.getInputStream();
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        if (sheet.getLastRowNum() <= 1){
            return Result.error("-1", "请不要导入空模板！");
        }
        List<MaterielEO> materielEOList = new ArrayList<>();
        //记录导入行数
        int successRows = 0;
        //记录失败行数
        List<String> errorRows = new ArrayList<>();
        //记录因为样件数量错误导致的错误信息。
        StringBuilder errorMess = new StringBuilder();
        //记录因为物料类别不在数据字典里面导致的错误信息
        StringBuilder materielError = new StringBuilder();
        //查询出数据字典里面有多少种物料类别，方便一会循环体内的校验
        List<DicTypeEO> materialCategory = dicTypeEOService.getDicTypeByDictionaryCode("materialCategory");
        //从第三行开始解析
        outLoop:for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                //物料类别
                String materielType = getCellValueByCell(row.getCell(0));
                //物料名称
                String materielName = getCellValueByCell(row.getCell(1));
                //规格型号
                String normModel = getCellValueByCell(row.getCell(2));
                //物料编码
                String materielCode = getCellValueByCell(row.getCell(3));
                //品牌
                String brand = getCellValueByCell(row.getCell(4));
                //生产厂家
                String manufacturer = getCellValueByCell(row.getCell(5));
                //样件数量
                String sampleQuantity = getCellValueByCell(row.getCell(6));
                //单价
                String unitPrice = getCellValueByCell(row.getCell(7));
                //用途
                String purpose = getCellValueByCell(row.getCell(8));
                //单位
                String unit = getCellValueByCell(row.getCell(9));
                //将时间转成年月日格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //采购日期
                String procurementDate = simpleDateFormat.format(simpleDateFormat.parse(getCellValueByCell(row.getCell(10))));
                //生产日期
                String manufactureDate = simpleDateFormat.format(simpleDateFormat.parse(getCellValueByCell(row.getCell(11))));
                //有效期
                String termOfValidity = getCellValueByCell(row.getCell(12));
                //采购流程号
                String purchaseProcessNo = getCellValueByCell(row.getCell(13));
                //库存上限
                String inventoryCeiling = getCellValueByCell(row.getCell(14));
                //库存下限
                String inventoryFloor = getCellValueByCell(row.getCell(15));
                //所在位置
                String location = getCellValueByCell(row.getCell(16));
                //备注
                String remarks = getCellValueByCell(row.getCell(17));
                if (StringUtils.isEmpty(materielType) || StringUtils.isEmpty(materielName) ||
                        StringUtils.isEmpty(materielCode)) {
                    errorRows.add(String.valueOf(i + 1));
                    continue;
                }
                if ("".equals(sampleQuantity)) {
                    sampleQuantity = null;
                }
                if ("".equals(unitPrice)) {
                    //unitPrice = "0";
                    unitPrice = null;
                }
                if ("".equals(termOfValidity)) {
                    termOfValidity = null;
                }
                if ("".equals(inventoryCeiling)) {
                    inventoryCeiling = null;
                }
                if ("".equals(inventoryFloor)) {
                    inventoryFloor = null;
                }
                //采购日期
                if (!"".equals(procurementDate)){
                    Date date = simpleDateFormat.parse(procurementDate);
                }
                //生产日期
                if (!"".equals(manufactureDate)){
                    Date date = simpleDateFormat.parse(manufactureDate);
                }
                //通过查询数据字典，来校验是否物料类别是否存在于数据字典
                boolean isCategory = false;
                for(DicTypeEO dicTypeEO:materialCategory){
                    if (materielType.trim().equals(dicTypeEO.getDicTypeName().trim())){
                        isCategory = true;
                    }
                }
                if (!isCategory){
                    int num = i+1;
                    materielError.append("导入失败！第" + num + "行的物料类别不在数据字典内！<br>");
                    continue outLoop;
                }
                //到此，该行的数据格式问题校验结束。开始校验样件数量的大小问题
                if (checkNum(materielCode,"") != 0){
                    List<MaterielEO> materielEOListByCode = dao.getMaterielListByCode(materielCode);
                    for (MaterielEO materielEOByCode:materielEOListByCode){
                        //旧的样件数量
                        Double oldSampleQuantity = materielEOByCode.getSampleQuantity();
                        if (sampleQuantity != null && oldSampleQuantity != null){
                            if (Double.valueOf(sampleQuantity)<oldSampleQuantity){
                                Integer number = i+1;
                                errorMess.append("导入失败！第" +  number + "行录入样件数量少于原样件数量!<br> ");
                            }
                        }else{
                            if ((oldSampleQuantity != null && sampleQuantity == null) ){
                                Integer number = i+1;
                                errorMess.append("导入失败！第" +  number + "行录入样件数量少于原样件数量!<br> ");
                            }
                        }

                    }
                }
                MaterielEO materielEO = new MaterielEO();
                String materielTypeString = "";
                if ("办公".trim().equals(materielType.trim())){
                    materielTypeString = "0";
                }
                if ("劳保用品".trim().equals(materielType.trim())){
                    materielTypeString = "1";
                }
                if ("工具".trim().equals(materielType.trim())){
                    materielTypeString = "3";
                }
                if ("通用型耗材".trim().equals(materielType.trim())){
                    materielTypeString = "4";
                }
                if ("专用型耗材".trim().equals(materielType.trim())){
                    materielTypeString = "5";
                }
                materielEO.setMaterielType(materielTypeString);
                materielEO.setNormModel(normModel);
                materielEO.setMaterielName(materielName);
                materielEO.setMaterielCode(materielCode);
                if (sampleQuantity == null){
                    materielEO.setSampleQuantity(null);
                }else {
                    materielEO.setSampleQuantity(Double.valueOf(sampleQuantity));
                }
                materielEO.setProcurementDate(procurementDate);
                materielEO.setBrand(brand);
                materielEO.setManufacturer(manufacturer);
                if (unitPrice == null){
                    materielEO.setUnitPrice(null);
                }else{
                    materielEO.setUnitPrice(Double.valueOf(unitPrice));
                }
                materielEO.setPurpose(purpose);
                materielEO.setUnit(unit);
                if (termOfValidity == null){
                    materielEO.setTermOfValidity(null);
                }else{
                    materielEO.setTermOfValidity(Double.valueOf(termOfValidity));
                }
                materielEO.setPurchaseProcessNo(purchaseProcessNo);
                materielEO.setDelFlag(0);
                if (inventoryCeiling == null){
                    materielEO.setInventoryCeiling(null);
                }else{
                    materielEO.setInventoryCeiling(Double.valueOf(inventoryCeiling));
                }
                if (inventoryFloor == null){
                    materielEO.setInventoryFloor(null);
                }else{
                    materielEO.setInventoryFloor(Double.valueOf(inventoryFloor));
                }
                materielEO.setLocation(location);
                materielEO.setRemarks(remarks);
                if (sampleQuantity == null){
                    materielEO.setInventorySurpius(null);
                }else{
                    materielEO.setInventorySurpius(Double.valueOf(sampleQuantity));
                }
                materielEO.setManufactureDate(manufactureDate);
                materielEO.setMaterialStatus("0");
                materielEOList.add(materielEO);
                successRows++;
            } catch (Exception e) {
                errorRows.add(String.valueOf(i + 1));
                logger.error(e.getMessage(), e);
            }
        }
         if (errorRows.size() == 0 && errorMess.length() == 0 && materielError.length() == 0) {
            for (MaterielEO materielEO : materielEOList) {
                String materielCode = materielEO.getMaterielCode();
                 if (checkNum(materielCode,"") == 0) {
                     //时间
                    String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
                    materielEO.setId(UUID.randomUUID());
                    materielEO.setEntryBy(getUserName(UserUtils.getUserId()));
                    materielEO.setEntryTime(date);
                    materielEO.setCreateBy(UserUtils.getUserId());
                    materielEO.setCreateTime(date);
                    //新增记录保存至物料表
                    dao.insert(materielEO);
                    //将新增记录保存至出入库表
                    saveInventoryRecordEO(materielEO.getId(),materielEO.getSampleQuantity(),materielEO.getUnitPrice(),date,UserUtils.getUserId(),"0");

                } else {
                    List<MaterielEO> materielEOListByCode = dao.getMaterielListByCode(materielCode);
                    for (MaterielEO materielEOByCode : materielEOListByCode){
                        //时间
                        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
                        String id = materielEOByCode.getId();
                        materielEO.setId(id);
                        materielEO.setUpdateBy(UserUtils.getUserId());
                        materielEO.setUpdateTime(date);
                        //旧的样件数量
                        Double oldSampleQuantity = materielEOByCode.getSampleQuantity();
                        //出入库表的，样件数量(出入库表的样件数量是指每次记录的数量）
                        Double newSampleQuantity ;
                        if (oldSampleQuantity == null){
                            newSampleQuantity =  materielEO.getSampleQuantity();
                        }else{
                            newSampleQuantity = materielEO.getSampleQuantity()-oldSampleQuantity;
                        }
                        //旧的剩余库存
                        Double oldSam = materielEOByCode.getInventorySurpius();
                        //新的剩余库存=旧的剩余库存+出入库表的样件数量（批量导入，样件数量要求只能新增）
                        if (oldSam == null){
                            materielEO.setInventorySurpius(newSampleQuantity);
                        }else{
                            if (newSampleQuantity == null){
                                return Result.error("-1","上传失败!数据库该条数据为脏数据：样本数量为null，剩余库存不为null！");
                            }else{
                                materielEO.setInventorySurpius(oldSam + newSampleQuantity);
                            }
                        }
                        //设置物料有效状态
                        materielEO.setMaterialStatus("0");
                        dao.updateByPrimaryKeySelective(materielEO);
                        saveInventoryRecordEO(materielEO.getId(),newSampleQuantity,materielEO.getUnitPrice(),date,UserUtils.getUserId(),"1");
                    }
                }
            }
            result = Result.success("0", "批量导入成功！");
            return result;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : errorRows) {
            String string = "导入失败！第" + row + "行出现错误！<br>";
            stringBuilder.append(string);
        }
        //格式问题的错误信息
        String errorMessage = stringBuilder.toString();
        //物料类别的错误信息
        String materielErrorMess = materielError.toString();
        //样件数量问题的错误信息
        errorMess.append(errorMessage);
        errorMess.append(materielErrorMess);
        result = Result.error("-1", errorMess.toString());
        return result;
    }

    /**
     * 获取单元格各类型值，返回字符串类型
     *
     * @param cell
     * @return
     */
    public  static String getCellValueByCell(Cell cell) {
        return UserEOService.getCellValueByCell(cell);
    }


    /**
     * 获取物料所有记录
     *
     * @param searchPhrase 查询字段
     * @Title：list
     * @return: ResponseMessage<List < MaterielList>>
     * @author： lcx
     * @date：2019/11/19
     */
    public List<MaterielEO> page(MaterielEOPage page,
                                 String searchPhrase) {
        //解析通用查询
    	if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
    		searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<Map<String, Object>> list = new ArrayList<>();
            while (dateMatcher.find()) {
            	Map<String, Object> map = new HashMap<>();
            	//通用查询关键字
                String search = dateMatcher.group();
                map.put("search", search);
                //根据search获取物料类别关键字
                List<String> typeKeys = dicEOService.getTypeCodesBySearch("materialCategory", search);
                map.put("typeKeys", typeKeys);
                //根据search获取物料状态关键字
                List<String> statusKeys = dicEOService.getTypeCodesBySearch("materialState", search);
                map.put("statusKeys", statusKeys);
                list.add(map);
            }
            page.setSearchMap(list);
        }
        return dao.queryByPage(page);
    }

    /**
     * 保存物料
     */
    public MaterielEO save(MaterielEO materielEO) throws Exception {
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        //设置id
        materielEO.setId(UUID.randomUUID());
        //设置剩余库存                            样件数量
        materielEO.setInventorySurpius(materielEO.getSampleQuantity());
        //设置物料状态 0：有效 1：报废
        materielEO.setMaterialStatus("0");
        //设置创建人
        materielEO.setCreateBy(UserUtils.getUserId());
        //设置创建时间
        materielEO.setCreateTime(date);
        //设置修改人
        materielEO.setUpdateBy(UserUtils.getUserId());
        //设置修改时间
        materielEO.setUpdateTime(date);
        //设置删除状态 0：有效  1：删除
        materielEO.setDelFlag(0);
        String entryById = materielEO.getEntryBy();
        String entryBy = "";
        if (StringUtils.isEmpty(entryById)){
            entryBy = UserUtils.getUserId();
        }else {
            entryBy = entryById;
        }
        if (StringUtils.isNotEmpty(entryById)){//将录入用户的id转化为名字
            String userName = getUserName(entryById);
            materielEO.setEntryBy(userName);
        }
        //保存物料信息
        dao.insert(materielEO);
        //将新增记录保存至出入库表
        String entryTime = "";
        if (StringUtils.isEmpty(materielEO.getEntryTime())){
            entryTime = date;
        }else{
            entryTime = materielEO.getEntryTime();
        }
        saveInventoryRecordEO(materielEO.getId(),materielEO.getSampleQuantity(),materielEO.getUnitPrice(),entryTime,entryBy,"0");
        //剩余库存到达库存下限就给物料管理员发送消息通知
        sendMessageToAdmin(materielEO.getInventorySurpius(),materielEO.getInventoryFloor(),materielEO.getMaterielCode(),materielEO.getId());
        return materielEO;
    }
    /*保存物料操作到出入库表
    * materielId  物料id
    * sampleQuantity  样件数量
    * unitPrice 单价
    * entryTime 入库时间
    * entryBy  入库操作人
    * operationType  物料类别 操作类别:0:新增 1：编辑 2：领用 3：报废 4：归还
     */
    public void saveInventoryRecordEO(String materielId,Double sampleQuantity,Double unitPrice,String entryTime,String entryBy,String operationType) throws Exception {
        InventoryRecordEO inventoryRecordEO = new InventoryRecordEO();
        inventoryRecordEO.setId(UUID.randomUUID());
        inventoryRecordEO.setMaterielId(materielId);//物料id
        inventoryRecordEO.setSampleQuantity(sampleQuantity);//样件数量
        inventoryRecordEO.setOutInTime(entryTime);//入库时间
        inventoryRecordEO.setBorrowerReturnee(entryBy);//入库操作人
        inventoryRecordEO.setOperationType(operationType);//操作类别:0:新增 1：编辑 2：领用 3：报废 4：归还
        Double totalPrice = 0d;
        if (sampleQuantity != null && unitPrice != null){
            totalPrice = sampleQuantity*unitPrice;//计算总价
        }
        inventoryRecordEO.setTotalPrice(totalPrice);//总价
        inventoryRecordEOService.insert(inventoryRecordEO);//保存物料新增的操作到出入库表
    }

    /**
     * 验证物料编码数据库是否存在
     * @param materielCode
     */
    public Integer checkNum(String materielCode,String id){
        Integer integer = dao.checkNum(materielCode,id);
        return  integer;
    }

    /**
     * 查询物料的剩余库存
     * @param id
     */
    public Double queryInventorySurpius(String id){
        Double sampleQuantity = dao.queryInventorySurpius(id);
        return sampleQuantity;
    }


    /**
     * 查询物料的原始样件数量以及剩余库存
     * @param id
     */
    public MaterielEO sampleAndStock(String id){
        MaterielEO materielEO = dao.SampleAndStock(id);
        return materielEO;
    }

    /**
     * 物料编辑
     * @param materielEO
     */
    public MaterielEO update(MaterielEO materielEO) throws Exception {
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        String id = materielEO.getId();
        //获取物料的原始样件数量以及原始剩余库存
        MaterielEO materiel = dao.SampleAndStock(id);
        Double oldQuantity = materiel.getSampleQuantity();//原始样件数量
        Double oldInventorySurpius = materiel.getInventorySurpius();//原始剩余库存
        //现记录的样件数量
        Double sampleQuantity = materielEO.getSampleQuantity();
        materielEO.setUpdateBy(UserUtils.getUserId());
        materielEO.setUpdateTime(date);
        if (oldQuantity != null && sampleQuantity != null){
            Double difference = sampleQuantity - oldQuantity;//现记录样件数量 - 原始样件数量
            if (difference > 0d){//编辑增加样件数量
                Double newInventorySurpius = 0d;
                if (oldInventorySurpius != null){//原始库存不为空时，现剩余库存为原始剩余库存 + 编辑增加的样件数量
                    newInventorySurpius = oldInventorySurpius + difference;//计算新的库存
                }else {//原始剩余库存为空，现剩余库存为现记录的样件数量
                    newInventorySurpius = sampleQuantity;
                }
                materielEO.setInventorySurpius(newInventorySurpius);//修改此时的剩余库存
                saveInventoryRecordEO(materielEO.getId(),difference,materielEO.getUnitPrice(),date,UserUtils.getUserId(),"1");
            }
        }
        dao.updateByPrimaryKeySelective(materielEO);//编辑保存
        //剩余库存到达库存下限就给物料管理员发送消息通知
        sendMessageToAdmin(materielEO.getInventorySurpius(),materielEO.getInventoryFloor(),materielEO.getMaterielCode(),materielEO.getId());
        return materielEO;
    }


    /**
     * 物料报废
     * @param id
     */
    public void scrapByPrimaryKey(String id) throws Exception {
        dao.scrapByPrimaryKey(id);
        MaterielEO materielEO = dao.selectByPrimaryKey(id);//根据物料id获取物料实体
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        //报废记录保存至出入库表
        saveInventoryRecordEO(materielEO.getId(),materielEO.getSampleQuantity(),materielEO.getUnitPrice(),date,UserUtils.getUserId(),"3");
    }

    /**
     *查询所有未删除的物料的有效期以及生产日期
     */
    public List<MaterielEO> queryValidity(){
        return dao.queryValidity();
    }

    /**
     *定时任务物料报废
     */
    public void scrapByScheduling(MaterielEO materielEO) throws Exception {
        dao.scrapByPrimaryKey(materielEO.getId());
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        //报废记录保存至出入库表
        saveInventoryRecordEO(materielEO.getId(),materielEO.getSampleQuantity(),materielEO.getUnitPrice(),date," ","3");
    }

    /**
     *物料领用
     * @param borrowRecordEO
     */
    public BorrowRecordEO insertBorrowRecord(BorrowRecordEO borrowRecordEO) throws Exception {
        BorrowRecordEO borrowRecord = borrowRecordEOService.getReturned(borrowRecordEO.getMaterielId(), borrowRecordEO.getBorrowerId());
        String borrowingQuantity = borrowRecordEO.getBorrowingQuantity();//现领用数量
        if (borrowRecord != null){//代表当前用户借用过此物料
            String borrowingNums = borrowRecord.getBorrowingQuantity();//此用户之前借用数量
            Double borrowingNum = Double.valueOf(borrowingNums);
            Double borrowingQuantityNum = Double.valueOf(borrowingQuantity);
            Double newBorrowingNums = borrowingNum + borrowingQuantityNum;
            borrowRecord.setBorrowingQuantity(newBorrowingNums.toString());
            borrowRecord.setBorrower(borrowRecordEO.getBorrower());
            borrowRecord.setBorrowingTime(borrowRecordEO.getBorrowingTime());
            borrowRecord.setBorrowingRemakes(borrowRecordEO.getBorrowingRemakes());
            borrowRecord.setBorrowerId(borrowRecordEO.getBorrowerId());
            borrowRecordEOService.updateByPrimaryKeySelective(borrowRecord);
        }else {
            borrowRecordEO.setId(UUID.randomUUID());
            borrowRecordEOService.insert(borrowRecordEO);
        }
        Double borrowQuantity = Double.valueOf(borrowingQuantity);
        String materielId = borrowRecordEO.getMaterielId();//物料id
        MaterielEO materielEO = dao.selectByPrimaryKey(materielId);//根据物料id获取物料实体
        Double inventorySurpius = materielEO.getInventorySurpius();//剩余库存
        Double newSurpius = 0.0;
        if (inventorySurpius != null && borrowingQuantity != null){
            newSurpius = inventorySurpius - borrowQuantity;
        }else {
            newSurpius = inventorySurpius;
        }
        materielEO.setInventorySurpius(newSurpius);//修改库存
        dao.updateByPrimaryKeySelective(materielEO);
        Double unitPrice = materielEO.getUnitPrice();//单价
        //领用记录保存至出入库记录表
        saveInventoryRecordEO(materielId,borrowQuantity,unitPrice,borrowRecordEO.getBorrowingTime(),borrowRecordEO.getBorrowerId(),"2");
        //剩余库存到达库存下限就给物料管理员发送消息通知
        sendMessageToAdmin(newSurpius,materielEO.getInventoryFloor(),materielEO.getMaterielCode(),materielEO.getId());
        return borrowRecordEO;
    }
    /**
     * 根据用户id获取用户名称
     * @param userId
     */
    public String getUserName(String userId) throws Exception {
        UserEO userEO = userEOService.selectByPrimaryKey(userId);
        return userEO.getUsname();
    }

    /**
     * 根据用户id以及物料id获取当前用户是否可以归还，以及应该归还的数量
     * @param userId,materielId
     */
    public Double getReturnedNums(String userId,String materielId){
        return borrowRecordEOService.selectReturnedNums(materielId, userId);
    }

    /**
     * 判断是否可以被删除。没有被借出的时候可以删除。样件数量或者剩余库存为null的时候可以删除
     * 当处于报废状态时，也可以删除
     * @param id
     * @return
     */
    public boolean checkMaterielById(String id) {
        MaterielEO materielEO = dao.selectByPrimaryKey(id);
        String status = materielEO.getMaterialStatus();
        Double dis=1e-6;
        //样件数量
        Double sampleQuantity = materielEO.getSampleQuantity();
        //剩余库存
        Double inventorySurplus=materielEO.getInventorySurpius();
        //当样件数量等于剩余库存，则没有被借出情况，可以被删除
        if (sampleQuantity == null || inventorySurplus == null){
            return true;
        }else if (Math.abs(sampleQuantity-inventorySurplus)<dis){
            return true;
        }else if ("1".equals(status)){
            return true;
        }
        return false;
    }

    /**
     * 通过IDS数组来批量删除物料信息
     * @param ids
     */
    public void deleteByIds(String[] ids) {
        dao.deleteByPrimaryKeyArray(ids);
    }

    /**
     * 归还
     * @param borrowRecordEO
     */
    public BorrowRecordEO userReturned(BorrowRecordEO borrowRecordEO) throws Exception {
        String materielId = borrowRecordEO.getMaterielId();//物料id
        String borrowerId = borrowRecordEO.getBorrowerId();
        BorrowRecordEO borrowRecord = borrowRecordEOService.getReturned(materielId, borrowerId);
        String borrowingQuantity = borrowRecord.getBorrowingQuantity();
        Double oldBorrowingQuantity = Double.valueOf(borrowingQuantity);//原始领用数量
        Double returnQuantity = Double.valueOf(borrowRecordEO.getReturnQuantity());//本次归还数量
        Double newBorrowingQuantity = oldBorrowingQuantity - returnQuantity;//新的领用数量
        borrowRecord.setBorrowingQuantity(Double.toString(newBorrowingQuantity));//修改领用数量
        borrowRecord.setReturnDepartment(borrowRecordEO.getReturnDepartment());
        borrowRecord.setReturner(borrowRecordEO.getReturner());
        borrowRecord.setReturnQuantity(borrowRecordEO.getReturnQuantity());
        borrowRecord.setReturnTime(borrowRecordEO.getReturnTime());
        borrowRecord.setReturnRemakes(borrowRecordEO.getReturnRemakes());
        borrowRecordEOService.updateByPrimaryKeySelective(borrowRecord);//修改借用归还记录
        MaterielEO materielEO = dao.selectByPrimaryKey(materielId);
        //归还记录保存至出入库记录表
        saveInventoryRecordEO(materielId,returnQuantity,materielEO.getUnitPrice(),borrowRecordEO.getReturnTime(),borrowRecordEO.getBorrowerId(),"4");
        Double inventorySurpius = materielEO.getInventorySurpius();//剩余库存
        Double newSurpius = 0.0;
        if (inventorySurpius != null && returnQuantity != null){
            newSurpius = inventorySurpius + returnQuantity;
        }else {
            newSurpius = inventorySurpius;
        }
        materielEO.setInventorySurpius(newSurpius);//修改库存
        dao.updateByPrimaryKeySelective(materielEO);
        //剩余库存到达库存下限就给物料管理员发送消息通知
        sendMessageToAdmin(newSurpius,materielEO.getInventoryFloor(),materielEO.getMaterielCode(),materielEO.getId());
        return borrowRecordEO;
    }

    /**
     * 根据角色code给对应用户发送消息通知
     * @param roleCode 角色code
     * @param title  消息标题
     * @param businessId  业务id
     * @param sendlink  标识key,对应前端打开页面标识
     */
    public void sendMessageToRole(String roleCode,String sendlink,String sendUser,String title,String businessId) throws Exception {
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        List<UserEO> userList = userEOService.getUserByCode(roleCode);
        MessageEO messageEO;
        for (UserEO user : userList){
            //消息通知
            messageEO = new MessageEO(UUID.randomUUID(), "0", sendlink, date, sendUser, title, user.getUsid(), businessId);
            //发送消息通知
            messageEOService.sendMessage(messageEO);
			messageEOService.insertSelective(messageEO);
        }
    }

    /**
     * 根据当前库存是否到达库存下限给物料管理员发送消息通知
     * @param inventorySurpius 剩余库存
     * @param inventoryFloor  库存下限
     * @param materielCode 物料code
     *  @param materielId 物料id
     */
    public void sendMessageToAdmin(Double inventorySurpius, Double inventoryFloor,String materielCode,String materielId) throws Exception {
        if ((inventorySurpius != null && inventoryFloor != null) && ((inventorySurpius - inventoryFloor) <= 0d)){
            //消息标题
            String title = "物料编码为" + materielCode + "的物料，当前库存已达库存下限";
            sendMessageToRole("Materiel_Manager","",materielId,title,UserUtils.getUserId());
        }
    }

    public void materielBatchExport(HttpServletResponse response, HttpServletRequest request,MaterielEOPage page,
                                    String searchPhrase) throws IOException {
        //工作空间
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置标题栏
        createTitle(workbook, sheet);
        //填充数据
        createMateriel(workbook, sheet,page,searchPhrase);
        //生成Excel文件名称
        //ex : 20190722检验项目导出．xlsx
        //获取当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + "物料信息导出";
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
     * 物料信息导出 填充表格数据
     * @param workbook
     * @param sheet
     */
    private void createMateriel(Workbook workbook, Sheet sheet,MaterielEOPage page,
                                String searchPhrase) {
        //通过关键字查出所有数据
        List<MaterielEO> materielEOList = page(page,searchPhrase);
        int rowNum = 1;
        for (MaterielEO materielEO:materielEOList){
            Row rowAdd = sheet.createRow(rowNum);
            //物料类别
            String materielType = "";
            if("0".equals(materielEO.getMaterielType().trim())){
                materielType = "办公";
            }
            if("1".equals(materielEO.getMaterielType().trim())){
                materielType = "劳保用品";
            }
            if("3".equals(materielEO.getMaterielType().trim())){
                materielType = "工具";
            }
            if("4".equals(materielEO.getMaterielType().trim())){
                materielType = "通用型耗材";
            }
            if("5".equals(materielEO.getMaterielType().trim())){
                materielType = "专用型耗材";
            }
            rowAdd.createCell(0).setCellValue(materielType);
            //物料名称
            rowAdd.createCell(1).setCellValue(materielEO.getMaterielName());
            //物料编码
            rowAdd.createCell(2).setCellValue(materielEO.getMaterielCode());
            //规格型号
            rowAdd.createCell(3).setCellValue(materielEO.getNormModel());
            //品牌
            rowAdd.createCell(4).setCellValue(materielEO.getBrand());
            //单位
            rowAdd.createCell(5).setCellValue(materielEO.getUnit());
            //所在位置
            rowAdd.createCell(6).setCellValue(materielEO.getLocation());
            //用途
            rowAdd.createCell(7).setCellValue(materielEO.getPurpose());
            //录入日期
            rowAdd.createCell(8).setCellValue(materielEO.getEntryTime());
            //剩余库存
            rowAdd.createCell(9).setCellValue(String.valueOf(Integer.valueOf(materielEO.getInventorySurpius().intValue())));
            //物料状态
            String materialStatus = "";
            if (("0".equals(materielEO.getMaterialStatus()))){
                 materialStatus = "有效";
            }
            if (("1".equals(materielEO.getMaterialStatus()))){
                 materialStatus = "报废";
            }
            rowAdd.createCell(10).setCellValue(materialStatus);
            //继续创建下一行
            rowNum ++;
            }
        }


    /**
     * 物料信息导出 填充表格标题栏
     * @param workbook
     * @param sheet
     */
    private void createTitle(Workbook workbook, Sheet sheet) {
        //标题栏
        Row row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，
        //sheet.setDefaultColumnWidth(100*256);
        sheet.setColumnWidth(0,20*256);
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,20*256);
        sheet.setColumnWidth(4,15*256);
        sheet.setColumnWidth(5,5*256);
        sheet.setColumnWidth(6,20*256);
        sheet.setColumnWidth(7,25*256);
        sheet.setColumnWidth(8,25*256);
        sheet.setColumnWidth(9,10*256);
        sheet.setColumnWidth(10,10*256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第一个 物料类别
        cell = row.createCell(0);
        cell.setCellValue("物料类别");
        cell.setCellStyle(style);
        //第二个 物料名称
        cell = row.createCell(1);
        cell.setCellValue("物料名称");
        cell.setCellStyle(style);
        //第三个 物料编码
        cell = row.createCell(2);
        cell.setCellValue("物料编码");
        cell.setCellStyle(style);
        //第四个 检验依据(规格型号)
        cell = row.createCell(3);
        cell.setCellValue("规格型号");
        cell.setCellStyle(style);
        //第五个 品牌
        cell = row.createCell(4);
        cell.setCellValue("品牌");
        cell.setCellStyle(style);
        //第六个 单位
        cell = row.createCell(5);
        cell.setCellValue("单位");
        cell.setCellStyle(style);
        //第七个 库位
        cell = row.createCell(6);
        cell.setCellValue("库位");
        cell.setCellStyle(style);
        //第八个 用途
        cell = row.createCell(7);
        cell.setCellValue("用途");
        cell.setCellStyle(style);
        //第九个 录入时间
        cell = row.createCell(8);
        cell.setCellValue("录入时间");
        cell.setCellStyle(style);
        //第十个 剩余库存
        cell = row.createCell(9);
        cell.setCellValue("剩余库存");
        cell.setCellStyle(style);
        //第十一个 物料状态
        cell = row.createCell(10);
        cell.setCellValue("物料状态");
        cell.setCellStyle(style);
    }

    /**
     * 获取物料的样件数量
     * @parem id
     */
    public Double getNum(String id){
        MaterielEO materielEO = dao.selectByPrimaryKey(id);
        return materielEO.getSampleQuantity();
    }
}
