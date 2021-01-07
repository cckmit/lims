package com.adc.da.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/3 9:56
 * @description：${description}
 */
public class ExcelDataUtil {

    private ExcelDataUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDataUtil.class);

    public static int totalRows; // sheet中总行数
    public static int totalCells; // 每一行总单元格数

    public static List<ArrayList<String>> read(MultipartFile file) {
        try (Workbook wb = WorkbookFactory.create(file.getInputStream());) {
            List<ArrayList<String>> list = new ArrayList<>();

            // 读取sheet(页)
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                Sheet xssfSheet = wb.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                totalRows = xssfSheet.getLastRowNum();
                // 读取Row,从第 index+1 行开始
                for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                    Row xssfRow = xssfSheet.getRow(rowNum);
                    listAddCellValue(list, xssfRow,0);
                }
            }
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Map<String, Object> read(MultipartFile file, Integer index) {
        Map<String, Object> map = new HashMap<>();
        int rowNum = 0;
        try (Workbook wb = WorkbookFactory.create(file.getInputStream());) {
            List<ArrayList<String>> list = new ArrayList<>();
            //用来判断list集合是否有空值
            Boolean flagAll = false;
            // 读取sheet(页)
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                Sheet xssfSheet = wb.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                totalRows = xssfSheet.getLastRowNum();
                // 读取Row,从第 index+1 行开始

                //改取字段数量，因为经测试如若编辑的数据行中出现空值，此数量计算是错的导致add的list数量不够从而get报错
                Row coloum = xssfSheet.getRow(index-1);  //字段总数量
                int num = coloum.getLastCellNum();
                for (rowNum = index; rowNum <= totalRows; rowNum++) {
                    Row xssfRow = xssfSheet.getRow(rowNum);
                    listAddCellValue(list, xssfRow,num);
                }
            }

            //遍历判断上传的文件是否为空
            for1 : for(int i = 0;i < list.size();i++){
                List<String> stringList = list.get(i);
                for(int j = 0;j < stringList.size();j++){
                    if(stringList.get(j) != null && !stringList.get(i).equals("")){
                        flagAll = true;
                        break for1;
                    }
                }
            }

            if(flagAll){
                map.put("success", list);
            }else{
                map.put("nullAll", "上传的文件不能为空");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            map.put("failed", rowNum + 1);
        }
        return map;
    }

    private static void listAddCellValue(List<ArrayList<String>> list, Row xssfRow,int num) {
        if (xssfRow != null) {
            ArrayList<String> rowList = new ArrayList<>();
//            totalCells = xssfRow.getLastCellNum();      //改取字段数量，因为经测试如若编辑的数据行中出现空值，此数量计算是错的导致add的list数量不够从而get报错
            // 读取列，从第一列开始
            // c <= totalCells + 1
//           for (int c = 0; c < totalCells; c++) {
            for (int c = 0; c < num; c++) {
                Cell cell = xssfRow.getCell(c);
                if (cell == null) {
                    rowList.add("");
                    continue;
                }
                rowList.add(getCellValue(cell));
            }
            list.add(rowList);
        }
    }

    /**
     * readExcel 根据文件地址、行号、列号 获取对应单元格的值
     * filePath 源文件路径
     * outRow 要读取的行号
     * outLine 要读取的列号
     */
    public static String readExcel(String filePath, int outRow, int outLine) {
        try (
                FileInputStream inStream = new FileInputStream(new File(filePath));
                Workbook workBook = WorkbookFactory.create(inStream)
        ) {
            Sheet sheet = workBook.getSheetAt(1); // 获取第二个sheet页的数据
            Cell cell = sheet.getRow(outRow).getCell(outLine); // 获取对应行号、列号单元格数据
            return getCellValue(cell);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;

    }


    /**
     * 将获取单元格的值转换为String类型返回
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            // 判断单元格数据的类型，不同类型调用不同的方法
            switch (cell.getCellType()) {
                // 数值类型
                case Cell.CELL_TYPE_NUMERIC:
                    // 进一步判断 ，单元格格式是日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        // 数值
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                // 判断单元格是公式格式，需要做一种特殊处理来得到相应的值
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = getString(cell);
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

    public static String getString(Cell cell) {
        String cellValue;
        try {
            cellValue = String.valueOf(cell.getNumericCellValue());
        } catch (IllegalStateException e) {
            cellValue = String.valueOf(cell.getRichStringCellValue());
        }
        return cellValue;
    }
}
