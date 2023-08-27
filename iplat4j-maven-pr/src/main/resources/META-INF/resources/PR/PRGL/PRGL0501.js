//图片加载方法
loadPic();
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


$(function () {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.query(1);
    });
});