package com.adc.da.trial_execute.service;

import com.adc.da.common.ConstantUtils;
import com.adc.da.trial_execute.page.TrialCostEOPage;
import com.adc.da.util.utils.Encodes;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.trial_execute.dao.TrialCostEODao;
import com.adc.da.trial_execute.entity.TrialCostEO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * <b>功能：</b>EV_TRIAL_COST TrialCostEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("trialCostEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialCostEOService extends BaseService<TrialCostEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialCostEOService.class);

    @Autowired
    private TrialCostEODao dao;

    public TrialCostEODao getDao() {
        return dao;
    }

    /**
     * 总数
     *
     * @param page
     * @return
     */
    public int queryByVOCount(TrialCostEOPage page) {
        return dao.queryByVOCount(page);
    }

    /**
     * fenye
     *
     * @param page
     * @return
     */
    public List<TrialCostEO> queryByVOPage(TrialCostEOPage page) {
        return dao.queryByVOPage(page);
    }

    /**
     * 设置单元格value和样式
     *
     * @param cell
     * @param value
     * @param style
     */
    public void setValueAndStyle(XSSFCell cell, String value, XSSFCellStyle style) {
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setWrapText(true);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    /**
     * 封装表头list
     *
     * @return
     */
    public List<String> AddAllTitle() {
        List<String> list = new ArrayList();
        list.add("序号");
        list.add("记录人");
        list.add("记录日期");
        list.add("任务书编号");
        list.add("任务书内容");
        list.add("上下台架");
        list.add("保养");
        list.add("台架设备运行");
        list.add("工程师");
        list.add("技师");
        list.add("K型热电偶");
        list.add("PT100");
        list.add("汽油费用");
        list.add("水");
        list.add("电");
        list.add("冷却液");
        list.add("机油");
        list.add("管理费");
        list.add("发动机拆装");
        list.add("发动机");
        list.add("线束");
        list.add("ECU费用");
        list.add("工装");
        list.add("试验报告费用");
        list.add("物流费用");
        return list;
    }

    /**
     * 根据实体给每一个单元格赋值
     *
     * @param i
     * @param trialCostEO
     * @param row
     * @param style
     */
    public void setAllCells(int i, TrialCostEO trialCostEO, XSSFRow row, XSSFCellStyle style, XSSFCell cell) {
        cell = row.createCell(0);//序号
        setValueAndStyle(cell, i + "", style);
        cell = row.createCell(1);//人员
        setValueAndStyle(cell, trialCostEO.getCreateByName(),style);
        cell = row.createCell(2);//时间
        setValueAndStyle(cell, trialCostEO.getCreateTime(),style);
        cell = row.createCell(3);//任务书编号
        setValueAndStyle(cell, trialCostEO.getEvNumber(),style);
        cell = row.createCell(4);//标题
        setValueAndStyle(cell, trialCostEO.getTitle(),style);
        cell = row.createCell(5);//上下架费用
        setValueAndStyle(cell, trialCostEO.getScaffoldingCost(),style);
        cell = row.createCell(6);    // 保养 **/
        setValueAndStyle(cell, trialCostEO.getUpkeepCost(),style);
        cell = row.createCell(7);// 泰加设备运行 **/
        setValueAndStyle(cell, trialCostEO.getScaffoldingRunCost(),style);
        cell = row.createCell(8);//工程师费用
        setValueAndStyle(cell, trialCostEO.getEngineerCost(),style);
        cell = row.createCell(9);//技师费用
        setValueAndStyle(cell, trialCostEO.getTechnicianCost(),style);
        cell = row.createCell(10);//k型热电偶费用
        setValueAndStyle(cell, trialCostEO.getKThermocoupleCost(),style);
        cell = row.createCell(11);//PT费用
        setValueAndStyle(cell, trialCostEO.getPtCost(),style);
        cell = row.createCell(12);//汽油费用
        setValueAndStyle(cell, trialCostEO.getGasolineCost(),style);
        cell = row.createCell(13);//水费
        setValueAndStyle(cell, trialCostEO.getWaterCost(),style);
        cell = row.createCell(14);//电费
        setValueAndStyle(cell, trialCostEO.getElectricityCost(),style);
        cell = row.createCell(15);// 冷却费用
        setValueAndStyle(cell, trialCostEO.getCoolantCost(),style);
        cell = row.createCell(16);//机油费用
        setValueAndStyle(cell, trialCostEO.getMotoroilCost(),style);
        cell = row.createCell(17);//管理费
        setValueAndStyle(cell, trialCostEO.getManagementCost(),style);
        cell = row.createCell(18);//发动机拆装费
        setValueAndStyle(cell, trialCostEO.getEngineDestuffingCost(),style);
        cell = row.createCell(19);//发动机费用
        setValueAndStyle(cell, trialCostEO.getEngineCost(),style);
        cell = row.createCell(20);//线束费用
        setValueAndStyle(cell, trialCostEO.getWickingCost(),style);
        cell = row.createCell(21);//ECU费用
        setValueAndStyle(cell, trialCostEO.getEcvCost(),style);
        cell = row.createCell(22);//工装费用
        setValueAndStyle(cell, trialCostEO.getFrockCost(),style);
        cell = row.createCell(23);// 试验报告费用
        setValueAndStyle(cell, trialCostEO.getReportCost(),style);
        cell = row.createCell(24);//物流费用
        setValueAndStyle(cell, trialCostEO.getLogisticsCost(),style);
    }

    /**
     * 导出
     *
     * @param trialCostEOPage
     */
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, TrialCostEOPage trialCostEOPage) throws Exception {
        //根据导出条件获取数据
        List<TrialCostEO> trialCostEOList = dao.queryByVOList(trialCostEOPage);
        String fileName = "费用统计.xlsx";
        //表头list
        List<String> titleList = AddAllTitle();
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("费用统计");
            XSSFRow row = null;
            //样式
            XSSFCellStyle style = wb.createCellStyle();
            //单元格
            XSSFCell cell = null;
            CellRangeAddress region;
            //写入表头
            //县创建第二行，避免边框被覆盖
            row = sheet.createRow(1);
            for(int i=8; i<=11; i++){
                region = new CellRangeAddress(1, 1, i, i);
                sheet.addMergedRegion(region);
                cell = row.createCell(i);
                setValueAndStyle(cell, titleList.get(i), style);
            }
            //创建第一行
            row = sheet.createRow(0);
            for (int i = 0; i < titleList.size(); i++) {
                if(i<8 || i>11) {
                     region = new CellRangeAddress(0, 1, i, i);
                    sheet.addMergedRegion(region);
                    RegionUtil.setBorderBottom(1, region, sheet,wb); // 下边框
                    RegionUtil.setBorderLeft(1, region, sheet,wb); // 左边框
                    RegionUtil.setBorderRight(1, region, sheet,wb); // 有边框
                    RegionUtil.setBorderTop(1, region, sheet,wb); // 上边框
                    cell = row.createCell(i);
                    setValueAndStyle(cell, titleList.get(i), style);
                }
            }
            region = new CellRangeAddress(0, 0, 8, 9);
            sheet.addMergedRegion(region);
            cell = row.createCell(8);
            setValueAndStyle(cell, "人员费用", style);

            region = new CellRangeAddress(0, 0, 10, 11);
            sheet.addMergedRegion(region);
            cell = row.createCell(10);
            setValueAndStyle(cell, "电偶费用", style);
            //设置循环变量 k
            int k = 1;
            for (TrialCostEO trialCostEO : trialCostEOList) {
                row = sheet.createRow(k+1);
                setAllCells(k, trialCostEO, row, style, cell);
                k++;
            }
            //浏览器下载
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/octet-stream");
            //火狐浏览器需特殊处理
            if (agent.contains(ConstantUtils.FIREFOX)) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + Encodes.urlEncode(fileName));
            }
            //写到响应输出流
            wb.write(response.getOutputStream());
        }catch (IOException e){
            //向上一层抛异常
            throw new IOException(e.getMessage());
        }finally {
            //最后强制关闭
            if(wb!=null){
                wb.close();
            }
        }

    }

}
