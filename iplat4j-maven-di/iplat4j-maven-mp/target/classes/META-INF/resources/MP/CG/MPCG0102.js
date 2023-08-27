$(function() {
	IPLATUI.EFGrid = {
		"mat": {
			onCellClick:function(e){
				//查看图片大图
				if(e.field === "pictureUri") {
					let img = e.model['pictureUri'];
					WindowUtil({
						title: "图片",
						draggable: false,
						content: `<span style='padding:3px;display:inline-block;border:1px solid black;'>
                                       <img src='${IPLATUI.CONTEXT_PATH}${img}'>
                                 </span>`
					});
					window["matGrid"].unCheckAllRows();
				}
			},
			loadComplete:function(){
				//确定
				$("#COMFIRM").on("click", function(e) {
					let checkRows = matGrid.getCheckedRows();
					if (checkRows.length > 0) {
						window.parent.addRows(checkRows);
						window.parent["popData1Window"].close();
					} else {
						IPLAT.NotificationUtil("请选择物资");
					}
				});
				
				//关闭
				$("#CLOSE").on("click", function(e) {
					window.parent['popData1Window'].close();
				});
			},
			columns : [{
				field: "pictureUri",
				title: "图片",
				template: "<span ><img src='"+IPLATUI.CONTEXT_PATH+"#:pictureUri#' height='30' ></span>",
			}]
		}
	}
	//查询
	$("#QUERY").on("click", function(e) {
		matGrid.dataSource.page(1);
	});
	
	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		matGrid.dataSource.page(1);
	});
})