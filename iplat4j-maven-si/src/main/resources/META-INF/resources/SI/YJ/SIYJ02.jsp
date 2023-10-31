<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>库存预警查看</title>--%>
<title>库存预警</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择" >
			</EF:EFSelect>
			<EF:EFTreeInput ename="inqu_status-0-matTypeNum" cname="物资分类" 
				serviceName="SIKC01" methodName="getMateriaType" textField="text"
				valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
				popupTitile="上级分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库库存预警信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row"
			autoBind="true" readonly="true" sort="single">
			<EF:EFColumn ename="id" cname="id" width="100"  hidden="true" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库名称" width="150" align="center" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="60" align="center" sort="false"/>
			<EF:EFColumn ename="totalNum" cname="库存量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="minNum" cname="库存下限" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="maxNum" cname="库存上限" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="recCreator" cname="创建人" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="生成需求计划" url=" " lazyload="true" width="85%" height="80%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>