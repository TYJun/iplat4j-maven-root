<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<title>职工心声登记管理</title>
<EF:EFPage>

	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFDateSpan startName="inqu_status-0-complaintDateS" startCname="发生日期起"
						   endName="inqu_status-0-complaintDateE" endCname="发生日期止"
						   ratio="3:3" startRatio="4:8" endRatio="4:8"/>
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="3" ratio="4:8"/>
			<span id="labor" style="display: none">
				<EF:EFInput ename="inqu_status-0-complaintName" cname="发起人" colWidth="3" ratio="4:8"/>
				<EF:EFSelect ename="inqu_status-0-statusCode" cname="单子状态" colWidth="3"
							  ratio="4:8" textField="label" valueField="value" defaultValue="00">
					<EF:EFOption label="全部" value=""/>
					<EF:EFOption label="新建" value="0"/>
					<EF:EFOption label="待分配" value="00" />
					<EF:EFOption label="作废" value="5"/>
				</EF:EFSelect>
			</span>
			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="3" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-complaintContent" cname="发起内容" colWidth="3" ratio="4:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="职工心声登记管理">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="billNo" cname="发起单号" width="120" align="center" />
			<EF:EFColumn ename="statusCode" cname="单子状态" width="100" align="center" hidden="false" />
			<EF:EFColumn ename="complaintDate" cname="发生日期" width="100" align="center"/>
			<EF:EFColumn ename="complaintName" cname="发起人" width="100" align="center"/>
			<EF:EFColumn ename="complaintPhone" cname="发起人电话" width="100" align="center"/>
			<EF:EFColumn ename="complaintDept" cname="发起人部门/单位" width="100" align="center"/>
			<EF:EFColumn ename="complaintType" cname="发起人类别" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="deptName" cname="处理科室" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="complaintWay" cname="登记方式" width="100" align="center" hidden="true"/>
			<EF:EFColumn ename="complaintContent" cname="发起内容" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹窗配置 -->
<EF:EFWindow id="popData" url="" lazyload="true" width="100%" height="100%" title="职工心声邮箱" />