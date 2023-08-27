var datagrid = null;
$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},

			loadComplete: function (grid) {
				//选房按钮
				$("#ACCEPT").unbind('click').on('click', function() {
					// 防止连续提交
					$("#ACCEPT").attr("disabled", true);
					setTimeout(function() {
						$("#ACCEPT").attr("disabled", false);
					}, 3000);
					var checkRows = resultGrid.getCheckedRows();
					if (checkRows.length > 1){
						NotificationUtil("请勿勾选多个宿舍", "error");
						return;
					}else if (checkRows.length < 1){
						NotificationUtil("请挑选一个心仪的宿舍入住", "error");
						return;
					}
					IPLAT.confirm({
						message: '<b>该选择操作只有一次机会！</br><font color="red">是否确定？</font></b>',
						okFn: function (e) {
							//提交数据
							var eiInfo = new EiInfo();
							var roomId = checkRows[0].roomId;
							var manId = checkRows[0].manId;
							var keepRoomDays = checkRows[0].keepRoomDays;
							var remainingBedNum = checkRows[0].remainingBedNum;
							var rent = checkRows[0].rent;
							var manageFee = checkRows[0].manageFee;
							var annualFee = checkRows[0].annualFee;
							eiInfo.set("roomId",roomId);
							eiInfo.set("manId",manId);
							eiInfo.set("keepRoomDays",keepRoomDays);
							eiInfo.set("remainingBedNum",remainingBedNum);
							eiInfo.set("actualRent",rent);
							eiInfo.set("actualManageFee",manageFee);
							eiInfo.set("annualFee",annualFee);
							EiCommunicator.send("DMXF01", "insert", eiInfo, {
								onSuccess : function(ei) {
									if(ei.msg!='操作成功'){
										IPLAT.alert({
											message: ei.msg,
											okFn: function (e) {
												setTimeout(function() {
													window.parent.location.reload()
												}, 600);
											},
											title: '注意'
										});
										IPLAT.NotificationUtil('操作成功');
									}else{
										IPLAT.NotificationUtil('操作成功');
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								}
							});
						},
						title: '提醒'
					});
				});
			}
		}
	}

});





	



	
	
