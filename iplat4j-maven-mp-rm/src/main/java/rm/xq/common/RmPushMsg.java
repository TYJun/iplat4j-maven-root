package rm.xq.common;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.rm.xq.config.WxConstantr;
import com.baosight.wilp.rm.xq.util.RestUtilsr;
import com.baosight.wilp.rm.xq.util.URIUtil;
import com.baosight.wilp.rm.xq.util.XmlDataTools;
import groovy.util.logging.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.URI;
import java.net.URL;
import java.util.*;

@Slf4j
public class RmPushMsg {
    private static final String url = "http://199.168.200.95:9080/wechat/enterprise/api.asmx/SendMsg";

//    public static String pushWxMsg(String userId,String name,String node,String time,String nid) throws Exception {
//        String getSeeUserId = url;
//        List<String> user_list = Arrays.asList(userId);
//        String H5_URL = WxConstantr.H5_REQUEST_URL + "?serial="+WxConstantr.serial+"&nid="+nid;
//        String setOf = "发起人："+name+"" +"，"+"当前节点："+node+"，"+"最近处理时间："+time+"，"+"<div class=\\\"highlight\\\">"+"驳回原因："+"</div>";
//        String urlr = String.format(WxConstantr.WX_H5_OAUTH2_PRIVATE_URL, WxConstantr.WX_APP_ID, URIUtil.encodeURIComponent(URIUtil.encodeURIComponent(H5_URL)),
//                WxConstantr.WX_SNSAPI_BASE, StringUtils.trimToEmpty(WxConstantr.WX_STATE),WxConstantr.AGENT_ID);
//        String sb = "【待办】后期一体化系统需求计划审批,链接为："+urlr;
//        JSONObject postJsonUserId = new JSONObject();
//        JSONObject postJsonUserIds = new JSONObject();
//        postJsonUserId.put("touser",userId);
//        postJsonUserId.put("toparty","");
//        postJsonUserId.put("totag","");
//        postJsonUserId.put("msgtype","textcard");
////        postJsonUserId.put("msgtype","text");
//        postJsonUserId.put("agentid", "1000049");
//        postJsonUserIds.put("title","【待办】后期一体化系统需求计划审批");
//        postJsonUserIds.put("description",setOf);
//        postJsonUserIds.put("url",urlr);
////        postJsonUserIds.put("content",sb);
//        postJsonUserId.put("textcard", postJsonUserIds);
////        postJsonUserId.put("safe", "0");
//        postJsonUserId.put("enable_id_trans", "0");
//        postJsonUserId.put("enable_duplicate_check", "0");
//        postJsonUserId.put("duplicate_check_interval", "1800");
//        System.out.println("postJsonUserId:" +postJsonUserId);
//        //发送请求
//        String urls = getSeeUserId + "?agentId=1000049"+"&postData=" + postJsonUserId;
//        System.out.println("urllss "+urlr);
//        JSONObject seeUserIdResponse = RestUtilsr.httpClientGet(urls);
//        System.out.println("popsss:"+seeUserIdResponse.toString());
//        return "sucess";
//    }

    /**
     * @description：
     * @author:kwr
     * @parms:
     * @time：2022-9-17 return eiInfo
     */
    public static String pushWxMsg(String userId,String name,String node,String time,String nid) throws DocumentException {

        String urls = url;
        String agentId = "1000049";
        String xml = getXMLs(userId,name,node,time,nid);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("postData", xml);
        pMap.put("agentId", agentId);
        System.out.println("pMapss:" +pMap);
        HttpClient client = new HttpClient();
        PostMethod httpPost = new PostMethod(urls);
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

            e.printStackTrace();
            System.err.println("解析错误的XML文件内容("+e.toString()+")："+result);
            return "erro";
        }

        return "sucess";
    }

    /**
     * 组合参数
     * @param
     * @return
     */
    public static String getXMLs(String userId,String name,String node,String time,String nid){
        String H5_URL = WxConstantr.H5_REQUEST_URL + "?serial="+WxConstantr.serial+"&nid="+nid;
        String setOf = "发起人："+name+"" +"，"+"当前节点："+node+"，"+"最近处理时间："+time+"，"+"驳回原因：";
        String urlr = String.format(WxConstantr.WX_H5_OAUTH2_PRIVATE_URL, WxConstantr.WX_APP_ID, URIUtil.encodeURIComponent(URIUtil.encodeURIComponent(H5_URL)),
                WxConstantr.WX_SNSAPI_BASE, StringUtils.trimToEmpty(WxConstantr.WX_STATE),WxConstantr.AGENT_ID);
        String sb = "【待办】后期一体化系统需求计划审批,链接为："+urlr;
        JSONObject postJsonUserId = new JSONObject();
        JSONObject postJsonUserIds = new JSONObject();
        postJsonUserId.put("touser",userId);
        postJsonUserId.put("toparty","");
        postJsonUserId.put("totag","");
        postJsonUserId.put("msgtype","textcard");
//        postJsonUserId.put("msgtype","text");
        postJsonUserId.put("agentid", "1000049");
        postJsonUserIds.put("title","【待办】后期一体化系统需求计划审批");
        postJsonUserIds.put("description",setOf);
        postJsonUserIds.put("url",urlr);
//        postJsonUserIds.put("content",sb);
        postJsonUserId.put("textcard", postJsonUserIds);
//        postJsonUserId.put("safe", "0");
        postJsonUserId.put("enable_id_trans", "0");
        postJsonUserId.put("enable_duplicate_check", "0");
        postJsonUserId.put("duplicate_check_interval", "1800");
        String soapXML = postJsonUserId.toJSONString();
        System.out.println("soapXML:" +soapXML);
        return soapXML;
    }

}
