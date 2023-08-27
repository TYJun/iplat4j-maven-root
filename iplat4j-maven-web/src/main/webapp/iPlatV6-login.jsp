<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.baosight.iplat4j.core.FrameworkInfo" %>
<%@ page import="com.baosight.iplat4j.core.license.LicenseStub" %>

<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import="com.baosight.iplat4j.core.util.StringUtils" %>
<%@ page import="java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<%
    org.springframework.security.core.context.SecurityContextHolder.clearContext();
    LicenseStub.setLicenseDir(application.getRealPath("/WEB-INF"));
    String[] ret = LicenseStub.checkLicense2();
    boolean valid = "true".equals(ret[0]); //LicenseStub.checkLicense2();
    int days = 0;
    if (!"".equals(ret[2]) && !"0".equals(ret[2])) {
        days = Integer.parseInt(ret[2]);
    }
    String licMsg = valid ? (("false".equals(ret[3]) && days >= -10 && days < 0) ? "<div style='color:#ee9933;font-weight:bold;font-size:18px'>许可证还有[" + (-days) + "]天将过期!</div>" : "")
            : "<div style='color:red;font-weight:bold;font-size:22px'>许可证非法!</div>";

    Exception exp = (Exception) request.getAttribute("AuthenticationException");
    String user = (String) request.getAttribute("AuthenticationUser");

    if (!org.springframework.util.StringUtils.isEmpty(request.getParameter("expmsg"))) {
        String expmsg = request.getParameter("expmsg");
        exp = new Exception(URLDecoder.decode("Exception:" + expmsg));
    }
    String loginErrTag = "0";
    if (!org.springframework.util.StringUtils.isEmpty(request.getParameter("login_error"))) {
        loginErrTag = request.getParameter("login_error");
    }

    String username = "";
    String password = "";
    String captcha = "";
    if (exp != null) {
        username = user;
    }

    String usrHeader = request.getHeader("user-agent");


    String projectCname = FrameworkInfo.getProjectCname();
    String projectTypeDesc = FrameworkInfo.getProjectTypeDesc();

    // 获取iPlatUI静态资源地址
    String iPlatStaticURL = FrameworkInfo.getPlatStaticURL(request);

    String theme = org.apache.commons.lang.StringUtils.defaultIfEmpty(PlatApplicationContext.getProperty("theme"),"ant");

    // 获取Context根路径，考虑到分布式部署的场景，不能直接使用WebContext
    String iPlatContext = FrameworkInfo.getPlatWebContext(request);
%>
<c:set var="ctx" value="<%=iPlatContext%>"/>
<c:set var="iPlatStaticURL" value="<%=iPlatStaticURL%>"/>

<c:set var="loginExp" value="<%=exp%>"/>
<c:set var="theme" value="<%=theme%>" scope="session"/>

<html class="i-theme-blue">
<head>
    <meta charset="utf-8"/>
    <meta name="robots" content="noindex, nofollow"/>
    <meta name="description" content="登录界面"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <% if (StringUtils.isNotEmpty(projectCname) && StringUtils.isNotEmpty(projectTypeDesc)) { %>
    <title><%=projectCname%>[<%=projectTypeDesc%>]登录界面</title>
    <% } else { %>
    <title>登录界面</title>
    <% } %>

    <link rel="shortcut icon" href="iplat.ico" type="image/x-icon">
    <link rel="stylesheet" id="css-main" href="${iPlatStaticURL}/iplatui/assets/css/iplat.ui.bootstrap.min.css">
    <link href="${iPlatStaticURL}/iPlatV6-login.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" type="text/css"  href="${iPlatStaticURL}/iplatui/css/iplat.ui.ued.login.css">&lt;%&ndash;ued亮色样式&ndash;%&gt;--%>
    <script src="${iPlatStaticURL}/kendoui/js/jquery.min.js"></script>

    <!--[if lte IE 8]>
    <link href="${iPlatStaticURL}/iPlatV6-login-ie.css" rel="stylesheet" type="text/css"/>
    <script src="${iPlatStaticURL}/iplatui/assets/js/polyfills/iplat.ui.ie8.polyfills.min.js"></script>
    <![endif]-->

    <script src="${iPlatStaticURL}/iPlatV6-login.js"></script>
    <%
        String domain = FrameworkInfo.getProjectAppTopDomain();
        if (domain != null && domain.startsWith(".")) {
            domain = domain.substring(1);
    %>
    <script type="text/javascript">
        try {
            document.domain = '<%=domain%>';
        } catch (ex) {
            alert('model not valid[<%=domain%>]');
        }
    </script>
    <%
        }
    %>
</head>
<body class="i-theme-${theme}">
<div class="main">
    <div class="wrapper">
        <div class="content overflow-hidden">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <div class="login-block <c:if test="${not empty loginExp}"> animated shake</c:if>">
                        <div class="form-header">
                            <div class="logo"></div>
                            <p>用户登录</p>
                            <p class="text-danger">
                                <c:if test="${not empty loginExp}">
                                    <%
                                        String loginError = exp.getMessage();
                                        int index = loginError.indexOf("Exception:");
                                        if (index >= 0) {
                                            loginError = loginError.substring(index + 10);
                                        }
                                        if (!"1".equals(loginErrTag)&&
                                                (request.getAttribute("AuthenticationUser") == null || request.getAttribute("AuthenticationUser") == "")) {
                                            loginError = "请输入用户名";
                                        }
                                    %>
                                    <%=loginError%>
                                </c:if>
                            </p>
                        </div>

                        <form autocomplete="off" class="form-horizontal push-10-t push-10" action="${ctx}/login"
                              method="post"  onsubmit="javascript:return loginClick();">
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input class="form-input" type="text"
<%--                                           value="<%=encoder.encodeForHTMLAttribute(username)%>" id="p_username"--%>
                                           name="p_username"
                                           placeholder="用户名"/>
                                </div>
                            </div>
                            <div class="form-group password">
                                <div class="col-xs-12">
                                    <input class="form-input" type="password"
<%--                                           value="<%=encoder.encodeForHTMLAttribute(password)%>" id="p_password"--%>
                                           name="p_password"
                                           placeholder="密码"/>
                                </div>
                            </div>
                            
                            <%
								 if (FrameworkInfo.isCaptchaEnabled()) {
							%>
                            
                           
			               <div class="form-group">
								 <div class="col-xs-8">
								 <input class="form-input" type="text" id="p_captcha"
									 name="p_captcha"
									 placeholder=""/>
								 </div>
								 <div class="col-xs-4">
								 <img class="id-code-img" src="./captcha.jpg"
									 style="vertical-align:top;cursor:pointer;"
									 title=""
									 onclick="javacript:this.src='./captcha.jpg?'+(new Date()).getTime();"/>
								 </div>
						   </div>

                          <%
							 }
						  %>                        
                            
                            
                          <%--  <div class="form-group remember"> --%>
                           <%--     <div class="col-xs-6"> --%>
                                    <%--<label class="css-input">--%>
                                    <%--<input type="checkbox" id="login-remember-me" value="false"--%>
                                    <%--name="remember-me"/><span class="i-icon"></span>--%>
                                    <%--2周内免登录--%>
                                    <%--</label>--%>
                              <%--  </div> --%>
                             <%--   <div class="col-xs-6" style="text-align: right">--%>
                              <%--      <%--<a href="${ctx}/web/XS0102" style="margin-right: 6px">注册</a>--%>
                              <%--      <a href="${ctx}/web/XS0106">忘记密码？</a>--%>

                             <%--   </div>--%>
                           <%-- </div>--%>
                            <div class="form-group log-in">
                                <div class="col-xs-12">
                                    <button id="login" class="login-btn" type="submit">登录
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-lg-6 col-lg-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="warning-window" style="margin-top: -354px; height: 354px;">
                        <div class="i-region-header">警告<span class="i-icon i-close"></span>
                        </div>
                        <div class="i-region-content">
                            <div class="col-md-12">
                                <span class="warning-msg">请使用IE8或Chrome v35及以上浏览器访问</span>
                            </div>
                            <div class="col-lg-6 col-lg-offset-3 col-sm-8 col-sm-offset-2" style="padding: 0">
                                <div class="browser-icon col-md-12" style="padding: 0">
                                    <div class="col-xs-5">
                                        <img src="${iPlatStaticURL}/iplatui/img/icon_ie.png" width="104" height="101">
                                    </div>
                                    <div class="col-xs-5 col-xs-offset-2">
                                        <img src="${iPlatStaticURL}/iplatui/img/icon_chrome.png" width="101" height="101">
                                    </div>
                                </div>
                                <div class="browser-name col-md-12" style="padding: 0">
                                    <div class="col-xs-5">
                                        <span class="ie-name">IE</span>
                                    </div>
                                    <div class="col-xs-5 col-xs-offset-2">
                                        <span class="chrome-name">Chrome</span>
                                    </div>
                                </div>
                                <div class="download-browser col-md-12" style="padding: 0">
                                    <div class="col-xs-5">
                                        <input class="download-ie download-btn" type="button" value="点击下载"
                                               onclick="window.open('https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads')"/>
                                    </div>
                                    <div class="col-xs-5 col-xs-offset-2">
                                        <input class="download-chrome download-btn" type="button" value="点击下载"
                                               onclick="window.open('https://www.google.com/chrome/browser/desktop/index.html')"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="info">
        <div class="row">
            <div class="footer-center col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <div class="phone-number">
                    &emsp;
<%--                    <span>运维平台热线  8008200220、4008210860、26646708、26642410</span>--%>
                </div>
                <div class="copyright-info">
                    <span>技术支持：南京博纳睿通软件科技有限公司 咨询电话：025-57716635</span>
                </div>
            </div>
            <div class="footer-right col-sm-2 col-md-3">
                <div class="footer-logo">
                    <img src="${iPlatStaticURL}/iplatui/img/icon_ie.png" width="51"
                         onclick="window.open('https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads')">
                </div>
                <div class="footer-logo">
                    <img src="${iPlatStaticURL}/iplatui/img/icon_chrome.png" width="51"
                         onclick="window.open('https://www.google.com/chrome/browser/desktop/index.html')">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="i-overlay"></div>
</body>
</html>
