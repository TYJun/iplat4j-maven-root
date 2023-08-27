package com.baosight.wilp.mc.jk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.mc.jk.domain.DepartMent;
import com.baosight.wilp.mc.jk.service.DeptService;
import org.dom4j.*;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "DeptService", // 与接口中指定的name一致
        targetNamespace = "http://service.jk.mc.wilp.baosight.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.baosight.wilp.mc.jk.service.DeptService"// 接口地址
)
public class DeptServiceImpl implements DeptService {
    @Override
    public void insertDept(String str) {
        try {
            readStringXml(str);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public  void readStringXml(String str) throws DocumentException {
        //json类型数据
        Document doc = DocumentHelper.parseText(str.toString());
        JSONObject jsonObject = new JSONObject();
        dom4j2Json(doc.getRootElement(), jsonObject);
        Object data = jsonObject.get("DEPARTMENT");
        if (data == null) {
            //对象实现方式
            DepartMent departMent = new DepartMent();
            departMent.setBIZ_ID(jsonObject.getString("BIZ_ID"));
            departMent.setDEP_CODE(jsonObject.getString("DEP_CODE"));
            departMent.setDEP_NAME(jsonObject.getString("DEP_NAME"));
            List<DepartMent> list = new ArrayList<DepartMent>();
            list.add(departMent);
//			ServiceMCJK01 serviceMCJK01 = new ServiceMCJK01();
            EiInfo info = new EiInfo();
//			serviceMCJK01.depart(info);
            info.set("obj",list);
            info.set(EiConstant.serviceName,"MCJK01");
            info.set(EiConstant.methodName,"depart");
            EiInfo outInfo =XLocalManager.call(info);
            outInfo.setMsg("success");
        } else {
            //集合对象实现方式
            JSONArray jsonArray = jsonObject.getJSONArray("DEPARTMENT");
            List<DepartMent> list = jsonArray.toJavaList(DepartMent.class);
//			ServiceMCJK01 serviceMCJK01 = new ServiceMCJK01();
            EiInfo info = new EiInfo();
            info.set("obj",list);
            info.set(EiConstant.serviceName,"MCJK01");
            info.set(EiConstant.methodName,"depart");
            EiInfo outInfo =XLocalManager.call(info);
//			serviceMCJK01.depart(info);
        }
    }
    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    private void dom4j2Json(Element element, JSONObject json) {
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
