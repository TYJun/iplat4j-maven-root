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
			dataBinding: function (e) {
				for (var i = 0, length = e.items.length; i < length; i++){
					if(isAvailable(e.items[i].budget)){
						e.items[i].budget = parseFloat(e.items[i].budget);
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
		}
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
//		if(contSignTimeS>contSignTimeE){
//			NotificationUtil("起始日期不能大于截止日期", "error")
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
		IPLAT.EFSelect.value($("#contTypeNum"),"");
		resultGrid.dataSource.page(1);
	});


})