package com.baosight.wilp.mp.controller;

import com.baosight.wilp.mp.common.MpExcelHeader;
import com.baosight.wilp.mp.common.MpExcelUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单导出
 * @ClassName: MpPurchaseOrderController
 * @Package com.baosight.wilp.mp.excel
 * @date: 2022年10月27日 13:29
 *
 * 1.订单导出(excel下载)
 */
@Controller
@RequestMapping("/mp/dd")
public class MpPurchaseOrderController {

    /**excel文件格式:.xls**/
    private static String FILE_SUFFIX_XLS = ".xls";
    /**excel文件格式:.xlsx**/
    private static String FILE_SUFFIX_XLSX = ".xlsx";

    /**表头集合**/
    private final static List<String> heads = Arrays.asList("物资名称", "物资分类名称", "物资规格", "物资型号", "数量");

    @Autowired
    private MpPurchaseOrderService orderService;

    /**
     * 订单导出(excel下载)
     * @Title: downloadFixedTemplate
     * @param req req
     * @param resp resp
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/orderExport")
    public void downloadFixedTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取订单明细
        String id = req.getParameter("id");
        if(StringUtils.isBlank(id)) { return; }
        List<MpPurchaseOrderDetail> detailList = orderService.queryPurchaseOrderDetailList(id);
        List<String[]> dataList = changeToExcelData(detailList);
        //生成excel文件对象
        Workbook workBook = MpExcelUtils.createWorkBook(MpExcelHeader.toInputHeaders(heads), dataList);
        // 设置文件名
        resp.setHeader("Content-Disposition", "attachment; filename=mp_purchase_order.xls");
        //获取输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        //输出excel文件流
        workBook.write((OutputStream) servletOutputStream);
        servletOutputStream.close();
    }

    /**
     * 将订单明细转换成excel表格数据
     * @Title: changeToExcelData
     * @param detailList detailList
     * @return java.util.List<java.lang.String[]>
     * @throws
     **/
    private List<String[]> changeToExcelData(List<MpPurchaseOrderDetail> detailList) {
        List<String[]> dataList = new ArrayList<>();
        detailList.forEach(detail -> {
            String[] row = new String[] {detail.getMatName(), detail.getMatTypeName(),detail.getMatSpec(),
                    detail.getMatModel(), detail.getNum().toString()};
            dataList.add(row);
        });
        return dataList;
    }

}
