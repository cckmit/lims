package com.adc.da.car.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.car.dto.CarDepotDTO;
import com.adc.da.car.dto.CarSpaceDTO;
import com.adc.da.car.entity.SaCarDepotEO;
import com.adc.da.car.page.CarDepotEOPage;
import com.adc.da.car.service.CarDepotService;
import com.adc.da.car.vo.CarDepotVO;
import com.adc.da.car.vo.CarSpaceVO;
import com.adc.da.common.utils.TransformUtil;
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
 * @date ：Created in 2019/7/8 11:35
 * @description：${description}
 */
@RestController
@RequestMapping("/${restPath}/sa/carDepot")
@Api(
        tags = {"Sa-整车库房管理"}
)
public class CarDepotController extends BaseController<SaCarDepotEO> {

    private static final Logger logger = LoggerFactory.getLogger(CarDepotController.class);

    @Autowired
    private CarDepotService carDepotService;

    @Autowired
    private BeanMapper beanMapper;

    @ApiOperation(value = "|分页查询")
    @GetMapping("")
    // @RequiresPermissions("car:depot:list")
    public ResponseMessage<PageInfo<CarDepotVO>> page(
            @ApiParam(value = "停车场名称") @RequestParam(value = "depotName", required = false) String depotName,
            @ApiParam(value = "停车场编号") @RequestParam(value = "depotNumber", required = false) String depotNumber,
            @ApiParam(value = "起始更新时间") @RequestParam(value = "updateTime1", required = false) String updateTime1,
            @ApiParam(value = "结束更新时间") @RequestParam(value = "updateTime2", required = false) String updateTime2,
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            CarDepotEOPage eoPage = new CarDepotEOPage();
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
            List<SaCarDepotEO> rows = carDepotService.queryByPage(eoPage);
            if (rows != null && !rows.isEmpty()) {
                for (SaCarDepotEO eo : rows) {
                    // 通过库房ID查询库房下未使用的车位
                    Integer number = carDepotService.queryUnUseCarSpaceNumber(eo.getId());
                    eo.setUnUseCarSpaceNumber(number == null ? 0 : number);
                }
            }
            return Result.success(beanMapper.mapPage(getPageInfo(eoPage.getPager(), rows), CarDepotVO.class));
        } catch (Exception e) {
            logger.error("查询整车库房数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }


    @ApiOperation(value = "|详情查询")
    @GetMapping("/{id}")
    // @RequiresPermissions("car:depot:one")
    public ResponseMessage<CarDepotVO> getById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            SaCarDepotEO saCarDepotEO = carDepotService.selectByParam(id, 0);
            if (saCarDepotEO != null) {
                Integer number = carDepotService.queryUnUseCarSpaceNumber(saCarDepotEO.getId());
                saCarDepotEO.setUnUseCarSpaceNumber(number == null ? 0 : number);
            }
            return Result.success(beanMapper.map(saCarDepotEO, CarDepotVO.class));
        } catch (Exception e) {
            logger.error("查询整车库房数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|保存整车库房")
    @PostMapping("")
    // @RequiresPermissions("car:depot:save")
    public ResponseMessage<CarDepotVO> save(@RequestBody CarDepotDTO carDepotDTO) {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("depotNumber", 20);
            map.put("depotName", 200);
            ResponseMessage verifyLength = TransformUtil.verifyLength(carDepotDTO, map);
            if (verifyLength != null) {
                return verifyLength;
            }
            List<SaCarDepotEO> saCarDepotEO = carDepotService.queryByDepotNuhmber(carDepotDTO.getDepotNumber());
            if (saCarDepotEO != null && !saCarDepotEO.isEmpty()) {
                return Result.error("-1", "库房编号已经存在", null);
            }
            carDepotService.save(beanMapper.map(carDepotDTO, SaCarDepotEO.class));
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("保存整车库房异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑整车库房")
    @PutMapping("")
    // @RequiresPermissions("car:depot:edit")
    public ResponseMessage<CarDepotVO> edit(@RequestBody CarDepotDTO carDepotDTO) {
        try {
            SaCarDepotEO saCarDepotEO = carDepotService.selectByParam(carDepotDTO.getId(), 0);
            if (saCarDepotEO == null) {
                return Result.error("-1", "整车库房不存在", null);
            }
            Map<String, Integer> map = new HashMap<>();
            map.put("depotNumber", 20);
            map.put("depotName", 200);
            ResponseMessage verifyLength = TransformUtil.verifyLength(carDepotDTO, map);
            if (verifyLength != null) {
                return verifyLength;
            }
            List<SaCarDepotEO> saCarDepotEOList = carDepotService.queryByDepotNuhmber(carDepotDTO.getDepotNumber());
            if (saCarDepotEOList != null && !saCarDepotEOList.isEmpty()) {
                for (SaCarDepotEO eo : saCarDepotEOList) {
                    if (!eo.getId().equals(carDepotDTO.getId())) {
                        return Result.error("-1", "库房编号已经存在", null);
                    }
                }
            }
            saCarDepotEO.setDepotName(carDepotDTO.getDepotName());
            saCarDepotEO.setDepotNumber(carDepotDTO.getDepotNumber());
            carDepotService.edit(saCarDepotEO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑整车库房异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|批量删除")
    @DeleteMapping("/ids")
    // @RequiresPermissions("car:depot:delete")
    public ResponseMessage<CarDepotVO> deleteByIds(
            @ApiParam(value = "ids", required = true) @RequestParam("ids") String[] ids
    ) {
        try {
            for (String id : ids) {
                carDepotService.deleteById(id);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("批量删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|删除")
    @DeleteMapping("/{id}")
    // @RequiresPermissions("car:depot:delete")
    public ResponseMessage deleteById(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id
    ) {
        try {
            carDepotService.deleteById(id);
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|新增库房校验整车库房编号是否存在")
    @PatchMapping("")
    // @RequiresPermissions("car:depot:verify")
    public ResponseMessage verify(
            @ApiParam(value = "库房编号", required = true) @RequestParam("depotNumber") String depotNumber
    ) {
        try {
            List<SaCarDepotEO> saCarDepotEO = carDepotService.queryByDepotNuhmber(depotNumber);
            if (saCarDepotEO == null || saCarDepotEO.isEmpty()) {
                return Result.success();
            } else {
                return Result.error("-1", "库房编号已经存在", null);
            }
        } catch (Exception e) {
            logger.error("新增库房校验整车库房编号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑库房校验整车库房编号是否存在")
    @PatchMapping("/{id}")
    // @RequiresPermissions("car:depot:verify")
    public ResponseMessage editVerify(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "库房编号", required = true) @RequestParam("depotNumber") String depotNumber
    ) {
        try {
            List<SaCarDepotEO> saCarDepotEO = carDepotService.queryByDepotNuhmber(depotNumber);
            if (saCarDepotEO != null && !saCarDepotEO.isEmpty()) {
                for (SaCarDepotEO eo : saCarDepotEO) {
                    if (!eo.getId().equals(id)) {
                        return Result.error("-1", "库房编号已经存在", null);
                    }
                }
            }
            return Result.success();
        } catch (Exception e) {
            logger.error("编辑库房校验整车库房编号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|获取库位状态")
    @GetMapping("/carSpaceStatus/{carDepotId}")
    public ResponseMessage<List<CarSpaceVO>> getPartSpaceStatus(
            @ApiParam(value = "库房ID", required = true) @PathVariable("carDepotId") String carDepotId
    ) {
        try {
            List<SaCarDepotEO> partSpaceStatus = carDepotService.getPartSpaceStatus(carDepotId);
            return Result.success(beanMapper.mapList(partSpaceStatus, CarSpaceVO.class));
        } catch (Exception e) {
            logger.error("获取库位状态异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    //-------------------------------------------------车位管理------------------------------------------------------//

    @ApiOperation(value = "|保存车位")
    @PostMapping("/carSpace")
    // @RequiresPermissions("car:space:save")
    public ResponseMessage<CarSpaceVO> saveSpace(@RequestBody CarSpaceDTO carSpaceDTO) {
        try {
            ResponseMessage message = TransformUtil.verify(carSpaceDTO, "carDepotId", "carSpaceNumber",
                    "rowNumber", "startNumber");
            if (message != null) {
                return message;
            }
            List<SaCarDepotEO> saCarDepotEOList = carDepotService.queryByRowNumber(carSpaceDTO.getCarDepotId(),carSpaceDTO.getRowNumber());
            if (saCarDepotEOList != null && !saCarDepotEOList.isEmpty()) {
                return Result.error("-1", "行号已经存在", null);
            }
            carDepotService.saveSpace(beanMapper.map(carSpaceDTO, SaCarDepotEO.class));
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("保存车位异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑车位")
    @PutMapping("/carSpace")
    // @RequiresPermissions("car:space:edit")
    public ResponseMessage<CarSpaceVO> editSpace(@RequestBody CarSpaceDTO carSpaceDTO) {
        try {
            SaCarDepotEO saCarDepotEO = carDepotService.selectByParam(carSpaceDTO.getId(), 1);
            if (saCarDepotEO == null) {
                return Result.error("-1", "车位不存在", null);
            }
            ResponseMessage message = TransformUtil.verify(carSpaceDTO, "carSpaceNumber", "rowNumber",
                    "startNumber");
            if (message != null) {
                return message;
            }
            List<SaCarDepotEO> saCarDepotEOList = carDepotService.queryByRowNumber(carSpaceDTO.getCarDepotId(),carSpaceDTO.getRowNumber());
            if (saCarDepotEOList != null && !saCarDepotEOList.isEmpty()) {
                for (SaCarDepotEO eo : saCarDepotEOList) {
                    if (!eo.getId().equals(carSpaceDTO.getId())) {
                        return Result.error("-1", "行号已经存在", null);
                    }
                }
            }
            saCarDepotEO.setRowNumber(carSpaceDTO.getRowNumber());
            saCarDepotEO.setCarSpaceNumber(carSpaceDTO.getCarSpaceNumber());
            saCarDepotEO.setStartNumber(carSpaceDTO.getStartNumber());
            saCarDepotEO.setUnUseCarSpaceNumber(carSpaceDTO.getCarSpaceNumber());
            carDepotService.edit(saCarDepotEO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑车位异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|详情查询")
    @GetMapping("/carSpace/{id}")
    // @RequiresPermissions("car:depot:one")
    public ResponseMessage<CarSpaceVO> getCarSpaceById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            SaCarDepotEO saCarDepotEO = carDepotService.selectByParam(id, 1);
            return Result.success(beanMapper.map(saCarDepotEO, CarSpaceVO.class));
        } catch (Exception e) {
            logger.error("查询车位数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|分页查询车位")
    @GetMapping("/carSpace")
    // @RequiresPermissions("car:depot:list")
    public ResponseMessage<PageInfo<CarSpaceVO>> getCarSpacePage(
            @ApiParam(value = "id") @RequestParam(value = "id") String id,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            CarDepotEOPage page = new CarDepotEOPage();
            page.setId(id);
            page.setPage(pageNo);
            page.setPageSize(pageSize);
            List<SaCarDepotEO> rows = carDepotService.getCarSpacePage(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), CarSpaceVO.class));
        } catch (Exception e) {
            logger.error("查询车位数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }
    }

    @ApiOperation(value = "|新增车位校验行号是否存在")
    @PatchMapping("/carSpace")
    // @RequiresPermissions("car:depot:verify")
    public ResponseMessage verifyCarSpace(
            @ApiParam(value = "车位关联停车场id", required = true) @RequestParam("carDepotId") String carDepotId,
            @ApiParam(value = "行号", required = true) @RequestParam("rowNumber") Integer rowNumber
    ) {
        try {
            List<SaCarDepotEO> saCarDepotEO = carDepotService.queryByRowNumber(carDepotId,rowNumber);
            if (saCarDepotEO == null || saCarDepotEO.isEmpty()) {
                return Result.success();
            } else {
                return Result.error("-1", "行号已经存在", null);
            }
        } catch (Exception e) {
            logger.error("新增车位校验行号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑车位校验行号是否存在")
    @PatchMapping("/carSpace/{id}")
    // @RequiresPermissions("car:depot:verify")
    public ResponseMessage editCarSpaceVerify(
            @ApiParam(value = "id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "车位关联停车场id", required = true) @RequestParam("carDepotId") String carDepotId,
            @ApiParam(value = "行号", required = true) @RequestParam("rowNumber") Integer rowNumber
    ) {
        try {
            List<SaCarDepotEO> saCarDepotEO = carDepotService.queryByRowNumber(carDepotId,rowNumber);
            if (saCarDepotEO != null && !saCarDepotEO.isEmpty()) {
                for (SaCarDepotEO eo : saCarDepotEO) {
                    if (!eo.getId().equals(id)) {
                        return Result.error("-1", "行号已经存在", null);
                    }
                }
            }
            return Result.success();
        } catch (Exception e) {
            logger.error("编辑车位校验行号是否存在异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

}
