$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	IPLATUI.EFGrid = {
			"result": {
				loadComplete: function (grid) {
					$("#ADD").on("click", function (e) {
						//选择数据保存到上级页面
						var checkRows = resultGrid.getCheckedRows();
						//var	pcheckRows=window.parent.itemGrid.getRows();
						if(checkRows.length < 1){
							NotificationUtil("请至少选择一条数据", "error");
							return;
						}
						for (var i = 0; i < checkRows.length; i++) {
							
							var model = createModel(i);
							var par = checkRows[i];
							
							var id = par.id;
							var content = par.content;
							var projectDesc = par.projectDesc;
							var actualValueUnit = par.actualValueUnit;
							
							model.itemID_xmID = id;
							model.jitemName = content;
							model.itemDesc = projectDesc;
							model.actualValueUnit = actualValueUnit;
						 //遍历渲染参数，必须存model对象
						 window.parent.itemGrid.addRows(model);
						}
						window.parent['popDataItemWindow'].close();
				    });
					$("#DELETEROW").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						resultGrid.removeRows(checkRows);
				    });
				}
			}
		}
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "itemID_xmID": {type: "string"},
		        "jitemName": {type: "string"},
		        "itemDesc": {type: "string"},
		        "actualValueUnit": {type: "string"},
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}
 });
