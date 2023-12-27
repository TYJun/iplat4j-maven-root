package com.baosight.wilp.ms.common.web;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.ms.common.service.WebSocketService;
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
 * 智能监控设备配置和点位参数导入功能类
 * <p>用于智能监控设备配置和点位参数导入数据
 *
 * @Title: MsMxExcelImport.java
 * @ClassName: MsMxExcelImport
 * @Package：com.baosight.wilp.ms.common.web
 * @author: LENOVO
 * @date: 2022年03月17日 下午2:04:46
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@WebServlet({"/msmxImport"})
@MultipartConfig
public class MsMxExcelImport extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    private String[] headList = new String[]{"参数项Tag","参数项名称","描述","参数项类型",
            "参数所属分类名称","所属设备名称","数据格式","计量单位","计量单位名称","计量量纲","计量量纲名称","枚举值01","枚举值01显示文本",
            "枚举值02","枚举值02显示文本","枚举值03","枚举值03显示文本","枚举值04","枚举值04显示文本","枚举值05","枚举值05显示文本",
            "枚举值06","枚举值06显示文本","死区时间","报警启用状态",
            "参数启用状态","真值标签","假值标签","报警值","是否记录日志"};


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
        resp.setHeader("Content-Disposition", "attachment; filename=msmx.xls");
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
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param row ： excel数据行对象
     * @param:  @param errorList ： 错误数据集合
     * @param:  @param saveList ： 正确数据集合
     * @return: void
     * @throws
     */
    private void parseRow(Row row, List<String[]> errorList, List<Map<String,String>> saveList) {
        String tag = getCellValue(row.getCell(0));
        String name_ = getCellValue(row.getCell(1));
        String description_ = getCellValue(row.getCell(2));
        String type = getCellValue(row.getCell(3));
        String tParamClassifyName = getCellValue(row.getCell(4));
        String tmsdc01Name = getCellValue(row.getCell(5));
        String dataFormat = getCellValue(row.getCell(6));
        String unit = getCellValue(row.getCell(7));
        String unitName = getCellValue(row.getCell(8));
        String dimension = getCellValue(row.getCell(9));
        String dimensionName = getCellValue(row.getCell(10));
        String enumValue01 = getCellValue(row.getCell(11));
        String enumValue01Label = getCellValue(row.getCell(12));
        String enumValue02 = getCellValue(row.getCell(13));
        String enumValue02Label = getCellValue(row.getCell(14));
        String enumValue03 = getCellValue(row.getCell(15));
        String enumValue03Label = getCellValue(row.getCell(16));
        String enumValue04 = getCellValue(row.getCell(17));
        String enumValue04Label = getCellValue(row.getCell(18));
        String enumValue05 = getCellValue(row.getCell(19));
        String enumValue05Label = getCellValue(row.getCell(20));
        String enumValue06 = getCellValue(row.getCell(21));
        String enumValue06Label = getCellValue(row.getCell(22));
        String deadTime = getCellValue(row.getCell(23));
        String alarmEnableStatus = getCellValue(row.getCell(24));
        String paramEnableStatus = getCellValue(row.getCell(25));
        String trueValueLabel = getCellValue(row.getCell(26));
        String falseValueLabel = getCellValue(row.getCell(27));
        String alarmValue = getCellValue(row.getCell(28));
        String isWriteLog = getCellValue(row.getCell(29));
        String errorMsg = "";String dataGroupCode = "";
        String tParamClassifyId="";
        String tmsdc01Id="";
        //校验
        if(StringUtils.isBlank(tag)){
            errorMsg = errorMsg + "参数项Tag不能为空\r\n";
        }
        if(StringUtils.isBlank(name_)){
            errorMsg = errorMsg + "参数项名称不能为空\r\n";
        }
//        if(StringUtils.isBlank(description_)){
//            errorMsg = errorMsg + "描述\r\n";
//        }
        if(StringUtils.isBlank(type)){
            errorMsg = errorMsg + "参数项类型不能为空\r\n";
        }
        if(StringUtils.isBlank(tParamClassifyName)){
            errorMsg = errorMsg + "参数所属分类主键不能为空\r\n";
        }
        if(StringUtils.isBlank(tmsdc01Name)){
            errorMsg = errorMsg + "所属设备主键不能为空\r\n";
        }
//        if(StringUtils.isBlank(dataFormat)){
//            errorMsg = errorMsg + "数据格式\r\n";
//        }
//        if(StringUtils.isBlank(unit)){
//            errorMsg = errorMsg + "计量单位\r\n";
//        }
//        if(StringUtils.isBlank(unitName)){
//            errorMsg = errorMsg + "计量单位名称\r\n";
//        }
//        if(StringUtils.isBlank(dimension)){
//            errorMsg = errorMsg + "计量量纲\r\n";
//        }
//        if(StringUtils.isBlank(dimensionName)){
//            errorMsg = errorMsg + "计量量纲名称\r\n";
//        }
//        if(StringUtils.isBlank(deadTime)){
//            errorMsg = errorMsg + "死区时间\r\n";
//        }
        if(StringUtils.isBlank(alarmEnableStatus)){
            errorMsg = errorMsg + "报警启用状态不能为空\r\n";
        }
        if(StringUtils.isBlank(paramEnableStatus)){
            errorMsg = errorMsg + "参数启用状态不能为空\r\n";
        }
//        if(StringUtils.isBlank(trueValueLabel)){
//            errorMsg = errorMsg + "真值标签\r\n";
//        }
//        if(StringUtils.isBlank(falseValueLabel)){
//            errorMsg = errorMsg + "假值标签\r\n";
//        }
        if(StringUtils.isBlank(isWriteLog)){
            errorMsg = errorMsg + "是否记录日志不能为空\r\n";
        }
        //根据设备名称获取对应设备id
        if(getMachineIdByName(tmsdc01Name).isEmpty()) {
            errorMsg = errorMsg + "该设备名称不在系统中,请核对\r\n";
        }else{
            tmsdc01Id=getMachineIdByName(tmsdc01Name);
        }
        //根据参数分类名称获取对应分类id
        if(getParamIdByName(tParamClassifyName).isEmpty()) {
            errorMsg = errorMsg + "该参数分类名称不在系统中,请核对\r\n";
        }else{
            tParamClassifyId=getParamIdByName(tParamClassifyName);
        }

        //判断tag点是否存在
        if(getListByTag(tag)){
            errorMsg = errorMsg + "该参数Tag点已存在系统中,请核对\r\n";
        }
        //判断
        if(errorMsg.length() > 0){
            errorList.add(new String[]{tag,name_,description_,type,tParamClassifyName,
                    tmsdc01Name,dataFormat,unit,unitName,dimension,dimensionName,enumValue01,enumValue01Label,enumValue02,enumValue02Label,
                    enumValue03,enumValue03Label,enumValue04,enumValue04Label,enumValue05,enumValue05Label,enumValue06,enumValue06Label,
                    deadTime,alarmEnableStatus,paramEnableStatus,trueValueLabel,falseValueLabel,alarmValue,isWriteLog,errorMsg});
        } else {
            Map<String,String> dfda=new HashMap<String, String>();
            dfda.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
            dfda.put("tag", tag);
            dfda.put("name_", name_);
            dfda.put("description_", description_);
            dfda.put("type", type);
            dfda.put("tParamClassifyId", tParamClassifyId);
            dfda.put("tmsdc01Id", tmsdc01Id);
            dfda.put("dataFormat", dataFormat);
            dfda.put("unit", unit);
            dfda.put("unitName", unitName);
            dfda.put("dimension", dimension);
            dfda.put("dimensionName", dimensionName);
            dfda.put("enumValue01", enumValue01);
            dfda.put("enumValue01Label", enumValue01Label);
            dfda.put("enumValue02", enumValue02);
            dfda.put("enumValue02Label", enumValue02Label);
            dfda.put("enumValue03", enumValue03);
            dfda.put("enumValue03Label", enumValue03Label);
            dfda.put("enumValue04", enumValue04);
            dfda.put("enumValue04Label", enumValue04Label);
            dfda.put("enumValue05", enumValue05);
            dfda.put("enumValue05Label", enumValue05Label);
            dfda.put("enumValue06", enumValue06);
            dfda.put("enumValue06Label", enumValue06Label);
            dfda.put("deadTime", deadTime);
            dfda.put("alarmEnableStatus", alarmEnableStatus);
            dfda.put("paramEnableStatus", paramEnableStatus);
            dfda.put("trueValueLabel", trueValueLabel);
            dfda.put("falseValueLabel", falseValueLabel);
            dfda.put("alarmValue", alarmValue);
            dfda.put("isWriteLog", isWriteLog);
            dfda.put("dataGroupCode", dataGroupCode);
            // 插入数据
            dao.insert("MSDC01.insertImport",dfda);
            WebSocketService.TAG_CONFIG_CACHE.remove(tag);
            GatherServer.TAG_CONFIG_CACHE.remove(tag);
        }
    }

    /**
     * 根据设备名称获取设备id
     * @Title: getMachineIdByName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param tmsdc01Name ： 设备名称
     * @param:  @return
     * @return: tmsdc01Id
     * @throws
     */
    private String getMachineIdByName(String tmsdc01Name) {
        List<String> tmsdc01Id = dao.query("MSDC01.getMachineIdByName", tmsdc01Name);
        if(tmsdc01Id.isEmpty()) {
            return "";
        }
        return tmsdc01Id.get(0);
    }

    /**
     * 根据参数分类名称名称获取参数分类id
     * @Title: getParamIdByName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param tParamClassifyName ： 分类名称
     * @param:  @return
     * @return: paramId
     * @throws
     */
    private String getParamIdByName(String tParamClassifyName) {
        List<String> paramId = dao.query("MSDC01.getParamIdByName", tParamClassifyName);
        if(paramId.isEmpty()) {
            return "";
        }
        return paramId.get(0);
    }

    /**
     * 根据tag点判断该tag是否存在表里
     * @Title: getListByTag
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param tmsdc01Name ： 设备名称
     * @param:  @return
     * @return: tmsdc01Id
     * @throws
     */
    private Boolean getListByTag(String tag) {
        List<String> tags = dao.query("MSDC01.getListByTag", tag);
        if(tags.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 获取Excel单元格数据
     * @Title: getCellValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param cell ： excel 单元格对象
     * @param:  @return
     * @return: String
     * @throws
     */
    public static String getCellValue(Cell cell){
        String cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf((int)cell.getNumericCellValue()).trim();
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString().trim();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = null;
        }
        return cellValue;
    }
}
