<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>调派申请管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-billNo" cname="申请单号" colWidth="3" ratio="3:8"/>
			<EF:EFDatePicker ename="inqu_status-0-createDateS" cname="申请时间起" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-createDateE" cname="申请时间止" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-arriveTimeS" cname="到岗时间起" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" />
			<EF:EFDatePicker ename="inqu_status-0-arriveTimeE" cname="到岗时间止" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="申请科室" colWidth="3" ratio="3:8"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" colWidth="3" ratio="3:8">
				<EF:EFOption label="请选择" value=""/>
				<EF:EFOption label="新建" value="01"/>
				<EF:EFOption label="驳回" value="04"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="billNo" cname="调派申请单号" width="100" align="center" enable="false" displayType="url"/>
			<EF:EFColumn ename="deptNum" cname="申请科室" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="createDate" cname="申请日期" width="150" align="center" enable="false"/>
			<EF:EFColumn ename="arriveTime" cname="到岗日期" width="150" align="center" enable="false"/>
		<%--	<EF:EFColumn ename="shiftTimeSection" cname="支援时段" width="150" align="center" enable="false"/>--%>
			<EF:EFColumn ename="numbers" cname="支援人数" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="changeCode" cname="支援类别" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="auditOpinion" cname="驳回理由" width="100" align="center" enable="false"/>
			<EF:EFComboColumn ename="statusCode" cname="状态" align="center" enable="false">
				<EF:EFCodeOption codeName="wilp.hr.dispatchCode"/>
			</EF:EFComboColumn>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="新增" url=" " lazyload="true" width="87%" height="80%"></EF:EFWindow>
<EF:EFWindow id="popDataModify" title="编辑" url=" " lazyload="true" width="87%" height="80%"></EF:EFWindow>