$(function(){
	IPLATUI.EFDatePicker = {
		"inqu_status-0-month": {
			start: "year",
			depth: "year"
		},
	};

	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {
			pageable: {
				pageSize: 50, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
				pageSizes: [10, 20, 50, 100, 500, 1000]
			}
		}
	})

	/**回车键查询**/
	keydown("inqu", "QUERY");

	//查询
	$("#inqu_status-0-month").val(new Date().Format("yyyy-MM"));
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
		// 查询更新库存总价和库存总量
		var eiInfo = new EiInfo();
		eiInfo.set("inqu_status-0-wareHouseNo",IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo")));
		eiInfo.set("inqu_status-0-matNum",IPLAT.EFInput.value($("#inqu_status-0-matNum")));
		eiInfo.set("inqu_status-0-matName",IPLAT.EFInput.value($("#inqu_status-0-matName")));
		eiInfo.set("inqu_status-0-month",$("#inqu_status-0-month").val());
		EiCommunicator.send("SIKC04", "countNumAndSum", eiInfo, {
			onSuccess : function(ei) {
				$("#firstSumAll").text(ei.extAttr.firstNum);
				$("#lastSumAll").text(ei.extAttr.lastNum);
				$("#firstMoneyAll").text(ei.extAttr.firstAmount);
				$("#lastMoneyAll").text(ei.extAttr.lastAmount);
			}
		});
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		$("#inqu_status-0-month").val(new Date().Format("yyyy-MM"));
		resultGrid.dataSource.page(1);
		// 查询更新库存总价和库存总量
		var eiInfo = new EiInfo();
		eiInfo.set("inqu_status-0-month",$("#inqu_status-0-month").val());
		EiCommunicator.send("SIKC04", "countNumAndSum", eiInfo, {
			onSuccess : function(ei) {
				$("#firstSumAll").text(ei.extAttr.firstNum);
				$("#lastSumAll").text(ei.extAttr.lastNum);
				$("#firstMoneyAll").text(ei.extAttr.firstAmount);
				$("#lastMoneyAll").text(ei.extAttr.lastAmount);
			}
		});
	});

	IPLATUI.EFGrid = {
		"result": {
			loadComplete:function (e) {
				// 显示月初库存总价、月末库存总价、月初库存总量、月末库存总量
				$("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>月初库存总量：<span id='firstSumAll' style='color: red'>0</span>件，月末库存总量：<span id='lastSumAll' style='color: red'>0</span>件，月初库存总价：<span id='firstMoneyAll' style='color: red'>0</span>元，月末库存总价：<span id='lastMoneyAll' style='color: red'>0</span>元</div>");
				// 查询更新库存总价和库存总量
				var eiInfo = new EiInfo();
				eiInfo.set("inqu_status-0-month",$("#inqu_status-0-month").val());
				EiCommunicator.send("SIKC04", "countNumAndSum", eiInfo, {
					onSuccess : function(ei) {
						$("#firstSumAll").text(ei.extAttr.firstNum);
						$("#lastSumAll").text(ei.extAttr.lastNum);
						$("#firstMoneyAll").text(ei.extAttr.firstAmount);
						$("#lastMoneyAll").text(ei.extAttr.lastAmount);
					}
				});

				$("#EXPORT").on("click", function(e) {
					let eiInfo = new EiInfo(); let url = IPLATUI.CONTEXT_PATH+"/si/exportMonthStorage?foo=";
					eiInfo.setByNode("inqu");
					let params = eiInfo.getBlock("inqu_status").getMappedRows()[0];
					for (let field in params) {
						if(params[field]) { url = url + "&"+field+"="+params[field]}
					}
					window.location.href = url;
				});
			}
		}
	}

})

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