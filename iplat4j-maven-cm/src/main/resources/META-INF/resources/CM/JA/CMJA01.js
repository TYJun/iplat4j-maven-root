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
				$("#QUERY").on("click", function(e) {
					var contSignTimeS = $("#inqu_status-0-contSignTime").val();
					var contSignTimeE = $("#inqu_status-0-contSignTime1").val();
//					if(contSignTimeS==null||contSignTimeS==""){
//						NotificationUtil("起始日期不能为空", "error")
//						return;
//					}
//					if(contSignTimeE==null||contSignTimeE==""){
//						NotificationUtil("截止日期不能为空", "error")
//						return;
//					}
//					if(contSignTimeS>contSignTimeE){
//						NotificationUtil("起始日期不能大于截至日期", "error")
//						return;
//					}
					resultGrid.dataSource.page(1);
				});

				//重置
				$("#REQUERY").on("click",function(e){
					$("#inqu_status-0-contSignTime").val("");
					$("#inqu_status-0-contSignTime1").val("");
					$("#inqu_status-0-contNo").val("");
					$("#inqu_status-0-contName").val("");
					IPLAT.EFSelect.value($("#inqu_status-0-contTypeNum"),"");
					resultGrid.dataSource.page(1);
				});

				/* 结案*/
				$("#END").on("click",function(e){
					confirm
					if (datagrid == null) {
						NotificationUtil("请选择需要结案的记录", "error")
						return
					} 
					var eiInfo = new EiInfo();
					eiInfo.set("id",datagrid.id);
					datagrid = null;
					IPLAT.confirm({
						message: '<b>确定执行该操作吗？</b>',
						okFn: function (e) {
							EiCommunicator.send("CMJA01", "end", eiInfo, {
								onSuccess : function(ei) {
									if(ei.getStatus() === -1){
										IPLAT.alert(ei.getMsg());
									}else{
										NotificationUtil("结案成功", "success")
										/*  IPLAT.alert({
                                                 title: '温馨提示',
                                                 message: '结案成功'
                                             }); */
										window.parent['popDataWindow'].close();
										resultGrid.dataSource.page(1);
									}
								}
							})
						},
						cancelFn: function (e) {
						}
					})
				});

				/* 查看按钮 */
				$("#LOOK").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行查看");
						NotificationUtil("请选择需要查看的记录", "error")
					} else {
						popData(datagrid.id, "see");
					}
				}); 
			}
		}
}

/* 查看窗口 */
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