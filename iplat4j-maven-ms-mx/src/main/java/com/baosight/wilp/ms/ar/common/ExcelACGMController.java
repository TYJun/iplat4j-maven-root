/**
 * 	模板导入功能和模板下载功能
 *	@Name ExcelACGMController.java
 *	@Description 物资信息excel导入功能
 *	@Author jiangzhongmin
 *	@Date 2021年8月10日 上午10:13:05
 *	@Version 1.0
 **/

package com.baosight.wilp.ms.ar.common;


import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Base64Utils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;


@WebServlet("/excelACGMS")
@MultipartConfig  
public class ExcelACGMController extends HttpServlet{

	//物资表头
	String[] mat = {
			"计量量纲", "计量单位", "规则说明", "低报警", "低低报警", "高报警",
			"高高报警"
	};
	String[] matFieldName = {
			"matName", "matClassId", "matSpec", "matModel", "unit", "price",
			"supplier", "manufacturer", "matTypeCode", "remark", "error"
	};


	/**
	 * 	模板下载
	 * 	入参：HttpServletRequest，HttpServletResponse
	 * 	出参：void
	 * 	处理：
	 * 	1.自动生成模板
	 * 	2.设置返回消息头
	 * 	3.返回数据
	 * 	4.关闭流
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		List<String> list = Arrays.asList(mat);
		String sheetName = "msar";
		String fileName = "msar";

		Workbook workBook = createWorkBook("2003", sheetName, list);

		fileName += ".xls";
        //设置消息头
        resp.setHeader("Content-Disposition", "attachment; filename="+fileName);

        ServletOutputStream  servletOutputStream = resp.getOutputStream();
        workBook.write(servletOutputStream);
        //关闭资源
        servletOutputStream.close();

	}
	
	/**
	 * 	模板上传，导入数据
	 * 	入参：HttpServletRequest,HttpServletResponse
	 * 	出参：void
	 * 	处理：
	 * 	1.读取request中得到的文件
	 * 	2.判断是否为excel后缀，如果不是excel后缀则返回错误信息：please upload excel file!
	 * 	3.调用openExcelByInputStream()方法导入数据
	 * 	
	 */
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Part part = req.getPart("fileUpload");
        String disposition = part.getHeader("Content-Disposition");
        String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);
		resp.setContentType("application/json");
		//判断是否为excel后缀
        if (".xls".equals(suffix) || ".xlsx".equals(suffix) ) {
        	// 处理excel
			EiInfo eiInfo = openExcelByInputStream(part.getInputStream());
			if (eiInfo.getStatus() == 0){
				// 全部导入成功
				resp.getWriter().print("{\"msg\":\"all\"}");
			}else if(eiInfo.getStatus() == 1){
				// 部分导入成功 返回错误数据excel下载地址
				String type = eiInfo.getString("type");
				List<Map<String, String>>  errorList = (List<Map<String, String>>)eiInfo.get("list");
				if ("msar".equals(type)){
					ArrayList<String> matList = new ArrayList<>(Arrays.asList(mat));
					matList.add("错误信息");
					String fileName = "error.xls";
					Workbook workBook = createWorkBook("2003", type, matList,errorList,matFieldName);

					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					workBook.write(bout);
					byte[] bytes = bout.toByteArray();

					String base64 = Base64Utils.encodeToString(bytes);
					resp.getWriter().print("{\"msg\":\"part\",\"base64\":\""+base64+"\"}");

				}

			}
		}else {
        	// 用户上载的文件可能不是excel
			resp.getWriter().print("{\"msg\":\"error\"}");
		}
	}
	
	/**
	 * 	读取Excel文件流将内容导入数据库
	 * 	入参：InputStream
	 * 	出参：void
	 * 	处理：
	 * 	1.读取地点sheet，导入数据库
	 * 	2.读取电话sheet，导入数据库
	 * @return
	 */
	public static EiInfo openExcelByInputStream(InputStream inputStream){
	       try {
	           Workbook wb = WorkbookFactory.create(inputStream);
			   String sheetName = wb.getSheetName(0);
			   EiInfo eiInfo = new EiInfo();

			   List<Map<String, String>> list = new ArrayList<>();
			   // 判断sheet 名称
			   // 导入物资数据
			   int status = 0;
			   String msg = "";
			   if ("msar".equals(sheetName)) {
				   Sheet sheet = wb.getSheetAt(0);
				   for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					   Row row = sheet.getRow(i);//获取行
					   Map<String,String> map = new HashMap<>();
					   Map map1 = getMap(null);
					   map.put("dimension", getValue(row.getCell(0)));
					   if(!map1.containsKey(getValue(row.getCell(0)))) {
							status = -1;
							break;
					   }
					   map.put("dimensionName", map1.get(getValue(row.getCell(0))).toString());
					   map.put("unit", getValue(row.getCell(1)));
					   map1 = getMap(getValue(row.getCell(0)));
					   if(!map1.containsKey(getValue(row.getCell(1)))) {
					   		status = -2;
					   		break;
					   }
					   map.put("description", getValue(row.getCell(2)));
					   map.put("lower", getValue(row.getCell(3)));
					   map.put("lowerLower", getValue(row.getCell(4)));
					   map.put("upper", getValue(row.getCell(5)));
					   map.put("upperUpper", getValue(row.getCell(6)));
					   list.add(map);
				   }
				   if(status==-1) {
						msg = "无此计量量纲，请重新填写！";
					}
					if(status==-2) {
						msg = "无此计量单位，请重新填写！";
					}
				   HashMap<String, Object> map1 = new HashMap<>();
				   map1.put("list", list);
				   eiInfo.setAttr(map1);
				   eiInfo.set(EiConstant.serviceName, "MSAR01");
				   eiInfo.set(EiConstant.methodName, "importForm");
				   EiInfo outInfo = XLocalManager.call(eiInfo);
				   outInfo.set("type","msar");
				   return outInfo;
			   }
	       }catch (Exception ex){
	    	   ex.printStackTrace();
	       }
	       EiInfo out = new EiInfo();
	       out.setStatus(-1);
	       return out;
	}
	
	/**
	 * 	Excel Cell内容转String读取
	 * 	入参：Cell
	 * 	出参：String
	 * 	处理：
	 * 	1.判断是否为布尔类型的值，如果是则返回对应字符串
	 * 	2.判断是否为值类型的值，如果是则返回对应字符串
	 */
	private  static String getValue(Cell hssfCell) {
			if (hssfCell == null){
				return "";
			}
	      if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	          // 返回布尔类型的值
	          return String.valueOf(hssfCell.getBooleanCellValue());
	      } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	          // 返回数值类型的值
	          Object inputValue = null;// 单元格值
	          Long longVal = Math.round(hssfCell.getNumericCellValue());
	          double doubleVal = hssfCell.getNumericCellValue();
	          if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
	              inputValue = longVal;
	          }
	          else{
	              inputValue = doubleVal;
	          }
	          DecimalFormat df = new DecimalFormat("#.####");    //格式化为四位小数，按自己需求选择；
	          return String.valueOf(df.format(inputValue));      //返回String类型
	      } else {
	          // 返回字符串类型的值
	          return String.valueOf(hssfCell.getStringCellValue());
	      }
	  }

	/**
	 * 新建Excel文件，New Workbook
	 * @param excelType 可为null，Excel版本，可为2003（.xls）或者2007（.xlsx）,默认为2003版本
	 * @param sheetName 新建表单名称
	 * @param headList 表头List集合
	 * @return
	 */
	public Workbook createWorkBook(String excelType, String sheetName, List<String> headList){
		Workbook wb = null;
		/*创建文件*/
		if (excelType == null || excelType.endsWith("2003")) {
			/*操作Excel2003以前（包括2003）的版本，扩展名是.xls */
			wb = new HSSFWorkbook();
		}else if (excelType.endsWith("2007")){
			/*XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx */
			wb = new XSSFWorkbook();
		}else {   //默认为2003版本
			/*操作Excel2003以前（包括2003）的版本，扩展名是.xls */
			wb = new HSSFWorkbook();
		}
		/*Excel文件创建完毕*/
		CreationHelper createHelper = wb.getCreationHelper();  //创建帮助工具

		/*创建表单*/
		Sheet sheet = wb.createSheet(sheetName!=null?sheetName:"new sheet");
		// Note that sheet name is Excel must not exceed 31 characters（注意sheet的名字的长度不能超过31个字符，若是超过的话，会自动截取前31个字符）
		// and must not contain any of the any of the following characters:（不能包含下列字符）
		// 0x0000  0x0003  colon (:)  backslash (\)  asterisk (*)  question mark (?)  forward slash (/)  opening square bracket ([)  closing square bracket (])
        /*若是包含的话，会报错。但有一个解决此问题的方法，
        就是调用WorkbookUtil的createSafeSheetName(String nameProposal)方法来创建sheet name,
        若是有如上特殊字符，它会自动用空字符来替换掉，自动过滤。*/
        /*String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "过滤掉上面出现的不合法字符
        Sheet sheet3 = workbook.createSheet(safeName); //然后就创建成功了*/
		/*表单创建完毕*/

		//设置字体
		Font headFont = wb.createFont();
		headFont.setFontHeightInPoints((short)14);
		headFont.setFontName("Courier New");
		headFont.setItalic(false);
		headFont.setStrikeout(false);



		Row headRow = sheet.createRow(0); //第一行为头
		for (int i=0;i<headList.size();i++){  //遍历表头数据
			Cell cell = headRow.createCell(i);  //创建单元格
			cell.setCellValue(createHelper.createRichTextString(headList.get(i)));  //设置值

		}

		return wb;

	}

	public Workbook createWorkBook(String excelType, String sheetName, List<String> headList, List<Map<String,String>> dataList,String[] filedName){
		Workbook wb = null;
		/*创建文件*/
		if (excelType == null || excelType.endsWith("2003")) {
			/*操作Excel2003以前（包括2003）的版本，扩展名是.xls */
			wb = new HSSFWorkbook();
		}else if (excelType.endsWith("2007")){
			/*XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx */
			wb = new XSSFWorkbook();
		}else {   //默认为2003版本
			/*操作Excel2003以前（包括2003）的版本，扩展名是.xls */
			wb = new HSSFWorkbook();
		}
		/*Excel文件创建完毕*/
		CreationHelper createHelper = wb.getCreationHelper();  //创建帮助工具

		/*创建表单*/
		Sheet sheet = wb.createSheet(sheetName!=null?sheetName:"new sheet");
		// Note that sheet name is Excel must not exceed 31 characters（注意sheet的名字的长度不能超过31个字符，若是超过的话，会自动截取前31个字符）
		// and must not contain any of the any of the following characters:（不能包含下列字符）
		// 0x0000  0x0003  colon (:)  backslash (\)  asterisk (*)  question mark (?)  forward slash (/)  opening square bracket ([)  closing square bracket (])
        /*若是包含的话，会报错。但有一个解决此问题的方法，
        就是调用WorkbookUtil的createSafeSheetName(String nameProposal)方法来创建sheet name,
        若是有如上特殊字符，它会自动用空字符来替换掉，自动过滤。*/
        /*String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "过滤掉上面出现的不合法字符
        Sheet sheet3 = workbook.createSheet(safeName); //然后就创建成功了*/
		/*表单创建完毕*/

		//设置字体
		Font headFont = wb.createFont();
		headFont.setFontHeightInPoints((short)14);
		headFont.setFontName("Courier New");
		headFont.setItalic(false);
		headFont.setStrikeout(false);



		Row headRow = sheet.createRow(0); //第一行为头
		for (int i=0;i<headList.size();i++){  //遍历表头数据
			Cell cell = headRow.createCell(i);  //创建单元格
			cell.setCellValue(createHelper.createRichTextString(headList.get(i)));  //设置值
		}

		// 数据部分
		for (int i = 1; i <= dataList.size(); i++) {
			Row row = sheet.createRow(i); //获取行
			for (int j = 0; j < dataList.get(i-1).size(); j++) {
				Cell cell = row.createCell(j);  //创建单元格
				cell.setCellValue(createHelper.createRichTextString(dataList.get(i-1).get(filedName[j])));  //设置值
			}
		}

		return wb;

	}

	/**
	 　　* @description: 查询计量常量map
	 　　* @param String
	 　　* @return Map
	 　　* @throws
	 　　* @author znl
	 　　* @date 2021/8/6 16:33
	 　　*/
	public static Map getMap(String a) {
		Map map = new HashMap();
		if(StringUtil.isNotEmpty(a)) {
			a = "ms.ar.dimension." + a;
		} else {
			a = "ms.ar.dimension";
		}
		EiInfo inInfo = new EiInfo();
		inInfo.set(EiConstant.serviceId,"S_ED_02");
		inInfo.set("codeset", a);
		EiInfo outInfo = XServiceManager.call(inInfo);
		List<HashMap> list = (List) outInfo.get("list");
		list.forEach(it->{
			map.put(it.get("value").toString(),it.get("label").toString());
		});
		return map;
	}
}

