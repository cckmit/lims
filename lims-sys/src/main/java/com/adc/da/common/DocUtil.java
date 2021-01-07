package com.adc.da.common;

import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

@Component
public class DocUtil {

    private Configuration configure = null;

    public DocUtil() {
        configure = new Configuration();
        configure.setDefaultEncoding(ConstantUtils.UTF8);
    }

    /**
     * 根据Doc模板生成word文件
     *
     * @param dataMap      Map 需要填入模板的数据
     * @param downloadType 文件名称
     * @param savePath     保存路径
     */
    public File createDoc(Map<String, Object> dataMap, String downloadType, String savePath) {
        File outFile = new File(savePath);
        try {
            FileUtil.checkAndMkdirs(outFile);
            //加载模板文件
            configure.setClassForTemplateLoading(DocUtil.class, "/templates/");
            //设置对象包装器
            configure.setObjectWrapper(new DefaultObjectWrapper());
            //设置异常处理器
            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //定义Template对象,注意模板类型名字与downloadType要一致
            //加载需要装填的模板
            Template template = configure.getTemplate(downloadType + ".ftl");
            //输出文档
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), ConstantUtils.UTF8));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outFile;
    }

    /**
     * 根据Doc模板导出word文件
     *
     * @param dataMap      Map 需要填入模板的数据
     * @param downloadType 文件名称
     * @param response
     * @param fileName     导出文件名称
     * @param fileExtend   导出文件后缀
     */
    public void createDoc(Map<String, Object> dataMap, String downloadType,
                          HttpServletResponse response, HttpServletRequest request, String fileName, String fileExtend) {
        try {
            //加载模板文件
            configure.setClassForTemplateLoading(DocUtil.class, "/templates/");
            //设置对象包装器
            configure.setObjectWrapper(new DefaultObjectWrapper());
            //设置异常处理器
            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //定义Template对象,注意模板类型名字与downloadType要一致
            //加载需要装填的模板
            Template template = configure.getTemplate(downloadType + ".ftl");
            //输出文档
            try {
                OutputStream output = response.getOutputStream();
                response.reset();
                response.setContentType("application/octet-stream");
                //浏览器下载
                String agent = request.getHeader("USER-AGENT").toLowerCase();
                //火狐浏览器需特殊处理
                if (agent.contains(ConstantUtils.FIREFOX)) {
                    response.setCharacterEncoding(ConstantUtils.UTF8);
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + fileExtend);
                } else {
                    response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName) + fileExtend);
                }
                Writer out = new BufferedWriter(new OutputStreamWriter(output, ConstantUtils.UTF8));
                template.process(dataMap, out);
                out.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
