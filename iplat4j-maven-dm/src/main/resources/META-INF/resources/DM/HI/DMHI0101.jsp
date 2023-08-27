<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFInput ename="id" type="hidden"/>

		<div class="row">
			<EF:EFInput ename="id" cname="id"  type="hidden"/>
			<EF:EFInput ename="employeeId" cname="工号" 
				colWidth="5"  type="text" required="true" readonly="true" />
			<EF:EFInput ename="manName" cname="姓名" 
				colWidth="6"  type="text" required="true" readonly="true" />
			<EF:EFInput ename="curRoom" cname="当前地址" 
				colWidth="5"  type="text" required="true" readonly="true" />
			<EF:EFInput ename="sexCode" cname="性别"
						colWidth="6"  type="text" required="true" readonly="true" />
			<EF:EFPopupInput ename="roomId" cname="新的地址" 
		    popupTitle="房间选择" required="true" 
		    popupType="ServiceGrid" serviceName="DMWI0101" methodName="queryRoom" resultId="rooms"
		    valueField="id" textField="roomName" colWidth="5" filterPopupGrid="false"
		    columnEnames="id,roomName,typeCode,remainBed" columnCnames="房间编号,房间地址,宿舍类型,剩余床位数" />
			<EF:EFDatePicker ename="changeTime" cname="预计换房时间"
							 colWidth="6"  type="text" required="true"
							 placeholder="请输换房职时间" />
	
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
$(function () {
	$("#SUBMIT").click(function(){
		var eiInfo = new EiInfo();
		var roomId = IPLAT.EFInput.value($("#roomId"));
		var id = IPLAT.EFInput.value($("#id"));
		var changeTime = IPLAT.EFInput.value($("#changeTime"));
		if(!validate(roomId,changeTime)){
			return;
		}
		eiInfo.set("roomId",roomId);
		eiInfo.set("id",id);
		eiInfo.set("changeTime",changeTime);
		EiCommunicator.send("DMWI01", "update", eiInfo, {
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
//判断是否为空
function validate(roomId,changeTime){
	if(isEmpty(roomId)){
		NotificationUtil("新的地址不能为空", "error");
		return false;
	}
	if(isEmpty(changeTime)){
		NotificationUtil("换房时间不能为空", "error");
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

/**
 * 测试专用
 @parm vals 变量数组
 */

function printVal(vals){
	for(var i = 0; i < vals.length; i++){
		console.log(vals[i]);
	}
}
</script>




