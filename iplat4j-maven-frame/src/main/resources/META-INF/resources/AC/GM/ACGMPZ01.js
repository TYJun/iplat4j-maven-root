var datagrid = null;


	IPLATUI.EFGrid = {
		"result" : {
			loadComplete: function (grid) {
				//查询
				$("#QUERY").on("click", function(e) {
					resultGrid.dataSource.query(1);
				});

				//删除按钮
				$("#DELETES").on("click", function (e) {

					// var id;
					var rows = resultGrid.getCheckedRows();
					if(rows.length > 0){
					var	id = rows[0].id;
					}else{
						NotificationUtil("没有勾选数据", "error");
						return;
					}
					var eiInfo = new EiInfo();
					eiInfo.set("id", id);
						EiCommunicator.send("ACGMPZ01", "delete", eiInfo, {
							onSuccess : function(ei) {
								if(ei.getMsg()=='删除成功'){
								    NotificationUtil(ei.getMsg(), "success");
									setTimeout(function() {
										window.parent.location.reload()
									}, 500);
								}else{
									NotificationUtil(ei.getMsg(), "faild");
									}
								resultGrid.dataSource.page(1);
								}
						});
				});

				// 新增按钮
				$("#ADD").on("click", function(e) {
					// 防止连续提交
					$("#REGISTERDROM").attr("disabled",true);
					setTimeout(function(){
						$("#REGISTERDROM").attr("disabled",false);
					},3000);

					var eiInfo = new EiInfo();
					console.log("新增");
					var url = IPLATUI.CONTEXT_PATH + "/web/ACGMPZ0101";
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



			},
		}
	}

