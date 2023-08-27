package com.baosight.wilp.ir.dl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baosight.bpm.util.StringUtil;
import com.baosight.wilp.ir.dl.aop.AppLoginAspect;
import com.baosight.wilp.ir.dl.util.VerifyCode;
import com.baosight.wilp.utils.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @PackageName com.baosight.wilp.ir.dl.controller
 * @ClassName VerifyController
 * @Description TODO
 * @Author chunchen2
 * @Date 2021/12/1 14:32
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/12/1 14:32
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Controller
@RequestMapping("/verifyController")
public class VerifyController {

    @RequestMapping("/verCode")
    @CrossOrigin
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-type","application/octet-stream");
        int width = 100;
        int height = 44;
        int size = 4;
        VerifyCode vc = new VerifyCode(width,height,size);
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();

        String uuid;
        String deviceId = request.getParameter("deviceId");
        if (StringUtil.isNotEmpty(deviceId)) { // 有设备码，直接用设备码做唯一标识
            uuid = deviceId;
        } else {
            uuid = UUID.fastUUID().toString(true);
        }

        AppLoginAspect.verifyCodeMap.put(uuid, text);
        VerifyCode.output(image, os);
        String img = Base64Utils.encodeToString(os.toByteArray());
        responseReq(response, uuid, img);
    }

    private void responseReq(HttpServletResponse response, String uuid, String img) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("respCode", "200");
        jsonObject.put("uuid", uuid); // 用于唯一标识一台机器，知道验证码的来源，用于判断
        jsonObject.put("img", img);   // 返回的图片base64字符串

        PrintWriter writer = response.getWriter();
        writer.print(jsonObject);
        writer.flush();
    }
}
