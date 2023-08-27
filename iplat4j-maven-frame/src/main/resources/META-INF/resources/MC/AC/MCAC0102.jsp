<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="result"  fitHeight="true" >
		<div class="col-md-12">
			<div class="row">
				<EF:EFInput ename="id" cname="id" colWidth="5"  type="hidden"/>
				<EF:EFInput ename="appName" cname="APP名称" colWidth="5"  required="true"/>
				<EF:EFInput ename="appKey" cname="AppKey" colWidth="5"  required="true"/>
			</div>
			<div class="row">
				<EF:EFInput ename="appId" cname="AppId" colWidth="5"  required="true"/>
				<EF:EFInput ename="masterSecret" cname="MasterSecret" colWidth="5"  required="true"/>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

$(function() {

	//保存按钮
	$("#SAVE").on("click", function (e) {
		var id = IPLAT.EFInput.value(('#id'));
		var appName = IPLAT.EFInput.value(('#appName'));
		var appKey = IPLAT.EFInput.value(('#appKey'));
		var appId = IPLAT.EFInput.value(('#appId'));
		var masterSecret = IPLAT.EFInput.value(('#masterSecret'));
		//参数校验
		if(!validate(appName,appKey,appId,masterSecret)){
			return;
		}
		var info=new EiInfo();
		info.set("id",id);
		info.set("appName",appName);
		info.set("appKey",appKey);
		info.set("appId",appId);
		info.set("masterSecret",masterSecret);

		EiCommunicator.send("MCAC0102", "update", info, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			window.parent.resultGrid.dataSource.page(1);
			window.parent['editPopDataWindow'].close();
			}
		});  
		
    });
});


//参数校验
function validate(appName,appKey,appId,masterSecret){
	if(isEmpty(appName)){
		NotificationUtil("APP名称不能为空", "error");
		return false;
	}
	if(isEmpty(appKey)){
		NotificationUtil("AppKey不能为空", "error");
		return false;
	}

	// 长度验证
	if(isEmpty(appId)){
		NotificationUtil("AppID不能为空", "error");
		return false;
	}


	if(isEmpty(masterSecret)){
		NotificationUtil("MasterSecret不能为空", "error");
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
</script>
