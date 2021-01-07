package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adc.da.equipment.page.UserVOPage;
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
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.page.BasePage;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.Util.PDFUtil;
import com.adc.da.equipment.VO.EqBarcodeVo;
import com.adc.da.equipment.VO.EquipmentVO;
import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.equipment.entity.EquipmentPartsEO;
import com.adc.da.equipment.entity.EquipmentUseLogEO;
import com.adc.da.equipment.page.EquipmentEOPage;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/${restPath}/equipment/equipment")
@Api(tags = "资源管理—设备管理")
@SuppressWarnings("unchecked")
public class EquipmentEOController extends BaseController<EquipmentEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentEOController.class);

    @Autowired
    private EquipmentEOService equipmentEOService;

	@ApiOperation(value = "设备分页查询")
    @GetMapping("/page")
    @RequiresPermissions("equipment:equipment:page")
    public ResponseMessage<PageInfo<EquipmentVO>> page(EquipmentEOPage page) {
		try {
			//解析通用查询关键字
			equipmentEOService.analyzeSearchPhrase(page);
			//查询集合
			List<EquipmentVO> list = equipmentEOService.queryByPage(page);
			//查询总数
			Integer count = equipmentEOService.queryByCount(page);
			//构建page
			PageInfo<EquipmentVO> pageInfo = new PageInfo<>();
			pageInfo.setList(list);
			pageInfo.setCount((long) count);
			pageInfo.setPageSize(page.getPager().getRowCount());
			pageInfo.setPageCount((long) page.getPager().getPageCount());
			pageInfo.setPageNo(page.getPager().getPageId());
			return Result.success("0", "查询成功", pageInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
		}
    }

	@ApiOperation(value = "设备查询")
    @GetMapping("")
    public ResponseMessage<List<EquipmentVO>> list(EquipmentEOPage page) {
		try {
			//解析通用查询关键字
			equipmentEOService.analyzeSearchPhrase(page);
			return Result.success("0", "查询成功", equipmentEOService.queryByList(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
		}
	}

    @ApiOperation(value = "设备详情")
    @GetMapping("/{id}")
    @RequiresPermissions("equipment:equipment:get")
    @EnableAccess
    public ResponseMessage<EquipmentVO> find(@PathVariable String id) {
        try {
        	EquipmentVO equipmentVO = (EquipmentVO) equipmentEOService.selectByPrimaryKey(id);
        	//查询配件列表
        	List<EquipmentPartsEO> partsList = equipmentEOService.getPartsByEqId(id);
        	equipmentVO.setPartsList(partsList);
        	//查询子设备列表
        	List<EquipmentVO> children = equipmentEOService.getChildrenByParentId(id);
        	equipmentVO.setChildren(children);
			return Result.success("0", "查询成功", equipmentVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
		}
    }

	@ApiOperation(value = "设备新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @BusinessLog(description = "设备-新增")
    @RequiresPermissions("equipment:equipment:save")
    public ResponseMessage<EquipmentVO> create(@RequestBody EquipmentVO equipmentVO) {
    	try {
			//校验必填字段是否为空
			if(StringUtils.isEmpty(equipmentVO.getEqNo())) {
				return Result.error("-1", "设备编号不可为空");
			}
			if(StringUtils.isEmpty(equipmentVO.getEqName())) {
				return Result.error("-1", "设备名称不可为空");
			}
			//校验设备编号是否唯一
			if("n".equals(equipmentEOService.checkNo(equipmentVO.getEqNo(), null))) {
				return Result.error("-1", "设备编号已存在");
			}
			return Result.success("0", "新增成功", equipmentEOService.add(equipmentVO));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","新增失败!");
		}
    }

    @ApiOperation(value = "设备修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @BusinessLog(description = "设备-修改")
    @RequiresPermissions("equipment:equipment:update")
    public ResponseMessage<EquipmentVO> update(@RequestBody EquipmentVO equipmentVO) {
    	try {
    		//校验id是否为空
    		if(StringUtils.isEmpty(equipmentVO.getId())) {
    			return Result.error("-1", "设备id必须要传");
    		}
			//校验必填字段是否为空
			if(StringUtils.isEmpty(equipmentVO.getEqNo())) {
				return Result.error("-1", "设备编号不可为空");
			}
			if(StringUtils.isEmpty(equipmentVO.getEqName())) {
				return Result.error("-1", "设备名称不可为空");
			}
			//校验设备编号是否唯一
			if("n".equals(equipmentEOService.checkNo(equipmentVO.getEqNo(), equipmentVO.getId()))) {
				return Result.error("-1", "设备编号已存在");
			}
			return Result.success("0", "修改成功", equipmentEOService.update(equipmentVO));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","修改失败!");
		}
    }
    @ApiOperation(value = "设备信息删除")
    @BusinessLog(description = "设备管理-设备删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("equipment:equipment:delete")
    public ResponseMessage delete(@PathVariable String id){
        try{
            List<String> viceEqIdList = equipmentEOService.getViceEqIdList(id);
            if (CollectionUtils.isNotEmpty(viceEqIdList)){
                return Result.error("-1", "删除失败!当前设备含有附属子设备");
            }else {
                equipmentEOService.deleteById(id);
                return Result.success("0","删除成功!");
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败!");
        }

    }

    /**
     * 通过idList来批量删除
     * @param idList
     * @return
     */
    @ApiOperation(value = "|设备管理批量删除")
    @BusinessLog(description = "设备管理-批量删除")
    @DeleteMapping("/ids")
    @RequiresPermissions("equipment:equipment:batchDelete")
    public ResponseMessage deleteByIds(@ApiParam(value = "ids", required = true) @RequestParam(value = "ids") List<String> idList) {
        try {
            if (idList.size() == 0){
                return Result.error("-1", "请选择数据!");
            }
            boolean idDel = true;//是否被借用
            boolean eqUseStatus = true;//是否被删除
            for (String id : idList){
                EquipmentEO entity = equipmentEOService.getEntityById(id);
                if (entity.getEqUseStatus().equals("1")){
                    idDel = false;
                    break;
                }
                List<String> viceEqIdList = equipmentEOService.getViceEqIdList(id);
                if (CollectionUtils.isNotEmpty(viceEqIdList)){
                    eqUseStatus = false;
                    break;
                }
            }
            if (!idDel){
                return Result.error("-1", "被删除设备存在已借出情况,不可被删除！");
            }
            if (!eqUseStatus){
                return Result.error("-1", "被删除设备下含有其他的副设备,不可被删除！");
            }
            if (idDel && eqUseStatus){
                equipmentEOService.deleteByIdList(idList);
                return Result.success("0","设备批量删除成功!");
            }
            return Result.error("-1", "删除失败！");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
    /**
     * 设备的批量导入
     * 2019.11.28
     * lcx
     * @return
     */
    @ApiOperation(value = "设备信息批量导入")
    @BusinessLog(description = "设备信息-批量导入")
    @PostMapping(path = "/importEquipment")
    @RequiresPermissions("equipment:equipment:importEquipment")
    public ResponseMessage<String> importMateriel(@RequestParam("file") MultipartFile file){
        try {
            if(StringUtils.isNotEmpty(file)) {
                //得到该文件后缀
                String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
                if (!ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)
                        &&!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)){
                    return Result.error( "-1","设备信息的批量导入只支持上传Excel格式的文件!");
                }
            }
            ResponseMessage result  = equipmentEOService.importMateriel(file);
            return result;
        }catch (Exception e){
            logger.info(e.getMessage());
            return Result.error("-1","上传失败!");
        }
    }
    /**
     * 设备核检信息批量导出
     * 2019.11.29
     * lcx
     * @return
     */
    @ApiOperation(value = "设备核检-使用日志批量导出")
    @BusinessLog(description = "设备核检或使用日志-批量导出")
    @GetMapping(path ="/checkBatchExport")
    @RequiresPermissions("equipment:equipment:checkBatchExport")
    public ResponseMessage checkBatchExport(HttpServletResponse response, HttpServletRequest request,
                                            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids,String type) {
        try {
           if (ids.length == 0){
               return Result.error("-1", "请选择数据!");
           }
           equipmentEOService.checkBatchExport(response,request,ids,type);
           return Result.success("0", "导出成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation(value = "校验设备编号是否唯一")
    @GetMapping("/checkNo")
    public ResponseMessage<String> checkNo(String param, String id){
    	try {
			return Result.success("0", "已校验", equipmentEOService.checkNo(param, id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1", "验证失败!", "n");
		}
    }

    @ApiOperation(value = "|导出二维码")
    @GetMapping("/barCode")
    @RequiresPermissions("equipment:equipment:barCode")
    public ResponseMessage barCode(
            @ApiParam(value = "ids", required = true)@RequestParam(value = "ids") String[] ids,
            @ApiParam(hidden = true) HttpServletResponse response
    ) {
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            List<EqBarcodeVo> barcodeVoList = equipmentEOService.barCode(ids);
            if (CollectionUtils.isNotEmpty(barcodeVoList)){
               PDFUtil.generalBarcodePdf(barcodeVoList, response);
               return Result.success("0", "导出二维码成功");
            }
            return Result.error("-1", "设备不存在", null);
        } catch (Exception e) {
            logger.error("导出二维码异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }
    
    @ApiOperation(value = "设置设备使用记录")
    @PostMapping("/setUseLog")
    @EnableAccess
    public ResponseMessage<EquipmentUseLogEO> setUseLog(EquipmentUseLogEO useLog) {
    	try {
			if(StringUtils.isEmpty(useLog.getEqId())) {
				return Result.error("-1", "设备id必须要传");
			}
			EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(useLog.getEqId());
			if(equipmentEO == null) {
				return Result.error("-1", "该设备不存在");
			}
			return Result.success("0", "操作成功", equipmentEOService.setUseLog(useLog));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","新增失败!");
		}
    }

    /**
     * @param response
     * @return: ResponseMessage
     * @author： lcx
     * @date： 2019.12.2
     */
    @ApiOperation(value = "设备信息导出")
    @BusinessLog(description = "设备信息-批量导出")
    @GetMapping(path ="/batchExport")
    @RequiresPermissions("equipment:equipment:batchExport")
    public ResponseMessage exportEq(HttpServletResponse response, HttpServletRequest request,EquipmentEOPage page) {
        try {
            page.setPage(1);
            page.setPageSize(100000);
            //解析通用查询关键字
            equipmentEOService.analyzeSearchPhrase(page);
            equipmentEOService.eqExport(response,request,page);
            return Result.success("0", "导出成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
    
    @ApiOperation(value = "查询设备使用记录")
    @GetMapping("/useLogPage")
    public ResponseMessage<PageInfo<EquipmentUseLogEO>> useLogPage(BasePage page, String eqId, String eqUlStatus, 
    		String eqUlStartTimeStart, String eqUlStartTimeEnd, String eqUlEndTimeStart, String eqUlEndTimeEnd, String eqUlResponsiblePeople){
    	try {
			//查询集合
			List<EquipmentUseLogEO> list = equipmentEOService.getUseLogPage(page, eqId, eqUlStatus, eqUlStartTimeStart, 
					eqUlStartTimeEnd, eqUlEndTimeStart, eqUlEndTimeEnd, eqUlResponsiblePeople);
			//查询总数
			Integer count = equipmentEOService.getUseLogCount(page, eqId, eqUlStatus, eqUlStartTimeStart, 
					eqUlStartTimeEnd, eqUlEndTimeStart, eqUlEndTimeEnd, eqUlResponsiblePeople);
			//构建page
			PageInfo<EquipmentUseLogEO> pageInfo = new PageInfo<>();
			pageInfo.setList(list);
			pageInfo.setCount((long) count);
			pageInfo.setPageSize(page.getPager().getRowCount());
			pageInfo.setPageCount((long) page.getPager().getPageCount());
			pageInfo.setPageNo(page.getPager().getPageId());
			return Result.success("0", "查询成功", pageInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
		}
    }
    
    @ApiOperation(value = "新增、修改时选择管理人")
    @PostMapping("/getManagerList")
    public ResponseMessage<PageInfo<UserVO>> getManagerList(UserVOPage page){
        String searchPhrase = page.getSearchPhrase();
        String account = page.getAccount();
        String orgName = page.getOrgName();
        String usName = page.getUsName();
        String roleName = page.getRoleName();
        String userCode = page.getUserCode();
        String usposition = page.getUsposition();
        String email = page.getEmail();
        String cellPhoneNumber = page.getCellPhoneNumber();
        try {
    		//查询集合
            List<UserVO> list = equipmentEOService.getManagerPage(page, searchPhrase, account, orgName, usName,
            		roleName, userCode, usposition, email, cellPhoneNumber);
            //查询总数
            Integer count = equipmentEOService.getManagerCount(page, searchPhrase, account, orgName, usName,
            		roleName, userCode, usposition, email, cellPhoneNumber);
            //构建page
			PageInfo<UserVO> pageInfo = new PageInfo<>();
			pageInfo.setList(list);
			pageInfo.setCount((long) count);
			pageInfo.setPageSize(page.getPager().getRowCount());
			pageInfo.setPageCount((long) page.getPager().getPageCount());
			pageInfo.setPageNo(page.getPager().getPageId());
			return Result.success("0", "查询成功", pageInfo);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }


    /**
     * 设备核检信息批量导出
     * 2019.11.29
     * lcx
     * @return
     */
    @ApiOperation(value = "设备借用记录批量导出")
    @BusinessLog(description = "设备借用记录-批量导出")
    @GetMapping(path ="/checkBorrowExport")
//    @RequiresPermissions("equipment:equipment:checkBorrowExport")
    public ResponseMessage checkBorrowExport(HttpServletResponse response, HttpServletRequest request) {
        try {
            equipmentEOService.checkBorrowRecordExport(response,request);
            return Result.success("0", "导出成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

}
