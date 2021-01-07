package com.adc.da.common;

import com.adc.da.car.vo.SaCarDataVO;
import com.adc.da.car.vo.TrialtaskSampleVO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.vo.SaPartDataVO;
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

public class ExportDataUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportDataUtil.class);


    /**
     * 导出零部件信息
     *
     * @param saPartDataVOS
     * @param response
     */
    public static void exportPartData(List<SaPartDataVO> saPartDataVOS, HttpServletResponse response) {
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
        cell.setCellValue("零部件数据列表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 30));

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
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 30));
        //在sheet里创建第三行
        HSSFRow row3 = sheet.createRow(2);
        //创建单元格并设置单元格内容及样式
        HSSFCell cell0 = row3.createCell(0);
        cell0.setCellStyle(style);
        cell0.setCellValue("项目平台");
        HSSFCell cell1 = row3.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("样品名称");
        HSSFCell cell2 = row3.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("样件数量");
        HSSFCell cell3 = row3.createCell(3);
        cell3.setCellStyle(style);
        cell3.setCellValue("零部件号或发动机号");
        HSSFCell cell4 = row3.createCell(4);
        cell4.setCellStyle(style);
        cell4.setCellValue("生产厂家");
        HSSFCell cell5 = row3.createCell(5);
        cell5.setCellStyle(style);
        cell5.setCellValue("是否退样");
        HSSFCell cell6 = row3.createCell(6);
        cell6.setCellStyle(style);
        cell6.setCellValue("存放位置");
        HSSFCell cell7 = row3.createCell(7);
        cell7.setCellStyle(style);
        cell7.setCellValue("样车或发动机阶段");
        HSSFCell cell8 = row3.createCell(8);
        cell8.setCellStyle(style);
        cell8.setCellValue("送样人");
        HSSFCell cell9 = row3.createCell(9);
        cell9.setCellStyle(style);
        cell9.setCellValue("送样人电话");
        HSSFCell cell10 = row3.createCell(10);
        cell10.setCellStyle(style);
        cell10.setCellValue("送样人部门");
        HSSFCell cell11 = row3.createCell(11);
        cell11.setCellStyle(style);
        cell11.setCellValue("接收人");
        HSSFCell cell12 = row3.createCell(12);
        cell12.setCellStyle(style);
        cell12.setCellValue("接收日期");
        HSSFCell cell13 = row3.createCell(13);
        cell13.setCellStyle(style);
        cell13.setCellValue("创建人");
        HSSFCell cell14 = row3.createCell(14);
        cell14.setCellStyle(style);
        cell14.setCellValue("创建日期");
        HSSFCell cell15 = row3.createCell(15);
        cell15.setCellStyle(style);
        cell15.setCellValue("零部件状态");
        HSSFCell cell16 = row3.createCell(16);
        cell16.setCellStyle(style);
        cell16.setCellValue("试验类型");
        HSSFCell cell17 = row3.createCell(17);
        cell17.setCellStyle(style);
        cell17.setCellValue("样品所在地");
        HSSFCell cell18 = row3.createCell(18);
        cell18.setCellStyle(style);
        cell18.setCellValue("样品所在地外场管理责任人");
        HSSFCell cell19 = row3.createCell(19);
        cell19.setCellStyle(style);
        cell19.setCellValue("样品状态");
        HSSFCell cell20 = row3.createCell(20);
        cell20.setCellStyle(style);
        //检测委托合同编号→试验任务书编号
        cell20.setCellValue("试验任务书编号");
        HSSFCell cell21 = row3.createCell(21);
        cell21.setCellStyle(style);
        cell21.setCellValue("样品编号");

        //宽度自适应可自行选择自适应哪一行，这里写在前面的是适应第二行，写在后面的是适应第三行
        for (int i = 0; i < saPartDataVOS.size(); i++) {
            //从sheet第三行开始填充数据
            HSSFRow rowx = sheet.createRow(i + 3);
            //这里的hospitalid,idnumber等都是前面定义的全局变量
            // 项目平台
            if (saPartDataVOS.get(i).getBmProjectName() != null) {
                HSSFCell cell00 = rowx.createCell(0);
                cell00.setCellStyle(style);
                cell00.setCellValue(saPartDataVOS.get(i).getBmProjectName());
            }
            // 样品名称
            if (saPartDataVOS.get(i).getPartName() != null) {
                HSSFCell cell01 = rowx.createCell(1);
                cell01.setCellStyle(style);
                cell01.setCellValue(saPartDataVOS.get(i).getPartName());
            }
            // 样件数量
            if (saPartDataVOS.get(i).getPartSampleNumber() != null) {
                HSSFCell cell02 = rowx.createCell(2);
                cell02.setCellStyle(style);
                cell02.setCellValue(saPartDataVOS.get(i).getPartSampleNumber());
            }
            // 零部件号或发动机号
            if (saPartDataVOS.get(i).getPartEngineNo() != null) {
                HSSFCell cell03 = rowx.createCell(3);
                cell03.setCellStyle(style);
                cell03.setCellValue(saPartDataVOS.get(i).getPartEngineNo());
            }
            // 生产厂家
            if (saPartDataVOS.get(i).getProducedFactory() != null) {
                HSSFCell cell04 = rowx.createCell(4);
                cell04.setCellStyle(style);
                cell04.setCellValue(saPartDataVOS.get(i).getProducedFactory());
            }
            // 是否退样
            if (saPartDataVOS.get(i).getWhetherReturn() != null) {
                HSSFCell cell05 = rowx.createCell(5);
                cell05.setCellStyle(style);
                cell05.setCellValue(saPartDataVOS.get(i).getWhetherReturn());
            }
            // 存放位置
            if (saPartDataVOS.get(i).getPartSpaceLocation() != null) {
                HSSFCell cell06 = rowx.createCell(6);
                cell06.setCellStyle(style);
                cell06.setCellValue(saPartDataVOS.get(i).getPartSpaceLocation());
            }
            // 样车或发动机阶段
            if (saPartDataVOS.get(i).getPartStage() != null) {
                HSSFCell cell07 = rowx.createCell(7);
                cell07.setCellStyle(style);
                cell07.setCellValue(saPartDataVOS.get(i).getPartStageName());
                //cell07.setCellValue(saPartDataVOS.get(i).getPartStage());
            }
            // 送样人
            if (saPartDataVOS.get(i).getSendPartUserName() != null) {
                HSSFCell cell08 = rowx.createCell(8);
                cell08.setCellStyle(style);
                cell08.setCellValue(saPartDataVOS.get(i).getSendPartUserName());
            }
            // 送样人电话
            if (saPartDataVOS.get(i).getSendPartUserPhone() != null) {
                HSSFCell cell09 = rowx.createCell(9);
                cell09.setCellStyle(style);
                cell09.setCellValue(saPartDataVOS.get(i).getSendPartUserPhone());
            }
            // 送样人部门
            if (saPartDataVOS.get(i).getSendPartUserOrg() != null) {
                HSSFCell cell010 = rowx.createCell(10);
                cell010.setCellStyle(style);
                cell010.setCellValue(saPartDataVOS.get(i).getSendPartUserOrg());
            }
            // 接收人
            if (saPartDataVOS.get(i).getReceivedUserName() != null) {
                HSSFCell cell011 = rowx.createCell(11);
                cell011.setCellStyle(style);
                cell011.setCellValue(saPartDataVOS.get(i).getReceivedUserName());
            }
            // 接收日期
            if (saPartDataVOS.get(i).getReceivedTime() != null) {
                HSSFCell cell013 = rowx.createCell(12);
                cell013.setCellStyle(style);
                cell013.setCellValue(saPartDataVOS.get(i).getReceivedTime());
            }
            //创建人
            if (saPartDataVOS.get(i).getCreateByName() != null) {
                HSSFCell cell014 = rowx.createCell(13);
                cell014.setCellStyle(style);
                cell014.setCellValue(saPartDataVOS.get(i).getCreateByName());
            }
            // 创建日期
            if (saPartDataVOS.get(i).getCreateTime() != null) {
                HSSFCell cell015 = rowx.createCell(14);
                cell015.setCellStyle(style);
                cell015.setCellValue(saPartDataVOS.get(i).getCreateTime());
            }
            // 零部件状态
            if (saPartDataVOS.get(i).getPartStatus() != null) {
                HSSFCell cell016 = rowx.createCell(15);
                cell016.setCellStyle(style);
                cell016.setCellValue(saPartDataVOS.get(i).getPartStatusName());
                //cell016.setCellValue(saPartDataVOS.get(i).getPartStatus());
            }
            // 试验类型
            if (saPartDataVOS.get(i).getTrialType() != null) {
                HSSFCell cell017 = rowx.createCell(16);
                cell017.setCellStyle(style);
                cell017.setCellValue(saPartDataVOS.get(i).getTrialTypeName());
                //cell017.setCellValue(saPartDataVOS.get(i).getTrialType());
            }
            // 样品所在地
            if (saPartDataVOS.get(i).getPartLocation() != null) {
                HSSFCell cell018 = rowx.createCell(17);
                cell018.setCellStyle(style);
                cell018.setCellValue(saPartDataVOS.get(i).getPartLocation());
            }
            // 样品所在地外场管理责任人
            if (saPartDataVOS.get(i).getPartLocationManagerName() != null) {
                HSSFCell cell019 = rowx.createCell(18);
                cell019.setCellStyle(style);
                cell019.setCellValue(saPartDataVOS.get(i).getPartLocationManagerName());
            }
            //todo 样品状态
            if (saPartDataVOS.get(i).getStatus() != null) {
                HSSFCell cell020 = rowx.createCell(19);
                cell020.setCellStyle(style);
                cell020.setCellValue(saPartDataVOS.get(i).getStatusName());
                //cell020.setCellValue(saPartDataVOS.get(i).getStatus());
            }
            if (saPartDataVOS.get(i).getTrialTaskBookNO() != null) {
                // 试验任务书编号
                HSSFCell cell021 = rowx.createCell(20);
                cell021.setCellStyle(style);
                cell021.setCellValue(saPartDataVOS.get(i).getTrialTaskBookNO());
            }
           /* if (saPartDataVOS.get(i).getTrialApplyNO() != null) {
                // 检测委托合同编号
                HSSFCell cell021 = rowx.createCell(20);
                cell021.setCellStyle(style);
                cell021.setCellValue(saPartDataVOS.get(i).getTrialApplyNO());
            }*/
            if (saPartDataVOS.get(i).getPartNO() != null) {
                // 样品编号
                HSSFCell cell022 = rowx.createCell(21);
                cell022.setCellStyle(style);
                cell022.setCellValue(saPartDataVOS.get(i).getPartNO());
            }
        }
        //输出Excel文件
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + URLEncoder.encode("零部件信息.xls", "UTF-8"));//文件名这里可以改
            response.setContentType("application/ms-excel;");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }


    /**
     * 导出整车信息
     *
     * @param saCarDataVOS
     * @param response
     */
    public static void exportCarData(List<SaCarDataVO> saCarDataVOS, HttpServletResponse response) {
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
        cell.setCellValue("整车数据列表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 30));

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
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 30));
        //在sheet里创建第三行
        HSSFRow row3 = sheet.createRow(2);
        //创建单元格并设置单元格内容及样式
        HSSFCell cell0 = row3.createCell(0);
        cell0.setCellStyle(style);
        cell0.setCellValue("项目平台");
        HSSFCell cell1 = row3.createCell(1);
        cell1.setCellStyle(style);
        cell1.setCellValue("车辆名称");
        HSSFCell cell2 = row3.createCell(2);
        cell2.setCellStyle(style);
        cell2.setCellValue("VIN码");
        HSSFCell cell3 = row3.createCell(3);
        cell3.setCellStyle(style);
        cell3.setCellValue("车型");
        HSSFCell cell4 = row3.createCell(4);
        cell4.setCellStyle(style);
        cell4.setCellValue("发动机号");
        HSSFCell cell5 = row3.createCell(5);
        cell5.setCellStyle(style);
        cell5.setCellValue("发动机型号");
        HSSFCell cell6 = row3.createCell(6);
        cell6.setCellStyle(style);
        cell6.setCellValue("整备质量");
        HSSFCell cell7 = row3.createCell(7);
        cell7.setCellStyle(style);
        cell7.setCellValue("乘员数量");
        HSSFCell cell8 = row3.createCell(8);
        cell8.setCellStyle(style);
        cell8.setCellValue("里程表数（km）");
        HSSFCell cell9 = row3.createCell(9);
        cell9.setCellStyle(style);
        cell9.setCellValue("生产厂家");
        HSSFCell cell10 = row3.createCell(10);
        cell10.setCellStyle(style);
        cell10.setCellValue("生产日期");
        HSSFCell cell11 = row3.createCell(11);
        cell11.setCellStyle(style);
        cell11.setCellValue("存放位置");
        HSSFCell cell12 = row3.createCell(12);
        cell12.setCellStyle(style);
        cell12.setCellValue("是否借用");
        HSSFCell cell13 = row3.createCell(13);
        cell13.setCellStyle(style);
        cell13.setCellValue("检测委托合同编号");
        HSSFCell cell14 = row3.createCell(14);
        cell14.setCellStyle(style);
        cell14.setCellValue("送样人");
        HSSFCell cell15 = row3.createCell(15);
        cell15.setCellStyle(style);
        cell15.setCellValue("送样人电话");
        HSSFCell cell16 = row3.createCell(16);
        cell16.setCellStyle(style);
        cell16.setCellValue("送样人部门");
        HSSFCell cell17 = row3.createCell(17);
        cell17.setCellStyle(style);
        cell17.setCellValue("接收人");
        HSSFCell cell18 = row3.createCell(18);
        cell18.setCellStyle(style);
        cell18.setCellValue("接收日期");
        HSSFCell cell19 = row3.createCell(19);
        cell19.setCellStyle(style);
        cell19.setCellValue("创建人");
        HSSFCell cell20 = row3.createCell(20);
        cell20.setCellStyle(style);
        cell20.setCellValue("创建日期");
        HSSFCell cell21 = row3.createCell(21);
        cell21.setCellStyle(style);
        cell21.setCellValue("借用周期");
        HSSFCell cell22 = row3.createCell(22);
        cell22.setCellStyle(style);
        cell22.setCellValue("样车阶段");
        HSSFCell cell23 = row3.createCell(23);
        cell23.setCellStyle(style);
        cell23.setCellValue("零部件状态");
        HSSFCell cell24 = row3.createCell(24);
        cell24.setCellStyle(style);
        cell24.setCellValue("轮胎厂家");
        HSSFCell cell25 = row3.createCell(25);
        cell25.setCellStyle(style);
        cell25.setCellValue("轮胎型号");
        HSSFCell cell26 = row3.createCell(26);
        cell26.setCellStyle(style);
        cell26.setCellValue("驱动轮胎压");
        HSSFCell cell27 = row3.createCell(27);
        cell27.setCellStyle(style);
        cell27.setCellValue("样品编号");
        HSSFCell cell28 = row3.createCell(28);
        cell28.setCellStyle(style);
        cell28.setCellValue("样品所在地");
        HSSFCell cell29 = row3.createCell(29);
        cell29.setCellStyle(style);
        cell29.setCellValue("样品所在地外场管理责任人");
        HSSFCell cell30 = row3.createCell(30);
        cell30.setCellStyle(style);
        cell30.setCellValue("样品状态");

        //宽度自适应可自行选择自适应哪一行，这里写在前面的是适应第二行，写在后面的是适应第三行
        for (int i = 0; i < saCarDataVOS.size(); i++) {
            //从sheet第三行开始填充数据
            HSSFRow rowx = sheet.createRow(i + 3);
            // 项目平台
            if (saCarDataVOS.get(i).getBmProjectName() != null) {
                HSSFCell cell00 = rowx.createCell(0);
                cell00.setCellStyle(style);
                cell00.setCellValue(saCarDataVOS.get(i).getBmProjectName());
            }
            // 车辆名称
            if (saCarDataVOS.get(i).getCarName() != null) {
                HSSFCell cell01 = rowx.createCell(1);
                cell01.setCellStyle(style);
                cell01.setCellValue(saCarDataVOS.get(i).getCarName());
            }
            // vin
            if (saCarDataVOS.get(i).getCarVin() != null) {
                HSSFCell cell02 = rowx.createCell(2);
                cell02.setCellStyle(style);
                cell02.setCellValue(saCarDataVOS.get(i).getCarVin());
            }
            // 车型
            if (saCarDataVOS.get(i).getCarType() != null) {

                HSSFCell cell03 = rowx.createCell(3);
                cell03.setCellStyle(style);
                cell03.setCellValue(saCarDataVOS.get(i).getCarType());
            }
            // 发动机号
            if (saCarDataVOS.get(i).getCarEngineNo() != null) {
                HSSFCell cell04 = rowx.createCell(4);
                cell04.setCellStyle(style);
                cell04.setCellValue(saCarDataVOS.get(i).getCarEngineNo());
            }
            // 发动机型号
            if (saCarDataVOS.get(i).getCarEngineType() != null) {
                HSSFCell cell05 = rowx.createCell(5);
                cell05.setCellStyle(style);
                cell05.setCellValue(saCarDataVOS.get(i).getCarEngineType());
            }
            // 整备质量
            if (saCarDataVOS.get(i).getCarWeight() != null) {
                HSSFCell cell06 = rowx.createCell(6);
                cell06.setCellStyle(style);
                cell06.setCellValue(saCarDataVOS.get(i).getCarWeight());
            }
            // 乘员数量
            if (saCarDataVOS.get(i).getPassengerNumber() != null) {
                HSSFCell cell07 = rowx.createCell(7);
                cell07.setCellStyle(style);
                cell07.setCellValue(saCarDataVOS.get(i).getPassengerNumber());
            }
            // 里程数
            if (saCarDataVOS.get(i).getCarMilieage() != null) {
                HSSFCell cell08 = rowx.createCell(8);
                cell08.setCellStyle(style);
                cell08.setCellValue(saCarDataVOS.get(i).getCarMilieage());
            }
            // 生产厂家
            if (saCarDataVOS.get(i).getProducedFactory() != null) {
                HSSFCell cell09 = rowx.createCell(9);
                cell09.setCellStyle(style);
                cell09.setCellValue(saCarDataVOS.get(i).getProducedFactory());
            }
            // 生产时间
            if (saCarDataVOS.get(i).getProducedTime() != null) {
                HSSFCell cell010 = rowx.createCell(10);
                cell010.setCellStyle(style);
                cell010.setCellValue(saCarDataVOS.get(i).getProducedTime());
            }
            // 样品所在位置
            if (saCarDataVOS.get(i).getCarSpaceLocation() != null) {
                HSSFCell cell011 = rowx.createCell(11);
                cell011.setCellStyle(style);
                cell011.setCellValue(saCarDataVOS.get(i).getCarSpaceLocation());
            }
            // 是否借用
            if (saCarDataVOS.get(i).getWhetherUse() != null) {
                HSSFCell cell013 = rowx.createCell(12);
                cell013.setCellStyle(style);
                cell013.setCellValue(saCarDataVOS.get(i).getWhetherUse());
            }
            //检验委托合同编号
            List<TrialtaskSampleVO> vos = saCarDataVOS.get(i).getTrialtaskSampleVOS();
            if (vos != null && !vos.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int i1 = 0; i1 < vos.size(); i1++) {
                    if (i1 == vos.size() - 1) {
                        sb.append(vos.get(i1).getTrialApplyNO());
                    } else {
                        sb.append(vos.get(i1).getTrialApplyNO()).append(",");
                    }
                }
                HSSFCell cell014 = rowx.createCell(13);
                cell014.setCellStyle(style);
                cell014.setCellValue(sb.toString());
            }
            // 送样人
            if (saCarDataVOS.get(i).getSendCarUserName() != null) {
                HSSFCell cell015 = rowx.createCell(14);
                cell015.setCellStyle(style);
                cell015.setCellValue(saCarDataVOS.get(i).getSendCarUserName());
            }
            // 送样人电话
            if (saCarDataVOS.get(i).getSendCarUserPhone() != null) {
                HSSFCell cell016 = rowx.createCell(15);
                cell016.setCellStyle(style);
                cell016.setCellValue(saCarDataVOS.get(i).getSendCarUserPhone());
            }
            // 送样人部门
            if (saCarDataVOS.get(i).getSendCarUserOrg() != null) {
                HSSFCell cell017 = rowx.createCell(16);
                cell017.setCellStyle(style);
                cell017.setCellValue(saCarDataVOS.get(i).getSendCarUserOrg());
            }
            // 接收人
            if (saCarDataVOS.get(i).getReceivedUserName() != null) {
                HSSFCell cell018 = rowx.createCell(17);
                cell018.setCellStyle(style);
                cell018.setCellValue(saCarDataVOS.get(i).getReceivedUserName());
            }
            // 接收时间
            if (saCarDataVOS.get(i).getReceivedTime() != null) {
                HSSFCell cell019 = rowx.createCell(18);
                cell019.setCellStyle(style);
                cell019.setCellValue(saCarDataVOS.get(i).getReceivedTime());
            }
            // 创建人
            if (saCarDataVOS.get(i).getCreateName() != null) {
                HSSFCell cell020 = rowx.createCell(19);
                cell020.setCellStyle(style);
                cell020.setCellValue(saCarDataVOS.get(i).getCreateName());
            }
            // 创建时间
            if (saCarDataVOS.get(i).getCreateTime() != null) {
                HSSFCell cell021 = rowx.createCell(20);
                cell021.setCellStyle(style);
                cell021.setCellValue(saCarDataVOS.get(i).getCreateTime());
            }
            // 借用周期
            if (saCarDataVOS.get(i).getUseCycle() != null) {
                HSSFCell cell022 = rowx.createCell(21);
                cell022.setCellStyle(style);
                cell022.setCellValue(saCarDataVOS.get(i).getUseCycle());
            }
            // 样车阶段
            if (saCarDataVOS.get(i).getCarStage() != null) {
                HSSFCell cell023 = rowx.createCell(22);
                cell023.setCellStyle(style);
                cell023.setCellValue(saCarDataVOS.get(i).getCarStage());
            }
            // 零部件状态
            if (saCarDataVOS.get(i).getPartStatus() != null) {
                HSSFCell cell024 = rowx.createCell(23);
                cell024.setCellStyle(style);
                cell024.setCellValue(saCarDataVOS.get(i).getPartStatus());
            }
            // 轮胎厂家
            if (saCarDataVOS.get(i).getTyreFactory() != null) {
                HSSFCell cell025 = rowx.createCell(24);
                cell025.setCellStyle(style);
                cell025.setCellValue(saCarDataVOS.get(i).getTyreFactory());
            }
            // 轮胎型号
            if (saCarDataVOS.get(i).getTyreType() != null) {
                HSSFCell cell026 = rowx.createCell(25);
                cell026.setCellStyle(style);
                cell026.setCellValue(saCarDataVOS.get(i).getTyreType());
            }
            // 驱动轮胎压
            if (saCarDataVOS.get(i).getTyrePressure() != null) {
                HSSFCell cell027 = rowx.createCell(26);
                cell027.setCellStyle(style);
                cell027.setCellValue(saCarDataVOS.get(i).getTyrePressure());
            }
            // 样品编号
            if (vos != null && !vos.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int i1 = 0; i1 < vos.size(); i1++) {
                    if (i1 == vos.size() - 1) {
                        sb.append(vos.get(i1).getCarNO());
                    } else {
                        sb.append(vos.get(i1).getCarNO()).append(",");
                    }
                }
                HSSFCell cell028 = rowx.createCell(27);
                cell028.setCellStyle(style);
                cell028.setCellValue(sb.toString());
            }
            // 样品所在地
            if (saCarDataVOS.get(i).getCarLocation() != null) {
                HSSFCell cell029 = rowx.createCell(28);
                cell029.setCellStyle(style);
                cell029.setCellValue(saCarDataVOS.get(i).getCarLocation());
            }
            // 样品所在地外场管理责任人
            if (saCarDataVOS.get(i).getCarLocationManagerName() != null) {
                HSSFCell cell030 = rowx.createCell(29);
                cell030.setCellStyle(style);
                cell030.setCellValue(saCarDataVOS.get(i).getCarLocationManagerName());
            }
            // 样品状态
            if (saCarDataVOS.get(i).getCarStatus() != null) {
                HSSFCell cell031 = rowx.createCell(30);
                cell031.setCellStyle(style);
                cell031.setCellValue(saCarDataVOS.get(i).getCarStatus());
            }
        }
        //输出Excel文件
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + URLEncoder.encode("整车信息.xls", "UTF-8"));//文件名这里可以改
            response.setContentType("application/ms-excel;");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
