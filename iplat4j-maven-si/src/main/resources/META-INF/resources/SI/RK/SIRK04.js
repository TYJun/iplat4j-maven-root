$(function() {
	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {
			pageable: {
				pageSize: 50, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
				pageSizes: [10, 20, 50, 100, 500, 1000]
			}
		}
	})

	IPLATUI.EFGrid = {
		"result" : {
			onRowClick : function (e) {
				e.preventDefault();
				$("#mainEnterBillNo").val( e.model['enterBillNo']);
				window["detailGrid"].dataSource.page(1);
			},
			loadComplete:function (e) {
				//入库验收
				$("#RKYS").unbind('click').on('click', function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要验收入库记录","error");
						return;
					}
					IPLAT.confirm({
						message: '<b>您确定要验收吗？</b>',
						okFn: function (e) {
							let list = checkRows.map(row => row['enterBillNo']);
							//设置电子签名属性
							setConfig(__ei['workNo'], __ei['name'], "SIC");
							//验收签名
							getSign(fileCode => {
								let eiInfo = new EiInfo();
								eiInfo.set("list", list);eiInfo.set("signature", fileCode);
								eiInfo.set("token", JSON.stringify(list));
								EiCommunicator.send("SIRK04", "enterCheck", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("验收成功");
										window["resultGrid"].dataSource.page(1);
										$("#mainEnterBillNo").val('no data');
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