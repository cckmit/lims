package com.adc.da.sys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adc.da.common.CustomXWPFDocument;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
//============================
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;


public class DocWriterUtils {

    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DocWriterUtils.class);

    static boolean keyFlag = false;
    private static float[] resu = null;
    static boolean flag = false;
    static int i = 0;

    public String fillDataToWord(String multipartFile, String path, Map<String, String> dataMap) throws IOException {
        XWPFDocument doc = new XWPFDocument(POIXMLDocument.openPackage(multipartFile));
        FileOutputStream outStream = null;
        try {
            /**
             * 替换指定段落的文字
             */
            if (CollectionUtils.isNotEmpty(doc.getHeaderFooterPolicy().getDefaultHeader().getTables())) {
                doc.getHeaderFooterPolicy().getDefaultHeader().getTables().get(0).getRows().get(0).getCell(1).setText(dataMap.get("${EV_REPORT_NO}"));
            }
            Iterator<XWPFParagraph> itPara = doc.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> paraSet = dataMap.keySet();
                Iterator<String> itSet = paraSet.iterator();
                while (itSet.hasNext()) {
                    String key = itSet.next();
                    List<XWPFRun> xwpfRuns = paragraph.getRuns();
                    for (int i = 0; i < xwpfRuns.size(); i++) {
                        String text = xwpfRuns.get(i).getText(0);
                        for (Map.Entry<String, String> e : dataMap.entrySet()) {
                            if (text != null && text.contains(e.getKey())) {
                                text = text.replace(e.getKey(), e.getValue());
                                xwpfRuns.get(i).setText(text, 0);
                            }
                        }
                    }
                }
            }

            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = doc.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                for (Map.Entry<String, String> e : dataMap.entrySet()) {
                                    if (text != null && text.contains(e.getKey())) {
                                        text = text.replace(e.getKey(), e.getValue());
                                        r.setText(text, 0);
                                    }
                                }
                            }
                        }

                    }
                }
            }
            //页眉
            XWPFHeader xwpfHeader = doc.getHeaderFooterPolicy().getDefaultHeader();
            XWPFParagraph paragraphArray = xwpfHeader.getParagraphArray(0);
            XWPFRun run = paragraphArray.createRun();
            run.setText(dataMap.get("${EV_REPORT_NO}"));
            // 先创建好文件夹
            FileUtil.checkAndMkdirs(new File(path));
            outStream = new FileOutputStream(path);
            doc.write(outStream);
        } catch (Exception e) {
            logger.error("error", e.getStackTrace());
            return "ERROR : " + e.getMessage();
        } finally {
            outStream.close();
        }
        return "SUCCESS";
    }


    /**
     * 将签章根据关键字插入到pdf
     *
     * @param pdfPath
     * @param imagePath
     * @param KeyWord
     */
    public static String signPDF(final String pdfPath, final String imagePath, final String KeyWord) {
        PdfReader pdfReader = null;
        String pdfPath1 = UUID.randomUUID() + ".pdf";
        try {
            String basePath = pdfPath.substring(0, pdfPath.lastIndexOf("/") + 1);
            final String newPdfPath = basePath + pdfPath1;
            fileReName(newPdfPath, pdfPath);
            pdfReader = new PdfReader(newPdfPath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            for (i = 1; i <= pageNum; i++) {
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    @Override
                    public void beginTextBlock() {

                    }

                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText();
                        if (StringUtils.isNotEmpty(text) && analysisText(text, KeyWord)) {
                            Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                            resu = new float[3];
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = i;
                            flag = true;

                            try {
                                manipulatePdf(newPdfPath, pdfPath, imagePath, resu[0], resu[1], resu[2]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }

                        }

                    }

                    @Override
                    public void endTextBlock() {

                    }

                    @Override
                    public void renderImage(ImageRenderInfo imageRenderInfo) {

                    }
                });
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            pdfReader.close();
        }
        return pdfPath1;

    }

    /**
     * 重命名文件
     * @param newFileName
     * @param oldFileName
     */
    public static void fileReName(String newFileName, String oldFileName) {
        File file = new File(newFileName);
        File file1 = new File(oldFileName);
        file1.renameTo(file);
    }


    /**
     * 分析文本信息
     *
     * @return
     */
    private static boolean analysisText(String pdfText, String keyWord) {
        String key = "";
        boolean value = false;
        if (pdfText.trim().startsWith("写") || pdfText.trim().startsWith("核") || pdfText.trim().startsWith("准")) {
            keyFlag = true;
            key = "";
        }
        if (keyFlag) {
            key += pdfText;
        }
        if (pdfText.trim().endsWith(":") || pdfText.trim().endsWith("：")) {
            keyFlag = false;
            key += pdfText;
            key = key.replace("：", ":");

            keyWord = keyWord.replace("：", ":");
            if (key.contains(keyWord)) {
                value = true;
            }
            key = "";
        }
        return value;
    }

    /**
     * 功能:插入图片
     */
    public static void manipulatePdf(String src, String dest, String img, float x, float y, float i)
            throws IOException, DocumentException {
        //这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 10);
        font.setStyle(Font.BOLD);
        font.getBaseFont();
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Image image = Image.getInstance(img);
        PdfImage stream = new PdfImage(image, "", null);
        stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
        PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
        int page = (int) i;
        PdfContentByte over = stamper.getOverContent(page);
        image.setDirectReference(ref.getIndirectReference());
        image.scaleAbsoluteWidth(61);
        image.scaleAbsoluteHeight(30);
        image.setAbsolutePosition(x + 30, y - 10);// 控制图片位置，左下角是坐标原点
        over.addImage(image);
        //开始写入文本
        over.beginText();
        //设置字体和大小
        over.setFontAndSize(font.getBaseFont(), 15);
        //设置字体颜色
        over.setColorFill(new BaseColor(0, 0, 0));
        com.itextpdf.text.pdf.PdfGState gState = new PdfGState();
        gState.setStrokeOpacity(0.1f);
        over.setGState(gState);
        //要输出的text          对齐方式          写的字        设置字体的输出位置  字体是否旋转
        over.showTextAligned(Element.ALIGN_CENTER, DateUtils.dateToString(new Date(), "yyyy年MM月dd日"), x + 280, y, 0);
        over.endText();
        stamper.close();
        reader.close();
    }

    @SuppressWarnings("resource")
    public static boolean addFile(String string, String path) {
        PrintStream stream = null;
        try {
            stream = new PrintStream(path);
            // 写入的文件path
            stream.print(string);
            // 写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        return false;
    }

    
    /**
     * 填充图片
     * @Title: writePic
     * @param fileNew
     * @param map
     * @param tag
     * @throws IOException
     * @return void
     * @author: ljy
     * @date: 2020年6月18日
     */
    public static void  writePic(CustomXWPFDocument doc, Map<String, String> map , String tag) throws IOException{
		FileInputStream is;
		try {
			Iterator<XWPFTable> it = doc.getTablesIterator();  
            while(it.hasNext()){  
                XWPFTable table = it.next();  
                List<XWPFTableRow> rows = table.getRows();  
                for(XWPFTableRow row:rows){  
                    List<XWPFTableCell> cells = row.getTableCells();  
                    for(XWPFTableCell cell:cells){  
                        if(cell.getText().endsWith(tag)){  
                            cell.removeParagraph(0);  
                            File pic = new File(map.get(tag));
                            if(pic.exists()){
                        		is = new FileInputStream(pic);
                        		XWPFParagraph pargraph = cell.addParagraph(); 
	                            //50为宽，50为高  
	                            byte[] bytes = inputStream2ByteArray(is, true);
	                            String fileName = pic.getName();
	                            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	                            String ind = doc.addPictureData(bytes, getPictureType(suffix));
	                            doc.createPicture(ind, 80, 40,pargraph); 
	                            is.close();
                            }else{
                            	 //不显示图片时,显示图片名
                            	 XWPFParagraph pargraph = cell.addParagraph();
		                 	     XWPFRun headRun_12 = pargraph.createRun();
		                 	     headRun_12.setFontSize(9);
		                 	     headRun_12.setText("");
                            }
                         
                        }  
                        List<XWPFParagraph> pars = cell.getParagraphs();  
                        for(XWPFParagraph par:pars){  
                            List<XWPFRun> runs = par.getRuns();  
                            for(XWPFRun run:runs){  
                                run.removeBreak();  
                                  
                            }  
                        }  
                    }  
                }  
            }  
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}  
    }
    
    
    
	/**
	 * 根据图片类型，取得对应的图片类型代码
	 * @param picType
	 * @return int
	 */
	private static int getPictureType(String picType){
	    int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
	    if(picType != null){
	        if(picType.equalsIgnoreCase("png")){
	            res = CustomXWPFDocument.PICTURE_TYPE_PNG;
	        }else if(picType.equalsIgnoreCase("dib")){
	            res = CustomXWPFDocument.PICTURE_TYPE_DIB;
	        }else if(picType.equalsIgnoreCase("emf")){
	            res = CustomXWPFDocument.PICTURE_TYPE_EMF;
	        }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){
	            res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
	        }else if(picType.equalsIgnoreCase("wmf")){
	            res = CustomXWPFDocument.PICTURE_TYPE_WMF;
	        }
	    }
	    return res;
	}
    
    
	
    /**
     * 将输入流中的数据写入字节数组
     *
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
        return byteArray;
    }
    
    
    
//    public static void refreshBooks(XWPFDocument doc, Map<String, InputStream> dataMap, String type) throws IOException, InvalidFormatException { 
//    	List<XWPFParagraph> paragraphs = doc.getParagraphs(); 
//    	for (XWPFParagraph xwpfParagraph : paragraphs) { 
//    		CTP ctp = xwpfParagraph.getCTP(); 
//    		for (int dwI = 0; dwI < ctp.sizeOfBookmarkStartArray(); dwI++) { 
//    			CTBookmark bookmark = ctp.getBookmarkStartArray(dwI); 
//    			InputStream picIs = dataMap.get(bookmark.getName()); 
//    			if(picIs != null){ 
//    				XWPFRun run = xwpfParagraph.createRun(); 
//    				//bus.png为鼠标在word里选择图片时，图片显示的名字，400，400则为像素单元，根据实际需要的大小进行调整即可。 
//    				run.addPicture(picIs, getPictureType(type),"bus.png,", Units.toEMU(80), Units.toEMU(40)); 
//    			} 
//    		}
//    	}
//    }


    
    /**
     * 
     * @Title: picToWord
     * @param docPath   原Word路径
     * @param list   书签list
     * @param map   书签+对应签章图片地址
     * @param docNewPath   插入图片后的Word路径
     * @return void
     * @author: ljy
     * @date: 2020年6月19日
     */
    
    public void picToWord(String docPath, List<String> list, Map<String, String> map, String docNewPath) {
    	//加载包含书签的文档
        Document doc = new Document();
        doc.loadFromFile(docPath);

        for (String bookmark : list) {
        	if(StringUtils.isNotEmpty(map.get(bookmark))) {
        		//定位到指定书签位置起始标签位置，插入图片
        		BookmarksNavigator bookmarksNavigator = new BookmarksNavigator(doc);
        		bookmarksNavigator.moveToBookmark(bookmark,true,true);
        		Paragraph para = new Paragraph(doc);
        		DocPicture picture = para.appendPicture(map.get(bookmark));
        		picture.setHeight(40);
        		picture.setWidth(80);
        		picture.setTextWrappingStyle(TextWrappingStyle.Through);
        		bookmarksNavigator.insertParagraph(para);
        	}
		}
        
        //保存文档
        doc.saveToFile(docNewPath,FileFormat.Docx_2013);
        doc.dispose();
    }
    
    private static String EV_Createby = "EV_Createby";
    private static String EV_Charge = "EV_Charge";
    private static String EV_SectionChief = "EV_SectionChief";
    private static String EV_Minister = "EV_Minister";
    
    public static List<String> EVSealList = new ArrayList<String>();
    static {
    	EVSealList.add(EV_Createby); //编写
    	EVSealList.add(EV_Charge); //主管 --校对
    	EVSealList.add(EV_SectionChief); //科长 --审核
    	EVSealList.add(EV_Minister);//部长 --批准
    }

    public static void main(String[] args) throws IOException {
    	try { 
    		
//    		
//    		 FileInputStream fileInputStream = new FileInputStream("D:\\tmp\\EV.docx"); 
//    		 XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream); 
//    		 PdfOptions pdfOptions = PdfOptions.create(); 
//    		 FileOutputStream fileOutputStream = new FileOutputStream("D:\\tmp\\EV.pdf"); 
//    		 PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,pdfOptions); 
//    		 fileInputStream.close(); 
//    		 fileOutputStream.close();
    		
//            String filepath = "D:\\tmp\\EV.docx";  
//            String outpath = "D:\\tmp\\EV.pdf";   
//               
//            InputStream source;
//    		OutputStream target;
//    		try {
//    			source = new FileInputStream(filepath);  
//    			target = new FileOutputStream(outpath);
//    	        Map<String, String> params = new HashMap<String, String>();  
//    	           
//    	           
//    	        PdfOptions options = PdfOptions.create();  
//    	           
//    	        wordConverterToPdf(source, target, options, params);  
//    		} catch (FileNotFoundException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		} catch (Exception e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}  
//     
    		String docPath = "D:\\tmp\\EV.docx";
    		String docNewPath = "D:\\tmp\\EV1.docx";
    		
    		
    		
    		
    		
    		Map<String, String> map = new HashMap<String, String>();
            //科长  --审核
            map.put(EV_SectionChief, "D:\\tmp\\ouywh.png");
            //部长  --批准
            map.put(EV_Minister, "D:\\tmp\\xcs.png");
    		
    		//加载包含书签的文档
            Document doc = new Document();
            doc.loadFromFile(docPath);

            for (String bookmark : EVSealList) {
            	if(StringUtils.isNotEmpty(map.get(bookmark))) {
            		//定位到指定书签位置起始标签位置，插入图片
            		BookmarksNavigator bookmarksNavigator = new BookmarksNavigator(doc);
            		bookmarksNavigator.moveToBookmark(bookmark,true,true);
            		Paragraph para = new Paragraph(doc);
            		DocPicture picture = para.appendPicture(map.get(bookmark));
            		picture.setHeight(40);
            		picture.setWidth(80);
            		picture.setTextWrappingStyle(TextWrappingStyle.Through);
            		bookmarksNavigator.insertParagraph(para);
            	}
    		}
            
            //保存文档
            doc.saveToFile(docNewPath,FileFormat.Docx_2013);
            doc.dispose();
    		
//            
            
//    		FileInputStream is = new FileInputStream("D:\\tmp\\EV.docx"); 
//    		XWPFDocument doc = new XWPFDocument(is); 
//    		Map<String,InputStream> dataMap = new HashMap<>(); 
//    		dataMap.put("EV_SectionChief",new FileInputStream("D:\\tmp\\ouywh.png")); 
//    		dataMap.put("EV_Minister",new FileInputStream("D:\\tmp\\xcs.png")); 
//    		refreshBooks(doc,dataMap,"png"); 
//    		doc.write(new FileOutputStream("D:\\tmp\\EV1.docx")); 
    		}catch (Exception e){ 
    			e.printStackTrace(); 
    		}
    	}
//        final String p = "D:/CarProjects/DOC/temp/upload/160ba874c9e7497b890973db84b03224.pdf";
//        final String p2 = "C:\\Users\\TDBG\\Desktop\\PC实验报告模板.docx";
////		new File(p).delete();//f2d02d7de4114ed28caa8cfe3774d0fc_
////		new File(p2).renameTo(new File(p));
//        final String f = "核";
//        System.out.println(f);
//
//        signPDF(p, p2, f);
}
