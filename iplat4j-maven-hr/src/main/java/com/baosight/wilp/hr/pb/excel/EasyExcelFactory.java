package com.baosight.wilp.hr.pb.excel;

import java.io.File;

import com.baosight.wilp.hr.pb.excel.write.builder.ExcelWriterBuilder;

/**
 * Reader and writer factory class
 *
 * <h1>Quick start</h1>
 * <h2>Read</h2>
 * <h3>Sample1</h3>
 *
 * <h3>Sample2</h3>
 *
 * <h2>Write</h2>
 *
 * <h3>Sample1</h3>
 *
 * <h3>Sample2</h3>
 *
 *
 *
 * @author jipengfei
 */
public class EasyExcelFactory {


    /**
     * Build excel the write
     *
     * @param file
     *            File to write
     * @return Excel writer builder
     */
    public static ExcelWriterBuilder write(File file) {
        return write(file, null);
    }

    /**
     * Build excel the write
     *
     * @param file
     *            File to write
     * @param head
     *            Annotate the class for configuration information
     * @return Excel writer builder
     */
    public static ExcelWriterBuilder write(File file, Class head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(file);
        if (head != null) {
            excelWriterBuilder.head(head);
        }
        return excelWriterBuilder;
    }

    /**
     * Build excel the write
     *
     * @param pathName
     *            File path to write
     * @return Excel writer builder
     */
    public static ExcelWriterBuilder write(String pathName) {
        return write(pathName, null);
    }

    /**
     * Build excel the write
     *
     * @param pathName
     *            File path to write
     * @param head
     *            Annotate the class for configuration information
     * @return Excel writer builder
     */
    public static ExcelWriterBuilder write(String pathName, Class head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(pathName);
        if (head != null) {
            excelWriterBuilder.head(head);
        }
        return excelWriterBuilder;
    }

}
