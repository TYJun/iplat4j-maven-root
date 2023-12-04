package rm.xq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

@Configuration
public class RestUtilsr {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static JSONObject get(String url, Map<String,String> urlParams){
        return get(urlToUri(url,urlParams));
    }

    //在处理企业微信某些参数时有问题
    public static JSONObject get(String url){
        return get(URI.create(url));
    }

    private static JSONObject get(URI uri){
        ResponseEntity<JSONObject> responseEntity =restTemplate.getForEntity(uri,JSONObject.class);
        serverIsRight(responseEntity);   //判断服务器返回状态码
        return responseEntity.getBody();
    }

    public static JSONObject post(String url,Map<String,String> urlParams,JSONObject json){
        //组装url
        return post(urlToUri(url,urlParams),json);
    }

    public static JSONObject post(String url,JSONObject json){
        //组装urL
        return post(URI.create(url),json);
    }

    private static JSONObject post(URI uri,JSONObject json){
        //组装url
        //设置提交json格式数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity(json, headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(uri,request,JSONObject.class);
        serverIsRight(responseEntity);  //判断服务器返回状态码
        return responseEntity.getBody();
    }

    private static URI urlToUri(String url,Map<String,String> urlParams){
        //设置提交json格式数据
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        for(Map.Entry<String,String> entry : urlParams.entrySet())  {
            uriBuilder.queryParam((String)entry.getKey(),  (String) entry.getValue()) ;
        }
        return  uriBuilder.build(true).toUri();
    }

    public static JSONObject upload(String url,MultiValueMap formParams){
        //设置表单提交
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formParams, headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url,request,JSONObject.class);
        serverIsRight(responseEntity);  //判断服务器返回状态码
        return responseEntity.getBody();
    }

    public static String download(String url,String targetPath,HttpEntity<MultiValueMap<String, String>> httpEntity ) throws IOException {

        ResponseEntity<byte[]> rsp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
        if(rsp.getStatusCode() != HttpStatus.OK){
            System.out.println("文件下载请求结果状态码：" + rsp.getStatusCode());
        }
        // 将下载下来的文件内容保存到本地
        Files.write(Paths.get(targetPath), Objects.requireNonNull(rsp.getBody()));
        return targetPath;
    }

    public static String download(String url,String targetPath) throws IOException {

        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
        if(rsp.getStatusCode() != HttpStatus.OK){
            System.out.println("文件下载请求结果状态码：" + rsp.getStatusCode());
        }
        // 将下载下来的文件内容保存到本地
        Files.write(Paths.get(targetPath), Objects.requireNonNull(rsp.getBody()));
        return targetPath;

    }

    public static byte[] dowload(String url){
        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
        return rsp.getBody();
    }

    private static void serverIsRight(ResponseEntity responseEntity){
        if(responseEntity.getStatusCodeValue()==200){
//            System.out.println("服务器请求成功：{}"+responseEntity.getStatusCodeValue());
        }else {
            System.out.println("服务器请求异常：{}"+responseEntity.getStatusCodeValue());
        }
    }

    /**
     * httpClient get请求
     * @Title: httpClientGet
     * @param url url
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     **/
    public static JSONObject httpClientGet(String url) throws Exception {
        URL postUrl = new URL(url);
        URI uri = new URI(postUrl.getProtocol(), postUrl.getHost()+":"+postUrl.getPort(), postUrl.getPath(), postUrl.getQuery(), null);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Content-Type","text/xml;charset=utf-8");
        httpGet.setHeader("Connection","keep-alive");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            String tSourceContent = EntityUtils.toString(response.getEntity());   //返回的内容  getResponseContent(pResponse)
            System.out.println("tSourr"+tSourceContent);
            //去除前后的<String....  </string>
            int iBegin=tSourceContent.indexOf("{");
            int iEnd=tSourceContent.lastIndexOf("}");
            String tContent=tSourceContent.substring(iBegin,iEnd+1);
            System.out.println("Status:"+response.getStatusLine().getStatusCode());
            // 从响应模型中获取响应实体
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            if (tContent != null) {
//                String xmlString = EntityUtils.toString(tContent);
                JSONObject result = parseXml(tContent);
                System.out.println("kksskk"+result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {httpClient.close();}
                if (response != null) { response.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new JSONObject();
    }

    /**
     * xml返回数据解析
     * @Title: parseXml
     * @param xmlString xmlString
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     **/
    public static JSONObject parseXml(String xmlString) throws Exception {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlString)));
        String result = document.getElementsByTagName("string").item(0).getTextContent();
        JSONObject parse = JSON.parseObject(result);
        return parse;
    }
}
