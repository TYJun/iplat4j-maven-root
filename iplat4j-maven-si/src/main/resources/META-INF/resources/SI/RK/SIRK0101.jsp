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
				serviceName="SIWH01" methodName="selectUseWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFPopupInput ename="inqu_status-0-deptNum" cname="领用科室" 
				popupTitle="科室选择" required="true" readOnly="true" 
				popupType="ServiceGrid" serviceName="SIRK01" methodName="queryDept" 
				resultId="dept" valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-suppilerNum" cname="供应商"
							 popupTitle="供应商选择" required="true" readOnly="true"
							 popupType="ServiceGrid" serviceName="SITY02" methodName="selectSupplier"
							 resultId="supplier" valueField="supplierCode" textField="supplierName"
							 columnEnames="supplierCode,supplierName" columnCnames="供应商编码,供应商名称" />
			<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" placeholder="不能超过200字" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="直入直出物资信息">
		<EF:EFGrid blockId="result" autoDraw="no"  height="400" checkMode="row" serviceName="TSFW01" queryMethod="loadTempData">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="200" enable="false" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" enable="false" align="center"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="200" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="物资选择" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>