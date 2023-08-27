package com.baosight.wilp.fa.lb.web;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.fa.utils.OneSelfUtils;
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
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhaowei
 * @version V5.0.0
 * @date 2022/9/16 13:17
 * @todo 用实体类替换map [这个问题下次迭代时处理]
 */
@WebServlet({"/fa/FALBImport"})
@MultipartConfig
public class FALBExcelImport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String[] headList = new String[]{"*固定资产类别编码", "*固定资产类别名称", "*固定资产上级类别编码", "*使用年限(年)", "编码规则", "*序号", "备注"};

	/**
	 * 数据导出
	 *
	 * @param req
	 * @param resp
	 * @author zhaowei
	 * @date 2022/5/31 18:07
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//生成excel文件对象
		Workbook workBook = createWorkBook(headList, null);
		// 设置文件名
		resp.setHeader("Content-Disposition", "attachment; filename=FALB.xls");
		//获取输出流
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		//输出excel文件流
		workBook.write((OutputStream) servletOutputStream);
		servletOutputStream.close();
	}

	/**
	 * 数据导入
	 *
	 * @param req
	 * @param resp
	 * @author zhaowei
	 * @date 2022/5/31 18:08
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("fileUpload");
		String disposition = part.getHeader("Content-Disposition");
		String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
		resp.setContentType("application/json");
		if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {
			try {
				/*
				 * saveList：Excel中的行数据
				 * errorList：返回Excel失败信息列表
				 * resultList：返回Ecxcel成功信息列表
				 * faTypeCodeList：固定资产类别编码
				 * faTypeCodeExcelList：Excel中上级固定资产编码
				 */
				List<Map<String, Object>> saveList = new ArrayList<>();
				List<String[]> errorList = new ArrayList<>();
				List<String[]> resultList = new ArrayList<>();
				List<String> faTypeCodeList = new ArrayList<>();
				List<String> faTypeCodeExcelList = new ArrayList<>();
				List<String> diffFaTypeCodeExcelList;
				/*
				 * 从数据库查询存在的上级编码并保存
				 */
				EiInfo faTypeSqlEiInfo = OneSelfUtils.invoke(new EiInfo(), "FALB01", "faTypeEFSelect");
				EiBlock resultEiBlock = faTypeSqlEiInfo.getBlock("result");
				List<Map<String, Object>> rows = resultEiBlock.getRows();
				faTypeCodeList.add("root");
				for (Map map : rows) {
					faTypeCodeList.add((String) map.get("value"));
				}
				Workbook wb = WorkbookFactory.create(part.getInputStream());
				Sheet sheet = wb.getSheetAt(0);
				//数据解析
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					// 获取Excel中的关键列信息
					parseRow(row, faTypeCodeList, faTypeCodeExcelList);
				}
				// 数据库的集合与Excel的集合
				// faTypeCodeList，faTypeCodeExcelList集合去重
				faTypeCodeList = faTypeCodeList.stream().distinct().collect(Collectors.toList());
				faTypeCodeExcelList = faTypeCodeExcelList.stream().distinct().collect(Collectors.toList());
				diffFaTypeCodeExcelList = faTypeCodeExcelList;
				diffFaTypeCodeExcelList.removeAll(faTypeCodeList);
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					// 校验Excel数据
					parseRow(row, errorList, resultList, saveList, diffFaTypeCodeExcelList);
				}
				//处理解析后的数据
				if (saveList.size() > 0) {
					EiInfo info = new EiInfo();
					info.setRows("info", saveList);
					OneSelfUtils.invoke(info, "FALB0101", "saveAessettypeByExcel");
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
					List<String> hList = new ArrayList(Arrays.asList(headList));
					hList.add("成功状态");
					Workbook workBook = createWorkBook(hList.toArray(new String[hList.size()]), resultList);
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					workBook.write(bout);
					byte[] bytes = bout.toByteArray();
					String base64 = Base64Utils.encodeToString(bytes);
					resp.getWriter().print("{\"msg\":\"all\",\"base64\":\"" + base64 + "\"}");
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
	 * 创建excel文档
	 *
	 * @param headList
	 * @param dataList
	 * @return org.apache.poi.ss.usermodel.Workbook
	 * @author zhaowei
	 * @date 2022/5/31 16:42
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

	/*
	 * Excel表格元素获取
	 * @author zhaowei
	 * @date 2022/9/16 13:46
	 * @param row
	 * @param faTypeCodeList
	 * @param faTypeCodeExcelList
	 */
	private void parseRow(Row row, List<String> faTypeCodeList, List<String> faTypeCodeExcelList) {
		//判断空行，是空行则返回
		if (isEmptyRow(row)) {
			return;
		}
		//获取对应列Excel数据
		String typeCode = getCellValue(row.getCell(0)).trim();
		String parentId = getCellValue(row.getCell(2)).trim();
		faTypeCodeList.add(typeCode);
		faTypeCodeExcelList.add(parentId);
	}

	/*
	 * Excel表格列校验
	 * @author zhaowei
	 * @date 2022/9/16 13:38
	 * @param row
	 * @param errorList
	 * @param resultList
	 * @param saveList
	 * @todo 参数放到一个配置类中 [这个问题下次迭代时处理]
	 */
	private void parseRow(Row row, List<String[]> errorList, List<String[]> resultList, List<Map<String, Object>> saveList, List<String> diffFaTypeCodeExcelList) {
		//判断空行
		if (isEmptyRow(row)) {
			return;
		}
		//获取Excel数据
		String typeCode = getCellValue(row.getCell(0)).trim();
		String typeName = getCellValue(row.getCell(1)).trim();
		String parentId = getCellValue(row.getCell(2)).trim();
		String useYears = getCellValue(row.getCell(3)).trim();
		String codeRule = getCellValue(row.getCell(4)).trim();
		String sortNo = getCellValue(row.getCell(5)).trim();
		String remark = getCellValue(row.getCell(6)).trim();

		String errorMsg = "";
		//校验
		if (StringUtils.isBlank(typeCode)) {
			errorMsg = errorMsg + "资产类别编码不能为空\r\n";
		}
		if (typeCode.length() >= 20) {
			errorMsg = errorMsg + "资产类别编码不能超过20位\r\n";
		}
		if (StringUtils.isBlank(typeName)) {
			errorMsg = errorMsg + "资产类别名称不能为空\r\n";
		}
		if (typeName.length() >= 100) {
			errorMsg = errorMsg + "资产类别名称不能为空不能超过100位\r\n";
		}
		if (parentId.length() >= 20) {
			errorMsg = errorMsg + "上级资产类别编码不能超过20位\r\n";
		}
		if (StringUtils.isBlank(parentId)) {
			errorMsg = errorMsg + "上级资产类别编码不能为空\r\n";
		}
		if (StringUtils.isBlank(useYears)) {
			errorMsg = errorMsg + "资产使用年限不能为空\r\n";
		}
		if (StringUtils.isBlank(sortNo)) {
			errorMsg = errorMsg + "资产排序不能为空\r\n";
		}
		if (remark.length() >= 256) {
			errorMsg = errorMsg + "备注不能超过256位\r\n";
		}
		if (diffFaTypeCodeExcelList.contains(parentId)) {
			errorMsg = errorMsg + "上级资产类别编码取值在系统中或文档中不存在\r\n";
		}
		//判断是否存在错误信息，存在，向错误数据集合中添加；不存在，表示数据没有问题，添加到数据保存集合
		if (errorMsg.length() > 0) {
			errorList.add(new String[]{typeCode, typeName, parentId, useYears, codeRule, sortNo, remark, errorMsg});
		} else {
			resultList.add(new String[]{typeCode, typeName, parentId, useYears, codeRule, sortNo, remark, "成功"});
			Map<String, Object> param = new HashMap<>(8);
			param.put("typeCode", typeCode);
			param.put("typeName", typeName);
			param.put("parentId", parentId);
			param.put("useYears", Integer.valueOf(useYears));
			param.put("codeRule", codeRule);
			param.put("sortNo", Integer.valueOf(sortNo));
			param.put("remark", remark);
			saveList.add(param);
		}
	}

	/**
	 * 判断是否是空行
	 *
	 * @param row
	 * @return java.lang.Boolean
	 * @author zhaowei
	 * @date 2022/5/31 18:11
	 */
	private Boolean isEmptyRow(Row row) {
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取Excel单元格数据
	 *
	 * @param cell
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/5/31 18:13
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = null;
		if (cell != null) {
			//判断cell类型
			switch (cell.getCellType()) {
				//数字类型
				case Cell.CELL_TYPE_NUMERIC: {
					cellValue = String.valueOf(BigDecimal.valueOf(cell.getNumericCellValue()).setScale(0, BigDecimal.ROUND_DOWN));
					break;
				}
				//字符串类型类型
				case Cell.CELL_TYPE_STRING: {
					cellValue = cell.getRichStringCellValue().getString();
					break;
				}
				default:
					cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}

	/**
	 * 数字校验
	 *
	 * @param num
	 * @return boolean
	 * @author zhaowei
	 * @date 2022/5/31 18:15
	 */
	private static boolean isNumeric(String num) {
		if (StringUtils.isBlank(num)) {
			return false;
		}
		return Pattern.matches("^[0-9]+(\\.[0-9]{1,2})?$", num);
	}
}
