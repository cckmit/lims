package com.adc.da.pc_announcement.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_announcement.VO.AnnPlanProjectVO;
import com.adc.da.pc_announcement.VO.AnnPlanSaveVO;
import com.adc.da.pc_announcement.VO.AnnPlanVO;
import com.adc.da.pc_announcement.VO.DelegateDataVO;
import com.adc.da.pc_announcement.entity.AnnPlanAttachEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_announcement.service.AnnPlanAttachEOService;
import com.adc.da.pc_announcement.service.AnnPlanProjectEOService;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.*;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.page.AnnPlanEOPage;
import com.adc.da.pc_announcement.service.AnnPlanEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/${restPath}/pc_announcement/annPlan")
@Api(tags = "公告管理")
public class AnnPlanEOController extends BaseController<AnnPlanEO>{

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanEOController.class);

    @Autowired
    private AnnPlanEOService annPlanEOService;
    @Autowired
    private AnnPlanProjectEOService annPlanProjectEOService;
    @Autowired
    private AnnPlanAttachEOService annPlanAttachEOService;
    @Autowired
    private IFileStore iFileStore;

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    @ApiOperation(value = "查询对应创建人的计划列表")
    @GetMapping("/getAnnPlanList")
    @RequiresPermissions("pc_announcement:annPlan:getAnnPlanList")
    public  List<AnnPlanEO> getAnnPlanList(@RequestBody AnnPlanEO annPlanEO){
        try{
            String createBy = annPlanEO.getPlCreateBy();
            return annPlanEOService.getAnnPlanList(createBy);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @ApiOperation(value = "查询对应创建人的计划列表分页")
    @GetMapping("/getAnnPlanPageList")
    @RequiresPermissions("pc_announcement:annPlan:getAnnPlanPageList")
    public  ResponseMessage<PageInfo<AnnPlanEO>> getAnnPlanPageList(AnnPlanEOPage page) {
        try {
        	//当前登录人
        	String curUserId = UserUtils.getUserId();
        	page.setPlCreateBy(curUserId);
        	//解析通用查询关键字
        	annPlanEOService.analyzeSearchPhrase(page);
            //查询
            List<AnnPlanEO> rows = annPlanEOService.getAnnPlanPage(page);
            //设置总条数
            Integer rowsCount = annPlanEOService.queryForAnnPlanCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows),
                    AnnPlanEO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "CV主管查询对应计划的申报项目列表")
    @GetMapping("/getAnnPlanProjectList/{planId}")
    @RequiresPermissions("pc_announcement:annPlan:getAnnPlanProjectList")
    public  List<AnnPlanProjectEO> getAnnPlanProjectList(@PathVariable String planId){
        try{
            return annPlanEOService.getAnnPlanProjectList(planId);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "查询对应计划的详情列表")
    @GetMapping("/getAnnPlanMes/{planId}")
    @RequiresPermissions("pc_announcement:annPlan:getAnnPlanMes")
    public  AnnPlanVO getAnnPlanMes(@PathVariable String planId){
        try{
            return annPlanEOService.getAnnPlanMes(planId);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "工程师查询对应计划的申报项目List")
    @GetMapping("/getEngineerAnnPlanProList/{planId}")
    @RequiresPermissions("pc_announcement:annPlan:getEngineerAnnPlanProList")
    public  List<AnnPlanProjectEO> getEngineerAnnPlanProList(@PathVariable String planId){
        try{
            String userID =  UserUtils.getUserId();
            return annPlanEOService.getEngineerAnnPlanProList(planId,userID);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "工程师查询对应计划详情")
    @GetMapping("/getEngineerAnnPlanMes/{planId}")
    @RequiresPermissions("pc_announcement:annPlan:getEngineerAnnPlanMes")
    public  AnnPlanVO getEngineerAnnPlanMes(@PathVariable String planId){
        try{
            String userID =  UserUtils.getUserId();
            return annPlanEOService.getEngineerAnnPlanMes(planId,userID);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "工程师查看分配给自己的计划-分页")
    @GetMapping("/findAnnPlanForEngineer")
    @RequiresPermissions("pc_announcement:annPlan:findAnnPlanForEngineer")
    public  ResponseMessage<PageInfo<AnnPlanEO>> findAnnPlanForEngineer(AnnPlanEOPage page){
        try{
            String userID = UserUtils.getUserId();
            //解析通用查询关键字
        	annPlanEOService.analyzeSearchPhrase(page);
            //查询
            List<AnnPlanEO> rows = annPlanEOService.findAnnPlanForEngineer(page,userID);
            //设置总条数
            Integer rowsCount = annPlanEOService.queryForEngineerAnnPlanCount(page,userID);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows),
                    AnnPlanEO.class));
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "计划新增")
    @BusinessLog(description = "计划新增")
    @PostMapping(path ="/savePlan")
    @RequiresPermissions("pc_announcement:annPlan:saveAnnPlanList")
    public ResponseMessage savePlan(@RequestBody AnnPlanSaveVO annPlanSaveVO){
        try{
            AnnPlanEO annPlanEO = annPlanSaveVO.getAnnPlanEO();
            List<AnnPlanProjectVO> annPlanProjectList = annPlanSaveVO.getAnnPlanProjectVOList();
            //申报项目存在Excel导入情况，这个时候，试验工程师是没有ID传过来的。先对这种情况进行处理
            for (AnnPlanProjectVO annPlanProjectVO : annPlanProjectList){
                if (annPlanProjectVO.getPjEngineerId() == null || "".equals(annPlanProjectVO.getPjEngineerId())){
                    if (annPlanProjectVO.getPjEngineerName() != null && !"".equals(annPlanProjectVO.getPjEngineerName())){
                        //尝试根据姓名唯一确定一个试验工程师
                        String name = annPlanProjectVO.getPjEngineerName().trim();
                        List<UserEO> userEOS = annPlanEOService.getUserListByName(name);
                        int size = userEOS.size();
                        if(size == 0){
                            return Result.error("-1", "新增失败!请检查姓名为“"+name+"”的试验工程师是否存在！");
                        }else if(size > 1){
                            return Result.error("-1", "新增失败!姓名为“"+name+"”的试验工程师存多个！请手动选择！");
                        }else{
                            String userID = userEOS.get(0).getUsid();
                            annPlanProjectVO.setPjEngineerId(userID);
                        }
                    }else{
                        return Result.error("-1", "新增失败!请检查所有申报项目是否都选择了试验工程师！");
                    }
                }
            }
            String planID = UUID.randomUUID();
            annPlanEO.setId(planID);
            //将实验计划状态设置为“未开始”
            annPlanEO.setPlStatus("0");
            //新增计划
            annPlanEOService.insertPlan(annPlanEO);
            //新增相关的申报项目
            annPlanProjectEOService.insertProject(annPlanProjectList,planID);
            return Result.success("0","新增成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "新增失败!");
        }
    }
    @ApiOperation(value = "编辑计划")
    @BusinessLog(description = "编辑计划")
    @PutMapping(path ="/updatePlan")
    @RequiresPermissions("pc_announcement:annPlan:updateAnnPlanList")
    public ResponseMessage updatePlan(@RequestBody AnnPlanSaveVO annPlanSaveVO) {
        try {
            AnnPlanEO annPlanEO = annPlanSaveVO.getAnnPlanEO();
            List<AnnPlanProjectVO> annPlanProjectList = annPlanSaveVO.getAnnPlanProjectVOList();
            for (AnnPlanProjectVO annPlanProjectVO : annPlanProjectList){
                if (annPlanProjectVO.getPjEngineerId() == null || "".equals(annPlanProjectVO.getPjEngineerId())){
                    if (annPlanProjectVO.getPjEngineerName() != null && !"".equals(annPlanProjectVO.getPjEngineerName())){
                        //尝试根据姓名唯一确定一个试验工程师
                        String name = annPlanProjectVO.getPjEngineerName().trim();
                        List<UserEO> userEOS = annPlanEOService.getUserListByName(name);
                        int size = userEOS.size();
                        if(size == 0){
                            return Result.error("-1", "新增失败!请检查姓名为“"+name+"”的试验工程师是否存在！");
                        }else if(size > 1){
                            return Result.error("-1", "新增失败!姓名为“"+name+"”的试验工程师存多个！请手动选择！");
                        }else{
                            String userID = userEOS.get(0).getUsid();
                            annPlanProjectVO.setPjEngineerId(userID);
                        }
                    }else{
                        return Result.error("-1", "新增失败!请检查所有申报项目是否都选择了试验工程师！");
                    }
                }
                }
            //更新计划
            annPlanEO.setPlUpdateBy(UserUtils.getUserId());
            annPlanEO.setPlUpdateTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4));
            annPlanEOService.updateByPrimaryKeySelective(annPlanEO);
            //更新计划相关的申报项目
            annPlanProjectEOService.updateListByList(annPlanProjectList);
            return Result.success("0","编辑成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "编辑失败!");
        }
    }
    @ApiOperation(value = "计划提交")
    @BusinessLog(description = "计划提交")
    @PostMapping(path ="/savePlanToEngineer")
    @RequiresPermissions("pc_announcement:annPlan:savePlanToEngineer")
    public ResponseMessage savePlanToEngineer(@RequestBody AnnPlanSaveVO annPlanSaveVO) {
        try {
            AnnPlanEO annPlanEO = annPlanSaveVO.getAnnPlanEO();
            List<AnnPlanProjectVO> annPlanProjectList = annPlanSaveVO.getAnnPlanProjectVOList();
            for (AnnPlanProjectVO annPlanProjectVO : annPlanProjectList){
                if (annPlanProjectVO.getPjEngineerId() == null || "".equals(annPlanProjectVO.getPjEngineerId())){
                    if (annPlanProjectVO.getPjEngineerName() != null && !"".equals(annPlanProjectVO.getPjEngineerName())){
                        //尝试根据姓名唯一确定一个试验工程师
                        String name = annPlanProjectVO.getPjEngineerName().trim();
                        List<UserEO> userEOS = annPlanEOService.getUserListByName(name);
                        int size = userEOS.size();
                        if(size == 0){
                            return Result.error("-1", "新增失败!请检查姓名为“"+name+"”的试验工程师是否存在！");
                        }else if(size > 1){
                            return Result.error("-1", "新增失败!姓名为“"+name+"”的试验工程师存多个！请手动选择！");
                        }else{
                            String userID = userEOS.get(0).getUsid();
                            annPlanProjectVO.setPjEngineerId(userID);
                        }
                    }else{
                        return Result.error("-1", "新增失败!请检查所有申报项目是否都选择了试验工程师！");
                    }
                }
            }
            //该情况是编辑界面点击的提交，否则是新增界面
            if(annPlanEO.getId() != null && !"".equals(annPlanEO.getId())){
                annPlanEO.setPlStatus("1");
                //更新计划
                annPlanEOService.updateByPrimaryKeySelective(annPlanEO);
                //更新计划相关的申报项目
                annPlanProjectEOService.updateListByList(annPlanProjectList);
                return Result.success("0","提交成功!");
            }else{
                String planID = UUID.randomUUID();
                annPlanEO.setId(planID);
                //将实验计划状态设置为“进行中”
                annPlanEO.setPlStatus("1");
                //新增计划
                annPlanEOService.insertPlan(annPlanEO);
                //新增相关的申报项目
                annPlanProjectEOService.insertProject(annPlanProjectList,planID);
                return Result.success("0","提交成功!");
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "提交失败!");
        }
    }
    @ApiOperation(value = "删除计划")
    @DeleteMapping("/delPlan/{planID}")
    @BusinessLog(description = "删除计划")
    @RequiresPermissions("pc_announcement:annPlan:delPlan")
    public ResponseMessage delPlan(@PathVariable String planID){
        try{
            //删除计划，包括删除计划本身，以及计划所关联的申报项目，以及申报项目所关联的检验方案
            annPlanEOService.delPlan(planID);
            return Result.success("0","删除成功!");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败!");
        }
    }
	@ApiOperation(value = "|AnnPlanEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_announcement:annPlan:page")
    public ResponseMessage<PageInfo<AnnPlanEO>> page(AnnPlanEOPage page) throws Exception {
        List<AnnPlanEO> rows = annPlanEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|AnnPlanEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_announcement:annPlan:list")
    public ResponseMessage<List<AnnPlanEO>> list(AnnPlanEOPage page) throws Exception {
        return Result.success(annPlanEOService.queryByList(page));
	}

    @ApiOperation(value = "|AnnPlanEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlan:get")
    public ResponseMessage<AnnPlanEO> find(@PathVariable String id) throws Exception {
        return Result.success(annPlanEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|AnnPlanEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlan:save")
    public ResponseMessage<AnnPlanEO> create(@RequestBody AnnPlanEO annPlanEO) throws Exception {
        annPlanEOService.insertSelective(annPlanEO);
        return Result.success(annPlanEO);
    }

    @ApiOperation(value = "|AnnPlanEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlan:update")
    public ResponseMessage<AnnPlanEO> update(@RequestBody AnnPlanEO annPlanEO) throws Exception {
        annPlanEOService.updateByPrimaryKeySelective(annPlanEO);
        return Result.success(annPlanEO);
    }

    @ApiOperation(value = "|AnnPlanEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlan:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        annPlanEOService.deleteByPrimaryKey(id);
        logger.info("delete from ANN_PLAN where id = {}", id);
        return Result.success();
    }
    /**
     * 公告计划批量导入
     * 2019.12.25
     * lcx
     * @return
     */
    @ApiOperation(value = "申报项目批量导入")
    @BusinessLog(description = "申报项目-批量导入")
    @PostMapping(path = "/importAnnPlan")
    @RequiresPermissions("pc_announcement:annPlan:importAnnPlan")
    public ResponseMessage importAnnPlan(@RequestParam("file") MultipartFile file){
        try {
            if(StringUtils.isNotEmpty(file)) {
                //得到该文件后缀
                String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
                if (!ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)
                        &&!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)){
                    return Result.error( "-1","申报项目的批量导入只支持上传Excel格式的文件!");
                }
            }
            ResponseMessage responseMessage = annPlanEOService.importAnnPlan(file);
            return responseMessage;
        }catch (Exception e){
            logger.info(e.getMessage());
            return Result.error("-1","上传失败!");
        }
    }
    /**
     * 公告计划导出
     * 2019.12.25
     * lcx
     * @return
     */
    @ApiOperation(value = "申报项目批量导出")
    @BusinessLog(description = "申报项目批量导出")
    @GetMapping(path ="/checkBatchExport")
    @RequiresPermissions("pc_announcement:annPlan:checkBatchExport")
    public ResponseMessage checkBatchExport(HttpServletResponse response, HttpServletRequest request,
                                            @ApiParam(value = "id", required = true) @RequestParam(value = "id") String id) {
        try {
            List<AnnPlanProjectEO> annPlanProjectList = annPlanProjectEOService.getAnnPlanProjectList(id);
            if (annPlanProjectList.size() == 0){
                return Result.error("-1","导出失败，没有申报项目数据!");
            }else {
                annPlanEOService.checkBatchExport(response,request,id);
                return Result.success("0", "导出成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1","导出失败!");
        }
    }
    /**
     *生产委托单
     * 2019.12.26
     * lcx
     */
    @ApiOperation(value = "生产委托单")
    @BusinessLog(description = "生产委托单")
    @PostMapping(path ="/createDelegate")
    public ResponseMessage createDelegate(@RequestBody DelegateDataVO delegateDataVO){
        try {
            String[] idS = delegateDataVO.getIdS();
            if (idS.length == 0){
                return Result.error("-1","请选择申报项目!");
            }
            String delegate = annPlanProjectEOService.createDelegate(delegateDataVO);
            return Result.success("0", delegate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1","委托单生成失败!");
        }
    }

    @ApiOperation(value = "查看生成委托单记录")
    @GetMapping(path ="/getDelegate")
    public ResponseMessage getDelegate(@RequestParam(value = "annPlId") String annPlId) {
        try {
            annPlanAttachEOService.selectByPlId(annPlId);
            return Result.success("0","查询成功!",annPlanAttachEOService.selectByPlId(annPlId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

    //下载生成的委托单
    @ApiOperation(value = "|委托单下载")
    @BusinessLog(description = "委托单下载")
    @GetMapping("/downloadAnn/{fileId}")
    public void downloadAnn(HttpServletResponse response,
                            HttpServletRequest request,
                            @NotNull @PathVariable(value = "fileId") String fileId) {
        //文件下载
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            String fileName = "";
            AnnPlanAttachEO annPlanAttachEO = annPlanAttachEOService.selectByPrimaryKey(fileId);
            if (StringUtils.isNotEmpty(annPlanAttachEO)) {
                fileName = annPlanAttachEO.getAnnFileName();
            }
            String annRelativePath = annPlanAttachEO.getAnnRelativePath();
            String fileExtend = ConstantUtils.SPOT + annRelativePath.substring(annRelativePath.indexOf("."),annRelativePath.length());
            //浏览器下载
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            //火狐浏览器需特殊处理
            if (agent.contains(ConstantUtils.FIREFOX)) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + fileExtend);
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName) + fileExtend);
            }
            response.setContentType("application/octet-stream");
            is = this.iFileStore.loadFile(annRelativePath);
            os = response.getOutputStream();
            IOUtils.copy(is, os);
            os.flush();

        } catch (Exception var11) {
            logger.error(var11.getMessage(), var11);
            throw new AdcDaBaseException("下载文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }
    /**
     * 通过申报项目ids数组来计算各个项目的实际费用之和
     * @param delegateDataVO
     * @return
     */
    @ApiOperation(value = "|计算申报项目实际费用之和")
    @PostMapping("/getActualCost")
    public ResponseMessage getActualCost(@RequestBody DelegateDataVO delegateDataVO) {
        try {
            String[] idS = delegateDataVO.getIdS();
            if (idS.length == 0){
                return Result.error("-1", "请选择数据!");
            }
            Double actualCost = 0.0;
            for (String id : idS){
                AnnPlanProjectEO annPlanProjectEO = annPlanProjectEOService.selectByPrimaryKey(id);
                String sumActualCost = annPlanProjectEO.getSumActualCost();
                if (StringUtils.isEmpty(sumActualCost)){
                    continue;
                }
                actualCost += Double.valueOf(sumActualCost);
            }
            return Result.success("0", "查询成功!",actualCost);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

}
