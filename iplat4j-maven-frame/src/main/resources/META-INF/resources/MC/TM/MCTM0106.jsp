<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	 <EF:EFRegion id="result" title="试推企业微信" fitHeight="true" >
		
		<div class="row">
			<EF:EFInput ename="workNo" cname="工号" colWidth="5" />
			<EF:EFInput ename="appCode" cname="App编码" colWidth="5" />
			<EF:EFInput ename="message" cname="消息" colWidth="5" type="hidden" />
		</div>
	
		
		<EF:EFGrid blockId="result" autoDraw="no">
			<EF:EFColumn ename="paramName" cname="参数名称" width="100" />
			<EF:EFColumn ename="paramValue" cname="参数值" width="100" />
		</EF:EFGrid>
		
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

$(function() {
	
	//发送按钮
	$("#SEND").on("click", function (e) {
		
		var workNo = IPLAT.EFInput.value(('#workNo'));
		var appCode = IPLAT.EFInput.value(('#appCode'));
		var message = IPLAT.EFInput.value(('#message'));

		var checkRows = resultGrid.getCheckedRows();
		
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].paramValue;
		}
		var info=new EiInfo();
		info.set("paramList",arr);
		info.set("workNo",workNo);
		info.set("appCode",appCode);
		info.set("message",message);

		EiCommunicator.send("MCTM0106", "sendWX", info, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			window.parent['pushWXPopDataWindow'].close();
			}
		});  
		
    });
	
	
});
</script>
