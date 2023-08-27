<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息新增</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="新增">
			<EF:EFPopupInput ename="serviceDeptName" cname="服务部门" colWidth="3" ratio="3:8"
				popupType="ServiceGrid" popupTitle="科室" serviceName="HRPZ01"
				methodName="queryDept" resultId="dept" autofit="true" readonly="true" required="true"
				valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称"
			/>
			<EF:EFInput ename="surpName" cname="物业公司" colWidth="3" required="true" ratio="3:8"/>
			<EF:EFInput ename="position" cname="岗位" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="peopleLines" cname="招标配额" colWidth="3" required="true" ratio="3:8"/>
			<EF:EFInput ename="peopleLinesStable" cname="稳定配额" colWidth="3" required="true" ratio="3:8"/>
			<EF:EFInput ename="memo" cname="备注" colWidth="3" ratio="3:8"/>
	</EF:EFRegion>

</EF:EFPage>
<EF:EFWindow id="popData1" title="" url=" " lazyload="true" width="45%" height="50%"></EF:EFWindow>