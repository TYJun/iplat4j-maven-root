<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="基准新增">
	<EF:EFRegion id="ADD">
		<div class="row">
			<EF:EFInput id="operType" ename="operType" cname="操作类型:" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput id="schemeID"  ename="schemeID" cname="基准id码:" colWidth="4" type="hidden" readonly="true"/>
			<EF:EFInput ename="schemeCode" cname="基准代码:" colWidth="4" type="text" readonly="true"/>
			<EF:EFInput ename="schemeName" cname="基准名称:" colWidth="4" type="text" required="true"/>
			
		<%--<EF:EFCascadeSelect ename="schemeDept" autoBind="true" required="true"
	        cname="巡查单位:" textField="deptName" valueField="deptNum" serviceName="DIJZ0101"
	        methodName="queryDept" resultId="dept" >
	        <EF:EFOption label="--请选择--" value=""/>
	    </EF:EFCascadeSelect> --%>
	   		 <EF:EFPopupInput ename="schemeMan" cname="管理员:" colWidth="4" ratio="4:8"
					popupTitle="管理员选择" required="true" readonly="true"
					popupType="ServiceGrid" serviceName="IMJZ0101" methodName="queryPerson" resultId="person"
					valueField="workNo" textField="name"
					columnEnames="workNo,name" columnCnames="人员工号,人员姓名" />
		   
		</div>
		<div class="row">
		<%-- <EF:EFCascadeSelect ename="schemeMan" 
	        cascadeFrom="schemeDept" filter="contains" 
	        cname="负责人:" textField="name" valueField="workNo" serviceName="IMJZ0101"
	        methodName="queryMan" resultId="man" colWidth="4" 
			ratio="4:8">
	    </EF:EFCascadeSelect> --%>
	    	 <EF:EFPopupInput ename="schemeDept" cname="管理科室:"  colWidth="4" ratio="4:8"
					popupTitle="科室选择" required="true" readonly="true"
					popupType="ServiceGrid" serviceName="IMJZ0101" methodName="queryDept" resultId="dept"
					valueField="deptNum" textField="deptName"
					columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
				
				
			<EF:EFInput ename="activeTime" cname="有效时间（小时）:" colWidth="4" type="text" 
				data-rules="positive_integer" placeholder="请输入正整数" required="true" min="0"/>
			<EF:EFSelect cname="短信提醒" ename="smsSend" >
					<EF:EFOption label="是" value="Y" />
					<EF:EFOption label="否" value="N" />
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="timeoutReminderTime" cname="超时提前提醒时间:" placeholder="请输入正整数" colWidth="4" type="text" min="0"/>
			<EF:EFInput ename="jobContent" cname="作业说明:" colWidth="4" type="text"/>
			<EF:EFWindow id="popDataDevicePackage" url="" lazyload="true" width="600" height="600" title="设备包 " modal="true" />
		</div>
	</EF:EFRegion>
		
	<EF:EFRegion title="巡查对象信息">
		<EF:EFGrid blockId="device" autoDraw="no" serviceName="IMJZ0101" showCount="false" checkMode="row" rowNo="true">
			<EF:EFColumn ename="objId" align="center" cname="主键" width="100" enable="false" hidden="true"/>
			<EF:EFColumn ename="objName" align="center" cname="对象名称"  width="100" enable="false"/>
			<EF:EFColumn ename="objRemark" align="center" cname="对象备注"  width="200" enable="false"/>
			<EF:EFColumn ename="spotCode" align="center" cname="安装地点编码" width="150" enable="false"/>
			<EF:EFColumn ename="spotName" align="center" cname="安装地点" width="400" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataDevice" url="" lazyload="true" width="50%" height="80%" title="添加巡查对象" modal="true" />
		
	<EF:EFRegion title="周期">
		<EF:EFGrid blockId="cycle" autoDraw="no" serviceName="IMJZ0101" checkMode="row" rowNo="true">
			<EF:EFColumn ename="cycle" align="center" cname="周期" enable="false"/>
			<EF:EFColumn ename="unit" align="center" cname="单位" enable="false" hidden="true"/>
			<EF:EFColumn ename="unitName" align="center" cname="单位" enable="false" />
			<EF:EFColumn ename="holiday" align="center" cname="是否跳过假期" enable="false"  hidden="true"/>
			<EF:EFColumn ename="holidayName" align="center" cname="是否跳过假期" enable="false" />
			<EF:EFColumn ename="weekend" align="center" cname="是否跳过周末" enable="false"  hidden="true"/>
			<EF:EFColumn ename="weekendName" align="center" cname="是否跳过周末" enable="false" />
			<EF:EFColumn ename="startTime" align="center" cname="开始时间" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataCycle" url="" lazyload="true" width="55%" height="80%" title="添加周期" modal="true" />
		
	<EF:EFRegion title="项目信息">
		<EF:EFGrid blockId="item" autoDraw="no" serviceName="IMJZ0101" checkMode="row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" enable="false"  hidden="true"/>
			<EF:EFColumn ename="itemId" cname="itemId" hidden="true" enable="false"/>
			<EF:EFColumn ename="code" cname="code" enable="false"  hidden="true"/>
			<EF:EFColumn ename="content" align="center" cname="巡查作业项目名称" enable="false" />
			<EF:EFColumn ename="referenceValue" align="center" cname="巡查项目参考值" enable="false" />
			<EF:EFColumn ename="projectDesc" align="center" cname="巡查作业说明" enable="false" />
			<EF:EFColumn ename="actualValueUnit" align="center" cname="巡查实际单位" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataItem" url="" lazyload="true" width="80%" height="80%" title="添加项目" modal="true" />
	<EF:EFWindow id="popDataItemCard" url="" lazyload="true" width="90%" height="80%" title="添加卡片" modal="true" />
	
	<EF:EFRegion title="执行人员"> 
		<EF:EFGrid blockId="exman" autoDraw="no" serviceName="IMJZ0101" checkMode="row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" enable="false" hidden="true"/>
			<EF:EFColumn ename="workNo" cname="manId" hidden="true" enable="false"/>
			<EF:EFColumn ename="deptName" align="center" cname="执行部门" enable="false" />
			<EF:EFColumn ename="deptNum" align="center" cname="执行部门编号" hidden="true" enable="false"/>
			<EF:EFColumn ename="name" align="center" cname="执行人" enable="false" />
			<EF:EFColumn ename="workNo" align="center" cname="工号" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataExman" url="" lazyload="true" width="50%" height="82%" title="添加执行人" modal="true" />
</EF:EFPage>
