package com.adc.da.calender.util;

import com.adc.da.calender.entity.PersonCalenderEO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static void excelFile(List<PersonCalenderEO> list, HttpServletResponse response, HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("Content-disposition");
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String fileName = "工作日报表.xls";
        if ((userAgent.contains("msie") || userAgent.contains("like gecko")) && userAgent.indexOf("khtml") < 0) {
            try {
                response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            try {
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("工作日报表");
        sheet.setDefaultRowHeightInPoints(25);//设置缺省列高
        sheet.setDefaultColumnWidth(40);//设置缺省列宽

        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setWrapText(true);

        HSSFCellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setWrapText(true);

        HSSFRow row = sheet.createRow(0);
        setCellStyle(0, 4, row, headStyle, "工作日报表");
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(range);
        setCaneder(sheet, headStyle);
        setFormEntity(2, list, style, sheet);
        OutputStream output;
        try {
            output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    private static void setCellStyle(int start, int end, HSSFRow row, HSSFCellStyle headStyle, String str) {
        HSSFCell cell = null;
        for (int j = start; j <= end; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(headStyle);
            cell.setCellValue(str);
        }
    }

    //导出工作日报的标题
    private static void setCaneder(HSSFSheet sheet, HSSFCellStyle headStyle) {
        HSSFRow row2 = sheet.createRow(1);
        setCellStyle(0, 0, row2, headStyle, "事件标题");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 0));
        setCellStyle(1, 1, row2, headStyle, "开始时间");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 1));
        setCellStyle(2, 2, row2, headStyle, "结束时间");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 2));
        setCellStyle(3, 4, row2, headStyle, "事件内容");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
    }

    //工作日报的内容
    private static int setFormEntity(int rownum, List<PersonCalenderEO> formList, HSSFCellStyle style,
                              HSSFSheet sheet) {
        if (formList != null && !formList.isEmpty()) {
            for (int i = 0; i < formList.size(); i++) {
                HSSFRow row3 = sheet.createRow(rownum);

                setCellStyle(0, 0, row3, style, formList.get(i).getTitle());
                sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 0));
                if (formList.get(i).getStarttime() != null) {
                    String startdate = formList.get(i).getStarttime();
                    setCellStyle(1, 1, row3, style, startdate);
                } else {
                    setCellStyle(1, 1, row3, style, "");
                }
                sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 1, 1));
                if (formList.get(i).getEndtime() != null) {
                    String enddate = formList.get(i).getEndtime();
                    setCellStyle(2, 2, row3, style, enddate);
                } else {
                    setCellStyle(2, 2, row3, style, "");
                }
                sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 2, 2));
                setCellStyle(3, 4, row3, style, formList.get(i).getContent());
                sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 3, 4));
                rownum++;
            }

        }
        return rownum;

    }

}
