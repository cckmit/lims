package com.adc.da.insproject.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.insproject.dao.InsProjectEODao;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.insproject.page.InsProjectEOPage;
import com.adc.da.insprostd.dao.InsProStdEODao;
import com.adc.da.insprostd.entity.InsProStdEO;
import com.adc.da.instype.dao.InsTypeEODao;
import com.adc.da.instype.entity.InsTypeEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.standard.dao.StandardEODao;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.util.Utils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.alibaba.fastjson.JSONObject;

/**
 *  基础数据模块--检验项目管理
 * @author Administrator
 * @date 2019年7月17日
 */
@SuppressWarnings("all")
@Service("insProjectEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InsProjectEOService extends BaseService<InsProjectEO, String> {

	@Autowired
	private InsProjectEODao insProjectEODao;
	
	public InsProjectEODao getDao() {
		return insProjectEODao;
	}

	@Autowired
	private InsProStdEODao insProStdEODao;
	
	@Autowired
	private StandardEODao standardEODao;
	
	@Autowired
	private InsTypeEODao insTypeEODao;
	
	/**
     * 获取检验项目列表
    * @Title：list
    * @param insProjectSearchVO
    * @return
    * @return: ResponseMessage<List<InsProjectSearchVO>>
    * @author： ljy  
    * @date： 2019年7月18日
     */
	public List<InsProjectEO> page(InsProjectEOPage page, String searchPhrase){
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
		//--------------单条件查询-------------//
		List<InsProjectEO> pageList = insProjectEODao.queryByPage(page);
		List<InsProjectEO> list = new ArrayList<>();
		for(InsProjectEO eo : pageList) {
			InsProjectEO insPro = insProjectEODao.selectByPrimaryKey(eo.getId());
			list.add(insPro);
		}
        return list;
	}
	
	/**
	 * 保存检验项目
	* @Title：save
	* @param insProjectEO
	* @return
	* @throws Exception
	* @return: InsProjectEO
	* @author： ljy  
	* @date： 2019年7月18日
	 */
	public InsProjectEO save(InsProjectEO insProjectEO) throws Exception {
		//设置UUID
		insProjectEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		insProjectEO.setCreateTime(date);
		insProjectEO.setCreateBy(UserUtils.getUser());
		insProjectEO.setUpdateBy(UserUtils.getUser());
		insProjectEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		insProjectEO.setDelFlag(0);
		//设置父级
		//去除树形
		//保存检验项目
		insProjectEODao.insert(insProjectEO);
		//保存至关联表
		if(StringUtils.isNotEmpty(insProjectEO.getStdIds())) {
			String[] splits = insProjectEO.getStdIds().split(ConstantUtils.COMMA);
			List<String> splitsArray = Arrays.asList(splits);
			//关联实体
			for (int i = 0; i < splitsArray.size(); i++) {
				InsProStdEO insProStdEO = new InsProStdEO();
				insProStdEO.setProId(insProjectEO.getId());
				insProStdEO.setStdId(splitsArray.get(i));
				insProStdEODao.insert(insProStdEO);
			}
		}
		return insProjectEO;
	}
	
    /**
     * 检验项目编辑
    * @Title：edit
    * @param insProjectVO
    * @return
    * @return: ResponseMessage<InsProjectVO>
    * @author： ljy  
    * @date： 2019年7月18日
     */
	public InsProjectEO edit(InsProjectEO insProjectEO) throws Exception {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		insProjectEO.setUpdateBy(UserUtils.getUser());
		insProjectEO.setUpdateTime(date);
		//更新检验项目
		insProjectEODao.updateByPrimaryKeySelective(insProjectEO);
		//删除原有关联
		insProStdEODao.deleteByPrimaryKey(insProjectEO.getId());
		//保存至关联表
		if(StringUtils.isNotEmpty(insProjectEO.getStdIds())) {
			//保存更新后的关联
			String[] splits = insProjectEO.getStdIds().split(ConstantUtils.COMMA);
			List<String> splitsArray = Arrays.asList(splits);
			//关联实体
			for (int i = 0; i < splitsArray.size(); i++) {
				InsProStdEO insProStdEO = new InsProStdEO();
				insProStdEO.setProId(insProjectEO.getId());
				insProStdEO.setStdId(splitsArray.get(i));
				insProStdEODao.insert(insProStdEO);
			}
		}
		return insProjectEO;
	}
	
	/**
	 * 查询是否有子节点
	* @Title：selectChildsById
	* @param proBelongId 所属项目id
	* @return
	* @return: List<InsProjectEO>
	* @author： ljy  
	* @date： 2019年7月18日
	 */
	public List<InsProjectEO> selectChildsById(String proBelongId){
		return insProjectEODao.selectChildsById(proBelongId);
	}
	
	/**
	 * 批量删除
	* @Title：deleteByIds
	* @param ids
	* @return
	* @return: ResponseMessage
	* @author： ljy  
	* @date： 2019年7月18日
	 */
	public void deleteByIds(String[] ids){
		for(String id : ids) {
    		//检验项目与检验标准关系表删除
    		insProStdEODao.deleteByPrimaryKey(id);
    		//检验项目删除
    		insProjectEODao.deleteByPrimaryKey(id);
		}
	}
	
	/**
	 * 批量导出
	* @Title：insProBatchExport
	* @return: void
	* @author： ljy  
	* @date： 2019年7月22日
	 * @throws IOException 
	 */
	public void insProBatchExport(HttpServletResponse response, HttpServletRequest request) throws IOException {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		createTitle(workbook, sheet);
        //填充数据
		createInsPoject(workbook, sheet);
        //生成Excel文件名称
		//ex : 20190722检验项目导出．xlsx
		//获取当前日期
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + ConstantUtils.INSPRO_EXPORT;
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
	 * 检验项目导出 标题栏
	* @Title：createTitle
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年7月22日
	 */
	public void createTitle(Workbook workbook, Sheet sheet) {
		//标题栏
		Row row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,50*256);
        //设置样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font);
        //创建标题栏的单元格
        Cell cell;
        //第一个 项目代码
        cell = row.createCell(0);
        cell.setCellValue(ConstantUtils.INSPRO_EXPORT_CODE);
        cell.setCellStyle(style);
        //第二个 项目名称
        cell = row.createCell(1);
        cell.setCellValue(ConstantUtils.INSPRO_EXPORT_NAME);
        cell.setCellStyle(style);
        //第三个 检验依据(检验标准)
        cell = row.createCell(2);
        cell.setCellValue(ConstantUtils.INSPRO_EXPORT_STANDARD);
        cell.setCellStyle(style);
	}
	
	/**
	 * 检验项目导出数据填充
	* @Title：createInsPoject
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年7月22日
	 */
	public void createInsPoject(Workbook workbook, Sheet sheet) {
		//填充数据,查询所有数据
		List<InsProjectEO> rows = insProjectEODao.findAll();
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(InsProjectEO insProjectEO : rows) {
        	Row rowAdd = sheet.createRow(rowNum);
        	//项目代码
        	rowAdd.createCell(0).setCellValue(insProjectEO.getProCode());
        	//项目名称
        	rowAdd.createCell(1).setCellValue(insProjectEO.getProName());
        	//检验依据(检验标准)
        	List<String> stdValueList = new ArrayList<>();
        	for(StandardEO standardEO : insProjectEO.getStandardList()) {
        		stdValueList.add(standardEO.getStdNo());
        	}
        	//去除最后一个逗号
        	String stdValue = StringUtils.join(stdValueList, ConstantUtils.COMMA);
        	rowAdd.createCell(2).setCellValue(stdValue);
        	//继续创建下一行
        	rowNum ++;
        }
	}
	
	/**
	 * 批量导入
	* @Title：insProBatchImport
	* @param file
	* @return
	* @throws Exception
	* @return: ResponseMessage
	* @author： ljy  
	* @date： 2019年7月23日
	 */
	public ResponseMessage insProBatchImport(MultipartFile file, String type) throws Exception {
		ResponseMessage result = new ResponseMessage();
		Workbook workbook = Utils.getWorkbook(file);
		//我们通过 getSheetAt(0) 指定表格索引来获取对应表格,注意表格索引从 0 开始！
		int sheetNum=0;
		// FSheet 代表 Excel 文件中的一张表格,
		Sheet sheet = workbook.getSheetAt(sheetNum);
		//校验数据完整性
		result = Utils.checkExcelData(sheet);
		if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
			//校验检验标准是否存在
			result = checkStdNo(sheet);
			if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
				//导入业务逻辑
				saveImportData((List<JSONObject>)result.getData(), type);
			}else {
				return result;
			}
		}
		return result;
	}
	
	/**
	 * 保存检验项目数据
	* @Title：saveImportData
	* @param list
	* @throws Exception
	* @return: void
	* @author： ljy  
	* @date： 2019年7月23日
	 */
	public void saveImportData(List<JSONObject> list, String type) throws Exception {
		for(JSONObject json : list) {
			InsProjectEO insProjectEO = new InsProjectEO();
			//设置UUID
			insProjectEO.setId(UUID.randomUUID());
			//设置时间
			String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
			insProjectEO.setCreateTime(date);
			insProjectEO.setCreateBy(UserUtils.getUser());
			insProjectEO.setUpdateBy(UserUtils.getUser());
			insProjectEO.setUpdateTime(date);
			//删除标记 0 未删除;  1 删除
			insProjectEO.setDelFlag(0);
			//设置父级
			if(StringUtils.isEmpty(insProjectEO.getProBelongId())) {
				insProjectEO.setProBelongId(ConstantUtils.PARENT_ID);
			}
			//项目code
			insProjectEO.setProCode(json.get(ConstantUtils.INSPRO_IMPORT_CODE).toString());
			//项目名称
			insProjectEO.setProName(json.get(ConstantUtils.INSPRO_IMPORT_NAME).toString());
			//试验方法
			insProjectEO.setTrialMethod(json.get(ConstantUtils.INSPRO_IMPORT_TRIALMETHOD).toString());
			//项目说明
			insProjectEO.setProDesc(json.get(ConstantUtils.INSPRO_IMPORT_PRODESC).toString());
			//类型
			String oneTypeName = json.get(ConstantUtils.INSPRO_IMPORT_ONETYPE).toString();
			String twoTypeName = json.get(ConstantUtils.INSPRO_IMPORT_TWOTYPE).toString();
			String threeTypeName = json.get(ConstantUtils.INSPRO_IMPORT_THREETYPE).toString();
			//第三级
			List<InsTypeEO> threeTypeList = insTypeEODao.findByTypeName(threeTypeName);
			if(CollectionUtils.isNotEmpty(threeTypeList)) {
				if(threeTypeList.size() == 1) {
					insProjectEO.setProTypeId(threeTypeList.get(0).getId());
				}else {
					String typeId = insTypeEODao.findIdByName(oneTypeName, twoTypeName, threeTypeName);
					insProjectEO.setProTypeId(typeId);
				}
			}
			//保存检验项目
			insProjectEODao.insert(insProjectEO);
			//保存至关联表
			String stdIds = json.get(ConstantUtils.INSPRO_IMPORT_STANDARD).toString();
			if(StringUtils.isNotEmpty(stdIds)) {
				String[] splits = stdIds.split(ConstantUtils.COMMA);
				List<String> splitsArray = Arrays.asList(splits);
				//关联实体
				for (int i = 0; i < splitsArray.size(); i++) {
					InsProStdEO insProStdEO = new InsProStdEO();
					insProStdEO.setProId(insProjectEO.getId());
					insProStdEO.setStdId(splitsArray.get(i));
					insProStdEODao.insert(insProStdEO);
				}
			}
		}
	} 
	
	/**
	 * 校验所有检验标准号 是否存在
	* @Title：checkStdNo
	* @param sheet
	* @return
	* @return: ResponseMessage
	* @author： ljy  
	* @date： 2019年7月23日
	 */
	public ResponseMessage checkStdNo(Sheet sheet) {
		ResponseMessage result = new ResponseMessage();
		//获取Excel中所有的数据
		List<JSONObject> jsonList = getExcelInsProData(sheet, new ArrayList<JSONObject>());
		//返回新的组合
		List<JSONObject> newList = new ArrayList<JSONObject>();
		//不符合数据信息返回
		String inconformityMsg = "";
		//获取所有检验标准编号
		List<String> list = standardEODao.findAll();
		//循环遍历
		for(JSONObject json : jsonList) {
			 String msg = "";
			 //依据因为逗号分隔数据
			 String[] split = json.get(ConstantUtils.INSPRO_IMPORT_STANDARD).toString().split(ConstantUtils.COMMA);
             List<String> stdNoStrs = Arrays.asList(split);
	         //返回符合数据的检验标准id,用于关联
	     	 String stdNoIds = "";
             //判断当前检验标准库中是否含有当前标准号
             for(String str : stdNoStrs) {
            	 if(!list.contains(str)) {
            		 //返回检验标准错误集合
            		 msg = json.get(ConstantUtils.ROW_NUM) + ConstantUtils.COMMA;
            		 break;
            	 }else {
            		//返回检验标准ids
            		 stdNoIds += standardEODao.selectByStdNo(str) + ConstantUtils.COMMA;
            	 }
             }
            inconformityMsg += msg;
            //返回包含检验标准号的集合
            JSONObject jsonObj = new JSONObject();
 			//检验项目code
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_CODE, json.get(ConstantUtils.INSPRO_IMPORT_CODE));
 			
 			//检验项目第一类型
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_ONETYPE, json.get(ConstantUtils.INSPRO_IMPORT_ONETYPE));
 			
 			//检验项目第二类型
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_TWOTYPE, json.get(ConstantUtils.INSPRO_IMPORT_TWOTYPE));
 			
 			//检验项目第三类型
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_THREETYPE, json.get(ConstantUtils.INSPRO_IMPORT_THREETYPE));
 			
 			//检验项目名称
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_NAME, json.get(ConstantUtils.INSPRO_IMPORT_NAME));
 			//检验依据(标准)
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_STANDARD, stdNoIds);
 			
 			//试验方法
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_TRIALMETHOD, json.get(ConstantUtils.INSPRO_IMPORT_TRIALMETHOD));
 			//项目说明
 			jsonObj.put(ConstantUtils.INSPRO_IMPORT_PRODESC, json.get(ConstantUtils.INSPRO_IMPORT_PRODESC));
 			
 			//返回当前行号
 			jsonObj.put(ConstantUtils.ROW_NUM, json.get(ConstantUtils.ROW_NUM));
 			newList.add(jsonObj);
 			
		}
		//如果错误信息不为空, 只返回错误信息
        if(StringUtils.isNotEmpty(inconformityMsg)) {
    		result.setMessage(inconformityMsg.substring(0, inconformityMsg.length() - 1));
    		result.setRespCode(ConstantUtils.RETURN_MISS);
        }else {//如果错误信息为空,则直接返回包含ids的数据
        	result.setData(newList);
    		result.setRespCode(ConstantUtils.RETURN_SUCCESS);
        }
		//返回结果
		return result;
	}
	
	/**
	 * 获取Excel中所有的数据
	* @Title：getExcelInsProData
	* @param sheet
	* @param list
	* @return
	* @return: List<JSONObject>
	* @author： ljy  
	* @date： 2019年7月23日
	 */
	public List<JSONObject> getExcelInsProData(Sheet sheet, List<JSONObject> list){
		int firstRowNum  = sheet.getFirstRowNum();
		//获得当前sheet的结束行
		int lastRowNum = sheet.getLastRowNum();
		//循环除了第一行的所有行  应该从第一条开始循环此处数字错误
		for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
			//获得当前行
			Row row = sheet.getRow(rowNum);
			if(row == null){
				continue;
			}
			//当前行数据
			JSONObject jsonObj = new JSONObject();
			//检验项目code
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_CODE, Utils.getCellValue(row.getCell(1)).trim());
			//第一类型
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_ONETYPE, Utils.getCellValue(row.getCell(2)).trim());
			//第二类型
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_TWOTYPE, Utils.getCellValue(row.getCell(3)).trim());
			//第三类型
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_THREETYPE, Utils.getCellValue(row.getCell(4)).trim());
			//检验项目名称
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_NAME, Utils.getCellValue(row.getCell(5)).trim());
			//检验依据(标准)
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_STANDARD, Utils.getCellValue(row.getCell(6)).trim());
			//试验方法
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_TRIALMETHOD, Utils.getCellValue(row.getCell(8)).trim());
			//项目说明
			jsonObj.put(ConstantUtils.INSPRO_IMPORT_PRODESC, Utils.getCellValue(row.getCell(9)).trim());
			//返回当前行号
			jsonObj.put(ConstantUtils.ROW_NUM, rowNum);
			//判断当前list是否包含该数据,去重
			if(!list.contains(jsonObj)) {
				list.add(jsonObj);
			}
		}
		
		return list;
	}
	
	/**
	 *  根据检验项目id查询检验项目是否关联过试验任务
	* @Title：checkTrialTaskById
	* @param id
	* @return
	* @return: int
	* @author： ljy  
	* @date： 2019年9月23日
	 */
	public int checkTrialTaskById(String id) {
		//查询校验
		return insProjectEODao.checkTrialTaskById(id);
	}

	/**
	 * 通过试验任务ID来查出试验任务相关联的检验项目-分页
	 * @param page
	 * @param searchPhrase
	 * @return
	 */
	public List<InsProjectEO> pageForPcTask(InsProjectEOPage page, String searchPhrase) {
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) &&
				StringUtils.isNotEmpty(searchPhrase.trim())){
			searchPhrase = searchPhrase.trim();
			Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
			Matcher dateMatcher = datePattern.matcher(searchPhrase);
			List<String> list = new ArrayList<>();
			while (dateMatcher.find()) {
				String search = dateMatcher.group();
				list.add(search);
			}
			page.setSearchPhrase(list);
		}
		//--------------单条件查询-------------//
		List<InsProjectEO> pageList = insProjectEODao.queryByPageForPcTask(page);
		List<InsProjectEO> list = new ArrayList<>();
		for(InsProjectEO eo : pageList) {
			InsProjectEO insPro = insProjectEODao.selectByPrimaryKey(eo.getId());
			list.add(insPro);
		}
		return list;
	}

	public Integer queryByCountForPcTask(InsProjectEOPage page) {
		return insProjectEODao.queryByCountForPcTask(page);
	}
	
	
	
	
	
}
