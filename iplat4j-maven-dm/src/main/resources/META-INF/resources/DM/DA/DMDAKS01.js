//获取url中的参数
function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串

	var theRequest = new Object();

	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
//获取传参中的方法名
var params = GetRequest();
var fn1 = eval(params.fn1);
//调用方法
new fn1(params.arg1 + "");
//设置checkMode
function setCheckMode(param) {//"single, row"
	$.extend(true, IPLATUI.Config, {
		EFGrid : {
			checkMode : param
		}
	});
}

function setData(e){
	console.log(e);
}

function getData(){
	var rows = resultGrid.getCheckedRows();
	console.log(rows);
	return rows;
}

$(function(){
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	//刷新grid
	//resultGrid.dataSource.page(1);
});