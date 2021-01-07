package com.adc.da.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.NClob;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.dao.QuestionEODao;
import com.adc.da.dao.QuestionUserEODao;
import com.adc.da.dao.TopicsEODao;
import com.adc.da.entity.QuestionEO;
import com.adc.da.entity.QuestionUserEO;
import com.adc.da.entity.TopicsEO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.dao.MessageEODao;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.page.TopicsEOPage;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.vo.AnswerVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 问卷调查
 * @author Administrator
 *
 */
@Service("TopicsEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TopicsEOService extends BaseService<TopicsEO, String> {
	@Autowired
	private TopicsEODao topicsEODao;
	
	public TopicsEODao getDao() {
		return topicsEODao;
	}
	
	@Autowired
	public QuestionEODao questionEODao;
	
	@Autowired
	public QuestionUserEODao questionUserEODao;
	
	@Autowired
	public OrgEODao orgEODao;
	
	@Autowired
	public MessageEODao messageEODao;
	
	
	/**
	 *  问卷调查-分页查询
	 * @Title: page
	 * @param page
	 * @param searchPhrase
	 * @return
	 * @return List<TopicsEO>
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public List<TopicsEO> page(TopicsEOPage page, String searchPhrase){
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
		return topicsEODao.queryByPage(page);
	}

    /**
     *  问卷管理-分页查询
     */
    public List<TopicsEO> manageGetPage(TopicsEOPage page, String searchPhrase){
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
        return topicsEODao.manageQueryByPage(page);
    }


    /**
	 * 查询主题问题详情
	 * @Title: getDetailById
	 * @param id
	 * @return
	 * @return TopicsEO
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public TopicsEO getDetailById(String id) {
		TopicsEO topicsEO = topicsEODao.selectByPrimaryKey(id);
		List<QuestionEO> questionList =  questionEODao.selectByTopicsId(id);
		topicsEO.setQuestionList(questionList);
		return topicsEO;
	}
	
	
	/**
	 * 问卷调查-新增
	 * @Title: save
	 * @param topicsEO
	 * @return
	 * @return TopicsEO
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public TopicsEO save(TopicsEO topicsEO) {
		//设置UUID
		topicsEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		topicsEO.setCreateTime(date);
		topicsEO.setCreateBy(UserUtils.getUserId());
		topicsEO.setUpdateBy(UserUtils.getUserId());
		topicsEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		topicsEO.setDelFlag("0");
		topicsEODao.insert(topicsEO);
		//循环插入问题
		saveQuestion(topicsEO);
		return topicsEO;
	}
	
	
	/**
	 * 问卷调查-编辑
	 * @Title: edit
	 * @param topicsEO
	 * @return
	 * @return TopicsEO
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public TopicsEO edit(TopicsEO topicsEO) {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		topicsEO.setUpdateBy(UserUtils.getUserId());
		topicsEO.setUpdateTime(date);
		//删除标记 0 未删除;  1 删除
		topicsEODao.updateByPrimaryKeySelective(topicsEO);
		//循环插入问题
		saveQuestion(topicsEO);
		return topicsEO;
	}
	
	
	
	/**
	 * 保存问题
	 * @Title: saveQuestion
	 * @param topicsEO
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public void saveQuestion(TopicsEO topicsEO) {
		//先删除
		questionEODao.deleteByTopicsId(topicsEO.getId());
		//后保存
		if(CollectionUtils.isNotEmpty(topicsEO.getQuestionList())) {
			for (int i = 0; i < topicsEO.getQuestionList().size(); i++) {
				QuestionEO question = new QuestionEO();
				question.setId(UUID.randomUUID());
				//删除标记 0 未删除;  1 删除
				question.setDelFlag("0");
				//选项内容
				question.setOptionContent(topicsEO.getQuestionList().get(i).getOptionContent());
				//问题标题
				question.setQueName(topicsEO.getQuestionList().get(i).getQueName());
				//问题类型
				question.setQueType(topicsEO.getQuestionList().get(i).getQueType());
				//问题排序
				question.setQueSort(i+1);
				//主题id外键
				question.setTopicsId(topicsEO.getId());
				questionEODao.insert(question);
			}
		}
	}
	
	
	/**
	 * 问卷调查-发布
	 * @Title: publishTopics
	 * @param topicsEO
	 * @return void
	 * @author: ljy
	 * @throws Exception 
	 * @date: 2020年2月24日
	 */
	public void  publishTopics(TopicsEO topicsEO) throws Exception {
		//给用户发消息
		List<UserEO> userList = new ArrayList<>();
		List<String> orgIds = new ArrayList<>();
		List<OrgEO> orgList = orgEODao.getChildren(topicsEO.getOrgId(), "");
		for (OrgEO org : orgList) {
			orgIds.add(org.getId());
			userList.addAll(orgEODao.listUserEOByOrgId(org.getId()));
		}
		//发布,设置状态为1
		topicsEO.setTopicsStatus("1");
		topicsEO.setPublishUserCount(String.valueOf(userList.size()));
		topicsEO.setOrgIds(orgIds.toString());
		if(StringUtils.isNotEmpty(topicsEO.getId())) {
			edit(topicsEO);
		}else {
			save(topicsEO);
		}
		for (int i = 0; i < userList.size(); i++) {
			MessageEO msg = new MessageEO();
			//当前日期
	      	String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
			msg.setId(UUID.randomUUID());
			msg.setBusinessId(topicsEO.getId());
			msg.setCcCreateById(userList.get(i).getUsid());
			msg.setIsread("0");
			msg.setSendlink(ConstantUtils.QUESTION_USER_CODE);
			msg.setSendtime(date);
			msg.setSenduser(UserUtils.getUser().getUsname());
			msg.setTitle("邀请您参与问卷调查【"+topicsEO.getTopicsName() +"");
			messageEODao.insertSelective(msg);
		}
	}
	
	
	/**
	 * 问卷调查-删除
	 * @Title: deleteById
	 * @param id
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	public void deleteById(String id) {
		//删除主题
		topicsEODao.deleteByPrimaryKey(id);
		//删除问题
		questionEODao.deleteQuestion(id);
	}
	
	
	/**
	 * 问卷调查-用户答卷
	 * @Title: questionUser
	 * @param questionUserEO
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月24日
	 */
	public void questionUser(QuestionUserEO questionUserEO) {
		//设置UUID
		questionUserEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		questionUserEO.setCreateTime(date);
		questionUserEO.setCreateBy(UserUtils.getUserId());
		questionUserEO.setUpdateBy(UserUtils.getUserId());
		questionUserEO.setUpdateTime(date);
		JSONArray jobj = JSONArray.fromObject(questionUserEO.getContextObj());
		questionUserEO.setContext(jobj.toString());
		//删除标记 0 未删除;  1 删除
		questionUserEO.setDelFlag("0");
		questionUserEODao.insert(questionUserEO);
	}
	
	
	
	  /**
     * 问卷调查-用户答卷导出
     * @Title: questionUser
     * @param questionUserVO
     * @return
     * @return ResponseMessage
     * @author: ljy
	 * @throws IOException 
     * @date: 2020年2月24日
     */
	public void exportQuestionUser(HttpServletResponse response, 
    		HttpServletRequest request, String topicsId) throws Exception {
		//工作空间
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		//设置标题栏
		TopicsEO topicsEO = topicsEODao.selectByPrimaryKey(topicsId);
		List<String> questionIdList = createTitle(workbook, sheet, topicsEO);
		//填充数据
		createQuestionUseData(workbook, sheet, topicsId, questionIdList);
        //生成Excel文件名称
		//ex : 20190722问卷主题导出．xlsx
		//获取当前日期
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT2);
        String fileName = date + "问卷调查["+ topicsEO.getTopicsName() +"]";
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
	
	
	
	/**标题栏
	 * 问卷调查-用户答卷导出 
	 * @Title: createTitle
	 * @param workbook
	 * @param sheet
	 * @param topicsEO
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月26日
	 */
	public List<String> createTitle(Workbook workbook, Sheet sheet, TopicsEO topicsEO) {
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
        //问卷主题
        cell = row.createCell(0);
        cell.setCellValue(topicsEO.getTopicsName());
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        List<QuestionEO> questionList = questionEODao.selectByTopicsId(topicsEO.getId());
        int num = 10;
        int size = questionList.size();
        //问题id  list
        List<String> questionIdList = new ArrayList<String>();
        questionIdList.add("0");
        if(CollectionUtils.isNotEmpty(questionList)) {
        	num = questionList.size();
        	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, num));
        	Row row1 = sheet.createRow(1);
        	//创建问卷问题标题
            Cell cell1;
            cell1 = row1.createCell(0);
            cell1.setCellStyle(style);
        	cell1.setCellValue("姓名");
            //问题内容
            for (int i = 0; i < size; i++) {
            	cell1 = row1.createCell(i + 1);
                cell1.setCellStyle(style);
            	cell1.setCellValue(questionList.get(i).getQueName());
            	questionIdList.add(questionList.get(i).getId());
			}
        }else {
        	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, num));
        }
        
        return questionIdList;
	}
	
	
	/**问题内容
	 * 问卷调查-用户答卷导出 
	 * @Title: createQuestionUseData
	 * @param workbook
	 * @param sheet
	 * @param topicsId
	 * @return void
	 * @author: ljy
	 * @throws Exception 
	 * @date: 2020年2月26日
	 */
	public void createQuestionUseData(Workbook workbook, Sheet sheet, 
			String topicsId, List<String> questionIdList) throws Exception {
		
		//填充数据,查询所有数据
		List<QuestionUserEO> rows =  questionUserEODao.selectByTopicsId(topicsId);
		//新增数据行，并且设置单元格数据
		int rowNum = 2;
			for(QuestionUserEO questionUserEO : rows) {
				JSONArray fromObject = JSONArray.fromObject(questionUserEO.getContext());
				Row rowAdd = sheet.createRow(rowNum);
				for (int i = 0; i < questionIdList.size(); i++) {
				//用户名称
				if("0".equals(questionIdList.get(i))) {
					rowAdd.createCell(i).setCellValue(questionUserEO.getUserName());
					continue;
				}
				
				//用户答案
	        	for (Object object : fromObject) {
	        		JSONObject jobj = (JSONObject) object;
	        		if(questionIdList.get(i).equals(String.valueOf(jobj.get("questionId")))) {
	        			rowAdd.createCell(i).setCellValue(jobj.getString("answer"));
	        			break;
	        		}
				}
	        	
			}
				//继续创建下一行
				rowNum ++;
		}
	}

	public String clob2Str(NClob nclob) throws Exception {
	    String content = "";
	    try {
	        Reader is = nclob.getCharacterStream();
	        BufferedReader buff = new BufferedReader(is);// 得到流
	        String line = buff.readLine();
	        StringBuffer sb = new StringBuffer();
	        while (line != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
	            sb.append(line);
	            line = buff.readLine();
	        }
	        content = sb.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return content;
	}
	
	/**
	 * 统计
	 * @Title: countQuestionUser
	 * @param topicsId
	 * @return
	 * @return JSONObject
	 * @author: ljy
	 * @date: 2020年2月26日
	 */
	public JSONObject countQuestionUser(String topicsId) {
		//获取总答题人数
		TopicsEO topicsEO = topicsEODao.selectByPrimaryKey(topicsId);
		String allCount = topicsEO.getPublishUserCount();
		//获取已答题人数
		List<QuestionUserEO> questionUserEOList = questionUserEODao.selectByTopicsId(topicsId);
		int already = questionUserEOList.size();
		JSONObject json = new JSONObject();
		json.put("allCount", allCount);
		json.put("already", already);
		return json;
	}
	
	
    /**
     * 查看答题详情
     * @Title: selectByUserId
     * @param topicsId
     * @param userId
     * @return
     * @return QuestionUserEO
     * @author: ljy
     * @date: 2020年3月30日
     */
	public QuestionUserEO viewQuestionUser(String topicsId, String userId) {
		return questionUserEODao.selectByUserId(topicsId, userId);
	}
	
}
