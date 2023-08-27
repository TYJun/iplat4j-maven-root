package com.baosight.wilp.mc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mc.jk.domain.DepartMent;
import com.alibaba.fastjson.JSONObject;
import com.baosight.wilp.mc.jk.service.ServiceMCJK01;
import org.activiti.engine.impl.util.json.XML;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DeptController extends ServiceBase {


	/**
	 * 入参:methodName 方法名    serviceName Service包下类名
	 */
	@PostMapping("/workDept")
	@CrossOrigin
	public Object handlePost(HttpServletRequest request) throws DocumentException {
		StringBuffer reqXmlData = new StringBuffer();
		try {
			InputStream inputStream = request.getInputStream();
			String s;
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			while ((s = in.readLine()) != null) {
				reqXmlData.append(s);
			}
			in.close();
			inputStream.close();
		} catch ( IOException e) {
			System.out.println("流解析xml数据异常!");
			e.printStackTrace();
		}
		//判断请求数据是否为空
		if (reqXmlData.length() <= 0) {
			System.out.println("请求数据为空!");
		}
		//json类型数据
		Document doc = DocumentHelper.parseText(reqXmlData.toString());
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
		return jsonObject.toString();
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
