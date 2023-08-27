package com.baosight.wilp.mc.jk.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mc.jk.domain.Personnel;
import com.baosight.wilp.mc.jk.utils.StringUtil;
import com.baosight.wilp.mc.jk.utils.XmlDataTools;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.dom4j.*;

import java.text.SimpleDateFormat;


/**
 * 
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: ServicePJJK01.java
 * @ClassName: ServicePJJK01
 * @Package：com.baosight.wilp.pj.jk.service
 * @author: zhaow
 * @date: 2021年12月23日 下午8:39:26
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceMCJK02 extends ServiceBase{
	public static final  String GET_PA_INFO = PlatApplicationContext.getProperty("ac_pa_info");
	/**
	 *
	 */
	public EiInfo personnel(EiInfo inInfo) {
		List<EiInfo> list = new ArrayList<EiInfo>();
		list.add(inInfo);
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr();
//		DepartMent departMent = (DepartMent)attr.get("obj");
		List lll= (List) attr.get("obj");
		for (int a = 0; a < lll.size(); a++) {
			Personnel personnel = (Personnel) lll.get(a);

			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STAFF_CODE",personnel.getSTAFF_CODE());
			paramMap.put("STAFF_NAME",personnel.getSTAFF_NAME());
			paramMap.put("ID_NUMBER",personnel.getID_NUMBER());
			paramMap.put("GENDER_CODE",personnel.getGENDER_CODE());
			paramMap.put("TELEPHONE",personnel.getTELEPHONE());
			paramMap.put("DEFAULT_ADMIN_DEP_CODE",personnel.getDEFAULT_ADMIN_DEP_CODE());
			paramMap.put("DEFAULT_ADMIN_DEP_NAME",personnel.getDEFAULT_ADMIN_DEP_NAME());
			paramMap.put("DEFAULT_BIZ_ID",personnel.getDEFAULT_BIZ_ID());
			paramMap.put("RCRT_NAME",personnel.getRCRT_NAME());
			paramMap.put("RCRT_CODE",personnel.getRCRT_CODE());
			paramMap.put("WEIXIN_WORK_ACCOUNT",personnel.getWEIXIN_WORK_ACCOUNT());



			System.out.println("-----------------------------");
			System.out.println(paramMap);

			List<Personnel> list1 = dao.query("MCJK02.query",personnel);
			if(CollectionUtils.isEmpty(list1)) {
				dao.insert("MCJK02.insert",paramMap);
				//记录操作的时间和获取的数据
				dao.insert("MCJK02.insert1",paramMap);
			}
//
			dao.update("MCJK02.update",paramMap);
			List<Personnel> list2 = dao.query("MCJK02.query2",paramMap);


			paramMap.put("dept_id",list2.get(0).getId());

			dao.update("MCJK02.update2",paramMap);
			//更新退休或者离职的员工的状态变成停用
			dao.update("MCJK02.update3",paramMap);
		}
		inInfo.setStatus(0);
		inInfo.setMsg("更新成功");
		return inInfo;
	}

	/**
	 * @description：根据工号查询该人员是否存在系统，如果存在则更新，不存在则新加数据
	 * @author:kwr
	 * @parms:
	 * @time：2022-9-17 return eiInfo
	 */
	public EiInfo updatePop(EiInfo info) throws DocumentException {

		String url = "http://199.168.200.136/esbsvc.asmx/EsbApi";
		//String url="http://127.0.0.1:8080/web/demo/man.xml"; //测试用
		String xml = getXMLs();

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);

		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		//GetMethod httpPost = new GetMethod(url); //测试用
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
			doc = XmlDataTools.parseXmlString(doc.getRootElement().getText());  //二次解析
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
			if(dataJson.containsKey("STAFF_LIST"))
			{
				jsonObject=dataJson;
			}
		}
		if(jsonObject == null){
			System.out.println("未获取到数据");
			return info;
		}else {
			Object emloyObject=jsonObject.getJSONObject("STAFF_LIST").get("STAFF_INFO");
			JSONArray jsonArray =null;
			if(emloyObject instanceof JSONObject)
			{
				jsonArray=new JSONArray();
				jsonArray.add(emloyObject);
			}
			else
			{
			     jsonArray = jsonObject.getJSONObject("STAFF_LIST").getJSONArray("STAFF_INFO");//jsonObject.getJSONArray("STAFF_INFO");
			}
			List<Personnel> list = jsonArray.toJavaList(Personnel.class);

			for (int a = 0; a < list.size(); a++) {
				Personnel personnelInfo = (Personnel) list.get(a);


				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("STAFF_CODE",personnelInfo.getSTAFF_CODE());
				paramMap.put("STAFF_NAME",personnelInfo.getSTAFF_NAME());
				paramMap.put("ID_NUMBER",personnelInfo.getID_NUMBER());
				paramMap.put("GENDER_CODE",personnelInfo.getGENDER_CODE());
//				paramMap.put("TELEPHONE",personnelInfo.getTELEPHONE());
				paramMap.put("DEFAULT_ADMIN_DEP_CODE",personnelInfo.getDEFAULT_ADMIN_DEP_CODE());
				paramMap.put("DEFAULT_ADMIN_DEP_NAME",personnelInfo.getDEFAULT_ADMIN_DEP_NAME());
				paramMap.put("DEFAULT_BIZ_ID",personnelInfo.getDEFAULT_BIZ_ID());
				paramMap.put("RCRT_NAME",personnelInfo.getRCRT_NAME());
				paramMap.put("RCRT_CODE",personnelInfo.getRCRT_CODE());
				paramMap.put("WEIXIN_WORK_ACCOUNT",personnelInfo.getWEIXIN_WORK_ACCOUNT());
				paramMap.put("OCCUPATION_NAME",personnelInfo.getOCCUPATION_NAME());

				paramMap.put("ACT_FROM",personnelInfo.getACT_FROM());
				paramMap.put("ACT_TO",personnelInfo.getACT_TO());
				paramMap.put("STATUS_CODE",personnelInfo.getSTATUS_CODE());
				paramMap.put("STATUS_NAME",personnelInfo.getSTATUS_NAME());

				System.out.println("paramMap:"+paramMap);

				List<Personnel> list1 = dao.query("MCJK02.query",personnelInfo);
				if(CollectionUtils.isEmpty(list1)) {
					dao.insert("MCJK02.insert",paramMap);
					//记录操作的时间和获取的数据
					dao.insert("MCJK02.insert1",paramMap);
				}

				dao.update("MCJK02.update",paramMap);
				List<Personnel> list2 = dao.query("MCJK02.query2",paramMap);
				if(list2.isEmpty()){
					paramMap.put("dept_id","waitinsert");
				}else {
					paramMap.put("dept_id",list2.get(0).getId());
				}

				dao.update("MCJK02.update2",paramMap);

				dao.update("MCJK02.update3",paramMap);

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
