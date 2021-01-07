package com.adc.da.line.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.line.entity.BmLineCaseEO;
import com.adc.da.line.entity.BmLineCaseInfoEO;
import com.adc.da.line.page.BmLineCaseEOPage;
import com.adc.da.line.service.BmLineCaseEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/line/bmLineCase")
@Api(tags = "BM-基础数据模块")
public class BmLineCaseEOController extends BaseController<BmLineCaseEO> {

    private static final Logger logger = LoggerFactory.getLogger(BmLineCaseEOController.class);

    @Autowired
    private BmLineCaseEOService bmLineCaseEOService;

    @ApiOperation(value = "|BmLineCaseEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("line:bmLineCase:page")
    public ResponseMessage<PageInfo<BmLineCaseEO>> page(BmLineCaseEOPage page) throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<BmLineCaseEO> rows = bmLineCaseEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|BmLineCaseEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("line:bmLineCase:get")
    public ResponseMessage<BmLineCaseEO> find(@PathVariable String id) throws Exception {
        return Result.success(bmLineCaseEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BmLineCaseEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("line:bmLineCase:save")
    public ResponseMessage<BmLineCaseEO> create(@RequestBody BmLineCaseEO bmLineCaseEO) throws Exception {
        ResponseMessage<BmLineCaseEO> x = verifyParam(bmLineCaseEO);
        if (x != null){
            return x;
        }
        bmLineCaseEOService.create(bmLineCaseEO);
        return Result.success("0", "保存成功", bmLineCaseEO);
    }

    @ApiOperation(value = "|BmLineCaseEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("line:bmLineCase:update")
    public ResponseMessage<BmLineCaseEO> update(@RequestBody BmLineCaseEO bmLineCaseEO) throws Exception {
        ResponseMessage<BmLineCaseEO> x = verifyParam(bmLineCaseEO);
        if (x != null) {
            return x;
        }
        bmLineCaseEOService.update(bmLineCaseEO);
        return Result.success("0", "编辑成功", bmLineCaseEO);
    }

    public ResponseMessage<BmLineCaseEO> verifyParam(@RequestBody BmLineCaseEO bmLineCaseEO) {
        ResponseMessage responseMessage = TransformUtil.verify(bmLineCaseEO, "createTime", "createBy", "lineCode",
                "lineName");
        if (responseMessage != null) {
            return responseMessage;
        }
        if (CollectionUtils.isNotEmpty(bmLineCaseEO.getBmLineCaseInfoEOList())) {
            for (BmLineCaseInfoEO bmLineCaseInfoEO : bmLineCaseEO.getBmLineCaseInfoEOList()) {
                ResponseMessage verify = TransformUtil.verify(bmLineCaseInfoEO, "dayNo", "driveOrder",
                        "lineType", "oneDayKm");
                if (verify != null) {
                    return verify;
                }
            }
        }
        return null;
    }

    @ApiOperation(value = "|BmLineCaseEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("line:bmLineCase:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        bmLineCaseEOService.deleteByPrimaryKey(id);
        logger.info("delete from BM_LINE_CASE where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value = "|获取委托单编号接口")
    @GetMapping("/lineNo")
    public ResponseMessage<String> getLineNo() {
        return Result.success(bmLineCaseEOService.getLineNo());
    }

}
