package com.adc.da.pc_announcement.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adc.da.pc_announcement.VO.SupProjectVO;
import org.apache.commons.lang.StringUtils;
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
import com.adc.da.insproject.dao.InsProjectEODao;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.pc_announcement.VO.AnnPlanProgramImportVO;
import com.adc.da.pc_announcement.dao.AnnPlanProgramEODao;
import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;
import com.adc.da.util.Utils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;


/**
 *
 * <br>
 * <b>功能：</b>ANN_PLAN_PROGRAM AnnPlanProgramEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-25 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("annPlanProgramEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnnPlanProgramEOService extends BaseService<AnnPlanProgramEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanProgramEOService.class);

    @Autowired
    private AnnPlanProgramEODao dao;
    @Autowired
	private InsProjectEODao insProjectEODao;
    
    public AnnPlanProgramEODao getDao() {
        return dao;
    }

    /**
     * 查出所有申报项目对应的检验方案Id数组
     * @param projectIdList
     * @return
     */
    public List<String> getIdListByPjIDList(List<String> projectIdList) {
        return dao.getIdListByPjIDList(projectIdList);
    }

    /**
     * 通过ID数组，物理删除检验方案
     * @param programIdList
     */
    public void delByIdList(List<String> programIdList) {
        dao.delByIdList(programIdList);
    }
    
    /**
     * 上传方案,覆盖历史数据
     * @param file
     * @throws IOException 
     */
	public ResponseMessage<Collection<AnnPlanProgramImportVO>> importProgram(MultipartFile file) throws IOException {
		String code = "0";
		String message = "上传成功";
		
		//记录数据有误的行
		Map<String, List<String>> errorMap = new HashMap<>();
		//记录导入行记录
		List<AnnPlanProgramImportVO> list = new ArrayList<>();
		//记录所有的序号，用来判断是否重复
		List<String> noList = new ArrayList<>();
		
		Workbook workbook = Utils.getWorkbook(file);
		Iterator<Sheet> iterator = workbook.sheetIterator();
		while(iterator.hasNext()){
			Sheet sheet = iterator.next();
			int sheetIndex = workbook.getSheetIndex(sheet);//第几页
			int lastRowNum = sheet.getLastRowNum();//最大行数
			
			//判断当前页的项目类型
			String title = Utils.getCellValue(sheet.getRow(0).getCell(0));
			String pjType = "";
			if(title != null && title.contains("强")) {
				pjType = "QJ";
			}else if(title != null && title.contains("新")) {
				pjType = "XNY";
			}else if(title != null && title.contains("营")) {
				pjType = "YYHC";
			}
			
			//从第九行开始解析数据
			for(int rowNum = 8; rowNum < lastRowNum; rowNum++) {
				Row row = sheet.getRow(rowNum);
				String testPjNo = Utils.getCellValue(row.getCell(0));//检验项目编号
				String testPjName = Utils.getCellValue(row.getCell(1));//检验项目名称
				String testBasis = Utils.getCellValue(row.getCell(2));//检验依据
				String productId = Utils.getCellValue(row.getCell(4));//产品id
				String productType = Utils.getCellValue(row.getCell(5));//产品型号
				String testReportNo = Utils.getCellValue(row.getCell(6));//检验报告编号
				String actualPjAmount = Utils.getCellValue(row.getCell(7));//实测项目数
				String configExplain = Utils.getCellValue(row.getCell(8));//配置说明
				String pjDescription = Utils.getCellValue(row.getCell(9));//项目说明
				
				//根据实测项目数是否为空来判断该行是否导入
				if(StringUtils.isEmpty(actualPjAmount))
					continue;
				//判断检验项目编号、检验项目名称、检验依据是否为空
				if(StringUtils.isEmpty(testPjNo) || StringUtils.isEmpty(testPjName) || StringUtils.isEmpty(testBasis)) {
					List<String> rowNums = errorMap.get(sheetIndex + 1 + "") == null ? new ArrayList<>() : errorMap.get(sheetIndex + 1 + "");
					rowNums.add(rowNum + 1 + "");
					errorMap.put(sheetIndex + 1 + "", rowNums);
					continue;
				}
				//根据检验项目名称和项目类型判断检验项目是否存在
				InsProjectEO projectEO = insProjectEODao.getByNameAndType(testPjName, pjType);
				if(projectEO == null) {
					List<String> rowNums = errorMap.get(sheetIndex + 1 + "") == null ? new ArrayList<>() : errorMap.get(sheetIndex + 1 + "");
					rowNums.add(rowNum + 1 + "");
					errorMap.put(sheetIndex + 1 + "", rowNums);
					continue;
				}
				
				AnnPlanProgramImportVO program = new AnnPlanProgramImportVO();
				program.setId(UUID.randomUUID());
				program.setTpTestType("实测");
				program.setTpPjType(pjType);
				program.setTpTestPjNo(testPjNo);
				program.setTpTestPjName(testPjName);
				program.setTpTestPjId(projectEO.getId());
				program.setTpTestBasis(testBasis);
				program.setTpProductId(productId);
				program.setTpProductType(productType);
				program.setTpTestReportNo(testReportNo);
				program.setTpActualPjAmount(actualPjAmount);
				program.setTpConfigExplain(configExplain);
				program.setTpPjDescription(pjDescription);
				list.add(program);
				noList.add(testPjNo + "_" + pjType);//记录序号
			}
		}
		
		//对序号有重复的项目进行标记
		for(AnnPlanProgramImportVO program : list) {
			String v = program.getTpTestPjNo() + "_" + program.getTpPjType();
			if(noList.indexOf(v) != noList.lastIndexOf(v)) {
				program.setDuplicateFlag("1");
			}else {
				program.setDuplicateFlag("0");
			}
		}
		
		//如果有数据错误的行，不再进行上传，返回错误行数
		if(errorMap.values().size() > 0) {
			StringBuilder sb = new StringBuilder("数据有误，请请修改后重新上传，错误行数：");
			for(Map.Entry<String, List<String>> entry : errorMap.entrySet()) {		
				String sheetIndex = entry.getKey();
				List<String> rowNums = entry.getValue();
				sb.append("<br/>第" + sheetIndex + "页：");
				for(String num : rowNums) {
					sb.append(num + "、");
				}
				sb = new StringBuilder(sb.substring(0, sb.length() - 1) + "行");
			};
			code = "-1";
			message = sb.toString();
		}
		
		if(list == null  || list.isEmpty()) {
			code = "-1";
			message = "没有可上传的数据，请修改后重新上传";
		}
		
		return Result.success(code, message, list);
	}

	/**
	 * 通过ID数组，查出对应的检验方案
	 * @param idListByPjIDList
	 * @return
	 */
	public List<AnnPlanProgramEO> getProgramMesByIds(List<String> idListByPjIDList) {
		if(idListByPjIDList.size() != 0){
			return dao.selectByIDList(idListByPjIDList);
		}else {
			//为了前台处理数据方便。空的时候不返回null，传一个空list
			List<AnnPlanProgramEO> nullList = new ArrayList<>();
			return nullList ;
		}

	}

	/**
	 * 批量修改检验方案
	 * @param projectId
	 * @param programList
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void batchUpdateProgram(String projectId, List<AnnPlanProgramEO> programList) throws IllegalAccessException, InvocationTargetException {
		for(AnnPlanProgramEO program : programList) {
			program.setProjectId(projectId);
			//根据id判断是更新还是新增
			if(StringUtils.isNotBlank(program.getId())) {
				//如果是更新的话将单价、系数、价格信息先保存起来，防止删除历史数据的时候丢失
				AnnPlanProgramEO entity = dao.selectByPrimaryKey(program.getId());
				if(entity != null) {					
					program.setTpUnitPrice(entity.getTpUnitPrice());
					program.setTpCoefficient(entity.getTpCoefficient());
					program.setTpTotalPrice(entity.getTpTotalPrice());
				}else {
					program.setId(UUID.randomUUID());
				}
			}else {
				//新增
				program.setId(UUID.randomUUID());
			}
		}
		
		//删除历史数据
		List<AnnPlanProgramEO> oldList = dao.findProgramByProjectId(projectId);
		oldList.stream().forEach(progrma -> dao.deleteByPrimaryKey(progrma.getId()));
		
		//保存数据
		programList.stream().forEach(progrma -> dao.insert(progrma));
	}

	/**
	 * 找出对应供应商，对应检验项目
	 * @param supplierID
	 * @param projectCode
	 * @return
	 */
    public List<SupProjectVO> getSupProject(String supplierID, String projectCode) {
    	return dao.getSupProject(supplierID,projectCode);
    }
}
