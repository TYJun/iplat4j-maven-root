/* 获取选中的全局变量 */
var datagrid = null;
$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	})

	//重置
	$("#REQUERY").on("click",function(e){
		$("#inqu_status-0-billNo").val("")
		$("#inqu_status-0-complaintDept").val("")
		$("#inqu_status-0-complaintName").val("")
		$("#inqu_status-0-complaintDateS").val("")
		$("#inqu_status-0-complaintDateE").val("")
		$("#inqu_status-0-complaintContent").val("")
		$("#inqu_status-0-dealDateS").val("")
		$("#inqu_status-0-dealDateE").val("")
		$("#inqu_status-0-dealWorkName").val("")
		$("#inqu_status-0-dealDept").val("")
		$("#inqu_status-0-returnDateS").val("")
		$("#inqu_status-0-returnDateE").val("")
		$("#inqu_status-0-returnWorkName").val("")
		$("#inqu_status-0-returnDesc").val("")
		resultGrid.dataSource.page(1);
	});

	if (__ei.labor == "labor"){
		$("#labor").show()
	}
})

IPLATUI.EFGrid = {
	"result": {
		toolbarConfig: {
			hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
			add: false, cancel: false, save: false, 'delete': false,
			buttons: [{
				name: "goods", text: "查看", layout: "3",
				click: function () {
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length < 0) {
						IPLAT.NotificationUtil("请选择一条数据", "error")
					}
					var id = checkRows[0].id;
					var billNo = checkRows[0].billNo;
					var url = IPLATUI.CONTEXT_PATH + "/web/CPZH0101?initLoad" + "&id=" + id +"&billNo=" +billNo;
					var popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open: function () {
							popData.data("kendoWindow").refresh({
								url: url,
							});
						}
					});
					// 打开工作流节点人员选择弹窗
					popDataWindow.open().center();
				}
			}]
		}
	},
	dataBound: function (e) {

	},
	loadComplete: function (e) {

	},
}

