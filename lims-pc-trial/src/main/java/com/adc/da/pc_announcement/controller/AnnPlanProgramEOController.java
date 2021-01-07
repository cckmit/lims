package com.adc.da.pc_announcement.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.pc_announcement.VO.AnnPlanProgramImportVO;
import com.adc.da.pc_announcement.VO.AnnPlanProgramSaveVO;
import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;
import com.adc.da.pc_announcement.page.AnnPlanProgramEOPage;
import com.adc.da.pc_announcement.service.AnnPlanProgramEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_announcement/annPlanProgram")
@Api(description = "|AnnPlanProgramEO|")
@SuppressWarnings("all")
public class AnnPlanProgramEOController extends BaseController<AnnPlanProgramEO>{

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanProgramEOController.class);

    @Autowired
    private AnnPlanProgramEOService annPlanProgramEOService;

	@ApiOperation(value = "|AnnPlanProgramEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_announcement:annPlanProgram:page")
    public ResponseMessage<PageInfo<AnnPlanProgramEO>> page(AnnPlanProgramEOPage page) throws Exception {
        List<AnnPlanProgramEO> rows = annPlanProgramEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|AnnPlanProgramEO|查询")
    @GetMapping("")
    //@RequiresPermissions("pc_announcement:annPlanProgram:list")
    public ResponseMessage<List<AnnPlanProgramEO>> list(AnnPlanProgramEOPage page) throws Exception {
        return Result.success(annPlanProgramEOService.queryByList(page));
	}

    @ApiOperation(value = "|AnnPlanProgramEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanProgram:get")
    public ResponseMessage<AnnPlanProgramEO> find(@PathVariable String id) throws Exception {
        return Result.success(annPlanProgramEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|AnnPlanProgramEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanProgram:save")
    public ResponseMessage<AnnPlanProgramEO> create(@RequestBody AnnPlanProgramEO annPlanProgramEO) throws Exception {
        annPlanProgramEOService.insertSelective(annPlanProgramEO);
        return Result.success(annPlanProgramEO);
    }

    @ApiOperation(value = "|AnnPlanProgramEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanProgram:update")
    public ResponseMessage<AnnPlanProgramEO> update(@RequestBody AnnPlanProgramEO annPlanProgramEO) throws Exception {
        annPlanProgramEOService.updateByPrimaryKeySelective(annPlanProgramEO);
        return Result.success(annPlanProgramEO);
    }

    @ApiOperation(value = "|AnnPlanProgramEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanProgram:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        annPlanProgramEOService.deleteByPrimaryKey(id);
        logger.info("delete from ANN_PLAN_PROGRAM where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value = "上传方案，返回导入文件中的数据")
    @PostMapping("importProgram")
    public ResponseMessage<Collection<AnnPlanProgramImportVO>> importProgram(MultipartFile file){
    	try {
			if(file != null) {
				//文件后缀
				String fileReportExtension = FileUtil.getFileExtension(file.getOriginalFilename());
				if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileReportExtension)) {
					return Result.error("-1", "只支持上传xlsx格式的文件!");
				}
			}
			return annPlanProgramEOService.importProgram(file);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "上传失败!");
		}
    }
    @ApiOperation(value = "检验方案查看")
    @GetMapping("/getRelProgramMes/{projectId}")
    public List<AnnPlanProgramEO> getRelProgramMes(@PathVariable String projectId){
	    try{
	        List<String> projectIdList = new ArrayList<>();
	        projectIdList.add(projectId);
            List<String> idListByPjIDList = annPlanProgramEOService.getIdListByPjIDList(projectIdList);
            return annPlanProgramEOService.getProgramMesByIds(idListByPjIDList);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
    @ApiOperation(value = "保存方案")
    @PostMapping("saveProgram")
    public ResponseMessage<String> saveProgram(@RequestBody AnnPlanProgramSaveVO vo){
    	try {
    		if(StringUtils.isEmpty(vo.getProjectId())) {
    			return Result.error("-1", "检验项目id不能为空");
    		}
			if(vo == null || vo.getProgramList() == null || vo.getProgramList().isEmpty()) {
				return Result.error("-1", "检验方案不能为空");
			}
			annPlanProgramEOService.batchUpdateProgram(vo.getProjectId(), vo.getProgramList());
			return Result.success("0", "保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "保存失败");
		}
    }
}
