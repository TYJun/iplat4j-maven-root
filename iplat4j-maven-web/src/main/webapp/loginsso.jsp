<!DOCTYPE html>

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.baosight.iplat4j.core.FrameworkInfo"%>
<%@page import="org.apache.commons.httpclient.HttpClient"%>
<%@page import="org.apache.commons.httpclient.methods.MultipartPostMethod"%>
<%@page import="org.dom4j.Document"%>
<%@page import="org.dom4j.DocumentException"%>
<%@page import="org.dom4j.DocumentHelper"%>
<%@page import="org.dom4j.Element"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken"%>
<%@page import="com.baosight.iplat4j.core.web.threadlocal.UserSession"%>
<%@ page import="com.baosight.iplat4j.core.ei.EiInfo"%>
<%@ page import="com.baosight.iplat4j.core.ei.EiConstant"%>
<%@ page import="com.baosight.iplat4j.core.service.soa.XServiceManager"%>
<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%
    String domain = FrameworkInfo.getProjectAppTopDomain();
    if (domain != null && domain.startsWith(".")) {
        domain = domain.substring(1);
%>
<script type="text/javascript">
    try {
        document.domain = '<%=domain%>';
    } catch (ex) {
        alert('model not valid[<%=domain%> ]');
    }
</script>
<%
    }
%>

<%
    org.springframework.security.core.context.SecurityContextHolder.clearContext();
    session.setAttribute("iplat.logout", "1");

    RequestDispatcher rd;
    //统一认证注销时会调用单点系统的serviceUrl?logoutRequest=***,根据是否有logoutRequest判断处理session
    String logoutFlag=request.getParameter("logoutRequest");
    if (logoutFlag != null && logoutFlag.length() > 0) {
        session.removeAttribute("SPRING_SECURITY_CONTEXT");
        session.removeAttribute("loginname");
        session.removeAttribute("userid");
        session.invalidate();
        return;
    }

    session = request.getSession(true);
    response.setDateHeader("Expires", (new java.util.Date(0)).getTime());
    request.setCharacterEncoding("UTF-8");

    String olduserid = session.getAttribute("userid") == null ? "" : session.getAttribute("userid").toString();
    //1.如果session中已经包含user代表此用户已经登录
    if (!olduserid.equals("")) {
        rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
        return;
    }

    // 统一认证地址 http://172.16.5.126/sso/login
    // 统一退出地址 http://172.16.5.126/sso/logout
    //appid
    String appid = PlatApplicationContext.getProperty("eqms.sso.appid");//"7865f9db-f1cc-4eea-9124-c196d2de0b72";//
    //sso-server
    String ssohost = PlatApplicationContext.getProperty("eqms.sso.serverHost");//"http://172.16.5.126/sso/";//
    //sso-client 应用context/loginsso.jsp
    String service = PlatApplicationContext.getProperty("eqms.sso.service");//"http://127.0.0.1:8081/eqms/loginsso.jsp";//
    service = URLEncoder.encode(service, "UTF-8");
    System.out.println("service : " + service);
    //2.如果token为空代表此用户并没有登录 重定向到登录页面
    String token = request.getParameter("token");
    System.out.println("token : " + token);
    String casLogin = "";
    if (token == null) {
        casLogin = ssohost + "login?appid=" + appid + "&service=" + service;
        System.out.println("casLogin : " + casLogin);
        response.sendRedirect(casLogin);
        return;
    }
    //3.如果token不为空则跳转到server校验token有效性
    String validateurl = ssohost + "serviceValidate?token=" + token + "&service=" + service;
    System.out.println("validateurl : " + validateurl);
    HttpClient client = new HttpClient();
    client.setTimeout(60000);
    MultipartPostMethod method = new MultipartPostMethod(validateurl);
    method.setRequestHeader("Content-Type", "text/xml;charset=UTF-8");
    client.executeMethod(method);
    String result = method.getResponseBodyAsString();
    System.out.println("result : " + result);
    try {
        if (result != null) {
            Document dom = DocumentHelper.parseText(result);
            Element element = dom.getRootElement().element("authenticationSuccess").element("user");
            String userid = element.getText();
            System.out.println("userid : " + userid);
            //4. 如果检验成功 sso返回一个userid 根据这个userid进行相关登录操作
            if (userid != null) {
                //判断根据sso验证的用户在业务系统存不存在，存在则进行登录操作，不存在则跳转到错误提示页面
                EiInfo eiInfo = new EiInfo();
                eiInfo.set("loginName", userid);
                eiInfo.set(EiConstant.serviceId, "S_XS_03");
                EiInfo outInfo = XServiceManager.call(eiInfo);
                //状态为1表示存在此用户
                if (outInfo.getStatus() == EiConstant.STATUS_SUCCESS) {
                    //放入spring security认证信息
                    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_VERIFIED");
                    List<GrantedAuthority> listGra = new ArrayList<>();
                    listGra.add(authority);
                    Authentication authentication = new PreAuthenticatedAuthenticationToken(userid, userid, listGra);
                    authentication.setAuthenticated(true);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                    session.setAttribute("userid", userid);
                    session.setAttribute("loginname", userid);
                    rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                } else {
                    System.err.println("此用户在业务系统不存在");
                    response.sendRedirect(casLogin);
                }
            }
        }
    } catch (DocumentException e) {
        e.printStackTrace();
    }
%>