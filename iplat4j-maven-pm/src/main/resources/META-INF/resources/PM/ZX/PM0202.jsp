<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-matNum" cname="材料编码"  ratio="3:9"/>
			<EF:EFInput ename="inqu_status-0-matName" cname="材料名称" ratio="3:9"/>
			<EF:EFInput ename="inqu_status-0-stuffType" cname="区分甲供材料和乙供材料" type="hidden" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="matTypeNum" cname="材料分类编号" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="matTypeName" cname="材料分类名称" width="150" hidden="true" enable="false"/>
			<EF:EFColumn ename="matNum" cname="材料编码" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="matName" cname="材料名称" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="spec" cname="规格" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="unitNum" cname="单位" width="80" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="unitName" cname="单位名称" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="price" cname="价格" width="80" enable="false" align="center"/>
			<EF:EFColumn ename="num" cname="数量" width="80" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="totalPrice" cname="总价" width="80" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="supType" cname="供应方" width="150" enable="false" align="center"/>
			<EF:EFColumn ename="fashion" cname="方式" width="80" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="remark" cname="备注" width="80" hidden="true" enable="false" align="center"/>
			<EF:EFColumn ename="brand" cname="品牌" width="80" hidden="true" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.page(1);
		});

		//确定
		$("#SURESTUFF").on("click", function() {
			debugger
			var checkRows = resultGrid.getCheckedRows();
			var eiInfo = new EiInfo();
			if (checkRows.length > 0) {
				var stuffType = $("[name = 'inqu_status-0-stuffType']").val();
				//checkRows[0]["supType"] = stuffType == "JSTUFF" ? "1" : "2";
				window.parent.addRows(stuffType,checkRows);
				window.parent["stuffChooseWindow"].close();
			} else {
				IPLAT.NotificationUtil("请选择材料");
			}
		});

		//取消
		$("#CANCEL").on("click", function() {
			window.parent["stuffChooseWindow"].close();
		});
	})
	


</script>

