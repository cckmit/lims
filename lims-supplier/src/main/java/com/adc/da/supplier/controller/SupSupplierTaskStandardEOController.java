package com.adc.da.supplier.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.supplier.entity.SupSupplierTaskStandardEO;
import com.adc.da.supplier.page.SupSupplierTaskStandardEOPage;
import com.adc.da.supplier.service.SupSupplierTaskStandardEOService;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/supplier/supSupplierTaskStandard")
@Api(tags = {"Sup-外包供应商标准库"})
public class SupSupplierTaskStandardEOController extends BaseController<SupSupplierTaskStandardEO> {

    @Autowired
    private SupSupplierTaskStandardEOService supSupplierTaskStandardEOService;

    @ApiOperation(value = "|SupSupplierTaskStandardEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("supplier:supSupplierTaskStandard:page")
    public ResponseMessage<PageInfo<SupSupplierTaskStandardEO>> page(SupSupplierTaskStandardEOPage page)
            throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<SupSupplierTaskStandardEO> rows = supSupplierTaskStandardEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|SupSupplierTaskStandardEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("supplier:supSupplierTaskStandard:get")
    public ResponseMessage<SupSupplierTaskStandardEO> find(@PathVariable String id) throws Exception {
        return Result.success(supSupplierTaskStandardEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SupSupplierTaskStandardEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supplier:supSupplierTaskStandard:save")
    public ResponseMessage<SupSupplierTaskStandardEO> create(
            @RequestBody SupSupplierTaskStandardEO supSupplierTaskStandardEO) throws Exception {
        supSupplierTaskStandardEO.setId(UUID.randomUUID10());
        String userId = UserUtils.getUserId();
        supSupplierTaskStandardEO.setCreateBy(userId);
        supSupplierTaskStandardEO.setUpdateBy(userId);
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        supSupplierTaskStandardEO.setCreateTime(date);
        supSupplierTaskStandardEO.setUpdateTime(date);
        supSupplierTaskStandardEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        supSupplierTaskStandardEOService.insertSelective(supSupplierTaskStandardEO);
        return Result.success("0", "保存成功");
    }

    @ApiOperation(value = "|SupSupplierTaskStandardEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supplier:supSupplierTaskStandard:update")
    public ResponseMessage<SupSupplierTaskStandardEO> update(
            @RequestBody SupSupplierTaskStandardEO supSupplierTaskStandardEO) throws Exception {
        String userId = UserUtils.getUserId();
        supSupplierTaskStandardEO.setUpdateBy(userId);
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        supSupplierTaskStandardEO.setUpdateTime(date);
        supSupplierTaskStandardEOService.updateByPrimaryKeySelective(supSupplierTaskStandardEO);
        return Result.success("0", "编辑成功");
    }

    @ApiOperation(value = "|SupSupplierTaskStandardEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("supplier:supSupplierTaskStandard:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        SupSupplierTaskStandardEO supSupplierTaskStandardEO = supSupplierTaskStandardEOService.selectByPrimaryKey(id);
        // 删除标识
        supSupplierTaskStandardEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
        // 删除人
        supSupplierTaskStandardEO.setUpdateBy(UserUtils.getUserId());
        // 删除时间
        supSupplierTaskStandardEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        supSupplierTaskStandardEOService.updateByPrimaryKeySelective(supSupplierTaskStandardEO);
        return Result.success("0", "删除成功");
    }

}
