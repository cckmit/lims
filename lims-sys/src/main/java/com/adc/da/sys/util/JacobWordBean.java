package com.adc.da.sys.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.adc.da.util.utils.StringUtils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobWordBean {

	// word 文档
    private Dispatch doc;
    
    // word运行程序对象
    private ActiveXComponent word;
    
    // 所有word文档集合
    private Dispatch documents;
    
    // 选定的范围或插入点
    private Dispatch selection;
    private boolean saveOnExit = true;
    
    /**
     * true表示word应用程序可见
     * @param visible
     *    
     */
    public JacobWordBean(boolean visible) {
        if (word == null) {
            word = new ActiveXComponent("Word.Application");
            word.setProperty("Visible", new Variant(visible));
        }
        if (documents == null) {
        	documents = word.getProperty("Documents").toDispatch();
        }
    }
    
    
    /**
     * 创建一个新的word文档
     */
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }

    
    /**
     * 打开一个已存在的文档
     *
     * @param docPath
     */
    public void openDocument(String docPath) {
        closeDocument();
        doc = Dispatch.call(documents, "Open", docPath).toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }

    
    /**
     * 把选定的内容或插入点向上移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveUp(int pos) {
        if (selection == null) {
        	selection = Dispatch.get(word, "Selection").toDispatch();
        }
        for (int i = 0; i < pos; i++) {
        	Dispatch.call(selection, "MoveUp");
        }
    }

    
    /**
     * 把选定的内容或者插入点向下移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveDown(int pos) {
        if (selection == null) {
        	selection = Dispatch.get(word, "Selection").toDispatch();
        }
        for (int i = 0; i < pos; i++) {
        	Dispatch.call(selection, "MoveDown");
        }
    }
    
    
    /**
     * 把选定的内容或者插入点向左移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveLeft(int pos) {
        if (selection == null) {
        	selection = Dispatch.get(word, "Selection").toDispatch();
        }
        for (int i = 0; i < pos; i++) {
            Dispatch.call(selection, "MoveLeft");
        }
    }
    
    
    /**
     * 把选定的内容或者插入点向右移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveRight(int pos) {
        if (selection == null) {
        	selection = Dispatch.get(word, "Selection").toDispatch();
        }
        for (int i = 0; i < pos; i++) {
        	Dispatch.call(selection, "MoveRight");
        }
    }
    
    
    /**
     * 把插入点移动到文件首位置
     */
    public void moveStart() {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        Dispatch.call(selection, "HomeKey", new Variant(6));
    }
 
    
    /**
     * 把插入点移动到文件结束位置
     */
    public void moveEnd() {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        Dispatch.call(selection, "EndKey", new Variant(6));
    }
    
    
    /**
     * 向 document 中插入文本内容
     *
     * @param textToInsert
     *            插入的文本内容
     */
    public void insertText(String textToInsert) {
        // 在指定的位置插入文本内容
        Dispatch.put(selection, "Text", textToInsert);
        // 取消选中,应该就是移动光标
        Dispatch format = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        // 设置段落格式为首行缩进2个字符
        Dispatch.put(format, "CharacterUnitFirstLineIndent", new Variant(2));
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));
        //moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //Dispatch.call(selection, "MoveUp");
        //moveDown(1);
    }
    
    
    /**
     * 插入标题
     * @param num  标题编号
     * @param level 标题级别：-2：一级标题；-3：二级标题；-4：三级标题；-5：四级标题
     * @param text 标题题目
     */
    public void insertTitle(String num, int level, String text) {
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();
 
        //Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //moveDown(1);
        Dispatch.put(selection, "Text", num + " " + text);
        Dispatch style = Dispatch.call(activeDocument, "Styles", new Variant(level)).toDispatch();;
        Dispatch.put(selection, "Style", style);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //moveDown(1);
    }

    
    /**
     * 创建目录
     */
    public void createCatalog() {
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();
        
        Dispatch.call(selection, "HomeKey", new Variant(6)); // 将光标移到文件首的位置
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        moveUp(1);
        
        Dispatch.put(selection, "Text", "目录");
        Dispatch style = Dispatch.call(activeDocument, "Styles", new Variant(-2)).toDispatch();;
        Dispatch.put(selection, "Style", style);
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        
        Dispatch myRange = Dispatch.call(selection, "Range").toDispatch();
        
        /** 获取目录 */
        Dispatch tablesOfContents = Dispatch.get(activeDocument, "TablesOfContents").toDispatch();
        
        Dispatch add = Dispatch.call(tablesOfContents, "Add", myRange, new Variant(true),
                new Variant(1), new Variant(3), new Variant(true), new Variant(true), new Variant('T'),
                new Variant(true), new Variant(true), new Variant(1), new Variant(true)).toDispatch();
        
//        Dispatch.put(add, "RightAlignPageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHeadingStyles", new Variant(true));
//        Dispatch.put(add, "UpperHeadingLevel", new Variant(1));
//        Dispatch.put(add, "LowerHeadingLevel", new Variant(3));
//        Dispatch.put(add, "IncludePageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHyperlinks", new Variant(true));
//        Dispatch.put(add, "HidePageNumbersInWeb", new Variant(true));
 
//        Dispatch.call(add, "Add", myRange);
        
        //插入一个分页符
        Dispatch.call(selection, "InsertBreak", new Variant(7));
        Dispatch.call(selection, "TypeBackspace");
    }

    
    /**
     * 更新目录
     * @param outputPath
     * @param doc
     */
    public void updateCatalog(String outputPath, Dispatch doc) {
        /** 打开word文档 */
        //Dispatch doc = Dispatch.invoke(documents, "Open", Dispatch.Method,
        //        new Object[] { outputPath, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
        //Dispatch doc = Dispatch.call(documents, "Open", outputPath).toDispatch();
        
        Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
        /** 获取目录 */
        Dispatch tablesOfContents = Dispatch.get(activeDocument, "TablesOfContents").toDispatch();
        /** 获取第一个目录。若有多个目录，则传递对应的参数  */
        Variant tablesOfContent = Dispatch.call(tablesOfContents, "Item", new Variant(1));
        /** 更新目录，有两个方法：Update 更新域，UpdatePageNumbers 只更新页码 */
        Dispatch toc = tablesOfContent.toDispatch();
        toc.call(toc, "Update");
 
        /** 另存为 */
        Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", outputPath);
        //System.out.println("更新目录");
        /** 关闭word文档 */
        Dispatch.call(doc, "Close", new Variant(false));
 
        /** 退出word进程 */
        close();
    }

    
    /**
     * 在当前插入点插入图片
     *
     * @param imagePath
     *            图片路径
     */
    public void insertImage(String imagePath, int c, int tc, String title) {
        Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
        
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        //moveRight(1);
        putText("图" + c + "-" + tc + " " + title);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
    }

    
    public void insertBlankRow() {
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "3"); // (1:置中 2:靠右 3:靠左)
    }
    
    
    /**
     * 创建表格
     *
     * @param cols
     *            列数
     * @param rows
     *            行数
     */
    public void createTable(int numCols, int numRows, int c, int tc, String title) {
//        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
//        Dispatch range = Dispatch.get(selection, "Range").toDispatch();
//        Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols))
//                .toDispatch();
//        Dispatch.call(selection, "MoveRight");
//        moveEnd();
        
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        putText("表" + c + "-" + tc + " " + title);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();
        Dispatch tables1 = Dispatch.get(activeDocument, "Tables").toDispatch();
        
        Dispatch range = Dispatch.get(selection, "Range").toDispatch();
        Dispatch.call(tables1, "Add", range, new Variant(numRows), new Variant(numCols), new Variant(1), new Variant(0)).toDispatch();
        
        Dispatch.call(selection, "MoveDown", new Variant(5), new Variant(numRows), new Variant(1));
        Dispatch format = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        Dispatch.put(format, "Alignment", new Variant(1));
        
        moveLeft(1);
    }

    
    /**
     * 向选中的单元格中写入数据
     * @param text
     */
    public void putText(String text) {
        Dispatch.put(selection, "Text", text);
    }
    
    
    /**
     * 增加一行
     *
     * @param tableIndex
     *            word文档中的第N张表(从1开始)
     */
    public void addRow(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch.call(rows, "Add");
    }

    
    /**
     * 合并单元格
     *
     * @param tableIndex
     * @param fstCellRowIdx
     * @param fstCellColIdx
     * @param secCellRowIdx
     * @param secCellColIdx
     */
    public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx))
                .toDispatch();
        Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx))
                .toDispatch();
        Dispatch.call(fstCell, "Merge", secCell);
    }

    
    /**
     * 在指定的单元格里填写数据
     *
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch.put(selection, "Text", txt);
    }

    
    /**
     * 增加一列
     *
     * @param tableIndex
     *            word文档中的第N张表(从1开始)
     */
    public void addCol(int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        Dispatch.call(cols, "Add").toDispatch();
        Dispatch.call(cols, "AutoFit");
    }

    
    /**
     * 设置当前选定内容的字体
     *
     * @param boldSize
     * @param italicSize
     * @param underLineSize
     *            下划线
     * @param colorSize
     *            字体颜色
     * @param size
     *            字体大小
     * @param name
     *            字体名称
     */
    public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
        Dispatch font = Dispatch.get(selection, "Font").toDispatch();
        Dispatch.put(font, "Name", new Variant(name));
        Dispatch.put(font, "Bold", new Variant(bold));
        Dispatch.put(font, "Italic", new Variant(italic));
        Dispatch.put(font, "Underline", new Variant(underLine));
        Dispatch.put(font, "Color", colorSize);
        Dispatch.put(font, "Size", size);
    }

    
    /**
     * 文件保存或另存为
     *
     * @param savePath
     *            保存或另存为路径
     */
    public void save(String savePath) {
        Dispatch.call((Dispatch) Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", savePath);
    }

    
    /**
     * 关闭当前word文档
     */
    public void closeDocument() {
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(saveOnExit));
            doc = null;
        }
    }

    
    /**
     * 关闭全部应用
     */
    public void close() {
        closeDocument();
        if (word != null) {
            Dispatch.call(word, "Quit");
            word = null;
        }
        selection = null;
        documents = null;
    }
    
    /**
     * 使用jacob动态替换word书签（单个）
     * 
     * @param templatePath 原word路径
     * @param targetPath 另存为word路径
     * @param bookmark 书签名
	 * @param imagePath 要插入图片的路径
	 * 
     * @return
     */
    public boolean insertPicByJacob(String templatePath, String targetPath, String bookmark, String imagePath) {
		System.out.println("打开Word...");
		boolean flag = true;
		try {
			openDocument(templatePath);
			Dispatch book_marks = word.call(doc, "Bookmarks").toDispatch();
			int bCount = Dispatch.get(book_marks, "Count").getInt();  //获取书签数
			
			for (int i = 1; i <= bCount; i++){
				Dispatch items = Dispatch.call(book_marks, "Item", i).toDispatch();
				String bookMarkKey = String.valueOf(Dispatch.get(items, "Name").getString()).replaceAll("null", "");   //读取书签命名
				Dispatch range = Dispatch.get(items, "Range").toDispatch();
				String bookMarkValue = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
				String MarkKey = bookMarkKey;
				String MarkValue = bookMarkValue;
				if( MarkKey.equals(bookmark)) {
					//替换图片
					Dispatch picture = Dispatch.call(Dispatch.get(range, "InLineShapes").toDispatch(), "AddPicture", imagePath).toDispatch();
					Dispatch.call(picture, "Select"); // 选中图片
					Dispatch.put(picture, "Width", new Variant(138)); // 图片的宽度
					Dispatch.put(picture, "Height", new Variant(61)); // 图片的高度
					Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
					Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
					Dispatch.put(WrapFormat, "Type", 5); // 图片的环绕类型
				}
					
			}
			close();
		} catch (Exception e) {
			System.out.println(e.toString());
			flag = false;
		} finally {
			// 总是关闭Word应用
			close();
			
		}
		return flag;
    }
    
    
    /**
     * 使用jacob动态替换word书签（批量）
     * 
     * @param templatePath 原word路径
     * @param targetPath 另存为word路径
     * @param bookmarkList 书签名List
	 * @param imagePathMap 要插入图片的路径Map
	 * 
     * @return
     */
    public boolean insertPicListByJacob(String templatePath, String targetPath, List<String> bookmarkList, Map<String, String> imagePathMap) {
		System.out.println("打开Word...");
		boolean flag = true;
		try {
			openDocument(templatePath);
			Dispatch book_marks = word.call(doc, "Bookmarks").toDispatch();
			int bCount = Dispatch.get(book_marks, "Count").getInt();  //获取书签数
			
			for (int i = 1; i <= bCount; i++){
				Dispatch items = Dispatch.call(book_marks, "Item", i).toDispatch();
				String bookMarkKey = String.valueOf(Dispatch.get(items, "Name").getString()).replaceAll("null", "");   //读取书签命名
				Dispatch range = Dispatch.get(items, "Range").toDispatch();
				String bookMarkValue = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
				String MarkKey = bookMarkKey;
				String MarkValue = bookMarkValue;
				
				
				for(String bookmark : bookmarkList) {
					//书签名并替换的内容
					if(MarkKey.equals(bookmark))//书签名为PO_user_name1
					{
						MarkValue = imagePathMap.get(bookmark);
						//替换图片
						Dispatch picture = Dispatch.call(Dispatch.get(range, "InLineShapes").toDispatch(), "AddPicture", MarkValue).toDispatch();
						Dispatch.call(picture, "Select"); // 选中图片
						Dispatch.put(picture, "Width", new Variant(138)); // 图片的宽度
						Dispatch.put(picture, "Height", new Variant(61)); // 图片的高度
						Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
						Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
						Dispatch.put(WrapFormat, "Type", 5); // 图片的环绕类型
					}
				}
			}
			
			close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			flag = false;
		} finally {
			// 总是关闭Word应用
			close();
			
		}
		return flag;
    }
    
    
    /**
     * 使用jacob动态替换word书签（文字书签结尾为_txt，图片书签结尾为_pic）
     * 
     * @param templatePath 原word路径
     * @param targetPath 另存为word路径
	 * @param insertMap 要插入图片的路径Map
	 * 
     * @return
     */
    public boolean insertMapByJacob(String templatePath, String targetPath, Map<String, String> insertMap) {
		System.out.println("打开Word...");
		boolean flag = true;
		try {
			openDocument(templatePath);
			Dispatch book_marks = word.call(doc, "Bookmarks").toDispatch();
			int bCount = Dispatch.get(book_marks, "Count").getInt();  //获取书签数
			
			for (int i = 1; i <= bCount; i++){
				Dispatch items = Dispatch.call(book_marks, "Item", i).toDispatch();
				String bookMarkKey = String.valueOf(Dispatch.get(items, "Name").getString()).replaceAll("null", "");   //读取书签命名
				Dispatch range = Dispatch.get(items, "Range").toDispatch();
				//String bookMarkValue = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
				String MarkKey = bookMarkKey;
				//String MarkValue = bookMarkValue;
				
				//书签名并替换的内容
				String MarkValue = insertMap.get(MarkKey);
				if(StringUtils.isNotEmpty(MarkValue)) {
					String suffix = MarkKey.substring(MarkKey.length() - 4);
					if("_pic".equals(suffix)){
						System.out.println("准备插入图片……");
						File file = new File(MarkValue);
						if(file.exists()) {
							//替换图片
							Dispatch picture = Dispatch.call(Dispatch.get(range, "InLineShapes").toDispatch(), "AddPicture", MarkValue).toDispatch();
							Dispatch.call(picture, "Select"); // 选中图片
							Dispatch.put(picture, "Width", new Variant(80)); // 图片的宽度
							Dispatch.put(picture, "Height", new Variant(40)); // 图片的高度
							Dispatch ShapeRange = Dispatch.call(picture, "ConvertToShape").toDispatch(); // 取得图片区域
							Dispatch WrapFormat = Dispatch.get(ShapeRange, "WrapFormat").toDispatch(); // 取得图片的格式对象
							Dispatch.put(WrapFormat, "Type", 5); // 图片的环绕类型
							System.out.println("插入图片完成……");
						}
					}else if ("_txt".equals(suffix)){
						System.out.println("准备插入文字……");
						Dispatch.put(range, "Text", new Variant(MarkValue));//替换文字
						System.out.println("插入文字完成……");
					}
					
				}
				
			}
			
			close();
			System.out.println("正常关闭Word");
			
		} catch (Exception e) {
			System.out.println(e.toString());
			flag = false;
			close();
			System.out.println("强制关闭Word");
		} finally {
			// 总是关闭Word应用
			close();
		}
		return flag;
    }
    
    
    
    
}
