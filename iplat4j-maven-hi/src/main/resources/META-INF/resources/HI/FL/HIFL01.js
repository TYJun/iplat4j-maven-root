$(function(){

	/**
	 * 医院标识分类树控件
	 * @type {{tree01: {select: IPLATUI.EFTree.tree01.select, ROOT: {label: string, text: string, leaf: boolean}}}}
	 */
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {label: "root", text: "医院标识分类", leaf: true},
			select: function(e) {
				let _data = this.dataItem(e.node);
				$("#inqu_status-0-parentId").val(_data.label);
				resultGrid.dataSource.page(1);
			},
		}
	};

	/**
	 * 表格控件
	 * @type {{result: {loadComplete: IPLATUI.EFGrid.result.loadComplete}}}
	 */
	IPLATUI.EFGrid = {
		"result" : {
			loadComplete:function (e) {
				//新增按钮
				$("#ADD").on("click", function(e) {
					popData("", "add", "HIFL0101");
				});
				
				//编辑按钮
				$("#EDIT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录进行修改","warning");
					} else {
						popData(checkRows[0].id, "edit", "HIFL0101");
					}
				});

				/* 删除 */
				$("#REDELETE").on("click",function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if(checkRows.length <= 0) {
						NotificationUtil("请选择需要删除的记录", "warning")
					}else {
						var eiInfo = new EiInfo();
						var id = checkRows[0].id;
						eiInfo.set("id",id);
						EiCommunicator.send("HIFL01", "delete", eiInfo, {
							onSuccess : function(ei) {
								if (ei.getStatus() === -1) {
									IPLAT.alert(ei.getMsg());
								} else {
									NotificationUtil("删除成功", "success");
									resultGrid.dataSource.page(1);
								}
							}
						});
					}
				});




			}
		}
	}

	/**
	 * 查询
	 */
	$("#QUERY").on("click", function () {
		 resultGrid.dataSource.page(1);
	})

	/**
	 * 重置
	 */
	$("#REQUERY").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
});

/**
 * 打开新增/编辑窗口
 * @param id
 * @param type
 * @param service
 */
function popData(id, type, service) {
	let url = IPLATUI.CONTEXT_PATH + "/web/"+ service +"?initLoad&id=" + id + "&type=" + type;
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}