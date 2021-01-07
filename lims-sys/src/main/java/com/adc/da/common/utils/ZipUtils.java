package com.adc.da.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/10/16 12:08
 * @description：zip
 */
public class ZipUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    // 目录标识判断符
    private static final String PATCH = "/";
    // 基目录
    private static final String BASE_DIR = "";
    // 缓冲区大小
    private static final int BUFFER = 2048;
    // 字符集
    private static final String CHAR_SET = "GBK";

    /**
     * 描述: 压缩
     *
     * @param srcFile
     * @param zipOutputStream
     * @param basePath
     * @throws Exception
     */
    public static void compress(File srcFile, ZipOutputStream zipOutputStream, String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            compressDir(srcFile, zipOutputStream, basePath);
        } else {
            compressFile(srcFile, zipOutputStream, basePath);
        }
    }

    /**
     * 描述:压缩目录下的所有文件
     *
     * @param dir
     * @param zipOutputStream
     * @param basePath
     * @throws Exception
     */
    private static void compressDir(File dir, ZipOutputStream zipOutputStream, String basePath) throws Exception {
        try {
            // 获取文件列表
            File[] files = dir.listFiles();

            if (files.length < 1) {
                ZipEntry zipEntry = new ZipEntry(basePath + dir.getName() + PATCH);

                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.closeEntry();
            }

            for (int i = 0, size = files.length; i < size; i++) {
                compress(files[i], zipOutputStream, basePath + dir.getName() + PATCH);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    /**
     * 描述:压缩文件
     *
     * @param file
     * @param zipOutputStream
     * @param dir
     * @throws Exception
     */
    private static void compressFile(File file, ZipOutputStream zipOutputStream, String dir) throws Exception {
        try {
            // 压缩文件
            ZipEntry zipEntry = new ZipEntry(dir + file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            // 读取文件
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            int count = 0;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                zipOutputStream.write(data, 0, count);
            }
            bis.close();
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                    new File("C:\\Users\\ethan\\Documents\\a.zip")));
            zipOutputStream.setEncoding(CHAR_SET);
            List<File> files = new ArrayList<>();
            files.add(new File("C:\\Users\\ethan\\Documents\\冯志伟发票.pdf"));
            files.add(new File("C:\\Users\\ethan\\Documents\\整车对接.docx"));

            if (CollectionUtils.isEmpty(files) == false) {
                for (int i = 0, size = files.size(); i < size; i++) {
                    compress(files.get(i), zipOutputStream, BASE_DIR);
                }
            }
            // 冲刷输出流
            zipOutputStream.flush();
            // 关闭输出流
            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
