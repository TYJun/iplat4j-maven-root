package com.baosight.wilp.si.web;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.si.common.SiUtils;
import net.sf.jasperreports.engine.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存工单打印Controller
 * @ClassName: SiJasperPrintController
 * @Package com.baosight.wilp.si.web
 * @date: 2022年12月09日 15:22
 *
 * 1.打印入库单
 * 2.打印出库单
 */
@Controller
@RequestMapping("/si")
public class SiJasperPrintController {

    /**jasperReport编译后模板**/
    private static Map<String, JasperReport> jasperTemplateMap = new HashMap<>(4);

    /**
     * 打印入库单
     * @Title: printEnterBill
     * @param enterBillNo enterBillNo
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/printEnterBill/{enterBillNo}")
    public void printEnterBill(@PathVariable("enterBillNo") String enterBillNo, HttpServletRequest request) {
        //获取参数
        List<Map> list = getEnterParameter(Arrays.asList(enterBillNo), request);
        completeJasper(list, "enterBill", true);
         /*Map parameters = new HashMap(8);
        parameters.put("enterBillNo", enterBillNo);
        parameters.put("wareHouseName", "物资仓");
        parameters.put("supplierName", "博纳睿通软件科技有限公司");
        parameters.put("enterDate", "2023年4月3日");
        parameters.put("matClassify", "物资用品");
        parameters.put("invoiceNo", "34643232");
        parameters.put("recCreateName", "测试人一");
        parameters.put("approvalMan", "http://127.0.0.1:8086/iplat/si/showSign/202303-056c4e90c14946e9a31ccc11034df8272");
        parameters.put("manager", "http://127.0.0.1:8086/iplat/si/showSign/202303-056c4e90c14946e9a31ccc11034df8272");*/
    }

    /**
     * 批量打印
     * @Title: printEnterBills
     * @param enterBillNos enterBillNos : 入库单号集合
     * @param request request
     * @return void
     * @throws
     **/
    @CrossOrigin
    @PostMapping("/printEnterBills")
    public void printEnterBills(@RequestBody List<String> enterBillNos, HttpServletRequest request) {
        List<Map> list = getEnterParameter(enterBillNos, request);
        completeJasper(list, "enterBill", true);
    }

    @CrossOrigin
    @PostMapping("/printEnterA5Bills")
    public void printEnterA5Bills(@RequestBody List<String> enterBillNos, HttpServletRequest request) {
        List<Map> list = getEnterParameter(enterBillNos, request);
        completeJasper(list, "enterBill_A5", true);
    }

    /**
     * 入库打印单参数处理
     * @Title: getEnterParameter
     * @param enterBillNos enterBillNos
     * @param request request
     * @return java.util.List<java.util.Map>
     * @throws
     **/
    private List<Map> getEnterParameter(List<String> enterBillNos, HttpServletRequest request) {
        List<Map> list = new ArrayList<>(enterBillNos.size());
        //遍历，获取参数
        String requestURL = request.getRequestURL().toString();
        String baseURI = StringUtils.substringBefore(requestURL, request.getContextPath()) + request.getContextPath();
        for (String enterBillNo : enterBillNos) {
            EiInfo invoke = SiUtils.invoke(new EiInfo(), "SIRK0105", "queryEnterBill", new String[]{"enterBillNo"}, enterBillNo);
            Map parameters = (Map) invoke.get("parameters");
            if(StringUtils.isNotBlank(SiUtils.isEmpty(parameters.get("approvalMan")))) {
                parameters.put("approvalMan", baseURI + parameters.get("approvalMan"));
            }
            if(StringUtils.isNotBlank(SiUtils.isEmpty(parameters.get("manager")))) {
                parameters.put("manager", baseURI + parameters.get("manager"));
            }
            list.add(parameters);
        }
        return list;
    }

    /**
     * 打印出库单
     * @Title: printOutBill
     * @param outBillNo outBillNo
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/printOutBill/{outBillNo}")
    public void printOutBill(@PathVariable("outBillNo") String outBillNo, HttpServletRequest request) {
        //获取参数
        List<Map> list = getOutBillParameters(Arrays.asList(outBillNo), request);
        completeJasper(list, "outBill", true);
    }

    /**
     * 批量打印出库单
     * @Title: printOutBills
     * @param outBillNos outBillNos : 出库单号集合
     * @param request request
     * @return void
     * @throws
     **/
    @CrossOrigin
    @PostMapping("/printOutBills")
    public void printOutBills(@RequestBody List<String> outBillNos, HttpServletRequest request) {
        List<Map> list = getOutBillParameters(outBillNos, request);
        completeJasper(list, "outBill", true);
    }

    /**
     * 构建出库报表参数
     * @Title: getOutBillParameters
     * @param outBillNos outBillNos : 出库单号集合
     * @param request request
     * @return java.util.List<java.util.Map>
     * @throws
     **/
    private List<Map> getOutBillParameters(List<String> outBillNos, HttpServletRequest request) {
        List<Map> list = new ArrayList<>(outBillNos.size());
        for (String outBillNo : outBillNos) {
            EiInfo invoke = SiUtils.invoke(new EiInfo(), "SICK0104", "queryOutBill", new String[]{"outBillNo"}, outBillNo);
            Map parameters = (Map) invoke.get("parameters");
            String requestURL = request.getRequestURL().toString();
            String baseURI = StringUtils.substringBefore(requestURL, request.getContextPath()) + request.getContextPath();
            if(StringUtils.isNotBlank(SiUtils.isEmpty(parameters.get("claimMan")))) {
                parameters.put("claimMan", baseURI + parameters.get("claimMan"));
            }
            list.add(parameters);
        }
        return list;
    }

    /**
     * 编译并打印报表
     * @Title: completeJasper
     * @param pList pList : 报表参数集合
     * @param reportName reportName : 报表名称
     * @return void
     * @throws
     **/
    private void completeJasper(List<Map> pList, String reportName, boolean showPrint) {
        Connection conn = null;
        try {
            JasperReport report = null;
            if(jasperTemplateMap.containsKey(reportName)) {
                report = jasperTemplateMap.get(reportName);
            } else {
                //获取容器资源解析器
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resolver.getResources("jasperReport/"+ reportName+".jrxml");
                if(resources == null || resources.length == 0){
                    throw new Exception("获取报表文件失败");
                }
                InputStream stream = resources[0].getInputStream();
                // 编辑报表,生成的 .jasper 文件
                report = JasperCompileManager.compileReport(stream);
            }

            //配置数据库连接
            String url= PlatApplicationContext.getProperty("jdbc.url") ;
            String username= PlatApplicationContext.getProperty("jdbc.username") ;
            String password= PlatApplicationContext.getProperty("jdbc.password") ;
            Class.forName(PlatApplicationContext.getProperty("jdbc.driverClassName"));
            conn = DriverManager.getConnection(url, username , password );

            //遍历
            for (Map map : pList) {
                //打印
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, conn);
                JasperPrintManager.printReport(jasperPrint,showPrint);
                //JasperViewer.viewReport(jasperPrint, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
