<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="领用申请管理">
	<EF:EFRegion id="inqu" title="查询条件"  showClear="true">
		<div class="row">
			<EF:EFDatePicker ename="startTime" cname="申请日期起：" role="datetime" colWidth="4" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFDatePicker ename="endTime" cname="申请日期止：" role="datetime" colWidth="4" ratio="3:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFSelect ename="statusCode" cname="申请状态" optionLabel="请选择" colWidth="4" ratio="3:8" >
				<EF:EFOption label="已提交" value="1"/>
				<EF:EFOption label="新建" value="0"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="result" title="领用申请管理" >
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" readonly="false" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" hidden="true" />
			<EF:EFColumn ename="applyBillNo" cname="申请单号" align="center" readonly="true"/>
			<EF:EFColumn ename="applyStaffId" cname="申请人" align="center" readonly="true" />
			<EF:EFColumn ename="applyCanteenName" cname="申请科室" align="center" readonly="true"  hidden="true" />
			<EF:EFColumn ename="recCreateTime" cname="申请日期" align="center" readonly="true"  />
			<EF:EFColumn ename="applySign" cname="申请状态" align="center" readonly="true" />
			<EF:EFColumn ename="recReviseTime" cname="修改时间" align="center" readonly="true"  />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true"  width="90%" height="92%" title="物资申请登记管理" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true"  width="90%" height="92%" title="物资申请修改管理" modal="true" />


	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="90%" height="92%" modal="true" url="" title="查看" style="display:none;">
		<EF:EFRegion id="inqu" title="采购入库信息">
			<div class="row">
				<EF:EFPopupInput ename="canteen" cname="食堂：" colWidth="4"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="infoResult" title="物资信息">
			<EF:EFGrid blockId="infoResult" autoDraw="no"  height="400" checkMode="row">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
				<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="matName" cname="物资名称" width="200" enable="false" align="center"/>
				<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="200" enable="false" align="center"/>
				<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
				<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
				<EF:EFColumn ename="applyNum" cname="申请数量" width="100" align="center"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>