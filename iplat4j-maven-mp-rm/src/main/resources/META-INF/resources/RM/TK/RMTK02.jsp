<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--退库管理-->
<title>物资退库</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="申请科室"/>
			<EF:EFInput ename="inqu_status-0-backOutNo" cname="退库申请单号" />
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.rm.backOut.status"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="申请日期起(>=)"></EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="申请日期止(<=)"></EF:EFDatePicker>
			<EF:EFInput ename="inqu_status-0-backOutId" cname="退库单ID" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="退库单列表">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true" height="250">
			<EF:EFColumn ename="id" cname="id" hidden="true" />
			<EF:EFColumn ename="backOutNo" cname="退库申请单号" align="center" displayType="url"/>
			<EF:EFColumn ename="deptName" cname="科室名称" align="center" />
			<EF:EFColumn ename="costDeptName" cname="成本科室" align="center" />
			<EF:EFColumn ename="backOutNum" cname="退库总数量" align="center" />
			<EF:EFColumn ename="statusName" cname="状态" align="center" />
			<EF:EFColumn ename="backReason" cname="退库原因" align="center" />
			<EF:EFColumn ename="recCreateTimeStr" cname="申请时间" align="center" />
			<EF:EFColumn ename="recCreatorName" cname="申请人" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="detail" title="退库明细列表">
		<EF:EFGrid blockId="detail" autoDraw="no" autoBind="true" checkMode="row" queryMethod="queryDetail">
			<EF:EFColumn ename="claimNo" cname="领用单号" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false"/>--%>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="参考单价(元)" align="center" enable="false"/>
			<EF:EFColumn ename="actualClaimNum" cname="实际领用数量" align="center" enable="false"/>
			<EF:EFColumn ename="num" cname="退库数量" align="center"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="92%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/RM/common/rm-grid.js"></script>
<script type="text/javascript" src="${ctx}/RM/common/rm-keydown.js"></script>