$(function() {
	IPLATUI.EFGrid = {
			"item": {
				loadComplete: function (grid) {
					$("#ADD").on("click", function (e) {
						var info = new EiInfo();
						var checkRows = itemGrid.getDataItems();
						var arr="";
						for(var i=0;i<checkRows.length;i++){
							arr+=checkRows[i].itemID_xmID+",";
						}
						var url = IPLATUI.CONTEXT_PATH + "/web/DKKP010101?id="+arr;
						var popData = $("#popDataItem");
						popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
								});
							}
						});
						popDataItemWindow.open().center();
				    });
					$("#DELETEROW").on("click", function (e) {
						var checkRows = itemGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						itemGrid.removeRows(checkRows);
				    });
					$("#SAVEADD").on("click", function (e) {
						
						var info = new EiInfo();
						//列表数据
						var itemList = itemGrid.getDataItems();
						
						//卡片数据
						var cardID = IPLAT.EFInput.value($("#cardID"));
						var cardCode = IPLAT.EFInput.value($("#cardCode"));
						var cardName = IPLAT.EFInput.value($("#cardName"));
						var kpType = IPLAT.EFPopupInput.value($("#kpType"));
						var memo = IPLAT.EFPopupInput.value($("#memo"));
						
						//参数校验
						if(!validate(cardName)){
							return;
						}
						
						info.set("itemList", itemList);
						info.set("cardID", cardID);
						info.set("cardCode", cardCode);
						info.set("cardName", cardName);
						info.set("kpType", kpType);
						info.set("memo", memo);
						
						EiCommunicator.send("DKKP0101", "insertKP", info, {
							onSuccess : function(ei) {
								var status = ei.getStatus();
								if(status!=0){
									NotificationUtil(ei.getMsg(),"error");
								}else{
									NotificationUtil(ei.getMsg(), "success");
									window.parent.resultGrid.dataSource.page(1);
									window.parent['popDataAddWindow'].close();
									window.parent['popDataModifyWindow'].close();
								}
								
							}
						});
					});
					
					function validate(cardName) {
//						if(isEmpty(cardCode)){
//							NotificationUtil("卡片代码不能为空", "error");
//							return false;
//						} 
						if(isEmpty(cardName)){
							NotificationUtil("卡片名称不能为空", "error");
							return false;
						} 
						return true;
						
					}
					
					function isEmpty(parameter){
						if(parameter == undefined || parameter == null){
							return true;
						} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
							return true;
						} else {
							return false;
						}
					}
				}
			}
		};
 });