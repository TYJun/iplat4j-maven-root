<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="宿舍入住申请">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">
					<EF:EFInput ename="employeeId" cname="工号" />
					<EF:EFInput ename="manName" cname="姓名" />
					<EF:EFInput ename="departmentName" cname="科室部门" />
					<EF:EFSelect ename="employmentNature" cname="员工类型" >
				    <EF:EFOption label="请先择员工类型" value=""/>    
					<EF:EFOption label="本院职工" value="本院职工"/>     
				    <EF:EFOption label="外协单位员工" value="外协单位员工"/>
				    <EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
				    <EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
				    <EF:EFOption label="进修医生" value="进修医生"/>
				    <EF:EFOption label="进修护士" value="进修护士"/>
				    <EF:EFOption label="实习医生" value="实习医生"/>
				    <EF:EFOption label="实习护士" value="实习护士"/>
				    <EF:EFOption label="研究生" value="研究生"/>
				    <EF:EFOption label="临时医技人员" value="临时医技人员"/>
				    <EF:EFOption label="规范会培训生" value="规范会培训生"/>
				    <EF:EFOption label="院聘临时工" value="院聘临时工"/>
				    <EF:EFOption label="科聘临时工" value="科聘临时工"/>
    				</EF:EFSelect>
    				<EF:EFSelect ename="ifReview" cname="审批状态"  >
						<EF:EFOption label="请先择审批状态" value=""/>    
					    <EF:EFOption label="待审核" value="-2"/>     
					    <EF:EFOption label="待分配" value="1"/>
					    <EF:EFOption label="不通过" value="-3"/>
					    <EF:EFOption label="已分配" value="2"/>
    				</EF:EFSelect>
					<%-- <EF:EFInput ename="queryDept" cname="状态" /> --%>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="申请信息" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMHM01">
					<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"   />
					<EF:EFColumn ename="employeeId" cname="工号 " readonly="true" width="80" />
					<EF:EFColumn ename="manName" cname="姓名 " readonly="true" width="70" />
					<EF:EFColumn ename="sexCode" cname="性别 " readonly="true" width="50" />
					<EF:EFColumn ename="age" cname="年龄 " readonly="true"  width="50" />
					<EF:EFColumn ename="degreeCode" cname="学历 " readonly="true"  width="60" />
					<EF:EFColumn ename="deptNum" cname="科室部门编号 " readonly="true"  width="100" typ="hidden" />
					<EF:EFColumn ename="departmentName" cname="科室部门 " readonly="true"  width="100" />
					<EF:EFColumn ename="employmentNature" cname="员工类型 " readonly="true" width="80" />
					<EF:EFColumn ename="phone" cname="联系电话 " readonly="true" width="100" />
					<EF:EFColumn ename="workTime" cname="入职时间 " readonly="true" width="80" />
					<%-- <EF:EFColumn ename="isBatch" cname="是否批量" readonly="true"/> --%>
					<EF:EFColumn ename="ifReview" cname="审批状态" readonly="true"  width="80"  />
					<EF:EFColumn ename="note" cname="申请理由 " readonly="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="60%" height="90%" title="新增" modal="true" />
			<EF:EFWindow id="popDataModify" url="" lazyload="true" width="60%" height="90%" title="修改" modal="true" />
		</div>
</EF:EFPage>