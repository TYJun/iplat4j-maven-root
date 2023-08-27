$(function(){

	/**
	 * 维修事项分类树控件
	 * @type {{tree01: {select: IPLATUI.EFTree.tree01.select, ROOT: {label: string, text: string, leaf: boolean}}}}
	 */
	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {label: "root", text: "医院标识地点名称分类", leaf: true},
			select: function(e) {
				let _data = this.dataItem(e.node);
				if(_data.pId == "root"){
					$("#building").val(_data.label);
				}else {
					$("#building").val(_data.pId);
					$("#floor").val(_data.label);
				}

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
				//查看按钮
				$("#LOOK").on("click",function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录进行查看","warning");
					} else {
						popData(checkRows[0].id, "see","HICX0101");
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