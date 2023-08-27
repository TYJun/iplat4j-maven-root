$(function () {
	//回显赋值
	console.log(__ei);
	IPLAT.EFInput.value($("#guaranteeNum"), __ei.reqStaffId);
	IPLAT.EFPopupInput.value($("#guaranteeName"),__ei.reqStaffId);
	IPLAT.EFPopupInput.text($("#guaranteeName"),__ei.reqStaffName);	
	IPLAT.EFInput.value($("#reqTelNum"), __ei.reqTelNum);
	IPLAT.EFPopupInput.value($("#reqDeptName"),__ei.reqDeptNum);	
	IPLAT.EFPopupInput.text($("#reqDeptName"),__ei.reqDeptName);
	IPLAT.EFInput.value($("#building"), __ei.building);
	IPLAT.EFInput.value($("#floor"), __ei.floor);
	IPLAT.EFInput.value($("#reqSpotNum"), __ei.reqSpotNum);
	IPLAT.EFInput.value($("#reqSpotName"), __ei.reqSpotName);
	IPLAT.EFInput.value($("#comments"), __ei.comments);
	var statusCode = __ei.statusCode; /* 状态代码*/

	// 工单登记编辑的提交按钮.
	$("#SUBMIT").unbind('click').on('click', function(){
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
		
		var eiInfo = new EiInfo();
		var taskId = $("#taskId").val();	 /* 工单id*/
		var taskNo = $("#taskNo").val();	/*工单号*/
		var reqStaffId = $("#guaranteeNum").val();		/* 来电人员工号*/
//		var reqStaffId = IPLAT.EFPopupInput.value($("#guaranteeName"));		/* 来电人员工号*/
		var reqStaffName = IPLAT.EFPopupInput.text($("#guaranteeName"));		/* 来电人员*/
		var reqTelNum = $("#reqTelNum").val()
		var reqDeptNum = IPLAT.EFPopupInput.value($("#reqDeptName"));	 	/* 来电科室编码*/ 
		var reqDeptName = IPLAT.EFPopupInput.text($("#reqDeptName"));		/* 来电科室*/
		var building = $("#building").val();						/* 楼*/
		var floor = $("#floor").val();								/* 层*/
		var reqSpotNum = $("#reqSpotNum").val();	/* 保养地点编码*/
		var reqSpotName = $("#reqSpotName").val();	/* 保养地点*/
		var comments = IPLAT.EFInput.value($("#comments"));		/* 备注*/
		var dataItems = resultItemGrid.getDataItems(); /* 保洁事项组*/
		console.log(dataItems);
		//参数校验
		if(!validate(reqDeptName,building,floor,reqSpotName,dataItems,reqTelNum)){
			return;
		}
		
		eiInfo.set("taskId",taskId);
		eiInfo.set("taskNo",taskNo);
		eiInfo.set("reqStaffId",reqStaffId);
		eiInfo.set("reqStaffName",reqStaffName);
		eiInfo.set("statusCode",statusCode);
		eiInfo.set("reqTelNum",reqTelNum);
		eiInfo.set("reqDeptNum",reqDeptNum);
		eiInfo.set("reqDeptName",reqDeptName);
		eiInfo.set("building",building);
		eiInfo.set("floor",floor);
		eiInfo.set("reqSpotNum",reqSpotNum);
		eiInfo.set("reqSpotName",reqSpotName);
		eiInfo.set("comments",comments);
		eiInfo.set("dataItems",dataItems);
		// 执行后台CSRE0301.update接口方法更新工单信息.
		EiCommunicator.send("CSRE0301", "update", eiInfo, {
			onSuccess : function(ei) {
				var popData = $("#popData", parent.document);
				popData.kendoWindow().data("kendoWindow").close();
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 600);
			}
		});
	});
});

//根据来电科室编码自动带出科室的地点信息（楼、层、地点编码、地点名称）
IPLATUI.EFPopupInput = {
		"reqDeptName":{
			backFill:function (e){
				var reqDeptNum = e.model.deptNum;
				console.log(reqDeptNum);
				var eiInfo = new EiInfo();
				eiInfo.set("deptNum",reqDeptNum);
				EiCommunicator.send("CSRE01", "selectSpotInfoByDeptName", eiInfo, {
					onSuccess : function(ei) {
						console.log(ei.getAttr().result[0]);
						if(ei.getAttr().result[0] == null){
							$("#building").val("");  /* 需保洁楼号 */
							$("#floor").val(""); /* 需保洁楼层号 */
							$("#reqSpotNum").val(""); /* 保洁地点编码 */
							$("#reqSpotName").val(""); /* 保洁地点名称 */
						}else{
							$("#building").val(ei.getAttr().result[0].building);  /* 需保洁楼号 */
							$("#floor").val(ei.getAttr().result[0].floor); /* 需保洁楼层号 */
							$("#reqSpotNum").val(ei.getAttr().result[0].spotNum); /* 保洁地点编码 */
							$("#reqSpotName").val(ei.getAttr().result[0].spotName); /* 保洁地点名称 */
						}
					}
				})
			}
		}
};

// 树状结构处理.
IPLATUI.EFTree = {
    "tree01": { //tree01对应jsp中EFTree的bindId属性
        ROOT: "root", // 虚拟节点不渲染，仅作为初始查询条件
        select: function (e) {
            var _data = this.dataItem(e.node);
            $("#typeId").val(_data.id);
            resultGrid.dataSource.page(1);
        }
    }
};

	
IPLATUI.EFGrid = {
	"resultItem":{
		pageable:false,
		exportGrid:false,
		loadComplete: function (grid) {
			// 三级弹框
         	$("#ADDITEM").on("click", function (e) {
         		var url = IPLATUI.CONTEXT_PATH + "/web/CSRE010101";
	            var popData = $("#popDataItem");
	            popData.data("kendoWindow").setOptions({
	            	open : function() {
	            		popData.data("kendoWindow").refresh({
	            			url : url
	            		});
	            	}
		            });
	            popDataItemWindow.open().center();
				// 向子窗口中的 iframe 对象传值
				setTimeout(function(){
					var iframe = popDataItemWindow.element.children("iframe")[0].contentWindow;
					// 获取所有行的数值.
					var rows = resultItemGrid.getDataItems();
					// 将所有行的数值传给其子窗口.
					console.log(iframe);
					if(iframe){
						iframe.setData(rows);
					}
				}, 400 );
            });

			// 保洁登记编辑子页面事项删除按钮.
         	$("#DELETE1").on("click", function (e) { 
                var checkRows = resultItemGrid.getCheckedRows();
                if (checkRows.length > 0) {
                	resultItemGrid.removeRows(checkRows);
                } else {
                 IPLAT.NotificationUtil("请选择需要删除的信息")
                 }
         	});
		}
	}
	
	
	
}

	
// -------------------自定义自动补全--------------
/** 登记人工号自动补全 */
$("#guaranteeNum").kendoAutoComplete({
	// 以guaranteeNum为参数，调用CSRE01/queryPersonnelList本地服务进行查询，将返回的person一并填写。
	dataSource: dataSourceConfig("/service/CSRE01/queryPersonnelList", "person",["guaranteeNum"]),
	// blockId 下的属性 workNo
    dataTextField: "workNo",
    filter:"contains",
    template:'<span>#:workNo#-#:name#</span>',
    select:function(e){
    	var dataItem = this.dataItem(e.item.index());
    	$("[name = 'guaranteeName']").val(dataItem.workNo);		/* 来电人工号*/
		$("[name = 'guaranteeName_textField']").val(dataItem.name);		/* 来电人*/
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 来电科室编码*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 来电科室*/
    }
});

/** 来电电话自动补全 */
$("#reqTelNum").kendoAutoComplete({
	// 以reqTelNum为参数，调用CSRE01/selectOfficePhone本地服务进行查询，将返回的phone作为第二个参数。
    dataSource: dataSourceConfig("/service/CSRE01/selectOfficePhone", "phone",["reqTelNum"]),
	// blockId 下的属性 telNum
    dataTextField: "telNum",
    filter:"contains",
    template:'<span>#:telNum#-#:deptName#</span>',
    select:function(e){
    	var dataItem = this.dataItem(e.item.index());
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 来电科室编码*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 来电科室*/
		$("#building").val(dataItem.building);  /* 需保洁楼号 */
		$("#floor").val(dataItem.floor); /* 需保洁楼层号 */
		$("#reqSpotNum").val(dataItem.spotNum); /* 保洁地点编码 */
		$("#reqSpotName").val(dataItem.spotName); /* 保洁地点名称 */
    }
});

/** 楼自动补全 */
$("#building").kendoAutoComplete({
	// 以building为参数，调用CSRE01/selectSpotBuildingName本地服务进行查询，将返回的building作为第二个参数。
	dataSource: dataSourceConfig("/service/CSRE01/selectSpotBuildingName", "building", ["building"]),
	// blockId 下的属性 building
	dataTextField: "building",
	filter:"contains",
	minLength :0
});

/** 层自动补全 */
$("#floor").kendoAutoComplete({
	// 以building和floor为参数，调用CSRE01/selectSpotFloorName本地服务进行查询，将返回的floor作为第二个参数。
	dataSource: dataSourceConfig("/service/CSRE01/selectSpotFloorName", "floor",["building","floor"]),
	// blockId 下的属性 floor
    dataTextField: "floor",
    filter:"contains"
});

/** 地点自动补全 */
$("#reqSpotName").kendoAutoComplete({
	// 以building和floor和reqSpotName为参数，调用CSRE01/selectSpotRoomName本地服务进行查询，将返回的room作为第二个参数。
	dataSource: dataSourceConfig("/service/CSRE01/selectSpotRoomName", "room",["building","floor","reqSpotName"]),
	// blockId 下的属性 spotName
    dataTextField: "spotName",
    filter:"contains",
    select:function(e){
    	var dataItem = this.dataItem(e.item.index());
    	$("#reqSpotNum").val(dataItem.spotNum); 
    }
});

// ---------------------------------------------

/** kendoAutoComplete的dataSource配置*/
function dataSourceConfig(url,blockId,param){
	return new kendo.data.DataSource({
			serverFiltering: true,//每次输入重新获取数据
		    transport: {
		        read: {
		        	url:IPLATUI.CONTEXT_PATH + url,
		            type: 'POST',
		            dataType: "json",
		            contentType: "application/json"
		        },
		        parameterMap: function (options, operation) {
		            var info = new EiInfo();
		            if(param != null){
		            	for(var index in param){ info.set(param[index],$("#"+param[index]).val()); }
		            }
		            return  info.toJSONString();
		        }
		    },
		    schema: {
		        data: function (response) {
		            ajaxEi = EiInfo.parseJSONObject(response);
		            var block = ajaxEi.getBlock(blockId);
		            var array = new Array();
		            for(var index in  block.getRows()){
		            	array.push(block.getMappedObject( block.getRows()[index]));
		            }
		            return array;
		        }
		    }
		})
}


//参数校验
// 对来电科室名称、楼、层、地点名称、事项列表、来电号码进行参数空值验证.
function validate(reqDeptName,building,floor,reqSpotName,dataItems,reqTelNum){
	if(isEmpty(reqDeptName)){
		NotificationUtil("来电科室不能为空", "error");
		return false;
	} 
	if(isEmpty(building)){
		NotificationUtil("楼不能为空", "error");
		return false;
	} 
	if(isEmpty(floor)){
		NotificationUtil("层不能为空", "error");
		return false;
	} 
	if(isEmpty(reqSpotName)){
		NotificationUtil("保洁地点不能为空", "error");
		return false;
	} 
	if(dataItems.length == 0){
		NotificationUtil("请添加保洁事项", "error");
		return false;
	} 
	if(!isEmpty(reqTelNum)){
		if(!checkPhone(reqTelNum)){
			NotificationUtil("请输入正确的电话号码", "error");
			return false;
		}
	}
	return true;
}

// 空值函数.
function isEmpty(parameter){
	if(parameter == undefined || parameter == null){
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	} else {
		return false;
	}
}

// 检查号码规则.
function checkPhone(mobile) {
    var tel = /^([0-9]{3,4}-)?[0-9]{4,8}$/;
    var phone = /^((\+?86)|(\(\+86\)))?(1[3-9][0-9]{9})$/; 
    if(tel.test(mobile) || phone.test(mobile) ){ //来电号码
      return true;
    }
    return false;
}


//function getWindowQueryInfo(){
//	var itemCode = IPLAT.EFInput.value($("#itemCode"));
//    //加载Grid数据
//    return params = {
//    		'itemCode' : itemCode
//    };
//}

// 显示所有，自动补齐
function showAll(selector){
	var autocomplete = $("#"+selector).data("kendoAutoComplete");
	autocomplete.search("");
}

// 对选中的数值回显.
function echoOne(selector){
	var autocomplete = $("#"+selector).data("kendoAutoComplete");
	var options = autocomplete.options;
	var rows = options.dataSource._data;
	var value = autocomplete.value();
	if(rows !=null && rows.length > 0 && value.length > 0){
		var index = findIndex(rows,value,selector);
		if(index > -1){
			autocomplete.select(parseInt(index));
			var dataItem = autocomplete.dataItem(parseInt(index));
			echoSelect(selector, dataItem);
		}
	}
}

function findIndex(rows,value,selector){
	for(var index in rows){
		if(selector == "guaranteeNum"){
			if(rows[index]['workNo'] == value){
				return index;
			}
		} else if (selector == "reqTelNum"){
			if(rows[index]['telNum'] == value){
				return index;
			}
		}
	}
	return -1;
}

// 当guaranteeNum 有值时自动补充guaranteeName、guaranteeName_textField、reqDeptName、reqDeptName_textField.
// 当reqTelNum 有时reqDeptName,reqDeptName_textField,building,floor
function echoSelect(selector, dataItem) {
	if(selector == "guaranteeNum"){
		$("[name = 'guaranteeName']").val(dataItem.workNo);		/* 来电人*/
		$("[name = 'guaranteeName_textField']").val(dataItem.name);		/* 来电人*/
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 来电科室*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 来电科室*/
	} else if (selector == "reqTelNum") {
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 来电科室*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 来电科室*/
		$("#building").val(dataItem.building); 
		$("#floor").val(dataItem.floor);
	}
}
