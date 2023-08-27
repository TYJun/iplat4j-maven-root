<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	 <EF:EFRegion id="result" title="查看参数" fitHeight="true" >


<%--			<EF:EFInput ename="parameter" cname="参数" type="textarea" rows="10" colWidth="10" ratio="2:10" readonly="true" />--%>
			<EF:EFInput ename="parameter" cname="参数" type="hidden" rows="10" colWidth="10" ratio="2:10" readonly="true" />
		 <div id="eiInfo"></div>


	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

$(function() {
	var parameter = IPLAT.EFInput.value(('#parameter'));
	parameter = parameter.replaceAll('\'','"');
	let parse = JSON.parse(parameter);
	var eiInfo = parse[0];
	var WindowUtil = window.WindowUtil /*|| console.log*/;

	IPLAT.Util.visualizeEiInfo(eiInfo, '#eiInfo');


	$("#eiInfo").on("click", ".i-click", function (e) {
		var data = e.target.innerHTML;
		var value = JSON.stringify(eval("(" + data + ")"), null, 4);
		// $("<div id='" + "aaa" + "'><pre>" + value + "</pre></div>").appendTo($("#b").kendoWindow());
		showJSON(value);
		return false;
	});


	function showJSON(json) {
		WindowUtil({
			title: "展示信息",
			content: "<div style='max-height:500px;min-width:100px;'><pre>" + json + "</pre></div>",
			// height:'80%',
			width: '80%'
		});
	}
});
</script>
