<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="采购申请管理">
	<EF:EFRegion id="inqu" title="查询条件"  showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="startTime" cname="申请日期起：" role="datetime" colWidth="4" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFDatePicker ename="endTime" cname="申请日期止：" role="datetime" colWidth="4" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFSelect ename="statusCode" cname="工单状态：" colWidth="3"	ratio="3:8" >
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="已审核" value="1"/>
				<EF:EFOption label="待审核" value="-1"/>
				<EF:EFOption label="驳回" value="2"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="result" title="采购申请信息管理" >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="false" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="applyBillNo" cname="申请单号" align="center" readonly="true"/>
			<EF:EFColumn ename="applyStaffId" cname="申请人" align="center" readonly="true" />
			<%--<EF:EFColumn ename="applyDeptName" cname="申请科室" readonly="true" />--%>
			<EF:EFColumn ename="recCreateTime" cname="申请日期" align="center" readonly="true"  />
			<EF:EFColumn ename="applySign" cname="申请状态" align="center" readonly="true"  />
			<EF:EFColumn ename="emo" cname="驳回理由" align="center" readonly="true"  />
			<EF:EFColumn ename="recReviseTime" cname="修改时间" align="center" readonly="true"  />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true"  width="90%" height="92%" title="采购申请登记管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true"  width="90%" height="92%" title="采购申请修改管理" modal="true" />
</EF:EFPage>