package com.baosight.wilp.mp.controller;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.mp.common.MpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.OS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购通用controller
 * @ClassName: MpCommonController
 * @Package com.baosight.wilp.mp.controller
 * @date: 2022年10月31日 15:42
 *
 * 1.物资图片访问
 */
@Controller
@RequestMapping("/mp")
public class MpCommonController {

    /**
     * 根据物资ID回显图片
     * @Title: showImg
     * @param id id
     * @param resp resp
     * @return void
     * @throws
     **/
    @CrossOrigin
    @GetMapping("/showImg/{id}")
    public void showImg(@PathVariable("id") String id, HttpServletResponse resp) throws Exception {
        //获取物资的图片路径
        EiInfo invoke = MpUtils.invoke("MPTY01", "queryMatImgById", Arrays.asList("id"), id);
        String path = invoke.getString("matPath");
        //图片路径不存在，直接返回
        if(StringUtils.isBlank(path)) {
            path = getRootPath() + "/MEAL/image/noneImg.png";
        }
        //返回图片
        File file = new File(path);
        if(file.exists()) {
            //使用字节流读取本地图片
            ServletOutputStream out=null;
            BufferedInputStream buf=null;
            try {
                //使用输入读取缓冲流读取一个文件输入流
                buf = new BufferedInputStream(new FileInputStream(file));
                //利用response获取一个字节流输出对象
                out = resp.getOutputStream();
                //定义个数组,由于读取缓冲流的内容
                byte[] buffer = new byte[1024];
                //循环一直读取缓冲流中的内容到输出的对象中
                while (buf.read(buffer)!=-1){
                    out.write(buffer);
                }
                //写出请求的地方
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //关闭
                if (buf!=null) { buf.close(); }
                if (out!=null) { out.close(); }
            }
        }
    }

    private String getRootPath() {
        String classpath = this.getClass().getResource("/").getPath();
        if(System.getProperty("os.name").toLowerCase().indexOf("windows") > 0) {
            classpath.replaceFirst("/", "");
        }
        // 把WEB-INF/classes截取
        return classpath.replaceAll("/WEB-INF/classes/", "");
    }
}
