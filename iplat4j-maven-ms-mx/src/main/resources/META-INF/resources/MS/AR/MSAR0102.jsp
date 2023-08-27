<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="com.baosight.iplat4j.core.ei.EiConstant" %>
<%@ page import="com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import="com.baosight.iplat4j.core.log.LoggerFactory" %>
<%@ page import="com.baosight.iplat4j.core.service.soa.XLocalManager" %>
<%@ page import="org.springframework.web.multipart.MultipartHttpServletRequest" %>
<%@ page import="org.springframework.web.multipart.commons.CommonsMultipartResolver" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.baosight.iplat4j.core.web.threadlocal.UserSession" %>
<%@ page import="com.baosight.iplat4j.core.exception.PlatException" %>
<%
        UserSession.web2Service(request);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        String ename = request.getParameter("ename");
        String serviceName = request.getParameter(EiConstant.serviceName);
        String methodName = request.getParameter(EiConstant.methodName);
        MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
        // Iterator multiFileIterator = multipartRequest.getMultiFileMap().get(ename).iterator();
        // serviceName methodName
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceName, serviceName);
        inInfo.set(EiConstant.methodName, methodName);
        inInfo.set("multipartRequest", multipartRequest);
        inInfo.set("file", multipartRequest.getFileMap().get(ename));
        EiInfo outInfo = XLocalManager.call(inInfo);
        UserSession.getData().clear();
        if(outInfo.getStatus()<0){
                throw new PlatException(outInfo.getMsg());
        }
%>