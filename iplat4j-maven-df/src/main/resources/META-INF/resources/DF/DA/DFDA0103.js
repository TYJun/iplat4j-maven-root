$(function(){
	$("#send").click(function(){
		// 实例化
		var qrcode = new QRCode(
			// 二维码存放的div
			document.getElementById("qrcode"), {
				width:200,// 设置宽高
				height:200
			}
		);
		// 根据input框的值生成二维码
		qrcode.makeCode(document.getElementById("getval").value);
	});
});