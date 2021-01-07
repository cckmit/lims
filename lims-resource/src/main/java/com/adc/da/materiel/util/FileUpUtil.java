package com.adc.da.materiel.util;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.common.ConstantUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;

public class FileUpUtil {

    private FileUpUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 读取Excel
     * @Title：getWorkbook
     * @param file
     * @return
     * @throws IOException
     * @return: Workbook
     * @author： ljy
     * @date： 2019年7月19日
     */
    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        //文件后缀
        String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
        //创建要读入的文件的输入流
        InputStream fis = file.getInputStream();
        // 创建其对象，就打开这个 Excel 文件
        Workbook workbook = null;
        //区别Excel 版本
        if (ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)) {
            workbook = new XSSFWorkbook(fis);
        }else{
            workbook = new HSSFWorkbook(fis);
        }
        // 输入流使用后，及时关闭！这是文件流操作中极好的一个习惯！
        fis.close();
        return workbook;
    }


    /**
     * 校验Excel数据不能为空
     */
    @SuppressWarnings("all")
    public static ResponseMessage checkExcelData(Sheet sheet) {
        ResponseMessage result = new ResponseMessage();
        //错误信息
        List<Integer> errormMsgList = new ArrayList<>();
        //获得当前sheet的开始行
        int firstRowNum  = sheet.getFirstRowNum();
        //获得当前sheet的结束行
        int lastRowNum = sheet.getLastRowNum();
        for (int rowNum = firstRowNum + 1;rowNum <= lastRowNum; rowNum++) {
            //获得当前行
            Row row = sheet.getRow(rowNum);
            if (row.getRowNum() < 1) {
                continue;
            }
            //获得当前行的开始列
            int firstCellNum = row.getFirstCellNum();
            //获得当前行的列数
            int lastCellNum = row.getPhysicalNumberOfCells();
            //循环当前行,校验数据完整性,如果有空值, 将返回,重新上传
            Cell cell = null;
            for(int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++){
                cell = row.getCell(cellNum);
                if(StringUtils.isEmpty(FileUpUtil.getCellValue(cell))){
                    errormMsgList.add(rowNum);
                    break;
                }
            }
        }
        //返回错误信息
        if(!errormMsgList.isEmpty()) {
            result.setMessage(StringUtils.join(errormMsgList, ConstantUtils.COMMA));
            result.setRespCode(ConstantUtils.RETURN_ERROR);
        }else {
            result.setRespCode(ConstantUtils.RETURN_SUCCESS);
        }
        return result;
    }

    /**
     * 获取Excel单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            if (!DateUtil.isCellDateFormatted(cell)) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 用于转化为日期格式
                    Date d = cell.getDateCellValue();
                    DateFormat formater = new SimpleDateFormat(ConstantUtils.DATE_FORMAT);
                    String timeStr = formater.format(d);
                    cellValue = timeStr;
                }else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


}

