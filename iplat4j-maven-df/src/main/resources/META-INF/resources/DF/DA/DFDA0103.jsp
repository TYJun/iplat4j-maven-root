<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage> 
	<div id="qrcode" style＝"letter-spacing:8px;>
		<!-- 二维码容器 -->
	</div>
	<input type="text" id="getval" />
	<button id="send">生成验证码</button>
</EF:EFPage>
<script type="text/javascript" src="../MEAL/common/qrcode/qrcode.js"></script>