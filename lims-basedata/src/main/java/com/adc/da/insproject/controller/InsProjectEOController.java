package com.adc.da.insproject.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

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
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.insproject.page.InsProjectEOPage;
import com.adc.da.insproject.service.InsProjectEOService;
import com.adc.da.insproject.vo.InsProjectSearchVO;
import com.adc.da.insproject.vo.InsProjectVO;
import com.adc.da.insprostd.dao.InsProStdEODao;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  基础数据模块--检验项目管理
 * @author Administrator
 * @date 2019年7月17日
 */
@RestController
@RequestMapping("${restPath}/bm/insPro")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class InsProjectEOController extends BaseController<InsProjectEO> {
	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(InsProjectEOController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private InsProjectEOService insProjectEOService;
    

	@Autowired
	private InsProStdEODao insProStdEODao;
    
    /**
     * 获取检验项目列表
    * @Title：list
    * @param proTypeId  检验项目类型id
    * @return
    * @return: ResponseMessage<List<InsProjectSearchVO>>
    * @author： ljy  
    * @date： 2019年7月18日
     */
	@ApiOperation(value = "|检验项目分页查询")
	@BusinessLog(description = "检验项目-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("bm:insPro:page")
    public ResponseMessage<PageInfo<InsProjectSearchVO>> page(
    		InsProjectEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
    		//查询
    		List<InsProjectEO> rows = insProjectEOService.page(page, searchPhrase);
    		//设置总条数
			Integer rowsCount = insProjectEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), InsProjectSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }

	/**
	 * 通过试验任务ID来查出试验任务相关联的检验项目-分页
	 * 其中stdNo字段用来接收试验任务ID
	 * @return
	 * @return: ResponseMessage<List<InsProjectSearchVO>>
	 */
	@ApiOperation(value = "|通过试验任务ID来查出检验项目分页")
	@BusinessLog(description = "通过试验任务ID来查出检验项目分页")
	@GetMapping("/pageForPcTask")
	public ResponseMessage<PageInfo<InsProjectSearchVO>> pageForPcTask(
			InsProjectEOPage page,
			@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
		try {
			//查询
			List<InsProjectEO> rows = insProjectEOService.pageForPcTask(page, searchPhrase);
			//设置总条数
			Integer rowsCount = insProjectEOService.queryByCountForPcTask(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), InsProjectSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
	}
    /**
     * 	获取检验项目详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<InsProjectVO>
    * @author： ljy  
    * @date： 2019年7月18日
     */
    @ApiOperation(value = "|检验项目详情")
    @BusinessLog(description = "检验项目-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("bm:insPro:get")
    public  ResponseMessage<InsProjectVO> getDetailById(@PathVariable(value = "id") String id){
    	try {
			return Result.success("0", "查询成功", beanMapper.map(insProjectEOService.selectByPrimaryKey(id), InsProjectVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    /**
     *	 检验项目保存
    * @Title：save
    * @param insProjectVO
    * @return
    * @return: ResponseMessage<InsProjectVO>
    * @author： ljy  
    * @date： 2019年7月18日
     */
    @ApiOperation(value = "|检验项目保存")
    @BusinessLog(description = "检验项目-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("bm:insPro:save")
    public ResponseMessage<InsProjectVO> save(@RequestBody InsProjectVO insProjectVO){
    	try {
    		//校验必填字段是否为空
    		if(StringUtils.isEmpty(insProjectVO.getProName())) {
    			return Result.error("-1", "检验项目名称不可为空");
    		}
    		if(StringUtils.isEmpty(insProjectVO.getProCode())) {
    			return Result.error("-1", "检验项目代码不可为空");
    		}
    		if(StringUtils.isEmpty(insProjectVO.getLabId())) {
    			return Result.error("-1", "检验项目负责试验室不可为空");
    		}
    		//保存
    		insProjectEOService.save(beanMapper.map(insProjectVO, InsProjectEO.class));
    		return Result.success("0","新增成功!",insProjectVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
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
    @ApiOperation(value = "|检验项目编辑")
    @BusinessLog(description = "检验项目-编辑")
    @PutMapping(path = "/edit", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:insPro:edit")
    public ResponseMessage<InsProjectVO> update(@RequestBody InsProjectVO insProjectVO){
    	try {
    		//校验必填字段是否为空
    		if(StringUtils.isEmpty(insProjectVO.getProName())) {
    			return Result.error("-1", "检验项目名称不可为空");
    		}
    		if(StringUtils.isEmpty(insProjectVO.getProCode())) {
    			return Result.error("-1", "检验项目代码不可为空");
    		}
    		if(StringUtils.isEmpty(insProjectVO.getLabId())) {
    			return Result.error("-1", "检验项目负责试验室不可为空");
    		}
    		//编辑
    		insProjectEOService.edit(beanMapper.map(insProjectVO, InsProjectEO.class));
    		return Result.success("0","编辑成功!",insProjectVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    /**
     * 	检验项目删除
    * @Title：delById
    * @param id
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月18日
     */
    @ApiOperation(value = "|检验项目删除")
    @BusinessLog(description = "检验项目-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("bm:insPro:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		//查询是否关联试验任务
    		if(insProjectEOService.checkTrialTaskById(id) > 0) {
    			return Result.success("0", "该检验项目已关联试验任务,不可被删除");
    		}
    		//检验项目与检验标准关系表删除
    		insProStdEODao.deleteByPrimaryKey(id);
    		//检验项目删除
    		insProjectEOService.deleteByPrimaryKey(id);
    		return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    /**
     * 	检验项目批量删除
    * @Title：delByIds
    * @param ids
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月18日
     */
    @ApiOperation(value = "|检验项目批量删除")
    @BusinessLog(description = "检验项目-批量删除")
    @DeleteMapping("/ids/{ids}")
    @RequiresPermissions("bm:insPro:batchDelete")
    public ResponseMessage deleteByIds(@PathVariable(value = "ids") String[] ids) {
    	try {
    		//设置判断变量
    		boolean isDel = false;
    		//循环校验是否关联试验任务
    		for(String id : ids) {
    			if(insProjectEOService.checkTrialTaskById(id) > 0) {
    				isDel = false;
    				break;
        		}
    			isDel = true;
    		}
    		//若存在关联的试验任务,则不可被删除
    		if(!isDel) {
    			return Result.success("0", "存在检验项目已关联试验任务,不可被删除");
    		}
    		//校验通过,删除数据
    		insProjectEOService.deleteByIds(ids);
    		return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(e.getMessage());
		}
    }
    
    /**
     * 	检验项目批量导出
    * @Title：insProBatchExport
    * @param response
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月22日
     */
    @ApiOperation(value = "|检验项目批量导出")
    @GetMapping("/batchExport")
    @BusinessLog(description = "检验项目-批量导出")
    @RequiresPermissions("bm:insPro:batchExport")
    public ResponseMessage exportInsPro(HttpServletResponse response, HttpServletRequest request) {
    	try {
			insProjectEOService.insProBatchExport(response, request);
			return Result.success("0", "导出成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(e.getMessage());
		}
    }

    /**
     * 检验项目批量导入
    * @Title：insProBatchImport
    * @param file 文件
    * @param type 类型 (强检项目, 新能源项目)
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月23日
     */
    @ApiOperation(value = "|检验项目批量导入")
    @BusinessLog(description = "检验项目-批量导入")
    @PostMapping(path = "/batchImport")
    @RequiresPermissions("bm:insPro:batchImport")
    public ResponseMessage importInsPro(
    		@RequestParam("file") MultipartFile file) {
    	try {
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension) &&
    			     !ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)) {
    				return Result.error("-1", "检验项目批量导入只支持上传Excel格式的文件!");
        		}
    		}
    		ResponseMessage result =  insProjectEOService.insProBatchImport(file, null);
			if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
				//校验成功
				result = Result.success("0", "上传成功!");
			}else if(ConstantUtils.RETURN_ERROR.equals(result.getRespCode())){
				result = Result.error("-1", "上传失败,第" + result.getMessage()
								+ "行数据有问题,请修改后重新上传");
			}else {
				result = Result.error("-1", "上传失败,第" + result.getMessage()
				+ "行检验标准不存在,请修改后重新上传");
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","上传失败!");
		}
    }
    
    
    
    
}
