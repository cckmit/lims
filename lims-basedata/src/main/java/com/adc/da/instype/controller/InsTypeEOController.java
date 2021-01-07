package com.adc.da.instype.controller;

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
import com.adc.da.insproject.entity.InsProjectEO;
import com.adc.da.instype.entity.InsTypeEO;
import com.adc.da.instype.service.InsTypeEOService;
import com.adc.da.instype.vo.InsTypeVO;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 基础数据管理模块--检验项目类型
 * @author Administrator
 * @date 2019年7月15日
 */
@RestController
@RequestMapping("${restPath}/bm/insType")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class InsTypeEOController extends BaseController<InsTypeEO>{

	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(InsTypeEO.class);
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private InsTypeEOService insTypeEOService;
    
    /**
     * 所有记录
    * @Title：list
    * @return
    * @return: ResponseMessage<List<InsTypeVO>>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验项目类型查询")
    @BusinessLog(description = "检验项目类型-查询列表")
    @GetMapping("/list")
    @RequiresPermissions("bm:insType:list")
    public ResponseMessage<List<InsTypeVO>> findList(){
    	try {
    		List<InsTypeEO> rows = insTypeEOService.findALL();
            return Result.success("0", "查询成功", beanMapper.mapList(rows, InsTypeVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    /**
     * 获取详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<InsTypeVO>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验项目类型详情")
    @BusinessLog(description = "检验项目类型-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("bm:insType:get")
    public  ResponseMessage<InsTypeVO> getDetailById(@PathVariable(value = "id") String id){
    	try {
			return Result.success("0", "查询成功", beanMapper.map(insTypeEOService.selectByPrimaryKey(id), InsTypeVO.class));
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
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验项目类型保存")
    @BusinessLog(description = "检验项目类型-新增")
    @PostMapping(path = "/save", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:insType:save")
    public ResponseMessage<InsTypeVO> save(@RequestBody InsTypeVO insTypeVO){
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(insTypeVO.getInsTypeName())) {
    			return Result.error("-1", "检验项目类型名称不可为空");
    		}
    		//保存
    		insTypeEOService.save(beanMapper.map(insTypeVO, InsTypeEO.class));
    		return Result.success("0","新增成功!",insTypeVO);
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
	* @date： 2019年7月15日
	 */
    @ApiOperation(value = "|检验项目类型编辑")
    @BusinessLog(description = "检验项目类型-编辑")
    @PutMapping(path = "/edit", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("bm:insType:edit")
    public ResponseMessage<InsTypeVO> update(@RequestBody InsTypeVO insTypeVO){
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(insTypeVO.getInsTypeName())) {
    			return Result.error("-1", "检验项目类型名称不可为空");
    		}
    		//编辑
    		insTypeEOService.edit(beanMapper.map(insTypeVO, InsTypeEO.class));
    		return Result.success("0","编辑成功!",insTypeVO);
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
    @ApiOperation(value = "|检验项目类型删除")
    @BusinessLog(description = "检验项目类型-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("bm:insType:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
        try {
        	//判断当前项目是否有子节点
        	List<InsTypeEO> insTypeEOList = insTypeEOService.selectChildsById(id);
        	if(insTypeEOList.size() > 0) {
        		return Result.error("0", "当前数据拥有子节点,不允许被删除!");
        	}
        	//判断当前类型是否有数据
        	List<InsProjectEO> insProjectEOList = insTypeEOService.selectInsProById(id);
        	if(insProjectEOList.size() > 0) {
        		return Result.error("0", "当前类型拥有数据,不允许被删除!");
        	}
        	//没有子节点 被删除
        	insTypeEOService.deleteByPrimaryKey(id);
        	return Result.success("0", "删除成功!");
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
        }
    }
}
