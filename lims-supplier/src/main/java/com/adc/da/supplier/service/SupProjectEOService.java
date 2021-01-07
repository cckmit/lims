package com.adc.da.supplier.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.supplier.dao.SupProjectEODao;
import com.adc.da.supplier.entity.SupProjectEO;
import com.adc.da.supplier.page.SupTestProjectVOPage;
import com.adc.da.supplier.vo.SupProjectVO;
import com.adc.da.util.Utils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;


/**
 *
 * <br>
 * <b>功能：</b>SUP_PROJECT ProjectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("projectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SupProjectEOService extends BaseService<SupProjectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SupProjectEOService.class);

    @Autowired
    private SupProjectEODao dao;

    public SupProjectEODao getDao() {
        return dao;
    }


    /**
     * 批量保存检验项目
     * @param projectEOList
     * @param supId
     */
    public void batchSavePro(List<SupProjectEO> projectEOList, String supId){
    	List<SupProjectEO> newList = new ArrayList<>();
        for(SupProjectEO projectEO:projectEOList){
            //供应商id
            projectEO.setSupAbilityId(supId);
            projectEO.setDelFlag("0");
            //计算折后价
            if(StringUtils.isNotEmpty(projectEO.getSupPrice()) 
            		&& StringUtils.isNotEmpty(projectEO.getSupDiscount())) {
            	double discountPrice = Double.parseDouble(projectEO.getSupPrice()) * 
            			Double.parseDouble(projectEO.getSupDiscount());
            	projectEO.setSupDiscountPrice(String.valueOf(Math.round(discountPrice)));
            }
            newList.add(projectEO);
        }
        dao.insertBatchSelective(newList);
    }

    /**
     * 批量删除检验项目
     * @param supId
     */
    public void batchDelete(String supId){
        dao.batchDelete(supId);
    }

    /**
     * chakangongyingshangnengli
     * @param supId
     * @return
     */
    public List<SupProjectEO> findListBySupId(String supId){
        return dao.findListBySupId(supId);
    }


    
    /**
     * 供应商能力检验项目导入
     * @Title: importSupProject
     * @param file
     * @return
     * @throws IOException
     * @return List<SupProjectVO>
     * @author: ljy
     * @date: 2020年3月9日
     */
    public List<SupProjectEO> importSupProject(MultipartFile file) throws IOException{
    	ResponseMessage result = new ResponseMessage();
		Workbook workbook = Utils.getWorkbook(file);
		//我们通过 getSheetAt(0) 指定表格索引来获取对应表格,注意表格索引从 0 开始！
		int sheetNum=0;
		// FSheet 代表 Excel 文件中的一张表格,
		Sheet sheet = workbook.getSheetAt(sheetNum);
		//校验数据完整性
		//result = Utils.checkExcelData(sheet);
		//if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
			return getExcelSupProData(sheet);
		//}
		//return null;
    }
    
    
    /**
     * 获取Excel中数据
     * @Title: getExcelSupProData
     * @param sheet
     * @return
     * @return List<SupProjectVO>
     * @author: ljy
     * @date: 2020年3月9日
     */
    public List<SupProjectEO> getExcelSupProData(Sheet sheet){
    	List<SupProjectEO> list = new ArrayList<>();
		int firstRowNum  = sheet.getFirstRowNum();
		//获得当前sheet的结束行
		int lastRowNum = sheet.getLastRowNum();
		//循环除了第一行的所有行
		for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
			//获得当前行
			Row row = sheet.getRow(rowNum);
			if(row == null){
				continue;
			}
			//当前行数据
			SupProjectEO jsonObj = new SupProjectEO();
			//id
			jsonObj.setId(UUID.randomUUID());
			//项目代号
			jsonObj.setProjectCode(Utils.getCellValue(row.getCell(1)));
			//委托类型 3C，环保，认证
			String type = "";
			switch (Utils.getCellValue(row.getCell(2))) {
				case "3C":
					type = "0";
					break;
				case "环保":
					type = "1";
					break;
				case "公告":
					type = "2";
					break;
				case "安全达标":
					type = "3";
					break;
				case "委托项目":
					type = "4";
					break;
				case "新能源":
					type = "5";
					break;
				case "出口认证":
					type = "6";
					break;
				case "纯电动车定型":
					type = "7";
					break;
				case "混合动力汽车定型":
					type = "8";
					break;
				case "上海新能源登记申请试验项目报价":
					type = "9";
					break;
				case "出口项目":
					type = "10";
					break;
				case "其他":
					type = "11";
					break;
				default:
					break;
			}
			jsonObj.setTestType(type);
			//试验对象  整车、零部件
			String testObj = "";
			switch (Utils.getCellValue(row.getCell(3))) {
				case "整车":
					testObj = "0";
					break;
				case "零部件":
					testObj = "1";
					break;
				default:
					break;
			}
			jsonObj.setTestObj(testObj);
			//试验项目
			jsonObj.setTestProject(Utils.getCellValue(row.getCell(4)));
			//依据标准
			jsonObj.setTestStandard(Utils.getCellValue(row.getCell(5)));
			//标准名称
			jsonObj.setTestStandardName(Utils.getCellValue(row.getCell(6)));
			//试验方法
			jsonObj.setTestMethod(Utils.getCellValue(row.getCell(7)));
			//单价
			jsonObj.setSupPrice(Utils.getCellValue(row.getCell(8)));
			//折扣
			jsonObj.setSupDiscount(Utils.getCellValue(row.getCell(9)));
			list.add(jsonObj);
		}
		return list;
	}
    

    /**
     * 获取供应商检验项目
     * @Title: selectSupProjectByPage
     * @param page
     * @return
     * @return List<SupTestProjectVO>
     * @author: ljy
     * @date: 2020年3月10日
     */
	public List<SupProjectEO> selectSupProjectByPage(SupTestProjectVOPage page){
        return dao.selectSupProjectByPage(page);
	}
	
    public int selectSupProjectByCount(SupTestProjectVOPage page) {
    	return dao.selectSupProjectByCount(page);
    }
}
