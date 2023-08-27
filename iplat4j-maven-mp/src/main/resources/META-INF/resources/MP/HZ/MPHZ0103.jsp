<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--领用单录入-->
<EF:EFPage>
	<div class="row">
		<div class="col-md-6">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFCascadeSelect ename="inqu_status-0-deptNum" cname="科室" resultId="deptNum" colWidth="5" required="true"
								 serviceName="MPHZ0103" methodName="queryDeptConfig" textField="deptName" valueField="deptNum">
						<EF:EFOption label="请选择" value=""/>
					</EF:EFCascadeSelect>

					<EF:EFCascadeSelect ename="inqu_status-0-matTypeNum" cname="物资分类"  cascadeFrom="inqu_status-0-deptNum"
						serviceName="MPHZ0103" methodName="queryMatType" textField="text" valueField="code" resultId="matType">
					</EF:EFCascadeSelect>

					<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0"></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="mat" title="物资信息">
				<EF:EFGrid blockId="mat" autoDraw="no" autoBind="true" checkMode="row" readonly="true">
					<EF:EFColumn ename="matNum" cname="物资编码" align="center" width="80"/>
					<EF:EFColumn ename="matName" cname="物资名称" align="center" width="120"/>
					<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" />
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" width="100"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" align="center" width="120"/>
					<EF:EFColumn ename="matModel" cname="物资型号" align="center" hidden="true"/>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" />
					<EF:EFColumn ename="unitName" cname="计量单位" align="center" width="70"/>
					<EF:EFColumn ename="price" cname="参考单价" align="center" width="80"/>
					<EF:EFColumn ename="num" cname="汇总数量" align="center" width="80"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
		<div class="col-md-6">
			<EF:EFRegion id="add_claim" title="汇总信息">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-collectDate" cname="汇总日期" readonly="true"/>
					<EF:EFInput ename="inqu_status-0-recCreatorName" cname="汇总人" readonly="true"/>
					<EF:EFInput ename="inqu_status-0-recCreator" cname="汇总人" type="hidden"/>
					<%--<EF:EFInput ename="inqu_status-0-matType" cname="物资分类" readonly="true"/>--%>
					<EF:EFInput type="hidden" ename="type" cname="操作类型" />
					<EF:EFInput type="hidden" ename="add_claim-0-id" cname="主键" />
					<EF:EFInput type="hidden" ename="inqu_status-0-collectDeptNum" cname="科室编码" readonly="true"/>
					<EF:EFInput ename="inqu_status-0-collectDeptName" cname="科室" readonly="true" required="true"/>
					<EF:EFSelect ename="inqu_status-0-purchaseType" cname="采购类别" resultId="purchaseType" optionLabel="请选择"
								 serviceName="MPPZ03" methodName="queryConfigs" required="true"
								 textField="label" valueField="value">
					</EF:EFSelect>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="汇总明细">
				<EF:EFGrid blockId="result" autoDraw="no" autoBind="false" checkMode="row">
					<EF:EFColumn ename="matNum" cname="物资编码" align="center" enable="false" width="80"/>
					<EF:EFColumn ename="matName" cname="物资名称" align="center" enable="false" width="120"/>
					<EF:EFColumn ename="matTypeId" cname="物资分类ID" hidden="true" enable="false"/>
					<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" enable="false" width="100"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" align="center" enable="false" width="120"/>
					<EF:EFColumn ename="matModel" cname="物资型号" align="center" enable="false" hidden="true"/>
					<EF:EFColumn ename="unit" cname="计量单位" hidden="true" enable="false"/>
					<EF:EFColumn ename="unitName" cname="计量单位" align="center" enable="false" width="70"/>
					<EF:EFColumn ename="price" cname="参考单价" align="center" enable="false" width="80"/>
					<EF:EFColumn ename="num" cname="汇总数量" align="center" width="80"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/MP/common/mp-gird.js"></script>
<script type="text/javascript" src="${ctx}/MP/common/mp-autoComplete.js"></script>
<script>
	$("#QUERY").click();

	window.onload  = function () {
		if (__ei.type == "edit") {
			IPLAT.EFSelect.readonly($("#inqu_status-0-deptNum"), true)
			IPLAT.EFSelect.readonly($("#inqu_status-0-matTypeNum"), true)
		}
	}
</script>




