<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
<%--		<EF:EFSelect ename="groupNum" cname="分组" colWidth="4" filter="contains">--%>
<%--			<EF:EFOption label="第一组" value="第一组"/>--%>
<%--			<EF:EFOption label="第二组" value="第二组"/>--%>
<%--			<EF:EFOption label="第三组" value="第三组"/>--%>
<%--			<EF:EFOption label="第四组" value="第四组"/>--%>
<%--			<EF:EFOption label="第五组" value="第五组"/>--%>
<%--			<EF:EFOption label="第六组" value="第六组"/>--%>
<%--			<EF:EFOption label="第七组" value="第七组"/>--%>
<%--		</EF:EFSelect>--%>
<%--		<EF:EFSelect ename="deptNum" cname="基准名称"--%>
<%--					 resultId="dept" textField="deptName" valueField="deptNum"--%>
<%--					 serviceName="DIJZ03" methodName="queryDeptName" optionLabel="请选择"--%>
<%--					 colWidth="4" filter="contains">--%>
<%--		</EF:EFSelect>--%>
		<EF:EFInput ename="configureName" cname="所需配置基准名称：" colWidth="4" ratio="3:8" />
		<EF:EFInput ename="configureTime" cname="配置倒计时时间(秒)：" colWidth="4" ratio="3:8" />
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

</script>

