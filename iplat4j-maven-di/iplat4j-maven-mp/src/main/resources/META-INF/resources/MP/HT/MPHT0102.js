$(function() {
	IPLATUI.EFGrid = {
		"result": {
			onRowClick: function (e) {
				e.preventDefault();
				$("#inqu_status-0-procurementId").val( e.model['id'])
				window["detailGrid"].dataSource.page(1);
			}
		},
		"detail": {
			pageable: false,
			toolbarConfig: {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons: [{
					name: "CONFIRM",text: "确定",layout: "3",
					click: function (){
						let checkRows = detailGrid.getCheckedRows();
						if (checkRows.length > 0) {
							window.parent.addRows(checkRows);
							window.parent["popDataWindow"].close();
						} else {
							IPLAT.NotificationUtil("请选择采购计划明细", "warning");
						}
					}
				}]
			}
		}
	}
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		resetTime("recCreateTimeS", "recCreateTimeE");
		$("#inqu_status-0-purchaseNo").val("");
		$("#inqu_status-0-purchaseRemark").val("");
		$("#inqu_status-0-procurementId").val("");
		resultGrid.dataSource.page(1);
		detailGrid.dataSource.page(1);
	});

})



/**
 * 设置日期初始值
 */
function resetTime(beginId, endId) {
	let lastDate = new Date();
	lastDate.setMonth(lastDate.getMonth()-1);
	$("#"+beginId).val(lastDate.Format("yyyy-MM-dd"));
	$("#"+endId).val(new Date().Format("yyyy-MM-dd"));
}


/**
 * 时间格式化
 * @param fmt ： 格式
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
	let o = {
		"M+": this.getMonth() + 1, // 月份
		"d+": this.getDate(), // 日
		"h+": this.getHours(), // 小时
		"m+": this.getMinutes(), // 分
		"s+": this.getSeconds(), // 秒
		"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		"S": this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (let k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}





