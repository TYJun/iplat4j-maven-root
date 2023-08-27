$(function() {
	
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	$("#RESET").on("click", function(e) {
		$("#spotNum").val("");
		$("#spotName").val("");
		$("#NFCCode").val("");
		resultGrid.dataSource.page(1);
	});
	
	window.setInterval(function() {
		readCard();
	}, 2000);
	IPLATUI.EFGrid = {
		"result" : {
			loadComplete : function(grid) {
				$("#SENDCARD").on("click", function(e) {
					var row = resultGrid.getCheckedRows()[0];
					var checkedSpotNum = row.spotNum;
					var id = row.id;
					sendCard(id);
					if (resultCode != "1") {
						NotificationUtil("发卡失败", "error");
						return;
					}
					var info = new EiInfo();
					info.set("cardCode", currentCardCode);
					info.set("checkedSpotNum", checkedSpotNum);
					info.set("spotId", id);
					EiCommunicator.send("DIFK01", "sendCard", info, {
						onSuccess : function(ei) {
							NotificationUtil("发卡成功", "success");
						},
						onFail : function(ei) {
							NotificationUtil("调用失败，原因[" + ei + "]", "error");
						}
					});
				});
				
				//生成二维码
				$("#QRCODE").on("click", function(e) {
					var row = resultGrid.getCheckedRows()[0];
					var id = row.id;
					var spotName = row.spotName;
					popData(id,spotName);
				});
				function popData(id,spotName) {
					var url = IPLATUI.CONTEXT_PATH + "/web/DIFK0101?&id="+id+"&spotName="+spotName;
					var popData = $("#popData");
					
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url,
							});
						}
					});
					// 打开生成弹窗
					popDataWindow.open().center();
				}
			}
		}
	};
});
var currentCardCode = "";
var spotName = "";
var spotNum = "";
var resultCode="";
function readCard() {
	var params = {};
	params["userNo"] = "localUser";
	params["userName"] = "本地用户";
	params["type"] = "getCardNo";
	params["token"] = "123456";
	$.ajax({
		url : "http://127.0.0.1:9999/",
		type : "post",
		cache : false,
		csrf : false,
		async : false,
		data : JSON.stringify(params),
		success : function(data) {
			var result = JSON.parse(data);
			console.log(result);
			var code = result.respCode;
			var nfcCode = result.obj;
			if (nfcCode == null) {
				return;
			}
			currentCardCode = nfcCode;
			if (code != "200") {
				NotificationUtil("系统错误", "error");
			} else {
				getSpotName(nfcCode);
				//readCardDetail();
				if (spotName == "无") {
					NotificationUtil("该卡片未绑定数据", "success");
					return;
				}
				NotificationUtil("卡片对应地点为：" + spotName, "success");
			}
		}
	});
}
//想看有没有发卡成功就调这个
function readCardDetail(){
	var params = {};
	params["userNo"] = "localUser";
	params["userName"] = "本地用户";
	params["type"] = "readData";
	params["token"] = "123456";
	params["cardNo"] = currentCardCode;
	params["section"] = 0;
	params["block"] = 1;
	params["keyA"]="FFFFFFFF";
	params["keyB"]="";
	$.ajax({
		url : "http://127.0.0.1:9999/",
		type : "POST",
		cache : false,
		csrf : false,
		async : false,
		selfThis:this,
		data : {params:JSON.stringify(params)},
		success : function(data) {
			console.log(data);
			
		}
	});
}
function sendCard(id) {
	var params = {};
	params["userNo"] = "localUser";
	params["userName"] = "本地用户";
	params["type"] = "writeData";
	params["token"] = "123456";
	params["cardNo"] = currentCardCode;
	params["section"] = 0;
	params["block"] = 1;
	params["data"] = id;
	params["keyA"]="FFFFFFFF";
	params["keyB"]="";
	$.ajax({
		url : "http://127.0.0.1:9999/",
		type : "POST",
		cache : false,
		csrf : false,
		async : false,
		selfThis:this,
		data : {params:JSON.stringify(params)},
		success : function(data) {
			console.log(data);
			var result = JSON.parse(data);
			if (result.respCode != 200) {
				resultCode= "-1";
				return;
			}
			resultCode= "1";
		}
	});
}
function getSpotName(cardCode) {
	var info = new EiInfo();
	info.set("cardCode", cardCode);
	EiCommunicator.send("DIFK01", "querySpotName", info, {
		onSuccess : function(ei) {
			spotName = ei.get("spot").spotName==null?"":ei.get("spot").spotName;
			spotNum = ei.get("spot").spotNum==null?"":ei.get("spot").spotNum;
		},
		onFail : function(ei) {
			NotificationUtil("调用失败，原因[" + ei + "]", "error");
		}
	});
}