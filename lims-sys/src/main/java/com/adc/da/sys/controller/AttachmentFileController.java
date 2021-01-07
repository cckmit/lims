package com.adc.da.sys.controller;

import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.IOUtils;
import com.adc.da.util.utils.RequestUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/${restPath}/util/file/")
@Api(tags = "COMMON-文件模块")
@SuppressWarnings("all")
public class AttachmentFileController {
    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AttachmentFileController.class);

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private IFileStore iFileStore;

    @Value("${file.path}")
    private String filepath;

    @Value("${imagePath}")
    private String imagePath;

    //文件预览
    @ApiOperation(value = "|PDF文件预览")
    @BusinessLog(description = "PDF文件预览")
    @GetMapping("/preview/{fileId}")
    //@RequiresPermissions("util:file:preivew")
    public void preivew(HttpServletResponse response, @NotNull @PathVariable(value = "fileId") String fileId) {
        //文件预览
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(fileId);
            response.setContentType("application/pdf");
            os = response.getOutputStream();
            //获取文件
            File pdf = new File(filepath + tsAttachmentEO.getWaterMarkPath());
            byte[] buffer = new byte[1024 * 1024];
            response.setContentLength((int) pdf.length());
            FileInputStream fis = new FileInputStream(pdf);
            int readBytes = -1;
            while ((readBytes = fis.read(buffer, 0, 1024 * 1024)) != -1) {
                os.write(buffer, 0, 1024 * 1024);
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

    //文件下载
    @ApiOperation(value = "|文件下载")
    @BusinessLog(description = "文件下载")
    @GetMapping("/download/{fileId}")
    //@RequiresPermissions("util:file:download")
    public void download(HttpServletResponse response,
                         HttpServletRequest request,
                         @NotNull @PathVariable(value = "fileId") String fileId) {
        //文件下载
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            String fileName = "";
            TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(fileId);
            if (StringUtils.isNotEmpty(tsAttachmentEO)) {
                fileName = tsAttachmentEO.getAttachmentName();
            }
            String fileExtend = ConstantUtils.SPOT + tsAttachmentEO.getAttachmentType();
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

    //文件下载
    @ApiOperation(value = "|图片下载")
    @BusinessLog(description = "图片下载")
    @GetMapping("/downloadImage/{fileId}")
    //@RequiresPermissions("util:file:download")
    public void downloadImage(HttpServletResponse response,
                         HttpServletRequest request,
                         @NotNull @PathVariable(value = "fileId") String fileId) {
        //文件下载
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            String fileName = "";
            TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(fileId);
            if (StringUtils.isNotEmpty(tsAttachmentEO)) {
                fileName = tsAttachmentEO.getAttachmentName();
            }
            String fileExtend = ConstantUtils.SPOT + tsAttachmentEO.getAttachmentType();
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
            is = this.loadImage(tsAttachmentEO.getSavePath());
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

    public InputStream loadImage(String path) {
        try {
            String fullPath = this.getImagePath() + path;
            InputStream is = new FileInputStream(new File(fullPath));
            return is;
        } catch (FileNotFoundException var4) {
            throw new AdcDaBaseException("文件[" + path + "]不存在");
        }
    }

    private String getImagePath() {
        if (!this.imagePath.endsWith("\\") && !this.imagePath.endsWith("/")) {
            this.imagePath = this.imagePath + File.separator;
        }
        return this.imagePath;
    }


    private static String generateImagePath(String fileType) {
        String path = DateUtils.dateToString(new Date(), "yyyyMMdd");
        return path + File.separator + UUID.randomUUID() + "." + fileType;
    }

    public String storeImage(InputStream is, String fileExtension, String filePath) throws IOException {
        StringBuilder path = new StringBuilder();
        if (StringUtils.isNotBlank(filePath)) {
            path.append(filePath).append(File.separator);
        }
        path.append(generateImagePath(fileExtension));
        String fullPath = this.getImagePath() + path;
        File file = new File(fullPath);
        FileUtil.checkAndMkdirs(file);
        FileUtil.copyInputStreamToFile(is, file);
        return path.toString();
    }

    //文件上传
    @ApiOperation(value = "|文件上传")
    @BusinessLog(description = "文件上传")
    @PostMapping("/upload")
    //@RequiresPermissions("util:file:upload")
    @EnableAccess
    public ResponseMessage upload(MultipartFile file) {
        try {
            //当前日期
            String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
            //文件后缀
            String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
            //文件大小
            long size = file.getSize() / 1024;
            String sizeStr = String.valueOf(size);
            //文件流
            InputStream is = file.getInputStream();
            //返回路径
            String path = "";
            if("jpg".equalsIgnoreCase(fileExtension) ||
            	"png".equalsIgnoreCase(fileExtension) ||
            	"jpeg".equalsIgnoreCase(fileExtension)) {
            	//返回路径
	            path = this.iFileStore.storeFile(is, fileExtension, imagePath);
            }else {
	            //返回路径
	            path = this.iFileStore.storeFile(is, fileExtension, "");
            }
            //保存附件
            TsAttachmentEO tsEo = new TsAttachmentEO();
            //设置UUID
            tsEo.setId(UUID.randomUUID());
            //删除标记  0 未删除;  1删除
            tsEo.setDelFlag(0);
            tsEo.setCreateTime(date);
            tsEo.setUpdateTime(date);
            //原附件名称
            tsEo.setAttachmentName(FileUtil.getFileName(file.getOriginalFilename()));
            //附件大小（kb）
            tsEo.setAttachmentSize(sizeStr);
            //附件类型（后缀）
            tsEo.setAttachmentType(fileExtension);
            //保存路径
            tsEo.setSavePath(path);
            //保存
            tsAttachmentEODao.insert(tsEo);
            return Result.success("0", "上传成功", tsEo.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "上传失败!");
        }

    }

    @ApiOperation(value = "|图片上传")
    @BusinessLog(description = "图片上传")
    @PostMapping("/uploadImage")
    //@RequiresPermissions("util:file:upload")
    public ResponseMessage uploadImage(MultipartFile file) {
        try {
            //当前日期
            String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
            //文件后缀
            String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
            //文件大小
            long size = file.getSize() / 1024;
            String sizeStr = String.valueOf(size);
            //文件流
            InputStream is = file.getInputStream();
            //返回路径
            String path = this.storeImage(is, fileExtension, "");
            //保存附件
            TsAttachmentEO tsEo = new TsAttachmentEO();
            //设置UUID
            tsEo.setId(UUID.randomUUID());
            //删除标记  0 未删除;  1删除
            tsEo.setDelFlag(0);
            tsEo.setCreateTime(date);
            tsEo.setUpdateTime(date);
            //原附件名称
            tsEo.setAttachmentName(FileUtil.getFileName(file.getOriginalFilename()));
            //附件大小（kb）
            tsEo.setAttachmentSize(sizeStr);
            //附件类型（后缀）
            tsEo.setAttachmentType(fileExtension);
            //保存路径
            tsEo.setSavePath(path);
            //保存
            tsAttachmentEODao.insert(tsEo);
            return Result.success("0", "上传成功", tsEo.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "上传失败!");
        }

    }
    
    @ApiOperation(value = "文件上传，返回附件信息")
    @BusinessLog(description = "文件上传")
    @PostMapping("/upload2")
    //@RequiresPermissions("util:file:upload")
    public ResponseMessage<TsAttachmentEO> upload2(MultipartFile file) {
    	try {
    		//当前日期
    		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
    		//文件后缀
    		String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    		//文件大小
    		long size = file.getSize() / 1024;
    		String sizeStr = String.valueOf(size);
    		//文件流
    		InputStream is = file.getInputStream();
    		//返回路径
    		String path = this.iFileStore.storeFile(is, fileExtension, "");
    		//保存附件
    		TsAttachmentEO tsEo = new TsAttachmentEO();
    		//设置UUID
    		tsEo.setId(UUID.randomUUID());
    		//删除标记  0 未删除;  1删除
    		tsEo.setDelFlag(0);
    		tsEo.setCreateTime(date);
    		tsEo.setUpdateTime(date);
    		//原附件名称
    		tsEo.setAttachmentName(FileUtil.getFileName(file.getOriginalFilename()));
    		//附件大小（kb）
    		tsEo.setAttachmentSize(sizeStr);
    		//附件类型（后缀）
    		tsEo.setAttachmentType(fileExtension);
    		//保存路径
    		tsEo.setSavePath(path);
    		//保存
    		tsAttachmentEODao.insert(tsEo);
    		return Result.success("0", "上传成功", tsEo);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return Result.error("-1", "上传失败!");
    	}
    	
    }

    /**
     * 检查文件存在与否
     */
    @PostMapping("checkFile")
    public Boolean checkFile(
            @ApiParam(value = "文件唯一标识") @RequestParam(value = "md5File") String md5File
    ) {
        Boolean exist = false;

        //todo 实际项目中，这个md5File唯一值，应该保存到数据库或者缓存中，通过判断唯一值存不存在，来判断文件存不存在
        // if(true) {
        // 	exist = true;
        // }
        return exist;
    }

    /**
     * 检查分片存不存在
     */
    @PostMapping("checkChunk")
    @ResponseBody
    public Boolean checkChunk(
            @ApiParam(value = "文件唯一标识") @RequestParam(value = "md5File") String md5File,
            @ApiParam(value = "分片号") @RequestParam(value = "chunk") Integer chunk
    ) {
        Boolean exist = false;
        String path = filepath + md5File + "\\";//分片存放目录
        String chunkName = chunk + ".tmp";//分片名
        File file = new File(path + chunkName);
        if (file.exists()) {
            exist = true;
        }
        return exist;
    }

    /**
     * 修改上传
     */
    @PostMapping("bigUpload")
    public Boolean bigUpload(
            @ApiParam(value = "文件") @RequestParam(value = "file") MultipartFile file,
            @ApiParam(value = "文件唯一标识") @RequestParam(value = "md5File") String md5File,
            @ApiParam(value = "分片号") @RequestParam(value = "chunk", required = false) Integer chunk
    ) {
        String path = filepath + md5File + "\\";
        File dirfile = new File(path);
        if (!dirfile.exists()) {//目录不存在，创建目录
            dirfile.mkdirs();
        }
        String chunkName;
        if (chunk == null) {//表示是小文件，还没有一片
            chunkName = "0.tmp";
        } else {
            chunkName = chunk + ".tmp";
        }
        String filePath = path + chunkName;
        File savefile = new File(filePath);

        try {
            if (!savefile.exists()) {
                savefile.createNewFile();//文件不存在，则创建
            }
            file.transferTo(savefile);//将文件保存
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 合成分片
     */
    @PostMapping("merge")
    public ResponseMessage merge(
            @ApiParam(value = "分片号") @RequestParam(value = "chunks", required = false) Integer chunks,
            @ApiParam(value = "文件唯一标识") @RequestParam(value = "md5File") String md5File,
            @ApiParam(value = "保存文件名称") @RequestParam(value = "name") String name,
            HttpServletRequest request
    ) throws Exception {
        StringBuilder path = new StringBuilder();
        path.append(DateUtils.dateToString(new Date(), "yyyy" + File.separator + "MM" + File.separator + "dd"))
                .append(name);
        String tempPath = this.filepath;
        if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
            tempPath = this.filepath + File.separator;
        }
        String fullPath = tempPath + path;
        File fullFile = new File(fullPath);
        FileUtil.checkAndMkdirs(fullFile);
        FileOutputStream fileOutputStream = new FileOutputStream(fullFile);  //合成后的文件

        // File saveFile = new File(filepath + name);
        // FileOutputStream fileOutputStream = new FileOutputStream(saveFile);  //合成后的文件
        //保存附件
        TsAttachmentEO tsEo = new TsAttachmentEO();
        try {
            byte[] buf = new byte[1024];
            for (long i = 0; i < chunks; i++) {
                String chunkFile = i + ".tmp";
                File file = new File(filepath + md5File + "\\" + chunkFile);
                InputStream inputStream = new FileInputStream(file);
                int len = 0;
                while ((len = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                }
                inputStream.close();
                // 删除分片
                file.delete();
            }
            // 删除md5目录
            File dir = new File(filepath + md5File);
            if (dir.exists() && dir.isDirectory()) {
                dir.delete();
            }
            //todo 业务逻辑，保存到attachment表中
            //设置UUID
            tsEo.setId(UUID.randomUUID());
            //删除标记  0 未删除;  1删除
            tsEo.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            //当前日期
            String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
            tsEo.setCreateTime(date);
            tsEo.setUpdateTime(date);
            String userId = (String) request.getSession().getAttribute(RequestUtils.LOGIN_USER_ID);
            tsEo.setCreateBy(userId);
            tsEo.setUpdateBy(userId);
            //原附件名称
            tsEo.setAttachmentName(FileUtil.getFileName(name));
            // 附件大小（kb）
            Long size = fullFile.length() / 1024;
            tsEo.setAttachmentSize(String.valueOf(size));
            //附件类型（后缀）
            tsEo.setAttachmentType(FileUtil.getFileExtension(name));
            //保存路径
            tsEo.setSavePath(path.toString());
            //保存
            tsAttachmentEODao.insert(tsEo);
        } catch (Exception e) {
            return Result.success("-1", "上传失败");
        } finally {
            fileOutputStream.close();
        }
        return Result.success("0", "上传成功", tsEo);
    }

}
