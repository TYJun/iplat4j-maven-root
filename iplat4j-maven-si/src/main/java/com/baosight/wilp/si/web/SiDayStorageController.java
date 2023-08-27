package com.baosight.wilp.si.web;

import com.baosight.wilp.si.common.SiExcelHeader;
import com.baosight.wilp.si.common.SiExcelUtils;
import com.baosight.wilp.si.web.service.SiExcelImportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: SiDayStroageController
 * @Package com.baosight.wilp.si.web
 * @date: 2023年04月24日 9:13
 */
@Controller
@RequestMapping("/si")
public class SiDayStorageController {

    /**表头集合**/
    private final static List<String> dayHeads = Arrays.asList("日期","仓库名称", "物资编码","物资名称", "物资规格", "物资型号", "物资分类名称",
            "计量单位", "单价", "日初库存总量","日初库存总金额(元)", "日末库存总量", "日末库存总金额(元)", "入库数量", "入库总金额(元)","调拨入库数量",
            "调拨入库总金额(元)","出库数量","出库总金额(元)","调拨出库数量","调拨出库总金额(元)");

    @Autowired
    @Qualifier("siDayStorageExportService")
    private SiExcelImportService siDayStorageExportService;

    @CrossOrigin
    @GetMapping("/exportDayStorage")
    public void exportStorage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Map<String, Object> pMap = new HashMap<>(16);
        pMap.put("beginTime", req.getParameter("beginTime"));
        pMap.put("endTime", req.getParameter("endTime"));
        pMap.put("wareHouseNo", req.getParameter("wareHouseNo"));
        pMap.put("matNum", req.getParameter("matNum"));
        pMap.put("matName", req.getParameter("matName"));

        //获取数据
        List<String[]> dataList = siDayStorageExportService.buildData(pMap);
        //生成excel文件对象
        Workbook workBook = SiExcelUtils.createWorkBook(SiExcelHeader.toInputHeaders(dayHeads), dataList);
        // 设置文件名
        resp.setHeader("Content-Disposition", "attachment; filename=si_day_storage_export.xls");
        //获取输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        //输出excel文件流
        workBook.write(servletOutputStream);
        servletOutputStream.close();
    }
}
