package com.baosight.wilp.common.web;

import com.baosight.wilp.basic.db.DbTools;
import com.baosight.wilp.basic.db.SqlExecutor;
import com.baosight.wilp.basic.service.GlobalFilter;
import com.baosight.wilp.basic.struct.ClassTools;
import com.baosight.wilp.utils.SM2Utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.baosight.wilp.utils.SM2Utils.createECKeyPair;


//import static ;

/**
 *@description：单点登录
 *@author：lijunjun
 *@parms：
 *@Data
 *@Time：2022/9/20 17:25
 */
@RestController
public class SsoDispatchers {
//    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    Logger logger = LoggerFactory.getLogger(SsoDispatchers.class);
    static final String appId = "70";
    @RequestMapping("/ssoLogins")
    @CrossOrigin
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        HttpSession session = new MockHttpSession();
        //1.获取参数
        String token = request.getParameter("token");
//        String state1 = request.getParameter("state");
//        String state = URLEncoder.encode(state1);
//        session.setAttribute("state","CPDJ01");
        System.out.println("token:"+token);
       /* 2022-10-18 取消该方法段，改用专用方法段进行操作
        //2.使用httpClient  Get请求接口获取用用户信息
        HttpGet get = new HttpGet("http://199.168.200.168/jia/SSO_Api/sso.asmx/checktoken?token="+token);
//        get.addHeader("Content-Type", "application/json;charset=utf-8");
        HttpClient client = HttpClientBuilder.create().build();
        //发送请求并接收响应结果
        HttpResponse res= client.execute(get);
        System.out.println("res人员信息数据："+res);
        //获取响应信息实体
        String str = EntityUtils.toString(res.getEntity());
//        String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<string xmlns=\"http://tempuri.org/\">\n" +
//                "{\n" +
//                "  \"result\": {\n" +
//                "    \"code\": \"0\",\n" +
//                "    \"msg\": \"token失效\"\n" +
//                "  },\n" +
//                "  \"data\": \"\"\n" +
//                "}</string>";
        //json类型数据
        Document doc = DocumentHelper.parseText(str.toString());
        JSONObject jsonObject = new JSONObject();
        dom4j2Json(doc.getRootElement(), jsonObject);
        Object ob1 = jsonObject.get("string");

        //数据解析
        JSONObject ob = JSONObject.parseObject(ob1.toString());
        System.out.println("ob信息"+ob);
        JSONObject result = ob.getJSONObject("result");
        //获取结果代码 1成功，其他失败
        String code = result.getString("code");
        //失败
        if(!code.equals("1")){
            System.out.println(result.getString("msg"));
            return;
        }
        //获取成功
        String data = ob.getString("data");
//        String  data = "0404F1976A95A7E1059DFA07020612EF52E83E748DCAB8689D0F6F0C17AEF36E7472B16851DDD1D73E86E8A693B4926A4042322EECA9FFE5A9E2B31D8DF8D796FA1736553CE1209FDCEF285C6C7611D1A5D5D010DAE30400368B2B424C22F4659DB8050E3FBA872611A2294D35587CCB83171931FD34B24D898938BBFA97ACC554B0A68233DCBDD38718D865E2CE06C70A2436825100F8876D44F1016F4FA2CC2EE987BD8A5EFD344C6E46784EEED8B292AFD74F8E0B7B4AA3EBB6DA71F91F90E6A314C60F45C0204E57176B1F62C426BAFCC3D2CCC681DD127C7B221AD2760752F125B850DB8710D38E8011755D59A3BA1C30BD30333F66C8BC2B95C477FA8728AC22D42B3C5D7572106D2251B89FDE9A2EFDF0B65CDF77E04A6FDCC4F57791AA89";
        String encryptData = this.deCode(data);
        //对用户信息进行解密,获取的格式如下

        / **
         {
         "userinfo":{
         "openid": "用户的OpenID",
         "password": "CA密码。当目标系统要求CA登录时，不可为空",
         "device": "当前设备IP",
         "target": "目标应用唯一标识"
         }
         "sign": { "signdata": "userinfo节点的签名值，用MD5摘要算法获得" }
         }
        * /
        JSONObject outOb = JSONObject.parseObject(encryptData);  */
        try
        {
            Thread.sleep(200L);
        }
        catch(Throwable error)
        {
            error.printStackTrace();
        }
        int errorAmount=0;
        do
        {
        	
        	String[]   pContentArray=new String[] {"",""};
        	JSONObject outOb=this.getUserInfoData(token,pContentArray,errorAmount+1);
        	if(outOb==null)
        	{
        		errorAmount++;
        		try
                {
                    Thread.sleep(500L); //暂停0.5秒
                }
                catch(Throwable error)
                {
                    error.printStackTrace();
                }
        		logger.error("通过TOKEN:"+token+"偿试出错，错误"+String.valueOf(errorAmount)+"次。\n原始内容：\n"+pContentArray[0]+"\n解码后内容：\n"+pContentArray[1]);
        		System.out.println("通过TOKEN:"+token+"偿试出错，错误"+String.valueOf(errorAmount)+"次。\n原始内容：\n"+pContentArray[0]+"\n解码后内容：\n"+pContentArray[1]);
        		continue;
        	}
        	logger.debug("通过TOKEN:"+token+",获取到用户信息："+outOb.toString(1));
        	System.out.println("通过TOKEN:"+token+",获取到用户信息："+outOb.toString(1));
	        JSONObject userInfo = outOb.getJSONObject("userinfo");
	        String openId = userInfo.getString("openid");
	//        int count = dao.count("SSO.isExist",openId);
	//        if(count == 0){
	//            throw new Exception("用户信息不存在");
	//        }
	        //3.构建参数
	        String pathParam = "p_username="+ openId+"&p_password="+token+"&p_authen=CasPlatAuth";
//            state = "state="+ state;
	        //4.获取项目路径
	        String requestURL = request.getRequestURL().toString();
	        String servletPath = request.getServletPath();
//	        String url = requestURL.replace(servletPath, "") + "/index.jsp?" + pathParam;
//            String url = "http://172.16.200.20:8081/mzsrmyy" + "/indexs.jsp?" + pathParam;
            String url = "http://172.16.200.20:8081/mzsrmyy" + "/indexs.jsp?" + pathParam ;
	        //5.重定向
	        response.sendRedirect(url);
	        updateSuccessLog(token,errorAmount+1);
	        return;
        }while(errorAmount<5);
        response.setStatus(403);
    }
//    private Map<String, String> httpPost(String param, String serviceUrl) throws Exception {
//        //构建httpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(serviceUrl);
//        // 设置请求的header
//        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//        //参数处理
//        StringEntity entity = new StringEntity(param, "utf-8");
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        //执行请求
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        //处理返回结果
//        String body = EntityUtils.toString(response.getEntity(), "utf-8");
//        logger.info("findSSOInfoByToken返回结果："+body);
//        Map<String, String> rMap = JSON.parseObject(body, Map.class);
//        logger.info("findSSOInfoByToken返回结果转换成的Map："+JSON.toJSONString(rMap));
////        throw new Exception("findSSOInfoByToken返回结果："+body + "findSSOInfoByToken返回结果转换成的Map："+JSON.toJSONString(rMap));
//        if(rMap.get("errCode").equals("0") && StringUtils.isNotBlank(rMap.get("userCode"))){
//            return rMap;
//        } else {
//            return null;
//        }
//    }
    
    /**
     * 将登录日志插入数据表
     * @param tToken         请求的Token
     * @param iTryIndex      偿试的次数
     */
    private void insertLog(String tToken,int iTryIndex)
    {
        try
        {
            DbTools.executeUpdate(
                    SqlExecutor.buildInsert("system_sso_log", 
                                            new JSONObject().put("token_id",tToken)
                                                            .put("try_index",iTryIndex)
                                                            .put("request_time",new Date())
                                                            .put("token_request_ip",GlobalFilter.getCurrentHttpRequest().getRemoteAddr())
                                                            .put("response_success",0)));
        }
        catch (SQLException | JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 更新日志    
     * @param tToken              令牌
     * @param iTryIndex           偿试次数
     * @param tResponseContent    HTTP返回内容
     * @param tDataContent        JSON中抽取的加密内容
     * @param iProcessFlag        处理结果码
     */
    private void updateContentLog(String tToken,int iTryIndex,String tResponseContent,String tDataContent,int iProcessFlag)
    {
        try
        {
            DbTools.executeUpdate(SqlExecutor.buildUpdate("system_sso_log",
                                                          new JSONObject().put("response_content",tResponseContent)
                                                                          .put("source_code_data",tDataContent)
                                                                          .put("process_success_flag",iProcessFlag)
                                                                          .put("response_success", 1),
                                                          new JSONObject().put("token_id",tToken)
                                                                          .put("try_index",iTryIndex)));
        }
        catch (SQLException | JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 登记异常结果
     * @param tToken       令牌
     * @param iTryIndex    偿试次数
     * @param pError       异常
     */
    private void updateErrorLog(String tToken,int iTryIndex,Throwable pError)
    {
        String tErrorContent=ClassTools.getThrowableStackContent(pError);
        try
        {
            DbTools.executeUpdate(SqlExecutor.buildUpdate("system_sso_log", 
                                                         new JSONObject().put("process_error_detial",tErrorContent)
                                                                         .put("process_success_flag",-2),
                                                          new JSONObject().put("token_id",tToken)
                                                                          .put("try_index",iTryIndex)));
        }
        catch (SQLException | JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 更新日志，说明跳转成功
     * @param tToken
     * @param iTryIndex
     */
    private void updateSuccessLog(String tToken,int iTryIndex)
    {
        try
        {
            DbTools.executeUpdate(SqlExecutor.buildUpdate("system_sso_log",
                                                          new JSONObject().put("process_success_flag",1),
                                                          new JSONObject().put("token_id",tToken)
                                                                          .put("try_index",iTryIndex)));
        }
        catch (SQLException | JSONException e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    /**
     * 使用TOKEN，对单点登记进行调用，并返回用户信息数据
     * @param tToken             验证用的TOKEN
     * @param pOutContentArray   返回获取到的内容情况
     * @param iTryAmount         第几次偿试
     * @return 如果验证通过，返回对象。如果验证失败，则返回NULL。
     */
    private JSONObject getUserInfoData(String tToken,String[] pOutContentArray,int iTryAmount) 
    {
        this.insertLog(tToken, iTryAmount); //登记开始处理操作 
    	try
    	{
//    		HttpGet      pGet = new HttpGet("http://127.0.0.1:8080/sso.txt?token="+tToken);
	    	HttpGet      pGet = new HttpGet("http://199.168.200.168/jia/SSO_Api/sso.asmx/checktoken?token="+tToken);
    		HttpClient   pClient = HttpClientBuilder.create()
	   	 			                                .setDefaultSocketConfig(SocketConfig.custom()
	   	 			                                									.setTcpNoDelay(true)
	   	 			                                									.setSoLinger(1)
	   	 			                                									.setSoReuseAddress(true)
	   	 			                                									.setSoKeepAlive(false)
	   	 			                                								    .setSoTimeout(3*1000)
	   	 			                                								    .build())
	   	 			                                .setConnectionTimeToLive(3, TimeUnit.SECONDS)
	   	 			                                .build();             		   //HTTP客户端
	   	 	HttpResponse pResponse= pClient.execute(pGet);                         //GET方法调用，返回的RESPONSE
	   	 	String       tSourceContent = EntityUtils.toString(pResponse.getEntity());   //返回的内容  getResponseContent(pResponse)
	   	 	if(pOutContentArray!=null&&pOutContentArray.length>0)
	   	 	{
	   	 	    pOutContentArray[0]=tSourceContent;
	   	 	}
	   	 	//去除前后的<String....  </string>
	   	 	int iBegin=tSourceContent.indexOf("{");
	   	 	int iEnd=tSourceContent.lastIndexOf("}");
	   	 	String tContent=tSourceContent.substring(iBegin,iEnd+1);
	   	 	//处理获取JSON
	   	    JSONObject   pResultJson = new JSONObject(tContent);           //格式化后的JSON对象
	   	    //判断返回内容是否异常!
	   	    if(!pResultJson.has("result")||
	   	       !pResultJson.getJSONObject("result").has("code")||
	   	       pResultJson.getJSONObject("result").get("code")==null||
	   	       !pResultJson.getJSONObject("result").getString("code").equals("1")||
	   	       !pResultJson.has("data")|
	   	       pResultJson.get("data")==null)
	   	    {
	   	        this.updateContentLog(tToken, iTryAmount, tSourceContent, "", -1);//更新数据库，设置异常,状态-1
	   	    	logger.error("单点登录调用返回异常,返回内容："+pResultJson.toString(1));
	   	    	System.err.println("单点登录调用返回异常,返回内容："+pResultJson.toString(1));
	   	    	return null;
	   	    }
	   	    String tData = pResultJson.getString("data");
	   	    //登记日志
	   	    this.updateContentLog(tToken, iTryAmount, tSourceContent,tData,0);//更新数据库，登记内容
	   	    //解码
	   	    String tDeCodeContent=this.deCodeCSharp(tData);//改用C#处理操作//this.deCode(tData);
    	   	if(pOutContentArray!=null&&pOutContentArray.length>1)
            {
    	   	    pOutContentArray[1]=tContent; //返回解码的内容
            }
	   	    JSONObject pReturnJson = new JSONObject(tDeCodeContent);
	   	    if(!pReturnJson.has("userinfo"))
	   	    {
	   	    	logger.error("单点登录调用返回异常,返回中有少userinfo等关键内容："+pReturnJson.toString(1));
	   	    	System.err.println("单点登录调用返回异常,返回中有少userinfo等关键内容："+pReturnJson.toString(1));
	   	    	return null;
	   	    }
	   	    return pReturnJson;
    	}
    	catch(Throwable error)
    	{
    	    this.updateErrorLog(tToken, iTryAmount, error); //登记异常信息
    	}
    	return null;
    }
    
    /**
     * 通过C#去解码
     * @param tEncodeData 已经编码的数据
     * @return
     * @throws IOException 
     * @throws InterruptedException 
     */
    public String deCodeCSharp(String tEncodeData) throws IOException, InterruptedException
    {
        String         tReturn=null;
        //分割参数
        List<String>   pWriteList=new ArrayList<String>();
        while(tEncodeData.length()>100)
        {
            String tContent=tEncodeData.substring(0,100);
            pWriteList.add(tContent);
            tEncodeData=tEncodeData.substring(100);
        }
        pWriteList.add(tEncodeData);
        //调用命令
        ProcessBuilder pBuilder=new ProcessBuilder();
        pBuilder.command("C:\\Bonawise\\Sm2TestConsole.exe",         //Exe文件目录，根据实际情况调整
                         "-input");
        //启动进程d
        Process               pProcessor=pBuilder.start();                 //进程
        byte[]                pBuffer=new byte[1024];                      //读取InputStream缓存数组
        ByteArrayOutputStream pByteStream=new ByteArrayOutputStream();     //内部的字节缓存
        InputStream           pInStream=null;                              //进程的输入流
        OutputStream          pOutStream=pProcessor.getOutputStream();     //进程的输出流
        try
        {
            //第一步，写入
            for(int iCyc=0;iCyc<pWriteList.size();iCyc++)
            {
                pOutStream.write(pWriteList.get(iCyc).getBytes("gb2312"));
                pOutStream.flush();
                if(iCyc>0)
                {
                    pOutStream.write(new byte[] {13,10});
                    pOutStream.flush();
                }
            }
            pOutStream.write(new byte[] {13,10,13,10,13,10});
            pOutStream.flush();
            if(!pProcessor.waitFor(3, TimeUnit.SECONDS))
            {
                return tReturn;
            }
            pInStream=pProcessor.getInputStream();
            //读取结果
            do
            {
                int iLength=pInStream.read(pBuffer);
                if(iLength<=0)
                {
                    break;
                }
                pByteStream.write(pBuffer, 0, iLength);
            }while(true);
            //读取结果
            String tContent=new String(pByteStream.toByteArray(),"gb2312");
            int    iPosition=tContent.indexOf("success");
            if(iPosition>=0)
            {
                tReturn=tContent.substring(iPosition+7);
            }   
        }
        finally
        {
            pByteStream.close();
            if(pInStream!=null)
            {
                pInStream.close();
            }
            pOutStream.close();
            pProcessor.destroy();
        }
        return tReturn;
    }

    public String deCode(String encryptData){
        String privateKey = "1724C038D924F727B4E6B625C891E0F8550905D37156CD61D2BC02DCB068236A";
        KeyPair keyPair = createECKeyPair();
        System.out.println("encryptData参数是:"+encryptData);
        /**
         * 私钥解密
         */
        //将十六进制私钥串转换为 BCECPrivateKey 私钥对象
//        String encryptData = "0404F1976A95A7E1059DFA07020612EF52E83E748DCAB8689D0F6F0C17AEF36E7472B16851DDD1D73E86E8A693B4926A4042322EECA9FFE5A9E2B31D8DF8D796FA1736553CE1209FDCEF285C6C7611D1A5D5D010DAE30400368B2B424C22F4659DB8050E3FBA872611A2294D35587CCB83171931FD34B24D898938BBFA97ACC554B0A68233DCBDD38718D865E2CE06C70A2436825100F8876D44F1016F4FA2CC2EE987BD8A5EFD344C6E46784EEED8B292AFD74F8E0B7B4AA3EBB6DA71F91F90E6A314C60F45C0204E57176B1F62C426BAFCC3D2CCC681DD127C7B221AD2760752F125B850DB8710D38E8011755D59A3BA1C30BD30333F66C8BC2B95C477FA8728AC22D42B3C5D7572106D2251B89FDE9A2EFDF0B65CDF77E04A6FDCC4F57791AA89";
        //将十六进制私钥串转换为 BCECPrivateKey 私钥对象
        encryptData = SM2Utils.decrypt(privateKey, encryptData);
        System.out.println("---->解密结果：" + encryptData);
        return encryptData;
    }
    
    /**
     * 获取返回的内容
     * @param pResponse   返回的内容
     * @return  内容
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    private static String getResponseContent(HttpResponse pResponse) throws UnsupportedOperationException, IOException
    {
        String      tReturn="";
        InputStream pInStream=pResponse.getEntity().getContent();
        ByteArrayOutputStream pOutStream=new ByteArrayOutputStream();
        byte[] pBuffer=new byte[4096];
        String tCode="utf-8";
        if(pResponse.getEntity().getContentEncoding()!=null)
        {
            String tValue=pResponse.getEntity().getContentEncoding().getValue();
            if(tValue!=null&&tValue.trim().equals("")!=true)
            {
                tCode=tValue;
            }
        }
        try
        {
            do
            {
                int iRead=pInStream.read(pBuffer);
                if(iRead<=0)
                {
                    break;
                }
                pOutStream.write(pBuffer,0,iRead);
            }while(true);
            tReturn=new String(pOutStream.toByteArray(),tCode);
        }
        finally
        {
            pOutStream.close();
            pInStream.close();
        }
        return tReturn;
    }

}
