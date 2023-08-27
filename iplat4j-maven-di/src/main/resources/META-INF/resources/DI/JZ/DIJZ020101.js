
//page参数
var datagrid = null;
var id = null;
var arr = [];


/**
 * 设备系统分类菜单树
 */
$(function() {
	IPLATUI.EFTreeInput = {
			"machineTypeId":{
				/**
				 * Tree 中数据回填触发事件
				 * e.tree Tree 对象
				 * e.node 待回填的树结点数据对象（勾选回填时是集合）
				 */
				backFill: function (e) {
					$("#machineTypeId").val(e.node.id);
					resultGrid.dataSource.page(1);
				},
			}
	};
	IPLATUI.EFGrid = {
			"result":{
				onCheckRow : function(e) {
					if (!e.fake) {
						var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
						datagrid = model;
						id = datagrid.id;
						arr.push(id);
					}
				},
				loadComplete:function(){
					//确定
					$("#ADD").on("click", function(e) {
						debugger;
						if (0 == arr.length) {
							IPLAT.alert("请选择需要巡查的设备");
							return;
						}else{
						//var grid = $("#ef_grid_result").data("kendoGrid");
						var data = resultGrid.getCheckedRows();// 获取选中行数据
						window.parent.save(data);
						window.parent['popDataWindow'].close();
						}
					});
				},
				//pageable:false,
				//exportGrid:false,
			},
	}
	
	//重置按钮
	$("#RESET").on("click", function (e) {
		$("#inqu_status-0-machineName").val("");
		$("#inqu_status-0-machineCode").val("");
		$("#machineTypeId").val("");
		resultGrid.dataSource.query(1);
	});
	
	//查询按钮
	$("#QUER").on("click", function(e) {
		
		resultGrid.dataSource.page(1);
	})
});