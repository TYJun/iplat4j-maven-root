<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result"  fitHeight="true" >
    	<EF:EFInput ename="id" cname="id" colWidth="4"  type="hidden"/>
		<div class="row">
		  	<EF:EFInput ename="roleName" cname="角色名称" colWidth="4"  required="true"/>
		</div>

		<div class="row">
			<EF:EFInput ename="remark" type="textarea" colWidth="4" cname="备注"/>
		</div>
		
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {
		
		$("#SAVE").on("click", function (e) {
			
			var eiInfo = new EiInfo();

			var id = IPLAT.EFInput.value($("#id"));
			var roleName = IPLAT.EFInput.value($("#roleName"));
			var remark = IPLAT.EFInput.value($("#remark"));
			
			//参数校验
			if(!validate(roleName)){
				return;
			}
			
			eiInfo.set("id",id);
			eiInfo.set("roleName",roleName);
			eiInfo.set("remark",remark);

			
			EiCommunicator.send("AURO0102", "update", eiInfo, {
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
	function validate(roleName){
		if(isEmpty(roleName)){
			NotificationUtil("角色名称不能为空", "error");
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
