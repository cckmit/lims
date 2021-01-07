package com.adc.da.stdtype.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.stdtype.entity.StdTypeEO;
import com.adc.da.stdtype.service.StdTypeEOService;
import com.adc.da.stdtype.vo.StdTypeVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 基础数据管理模块--检验标准类型
 * @author ljy
 * @date 2019年7月12日
 */
@RestController
@RequestMapping("${restPath}/bm/stdType")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class StdTypeEOController extends BaseController<StdTypeEO>{

	/**
     * 用于记录日志.
     */
	private static final Logger logger = LoggerFactory.getLogger(StdTypeEOController.class);
    
	/**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;
    
    @Autowired
    private StdTypeEOService stdTypeEOService;
	
    /**
     * 查询所有记录
    * @Title：list
    * @return
    * @return: ResponseMessage<List<StdTypeVO>>
    * @author： ljy  
    * @date： 2019年7月12日
     */
    @ApiOperation(value = "|检验标准类型查询")
    @BusinessLog(description = "检验标准类型-查询类型")
	@GetMapping("/list")
	@RequiresPermissions("bm:stdType:list")
	public ResponseMessage<List<StdTypeVO>> findList(){
		try {
			List<StdTypeVO> list = beanMapper.mapList(stdTypeEOService.findAll(), StdTypeVO.class);
			return Result.success("0", "查询成功", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
	}
    
    /**
     * 检验标准类型详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<StdTypeVO>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验标准类型详情")
    @BusinessLog(description = "检验标准类型-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("bm:stdType:get")
    public  ResponseMessage<StdTypeVO> getDetailById(@PathVariable(value = "id") String id){
    	try {
			return Result.success("0", "查询成功", beanMapper.map(stdTypeEOService.selectByPrimaryKey(id), StdTypeVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
	
	/**
	 * 保存
	* @Title：save
	* @param stdTypeVO
	* @return
	* @return: ResponseMessage<StdTypeVO>
	* @author： ljy  
	* @date： 2019年7月12日
	 */
    @ApiOperation(value = "|检验标准类型保存")
    @BusinessLog(description = "检验标准类型-保存")
    @PostMapping(path = "/save", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:stdType:save")
    public ResponseMessage<StdTypeVO> save(@RequestBody StdTypeVO stdTypeVO){
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(stdTypeVO.getStdTypeName())) {
    			return Result.error("-1", "检验标准类型名称不可为空");
    		}
    		//保存
    		stdTypeEOService.save(beanMapper.map(stdTypeVO, StdTypeEO.class));
    		return Result.success("0","新增成功!",stdTypeVO);
    	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
    /**
     * 编辑
    * @Title：edit
    * @param stdTypeVO
    * @return
    * @return: ResponseMessage<StdTypeVO>
    * @author： ljy  
    * @date： 2019年7月12日
     */
    @ApiOperation(value = "|检验标准类型编辑")
    @BusinessLog(description = "检验标准类型-编辑")
    @PutMapping(path = "/edit", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:stdType:edit")
    public ResponseMessage<StdTypeVO> update(@RequestBody StdTypeVO stdTypeVO){
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(stdTypeVO.getStdTypeName())) {
    			return Result.error("-1", "检验标准类型名称不可为空");
    		}
    		//编辑
    		stdTypeEOService.edit(beanMapper.map(stdTypeVO, StdTypeEO.class));
    		return Result.success("0","编辑成功!",stdTypeVO);
    	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    /**
     * 删除
    * @Title：delById
    * @param id
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验标准类型删除")
    @BusinessLog(description = "检验标准类型-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("bm:stdType:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
        try {
        	//判断当前类型是否有子节点
        	List<StdTypeEO> stdTypeEOList = stdTypeEOService.selectChildsById(id);
        	if(stdTypeEOList.size() > 0) {
        		return Result.error("0", "当前数据拥有子节点,不允许被删除!");
        	}
        	//判断当前类型是否有数据
        	List<StandardEO> standardEOList = stdTypeEOService.selectStdById(id);
        	if(standardEOList.size() > 0) {
        		return Result.error("0", "当前类型拥有数据,不允许被删除!");
        	}
        	//没有子节点 被删除
        	stdTypeEOService.deleteByPrimaryKey(id);
        	return Result.success("0", "删除成功!");
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
        }
    }
    
    
}
