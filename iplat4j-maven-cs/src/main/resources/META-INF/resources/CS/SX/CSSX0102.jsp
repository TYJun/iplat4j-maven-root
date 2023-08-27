<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保洁事项修改">
	<EF:EFRegion id="EDIT">
		<div class="row">
			<EF:EFInput ename="moduleId" cname="事项分类id" colWidth="5"
				type="hidden" />
			<EF:EFInput ename="itemId" cname="事项主键" colWidth="5" type="hidden" />
			<EF:EFInput ename="itemCode" cname="事项编码" colWidth="5" type="hidden" />
			<EF:EFInput ename="itemName" cname="事项名称" colWidth="5" type="text"
				required="true" />
			<EF:EFPopupInput ename="serDeptName" cname="服务科室" colWidth="5"
				popupHeight="575" popupWidth="550" popupTitle="科室选择" required="true"
				readOnly="true" popupType="ServiceGrid" serviceName="CSRE01"
				methodName="queryServerDept" resultId="dept" valueField="deptNum"
				textField="deptName" columnEnames="deptNum,deptName"
				columnCnames="科室编码,科室名称" />
			<EF:EFInput ename="comments" cname="备注" colWidth="5" type="text" />
		</div>
	</EF:EFRegion>
</EF:EFPage>
