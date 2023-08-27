$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onRowClick : function (e) {
				e.preventDefault();
				$("#mainEnterBillNo").val( e.model['enterBillNo']);
				window["detailGrid"].dataSource.page(1);
			},
			loadComplete:function (e) {
				//入库验收
				$("#YSHE").unbind('click').on('click', function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要审核入库记录","error");
						return;
					}
					IPLAT.confirm({
						message: '<b>您确定要审核吗？</b>',
						okFn: function (e) {
							let list = checkRows.map(row => row['enterBillNo']);
							//设置电子签名属性
							setConfig(__ei['workNo'], __ei['name'], "SIC");
							//验收签名
							getSign(fileCode => {
								let eiInfo = new EiInfo();
								eiInfo.set("signature", fileCode);
								eiInfo.set("list", list);
								EiCommunicator.send("SIRK05", "checkApproval", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("审核成功");
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