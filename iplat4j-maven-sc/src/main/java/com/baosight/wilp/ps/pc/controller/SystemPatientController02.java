package com.baosight.wilp.ps.pc.controller;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.ps.pc.domain.BillingRefund;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.bm.utils.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.baosight.iplat4j.core.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class SystemPatientController02 {

    private final static String ADDRESS = "com.baosight.wilp.ps.pc.service.";

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
    @PostMapping("/meals")
    @CrossOrigin
    public Object handlePost(HttpServletRequest request, HttpServletResponse response) {

        EiInfo outInfo = null;

        String serviceName = request.getHeader("serviceName");
        String methodName = request.getHeader("methodName");

        EiInfo inInfo = null;

        String prames = "";

        if (request.getParameter("prames") != null) {
            prames = request.getParameter("prames");
            inInfo = EiInfo.parseJSONString(prames);
        } else {
            inInfo = new EiInfo();
        }

        if ((StringUtils.isNotEmpty(serviceName)) && (StringUtils.isNotEmpty(methodName))) {
            try {

                inInfo.set(EiConstant.serviceName, serviceName);
                inInfo.set(EiConstant.methodName, methodName);
                outInfo = XLocalManager.call(inInfo);
            } catch (PlatException ex) {
                outInfo.setStatus(EiConstant.STATUS_FAILURE);
                outInfo.setMsg("服务" + serviceName + "-" + methodName + "调用失败:" + ex.getMessage() + ",错误原因:"
                        + ex.getCause());
//				LOGGER.error(ex.getMessage(), ex);
            }
        } else {
            throw new PlatException("传入参数json中未指定服务号serviceId或服务serviceName、methodName");
        }

        return outInfo;

    }
}
