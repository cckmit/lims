package com.adc.da.internalcost.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.internalcost.dao.InternalCostEODao;
import com.adc.da.internalcost.entity.InternalCostEO;
import com.adc.da.internalcost.page.InternalCostEOPage;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 费用管理模块--内部费用库
 * @author ljy
 * @date 2019年8月5日
 */
@Service("internalCostEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InternalCostEOService extends BaseService<InternalCostEO, String> {

	@Autowired
	private InternalCostEODao internalCostEODao;
	
	public InternalCostEODao getDao() {
		return internalCostEODao;
	}
	
    /**
     * 内部费用库分页查询
    * @Title：page
    * @param internalCostVO
    * @param pageNo
    * @param pageSize
    * @param searchPhrase
    * @return
    * @return: ResponseMessage<PageInfo<InternalCostVO>>
    * @author： ljy  
    * @date： 2019年8月5日
     */
	public List<InternalCostEO> page(InternalCostEOPage page, String searchPhrase) {
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
		
		//试验项目
		if(StringUtils.isNotEmpty(page.getProName())) {
			page.setProName(page.getProName());
		}
		//试验项目编号
		if(StringUtils.isNotEmpty(page.getProCode())) {
			page.setProCode(page.getProCode());
		}
		//负责组
		if(StringUtils.isNotEmpty(page.getLabName())) {
			page.setLabName(page.getLabName());
		}
		//检验标准
		if(StringUtils.isNotEmpty(page.getCostStdNos())) {
			page.setCostStdNos(page.getCostStdNos());
		}
		//检验名称
		if(StringUtils.isNotEmpty(page.getCostStdNames())) {
			page.setCostStdNames(page.getCostStdNames());
		}
		//查询
		return internalCostEODao.queryByPage(page);
	}
	
	/**
	 * 内部费用库保存
	* @Title：save
	* @param internalCostEO
	* @return
	* @throws Exception
	* @return: InternalCostEO
	* @author： ljy  
	* @date： 2019年8月6日
	 */
	public InternalCostEO save(InternalCostEO internalCostEO) throws Exception {
		//设置UUID
		internalCostEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		internalCostEO.setCreateTime(date);
		internalCostEO.setCreateBy(UserUtils.getUser());
		internalCostEO.setUpdateBy(UserUtils.getUser());
		internalCostEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		internalCostEO.setDelFlag(0);
		//保存
		internalCostEODao.insert(internalCostEO);
		return internalCostEO;
	}
	
	/**
	 * 内部费用库编辑
	* @Title：edit
	* @param internalCostEO
	* @return
	* @throws Exception
	* @return: InternalCostEO
	* @author： ljy  
	* @date： 2019年8月6日
	 */
	public InternalCostEO edit(InternalCostEO internalCostEO) throws Exception {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		internalCostEO.setUpdateBy(UserUtils.getUser());
		internalCostEO.setUpdateTime(date);
		//更新
		internalCostEODao.updateByPrimaryKeySelective(internalCostEO);
		return internalCostEO;
	}
	
	
	/**
	 * 内部费用库导出
	* @Title：internalCostBatchExport
	* @param response
	* @param request
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年8月8日
	 */
	public void internalCostBatchExport(HttpServletResponse response, HttpServletRequest request) throws IOException {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		createTitle(workbook, sheet);
        //填充数据
		createInternalCost(workbook, sheet);
        //生成Excel文件名称
		//ex : 20190808内部费用库导出．xlsx
		//获取当前日期
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + ConstantUtils.INTERNALCOST_EXPORT;
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
	 * 内部费用库导出--创建标题栏
	* @Title：createTitle
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年8月8日
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
        //第一个 试验项目
        cell = row.createCell(0);
        cell.setCellValue(ConstantUtils.INTERNALCOST_INSPRONAME);
        cell.setCellStyle(style);
        //第二个 试验项目编号
        cell = row.createCell(1);
        cell.setCellValue(ConstantUtils.INTERNALCOST_INSPROCODE);
        cell.setCellStyle(style);
        //第三个 负责组
        cell = row.createCell(2);
        cell.setCellValue(ConstantUtils.INTERNALCOST_INSPROORG);
        cell.setCellStyle(style);
        //第四个 检验标准
        cell = row.createCell(3);
        cell.setCellValue(ConstantUtils.INTERNALCOST_STDNO);
        cell.setCellStyle(style);
        //第五个 标准名称
        cell = row.createCell(4);
        cell.setCellValue(ConstantUtils.INTERNALCOST_STDNAME);
        cell.setCellStyle(style);
        //第六个 单价
        cell = row.createCell(5);
        cell.setCellValue(ConstantUtils.INTERNALCOST_PRICE);
        cell.setCellStyle(style);
        //第七个 单位
        cell = row.createCell(6);
        cell.setCellValue(ConstantUtils.INTERNALCOST_UNIT);
        cell.setCellStyle(style);
        //第八个 备注
        cell = row.createCell(7);
        cell.setCellValue(ConstantUtils.INTERNALCOST_REMARKS);
        cell.setCellStyle(style);
	}
	
	/**
	 * 内部费用库导出--填充数据
	* @Title：createInternalCost
	* @param workbook
	* @param sheet
	* @return: void
	* @author： ljy  
	* @date： 2019年8月8日
	 */
	public void createInternalCost(Workbook workbook, Sheet sheet) {
		//填充数据,查询所有数据
		List<InternalCostEO> rows = internalCostEODao.findAll();
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(InternalCostEO internalCostEO : rows) {
        	Row rowAdd = sheet.createRow(rowNum);
        	//试验项目
        	rowAdd.createCell(0).setCellValue(internalCostEO.getProName());
        	//试验项目编号
        	rowAdd.createCell(1).setCellValue(internalCostEO.getProCode());
        	//负责组
        	rowAdd.createCell(2).setCellValue(internalCostEO.getLabName());
        	//检验标准
        	rowAdd.createCell(3).setCellValue(internalCostEO.getCostStdNos());
        	//标准名称
        	rowAdd.createCell(4).setCellValue(internalCostEO.getCostStdNames());
        	//单价
        	rowAdd.createCell(5).setCellValue(internalCostEO.getCostPrice());
        	//单位
        	rowAdd.createCell(6).setCellValue(internalCostEO.getCostUnit());
        	//备注
        	rowAdd.createCell(7).setCellValue(internalCostEO.getRemarks());
        	//继续创建下一行
        	rowNum ++;
        }
	}
}
