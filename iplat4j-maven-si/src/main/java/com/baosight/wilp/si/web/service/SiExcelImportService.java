package com.baosight.wilp.si.web.service;

import com.baosight.wilp.si.common.SiExcelHeader;
import com.baosight.wilp.si.common.SiExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 后勤成本Excel导入Service接口
 * @ClassName: LeExcelImportService
 * @Package com.baosight.wilp.le.excel.service
 * @date: 2022年08月19日 10:43
 */
public interface SiExcelImportService {

    List<SiExcelHeader> cacheHeaders = new ArrayList<>();

    /**
     * 构建excel模板表头
     * @Title: buildHeader
     * @return java.util.List<com.baosight.wilp.le.common.SiExcelHeader>
     * @throws
     **/
    List<SiExcelHeader> buildHeader();

    /**
     * 获取excel模板表头
     * @Title: getHeaders
     * @return java.util.List<com.baosight.wilp.le.common.SiExcelHeader>
     * @throws
     **/
    default List<SiExcelHeader> getHeaders() {
        if(cacheHeaders.isEmpty()) {
            buildHeader();
        }
        return cacheHeaders;
    }

    /**
     * 构建excel模板返回数据
     * @Title: buildData
     * @param map map : 参数
     * @return java.util.List<java.lang.String[]>
     * @throws
     **/
    List<String[]> buildData(Map<String, Object> map);

    /**
     * 构建Excel导出模板
     * @Title: buildTemplate
     * @param map map : 参数
     * @return org.apache.poi.ss.usermodel.Workbook
     * @throws
     **/
    default Workbook buildTemplate(Map<String, Object> map) {
        return buildTemplate(buildHeader(), buildData(map));
    }

    /**
     * 构建Excel导出模板
     * @Title: buildTemplate
     * @param dataList dataList
     * @return org.apache.poi.ss.usermodel.Workbook
     * @throws
     **/
    default Workbook buildTemplate(List<String[]> dataList) {
        return buildTemplate(buildHeader(), dataList);
    };

    /**
     * 构建Excel导出模板
     * @Title: buildTemplate
     * @param headerList headerList
     * @param dataList dataList
     * @return org.apache.poi.ss.usermodel.Workbook
     * @throws
     **/
    default Workbook buildTemplate(List<SiExcelHeader> headerList, List<String[]> dataList) {
        return SiExcelUtils.createWorkBook(headerList, dataList);
    }

    /**
     * 解析excel文件
     *
     * @param inputStream inputStream
     * @return java.util.List<java.lang.String [ ]>
     * @throws Exception
     * @Title: parseExcel
     **/
    default List<String[]> parseExcel(InputStream inputStream) throws Exception {
        return SiExcelUtils.parseExcel(inputStream, this);
    }

    /**
     * 解析Excel行数据
     * @Title: parseRow
     * @param row row
     * @param dataList dataList
     * @param errorList errorList
     * @return void
     * @throws
     **/
    void parseRow(Row row, List<Object> dataList, List<String[]> errorList);

   /**
    * 数据校验
    * @Title: validate
    * @param object object
    * @return String
    * @throws
    **/
   String validate(Object object);

    /**
     * 保存导入数据
     * @Title: saveData
     * @param list list
     * @return String
     * @throws
     **/
    String saveData(List<Object> list);



}
