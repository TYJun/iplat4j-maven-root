<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍满意度评价</title>
<EF:EFPage title="宿舍满意度评价">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manNo" cname="工号" />
			<EF:EFInput ename="manName" cname="姓名" />
			<EF:EFSelect ename="employmentNature" cname="员工类型" >
				<EF:EFOption label="请选择员工类型" value=""/>
				<EF:EFOption label="本院职工" value="本院职工"/>
				<EF:EFOption label="全日制学生" value="全日制学生"/>
				<EF:EFOption label="政策类研究生" value="政策类研究生"/>
				<EF:EFOption label="外协单位员工" value="外协单位员工"/>
				<EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
				<EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
				<EF:EFOption label="进修医生" value="进修医生"/>
				<EF:EFOption label="进修护士" value="进修护士"/>
				<EF:EFOption label="实习医生" value="实习医生"/>
				<EF:EFOption label="实习护士" value="实习护士"/>
				<EF:EFOption label="临时医技人员" value="临时医技人员"/>
				<EF:EFOption label="规范会培训生" value="规范会培训生"/>
				<EF:EFOption label="院聘临时工" value="院聘临时工"/>
				<EF:EFOption label="科聘临时工" value="科聘临时工"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="deptName" cname="科室部门" />
			<EF:EFSelect ename="evalStatus" cname="评价状态" >
				<EF:EFOption label="请选择评价状态" value=""/>
				<EF:EFOption label="未评价" value="0"/>
				<EF:EFOption label="已评价" value="1"/>
			</EF:EFSelect>
			<EF:EFSelect ename="typeCode" cname="房间类型" >
				<EF:EFOption label="请选择房间类型" value=""/>
				<EF:EFOption label="男生宿舍" value="1"/>
				<EF:EFOption label="女生宿舍" value="0"/>
			</EF:EFSelect>
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
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMPJ01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manId" cname="manId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="roomId" cname="roomId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="deptName" cname="科室部门" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="building" cname="楼" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="floor" cname="层" readonly="true" width="70" align="center" />
			<EF:EFColumn ename="roomNo" cname="宿舍号" readonly="true" width="70" align="center"/>
			<EF:EFColumn ename="bedNo" cname="床位号" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="rent" cname="房租" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="manageFee" cname="管理费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="actualRent" cname="实际房租" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="actualManageFee" cname="实际管理费" readonly="true" width="60" align="center" />
			<EF:EFColumn ename="evalStatusMeaning" cname="评价状态" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="statusCode" cname="状态"  hidden="true" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="evalStatus" cname="评价状态"  hidden="true" readonly="true" width="80" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="90%" height="90%" title="满意度评价" modal="true" />
	<EF:EFWindow id="popDataHistoryShow" url="" lazyload="true" width="90%" height="90%" title="宿舍满意度评价历史" modal="true" />
</EF:EFPage>