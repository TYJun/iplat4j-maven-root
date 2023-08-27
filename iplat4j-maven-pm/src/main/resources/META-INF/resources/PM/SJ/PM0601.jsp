<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="归档结算" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-documentsCode" cname="归档编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-documentsName" cname="归档名称" required="true" readOnly="true"/>
			<EF:EFPopupInput ename="inqu_status-0-submitterCode" cname="乙方提交人员" readOnly="true"
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-recipientCode" cname="甲方收件人员" readOnly="true"
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFInput ename="inqu_status-0-charge" cname="结算费用" required="true" readOnly="true" />
			<EF:EFSelect ename="inqu_status-0-materialStatusCode" cname="是否收到决算材料" readonly="true">
				<EF:EFOption label="收到" value="1"/>
				<EF:EFOption label="未收到" value="0"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="是否施工确认" readonly="true">
				<EF:EFOption label="已确认" value="1"/>
				<EF:EFOption label="未确认" value="0"/>
			</EF:EFSelect>
			<EF:EFPopupInput ename="inqu_status-0-dataSubmitterCode" cname="资料提交人员" 
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-dataRecipientCode" cname="审计接受人员" 
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-dataCode" cname="提交资料" 
				popupTitle="资料选择"  popupWidth="400" clear="true"
				popupType="DynamicGrid"  codeName="pm.projectData"
				valueField="ITEM_CODE" textField="ITEM_CNAME"
				columnEnames="ITEM_CODE,ITEM_CNAME" columnCnames="资料编码, 资料名称" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVE" layout="0" ></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		var validator = IPLAT.Validator({id: "result"});//开启校验
		
		//保存
		$("#SAVE").on("click", function() {
			//获取参数
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			//参数校验
			if(!validateArchive(eiInfo)){ return; }
			//提交
			EiCommunicator.send("PM0601", "editArchive", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultAGrid"].dataSource.page(1);
					window.parent["resultBGrid"].dataSource.page(1);
				}
			})
		});
		
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow();
		});
	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['archiveWindow'].close();
	}
	
	//参数校验
	function validateArchive(eiInfo){
		if(isEmpty(eiInfo.get("inqu_status-0-dataSubmitterCode_textField"))){
			IPLAT.NotificationUtil("资料提交人员不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-dataRecipientCode_textField"))){
			IPLAT.NotificationUtil("审计接受人员不能为空");
			return false;
		}
		return true;
	}
	
	function isEmpty(str) { 
		if(str == undefined){
			return true;
		}
		if(str == null){
			return true;
		}
		if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		}
		return false;
	}
</script>

