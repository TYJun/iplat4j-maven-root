package com.baosight.wilp.si.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: excel表头对象
 * @ClassName: SiExcelHeader
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年08月18日 16:05
 */
public class SiExcelHeader {

    /**表头类型: 输入**/
    public static final String TYPE_INPUT = "input";

    /**表头类型: 下拉**/
    public static final String TYPE_SELECT = "select";

    /**表头名称**/
    private String headerName;

    /**表头类型(input=输入,select=选择)**/
    private String headerType;

    /**选择类型数据**/
    private List<String> data;

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderType() {
        return headerType;
    }

    public void setHeaderType(String headerType) {
        this.headerType = headerType;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    /**
     * 构建MpExcelHeader对象
     * @Title: inputHeader
     * @param headerName headerName
     * @return com.baosight.wilp.le.common.LeExcelHeader
     * @throws
     **/
    public static SiExcelHeader inputHeader(String headerName) {
        return excelHeader(headerName, TYPE_INPUT, null);
    }

    /**
     * 构建LeExcelHeader对象
     * @Title: selectHeader
     * @param headerName headerName
     * @param data data
     * @return com.baosight.wilp.le.common.LeExcelHeader
     * @throws
     **/
    public static SiExcelHeader selectHeader(String headerName, List<String> data) {
        return excelHeader(headerName, TYPE_SELECT, data);
    }

    /**
     * 构建MpExcelHeader对象
     * @Title: inputHeader
     * @param headerName headerName
     * @param headerType headerType
     * @param data data
     * @return com.baosight.wilp.le.common.LeExcelHeader
     * @throws
     **/
    public static SiExcelHeader excelHeader(String headerName, String headerType, List<String> data) {
        SiExcelHeader excelHeader = new SiExcelHeader();
        excelHeader.setHeaderName(headerName);
        excelHeader.setHeaderType(headerType);
        excelHeader.setData(data);
        return excelHeader;
    }

    /**
     * 构建MpExcelHeader对象集合
     * @Title: toInputHeaders
     * @param headerNames headerNames
     * @return java.util.List<com.baosight.wilp.le.common.LeExcelHeader>
     * @throws
     **/
    public static List<SiExcelHeader> toInputHeaders(List<String> headerNames) {
        List<SiExcelHeader> list = new ArrayList<>();
        for (String headerName : headerNames) {
            list.add(inputHeader(headerName));
        }
        return list;
    }

}
