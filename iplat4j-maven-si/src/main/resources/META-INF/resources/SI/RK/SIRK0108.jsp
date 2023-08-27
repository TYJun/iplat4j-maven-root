<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>红冲入库</title>--%>
<EF:EFPage>
	<div class="row">
		<div class="col-md-6">
			<EF:EFRegion id="inqu" title="物资选择" showClear="true">
				<div class="row" style="height: 40px; line-height: 40px">
					<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" type="hidden"/>
					<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" />
					<EF:EFInput ename="inqu_status-0-matName" cname="物资名称" />
					<EF:EFSelect resultId="matType" ename="inqu_status-0-matTypeNum" cname="物资分类" optionLabel="请选择"
								 serviceName="SITY02" methodName="selectMatType" filter="contains"
								 textField="matClassName" valueField="matClassCode">
					</EF:EFSelect>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="mat" title="物资列表">
				<EF:EFGrid blockId="mat" autoDraw="no" checkMode="row"
						   readonly="true" sort="single" queryMethod="queryOriginEnter" >
					<EF:EFColumn ename="matNum" cname="物资编码" width="90" align="center" sort="true"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="120" align="center" sort="false"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="false"/>
					<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>--%>
					<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" align="center" sort="false"/>
					<EF:EFColumn ename="unitName" cname="单位" width="60" align="center" sort="false"/>
					<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center" sort="false"/>
					<EF:EFColumn ename="enterNum" cname="入库数量" width="80" align="center" sort="false"/>
					<EF:EFColumn ename="totalRedRushNum" cname="已红冲数量" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" sort="false"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类" width="100" align="center" sort="false"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>

		<div class = "col-md-6">
			<EF:EFRegion id="redRush" title="入库信息">
				<div class="row">
					<EF:EFInput ename="redRush-0-enterBillNo" cname="入库单号" readonly="true"/>
					<EF:EFInput ename="redRush-0-enterTypeName" cname="入库类别" readonly="true"/>
					<EF:EFInput ename="redRush-0-wareHouseNo" cname="仓库号" type="hidden"/>
					<EF:EFInput ename="redRush-0-wareHouseName" cname="仓库名称" readonly="true"/>
					<EF:EFInput ename="redRush-0-supplierNum" cname="供应商编码" type="hidden"/>
					<EF:EFInput ename="redRush-0-supplierName" cname="供应商名称" readonly="true"/>
					<EF:EFInput ename="redRush-0-userDeptNum" cname="直入直出科室" type="hidden"/>
					<EF:EFInput ename="redRush-0-userDeptName" cname="直入直出科室" type="hidden"/>
					<EF:EFInput ename="redRush-0-remark" cname="红冲原因" type="textarea" placeholder="不能超过200字" />
					<EF:EFInput ename="type" cname="操作类型" type="hidden"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="退库信息">
				<EF:EFGrid blockId="result" autoDraw="no" autoBind="false"  checkMode="row" >
					<EF:EFColumn ename="matNum" cname="物资编码" width="90" enable="false" align="center"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="120" enable="false" align="center"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" enable="false" align="center"/>
					<%--	<EF:EFColumn ename="matModel" cname="物资型号" width="100" enable="false" align="center"/>--%>
					<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
					<EF:EFColumn ename="unitName" cname="单位" width="50" enable="false" align="center"/>
					<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="90" enable="false" align="center"/>
					<EF:EFColumn ename="redRushNum" cname="红冲数量" width="80" align="center" />
					<EF:EFColumn ename="enterNum" cname="入库数量" width="80" enable="false" align="center"/>
					<EF:EFColumn ename="totalRedRushNum" cname="已红冲数量" width="100" enable="false" align="center"/>
					<%--<EF:EFColumn ename="enterAmount" cname="红冲总价(元)" width="100" enable="false" align="center"/>--%>
					<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" enable="false" align="center"/>
					<EF:EFColumn ename="tempNum" cname="临时数据,值为初始红冲数量" hidden="true" />
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>