package com.adc.da.standard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.standard.entity.StandardEO;
import com.adc.da.standard.page.StandardEOPage;
import com.adc.da.standard.service.StandardEOService;
import com.adc.da.standard.vo.StandardVO;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.IOUtils;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 基础数据模块--检验标准管理
 * @author ljy
 * @date 2019年7月15日
 */
@RestController
@RequestMapping("${restPath}/bm/std")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class StandardEOController extends BaseController<StandardEO>{
	/**
	 * 用户记录日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(StandardEOController.class);
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private StandardEOService stdEOService;
    
    @Autowired
    private IFileStore iFileStore;
    
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
    
    @Value("${file.path}")
    private String filepath;

    
    /**
        * 分页查询
    * @Title：page
    * @param standardVO 实体
    * @param pageNo 页码
    * @param pageSize 分页大小
    * @param searchPhrase查询条件
    * @param stdImplementDate1 实施时间
    * @param stdImplementDate2 实施时间
    * @return: ResponseMessage<PageInfo<StandardVO>>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验标准分页查询")
    @BusinessLog(description = "检验标准-查询列表")
    @GetMapping("/page")
    @RequiresPermissions("bm:std:page")
    public ResponseMessage<PageInfo<StandardVO>> page(
    		StandardEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<StandardEO> rows =  stdEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = stdEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), StandardVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    /**
     * 检验标准详情
    * @Title：getDetialById
    * @param id
    * @return
    * @return: ResponseMessage<StandardVO>
    * @author： ljy  
    * @date： 2019年7月18日
     */
    @ApiOperation(value = "|检验标准详情")
    @BusinessLog(description = "检验标准-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("bm:std:get")
    public ResponseMessage<StandardVO> getDetialById(@PathVariable(value = "id") String id) {
        try {
            return Result.success("0", "查询成功", beanMapper.map(stdEOService.selectByPrimaryKey(id), StandardVO.class));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
        }
    }
    
    
    /**
     * 保存
    * @Title：save
    * @param standardVO
    * @param file
    * @param filePath
    * @return
    * @return: ResponseMessage<StandardVO>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验标准保存")
    @BusinessLog(description = "检验标准-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("bm:std:save")
    public ResponseMessage<StandardVO> save(StandardVO standardVO,MultipartFile file){
    	try {
			//校验是否为空
    		if(StringUtils.isEmpty(standardVO.getStdName())) {
    			return Result.error("-1", "检验标准名称不可为空");
    		}
    		if(StringUtils.isEmpty(standardVO.getStdNo())) {
    			return Result.error("-1", "检验标准编号不可为空");
    		}
    		if(StringUtils.isEmpty(standardVO.getStdTypeId())) {
    			return Result.error("-1", "检验标准类型不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_PDF.equalsIgnoreCase(fileExtension)) {
        			return Result.error("-1", "检验标准只支持上传PDF格式的文件!");
        		}
    		}
    		//保存
    		stdEOService.save(beanMapper.map(standardVO, StandardEO.class), file);
    		return Result.success("0","新增成功!",standardVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
   
    /**
     * 	编辑
    * @Title：edit
    * @param standardVO
    * @param file
    * @param filePath
    * @return
    * @return: ResponseMessage<StandardVO>
    * @author： ljy  
    * @date： 2019年7月15日
     */
    @ApiOperation(value = "|检验标准编辑")
    @BusinessLog(description = "检验标准-编辑")
    @PostMapping(path = "/edit")
    @RequiresPermissions("bm:std:edit")
    public ResponseMessage<StandardVO> update(StandardVO standardVO,MultipartFile file){
    	try {
			//校验是否为空
    		if(StringUtils.isEmpty(standardVO.getStdName())) {
    			return Result.error("-1", "检验标准名称不可为空");
    		}
    		if(StringUtils.isEmpty(standardVO.getStdNo())) {
    			return Result.error("-1", "检验标准编号不可为空");
    		}
    		if(StringUtils.isEmpty(standardVO.getStdTypeId())) {
    			return Result.error("-1", "检验标准类型不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_PDF.equalsIgnoreCase(fileExtension)) {
    				return Result.error("-1", "检验标准只支持上传PDF格式的文件!");
        		}
    		}
    		
    	    //编辑
    		stdEOService.edit(beanMapper.map(standardVO, StandardEO.class), file);
    		return Result.success("0","编辑成功!",standardVO);
    		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    /**
     * 删除
    * @Title：delById
    * @param id
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月15日
     */
    
    @ApiOperation(value = "|检验标准删除")
    @BusinessLog(description = "检验标准-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("bm:std:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
			//检验标准删除
			stdEOService.deleteByPrimaryKey(id);
			return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    /**
     * 检验标准-校验标准号唯一性
     * @Title: checkNo
     * @param id
     * @param num
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年12月30日
     */
    @ApiOperation(value = "|检验标准校验标准号唯一性")
    @BusinessLog(description = "检验标准-校验标准号唯一性")
    @GetMapping("/checkNo")
    public ResponseMessage checkNo(String id, String num) {
    	try {
    		if(stdEOService.checkNo(id, num)) {
    			return Result.success("1", "该编码已存在!", num);
    		}else {
    			return Result.success("0", "校验通过!", num);
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","校验失败!");
		}
    }
    
    
    /**
     * 文件下载
    * @Title：download
    * @param response
    * @param stdFileId 附件id
    * @return: void
    * @author： ljy  
    * @date： 2019年7月16日
     */
    @ApiOperation(value = "|检验标准文件下载")
    @BusinessLog(description = "检验标准-下载")
    @GetMapping("/download/{stdFileId}")
    @RequiresPermissions("bm:std:download")
    public void download(HttpServletResponse response, 
    		HttpServletRequest request,
    		@NotNull @PathVariable(value = "stdFileId") String stdFileId) {
    	//文件下载
    	InputStream is = null;
        ServletOutputStream os = null;
    	try {
    		String fileName = "";
    		TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(stdFileId);
    		 if (StringUtils.isNotEmpty(tsAttachmentEO)) {
                 fileName = tsAttachmentEO.getAttachmentName();
             }
    	     String fileExtend = ConstantUtils.SPOT + tsAttachmentEO.getAttachmentType();
    		 //浏览器下载
	         String agent = request.getHeader("USER-AGENT").toLowerCase();
	         //火狐浏览器需特殊处理
	         if(agent.contains(ConstantUtils.FIREFOX)) {
	        	 response.setCharacterEncoding("utf-8");
	             response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + fileExtend);
	         }else {
	        	 response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName) + fileExtend);
	         }
             response.setContentType("application/octet-stream");
             is = this.iFileStore.loadFile(tsAttachmentEO.getSavePath());
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
     * 检验标准批量导入
    * @Title：stdBatchImport
    * @param file
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年7月19日
     */
    @ApiOperation(value = "|检验标准批量导入")
    @BusinessLog(description = "检验标准-批量导入")
    @PostMapping(path = "/batchImport")
    @RequiresPermissions("bm:std:batchImport")
    public ResponseMessage importStd(@RequestParam("file") MultipartFile file) {
    	try {
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension) ||
    			     !ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)) {
    				return Result.error("-1", "检验标准批量导入只支持上传Excel格式的文件!");
        		}
    		}
    		ResponseMessage result =  stdEOService.stdBatchImport(file);
			if(ConstantUtils.RETURN_SUCCESS.equals(result.getRespCode())) {
				//校验成功
				result = Result.success("0", "上传成功!");
			}else {
				result = Result.error("-1", "上传失败,第" + result.getMessage() 
								+ "行数据有问题,请修改后重新上传");
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","上传失败!");
		}
    }
    
    
    //文件预览
    @ApiOperation(value = "|检验标准文件预览")
    @BusinessLog(description = "检验标准-预览")
    @GetMapping("/preview/{stdFileId}")
    @RequiresPermissions("bm:std:preivew")
    public void preivew(HttpServletResponse response, @NotNull @PathVariable(value = "stdFileId") String stdFileId) {
    	//文件预览
    	InputStream is = null;
        ServletOutputStream os = null;
    	try {
    		 TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(stdFileId);
             response.setContentType("application/pdf");
             os = response.getOutputStream();
             //获取加水印后的文件
             File pdf = new File(filepath + tsAttachmentEO.getWaterMarkPath());
             byte[] buffer = new byte[1024*1024];
             response.setContentLength((int) pdf.length()); 
             FileInputStream fis = new FileInputStream(pdf);
             int readBytes = -1;  
             while((readBytes = fis.read(buffer, 0, 1024*1024)) != -1){  
                 os.write(buffer, 0, 1024*1024);  
             }  
             os.close();  
             fis.close();
             os.flush();
            
        } catch (Exception var11) {
            logger.error(var11.getMessage(), var11);
            throw new AdcDaBaseException("预览失败，请重试");
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }
}
