var datagrid = null;
var cardids = null;
var eiInfo = new EiInfo();
var rows = null;
$(function() {
	
	//查询按钮
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
});
IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, 
					model = e.model, 
					$tr = e.tr, 
					row = e.row; 
					datagrid = model;    
					cardids = datagrid.cardid;
				}    
			},
			loadComplete: function (grid) {

//				$("#QUERY").on("click", function (e) {
//					resultGrid.dataSource.query(1);
//				});

				//新增按钮
				$("#INSERT1").on("click", function(e) {
					popData();
				});

				//編輯
				$("#UPDATE01").on("click", function(e) {
					//console.log(cardids);
					popDataUpdate(cardids);
				});


				$("#START").on("click",function(e){
					if (datagrid == null) {
						IPLAT.alert("请先选择一条记录进行启用");
					}  else {
						var eiInfo = new EiInfo();
						eiInfo.set("id",datagrid.id);

						EiCommunicator.send("DIKP01", "openInfo",
								eiInfo, {
							onSuccess : function(ei) {
								IPLAT.alert(ei.getMsg());
								resultGrid.dataSource.page(1);
							}
						})
					} 
				});

				$("#END").on("click",function(e){
					if (datagrid == null) {
						IPLAT.alert("请先选择一条记录进行禁用");
					}  else {
						var eiInfo = new EiInfo();
						eiInfo.set("id",datagrid.id);

						EiCommunicator.send("DIKP01", "endInfo",
								eiInfo, {
							onSuccess : function(ei) {
								IPLAT.alert(ei.getMsg());
								resultGrid.dataSource.page(1);
							}
						})
					} 
				});
			}
		}
}




function popData() {
	var url = IPLATUI.CONTEXT_PATH + "/web/DIKP02";
	var popData = $("#popData");

	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
				//resultGrid:datagrid
			});
		}
	});
	// 打开生成弹窗
	popDataWindow.open().center();
}


function popDataUpdate(cardids) {
	var url = IPLATUI.CONTEXT_PATH + "/web/DIKP0201?initLoad&id="+cardids;
	var popData = $("#popData");

	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
				//resultGrid:datagrid
			});
		}
	});
	// 打开生成弹窗
	popDataWindow.open().center();
}


//提交时判断数据是否存在
function validatePR(eiInfo) {
	if (isEmpty(eiInfo)) {
		IPLAT.NotificationUtil("不能为空");
		return false;
	}

	return true;
}

//判空
function isEmpty(str) { 
	if(str == undefined){
		return true;
	}
	if(str == null){
		return true;
	}
	if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	}
	return false;
}

