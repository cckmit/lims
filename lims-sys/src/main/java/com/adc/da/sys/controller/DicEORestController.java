package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.DictionaryEO;

import com.adc.da.sys.page.DicTypeEOPage;
import com.adc.da.sys.page.DictionaryEOPage;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.vo.DictionaryVO;

import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典管理模块相关接口
 * 1.新增字典
 * 2.分页查询
 * 3.修改字典
 * 4.字典详情
 * 5.删除字典
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-15
 * @see DicEOService
 * @see DicTypeEOService
 * @see DictionaryEO
 * @see DictionaryVO
 * @see DictionaryEOPage
 * @see DicTypeEO
 * @see com.adc.da.sys.dao.DicEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/dictionary")
@Api(tags = { "Sys-数据字典管理" })
public class DicEORestController extends BaseController<DictionaryEO> {

    public static final String REGEX_EXCEPT_BLANK = "[^\\s]+";    //匹配除了空格以外的任意字符
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DictionaryEO.class);

    /**
     * 数据字典相关Service
     *
     * @see DicEOService
     */
    @Autowired
    private DicEOService dicEOService;

    /**
     * 数据类型相关Service
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
     * 新增数据字典
     * 先对字典编码判空，判断是否存在
     * 再对字典名称判断空，判断是否存在
     * 对异常编码，异常信息进行记录，最后统一返回
     * 权限字段：sys:dic:save
     *
     * @param dicVO 字典详情
     * @return 判断详情，按条件返回信息
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "|DictionaryEO|字典数据新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:dic:save")
    public ResponseMessage<DictionaryVO> create(@RequestBody DictionaryVO dicVO) {

        StringBuilder errorMessage = new StringBuilder();
        StringBuilder errorCode = new StringBuilder();

        if (StringUtils.isBlank(dicVO.getDictionaryCode())) {
            errorCode.append("dic001");
            errorMessage.append("字典码不能为空");

        } else if (dicEOService.getDictionaryByDicCode(dicVO.getDictionaryCode()) != null) {
            errorCode.append("dic002");
            errorMessage.append("字典码已存在");

        } else if (StringUtils.isBlank(dicVO.getDictionaryName())) {
            errorCode.append("dic003");
            errorMessage.append("字典名称不能为空");

        } else if (dicEOService.getDictionaryByDicCode(dicVO.getDictionaryName()) != null) {
            errorCode.append("dic004");
            errorMessage.append("字典名称已存在");

        } else {
            DictionaryEO dicEO = dicEOService.save(beanMapper.map(dicVO, DictionaryEO.class));
            return Result.success(beanMapper.map(dicEO, DictionaryVO.class));

        }
        return Result.error(errorCode.toString(), errorMessage.toString());
    }

    /**
     * 分页查询
     * <p>
     * 权限字段：sys:dic:list
     *
     * @param pageNo   页码
     * @param pageSize 分页大小
     * @param dicCode  字典编码
     * @param dicName  字典名称
     * @return 查询结果，若出现异常记录日志，返回error信息
     */
    @ApiOperation(value = "|DictionaryEO|字典数据分页查询")
    @GetMapping("")
    @RequiresPermissions("sys:dic:list")
    public ResponseMessage<PageInfo<DictionaryVO>> page(Integer pageNo, Integer pageSize, String dicCode,
                                                        String dicName,String searchPhrase) {
        DictionaryEOPage page = new DictionaryEOPage();
        try {
            List<DictionaryEO> rows = null;
            if (pageNo != null) {
                page.setPage(pageNo);
            }
            if (pageSize != null) {
                page.setPageSize(pageSize);
            }
            if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
                //通用查询的参数不为空即为通用查询
                DictionaryEO dictionaryEO = new DictionaryEO();
                searchPhrase = searchPhrase.trim();
                Pattern datePattern = Pattern.compile(REGEX_EXCEPT_BLANK);
                Matcher dateMatcher = datePattern.matcher(searchPhrase);
                List<String> list = new ArrayList<String>();
                while (dateMatcher.find()) {
                    String search = dateMatcher.group();
                    list.add(search);
                }
                page.setSearchPhrase(list);
                page.setPager(new Pager());
                rows = dicEOService.getDictionaryEOBySearchPhrase(page);
            }else{
                //通用查询 测试
                /*List<String> list = new ArrayList<String>();
                list.add("1");
                list.add("2");
                page.setSearchPhrase(list);
                page.setPager(new Pager());
                rows = dicEOService.getDictionaryEOBySearchPhrase(page);*/

                //普通查询：按照某个字段进行查询
                if (StringUtils.isNotEmpty(dicCode)) {
                    page.setDictionaryCode(dicCode);
                    page.setDictionaryCodeOperator("LIKE");
                }
                if (StringUtils.isNotEmpty(dicName)) {
                    page.setDictionaryName(dicName);
                    page.setDictionaryNameOperator("LIKE");
                }
                page.setPager(new Pager());
                rows = dicEOService.queryByPage(page);
            }
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), DictionaryVO.class));
        } catch (Exception e) {
            logger.error("数据字典分页查询异常", e);
            return Result.error("数据字典分页查询异常");
        }
    }

    /**
     * 修改数据字典
     * 权限字段：sys:dic:update
     *
     * @param dictionaryVO 数据字典信息
     * @return 修改结果，若异常记录日志，返回异常信息
     */
    @ApiOperation(value = "|DictionaryEO|字典数据修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:dic:update")
    public ResponseMessage<DictionaryVO> update(@RequestBody DictionaryVO dictionaryVO) {
        try {
            dicEOService.updateByPrimaryKeySelective(beanMapper.map(dictionaryVO, DictionaryEO.class));
            return Result.success(dictionaryVO);
        } catch (Exception e) {
            logger.error("数据字典修改异常", e);
            return Result.error("数据字典修改异常");
        }

    }

    /**
     * 数据字典详情
     * 权限字段：sys:dic:get
     *
     * @param id 数据字典id
     * @return 数据字典信息
     */
    @ApiOperation(value = "|DictionaryEO|字典数据详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:dic:get")
    public ResponseMessage<DictionaryVO> getById(@NotNull @PathVariable("id") String id) {
        DictionaryVO dictionaryVO = beanMapper.map(dicEOService.getDictionaryById(id), DictionaryVO.class);
        return Result.success(dictionaryVO);
    }

    /**
     * 删除数据字典
     * 权限字段：sys:dic:delete
     *
     * @param id 数据字典id
     * @return 成功信息
     */
    @ApiOperation(value = "|DictionaryEO|字典数据删除")
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:dic:delete")
    public ResponseMessage delete(@NotNull @PathVariable("id") String id) {
        DicTypeEOPage page = new DicTypeEOPage();
        if (StringUtils.isNotEmpty(id)) {
            page.setDicId(id);
        }
        page.setPager(new Pager());

        try {
            List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
            if (rows != null && !rows.isEmpty()) {
                return Result.error("dic005", "该字典下存在类型明细，确定删除吗？");
            }
        } catch (Exception e) {
            logger.error("查询该字典记录失败", e);
            return Result.error("查询该字典记录失败");
        }

        dicEOService.delete(id);
        return Result.success("0","操作成功！");
    }
}
