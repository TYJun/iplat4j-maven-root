<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" >
		<div class="row">
			<EF:EFInput ename="inqu_status-0-documentsName" cname="归档名称" />
			<EF:EFInput ename="inqu_status-0-submitter" cname="提交人" />
			<EF:EFInput ename="inqu_status-0-docStatusCode" cname="归档状态" type="hidden" value="02"/>
			<EF:EFInput ename="inqu_status-0-documentsCode" cname="归档编号" type="hidden" value="xx"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="resultA" title="归档列表" >
		<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryCalculate">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="docStatusCode" cname="归档状态" width="100" hidden="true" />
			<EF:EFColumn ename="documentsCode" cname="归档编码" width="100" align="center"/>
			<EF:EFColumn ename="documentsName" cname="归档名称" width="200" align="center"/>
			<EF:EFColumn ename="submitterCode" cname="提交人工号" width="100" hidden="true"/>
			<EF:EFColumn ename="submitter" cname="提交人" width="100" align="center"/>
			<EF:EFColumn ename="charge" cname="结算费用" width="100" align="center"/>
			<EF:EFComboColumn ename="statusCode" cname="是否施工确认" width="100" align="center">
				<EF:EFOption label="已确认" value="1"/>
				<EF:EFOption label="未确认" value="0"/>
			</EF:EFComboColumn>
			<EF:EFComboColumn ename="materialStatusCode" cname="是否收到决算材料" width="100" align="center">
				<EF:EFOption label="收到" value="1"/>
				<EF:EFOption label="未收到" value="0"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="dataSubmitterCode" cname="资料提交人工号" width="100" hidden="true"/>
			<EF:EFColumn ename="dataSubmitter" cname="资料提交人" width="100" align="center"/>
			<EF:EFColumn ename="dataRecipientCode" cname="审计接收人工号" width="100" hidden="true"/>
			<EF:EFColumn ename="dataRecipient" cname="审计接受人" width="100" align="center"/>
			<EF:EFComboColumn ename="dataCode" cname="提交资料" width="100" align="center">
				<EF:EFCodeOption codeName="pm.projectData"/>
			</EF:EFComboColumn>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="resultB" title="归档项目列表">
		<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryCalculateDetail">
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
			<EF:EFColumn ename="actFinisthDate" cname="完成时间" width="100" align="center"/>
			<EF:EFColumn ename="ystime" cname="验收时间" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
 <!-- 新增弹出窗口 -->
 <EF:EFWindow id="archive" title="归档结算" url=" " lazyload="true" width="40%" height="55%"></EF:EFWindow>
</EF:EFPage>
<<script type="text/javascript">
	IPLATUI.EFGrid = {
		"resultA":{
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "EDITARCHIVE",text: "编辑",layout: "3",
					click: function () { 
						var checkRows = resultAGrid.getCheckedRows();
						if (checkRows.length > 0) {
							archiveWindowOpen(checkRows[0].id, checkRows[0].docStatusCode);
						} else {
							IPLAT.NotificationUtil("请选择需要编辑的数据")
						}
					}
				},{
					name: "BTNSBUMIT",text: "提交",layout: "3",
					click: function () { 
						var checkRows = resultAGrid.getCheckedRows();
						if (checkRows.length > 0) {
							submit("audiaArchive",checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要提交的数据")
						}
					}
				}]
			},
			onRowClick: function (e) {
				var documentsCode = e.model.documentsCode;
				$("[name = 'inqu_status-0-documentsCode']").val(documentsCode);
				resultBGrid.dataSource.page(1);
			},
		},
	}
	$(function(){
		resultAGrid.dataSource.page(1);

		//单据页面查询
		$("#QUERY").on("click", function(e) {
			resultAGrid.dataSource.page(1);
			resultBGrid.dataSource.remove(resultBGrid.getDataItems());
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			$("#inqu_status-0-documentsName").val("");
			$("#inqu_status-0-submitter").val("");
			resultAGrid.dataSource.page(1);
		});
		
	})


	//归档结算弹窗 
	function archiveWindowOpen(id,docStatusCode) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0601?methodName=initLoad&id="+id+"&docStatusCode="+docStatusCode;
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
	function submit(methodName,data){
		var ids = new Array();
		for(var index in data) {
			ids.push(data[index].id);
		}
		var eiInfo = new EiInfo();
		eiInfo.set("ids",ids.join(","))
		IPLAT.confirm({
			message: '<b>确定执行该操作吗？</b></i>',
			okFn: function (e) { 
				EiCommunicator.send("PM06", methodName, eiInfo, {
					onSuccess : function(ei) {
						IPLAT.NotificationUtil(ei.msg)
						resultAGrid.dataSource.page(1);
						$("[name = 'inqu_status1-0-documentsCode']").val("XX");
						resultBGrid.dataSource.page(1);
					}
				})
			},
			cancelFn: function (e) {}
		})
	}
</script>

