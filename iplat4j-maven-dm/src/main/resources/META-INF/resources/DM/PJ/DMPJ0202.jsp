<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="评价项目修改">
	<EF:EFRegion id="EDIT">
		<div class="row">			
			<EF:EFInput ename="moduleId" cname="项目分类id" colWidth="10" type="hidden"/>
			<EF:EFInput ename="id" cname="项目主键" colWidth="10" type="hidden"/>
			<EF:EFInput ename="code" cname="项目编码" colWidth="10" type="hidden"/>
			<EF:EFInput ename="content" cname="项目名称" colWidth="10" type="text" required="true"/>
			<EF:EFInput ename="memo" cname="备注" colWidth="10" type="text"/>
		</div>
	</EF:EFRegion>
	
</EF:EFPage>
<script type="text/javascript">
	$(function () {
		$("#SUBMITEDIT").click(function(){
			var eiInfo = new EiInfo();
			var moduleId = IPLAT.EFInput.value($("#moduleId"));
			var id = IPLAT.EFInput.value($("#id"));
			var code = IPLAT.EFInput.value($("#code"));
			var content = IPLAT.EFInput.value($("#content"));
			var memo = IPLAT.EFInput.value($("#memo"));
			
			//判断
			if (content == null || content == "") {
				NotificationUtil("评价项目名称不得为空", "error");
				return;
			}
			
			eiInfo.set("moduleId",moduleId);
			eiInfo.set("id",id);
			eiInfo.set("code",code);
			eiInfo.set("content",content);
			eiInfo.set("memo",memo);
			EiCommunicator.send("DMPJ0202", "update", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataEditWindow'].close();
				}
			});
		});
	});
</script>