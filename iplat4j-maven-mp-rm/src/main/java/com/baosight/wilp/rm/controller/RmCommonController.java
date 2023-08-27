package com.baosight.wilp.rm.controller;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.rm.common.RmUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Arrays;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用通用controller
 * @ClassName: MpCommonController
 * @Package com.baosight.wilp.mp.controller
 * @date: 2022年10月31日 15:42
 * <p>
 * 1.根据物资ID回显图片
 * 2.根据物资分类编码和物资编码回显图片
 */
@Controller
@RequestMapping("/rm")
public class RmCommonController {

    /**
     * 获取操作系统
     */
    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * 根据物资ID回显图片
     *
     * @param id   id : 物资ID
     * @param resp resp
     * @return void
     * @throws
     * @Title: showImg
     **/
    @CrossOrigin
    @GetMapping("/showImg/{id}")
    public void showImg(@PathVariable("id") String id, HttpServletResponse resp) throws Exception {
        //获取物资的图片路径
        EiInfo invoke = RmUtils.invoke("RMTY01", "queryMatImgById", Arrays.asList("id"), id);
        String path = invoke.getString("matPath");
        //图片路径不存在，直接返回
        if (StringUtils.isBlank(path)) {
            path = getRootPath() + "/MEAL/image/noneImg.png";
        }
        showImg(resp, path);
    }

    /**
     * 根据物资分类编码和物资编码回显图片
     *
     * @param nums nums : 物资分类编码和物资编码拼接字符串
     * @param resp resp
     * @return void
     * @throws
     * @Title: showImg2
     **/
    @CrossOrigin
    @GetMapping("/showImg2/{nums}")
    public void showImg2(@PathVariable("nums") String nums, HttpServletResponse resp) throws Exception {
        //参数处理
        String[] param = nums.split("-");
        //获取物资的图片路径
        EiInfo invoke = RmUtils.invoke("RMTY01", "queryMatImgByNum", Arrays.asList("matClassCode", "matCode"),
                param[0], param[1]);
        String path = invoke.getString("matPath");
        //图片路径不存在，直接返回
        if (StringUtils.isBlank(path)) {
            path = getRootPath() + "/MEAL/image/noneImg.png";
        }
        showImg(resp, path);
    }

    /**
     * 将图片路径转换成图片并返回
     *
     * @param resp resp
     * @param path path
     * @return void
     * @throws
     * @Title: showImg
     **/
    private void showImg(HttpServletResponse resp, String path) throws IOException {
        //返回图片
        File file = new File(path);
        if (file.exists()) {
            //使用字节流读取本地图片
            ServletOutputStream out = null;
            BufferedInputStream buf = null;
            try {
                //使用输入读取缓冲流读取一个文件输入流
                buf = new BufferedInputStream(new FileInputStream(file));
                //利用response获取一个字节流输出对象
                out = resp.getOutputStream();
                //定义个数组,由于读取缓冲流的内容
                byte[] buffer = new byte[1024];
                //循环一直读取缓冲流中的内容到输出的对象中
                while (buf.read(buffer) != -1) {
                    out.write(buffer);
                }
                //写出请求的地方
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭
                if (buf != null) {
                    buf.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    private String getRootPath() {
        String classpath = this.getClass().getResource("/").getPath();
        if (OS.indexOf("windows") > 0) {
            classpath.replaceFirst("/", "");
        }
        // 把WEB-INF/classes截取
        return classpath.replaceAll("/WEB-INF/classes/", "");
    }

    /**
     * 根据电子签名编码回显图片
     *
     * @param fileCode fileCode : fileCode : 电子签名编码
     * @param resp     resp
     * @return void
     * @throws
     * @Title: showImg
     **/
    @CrossOrigin
    @GetMapping("/showSign/{fileCode}")
    public void showSign(@PathVariable("fileCode") String fileCode, HttpServletResponse resp) throws Exception {
        //获取物资的图片路径
        EiInfo invoke = RmUtils.invoke("XQMS03", "getSignImageByFileCode", Arrays.asList("fileCode"), fileCode);
        String imgBase64 = invoke.getString("data");
        imgBase64 = StringUtils.substringAfter(imgBase64, "base64,");
        //图片路径不存在，直接返回
        if (StringUtils.isBlank(imgBase64)) {
            return;
        }
        outputImg(resp, imgBase64);
    }

    /**
     * 将图片base64输出
     *
     * @param resp      resp
     * @param imgBase64 imgBase64
     * @return void
     * @throws
     * @Title: outputImg
     **/
    private void outputImg(HttpServletResponse resp, String imgBase64) throws IOException {
        resp.setContentType("image/png;charset=utf-8");
        byte[] decode = DatatypeConverter.parseBase64Binary(imgBase64);
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            output.write(decode);
            ServletOutputStream out = resp.getOutputStream();
            output.writeTo(out);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
