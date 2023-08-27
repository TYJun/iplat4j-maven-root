<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍退宿审核</title>
<EF:EFPage title="宿舍退宿审核">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manNo" cname="工号" />
			<EF:EFInput ename="manName" cname="姓名" />
			<EF:EFInput ename="deptName" cname="科室部门" />
		</div>
		<div class="row">
			<EF:EFInput ename="building" cname="宿舍楼" />
			<EF:EFInput ename="floor" cname="宿舍层" />
			<EF:EFInput ename="roomNo" cname="房间号" />
		</div>
		<div class="row">
			<div id="hiddenDiv">
				<EF:EFSelect ename="manNature" cname="人员大类" defaultValue="职工">
					<EF:EFOption label="全部" value=""/>
					<EF:EFOption label="学生" value="学生"/>
					<EF:EFOption label="职工" value="职工"/>
				</EF:EFSelect>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="相关信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMTS02" rowNo="true" checkMode="mutiple,row">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manId" cname="manId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="roomId" cname="roomId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="deptName" cname="科室部门" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="building" cname="楼" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="floor" cname="层" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="roomNo" cname="宿舍号" readonly="true" width="60" align="center"/>
			<EF:EFColumn ename="bedNo" cname="床位号" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="actualInDate" cname="实际入住时间" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="outRoomNote" cname="退宿备注" readonly="true" width="150"/>
			<EF:EFColumn ename="applyOutDate" cname="申请时间" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="checkoutRoomStatusMean" cname="检查清单" readonly="true" width="60" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="90%"
				 height="90%" title="检查清单" />
	<EF:EFWindow id="popDataSettle" url="" lazyload="true" width="60%"
				 height="60%" title="结算退宿" />
	<EF:EFWindow id="popDatashows" url="" lazyload="true" width="60%" height="90%" title="房间物资信息查看" modal="true" />
</EF:EFPage>