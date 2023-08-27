var datagrid = null;
$(function() {
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				//新增按钮
				$("#ADDDROM").on("click", function(e) {
					console.log("新增");
					var url = IPLATUI.CONTEXT_PATH + "/web/DMXX0101";
					var popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataWindow.open().center();
				});

				//编辑按钮
				$("#EDITDROM").on("click", function(e) {
					console.log("编辑");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var roomId = checkRows[0]["id"];
						console.log(roomId);
						var url = IPLATUI.CONTEXT_PATH + "/web/DMXX0102?initLoad&roomId=" + roomId;
						var popData = $("#popDataModify");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataModifyWindow.open().center();
					} else {
						NotificationUtil("请选择想要编辑的宿舍", "warning")
					}
				});

				// 删除按钮
				$("#DELETEDROM").on("click", function(e) {
					console.log("删除");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var id = checkRows[0]["id"];
						IPLAT.confirm({
							message: '<b>将会删除该宿舍信息！</br><font color="red">是否确定？</font></b>',
							okFn: function (e) {
								//提交数据
								var eiInfo = new EiInfo();
								eiInfo.set("roomId", id);
								EiCommunicator.send("DMXX01", "deleteDrom", eiInfo, {
									onSuccess : function(ei) {
										IPLAT.NotificationUtil(ei.msg);
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								});
							},
							title: '提醒'
						});
					} else {
						NotificationUtil("请选择想要删除的宿舍", "warning")
					}
				});

				// 查看按钮.
				$("#SHOWDROM").on("click", function(e) {
					console.log("查看按钮");
					var eiInfo = new EiInfo();
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var roomId = checkRows[0]["id"];
						popDatashow(roomId);
					} else {
						NotificationUtil("请选择想要查看详情的宿舍", "warning")
					}
				});
			}
		}
	}
});

// 查看详情工单
function popDatashow(roomId) {
	var url = IPLATUI.CONTEXT_PATH + "/web/DMXX0103?initLoad&roomId=" + roomId;
	var popData = $("#popDatashow");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDatashowWindow.open().center();
}