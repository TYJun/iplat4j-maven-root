$(function() {
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
	//查询方法
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
});
IPLATUI.EFGrid = {
	"result": {
		loadComplete: function (grid) {
			//新增按钮
			$("#ADD").on("click", function(e) {
				var url = IPLATUI.CONTEXT_PATH + "/web/SQWJ02?initLoad";
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
			//编辑问卷
			$("#UPDT").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length<1){
					NotificationUtil("请选择要编辑的问卷", "error");
					return;
				}
				var id = checkRows[0].id;
				var url = IPLATUI.CONTEXT_PATH + "/web/SQWJ02?initLoad&id=" + id;
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

			//终止按钮
			$("#UPD").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length<1){
					NotificationUtil("请选择要终止的问卷", "error");
					return;
				}
				var id = checkRows[0].id;
				var eiInfo = new EiInfo();
				eiInfo.set("id",id);
				EiCommunicator.send("SQWJ01", "update", eiInfo, {
					onSuccess : function(ei) {
						var status = ei.getStatus();
						if(status == -1){
							IPLAT.alert(ei.getMsg());
						}else{
							setTimeout(function() {
								NotificationUtil(ei.getMsg(), "success");
								window.parent.location.reload()
							}, 300);
						}
					}
				})
			})
		}
	}
}

