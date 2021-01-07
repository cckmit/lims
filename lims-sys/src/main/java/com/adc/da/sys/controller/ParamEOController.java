package com.adc.da.sys.controller;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.entity.ParamEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.page.ParamEOPage;
import com.adc.da.sys.service.ParamEOService;
import com.adc.da.sys.vo.ParamVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @Author syxy_zhangyinghui
 * @Description 系统参数管理模块
 * @Date 15:27 2019/8/2
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/${restPath}/sys/param")
@Api(tags = "Sys-参数管理")
public class ParamEOController extends BaseController<ParamEO> {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(ParamEOController.class);
    /**
     * //匹配除了空格以外的任意字符
     */
    public static final String REGEX_EXCEPT_BLANK = "[^\\s]+";

    @Autowired
    private ParamEOService paramEOService;
    @Autowired
    private BeanMapper beanMapper;

    /*
     * @Author syxy_zhangyinghui
     * @Description 分页查询
     * @Date 15:54 2019/8/5
     * @Param
     * @return
     **/
    @BusinessLog(description = "参数管理-分页查询参数信息")
    @ApiOperation(value = "|ParamEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("sys:param:list")
    public ResponseMessage<PageInfo<ParamVO>> page(Integer pageNo, Integer pageSize,String createDateStart,
                                                   String createDateEnd,String paramCode,String paramName,
                                                   String paranValue,String searchPhrase){
        ParamEOPage paramEOPage = new ParamEOPage();
        //根据创建时间进行排序
        paramEOPage.setOrderBy("CREATEDATE");
        if (pageNo != null) {
            paramEOPage.setPage(pageNo);
        }
        if (pageSize != null) {
            paramEOPage.setPageSize(pageSize);
        }
        if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
            //通用查询
            searchPhrase = searchPhrase.trim();
            String patten = REGEX_EXCEPT_BLANK;
            Pattern datapattern = Pattern.compile(patten);
            Matcher matcher = datapattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (matcher.find()){
                String search = matcher.group();
                list.add(search);
            }
            paramEOPage.setSearchPhrase(list);
            paramEOPage.setSearchPhraseOperation("LIKE");
        }else{
            if(StringUtils.isNotEmpty(paramCode)){
                paramEOPage.setParamCode(paramCode);
                paramEOPage.setParamCodeOperation("LIKE");
            }
            if (StringUtils.isNotEmpty(paramName)){
                paramEOPage.setParamName(paramName);
                paramEOPage.setParamNameOperation("LIKE");
            }
            if(StringUtils.isNotEmpty(paranValue)){
                paramEOPage.setParamValue(paranValue);
                paramEOPage.setParamValueOperation("LIKE");
            }
            if(StringUtils.isNotEmpty(createDateStart) && StringUtils.isNotEmpty(createDateEnd)){
                paramEOPage.setCreateDateStart(createDateStart);
                paramEOPage.setCreateDateStartOperation("<=");
                paramEOPage.setCreateDateEnd(createDateEnd);
                paramEOPage.setCreateDateEndOperation(">=");
            }
        }
        paramEOPage.setPager(new Pager());
        try {
            List<ParamEO> rows = paramEOService.queryByPage(paramEOPage);
            return Result.success(beanMapper.mapPage(getPageInfo(paramEOPage.getPager(), rows),ParamVO.class));
        }catch (Exception e){
            logger.error("查询参数数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }

    }

    @BusinessLog(description = "参数管理-新增参数信息")
    @ApiOperation(value = "|paramEo|新增参数")
    @PutMapping("/saveParam")
   @RequiresPermissions("sys:param:save")
    public ResponseMessage<ParamVO> saveParam(@RequestBody ParamVO paramVO,HttpServletRequest request){
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        if(StringUtils.isEmpty(paramVO.getParamCode())){
            errorCode.append("p0001");
            errorMessage.append("参数码不能为空");
        }else if (StringUtils.isEmpty(paramVO.getParamName())){
            errorCode.append("p0002");
            errorMessage.append("参数名称不能为空");
        }else if(StringUtils.isEmpty(paramVO.getParamValue())){
            errorCode.append("p0003");
            errorMessage.append("参数值不能为空");
        }else{
            ParamEO paramEo = beanMapper.map(paramVO, ParamEO.class);
            ParamEO resultEo = paramEOService.saveParam(paramEo,request);
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(resultEo)){
                return Result.success("1","新增参数信息成功",beanMapper.map(resultEo,ParamVO.class));
            }else{
                return Result.error("0","新增参数信息失败",beanMapper.map(paramEo,ParamVO.class));
            }
        }
        return Result.error(errorCode.toString(),errorMessage.toString(),paramVO);
    }

    @BusinessLog(description = "参数管理-修改参数信息")
    @ApiOperation(value = "|ParamEo|根据id修改参数信息")
    @PutMapping("/updateParam")
    @RequiresPermissions("sys:param:update")
    public ResponseMessage<ParamVO> updateParam(@RequestBody ParamVO paramVO){
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        if(StringUtils.isEmpty(paramVO.getParamCode())){
            errorCode.append("p0001");
            errorMessage.append("参数码不能为空");
        }else if (StringUtils.isEmpty(paramVO.getParamName())){
            errorCode.append("p0002");
            errorMessage.append("参数名称不能为空");
        }else if(StringUtils.isEmpty(paramVO.getParamValue())){
            errorCode.append("p0003");
            errorMessage.append("参数值不能为空");
        }else{
            ParamEO paramEo = beanMapper.map(paramVO, ParamEO.class);
            ParamEO resultEo = paramEOService.updateParam(paramEo);
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(resultEo)){
                return Result.success("1","修改参数信息成功",beanMapper.map(resultEo,ParamVO.class));
            }else{
                return Result.error("0","修改参数信息失败",beanMapper.map(paramEo,ParamVO.class));
            }
        }
        return Result.error("0","修改参数信息失败",paramVO);
    }

    @BusinessLog(description = "参数管理-删除参数信息")
    @ApiOperation(value = "|ParamEo|删除参数信息")
    @DeleteMapping("/deleteParam/{id}")
    @RequiresPermissions("sys:param:delete")
    public ResponseMessage deleteParam(@NotNull @PathVariable("id") String id, HttpServletRequest request){
        paramEOService.deleteParam(id);
        return Result.success();
    }

    @BusinessLog(description = "参数管理-查询详细的参数信息")
    @ApiOperation(value = "|ParamEo|查看参数详情")
    @GetMapping("/getParamById")
    @RequiresPermissions("sys:param:detail")
    public ResponseMessage<ParamVO> getParamById(@RequestParam("id") String id){
        ParamEO res = paramEOService.getParamById(id);
        return Result.success(beanMapper.map(res,ParamVO.class));
    }

    @ApiOperation(value = "|ParamEo|验证参数code是否重复")
    @GetMapping("/checkCode")
    public ResponseMessage checkCode(String id, String code){
        try{
            Integer integer = paramEOService.checkNum(code, id);
            return Result.success("0", "验证通过!",integer);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","验证失败!");
        }
    }
    @BusinessLog(description = "参数管理-新增或修改预登陆账号信息")
    @ApiOperation(value = "|paramEo|新增或修改预登陆账号信息")
    @PutMapping("/updatePreAccount")
    @RequiresPermissions("sys:param:updatePre")
    public ResponseMessage updatePreAccount(@RequestBody ParamVO paramVO){
        ResponseMessage message = TransformUtil.verify(paramVO, "paramCode", "paramValue");
        if(message!=null){
            return message;
        }else{
            String name= "预登陆账号";
            ParamEO paramEO = paramEOService.getParamByName(name);;
            paramEO.setParamCode(paramVO.getParamCode());
            paramEO.setParamValue(Encodes.encodeBase64(paramVO.getParamValue().getBytes()));
            ParamEO param = paramEOService.updatePreById(paramEO);
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(paramEO)){
                return Result.success("0","新增参数信息成功",beanMapper.map(param,ParamVO.class));
            }else{
                return Result.error("-1","新增参数信息失败");
            }
        }
    }
    @BusinessLog(description = "参数管理-获取预登陆账号信息")
    @ApiOperation(value = "|paramEo|获取预登陆账号信息")
    @GetMapping("/getPreAccount")
    @RequiresPermissions("sys:param:detailPre")
    public ResponseMessage getPreAccount(){
        String name= "预登陆账号";
        ParamEO paramEO = paramEOService.getParamByName(name);
        return Result.success("0","获取预登陆账号信息成功",beanMapper.map(paramEO,ParamVO.class));
    }
}
