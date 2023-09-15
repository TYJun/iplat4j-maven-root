<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息新增</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="新增">
		<div class="row">
			<EF:EFInput ename="id" cname="id" type="hidden"/>
			<EF:EFInput ename="type" cname="类型" type="hidden"/>
			<EF:EFInput ename="billNo" cname="单号" type="hidden"/>
			<EF:EFDatePicker ename="billTime" cname="申请日期" role="datetime" colWidth="3" ratio="3:8"
							 format="yyyy-MM-dd HH:mm:ss" required="true"/>
			<EF:EFDatePicker ename="arriveTime" cname="到岗日期" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>
			<EF:EFDatePicker ename="leaveTime" cname="离岗日期" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 readonly="true" required="true"/>
			<EF:EFPopupInput ename="deptNum" cname="申请科室" colWidth="3" ratio="3:8"
							 popupType="ServiceGrid" popupTitle="科室" serviceName="HRPZ01"
							 methodName="queryDept" resultId="dept" autofit="true" readonly="true" required="true"
							 valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName"
							 columnCnames="科室编码,科室名称"
			/>
			<%--<EF:EFInput ename="shiftTimeSection" cname="支援时段" colWidth="3" required="true" ratio="3:8"/>--%>
			<EF:EFSelect ename="changeCode" cname="支援类别" colWidth="3" ratio="3:8">
				<EF:EFCodeOption codeName="wilp.hr.typeCode"/>
			</EF:EFSelect>
			<EF:EFInput ename="numbers" cname="支援人数" colWidth="3" required="true" ratio="3:8"/>
			<%--<EF:EFDatePicker ename="shiftFirstTime" cname="上班时间" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd "
							 parseFormats="['yyyy-mm-dd HH:mm:ss']" readonly="true" required="true"/>--%>

			<EF:EFInput ename="supportStation" cname="支援岗位" colWidth="3" required="true" ratio="3:8"/>
			<EF:EFInput ename="becauseMemo" cname="申请原因" type="textarea" maxLength="400" colWidth="3" ratio="3:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="realName" cname="姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="phone" cname="电话" width="100" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<%--人员选择窗口--%>
<EF:EFWindow id="popDataPerson" title="人员选择" url=" " lazyload="true" width="90%" height="92%"></EF:EFWindow>