<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" >
		<div class="row">
			<EF:EFInput ename="inqu_status-0-projectName" cname="项目名称" />
			<EF:EFSelect ename="inqu_status-0-projectProp" cname="项目性质">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="pm.projectProp"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-projectTypeNum" cname="项目类型">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="pm.projectType"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-projectPrincipal" cname="项目负责人" />
			<EF:EFInput ename="inqu_status-0-projectStatusIN" cname="项目状态" type="hidden" value="01,02,04"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="projectNo" cname="项目号" width="100" align="center"/>
			<EF:EFColumn ename="projectName" cname="项目名称" width="200" align="center"/>
			<EF:EFComboColumn ename="projectProp" cname="项目性质" width="100" align="center">
				<EF:EFCodeOption codeName="pm.projectProp"/>
			</EF:EFComboColumn>
			<EF:EFComboColumn ename="projectTypeNum" cname="项目类型" width="100" align="center">
				<EF:EFCodeOption codeName="pm.projectType"/>
			</EF:EFComboColumn>
			<EF:EFComboColumn ename="projectStatus" cname="项目状态" width="80" align="center">
				<EF:EFCodeOption codeName="pm.projectStatus"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="contorId" cname="项目负责人" width="100" hidden="true"/>
			<EF:EFColumn ename="projectPrincipal" cname="项目负责人" width="100" align="center"/>
			<EF:EFColumn ename="projectObjectDeptNum" cname="项目科室" width="150" hidden="true" />
			<EF:EFColumn ename="projectObjectDeptName" cname="项目科室" width="150" align="center"/>
			<EF:EFColumn ename="undertakeDeptNum" cname="承办科室" width="150" hidden="true"/>
			<EF:EFColumn ename="undertakeDeptName" cname="承办科室" width="150" align="center"/>
			<EF:EFColumn ename="startDate" cname="开始日期" width="100" align="center"/>
			<EF:EFColumn ename="finishDeadline" cname="完成期限" width="100" align="center"/>
			<EF:EFColumn ename="projectObjectCons" cname="项目联络人" width="100" hidden="true" />
			<EF:EFColumn ename="projectPriNum" cname="项目优先级" width="100" hidden="true" />
			<EF:EFColumn ename="projectProgress" cname="项目进度" width="100" hidden="true" />
			<EF:EFColumn ename="winbidExpense" cname="中标费用" width="100" hidden="true" />
			<EF:EFColumn ename="finishExpense" cname="结算费用" width="100" hidden="true" />
			<EF:EFColumn ename="projectContent" cname="项目内容" width="200" hidden="true" />
			<EF:EFColumn ename="projectRemark" cname="备注" width="100" hidden="true" />
			<EF:EFColumn ename="supplierNum" cname="供应商" width="100" hidden="true" />
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 查看详情 -->
	<EF:EFWindow id="projectEdit" title="项目执行" url="" lazyload="true" width="85%" height="88%"></EF:EFWindow>
	
	<!-- 打印工单 -->
	<EF:EFWindow id="print" title="工单打印" url="" lazyload="true" width="85%" height="88%"></EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		//初始加载表格数据
		resultGrid.dataSource.query(1);
		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			$("#inqu_status-0-projectName").val("");
			$("#inqu_status-0-projectPrincipal").val("");
			IPLAT.EFSelect.value($("#inqu_status-0-projectProp"),"");
			IPLAT.EFSelect.value($("#inqu_status-0-projectTypeNum"),"");
			resultGrid.dataSource.query(1);
		});
		
		//拆分
		$("#SPLITPR").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();
			if (checkRows.length > 0) {
				var eiInfo = new EiInfo();
				eiInfo.set("id",checkRows[0].id)
			} else {
				IPLAT.NotificationUtil("请选择需要拆分的项目");
				return;
			}
			EiCommunicator.send("PM02", "splitProject", eiInfo, {
				onSuccess : function(ei) {
					IPLAT.NotificationUtil(ei.msg)
					resultGrid.dataSource.query(1);
				}
			})
			
		});
		
		//执行
		$("#EXCUTEPR").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();
			if (checkRows.length > 0) {
				projectEditWindowOpen(checkRows[0],"excute");
			} else {
				IPLAT.NotificationUtil("请选择需要执行的项目");
				return;
			}
		});
		
		//完工
		$("#FINISHPR").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();
			if (checkRows.length > 0) {
				if(checkRows[0].projectStatus =="01"){
					IPLAT.NotificationUtil("项目未执行无法完工");
					return;
				}
				var eiInfo = new EiInfo();
				eiInfo.set("id",checkRows[0].id)
			} else {
				IPLAT.NotificationUtil("请选择需要完工的项目");
				return;
			}
			EiCommunicator.send("PM02", "finishProject", eiInfo, {
				onSuccess : function(ei) {
					IPLAT.NotificationUtil(ei.msg)
					resultGrid.dataSource.query(1);
				}
			})
		});

		//详情
		$("#DETAIL").on("click", function() {
			var checkRows = resultGrid.getCheckedRows();
			if(checkRows.length > 0){
				projectEditWindowOpen(checkRows[0],"detail");
			} else {
				IPLAT.NotificationUtil("请选择需要查看的记录")
			}
		});

		//删除
		$("#DELETEPR").on("click", function() {
			var checkRows = resultGrid.getCheckedRows();
			var eiInfo = new EiInfo();
			if (checkRows.length > 0) {
				if(checkRows[0].projectStatus !="01"){
					IPLAT.NotificationUtil("执行中的项目无法删除");
					return;
				}
				var block = resultGrid.model2EiBlock(checkRows)
				eiInfo.setByNode("inqu");
				eiInfo.addBlock(block);
			} else {
				IPLAT.NotificationUtil("请选择需要删除的数据");
				return;
			}
			IPLAT.confirm({
				message: '<b>确定执行该操作吗？</b></i>',
				okFn: function (e) { 
					EiCommunicator.send("PM01", "delete", eiInfo, {
						onSuccess : function(ei) {
							IPLAT.NotificationUtil(ei.msg)
							resultGrid.dataSource.query(1);
						}
					})
				},
				cancelFn: function (e) {}
			})
		});

		//打印工单
		$("#PRINT").on("click", function() {
			var checkRows = resultGrid.getCheckedRows();
			
		});
	})
	
	function projectEditWindowOpen(data,type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0201?methodName=projectInit&id="+data.id;
		var popData = $("#projectEdit");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
				setTimeout(function(){
					if(type=="detail"){
						var frame = popData.find("iframe")[0].contentWindow.$("#EXCUTE").hide();
					}
				}, 300)
				
			}
		});
		// 打开弹窗
		projectEditWindow.open().center();
	}

</script>

