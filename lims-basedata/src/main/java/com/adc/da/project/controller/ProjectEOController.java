package com.adc.da.project.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

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
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.project.entity.ProjectEO;
import com.adc.da.project.page.ProjectEOPage;
import com.adc.da.project.service.ProjectEOService;
import com.adc.da.project.vo.ProjectSearchVO;
import com.adc.da.project.vo.ProjectVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 基础数据模块--项目管理
 * @author Administrator
 * @date 2019年7月10日
 */
@RestController
@RequestMapping("${restPath}/bm/pro")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class ProjectEOController extends BaseController<ProjectEO> {
	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProjectEOController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private ProjectEOService projectEOService;

    /**
        * 查询列表数据,不分页
    * @param proVo 实体
    * @return: ResponseMessage<PageInfo<ProjectVO>>
    * @author： ljy  
    * @date： 2019年7月10日
     */
    @ApiOperation(value = "|项目分页查询")
    @BusinessLog(description = "项目管理-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("bm:pro:page")
    public ResponseMessage<PageInfo<ProjectSearchVO>> page(
    		ProjectEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
    		List<ProjectEO> rows = projectEOService.page(page, searchPhrase);
    		//设置总条数
			Integer rowsCount = projectEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), ProjectSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    /**
     * 项目列表查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<List<ProjectSearchVO>>
    * @author： ljy  
    * @date： 2019年8月20日
     */
    @ApiOperation(value = "|项目列表查询")
    @BusinessLog(description = "项目管理-列表查询")
    @GetMapping("/list")
    @RequiresPermissions("bm:pro:list")
    public ResponseMessage<List<ProjectSearchVO>> findList(){
    	 try {
			return Result.success("0", "查询成功", beanMapper.mapList(projectEOService.findByList(), ProjectSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    /**
     * 获取项目详细信息
    * @Title：getDetialById
    * @param id
    * @return
    * @return: ResponseMessage<ProjectVO>
    * @author： ljy  
    * @date： 2019年7月11日
     */
    @ApiOperation(value = "|项目详情")
    @BusinessLog(description = "项目管理-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("bm:pro:get")
    public ResponseMessage<ProjectVO> getDetialById(@PathVariable(value = "id") String id) {
        try {
            return Result.success("0", "查询成功", beanMapper.map(projectEOService.selectByPrimaryKey(id), ProjectVO.class));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
        }
    }
    
    /**
        * 保存项目信息
    * @Title：save
    * @param projectVO
    * @return
    * @return: ResponseMessage<ProjectVO>
    * @author： ljy  
    * @date： 2019年7月11日
     */
    @ApiOperation(value = "|项目保存")
    @BusinessLog(description = "项目管理-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("bm:pro:save")
    public ResponseMessage<ProjectVO> save(@RequestBody ProjectVO projectVO) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(projectVO.getName())) {
    			return Result.error("-1", "项目名称不可为空");
    		}
    		if(StringUtils.isEmpty(projectVO.getNum())) {
    			return Result.error("-1", "项目编号不可为空");
    		}
    		//保存
    		projectEOService.save(beanMapper.map(projectVO, ProjectEO.class));
    		return Result.success("0","新增成功!",projectVO);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","新增失败!");
        }
    }
    
    /**
       * 编辑项目信息
    * @Title：edit
    * @param projectVO
    * @return
    * @return: ResponseMessage<ProjectVO>
    * @author： ljy  
    * @date： 2019年7月11日
     */
    @ApiOperation(value = "|项目编辑")
    @BusinessLog(description = "项目管理-编辑")
    @PutMapping(path = "/edit", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:pro:edit")
    public ResponseMessage<ProjectVO> update(@RequestBody ProjectVO projectVO) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(projectVO.getName())) {
    			return Result.error("-1", "项目名称不可为空");
    		}
    		if(StringUtils.isEmpty(projectVO.getNum())) {
    			return Result.error("-1", "项目编号不可为空");
    		}
    		//编辑
			projectEOService.edit(beanMapper.map(projectVO, ProjectEO.class));
			return Result.success("0","编辑成功!",projectVO);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
			return Result.error("-1","编辑失败!");
		}
    }
    
    /**
     * 删除项目信息
    * @Title：delById
    * @param id
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月11日
     */
    @ApiOperation(value = "|项目删除")
    @BusinessLog(description = "项目管理-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("bm:pro:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
        try {
        	//删除
        	projectEOService.deleteByPrimaryKey(id);
        	return Result.success("0", "删除成功!");
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
        }
    }
    
    
    /**
     * 校验编码唯一性
     * @Title: checkNo
     * @param id
     * @param num
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年12月11日
     */
    @ApiOperation(value = "|项目校验编码唯一性")
    @BusinessLog(description = "项目管理-校验编码唯一性")
    @GetMapping("/checkNo")
    public ResponseMessage checkNo(String id, String num) {
    	try {
    		if(projectEOService.checkNo(id, num)) {
    			return Result.success("1", "该编码已存在!", num);
    		}else {
    			return Result.success("0", "校验通过!", num);
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","校验失败!");
		}
    }
    
}
