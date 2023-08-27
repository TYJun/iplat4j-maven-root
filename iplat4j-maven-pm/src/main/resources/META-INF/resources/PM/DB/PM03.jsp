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
			<EF:EFInput ename="inqu_status-0-projectStatus" cname="项目状态" type="hidden" value="01"/>
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
	<!-- 督办 -->
	<EF:EFWindow id="via" title="督办" url="" lazyload="true" width="85%" height="88%"></EF:EFWindow>
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
		
		
		//督办
		$("#VIAPR").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();
			if (checkRows.length > 0) {
				viaWindowOpen(checkRows[0],"via");
			} else {
				IPLAT.NotificationUtil("请选择需要督办的项目");
			}
		});
		
		//详情
		$("#DETAIL").on("click", function() {
			var checkRows = resultGrid.getCheckedRows();
			if(checkRows.length > 0){
				viaWindowOpen(checkRows[0],"detail");
			} else {
				IPLAT.NotificationUtil("请选择需要查看的记录")
			}
		});

	})
	
	function viaWindowOpen(data,type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0301?methodName=projectInit&id="+data.id
		var popData = $("#via");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
				setTimeout(function(){
					if(type=="detail"){
						var frame = popData.find("iframe")[0].contentWindow.$("#VIASAVE").hide();
					}
				}, 300)
			}
		});
		// 打开弹窗
		viaWindow.open().center();
	}

</script>

