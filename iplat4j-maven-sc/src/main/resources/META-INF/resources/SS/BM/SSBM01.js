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
						EiCommunicator.send("SSBM01", "delete", eiInfo, {
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
						var typeName = rows[0].typeName;
						var mealTimeName = rows[0].mealTimeName;
						var menuName = rows[0].menuName;
						var discountAmount = rows[0].discountAmount;
						var typeCode = rows[0].typeCode;
						var mealTimeCode = rows[0].mealTimeCode;

					}else{
						NotificationUtil("没有勾选数据", "error");
						return;
					}

					// console.log(htb)
					eiInfo.set("typeName", typeName);
					eiInfo.set("mealTimeName", mealTimeName);
					eiInfo.set("menuName", menuName);
					eiInfo.set("discountAmount", discountAmount);
					eiInfo.set("typeCode", typeCode);
					eiInfo.set("mealTimeCode", mealTimeCode);
					// eiInfo.set("htb", htb);
					EiCommunicator.send("SSBM01", "insert", eiInfo, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							setTimeout(function() {
								window.parent.location.reload()
							}, 600);
						}
					});
				});


				//新增按钮
				// $("#ADD").on("click", function(e) {
				// 	popData("", "add");
				// });

			},
		}
	}


	$(function() {

	});