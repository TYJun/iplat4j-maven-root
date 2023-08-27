<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="特种设备检验管理">
	<EF:EFRegion id="inqu" title="查询条件"  showClear="true">
		<div class="row">
			<EF:EFInput ename="taskNo" cname="工单号：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineCode" cname="检验设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="检验设备名称：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="statusCode" cname="工单状态：" colWidth="4"	ratio="3:8" >
			    <EF:EFOption label="请选择" value=""/>
			    <EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="已审核" value="1"/>
				<EF:EFOption label="待审核" value="-1"/>
			</EF:EFSelect>			
			<EF:EFDatePicker ename="thisCheckDateS" cname="检验日期起：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
            <EF:EFDatePicker ename="thisCheckDateE" cname="检验日期止：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
		</div>
	</EF:EFRegion>
	
	<EF:EFRegion id="result" title="特种设备信息管理" >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="false" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="taskNo" cname="工单号" readonly="true"/>
			<EF:EFColumn ename="machineCode" cname="检验设备编码" readonly="true" />
			<EF:EFColumn ename="machineName" cname="检验设备名称" readonly="true" />
			
			<EF:EFComboColumn ename="statusCode" cname="工单状态" width="100" readonly="true" >
				<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="已审核" value="1"/>
				<EF:EFOption label="待审核" value="-1"/>
				</EF:EFComboColumn>	
			<EF:EFColumn ename="thisCheckDate" cname="本次检验日期" readonly="true"  />
			<EF:EFColumn ename="thisFinishDate" cname="本次检验完工日期" readonly="true" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="82%" title="特种设备检验管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="70%" height="90%" title="" modal="true" />
</EF:EFPage>