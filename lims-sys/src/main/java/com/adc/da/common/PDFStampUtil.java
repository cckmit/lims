package com.adc.da.common;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.annotations.PdfRubberStampAnnotation;
import com.spire.pdf.annotations.appearance.PdfAppearance;
import com.spire.pdf.graphics.PdfImage;
import com.spire.pdf.graphics.PdfTemplate;

/**
 * PDF加签章工具类
 * @author ljy
 * @date 2019年8月23日
 */
public class PDFStampUtil {
	
	private PDFStampUtil() {
	    throw new IllegalStateException("Utility class");
	}
	/**
	 * PDF加签章: 在第一页添加 完整章+骑缝章
	* @Title：PDFAddStamp
	* @param pdfPath PDF路径(完整路径)
	* @param stampPath 签章路径(完整路径)
	* @param x 签章x轴偏移量
	* @param y 签章y轴偏移量
	* @return: void
	* @author： ljy  
	* @date： 2019年8月23日
	 */
	public static void PDFAddStamp(String pdfPath, String stampPath,float x, float y) {
    	//创建PdfDocument对象，加载PDF测试文档
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(pdfPath);
        //获取文档第1页
        PdfPageBase page = doc.getPages().get(0);
        
        //加载印章图片
        PdfImage image = PdfImage.fromFile(stampPath);
        //获取印章图片的宽度和高度
        int width = image.getWidth();
        int height = image.getHeight();
        //创建PdfTemplate对象
        PdfTemplate template = new PdfTemplate(width, height);
        //将图片绘制到模板
        template.getGraphics().drawImage(image, 0, 0, width, height);
        /**
         * 完整章
         */
        //创建PdfRubebrStampAnnotation对象，指定大小和位置 
        Rectangle2D rect = new Rectangle2D.Float((float) (
        		page.getActualSize().getWidth() - width), 
        		(float) (page.getActualSize().getHeight() - height -y),
        		width, 
        		height);
        PdfRubberStampAnnotation stamp = new PdfRubberStampAnnotation(rect);
        //创建PdfAppearance对象
        PdfAppearance pdfAppearance = new PdfAppearance(stamp);
        //将模板应用为PdfAppearance的一般状态
        pdfAppearance.setNormal(template);
        //将PdfAppearance 应用为图章的样式
        stamp.setAppearance(pdfAppearance);
        //添加图章到PDF
        page.getAnnotationsWidget().add(stamp);
        /**
         * 骑缝章
         */
        //创建PdfRubebrStampAnnotation对象，指定大小和位置
        Rectangle2D acrossPageRect = new Rectangle2D.Float((float) (
        		page.getActualSize().getWidth() - width - 50*x ), 
        		(float) (page.getActualSize().getHeight() - height -y),
        		width, 
        		height);
        PdfRubberStampAnnotation acrossPageStamp = new PdfRubberStampAnnotation(acrossPageRect);
        //创建PdfAppearance对象
        PdfAppearance acrossPagePdf = new PdfAppearance(acrossPageStamp);
        //将模板应用为PdfAppearance的一般状态
        acrossPagePdf.setNormal(template);
        //将PdfAppearance 应用为图章的样式
        acrossPageStamp.setAppearance(acrossPagePdf);
        //添加图章到PDF
        page.getAnnotationsWidget().add(acrossPageStamp);
 
        //保存文档
        doc.saveToFile(pdfPath,FileFormat.PDF);
    }
    

   
	/**
	 * 给PDF加文字水印
	* @Title：waterMark
	* @param inputFile 你的PDF文件地址
	* @param outputFile 添加水印后生成PDF存放的地址
	* @param waterMarkName 你的水印
	* @return: boolean
	* @author： ljy  
	* @date： 2019年7月24日
	 * @throws IOException 
	 * @throws DocumentException 
	 * @throws Exception 
	 */
      public static void waterMark(String inputFile, String outputFile, String waterMarkName) throws DocumentException, IOException {
    	PdfReader reader = new PdfReader(inputFile);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                outputFile));
        //这里的字体设置比较关键，这个设置是支持中文的写法
        BaseFont base = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.EMBEDDED);// 使用系统字体
        int total = reader.getNumberOfPages() + 1;

        PdfContentByte under;
        Rectangle pageRect = null;
        for (int i = 1; i < total; i++) {
            pageRect = stamper.getReader().
                    getPageSizeWithRotation(i);
            // 计算水印X,Y坐标
            float x = pageRect.getWidth()/10 + 150;
            float y = pageRect.getHeight()/10 + 250;
            // 获得PDF最顶层
            under = stamper.getOverContent(i);
            under.saveState();
            // set Transparency
            PdfGState gs = new PdfGState();
            // 设置透明度为0.2
            gs.setFillOpacity(0.1f);
            under.setGState(gs);
            under.restoreState();
            under.beginText();
            under.setFontAndSize(base, 40);
            under.setColorFill(BaseColor.LIGHT_GRAY);

            // 水印文字成45度角倾斜
            under.showTextAligned(Element.ALIGN_CENTER
                    , waterMarkName, x,
                    y, 30);
            // 添加水印文字
            under.endText();
            under.setLineWidth(0.1f);
            under.stroke();
        }
        stamper.close();
        reader.close();
    }
	    
      
      /**
       * 将图片转换成Base64编码
       * @param imgFile 待处理图片
       * @return
       */
      public static String getImgStr(String imgFile) {
          // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

          InputStream in = null;
          byte[] data = null;
          // 读取图片字节数组
          try {
              in = new FileInputStream(imgFile);
              data = new byte[in.available()];
              in.read(data);
              in.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return new String(Base64.encodeBase64(data));
      }
      
      
      public static void main(String[] args) {
		 try {
			 
			//加载印章图片
		        PdfImage image = PdfImage.fromFile("D:\\tmp\\timg.jpg");
		        //获取印章图片的宽度和高度
		        int width = image.getWidth();
		        int height = image.getHeight();
			PDFAddStamp("D:\\tmp\\pdf.pdf", "D:\\tmp\\timg.jpg" , width, height);
			waterMark("D:\\tmp\\pdf.pdf", "D:\\tmp\\pdf1.pdf", "东风柳州汽车有限公司");
			
			
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
      
}
