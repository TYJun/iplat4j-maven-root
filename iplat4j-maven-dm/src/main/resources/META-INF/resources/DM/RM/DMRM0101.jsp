<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFInput ename="type" type="hidden"/>
		<EF:EFInput ename="id" type="hidden"/>

		<div class="row">
			<%--<EF:EFInput ename="buildingCode" cname="楼"
						colWidth="4"  type="text" required="true"
						placeholder="楼" />--%>

			<EF:EFSelect ename="buildingCode" cname="楼" required="true" colWidth="4" placeholder="楼"
						 defaultValue="请选择楼" >
				<EF:EFCodeOption codeName="wilp.dm.rm.buildingCode" />
			</EF:EFSelect>
			<EF:EFSelect ename="floorCode" cname="层" required="true" colWidth="4" placeholder="层"
						 defaultValue="请选择层" >
				<EF:EFCodeOption codeName="wilp.dm.rm.floorCode" />
			</EF:EFSelect>

			<%--<EF:EFInput ename="floorCode" cname="层"
						colWidth="4"  type="text" required="true"
						placeholder="层" />--%>
			<EF:EFInput ename="roomNo" cname="房间号"
						colWidth="4"  type="text" required="true"
						placeholder="房间号" />
		<%--</div>

		<div class="row">--%>

			<%--<EF:EFInput ename="bedNum" cname="床位数量"
				colWidth="4"  type="text" required="true"
				placeholder="请输入床位数量" /> --%>
					<%-- 把床位数量修改为宿舍类型(双人、三人、四人、十二人)
						使用床位数判断是几人间
					--%>
				<EF:EFSelect ename="bedNum" cname="宿舍类型" required="true">
					<EF:EFOption label="请先择房间类型" value=""/>
					<EF:EFOption label="单人间" value="1"/>
					<EF:EFOption label="双人间" value="2"/>
					<EF:EFOption label="三人间" value="3"/>
					<EF:EFOption label="四人间" value="4"/>
					<EF:EFOption label="十二人间" value="12"/>
				</EF:EFSelect>
			<EF:EFInput ename="dormsAreas" cname="房屋面积"
				colWidth="4"  type="text" required="true"
				placeholder="请输入房屋面积" />
			<EF:EFInput ename="rent" cname="房租"
				colWidth="4"  type="text" required="true"
				placeholder="请输入房租" />
		<%--</div>
		<div class="row">--%>

		<%--	<EF:EFInput ename="manageFee" cname="管理费"
				colWidth="4"  type="text" required="true"
				placeholder="请输入管理费" />
		--%>
				<%-- 管理费分成物业管理费和医院管理费--%>
		<EF:EFInput ename="hospitalManageFee" cname="医院管理费"
					colWidth="4"  type="text" required="true"
					placeholder="请输入管理费" />
		<EF:EFInput ename="propertyManageFee" cname="物业管理费"
					colWidth="4"  type="text" required="true"
					placeholder="请输入物业管理费" />
			<EF:EFSelect ename="typeCode" cname="男/女生宿舍" >
						<EF:EFOption label="请先择房间类型" value=""/>
					    <EF:EFOption label="男生宿舍" value="男生宿舍"/>
					    <EF:EFOption label="女生宿舍" value="女生宿舍"/>
    				</EF:EFSelect>
    		<EF:EFSelect ename="dormsPosition" cname="宿舍位置" >
						<EF:EFOption label="请先择宿舍位置" value=""/>
					    <EF:EFOption label="院内"  value="院内"/>
					    <EF:EFOption label="院外"  value="院外"/>
    				</EF:EFSelect>
		<%--</div>

		<div class="row">--%>
<%--			<EF:EFSelect ename="dormsIfwc" cname="是否独卫" >--%>
<%--						<EF:EFOption label="请先择是否独立卫生间" value=""/>--%>
<%--					    <EF:EFOption label="有"  value="有"/>--%>
<%--					    <EF:EFOption label="没有" value="没有"/>--%>
<%--    				</EF:EFSelect>--%>
    		<EF:EFInput ename="note" type="textarea" colWidth="4" cname="备注"/>
		</div>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url=" " lazyload="true" width="80%" height="70%" title="信息" modal="true" />
</EF:EFPage>

<script type="text/javascript">

$(function () {
	IPLAT.EFSelect.text($("#buildingCode"),__ei.buildingCode);
	IPLAT.EFSelect.text($("#floorCode"),__ei.floorCode);
	$("#SUBMIT").click(function(){
		//debugger;
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
		var eiInfo = new EiInfo();
		var type = IPLAT.EFInput.value($("#type"));
		var id = IPLAT.EFInput.value($("#id"));
		var buildingCode = IPLAT.EFSelect.text($("#buildingCode"));
		var floorCode = IPLAT.EFSelect.text($("#floorCode"));
		var roomNo = IPLAT.EFInput.value($("#roomNo"));
		var bedNum = IPLAT.EFSelect.value($("#bedNum"));
		var dormsAreas = IPLAT.EFInput.value($("#dormsAreas"));
		var rent = IPLAT.EFInput.value($("#rent"));
		//var manageFee = IPLAT.EFInput.value($("#manageFee"));	//管理费
		var hospitalManageFee = IPLAT.EFInput.value($("#hospitalManageFee"));
		var propertyManageFee = IPLAT.EFInput.value($("#propertyManageFee"));
		var typeCode = IPLAT.EFSelect.value($("#typeCode"));
		var dormsPosition = IPLAT.EFSelect.value($("#dormsPosition"));
		//var dormsIfwc = IPLAT.EFSelect.value($("#dormsIfwc"));	//去掉是否独卫
		var note = IPLAT.EFInput.value($("#note"));
		if(!validate(buildingCode,floorCode,roomNo,bedNum,dormsAreas,rent,hospitalManageFee,propertyManageFee)){
			return;
		}
		if(type === "edit"){
			eiInfo.set("id",id);
		}
		eiInfo.set("buildingCode",buildingCode);
		eiInfo.set("floorCode",floorCode);
		eiInfo.set("roomNo",roomNo);
		eiInfo.set("bedNum",bedNum);
		eiInfo.set("dormsAreas",dormsAreas);
		eiInfo.set("rent",rent);
		eiInfo.set("hospitalManageFee",hospitalManageFee);
		eiInfo.set("propertyManageFee",propertyManageFee);
		eiInfo.set("typeCode",typeCode);
		eiInfo.set("dormsPosition",dormsPosition);
		//eiInfo.set("dormsIfwc",dormsIfwc);	//去掉是否独卫
		eiInfo.set("note",note);
		if(type === "edit"){
			EiCommunicator.send("DMRM01", "update", eiInfo, {
				onSuccess : function(ei) {
					var popData = $("#popData", parent.document);
					popData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		}else{
			EiCommunicator.send("DMRM01", "insert", eiInfo, {
				onSuccess : function(ei) {
					var popData = $("#popData", parent.document);
					popData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		}
	});
	//取消按钮 实现
	$("#CANCEL").click(function () {
		window.parent['popDataWindow'].close();
	});


});

function validate(buildingCode,floorCode,roomNo,bedNum,dormsAreas,rent,hospitalManageFee,propertyManageFee){
	if(isEmpty(buildingCode)){
		NotificationUtil("宿舍楼不能为空", "error");
		return false;
	}
	if(isEmpty(floorCode)){
		NotificationUtil("宿舍层不能为空", "error");
		return false;
	}
	if(isEmpty(roomNo)){
		NotificationUtil("宿舍房间号不能为空", "error");
		return false;
	}
	//宿舍几人间
	if(isEmpty(bedNum)){
		NotificationUtil("宿舍类型不能为空", "error");
		return false;
	}
	if(isEmpty(dormsAreas)){
		NotificationUtil("宿舍房屋面积不能为空", "error");
		return false;
	}

	if(isEmpty(rent)){
		NotificationUtil("宿舍房租不能为空", "error");
		return false;
	}
	// if(isEmpty(manageFee)){
	// 	NotificationUtil("宿舍管理费不能为空", "error");
	// 	return false;
	// }
	if(isEmpty(hospitalManageFee)){
		NotificationUtil("医院管理费不能为空", "error");
		return false;
	}
	if(isEmpty(propertyManageFee)){
		NotificationUtil("物业管理费不能为空", "error");
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


/** 楼自动补全 */
/*$("#buildingCode").kendoAutoComplete({
	dataSource: dataSourceConfig("/service/DMRM01/selectSpotBuildingName", "building", ["buildingCode"]),
	dataTextField: "building",
	filter:"contains",
	minLength :0
});*/

/** 层自动补全 */
/*$("#floorCode").kendoAutoComplete({
	dataSource: dataSourceConfig("/service/DMRM01/selectSpotFloorName", "floor",["buildingCode","floorCode"]),
	dataTextField: "floor",
	filter:"contains"
});*/

/** 地点自动补全 */
$("#roomNo").kendoAutoComplete({
	dataSource: dataSourceConfig("/service/DMRM01/selectSpotRoomName", "room",["buildingCode","floorCode","roomNo"]),
	dataTextField: "room",
	filter:"contains",
	select:function(e){
		var dataItem = this.dataItem(e.item.index());
		$("#roomNo").val(dataItem.spotNum);
	}
});

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

function showAll(selector){
	var autocomplete = $("#"+selector).data("kendoAutoComplete");
	autocomplete.search("");
}

/**
 * kendoAutoComplete回显指定下拉选项数据
 * @param selector
 */
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

/**
 * 获取指定元素在数组中的索引
 * @param rows : 数据数组
 * @param value ： 元素值
 * @param selector ： 元素选择器
 * @returns {string|number}
 */
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
/**
 * 回显指定数据
 * @param selector
 * @param dataItem
 */
function echoSelect(selector, dataItem) {
	if(selector == "guaranteeNum"){
		$("[name = 'guaranteeName']").val(dataItem.workNo);		/* 报修人*/
		$("[name = 'guaranteeName_textField']").val(dataItem.name);		/* 报修人*/
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 报修科室*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 报修科室*/
	} else if (selector == "reqTelNum") {
		debugger;
		$("[name = 'reqDeptName']").val(dataItem.deptNum);		/* 报修科室*/
		$("[name = 'reqDeptName_textField']").val(dataItem.deptName);		/* 报修科室*/
		$("#buildingCode").val(dataItem.buildingCode);
		$("#floorCode").val(dataItem.floorCode);
	}
}
</script>