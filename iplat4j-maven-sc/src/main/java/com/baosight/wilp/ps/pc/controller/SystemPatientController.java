package com.baosight.wilp.ps.pc.controller;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ps.pc.domain.BillingRefund;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.bm.utils.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * SystemController 移动端ajax请求中转Controller
 * 1.配置白名单，EDCC03页面搜索ano
 * 2.本类设置订餐的统一app接口，在请求头规定类名方法名，以此分发请求
 * 
 * @Title: SystemPatientController.java
 * @ClassName: SystemPatientController
 * @Package：com.baosight.wilp.ps.pc.controller
 * @author: liutao
 * @date: 2021年9月9日 上午10:26:43
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@RestController
public class SystemPatientController {

    private final static String ADDRESS = "com.baosight.wilp.ps.sc.service."; 

    /**
     * 
     * executeMethod 执行ajax方法
     * *入参：methodName 方法名,className 服务名
     * *出餐：方法执行结果(ResultData)
     *
     * @Title: executeMethod 
     * @param request
     * @param response
     * @return 
     * @return: Object 
     * @author: liutao
     * @date: 2021年9月9日 上午10:26:57
     */
    @PostMapping("/meal")
    @CrossOrigin
    public Object executeMethod(HttpServletRequest request, HttpServletResponse response) {
        // 分发请求
        String methodName = request.getHeader("methodName");
        String className = request.getHeader("className");
        try {
            Class<?> cls = Class.forName(ADDRESS + className);
            Object obj = cls.newInstance();
            // 获取方法
            Method m = cls.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            return m.invoke(obj, request,response);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("respCode", "199");
        resultMap.put("respMsg", "系统错误");
        return resultMap;
    }
    
    /**
     * 
     * executeMethod 执行ajax方法
     * *入参：methodName 方法名,className 服务名
     * *出餐：方法执行结果(ResultData)
     *
     * @Title: executeMethod1 
     * @param paramMap
     * @param request
     * @param response
     * @return 
     * @return: Object 
     * @author: liutao
     * @date: 2021年9月9日 上午10:27:15
     */
    @PostMapping("/abcPay")
    @CrossOrigin
    public Object executeMethod1(@RequestBody Map<String, Object> paramMap,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("paramMap");
    	request.setAttribute("payMap", paramMap);
    	// 分发请求
    	String methodName = request.getHeader("methodName");
    	String className = request.getHeader("className");
    	try {
    		Class<?> cls = Class.forName(ADDRESS + className);
    		Object obj = cls.newInstance();
    		// 获取方法
    		Method m = cls.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
    		return m.invoke(obj, request,response);
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (InstantiationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (NoSuchMethodException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (SecurityException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IllegalArgumentException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (InvocationTargetException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	Map<String, Object> resultMap = new HashMap<>();
    	resultMap.put("respCode", "199");
    	resultMap.put("respMsg", "系统错误");
    	return resultMap;
    }

    /**
     * 
     * executeMethod 执行ajax方法
     * *入参：methodName 方法名,className 服务名
     * *出餐：方法执行结果(ResultData)
     * 农行支付回调接口
     * @Title: executeMethod2 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 上午10:27:59
     */
    @PostMapping({"/abcmeal"})
    @CrossOrigin
    public ResultData executeMethod2(HttpServletRequest request, HttpServletResponse response) {
      ResultData resultData = new ResultData();
      System.out.println("billNo = " + request.getParameter("billNo"));
      HashMap<String, Object> paramMap = new HashMap<>();
      paramMap.put("OrderNo", request.getParameter("OrderNo"));
      paramMap.put("MerchantId", request.getParameter("MerchantId"));
      paramMap.put("ReceiptAmount", request.getParameter("ReceiptAmount"));
      paramMap.put("OrderAmount", request.getParameter("OrderAmount"));
      try {
        EiInfo call = LocalServiceUtil.callNoTx("PSPCABCPay", "payBack", paramMap);
        if (call.getStatus() < 0) {
          System.out.println("调用PSPCABCPay.payBack失败");
          resultData.setRespCode("201");
          resultData.setRespMsg("failed");
        } else if ("success".equals(call.get("result"))) {
          resultData.setRespCode("200");
          resultData.setRespMsg("success");
        } else {
          resultData.setRespCode("201");
          resultData.setRespMsg("failed");
        } 
      } catch (Exception e) {
        e.printStackTrace();
        resultData.setRespCode("201");
        resultData.setRespMsg("程序异常");
      } 
      return resultData;
    }

    @PostMapping(value = "/HISsettlAccounts",produces = {"application/xml;charset=UTF-8"})
    @CrossOrigin
    public Object executeMethod3(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> paramMap = new HashMap<>();
        String result = "";
        InputStream fis = null;
        InputStream f = null;
        long startTime = System.currentTimeMillis();


        try {
            fis = request.getInputStream();
            byte[] bys = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while((len = fis.read(bys))!= -1){
                // 此时读到的数据是装到了数组中了.
                String res = new String(bys);
                sb.append(res);
            }
            String xml = sb.toString();
            int i = xml.indexOf("<Request>");
            xml = xml.substring(i);
            xml = xml.trim();
            Map<String, Object> nodeMap = XmlUtil.getNodeMap(xml,"BillingRefund");
            //BillingRefund refund = (BillingRefund)LocalServiceUtil.beanCastByJson(nodeMap, BillingRefund.class);
            System.out.println("HIS发来的数据：" + nodeMap);
            nodeMap.put("openId",nodeMap.get("IPID"));
            nodeMap.put("feeFn",UUIDUtils.getUUID32());
            BillingRefund refund = new BillingRefund();
            //业务流水号
            refund.setRecordFlow(StringUtil.toString(nodeMap.get("RecordFlow")));
            boolean flag = true;
            //查询结算金额
            EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryKeepAccounts", nodeMap);
            if(call.getStatus() > 0){
                List<PSPCTmealOrderBillPatient> billInfo = (List< PSPCTmealOrderBillPatient>)call.getAttr().get("obj");
                if(billInfo!=null && billInfo.size() > 0){
                    PSPCTmealOrderBillPatient bill = billInfo.get(0);
                    refund.setFeeSn(StringUtil.toString(nodeMap.get("feeFn")));
                    refund.setHospitalCode("sz");
                    refund.setInoutFlag("i");
                    refund.setIPID(bill.getOpenId());
                    refund.setPatientName(bill.getUserName());
                    refund.setBillingRefFlag("jf");
                    refund.setChargeType("1");
                    refund.setChargeDate(CalendarUtils.dateTimeShortFormat(new Date()));
                    refund.setFeeTypeCode("ss");
                    refund.setFeeTypeName("膳食费");
                    refund.setFee(bill.getBillTotalPrice().toString());
                    refund.setTotalFee(bill.getBillTotalPrice().toString());
                    refund.setRetCode("1");
                    refund.setRetCont("查询成功");
                }else{
                    flag = false;
                    refund.setRetCode("1");
                    refund.setRetCont("未查询到膳食费用");
                }
            }else{
                flag = false;
                refund.setRetCode("0");
                refund.setRetCont("调用接口失败");
            }
            f = getClass().getClassLoader().getResourceAsStream("BillingRefund.xml");
            //获取模板xml
            Document document = XmlUtil.getDocument(f);
            //将实体类的值填充到xml
            Element root = document.getRootElement();
            //填充Head
            document = XmlUtil.getXmlDocument(document,"Head",refund);
            //填充Body
            if(!flag){
                refund.setRecordFlow(null);
            }
            document = XmlUtil.getXmlDocument(document,"Body",refund);
            //document = XmlUtil.getXmlDocument(document,refund);
            //将xml输出为字符串
            result = XmlUtil.formatXml(document);
            System.out.println("返回的结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fis != null){
                    fis = null;
                }
            }
            //释放资源
            try {
                if(f != null){
                    f.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(f != null){
                    f = null;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("need" + (endTime - startTime) + "ms");
        return result;
    }
}
