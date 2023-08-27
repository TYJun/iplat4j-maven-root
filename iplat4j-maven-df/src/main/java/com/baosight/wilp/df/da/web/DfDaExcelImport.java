 package com.baosight.wilp.df.da.web;

 import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
 import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
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

/**
 * 设备档案导入功能类
 * <p>用于设备档案导入数据
 * 
 * @Title: DfDaExcelImport.java
 * @ClassName: DfDaExcelImport
 * @Package：com.baosight.wilp.df.da.web
 * @author: LENOVO
 * @date: 2021年10月8日 下午2:04:46
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
@WebServlet({"/dfdaImport"})
@MultipartConfig
public class DfDaExcelImport extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    
    private String[] headList = new String[]{"设备编码","设备名称","设备分类id","安装地方id",
        "安装地方编号","安装地方名称","生产单位","供应商名称","保养单位","管理员工号","管理员名称",
        "管理科室编号","管理科室名称","使用科室编号","使用科室名称"};


    /**
     * 生成数据导出模板
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
        Workbook workBook = createWorkBook(headList, null);
        resp.setHeader("Content-Disposition", "attachment; filename=df_da.xls");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        workBook.write((OutputStream)servletOutputStream);
        servletOutputStream.close();
    }

    /**
     * 数据导入
     * <p>Title: doPost</p>   
     * <p>Description: </p>   
     * @param req
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
        if(".xls".equals(suffix) || ".xlsx".equals(suffix)){
             try {
                 List<Map<String,String>> saveList = new ArrayList<>();
                 List<String[]> errorList = new ArrayList<>();
                 Workbook wb = WorkbookFactory.create(part.getInputStream());
                 Sheet sheet = wb.getSheetAt(0);
                 //数据解析
                 for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                     Row row = sheet.getRow(i);
                     if(row == null) { continue; }
                     parseRow(row, errorList, saveList);
                 } 
                 //处理解析后的数据
//                 if(saveList.size() > 0){
//                     EiInfo info = new EiInfo();
//                     info.set("list", saveList);
//                     MTUtils.invoke(info, "MTSX0201", "saveBatch");
//                 }
                 //错误数据返回
                 if(errorList.size() > 0){
                     List<String> hList = new ArrayList(Arrays.asList(headList));
                     hList.add("错误信息");
                     Workbook workBook = createWorkBook(hList.toArray(new String [hList.size()]), errorList);
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
     * 创建excel文档
     * @Title: createWorkBook
     * @Description: 创建excel文档
     * @param: headList ： 表格头数组
     * @param: dataList : 数据list
     * @return: Workbook
     * @throws
     */
    private Workbook createWorkBook(String[] headList, List<String[]> dataList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet,并给sheet赋值
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //设置行高
        sheet.setDefaultRowHeightInPoints(16);
        //创建表格头行
        HSSFRow headRow = sheet.createRow(0);
        //设置样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式
        //设置边框
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        //设置字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("宋体");
        //表头赋值
        for (int i = 0; i < headList.length; i++) {
            //对表格头赋值
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(headList[i]);
            headCell.setCellStyle(style);
            sheet.setColumnWidth(i, headList[i].getBytes().length*2*180);
        }
        //数据赋值
        if(dataList !=null && dataList.size() > 0) {
            for (int j = 0; j < dataList.size(); j++) {
                //创建数据行
                HSSFRow row = sheet.createRow(j+1);
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
     * @Title: parseRow
     * @Description: 解析每行数据
     * @param: row ： excel数据行对象
     * @param: errorList ： 错误数据集合
     * @param: saveList ： 正确数据集合
     * @return: void  
     * @throws
     */
    private void parseRow(Row row, List<String[]> errorList, List<Map<String,String>> saveList) {
        String machineCode = getCellValue(row.getCell(0));
        String machineName = getCellValue(row.getCell(1));
        String machineTypeId = getCellValue(row.getCell(2));
        String spotId = getCellValue(row.getCell(3));
        String spotCode = getCellValue(row.getCell(4));
        String fixedPlace = getCellValue(row.getCell(5));
        String manufacturerName = getCellValue(row.getCell(6));
        String supplierName = getCellValue(row.getCell(7));
        String maintUnit = getCellValue(row.getCell(8));
        String managerManId = getCellValue(row.getCell(9));
        String managerMan = getCellValue(row.getCell(10));
        String managerDepartId = getCellValue(row.getCell(11));
        String managerDepartName = getCellValue(row.getCell(12));
        String useDeaprtId = getCellValue(row.getCell(13));
        String useDeaprtName = getCellValue(row.getCell(14));
        String errorMsg = "";String dataGroupCode = "";
        //校验
        if(StringUtils.isBlank(machineCode)){
            errorMsg = errorMsg + "设备编码不能为空\r\n";
        }
        if(StringUtils.isBlank(machineName)){
            errorMsg = errorMsg + "设备名称不能为空\r\n";
        }
        if(StringUtils.isBlank(machineTypeId)){
            errorMsg = errorMsg + "设备分类id不能为空\r\n";
        }
        if(StringUtils.isBlank(spotId)){
            errorMsg = errorMsg + "安装地址id不能为空\r\n";
        }
        if(StringUtils.isBlank(spotCode)){
            errorMsg = errorMsg + "安装地址编码不能为空\r\n";
        }
        if(StringUtils.isBlank(fixedPlace)){
            errorMsg = errorMsg + "安装地址名称不能为空\r\n";
        }
        if(StringUtils.isBlank(manufacturerName)){
            errorMsg = errorMsg + "生产单位不能为空\r\n";
        }
        if(StringUtils.isBlank(supplierName)){
            errorMsg = errorMsg + "供应商名称不能为空\r\n";
        }
        if(StringUtils.isBlank(maintUnit)){
            errorMsg = errorMsg + "保养单位不能为空\r\n";
        }
        if(StringUtils.isBlank(managerManId)){
            errorMsg = errorMsg + "管理员工号不能为空\r\n";
        }
        if(StringUtils.isBlank(managerMan)){
            errorMsg = errorMsg + "管理员名称不能为空\r\n";
        }
        if(StringUtils.isBlank(managerDepartId)){
            errorMsg = errorMsg + "管理科室编码不能为空\r\n";
        }
        if(StringUtils.isBlank(managerDepartName)){
            errorMsg = errorMsg + "管理科室名称不能为空\r\n";
        }
        if(StringUtils.isBlank(useDeaprtId)){
            errorMsg = errorMsg + "使用科室编码不能为空\r\n";
        }
        if(StringUtils.isBlank(useDeaprtName)){
            errorMsg = errorMsg + "使用科室名称不能为空\r\n";
        }
        //判断设备编号是否已存在
        if(isExistItem(machineCode)) {
            errorMsg = errorMsg + "设备编号已存在\r\n";
        }
        //判断
        if(errorMsg.length() > 0){
            errorList.add(new String[]{machineCode,machineName,machineTypeId,spotId,spotCode,
                fixedPlace,manufacturerName,supplierName,maintUnit,managerManId,managerMan,
                managerDepartId,managerDepartName,useDeaprtId,useDeaprtName,errorMsg});
        } else {
            Map<String,String> dfda=new HashMap<String, String>();
            dfda.put("id", UUID.randomUUID().toString());
            dfda.put("machineCode", machineCode);
            dfda.put("machineName", machineName);
            dfda.put("machineTypeId", machineTypeId);
            dfda.put("spotId", StringUtils.isBlank(spotId)?"":spotId.replaceAll("-", ""));
            dfda.put("spotCode", spotCode);
            dfda.put("fixedPlace", fixedPlace);
            dfda.put("spotName", fixedPlace);
            dfda.put("manufacturerName", manufacturerName);
            dfda.put("supplierName", supplierName);
            dfda.put("maintUnit", maintUnit);
            dfda.put("managerManId", managerManId);
            dfda.put("managerMan", managerMan);
            dfda.put("managerDepartId", managerDepartId);
            dfda.put("managerDepartName", managerDepartName);
            dfda.put("useDeaprtId", useDeaprtId);
            dfda.put("useDeaprtName", useDeaprtName);
            dfda.put("dataGroupCode", dataGroupCode);
         // 插入数据
            dao.insert("DFDA01.insertImport",dfda);
            // 查询设备档案返回list集合
            List<Map<String, Object>> listMap = dao.query("DFDA02.query", dfda);
            // 如果不存在地点新增，设备为1
            // 否则地点数量变更
            if(CollectionUtils.isEmpty(listMap)) {
                // map赋值spotId(下同)
                // dfda.put("spotId" , StringUtils.isBlank(spotId)?"":spotId.replaceAll("-", "")); 
                dfda.put("id" , UUID.randomUUID().toString());
                dfda.put("deviceCount", "1");
                // 插入数据
                dao.insert("DFDA02.insertMachineSpot",dfda);
            }else {
                // 获取设备数量
                Integer deviceCount = (Integer)listMap.get(0).get("deviceCount");
                // map赋值deviceCount
                dfda.put("deviceCount", String.valueOf(deviceCount+1));
                // 更新
                dao.update("DFDA02.updateMachineSpot",dfda);
            }
        }
    }
    
    /**
     * 判断设备编号是否存在
     * @Title: isExistItem
     * @Description: 判断设备编号是否存在
     * @param: itemName ： 维修事项名称
     * @param: itemTypeNum ： 维修事项分类编码
     * @return: boolean
     * @throws
     */
    private boolean isExistItem(String machineCode) {
        int num = dao.count("DFDA01.isExist", machineCode);
        if(num==0) {
            return false;
        }
        return true;
    }

    /**
     * 获取Excel单元格数据
     * @Title: getCellValue
     * @Description: 获取Excel单元格数据
     * @param: cell ： excel 单元格对象
     * @return: String
     * @throws
     */
    public static String getCellValue(Cell cell){
        String cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf((int)cell.getNumericCellValue());
                    break;
                }
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

}
