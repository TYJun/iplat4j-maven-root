var datagrid = null;


	IPLATUI.EFGrid = {
		"result" : {
			loadComplete: function (grid) {
				//查询
				$("#QUERY").on("click", function(e) {
					resultGrid.dataSource.query(1);
				});

				//编辑按钮
				$("#EDIT").on("click", function(e) {
					if (datagrid == null) {
						IPLAT.alert("请先选择一条记录进行修改");
					} else {
						popData(datagrid.id, "edit");
						datagrid = null;
					}
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
						EiCommunicator.send("RMPZ03", "delete", eiInfo, {
							onSuccess : function(ei) {
								if(ei.getMsg()=='删除成功'){
								    NotificationUtil(ei.getMsg(), "success");
								}else{
									NotificationUtil(ei.getMsg(), "faild");
									}
								resultGrid.dataSource.page(1);
								}
						});
				});

				$("#SAVES").unbind('click').on('click', function(){
					// 防止连续提交
					$("#REGISTERDROM").attr("disabled",true);
					setTimeout(function(){
						$("#REGISTERDROM").attr("disabled",false);
					},3000);

					var eiInfo = new EiInfo();
					// var htbs = resultGrid.getDataItems();
					//获取tab数据
					var rows = resultGrid.getCheckedRows();
					if(rows.length > 0){
						var matCode = rows[0].matCode;
						var matName = rows[0].matName;

					}else{
						NotificationUtil("没有勾选数据", "error");
						return;
					}

					// console.log(htb)
					eiInfo.set("matCode", matCode);
					eiInfo.set("matName", matName);

					// eiInfo.set("htb", htb);
					EiCommunicator.send("RMPZ03", "insert", eiInfo, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							setTimeout(function() {
								window.parent.location.reload()
							}, 600);
						}
					});
				});


				// 新增按钮
				// $("#ADD").on("click", function(e) {
				// 	popData("", "add");
				// });

			},
		}
	}


	$(function() {

	});
