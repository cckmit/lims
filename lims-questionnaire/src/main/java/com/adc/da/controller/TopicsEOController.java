package com.adc.da.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.entity.QuestionUserEO;
import com.adc.da.entity.TopicsEO;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.insproject.vo.InsProjectVO;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.page.TopicsEOPage;
import com.adc.da.service.TopicsEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.vo.AnswerVO;
import com.adc.da.vo.QuestionUserVO;
import com.adc.da.vo.TopicsDetailVO;
import com.adc.da.vo.TopicsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
@RequestMapping("${restPath}/qm/topics")
@Api(tags = "QM-问卷调查模块")
@SuppressWarnings("all")
public class TopicsEOController extends BaseController<TopicsEO> {

	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(TopicsEOController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private TopicsEOService topicsEOService;
    
    
    
    /**
     * 问卷答题-分页查询
     * @Title: page
     * @param page
     * @param searchPhrase
     * @return
     * @return ResponseMessage<PageInfo<TopicsVO>>
     * @author: ljy
     * @date: 2020年2月21日
     */
	@ApiOperation(value = "|问卷答题分页查询")
	@BusinessLog(description = "问卷答题-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("qm:topics:page")
    public ResponseMessage<PageInfo<TopicsVO>> page(
    		TopicsEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
    		//查询
    		List<TopicsEO> rows = topicsEOService.page(page, searchPhrase);
    		//设置总条数
			Integer rowsCount = topicsEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), TopicsVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    @ApiOperation(value = "|问卷管理分页查询")
    @BusinessLog(description = "问卷管理-分页查询")
    @GetMapping("/managePage")
    @RequiresPermissions("qm:topics:managePage")
    public ResponseMessage<PageInfo<TopicsVO>> managePage(
            TopicsEOPage page,
            @RequestParam(value = "searchPhrase", required = false) String searchPhrase){
        try {
            //查询
            List<TopicsEO> rows = topicsEOService.manageGetPage(page, searchPhrase);
            //设置总条数
            Integer rowsCount = topicsEOService.queryByCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), TopicsVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    
    
	/**
	 * 问卷调查-详情
	 * @Title: getDetailById
	 * @param id
	 * @return
	 * @return ResponseMessage<TopicsDetailVO>
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
	@ApiOperation(value = "|问卷调查详情")
	@BusinessLog(description = "问卷调查-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("qm:topics:get")
    public ResponseMessage<TopicsDetailVO> getDetailById(
    		@PathVariable(value = "id") String id){
    	try {
    		return Result.success("0", "查询成功", beanMapper.map(
    				topicsEOService.getDetailById(id), TopicsDetailVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
	
	/**
	 * 问卷调查-新增
	 * @Title: save
	 * @param topicsDetailVO
	 * @return
	 * @return ResponseMessage<TopicsDetailVO>
	 * @author: ljy
	 * @date: 2020年2月21日
	 */
    @ApiOperation(value = "|问卷调查保存")
    @BusinessLog(description = "问卷调查-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("qm:topics:save")
    public ResponseMessage<TopicsDetailVO> save(@RequestBody TopicsDetailVO topicsDetailVO){
    	try {
    		//保存
    		topicsEOService.save(beanMapper.map(topicsDetailVO, TopicsEO.class));
    		return Result.success("0","新增成功!",topicsDetailVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
	
    
    
    /**
     * 问卷调查-编辑
     * @Title: edit
     * @param topicsDetailVO
     * @return
     * @return ResponseMessage<TopicsDetailVO>
     * @author: ljy
     * @date: 2020年2月21日
     */
    @ApiOperation(value = "|问卷调查编辑")
    @BusinessLog(description = "问卷调查-编辑")
    @PutMapping(path = "/edit")
    @RequiresPermissions("qm:topics:save")
    public ResponseMessage<TopicsDetailVO> edit(@RequestBody TopicsDetailVO topicsDetailVO){
    	try {
    		//保存
    		topicsEOService.edit(beanMapper.map(topicsDetailVO, TopicsEO.class));
    		return Result.success("0","编辑成功!",topicsDetailVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    
    /**
     * 问卷调查-发布
     * @Title: publishTopics
     * @param topicsDetailVO
     * @return
     * @return ResponseMessage<TopicsDetailVO>
     * @author: ljy
     * @date: 2020年2月24日
     */
    @ApiOperation(value = "|问卷调查发布")
    @BusinessLog(description = "问卷调查-发布")
    @PostMapping(path = "/publish")
    @RequiresPermissions("qm:topics:publish")
    public ResponseMessage<TopicsDetailVO> publishTopics(@RequestBody TopicsDetailVO topicsDetailVO){
    	try {
    		//保存
    		topicsEOService.publishTopics(beanMapper.map(topicsDetailVO, TopicsEO.class));
    		return Result.success("0","发布成功!",topicsDetailVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "发布失败!");
		}
    }
    
    
    
    
    /**
     * 问卷调查-删除
     * @Title: deleteById
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年2月21日
     */
    @ApiOperation(value = "|问卷调查删除")
    @BusinessLog(description = "问卷调查-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("qm:topics:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		topicsEOService.deleteById(id);
    		return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    
    
    /**
     * 问卷调查-用户答卷
     * @Title: questionUser
     * @param questionUserVO
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年2月24日
     */
    @ApiOperation(value = "|问卷调查用户答卷")
    @BusinessLog(description = "问卷调查-用户答卷")
    @PostMapping(path = "/questionUser")
    @RequiresPermissions("qm:topics:questionUser")
    public ResponseMessage questionUser(@RequestBody QuestionUserVO questionUserVO){
    	try {
    		//保存
    		topicsEOService.questionUser(beanMapper.map(questionUserVO, QuestionUserEO.class));
    		return Result.success("0","提交成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "提交失败!");
		}
    }
    
	
    
    /**
     * 问卷调查-用户答卷导出
     * @Title: questionUser
     * @param questionUserVO
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年2月24日
     */
    @ApiOperation(value = "|问卷调查用户答卷导出")
	@BusinessLog(description = "问卷调查-用户答卷导出")
    @GetMapping("/questionUser/export")
    @RequiresPermissions("qm:topics:export")
    public ResponseMessage exportQuestionUser(HttpServletResponse response, 
    		HttpServletRequest request, String topicsId){
    	try {
    		topicsEOService.exportQuestionUser(response, request, topicsId);
    		return Result.success("0", "导出成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","导出失败!");
		}
    }
    
    
    /**
     * 统计
     * @Title: countQuestionUser
     * @param topicsId
     * @return
     * @return ResponseMessage<JSONObject>
     * @author: ljy
     * @date: 2020年2月26日
     */
    @ApiOperation(value = "|问卷调查用户答卷统计")
	@BusinessLog(description = "问卷调查-用户答卷统计")
    @GetMapping("/questionUser/count")
    @RequiresPermissions("qm:topics:count")
    public ResponseMessage<JSONObject> countQuestionUser(String topicsId) {
    	try {
    		JSONObject obj = topicsEOService.countQuestionUser(topicsId);
    		return Result.success("0", "统计成功!", obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","统计失败!");
		}
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
    @ApiOperation(value = "|问卷调查用户答卷查看")
	@BusinessLog(description = "问卷调查-用户答卷查看")
    @GetMapping("/questionUser/view")
    @RequiresPermissions("qm:topics:view")
    public ResponseMessage<QuestionUserVO> viewQuestionUser(String topicsId, String userId){
    	try {
    		//查询
    		QuestionUserEO eo = topicsEOService.viewQuestionUser(topicsId, userId);
    		JSONArray jsonArray = JSONArray.fromObject(eo.getContext());
    		eo.setContextObj((List<AnswerVO>)jsonArray);
    		return Result.success("0","查询成功!", beanMapper.map(eo, QuestionUserVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
}
