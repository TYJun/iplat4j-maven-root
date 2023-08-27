package com.baosight.wilp.cm.dj.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * 该页面为合同文件预览
 * <p>1.初始化查询 openFile
 * @Title: CmPdf.java
 * @ClassName: CmPdf
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: gao
 * @date: 2021年11月15日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@RestController
public class CmPdf {

    /**
     * @Title: openFile
     * @Description: 初始化查询
     * <p>1.报文类型为pdf
     * <p>2.获取tomcate的bin目录
     * <p>3.返回预览pdf文件
     * 合同pdf文件预览
     *
     * @param EiInfo
     * fileName 文件名
     * filePath 文件路径
     * @return EiInfo
     * fileName 文件名
     * filePath 文件路径
     */
    @RequestMapping("/pdf")
    public void openFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.报文类型为pdf
        response.setContentType("application/pdf");
        String fileName = (String) request.getParameter("fileName");
        String filePath = (String) request.getParameter("filePath");
        //2.获取tomcate的bin目录
        String path = System.getProperty("user.dir");
        String classPath = path + File.separator + filePath+"/Content/" + fileName;
        System.out.println(classPath);
        File file=new File(classPath);
        FileInputStream input = new FileInputStream(file);

        byte[] data = new byte[input.available()];

        input.read(data);
        //3.返回预览pdf文件
        response.getOutputStream().write(data);

        input.close();
    }
}
