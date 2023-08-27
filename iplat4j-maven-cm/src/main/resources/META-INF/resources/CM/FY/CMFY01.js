var datagrid = null;
IPLATUI.EFGrid = {
		"result" : {
			page: function (e) {
				datagrid = null;
			},
			onRowClick : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}				
			},
			onCheckRow : function(e){
				if (!e.fake) {
					var flag = datagrid;
					console.log(flag);
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
					console.log(datagrid);
					if(flag == datagrid){
						datagrid = null;
					}
				}
			},
			onDelete : function(e){
				datagrid = null;
			},
			loadComplete: function (e) {
				//新增按钮
				$("#ADD").on("click",function(e){
					popDataWindow.setOptions({"title":"新增合同费用定义"});
					popData("","add");			
				});

				//查看
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要查看的记录", "error")
						//IPLAT.alert("请先选择一条记录进行查看");
					} else {
						popDataWindow.setOptions({"title":"查看合同费用定义"}); 
						popData(datagrid.id, "see");

					}
				}); 

				/* 编辑 */
				$("#EDIT").on("click", function() {
					if (datagrid == null) {
						NotificationUtil("请选择需要编辑的记录", "error")
						//IPLAT.alert("请先选择一条记录进行修改");
					} else {
						popDataWindow.setOptions({"title":"编辑合同费用定义"});
						popData(datagrid.id, "edit");
					}
				});
			}
		}
}

$(function(){
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.page(1);
	});

	//重置
	$("#REQUERY").on("click",function(e){
		$("#contCostName").val("");
		resultGrid.dataSource.page(1);
	});

	$("#DELETE").on("click",function(e){
		resultGrid.dataSource.delete(1);
	});
})

function popData(id,type) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CMFY0101?initLoad&id="+id+"&type="+type;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
			setTimeout(function(){
				if(type=="see"){
					var frame = popData.find("iframe")[0].contentWindow.$("#SAVEPR").hide();
				}
			}, 600)
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
} 