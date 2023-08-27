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
				 
				/* 审批*/
				$("#EXAMINE").on("click",function(e){
					if (datagrid == null) {
						//IPLAT.alert("请先选择一条记录进行审批");
						NotificationUtil("请选择需要审批的记录", "error")
					} else {
						var eiInfo = new EiInfo();
						eiInfo.set("id",datagrid.id);
					
						EiCommunicator.send("CMDJ01","examine",eiInfo, {
							onSuccess : function(ei) {
								if(ei.getStatus() === -1){
									IPLAT.alert(ei.getMsg());
								}else{
									//IPLAT.alert("审批成功");
									NotificationUtil("审批成功", "success");
									window.parent['popDataWindow'].close();
									window.parent["resultGrid"].dataSource.query(1);
								}
							}
						})
					}
				});
			},
		}
	}

	$(function(){


	    /* 查询 */
		$("#QUERY").on("click",function(e){
			var contSignTimeS = $("#contSignTimeS").val();
			var contSignTimeE = $("#contSignTimeE").val();
			if (contSignTimeS != "" && contSignTimeE != "") {
				if(contSignTimeS>contSignTimeE){
					NotificationUtil("签订日期起不能大于签订日期止", "warning")
					return;
				}
			}
			resultGrid.dataSource.page(1);
		});

		//重置
		$("#REQUERY").on("click",function(e){
			$("#contSignTimeS").val("");
	        $("#contSignTimeE").val("");
			$("#contNo").val("");
			$("#contName").val("");
			$("#contDeptName").val("");
			resultGrid.dataSource.page(1);
		});
	})
	/*新增,修改,查看窗口 */
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
				}, 600)
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	} 