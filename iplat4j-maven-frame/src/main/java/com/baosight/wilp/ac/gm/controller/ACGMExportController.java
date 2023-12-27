package com.baosight.wilp.ac.gm.controller;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 物资档案Excel
 * @Author tanyongjun
 * @Date 2023-12-21 16:26
 */
@Controller
public class ACGMExportController {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");


    final String projectSchema = PrUtils.getConfigure();
    final String platSchema = PrUtils.getIplatConfigure();

    /**
     * 物资档案EXCEL导出功能
     */
    @CrossOrigin
    @GetMapping("/ACGM01/outMatFromExcel")
    public void outMatFromExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //封装条件参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("projectSchema", projectSchema);
        map.put("platSchema", platSchema);
        map.put("matClassCode",req.getParameter("matClassCode"));
        map.put("matClassName",req.getParameter("matClassName"));
        map.put("matCode",req.getParameter("matCode"));
        map.put("matName",req.getParameter("matName"));
        map.put("matTypeCode",req.getParameter("matTypeCode"));
        map.put("status",req.getParameter("status"));
        map.put("matClassId",req.getParameter("matClassId"));

        //获取表格数据
        SqlMapDao sqlDao = (SqlMapDao) dao;
        sqlDao.setMaxQueryCount(20000);
        List<Map<String,String>> query = sqlDao.query("ACGM01.queryOutMaterialList", map);

        //导出表头
        String[] mat = { "物资编码", "物资名称", "计量单位", "物资分类名", "物资规格", "物资型号", "最近订购单价(元)", "制造商", "物资大类", "备注","状态" };
        ArrayList<String> HeadList = new ArrayList<>(Arrays.asList(mat));
        //导出字段名
        String[] filedName = { "matCode", "matName", "unit", "matClassName", "matSpec", "matModel", "price", "manufacturer", "matClassName", "remark","statusText" };
        // 设置文件名
        resp.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode("物资档案导出文件.xls", "UTF-8"));
        Workbook workBook = createWorkBook(null,HeadList,query,filedName);
        //获取输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        //输出excel文件流
        workBook.write(servletOutputStream);
        servletOutputStream.close();

    }

    /**
     * 创建Excel表
     */
    public static Workbook createWorkBook(String sheetName, List<String> headList,
                                   List<Map<String, String>> dataList, String[] filedName) {
        Workbook wb = new HSSFWorkbook();

        /* Excel文件创建完毕 */
        CreationHelper createHelper = wb.getCreationHelper(); // 创建帮助工具

        /* 创建表单 */
        Sheet sheet = wb.createSheet(sheetName != null ? sheetName : "sheet1");


        // 设置字体
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 16);
        headFont.setFontName("宋体");
        headFont.setItalic(false);
        headFont.setStrikeout(false);

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(headFont);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        Font Font = wb.createFont();
        Font.setFontHeightInPoints((short) 12);
        Font.setFontName("宋体");
        Font.setItalic(false);
        Font.setStrikeout(false);

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setFont(Font);
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        Row headRow = sheet.createRow(0); // 第一行为头
        for (int i = 0; i < headList.size(); i++) { // 遍历表头数据
            Cell cell = headRow.createCell(i); // 创建单元格
            cell.setCellValue(createHelper.createRichTextString(headList.get(i))); // 设置值
            cell.setCellStyle(cellStyle);
            if (i==1 || i==6 || i==4 || i==5){
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 3);
            }else {
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 2);
            }

        }

        // 数据部分
        for (int i = 1; i <= dataList.size(); i++) {
            Row row = sheet.createRow(i); // 获取行
            for (int j = 0; j < filedName.length; j++) {
                Cell cell = row.createCell(j); // 创建单元格
                cell.setCellValue(createHelper.createRichTextString(dataList.get(i - 1).get(filedName[j]))); // 设置值
                cell.setCellStyle(cellStyle1);
            }
        }

        return wb;

    }
}
