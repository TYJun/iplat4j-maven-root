<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="特种设备档案管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="machineCode" cname="设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="classifyName" cname="设备分类：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="statusCode" cname="设备状态：" colWidth="4"	ratio="3:8" >
			    <EF:EFOption label="请选择" value=""/>
			     <EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="启用" value="1"/>
				<EF:EFOption label="停用" value="-1"/>
			</EF:EFSelect>			
			<EF:EFInput ename="models" cname="规格型号：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="needScan" cname="是否扫二维码：" colWidth="4"	ratio="3:8" >
			    <EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="否" value="N"/>
				<EF:EFOption label="是" value="Y"/>
			</EF:EFSelect>			
			<EF:EFDatePicker ename="thisCheckDateS" cname="检查日期起：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
            <EF:EFDatePicker ename="thisCheckDateE" cname="检查日期止：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="特种设备信息管理" fitHeight="true" >
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="false"
			checkMode="single,row" readonly="false" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true"/>
			<EF:EFColumn ename="machineCode" cname="设备编码" enable="false" locked="true"/>
			<EF:EFColumn ename="machineName" cname="设备名称"  enable="false" locked="true"/>			
			<EF:EFColumn ename="classifyName" cname="设备分类" enable="false" locked="true"/>
			<EF:EFColumn ename="models" cname="规格型号" enable="false"/>
			<EF:EFComboColumn ename="statusCode" cname="设备状态" width="100" readonly="true" >
			    <EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="启用" value="1"/>
				<EF:EFOption label="停用" value="-1"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="fixedId" cname="安装地点id" hidden="true" enable="false"/>
			<EF:EFColumn ename="fixedTime" cname="安装日期" enable="false"/>
			<EF:EFColumn ename="thisCheckDate" cname="本年检查日期" enable="false"/>
			<EF:EFColumn ename="fixedPlace" cname="安装地点" enable="false"/>
			<EF:EFColumn ename="useDeaprtName" cname="使用科室" enable="false"/>
			<EF:EFComboColumn ename="needScan" cname="是否扫描二维码"  width="100" >
				<EF:EFOption label="否" value="N"/>
				<EF:EFOption label="是" value="Y"/>
			</EF:EFComboColumn>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData1" url="" lazyload="true" width="400" height="350" title="二维码" modal="true" />
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="99%" title="设备信息管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="70%" height="90%" title="" modal="true" />
</EF:EFPage>