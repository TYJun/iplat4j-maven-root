//page参数
var datagrid = null;
var id = null;
var arr = [];

$(function() {
	IPLATUI.EFGrid = {
			"result": {
				onCheckRow : function(e) {
					if (!e.fake) {
						var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
						datagrid = model;
						id = datagrid.id;
						arr.push(id);
					}
				},
				loadComplete: function (grid) {
					$("#ADD").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ020101?operType=DETERMINE";
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
					//确定按钮
					$("#DETERMINE").on("click", function (e) {
						//获取参数
						var dangerwhere = $("#dangerwhere").val();
						var requrieDesc = $("#requiredesc").val();
						var grid = $("#ef_grid_result").data("kendoGrid");
						if(null == dangerwhere || "" == dangerwhere){
							IPLAT.alert("请输入设备包名");
							return;
						}
						grid.checkAllRows();
						var data = grid.getCheckedRows();
						var eiInfo = new EiInfo();
						eiInfo.set("data",data);
						eiInfo.set("dangerwhere",dangerwhere);
						eiInfo.set("requrieDesc",requrieDesc);
						EiCommunicator.send("IMJZ0201", "insr", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
								setTimeout(function() {
									window.parent.location.reload()
								},300);  
							}
						});
						grid.unCheckAllRows();
					});
					//删除按钮
					$("#DELETEROW").on("click", function (e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}else{
							var grid = $("#ef_grid_result").data("kendoGrid");
							grid.removeRows(checkRows);
						}
					})
				},
				/* 隐藏EF:EFGrid功能按钮 */
				pageable : false,
				exportGrid : false,
				//设置分页
				pageable : {
					pageSize : 1000,
				}
			}
	};
	save = function(rows) {
		//debugger;
		//关闭子页面
		popDataWindow.close();
		var grid = $("#ef_grid_result").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		//去重
		data = mergeArray(data, rows)
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	//两数组去除重复数值
	function mergeArray(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].moduleId;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].moduleId;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
})