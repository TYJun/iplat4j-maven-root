//package com.baosight.wilp.mc.jk.service;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.cxf.binding.soap.SoapMessage;
//import org.apache.cxf.helpers.IOUtils;
//import org.apache.cxf.interceptor.Fault;
//import org.apache.cxf.phase.AbstractPhaseInterceptor;
//import org.dom4j.Document;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//
//import java.io.*;
//import java.net.URLEncoder;
//
//public class WsInInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
//    public WsInInterceptor(String phase) {
//        super(phase);
//    }
//
//    @Override
//    public void handleMessage(SoapMessage message) throws Fault {
//        InputStream is = message.getContent(InputStream.class);
//        if (is != null) {
//            try {
//                String str = IOUtils.toString(is,"UTF-8");
//                // 原请求报文
//                System.out.println("====> request xml=\r\n" + str);
//
//                // 把siebel格式的报文替换成符合cxf带前缀的命名空间
//                str = str.replace("<msg>", "<msg><![CDATA[")
//                        .replace("</msg>", "]]></msg>");
//                // 替换后的报文
//                System.out.println("====> replace xml=\r\n" + str);
//
//                try {
//                    str =  formatXml(str);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                str = URLEncoder.encode(str, "UTF-8");
//
//                str = str.replace("&lt;","<")
//                        .replace("&gt;",">")
//                        .replace("%","");
//
//                InputStream ism = new ByteArrayInputStream(str.getBytes());
//
//                message.setContent(InputStream.class, ism);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static String formatXml(String inputXML) throws Exception {
//        String xml = null;
//        SAXReader reader = new SAXReader();
//        XMLWriter writer = null;
//        Document document = reader.read(new StringReader(inputXML));
//        try {
//            if (document != null) {
//                StringWriter stringWriter = new StringWriter();
//                OutputFormat format = OutputFormat.createPrettyPrint();
//                writer = new XMLWriter(stringWriter, format);
//                // 取消转义
//                writer.setEscapeText(false);
//                writer.write(document);
//                writer.flush();
//                xml = stringWriter.getBuffer().toString();
//            }
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return xml;
//    }
//
//}
