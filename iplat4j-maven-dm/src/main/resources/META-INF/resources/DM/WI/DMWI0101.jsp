<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFInput ename="id" type="hidden"/>

		<div class="row">
			<EF:EFInput ename="employeeId" cname="工号" 
				colWidth="4"  type="text" required="true"
				placeholder="请输工号" />
			<EF:EFInput ename="manName" cname="姓名" 
				colWidth="4"  type="text" required="true"
				placeholder="请输姓名" />
			<EF:EFInput ename="age" cname="请输入员工年龄" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入员工年龄" />
			
			<EF:EFSelect ename="sexCode" cname="性别" colWidth="4">
						<EF:EFOption label="请先择性别" value=""/>    
					    <EF:EFOption label="男" value="男"/>     
					    <EF:EFOption label="女" value="女"/>
    		</EF:EFSelect>

			<EF:EFSelect ename="degreeCode" cname="学历" colWidth="4">
						<EF:EFOption label="请先择学历" value=""/>
					    <EF:EFOption label="小学" value="小学"/>     
					    <EF:EFOption label="初中" value="初中"/>
					    <EF:EFOption label="高中" value="高中"/>
					    <EF:EFOption label="大学" value="大学"/>
					    <EF:EFOption label="研究生" value="研究生"/>
					    <EF:EFOption label="硕士" value="硕士"/>
					    <EF:EFOption label="博士" value="博士"/>
					    <EF:EFOption label="博士后" value="博士后"/>
    				</EF:EFSelect>
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
			
			<EF:EFInput ename="employmentNature" cname="用工性质" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入用工性质" />
			<EF:EFInput ename="phone" cname="电话" 
				colWidth="4"  readonly="true"
				placeholder="请输入电话" />
			<%-- <EF:EFInput ename="deposit" cname="押金编号" 
				colWidth="3"  type="text" required="true"
				placeholder="请输入押金编号" /> --%>
		<%-- 	<EF:EFSelect ename="isNetwork" cname="是否联网" >
						<EF:EFOption label="请先择是否联网" value=""/>    
					    <EF:EFOption label="是" value="是"/>     
					    <EF:EFOption label="否" value="否"/>
    				</EF:EFSelect> --%>
    	<%-- 	<EF:EFSelect ename="isStay" cname="是否办暂住证" >
						<EF:EFOption label="请先择是否办暂住证" value=""/>    
					    <EF:EFOption label="是" value="是"/>     
					    <EF:EFOption label="否" value="否"/>
    				</EF:EFSelect> --%>
			<EF:EFInput ename="note" cname="申请理由"
				colWidth="4" placeholder="请输入申请理由" />
			
			<EF:EFSelect ename="ifMarried" cname="婚否" colWidth="4">
						<EF:EFOption label="请输入婚否" value=""/>    
					    <EF:EFOption label="是" value="是"/>     
					    <EF:EFOption label="否" value="否"/>
    				</EF:EFSelect>
			
			<EF:EFDatePicker ename="workTime" cname="入职时间" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入入职时间" />
				
			<!-- =============================================== -->
			<EF:EFDatePicker ename="estimatedOutDate" cname="预计退房时间" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入预计退房时间" />
		<!-- <div class="row"></div> -->
			<EF:EFPopupInput ename="roomId" cname="房间地址" 
			    popupTitle="房间选择" required="true" colWidth="4"
			    popupType="ServiceGrid" serviceName="DMWI0101" methodName="queryRoom" resultId="rooms"
			    valueField="id" textField="roomName"  filterPopupGrid="false"
			    columnEnames="id,roomName,typeCode,remainBed,rent,elecPriece,waterPriece"
			    columnCnames="房间编号,房间地址,房间类型,剩余床位数,房租,水费,电费" />
			<EF:EFInput ename="rent" cname="月租费"  colWidth="4" center="true"/>
		
			<EF:EFInput ename="elecPriece" cname="月电费"
				colWidth="4"  type="text" required="true" placeholder="请输入月电费" />
			<EF:EFInput ename="waterPriece" cname="月水费" colWidth="4"  type="text" required="true" placeholder="请输入月水费" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
$(function () {
	
	$("#sexCode").val(__ei.sexCode);
	IPLAT.EFPopupInput.value($('#departmentName'),__ei.deptNum);
	IPLAT.EFPopupInput.text($('#departmentName'),__ei.departmentName);
	
	var flag1 = true;
	$("#SUBMIT").click(function(){
		if(!flag1){
			return;
		}
		flag1 = false;
		var eiInfo = new EiInfo();
		var estimatedOutDate = IPLAT.EFInput.value($("#estimatedOutDate"));
		var roomId = IPLAT.EFInput.value($("#roomId"));
		var id = IPLAT.EFInput.value($("#id"));
		var standardPriece = IPLAT.EFInput.value($("#rent"));
		var elecPriece = IPLAT.EFInput.value($("#elecPriece"));
		var waterPriece = IPLAT.EFInput.value($("#waterPriece"));
		if(estimatedOutDate == ""){
			NotificationUtil("请填写退房时间", "warning");
			flag1 = true;
			return
		}
 		eiInfo.set("estimatedOutDate",estimatedOutDate);
		eiInfo.set("roomId",roomId);
		eiInfo.set("manId",id);
		eiInfo.set("standardPriece",standardPriece);
		eiInfo.set("elecPriece",elecPriece);
		eiInfo.set("waterPriece",waterPriece);

		EiCommunicator.send("DMWI01", "insert", eiInfo, {
			onSuccess : function(ei) {
				var popData = $("#popData", parent.document);
				popData.kendoWindow().data("kendoWindow").close();
				NotificationUtil(ei.getMsg(), "success");
				//setTimeout(function(){flag1 = true;},500);
				setTimeout(function() {
					flag1 = true;
					window.parent.location.reload()
				}, 600);
			}
		});
	});
});

$(function() {
	// 安装地方选择后自动带出后面的楼和层
	$("#roomId").blur(function(e){
		var roomId = e.currentTarget.value;
		console.log(roomId);
		var eiInfo = new EiInfo();
		eiInfo.set("roomId",roomId);
		EiCommunicator.send("DMWI0101", "queryRoom", eiInfo, {
			onSuccess : function(ei) {
				var roomName = ei.get("roomName");
				var typeCode = ei.get("typeCode");
				var remainBed = ei.get("remainBed");
				var rent = ei.get("rent");
				IPLAT.EFInput.value($("#roomName"),roomName);
				IPLAT.EFInput.value($("#typeCode"),typeCode);
				IPLAT.EFInput.value($("#remainBed"),remainBed);
				IPLAT.EFInput.value($("#rent"),rent);
			}
		});
		
	});

});

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




