package com.adc.da.supplier.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adc.da.supplier.entity.SupProjectEO;
import com.adc.da.supplier.service.SupProjectEOService;
import com.adc.da.supplier.vo.SupProjectVO;
import com.adc.da.supplier.vo.SupTestProjectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.page.BasePage;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.insproject.page.InsProjectEOPage;
import com.adc.da.insproject.vo.InsProjectSearchVO;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.supplier.page.ProjectEOPage;
import com.adc.da.supplier.page.SupProjectEOPage;
import com.adc.da.supplier.page.SupTestProjectVOPage;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/supplier/project")
@Api(tags = "Sup-供应商关联检验项目")
public class SupProjectEOController extends BaseController<SupProjectEO>{

    private static final Logger logger = LoggerFactory.getLogger(SupProjectEOController.class);

    @Autowired
    private SupProjectEOService projectEOService;

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
	@ApiOperation(value = "|ProjectEO|查询")
    @GetMapping("")
    @RequiresPermissions("supplier:project:list")
    public ResponseMessage<List<SupProjectEO>> list(ProjectEOPage page) throws Exception {
        return Result.success(projectEOService.queryByList(page));
	}

    @ApiOperation(value = "|ProjectEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("supplier:project:get")
    public ResponseMessage<SupProjectEO> find(@PathVariable String id) throws Exception {
        return Result.success(projectEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|ProjectEO|查看供应商能力")
    @GetMapping("/getList")
//    @RequiresPermissions("supplier:project:getList")
    public ResponseMessage<List<SupProjectEO>> findListBySupId(String supId) throws Exception {
        return Result.success(projectEOService.findListBySupId(supId));
    }

    @ApiOperation(value = "|ProjectEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supplier:project:save")
    public ResponseMessage<SupProjectEO> create(@RequestBody SupProjectEO projectEO) throws Exception {
        projectEOService.insertSelective(projectEO);
        return Result.success(projectEO);
    }

    @ApiOperation(value = "|ProjectEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supplier:project:update")
    public ResponseMessage<SupProjectEO> update(@RequestBody SupProjectEO projectEO) throws Exception {
        projectEOService.updateByPrimaryKeySelective(projectEO);
        return Result.success(projectEO);
    }

    @ApiOperation(value = "|ProjectEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("supplier:project:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        projectEOService.deleteByPrimaryKey(id);
        logger.info("delete from SUP_PROJECT where id = {}", id);
        return Result.success();
    }

    
    
    /**
     * 供应商能力检验项目-导入
     * @Title: importSupProject
     * @param file
     * @return
     * @return ResponseMessage<List<SupProjectVO>>
     * @author: ljy
     * @date: 2020年3月9日
     */
    @ApiOperation(value = "|供应商能力检验项目导入")
    @BusinessLog(description = "供应商能力检验项目-导入")
    @PostMapping(path = "/batchImport")
    @RequiresPermissions("supplier:project:importSupProject")
    public ResponseMessage<PageInfo<SupProjectVO>> importSupProject(@RequestParam("file") MultipartFile file,
    		HttpServletRequest request) {
    	try {
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension) &&
    			     !ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)) {
    				return Result.error("-1", "供应商能力检验项目导入只支持上传Excel格式的文件!");
        		}
    		}
    		List<SupProjectEO> result =  projectEOService.importSupProject(file);
    		request.getSession().setAttribute("supProject", result);
    		long totalRow = result.size();
        	// 每页记录数
        	int pageSize = 10;
        	int totalPage = (int) ((totalRow + pageSize - 1) / pageSize);
        	List<SupProjectEO> temp = new ArrayList<SupProjectEO>();
        	if(totalRow > 10) {
        		temp = result.subList(0, 10);
        	}else {
        		temp = result;
        	}
        	SupProjectEOPage bp = new SupProjectEOPage();
        	bp.setPageSize(10);
        	bp.setPage(1);
        	bp.getPager().setRowCount(result.size());
			return Result.success("0", "导入成功!", beanMapper.mapPage(getPageInfo(bp.getPager(), temp), SupProjectVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","导入失败!");
		}
    }
    
    
    /**
     * session分页查询
     * @Title: importSupProject
     * @param request
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<SupProjectVO>>
     * @author: ljy
     * @date: 2020年3月19日
     */
    @ApiOperation(value = "")
    @GetMapping("/sessionPage")
    @RequiresPermissions("supplier:project:page")
    public ResponseMessage<PageInfo<SupProjectVO>> importSupProject(HttpServletRequest request, SupProjectEOPage page) {
    	try {
    		List<SupProjectEO> result = (List<SupProjectEO>) request.getSession().getAttribute("supProject");
    		int totalRow = result.size();
        	// 每页记录数
        	//int pageSize = 10;
        	//int totalPage = (int) ((totalRow + pageSize - 1) / pageSize);
        	List<SupProjectEO> temp = new ArrayList<SupProjectEO>();
        	SupProjectEOPage bp = new SupProjectEOPage();
        	bp.setPageSize(page.getPageSize());
        	bp.setPage(page.getPage());
        	bp.getPager().setRowCount(totalRow);
        	int indexNum = (page.getPage() - 1) * page.getPageSize();
        	int lastNum = indexNum + page.getPageSize();
        	temp = result.subList(indexNum, lastNum);
			return Result.success("0", "查询成功!", beanMapper.mapPage(getPageInfo(bp.getPager(), temp), SupProjectVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    
    /**
     * 获取供应商检验项目
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<SupTestProjectVO>>
     * @author: ljy
     * @date: 2020年3月10日
     */
    @ApiOperation(value = "")
    @GetMapping("/page")
    @RequiresPermissions("supplier:project:page")
    public ResponseMessage<PageInfo<SupTestProjectVO>> page(SupTestProjectVOPage page){
    	try {
    		//查询
    		List<SupProjectEO> rows = projectEOService.selectSupProjectByPage(page);
    		//设置总条数
			Integer rowsCount = projectEOService.selectSupProjectByCount(page);
			page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", 
            		beanMapper.mapPage(getPageInfo(page.getPager(), rows), SupTestProjectVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
}
