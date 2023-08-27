<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result"  fitHeight="true" >
		<div class="row">
		  	<EF:EFInput ename="resourceEname" cname="资源英文名" colWidth="4"  required="true"/>
		  	<EF:EFInput ename="resourceName" cname="资源中文名" colWidth="4"  required="true"/>
		</div>
		<div class="row">
		<EF:EFSelect ename="type"  cname="类型" required="true">
			<EF:EFCodeOption codeName="wilp.au.ar.type" textField="label" valueField="value"/>
		</EF:EFSelect>
		</div>

		
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {
		
		$("#SAVE").on("click", function (e) {
			
			var eiInfo = new EiInfo();

			var resourceEname = IPLAT.EFInput.value($("#resourceEname"));
			var resourceName = IPLAT.EFInput.value($("#resourceName"));
			var type =  IPLAT.EFSelect.value($("#type"));
			
			//参数校验
			if(!validate(resourceEname,resourceName)){
				return;
			}
			
			eiInfo.set("resourceEname",resourceEname);
			eiInfo.set("resourceName",resourceName);
			eiInfo.set("type",type);

			
			EiCommunicator.send("AUAR0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					var addPopData = $("#addPopData", parent.document);
					addPopData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		});
	

	});
	
	//参数校验
	function validate(resourceEname,resourceName){
		if(isEmpty(resourceEname)){
			NotificationUtil("资源英文名不能为空", "error");
			return false;
		} 
		if(isEmpty(resourceName)){
			NotificationUtil("资源中文名不能为空", "error");
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
