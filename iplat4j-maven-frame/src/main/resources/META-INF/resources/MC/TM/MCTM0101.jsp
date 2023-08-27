<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	 <EF:EFRegion id="result"  fitHeight="true" >
	 <div class="col-md-12">
		<div class="row">
		  	<EF:EFInput ename="templateName" cname="模板名称" colWidth="5"  required="true"/>
<%--		  	<EF:EFSelect ename="callModule" cname="调用模块" colWidth="5"  required="true">--%>
<%--				<EF:EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>--%>
<%--			</EF:EFSelect>--%>
			<EF:EFSelect ename="callModule" cname="调用模块" colWidth="5"  required="true">
				<%--					<EF:EFCodeOption codeName="wilp
				.mc.tm.callModule" textField="label" valueField="value"/>--%>

				<EF:EFTableOption schema="platSchema" tableName="tedpi02" textField="MODULE_CNAME_1"
								  valueField="MODULE_ENAME_1" condition="PROJECT_ENAME='WILP'"/>
			</EF:EFSelect>
		</div>

		<div class="row">
			<EF:EFSelect ename="deliveryChannel" cname="发送渠道" colWidth="5"  required="true">
		  		<EF:EFOption label="短信" value="DX"/>
				<EF:EFOption label="推送" value="PS"/>
		  		<EF:EFOption label="钉钉" value="DD"/>
		  		<EF:EFOption label="企业微信" value="WX"/>
		  	</EF:EFSelect>	
			
			<EF:EFSelect ename="isCc" cname="是否抄送" colWidth="5"  required="true">
		  		<EF:EFOption label="是" value="1"/>
		  		<EF:EFOption label="否" value="0"/>
		  	</EF:EFSelect>	
		</div>
		
		<div class="row">
			<EF:EFPopupInput ename="guaranteeName" cname="群组"  colWidth="5"
				popupTitle="选择群组" required="false"
				popupType="ServiceGrid" serviceName="MCTM0101" methodName="queryVariable" resultId="dept"
				valueField="variableCode" textField="variableName"
				columnEnames="variableCode,variableName" columnCnames="群组编码,群组名称" />
		<div class="row">
		
		</div>
			<EF:EFInput ename="message" cname="消息模板" type="textarea" rows="4" colWidth="10" ratio="2:10" />
		</div>
	 </div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

$(function() {

	//保存按钮
	$("#SAVE").on("click", function (e) {
		var templateName = IPLAT.EFInput.value(('#templateName'));
		var callModule = IPLAT.EFSelect.value(('#callModule'));
		var deliveryChannel = IPLAT.EFSelect.value(('#deliveryChannel'));
		var isCc = IPLAT.EFSelect.value(('#isCc'));
		var message = IPLAT.EFInput.value(('#message'));
		var variableCode = IPLAT.EFPopupInput.value($("#guaranteeName"));
		
		//参数校验
		if(!validate(templateName,message)){
			return;
		}
		
		var info=new EiInfo();
		info.set("templateName",templateName);
		info.set("callModule",callModule);
		info.set("deliveryChannel",deliveryChannel);
		info.set("isCc",isCc);
		info.set("message",message);
		info.set("variableCode",variableCode);

		EiCommunicator.send("MCTM0101", "insert", info, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			window.parent.resultGrid.dataSource.page(1);
			window.parent['addPopDataWindow'].close();
			}
		});  
		
    });
});


//参数校验
function validate(templateName,message){
	if(isEmpty(templateName)){
		NotificationUtil("模板名称不能为空", "error");
		return false;
	} 
	if(isEmpty(message)){
		NotificationUtil("消息模板不能为空", "error");
		return false;
	}

	// 长度验证
	if(isOverLegth(message)){
		NotificationUtil("消息模板长度不能大于150个字符", "error");
		return false;
	}


	if(isIllegal(message)){
		NotificationUtil("消息模板格式非法", "error");
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

function isOverLegth(message) {
	return message.length >= 150;
}

function isIllegal(msg) {
	var count = 0;
	for (var i = 0; i < msg.length; i++) {
		if (msg[i] === '#'){
			count++;
		}
	}
	return count % 2 !== 0;
}
</script>
