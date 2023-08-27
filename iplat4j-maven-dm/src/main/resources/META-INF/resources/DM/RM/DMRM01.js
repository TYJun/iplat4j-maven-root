
var datagrid = null;
//查询
$(function(){
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
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
			// 这里将按钮事件绑定放到EFGrid的loadComplate回调函数里解决
			// 不能绑定问题
			//新增按钮
			$("#ADD").on("click", function(e) {
				popData(0,"add");
				resultGrid.dataSource.page(1);
			});
			//修改按钮
			$("#EDIT").on("click", function(e) {
				 if (datagrid == null) {
					IPLAT.alert("请先选择一条记录进行修改");
					 resultGrid.dataSource.page(1);
				} else {
					popData(datagrid.id, "edit");
					datagrid = null;
					 resultGrid.dataSource.page(1);
				}
			});
		}
	}
}


function popData(id,type) {
	if(type === "add"){
		var url = IPLATUI.CONTEXT_PATH + "/web/DMRM0101?initLoad&type=add&id=1";
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		popDataWindow.open().center();
	}else{
		var url = IPLATUI.CONTEXT_PATH + "/web/DMRM0101?initLoad&type="+type+"&id="+id;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			title:"编辑",
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		popDataWindow.open().center();
	}
}

