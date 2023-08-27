var upLoadClicktag = 0;
var datagrid = null;
IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
}
$(function() {

	//医废数据字典分类击事件
	$("#QUERY1").on("click", function(e) {
		var checkRows = resultGrid.getCheckedRows(), eiInfo = new EiInfo();
		var listCheckRows = new Array();
		var map = new Map();
		//获取参数传递后端
		for (var i = 0; i < checkRows.length; i++) {
			var checkRowsValue = checkRows[i];
			var id = checkRowsValue.get("id");
			listCheckRows.push(id);
		}
		eiInfo.set("result", listCheckRows);
		//访问方法
		EiCommunicator.send("IMJZ010106", "queryTypegroup", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");

				//刷新页面
				setTimeout(function() {
					result2Grid.dataSource.page(1)
				}, 500);
			}
		}); 
	});


	$("#SUBMIT").click(function(){
		debugger;
		var list=result2Grid.getCheckedRows();
		window.parent.itemGrid.addRows(list);
		window.parent['popDataItemCardWindow'].close();
	});
}); 