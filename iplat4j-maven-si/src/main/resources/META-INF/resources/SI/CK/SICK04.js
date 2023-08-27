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
				$("#CKQS").unbind('click').on('click', function(e){
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请选择需要签收记录","error");
						return;
					}
					IPLAT.confirm({
						message: '<b>您确定要签收吗？</b>',
						okFn: function (e) {
							let list = checkRows.map(row => row['outBillNo']);
							//设置电子签名属性
							setConfig(__ei['workNo'], __ei['name'], "SIO");
							//验收签名
							getSign(fileCode => {
								let eiInfo = new EiInfo();
								eiInfo.set("list", list);eiInfo.set("signature", fileCode);
								EiCommunicator.send("SICK04", "signOut", eiInfo, {
									onSuccess: function (ei) {
										if (ei.getStatus() == -1) {
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil("签收成功");
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

				//已签收记录
				$("#SIGNRECORD").unbind('click').on('click', function(e){
                       popData();
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


	//出库录入
	function popData() {
		let url = IPLATUI.CONTEXT_PATH + "/web/SICK0106";
		let popData = $("#popData1");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		popData1Window.open().center();
	}

});
	
	