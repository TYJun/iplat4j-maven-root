<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import="com.baosight.iplat4j.core.log.Logger" %>
<%@ page import="com.baosight.iplat4j.core.log.LoggerFactory" %>
<%@ page import="com.baosight.iplat4j.eu.dm.PlatFileUploadManager" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>

<%
	// 根据docId获取单个附件的JSON信息
	// 根据docTag获取附件列表JSON信息

	String docId = request.getParameter("docId");
	String docTag = request.getParameter("docTag");
	String fileLocation = request.getParameter("fileLocation");
	String orderBy = request.getParameter("orderBy") == null ? "" : request.getParameter("orderBy");
	System.out.println(docId + docTag);
	PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");

	String fileJSON = "[]";

	try {
		//当fileLocation属性不为空并且为bcloud或者pcloud时
		if (StringUtils.isNotEmpty(fileLocation) && ("bcloud".equals(fileLocation) || "pcloud".equals(fileLocation))) {
			fileJSON = fileUpLoadManager.getDocJSONByTag(docTag);
		} else {
			if (StringUtils.isNotEmpty(docId)) {
				// 根据附件的docId获取单个附件在TEUDM02表中的信息
				fileJSON = fileUpLoadManager.getDocJSONById(docId);
			} else {
				// 根据docTag获取附件列表JSON信息
				if (StringUtils.isNotEmpty(docTag)) {
					fileJSON = fileUpLoadManager.getDocJSONByTag(docTag, orderBy);
				}
			}
		}
	} catch (Exception e) {
		Logger logger = LoggerFactory.getLogger(PlatFileUploadManager.class);
		logger.error("无法获取附件信息", e);
		out.print("无法获取附件信息:" + e.getMessage());
	}

	out.print(fileJSON);
%>
