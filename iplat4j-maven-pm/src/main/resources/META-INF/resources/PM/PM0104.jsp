<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-workNo" cname="工号"  ratio="3:9"/>
			<EF:EFInput ename="inqu_status-0-name" cname="姓名" ratio="3:9"/>
			<EF:EFInput ename="inqu_status-0-deptName" cname="所属科室" ratio="3:9"/>
			<EF:EFInput ename="inqu_status-0-personType" cname="区分执行人和知会人" type="hidden" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="multiple,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" enable="false"/>
			<EF:EFColumn ename="name" cname="姓名" width="150" enable="false"/>
			<EF:EFColumn ename="phone" cname="联系电话" width="100" enable="false"/>
			<EF:EFColumn ename="deptNum" cname="科室编码" width="150" hidden="true" enable="false"/>
			<EF:EFColumn ename="deptName" cname="科室名称" width="150" enable="false"/>
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
		$("#SUREPERSON").on("click", function() {
			var checkRows = resultGrid.getCheckedRows();
			var eiInfo = new EiInfo();
			if (checkRows.length > 0) {
				var personType = $("[name = 'inqu_status-0-personType']").val();
				window.parent.addRows(personType,checkRows);
				window.parent["personChooseWindow"].close();
			} else {
				IPLAT.NotificationUtil("请选择人员","failure");
			}
		});

		//取消
		$("#CANCEL").on("click", function() {
			window.parent["personChooseWindow"].close();
		});
	})
	


</script>

