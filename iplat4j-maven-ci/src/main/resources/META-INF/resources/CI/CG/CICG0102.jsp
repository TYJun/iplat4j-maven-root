<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="采购入库信息">
		<%--<div class="row">
			<EF:EFPopupInput ename="dept" cname="科室：" colWidth="4"
							 popupType="ServiceGrid" popupTitle="科室" required="true" serviceName="CICG0101"
							 methodName="queryDept" resultId="dept" autofit="true"
							 valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称"
			/>

		</div>--%>
		<EF:EFInput ename="id" cname="id" width="100" type="hidden"/>
		<EF:EFInput ename="applyBillNo" cname="单号" width="100" type="hidden"/>
		<EF:EFInput ename="emo" cname="驳回理由" width="100" readOnly="true" type="hidden"/>
	</EF:EFRegion>

<EF:EFRegion id="result" title="物资信息">
	<EF:EFGrid blockId="result" autoDraw="no"  height="400" checkMode="row">
		<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
		<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
		<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
		<EF:EFColumn ename="supplier" cname="供应商编码" width="100" align="center" hidden="true"/>
		<EF:EFColumn ename="supplierName" cname="供应商" width="100" align="center"/>
		<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>
		<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
		<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
		<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
		<EF:EFColumn ename="applyNum" cname="申请数量" width="100" align="center"/>

	</EF:EFGrid>
</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>
