package com.baosight.wilp.mc.jk.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mc.jk.domain.DepartMent;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mc.jk.utils.XmlDataTools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.*;

public class ServiceMCJK01 extends ServiceBase{
	public static final  String GET_PA_INFO = PlatApplicationContext.getProperty("ac_pa_info");
	public String projectSchema=PlatApplicationContext.getProperty("projectSchema");
	public EiInfo depart(EiInfo inInfo) {
		List<EiInfo> list = new ArrayList<EiInfo>();
		list.add(inInfo);
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr();
		List lll= (List) attr.get("obj");
		for (int a = 0; a < lll.size(); a++) {
			DepartMent departMent = (DepartMent) lll.get(a);
			System.out.println("-----------------------------");
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("BIZ_ID",departMent.getBIZ_ID());
			paramMap.put("DEP_CODE",departMent.getDEP_CODE());
			paramMap.put("DEP_NAME",departMent.getDEP_NAME());
			System.out.println(paramMap);

			List<DepartMent> list1 = dao.query("MCJK01.query",departMent);
			if(CollectionUtils.isEmpty(list1)) {
				dao.insert("MCJK01.insert",departMent);
			}

			dao.update("MCJK01.update",paramMap);
		}
		inInfo.setMsg("200");
		return inInfo;
	}


	/**
	 * @description：根据平台的BIZ_ID查询该科室是否存在系统，如果存在则更新，不存在则新加数据
	 * @author:kwr
	 * @parms:
	 * @time：2022-9-17 return eiInfo
	 */
	public EiInfo updateDept(EiInfo info) throws DocumentException {

		String url = "http://199.168.200.136/esbsvc.asmx/EsbApi";
		//String url="http://127.0.0.1:8080/web/demo/dept.xml"; //测试用
		String xml = getXMLs();

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);

		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		//GetMethod httpPost = new GetMethod(url);  //测试用
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

		Document doc=null;
		try
		{
			doc = XmlDataTools.parseXmlString(result);
			doc = XmlDataTools.parseXmlString(doc.getRootElement().getText()); //二次解析
		}
		catch (IOException | DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("解析错误的XML文件内容("+e.toString()+")："+result);
			return info;
		}
		JSONObject resultJson=XmlDataTools.xmlDocToJson(doc, false);
		System.out.println("解析后得到的JSON："+(resultJson==null?"NULL！！！":JSONObject.toJSONString(resultJson, true)));
		JSONObject responseJson=resultJson;//.getJSONObject("Response");
		JSONObject jsonObject=null;
		if(responseJson.containsKey("Data"))
		{
			JSONObject dataJson=responseJson.getJSONObject("Data");
			if(dataJson.containsKey("DEPARTMENT_LIST"))
			{
				jsonObject=dataJson;
			}
		}
		if(jsonObject == null){
			System.out.println("未获取到数据");
			return info;
		}else {

			Object depObject=jsonObject.getJSONObject("DEPARTMENT_LIST").get("DEPARTMENT");
			JSONArray jsonArray =null;
			if(depObject instanceof JSONObject)
			{
				jsonArray=new JSONArray();
				jsonArray.add(((JSONObject) depObject));
			}
			else
			{
				jsonArray=jsonObject.getJSONObject("DEPARTMENT_LIST").getJSONArray("DEPARTMENT");
			}
			List<DepartMent> list = jsonArray.toJavaList(DepartMent.class);
			System.out.println(String.valueOf(new Date())+" : 处理部门同步数据："+jsonArray.toJSONString());
			for (int a = 0; a < list.size(); a++) {
				DepartMent departMent = (DepartMent) list.get(a);
				System.out.println("departMent:"+departMent);

				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("BIZ_ID",departMent.getBIZ_ID());
				paramMap.put("DEP_CODE",departMent.getDEP_CODE());
				paramMap.put("DEP_NAME",departMent.getDEP_NAME());
				System.out.println(paramMap);


				System.out.println("paramMap:"+paramMap);

				List<DepartMent> list1 = dao.query("MCJK01.query",paramMap);
				if(CollectionUtils.isEmpty(list1)) {
					dao.insert("MCJK01.insert",paramMap);
					//记录操作的时间和获取的数据
					dao.insert("MCJK01.insert1",paramMap);
				}

				dao.update("MCJK01.update",paramMap);

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
		String soapXML ="<Message id='273' code='S0007' name='OrganizationInfoQuery' appid='70'>"
				+"<REQUEST>"
				+"<DICT_TYPE>业务区划</DICT_TYPE>"
				+"<UPDATE_DATE>"+string+"</UPDATE_DATE>"
				+"</REQUEST>"
				+"</Message>";
		return soapXML;
	}

	

	/**
	 * @description：根据平台的BIZ_ID查询该科室是否存在系统，如果存在则更新，不存在则新加数据
	 * @author:kwr
	 * @parms:
	 * @time：2022-9-17 return eiInfo
	 */
	public EiInfo updateDepts(EiInfo info) throws DocumentException {

		String url = GET_PA_INFO;
		//String url="http://127.0.0.1:8080/web/demo/dept.xml"; //测试用
		String xml = getXMLss();

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);

		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		//GetMethod httpPost = new GetMethod(url);  //测试用
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

		Document doc=null;
		try
		{
			doc = XmlDataTools.parseXmlString(result);
			doc = XmlDataTools.parseXmlString(doc.getRootElement().getText()); //二次解析
		}
		catch (IOException | DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("解析错误的XML文件内容("+e.toString()+")："+result);
			return info;
		}
		JSONObject resultJson=XmlDataTools.xmlDocToJson(doc, false);
		System.out.println("解析后得到的JSON："+(resultJson==null?"NULL！！！":JSONObject.toJSONString(resultJson, true)));
		JSONObject responseJson=resultJson;//.getJSONObject("Response");
		JSONObject jsonObject=null;
		if(responseJson.containsKey("Data"))
		{
			JSONObject dataJson=responseJson.getJSONObject("Data");
			if(dataJson.containsKey("DEPARTMENT_LIST"))
			{
				jsonObject=dataJson;
			}
		}
		if(jsonObject == null){
			System.out.println("未获取到数据");
			return info;
		}else {

			Object depObject=jsonObject.getJSONObject("DEPARTMENT_LIST").get("DEPARTMENT");
			JSONArray jsonArray =null;
			if(depObject instanceof JSONObject)
			{
				jsonArray=new JSONArray();
				jsonArray.add(((JSONObject) depObject));
			}
			else
			{
				jsonArray=jsonObject.getJSONObject("DEPARTMENT_LIST").getJSONArray("DEPARTMENT");
			}
			List<DepartMent> list = jsonArray.toJavaList(DepartMent.class);
			System.out.println(String.valueOf(new Date())+" : 处理部门同步数据："+jsonArray.toJSONString());
			for (int a = 0; a < list.size(); a++) {
				DepartMent departMent = (DepartMent) list.get(a);
				System.out.println("departMent:"+departMent);

				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("BIZ_ID",departMent.getBIZ_ID());
				paramMap.put("DEP_CODE",departMent.getDEP_CODE());
				paramMap.put("DEP_NAME",departMent.getDEP_NAME());
				System.out.println(paramMap);


				System.out.println("paramMap:"+paramMap);

				List<DepartMent> list1 = dao.query("MCJK01.query",paramMap);
				if(CollectionUtils.isEmpty(list1)) {
					dao.insert("MCJK01.insert",paramMap);
					//记录操作的时间和获取的数据
					dao.insert("MCJK01.insert1",paramMap);
				}

				dao.update("MCJK01.update",paramMap);

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
	public static String getXMLss(){

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		System.out.println(date.format(formatter));
		String string = formatter.format(date);
		System.out.println(formatter.format(date));
		String soapXML ="<Message id='273' code='S0007' name='OrganizationInfoQuery' appid='70'>"
				+"<REQUEST>"
				+"<DICT_TYPE>管理区划</DICT_TYPE>"
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




	public EiInfo synManageDept(EiInfo inInfo) {
		Map<String,String> map=new HashMap<>();
		map.put("projectSchema",projectSchema);
		Date date=new Date();
		String dateTime=new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		map.put("dateTime",dateTime);
		//创建一张表
		dao.query("MCJK01.createTable",map);
		//复制数据到这张表中
		dao.insert("MCJK01.insertPreData",map);
		System.out.println("================备份成功,表名:ac_department"+dateTime+"===================");

//		String url="http://199.168.200.136?op=EsbApi";
		String url = GET_PA_INFO;
		System.out.println("================url,url"+url+"===================");
//		String url="http://localhost:8081/iplat4j_maven_web_war_exploded/xml/20230511G.xml";//测试用
		String xml = getXMLssly();

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);
		System.out.println("================pMap成功,pMap"+pMap+"===================");
		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);

//		GetMethod httpPost = new GetMethod(url);  //测试用
		httpPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		String result = null;
		for (Map.Entry<String, String> entry : pMap.entrySet()) {
			httpPost.addParameter(entry.getKey(), entry.getValue());
		}
		try {
			System.out.println("================httpPost成功,httpPost"+httpPost+"===================");
			client.executeMethod(httpPost);
			result = new String(httpPost.getResponseBody(), StandardCharsets.UTF_8);
			System.out.println("HttpClient中的result：" + new String(httpPost.getResponseBody(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
		}



		Document doc=null;
		try
		{
			doc = XmlDataTools.parseXmlString(result);
			doc = XmlDataTools.parseXmlString(doc.getRootElement().getText()); //二次解析
		}
		catch (IOException | DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("解析错误的XML文件内容("+e.toString()+")："+result);
			return inInfo;
		}
		JSONObject resultJson=XmlDataTools.xmlDocToJson(doc, false);
		System.out.println("解析后得到的JSON："+(resultJson==null?"NULL！！！":JSONObject.toJSONString(resultJson, true)));
		JSONObject responseJson=resultJson;//.getJSONObject("Response");
		JSONObject jsonObject=null;
		if(responseJson.containsKey("Data"))
		{
			JSONObject dataJson=responseJson.getJSONObject("Data");
			if(dataJson.containsKey("DEPARTMENT_LIST"))
			{
				jsonObject=dataJson;
			}
		}
		if(jsonObject == null){
			System.out.println("未获取到数据");
			return inInfo;
		}else {

			Object depObject=jsonObject.getJSONObject("DEPARTMENT_LIST").get("DEPARTMENT");
			JSONArray jsonArray =null;
			if(depObject instanceof JSONObject)
			{
				jsonArray=new JSONArray();
				jsonArray.add(((JSONObject) depObject));
			}
			else
			{
				jsonArray=jsonObject.getJSONObject("DEPARTMENT_LIST").getJSONArray("DEPARTMENT");
			}
//			List<HashMap> list = jsonArray.toJavaList(HashMap.class);
			List<Map<String, String>> list =JSON.parseObject(jsonArray.toJSONString(),new TypeReference<List<Map<String, String>>>() {
			});
			System.out.println(String.valueOf(date)+" : 处理部门同步数据："+jsonArray.toJSONString());
			String loginName=UserSession.getLoginName();
			String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			for (int a = 0; a < list.size(); a++) {
				Map<String, String> paramMap =  list.get(a);
				if("".equals(paramMap.get("DEP_CODE"))){
					continue;
				}
				System.out.println("departMent:"+paramMap);

				paramMap.put("projectSchema",projectSchema);
				int flag=nowTime.compareTo(paramMap.get("ACT_TO").replace("T"," "));
				if(flag>0){
					//无效
					paramMap.put("status","0");
				}else {
					//有效
					paramMap.put("status","1");
				}
				//如果没有该科室编码的数据，则进行插入
				List<Map<String, String>> list1 = dao.query("MCJK01.queryByDeptCode",paramMap);
				if(CollectionUtils.isEmpty(list1)) {
					paramMap.put("recCreater",loginName);
					paramMap.put("recCreateTime",nowTime);
					dao.insert("MCJK01.insertDept",paramMap);
					continue;
				}
				//如果收到的数据与系统中的数据不一致，则进行更新，如果一致，则什么都不用做
				List<Map<String, String>> list2=dao.query("MCJK01.queryDeptAllInfo",paramMap);
				if(list2.isEmpty()){
					paramMap.put("recRevisor",loginName);
					paramMap.put("recReviseTime",nowTime);
					dao.update("MCJK01.updateByDeptCode",paramMap);
				}
			}
		}

		System.out.println("jsonObject = " + jsonObject);
		inInfo.setMsg("同步成功");
		return  inInfo;
	}

	/**
	 * 组合参数
	 * @param
	 * @return
	 */
	public static String getXMLssly(){

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		System.out.println(date.format(formatter));
		String string = formatter.format(date);
		System.out.println(formatter.format(date));
		String soapXML ="<Message id='273' code='S0007' name='OrganizationInfoQuery' appid='70'>"
				+"<REQUEST>"
				+"<DICT_TYPE>管理区划</DICT_TYPE>"
				+"<UPDATE_DATE></UPDATE_DATE>"
				+"</REQUEST>"
				+"</Message>";
		return soapXML;
	}




	public EiInfo synBusinessDept(EiInfo inInfo) {
		Map<String,String> map=new HashMap<>();
		map.put("projectSchema",projectSchema);
		Date date=new Date();
		String dateTime=new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		map.put("dateTime",dateTime);
		//创建一张表
		dao.query("MCJK01.createTable",map);
		//复制数据到这张表中
		dao.insert("MCJK01.insertPreData",map);
		System.out.println("================备份成功,表名:ac_department"+dateTime+"===================");

//		String url="http://199.168.200.136?op=EsbApi";
		String url = GET_PA_INFO;
		System.out.println("================url,url"+url+"===================");
//		String url="http://localhost:8081/iplat4j_maven_web_war_exploded/xml/20230511.xml";//测试用
		String xml = getXMLsly();

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);
		System.out.println("================pMap成功,pMap"+pMap+"===================");
		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		System.out.println("================httpPost成功,httpPost"+httpPost+"===================");
//		GetMethod httpPost = new GetMethod(url);  //测试用
		httpPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		String result = null;
		for (Map.Entry<String, String> entry : pMap.entrySet()) {
			httpPost.addParameter(entry.getKey(), entry.getValue());
		}
		try {
			System.out.println("================httpPost成功,httpPost"+httpPost+"===================");
			client.executeMethod(httpPost);
			result = new String(httpPost.getResponseBody(), StandardCharsets.UTF_8);
			System.out.println("HttpClient中的result：" + new String(httpPost.getResponseBody(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
		}



		Document doc=null;
		try
		{
			doc = XmlDataTools.parseXmlString(result);
			doc = XmlDataTools.parseXmlString(doc.getRootElement().getText()); //二次解析
		}
		catch (IOException | DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("解析错误的XML文件内容("+e.toString()+")："+result);
			return inInfo;
		}
		JSONObject resultJson=XmlDataTools.xmlDocToJson(doc, false);
		System.out.println("解析后得到的JSON："+(resultJson==null?"NULL！！！":JSONObject.toJSONString(resultJson, true)));
		JSONObject responseJson=resultJson;//.getJSONObject("Response");
		JSONObject jsonObject=null;
		if(responseJson.containsKey("Data"))
		{
			JSONObject dataJson=responseJson.getJSONObject("Data");
			if(dataJson.containsKey("DEPARTMENT_LIST"))
			{
				jsonObject=dataJson;
			}
		}
		if(jsonObject == null){
			System.out.println("未获取到数据");
			return inInfo;
		}else {

			Object depObject=jsonObject.getJSONObject("DEPARTMENT_LIST").get("DEPARTMENT");
			JSONArray jsonArray =null;
			if(depObject instanceof JSONObject)
			{
				jsonArray=new JSONArray();
				jsonArray.add(((JSONObject) depObject));
			}
			else
			{
				jsonArray=jsonObject.getJSONObject("DEPARTMENT_LIST").getJSONArray("DEPARTMENT");
			}
//			List<HashMap> list = jsonArray.toJavaList(HashMap.class);
			List<Map<String, String>> list =JSON.parseObject(jsonArray.toJSONString(),new TypeReference<List<Map<String, String>>>() {
			});
			System.out.println(String.valueOf(date)+" : 处理部门同步数据："+jsonArray.toJSONString());
			String loginName=UserSession.getLoginName();
			String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			for (int a = 0; a < list.size(); a++) {
				Map<String, String> paramMap =  list.get(a);
				if("".equals(paramMap.get("DEP_CODE"))){
					continue;
				}

				if("6".equals(paramMap.get("DEP_ID"))){
					System.out.println("6");
				}
				System.out.println("departMent:"+paramMap);

				paramMap.put("projectSchema",projectSchema);
				int flag=nowTime.compareTo(paramMap.get("ACT_TO").replace("T"," "));
				if(flag>0){
					//无效
					paramMap.put("status","0");
				}else {
					//有效
					paramMap.put("status","1");
				}
				//如果没有该科室编码的数据，则进行插入
				List<Map<String, String>> list1 = dao.query("MCJK01.queryByDeptCode",paramMap);
				if(CollectionUtils.isEmpty(list1)) {
					paramMap.put("recCreater",loginName);
					paramMap.put("recCreateTime",nowTime);
					dao.insert("MCJK01.insertDept",paramMap);
					continue;
				}
				//如果收到的数据与系统中的数据不一致，则进行更新，如果一致，则什么都不用做
				List<Map<String, String>> list2=dao.query("MCJK01.queryDeptAllInfo",paramMap);
				if(list2.isEmpty()){
					paramMap.put("recRevisor",loginName);
					paramMap.put("recReviseTime",nowTime);
					dao.update("MCJK01.updateByDeptCode",paramMap);
				}
			}
		}

		System.out.println("jsonObject = " + jsonObject);
		inInfo.setMsg("同步成功");
		return  inInfo;
	}

	/**
	 * 组合参数
	 * @param
	 * @return
	 */
	public static String getXMLsly(){

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		System.out.println(date.format(formatter));
		String string = "";
		System.out.println(formatter.format(date));
		String soapXML ="<Message id='273' code='S0007' name='OrganizationInfoQuery' appid='70'>"
				+"<REQUEST>"
				+"<DICT_TYPE>业务区划</DICT_TYPE>"
				+"<UPDATE_DATE></UPDATE_DATE>"
				+"</REQUEST>"
				+"</Message>";
		return soapXML;
	}

}
