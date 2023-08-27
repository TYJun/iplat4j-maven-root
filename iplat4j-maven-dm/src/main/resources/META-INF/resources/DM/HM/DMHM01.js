
var datagrid = null;
$(function(){
	//查询
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
			//新增按钮
			$("#ADD").on("click", function(e) {
				popData(0,"add");
			});
			
			//编辑按钮
			$("#EDIT").on("click", function(e) {
				 if (datagrid == null) {
					IPLAT.alert("请先选择一条记录进行操作");
				} else {
					popData(datagrid.id,"edit");
					datagrid = null;
				}
			});
		}
	}
}

function popData(id,type) {
	if(type === "add"){
		var url = IPLATUI.CONTEXT_PATH + "/web/DMHM0101?initLoad&type=add&id=1";
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
		var url = IPLATUI.CONTEXT_PATH + "/web/DMHM0101?initLoad&type="+type+"&id="+id;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		popDataWindow.open().center();
	}
}
	
	
