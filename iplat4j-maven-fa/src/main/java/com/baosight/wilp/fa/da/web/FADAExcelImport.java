package com.baosight.wilp.fa.da.web;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhaowei
 * @date 2022年05月31日 16:39
 */
@WebServlet({"/fa/FADAImport"})
@MultipartConfig
public class FADAExcelImport extends HttpServlet {

	private static ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>();

	private static final long serialVersionUID = 1L;

	private String[] headList = new String[]{"*固定资产名称", "*上级固资类别名称", "*固资类别名称", "*安装位置", "*使用科室名称"};

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
		resp.setHeader("Content-Disposition", "attachment; filename=FADA.xls");
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
				 * goodsNameList:固定资产名称集合
				 * goodsClassifyNameList：上级固资类别名称集合
				 * goodsTypeNameList：固资类别名称集合
				 * installLocationList：安装位置集合
				 * deptNameList：使用科室集合
				 */
				List<String[]> errorList = new ArrayList<>(), resultList = new ArrayList<>();
				List<String> goodsClassifyNameList = new ArrayList<>(), goodsTypeNameList = new ArrayList<>(), installLocationList = new ArrayList<>(), deptNameList = new ArrayList<>(),
						diffGoodsClassifyNameList = new ArrayList<>(), diffGoodsTypeNameList = new ArrayList<>(), diffInstallLocationList = new ArrayList<>(), diffDeptNameList = new ArrayList<>();
				List<Map<String, Object>> deptNameRows = new ArrayList<>(), spotNameRows = new ArrayList<>(), saveList = new ArrayList<>();
				/*
				 * 调用框架微服务
				 */
				EiInfo deptNameEiInfo = BaseDockingUtils.getDeptAll(new EiInfo());
				Map<String, Object> deptNameEiInfoAttr = deptNameEiInfo.getAttr();
				if (deptNameEiInfoAttr.containsKey("result")) {
					deptNameRows = (List<Map<String, Object>>) deptNameEiInfoAttr.get("result");
				}

				EiInfo spotNameEiInfo = BaseDockingUtils.getSpotByDept(new EiInfo());
				Map<String, Object> spotNameEiInfoAttr = spotNameEiInfo.getAttr();
				if (spotNameEiInfoAttr.containsKey("result")) {
					spotNameRows = (List<Map<String, Object>>) spotNameEiInfo.get("result");
				}

				EiInfo faTypeSqlEiInfo = OneSelfUtils.invoke(new EiInfo(), "FALB01", "faTypeEFSelect");
				EiBlock faTypeSqlEiBlock = faTypeSqlEiInfo.getBlock("result");
				List<Map<String, Object>> faTypeRows = faTypeSqlEiBlock.getRows();
				faTypeRows.add(new HashMap<String, Object>() {{
					put("text", "根节点");
					put("value", "root");
				}});

				Workbook wb = WorkbookFactory.create(part.getInputStream());
				Sheet sheet = wb.getSheetAt(0);
				String goodsNum = OneSelfUtils.publicCreateCode(FixedAssetsEum.DA.getAcronym());
				goodsNum = goodsNum.substring(0, 12) + String.format("%04d", Integer.valueOf(goodsNum.substring(12)) - 1);
				concurrentHashMap.put("goodsNum",goodsNum);
				// 数据解析
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					parseRow(row, goodsClassifyNameList, goodsTypeNameList, installLocationList, deptNameList);
				}
				differRows(diffGoodsClassifyNameList, diffGoodsTypeNameList, diffInstallLocationList, diffDeptNameList, faTypeRows, spotNameRows, deptNameRows);
				// 数据校验
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					concurrentHashMap.put("goodsNum",concurrentHashMap.get("goodsNum").substring(0, 12) + String.format("%04d", Integer.valueOf(concurrentHashMap.get("goodsNum").substring(12)) + 1));
					parseRow(row, errorList, saveList, resultList, diffGoodsClassifyNameList, diffGoodsTypeNameList, diffInstallLocationList, diffDeptNameList, faTypeRows, spotNameRows, deptNameRows);
				}
				//处理解析后的数据
				if (saveList.size() > 0) {
					EiInfo info = new EiInfo();
					ArrayList<Object> list = new ArrayList<>();
					info.setRows("info", saveList);
					OneSelfUtils.invoke(info, "FADA0101", "saveFaInfoByExcel");
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

	private void differRows(List<String> diffGoodsClassifyNameList, List<String> diffGoodsTypeNameList, List<String> diffInstallLocationList, List<String> diffDeptNameList, List<Map<String, Object>> faTypeRows, List<Map<String, Object>> spotNameRows, List<Map<String, Object>> deptNameRows) {
		faTypeRows.stream().forEach(map -> {
			diffGoodsClassifyNameList.add((String) map.get("text"));
			diffGoodsTypeNameList.add((String) map.get("text"));
		});
		spotNameRows.stream().forEach(map -> {
			diffInstallLocationList.add((String) map.get("spotName"));
		});
		deptNameRows.stream().forEach(map -> {
			diffDeptNameList.add((String) map.get("deptName"));
		});
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

	private void parseRow(Row row, List<String> goodsClassifyNameList, List<String> goodsTypeNameList, List<String> installLocationList, List<String> deptNameList) {
		//判断空行
		if (isEmptyRow(row)) {
			return;
		}
		//获取Excel数据
		String goodsClassifyName = getCellValue(row.getCell(1));
		goodsClassifyNameList.add(goodsClassifyName);
		String goodsTypeName = getCellValue(row.getCell(2));
		goodsTypeNameList.add(goodsTypeName);
		String installLocation = getCellValue(row.getCell(3));
		installLocationList.add(installLocation);
		String deptName = getCellValue(row.getCell(4));
		deptNameList.add(deptName);
	}

	private void parseRow(Row row, List<String[]> errorList, List<Map<String, Object>> saveList, List<String[]> resultList, List<String> diffGoodsClassifyNameList, List<String> diffGoodsTypeNameList, List<String> diffInstallLocationList, List<String> diffDeptNameList,List<Map<String,Object>> faTypeRows,List<Map<String,Object>> spotNameRows,List<Map<String,Object>> deptNameRows) {
		//判断空行
		if (isEmptyRow(row)) {
			return;
		}
		//获取Excel数据
		String goodsName = getCellValue(row.getCell(0));
		String goodsClassifyName = getCellValue(row.getCell(1));
		String goodsTypeName = getCellValue(row.getCell(2));
		String installLocation = getCellValue(row.getCell(3));
		String deptName = getCellValue(row.getCell(4));

		String errorMsg = "";
		//校验
		if (StringUtils.isBlank(goodsName)) {
			errorMsg = errorMsg + "固定资产名称不能为空\r\n";
		}
		if (goodsName.length() >= 40) {
			errorMsg = errorMsg + "固定资产名称不能超过40位\r\n";
		}
		if (StringUtils.isBlank(goodsTypeName)) {
			errorMsg = errorMsg + "固资类别名称不能为空\r\n";
		}
		if (StringUtils.isBlank(goodsClassifyName)) {
			errorMsg = errorMsg + "上级固资类别名称不能为空\r\n";
		}
		if (StringUtils.isBlank(deptName)) {
			errorMsg = errorMsg + "使用科室名称不能为空\r\n";
		}
		if (!diffGoodsTypeNameList.contains(goodsTypeName)){
			errorMsg = errorMsg + "固资类别名称不存在\r\n";
		}
		if (!diffGoodsClassifyNameList.contains(goodsClassifyName)){
			errorMsg = errorMsg + "上级固资类别名称不存在\r\n";
		}
		if (!diffInstallLocationList.contains(installLocation)){
			errorMsg = errorMsg + "安装位置不存在\r\n";
		}
		if (!diffDeptNameList.contains(deptName)){
			errorMsg = errorMsg + "使用科室名称不存在";
		}
		//判断是否存在错误信息，存在，向错误数据集合中添加；不存在，表示数据没有问题，添加到数据保存集合
		if (errorMsg.length() > 0) {
			errorList.add(new String[]{goodsName, goodsClassifyName, goodsTypeName, installLocation, deptName, errorMsg});
		} else {
			Map<String, Object> param = new HashMap<>(16);
			param.put("goodsNum", concurrentHashMap.get("goodsNum"));
			param.put("goodsName", goodsName);
			param.put("cardStatus", 0);
			param.put("amount", 1);
			param.put("archiveFlag", 1);
			param.put("lockFlag", 0);
			param.put("statusCode", "00");
			param.put("inAccountStatus", 0);
			param.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
			param.put("recCreateTime", DateUtils.curDateTimeStr19());
			List<Map<String, Object>> text = faTypeRows.stream().filter(map -> goodsClassifyName.equals(map.get("text"))).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(text)){
				param.put("goodsClassifyName",text.get(0).get("text"));
				param.put("goodsClassifyCode",text.get(0).get("value"));
			}
			List<Map<String, Object>> text1 = faTypeRows.stream().filter(map -> goodsTypeName.equals(map.get("text"))).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(text1)){
				param.put("goodsTypeName",text1.get(0).get("text"));
				param.put("goodsTypeCode",text1.get(0).get("value"));
			}
			List<Map<String, Object>> spotName = spotNameRows.stream().filter(map -> installLocation.equals(map.get("spotName"))).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(spotName)){
				param.put("build",spotName.get(0).get("building"));
				param.put("floor",spotName.get(0).get("floor"));
				param.put("installLocationNum",spotName.get(0).get("spotNum"));
				param.put("installLocation",spotName.get(0).get("spotName"));
			}
			List<Map<String, Object>> deptName1 = deptNameRows.stream().filter(map -> deptName.equals(map.get("deptName"))).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(deptName1)){
				param.put("deptNum",deptName1.get(0).get("deptNum"));
				param.put("deptName",deptName1.get(0).get("deptName"));
			}
			resultList.add(new String[]{goodsName, goodsClassifyName, goodsTypeName, installLocation, deptName, "成功"});
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
					cell.setCellType(1);
					cellValue = String.valueOf(cell.getStringCellValue());
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
