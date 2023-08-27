<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>直入直出信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="直入直出信息">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库" optionLabel="请选择仓库" required="true"
						 filter="contains" resultId="wareHouse" serviceName="MPJK02" methodName="dockWareHouse"
						 textField="wareHouseName" valueField="wareHouseNo">
			</EF:EFSelect>
			<EF:EFPopupInput ename="inqu_status-0-deptNum" cname="成本归集科室" required="true" popupTitle="成本归集科室"
					 popupType="ServiceGrid" serviceName="MPTY01" methodName="selectDeptBlock" resultId="dept"
					 valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称"
					 backFillColumnIds="deptName" backFillFieldIds="inqu_status-0-deptName"/>
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-orderNo" cname="采购订单号" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-wareHouseName" cname="仓库" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-supplierNum" cname="供应商编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商名称" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-enterType" cname="入库类型" value="01" type="hidden"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="采购入库物资信息">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="row" >
			<EF:EFColumn ename="contNo" cname="合同号" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="matTypeId" cname="物资分类编码" width="80" hidden="true" enable="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="unit" cname="计量单位" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="price" cname="单价(元)" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="num" cname="订单数量" width="80" align="center" enable="false"/>
			<EF:EFColumn ename="enterNum" cname="已入库数量" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="curEnterNum" cname="本次入库数量" width="80" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>


