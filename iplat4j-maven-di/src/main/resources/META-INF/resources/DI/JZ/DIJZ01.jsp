<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="巡检基准管理">
			<EF:EFRegion id="inqu" title="查询区">
				<div class="row">
					<EF:EFInput ename="schemeCode" cname="基准代码" />
					<EF:EFInput ename="schemeName" cname="基准名称" />
					<EF:EFInput ename="schemeDept" cname="单位部门" />
				</div>
				<div class="row">
					<EF:EFInput ename="machineCode" cname="设备编码" />
					<EF:EFInput ename="machineName" cname="设备名称" />
					<EF:EFSelect cname="状态:" ename="status" >
						<EF:EFOption label="全部" value="" />
						<EF:EFOption label="新建" value="0" />
						<EF:EFOption label="启用" value="1" />
						<EF:EFOption label="禁用" value="-1" />
					</EF:EFSelect>
				</div>
				<div class="row">
					<EF:EFInput ename="fixedPlace" cname="地点" />

					<EF:EFSelect ename="deptName" cname="科室"
								 resultId="dept" valueField="deptName" textField="deptName"
								 serviceName="DIZH01" methodName="queryDept"
								 optionLabel="请选择" filter="contains" />
				</div>
				
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DIJZ01">
					<EF:EFColumn ename="schemeID" cname="主键" hidden="true" align="center" width="100"/>
					<EF:EFColumn ename="schemeCode" cname="基准代码" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="schemeName" cname="基准名称" readonly="true" align="center" width="150"/>
					<EF:EFColumn ename="machineCode" cname="设备编码" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="machineName" cname="设备名称" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="fixedPlace" cname="地点" readonly="true" align="center" width="150"/> 
					<EF:EFColumn ename="status" cname="状态" readonly="true" align="center" width="40" hidden="true"/>
					<EF:EFColumn ename="statusName" cname="状态" readonly="true" align="center" width="40"/>
					<EF:EFColumn ename="createTime" cname="创建时间" readonly="true" align="center" width="80"/>
					<EF:EFColumn ename="createMan" cname="创建人" readonly="true" align="center" width="80"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="95%" height="90%" title="巡检基准新增" modal="true" />
			<EF:EFWindow id="popDataModify" url="" lazyload="true" width="95%" height="90%" title="巡检基准修改" modal="true" />
		</div>
</EF:EFPage>