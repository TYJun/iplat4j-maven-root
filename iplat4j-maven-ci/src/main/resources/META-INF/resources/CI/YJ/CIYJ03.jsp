<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>商品保质期预警</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="CIWH01" methodName="queryWareHouse" optionLabel="请选择" >
			</EF:EFSelect>
			<EF:EFTreeInput ename="inqu_status-0-matTypeNum" cname="物资分类" 
				serviceName="CIKC01" methodName="getMateriaType" textField="text"
				valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
				popupTitile="上级分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="商品保质期预警信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100"  hidden="true" align="center"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库名称" width="100" align="center"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="100" align="center"/>
			<EF:EFColumn ename="batchNo" cname="批次号" width="100" align="center"/>
			<EF:EFColumn ename="enterNum" cname="剩余数量" width="100" align="center"/>
			<EF:EFColumn ename="recCreateTime" cname="入库时间" width="100" align="center"/>
			<EF:EFColumn ename="expirationDate" cname="过期时间" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script>

	$(function(){
		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.page(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.page(1);
		});

	})
</script>