$(function(){
	/* 查询 */
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.page(1);
	});

	//重置
	$("#REQUERY").on("click",function(e){
		$("#inqu_status-0-billNo").val("")
		$("#inqu_status-0-complaintDept").val("")
		$("#inqu_status-0-complaintDateS").val("")
		$("#inqu_status-0-complaintDateE").val("")
		$("#inqu_status-0-complaintContent").val("")
		$("#inqu_status-0-dealDateS").val("")
		$("#inqu_status-0-dealDateE").val("")
		$("#inqu_status-0-dealWorkName").val("")
		$("#inqu_status-0-dealDept").val("")
		$("#inqu_status-0-returnDateS").val("")
		$("#inqu_status-0-returnDateE").val("")
		$("#inqu_status-0-returnWorkName").val("")
		$("#inqu_status-0-returnDesc").val("")
		resultGrid.dataSource.page(1);
	});
})
/* 获取选中的全局变量 */
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
					popData(" ","add");
				});
				
				/* 查看按钮 */
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要查看的记录", "error")
						//IPLAT.alert("请先选择一条记录进行查看");
					} else {
						popData(datagrid.id, "see");
					}
				});

				/* 编辑 */
				$("#EDIT").on("click", function() {
					if (datagrid == null) {
						NotificationUtil("请选择需要编辑的记录", "error")
						//IPLAT.alert("请先选择一条记录进行修改");
					} else {
						popData(datagrid.id, "edit", datagrid.billNo);
					}
				});
				
				 /* 删除 */
				$("#DELETE").on("click",function(e){
					resultGrid.dataSource.delete(1);
				});

			},
		}
	}

	/*新增,修改,查看窗口 */
 	function popData(id,type,billNo) {
		var url = IPLATUI.CONTEXT_PATH + "/web/CPPJ0101?initLoad&id="+id +"&type="+type+"&billNo="+billNo;
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
