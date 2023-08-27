package com.baosight.wilp.ss.wx.util;

import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * @Classname JacksonXmlUtil
 * @Description 将xml字符串转成实体类
 * @Date 2022/9/17 14:50
 */
public class JacksonXmlUtil {
    private static final Logger log = LoggerFactory.getLogger(JacksonXmlUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        T t = null;
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        try {
            t = xmlMapper.readValue(xml, clazz);
        } catch (IOException e) {
            log.error("xml转换到实体时出错!", e);
            e.printStackTrace();
        }
        return t;
    }

    public static String beanToXml(Object object) {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            xml = xmlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xml;
    }


    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToBean(String jsonData, Class<T> beanType) {
        try {
            T result = MAPPER.readValue(jsonData, beanType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);

        try {
            List<T> resultList = MAPPER.readValue(jsonData, javaType);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <K, V> Map<K, V> jsonToMap(String jsonData) {
        try {
            Map<K, V> resultMap = MAPPER.readValue(jsonData, Map.class);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml字符串转成JSON格式字符串
     *
     * @param xml
     * @return
     */
    public static String convertXmlToJson(String xml) {
        StringWriter w = new StringWriter();
        try {
            JsonParser jp = xmlMapper.getFactory().createParser(xml);
            JsonGenerator jg = MAPPER.getFactory().createGenerator(w);
            while (jp.nextToken() != null) {
                jg.copyCurrentEvent(jp);
            }
            jp.close();
            jg.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return w.toString();
    }
}
