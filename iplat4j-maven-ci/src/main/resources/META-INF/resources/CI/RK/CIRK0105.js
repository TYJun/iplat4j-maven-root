$(function(){

	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});

	$("#REQUERY").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
		"result": {
			loadComplete: function (grid) {

				//确定
				$("#COMFIRM").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows();
					var eiInfo = new EiInfo();
					if (checkRows.length > 0) {
						//后台查询对应采购单中的所有商品信息，填充表格

						//console.log("查询后台，并选择数据："+checkRows);

						var eiInfo = new EiInfo();
						eiInfo.set("applyBillNo", checkRows[0].applyBillNo);
						eiInfo.set("realPage","200");//最多查询200条采购单中的物资
						EiCommunicator.send("CICG0101", "queryDetailByApplyId", eiInfo, {
							onSuccess : function(ei) {
								if(ei.status != 0) {
									IPLAT.NotificationUtil("获取采购申请单数据错误："+ei.getMsg,"error");
								}else {
									console.debug("get apply data ok"+ei.extAttr.allApplyRows);
									datarows=JSON.parse(ei.extAttr.allApplyRows);
									//调整data的数据顺序
									if (datarows.length > 0) {
										window.parent.addRows(datarows);
									}else{
										IPLAT.NotificationUtil("采购申请单没有对应的物资");
										return;
									}
								}
							}
						});

						//window.parent.addRows(checkRows);
						window.parent["popData1Window"].close();
					} else {
						IPLAT.NotificationUtil("请选择采购申请单");
					}
				});

				//关闭
				$("#CLOSE").on("click", function(e) {
					window.parent['popData1Window'].close();
				});

			}
		}
	}

});