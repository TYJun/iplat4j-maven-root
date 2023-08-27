/*$(function(){
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
});*/

$(function (e) {	
	debugger
	var url = location.search;
	var obj = {};
	var reg = /\?/;
	if(url.match(reg)) {
	    //判断传入参数，以问号截取，问号后是参数
	    var chars = url.split('?')[1];
	    //再截&号
	    var arr = chars.split('&');
	    //获得截取后的数组为键值对字符串
	    for (var i = 0; i < arr.length; i++) {
	        //保守一点确定看是否为 name=value形式
	        var num = arr[i].indexOf("=");
	        //判断参数
	        if (num > 0) {
	            //拼接字符串
	            var name = arr[i].substring(0, num);
	            var value = arr[i].substr(num + 1);
	            //拼接对象，并转码
	            obj[decodeURIComponent(name)] = decodeURIComponent(value);
	        }
	    }
	}
	//获取id转换城二维码
	var id = obj.id;
	var fixedPlace = obj.fixedPlace;
	//绑定二维码地点名称
	var div = document.getElementById("spotNameDiv");  
	div.innerHTML = fixedPlace;
	
	//调用方法
	outputQRCod(id, 300, 300);
	//生成二维码
    function outputQRCod(txt, width, height) {
        //先清空
        $("#result").empty();
        //生成二维码
        $("#result").qrcode({
            render: "canvas",//canvas和table两种渲染方式
            width: width,
            height: height,
            text: txt
        });
    }
})