<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<title>宿舍入住申请</title>
<EF:EFPage title="宿舍入住申请">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manNo" cname="工号" />
			<EF:EFInput ename="manName" cname="姓名" />
			<EF:EFInput ename="deptName" cname="科室部门" />
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
			<EF:EFSelect ename="statusCode" cname="状态"  >
				<EF:EFOption label="请选择状态" value=""/>
				<EF:EFOption label="待审核" value="00"/>
				<EF:EFOption label="待分配" value="01"/>
				<EF:EFOption label="待选房" value="02"/>
				<EF:EFOption label="待入住" value="03"/>
				<EF:EFOption label="已入住" value="04"/>
				<EF:EFOption label="申请换宿" value="10"/>
				<EF:EFOption label="申请退宿" value="98"/>
				<EF:EFOption label="流程结束" value="99"/>
			</EF:EFSelect>
			<EF:EFSelect ename="archiveFlag" cname="归档状态"  >
				<EF:EFOption label="请选择状态" value=""/>
				<EF:EFOption label="未归档" value="0"/>
				<EF:EFOption label="已归档" value="1"/>
			</EF:EFSelect>
			<div id="hiddenDiv">
				<EF:EFSelect ename="manNature" cname="人员大类" defaultValue="学生">
					<EF:EFOption label="全部" value=""/>
					<EF:EFOption label="学生" value="学生"/>
					<EF:EFOption label="职工" value="职工"/>
				</EF:EFSelect>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="申请信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMXSRZ01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"   />
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="80" align="center" locked="true"/>
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" locked="true"/>
			<EF:EFColumn ename="sexMeaning" cname="性别" readonly="true" width="50" align="center" locked="true"/>
			<EF:EFColumn ename="age" cname="年龄" readonly="true"  width="50" align="center"/>
			<EF:EFColumn ename="phone" cname="联系电话" readonly="true" width="100" align="center"/>
			<EF:EFColumn ename="identityCard" cname="身份证号" readonly="true" width="100" align="center"/>
			<EF:EFColumn ename="deptName" cname="科室部门" readonly="true"  width="100" align="center"/>
			<EF:EFColumn ename="employmentNature" cname="人员属性" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="school" cname="所属学校" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="major" cname="专业" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="dormitoryDirector" cname="是否宿舍长" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="address" cname="申请房间地址" readonly="true" width="80" align="center"/>
			<EF:EFColumn ename="note" cname="备注信息" readonly="true"/>
			<EF:EFColumn ename="statusCodeMeaning" cname="状态" readonly="true"  width="80"  align="center"/>
			<EF:EFColumn ename="archiveFlagMean" cname="归档状态" readonly="true"  width="80"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="70%" title="入住申请" modal="true" />
	<EF:EFWindow id="popDataModify" url="" lazyload="true" width="40%" height="60%" title="信息编辑" modal="true" />
	<EF:EFWindow id="popDatashow" url="" lazyload="true" width="40%" height="60%" title="入住信息详情" modal="true" />
</EF:EFPage>