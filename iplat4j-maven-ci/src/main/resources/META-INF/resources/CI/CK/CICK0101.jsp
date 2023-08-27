<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>出库信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="出库信息">
		<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
			resultId="result" textField="wareHouseName"
			valueField="wareHouseNo" serviceName="CIWH01"
			methodName="queryWareHouse" optionLabel="请选择" required="true" >
		</EF:EFSelect>
		<EF:EFPopupInput ename="inqu_status-0-userDeptNum" cname="领用科室" 
			popupTitle="科室选择" required="true" readOnly="true"
			popupType="ServiceGrid" serviceName="CIRK01" methodName="queryDept"
			resultId="dept" valueField="deptNum" textField="deptName"
			columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
	</EF:EFRegion>
	<EF:EFRegion id="result" title="出库物资信息">
		<EF:EFGrid blockId="result" autoDraw="no" height="400" checkMode="row">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="totalNum" cname="当前数量" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="outNum" cname="出库数量" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>