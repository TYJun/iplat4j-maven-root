<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="特种设备预警管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<%-- <EF:EFInput ename="taskNo" cname="工单号：" colWidth="4" ratio="3:8"/> --%>
			<EF:EFInput ename="machineCode" cname="检验设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="检验设备名称：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="checkType" cname="检验类型：" colWidth="4"
				ratio="4:8" textField="label" valueField="value" >
			 	<EF:EFOption label="请选择" value=""/>	
				<EF:EFOption label="年度检验" value="0"/>
				<EF:EFOption label="定期检查" value="1"/>
			</EF:EFSelect>			
			<EF:EFDatePicker ename="thisFinishDateS" cname="检验日期起：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
            <EF:EFDatePicker ename="thisFinishDateE" cname="检验日期止：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
		</div>
	</EF:EFRegion>
	
	<EF:EFRegion id="result" title="预警管理"  >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="taskNo" cname="工单号" hidden="true" />
			<EF:EFColumn ename="machineId" cname="设备ID" hidden="true" />
			<EF:EFColumn ename="machineCode" cname="检验设备编码"  readonly="true"/>
			<EF:EFColumn ename="machineName" cname="检验设备名称"  readonly="true"/>
			<EF:EFColumn ename="innerMachineCode" cname="设备内部编码" hidden="true" />
			<EF:EFComboColumn ename="checkType" cname="检验类型" width="100" readonly="true">
				<EF:EFOption label="请选择" value=""/>	
				<EF:EFOption label="年度检验" value="0"/>
				<EF:EFOption label="定期检查" value="1"/>
				</EF:EFComboColumn>	
			<EF:EFColumn ename="thisFinishDate" cname="本次检验完工日期" readonly="true"/>
			<EF:EFColumn ename="nextCheckDate" cname="下次检验日期"  readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="82%" title="特种设备检验管理" modal="true" />
	</EF:EFPage>