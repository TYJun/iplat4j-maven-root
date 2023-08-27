<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="分类名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
					   hasChildren="leaf" serviceName="MSPL02" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFSelect ename="inqu_status-0-relation" cname="设备" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="关联" value="11" />
					<EF:EFOption label="未关联" value="10" />
				</EF:EFSelect>
				<EF:EFSelect ename="inqu_status-0-paramEnableStatus" cname="参数项" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="未启用" value="0" />
				</EF:EFSelect>
				<EF:EFSelect ename="inqu_status-0-alarmEnableStatus" cname="报警" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="未启用" value="0" />
				</EF:EFSelect>
				<EF:EFInput ename="inqu_status-0-name" cname="名称" />
				<EF:EFInput ename="inqu_status-0-tag" cname="Tag" />
				<EF:EFSelect ename="inqu_status-0-isSync" cname="大屏" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="未启用" value="0" />
				</EF:EFSelect>
				<EF:EFInput ename="inqu_status-0-parentId" cname="" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="参数项信息">
			<EF:EFGrid blockId="result" autoDraw="no"  serviceName="MSPL01" methodName="query">
				<EF:EFColumn ename="id" cname="主键" width="200" hidden="true" />
				<EF:EFColumn ename="name" cname="参数项名称" width="200" disabled="true"/>
				<EF:EFColumn ename="tag" cname="参数项Tag" width="200" disabled="true"/>
				<EF:EFColumn ename="description" cname="参数项描述" width="200" disabled="true"/>
				<EF:EFColumn ename="paramEnableStatus" cname="启用情况" width="200" disabled="true"/>
				<EF:EFColumn ename="dimensionName" cname="计量量纲" width="200" disabled="true"/>
				<EF:EFColumn ename="unitName" cname="计量单位" width="200" disabled="true"/>
				<EF:EFColumn ename="alarmEnableStatus" template="statusFormt" cname="报警启用" width="200" disabled="true"/>
				<EF:EFColumn ename="tmsarName" cname="报警规则" width="200" disabled="true"/>
				<EF:EFColumn ename="deadTime" cname="死区时间（秒）" width="200" disabled="true"/>
				<EF:EFColumn ename="tmsdcName" cname="所属设备" width="200" disabled="true"/>
				<EF:EFColumn ename="limitTimeValue" cname="报警时间段设置" width="200" disabled="true"/>
				<EF:EFColumn ename="limitRepeatsEnable" cname="报警短信限止" width="200" disabled="true"/>
				<EF:EFColumn ename="limitRepeatsEnable" cname="短信自动禁用" width="200" disabled="true"/>
				<EF:EFColumn ename="isWriteLog" cname="是否启用记录日志" width="200" disabled="true"/>
				<EF:EFColumn ename="limitTimeValue" cname="短信自动禁用时间设置（分钟）" width="200" disabled="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
			 height="70%">
</EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
	var eiInfo = new EiInfo();
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
			},
			columns: [{
				field: "paramEnableStatus",
				template: function (item) {
					if(item['paramEnableStatus']==0) {
						return "未启用"
					} else if(item['paramEnableStatus']==1) {
						return "启用中"
					}
				}

			},{
				field: "alarmEnableStatus",
				template: function (item) {
					if(item['alarmEnableStatus']==0) {
						return "未启用"
					} else if(item['alarmEnableStatus']==1) {
						return "启用中"
					}
				}

			},{
				field: "isWriteLog",
				template: function (item) {
					if(item['isWriteLog']==0) {
						return "未启用"
					} else if(item['isWriteLog']==1) {
						return "启用中"
					}
				}

			}],
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,/*'delete': false,*/
				buttons:[{
					name: "OPEN",text: "启用",layout:"5",
					// click: function () {
					// 	var wgroupNum = IPLAT.EFSelect.value($("#wgroupNum"));
					// 	if(isEmpty(wgroupNum)){
					// 		NotificationUtil("请先选择维修科室", "error");
					// 		return;
					// 	}
					// 	personChooseWindowOpen(wgroupNum);
					// }
				},
					 {
					 	name: "CLOSE",text: "禁用",layout:"6",}
				]
			}

		}
	}

	//弹窗
	function popData(id, parentId, type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSPL0101?initLoad&id=" + id
				+ "&type=" + type + "&parentId=" + parentId;
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
	function statusFormt(e) {
		console.log(e);
	}

	//弹窗
	function popDatas(id) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSPL0102?initLoad&id=" + id;
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

	//弹窗
	function popDataRemove(id) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSPL0103?initLoad&id=" + id;
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


	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
            var tag = $("#inqu_status-0-tag").val();
            console.log(tag);
            if (tag==" " && tag==null) {
                console.log("111111111111111");
            }else {
                console.log("111111111111111");
                resultGrid.dataSource.query(1);
            }
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			var parentId = $("#inqu_status-0-parentId").val();
			document.getElementById("inqu-trash").click();
			$("#inqu_status-0-parentId").val(parentId);
			resultGrid.dataSource.query(1);
		});
		//参数启用
		$("#OPEN").on("click", function(e) {
			debugger;
			var checkRows = resultGrid.getCheckedRows();
			console.log(checkRows);
			if ($(".check-one").is(':checked')==false) {
				IPLAT.alert("请先选择一条记录");
			} else {
				eiInfo.set("list",checkRows);
				EiCommunicator.send("MSPL01", "editOpen", eiInfo, {
					onSuccess : function(eiInfo) {
						NotificationUtil("操作成功，点位参数已启用", "");
						resultGrid.dataSource.query(1);
					},
					onFail : function(errorMsg, status, e) {
						NotificationUtil("启用失败", "error");
					}
				});
			}
		});
		//参数启用
		$("#CLOSE").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();
			console.log(checkRows);
			if ($(".check-one").is(':checked')==false) {
				IPLAT.alert("请先选择一条记录");
			} else {
				eiInfo.set("list",checkRows);
				EiCommunicator.send("MSPL01", "editOpen1", eiInfo, {
					onSuccess : function(eiInfo) {
						NotificationUtil("操作成功，点位参数已禁用", "");
						resultGrid.dataSource.query(1);
					},
					onFail : function(errorMsg, status, e) {
						NotificationUtil("禁用失败", "error");
					}
				});
			}
		});

		//新增按钮
		$("#ADD").on("click", function(e) {
			var parentId = $("#inqu_status-0-parentId").val();
			console.log(parentId);
			if(parentId=="" || parentId=="root") {
				IPLAT.alert("请先选择左侧树状菜单,请勿选择根节点");
			} else {
				popData("", parentId, "add");
			}

		});

		//编辑按钮
		$("#EDIT").on("click", function(e) {
			if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行编辑");
			}  else {
				popDatas(datagrid.id);
			}
		});

		//查看按钮
		$("#SEE").on("click", function(e) {
			if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行查看");
			}  else {
				popData(datagrid.id, "see");
			}
		});

		$("#REMOVE").on("click", function (e) {
			if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行查看");
			} else {
				popDataRemove(datagrid.id);
			}
		})

	});
</script>

