package com.baosight.wilp.app.controller;

import com.alibaba.fastjson.JSON;
import com.baosight.wilp.app.domain.InitProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @PackageName com.baosight.wilp.security.controller
 * @ClassName RefreshController
 * @Description 用于临时修改一些系统不想重启的配置
 * @Author chunchen2
 * @Date 2023/8/15 10:23
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/8/15 10:23
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Controller
@RequestMapping("/refresh")
public class RefreshController {

    @RequestMapping("/config")
    @CrossOrigin
    public void refreshConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 此处目前是防止app登录用的同一个接口，但是有的没有添加验证码，无法登录的问题
        initProperties();
        handleResponse(InitProperties.isCheckAppVerifyCode, response);
    }

    private void initProperties() {
        Properties pro = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        try {
            pro.load(new InputStreamReader(in, "utf8"));
            String checkAppVerify = pro.getProperty("iplat.login.app.verifyCode", "true");
            if(null != checkAppVerify) {
                InitProperties.isCheckAppVerifyCode = Boolean.parseBoolean(checkAppVerify);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void handleResponse(boolean msg, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.println("{result: "+ msg + "}");
        out.flush();
        out.close();
    }

}
