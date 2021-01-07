package com.adc.da.sys.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class InsertPicByJacob {

	private static final int wdFormatPDF = 17;// PDF 格式  
	
	/**
	 * 使用jacob动态替换word书签
	 * @param templatePath 原word路径
	 * @param targetPath 另存为word路径
	 * @param bookmark 书签名
	 * @param imagePath 要插入图片的路径
	 */
	@SuppressWarnings("finally")
	public boolean insertPicByjacob(String templatePath, String targetPath, 
			List<String> bookmarks, Map<String, String> imagePaths) {
		boolean bl = true ;
		ActiveXComponent app = null;
		try {
			System.out.println("启动word...");
			Dispatch doc = null;
			// 模板的路径
			String openPath = templatePath;
			// 要保存的文件的路径
			Dispatch docs = null;
			if (app == null || app.m_pDispatch == 0) {
				app = new ActiveXComponent("Word.Application");
				app.setProperty("Visible", new Variant(false));
				app.setProperty("DisplayAlerts", new Variant(false));
			}
			if (docs == null) {
				// 获得documents对象
				docs = app.getProperty("Documents").toDispatch();
			}
			doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { openPath, new Variant(false), new Variant(true) }, new int[1])
					.toDispatch();
			System.out.println("打开文档..." + openPath);
			Dispatch book_marks = app.call(doc, "Bookmarks").toDispatch();
			int bCount = Dispatch.get(book_marks, "Count").getInt();  //获取书签数
			//将书签列表存放到list + map 结构中
			for (int i = 1; i <= bCount; i++){
				Dispatch items = Dispatch.call(book_marks, "Item", i).toDispatch();
				String bookMarkKey = String.valueOf(Dispatch.get(items, "Name").getString()).replaceAll("null", "");   //读取书签命名
				Dispatch range = Dispatch.get(items, "Range").toDispatch();
				String bookMarkValue = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
				String MarkKey = bookMarkKey;
				String MarkValue = bookMarkValue;
				
				
				for(String bookmark : bookmarks) {
					//书签名并替换的内容
					if(MarkKey.equals(bookmark))//书签名为PO_user_name1
					{
						//Dispatch.put(range, "Text", new Variant(MarkValue));//替换文字
						//替换图片
						Dispatch picture = Dispatch.call(Dispatch.get(range, "InLineShapes").toDispatch(), 
								"AddPicture", imagePaths.get(bookmark)).toDispatch();
						Dispatch.call(picture, "Select"); // 选中图片
						Dispatch.put(picture, "Width", new Variant(138)); // 图片的宽度
						Dispatch.put(picture, "Height", new Variant(61)); // 图片的高度
						Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
						Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
						
						// 设置环bai绕格式（0 - 7）下面是参数说明du
						// wdWrapInline 7 将形状嵌入到文字中。
						// wdWrapNone 3 将形状放在文字前面。请参阅 wdWrapFront 。
						// wdWrapSquare 0 使文字环绕形状。行在形状的另一侧延续。
						// wdWrapThrough 2 使文字环绕形状。
						// wdWrapTight 1 使文字紧密地环绕形状。
						// wdWrapTopBottom 4 将文字放在形状的上方和下方。
						// wdWrapBehind 5 将形状放在文字后面。
						// wdWrapFront 6 将形状放在文字前面。
						Dispatch.put(WrapFormat, "Type", 5); // 图片的环绕类型
					}
				}
			}
			// 保存文件//new variant() 参数 0Doc 12、16Docx 17pdf
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { targetPath, new Variant(12) }, new int[1]);
			Dispatch.call((Dispatch) doc, "Close", new Variant(false));
			System.out.println("关闭文档");
			if (app != null)
				app.invoke("Quit", new Variant[] {});
		} catch (Exception e) {
			bl = false;
			System.err.println(e.getMessage());
		}finally {
			if(app != null){
				app.safeRelease();
				app = null;
			}
			ComThread.Release();
			return bl;
		}

	}

	public void insertWordsByJacob(List<String> list, Map<String, String> map, String templatePath, String targetPath) {
		ActiveXComponent app = null;
		try {
			System.out.println("启动word...");
			Dispatch doc = null;
			// 模板的路径
			String openPath = templatePath;
			// 要保存的文件的路径
			String toFileName = targetPath;
			Dispatch docs = null;
			if (app == null || app.m_pDispatch == 0) {
				app = new ActiveXComponent("Word.Application");
				app.setProperty("Visible", new Variant(false));
				app.setProperty("DisplayAlerts", new Variant(false));
			}
			if (docs == null) {
				// 获得documents对象
				docs = app.getProperty("Documents").toDispatch();
			}
			doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { openPath, new Variant(false), new Variant(true) }, new int[1])
					.toDispatch();
			System.out.println("打开文档..." + openPath);
			Dispatch book_marks = app.call(doc, "Bookmarks").toDispatch();
			int bCount = Dispatch.get(book_marks, "Count").getInt();  //获取书签数
			//将书签列表存放到list + map 结构中
			for (int i = 1; i <= bCount; i++){
				Dispatch items = Dispatch.call(book_marks, "Item", i).toDispatch();
				String bookMarkKey = String.valueOf(Dispatch.get(items, "Name").getString()).replaceAll("null", "");   //读取书签命名
				Dispatch range = Dispatch.get(items, "Range").toDispatch();
				String bookMarkValue = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
				String MarkKey = bookMarkKey;
				String MarkValue = bookMarkValue;
				
				
				for(String bookmark : list) {
					//书签名并替换的内容
					if(MarkKey.equals(bookmark))//书签名为PO_user_name1
					{
						MarkValue = map.get(bookmark);
						Dispatch.put(range, "Text", new Variant(MarkValue));//替换文字
					}
				}
			}
			// 保存文件//new variant() 参数 0Doc 12、16Docx 17pdf
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { targetPath, new Variant(12) }, new int[1]);
			Dispatch.call((Dispatch) doc, "Close", new Variant(false));
			System.out.println("关闭文档");
			if (app != null)
				app.invoke("Quit", new Variant[] {});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
			if(app != null){
				app.safeRelease();
				app = null;
			}
			ComThread.Release();
		}

	}
	
	
	/***
	 * wrod文档添加标签保存并转换保存PDF
	 * @param sfileName 初始文件路径(即需要修改的文件路径)
	 * @param toFileName 转换后的pdf路径
	 * @return
	 */
	public boolean convertWord(String sfileName,String toFileName){
		boolean bl = true ;
		System.out.println("启动Word...");      
        ActiveXComponent app = null;  
        Dispatch doc = null;  
        try {      
            app = new ActiveXComponent("Word.Application");      
            app.setProperty("Visible", new Variant(false));  
            Dispatch docs = app.getProperty("Documents").toDispatch();
            
            doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
					new Object[] { sfileName, new Variant(false), new Variant(false) }, new int[1]).toDispatch();
            Dispatch.call(doc, "Save");// 保存
            Dispatch.call(doc, "Close", new Variant(false));
            
            doc = Dispatch.call(docs,  "Open" , sfileName).toDispatch();  
            System.out.println("打开文档..." + sfileName);  
            System.out.println("转换文档到PDF..." + toFileName);      
            File tofile = new File(toFileName);      
            if (tofile.exists()) {      
                tofile.delete();      
            }      
            Dispatch.call(doc,      
                          "SaveAs",      
                          toFileName, // FileName      
                          wdFormatPDF);      
        } catch (Exception e) {     
        	bl = false;
            System.out.println("========Error:文档转换失败：" + e.getMessage());      
        } finally {  
            Dispatch.call(doc,"Close",false);  
            System.out.println("关闭文档");  
            if (app != null)      
                app.invoke("Quit", new Variant[] {});      
            
            }  
          //如果没有这句话,winword.exe进程将不会关闭  
           ComThread.Release();  
           return bl;
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
	
    
    private static String EV_Createby_Date = "EV_Createby_Date";
    private static String EV_Charge_Date = "EV_Charge_Date";
    public static List<String> EVDateList = new ArrayList<String>();
    static {
    	EVDateList.add(EV_Createby_Date); //编写
    	EVDateList.add(EV_Charge_Date); //主管 --校对
    }
    
	public static void main(String[] args) {
		
		String inFile = "D:\\tmp\\2.docx";
		String tpFile = "D:\\tmp\\a\\EV1.docx";
		
		Map<String, String> map = new HashMap<String, String>();
        //科长  --审核
        map.put(EV_Createby_Date, "2020.06.12");
        //部长  --批准
        map.put(EV_Charge_Date, "2020年06月13日");
		
        //insertWordsByJacob(EVDateList, map, inFile, tpFile);
        //insertPicByjacob(templatePath, targetPath, EVSealList, map);
        //convertWord("D:\\tmp\\a\\EV1.docx", "D:\\tmp\\EV1.pdf");
        
		//for(String bookmark : EVSealList) {
		//}
		//insertPicByJacob(templatePath, targetPath, word, imagePath);
		
	}
	
}
