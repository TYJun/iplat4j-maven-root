<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result" title="修改人员" fitHeight="true" >

		<div class="row">
			<EF:EFInput ename="id" cname="人员ID" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="workNo" cname="工号" readonly="true"/>
			<EF:EFInput ename="name" cname="姓名"    required="true"/>
			<EF:EFSelect ename="gender" cname="性别" >
				<EF:EFOption label="男" value="1"/>
				<EF:EFOption label="女" value="0"/>
			</EF:EFSelect>

		</div>
		<div class="row">
			<EF:EFInput ename="idNo" cname="身份证号"   />
			<EF:EFInput ename="contactTel" cname="联系电话"   />
			<EF:EFTreeInput ename="deptName" cname="所属科室" bindId="tree01" readonly="true"
							serviceName="ACDE01" methodName="queryTree"
							textField="deptName" valueField="label" hasChildren="leaf"
							root="{label: 'root',deptName: '根节点'}" popupTitile="上级分类" clear="true"
							ratio="4:8" required="true">
			</EF:EFTreeInput>


				<%--<EF:EFSelect ename="staff" cname="员工类型：" resultId="staff"
                    textField="staffName" valueField="staffType" serviceName="ACDE0101"
                    methodName="queryStaff" optionLabel="请选择"   ratio="4:8"
                    filter="contains" bindId="staff">
                </EF:EFSelect>--%>

		</div>
		<div class="row">
			<EF:EFSelect ename="type"  cname="员工类型" >
				<EF:EFOption label="请先择员工类型" value=""/>
				<EF:EFCodeOption codeName="wilp.ac.pe.type" textField="label" valueField="value"/>
			</EF:EFSelect>


			<EF:EFSelect ename="isService" cname="服务人员"    required="true">
				<EF:EFOption label="是" value="1"/>
				<EF:EFOption label="否" value="0"/>
			</EF:EFSelect>
		</div>

	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	$(function() {
		
		// 回显上级科室
		$("#deptName").val(__ei.deptId);
		$("#deptName_textField").val(__ei.deptName);
		
		$("#SUBMIT").on("click", function (e) {
			
			var eiInfo = new EiInfo();
			
			var id = IPLAT.EFInput.value($("#id"));
			var workNo = IPLAT.EFInput.value($("#workNo"));
			var name = IPLAT.EFInput.value($("#name"));
			var deptId = $("#deptName").val();
			var contactTel = IPLAT.EFInput.value($("#contactTel"));
			var idNo = IPLAT.EFInput.value($("#idNo"));
			var type = IPLAT.EFSelect.value($("#type"));
			var gender = IPLAT.EFSelect.value($("#gender"));
			var isService = IPLAT.EFSelect.value($("#isService"));
			
			//参数校验
			if(!validate(workNo,name,deptId)){
				return;
			}
			
			eiInfo.set("id",id);
			// eiInfo.set("workNo",workNo);
			eiInfo.set("name",name);
			eiInfo.set("deptId",deptId);
			eiInfo.set("contactTel",contactTel);
			eiInfo.set("idNo",idNo);
			eiInfo.set("type",type);
			eiInfo.set("gender",gender);
			eiInfo.set("isService",isService);
			
			EiCommunicator.send("ACPE0102", "update", eiInfo, {
				onSuccess : function(ei) {
					var deitPopData = $("#deitPopData", parent.document);
					deitPopData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		});
	

	});
	
	//参数校验
	function validate(workNo,name,deptId){
		if(isEmpty(workNo)){
			NotificationUtil("工号不能为空", "error");
			return false;
		}
		if(isEmpty(name)){
			NotificationUtil("姓名不能为空", "error");
			return false;
		} 
		if(isEmpty(deptId)){
			NotificationUtil("所属科室不能为空", "error");
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
