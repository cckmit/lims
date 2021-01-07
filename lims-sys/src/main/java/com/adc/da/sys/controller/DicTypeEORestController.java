package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
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

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.DicTypeEO;

import com.adc.da.sys.page.DicTypeEOPage;

import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.vo.DicTypeVO;

import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典类型明细管理
 * 1.新增字典明细
 * 2.明细列表
 * 3.修改
 * 4.详情
 * 5.删除
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-15
 * @see DicTypeEOService
 * @see DicTypeEO
 * @see DicTypeVO
 * @see DicTypeEOPage
 * @see com.adc.da.sys.dao.DicTypeEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/dictype")
@Api(tags = { "Sys-数据字典管理" })
public class DicTypeEORestController extends BaseController<DicTypeEO> {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DicTypeEO.class);
    
    /**
     * 字典类型相关Service
     *
     * @see DicTypeEOService
     */
    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;
    
    /**
     * 新增字典类型
     * 权限字段： sys:dicType:save
     *
     * @param dicTypeVO 字典类型信息
     * @return 新增结果，包含id字段
     */
    @ApiOperation(value = "|DicTypeEO|字典类型新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:dicType:save")
    public ResponseMessage<DicTypeVO> create(@RequestBody DicTypeVO dicTypeVO) {
        
        DicTypeEO dicTypeEO = dicTypeEOService.save(beanMapper.map(dicTypeVO, DicTypeEO.class));
        return Result.success(beanMapper.map(dicTypeEO, DicTypeVO.class));
    }
    
    /**
     * 获取字典类型分页信息
     * 权限字段：sys:dicType:list
     *
     * @param pageNo      页码
     * @param pageSize    分页大小
     * @param dicId       字典类型id
     * @param dicTypeName 字典类型名称
     * @return 分页信息
     */
    @ApiOperation(value = "|DicTypeEO|字典类型分页列表")
    @GetMapping("/page")
    @RequiresPermissions("sys:dicType:list")
    public ResponseMessage<PageInfo<DicTypeVO>> pageListByDicId(Integer pageNo, Integer pageSize, String dicId,
        String dicTypeName) {
        DicTypeEOPage page = new DicTypeEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        /* 按照id查询*/
        if (StringUtils.isNotEmpty(dicId)) {
            page.setDicId(dicId);
        }
        /* 按照名称查询*/
        if (StringUtils.isNotEmpty(dicTypeName)) {
            page.setDicTypeName(dicTypeName);
            page.setDicTypeNameOperator("LIKE");
        }
        
        page.setPager(new Pager());
        
        try {
            List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), DicTypeVO.class));
        } catch (Exception e) {
            logger.error("DicType:page", e);
            return Result.error("DicType:page：");
        }
        
    }
    
    /**
     * 修改字典类型
     * 权限字段：sys:dicType:update
     *
     * @param dicTypeVO 字典类型信息
     * @return 修改结果，若出现异常记录日志，返回相应信息
     */
    @ApiOperation(value = "|DicTypeEO|字典类型修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:dicType:update")
    public ResponseMessage<DicTypeVO> update(@RequestBody DicTypeVO dicTypeVO) {
        try {
            dicTypeEOService.updateByPrimaryKeySelective(beanMapper.map(dicTypeVO, DicTypeEO.class));
        } catch (Exception e) {
            logger.error("修改字典类型明细失败", e);
            return Result.error("修改字典类型明细失败：");
        }
        
        return Result.success("0","操作成功！",dicTypeVO);
    }
    
    /**
     * 查询字典类型详情
     * 权限字段：sys:dicType:get
     *
     * @param id 字典类型id
     * @return 字典类型详情
     */
    @ApiOperation(value = "|DicTypeEO|字典类型详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:dicType:get")
    public ResponseMessage<DicTypeVO> getById(@NotNull @PathVariable("id") String id) {
        DicTypeVO dicTypeVO = beanMapper.map(dicTypeEOService.getDicTypeById(id), DicTypeVO.class);
        return Result.success(dicTypeVO);
    }

    /**
     * 查询字典类型详情
     * 权限字段：sys:dicType:get
     *
     * @param id 字典类型id
     * @return 字典类型详情
     */
    @ApiOperation(value = "|DicTypeEO|字典类型详情")
    @GetMapping("getDicTypeByCode/{dicTypeCode}")
    @RequiresPermissions("sys:dicType:get")
    public ResponseMessage<DicTypeVO> getDicTypeEOByCode(@NotNull @PathVariable("dicTypeCode") String dicTypeCode) {
        DicTypeVO dicTypeVO = beanMapper.map(dicTypeEOService.getDicTypeEOByCode(dicTypeCode), DicTypeVO.class);
        return Result.success(dicTypeVO);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据字典Code查询字典类型列表
     * @Date 13:07 2019/8/2
     * @Param
     * @return
     **/
    @ApiOperation(value = "|DicTypeEO|根据字典Code查询字典类型列表")
    @GetMapping("getDicTypeByDictionaryCode/{dictionaryCode}")
    @EnableAccess
    public ResponseMessage<List<DicTypeVO>> getDicTypeByDictionaryCode(@NotNull @PathVariable("dictionaryCode") String dictionaryCode) {
        List<DicTypeEO> resultList = dicTypeEOService.getDicTypeByDictionaryCode(dictionaryCode);
        List<DicTypeVO> dicTypeVOList = beanMapper.mapList(resultList, DicTypeVO.class);
        return Result.success(dicTypeVOList);
    }
    /**
     * 删除字典类型
     * 权限字段：sys:dicType:delete
     *
     * @param ids 类型id组
     * @return 删除成功
     */
    @ApiOperation(value = "|DicTypeEO|字典类型删除")
    @DeleteMapping("/delete/{ids}")
    @RequiresPermissions("sys:dicType:delete")
    public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids) {
        dicTypeEOService.delete(Arrays.asList(ids));
        return Result.success("0","操作成功！");
    }
    
    
    @ApiOperation(value = "|DicTypeEO|字典类型唯一值查询")
    @GetMapping("/getDicTypeByDicCodeAndDicTypeId")
    public ResponseMessage<DicTypeEO> getDicTypeByDicCodeAndDicTypeId(String dictionaryCode,
    		String dicTypeCode){
    	try {
    		DicTypeEO eo = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(dictionaryCode, dicTypeCode);
        	return Result.success("0", "查询成功!", eo);
		} catch (Exception e) {
			return Result.error("-1", "查询失败!");
		}
    	
    }
    
}
