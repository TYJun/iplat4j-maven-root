package com.baosight.wilp.dm.utils;

import com.baosight.xservices.xs.up.utils.XSExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class XSSExcelUtils extends XSExcelUtils {

    public static String[][] getDataByInputStream(InputStream ins, int ignoreRows) throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList();
        int rowSize = 0;
        XSSFWorkbook wb = new XSSFWorkbook(ins);
        XSSFCell cell = null;

        for(int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); ++sheetIndex) {
            XSSFSheet st = wb.getSheetAt(sheetIndex);

            for(int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); ++rowIndex) {
                XSSFRow row = st.getRow(rowIndex);
                if (row != null) {
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }

                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;

                    for(short columnIndex = 0; columnIndex <= row.getLastCellNum(); ++columnIndex) {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            switch(cell.getCellType()) {
                                case 0:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
                                        } else {
                                            value = "";
                                        }
                                    } else {
                                        value = (new DecimalFormat("0.00")).format(cell.getNumericCellValue());
                                        if (".00".equals(value.substring(value.indexOf("."), value.length()))) {
                                            value = (new DecimalFormat("0")).format(cell.getNumericCellValue());
                                        }
                                    }
                                    break;
                                case 1:
                                    value = cell.getStringCellValue();
                                    break;
                                case 2:
                                    if (!"".equals(cell.getStringCellValue())) {
                                        value = cell.getStringCellValue();
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                case 3:
                                    break;
                                case 4:
                                    value = cell.getBooleanCellValue() ? "Y" : "N";
                                    break;
                                case 5:
                                    value = "";
                                    break;
                                default:
                                    value = "";
                            }
                        }

                        if (columnIndex == 0 && "".equals(value.trim())) {
                            break;
                        }

                        values[columnIndex] = rightTrim(value);
                        hasValue = true;
                    }

                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
        }

        String[][] returnArray = new String[result.size()][rowSize];

        for(int i = 0; i < returnArray.length; ++i) {
            returnArray[i] = (String[])((String[])result.get(i));
        }

        return returnArray;
    }

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        } else {
            int length = str.length();

            for(int i = length - 1; i >= 0 && str.charAt(i) == ' '; --i) {
                --length;
            }

            return str.substring(0, length);
        }
    }
}
