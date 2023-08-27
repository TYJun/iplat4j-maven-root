package com.baosight.wilp.hr.pb.utils;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;

import com.baosight.wilp.hr.pb.excel.EasyExcel;
import com.baosight.wilp.hr.pb.excel.style.ComplexHeadStyles;
import com.baosight.wilp.hr.pb.excel.style.HeadStyleWriteHandler;
import com.baosight.wilp.hr.pb.excel.write.builder.ExcelWriterSheetBuilder;

public class ExcelWriteUtils {

    /**
     * 写入Excel到指定目录
     * 
     * @param fileName
     *            文件路径
     * @param sheetName
     *            sheet页名称
     * @param headList
     *            表头数据
     * @param dataList
     *            表体数据
     */
    public static void writeExcel(String fileName, String sheetName, List<List<String>> headList, List dataList) {
        // 自定义表头策略
        HeadStyleWriteHandler headStyleWriteHandler =
            new HeadStyleWriteHandler(new ComplexHeadStyles(1, 1, IndexedColors.SKY_BLUE.getIndex()));

        // 写Excel
        ExcelWriterSheetBuilder sheet = EasyExcel.write(new File(fileName)).head(headList)
            .registerWriteHandler(headStyleWriteHandler).autoCloseStream(true).sheet(sheetName);
        sheet.doWrite(dataList);
    }
}
