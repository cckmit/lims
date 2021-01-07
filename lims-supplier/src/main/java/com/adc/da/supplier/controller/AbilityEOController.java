package com.adc.da.supplier.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.supplier.service.SupProjectEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.supplier.entity.AbilityEO;
import com.adc.da.supplier.entity.SupProjectEO;
import com.adc.da.supplier.page.AbilityEOPage;
import com.adc.da.supplier.service.AbilityEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/${restPath}/supplier/ability")
@Api(tags = "Sup-供应商能力管理")
public class AbilityEOController extends BaseController<AbilityEO>{

    private static final Logger logger = LoggerFactory.getLogger(AbilityEOController.class);

    @Autowired
    private AbilityEOService abilityEOService;


    @BusinessLog(description = "供应商能力库分页查询")
    @ApiOperation(value = "|AbilityEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("supplier:ability:page")
    public ResponseMessage<PageInfo<AbilityEO>> page(AbilityEOPage page, String searchFeild) throws Exception {
	    try{
            List<AbilityEO> rows = abilityEOService.queryByPage(page, searchFeild);
            page.getPager().setRowCount(abilityEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch(Exception e){
	        logger.error("-1","查询失败！");
            return  Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "供应商能力库列表查询")
	@ApiOperation(value = "|AbilityEO|查询")
    @GetMapping("")
    @RequiresPermissions("supplier:ability:list")
    public ResponseMessage<List<AbilityEO>> list(AbilityEOPage page) throws Exception {
        return Result.success(abilityEOService.queryByList(page));
	}

    @BusinessLog(description = "供应商能力库详情")
    @ApiOperation(value = "|AbilityEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("supplier:ability:get")
    public ResponseMessage<AbilityEO> find(@PathVariable String id) throws Exception {
        return Result.success(abilityEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "供应商能力库新增")
    @ApiOperation(value = "|AbilityEO|新增")
    @PostMapping("")
//    @RequiresPermissions("supplier:ability:save")
    public ResponseMessage<AbilityEO> create(String abilityEOStr,  MultipartFile file,
    		HttpServletRequest request) throws Exception {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        AbilityEO abilityEO = mapper.readValue(abilityEOStr, AbilityEO.class);
	    if(StringUtils.isEmpty(abilityEO)){
	        return Result.error("-1","保存失败！");
        }
	    abilityEO = saveSupProject(abilityEO, request);
        abilityEOService.saveAbilityEO(abilityEO, file);
        request.getSession().removeAttribute("supProject");
        return Result.success("0","添加成功！",abilityEO);
    }

    /**
     * 操作session 保存子表格数据
     * @Title: saveSupProject
     * @param abilityEO
     * @param request
     * @return
     * @return AbilityEO
     * @author: ljy
     * @date: 2020年3月19日
     */
    public AbilityEO saveSupProject(AbilityEO abilityEO, HttpServletRequest request) {
    	List<SupProjectEO> list = (List<SupProjectEO>)request.getSession().getAttribute("supProject");
	    //直接新增,未导入
    	if(CollectionUtils.isEmpty(list)) {
    		abilityEO.setSupProjectEOList(abilityEO.getSupProjectEOList());
    	}
    	//导入
    	else if(CollectionUtils.isNotEmpty(list)
    			&& CollectionUtils.isEmpty(abilityEO.getSupProjectEOList())) {
	    	//直接设置session
	    	abilityEO.setSupProjectEOList(list);
	    }else {
	    	List<SupProjectEO> tempList = list;
	    	List<SupProjectEO> addTempList = new ArrayList<SupProjectEO>();
	    	for (SupProjectEO eo : abilityEO.getSupProjectEOList()) {
				for (SupProjectEO supProjectEO : tempList) {
					if(StringUtils.isEmpty(eo)){
						addTempList.add(eo);
						break;
					}
					if(eo.getId().equals(supProjectEO.getId())) {
						supProjectEO = eo;
						break;
					}
				}
			}
	    	tempList.addAll(addTempList);
	    	request.getSession().setAttribute("supProject", tempList);
	    	abilityEO.setSupProjectEOList(tempList);
	    }
	    return abilityEO;
    }
    
    
    @BusinessLog(description = "供应商能力库修改")
    @ApiOperation(value = "|AbilityEO|修改")
    @PostMapping("/edit")
//    @RequiresPermissions("supplier:ability:update")
    public ResponseMessage<AbilityEO> update(String abilityEOStr,  MultipartFile file) throws Exception {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        AbilityEO abilityEO = mapper.readValue(abilityEOStr, AbilityEO.class);
        abilityEOService.updateAbilityEO(abilityEO, file);
        return Result.success("0","编辑成功！",abilityEO);
    }

    @BusinessLog(description = "供应商能力库删除")
    @ApiOperation(value = "|AbilityEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("supplier:ability:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        abilityEOService.deleteByPrimaryKey(id);
        logger.info("delete from SUP_ABILITY where id = {}", id);
        return Result.success("0","删除成功！");
    }

}
