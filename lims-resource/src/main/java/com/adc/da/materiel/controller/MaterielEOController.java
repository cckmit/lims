package com.adc.da.materiel.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.materiel.VO.InboundListVO;
import com.adc.da.materiel.entity.BorrowRecordEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.materiel.VO.MaterielList;
import com.adc.da.materiel.VO.MaterielVO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.adc.da.base.web.BaseController;
import com.adc.da.materiel.entity.MaterielEO;
import com.adc.da.materiel.page.MaterielEOPage;
import com.adc.da.materiel.service.MaterielEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * 物料管理
 * 2019/11/19
 * lcx
 */
@RestController
@RequestMapping("/${restPath}/materiel/materiel")
@Api(tags = "资源管理-物料管理")
public class MaterielEOController extends BaseController<MaterielEO>{
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MaterielEOController.class);

    @Autowired
    private MaterielEOService materielEOService;
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * 查询物料的入库清单
     * 2019.11.20
     * ly
     * @param page
     * @return
     */
    @ApiOperation(value = "查询物料库入库清单")
    @GetMapping("/getInboundVOByPage")
    @RequiresPermissions("materiel:inventoryRecord:page")
    public ResponseMessage<PageInfo<InboundListVO>> getInboundVOByPage(MaterielEOPage page){
        try {
            //查询
            List<MaterielEO> rows =  materielEOService.getInboundPage(page);
            //设置总条数
            Integer rowsCount = materielEOService.queryForInboundCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows),
                    InboundListVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    /**
     * 查询物料的出库清单
     * 2019.11.20
     * ly
     * @param page
     * @return
     */
    @ApiOperation(value = "查询物料出库清单")
    @GetMapping("/getOutboundVOByMatId")
    @RequiresPermissions("materiel:inventoryRecord:page")
    public ResponseMessage<PageInfo<InboundListVO>> getOutboundVOByPage(MaterielEOPage page){
        try {
            //查询
            List<MaterielEO> rows =  materielEOService.getOutboundPage(page);
            //设置总条数
            Integer rowsCount = materielEOService.queryForOutboundCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), InboundListVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    /**
     * 物料的批量导入
     * 2019.11.20
     * ly
     * @return
     */
    @ApiOperation(value = "物料信息批量导入")
    @BusinessLog(description = "物料信息-批量导入")
    @PostMapping(path = "/importMateriel")
    @RequiresPermissions("materiel:materiel:importMateriel")
    public ResponseMessage<String> importMateriel(@RequestParam("file") MultipartFile file){
        try {
            if(StringUtils.isNotEmpty(file)) {
                //得到该文件后缀
                String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
                if (!ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)
                        &&!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)){
                    return Result.error( "-1","物料信息的批量导入只支持上传Excel格式的文件!");
                }
            }
            ResponseMessage result  = materielEOService.importMateriel(file);
            return result;
        }catch (Exception e){
            logger.info(e.getMessage());
            return Result.error("-1","上传失败!");
        }
    }
    /**
     * @param response
     * @return
     * @return: ResponseMessage
     * @author： ly
     * @date： 2019.11.20
     */
    @ApiOperation(value = "物料信息批量导出")
    @BusinessLog(description = "物料信息-批量导出")
    @GetMapping(path ="/batchExport")
    @RequiresPermissions("materiel:materiel:batchExport")
    public ResponseMessage exportMateriel(HttpServletResponse response, HttpServletRequest request,MaterielEOPage page,
                                          String searchPhrase) {
        try {
            page.setPage(1);
            page.setPageSize(100000);
            materielEOService.materielBatchExport(response,request,page,searchPhrase);
            return Result.success("0", "导出成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
    /**
     * 通过ids数组来实现物料信息的批量删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "|物料信息的批量删除")
    @BusinessLog(description = "物料信息-批量删除")
    @DeleteMapping("/ids")
    @RequiresPermissions("materiel:materiel:batchDelete")
    public ResponseMessage deleteByIds(@ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids) {
        try {
            if (ids.length == 0){
                return Result.error("-1", "请选择数据!");
            }
            //设置判断变量
            boolean isDel = true;
            //循环校验id对应的物料是否存在被借出情况。若存在借出情况，则不允许删除
            for(String id : ids) {
                if(!materielEOService.checkMaterielById(id)) {
                    isDel = false;
                    break;
                }
            }
            //若存在借出情况，则不允许删除
            if(!isDel) {
                return Result.error("-1", "被删除物料存在已借出情况,不可被删除！");
            }
            //校验通过,删除数据
            materielEOService.deleteByIds(ids);
            return Result.success("0", "批量删除成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation(value = "物料分页查询")
    @GetMapping("/page")
    @RequiresPermissions("materiel:materiel:page")
    public ResponseMessage<PageInfo<MaterielList>> page(
                MaterielEOPage page,
                @RequestParam(value = "searchPhrase", required = false) String searchPhrase){
	    try{
	        //查询
            List<MaterielEO> dataList = materielEOService.page(page, searchPhrase);
            //设置条数
            Integer dataCount = materielEOService.queryByCount(page);
            page.getPager().setRowCount(dataCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), dataList),MaterielList.class));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    @ApiOperation(value = "物料详情")
    @GetMapping("/{id}")
    @RequiresPermissions("materiel:materiel:get")
    public ResponseMessage<MaterielVO> find(@PathVariable(value = "id") String id){
        try {
            return Result.success("0", "查询成功", beanMapper.map(materielEOService.selectByPrimaryKey(id), MaterielVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    @ApiOperation(value = "验证物料编码数据库是否存在")
    @GetMapping(path ="/checkNum")
    public ResponseMessage checkNum(String materielCode,String id){
	    try{
            Integer integer = materielEOService.checkNum(materielCode,id);
            return Result.success("0", "验证通过!",integer);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","验证失败!");
        }
    }

    @ApiOperation(value = "查询物料的剩余库存")
    @GetMapping(path ="/queryOldQuantity")
    public ResponseMessage queryOldQuantity(String id){
        try{
            Double sampleQuantity = 0d;
            Double num = materielEOService.queryInventorySurpius(id);
            if (num != null){
                sampleQuantity = num;
            }
            return Result.success("0", "查询成功!",sampleQuantity);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    @ApiOperation(value = "物料新增")
    @BusinessLog(description = "物料信息-物料新增")
    @PostMapping(path ="/save")
    @RequiresPermissions("materiel:materiel:save")
    public ResponseMessage<MaterielVO> create(@RequestBody MaterielVO materielVO){
	    try{
	        if (StringUtils.isEmpty(materielVO.getMaterielName())){
                return Result.error("-1", "物料名称不可为空");
            }
            if (StringUtils.isEmpty(materielVO.getMaterielCode())){
                return Result.error("-1", "物料编码不可为空");
            }
            if (StringUtils.isEmpty(materielVO.getMaterielType())){
                return Result.error("-1", "物料类型不可为空");
            }
            //处理保存业务
            materielEOService.save(beanMapper.map(materielVO, MaterielEO.class));
            return Result.success("0","新增成功!",materielVO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "新增失败!");
        }
    }

    @ApiOperation(value = "物料修改")
    @BusinessLog(description = "物料信息-物料修改")
    @PutMapping(path ="/update", consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("materiel:materiel:update")
    public ResponseMessage<MaterielVO> update(@RequestBody MaterielVO materielVO){
        try {
            //校验必填字段是否为空
            if(StringUtils.isEmpty(materielVO.getMaterielName())) {
                return Result.error("-1", "物料名称不可为空");
            }
            if(StringUtils.isEmpty(materielVO.getMaterielCode())) {
                return Result.error("-1", "物料编码不可为空");
            }
            if(StringUtils.isEmpty(materielVO.getMaterielType())) {
                return Result.error("-1", "物料类型不可为空");
            }
            //编辑
            materielEOService.update(beanMapper.map(materielVO,MaterielEO.class));
            return Result.success("0","编辑成功!",materielVO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "编辑失败!");
        }
    }

    @ApiOperation(value = "物料删除")
    @BusinessLog(description = "物料信息-物料删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("materiel:materiel:delete")
    public ResponseMessage delete(@PathVariable(value = "id") String id){
        try{
            MaterielEO materielEO = materielEOService.selectByPrimaryKey(id);
            String status = materielEO.getMaterialStatus();
            Double sampleQuantity = materielEO.getSampleQuantity();//原始样件数量
            Double inventorySurpius = materielEO.getInventorySurpius();//剩余库存
            Double difference = sampleQuantity - inventorySurpius;
            if (difference.equals(0.0) || "1".equals(status)){//原始样件数量等于剩余库存时允许删除
                materielEOService.deleteByPrimaryKey(id);
                return Result.success("0","删除成功!");
            }
            return Result.error("-1", "删除失败，当前物料有待归还记录!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败!");
        }
    }

    @ApiOperation(value = "物料报废")
    @BusinessLog(description = "物料信息-物料报废")
    @GetMapping(path ="/scrap")
    @RequiresPermissions("materiel:materiel:scrap")
    public ResponseMessage scrap(String id){
        try{
            MaterielEO materielEO = materielEOService.sampleAndStock(id);
            Double sampleQuantity = materielEO.getSampleQuantity();//原始样件数量
            Double inventorySurpius = materielEO.getInventorySurpius();//剩余库存
            if (sampleQuantity != null && inventorySurpius != null){
                Double difference = sampleQuantity - inventorySurpius;
                if (difference.equals(0.0)){//原始样件数量等于剩余库存时允许报废
                    materielEOService.scrapByPrimaryKey(id);
                    return Result.success("0","报废成功!");
                }
            }else {
                materielEOService.scrapByPrimaryKey(id);
                return Result.success("0","报废成功!");
            }
            return Result.error("-1", "报废失败，当前物料有待归还记录!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "报废失败!");
        }
    }

    @ApiOperation(value = "物料领用")
    @BusinessLog(description = "物料信息-物料领用")
    @PutMapping(path ="/borrow")
    @RequiresPermissions("materiel:materiel:borrow")
    public ResponseMessage<BorrowRecordEO> borrow(@RequestBody BorrowRecordEO borrowRecordEO){
        try{
            if (StringUtils.isEmpty(borrowRecordEO.getBorrower())){
                return Result.error("-1", "领用人不可为空");
            }
            if (StringUtils.isEmpty(borrowRecordEO.getBorrowingQuantity())){
                return Result.error("-1", "领用数量不可为空");
            }
            if (StringUtils.isEmpty(borrowRecordEO.getBorrowingTime())){
                return Result.error("-1", "领用时间不可为空");
            }
            materielEOService.insertBorrowRecord(borrowRecordEO);
            return Result.success("0","领用成功!",borrowRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "领用失败!");
        }
    }

    //查询当前用户的用户名
    @ApiOperation(value = "获取用户名")
    @GetMapping(path ="/getUserName")
    public ResponseMessage getUserName(@RequestParam(name = "userId") String userId){
        try{
            String userName = materielEOService.getUserName(userId);
            return Result.success("0", "查询成功!",userName);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    //根据用户id以及物料id获取当前用户是否可以归还，以及应该归还的数量
    @ApiOperation(value = "获取用户归还数量")
    @GetMapping(path ="/getReturnedNum")
    public ResponseMessage getReturnedNum(String materielId,String userId){
        try{
            Double returnedNums = 0.0;
            Double numS = materielEOService.getReturnedNums(userId, materielId);
            if (numS != null){
                returnedNums = numS;
            }
            return Result.success("0", "查询成功!",returnedNums);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    //物料归还
    @ApiOperation(value = "物料归还")
    @BusinessLog(description = "物料信息-物料归还")
    @PutMapping(path ="/returned")
    @RequiresPermissions("materiel:materiel:returned")
    public ResponseMessage<BorrowRecordEO> returned(@RequestBody BorrowRecordEO borrowRecordEO){
        try{
            if (StringUtils.isEmpty(borrowRecordEO.getReturner())){
                return Result.error("-1", "归还人不可为空");
            }
            if (StringUtils.isEmpty(borrowRecordEO.getReturnQuantity())){
                return Result.error("-1", "归还数量不可为空");
            }
            if (StringUtils.isEmpty(borrowRecordEO.getReturnTime())){
                return Result.error("-1", "归还时间不可为空");
            }
            materielEOService.userReturned(borrowRecordEO);
            return Result.success("0","归还成功!",borrowRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "归还失败!");
        }
    }

    @ApiOperation(value = "查询物料样件数量")
    @GetMapping(path ="/queryNums")
    public ResponseMessage queryNums(String id){
        try{
            Double sampleQuantity = 0d;
            Double num = materielEOService.getNum(id);
            if (num != null){
                sampleQuantity = num;
            }
            return Result.success("0", "查询成功!",sampleQuantity);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }
}
