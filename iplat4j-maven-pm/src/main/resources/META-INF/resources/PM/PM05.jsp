<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFTab id="tab-tab_view">
		<div title="单据页面">
			<EF:EFRegion id="inqu" title="查询条件" >
				<div class="row">
					<EF:EFInput ename="inqu_status-0-projectName" cname="项目名称" />
					<EF:EFSelect ename="inqu_status-0-projectProp" cname="项目性质">
						<EF:EFOption label="全部" value=""/>
						<EF:EFCodeOption codeName="pm.projectProp"/>
					</EF:EFSelect>
					<%--<EF:EFSelect ename="inqu_status-0-projectTypeNum" cname="项目类型">
						<EF:EFOption label="全部" value=""/>
						<EF:EFCodeOption codeName="pm.projectType"/>
					</EF:EFSelect>--%>
					<EF:EFInput ename="inqu_status-0-projectPrincipal" cname="项目负责人" />
				</div>
				<div class="row">
					<EF:EFInput ename="inqu_status-0-projectStatus" cname="项目状态" type="hidden" value="06"/>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERYPR" layout="0" ></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY" layout="0" ></EF:EFButton>
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
					<%--<EF:EFComboColumn ename="projectTypeNum" cname="项目类型" width="100" align="center">
						<EF:EFCodeOption codeName="pm.projectType"/>
					</EF:EFComboColumn>--%>
					<EF:EFColumn ename="projectTypeName" cname="项目类型" width="100" align="center"/>
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
		</div>
		<div title="归档页面">
			<EF:EFRegion id="inqu1" title="查询条件" >
				<div class="row">
					<EF:EFInput ename="inqu_status1-0-documentsName" cname="归档名称" />
					<EF:EFInput ename="inqu_status1-0-submitter" cname="提交人" />
					<EF:EFInput ename="inqu_status1-0-docStatusCode" cname="归档状态" type="hidden" value="01"/>
					<EF:EFInput ename="inqu_status1-0-documentsCode" cname="归档编号" type="hidden" value="xx"/>
				</div>
				<div class="button-region" id="buttonDiv">
					<EF:EFButton cname="查询" ename="QUERYARCHIVE" layout="0" ></EF:EFButton>
					<EF:EFButton cname="重置" ename="REQUERY2" layout="0" ></EF:EFButton>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="resultA" title="归档列表" >
				<EF:EFGrid blockId="resultA" autoDraw="no" checkMode="single,row" readonly="true" queryMethod="queryCalculate">
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
					<EF:EFColumn ename="documentsCode" cname="归档编码" width="100" />
					<EF:EFColumn ename="documentsName" cname="归档名称" width="200" />
					<EF:EFColumn ename="submitterCode" cname="提交人工号" width="100" hidden="true"/>
					<EF:EFColumn ename="submitter" cname="提交人" width="100" />
					<EF:EFColumn ename="charge" cname="结算费用" width="100" />
					<EF:EFComboColumn ename="statusCode" cname="是否施工确认" width="100" >
						<EF:EFOption label="已确认" value="1"/>
						<EF:EFOption label="未确认" value="0"/>
					</EF:EFComboColumn>
					<EF:EFComboColumn ename="materialStatusCode" cname="是否收到决算材料" width="100" >
						<EF:EFOption label="收到" value="1"/>
						<EF:EFOption label="未收到" value="0"/>
					</EF:EFComboColumn>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFRegion id="resultB" title="归档项目列表">
				<EF:EFGrid blockId="resultB" autoDraw="no" checkMode="single" readonly="true" queryMethod="queryCalculateDetail">
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
					<EF:EFColumn ename="projectNo" cname="项目号" width="100" />
					<EF:EFColumn ename="projectName" cname="项目名称" width="200" />
					<EF:EFComboColumn ename="projectProp" cname="项目性质" width="100" >
						<EF:EFCodeOption codeName="pm.projectProp"/>
					</EF:EFComboColumn>
					<EF:EFComboColumn ename="projectTypeNum" cname="项目类型" width="100" >
						<EF:EFCodeOption codeName="pm.projectType"/>
					</EF:EFComboColumn>
					<EF:EFComboColumn ename="projectStatus" cname="项目状态" width="80" >
						<EF:EFCodeOption codeName="pm.projectStatus"/>
					</EF:EFComboColumn>
					<EF:EFColumn ename="contorId" cname="项目负责人" width="100" hidden="true"/>
					<EF:EFColumn ename="projectPrincipal" cname="项目负责人" width="100" />
					<EF:EFColumn ename="projectObjectDeptNum" cname="项目科室" width="150" hidden="true" />
					<EF:EFColumn ename="projectObjectDeptName" cname="项目科室" width="150" />
					<EF:EFColumn ename="undertakeDeptNum" cname="承办科室" width="150" hidden="true"/>
					<EF:EFColumn ename="undertakeDeptName" cname="承办科室" width="150" />
					<EF:EFColumn ename="startDate" cname="开始日期" width="100" />
					<EF:EFColumn ename="finishDeadline" cname="完成期限" width="100" />
					<EF:EFColumn ename="actFinisthDate" cname="完成时间" width="100" />
					<EF:EFColumn ename="ystime" cname="验收时间" width="100" />
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</EF:EFTab>
 <!-- 新增弹出窗口 -->
 <EF:EFWindow id="archive" title="归档结算" url="" lazyload="true" width="40%" height="42%"></EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	IPLATUI.EFGrid = {
		"result":{
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "BTNARCHIVE",text: "归档结算",layout: "3",
					click: function () { 
						var checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							var projectNos = "";
							for(var index in checkRows){
								projectNos = projectNos+checkRows[index].projectNo+",";
							}
							archiveWindowOpen(projectNos.substring(0,projectNos.length-1),checkRows[0].projectName);
						} else {
							IPLAT.NotificationUtil("请选择需要归档结算的数据")
						}
					}
				}]
			}, 
		},
		"resultA":{
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "BTNSBUMIT",text: "提交",layout: "3",
					click: function () { 
						var checkRows = resultAGrid.getCheckedRows();
						if (checkRows.length > 0) {
							submitOrDelete("submitArchive", checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要提交的数据")
						}
					}
				},{
					name: "DELARCHIVE",text: "删除",layout: "3",
					click: function () { 
						var checkRows = resultAGrid.getCheckedRows();
						if (checkRows.length > 0) {
							submitOrDelete("deleteArchive", checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的数据")
						}
					}
				}]
			},
			onRowClick: function (e) {
				var documentsCode = e.model.documentsCode;
				$("[name = 'inqu_status1-0-documentsCode']").val(documentsCode);
				resultBGrid.dataSource.page(1);
			},
		},
	}
	$(function(){
		resultGrid.dataSource.page(1);
		resultAGrid.dataSource.page(1);
		resultBGrid.dataSource.page(1);
		//使用 grid 的 refresh 方法来解决渲染异常
		IPLATUI.EFTab = {
			"tab-tab_view": {
				show: function (e) {
					$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh()
				}
			}
		}
		//单据页面查询
		$("#QUERYPR").on("click", function(e) {
			resultGrid.dataSource.page(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			$("#inqu_status-0-projectName").val("");
			$("#inqu_status-0-projectPrincipal").val("");
			IPLAT.EFSelect.value($("#inqu_status-0-projectProp"),"");
			/*IPLAT.EFSelect.value($("#inqu_status-0-projectTypeNum"),"");*/
			resultGrid.dataSource.page(1);
		});
		
		//归档页面查询
		$("#QUERYARCHIVE").on("click", function(e) {
			resultAGrid.dataSource.page(1);
			resultBGrid.dataSource.page(1);
		});

		//重置按钮
		$("#REQUERY2").on("click", function(e) {
			$("#inqu_status1-0-documentsName").val("");
			$("#inqu_status1-0-submitter").val("");
			resultAGrid.dataSource.page(1);
			resultBGrid.dataSource.page(1);
		});
	})


	//归档结算弹窗 
	function archiveWindowOpen(projectNos,projectName) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0501?methodName=initLoad&projectNos="+projectNos+"&projectName="+projectName;
		var popData = $("#archive");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开弹窗
		archiveWindow.open().center();
	}
	
	//提交或者删除
	function submitOrDelete(methodName,data){
		var ids = new Array();
		for(var index in data) {
			ids.push(data[index].id);
		}
		var eiInfo = new EiInfo();
		eiInfo.set("ids",ids.join(","))
		IPLAT.confirm({
			message: '<b>确定执行该操作吗？</b></i>',
			okFn: function (e) { 
				EiCommunicator.send("PM05", methodName, eiInfo, {
					onSuccess : function(ei) {
						IPLAT.NotificationUtil(ei.msg)
						resultAGrid.dataSource.query(1);
						$("[name = 'inqu_status1-0-documentsCode']").val("XX");
						resultBGrid.dataSource.query(1);
					}
				})
			},
			cancelFn: function (e) {}
		})
	}
</script>

