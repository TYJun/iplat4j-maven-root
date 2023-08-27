package com.baosight.wilp.si.web;

import com.baosight.wilp.si.common.SiExcelHeader;
import com.baosight.wilp.si.common.SiExcelUtils;
import com.baosight.wilp.si.web.service.SiExcelImportService;
import com.baosight.wilp.si.web.service.impl.SiStorageExcelServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存工单打印Controller
 * @ClassName: SiJasperPrintController
 * @Package com.baosight.wilp.si.web
 * @date: 2022年12月09日 15:22
 *
 * 1.
 * 2.打印出库单
 */
@Controller
@RequestMapping("/si")
public class SiExportAndController {

    /**excel文件格式:.xls**/
    private static String FILE_SUFFIX_XLS = ".xls";
    /**excel文件格式:.xlsx**/
    private static String FILE_SUFFIX_XLSX = ".xlsx";

    /**表头集合**/
    private final static List<String> enterHeads = Arrays.asList("仓库号","仓库名称", "入库类型编码","入库类型名称","物资编码","物资名称",
            "物资分类编码","物资分类名称", "物资规格", "物资型号","计量单位", "入库数量","入库单价", "入库日期", "供应商名称", "发票号");

    /**表头集合**/
    private final static List<String> storageExportHeaders = Arrays.asList("仓库号","仓库名称", "物资编码","物资名称",
            "物资分类名称", "物资规格", "物资型号","计量单位", "库存总量","库存最低单价", "库存总价", "库存上限", "库存下限");

    @Autowired
    private SiExcelImportService siEnterExcelService;

    @Autowired
    private SiStorageExcelServiceImpl siStorageExcelService;

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
        //生成excel文件对象
        Workbook workBook = SiExcelUtils.createWorkBook(SiExcelHeader.toInputHeaders(enterHeads), null);
        // 设置文件名
        resp.setHeader("Content-Disposition", "attachment; filename=si_enter.xls");
        //获取输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        //输出excel文件流
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
        importExcel(req, resp, siEnterExcelService);
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
    private void importExcel(HttpServletRequest req, HttpServletResponse resp, SiExcelImportService service) throws IOException {
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
                    List<SiExcelHeader> headers = service.buildHeader();
                    headers.add(SiExcelHeader.inputHeader("错误信息"));
                    Workbook workBook = SiExcelUtils.createWorkBook(headers, errorList);
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

    /**
     * 导出库存数据
     * @Title: exportStorage
     * @param req req: HttpServletRequest对象
     * @param resp resp: HttpServletResponse对象
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/exportStorage")
    public void exportStorage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Map<String, Object> pMap = new HashMap<>(16);
        pMap.put("wareHouseNo", req.getParameter("wareHouseNo"));
        pMap.put("matTypeNum", req.getParameter("matTypeNum"));
        pMap.put("matNum", req.getParameter("matNum"));
        pMap.put("matName", req.getParameter("matName"));
        pMap.put("isNot0", req.getParameter("isNot0"));
        pMap.put("earlyWarning", req.getParameter("earlyWarning"));
        pMap.put("warningStock", req.getParameter("warningStock"));

        //获取数据
        List<String[]> dataList = siStorageExcelService.buildData(pMap);
        //生成excel文件对象
        Workbook workBook = SiExcelUtils.createWorkBook(SiExcelHeader.toInputHeaders(storageExportHeaders), dataList);
        // 设置文件名
        resp.setHeader("Content-Disposition", "attachment; filename=si_storage_export.xls");
        //获取输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        //输出excel文件流
        workBook.write(servletOutputStream);
        servletOutputStream.close();
    }

}
