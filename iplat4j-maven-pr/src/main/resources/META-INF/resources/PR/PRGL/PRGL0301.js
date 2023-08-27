//图片加载方法
loadPic();
$(function() {
	//整改驳回按钮
	$("#UPDATE2").on("click", function(e) {
		/*var eiInfo = new EiInfo();
		eiInfo.setByNode("inqu");*/
		var id = $("#id").val();
		var dangerid = $("#dangerid").val();
		var opinion = $("#inqu_status-opinion").val();
		var eiInfo = new EiInfo();
		//eiInfo.setByNode("inqu");
		eiInfo.set("id",id);
		eiInfo.set("dangerid",dangerid);
		eiInfo.set("opinion",opinion);
		//访问方法
		EiCommunicator.send("PRGL0301", "update2", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 300);
			}
		});
	});
	//整改同意按钮
	$("#UPDATE3").on("click", function(e) {
		var id = $("#id").val();
		var dangerid = $("#dangerid").val();
		var opinion = $("#inqu_status-opinion").val();
		var eiInfo = new EiInfo();
		//eiInfo.setByNode("inqu");
		eiInfo.set("id",id);
		eiInfo.set("dangerid",dangerid);
		eiInfo.set("opinion",opinion);
		//访问方法
		EiCommunicator.send("PRGL0301", "update3", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 300);
			}
		});
	})
})
//加载整改前图片
function loadPic(){
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	var id = vars[1].split("=")[1];
	var inInfo = new EiInfo();
	inInfo.set("id",id);
	EiCommunicator.send("PRGL0201", "showTempPic", inInfo, {
		onSuccess : function(ei) {
			var list = ei.get("list");
			$("#reqPic").html("")
			for (var i = 0; i < list.length; i++) {
				drawImage(list[i],1);
			}
		}
	});
	afterloadPic();
}
//加载整改后图片
function afterloadPic(){
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	var id = vars[1].split("=")[1];
	var inInfo = new EiInfo();
	inInfo.set("id",id);
	EiCommunicator.send("PRGL0301", "showTempPic", inInfo, {
		onSuccess : function(ei) {
			var list = ei.get("list");
			$("#reqPic2").html("")
			for (var i = 0; i < list.length; i++) {
				image(list[i],"#reqPic2")
			}
		}
	});
}