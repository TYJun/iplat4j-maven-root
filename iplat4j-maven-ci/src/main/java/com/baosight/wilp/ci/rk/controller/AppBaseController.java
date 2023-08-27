package com.baosight.wilp.ci.rk.controller;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 移动端ajax请求中转Controller
 *
 * @Title: AppController
 * @ClassName: AppController
 * @Package: com.baosight.wilp.ci.rk.controller
 * @author: liu
 * @date: 2022-07-19 10:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
@RestController
public class AppBaseController {


    private final static Logger logger = LoggerFactory.getLogger(AppBaseController.class);
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
    @PostMapping("/canteenInvoicing")
    @CrossOrigin
    public Object executeMethod(HttpServletRequest request, HttpServletResponse response) {

        EiInfo outInfo = null;

        String serviceName = request.getHeader("serviceName");
        String methodName = request.getHeader("methodName");
        logger.info("调用接口服务名, 方法名:{},{}",serviceName, methodName);
        EiInfo inInfo = null;

        String prames = "";

        if (request.getParameter("prames") != null) {
            prames = request.getParameter("prames");
            logger.info("调用接口参数:{}",prames);
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
