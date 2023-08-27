//读卡器读取卡片
function readCard(callback) {
	console.log("initReadCard");
	axios({
		method : 'post',
		url : 'http://127.0.0.1:9999/',
		data : {
			userNo : 'localUser',
			userName : '111',
			type : 'getCardNo',
			token : '123456'
		},
		headers : {
			"Content-Type" : "application/x-www-form-urlencoded"
		}
	}).then(function(res) {
		console.log(res)
		var cList = null;
		if (res.data.respCode == 200 || res.data.respCode == '200') {
			let list = res.data.obj
			cList = daXiaoDuanChange(list)
			console.log("basecode:" + cList);
		} else {
			console.log("读卡失败！");
			cList = null;
		}
		//回调
		if(callback){
			callback(cList);
		}
	});
}

//转换卡片编码
function daXiaoDuanChange(a) {
	var aChange = '';
	for (var index = 0; index < a.length; index += 2) {
		aChange += a.substring(a.length - index - 2, a.length - index)
	}
	return aChange;
}