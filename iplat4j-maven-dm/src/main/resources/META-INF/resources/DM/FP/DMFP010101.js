$(function() {

	//查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {
				$("#CHOOSEBUTTON").on("click", function (e) {
					//选择数据保存到上级页面
					var checkRows = resultGrid.getCheckedRows();
					console.log(checkRows);
					if(checkRows.length < 1){
						NotificationUtil("请至少选择一条数据", "error");
						return;
					}
					window.parent.resultDormItemGrid.addRows(checkRows);
					//window.parent.save(checkRows);
					window.parent['popDataItemWindow'].close();
				});
			}
		}
	};

});

// 接收其父窗口传来的数值，并借由这些数值进行自己页面的查询.
function setData(e){
	// e: 为其父窗口传来的数值.
	console.log(e);
	var roomIdList = "";
	// 判断是否有值.
	if (e.length > 0){
		// 对其中每一行进行遍历.
		for (var i = 0; i < e.length; i++) {
			// 将事项编码进行拼接并以","隔开。
			roomIdList += e[i].roomId + ",";
		}
		// 将拼接的字符串最后一位的","去除.
		roomIdList = roomIdList.substr(0, roomIdList.length - 1);
		console.log(roomIdList);
		IPLAT.EFInput.value($("#roomIdList"),roomIdList);
		// 自动执行一次查询按钮操作.
		$("#QUERY").click();
	}
}

