package com.adc.da.standard.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PDFStampUtil;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.standard.dao.StandardEODao;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.standard.page.StandardEOPage;
import com.adc.da.stdtype.dao.StdTypeEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.Utils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * 基础数据模块--检验标准管理
 * @author Administrator
 * @date 2019年7月10日
 */
@Service("standardEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardEOService extends BaseService<StandardEO, String>{
	@Autowired
	private StandardEODao standardEODao;
	
	public StandardEODao getDao() {
		return standardEODao;
	}
	
    @Value("${file.path}")
    private String filepath;
    
    @Value("${watermark.name}")
    private String waterMarkName;
	
    @Autowired
    private IFileStore iFileStore;
   
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
    
    
    @Autowired
    private StdTypeEODao stdTypeEODao;
	
	/**
	 * 分页查询
	* @Title：page
	* @param StandardEOPage
	* @return
	* @return: List<StandardEO>
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public List<StandardEO> page(StandardEOPage page, String searchPhrase){
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
		//查询
		return standardEODao.queryByPage(page);
	}
	
	/**
	 * 保存检验标准信息
	* @Title：save
	* @param standardVO 实体
	* @param file 文件对象
	* @param filePath 文件路径
	* @return: StandardEO
	* @author： ljy  
	* @date： 2019年7月15日
	 * @throws Exception 
	 */
	public StandardEO save(StandardEO standardEO, MultipartFile file) throws Exception{
		//设置UUID
		standardEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		standardEO.setDelFlag(0); 
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		standardEO.setCreateBy(UserUtils.getUser());
		standardEO.setCreateTime(date);
		standardEO.setUpdateBy(UserUtils.getUser());
		standardEO.setUpdateTime(date);
		//文件上传
		if(StringUtils.isNotEmpty(file) && file.getSize() > 0) {
			//保存附件
			String fileId = saveAttachment(file);
		    //返回附件id
			standardEO.setStdFileId(fileId);
		}
		//保存
		standardEODao.insert(standardEO);
		return standardEO;
	}
	
	/**
	 * 编辑
	* @Title：edit
	* @param standardVO
	* @param file
	* @param filePath
	* @return
	* @throws Exception
	* @return: StandardEO
	* @author： ljy  
	* @date： 2019年7月15日
	 */
	public StandardEO edit(StandardEO standardEO, MultipartFile file) throws Exception{
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		standardEO.setUpdateBy(UserUtils.getUser());
		standardEO.setUpdateTime(date);
		//文件上传
		if(StringUtils.isNotEmpty(file) && file.getSize() > 0) {
			//保存附件
			String fileId = saveAttachment(file);
		    //返回附件id
			standardEO.setStdFileId(fileId);
		    
		}
		//编辑
		standardEODao.updateByPrimaryKeySelective(standardEO);
		return standardEO;
	}
	
	/**
	 * 附件保存
	* @Title：saveTsA
	* @param file
	* @param filePath
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年7月17日
	 */
	public String saveAttachment(MultipartFile file) throws Exception {
		//当前日期
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		//文件后缀
		String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
		//文件大小
		long size = file.getSize()/1024;
		String sizeStr = String.valueOf(size);
		//文件流
		InputStream is = file.getInputStream();
		//返回路径
	    String path = this.iFileStore.storeFile(is, fileExtension, "");
	    //保存附件
	    TsAttachmentEO tsEo = new TsAttachmentEO();
	    //设置UUID
	    tsEo.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
	    tsEo.setDelFlag(0); 
	    tsEo.setCreateBy(UserUtils.getUserId());
	    tsEo.setCreateTime(date);
	    tsEo.setUpdateBy(UserUtils.getUserId());
	    tsEo.setUpdateTime(date);
	    //原附件名称
	    tsEo.setAttachmentName(FileUtil.getFileName(file.getOriginalFilename()));
	    //附件大小（kb）
	    tsEo.setAttachmentSize(sizeStr);
	    //附件类型（后缀）
	    tsEo.setAttachmentType(fileExtension);
	    //保存路径
	    tsEo.setSavePath(path);
	    //图片加水印
	    //定义水印地址
	    String waterMarkPath = path.substring(0, path.lastIndexOf(ConstantUtils.SPOT));
	    String outputFile = waterMarkPath + ConstantUtils.WATERMARK + ConstantUtils.SPOT + fileExtension;
	    PDFStampUtil.waterMark(filepath + path, filepath + outputFile, waterMarkName);
	    //保存水印路径
	    tsEo.setWaterMarkPath(outputFile);
	    //保存
	    tsAttachmentEODao.insert(tsEo);
	    return tsEo.getId();
	    
	}

    
    /**
     * 检验标准批量导入
    * @Title：stdBatchImport
    * @param file
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月19日
     */
	@SuppressWarnings("all")
	public ResponseMessage stdBatchImport(MultipartFile file) throws Exception {
		ResponseMessage result = new ResponseMessage();
		//错误信息
		String errorMsg = "";
		Workbook workbook = Utils.getWorkbook(file);
		//我们通过 getSheetAt(0) 指定表格索引来获取对应表格,注意表格索引从 0 开始！
		int sheetNum=0;
		// FSheet 代表 Excel 文件中的一张表格,
		Sheet sheet = workbook.getSheetAt(sheetNum);
		//校验数据完整性
		result = Utils.checkExcelData(sheet);
		//导入业务逻辑
		if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
			//获取所有检验标准编号
			List<String> list = standardEODao.findAll();
			//校验成功 保存数据
			saveExcelStandard(sheet, list);
		}
		return result;
	}

	
	/**
	 * 保存Excel检验标准数据
	 * @throws Exception 
	 */
	public void saveExcelStandard(Sheet sheet, List<String> list) throws Exception{
		int firstRowNum  = sheet.getFirstRowNum();
		//获得当前sheet的结束行
		int lastRowNum = sheet.getLastRowNum();
		//循环除了第一行的所有行
		for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
			//获得当前行
			Row row = sheet.getRow(rowNum);
			//先校验是否数据库已存在该条数据
			String stdNoCell = Utils.getCellValue(row.getCell(2));//检验标准
            //根据产品张工要求进行变动 将已经存在的标准号进行覆盖
			if( StringUtils.isEmpty(stdNoCell)) {
				continue;
			}
            //获取每行数据作为一个标准保存
            StandardEO stdEO = new StandardEO();
            //设置UUID
            stdEO.setId(UUID.randomUUID());
            //如果已经存在该标准号 则进行查询该标准号的数据
            boolean isContains=list.contains(stdNoCell);
			if(isContains){
                String stdId = standardEODao.selectByStdNo(stdNoCell);
                stdEO = standardEODao.selectByPrimaryKey(stdId);
            }
			//删除标记  0 未删除;  1删除
			stdEO.setDelFlag(0); 
			//设置时间
			String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
			stdEO.setCreateBy(UserUtils.getUser());
			stdEO.setCreateTime(date);
			stdEO.setUpdateBy(UserUtils.getUser());
			stdEO.setUpdateTime(date);
			//设置其他属性
			//标准类别
			String stdTypeName = Utils.getCellValue(row.getCell(1));
			String stdTypeId = stdTypeEODao.selectIdByName(stdTypeName);
			stdEO.setStdTypeId(stdTypeId);
			//检验标准号
			stdEO.setStdNo(Utils.getCellValue(row.getCell(2)).trim());
			//检验标准名称
			stdEO.setStdName(Utils.getCellValue(row.getCell(3)).trim());
			//标准解读
			stdEO.setStdRead(Utils.getCellValue(row.getCell(4)).trim());
			//检验标准实施时间
            //根据产品张工要求，修改实施时间显示格式修改为"yyyy-MM-dd"
            Cell stdImplementDateColl= row.getCell(5);
            String stdImplementDate="";
			if (DateUtil.isCellDateFormatted(stdImplementDateColl)) {
				// 用于转化为日期格式
				Date d = stdImplementDateColl.getDateCellValue();
				DateFormat formater = new SimpleDateFormat(ConstantUtils.DATE_FORMAT4);
				String timeStr = formater.format(d);
                stdImplementDate = timeStr;
			}else {
                stdImplementDate = String.valueOf(stdImplementDateColl.getNumericCellValue());
			}
            stdEO.setStdImplementDate(stdImplementDate.trim());
            //检验标准状态
			String stdStatusCell = Utils.getCellValue(row.getCell(6)).trim();
			//实施中
			if(ConstantUtils.CH_STD_STATUS_VALID.equals(stdStatusCell)) {
				stdEO.setStdStatus(ConstantUtils.EN_STD_STATUS_VALID);
			}else if(ConstantUtils.CH_STD_STATUS_INVALID.equals(stdStatusCell)) {
				//作废
				stdEO.setStdStatus(ConstantUtils.EN_STD_STATUS_INVALID);
			}
            //如果已经有该数据就进行修改 否则添加
            if(isContains){
                standardEODao.updateByPrimaryKey(stdEO);
            } else {
                //保存至数据库
                standardEODao.insert(stdEO);
            }
			//将新保存至数据库的编号添加至校验集合
			list.add(stdEO.getStdNo());
		}
	}

	
	/**
	 * 检验标准-校验标准号唯一性
	 * @Title: checkNo
	 * @param id
	 * @param num
	 * @return
	 * @return boolean
	 * @author: ljy
	 * @date: 2019年12月30日
	 */
	public boolean checkNo(String id, String num) {
		//获取所有编码集合
		List<String> numList = standardEODao.checkNo(id);
		return numList.contains(num);
	}
	
}
