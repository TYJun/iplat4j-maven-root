$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "SAVE",text: "确定",layout:"3",
					click: function () {
						let checkRows = resultGrid.getCheckedRows()
						if (checkRows.length < 1) {
							NotificationUtil("请先选择记录","error");
						} else{
							window.parent.addRows(checkRows);
							window.parent["popDataWindow"].close();
						}
					}
				}]
			},
		}
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");

	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
});