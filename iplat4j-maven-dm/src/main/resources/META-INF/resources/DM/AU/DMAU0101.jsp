<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFInput ename="type" type="hidden"/>
		<EF:EFInput ename="id" type="hidden"/>
		<EF:EFInput ename="departdmentName" type="hidden"/> 
		<EF:EFInput ename="departmentNo" type="hidden"/>


		<div class="row">
		<EF:EFPopupInput ename="name" cname="姓名" colWidth="4"
						 popupType="ServiceGrid"
						 popupTitle="人员信息"
						 required="true"
						 serviceName="DMHM01" methodName="queryPerson"
						 resultId="person"
						 autofit="true"
						 valueField="workNo" textField="name"
						 columnEnames="name,workNo,gender,contactTel,idNo"
						 columnCnames="姓名,工号,性别,电话,身份证号"
						 backFillFieldIds = "workNo,gender,contactTel,idNo"
						 backFillColumnIds = "workNo,gender,contactTel,idNo"
						 filterPopupGrid="false"/>
		    <EF:EFInput ename="employeeId" colWidth="5" cname="工号" center="true" type="hidden"/>
		    <EF:EFInput ename="workNo" colWidth="4" cname="工号"  center="true"/>
		    <EF:EFInput ename="idNo" colWidth="4" cname="身份证号" />
			<EF:EFInput ename="contactTel" cname="电话" colWidth="4" />
			<EF:EFInput ename="gender" colWidth="4" cname="性别"  center="true"/>

       </div>



		<%--<EF:EFPopupInput ename="workNo" cname="工号"  colWidth="5" ratio="3:8"
						 popupTitle="管理员选择" required="true" readOnly="true"
						 popupType="ServiceGrid" serviceName="DMHM01" methodName="queryPerson" resultId="person"
						 valueField="workNo" textField="name" filterPopupGrid="false"
						 columnEnames="workNo,name,gender,contactTel,idNo" columnCnames="工号,姓名,性别,电话,身份证号" />
		<EF:EFInput ename="employeeId" colWidth="5" cname="工号"  center="true" type="hidden"/>
		<EF:EFInput ename="name" colWidth="6" cname="姓名"  center="true"/>
		<EF:EFInput ename="idNo" colWidth="5" cname="身份证号" />
		<EF:EFInput ename="age" colWidth="6" cname="年龄"  center="true"/>--%>

		<%--	<EF:EFInput ename="employeeId" cname="工号"
				colWidth="5"  type="text" required="true"
				placeholder="请输工号" />
			<EF:EFInput ename="manName" cname="姓名" 
				colWidth="6"  type="text" required="true"
				placeholder="请输姓名" />
			<EF:EFInput ename="idNo" cname="身份证号" 
				colWidth="5"  type="text" required="true"
				placeholder="请输入身份证号"  />
			<EF:EFInput ename="age" cname="员工年龄"  required="true"
				colWidth="6"  type="text" 
				placeholder="请输入员工年龄" />--%>

		<div class="row">
			<%--<EF:EFSelect ename="sexCode" cname="性别"  required="true" colWidth="5">
						<EF:EFOption label="请先择性别" value=""/>    
					    <EF:EFOption label="男" value="男"/>     
					    <EF:EFOption label="女" value="女"/>
    		</EF:EFSelect>--%>

			<EF:EFSelect ename="degreeCode" cname="学历" colWidth="4">
						<EF:EFOption label="请选择学历" value=""/>
					    <EF:EFOption label="小学" value="小学"/>     
					    <EF:EFOption label="初中" value="初中"/>
					    <EF:EFOption label="高中" value="高中"/>
					    <EF:EFOption label="大学" value="大学"/>
					    <EF:EFOption label="研究生" value="研究生"/>
					    <EF:EFOption label="硕士" value="硕士"/>
					    <EF:EFOption label="博士" value="博士"/>
					    <EF:EFOption label="博士后" value="博士后"/>
    				</EF:EFSelect>
			<%-- <EF:EFInput ename="departmentNo" cname="部门科室编号" 
				colWidth="3"  required="true"
				placeholder="请输入部门科室编号"  type="hidden" /> --%>
			<%-- <EF:EFInput ename="departmentName" cname="部门科室名称" 
				colWidth="3"  type="text" required="true"
				placeholder="请输入部门科室名称" /> --%>
		<%--	<EF:EFPopupInput ename="departmentName" cname="部门科室名称"  colWidth="5"
				popupTitle="科室选择" required="true" 
				popupType="ServiceGrid" serviceName="YSDAKS01" methodName="query" resultId="dept"
				valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />--%>
			<EF:EFPopupInput ename="departmentName" colWidth="4" cname="部门科室名称"
							 popupHeight="575" popupWidth="550" required="true" maxLength="200"
							 containerId="ef_popup_iframe" resizable="true" draggable="true"
							 popupTitle="科室选择" center="true" readonly="true">
			</EF:EFPopupInput>
			<!-- 弹出的 iframe -->
			<div id="ef_popup_iframe" style=" display: none">

				<div class="row" style="height: 90%">
					<iframe frameborder="0" title="" style="width:100%;height:100%;"
							class="k-contentframe" src="${ctx}/web/DMDAKS01?fn1=setCheckMode&arg1=single,row"></iframe>
				</div>

				<div class="k-window-save k-popup-save">
					<EF:EFButton ename="ef_popup_iframe_fillback" cname="确定" class="i-btn-wide window-btn">
					</EF:EFButton>
				</div>

			</div>


			<EF:EFSelect ename="employmentNature" cname="职工属性" required="true" colWidth="4" >
						<EF:EFOption label="请先择职工属性" value=""/>    
					    <EF:EFOption label="本院职工" value="本院职工"/>     
					    <EF:EFOption label="外协单位员工" value="外协单位员工"/>
					    <EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
					    <EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
					    <EF:EFOption label="进修医生" value="进修医生"/>
					    <EF:EFOption label="进修护士" value="进修护士"/>
					    <EF:EFOption label="实习医生" value="实习医生"/>
					    <EF:EFOption label="实习护士" value="实习护士"/>
					    <EF:EFOption label="研究生" value="研究生"/>
					    <EF:EFOption label="临时医技人员" value="临时医技人员"/>
					    <EF:EFOption label="规范会培训生" value="规范会培训生"/>
					    <EF:EFOption label="院聘临时工" value="院聘临时工"/>
					    <EF:EFOption label="科聘临时工" value="科聘临时工"/>
    				</EF:EFSelect>

			<EF:EFInput ename="age" colWidth="4" cname="年龄"  center="true"/>
			<EF:EFInput ename="note" cname="申请理由"
				colWidth="4"  type="text"
				placeholder="请输入申请理由" />
			
			<EF:EFSelect ename="ifMarried" cname="婚否" colWidth="4">
						<EF:EFOption label="请输入婚否" value=""/>    
					    <EF:EFOption label="是" value="是"/>     
					    <EF:EFOption label="否" value="否"/>
    				</EF:EFSelect>
			<EF:EFInput ename="permanentResidence" cname="户口所在地" 
				colWidth="4"  type="text"
				placeholder="请输入户口所在地" />
			<EF:EFDatePicker ename="workTime" cname="入职时间" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入入职时间" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

/*	loadMtConfig();
	$("#workNo").val(__ei.workNo);*/


$(function () {
	console.log(__ei);
	debugger;
	IPLAT.EFPopupInput.text($('#name'),__ei.name);
	IPLAT.EFPopupInput.value($('#departmentName'),__ei.departmentNo);
	IPLAT.EFPopupInput.text($('#departmentName'),__ei.departmentName);
	
	
	$("#SUBMIT").click(function(){
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
		var eiInfo = new EiInfo();
		var type = IPLAT.EFInput.value($("#type"));
		var id  = IPLAT.EFInput.value($("#id"));
		var employeeId = IPLAT.EFInput.value($("#workNo"));
		var manName = IPLAT.EFInput.value($("#name"));
		var idNo = IPLAT.EFInput.value($("#idNo"));
		var sexCode = IPLAT.EFInput.value($("#gender"));
		var degreeCode = IPLAT.EFSelect.value($("#degreeCode"));
		/* var departmentNo = IPLAT.EFInput.value($("#departmentNo")); */
		var departmentName = IPLAT.EFPopupInput.text($("#departmentName"));
		var departmentNo = IPLAT.EFPopupInput.value($("#departmentName"));
		var employmentNature = IPLAT.EFSelect.value($("#employmentNature"));
		var phone = IPLAT.EFInput.value($("#contactTel"));
/* 		var deposit = IPLAT.EFInput.value($("#deposit"));
		var isNetwork = IPLAT.EFSelect.value($("#isNetwork"));
		var isStay = IPLAT.EFSelect.value($("#isStay")); */
		var note = IPLAT.EFInput.value($("#note"));
		var age = IPLAT.EFInput.value($("#age"));
		var ifMarried = IPLAT.EFSelect.value($("#ifMarried"));
		var permanentResidence = IPLAT.EFInput.value($("#permanentResidence"));
		var workTime = IPLAT.EFInput.value($("#workTime"));
		//参数校验
		if(!validate(departmentName,employmentNature,age,workTime)){
			return;
		}
		if(type === "edit"){
			eiInfo.set("id",id);
		}
		eiInfo.set("employeeId",employeeId);
		eiInfo.set("manName",manName);
		eiInfo.set("idNo",idNo);
//		eiInfo.set("isStay",isStay);
		eiInfo.set("sexCode",sexCode);
		eiInfo.set("degreeCode",degreeCode);
		eiInfo.set("departmentNo",departmentNo);
		eiInfo.set("departmentName",departmentName);
		eiInfo.set("employmentNature",employmentNature);
		eiInfo.set("phone",phone);
//		eiInfo.set("deposit",deposit);
//		eiInfo.set("isNetwork",isNetwork);
		eiInfo.set("note",note);
		eiInfo.set("age",age);
		eiInfo.set("ifMarried",ifMarried);
		eiInfo.set("permanentResidence",permanentResidence);
		eiInfo.set("workTime",workTime);
		if(type === "edit"){
			EiCommunicator.send("DMHM01", "update", eiInfo, {
				onSuccess : function(ei) {
					var popData = $("#popData", parent.document);
					popData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		}else{
			EiCommunicator.send("DMHM01", "insert", eiInfo, {
				onSuccess : function(ei) {
					var popData = $("#popData", parent.document);
					popData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		}
		
		
	});
});



//参数校验
function validate(departmentName,employmentNature,age,workTime){


	if(isEmpty(age)){
		NotificationUtil("入住人年龄不能为空", "error");
		return false;
	} 


	if(isEmpty(employmentNature)){
		NotificationUtil("入住人用工性质不能为空", "error");
		return false;
	} 

	
	if(isEmpty(workTime)){
		NotificationUtil("入住人入职时间不能为空", "error");
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

$("#ef_popup_iframe_fillback").on("click",function (e) {
	var checkRows = window.frames[0].resultGrid.getCheckedRows();
	if(checkRows && checkRows.length > 0){
		var values = [],texts = [];
		for (var i = 0; i < checkRows.length; i++) {
			values.push(checkRows[i]["deptNum"]);
			texts.push(checkRows[i]["deptName"]);
		}
		$("#departmentName").val(checkRows[0]["deptNum"]);
		$("#departmentName_textField").val(checkRows[0]["deptName"]);
		//IPLAT.EFSelect.value($("#fromDeptNo"),checkRows[0]["deptNum"]);
		var windowFrame = $("#ef_popup_iframe").data("kendoWindow");
		windowFrame.close();
	}else{
		NotificationUtil("请选择科室！", "warning");
	}
});

</script>




