<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备报废管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="scrapNo" cname="报废单号" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="machineName" cname="设备名称" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="status" colWidth="4" ratio="3:8" cname="状态" >
				<EF:EFOption label="请选择状态" value=""/>
				<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="确定" value="1"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="scrapWay" cname="报废方式" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="scrapDateS" cname="报废日期起" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
			<EF:EFDatePicker ename="scrapDateE" cname="报废日期止" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd" parseFormats="['yyyy-mm-dd']"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="设备信息管理" fitHeight="true" >
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="false"
			checkMode="single,row" readonly="false" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true"/>
			<EF:EFColumn ename="scrapNo" width="150" cname="报废单号" enable="false" locked="true" align="center"/>
<%--			<EF:EFColumn ename="machineCode" width="150" cname="设备编码"  enable="false" locked="true" align="center"/>--%>
			<EF:EFColumn ename="status" cname="状态" enable="false" align="center"/>
			<EF:EFColumn ename="machineName" width="150" cname="设备名称" enable="false"  locked="true" align="center"/>
			<EF:EFColumn ename="fixedPlace" width="150" cname="安装地点" enable="false" locked="true" align="center"/>
			<EF:EFColumn ename="scrapWay" cname="报废方式" enable="false" align="center"/>
			<EF:EFColumn ename="scrapDate" cname="报废日期" enable="false" align="center"/>
			<EF:EFColumn ename="scrapReason" cname="报废原因" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="99%" title="设备信息管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="70%" height="90%" title="" modal="true" />
    <EF:EFWindow id="execlImport" url="" lazyload="true" refresh="true" width="60%" height="60%">
	<EF:EFRegion id="sxUpload" title="设备档案导入">
		<h4>设备档案导入规则：</h4>
		<li>设备编码（必填; ）</li>
		<li>设备名称（必填; ）</li>
		<li>设备分类id（必填; 取值为系统中已录入的设备分类id）</li>
		<li>安装地方id（必填; 取值为系统中已录入的安装地方id）</li>
		<li>安装地方编号（必填; 取值为系统中已录入的安装地方编号）</li>
		<li>安装地方名称（必填; 取值为系统中已录入的安装地方名称）</li>
		<li>生产单位名称（必填; ）</li>
		<li>供应商名称（必填; ）</li>
		<li>保养单位名称（必填; ）</li>
		<li>管理员工号（必填; 取值为系统中已录入的管理员工号）</li>
		<li>管理员名称（必填; 取值为系统中已录入的管理员名称）</li>
		<li>管理科室编号（必填; 取值为系统中已录入的管理科室编号）</li>
		<li>管理科室名称（必填; 取值为系统中已录入的管理科室名称）</li>
		<li>使用科室编号（必填; 取值为系统中已录入的使用科室编号）</li>
		<li>使用科室名称（必填; 取值为系统中已录入的使用科室名称）</li>
		<br/>
		<form id="excelForm"  enctype="multipart/form-data">
			文件上传<input id="excelFile" type="file" name="file" ><br />
		</form>
		<button id="download">模板下载</button>
	</EF:EFRegion>
	<div class="button-region" id="buttonDiv" style="float: right">
		<EF:EFButton cname="提交" ename="IMPORTSUBMIT" layout="0" ></EF:EFButton>
		<EF:EFButton cname="关闭" ename="IMPORTCLOSE" layout="0" ></EF:EFButton>
	</div>
   </EF:EFWindow>
</EF:EFPage>