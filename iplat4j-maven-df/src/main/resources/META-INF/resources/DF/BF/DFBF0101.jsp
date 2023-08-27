<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="">
	<EF:EFRegion id="dfda" title="设备信息">
		<div class="row">
			<EF:EFInput ename="id" cname="主键" type="hidden" />
			<EF:EFInput ename="type" cname="type"  type="hidden"/>
			<EF:EFPopupInput ename="machineName" cname="设备"  colWidth="4" ratio="3:8"
							 popupTitle="设备选择" required="true" readonly="true"
							 popupType="ServiceGrid" serviceName="DFBF01" methodName="queryProject" resultId="dept"
							 valueField="machineCode" textField="machineName"
							 columnEnames="machineCode,machineName" columnCnames="设备编码,设备名称" />
			<EF:EFInput ename="fixedPlace" cname="安装地点" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="scrapDate" cname="报废日期" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>
		<div class="row">
<%--			<EF:EFSelect ename="scrapWay" colWidth="4" ratio="3:8" cname="报废方式" >--%>
<%--				<EF:EFOption label="到期报废" value="到期报废"/>--%>
<%--				<EF:EFOption label="不到期报废" value="不到期报废"/>--%>
<%--			</EF:EFSelect>--%>

			<EF:EFSelect ename="scrapWay" cname="报废方式" colWidth="4" ratio="3:8"  type="text" >
				<EF:EFOption label="请选择" value=""/>
				<EF:EFCodeOption codeName="wilp.df.scrapWay"/>
			</EF:EFSelect>
			<EF:EFInput ename="scrapReason" cname="报废原因"  type="textarea" colWidth="4" ratio="3:8"/>
		</div>
	</EF:EFRegion>


</EF:EFPage>