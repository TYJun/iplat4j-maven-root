$(function(){
	IPLATUI.EFGrid = new WilpGrid({add:false, edit: false, del: false }).buildGrid();

	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {
			columns:[ {
				field: "contCost",
				readonly: true,
				template: "<span>#=contCost#元</span>",
			}]

		}
	})
	/* 主页面查询按钮 */
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.page(1);
	});

	//重置
	$("#REQUERY").on("click",function(e){
		document.getElementById("inqu-trash").click();
		resetTime("inqu_status-0-recCreateTimeS", "inqu_status-0-recCreateTimeE");
		resultGrid.dataSource.page(1);
	});
})

