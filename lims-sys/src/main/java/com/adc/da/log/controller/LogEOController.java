package com.adc.da.log.controller;

import java.util.List;

import com.adc.da.base.page.Pager;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.BeanMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.entity.LogEO;
import com.adc.da.log.vo.LogVO;
import com.adc.da.log.page.LogEOPage;
import com.adc.da.log.service.LogEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 日志管理模块
 * 1.日志分页查询 @author 李坤澔
 * 2.日志详情
 * date 2018-08-16
 *
 * @author comments created by Lee Kwanho
 * @see LogEOService
 * @see LogEO
 * @see LogVO
 * @see LogEOPage
 * @see mybatis.mapper.log
 */
@RestController
@RequestMapping("/${restPath}/log/log")
@Api(tags = "Sys-日志管理")
public class LogEOController extends BaseController<LogEO> {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(LogEOController.class);

    /**
     * 统一标识日志异常
     */
    private static final String ERROR_INFO = "日志管理异常";

    /**
     * @see LogEOService
     */
    @Autowired
    private LogEOService logEOService;

    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * new paging query, using GetMapping.
     * total 5 variable: pageNo,pageSize,account,startTime,endTime
     * date 2018/03/26
     * 按照时间节点
     * 权限字段：sys:log:list
     *
     * @param page    页码
     * @param pageSize  分页大小
     * @param account   账户
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 查询结果
     * @author Lee Kwanho 李坤澔
     */
    @ApiOperation(value = "|LogEO|分页查询")
    @GetMapping("")
    @RequiresPermissions("sys:log:list")
    @BusinessLog(description = "日志管理-系统日志查询")
    public ResponseMessage<PageInfo<LogVO>> page(Integer page, Integer pageSize, String account,
            String startTime, String endTime) {

        LogEOPage page1 = new LogEOPage();
        //设置排序字段，对应是数据库中的字段非EO中的字段 倒叙排序
        page1.setOrderBy("start_time DESC");
        if (page != null) {
            page1.setPage(page);
        }
        if (pageSize != null) {
            page1.setPageSize(pageSize);
        }

        /* 日志相关账户查询 */
        if (StringUtils.isNotEmpty(account)) {
            page1.setAccount(account);
            page1.setAccountOperator("LIKE");
        }

        /* 设置查询起始时间 */
        if (StringUtils.isNotEmpty(startTime)) {
            page1.setStartTime1(startTime);
        }

        /* 设置查询终止时间 */
        if (StringUtils.isNotEmpty(endTime)) {
            page1.setStartTime2(endTime);
        }
        page1.setPager(new Pager());

        try {
            List<LogEO> rows = logEOService.queryByPage(page1);
            return Result.success(beanMapper.mapPage(getPageInfo(page1.getPager(), rows), LogVO.class));
        } catch (Exception e) {
            logger.error(ERROR_INFO, e);
            return Result.error(ERROR_INFO + e.getMessage());
        }

    }

    /**
     * 日志详情
     * 权限字段：sys:log:get
     *
     * @param id 日志id
     * @return 日志详情
     */
    @ApiOperation(value = "|LogEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:log:get")
    public ResponseMessage<LogVO> find(@PathVariable String id) {
        try {
            LogVO logVO = beanMapper.map(logEOService.selectByPrimaryKey(id), LogVO.class);
            return Result.success(logVO);
        } catch (Exception e) {
            logger.error(ERROR_INFO, e);
            return Result.error(ERROR_INFO + e.getMessage());
        }

    }

}

