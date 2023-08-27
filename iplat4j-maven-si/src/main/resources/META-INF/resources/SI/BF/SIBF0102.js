$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "SAVE",text: "通过",layout:"3",
					click: function () {
						//参数
						let eiInfo = new EiInfo();
						eiInfo.setByNode("inqu");
						//发送请求
						EiCommunicator.send("SIBF0102", "confirm", eiInfo, {
							onSuccess: function (ei) {
								if (ei.getStatus() == -1) {
									NotificationUtil(ei.getMsg(), "error");
									return;
								}

								NotificationUtil("保存成功", "success");
								window.parent['popDataWindow'].close();
								window.parent['resultGrid'].dataSource.page(1);
							},
							onFail: function (errorMsg, status, e) {
								NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
							}
						});
					}
				}]
			},
		}
	}
});