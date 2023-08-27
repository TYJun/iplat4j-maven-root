<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="采购审批管理">
	<EF:EFRegion id="inqu" title="查询条件"  showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="startTime" cname="申请日期起：" role="datetime" colWidth="3" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFDatePicker ename="endTime" cname="申请日期止：" role="datetime" colWidth="3" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>

		</div>
	</EF:EFRegion>

	<EF:EFRegion id="result" title="物资申请信息管理" >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="false" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="applyBillNo" cname="申请单号" readonly="true" align="center"/>
			<EF:EFColumn ename="applyStaffId" cname="申请人" readonly="true" align="center"/>
			<%--<EF:EFColumn ename="applyDeptName" cname="申请科室" readonly="true" />--%>
			<EF:EFColumn ename="recCreateTime" cname="申请日期" readonly="true" align="center" />
			<EF:EFColumn ename="applySign" cname="申请状态" readonly="true" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true"  width="90%" height="92%" title="物资申请登记管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true"  width="90%" height="92%" title="物资申请管理" modal="true" />
</EF:EFPage>