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
			// dataBound: function (e) {
			// 	var grid = e.sender;
			// 	var trs = grid.table.find("tr");
			// 	$.each(trs, function (i, tr) {
			// 		var planFinishTimeRed = e.sender.dataItems()[i].planFinishTimeRed;
			// 		console.log(planFinishTimeRed);
			// 		if (planFinishTimeRed=='1') {
			// 			var td = $(trs[i]).find('td').eq(1);
			// 			td.css('color','gray');
			// 		}else if(planFinishTimeRed=='2'){
			// 			var td = $(trs[i]).find('td').eq(1);
			// 			td.css('color','red');
			// 		}
			// 	});
			// },
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
						popData(datagrid.id, "edit");
					}
				});
				
				 /* 删除 */
				$("#DELETE").on("click",function(e){
					resultGrid.dataSource.delete(1);
				});
			},
		}
	}

	$(function(){
	    /* 查询 */
		$("#QUERY").on("click",function(e){
			var lubricateSpotS = $("#lubricateSpotS").val();
			var lubricateSpotE = $("#lubricateSpotE").val();
			if (lubricateSpotS != "" && lubricateSpotE != "") {
				if(lubricateSpotS>lubricateSpotE){
					NotificationUtil("保洁日期起不能大于保洁日期止", "warning")
					return;
				}
			}
			resultGrid.dataSource.page(1);
		});

		//重置
		$("#REQUERY").on("click",function(e){
			$("#lubricateDateS").val("");
	        $("#lubricateDateE").val("");
			$("#lubricateNo").val("");
			$("#machineName").val("");
			$("#fixedPlace").val("");
			resultGrid.dataSource.page(1);
		});
	})
	/*新增,修改,查看窗口 */
 	function popData(id,type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/DFRH0101?initLoad&id="+id +"&type="+type;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			    setTimeout(function(){
					if(type=="see"){
						var frame = popData.find("iframe")[0].contentWindow.$("#SAVEPR").hide();
						//var frame = popData.find("iframe")[0].contentWindow.$("#SAVEPR").hide();
					}
				}, 600)
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	} 