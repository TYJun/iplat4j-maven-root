package com.baosight.wilp.dm.rz.web;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.dm.common.DMUtils;
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
 * 宿舍入住信息导入Servlet
 *
 * <p>1.生成数据导出模板</p>
 * <p>2.入住信息数据导入</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  DmRzExcelImport.java
 * @ClassName:  DmRzExcelImport
 * @Package com.baosight.wilp.dm.rz.web
 * @author fangzekai
 * @date:   2022年8月30日 下午10:38:39
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
@WebServlet({"/dmrzImport"})
@MultipartConfig
public class DmRzExcelImport extends HttpServlet {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String[] headList = new String[]{"工号", "姓名", "性别（0：女 1：男）", "年龄", "电话", "身份证号" ,
			"是否已婚", "学历", "学年制", "职工属性", "是否联网", "是否已办暂住证", "户口所在地", "入职时间", "预计入住时间", "预计退宿时间", "备注" };

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
		resp.setHeader("Content-Disposition", "attachment; filename=dormRZData.xls");
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
    	        	 DMUtils.invoke(info, "DMRZ01", "saveBatch");
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
		if (row.getCell(0)!=null){
			row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
		}

		String manNo = getCellValue(row.getCell(0));
		String manName = getCellValue(row.getCell(1));
		if (row.getCell(2)!=null){
			row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		String sex = getCellValue(row.getCell(2));
		if (row.getCell(3)!=null){
			row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		String age = getCellValue(row.getCell(3)) == "" ? "0" : getCellValue(row.getCell(3));
		if (row.getCell(4)!=null){
			row.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		String phone = getCellValue(row.getCell(4));
		if (row.getCell(5)!=null){
			row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		String identityCard = getCellValue(row.getCell(5));
		String maritalStatus = getCellValue(row.getCell(6));
		String educationBackground = getCellValue(row.getCell(7));
		String academicYear = getCellValue(row.getCell(8));
		String employmentNature = getCellValue(row.getCell(9)) == "" ? "本院职工" : getCellValue(row.getCell(9));
		String isNetwork = getCellValue(row.getCell(10));
		String isStay = getCellValue(row.getCell(11));
		String permanentResidence = getCellValue(row.getCell(12));
		String hiredate = getCellValue(row.getCell(13));
		String estimatedInDate = getCellValue(row.getCell(14));
		String estimatedOutDate = getCellValue(row.getCell(15));
		String note = getCellValue(row.getCell(16));
		// 默认宿舍科室
		String deptNum = "NO00012";
		String deptName = findDeptInfo(deptNum);
		String errorMsg = "";
		Map<String, Object> manInfo = new HashMap<>();
		//校验
		if (StringUtils.isBlank(manNo)) {
			errorMsg = errorMsg + "工号不能为空\r\n";
		} else {
			manInfo = getNo99manInfo(manNo);
			if(manInfo != null && manInfo.size() > 0){
				errorMsg = errorMsg + "该工号已存在入住信息\r\n";
			}
		}
		if (StringUtils.isBlank(sex)){
			errorMsg = errorMsg + "性别不能为空\r\n";
		}else{
			if (!isNumeric(sex)){
				errorMsg = errorMsg + "性别录入的格式不正确\r\n";
			}
		}
		if (StringUtils.isNotBlank(age)){
			if (!isNumeric(age)){
				errorMsg = errorMsg + "年龄录入的格式不正确\r\n";
			}
		}
		if (StringUtils.isBlank(employmentNature)){
			errorMsg = errorMsg + "职工属性不能为空\r\n";
		}
		//判断是否存在错误信息，存在，向错误数据集合中添加；不存在，表示数据没有问题，添加到数据保存集合
		if(errorMsg.length() > 0){
			errorList.add(new String[]{manNo,manName,sex,age,phone,identityCard,
					maritalStatus,educationBackground,academicYear,employmentNature,isNetwork,isStay,
					permanentResidence,hiredate,estimatedInDate,estimatedOutDate,errorMsg});
		}
		else {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("id",UUID.randomUUID().toString());
			pMap.put("manNo",manNo);
			pMap.put("manName",manName);
			pMap.put("statusCode","00");
			pMap.put("deptNum",deptNum);
			pMap.put("deptName",deptName);
			pMap.put("sex",sex);
			pMap.put("age",age);
			pMap.put("phone",phone);
			pMap.put("identityCard",identityCard);
			pMap.put("maritalStatus",maritalStatus);
			pMap.put("educationBackground",educationBackground);
			pMap.put("academicYear",academicYear);
			pMap.put("employmentNature",employmentNature);
			pMap.put("isNetwork",isNetwork);
			pMap.put("isStay",isStay);
			pMap.put("permanentResidence",permanentResidence);
			pMap.put("hiredate",hiredate);
			pMap.put("estimatedInDate",estimatedInDate);
			pMap.put("estimatedOutDate",estimatedOutDate);
			pMap.put("note",note);
			String recCreator = UserSession.getUser().getUsername();        /* 操作人工号*/
			Map<String, Object> createUserInfo = DMUtils.getUserInfo(recCreator);
			String recCreateName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*操作人名称*/
			pMap.put("recCreator",recCreator);
			pMap.put("recCreateName",recCreateName);
			pMap.put("recCreateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			saveList.add(pMap);
		}
	}

	/**
	 * 获取入住申请表中已存在该工号的入住信息 流程结束(状态为99)的人除外，得出该工号是否有入住信息。
	 * @Title: getNo99manInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param manNo:工号
	 * @param:  @return
	 * @return: Map<String,Object>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getNo99manInfo(String manNo) {
		EiInfo info = new EiInfo();
		List<Map<String, Object>> rows = new ArrayList<>();
		info.set("manNo", manNo);
		info.set(EiConstant.serviceName, "DMRZ01");
		info.set(EiConstant.methodName, "showNo99ManInfo");
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
					if(HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
					}else {
						cellValue = String.valueOf(cell.getNumericCellValue());
					}
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

	/**
	 * 根据科室编码查询科室相关信息
	 * @Title: findDeptInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum:科室编码
	 * @param:  @return
	 * @return: Map<String,Object>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String findDeptInfo(String deptNum) {
		EiInfo info = new EiInfo();
		Map<String, Object> rows = new HashMap<>();
		info.set("deptNum", deptNum);
		info.set(EiConstant.serviceId, "S_AC_FW_05");
		EiInfo outInfo = XServiceManager.call(info);
		if (outInfo != null){
			rows = (Map<String, Object>) outInfo.getAttr();
		}
		if(rows !=null && rows.size() > 0){
			List<Map<String,Object>> deptInfo = (List<Map<String,Object>>) rows.get("result");
			return (String) deptInfo.get(0).get("deptName");
		}
		return null;
	}

}
