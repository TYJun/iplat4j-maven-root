$(function() {
	//新增
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		//获取参数
		var wareHouseNo = IPLAT.EFSelect.value( $("#wareHouseNo") );
		var matTypeNum = $("#matTypeNum").val();
		//保存数据
		var eiInfo = new EiInfo();
		eiInfo.set("wareHouseNo", wareHouseNo);
		eiInfo.set("matTypeNum", matTypeNum);
		if (wareHouseNo == null || wareHouseNo == "") {
			NotificationUtil("请先选择仓库","error");
		} else {
			IPLAT.confirm({
				message: '<b>生成盘库单时会冻结此仓库,确定要生成盘库单吗？</b>',
				okFn: function (e) { 
					EiCommunicator.send("SIKC0102", "generateStockInven", eiInfo, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg());
							window.parent['popData1Window'].close();
						}
					})
				},
				cancelFn: function (e) {}
			})
		}
	});

	//关闭
	$("#CLOSE").on("click", function(e) {
		window.parent['popData1Window'].close();
	});

})