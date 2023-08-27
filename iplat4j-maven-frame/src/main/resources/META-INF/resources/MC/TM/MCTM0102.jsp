<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	 <EF:EFRegion id="result"  fitHeight="true" >
		<div class="row">
			<EF:EFInput ename="id" cname="主键ID" colWidth="5"  type="hidden"/>
		  	<EF:EFInput ename="templateCode" cname="模板代码" colWidth="5" readonly="true" required="true"/>
		  	<EF:EFInput ename="templateName" cname="模板名称" colWidth="5"  required="true"/>
		</div>

		<div class="row">
			<EF:EFSelect ename="callModule" cname="调用模块" colWidth="5"  required="true">
<%--				<EF:
EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>--%>
				<EF:EFTableOption schema="platSchema" tableName="tedpi02" textField="MODULE_CNAME_1"
								  valueField="MODULE_ENAME_1" condition="PROJECT_ENAME='WILP'"/>
			</EF:EFSelect>
			<EF:EFSelect ename="deliveryChannel" cname="发送渠道" colWidth="5"  required="true">
		  		<EF:EFOption label="短信" value="DX"/>
		  		<EF:EFOption label="钉钉" value="DD"/>
		  		<EF:EFOption label="企业微信" value="WX"/>
		  	</EF:EFSelect>	
		</div>
		
		<div class="row">
			<EF:EFSelect ename="isCc" cname="是否抄送" colWidth="5"  required="true">
		  		<EF:EFOption label="是" value="1"/>
		  		<EF:EFOption label="否" value="0"/>
		  	</EF:EFSelect>	
			<EF:EFPopupInput ename="variableName" cname="变量"  colWidth="5"
				popupTitle="选择变量" required="false" 
				popupType="ServiceGrid" serviceName="MCTM0101" methodName="queryVariable" resultId="dept"
				valueField="variableCode" textField="variableName"
				columnEnames="variableCode,variableName" columnCnames="变量编码,变量名称" />
		</div>

		 <div class="row">
			 <EF:EFInput ename="message" cname="消息模板" type="textarea" rows="4" colWidth="10" ratio="2:10" />
		 </div>
		
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

$(function() {
	IPLAT.EFPopupInput.text("#variableName",__ei.variableName);
	IPLAT.EFPopupInput.value("#variableName",__ei.variableCode);
	//保存按钮
	$("#SAVE").on("click", function (e) {
		var id = IPLAT.EFInput.value(('#id'));
		var templateName = IPLAT.EFInput.value(('#templateName'));
		var callModule = IPLAT.EFSelect.value(('#callModule'));
		var deliveryChannel = IPLAT.EFSelect.value(('#deliveryChannel'));
		var isCc = IPLAT.EFSelect.value(('#isCc'));
		var message = IPLAT.EFInput.value(('#message'));
		var variableCode = IPLAT.EFPopupInput.value($("#variableName"));

		var info=new EiInfo();
		info.set("id",id);
		info.set("templateName",templateName);
		info.set("callModule",callModule);
		info.set("deliveryChannel",deliveryChannel);
		info.set("isCc",isCc);
		info.set("message",message);
		info.set("variableCode",variableCode);
		

		EiCommunicator.send("MCTM0102", "update", info, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			window.parent.resultGrid.dataSource.page(1);
			window.parent['deitPopDataWindow'].close();
			}
		});  
		
    });
});
</script>
