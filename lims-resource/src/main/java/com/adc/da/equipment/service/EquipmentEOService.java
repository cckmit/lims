package com.adc.da.equipment.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adc.da.common.GenerateQrCode;
import com.adc.da.equipment.VO.EqBarcodeVo;
import com.adc.da.equipment.entity.*;
import com.adc.da.sys.entity.UserEO;
import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.page.BasePage;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.VO.EquipmentVO;
import com.adc.da.equipment.dao.EquipmentEODao;
import com.adc.da.equipment.entity.EquipmentUseLogEO;
import com.adc.da.equipment.page.EquipmentEOPage;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.page.UserEOPage;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.UUID;


/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT EquipmentEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("equipmentEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EquipmentEOService extends BaseService<EquipmentEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentEOService.class);

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private EquipmentEODao dao;
    @Autowired
    private DicEOService dicEOService;
    @Autowired
    private OrgEODao orgEODao;
    
    @Autowired
    private DicTypeEODao dicTypeEODao;

    public EquipmentEODao getDao() {
        return dao;
    }

    @Autowired
    private UserEOService userEOService;

    @Value("${barCode.path}")
    private String barCodePath;

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<EquipmentVO> queryByPage(EquipmentEOPage page){
    	return dao.queryByPage(page);
    }

    /**
     * 根据条件查询全部数据
     * @param page
     * @return
     */
    public List<EquipmentVO> queryByList(EquipmentEOPage page){
    	return dao.queryByList(page);
    }

    /**
     * 解析通用查询关键字
     * @param page
     */
    public void analyzeSearchPhrase(EquipmentEOPage page) {
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
                //根据search获取设备使用状态关键字
                List<String> useStatusKeys = dicEOService.getTypeCodesBySearch("equipmentUseStatus", search);
                map.put("useStatusKeys", useStatusKeys);
                //根据search获取设备状态关键字
                List<String> statusKeys = dicEOService.getTypeCodesBySearch("equipmentStatus", search);
                map.put("statusKeys", statusKeys);
                //根据search获取类别关键字
                List<String> sortKeys = dicEOService.getTypeCodesBySearch("equipmentSort", search);
                map.put("sortKeys", sortKeys);
                list.add(map);
            }
            page.setSearchMap(list);
        }
    }

    /**
     * 校验设备编号是否唯一
     * @param param 设备编号
     * @param id 设备编辑时需要传当前设备id
     * @return
     */
	public String checkNo(String param, String id) {
		EquipmentEO equipment = dao.checkNo(param, id);
		if(equipment != null) {
			return "n";
		}else {
			return "y";
		}

	}

	/**
	 * 设备新增
	 * @param equipmentVO
	 */
	public EquipmentVO add(EquipmentVO equipmentVO) {
		String id = UUID.randomUUID();
		equipmentVO.setId(id);
		equipmentVO.setEqStatus("0");//正常
		equipmentVO.setEqUseStatus("0");//空闲
		equipmentVO.setCreateBy(UserUtils.getUserId());
		equipmentVO.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		equipmentVO.setUpdateBy(UserUtils.getUserId());
		equipmentVO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		equipmentVO.setDelFlag("0");
		dao.insertSelective(equipmentVO);
		
		//保存设备配件
		if(equipmentVO.getPartsList() != null && !equipmentVO.getPartsList().isEmpty()) {
			List<EquipmentPartsEO> partsList = equipmentVO.getPartsList();
			for(EquipmentPartsEO parts : partsList) {
				if(StringUtils.isNotBlank(parts.getEqPartsName()) && StringUtils.isNotBlank(parts.getEqPartsNumber())) {					
					parts.setId(UUID.randomUUID());
					parts.setEqId(id);
					dao.addParts(parts);
				}
			}
		}
		//设置子设备
		if(equipmentVO.getChildren() != null && !equipmentVO.getChildren().isEmpty()) {
			for(EquipmentVO vo : equipmentVO.getChildren()) {
				if(StringUtils.isEmpty(vo.getId()))
					continue;
				EquipmentEO e = dao.selectByPrimaryKey(vo.getId());
				if(e != null) {					
					e.setEqParentId(id);
					e.setEqParentName(equipmentVO.getEqName());
					e.setUpdateBy(UserUtils.getUserId());
					e.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					dao.updateByPrimaryKey(e);
				}
			}
		}
		return equipmentVO;
	}
	
	/**
	 * 设备修改
	 * @param equipmentVO
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public EquipmentVO update(EquipmentVO equipmentVO) throws IllegalAccessException, InvocationTargetException {
		EquipmentEO entity = dao.selectByPrimaryKey(equipmentVO.getId());
		//将设备状态、创建人等信息设置到equipmentEO中，以防复制属性时这些信息丢失
		equipmentVO.setEqStatus(entity.getEqStatus());//正常
		equipmentVO.setEqUseStatus(entity.getEqUseStatus());//空闲
		equipmentVO.setEqParentId(entity.getEqParentId());
		equipmentVO.setEqParentName(entity.getEqParentName());
		equipmentVO.setCreateBy(entity.getCreateBy());
		equipmentVO.setCreateTime(entity.getCreateTime());
		equipmentVO.setDelFlag(entity.getDelFlag());
		equipmentVO.setEqProcedureState(entity.getEqProcedureState());
		BeanUtils.copyProperties(entity, equipmentVO);
		entity.setUpdateBy(UserUtils.getUserId());
		entity.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		dao.updateByPrimaryKey(entity);
		
		//删除原来的设备配件
		List<EquipmentPartsEO> oldPartsList = dao.getPartsByEqId(equipmentVO.getId());
		if(oldPartsList != null && !oldPartsList.isEmpty()) {
			dao.deletePartsList(oldPartsList);
		}
		
		//保存新的设备配件
		if(equipmentVO.getPartsList() != null && !equipmentVO.getPartsList().isEmpty()) {
			for(EquipmentPartsEO parts : equipmentVO.getPartsList()) {
				if(StringUtils.isNotBlank(parts.getEqPartsName()) && StringUtils.isNotBlank(parts.getEqPartsNumber())) {					
					parts.setId(UUID.randomUUID());
					parts.setEqId(equipmentVO.getId());
					dao.addParts(parts);
				}
			}
		}
		
		//旧的子设备列表
		List<EquipmentVO> oldChildrenEq = dao.getChildrenByParentId(equipmentVO.getId());
		List<String> oldChildren = oldChildrenEq != null ? 
				oldChildrenEq.stream().map(eq -> eq.getId()).collect(Collectors.toList()) : new ArrayList<>();
		//新的子设备列表
		List<EquipmentVO> childrenEq = equipmentVO.getChildren();
		List<String> children = childrenEq != null ? 
				childrenEq.stream().map(eq -> eq.getId()).collect(Collectors.toList()) : new ArrayList<>();
		
		//将删除的子设备和当前设备解除绑定
		if(oldChildren != null && !oldChildren.isEmpty()) {
			for(String eqId : oldChildren) {
				if(children != null && children.contains(eqId))//该设备在新的子设备列表中
					continue;
				EquipmentEO child = dao.selectByPrimaryKey(eqId);
				child.setEqParentId("");
				child.setEqParentName("");
				child.setUpdateBy(UserUtils.getUserId());
				child.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				dao.updateByPrimaryKey(child);
			}
		}
		
		//绑定新的子设备
		if(children != null && !children.isEmpty()) {
			for(String eqId : children) {
				if(StringUtils.isEmpty(eqId))
					continue;
				if(oldChildren != null && oldChildren.contains(eqId))
					continue;
				if(equipmentVO.getId().equals(eqId))//不能将自己作为子设备
					continue;
				EquipmentEO child = dao.selectByPrimaryKey(eqId);
				if(child != null) {					
					child.setEqParentId(equipmentVO.getId());
					child.setEqParentName(equipmentVO.getEqName());
					child.setUpdateBy(UserUtils.getUserId());
					child.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					dao.updateByPrimaryKey(child);
				}
			}
		}
		
		return equipmentVO;
	}

    /**
     * 根据设备Id，来更新设备的使用状态，使用人，使用人单位
     * @param eqId
     */
    public void updateUseStatusById(String eqId,String status,String userName,String department) {
        EquipmentEO equipmentEO = new EquipmentEO();
        equipmentEO.setId(eqId);
        equipmentEO.setEqUseStatus(status);
        equipmentEO.setEqUser(userName);
        equipmentEO.setEqUserOrg(department);
        dao.updateByPrimaryKeySelective(equipmentEO);
    }

    /**
     * 批量导入设备
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage importMateriel(MultipartFile file) throws Exception {
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
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
        List<EquipmentEO> eoList = new ArrayList<>();
        //设备类别的数据字典
        List<DicTypeEO> equipmentSort = dicTypeEOService.getDicTypeByDictionaryCode("equipmentSort");
        //设备类别数据字典的名称
        List<String> equipmentSortName = new ArrayList<>();
        for (DicTypeEO dicType : equipmentSort){
            equipmentSortName.add(dicType.getDicTypeName());
        }
        //设备重要度的数据字典
        List<DicTypeEO> equipmentImportance = dicTypeEOService.getDicTypeByDictionaryCode("equipmentImportance");
        List<String> equipmentImportanceName = new ArrayList<>();
        for (DicTypeEO dicType : equipmentImportance){
            equipmentImportanceName.add(dicType.getDicTypeName());
        }
        //从第三行开始解析
        outLoop:for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                //设备名称
                String eqName = getCellValueByCell(row.getCell(0));
                //设备编号
                String eqNo = getCellValueByCell(row.getCell(1));
                //固定资产编号
                String eqAssetsNo = getCellValueByCell(row.getCell(2));
                //规格型号
                String eqType = getCellValueByCell(row.getCell(3));
                //测量范围
                String eqSpecification = getCellValueByCell(row.getCell(4));
                //管理人
                String eqManager = getCellValueByCell(row.getCell(5));
                //管理人部门
                String eqManagerOrg = getCellValueByCell(row.getCell(6));
                //供应商
                String eqSupplier = getCellValueByCell(row.getCell(7));
                //生产厂家
                String eqCompany = getCellValueByCell(row.getCell(8));
                //重要度
                String eqImportance = getCellValueByCell(row.getCell(9));
                //设备存放地点
                String eqStorageLocation = getCellValueByCell(row.getCell(10));
                //终验收日期
                String eqTerminationDate = getCellValueByCell(row.getCell(11));
                //质保结束日期
                String eqPeriodArrivalDate = getCellValueByCell(row.getCell(12));
                //类别
                String eqSort = getCellValueByCell(row.getCell(13));
                //备注
                String eqRemark = getCellValueByCell(row.getCell(14));
                if (StringUtils.isEmpty(eqName)) {
                    errorMess.append("导入失败！第" +  (i + 1) + "行设备名称不能为空!<br> ");
                }
                if (StringUtils.isEmpty(eqNo)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行设备编号不能为空!<br> ");
                }
                if (StringUtils.isNotEmpty(eqImportance)){//填写了重要度就验证，不正确就提示错误
                    if (!equipmentImportanceName.contains(eqImportance.trim())){
                        errorMess.append("导入失败！第" +  (i + 1) + "行重要度填写不正确!<br> ");
                    }else {
                        for (DicTypeEO dicType : equipmentImportance){
                            if ((eqImportance.trim()).equals(dicType.getDicTypeName())) {
                                eqImportance =  dicType.getDicTypeCode();
                            }
                        }
                    }
                }
                if (StringUtils.isNotEmpty(eqSort)){//填写了类别就验证，不正确就提示错误
                    if (!equipmentSortName.contains(eqSort.trim())){
                        errorMess.append("导入失败！第" +  (i + 1) + "行类别填写不正确!<br> ");
                    }else {
                        for (DicTypeEO dicType : equipmentSort){
                            if ((eqSort.trim()).equals(dicType.getDicTypeName())) {
                                eqSort =  dicType.getDicTypeCode();
                            }
                        }
                    }
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                if (StringUtils.isNotEmpty(eqTerminationDate)){//填写了终验收日期就验证，不正确就提示错误
                    try {
                        Date date1 = simpleDateFormat.parse(eqTerminationDate.trim());
                        eqTerminationDate = simpleDateFormat.format(date1);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行终验收日期填写不正确!<br> ");
                    }
                }
                if (StringUtils.isNotEmpty(eqPeriodArrivalDate)){//填写了质保期结束日期就验证，不正确就提示错误
                    try {
                        Date date2 = simpleDateFormat.parse(eqPeriodArrivalDate.trim());
                        eqPeriodArrivalDate = simpleDateFormat.format(date2);
                    }catch (Exception e){
                        errorMess.append("导入失败！第" +  (i + 1) + "行质保期结束日期填写不正确!<br> ");
                    }
                }
                EquipmentEO equipmentEO = new EquipmentEO();
                equipmentEO.setEqName(eqName);
                equipmentEO.setEqNo(eqNo);
                equipmentEO.setEqAssetsNo(eqAssetsNo);
                equipmentEO.setEqType(eqType);
                equipmentEO.setEqSpecification(eqSpecification);
                equipmentEO.setEqManager(eqManager);
                equipmentEO.setEqManagerOrg(eqManagerOrg);
                equipmentEO.setEqSupplier(eqSupplier);
                equipmentEO.setEqCompany(eqCompany);
                equipmentEO.setEqImportance(eqImportance);
                equipmentEO.setEqStorageLocation(eqStorageLocation);
                equipmentEO.setEqTerminationDate(eqTerminationDate);
                equipmentEO.setEqPeriodArrivalDate(eqPeriodArrivalDate);
                equipmentEO.setEqSort(eqSort);
                equipmentEO.setEqRemark(eqRemark);
                eoList.add(equipmentEO);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        if (errorMess.length() == 0){
            for (EquipmentEO equipmentEO : eoList){
                List<EquipmentEO> equipmentList = dao.selectByNo(equipmentEO.getEqNo());
                if (CollectionUtils.isEmpty(equipmentList)){
                    equipmentEO.setId(UUID.randomUUID());
                    equipmentEO.setCreateBy(UserUtils.getUserId());
                    equipmentEO.setCreateTime(date);
                    equipmentEO.setEqStatus("0");//状态正常
                    equipmentEO.setEqUseStatus("0");//使用状态空闲
                    equipmentEO.setDelFlag("0");
                    dao.insertSelective(equipmentEO);
                }else {
                   for (EquipmentEO eq : equipmentList){
                       equipmentEO.setId(eq.getId());
                       equipmentEO.setUpdateBy(UserUtils.getUserId());
                       equipmentEO.setUpdateTime(date);
                       dao.updateByPrimaryKeySelective(equipmentEO);
                   }
                }
            }
            result = Result.success("0", "批量导入成功！");
            return result;
        }else {
            //导入失败--提示错误信息
            result = Result.error("-1", errorMess.toString());
            return result;
        }
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
     * 根据id获取设备
     *
     * @param id
     * @return
     */
    public EquipmentEO getEntityById(String id){
        EquipmentEO equipmentEO = dao.selectByPrimaryKey(id);
        return equipmentEO;
    }

    /**
     * 根据id获取对应设备的副设备
     *
     * @param id
     * @return
     */
    public List<String> getViceEqIdList(String id){
        return dao.getViceEqList(id);
    }

    /**
     * 根据设备id的list删除设备
     * @parem List
     */
    public void deleteByIdList(List<String> idS){
        dao.deleteByIdS(idS);
    }

    /**
     * 根据设备id删除设备
     * @parem id
     */
    public void deleteById(String id){
        dao.deleteByPrimaryKey(id);
    }

    /**
     * 根据设备ID，来更新设备的状态
     * @param eqId
     * @param staAfterStatus
     */
    public void updateEquStatusById(String eqId, String staAfterStatus) {
        EquipmentEO equipmentEO = new EquipmentEO();
        equipmentEO.setId(eqId);
        equipmentEO.setEqStatus(staAfterStatus);
        dao.updateByPrimaryKeySelective(equipmentEO);
    }

    public void checkBatchExport(HttpServletResponse response, HttpServletRequest request, String[] ids,String type) throws IOException {
        //工作空间
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置标题栏
        createTitle(workbook, sheet,type);
        //填充数据
        createEquipment(workbook, sheet,ids,type);
        //生成Excel文件名称
        //获取当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = "";
        if (type.equals("1")){
           fileName = date + "设备核检信息导出";
        }else {
           fileName = date + "设备使用记录导出";
        }
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
     * 设备-核检记录--使用记录导出
     * 填充表格标题栏
     * @param workbook
     * @param sheet
     */
    private void createTitle(Workbook workbook, Sheet sheet,String type) {
        if (type.equals("1")){//核检表头
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
            //设置样式
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setAlignment(CellStyle.ALIGN_LEFT);
            style.setFont(font);
            //创建标题栏的单元格
            Cell cell;
            //第0个 设备名称
            cell = row.createCell(0);
            cell.setCellValue("设备名称");
            cell.setCellStyle(style);
            //第1个 设备编号
            cell = row.createCell(1);
            cell.setCellValue("设备编号");
            cell.setCellStyle(style);
            //第2个 核检方式
            cell = row.createCell(2);
            cell.setCellValue("检定方式");
            cell.setCellStyle(style);
            //第3个 核检方式
            cell = row.createCell(3);
            cell.setCellValue("负责人");
            cell.setCellStyle(style);
            //第4个 下一次计划核检日期
            cell = row.createCell(4);
            cell.setCellValue("计划检定日期");
            cell.setCellStyle(style);
            //第5个 核检单位
            cell = row.createCell(5);
            cell.setCellValue("核检单位");
            cell.setCellStyle(style);
            //第6个 核检单位地址
            cell = row.createCell(6);
            cell.setCellValue("核检单位地址");
            cell.setCellStyle(style);
            //第7个 核检单位联系方式
            cell = row.createCell(7);
            cell.setCellValue("核检单位联系方式");
            cell.setCellStyle(style);
            //第8个 核检周期
            cell = row.createCell(8);
            cell.setCellValue("完成日期");
            cell.setCellStyle(style);
            //第9个 核检周期
            cell = row.createCell(9);
            cell.setCellValue("有效期");
            cell.setCellStyle(style);
            //第10个 金额
            cell = row.createCell(10);
            cell.setCellValue("金额");
            cell.setCellStyle(style);
            //第11个 备注
            cell = row.createCell(11);
            cell.setCellValue("备注");
            cell.setCellStyle(style);
        }else {//使用日志表头
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
            //设置样式
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setAlignment(CellStyle.ALIGN_LEFT);
            style.setFont(font);
            //创建标题栏的单元格
            Cell cell;
            //第一个 设备名称
            cell = row.createCell(0);
            cell.setCellValue("设备名称");
            cell.setCellStyle(style);
            //第二个 设备编号
            cell = row.createCell(1);
            cell.setCellValue("设备编号");
            cell.setCellStyle(style);
            //第三个 设备状态
            cell = row.createCell(2);
            cell.setCellValue("设备状态");
            cell.setCellStyle(style);
            //第四个 开始时间
            cell = row.createCell(3);
            cell.setCellValue("开始时间");
            cell.setCellStyle(style);
            //第五个 结束时间
            cell = row.createCell(4);
            cell.setCellValue("结束时间");
            cell.setCellStyle(style);
            //第六个 使用人
            cell = row.createCell(5);
            cell.setCellValue("负责人");
            cell.setCellStyle(style);
            //第七个 使用部门
            cell = row.createCell(6);
            cell.setCellValue("负责人部门");
            cell.setCellStyle(style);
            //第八个 备注
            cell = row.createCell(7);
            cell.setCellValue("备注");
            cell.setCellStyle(style);
        }
    }

    public void createEquipment(Workbook workbook, Sheet sheet,String[] ids,String type) {
        if (type.equals("1")){//核检数据填充
            List<EquipmentEO> checkList = dao.getCheckList(ids);
            List<EquipmentEO> equipmentEOS = new ArrayList<>();
            for (EquipmentEO eo : checkList){
                List<EquipmentCheckEO> equipmentCheckList = eo.getEquipmentCheckList();
                for (EquipmentCheckEO checkEO : equipmentCheckList){
                    eo.setEqCkAmount(checkEO.getEqCkAmount());
                    eo.setEqCkCheckTimePlan(checkEO.getEqCkCheckTimePlan());
                    eo.setEqCkOrgTel(checkEO.getEqCkOrgTel());
                    eo.setEqCkOrgAddress(checkEO.getEqCkOrgAddress());
                    eo.setEqCkOrg(checkEO.getEqCkOrg());
                    eo.setEqCkCycleUnit(checkEO.getEqCkCycleUnit());
                    eo.setEqCkCycle(checkEO.getEqCkCycle());
                    eo.setEqCkType(checkEO.getEqCkType());
                    eo.setEqCkPlanOwner(checkEO.getEqCkPlanOwner());
                    eo.setEqCkCheckTimeActual(checkEO.getEqCkCheckTimeActual());
                    eo.setEqCkRemark(checkEO.getEqCkRemark());
                    equipmentEOS.add(eo);
                }
            }
            int row = 1;
            for (EquipmentEO eq : equipmentEOS){
                Row rowAdd = sheet.createRow(row);
                String eqCkType = eq.getEqCkType();//检定方式：1：点检 2：期间核查 3：检定校准 4：保养设定
                if (eqCkType.equals("1")){
                    eqCkType = "点检设定";
                }else if (eqCkType.equals("2")){
                    eqCkType = "期间核查";
                }else if (eqCkType.equals("3")){
                    eqCkType = "检定校准";
                }else if (eqCkType.equals("4")){
                    eqCkType = "保养设定";
                }
                rowAdd.createCell(0).setCellValue(eq.getEqName());//设备名称
                rowAdd.createCell(1).setCellValue(eq.getEqNo());//设备编号
                rowAdd.createCell(2).setCellValue(eqCkType);//检定方式
                String eqCkPlanOwner = eq.getEqCkPlanOwner();//负责人
                if (StringUtils.isNotEmpty(eqCkPlanOwner)){
                    UserEO userEO = null;
                    try {
                        userEO = userEOService.selectByPrimaryKey(eqCkPlanOwner);
                        if (userEO != null){
                            eqCkPlanOwner = userEO.getUsname();
                        }else {
                            eqCkPlanOwner = "";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    eqCkPlanOwner = "";
                }
                rowAdd.createCell(3).setCellValue(eqCkPlanOwner);//设置负责人
                String eqCkCycle = "";
                if (eq.getEqCkCycle() != null){
                    eqCkCycle = String.valueOf(eq.getEqCkCycle());
                }
                String eqCkCycleUnit = "";
                if (eq.getEqCkCycleUnit() != null){
                    eqCkCycleUnit = String.valueOf(eq.getEqCkCycleUnit());
                }
                rowAdd.createCell(4).setCellValue(eq.getEqCkCheckTimePlan());//计划检定日期
                rowAdd.createCell(5).setCellValue(eq.getEqCkOrg());//检定单位
                rowAdd.createCell(6).setCellValue(eq.getEqCkOrgAddress());//检定单位地址
                rowAdd.createCell(7).setCellValue(eq.getEqCkOrgTel());//检定单位联系方式
                rowAdd.createCell(8).setCellValue(eq.getEqCkCheckTimeActual());//设置完成日期
                rowAdd.createCell(9).setCellValue(eqCkCycle + eqCkCycleUnit);//有效期====检定周期+检定周期单位（年、月、日）
                if (eq.getEqCkAmount() == null){
                    rowAdd.createCell(10).setCellValue(0);//金额
                }else {
                    rowAdd.createCell(10).setCellValue(eq.getEqCkAmount());//金额
                }
                rowAdd.createCell(11).setCellValue(eq.getEqCkRemark());
                //继续创建下一行
                row ++;
            }
        }else {
            List<EquipmentEO> useLogList = dao.getUseLogList(ids);
            List<EquipmentEO> equipmentEOList = new ArrayList<>();
            for (EquipmentEO eq : useLogList){
                List<EquipmentUseLogEO> equipmentUseLogList = eq.getEquipmentUseLogList();
                for (EquipmentUseLogEO useLogEO : equipmentUseLogList){
                    eq.setEqUlStatus(useLogEO.getEqUlStatus());
                    eq.setEqUlStartTime(useLogEO.getEqUlStartTime());
                    eq.setEqUlEndTime(useLogEO.getEqUlEndTime());
                    eq.setEqUlResponsiblePeople(useLogEO.getEqUlResponsiblePeople());
                    eq.setEqUlResponsiblePeopleOrg(useLogEO.getEqUlResponsiblePeopleOrg());
                    eq.setEqUlRemark(useLogEO.getEqUlRemark());
                    equipmentEOList.add(eq);
                }
            }
            int row = 1;
            for (EquipmentEO equipmentEO : equipmentEOList){
                Row rowAdd = sheet.createRow(row);
                String eqUlStatus = equipmentEO.getEqUlStatus();
                List<DicTypeEO> equipmentStatusForUseLog = dicTypeEOService.getDicTypeByDictionaryCode("equipmentStatus");//设备状态
                for (DicTypeEO dicTypeEO : equipmentStatusForUseLog){
                    if (eqUlStatus.equals(dicTypeEO.getDicTypeCode())){
                        eqUlStatus = dicTypeEO.getDicTypeName();
                    }
                }
                rowAdd.createCell(0).setCellValue(equipmentEO.getEqName());//设备名称
                rowAdd.createCell(1).setCellValue(equipmentEO.getEqNo());//设备编号
                rowAdd.createCell(2).setCellValue(eqUlStatus);//设备状态
                rowAdd.createCell(3).setCellValue(equipmentEO.getEqUlStartTime());//开始时间
                rowAdd.createCell(4).setCellValue(equipmentEO.getEqUlEndTime());//结束时间
                rowAdd.createCell(5).setCellValue(equipmentEO.getEqUlResponsiblePeople());//负责人
                rowAdd.createCell(6).setCellValue(equipmentEO.getEqUlResponsiblePeopleOrg());//负责人部门
                rowAdd.createCell(7).setCellValue(equipmentEO.getEqUlRemark());//备注
                //继续创建下一行
                row ++;
            }
        }
    }

    /**
     * 查询设备的配件列表
     * @param id
     * @return
     */
	public List<EquipmentPartsEO> getPartsByEqId(String id){
		return dao.getPartsByEqId(id);
	}
	
    /**
     * 生成二维码
     * @parem ids
     */
    public List<EqBarcodeVo> barCode(String[] ids){
        List<EqBarcodeVo> barCodeVoList = new ArrayList<>();
        for (String id : ids){
            EquipmentEO equipmentEO = dao.selectEqBarCode(id);
            EqBarcodeVo eqBarcodeVo = new EqBarcodeVo();
            eqBarcodeVo.setEqName(equipmentEO.getEqName());
            eqBarcodeVo.setEqNo(equipmentEO.getEqNo());
            eqBarcodeVo.setEqSupplier(equipmentEO.getEqSupplier());
            eqBarcodeVo.setEqAssetsNo(equipmentEO.getEqAssetsNo());
            try {
                BufferedImage bufferedImage = null;
                bufferedImage = GenerateQrCode.init().width(80).height(80).content(id).generate();
                File file = new File(barCodePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                ImageIO.write(
                        bufferedImage,
                        "png",
                        new File(barCodePath + eqBarcodeVo.getEqNo().replace("/", "-") + ".png"));
                eqBarcodeVo.setBarCode(
                        barCodePath + eqBarcodeVo.getEqNo().replace("/", "-") + ".png");
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            barCodeVoList.add(eqBarcodeVo);
        }
        return barCodeVoList;
    }

	/**
	 * 根据id获取所有子设备列表
	 * @param eqId
	 * @return
	 */
	public List<EquipmentVO> getChildrenByParentId(String eqId){
		return dao.getChildrenByParentId(eqId);
	}

	/**
	 * 设置设备使用记录
	 * @param useLog
	 * @return
	 * @throws Exception 
	 */
	public EquipmentUseLogEO setUseLog(EquipmentUseLogEO useLog) throws Exception {
		//设备id，设置设备开始、结束时必传
		String eqId = useLog.getEqId();
		//备注，设置设备结束时可传
		String remark = useLog.getEqUlRemark();
		
		//判断当前是设置设备开始，还是结束
		EquipmentUseLogEO ul = dao.getLastUseLog(eqId);
		if(ul == null || (StringUtils.isNotBlank(ul.getEqUlStartTime()) && StringUtils.isNotBlank(ul.getEqUlEndTime()))) {
			//无使用记录或最后一条使用记录开始时间和结束时间都不为空，说明当前是设置设备开始
			ul = useLog;
			ul.setId(UUID.randomUUID());
			ul.setEqUlStartTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			ul.setCreateBy(UserUtils.getUserId());
			ul.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			dao.addUseLog(ul);
		}else {
			//最后一条使用记录开始时间不为空，结束时间为空，说明当前是设置设备结束
			EquipmentEO equipmentEO = dao.selectByPrimaryKey(eqId);
			ul.setEqUlStatus(equipmentEO.getEqStatus());
			ul.setEqUlEndTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			ul.setEqUlRemark(remark);
			if(UserUtils.getUser() != null) {
				ul.setEqUlResponsiblePeople(UserUtils.getUser().getUsname());
				ul.setEqUlResponsiblePeopleOrg(UserUtils.getUser().getOrgName());
			}
			dao.updateUseLog(ul);
		}
		return ul;
	}

    public void eqExport(HttpServletResponse response, HttpServletRequest request, EquipmentEOPage page) throws IOException {
        //工作空间
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置标题栏
        title(workbook, sheet);
        //填充数据
        createEq(workbook, sheet,page);
        //生成Excel文件名称
        //获取当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = "";
        fileName = date + "设备信息导出";
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
     * 设备-核检记录--使用记录导出
     * 填充表格标题栏
     * @param workbook
     * @param sheet
     */
    private void title(Workbook workbook, Sheet sheet) {
        //标题栏
        Row row = sheet.createRow(0);
        //设置列宽
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 25 * 256);
        sheet.setColumnWidth(9, 25 * 256);
        sheet.setColumnWidth(10, 25 * 256);
        sheet.setColumnWidth(11, 25 * 256);
        sheet.setColumnWidth(12, 25 * 256);
        sheet.setColumnWidth(13, 25 * 256);
        sheet.setColumnWidth(14, 25 * 256);
        sheet.setColumnWidth(15, 25 * 256);
        sheet.setColumnWidth(16, 25 * 256);
        sheet.setColumnWidth(17, 25 * 256);
        sheet.setColumnWidth(18, 25 * 256);
        sheet.setColumnWidth(19, 25 * 256);
        sheet.setColumnWidth(20, 25 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 25 * 256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第0个 设备编号
        cell = row.createCell(0);
        cell.setCellValue("设备编号");
        cell.setCellStyle(style);
        //第1个 设备名称
        cell = row.createCell(1);
        cell.setCellValue("资产编号");
        cell.setCellStyle(style);
        //第2个 规格型号
        cell = row.createCell(2);
        cell.setCellValue("出厂编号");
        cell.setCellStyle(style);
        //第3个 设备状态
        cell = row.createCell(3);
        cell.setCellValue("设备类型");
        cell.setCellStyle(style);
        //第4个 设备使用状态
        cell = row.createCell(4);
        cell.setCellValue("设备名称");
        cell.setCellStyle(style);
        //第5个 使用单位
        cell = row.createCell(5);
        cell.setCellValue("设备型号");
        cell.setCellStyle(style);
        //第6个 负责人
        cell = row.createCell(6);
        cell.setCellValue("启用日期");
        cell.setCellStyle(style);
        //第7个 类别
        cell = row.createCell(7);
        cell.setCellValue("主要用途");
        cell.setCellStyle(style);
        //第8个 检定/校准完成日期
        cell = row.createCell(8);
        cell.setCellValue("主要技术参数");
        cell.setCellStyle(style);
        //第9个 检定/校准有效期
        cell = row.createCell(9);
        cell.setCellValue("测量范围");
        cell.setCellStyle(style);
        //第10个 维护保养完成日期
        cell = row.createCell(10);
        cell.setCellValue("设备分辨率");
        cell.setCellStyle(style);
        //第11个 维护保养有效期
        cell = row.createCell(11);
        cell.setCellValue("示值误差要求");
        cell.setCellStyle(style);
        //第12个 期间核查完成日期
        cell = row.createCell(12);
        cell.setCellValue("在用状态");
        cell.setCellStyle(style);
        //第13个 期间核查有效期
        cell = row.createCell(13);
        cell.setCellValue("设备负责人");
        cell.setCellStyle(style);
        //第14个 点检完成日期
        cell = row.createCell(14);
        cell.setCellValue("生产厂家");
        cell.setCellStyle(style);
        //第15个 期间核查有效期
        cell = row.createCell(15);
        cell.setCellValue("设备原值（不含税）");
        cell.setCellStyle(style);
        //第16个 合同编号
        cell = row.createCell(16);
        cell.setCellValue("合同编号");
        cell.setCellStyle(style);
        //第17个检定/标定日期
        cell = row.createCell(17);
        cell.setCellValue("检定/标定日期");
        cell.setCellStyle(style);
        //第18个检定/标定有效期
        cell = row.createCell(18);
        cell.setCellValue("检定/标定有效期");
        cell.setCellStyle(style);
        //第19个维护/保养日期
        cell = row.createCell(19);
        cell.setCellValue("维护/保养日期");
        cell.setCellStyle(style);
        //第20个维护/保养有效期
        cell = row.createCell(20);
        cell.setCellValue("维护/保养有效期");
        cell.setCellStyle(style);
        //第21个期间核查日期
        cell = row.createCell(21);
        cell.setCellValue("期间核查日期");
        cell.setCellStyle(style);
        //第22个期间核查有效期
        cell = row.createCell(22);
        cell.setCellValue("期间核查有效期");
        cell.setCellStyle(style);

    }

    public void createEq(Workbook workbook, Sheet sheet,EquipmentEOPage page){
        List<EquipmentVO> equipmentVOS = dao.queryByList(page);
        int row = 1;
        for (EquipmentVO equipmentVO : equipmentVOS){
            Row rowAdd = sheet.createRow(row);
            rowAdd.createCell(0).setCellValue(equipmentVO.getEqNo());//设备编号
            rowAdd.createCell(1).setCellValue(equipmentVO.getEqAssetsNo());//资产编号
            rowAdd.createCell(2).setCellValue(equipmentVO.getEqPlantNo());//出厂编号
            String eqSort = equipmentVO.getEqSort();
            if (StringUtils.isNotEmpty(eqSort)){
            	DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("equipmentSort", eqSort);
            	eqSort = dic != null ? dic.getDicTypeName() : "";
            }
            rowAdd.createCell(3).setCellValue(eqSort);//设备类型
            rowAdd.createCell(4).setCellValue(equipmentVO.getEqName());//设备名称
            rowAdd.createCell(5).setCellValue(equipmentVO.getEqType());//设备型号
            rowAdd.createCell(6).setCellValue(equipmentVO.getCreateTime());//启用日期
            rowAdd.createCell(7).setCellValue(equipmentVO.getEqMainPurpose());//主要用途
            rowAdd.createCell(8).setCellValue(equipmentVO.getEqMainParameter());//主要技术参数
            rowAdd.createCell(9).setCellValue(equipmentVO.getEqSpecification());//测量范围
            rowAdd.createCell(10).setCellValue(equipmentVO.getEqResolution());//设备分辨率
            rowAdd.createCell(11).setCellValue(equipmentVO.getEqErrorRequirement());//示值误差要求
            String eqUseStatus = equipmentVO.getEqUseStatus();
            if (StringUtils.isNotEmpty(eqUseStatus)){
            	DicTypeEO dic = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId("equipmentUseStatus", eqUseStatus);
            	eqUseStatus = dic != null ? dic.getDicTypeName() : "";
            }
            rowAdd.createCell(12).setCellValue(eqUseStatus);//在用状态
            rowAdd.createCell(13).setCellValue(equipmentVO.getEqManager());//设备负责人
            rowAdd.createCell(14).setCellValue(equipmentVO.getEqCompany());//生产厂家
            rowAdd.createCell(15).setCellValue(equipmentVO.getEqOriginalValue());//设备原值（不含税）
            rowAdd.createCell(16).setCellValue(equipmentVO.getEqContractNumber());//合同编号
            rowAdd.createCell(17).setCellValue(equipmentVO.getCalibrateCompleteDate());//检定/标定日期
            rowAdd.createCell(18).setCellValue(equipmentVO.getCalibrateValidDate());//检定/标定有效期
            rowAdd.createCell(19).setCellValue(equipmentVO.getMaintainCompleteDate());//维护保养日期
            rowAdd.createCell(20).setCellValue(equipmentVO.getMaintainValidDate());//维护保养有效期
            rowAdd.createCell(21).setCellValue(equipmentVO.getCheckCompleteDate());//期间核查日期
            rowAdd.createCell(22).setCellValue(equipmentVO.getCheckValidDate());//期间核查有效期
            row ++;
        }
    }
	/**
	 * 设备使用日志分页查询
	 * @param page
	 * @param eqUlStatus
	 * @param eqUlStartTimeStart
	 * @param eqUlStartTimeEnd
	 * @param eqUlEndTimeStart
	 * @param eqUlEndTimeEnd
	 * @param eqUlResponsiblePeople
	 * @return
	 */
	public List<EquipmentUseLogEO> getUseLogPage(BasePage page, String eqId, String eqUlStatus, String eqUlStartTimeStart,
			String eqUlStartTimeEnd, String eqUlEndTimeStart, String eqUlEndTimeEnd, String eqUlResponsiblePeople) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pager", page.getPager());
		paramMap.put("eqId", eqId);
		paramMap.put("eqUlStatus", eqUlStatus);
		paramMap.put("eqUlStartTimeStart", eqUlStartTimeStart);
		paramMap.put("eqUlStartTimeEnd", eqUlStartTimeEnd);
		paramMap.put("eqUlEndTimeStart", eqUlEndTimeStart);
		paramMap.put("eqUlEndTimeEnd", eqUlEndTimeEnd);
		paramMap.put("eqUlResponsiblePeople", eqUlResponsiblePeople);
		return dao.getUseLogPage(paramMap);
	}

	/**
	 * 设备使用日志查询总数
	 * @param page
	 * @param eqUlStatus
	 * @param eqUlStartTimeStart
	 * @param eqUlStartTimeEnd
	 * @param eqUlEndTimeStart
	 * @param eqUlEndTimeEnd
	 * @param eqUlResponsiblePeople
	 * @return
	 */
	public Integer getUseLogCount(BasePage page, String eqId, String eqUlStatus, String eqUlStartTimeStart, String eqUlStartTimeEnd,
			String eqUlEndTimeStart, String eqUlEndTimeEnd, String eqUlResponsiblePeople) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pager", page.getPager());
		paramMap.put("eqId", eqId);
		paramMap.put("eqUlStatus", eqUlStatus);
		paramMap.put("eqUlStartTimeStart", eqUlStartTimeStart);
		paramMap.put("eqUlStartTimeEnd", eqUlStartTimeEnd);
		paramMap.put("eqUlEndTimeStart", eqUlEndTimeStart);
		paramMap.put("eqUlEndTimeEnd", eqUlEndTimeEnd);
		paramMap.put("eqUlResponsiblePeople", eqUlResponsiblePeople);
		return dao.getUseLogCount(paramMap);
	}

	/**
	 * 新增、修改时选择管理人分页查询（选择范围为当前登录用户所属部门下的设备管理员）
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<UserVO> getManagerPage(BasePage page, String searchPhrase, String account, String orgName,
			String usName, String roleName, String userCode, String usposition, String email, String cellPhoneNumber) throws Exception {
		//封装查询条件
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pager", page.getPager());
		if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
            //通用查询
            searchPhrase = searchPhrase.trim();
            String patten = ConstantUtils.REGEX_EXCEPT_BLANK;
            Pattern datapattern = Pattern.compile(patten);
            Matcher matcher = datapattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (matcher.find()){
                String search = matcher.group();
                list.add(search);
            }
            paramMap.put("searchPhrase", list);
        }
		paramMap.put("account", account);
		paramMap.put("orgName", orgName);
		paramMap.put("usname", usName);
		paramMap.put("roleName", roleName);
		paramMap.put("userCode", userCode);
		paramMap.put("usposition", usposition);
		paramMap.put("email", email);
		paramMap.put("cellPhoneNumber", cellPhoneNumber);
		//获取当前登录人的组织机构及所有下级组织机构
		if(StringUtils.isNotBlank(UserUtils.getUserId())) {
			OrgEO orgEO = orgEODao.getByUserId(UserUtils.getUserId());
			List<OrgEO> orgList = orgEODao.getChildren(orgEO.getId(), null);
			if(orgList != null && !orgList.isEmpty()) {
				List<String> orgIds = orgList.stream().map(org -> org.getId()).collect(Collectors.toList());
				paramMap.put("orgIds", orgIds);
			}
		}
        return dao.getManagerPage(paramMap);
	}

	/**
	 * 新增、修改时选择管理人查询总数（选择范围为当前登录用户所属部门下的设备管理员）
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public Integer getManagerCount(BasePage page, String searchPhrase, String account, String orgName, String usName,
			String roleName, String userCode, String usposition, String email, String cellPhoneNumber) throws Exception {
		//封装查询条件
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pager", page.getPager());
		if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
            //通用查询
            searchPhrase = searchPhrase.trim();
            String patten = ConstantUtils.REGEX_EXCEPT_BLANK;
            Pattern datapattern = Pattern.compile(patten);
            Matcher matcher = datapattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (matcher.find()){
                String search = matcher.group();
                list.add(search);
            }
            paramMap.put("searchPhrase", list);
        }
		paramMap.put("account", account);
		paramMap.put("orgName", orgName);
		paramMap.put("usName", usName);
		paramMap.put("roleName", roleName);
		paramMap.put("userCode", userCode);
		paramMap.put("usposition", usposition);
		paramMap.put("email", email);
		paramMap.put("cellPhoneNumber", cellPhoneNumber);
		//获取当前登录人的组织机构及所有下级组织机构
		if(StringUtils.isNotBlank(UserUtils.getUserId())) {
			OrgEO orgEO = orgEODao.getByUserId(UserUtils.getUserId());
			List<OrgEO> orgList = orgEODao.getChildren(orgEO.getId(), null);
			if(orgList != null && !orgList.isEmpty()) {
				List<String> orgIds = orgList.stream().map(org -> org.getId()).collect(Collectors.toList());
				paramMap.put("orgIds", orgIds);
			}
		}
		return dao.getManagerCount(paramMap);
	}
	

    public String getOrgNameByUserId(String brwBorrowerId) {
	    return dao.getOrgNameByUserId(brwBorrowerId);
    }
	
	/**
	 * 查询今天超期未检的设备，修改其设备状态为超期未检
	 * @throws Exception
	 */
	public void changeStateOfOverdueEquipment() throws Exception{
	    dao.changeStateOfOverdueEquipment();
	}

    /**
     * 查询id对应设备的使用状态，如果为空闲，则返回true，否则返回false
     * @param id
     * @return
     */
    public boolean queryUseStatusByIds(String id) {
        EquipmentEO equipmentEO = dao.selectByPrimaryKey(id);
        String useStatus = equipmentEO.getEqUseStatus();
        if("0".equals(useStatus)){
            return true;
        }
        return false;
    }


    public void checkBorrowRecordExport(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //工作空间
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置标题栏
        createBorrowRecordTitle(workbook, sheet);
//        //填充数据
        createBorrowRecord(workbook, sheet);
        //生成Excel文件名称
        //获取当前日期
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + "设备借用记录导出";
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
     * 设备借用记录导出
     * 填充表格标题栏
     * @param workbook
     * @param sheet
     */
    private void createBorrowRecordTitle(Workbook workbook, Sheet sheet) {
        //标题栏
        Row row = sheet.createRow(0);
        //设置列宽
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第0个 设备名称
        cell = row.createCell(0);
        cell.setCellValue("设备名称");
        cell.setCellStyle(style);
        //第1个 设备编号
        cell = row.createCell(1);
        cell.setCellValue("设备编号");
        cell.setCellStyle(style);
        //第2个 借用时间
        cell = row.createCell(2);
        cell.setCellValue("借用时间");
        cell.setCellStyle(style);
        //第3个 归还时间
        cell = row.createCell(3);
        cell.setCellValue("归还时间");
        cell.setCellStyle(style);
        //第4个 借用人
        cell = row.createCell(4);
        cell.setCellValue("借用人");
        cell.setCellStyle(style);
    }
    //填充数据
    public void createBorrowRecord(Workbook workbook, Sheet sheet) {
        List<EquipmentEO> borrowRecord = dao.getBorrowRecord();
        List<EquipmentEO> equipmentList = new ArrayList<>();
        for (EquipmentEO eq : borrowRecord){
            List<EquipmentBorrowRecordEO> borrowRecordList = eq.getBorrowRecordList();
            for (EquipmentBorrowRecordEO equipmentBorrowRecordEO : borrowRecordList){
                EquipmentEO equipmentEO = new EquipmentEO();
                equipmentEO.setEqNo(eq.getEqNo());
                equipmentEO.setEqName(eq.getEqName());
                equipmentEO.setBrwBorrowerTime(equipmentBorrowRecordEO.getBrwBorrowerTime());
                equipmentEO.setBrwReturnTime(equipmentBorrowRecordEO.getBrwReturnTime());
                equipmentEO.setBrwBorrowerName(equipmentBorrowRecordEO.getBrwBorrowerName());
                equipmentList.add(equipmentEO);
            }
        }
        int row = 1;
        for (EquipmentEO equipmentEO : equipmentList){
            Row rowAdd = sheet.createRow(row);
            rowAdd.createCell(0).setCellValue(equipmentEO.getEqName());//设备名称
            rowAdd.createCell(1).setCellValue(equipmentEO.getEqNo());//设备编号
            rowAdd.createCell(2).setCellValue(equipmentEO.getBrwBorrowerTime());//借用时间
            rowAdd.createCell(3).setCellValue(equipmentEO.getBrwReturnTime());//归还时间
            rowAdd.createCell(4).setCellValue(equipmentEO.getBrwBorrowerName());//借用人
            //继续创建下一行
            row++;
        }
    }
}
