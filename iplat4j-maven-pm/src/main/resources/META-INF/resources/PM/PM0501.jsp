<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="归档结算" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-projectNos" cname="项目号" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-documentsCode" cname="归档编码" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-documentsName" cname="归档名称" required="true"/>
			<EF:EFPopupInput ename="inqu_status-0-submitterCode" cname="乙方提交人员" 
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-recipientCode" cname="甲方收件人员" 
				popupTitle="人员选择" required="true" popupWidth="400"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFInput ename="inqu_status-0-charge" cname="结算费用" required="true" 
				data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字"/>
			<EF:EFSelect ename="inqu_status-0-materialStatusCode" cname="是否收到决算材料" >
				<EF:EFOption label="收到" value="1"/>
				<EF:EFOption label="未收到" value="0"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="是否施工确认">
				<EF:EFOption label="已确认" value="1"/>
				<EF:EFOption label="未确认" value="0"/>
			</EF:EFSelect>
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
			EiCommunicator.send("PM0501", "archiveSettlement", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.page(1);
					window.parent["resultAGrid"].dataSource.page(1);
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
		if(isEmpty(eiInfo.get("inqu_status-0-documentsName"))){
			IPLAT.NotificationUtil("归档名称不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-submitterCode"))){
			IPLAT.NotificationUtil("乙方提交人员不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-recipientCode"))){
			IPLAT.NotificationUtil("甲方收件人员不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-charge"))){
			IPLAT.NotificationUtil("结算费用不能为空");
			return false;
		}
		//归档结算费用
		var regst= /^\d*\.?\d+$/;
		if(!regst.test(eiInfo.get("inqu_status-0-charge"))){
			IPLAT.NotificationUtil("请重新输入归档结算费用为正数");
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

