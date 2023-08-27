IPLATUI.EFGrid = {
		"result": {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "projectName") {
					var id = e.model['id'];
					var projectCode = e.model['projectCode'];
					if ((id != " " && id != null)&&(projectCode != " " && projectCode != null)) {
						popData(datagrid.id,datagrid.projectCode);
					}
				}
			},
			//页面点击事件获取选中行id
			loadComplete: function (grid) {
				//保存方法
				$("#SAV").unbind('click').on("click", function (e) {
					debugger
					$("#SAV").attr("disabled", false);
					setTimeout(function(){$("#SAV").attr("disabled",false);},5000);
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length<1){
						NotificationUtil("请选择需要保存的项目", "error");
						return;
					}
					for (var i = 0; i < checkRows.length; i++) {
						var orderNumber = checkRows[i].orderNumber;
						var projectName = checkRows[i].projectName;
						var projectRemark = checkRows[i].projectRemark;
						if(orderNumber != ""){
							var reg = /^[0-9]*$/;
							if(!reg.test(orderNumber)){
								NotificationUtil("调查排序请输入数字", "error");
								return;
							}
						}
						if(orderNumber==""||projectName==""||projectRemark==""){
							NotificationUtil("请输入必填项", "error");
							return;
						}
					}
					var info = new EiInfo();
					info.set("checkRows",checkRows);
					EiCommunicator.send("SQBZ02", "save", info, {
						onSuccess : function(ei) {
							var status = ei.getStatus();
							if(status == -1){
								IPLAT.alert(ei.getMsg());
							}else{
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
//								setTimeout(function(){
//									resultGrid.dataSource.page(1);
//								},500);
							}
						}
					});
				});
				//删除方法
				$("#DELET").on("click", function (e) {
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length<1){
						NotificationUtil("请选择需要删除的项目", "error");
						return;
					}
					//删除表单中新增的数据
					for (var i = 0; i < checkRows.length; i++) {
						var id = checkRows[i].id;
						if(id == "" || id == null){
							grid.removeRows(checkRows[i]);
							NotificationUtil("删除成功");
							return;
						}
					}
					//删除数据库中存在的数据
					var info = new EiInfo();
					info.set("checkRows",checkRows);
					EiCommunicator.send("SQBZ02", "delete", info, {
						onSuccess : function(ei) {
							var status = ei.getStatus();
							if(status == -1){
								IPLAT.alert(ei.getMsg());
							}else{
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						}
					});
				});
			},
		}
}

//查看
function popData(id,projectCode) {
	var url = IPLATUI.CONTEXT_PATH + "/web/SQBZ03?initLoad&id=" + id + "&projectCode=" + projectCode;
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