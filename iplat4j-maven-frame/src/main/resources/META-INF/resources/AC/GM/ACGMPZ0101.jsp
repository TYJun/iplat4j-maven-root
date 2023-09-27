<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="新增信息">
		<div class = row>
			<EF:EFSelect cname="物资类别名称" ename="materialCategory"
						 template="#=valueField#-#=textField#" filter="contains"
						 serviceName="ACGMPZ0101" methodName="queryMaterialCategory"
						 resultId="materialCategory" required="true"
						 valueField="mat_class_code" textField="mat_class_name">
			</EF:EFSelect>

			<EF:EFSelect cname="群组名称" ename="Permission"
						 template="#=valueField#-#=textField#" filter="contains"
						 serviceName="ACGMPZ0101" methodName="queryPermissionGroup"
						 resultId="Permission" required="true"
						 valueField="RESOURCE_GROUP_ENAME" textField="RESOURCE_GROUP_CNAME">
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv" style="float: right">
			<EF:EFButton cname="保存" ename="SAVE" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>


