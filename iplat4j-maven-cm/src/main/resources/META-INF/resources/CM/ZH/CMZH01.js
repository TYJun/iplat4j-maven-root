/* 获取选中的全局变量 */
var datagrid = null;
IPLATUI.EFGrid = {
		"result" : {
			page: function (e) {
				datagrid = null;
			},
			dataBinding: function (e) {
				for (var i = 0, length = e.items.length; i < length; i++){
					if(isAvailable(e.items[i].budget)){
						e.items[i].budget = parseFloat(e.items[i].budget);
					}
				}
			},
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "contNo") {
					var contNo = e.model['contNo'];
					if (contNo != " " && contNo != null) {
						popData(datagrid.id, "see");
					}
				}
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
			dataBound: function (e) {
				var grid = e.sender;
				var trs = grid.table.find("tr");
				$.each(trs, function (i, tr) {
					var planFinishTimeRed = e.sender.dataItems()[i].planFinishTimeRed;
					var contStatus =e.sender.dataItems()[i].contStatus
					console.log(planFinishTimeRed);
					if(contStatus=='执行' || contStatus=='登记') {
						if (planFinishTimeRed == '9') {
							var td = $(trs[i]).find('td').eq(2);
							td.css('color', 'red');
						} else if (planFinishTimeRed == '8') {
							var td = $(trs[i]).find('td').eq(2);
							td.css('background', 'rgb(212,21,21)');
							td.css('color', 'rgb(255,255,255)');
						} else if (planFinishTimeRed == '7') {
							var td = $(trs[i]).find('td').eq(2);
							td.css('background', 'rgb(243,146,0)');
							td.css('color', 'rgb(255,255,255)');
						} else if (planFinishTimeRed == '6') {
							var td = $(trs[i]).find('td').eq(2);
							td.css('background', '#0e498c');
							td.css('color', 'rgb(255,255,255)');
						}
					}
				});
			},
			onDelete : function(e){
				datagrid = null;
			},
		}
}

/*新增,修改,查看窗口 */
function popData(id,type) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CMZH0101?initLoad&id="+id +"&type="+type;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
}

$(function() {
	$("#QUERY").on("click", function(e) {
		var contSignTimeS = $("#contSignTimeS").val();
		var contSignTimeE = $("#contSignTimeE").val();
//		if(contSignTimeS==null||contSignTimeS==""){
//			NotificationUtil("起始日期不能为空", "error")
//			return;
//		}
//		if(contSignTimeE==null||contSignTimeE==""){
//			NotificationUtil("截止日期不能为空", "error")
//			return;
//		}
		if (contSignTimeS != "" && contSignTimeE != "") {
			if(contSignTimeS>contSignTimeE){
				NotificationUtil("签订日期起不能大于签订日期止", "warning")
				return;
			}
		}
		resultGrid.dataSource.page(1);
	})

	//重置
	$("#REQUERY").on("click",function(e){
		$("#contSignTimeS").val("");
		$("#contSignTimeE").val("");
		$("#contNo").val("");
		$("#contName").val("");
		IPLAT.EFSelect.value($("#contStatus"),"");
		IPLAT.EFSelect.value($("#contTypeNum"),"");
		resultGrid.dataSource.page(1);
	});


})