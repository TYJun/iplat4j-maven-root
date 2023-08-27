<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
	<EF:EFRegion id="result" title="编辑" fitHeight="true" >
		<div class="row">
			<EF:EFInput ename="id" cname="id" colWidth="5"   disabled="true"/>
			<EF:EFInput ename="staffid" cname="员工工号" colWidth="5"   disabled="true"/>
			<EF:EFInput ename="name_" cname="姓名" colWidth="5"   required="true"/>
			<EF:EFInput ename="tel" cname="移动电话" ccolWidth="5"   required="true"/>
			<EF:EFInput ename="email" cname="电子邮件" colWidth="5"  required="true"/>
			<EF:EFInput ename="grade_filter" cname="报警等级过滤" colWidth="5"  required="true"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
			var eiInfo = new EiInfo();
			var staffid = IPLAT.EFInput.value($("#staffid"));
			var name_ = IPLAT.EFInput.value($("#name_"));
			var tel = IPLAT.EFInput.value($("#tel"));
			var email = IPLAT.EFInput.value($("#email"));
			var grade_filter = IPLAT.EFInput.value($("#grade_filter"));
			eiInfo.set("eiInfo",eiInfo);
			eiInfo.set("staffid",staffid);
			eiInfo.set("name_",name_);
			eiInfo.set("tel",tel);
			eiInfo.set("email",email);
			eiInfo.set("grade_filter",grade_filter);
			$("#SUBMIT").click(function(e) {
			var node = $('#MSPA0101');
			var staffid = $("#staffid").val();
			var name_ = $("#name_").val();
			var tel = $("#tel").val();
			var email = $("#email").val();
			var grade_filter = $("#grade_filter").val();
				if(staffid == null || staffid ==""){
					IPLAT.alert("员工工号不可为空")
				}else if(name_ == null || name_ ==""){
					IPLAT.alert("姓名不可为空")
				}else if(tel == null || tel ==""){
					IPLAT.alert("移动电话不可为空")
				}else if(email == null || email ==""){
					IPLAT.alert("电子邮件不可为空")
				}else if(grade_filter == null || grade_filter ==""){
					IPLAT.alert("报警等级过滤不可为空")
				}else{
					IPLAT.submitNode(node, 'MSPA0101', 'update', {
						onSuccess : function(eiInfo) {
							NotificationUtil("修改成功", "");
							//关闭弹窗
							window.parent['popDataWindow'].close();
							window.parent['resultGrid'].dataSource.query(1);
						},
						onFail : function(errorMsg, status, e) {
							NotificationUtil("修改失败，原因[" + errorMsg + "]", "error");
						}
					});
				}
		});

		$("#CANCEL").click(function() {
			window.parent['popDataWindow'].close();
		})
	});
</script>