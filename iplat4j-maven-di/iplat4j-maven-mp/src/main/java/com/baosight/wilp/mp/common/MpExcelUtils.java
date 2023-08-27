package com.baosight.wilp.mp.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购Excel工具类
 * @ClassName: MpExcelUtils
 * @Package com.baosight.wilp.le.common
 * @date: 2022年08月18日 15:54
 */
public class MpExcelUtils {

    /**
     * 创建excel文档
     * <p>使用API创建一个Excel表格</p>
     * @Title: createWorkBook
     * @param:  @param headList ： 表格头数组
     * @param:  @param dataList : 数据list
     * @param:  @return
     * @return: Workbook
     * @throws
     */
    public static Workbook createWorkBook(List<MpExcelHeader> headList, List<String[]> dataList) {
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
        font.setFontHeightInPoints((short)12);
        font.setFontName("宋体");
        //表头赋值
        for (int i = 0; i < headList.size(); i++) {
            MpExcelHeader excelHeader = headList.get(i);
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(excelHeader.getHeaderName());
            //设置单元格样式
            headCell.setCellStyle(style);
            sheet.setColumnWidth(i, excelHeader.getHeaderName().getBytes().length*2*256);
        }

        //设置下拉列
        setDataValidation(sheet, headList);

        //数据赋值
        if(dataList !=null && dataList.size() > 0) {
            for (int j = 0; j < dataList.size(); j++) {
                //创建数据行
                HSSFRow row = sheet.createRow(j+1);
                //获取每行数据
                String[] data = dataList.get(j);
                for (int k = 0; k < headList.size(); k++) {
                    HSSFCell dataCell = row.createCell(k);
                    dataCell.setCellValue(data[k]);
                    dataCell.setCellStyle(style);
                }
            }
        }
        return workbook;
    }

    /**
     * 设置下拉列
     * @Title: setDataValidation
     * @param sheet sheet
     * @param headList headList : 表头集合
     * @return void
     * @throws
     **/
    private static void setDataValidation(Sheet sheet, List<MpExcelHeader> headList) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        //遍历
        for (int i = 0; i < headList.size(); i++) {
            MpExcelHeader header = headList.get(i);
            if(MpExcelHeader.TYPE_SELECT.equals(header.getHeaderType())) {
                //加载下拉列表内容
                String[] textList = header.getData().toArray(new String[0]);
                DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
                constraint.setExplicitListValues(textList);
                //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
                CellRangeAddressList regions = new CellRangeAddressList(1, 2, i, i);
                //数据有效性对象
                sheet.addValidationData(helper.createValidation(constraint, regions));
            }
        }
    }

    /**
     * 获取Excel单元格数据
     * @Title: getCellValue
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
     * 判断是否是空行
     * @Title: isEmptyRow
     * @param row row : Excel数据行
     * @return java.lang.Boolean
     * @throws
     **/
    public static Boolean isEmptyRow(Row row) {
        for (int i = row.getFirstCellNum(); i< row.getLastCellNum(); i++){
            Cell cell = row.getCell(i);
            if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK){
                return false;
            }
        }
        return true;
    }
}
