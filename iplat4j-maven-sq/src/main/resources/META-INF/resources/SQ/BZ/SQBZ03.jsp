 <!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="标准信息">
		<EF:EFGrid blockId="result" autoDraw="no" copyToAdd="false">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"
				align="center" />
			<EF:EFColumn ename="instanceName" cname="考核标准名称" width="100"
				align="center" required="true"/>
			<%-- <EF:EFColumn ename="point" cname="分数" width="100" align="center" /> --%>
			<EF:EFComboColumn ename="point" cname="分数"
				columnTemplate="#=textField#"
				itemTemplate="#=textField#" textField="textField"
				valueField="valueField">
				<EF:EFOption label="5" value="5" />
				<EF:EFOption label="10" value="10" />
			</EF:EFComboColumn>
			<%-- <EF:EFColumn ename="pointType" cname="计分方式" width="100" align="center"/> --%>
			<EF:EFComboColumn ename="pointType" cname="打分方式"
				columnTemplate="#=textField#"
				itemTemplate="#=textField#" textField="textField"
				valueField="valueField">
				<EF:EFOption label="打分" value="打分" />
				<EF:EFOption label="判断" value="判断" />
			</EF:EFComboColumn>
			<EF:EFColumn ename="orderNumber" cname="调查排序" width="100"
				align="center" required="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

