package com.adc.da.part.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.part.dto.SaPartDepotDTO;
import com.adc.da.part.dto.SaPartSpaceDTO;
import com.adc.da.part.entity.SaPartDepotEO;
import com.adc.da.part.page.SaPartDepotEOPage;
import com.adc.da.part.service.SaPartDepotService;
import com.adc.da.part.vo.SaPartDepotVO;
import com.adc.da.part.vo.SaPartSpaceVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 16:54
 * @description：${description}
 */
@RestController
@RequestMapping("/${restPath}/sa/partDepot")
@Api(
        tags = {"Sa-零部件库房管理"}
)
public class SaPartDepotController extends BaseController<SaPartDepotEO> {

    private static final Logger logger = LoggerFactory.getLogger(SaPartDepotController.class);

    @Autowired
    private SaPartDepotService saPartDepotService;

    @Autowired
    private BeanMapper beanMapper;

    @ApiOperation(value = "|分页查询")
    @GetMapping("")
    // @RequiresPermissions("part:depot:list")
    public ResponseMessage<PageInfo<SaPartDepotVO>> page(
            @ApiParam(value = "库房名称") @RequestParam(value = "depotName", required = false) String depotName,
            @ApiParam(value = "库房编号") @RequestParam(value = "depotNumber", required = false) String depotNumber,
            @ApiParam(value = "起始更新时间") @RequestParam(value = "updateTime1", required = false) String updateTime1,
            @ApiParam(value = "结束更新时间") @RequestParam(value = "updateTime2", required = false) String updateTime2,
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            SaPartDepotEOPage eoPage = new SaPartDepotEOPage();
            if (StringUtils.isNotBlank(depotName)) {
                eoPage.setDepotName(depotName.trim());
            }
            if (StringUtils.isNotBlank(depotNumber)) {
                eoPage.setDepotNumber(depotNumber.trim());
            }
            if (StringUtils.isNotBlank(updateTime1)) {
                eoPage.setUpdateTime1(updateTime1.trim());
            }
            if (StringUtils.isNotBlank(updateTime2)) {
                eoPage.setUpdateTime2(updateTime2.trim());
            }
            if (StringUtils.isNotBlank(searchPhrase)) {
                List<String> list = TransformUtil.settingSearchPhrase(searchPhrase);
                eoPage.setKeyWords(list);
            }
            eoPage.setPage(page);
            eoPage.setPageSize(pageSize);
            List<SaPartDepotEO> rows = saPartDepotService.queryByPage(eoPage);
            if (rows != null && !rows.isEmpty()) {
                for (SaPartDepotEO eo : rows) {
                    // 通过id查询子级的剩余库位数
                    Integer number = saPartDepotService.queryUnUsePartSpaceNumber(eo.getId());
                    eo.setUnUsePartSpaceNumber(number == null ? 0 : number);
                }
            }
            return Result.success(beanMapper.mapPage(getPageInfo(eoPage.getPager(), rows), SaPartDepotVO.class));
        } catch (Exception e) {
            logger.error("查询零部件库房数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|保存零部件库房")
    @PostMapping("")
    // @RequiresPermissions("part:depot:save")
    public ResponseMessage<SaPartDepotVO> save(@RequestBody SaPartDepotDTO saPartDepotDTO) {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("depotNumber", 20);
            map.put("depotName", 200);
            ResponseMessage verifyLength = TransformUtil.verifyLength(saPartDepotDTO, map);
            if (verifyLength != null) {
                return verifyLength;
            }
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByDepotNuhmber(
                    saPartDepotDTO.getDepotNumber());
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                return Result.error("-1", "库房编号已经存在", null);
            }
            saPartDepotService.save(beanMapper.map(saPartDepotDTO, SaPartDepotEO.class));
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("保存零部件库房异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑零部件库房")
    @PutMapping("")
    // @RequiresPermissions("part:depot:edit")
    public ResponseMessage<SaPartDepotVO> edit(@RequestBody SaPartDepotDTO saPartDepotDTO) {
        try {
            SaPartDepotEO saPartDepotEO = saPartDepotService.selectByParam(saPartDepotDTO.getId(), 0);
            if (saPartDepotEO == null) {
                return Result.error("-1", "零部件库房不存在", null);
            }
            Map<String, Integer> map = new HashMap<>();
            map.put("depotNumber", 20);
            map.put("depotName", 200);
            ResponseMessage verifyLength = TransformUtil.verifyLength(saPartDepotDTO, map);
            if (verifyLength != null) {
                return verifyLength;
            }
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByDepotNuhmber(
                    saPartDepotDTO.getDepotNumber());
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                for (SaPartDepotEO eo : saPartDepotEOS) {
                    if (!eo.getId().equals(saPartDepotDTO.getId())) {
                        return Result.error("-1", "库房编号已经存在", null);
                    }
                }
            }
            saPartDepotEO.setDepotName(saPartDepotDTO.getDepotName());
            saPartDepotEO.setDepotNumber(saPartDepotDTO.getDepotNumber());
            saPartDepotService.edit(saPartDepotEO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑零部件库房异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|详情查询")
    @GetMapping("/{id}")
    // @RequiresPermissions("part:depot:one")
    public ResponseMessage<SaPartDepotVO> getById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            // 0 为 partSpaceStatus 是库房还是库位（0，库房；1，库位）
            SaPartDepotEO saPartDepotEO = saPartDepotService.selectByParam(id, 0);
            if (saPartDepotEO != null) {
                Integer number = saPartDepotService.queryUnUsePartSpaceNumber(saPartDepotEO.getId());
                saPartDepotEO.setUnUsePartSpaceNumber(number == null ? 0 : number);
            }
            return Result.success(beanMapper.map(saPartDepotEO, SaPartDepotVO.class));
        } catch (Exception e) {
            logger.error("查询零部件库房数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|批量删除")
    @DeleteMapping("/ids")
    // @RequiresPermissions("part:depot:delete")
    public ResponseMessage deleteByIds(
            @ApiParam(value = "ids", required = true) @RequestParam("ids") String[] ids
    ) {
        try {
            for (String id : ids) {
                saPartDepotService.deleteById(id);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("批量删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|删除")
    @DeleteMapping("/{id}")
    // @RequiresPermissions("part:depot:delete")
    public ResponseMessage deleteById(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id
    ) {
        try {
            saPartDepotService.deleteById(id);
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|新增库房校验零部件库房编号是否存在")
    @PatchMapping("")
    // @RequiresPermissions("part:depot:verify")
    public ResponseMessage verify(
            @ApiParam(value = "库房编号", required = true) @RequestParam("depotNumber") String depotNumber
    ) {
        try {
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByDepotNuhmber(depotNumber);
            if (saPartDepotEOS == null || saPartDepotEOS.isEmpty()) {
                return Result.success();
            } else {
                return Result.error("-1", "库房编号已经存在", null);
            }
        } catch (Exception e) {
            logger.error("新增库房校验零部件库房编号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑库房校验零部件库房编号是否存在")
    @PatchMapping("/{id}")
    // @RequiresPermissions("part:depot:verify")
    public ResponseMessage editVerify(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "库房编号", required = true) @RequestParam("depotNumber") String depotNumber
    ) {
        try {
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByDepotNuhmber(depotNumber);
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                for (SaPartDepotEO eo : saPartDepotEOS) {
                    if (!eo.getId().equals(id)) {
                        return Result.error("-1", "库房编号已经存在", null);
                    }
                }
            }
            return Result.success();
        } catch (Exception e) {
            logger.error("编辑库房校验零部件库房编号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|获取库位状态")
    @GetMapping("/partSpaceStatus/{partDepotId}")
    public ResponseMessage<List<SaPartSpaceVO>> getPartSpaceStatus(
            @ApiParam(value = "库房ID", required = true) @PathVariable("partDepotId") String partDepotId
    ) {
        try {
            List<SaPartDepotEO> partSpaceStatus = saPartDepotService.getPartSpaceStatus(partDepotId);
            return Result.success(beanMapper.mapList(partSpaceStatus, SaPartSpaceVO.class));
        } catch (Exception e) {
            logger.error("获取库位状态异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    //--------------------------------------------库位管理-----------------------------------------------------//

    @ApiOperation(value = "|新增库位")
    @PostMapping("/partSpace")
    // @RequiresPermissions("part:space:save")
    public ResponseMessage<SaPartSpaceVO> saveSpace(@RequestBody SaPartSpaceDTO saPartSpaceDTO) {
        try {
            ResponseMessage message = TransformUtil.verify(saPartSpaceDTO, "carDepotId", "carDepotId",
                    "carDepotId", "shelfNumber", "layerNumber");
            if (message != null) {
                return message;
            }
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByShelfNumberAndPart(saPartSpaceDTO.getShelfNumber(),saPartSpaceDTO.getPartDepotId());
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                return Result.error("-1", "货架号已经存在", null);
            }
            saPartDepotService.saveSpace(beanMapper.map(saPartSpaceDTO, SaPartDepotEO.class));
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("新增库位异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|详情查询")
    @GetMapping("/partSpace/{id}")
    // @RequiresPermissions("part:depot:one")
    public ResponseMessage<SaPartSpaceVO> getCarSpaceById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            // 1 为 partSpaceStatus 是库房还是库位（0，库房；1，库位）
            SaPartDepotEO saPartDepotEO = saPartDepotService.selectByParam(id, 1);
            return Result.success(beanMapper.map(saPartDepotEO, SaPartSpaceVO.class));
        } catch (Exception e) {
            logger.error("查询库位数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑库位")
    @PutMapping("/partSpace")
    // @RequiresPermissions("part:space:edit")
    public ResponseMessage<SaPartSpaceVO> editSpace(@RequestBody SaPartSpaceDTO saPartSpaceDTO) {
        try {
            SaPartDepotEO saPartDepotEO = saPartDepotService.selectByParam(saPartSpaceDTO.getId(), 1);
            if (saPartDepotEO == null) {
                return Result.error("-1", "库位不存在", null);
            }
            ResponseMessage message = TransformUtil.verify(saPartSpaceDTO, "carDepotId", "carDepotId",
                    "shelfNumber", "layerNumber");
            if (message != null) {
                return message;
            }
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByShelfNumberAndPart(saPartDepotEO.getShelfNumber(),saPartDepotEO.getPartDepotId());
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                for (SaPartDepotEO eo : saPartDepotEOS) {
                    if (!eo.getId().equals(saPartSpaceDTO.getId())) {
                        return Result.error("-1", "货架号已经存在", null);
                    }
                }
            }
            saPartDepotEO.setRowNumber(saPartSpaceDTO.getRowNumber());
            saPartDepotEO.setAreaNumber(saPartSpaceDTO.getAreaNumber());
            saPartDepotEO.setShelfNumber(saPartSpaceDTO.getShelfNumber());
            saPartDepotEO.setLayerNumber(saPartSpaceDTO.getLayerNumber());
            saPartDepotEO.setUnUsePartSpaceNumber(saPartSpaceDTO.getLayerNumber());
            saPartDepotService.editSpace(saPartDepotEO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑库位异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|分页查询库位")
    @GetMapping("/partSpace")
    // @RequiresPermissions("part:depot:list")
    public ResponseMessage<PageInfo<SaPartSpaceVO>> getCarSpacePage(
            @ApiParam(value = "id") @RequestParam(value = "id") String id,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            SaPartDepotEOPage page = new SaPartDepotEOPage();
            page.setId(id);
            page.setPage(pageNo);
            page.setPageSize(pageSize);
            List<SaPartDepotEO> rows = saPartDepotService.getPartSpacePage(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), SaPartSpaceVO.class));
        } catch (Exception e) {
            logger.error("查询库位数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|新增库位校验货架号是否存在")
    @PatchMapping("/partSpace")
    // @RequiresPermissions("part:depot:verify")
    public ResponseMessage verifyPartSpace(
            @ApiParam(value = "货架号", required = true) @RequestParam("shelfNumber") Integer shelfNumber
    ) {
        try {
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByShelfNumber(shelfNumber);
            if (saPartDepotEOS == null || saPartDepotEOS.isEmpty()) {
                return Result.success();
            } else {
                return Result.error("-1", "货架号已经存在", null);
            }
        } catch (Exception e) {
            logger.error("新增库位校验货架号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑库位校验货架号是否存在")
    @PatchMapping("/partSpace/{id}")
    // @RequiresPermissions("part:depot:verify")
    public ResponseMessage editPartSpaceVerify(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "货架号", required = true) @RequestParam("shelfNumber") Integer shelfNumber
    ) {
        try {
            List<SaPartDepotEO> saPartDepotEOS = saPartDepotService.queryByShelfNumber(shelfNumber);
            if (saPartDepotEOS != null && !saPartDepotEOS.isEmpty()) {
                for (SaPartDepotEO eo : saPartDepotEOS) {
                    if (!eo.getId().equals(id)) {
                        return Result.error("-1", "货架号已经存在", null);
                    }
                }
            }
            return Result.success();
        } catch (Exception e) {
            logger.error("编辑库位校验货架号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "判断库房是否被使用")
    @GetMapping("/judgePartIsUsed/{id}")
    public ResponseMessage judgePartIsUsed(@ApiParam(value = "库房ID", required = true) @RequestParam(value = "partDepotId") String partDepotId){
        try{
            //是为true  否为false
            boolean flag = saPartDepotService.judgePartIsUsed(partDepotId);
            if(flag){
                return Result.error("-1", "该库房已被使用", null);
            }else{
                return Result.success();
            }
        }catch (Exception e){
            logger.error("判断库房是否被使用存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "判断库位是否被使用")
    @GetMapping("/judgePartSpaceIsUsed")
    public ResponseMessage judgePartSpaceIsUsed(
            @ApiParam(value = "库房ID", required = true) @RequestParam("partDepotId") String partDepotId,
            @ApiParam(value = "第几区", required = true) @RequestParam("areaNumber") Integer areaNumber,
            @ApiParam(value = "第几行", required = true) @RequestParam("rowNumber") Integer rowNumber,
            @ApiParam(value = "第几个货架", required = true) @RequestParam("shelfNumber") Integer shelfNumber
    )
    {
        try{
            //是为true  否为false
            boolean flag = saPartDepotService.judgePartSpaceIsUsed(partDepotId, areaNumber, rowNumber, shelfNumber);
            if(flag){
                return Result.error("-1", "该库位已被使用", null);
            }else{
                return Result.success();
            }
        }catch (Exception e){
            logger.error("判断库位是否被使用存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

}
