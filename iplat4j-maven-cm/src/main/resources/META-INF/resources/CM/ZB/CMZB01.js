$(function () {
	// 查询
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
	// 重置按钮
	$("#REQUERY").on("click", function (e) {
		$("#bidName").val("");
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result": {
			toolbarConfig: {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false, cancel: false, save: false, 'delete': false,
				buttons: [{
					name: "new", text: "新增", layout: "3",
					click: function () {
						bidWindows("new","");
						bidDataWindow.setOptions({"title":"新增"});
					}
				}, {
					name: "edit", text: "编辑", layout: "3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						var eiInfo = new EiInfo();
						if (checkRows.length > 0) {
							var bidId = checkRows[0].id;
							bidWindows("edit",bidId);
							bidDataWindow.setOptions({"title":"编辑"});
						} else {
							IPLAT.NotificationUtil("请选择需要编辑的招标记录","error");
						}
					}
				}, {
					name: "deleter", text: "删除", layout: "3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						var eiInfo = new EiInfo();
						if (checkRows.length > 0) {
							var info = new EiInfo();
							var bidId = checkRows[0].id;
							info.set("bidId",bidId);
							IPLAT.confirm({
								message: '<b>确定删除操作吗？</b></i>',
								okFn: function (e) {
									EiCommunicator.send("CMZB01", "deleter", info, {
										onSuccess : function(ei) {
											NotificationUtil(ei.getMsg(), "success");
											resultGrid.dataSource.page(1);
										}
									});
								},
								cancelFn: function (e) {}
							})
						} else {
							IPLAT.NotificationUtil("请选择需要删除的招标记录","error");
						}
					}
				}, {
					name: "look", text: "查看", layout: "3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						var eiInfo = new EiInfo();
						if (checkRows.length > 0) {
							var bidId = checkRows[0].id;
							bidWindows("look",bidId);
							bidDataWindow.setOptions({"title":"查看"});
						} else {
							IPLAT.NotificationUtil("请选择需要查看的招标记录","error");
						}
					}
				}, {
					name: "printBid", text: "打印招标函", layout: "3",
					click: function () {
						alert("该功能不在本次评审范围内")
					}
				},{
					name: "createContract", text: "生成合同", layout: "3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						var eiInfo = new EiInfo();
						if (checkRows.length > 0) {
							var info = new EiInfo()
							console.log(checkRows[0])
							if ("是"==checkRows[0].isCm){
								IPLAT.NotificationUtil("请选择未生成合同的招标记录","error");
							}else{
								var bidId = checkRows[0].id;
								var bidWinnerNo = checkRows[0].bidWinnerNo;
								var bidWinner = checkRows[0].bidWinner;
								var bidName = checkRows[0].bidName;
								var tendereeNo = checkRows[0].tendereeNo;
								var tenderee = checkRows[0].tenderee;
								var undertakeDeptNo = checkRows[0].undertakeDeptNo;
								var undertakeDeptName = checkRows[0].undertakeDeptName;
								var budget = checkRows[0].budget;
								info.set("bidId",bidId);
								info.set("bidWinnerNo",bidWinnerNo);
								info.set("bidWinner",bidWinner);
								info.set("bidName",bidName);
								info.set("tendereeNo",tendereeNo);
								info.set("tenderee",tenderee);
								info.set("undertakeDeptNo",undertakeDeptNo);
								info.set("undertakeDeptName",undertakeDeptName);
								info.set("budget",budget);
								IPLAT.confirm({
									message: '<b>确定生成合同吗？</b></i>',
									okFn: function (e) {
										EiCommunicator.send("CMZB01", "createContract", info, {
											onSuccess : function(ei) {
												NotificationUtil(ei.getMsg(), "success");
												resultGrid.dataSource.page(1);
											}
										});
									},
									cancelFn: function (e) {}
								})
							}
						} else {
							IPLAT.NotificationUtil("请选择需要生成合同的招标记录","error");
						}
					}
				},{
					name: "createProject", text: "生成项目", layout: "3",
					click: function () {
						alert("该功能不在本次评审范围内")
					}
				}]
			},
			dataBound: function (e) {

			},
			loadComplete: function (e) {

			}
		}
	}
});

function bidWindows(type,bidId){
	var url = IPLATUI.CONTEXT_PATH + "/web/CMZB0101?methodName=initLoad&type=" + type + "&bidId=" + bidId;
	var popData = $("#bidData");
	popData.data("kendoWindow").setOptions({
		open: function () {
			popData.data("kendoWindow").refresh({
				url: url,
			});
		}
	});
	// 打开弹窗
	bidDataWindow.open().center();
}