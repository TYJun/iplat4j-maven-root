<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养综合查询">
	<EF:EFRegion id="inqu" title="查询区">
		<div class="row">
			<EF:EFInput ename="taskCode" cname="任务单号"  colWidth="4"/>
			<EF:EFInput ename="taskName" cname="任务名称" colWidth="3"/>
			<EF:EFInput ename="machineName" cname="设备名称" colWidth="3"/>
			</div>
		<div class="row">
			<EF:EFSelect cname="状态:" ename="status" >
			    <EF:EFOption label="请选择" value="" />
				<EF:EFOption label="执行中" value="1" />
				<EF:EFOption label="已完工" value="2" />
				<EF:EFOption label="已超时" value="-1" />
				<EF:EFOption label="关闭" value="3" />
			</EF:EFSelect>
			<EF:EFDatePicker ename="startTimeS" cname="开始日期起：" role="datetime"
				colWidth="3" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
				parseFormats="['yyyy-MM-dd HH:mm:ss']" />
			<EF:EFDatePicker ename="startTimeE" cname="开始日期止：" role="datetime"
				colWidth="3" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"
				parseFormats="['yyyy-MM-dd HH:mm:ss']" />
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="数据集" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKZH01">
			<EF:EFColumn ename="taskID" cname="主键" hidden="true" />
			<EF:EFColumn ename="schemeID" cname="基准的ID号" hidden="true" />
			<EF:EFColumn ename="status" cname="状态 " readonly="true" width="50"/>
			<EF:EFColumn ename="taskCode" cname="任务单号" readonly="true"  width="100"
			 displayType="url"/>
			<EF:EFColumn ename="schemeName" cname="任务名称  " readonly="true"  width="100"/>
			<EF:EFColumn ename="exceptionFlag" cname="异常状态 " readonly="true"  width="100"/>
			<EF:EFColumn ename="machineName" cname="设备名称 " readonly="true"  width="150"/>
			<EF:EFColumn ename="machineCode" cname="设备代码" readonly="true"  width="50"/>
			<EF:EFColumn ename="jobContent" cname="作业说明 " readonly="true"  width="100"/>
			<EF:EFColumn ename="deptName" cname="保养单位 " readonly="true"  width="100"/>
			<EF:EFColumn ename="name" cname="保养管理人" readonly="true"  width="80"/>
			<EF:EFColumn ename="finishManName" cname="完工操作人 " readonly="true"  width="80"/>
			<EF:EFColumn ename="createTime" cname="计划开始时间 " readonly="true"  width="100"/>
			<EF:EFColumn ename="finishTime" cname="完成时间 " readonly="true"  width="100"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url="" lazyload="true" width="80%"
		height="90%" title="保养综合查询详情" modal="true" />
	</div>
</EF:EFPage>
<script type="text/javascript">
	var datagrid = null;

	IPLATUI.EFGrid = {
		"result" : {

		}
	}


	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "taskCode") {
					var img = e.model['taskCode'];
					if (img != " " && img != null) {
						popData(datagrid.taskID,datagrid.taskCode,datagrid.schemeID);
					}
				}
			},
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
	}

	function popData(taskID,taskCode,schemeID) {
		var url = IPLATUI.CONTEXT_PATH + "/web/DKZH0101?initLoad&taskID=" + taskID+"&taskCode=" + taskCode+"&schemeID=" + schemeID;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	}


</script>