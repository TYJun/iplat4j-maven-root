<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 加载application配置文件 读取医院名称 -->
<fmt:setBundle basename="application" var="app" />
<fmt:message key="hospitalName" var="hospitalName" bundle="${app}" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">

	<EF:EFRegion id="pictureUploadOne" title="登录页背景图片上传">
		<li>上传图片只支持.png格式</li>
		<li>登录页背景图建议上传长宽为1450*900的图片,否则界面有拉伸影响效果</li>
		<br/>
		<form id="pictureFormOne"  enctype="multipart/form-data">
			<input id="pictureFileOne" type="file" name="file" >
			<button class="i-btn-lg" style="float: right" id="uploadButtonOne">提交</button>
		</form>
	</EF:EFRegion>
	
	<EF:EFRegion id="pictureUploadTwo" title="登录页前景图片上传">
		<li>上传图片只支持.png格式</li>
		<li>登录页前景图建议上传长宽为380*350的图片,否则界面有拉伸影响效果</li>
		<br/>
		<form id="pictureFormTwo"  enctype="multipart/form-data">
			<input id="pictureFileTwo" type="file" name="file" >
			<button class="i-btn-lg" style="float: right" id="uploadButtonTwo">提交</button>
		</form>
	</EF:EFRegion>
	
	<EF:EFRegion id="pictureUploadThree" title="登录页LOGO图片上传">
		<li>上传图片只支持.png格式</li>
		<li>登录页LOGO图建议上传长宽为150*40的图片,否则界面有拉伸影响效果</li>
		<br/>
		<form id="pictureFormThree"  enctype="multipart/form-data">
			<input id="pictureFileThree" type="file" name="file" >
			<button class="i-btn-lg" style="float: right" id="uploadButtonThree">提交</button>
		</form>
	</EF:EFRegion>

	<EF:EFRegion id="pictureUploadFour" title="APP首页LOGO图片上传">
		<li>上传图片只支持.png格式</li>
<%--		<li>登录页LOGO图建议上传长宽为150*40的图片,否则界面有拉伸影响效果</li>--%>
		<br/>
		<form id="pictureFormFour"  enctype="multipart/form-data">
			<input id="pictureFileFour" type="file" name="file" >
			<button class="i-btn-lg" style="float: right" id="uploadButtonFour">提交</button>
		</form>
	</EF:EFRegion>
	
	<EF:EFRegion id="modifyThemeColor" title="修改主题颜色">
		
		<br/>
		<EF:EFSelect ename="theme" cname="请选择主题颜色" defaultValue="">
			<EF:EFCodeOption codeName="wilp.css.theme" textField="label" valueField="value"/>
		</EF:EFSelect>
		<button class="i-btn-lg" style="float: right" id="modifyThemeButton">提交</button>
	</EF:EFRegion>
		
</div>
</EF:EFPage>

<script type="text/javascript">



	var uploading = false;
	$("#uploadButtonOne").on("click", function(){
		
		var thisFile = $('#pictureFileOne')[0].files[0];
		
		var fileType = thisFile.type.substring(thisFile.type.length-3,thisFile.type.length);
		if(fileType != "png"){
			IPLAT.alert("上传图片不支持该格式");
			return;
		}
		
		var fd =  new FormData();
		fd.append("fileUpload",$('#pictureFileOne')[0].files[0]);
		fd.append("type","1");
		
		if ($('#pictureFileOne')[0].files[0] == null){
			IPLAT.alert("请选择上传图片");
			return;
		}else {
			$("#uploadButtonOne").attr("disabled","disabled");
		}
		
		if(uploading){
			alert("图片正在上传中，请稍候");
			return;
		}
		
		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/pictureUpload",
			type: 'POST',
			cache: false,
			processData: false,
			contentType: false,
			dataType : 'json',
			data:fd,
			beforeSend: function(){
				uploading = true;
			},
			success : function(data) {
				uploading = false;
				
				if(data.msg == "success"){
					
					var obj = document.getElementById('pictureFileOne');
					obj.outerHTML=obj.outerHTML;
					
					$('#uploadButtonOne').attr("disabled",false);
					//$('#uploadButtonOne').removeAttr("disabled");
					//$('#uploadButtonOne').attr("disabled","");
					IPLAT.alert("图片上传成功");
					
				}else {
					NotificationUtil("图片上传失败","error");
				}
			
			}
		});
	});
	
	$("#uploadButtonTwo").on("click", function(){
		
		var thisFile = $('#pictureFileTwo')[0].files[0];
		
		var fileType = thisFile.type.substring(thisFile.type.length-3,thisFile.type.length);
		if(fileType != "png"){
			IPLAT.alert("上传图片不支持该格式");
			return;
		}
		
		var fd =  new FormData();
		fd.append("fileUpload",$('#pictureFileTwo')[0].files[0]);
		fd.append("type","2");
		
		if ($('#pictureFileTwo')[0].files[0] == null){
			IPLAT.alert("请选择上传图片");
			return;
		}else {
			$("#uploadButtonTwo").attr("disabled","disabled");
		}
		
		if(uploading){
			alert("图片正在上传中，请稍候");
			return;
		}
		
		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/pictureUpload",
			type: 'POST',
			cache: false,
			processData: false,
			contentType: false,
			dataType : 'json',
			data:fd,
			beforeSend: function(){
				uploading = true;
			},
			success : function(data) {
				uploading = false;
				
				if(data.msg == "success"){
					
					var obj = document.getElementById('pictureFileTwo');
					obj.outerHTML=obj.outerHTML;
					
					$('#uploadButtonTwo').attr("disabled",false);
					//$('#uploadButtonOne').removeAttr("disabled");
					//$('#uploadButtonOne').attr("disabled","");
					IPLAT.alert("图片上传成功");
					
				}else {
					NotificationUtil("图片上传失败","error");
				}
			
			}
		});
	});
	
	$("#uploadButtonThree").on("click", function(){
		
		var thisFile = $('#pictureFileThree')[0].files[0];
		
		var fileType = thisFile.type.substring(thisFile.type.length-3,thisFile.type.length);
		if(fileType != "png"){
			IPLAT.alert("上传图片不支持该格式");
			return;
		}
		
		var fd =  new FormData();
		fd.append("fileUpload",$('#pictureFileThree')[0].files[0]);
		fd.append("type","3");
		
		if ($('#pictureFileThree')[0].files[0] == null){
			IPLAT.alert("请选择上传图片");
			return;
		}else {
			$("#uploadButtonThree").attr("disabled","disabled");
		}
		
		if(uploading){
			alert("图片正在上传中，请稍候");
			return;
		}
		
		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/pictureUpload",
			type: 'POST',
			cache: false,
			processData: false,
			contentType: false,
			dataType : 'json',
			data:fd,
			beforeSend: function(){
				uploading = true;
			},
			success : function(data) {
				uploading = false;
				
				if(data.msg == "success"){
					
					var obj = document.getElementById('pictureFileThree');
					obj.outerHTML=obj.outerHTML;
					
					$('#uploadButtonThree').attr("disabled",false);
					//$('#uploadButtonOne').removeAttr("disabled");
					//$('#uploadButtonOne').attr("disabled","");
					IPLAT.alert("图片上传成功");
					
				}else {
					NotificationUtil("图片上传失败","error");
				}
			
			}
		});
	});

	$("#uploadButtonFour").on("click", function(){

		var thisFile = $('#pictureFileFour')[0].files[0];

		var fileType = thisFile.type.substring(thisFile.type.length-3,thisFile.type.length);
		if(fileType != "png"){
			IPLAT.alert("上传图片不支持该格式");
			return;
		}

		var fd =  new FormData();
		fd.append("fileUpload", $('#pictureFileFour')[0].files[0]);
		fd.append("type","4");

		if ($('#pictureFileFour')[0].files[0] == null){
			IPLAT.alert("请选择上传图片");
			return;
		}else {
			$("#uploadButtonFour").attr("disabled","disabled");
		}

		if(uploading){
			alert("图片正在上传中，请稍候");
			return;
		}

		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/pictureUpload",
			type: 'POST',
			cache: false,
			processData: false,
			contentType: false,
			dataType : 'json',
			data:fd,
			beforeSend: function(){
				uploading = true;
			},
			success : function(data) {
				uploading = false;

				if(data.msg == "success"){

					var obj = document.getElementById('pictureFileFour');
					obj.outerHTML=obj.outerHTML;

					$('#uploadButtonFour').attr("disabled",false);
					IPLAT.alert("图片上传成功");

				}else {
					NotificationUtil("图片上传失败","error");
				}

			}
		});
	});
	
	$("#modifyThemeButton").on("click", function (e) {
		
		var eiInfo = new EiInfo();
		var theme =  IPLAT.EFSelect.value($("#theme"));
		eiInfo.set("theme",theme);
		
		EiCommunicator.send("TCCM01", "modifyTheme", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				//IPLAT.alert("主题颜色修改成功,清刷新界面!");
			}
		});
	});


</script>

