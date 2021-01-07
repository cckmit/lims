package com.adc.da.persondoc.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.IOUtils;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.persondoc.entity.PersondocEO;
import com.adc.da.persondoc.page.PersondocEOPage;
import com.adc.da.persondoc.service.PersondocEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/persondoc/persondoc")
@Api(tags = "PersonSpace-个人文档库")
public class PersondocEOController extends BaseController<PersondocEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersondocEOController.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PersondocEOService persondocEOService;

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @BusinessLog(description = "个人空间-个人文档库-分页查询")
	@ApiOperation(value = "|PersondocEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("persondoc:persondoc:page")
    public ResponseMessage<PageInfo<PersondocEO>> page(PersondocEOPage page, String searchfield) throws Exception {
	    try{
            List<PersondocEO> rows = persondocEOService.queryByPage(page, searchfield);
            page.getPager().setRowCount(persondocEOService.queryByCount(page));
            return Result.success("0","查询成功",getPageInfo(page.getPager(), rows));
        }catch(Exception e){
	        logger.error("-1",e.getMessage());
	        return Result.error("-1","查询失败");
        }
    }

    @BusinessLog(description = "个人空间-个人文档库-下载")
    @ApiOperation(value = "|PersondocEO|下载文档")
    @GetMapping("/download/{tsId}")
//    @RequiresPermissions("persondoc:persondoc:download")
    public void download(HttpServletResponse response, HttpServletRequest request, @PathVariable String tsId) throws IOException {
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(tsId);
            String fileName = tsAttachmentEO.getAttachmentName();
            //浏览器下载
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/octet-stream");
            //火狐浏览器需特殊处理
            if(agent.contains(ConstantUtils.FIREFOX)) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            }else {
                response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName));
            }
            is = this.iFileStore.loadFile(tsAttachmentEO.getSavePath());
            os = response.getOutputStream();
            IOUtils.copy(is, os);
            os.flush();
        }catch (IOException e){
            logger.error(e.getMessage(), e);
            throw new AdcDaBaseException("下载文件失败，请重试");
        }finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    @BusinessLog(description = "个人空间-个人文档库-新增")
    @ApiOperation(value = "|PersondocEO|新增")
    @PostMapping("")
//    @RequiresPermissions("persondoc:persondoc:save")
    public ResponseMessage<PersondocEO> create(@RequestParam("file") MultipartFile file, String docTypeId) throws Exception {
        PersondocEO persondocEO = new PersondocEO();
        try{
            //判断上传文件是否为空
            if(file.isEmpty()){
                return Result.error("-2","上传文件不能为空！");
            }
            //判断文档节点
            if(StringUtils.isEmpty(docTypeId)){
                return Result.error("-3","请选择所属节点！");
            }
            persondocEO.setDoctypeId(docTypeId);
            //保存文件
            persondocEOService.savefile(file, persondocEO);
        }catch(Exception e){
            logger.error("-1",e.getMessage());
            return Result.error("-1","新增文档失败！");
        }
        return Result.success("0","新增成功",persondocEO);
    }


    @BusinessLog(description = "个人空间-个人文档库-删除")
    @ApiOperation(value = "|PersondocEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("persondoc:persondoc:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        persondocEOService.deleteByPrimaryKey(id);
        logger.info("delete from TP_PERSONDOC where id = {}", id);
        return Result.success("0","删除成功");
    }

}
