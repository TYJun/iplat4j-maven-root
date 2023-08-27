package com.baosight.wilp.mc.jk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.mc.jk.domain.Personnel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入人员数据
 */
@WebService(serviceName = "PersonnelService", // 与接口中指定的name一致
        targetNamespace = "http://service.jk.mc.wilp.baosight.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.baosight.wilp.mc.jk.service.PersonnelService"// 接口地址
)
@Component
public class PersonnelServiceImpl extends ServiceBase {

    public String insertPersooner() throws DocumentException {
        PersonnelServiceImpl per = new PersonnelServiceImpl();
        per.updatePop();
        return "成功！";

    }

    /**
     * @description：根据工号查询该人员是否存在系统，如果存在则更新，不存在则新加数据
     * @author:kwr
     * @parms:
     * @time：2022-9-17 return eiInfo
     */
    public EiInfo updatePop() throws DocumentException {
        EiInfo info = new EiInfo();

        String url = "http://199.168.200.136/esbsvc.asmx/EsbApi";

        String xml = getXMLs();

        Map<String, String> pMap = new HashMap<>();
        pMap.put("msg", xml);

        HttpClient client = new HttpClient();
        PostMethod httpPost = new PostMethod(url);
        httpPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String result = null;
        for (Map.Entry<String, String> entry : pMap.entrySet()) {
            httpPost.addParameter(entry.getKey(), entry.getValue());
        }
        try {
            client.executeMethod(httpPost);
            result = new String(httpPost.getResponseBody(), StandardCharsets.UTF_8);
            System.out.println("HttpClient中的result：" + new String(httpPost.getResponseBody(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
            ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
        }

        //对于特殊字符去转义
        result = StringEscapeUtils.unescapeHtml(result);

        String xmlStr3=result.replaceAll("&lt;","<");
        String xmlStr4=xmlStr3.replaceAll("&gt;",">");
        String xmlStr5=xmlStr4.replaceAll("<Response>","");
        String xmlStr6=xmlStr5.replaceAll("</Response>","");
        String xmlStr7=xmlStr6.replaceAll("<Data>","");
        String xmlStr8=xmlStr7.replaceAll("</Data>","");
        String xmlStr9=xmlStr8.replaceAll("<STAFF_LIST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">","");
        String xmlStr10=xmlStr9.replaceAll("</STAFF_LIST>","");
		String xmlStr11=xmlStr10.replaceAll("<STAFF_INFO>","<STAFF_INFO xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
//        String xmlStr11=xmlStr10.replaceAll("\"xsir: nil=\"true \"","");

        System.out.println("xmlStr11:"+xmlStr11);
        //json类型数据
        Document doc = DocumentHelper.parseText(xmlStr11.toString());
        JSONObject jsonObject = new JSONObject();
        dom4j2Json(doc.getRootElement(), jsonObject);
        Object data = jsonObject.get("STAFF_INFO");

        if(data == null){
            System.out.println("未获取到数据");
        }else {

            JSONArray jsonArray = jsonObject.getJSONArray("STAFF_INFO");
            List<Personnel> list = jsonArray.toJavaList(Personnel.class);
            for (int a = 0; a < list.size(); a++) {
                Personnel personnelInfo = (Personnel) list.get(a);
                System.out.println("personnelInfo:"+personnelInfo);

                HashMap<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("STAFF_CODE",personnelInfo.getSTAFF_CODE());
                paramMap.put("STAFF_NAME",personnelInfo.getSTAFF_NAME());
                paramMap.put("ID_NUMBER",personnelInfo.getID_NUMBER());
                paramMap.put("GENDER_CODE",personnelInfo.getGENDER_CODE());
                paramMap.put("TELEPHONE",personnelInfo.getTELEPHONE());
                paramMap.put("DEFAULT_ADMIN_DEP_CODE",personnelInfo.getDEFAULT_ADMIN_DEP_CODE());
                paramMap.put("DEFAULT_ADMIN_DEP_NAME",personnelInfo.getDEFAULT_ADMIN_DEP_NAME());
                paramMap.put("DEFAULT_BIZ_ID",personnelInfo.getDEFAULT_BIZ_ID());
                paramMap.put("RCRT_NAME",personnelInfo.getRCRT_NAME());
                paramMap.put("RCRT_CODE",personnelInfo.getRCRT_CODE());
                paramMap.put("WEIXIN_WORK_ACCOUNT",personnelInfo.getWEIXIN_WORK_ACCOUNT());

                System.out.println("paramMap:"+paramMap);

                List<Personnel> list1 = dao.query("MCJK02.query",personnelInfo);
                if(CollectionUtils.isEmpty(list1)) {
                    dao.insert("MCJK02.insert",paramMap);
                }

                dao.update("MCJK02.update",paramMap);
                List<Personnel> list2 = dao.query("MCJK02.query2",paramMap);


                paramMap.put("dept_id",list2.get(0).getId());

                dao.update("MCJK02.update2",paramMap);

            }
        }

        System.out.println("jsonObject = " + jsonObject);
        info.setMsg("更新成功");
        return info;
    }


    /**
     * 组合参数
     * @param
     * @return
     */
    public static String getXMLs(){

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		System.out.println(date.format(formatter));
        String string = formatter.format(date);
        System.out.println(formatter.format(date));
        String soapXML ="<Message id='284' code='S0010' name='ProviderInfoQuery' appid='70'>"
                +"<REQUEST>"
                +"<UNIQUE_ID></UNIQUE_ID>"
                +"<STAFF_CODE></STAFF_CODE>"
                +"<UPDATE_DATE>"+string+"</UPDATE_DATE>"
                +"</REQUEST>"
                +"</Message>";
        return soapXML;
    }

    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        //如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!attr.getValue().isEmpty()) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !element.getText().isEmpty()) {
            //如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {
            //有子元素
            if (!e.elements().isEmpty()) {
                //子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {
                        //如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }
            } else {
                //子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!attr.getValue().isEmpty()) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

}
