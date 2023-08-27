$(function () {

});

IPLATUI.EFGrid = {
	"result": {
		toolbarConfig:{
			hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
			add: false,cancel: false,save: false,'delete': false, 
			buttons:[{
				name: "ef_popup_grid_fillback",text: "确定",layout:"3",
				click: function () {
					var checkRows = resultGrid.getCheckedRows();
					var model = checkRows[0];
					if (model) {
						$("#info-0-goodsId").val(model['faInfoId']);
						$("#info-0-goodsNum").val(model['goodsNum']);
						$("#info-0-goodsNum_textField").val(model['goodsNum']);
						$("#info-0-goodsName").val(model['goodsName']);
						$("#info-0-model").val(model['model']);
						$("#info-0-manufacturer").val(model['manufacturer']);
						$("#info-0-buyCost").val(model['buyCost']);
						$("#info-0-useYears").val(model['useYears']);
						$("#info-0-totalDepreciation").val(model['totalDepreciation']);
						$("#info-0-netAssetValue").val(model['netAssetValue']);
						$("#info-0-buyDate").val(model['buyDate']);
						$("#info-0-useDate").val(model['useDate']);
						$("#info-0-deptName").val(model['deptName']);
						$("#info-0-installLocation").val(model['installLocation']);
						var popupGridWindow = $("#ef_popup_grid").data("kendoWindow");
						popupGridWindow.close();
					} else {
						NotificationUtil("请选择固定资产", "warning");
					}
				}
			}]
		},
		loadComplete:function (e) {
			$("#SAVE").on("click", function (e) {
				var eiInfo = new EiInfo();
				eiInfo.setByNode("info");
				if ($("#info-0-goodsNum").val() == ""){
					NotificationUtil("请填写固资编码", "warning")
					return
				}
				if ($("#info-0-goodsName").val() == ""){
					NotificationUtil("请填写固资名称", "warning")
					return
				}
				if ($("#info-0-reimburseDate").val() == ""){
					NotificationUtil("请填写报损日期", "warning")
					return
				}
				if ($("#info-0-finishTime").val() == ""){
					NotificationUtil("请填写完成时间", "warning")
					return
				}
				if ($("#info-0-reimburseReason").val() == ""){
					NotificationUtil("请填写报损原因", "warning")
					return
				}
				// 固资类别录入提交
				EiCommunicator.send("FABS0101", "saveFaReimburseInfo", eiInfo, {
					onSuccess : function(ei) {
						closeParentWindow()
					}
				})
			});

			$("#CLOSE").on("click", function (e) {
				closeParentWindow()
			});

			$("#queryFAInfoType").on("click", function () {
				 resultGrid.dataSource.page(1);
			})
		}
	}
}

function closeParentWindow() {
	window.parent['popDataWindow'].close();
	window.parent["resultGrid"].dataSource.page(1);
}