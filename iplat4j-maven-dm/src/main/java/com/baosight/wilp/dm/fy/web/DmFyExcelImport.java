package com.baosight.wilp.dm.fy.web;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
//import com.baosight.wilp.mt.common.MTUtils;
//import com.baosight.wilp.mt.sx.domain.MtSx;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.fy.domain.DormsRoomFee;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
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
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 宿舍费用导入Servlet
 *
 * <p>1.生成数据导出模板</p>
 * <p>2.数据导入</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  DmFyExcelImport.java
 * @ClassName:  DmFyExcelImport
 * @Package com.baosight.wilp.dm.fy.web
 * @author fangzekai
 * @date:   2022年3月22日 下午10:38:39
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
@WebServlet({"/dmfyImport"})
@MultipartConfig
public class DmFyExcelImport extends HttpServlet {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String[] headList = new String[]{"工号", "姓名", "水费", "电费", "是否已退宿", "备注" ,
			"退房租", "退管理费", "退水费", "退电费", "补房租", "补管理费", "补水费", "补电费", "其他费用" };

	/**
	 * 生成数据导出模板
	 *
	 * <p>创建excel文件对象，返回页面</p>
	 *
	 * <p>Title: doGet</p>
	 * <p>Description: </p>
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//生成excel文件对象
		Workbook workBook = createWorkBook(headList, null);
		// 设置文件名
		resp.setHeader("Content-Disposition", "attachment; filename=dormitory_fee.xls");
		//获取输出流
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		//输出excel文件流
		workBook.write((OutputStream) servletOutputStream);
		servletOutputStream.close();
	}

	/**
	 * 数据导入
	 * <p>1.获取上传的Excel文件,判断文件的格式，不是excel文件，返回错误信息
	 *    2.解析excel文件，获取数据
	 *    3.保存解析后正常的数据
	 *    4.将解析后不正常的数据重新转成excel文件base64字符串
	 *    5.返回
	 * </p>
	 *
	 * @pram req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("all")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("fileUpload");
		String disposition = part.getHeader("Content-Disposition");
		String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
		resp.setContentType("application/json");
		if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {
			try {
				List saveList = new ArrayList<>();
				List<String[]> errorList = new ArrayList<>();
				Workbook wb = WorkbookFactory.create(part.getInputStream());
				Sheet sheet = wb.getSheetAt(0);
				boolean emptyFlag = true;
				//数据解析
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					// 将是否为空行结果标记给flag，空行为true.
					emptyFlag = isRowEmpty(row);
					if (!emptyFlag){
						parseRow(row, errorList, saveList);
						emptyFlag = true;
					}
				}
    	         //处理解析后的数据
    	         if(saveList.size() > 0){
    	        	 EiInfo info = new EiInfo();
    	        	 info.set("list", saveList);
    	        	 DMUtils.invoke(info, "DMFY01", "saveBatch");
    	         }
				//错误数据返回
				if (errorList.size() > 0) {
					List<String> hList = new ArrayList(Arrays.asList(headList));
					hList.add("错误信息");
					Workbook workBook = createWorkBook(hList.toArray(new String[hList.size()]), errorList);
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					workBook.write(bout);
					byte[] bytes = bout.toByteArray();
					String base64 = Base64Utils.encodeToString(bytes);
					resp.getWriter().print("{\"msg\":\"part\",\"base64\":\"" + base64 + "\"}");
				} else {
					resp.getWriter().print("{\"msg\":\"all\"}");
				}
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().print("{\"msg\":\"error\"}");
			}
		} else {
			resp.getWriter().print("{\"msg\":\"error\"}");
		}
	}

	/**
	 * 判断是否为空行的方法
	 *
	 * @Title:isRowEmpty
	 * @Param Row
	 * @return: Row
	 */
	public static boolean isRowEmpty(Row row){
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK){
				return false;
			}else {
				break;
			}
		}
		return true;
	}


	/**
	 * 创建excel文档
	 *
	 * <p>使用API创建一个Excel表格</p>
	 *
	 * @Title: createWorkBook
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param headList ： 表格头数组
	 * @param:  @param dataList : 数据list
	 * @param:  @return
	 * @return: Workbook
	 * @throws
	 */
	private Workbook createWorkBook(String[] headList, List<String[]> dataList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建sheet,并给sheet赋值
		HSSFSheet sheet = workbook.createSheet("sheet1");
		//创建表格头行
		HSSFRow headRow = sheet.createRow(0);
		//设置样式
		HSSFCellStyle style = workbook.createCellStyle();
		//创建一个居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置边框
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		//设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");
		//表头赋值
		for (int i = 0; i < headList.length; i++) {
			HSSFCell headCell = headRow.createCell(i);
			headCell.setCellValue(headList[i]);
			//设置单元格样式
			headCell.setCellStyle(style);
			sheet.setColumnWidth(i, headList[i].getBytes().length * 2 * 256);
		}
		//数据赋值
		if (dataList != null && dataList.size() > 0) {
			for (int j = 0; j < dataList.size(); j++) {
				//创建数据行
				HSSFRow row = sheet.createRow(j + 1);
				//获取每行数据
				String[] data = dataList.get(j);
				for (int k = 0; k < headList.length; k++) {
					HSSFCell dataCell = row.createCell(k);
					dataCell.setCellValue(data[k]);
					dataCell.setCellStyle(style);
				}
			}
		}
		return workbook;
	}

	/**
	 * 解析每行数据
	 *
	 * <p>
	 *     1.从excel数据行中获取每个单元格的数据
	 *     2.对指定单元格的数据进行校验
	 *     3.校验通过的数据添加到保存集合，校验不通过数据添加错误数据集合
	 * </p>
	 *
	 * @Title: parseRow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param row ： excel数据行对象
	 * @param:  @param errorList ： 错误数据集合
	 * @param:  @param saveList ： 正确数据集合
	 * @return: void
	 * @throws
	 */
	private void parseRow(Row row, List<String[]> errorList, List saveList) {
		row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
		String manNo = getCellValue(row.getCell(0));
		String manName = getCellValue(row.getCell(1));
		String waterPriece = getCellValue(row.getCell(2));
		String elecPriece = getCellValue(row.getCell(3));
		String outFlag = getCellValue(row.getCell(4));
		String currentMonth = new SimpleDateFormat("yyyy-MM").format(new Date()); /* 获取当前月份 */
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		String getNextMonth = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());      /* 获取下个月份 */
		String remark = getCellValue(row.getCell(5));
		String returnRent = getCellValue(row.getCell(6)) == "" ? "0" : getCellValue(row.getCell(6));
		String returnManageFee = getCellValue(row.getCell(7)) == "" ? "0" : getCellValue(row.getCell(7));
		String returnWaterPriece = getCellValue(row.getCell(8)) == "" ? "0" : getCellValue(row.getCell(8));
		String returnElecPriece = getCellValue(row.getCell(9)) == "" ? "0" : getCellValue(row.getCell(9));
		String replenishRent = getCellValue(row.getCell(10)) == "" ? "0" : getCellValue(row.getCell(10));
		String replenishManageFee = getCellValue(row.getCell(11)) == "" ? "0" : getCellValue(row.getCell(11));
		String replenishWaterPriece = getCellValue(row.getCell(12)) == "" ? "0" : getCellValue(row.getCell(12));
		String replenishElecPriece = getCellValue(row.getCell(13)) == "" ? "0" : getCellValue(row.getCell(13));
		String extraCharges = getCellValue(row.getCell(14)) == "" ? "0" : getCellValue(row.getCell(14));
		String errorMsg = "";
		Map<String, Object> manInfo = new HashMap<>();
		//校验
		if (StringUtils.isBlank(manNo)) {
			errorMsg = errorMsg + "工号不能为空\r\n";
		} else {
			Map<String, Object> getInfo = getmanInfo(manNo);
			if(getInfo == null || getInfo.isEmpty()){
				errorMsg = errorMsg + "该工号没有相应的入住信息\r\n";
			} else {
//				if(!roomName.equals(manInfo.get("roomName"))){
//					errorMsg = errorMsg + "该宿舍的全程与当前工号的入住房间不符\r\n";
//				}
				manInfo = getInfo;
			}
		}
		if (StringUtils.isBlank(waterPriece)){
			errorMsg = errorMsg + "水费不能为空\r\n";
		}else{
			if (!isNumeric(waterPriece)){
				errorMsg = errorMsg + "水费格式不正确\r\n";
			}
		}
		if (StringUtils.isBlank(elecPriece)){
			errorMsg = errorMsg + "电费不能为空\r\n";
		}else{
			if (!isNumeric(elecPriece)){
				errorMsg = errorMsg + "电费格式不正确\r\n";
			}
		}
		if (!"0".equals(returnRent)){
			if (!isNumeric(returnRent)){
				errorMsg = errorMsg + "退房租格式不正确\r\n";
			}
		}
		if (!"0".equals(returnManageFee)){
			if (!isNumeric(returnManageFee)){
				errorMsg = errorMsg + "退管理费格式不正确\r\n";
			}
		}
		if (!"0".equals(returnWaterPriece)){
			if (!isNumeric(returnWaterPriece)){
				errorMsg = errorMsg + "退水费格式不正确\r\n";
			}
		}
		if (!"0".equals(returnElecPriece)){
			if (!isNumeric(returnElecPriece)){
				errorMsg = errorMsg + "退电费格式不正确\r\n";
			}
		}
		if (!"0".equals(replenishRent)){
			if (!isNumeric(replenishRent)){
				errorMsg = errorMsg + "补房租格式不正确\r\n";
			}
		}
		if (!"0".equals(replenishManageFee)){
			if (!isNumeric(replenishManageFee)){
				errorMsg = errorMsg + "补管理费格式不正确\r\n";
			}
		}
		if (!"0".equals(replenishWaterPriece)){
			if (!isNumeric(replenishWaterPriece)){
				errorMsg = errorMsg + "补水费格式不正确\r\n";
			}
		}
		if (!"0".equals(replenishElecPriece)){
			if (!isNumeric(replenishElecPriece)){
				errorMsg = errorMsg + "补电费格式不正确\r\n";
			}
		}
		if (!"0".equals(extraCharges)){
			if (!isNumeric(extraCharges)){
				errorMsg = errorMsg + "其他费用格式不正确\r\n";
			}
		}
		if((manInfo != null || !manInfo.isEmpty()) && !StringUtils.isBlank(currentMonth)){
			String getCurrentMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
			if (!currentMonth.equals(getCurrentMonth)){
				EiInfo info = new EiInfo();
				info.set("roomId", manInfo.get("roomId"));
				info.set("manId", manInfo.get("manId"));
				info.set("currentMonth", currentMonth);
				info.set(EiConstant.serviceName, "DMFY01");
				info.set(EiConstant.methodName, "queryIsExistFee");
				EiInfo outInfo = XLocalManager.call(info);
				int isExits = (int) outInfo.get("count");
				if (isExits>0){
					errorMsg = errorMsg + "该用户当前月份已存在费用信息，无法修改往月费用信息，请联系管理员\r\n";
				}
			}
		}
		//判断是否存在错误信息，存在，向错误数据集合中添加；不存在，表示数据没有问题，添加到数据保存集合
		if(errorMsg.length() > 0){
			errorList.add(new String[]{manNo,manName,waterPriece,elecPriece,outFlag,remark,
					returnRent,returnManageFee,returnWaterPriece,returnElecPriece,replenishRent,replenishManageFee,replenishWaterPriece,replenishElecPriece,extraCharges,errorMsg});
		}
		else {
			Map<String, Object> pMap = new HashMap<>();
//			pMap.put("id",UUID.randomUUID().toString());
			pMap.put("roomId",manInfo.get("roomId"));
			pMap.put("manId",manInfo.get("manId"));
			pMap.put("waterPriece",waterPriece);
			pMap.put("elecPriece",elecPriece);
			pMap.put("waterDegree",0);
			pMap.put("elecDegree",0);
			if ("是".equals(outFlag)){
				pMap.put("currentRent",0);
				pMap.put("currentManageFee",0);
			}else {
				String actualRent = (String) manInfo.get("actualRent");
				if ("".equals(actualRent)){
					pMap.put("currentRent",0);
				}else {
					pMap.put("currentRent", NumberUtils.createBigDecimal(actualRent));
				}
				String actualManageFee = (String) manInfo.get("actualManageFee");
				if ("".equals(actualManageFee)){
					pMap.put("currentManageFee",0);
				}else {
					pMap.put("currentManageFee", NumberUtils.createBigDecimal(actualManageFee));
				}
			}
			pMap.put("currentMonth",currentMonth);
			pMap.put("settlementMonth",getNextMonth);
			pMap.put("remark",remark);
			pMap.put("returnRent",returnRent);
			pMap.put("returnManageFee",returnManageFee);
			pMap.put("returnWaterPriece",returnWaterPriece);
			pMap.put("returnElecPriece",returnElecPriece);
			pMap.put("replenishRent",replenishRent);
			pMap.put("replenishManageFee",replenishManageFee);
			pMap.put("replenishWaterPriece",replenishWaterPriece);
			pMap.put("replenishElecPriece",replenishElecPriece);
			pMap.put("extraCharges",extraCharges);
			String operator = UserSession.getUser().getUsername();        /* 操作人工号*/
			Map<String, Object> createUserInfo = DMUtils.getUserInfo(operator);
			String operationName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*操作人名称*/
			pMap.put("operator",operator);
			pMap.put("operationName",operationName);
			pMap.put("operationTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			saveList.add(pMap);
		}
	}

	/**
	 * 判断工号相关入住信息是否存在，并根据工号获取相关信息内容。
	 * @Title: getmanInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param manNo ： 工号
	 * @param:  @return
	 * @return: Map<String,Object>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getmanInfo(String manNo) {
		EiInfo info = new EiInfo();
		List<Map<String, Object>> rows = new ArrayList<>();
		info.set("manNo", manNo);
		info.set(EiConstant.serviceName, "DMFY01");
		info.set(EiConstant.methodName, "isExistManInfo");
		EiInfo outInfo = XLocalManager.call(info);
		if (outInfo.getBlock("manInfo") != null){
			rows = outInfo.getBlock("manInfo").getRows();
		}
		if(rows !=null && rows.size() > 0){
			return rows.get(0);
		}
		return null;
	}

	/**
	 * 获取Excel单元格数据
	 * @Title: getCellValue
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param cell ： excel 单元格对象
	 * @param:  @return
	 * @return: String  ： 单元格数据
	 * @throws
	 */
	public static String getCellValue(Cell cell){
        String cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
				//数字类型
            	case Cell.CELL_TYPE_NUMERIC:{
            		cellValue = String.valueOf(cell.getNumericCellValue());
            		break;
            	}
				//字符串类型类型
	            case Cell.CELL_TYPE_STRING:{
	                cellValue = cell.getRichStringCellValue().getString();
	                break;
	            }
	            default:
	            	cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
	
	/**
	 * 数字校验
	 * @Title: isNumeric
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param num ： 单元格数据
	 * @param:  @return
	 * @return: boolean  ： true=是数字，false=不是数字
	 * @throws
	 */
	private static boolean isNumeric(String num) {
		if(StringUtils.isBlank(num)){
			return false;
		}
		return Pattern.matches("^[0-9]+(\\.[0-9]{1,2})?$", num);
	}
}
