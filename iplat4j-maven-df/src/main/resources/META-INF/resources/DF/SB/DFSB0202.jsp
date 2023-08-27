<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="">
	<EF:EFRegion id="inqu1" title="检验信息">
		<div class="row">
			 <EF:EFInput ename="id" colWidth="4" ratio="3:8" type="hidden"/>
			<EF:EFInput ename="taskNo" cname="工单号：" colWidth="4" ratio="4:8" readonly="true"/>
			<EF:EFPopupInput ename="machineId" cname="检验设备编码："  colWidth="4" ratio="4:8"
				popupTitle="设备信息选择" required="true" readonly="true"
				popupType="ServiceGrid" serviceName="DFSB02" methodName="queryMachineId" resultId="result"
				valueField="machineId" textField="machineCode"  
				columnEnames="machineCode,machineName,innerMachineCode"
				columnCnames="特种设备编码, 特种设备名称, 设备内部编码"
				backFillFieldIds="machineName,innerMachineCode"
				backFillColumnIds="machineName,innerMachineCode"/>
			<EF:EFInput ename="machineName" cname="检验设备名称：" colWidth="4" ratio="4:8" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="innerMachineCode" cname="内部设备编码：" colWidth="4" ratio="4:8"/>
			<EF:EFSelect ename="checkType" cname="检验类型：" colWidth="4"
				ratio="4:8" textField="label" valueField="value" required="true">
			 	<EF:EFOption label="请选择" value=""/>	
				<EF:EFOption label="年度检验" value="0"/>
				<EF:EFOption label="定期检查" value="1"/>
			</EF:EFSelect>
			<EF:EFSelect ename="statusCode" cname="工单状态"  colWidth="4"
				ratio="4:8" textField="label" valueField="value">
			 	<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="已审核" value="1"/>
				<EF:EFOption label="待审核" value="-1"/>
			</EF:EFSelect>
		</div>
		<div class="row">	
			<EF:EFDatePicker ename="thisCheckDate" cname="本次检验日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>	
			<EF:EFDatePicker ename="thisFinishDate" cname="本次检验完工日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>			
			<EF:EFDatePicker ename="nextCheckDate" cname="下次检验日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
				parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
		<div title="文件列表">
			<EF:EFGrid blockId="result" fitHeight="true" autoDraw="no" queryMethod="queryFile">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="relateId" cname="附件id" enable="false" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="附件名称" enable="false"/>
				<EF:EFColumn ename="filePath" cname="附件路径" enable="false"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
	<%-- 附件上传窗口 --%>
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="projectFile" docTag="sb_file" path="file"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
