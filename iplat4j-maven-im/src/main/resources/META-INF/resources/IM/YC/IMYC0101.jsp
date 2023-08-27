<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.padding20 {
	padding: 10px 0;
}
</style>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="基本内容">
	  <EF:EFInput ename="type" cname="" colWidth="4" ratio="3:8" type="hidden"/>
	  <EF:EFInput ename="taskCode" cname="" colWidth="4" ratio="3:8" type="hidden"/>
		<div class="col-md-12">
			<div class="row" style="height: 10px;">
				<EF:EFInput colWidth="12" ratio="4:12" type="textarea"
					ename="exceptionResult" cname="异常处理:" ></EF:EFInput>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">

$(function() {
	var s = $("#type").val();
	if (s === "look") {
		$("#INS").css("display", "none");
	}


	
})

</script>