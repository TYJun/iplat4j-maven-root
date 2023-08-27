$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onRowClick : function (e) {
				e.preventDefault();
				$("#mainOutBillNo").val( e.model['outBillNo']);
				window["detailGrid"].dataSource.page(1);
			},
			loadComplete:function (e) {
				//出库签收
				$("#STOCK_SIGN").unbind('click').on('click', function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要签字记录","error");
						return;
					}
					IPLAT.confirm({
						message: '<b>您确定要签字吗？</b>',
						okFn: function (e) {
							let list = checkRows.map(row => row['outBillNo']);
							//设置电子签名属性
							setConfig(__ei['workNo'], __ei['name'], "SIO");
							//验收签名
							getSign(fileCode => {
								let eiInfo = new EiInfo();
								eiInfo.set("list", list);eiInfo.set("signature", fileCode);
								EiCommunicator.send("SICK05", "outStockSign", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("签字成功");
										window["resultGrid"].dataSource.page(1);
										$("#mainOutBillNo").val('no data');
										window["detailGrid"].dataSource.page(1);
									}
								});
							})
						},
						cancelFn: function (e) {}
					})
				});

				//结束
				$("#OVER_SIGN").unbind('click').on('click', function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要结束的记录","error");
						return;
					}
					IPLAT.confirm({
						message: '<b>您确定要结束吗？</b>',
						okFn: function (e) {
							let list = checkRows.map(row => row['outBillNo']);
							//设置电子签名属性
							setConfig(__ei['workNo'], __ei['name'], "SIO");
							//验收签名
							getSign(fileCode => {
								let eiInfo = new EiInfo();
								eiInfo.set("list", list);eiInfo.set("signature", fileCode);
								EiCommunicator.send("SICK05", "overOutStockSign", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("结束成功");
										window["resultGrid"].dataSource.page(1);
										$("#mainOutBillNo").val('no data');
										window["detailGrid"].dataSource.page(1);
									}
								});
							})
						},
						cancelFn: function (e) {}
					})
				});
			}
		}
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");

	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resetTime(false);
		resultGrid.dataSource.page(1);
	});
});
	
	