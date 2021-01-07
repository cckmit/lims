/**
 *
 */
package com.adc.da.common;

import com.adc.da.car.vo.CarBarcodeVO;
import com.adc.da.part.vo.SaPartsQRCodeVO;
import com.adc.da.util.utils.StringUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author zer0
 */
public class PDFUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFUtil.class);

    private PDFUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void generalBarcodePdf(List<CarBarcodeVO> attributes, HttpServletResponse response)
            throws DocumentException, IOException {
        response.setContentType("application/pdf");

        Rectangle pageSize = new Rectangle(460, 400);
        BaseFont baseFont = BaseFont.createFont("/font/STSONG.TTF",
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 15f, Font.BOLD);
        Font font2 = new Font(baseFont, 19f, Font.BOLD);
        Document document = new Document(pageSize, 0, 0, 45, 0);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            for (CarBarcodeVO pdf : attributes) {
                float[] widths = {0.73f, 0.27f};
                PdfPTable tbTag = new PdfPTable(widths);
                PdfPCell ttCellItem = new PdfPCell(new Paragraph(" ", font2));
                ttCellItem.setHorizontalAlignment(1);
                ttCellItem.setBorder(0);
                ttCellItem.setUseAscender(true);
                ttCellItem.setUseDescender(true);
                ttCellItem.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ttCellItem.setPaddingBottom(20);
                ttCellItem.setColspan(2);
                PdfPTable nested = new PdfPTable(1);
                String carName = pdf.getCarName();
                if (StringUtils.isEmpty(carName)){
                    carName = "";
                }
                String bmProjectName = pdf.getBmProjectName();
                if (StringUtils.isEmpty(bmProjectName)){
                    bmProjectName = "";
                }
                String inspectProjectName = pdf.getInspectProjectName();
                if (StringUtils.isEmpty(inspectProjectName)){
                    inspectProjectName = "";
                }
                String carVin = pdf.getCarVin();
                if (StringUtils.isEmpty(carVin)){
                    carVin = "";
                }
                String carNO = pdf.getCarNO();
                if (StringUtils.isEmpty(carNO)){
                    carNO = "";
                }
                PdfPCell cell1 = new PdfPCell(new Paragraph("样品名称 : " + carName, font));
                cell1.setFixedHeight(50);
                cell1.setBorderWidthTop(80);
                cell1.setBorder(0);
                nested.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(new Paragraph("所属项目 : " + bmProjectName, font));
                cell2.setFixedHeight(50);
                cell2.setBorder(0);
                nested.addCell(cell2);
                PdfPCell cell21 = new PdfPCell(new Paragraph("检验项目 : " + inspectProjectName, font));
                cell21.setFixedHeight(50);
                cell21.setBorder(0);
                nested.addCell(cell21);
                PdfPCell cell3 = new PdfPCell(new Paragraph("VIN码 : " + carVin, font));
                cell3.setFixedHeight(50);
                cell3.setBorder(0);
                nested.addCell(cell3);
                PdfPCell cell4 = new PdfPCell(new Paragraph("样车编号 : " + carNO, font));
                cell4.setFixedHeight(50);
                cell4.setBorder(0);
                nested.addCell(cell4);
                PdfPTable nested2 = new PdfPTable(1);
                Image image41 = Image.getInstance(pdf.getBarCode());
                image41.setBorder(0);
                image41.scaleAbsolute(146f, 146f);
                PdfPCell cellimg = new PdfPCell(image41);
                cellimg.setBorder(0);
                nested2.addCell(cellimg);
                PdfPCell cellLeft = new PdfPCell(nested);
                cellLeft.setBorder(0);
                PdfPCell cellRight = new PdfPCell(nested2);
                cellRight.setBorder(0);
                cellRight.setPaddingTop(5);
                tbTag.addCell(cellLeft);
                tbTag.addCell(cellRight);
                document.add(tbTag);
                Paragraph paragraph = new Paragraph("待检 □  在检 □  检毕 □  留样 □", new Font(baseFont, 20f, Font.BOLD));
                paragraph.setIndentationLeft(44);
                document.add(paragraph);
                document.newPage();
            }
            document.close();
            writer.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 生成二维码
     *
     * @param partBarcodeVOList
     * @param response
     */
    public static void partQRCodePdf(List<SaPartsQRCodeVO> partBarcodeVOList, HttpServletResponse response)
            throws IOException, DocumentException {
        response.setContentType("application/pdf");

        Rectangle pageSize = new Rectangle(460, 250);
        BaseFont baseFont = BaseFont.createFont("/font/STSONG.TTF",
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 18f, Font.BOLD);
        Font font2 = new Font(baseFont, 19f, Font.BOLD);
        Document document = new Document(pageSize, 0, 0, 45, 0);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            for (SaPartsQRCodeVO pdf : partBarcodeVOList) {
                float[] widths = {0.73f, 0.27f};
                PdfPTable tbTag = new PdfPTable(widths);
                PdfPCell ttCellItem = new PdfPCell(new Paragraph(" ", font2));
                ttCellItem.setHorizontalAlignment(1);
                ttCellItem.setBorder(0);
                ttCellItem.setUseAscender(true);
                ttCellItem.setUseDescender(true);
                ttCellItem.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ttCellItem.setPaddingBottom(20);
                ttCellItem.setColspan(2);
                PdfPTable nested = new PdfPTable(1);
                String sampleName = pdf.getSampleName();
                if (StringUtils.isEmpty(sampleName)){
                    sampleName = "";
                }
                String subordinateItems = pdf.getSubordinateItems();
                if (StringUtils.isEmpty(subordinateItems)){
                    subordinateItems = "";
                }
                String ccTestProject = pdf.getCcTestProject();
                if (StringUtils.isEmpty(ccTestProject)){
                    ccTestProject = "";
                }
                String partNumber = pdf.getPartNumber();
                if (StringUtils.isEmpty(partNumber)){
                    partNumber = "";
                }
                String trialTaskBookNO = pdf.getTrialTaskBookNO();
                if (StringUtils.isEmpty(trialTaskBookNO)){
                    trialTaskBookNO = "";
                }
                PdfPCell cell1 = new PdfPCell(new Paragraph("样品名称 : " + sampleName, font));
                cell1.setFixedHeight(30);
                cell1.setBorderWidthTop(80);
                cell1.setBorder(0);
                nested.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(new Paragraph("所属项目 : " + subordinateItems, font));
                cell2.setFixedHeight(30);
                cell2.setBorder(0);
                nested.addCell(cell2);
                PdfPCell cell21 = new PdfPCell(new Paragraph("检验项目 : " + ccTestProject, font));
                cell21.setFixedHeight(30);
                cell21.setBorder(0);
                nested.addCell(cell21);
                PdfPCell cell3 = new PdfPCell(new Paragraph("零部件号 : " + partNumber, font));
                cell3.setFixedHeight(30);
                cell3.setBorder(0);
                nested.addCell(cell3);
                //PdfPCell cell4 = new PdfPCell(new Paragraph("检测委托合同编号 : " + pdf.getCommissionNumber(), font));
                PdfPCell cell4 = new PdfPCell(new Paragraph("试验任务书编号 : " + trialTaskBookNO, font));
                cell4.setFixedHeight(30);
                cell4.setBorder(0);
                nested.addCell(cell4);
                PdfPTable nested2 = new PdfPTable(1);
                Image image41 = Image.getInstance(pdf.getQrCode());
                image41.setBorder(0);
                image41.scaleAbsolute(146f, 146f);
                PdfPCell cellimg = new PdfPCell(image41);
                cellimg.setBorder(0);
                nested2.addCell(cellimg);
                PdfPCell cellLeft = new PdfPCell(nested);
                cellLeft.setBorder(0);
                PdfPCell cellRight = new PdfPCell(nested2);
                cellRight.setBorder(0);
                cellRight.setPaddingTop(5);
                tbTag.addCell(cellLeft);
                tbTag.addCell(cellRight);
                document.add(tbTag);
                Paragraph paragraph = new Paragraph("待检 □  在检 □  检毕 □  留样 □", new Font(baseFont, 20f, Font.BOLD));
                paragraph.setIndentationLeft(44);
                document.add(paragraph);
                document.newPage();
            }
            document.close();
            writer.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
