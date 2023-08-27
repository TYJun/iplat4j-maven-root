$(function(){
	/* 查询按钮 */
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.query(1);
	});
	
	/* 删除按钮 */
	$("#DELETE").on("click",function(e){
		resultGrid.dataSource.delete(1);
	});

	/* 新增 */
	$("#ADD").on("click",function(e){
		/* resultGrid.dataSource.insert(1); */
		 resultGrid.addRow()
	});

	$("#SAVA").on("click",function(e){
		var eiInfo = new EiInfo();
		eiInfo.setByNode("result");
		EiCommunicator.send("CMGL11", "sava", eiInfo, {
			onSuccess : function(e) {
				IPLAT.alert("保存成功");
			}
		})
	});
})