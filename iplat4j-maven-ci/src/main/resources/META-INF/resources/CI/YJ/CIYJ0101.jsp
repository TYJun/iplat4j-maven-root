<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="选择仓库">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库"
				resultId="result" textField="wareHouseName"
				valueField="wareHouseNo" serviceName="CIWH01"
				methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFInput ename="type" type="hidden" cname="操作" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库预警生成">
		<EF:EFGrid blockId="result" autoDraw="no" height="500" checkMode="row">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="minNum" cname="最低库存量" width="100" align="center"/>
			<EF:EFColumn ename="maxNum" cname="最高库存量" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>