<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="系统分类" fitHeight="true">
			<EF:EFTree id="tree01" valueField="label" textField="text"
					   hasChildren="leaf" serviceName="MSDC01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-device_name" cname="告警设备：	" />
					<EF:EFInput ename="inqu_status-0-item" cname="告警项：		" />
					<EF:EFSelect bindId="grade" resultId="grade" optionLabel="请选择" ename="grade"  row="0" cname="告警等级" >
                    <EF:EFCodeOption codeName="znjk.cm.grades"  textField="label" valueField="value"/>
                	</EF:EFSelect>
					<EF:EFDatePicker ename="inqu_status-0-start_time" cname="*开始报警时间:	"
									 format="yyyy-MM-dd" required="true">
					</EF:EFDatePicker>
					<EF:EFDatePicker ename="inqu_status-0-end_time" cname="*结束报警时间:"
									 format="yyyy-MM-dd" required="true">
					</EF:EFDatePicker>
					<EF:EFSelect bindId="process_start_time" resultId="process_start_time"  optionLabel="请选择" ename="process_start_time"  row="yes" cname="确认情况：" >
						<EF:EFCodeOption codeName="znjk.cm.confirm"  textField="label" valueField="value"/>
					</EF:EFSelect>
					<EF:EFSelect bindId="status" resultId="status"  ename="status" optionLabel="请选择"  row="yes" cname="告警状态：" >
						<EF:EFCodeOption codeName="ms.cm.status"  textField="label" valueField="value"/>
					</EF:EFSelect>
					<EF:EFSelect bindId="process_end_time" resultId="process_end_time" optionLabel="请选择" ename="process_end_time"  row="yes" cname="处理情况：" >
						<EF:EFCodeOption codeName="ms.cm.end"  textField="label" valueField="value"/>
					</EF:EFSelect>
					<EF:EFInput ename="inqu_status-0-parentId" cname="" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="报警信息">
				<EF:EFGrid blockId="result" autoDraw="no">
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true" disabled="true"/>
					<EF:EFColumn ename="area_name" cname="区域" width="100" disabled="true"/>
					<EF:EFColumn ename="device_name" cname="设备" width="100" disabled="true"/>
					<EF:EFColumn ename="item" cname="告警项" width="100" disabled="true"/>
					<EF:EFColumn ename="value_" cname="告警（阈）值" width="100" disabled="true"/>
					<EF:EFColumn ename="end_value" cname="结束值" width="80" disabled="true"/>
					<EF:EFColumn ename="grade" cname="告警等级" width="100" disabled="true"/>
					<EF:EFColumn ename="description_" cname="告警说明" width="100" disabled="true"/>
					<EF:EFColumn ename="start_time" cname="告警时间" width="100" disabled="true"/>
					<EF:EFColumn ename="end_time" cname="恢复时间" width="100" disabled="true"/>
					<EF:EFColumn ename="process_start_user" cname="开始操作人" width="100" disabled="true"/>
					<EF:EFColumn ename="process_end_user" cname="结束操作人" width="100" disabled="true"/>
					<EF:EFColumn ename="process_start_time" cname="开始时间"  width="100" disabled="true"/>
					<EF:EFColumn ename="process_end_time" cname="结束时间"  width="100" disabled="true"/>
					<EF:EFColumn ename="process_value" cname="处理说明"  width="100" disabled="false"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>

<script type="text/javascript">

	var datagrid = null;
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}else{
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
	}


	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
			var start_time=$("#inqu_status-0-start_time").val();
			var end_time=$("#inqu_status-0-end_time").val();
			if (start_time>end_time){
				IPLAT.alert("起始时间必须小于或等于结束时间");
			}else {
				resultGrid.dataSource.query(1);
			}
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			var parentId=$("#inqu_status-0-parentId").val();
			document.getElementById("inqu-trash").click();
			$("#inqu_status-0-parentId").val(parentId);
			resultGrid.dataSource.query(1);
		});

		//已处理按钮
		$("#ENDPROCESSING").on("click", function(e) {
			let rows = resultGrid.getCheckedRows();
			if (rows.length == 0) {
				IPLAT.alert("请至少选择一条记录进行处理！");
			}else {
				for (let row of rows) {
					if (row.process_start_time != null) {
						IPLAT.alert("该报警已经开始处理，请勿重复操作！");
						return;
					}
				}
				for (let row of rows) {
					debugger;
					popData3(row.id, "checks", "");
				}
				IPLAT.alert("操作成功！");
				resultGrid.dataSource.query(1);
			}
		});

		//已确认按钮
		$("#STARTPROCESSING").on("click", function(e) {
			let rows = resultGrid.getCheckedRows();
			if (rows.length == 0) {
				IPLAT.alert("请至少选择一条记录进行确认操作！");
			}else {
				for (let row of rows) {
					if (row.process_start_time == null || row.process_end_time != null) {
						IPLAT.alert("该报警仍未开始处理或者已经处理完毕，请勿重复操作！");
						return;
					}
				}
				let process_value = rows[0].process_value;
				if (process_value == null || process_value.length == 0) {
					IPLAT.alert("请在第一行数据输入处理说明！");
					return;
				}
				for (let row of rows) {
					popData3(row.id, "check", process_value);
				}
				IPLAT.alert("操作成功！");
				resultGrid.dataSource.query(1);
			}
		});

		function popData3(id,type,process_value) {
			var inInfo = new EiInfo();
			inInfo.set("id",id);
			inInfo.set("type",type);
			inInfo.set("process_value",process_value);
			EiCommunicator.send("MXAL01","isCheck", inInfo, {
				onSuccess: function(outInfo){
					if (outInfo.getMsg() == "修改成功") {
						console.log("开始处理！")
						return;
					}
					if (outInfo.getMsg() == "修改成功2") {
						console.log("处理完成！")
						return;
					}
					console.log(outInfo.getMsg());
				},
				onFail: function(errorMsg) {
					console.log(errorMsg);
				}
			}, {async: true});
		}





	})
</script>
