<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>直入直出</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="直入直出信息">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称" required="true"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="CIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="直入直出物资信息">
		<EF:EFGrid blockId="result" autoDraw="no"  height="400" checkMode="row">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center" required="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价" width="100" align="center"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价" width="100" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>