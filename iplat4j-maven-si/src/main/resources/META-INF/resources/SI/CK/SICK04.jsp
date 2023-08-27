<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>出库签收</title>--%>
<title>签收物资</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-outBillNo" cname="出库单号"/>
			<%--<EF:EFInput ename="inqu_status-0-userDeptNums" cname="领用科室" readonly="true"/>--%>
			<EF:EFSelect ename="inqu_status-0-costDeptNum" cname="成本科室" resultId="userDept"
					serviceName="SITY02" methodName="selectUserBusinessDept"
		 			textField="deptName" valueField="deptNum" filter="contains" >
		 		<EF:EFOption label="全部" value=""/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单日期起"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单日期止"
				role="date" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
			<EF:EFInput ename="mainOutBillNo" type="hidden" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="出库签收单信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" height="300"
			autoBind="true" readonly="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="outBillNo" cname="出库单号" width="100" displayType="url" align="center" />
			<EF:EFColumn ename="outTypeName" cname="出库类别" width="100" align="center" />
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="userDeptName" cname="领用科室" width="100" align="center"/>
			<EF:EFColumn ename="costDeptName" cname="成本科室" width="100" align="center"/>
			<EF:EFColumn ename="totalAmount" cname="出库总金额(元)" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
		</EF:EFGrid>
		<EF:EFRegion id="detail" title="物资信息">
			<EF:EFGrid blockId="detail" autoDraw="no" checkMode="row" readonly="true" queryMethod="queryDetail">
				<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
				<%--<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>--%>
				<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
				<EF:EFColumn ename="unitName" cname="物资单位" width="80" enable="false" align="center"/>
				<EF:EFColumn ename="outNum" cname="送货数量" width="100" align="center"/>
				<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
				<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" enable="false" align="center"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="授权" url=" " lazyload="true" width="70%" height="85%"></EF:EFWindow>
<EF:EFWindow id="popData1" title="已签收记录" url=" " lazyload="true" width="80%" height="85%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-yxSign.js
"></script>