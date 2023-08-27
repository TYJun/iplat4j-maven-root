/*获取选中的全局变量 */
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

				/* 编辑按钮 */
				$("#EDIT").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行修改");
						NotificationUtil("请选择需要变更的记录", "error")
					} else if(datagrid.contStatus != "执行") {
						NotificationUtil("请先将合同恢复", "error")
					} else{
						popDataWindow.setOptions({"title":"变更合同信息"});
						popData(datagrid.id, "edit");
					}
				});
				/* 冻结按钮 */
				$("#FREEZR").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要冻结的记录", "error")
						return
					}
					if (datagrid.contStatus == "冻结"){
						NotificationUtil("请选择未冻结的记录", "error")
						return
					}
					var eiInfo = new EiInfo();
					eiInfo.set("id",datagrid.id);
					datagrid = null;
					EiCommunicator.send("CMBG01", "freezr",eiInfo, {
						onSuccess : function(ei) {
							if(ei.getStatus() === -1){
								IPLAT.alert(ei.getMsg());
							}else{
								//IPLAT.alert("");
								NotificationUtil("冻结成功")
								window.parent['popDataWindow'].close();
								resultGrid.dataSource.page(1);
							}
						}
					})
				});
				/*恢复冻结按钮 */
				$("#RECOVER").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要恢复的记录", "error")
						return
					} 
					if (datagrid.contStatus == "执行"){
						NotificationUtil("请选择未恢复的记录", "error")
						return
					}
					var eiInfo = new EiInfo();
					eiInfo.set("id",datagrid.id);
					datagrid = null;
					EiCommunicator.send("CMBG01", "fecover", eiInfo, {
						onSuccess : function(ei) {
							if(ei.getStatus() === -1){
								IPLAT.alert(ei.getMsg());
							}else{
								//IPLAT.alert("恢复成功");
								NotificationUtil("恢复成功")
								window.parent['popDataWindow'].close();
								resultGrid.dataSource.page(1);
							}
						}
					})
				});
				/* 停止按钮 */
				$("#STOP").on("click",function(e){
					if (datagrid == null) {
						NotificationUtil("请选择需要终止的记录", "error")
						return
					} 
					if (datagrid.contStatus == "终止"){
						NotificationUtil("请选择未终止的记录", "error")
						return
					}
					var eiInfo = new EiInfo();
					eiInfo.set("id",datagrid.id);
					datagrid = null;
					EiCommunicator.send("CMBG01", "stop", eiInfo, {
						onSuccess : function(ei) {
							if(ei.getStatus() === -1){
								IPLAT.alert(ei.getMsg());
							}else{
								//IPLAT.alert("终止成功");
								NotificationUtil("终止成功")
								window.parent['popDataWindow'].close();
								resultGrid.dataSource.page(1);
							}
						}
					})
				});
				/* 查看按钮 */
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行查看");
						NotificationUtil("请选择需要查看的记录", "error")
					} else {
						popDataWindow.setOptions({"title":"查看合同信息"});
						popData(datagrid.id, "see");
						//popData(datagrid.id, "see");
						// var url = IPLATUI.CONTEXT_PATH + "/web/CMBG0101?initLoad&id="+datagrid.id +"&type=see";
						// var popData = $("#popData");
						// popData.data("kendoWindow").setOptions({
						// 	open : function() {
						// 		popData.data("kendoWindow").refresh({
						// 			url : url,
						// 		});
						// 	}
						// });
						// // 打开工作流节点人员选择弹窗
						// popDataWindow.open().center();
					}
				});
			}
		}
}

$(function(){
	/* 主页面查询按钮 */
	$("#QUERY").on("click",function(e){
		var contSignTimeS = $("#contSignTime").val();
		var contSignTimeE = $("#contSignTime1").val();
		if (contSignTimeS != "" && contSignTimeE != "") {
			if(contSignTimeS>contSignTimeE){
				NotificationUtil("签订日期起不能大于签订日期止", "error")
				return;
			}
		}

		resultGrid.dataSource.page(1);
	});

	//重置
	$("#REQUERY").on("click",function(e){
		$("#contSignTime").val("");
		$("#contSignTime1").val("");
		$("#contNo").val("");
		$("#contName").val("");
		IPLAT.EFSelect.value($("#contTypeNum"),"");
		resultGrid.dataSource.page(1);
	});
})

/* 新增,修改,查看窗口 */
function popData(id,type) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CMDJ0101?initLoad&id="+id +"&type="+type;
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
			}, 300)
		}
	});
	// 打开工作流节点人员选择弹窗
	popDataWindow.open().center();
} 

