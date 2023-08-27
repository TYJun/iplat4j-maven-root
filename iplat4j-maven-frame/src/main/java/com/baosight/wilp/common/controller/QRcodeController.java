package com.baosight.wilp.common.controller;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.FontConfig;
import com.baosight.wilp.common.util.QrUtil;
import com.baosight.wilp.common.util.ZipUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;

/**
 * @author BH6AOL
 * @version 1.0
 * @description: 处理 科室，人员，地点 二维码下载请求(/qrcode?module=(dept|perl|spot)&ids=(null|id1,id2,...))
 * @date 2021/8/30 9:42
 */
@WebServlet("/qrcode")
@MultipartConfig
public class QRcodeController extends HttpServlet {

    /**
     * 处理 HTTP GET 请求
     * 作者：jzm
     * 入参 module (模块), ids (选中的编码集合) 注意：GET传参存在长度限制
     * 如果仅仅传入 module 则 默认为导出该模块全部二维码
     * 如果传入 module 和 ids 则导出 ids集合的二维码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String module = req.getParameter("module");
        String ids = req.getParameter("ids");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        // 根据传入 module 来判断 来调用对应的方法
        switch (module){
            // 科室信息
            case "dept":{
                // 设置消息头
                resp.setHeader("Content-Disposition", "attachment; filename=" + module+"_qrcode.zip");
                if (ids == null){   // 如果不传入ids则下载全部

                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set(EiConstant.serviceName, "ACDE01");
                    eiInfo.set(EiConstant.methodName, "queryAllDept");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();

                    List<String> codes = new ArrayList<>();
                    List<String> nodes = new ArrayList<>();
                    // 从结果中提取出 编码集合（用于生成二维码） 和节点集合(用于生成二维码下面的文字)
                    result.forEach(e->{
                        codes.add(e.get("deptNum"));
                        nodes.add("["+e.get("deptNum")+"] "+e.get("deptName"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codes);

                    // 添加 二维码下方的文字标识
                    addNote(bufferedImageList,nodes);

                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codes,servletOutputStream);
                }else{
                    // 下载部分
                    String[] idArray = ids.split(",");
                    List<String> codes = Arrays.asList(idArray);
                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set("list",codes);
                    eiInfo.set(EiConstant.serviceName, "ACDE01");
                    eiInfo.set(EiConstant.methodName, "queryDeptInfoByNum");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();
                    List<String> nodes = new ArrayList<>();
                    List<String> codess = new ArrayList<>();
                    result.forEach(e->{
                        codess.add(e.get("deptNum"));
                        nodes.add("["+e.get("deptNum")+"] "+e.get("deptName"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codess);
                    // 添加 二维码下方的文字标识
                    addNote(bufferedImageList,nodes);

                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codess,servletOutputStream);
                }
            }break;
            // 人员信息
            case "perl":{
                // 设置消息头
                resp.setHeader("Content-Disposition", "attachment; filename=" + module+"_qrcode.zip");
                if (ids == null){
                    // 下载全部
                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set(EiConstant.serviceName, "ACPE01");
                    eiInfo.set(EiConstant.methodName, "queryAllPerl");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();
                    List<String> codes = new ArrayList<>();
                    List<String> nodes = new ArrayList<>();
                    result.forEach(e->{
                        codes.add(e.get("workNo"));
                        nodes.add("["+e.get("workNo")+"] "+e.get("name"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codes);
                    // 添加 文字标识
                    addNote(bufferedImageList,nodes);
                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codes,servletOutputStream);
                }else{
                    // 下载部分
                    String[] idArray = ids.split(",");
                    List<String> codes = Arrays.asList(idArray);
                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set("list",codes);
                    eiInfo.set(EiConstant.serviceName, "ACPE01");
                    eiInfo.set(EiConstant.methodName, "queryPerlInfoByNum");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();
                    List<String> nodes = new ArrayList<>();
                    List<String> codess = new ArrayList<>();
                    result.forEach(e->{
                        codess.add(e.get("workNo"));
                        nodes.add("["+e.get("workNo")+"] "+e.get("name"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codess);
                    // 添加 文字标识
                    addNote(bufferedImageList,nodes);
                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codess,servletOutputStream);
                }
            }break;
            // 地点信息
            case "spot":{
                // 设置消息头
                resp.setHeader("Content-Disposition", "attachment; filename=" + module+"_qrcode.zip");
                if (ids == null){
                    // 下载全部
                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set(EiConstant.serviceName, "ACSP01");
                    eiInfo.set(EiConstant.methodName, "queryAllSpot");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();
                    List<String> codes = new ArrayList<>();
                    List<String> nodes = new ArrayList<>();
                    result.forEach(e->{
                        codes.add(e.get("spotNum"));
                        nodes.add("["+e.get("spotNum")+"] "+e.get("building")+" "+e.get("floor")+" "+e.get("room"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codes);
                    // 添加 文字标识
                    addNote(bufferedImageList,nodes);
                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codes,servletOutputStream);
                }else{
                    // 下载部分
                    String[] idArray = ids.split(",");
                    List<String> codes = Arrays.asList(idArray);
                    EiInfo eiInfo = new EiInfo();
                    eiInfo.set("list",codes);
                    eiInfo.set(EiConstant.serviceName, "ACSP01");
                    eiInfo.set(EiConstant.methodName, "querySpotInfoByNum");
                    EiInfo info = XLocalManager.call(eiInfo);
                    List<Map<String,String>> result = info.getBlock("result").getRows();
                    List<String> nodes = new ArrayList<>();
                    List<String> codess = new ArrayList<>();
                    result.forEach(e->{
                        codess.add(e.get("spotNum"));
                        nodes.add("["+e.get("spotNum")+"] "+e.get("building")+" "+e.get("floor")+" "+e.get("room"));
                    });
                    // 通过 编码 生成 二维码
                    List<BufferedImage> bufferedImageList = getBufferedImageList(codess);
                    // 添加 文字标识
                    addNote(bufferedImageList,nodes);
                    // 调用 压缩方法将 bufferedImage 压缩 并 以流的形式返回给 servletOutputStream
                    ZipUtils.toZip(bufferedImageList,codess,servletOutputStream);
                }
            }break;
        }
        servletOutputStream.close();
    }

    /**
     * 通过编码 list 生成QRCode 的 BufferedImage
     * 作者：jzm
     * @param codes
     * @return
     */
    public static List<BufferedImage> getBufferedImageList(List<String> codes) throws IOException {
        List<BufferedImage> bufferedImageList = new ArrayList<>();

        // 尝试读取 LOGO
        InputStream inputStream = QrUtil.class.getResourceAsStream("logo.jpg");
        Image logoImage = null;
        if (inputStream != null){
            Image src = ImageIO.read(inputStream);
            // 压缩LOGO
            logoImage = src.getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        }
        for (String code : codes) {
            String content = code;
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            BitMatrix bitMatrix = null;
            try {
                bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
            } catch (WriterException writerException) {
                writerException.printStackTrace();
            }
            try {
                bufferedImageList.add(QrUtil.toBufferedImage(bitMatrix,logoImage));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


        return bufferedImageList;
    }
    /**
     * 添加二维码下方注释文字
     * 作者：jzm
     *
     */
    public static void addNote(List<BufferedImage> bufferedImageList,List<String> notes){
        // 添加 二维码下方的文字标识
        //从util/simhei.ttf获得字体SIMHEI
        //graphics.setFont(new Font("黑体",Font.BOLD,16));
        Font definedFont = FontConfig.getSimheiFont(Font.BOLD,16);

        for (int i = 0; i < bufferedImageList.size(); i++) {
            BufferedImage bufferedImage = bufferedImageList.get(i);

            // 使用 Graphics2D 才能设置平滑字体
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

            // 设置自定义字体
            graphics.setFont(definedFont);

            // 设置字体颜色
            graphics.setColor(Color.BLACK);

            // 平滑字体
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // 左右居中
            int oneSize = (int) (bufferedImage.getWidth()/2-0.90*graphics.getFontMetrics().stringWidth(notes.get(i))/2);

            // 绘制
            graphics.drawString(notes.get(i),oneSize,bufferedImage.getHeight() - 20);

            // 释放
            graphics.dispose();
        }
    }

}
