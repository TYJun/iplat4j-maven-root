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
		<div class ="row">
			<EF:EFInput ename="type" cname="" colWidth="4" ratio="3:8" type="hidden"/>
			<EF:EFInput ename="taskCode" cname="" colWidth="4" ratio="3:8" type="hidden"/>
			<EF:EFSelect ename="solutionType" cname="解决途径" required="true">
				<EF:EFOption label="全部" value="" />
				<EF:EFCodeOption codeName="wilp.di.solutionType"/>
			</EF:EFSelect>
		</div>

		<div class="row">
				<EF:EFPopupInput ename="dealMan" cname="处理人:"   colWidth="5" ratio="4:8"
								 popupTitle="管理员选择" required="false" readonly="true"
								 popupType="ServiceGrid" serviceName="DIYC0101" methodName="queryPerson" resultId="person"
								 valueField="workNo" textField="name"
								 columnEnames="workNo,name" columnCnames="人员工号,人员姓名" />
				<EF:EFDatePicker ename="finishTime" cname="处理完成时间:" role="date" colWidth="5" ratio="4:8" format="yyyy-MM-dd"
								 parseFormats="['yyyy-mm-dd']" readonly="true" required="false"/>
			    <EF:EFInput ename="exceptionResult" cname="解决措施:"  type="textarea" colWidth="5" ratio="4:8" required="true"/>
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