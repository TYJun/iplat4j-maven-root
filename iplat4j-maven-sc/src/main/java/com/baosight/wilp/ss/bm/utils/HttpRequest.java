package com.baosight.wilp.ss.bm.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Todo(这里用一句话描述这个方法的作用)
 * 
 * @Title: HttpRequest
 * @ClassName: HttpRequest.java
 * @Package：com.baosight.wilp.ss.bm.utils
 * @author: xiajunqing
 * @date: 2021/11/20 13:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class HttpRequest {
	

	@SuppressWarnings("deprecation")
	public static JSONObject sendApacHttpPost(String url,String vParams){
		HttpClient      pClient =new HttpClient();
		PostMethod 		postMethod = new PostMethod(url); 
		postMethod.setRequestBody(vParams);
		try {
			pClient.executeMethod(postMethod);
			byte[]          bpBuffer =new byte[4096*1024];
			bpBuffer = postMethod.getResponseBody();
			System.out.println(new String(bpBuffer,"utf-8"));
			
			JSONObject pReturnContent=new JSONObject(new String(bpBuffer,"utf-8"));
			return pReturnContent;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送Http请求
	 * @param url	请求地址
	 * @param vParams	参数
	 * @return
	 */
	public static JSONObject sendHttpPost(String url,String vParams){
        URL realUrl = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        //发送请求
        try{
            realUrl = new URL(url);
            //获取连接
            conn = (HttpURLConnection)realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            //添加发送参数
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(vParams);
            pw.flush();
            pw.close();
            //发送请求同时获取返回的输入流
            in = conn.getInputStream();//获得返回的输入流
        }catch(Exception e){
            e.printStackTrace();
        }
        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        String result = buffer.toString();//返回低字符串
        JSONObject josonResult =null;
        try{
        	//转码格式化
            josonResult = new JSONObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return josonResult;
    }
	
	public static JSONObject sendHttpPost2(String url,String vParams){
        URL realUrl = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        //发送请求
        try{
            realUrl = new URL(url);
            conn = (HttpURLConnection)realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(vParams);
            pw.flush();
            pw.close();
            in = conn.getInputStream();//获得返回的输入流
        }catch(Exception e){
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = buffer.toString();//返回低字符串
      //result=result.trim().substring(1,result.trim().length()-1).replaceAll("\\\\\"", "\"");
        JSONObject josonResult =null;
        try{
            josonResult = new JSONObject(result.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return josonResult;
    }
}
