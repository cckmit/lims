package com.adc.da.common.utils;

import com.adc.da.common.constant.TaskStatusEnum;
import com.adc.da.login.util.UserUtils;
import com.adc.da.task.vo.SupplierAttachmentVO;
import com.adc.da.task.vo.SupplierTaskStatisticsVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/3 10:03
 * @description：${description}
 */
public class ExportDataUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportDataUtil.class);

    /**
     * |外包供应商|任务统计导出
     *
     * @param taskApplyListVOList
     * @param response
     */
    public static void exportStatisticsData(List<SupplierTaskStatisticsVO> taskApplyListVOList,
                                            HttpServletResponse response) {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("导出信息");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        // 1.生成字体对象
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("新宋体");
        // 2.生成样式对象，这里的设置居中样式和版本有关，我用的poi用HSSFCellStyle.ALIGN_CENTER会报错，所以用下面的
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置居中样式
        style.setFont(font); // 调用字体样式对象
        style.setWrapText(true);
        // style.setAlignment(HorizontalAlignment.CENTER);//设置居中样式
        // 3.单元格应用样式
        cell.setCellStyle(style);
        //设置单元格内容
        cell.setCellValue("任务统计数据列表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row2 = sheet.createRow(1);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cellr2c1 = row2.createCell(0);
        // 1.生成字体对象
        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 12);
        font2.setFontName("新宋体");
        // 2.生成样式对象，这里的设置居中样式和版本有关，我用的poi用HSSFCellStyle.ALIGN_CENTER会报错，所以用下面的
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style2.setFont(font2); // 调用字体样式对象
        style2.setWrapText(true);
        // 3.单元格应用样式
        cellr2c1.setCellStyle(style2);
        //设置单元格内容
        try {
            cellr2c1.setCellValue("导出人：" + UserUtils.getUser().getUsname());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 13));
        //在sheet里创建第三行
        HSSFRow row3 = sheet.createRow(2);
        //创建单元格并设置单元格内容及样式
        HSSFCell cell0 = row3.createCell(0);
        cell0.setCellStyle(style);
        cell0.setCellValue("委托单编号");
        HSSFCell cell1 = row3.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("供应商名称");
        HSSFCell cell2 = row3.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("工作内容");
        HSSFCell cell3 = row3.createCell(3);
        cell3.setCellStyle(style);
        cell3.setCellValue("标准人数");
        HSSFCell cell4 = row3.createCell(4);
        cell4.setCellStyle(style);
        cell4.setCellValue("实际人数");
        HSSFCell cell5 = row3.createCell(5);
        cell5.setCellStyle(style);
        cell5.setCellValue("标准工时");
        HSSFCell cell6 = row3.createCell(6);
        cell6.setCellStyle(style);
        cell6.setCellValue("实际工时");
        HSSFCell cell7 = row3.createCell(7);
        cell7.setCellStyle(style);
        cell7.setCellValue("要求输出物");
        HSSFCell cell8 = row3.createCell(8);
        cell8.setCellStyle(style);
        cell8.setCellValue("任务负责人员");
        HSSFCell cell9 = row3.createCell(9);
        cell9.setCellStyle(style);
        cell9.setCellValue("车辆型号");
        HSSFCell cell10 = row3.createCell(10);
        cell10.setCellStyle(style);
        cell10.setCellValue("底盘号");
        HSSFCell cell11 = row3.createCell(11);
        cell11.setCellStyle(style);
        cell11.setCellValue("任务状态");
        HSSFCell cell12 = row3.createCell(12);
        cell12.setCellStyle(style);
        cell12.setCellValue("完成凭证");
        HSSFCell cell13 = row3.createCell(13);
        cell13.setCellStyle(style);
        cell13.setCellValue("完成时间");

        //宽度自适应可自行选择自适应哪一行，这里写在前面的是适应第二行，写在后面的是适应第三行
        for (int i = 0; i < taskApplyListVOList.size(); i++) {
            //从sheet第三行开始填充数据
            HSSFRow rowx = sheet.createRow(i + 3);
            //这里的hospitalid,idnumber等都是前面定义的全局变量
            // 委托单编号
            if (taskApplyListVOList.get(i).getApplyNo() != null) {
                HSSFCell cell00 = rowx.createCell(0);
                cell00.setCellStyle(style);
                cell00.setCellValue(taskApplyListVOList.get(i).getApplyNo());
            }
            // 供应商名称
            if (taskApplyListVOList.get(i).getSupName() != null) {
                HSSFCell cell01 = rowx.createCell(1);
                cell01.setCellStyle(style);
                cell01.setCellValue(taskApplyListVOList.get(i).getSupName());
            }
            // 工作内容
            if (taskApplyListVOList.get(i).getJobContent() != null) {
                HSSFCell cell02 = rowx.createCell(2);
                cell02.setCellStyle(style);
                cell02.setCellValue(taskApplyListVOList.get(i).getJobContent());
            }
            // 标准人数
            if (taskApplyListVOList.get(i).getPeopleNumber() != null) {
                HSSFCell cell03 = rowx.createCell(3);
                cell03.setCellStyle(style);
                cell03.setCellValue(taskApplyListVOList.get(i).getPeopleNumber());
            }
            // 实际人数
            if (taskApplyListVOList.get(i).getRealPeopleNumber() != null) {
                HSSFCell cell04 = rowx.createCell(4);
                cell04.setCellStyle(style);
                cell04.setCellValue(taskApplyListVOList.get(i).getRealPeopleNumber());
            }
            // 标准工时
            if (taskApplyListVOList.get(i).getWorkHour() != null) {
                HSSFCell cell05 = rowx.createCell(5);
                cell05.setCellStyle(style);
                cell05.setCellValue(taskApplyListVOList.get(i).getWorkHour());
            }
            // 实际工时
            if (taskApplyListVOList.get(i).getRealWorkHour() != null) {
                HSSFCell cell06 = rowx.createCell(6);
                cell06.setCellStyle(style);
                cell06.setCellValue(taskApplyListVOList.get(i).getRealWorkHour());
            }
            // 要求输出物
            if (taskApplyListVOList.get(i).getOutputContent() != null) {
                HSSFCell cell07 = rowx.createCell(7);
                cell07.setCellStyle(style);
                cell07.setCellValue(taskApplyListVOList.get(i).getOutputContent());
            }
            // 任务负责人员
            if (taskApplyListVOList.get(i).getUsName() != null) {
                HSSFCell cell08 = rowx.createCell(8);
                cell08.setCellStyle(style);
                cell08.setCellValue(taskApplyListVOList.get(i).getUsName());
            }
            // 车辆型号
            if (taskApplyListVOList.get(i).getCarType() != null) {
                HSSFCell cell09 = rowx.createCell(9);
                cell09.setCellStyle(style);
                cell09.setCellValue(taskApplyListVOList.get(i).getCarType());
            }
            // 底盘号
            if (taskApplyListVOList.get(i).getUnderpanNO() != null) {
                HSSFCell cell010 = rowx.createCell(10);
                cell010.setCellStyle(style);
                cell010.setCellValue(taskApplyListVOList.get(i).getUnderpanNO());
            }
            // 任务状态
            if (taskApplyListVOList.get(i).getTaskStatus() != null) {
                HSSFCell cell011 = rowx.createCell(11);
                cell011.setCellStyle(style);
                cell011.setCellValue(TaskStatusEnum.getName(taskApplyListVOList.get(i).getTaskStatus()));
            }
            // 完成凭证
            List<SupplierAttachmentVO> supplierAttachmentVO = taskApplyListVOList.get(i).getSupplierAttachmentVO();
            if (supplierAttachmentVO != null && !supplierAttachmentVO.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int i1 = 0; i1 < supplierAttachmentVO.size(); i1++) {
                    if (i1 == supplierAttachmentVO.size() - 1) {
                        sb.append(supplierAttachmentVO.get(i1).getAttachmentName());
                    } else {
                        sb.append(supplierAttachmentVO.get(i1).getAttachmentName()).append(",");
                    }
                }
                HSSFCell cell013 = rowx.createCell(12);
                cell013.setCellStyle(style);
                cell013.setCellValue(sb.toString());
            }
            //完成时间
            if (taskApplyListVOList.get(i).getTaskEndTime() != null) {
                HSSFCell cell014 = rowx.createCell(13);
                cell014.setCellStyle(style);
                cell014.setCellValue(taskApplyListVOList.get(i).getTaskEndTime());
            }
        }
        //输出Excel文件
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + URLEncoder.encode("任务统计信息.xls", "UTF-8"));//文件名这里可以改
            response.setContentType("application/ms-excel;");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
