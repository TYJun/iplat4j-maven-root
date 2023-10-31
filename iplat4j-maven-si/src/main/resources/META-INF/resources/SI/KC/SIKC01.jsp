<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>库存存量管理</title>--%>
<title>库存存量</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
				resultId="result" textField="wareHouseName" valueField="wareHouseNo"
				serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFTreeInput ename="inqu_status-0-matTypeNum" cname="物资分类" 
				serviceName="SIKC01" methodName="getMateriaType" textField="text"
				valueField="label" hasChildren="leaf" root="{label: 'root',text: '分类'}"
				popupTitile="上级分类" clear="true" placeholder="请选择">
			</EF:EFTreeInput>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
			<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
			<EF:EFSelect ename="inqu_status-0-isNot0" cname="是否显示0库存">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="显示" value="Y" />
				<EF:EFOption label="不显示" value="N" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-earlyWarning" cname="显示预警设置">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="显示" value="Y" />
				<EF:EFOption label="不显示" value="N" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-warningStock" cname="是否只显示告警库存">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="是" value="Y" />
				<EF:EFOption label="否" value="N" />
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-surpName" cname="供应商" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存明细列表">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" queryMethod="newQuery" sort="single">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" sort="false"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" hidden="true" sort="false"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="200" displayType="url" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="totalNum" cname="库存总量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="totalAmount" cname="库存总价" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="minNum" cname="库存下限" width="100" align="center" sort="false"/>
			<EF:EFColumn ename="maxNum" cname="库存上限" width="100" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 弹出窗口 -->
<EF:EFWindow id="popData" title="" url=" " lazyload="true" width="90%" height="85%"></EF:EFWindow>
<EF:EFWindow id="popData1" title="生成盘库单" url=" " lazyload="true" width="30%" height="42%"></EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>