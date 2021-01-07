/**
 *
 */
package com.adc.da.equipment.Util;

import com.adc.da.equipment.VO.EqBarcodeVo;
import com.adc.da.part.vo.SaPartsQRCodeVO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adc.da.util.utils.StringUtils;

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

    public static void generalBarcodePdf(List<EqBarcodeVo> attributes, HttpServletResponse response)
            throws DocumentException, IOException {
        response.setContentType("application/pdf");

        Rectangle pageSize = new Rectangle(460, 250);
        BaseFont baseFont = BaseFont.createFont("/font/STSONG.TTF",
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 14f, Font.BOLD);
        Font font2 = new Font(baseFont, 19f, Font.BOLD);
        Document document = new Document(pageSize, 0, 0, 45, 0);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            for (EqBarcodeVo pdf : attributes) {
                float[] widths = {0.73f, 0.27f};
                PdfPTable tbTag = new PdfPTable(widths);
                PdfPCell ttCellItem = new PdfPCell(new Paragraph("设备标签", font2));
                ttCellItem.setHorizontalAlignment(1);
                ttCellItem.setBorder(0);
                ttCellItem.setUseAscender(true);
                ttCellItem.setUseDescender(true);
                ttCellItem.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ttCellItem.setPaddingBottom(20);
                ttCellItem.setColspan(2);
                PdfPTable nested = new PdfPTable(1);
                String eqName = pdf.getEqName();
                if (StringUtils.isEmpty(eqName)){
                    eqName = "";
                }
                PdfPCell cell1 = new PdfPCell(new Paragraph("设备名称 : " + eqName, font));
                cell1.setFixedHeight(30);
                cell1.setBorderWidthTop(40);
                cell1.setBorder(0);
                nested.addCell(cell1);
                String eqNo = pdf.getEqNo();
                if (StringUtils.isEmpty(eqNo)){
                    eqNo = "";
                }
                PdfPCell cell2 = new PdfPCell(new Paragraph("设备编号 : " + eqNo, font));
                cell2.setFixedHeight(30);
                cell2.setBorder(0);
                nested.addCell(cell2);
                String eqSupplier = pdf.getEqSupplier();
                if (StringUtils.isEmpty(eqSupplier)){
                    eqSupplier = "";
                }
                PdfPCell cell21 = new PdfPCell(new Paragraph("供应商 : " + eqSupplier, font));
                cell21.setFixedHeight(30);
                cell21.setBorder(0);
                nested.addCell(cell21);
                String eqAssetsNo = pdf.getEqAssetsNo();
                if (StringUtils.isEmpty(eqAssetsNo)){
                    eqAssetsNo = "";
                }
                PdfPCell cell3 = new PdfPCell(new Paragraph("固定资产编号 : " + eqAssetsNo, font));
                cell3.setFixedHeight(30);
                cell3.setBorder(0);
                nested.addCell(cell3);
                PdfPTable nested2 = new PdfPTable(1);
                Image image41 = Image.getInstance(pdf.getBarCode());
                image41.setBorder(0);
                image41.scaleAbsolute(120f, 120f);
                PdfPCell cellimg = new PdfPCell(image41);
                cellimg.setBorder(0);
                nested2.addCell(cellimg);
                PdfPCell cellLeft = new PdfPCell(nested);
                cellLeft.setBorder(0);
                PdfPCell cellRight = new PdfPCell(nested2);
                cellRight.setBorder(0);
                cellRight.setPaddingTop(0);
                tbTag.addCell(ttCellItem);
                tbTag.addCell(cellLeft);
                tbTag.addCell(cellRight);
                document.add(tbTag);
                document.newPage();
            }
            document.close();
            writer.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
