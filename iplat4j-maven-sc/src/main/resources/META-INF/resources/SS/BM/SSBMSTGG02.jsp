<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="edit" title="编辑" showClear="true">
		<div class="row">
			<EF:EFDatePicker format="yyyy/MM/dd" ename="edit_status-0-date" cname="单个的日期">
			</EF:EFDatePicker>
		</div>
	</EF:EFRegion>
	
</EF:EFPage>
<script type="text/javascript">
	function setData(e){
		debugger;
		console.log(e);
	}
</script>