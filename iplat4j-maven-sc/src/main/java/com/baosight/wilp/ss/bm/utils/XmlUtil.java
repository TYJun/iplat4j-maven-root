package com.baosight.wilp.ss.bm.utils;


import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ps.pc.domain.BillingRefund;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class XmlUtil {

    public static void main(String[] args) throws Exception {
        /*XmlUtil t = new XmlUtil();
        SAXReader saxReader = new SAXReader();
        // 读取文件
        Document read = saxReader.read(t.getClassPath("BillingRefund.xml"));
        // 获取根节点
        Element rootElement = read.getRootElement();
        t.getNodes(rootElement);*/
        getDocument( new BillingRefund());
    }


    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    public static Document getXmlDocument(Document document,Object b) throws Exception{
        // 获取根节点元素
        Element root = document.getRootElement();
        Field[] field = b.getClass().getDeclaredFields();
        // 获取实体类b的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) {
            // 遍历所有有属性
            String name = field[j].getName();
            // 获取属属性的名字
            if (!name.equals("serialVersionUID")) {
                //去除串行化序列属性
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 将属性的首字符大写，方便构造get，set方法
                Method m = b.getClass().getMethod("get" + name);
                // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                String propertievalue = (String) m.invoke(b);
                // 找到对应的节点
                Element propertie = findNode(root,name);
                //Element propertie = root.addElement(name);
                if(propertie == null){
                    continue;
                }
                //没有值的填充空字符串
                propertie.setText(StringUtil.isBlank(propertievalue) ? "" : propertievalue);
            }
        }
        return document;
    }
    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    public static Document getXmlDocument(Document document,String elementName,Object b) throws Exception{
        // 获取根节点元素
        Element root = document.getRootElement();
        Element node = findNode(root, elementName);
        // 获取根节点元素
        Field[] field = b.getClass().getDeclaredFields();
        // 获取实体类b的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) {
            // 遍历所有有属性
            String name = field[j].getName();
            // 获取属属性的名字
            if (!name.equals("serialVersionUID")) {
                //去除串行化序列属性
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 将属性的首字符大写，方便构造get，set方法
                Method m = b.getClass().getMethod("get" + name);
                // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                String propertievalue = (String) m.invoke(b);
                // 找到对应的节点
                Element propertie = findNode(node,name);
                //Element propertie = root.addElement(name);
                if(propertie == null){
                    continue;
                }
                //没有值的填充空字符串
                propertie.setText(StringUtil.isBlank(propertievalue) ? "" : propertievalue);
            }
        }
        return document;
    }
    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    public static Document getDocument(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
            // 创建根节点元素
            Element root = document.addElement(b.getClass().getSimpleName());
            Field[] field = b.getClass().getDeclaredFields();
            // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                // 遍历所有有属性
                String name = field[j].getName();
                // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {
                    //去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
                    // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    String propertievalue = (String) m.invoke(b);
                    // 获取属性值
                    Element propertie = root.addElement(name);
                    propertie.setText(StringUtil.isBlank(propertievalue) ? "" : propertievalue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public static String formatXMLFile(InputStream in) {
        SAXReader reader = new SAXReader();
        String requestXML = null;
        XMLWriter writer = null;
        try {
            Document document = reader.read(in);
            if (document != null) {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = OutputFormat.createPrettyPrint();
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return requestXML;
    }

    public static String formatXml(Document document) throws IOException {
        String requestXML = null;
        XMLWriter writer = null;
        try {
            if (document != null) {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = OutputFormat.createPrettyPrint();
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return requestXML;
    }


    // 1. 通过文件名获取输入流
    public InputStream getClassPath(String xmlPath) throws Exception {
        InputStream fis = getClass().getClassLoader().getResourceAsStream(xmlPath);
        return fis;
    }

    // 2. 获取节点信息
    public static void getNodes(Element rootElement) {
        System.out.println("获取当前名称:" + rootElement.getName());
        // 获取属性信息
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性:" + attribute.getName() + " : " + attribute.getText());
        }
        // 获取属性value
        String value = rootElement.getTextTrim();
        if (!StringUtil.isEmpty(value)) {
            System.out.println("value:" + value);
        }
        // 使用迭代器遍历,继续遍历子节点
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNodes(next);
        }
    }


    //抽取入参返回出参
    public static BillingRefund getXmlInfo(String xml) {
        BillingRefund returnInfo = new BillingRefund();
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        Element el = document.getRootElement();
        //获取opReq数据
        Element opReqel = el.element("opReq");
        String serialNo = opReqel.element("serialNo").getTextTrim();
        //获取ReqParam数据
        Element reqParamel = opReqel.element("ReqParam");
        String bsnCode = reqParamel.element("bsnCode").getTextTrim();
        //封装返回结果
        Document documentReturn = DocumentHelper.createDocument();// 获取document
        documentReturn.setXMLEncoding("GBK");// 设置编码
        Element oneChildElement = documentReturn.addElement("CLEcdsData");// 创建根元素
        Element opRepElement = oneChildElement.addElement("opRep");// 创建根元素
        Element serialNoElement = opRepElement.addElement("serialNo");// 创建子元素
        Element retCodeElement = opRepElement.addElement("retCode");// 创建子元素
        Element retMsgElement = opRepElement.addElement("retMsg");// 创建子元素
        Element opResultElement = opRepElement.addElement("opResult");// 创建子元素
        if (opReqel.element("serialNo").getTextTrim() != null) {
            serialNoElement.setText(opReqel.element("serialNo").getTextTrim());
        }
        //默认成功
        retCodeElement.setText("001");
        retMsgElement.setText("成功");
        returnInfo.setAddFee("11");
        //returnInfo可以重新封装对应的参数体
        return returnInfo;
    }

    /**
     * Todo(这里用一句话描述这个方法的作用)
     * @Title: getNodeMap
     * @author xiajunqing
     * @date: 2021/12/30 10:01
     * @Param xml xml字符串
     * @Param rootNode 目标节点
     * @return: Map<String, Object>
     */
    public static Map<String, Object> getNodeMap(String xml, String targetNode) {
        Document document = getDocument(xml);
        // 获得根节点
        Element root = document.getRootElement();
        return getTargetNodeMap(root, targetNode);
    }

    /**
     * Todo(这里用一句话描述这个方法的作用)
     * @Title: getNodeMap
     * @author xiajunqing
     * @date: 2021/12/30 10:01
     * @Param in xml输入流
     * @Param targetNode 目标节点
     * @return: Map<String, Object>
     */
    public static Map<String, Object> getNodeMap(InputStream in, String targetNode) throws Exception{
        Document document = getDocument(in);
        // 获得根节点
        Element root = document.getRootElement();
        return getTargetNodeMap(root, targetNode);
    }

    /**
     * Todo(这里用一句话描述这个方法的作用)
     *
     * @Title:getTargetNodeMap
     * @author xiajunqing
     * @date: 2021/12/30 10:07
     * @Param root 根节点
     * @Param targetNode 目标节点
     * @return: Map<String, Object>
     */
    public static Map<String, Object> getTargetNodeMap(Element root, String targetNode) {
        List<Element> list = new ArrayList<Element>();
        if (targetNode.equals(root.getName())) {
            //根节点就是目标节点
            list = root.elements();
        } else {
            //根节点不是目标节点，递归从子节点中找
            Element node = findNode(root, targetNode);
            list = node.elements();
        }
        //组装成Map
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {
            Element e = list.get(i);
            map.put(e.getName(), e.getText());
        }
        return map;
    }


    /**
     * Todo(递归从节点中找指定节点)
     * @Title:
     * @author xiajunqing
     * @date: 2021/12/30 10:08
     * @Param e 节点
     * @Param targetNode 目标节点
     * @return:
     */
    public static Element findNode(Element e, String targetNode) {
        if (e == null) {
            return null;
        }
        if (targetNode.equals(e.getName())) {
            return e;
        }
        List<Element> children = e.elements();
        if (children != null) {
            Element target = null;
            for (Element child : children) {
                target = findNode(child, targetNode);
                if (target != null) {
                    return target;
                }
            }
        }
        return null;
    }

    /**
     * xml解析成document对象
     * @param xml
     * @return
     */
    public static Document getDocument(String xml) {
        StringReader stringReader = new StringReader(xml);
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("utf-8");
        Document document = null;
        try {
            document = saxReader.read(stringReader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * xml流解析成document对象
     * @param in
     * @return
     */
    public static Document getDocument(InputStream in) throws Exception{
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("utf-8");
        Document document = null;
        document = saxReader.read(in);
        return document;
    }

}
