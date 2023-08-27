<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-lubricateNo" cname="润滑单号" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-machineName" cname="设备"  colWidth="4" ratio="3:8"
							 popupTitle="设备选择" required="true" readonly="true"
							 popupType="ServiceGrid" serviceName="DFRH01" methodName="queryProject" resultId="dept"
							 valueField="machineCode" textField="machineName"
							 columnEnames="machineCode,machineName" columnCnames="设备编码,设备名称" />

			<EF:EFInput ename="inqu_status-0-fixedPlace" cname="安装地点" colWidth="4" ratio="3:8"/>

			<EF:EFPopupInput ename="inqu_status-0-lubricateDeptName" cname="负责单位"
							 popupTitle="润滑所属部门" popupType="ServiceGrid" serviceName="DFRH01"
							 methodName="queryDept" resultId="clean" readonly="true"
							 valueField="deptNum" textField="deptName" colWidth="4"
							 ratio="3:8" columnEnames="deptNum,deptName"
							 columnCnames="润滑单位编码,润滑单位名称"/>

		</div>
		<div class="row">


			<EF:EFPopupInput ename="inqu_status-0-lubricateManageName" cname="负责人"   colWidth="4" ratio="3:8"
							 popupTitle="负责人选择" required="false" readonly="true"
							 popupType="ServiceGrid" serviceName="DFRH01" methodName="queryPerson" resultId="person"
							 valueField="workNo" textField="name"
							 columnEnames="workNo,name" columnCnames="人员工号,人员姓名" />

			<EF:EFDatePicker ename="inqu_status-0-lubricateDate" cname="润滑日期"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
							 bindId="cleanDate" colWidth="4" ratio="3:8"/>

			<EF:EFInput ename="inqu_status-0-remark" cname="作业说明"  type="textarea" colWidth="4" ratio="3:8"/>

		</div>

		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>

	<!-- 页面分页 -->
	<EF:EFTab id="tab-tab_grid" active="0">
		<div title="润滑执行人">
			<EF:EFGrid blockId="resultE" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryPerson">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="workNo" cname="工号" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="name" cname="姓名" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="deptName" cname="科室名称" width="100" enable="false" align="center"/>
			</EF:EFGrid>
		</div>

		<div title="润滑信息">
			<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryPay">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="lubricateSpot" cname="润滑位置" width="100" align="center" />
				<EF:EFColumn ename="fillOil" cname="注油量" width="100" align="center" enable="true"/>
				<EF:EFColumn ename="wasteOil" cname="废油排量" width="100" align="center" enable="true"/>
				<EF:EFColumn ename="oilType" cname="油脂类型" width="100" align="center" enable="true"/>
				<EF:EFColumn ename="oilWaterContent" cname="油脂含水量" width="100" align="center" enable="true"/>
				<EF:EFColumn ename="oilAcidContent" cname="油脂含酸量" width="100" align="center" enable="true"/>
				<EF:EFColumn ename="eachElementContent" cname="各元素含量" width="100" align="center" enable="true"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>


	<!-- 人员选择弹出窗口 -->
	<EF:EFWindow id="personChoose" url="" lazyload="true" width="60%" height="90%" title="人员选择"/>
</EF:EFPage>

<script type="text/javascript">

</script>