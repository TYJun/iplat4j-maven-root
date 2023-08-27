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
		<div class="rowId" id="idName">
			<EF:EFInput ename="id" cname="id" colWidth="6" ratio="4:6" readonly="true" />
		</div>
		<div class="col-md-12">
			<div class="row" style="height: 10px;">
				<EF:EFInput ename="dangercode" cname="问题编码:" colWidth="6"
					ratio="2:8" readonly="true" />
				<EF:EFInput colWidth="6" ratio="2:8" type="textarea"
							ename="rejectreason" cname="挂账原因:" ></EF:EFInput>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
//隐藏id输入框
$("#idName").hide();
</script>
