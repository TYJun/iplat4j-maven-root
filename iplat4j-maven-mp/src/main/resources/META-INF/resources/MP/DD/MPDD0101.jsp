<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 加载application配置文件 读取医院名称 -->
<fmt:setBundle basename="application" var="app" />
<fmt:message key="hospitalName" var="hospitalName" bundle="${app}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<div class="row">
		<div class="col-md-3">
			<EF:EFRegion id="R1" title="合同信息" fitHeight="true">
				<EF:EFTree id="tree01" textField="text" valueField="label"
						   hasChildren="leaf" serviceName="MPDD0101" methodName="queryContTree">
				</EF:EFTree>
			</EF:EFRegion>
		</div>
		<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
					<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
					<EF:EFInput ename="inqu_status-0-contId" cname="合同ID" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-supplierNum" cname="供应商编码" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商名称" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-contNo" cname="合同编码" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-contName" cname="合同名称" readonly="true"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="合同明细列表">
				<EF:EFGrid blockId="result" autoDraw="no" queryMethod="queryContDetail">
					<EF:EFColumn ename="contNo" cname="合同号" width="100" hidden="true"/>
					<EF:EFColumn ename="matNum" cname="物资编码" width="80"  enable="false"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="80" enable="false"/>
					<EF:EFColumn ename="matTypeId" cname="物资分类编码" width="80" hidden="true"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类" width="80" enable="false" />
					<EF:EFColumn ename="matSpec" cname="物资规格" width="80" enable="false"/>
					<EF:EFColumn ename="matModel" cname="物资型号" width="80" enable="false"/>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
					<EF:EFColumn ename="unitName" cname="计量单位" width="70" enable="false"/>
					<EF:EFColumn ename="price" cname="含税单价(元)" width="80" enable="false" />
					<EF:EFColumn ename="num" cname="合同数量" width="80" enable="false" />
					<EF:EFColumn ename="orderNum" cname="已生成订单数量" width="100" enable="false"/>
					<EF:EFColumn ename="number" cname="本次订单数量" width="80" align="center" />
					<EF:EFColumn ename="pictureUri" cname="图片" hidden="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>


