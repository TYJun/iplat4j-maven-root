package com.baosight.wilp.hr.web;


import com.baosight.wilp.hr.common.HrExcelHeader;
import com.baosight.wilp.hr.common.HrExcelUtils;
import com.baosight.wilp.hr.web.service.HrExcelImportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存工单打印Controller
 * @ClassName: SiJasperPrintController
 * @Package com.baosight.wilp.hr.web
 * @date: 2022年12月09日 15:22
 *
 * 1.
 * 2.打印出库单
 */
@Controller
@RequestMapping("/hr")
public class HrExportAndController {

    /**excel文件格式:.xls**/
    private static String FILE_SUFFIX_XLS = ".xls";
    /**excel文件格式:.xlsx**/
    private static String FILE_SUFFIX_XLSX = ".xlsx";

    /**表头集合**/
    private final static List<String> enterHeads = Arrays.asList("姓名（必填）","联系电话（必填）", "性别","出生日期（yyyy-mm-dd）","身份证号码（必填）","民族（必填）",
            "籍贯","学历（必填）", "岗位（必填）", "预计入职时间(yyyy-mm-dd)","健康状况（必填）", "现住址（必填）","婚姻状况", "最高学历", "最高学位", "公司名称（必填）","基本工资","政治面貌","人员类别（必填）","所属部门（必填）","服务部门（必填）","紧急联系人（必填）","紧急联系人电话（必填）","管理部门（必填）","备注");

    /**表头集合**/
    private final static List<String> storageExportHeaders = Arrays.asList("姓名（必填）","联系电话（必填）", "性别","出生日期（yyyy-mm-dd）","身份证号码（必填）","民族（必填）",
            "籍贯","学历（必填）", "岗位（必填）", "预计入职时间(yyyy-mm-dd)","健康状况（必填）", "现住址（必填）","婚姻状况", "最高学历", "最高学位", "公司名称（必填）","基本工资","政治面貌","人员类别（必填）","所属部门（必填）","服务部门（必填）","紧急联系人（必填）","紧急联系人电话（必填）","管理部门（必填）","备注");

    @Autowired
    private HrExcelImportService hrExcelImportService;


    /**
     * 下载入库导入模板
     * @Title: downloadEnterTemplate
     * @param req req: HttpServletRequest对象
     * @param resp resp: HttpServletResponse对象
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/downloadEnterTemplate")
    public void downloadEnterTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 创建新的工作簿（XSSFWorkbook）代替HSSFWorkbook
        Workbook workBook = new XSSFWorkbook();

        // 创建新的工作表
        Sheet sheet = workBook.createSheet("导入模板");

        // 创建文本格式的单元格样式
        CellStyle textStyle = workBook.createCellStyle();
        textStyle.setDataFormat(workBook.createDataFormat().getFormat("@"));

        // 设置列宽（例如，每列10个字符）
        int numColumns = enterHeads.size();
        for (int i = 0; i < numColumns; i++) {
            sheet.setColumnWidth(i, 256 * 25);
        }

        // 填充表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < numColumns; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(enterHeads.get(i));
            cell.setCellStyle(textStyle); // 应用文本格式
        }

        // 设置整个工作表的文本格式和空白单元格的值
        int numRows = 500; // 假设有100行数据，可以根据实际情况进行调整
        for (int i = 1; i <= numRows; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < numColumns; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(textStyle); // 应用文本格式
                cell.setCellValue(""); // 设置空白单元格的值为空字符串


                // 设置下拉框选项（假设第3列是"性别"列）
                if (j == 2) {
                    String[] genders = {"男", "女"}; // 设置下拉框选项的内容
                    DataValidationHelper validationHelper = sheet.getDataValidationHelper();
                    CellRangeAddressList addressList = new CellRangeAddressList(i, i, j, j); // 设置下拉框选项的单元格范围
                    DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(genders);
                    DataValidation validation = validationHelper.createValidation(constraint, addressList);
                    sheet.addValidationData(validation);
                }

                // 设置下拉框选项（假设第19列是"人员类别"列，即第S列）
                if (j == 18) {
                    String[] categories = {"第三方人员", "后勤保障中心"}; // 设置下拉框选项的内容
                    DataValidationHelper validationHelper = sheet.getDataValidationHelper();
                    CellRangeAddressList addressList = new CellRangeAddressList(i, i, j, j); // 设置下拉框选项的单元格范围
                    DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(categories);
                    DataValidation validation = validationHelper.createValidation(constraint, addressList);
                    sheet.addValidationData(validation);
                }

                // 设置下拉框选项（假设第23列是"部门"列，即第X列）
                if (j == 23) {
                    String[] departments = {"数据中心", "动力运维科", "输送科", "消防科", "后勤保障中心", "物业管理科"}; // 设置下拉框选项的内容
                    DataValidationHelper validationHelper = sheet.getDataValidationHelper();
                    CellRangeAddressList addressList = new CellRangeAddressList(i, i, j, j); // 设置下拉框选项的单元格范围
                    DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(departments);
                    DataValidation validation = validationHelper.createValidation(constraint, addressList);
                    sheet.addValidationData(validation);
                }
            }
        }

        // 设置文件名和响应头
        resp.setHeader("Content-Disposition", "attachment; filename=hr_enter.xlsx");
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 获取输出流并写入Excel文件
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        workBook.write(servletOutputStream);
        servletOutputStream.close();
    }

    /**
     * 入库导入
     * @Title: importEnter
     * @param req req: HttpServletRequest对象
     * @param resp resp: HttpServletResponse对象
     * @return void
     * @throws
     **/
    @CrossOrigin
    @PostMapping("/importEnter")
    public void importEnter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        importExcel(req, resp, hrExcelImportService);
    }

    /**
     * Excel导入
     * @Title: importExcel
     * @param req req: HttpServletRequest对象
     * @param resp resp: HttpServletResponse对象
     * @param service service: LeExcelImportService对象
     * @return void
     * @throws
     **/
    private void importExcel(HttpServletRequest req, HttpServletResponse resp, HrExcelImportService service) throws IOException {
        try {
            //构建新的文件解析器CommonsMultipartResolver
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
            MultipartHttpServletRequest request = resolver.resolveMultipart(req);
           // MultipartHttpServletRequest request = (MultipartHttpServletRequest)(req);
            //重新解析文件参数
            MultipartFile excelFile = request.getFile("excelFile");
            String fileName = excelFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            resp.setContentType("application/json");
            //判断文件是否是Excel
            if (FILE_SUFFIX_XLS.equals(suffix) || FILE_SUFFIX_XLSX.equals(suffix)) {
                //解析Excel,获取返回错误信息
                List<String[]> errorList = service.parseExcel(excelFile.getInputStream());
                //取出数据保存返回
                String result = errorList.get(errorList.size()-1)[0];
                errorList.remove(errorList.size()-1);
                //错误数据返回
                if(errorList.size() > 0){
                    //构建错误Excel文件
                    List<HrExcelHeader> headers = service.buildHeader();
                    headers.add(HrExcelHeader.inputHeader("错误信息"));
                    Workbook workBook = HrExcelUtils.createWorkBook(headers, errorList);

                    // 获取工作簿的第一个（默认）工作表
                    Sheet sheet = workBook.getSheetAt(0);

                    // 设置文本格式
                    DataFormat textFormat = workBook.createDataFormat();
                    CellStyle textStyle = workBook.createCellStyle();
                    textStyle.setDataFormat(textFormat.getFormat("@"));

                    // 遍历工作表中的每一行
                    for (Row row : sheet) {
                        // 遍历行中的每个单元格
                        for (Cell cell : row) {
                            cell.setCellStyle(textStyle); // 应用文本格式样式
                        }
                    }

                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    workBook.write(bout);
                    byte[] bytes = bout.toByteArray();
                    String base64 = Base64Utils.encodeToString(bytes);
                    resp.getWriter().print("{\"msg\":\"part\",\"base64\":\"" + base64 + "\",\"id\":\""+result +"\"}");
                } else {
                    resp.getWriter().print("{\"msg\":\"all\",\"id\":\""+result +"\"}");
                }
            } else {
                resp.getWriter().print("{\"msg\":\"error\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("{\"msg\":\"error\"}");
        }
    }

}
