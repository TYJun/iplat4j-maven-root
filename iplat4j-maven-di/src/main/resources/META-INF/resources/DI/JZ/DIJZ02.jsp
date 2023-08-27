<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备包管理">
			<EF:EFRegion id="inqu" title="查询区">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-machineCode" cname="设备包编码" />
					<EF:EFInput ename="inqu_status-0-machineName" cname="设备包名称" />
					<EF:EFSelect cname="状态:" ename="inqu_status-0-status" >
						<EF:EFOption label="全部" value="" />
						<EF:EFOption label="新建" value="0" />
						<EF:EFOption label="启用" value="1" />
						<EF:EFOption label="禁用" value="-1" />
					</EF:EFSelect>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIJZ02" >
					<EF:EFColumn ename="id" cname="主键" hidden="true" align="center"/>
					<EF:EFColumn ename="packetCode" cname="设备包编码" readonly="true" align="center"/>
					<EF:EFColumn ename="packetName" cname="设备包名称" readonly="true" align="center"/>
					<EF:EFColumn ename="status" cname="状态" readonly="true" align="center"/>
					<EF:EFColumn ename="createtime" cname="创建时间" readonly="true" align="center"/>
					<EF:EFColumn ename="createman" cname="创建人" readonly="true" align="center"/>
					<EF:EFColumn ename="modifyman" cname="修改人" readonly="true" align="center"/>
					<EF:EFColumn ename="memo" cname="备注" readonly="true" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="800" height="700" title="新增" modal="true" />
			<EF:EFWindow id="popDataModify" url="" lazyload="true" width="800" height="700" title="编辑" modal="true" />
		</div>
</EF:EFPage>