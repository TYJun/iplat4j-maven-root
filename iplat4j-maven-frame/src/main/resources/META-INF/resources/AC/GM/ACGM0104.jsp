<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="result" title="修改物资" fitHeight="true" >

		<div class="row">
			<EF:EFInput ename="id" cname="物资ID" colWidth="4" type="hidden"/>

			<EF:EFInput ename="matCode" cname="物资编码" colWidth="4" maxLength="255" required="true"/>
			<EF:EFInput ename="matName" cname="物资名称" colWidth="4" required="true"/>

			<EF:EFTreeInput ename="matClassId" cname="物资分类" bindId="tree01" readonly="true"
							serviceName="ACGM01" methodName="queryTree"
							textField="matClassName" valueField="label" hasChildren="leaf"
							root="{label: 'root',matClassName: '根节点'}" popupTitile="上级物资分类" clear="true"
							colWidth="4" required="true">
			</EF:EFTreeInput>
			<EF:EFInput ename="matSpec" cname="物资规格" colWidth="4" />
		</div>
		<div class="row" >
			<EF:EFInput ename="matModel" cname="物资型号" colWidth="4" />
<%--			<EF:EFSelect ename="unit"  cname="计量单位" >--%>
<%--				<EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>--%>
<%--			</EF:EFSelect>--%>
			<EF:EFSelect ename="unit" cname="计量单位" resultId="unit"
						 serviceName="ACGM01" methodName="queryUnit" textField="itemCname" valueField="itemCode" filter="contains">
				<EF:EFOption label="全部" value="" />
			</EF:EFSelect>
			<EF:EFInput ename="price" cname="订购单价(元)" colWidth="4" />


		</div>
		<div class="row" >
<%--			<EF:EFPopupInput ename="supplier" cname="供应商"--%>
<%--							 popupTitle="供应商选择" readOnly="true"--%>
<%--							 popupType="ServiceGrid" serviceName="ACSU01" methodName="querySupplier" resultId="result"--%>
<%--							 valueField="supplierCode" textField="supplierName"--%>
<%--							 columnEnames="supplierCode,supplierName" columnCnames="供应商编码,供应商名称" />--%>
			<EF:EFInput ename="manufacturer" cname="制造商" colWidth="4" />
			<EF:EFSelect ename="matTypeCode"  cname="物资大类" >
				<EF:EFCodeOption codeName="wilp.ac.gm.type" textField="label" valueField="value"/>
			</EF:EFSelect>
			<EF:EFInput ename="packfactor" cname="包装系数" colWidth="4" maxLength="100" />
		</div>

		<div class="row" >
			<EF:EFInput ename="remark" cname="备注" colWidth="4" type="textarea" />
			<EF:EFInput ename="pictureUri" cname="图片地址" colWidth="4"  type="hidden"/>
<%--			<EF:EFInput ename="matCode" cname="物资编码" colWidth="4" required="true" type="hidden"/>--%>
			<EF:EFInput ename="preMatCode" cname="原来的物资编码" colWidth="4" required="true" type="hidden"/>
		</div>

		<div style=" margin-top: 15px;">
			<li style="margin-left:40%;list-style-type:none"><span style="color: red;">* </span>上传图片只支持<span id="format"></span>格式，图片大小最大为<span id="maxSize"></span>kb
			</li>
			<br/>
			<form id="pictureForm" enctype="multipart/form-data" method="post">
				<input style="margin-left:40%;" id="pictureFile" type="file" name="file">
				<button type="button" class="i-btn-lg" style="float: right;" id="uploadButton">
					上传
				</button>
			</form>
			<img id="imageShow" width="190px" height="100px" style="position: relative;left: 40%;margin-bottom: 20px;margin-top: 20px;">
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {
		$("#maxSize").text(__ei.maxSize);
		$("#format").text(__ei.format);
		//图片回显
		$("#imageShow").attr("src", IPLATUI.CONTEXT_PATH + "/" + $("#pictureUri").val()+"?nowTime="+new Date().getTime());
		// 物资分类回显
		$("#matClassId").val(__ei.matClassId);
		$("#matClassId_textField").val(__ei.matClassName);

		// 供应商回显
		// $("#supplier_valueField").val(__ei.supplier);
		// $("#supplier_textField").val(__ei.supplierText);

		// 计量单位回显
		$("#unit").val(__ei.unit);

		//保存原来的物资编码
		$("#preMatCode").val(__ei.matCode);


		$("#SUBMIT").on("click", function (e) {
			var eiInfo = new EiInfo();

			var id = IPLAT.EFInput.value($("#id"));
			var matCode = IPLAT.EFInput.value($("#matCode"));
			var preMatCode = IPLAT.EFInput.value($("#preMatCode"));
			var matName = IPLAT.EFInput.value($("#matName"));
			var matClassId = $("#matClassId").val();
			var matSpec = IPLAT.EFInput.value($("#matSpec"));
			var matModel = IPLAT.EFInput.value($("#matModel"));
			var unit =  IPLAT.EFSelect.value($("#unit"));
			var price = IPLAT.EFInput.value($("#price"));
			// var supplier = IPLAT.EFInput.value($("#supplier"));
			var manufacturer = IPLAT.EFInput.value($("#manufacturer"));
			var matTypeCode = IPLAT.EFSelect.value($("#matTypeCode"));
			var remark = IPLAT.EFInput.value($("#remark"));
			var pictureUri = IPLAT.EFInput.value($("#pictureUri"));
			var packfactor = IPLAT.EFInput.value($("#packfactor"));
			// 如果价格是 空 则 设置为默认值 0
			if (!price){
				price = 0.00;
			}

			//参数校验
			if(!validate(matCode,matName,matClassId,price)){
				return;
			}
			debugger
			// var picCode=pictureUri.substring(pictureUri.indexOf("/")+1,pictureUri.lastIndexOf("."))
			// if(matCode!=""&&picCode!=''&&picCode!=matCode){
			// 	IPLAT.alert("必须先输入自定义的物资编码，再上传图片");
			// 	return;
			// }
			eiInfo.set("id",id);
			eiInfo.set("matCode",matCode);
			eiInfo.set("preMatCode",preMatCode);
			eiInfo.set("matName",matName);
			eiInfo.set("matClassId",matClassId);
			eiInfo.set("matSpec",matSpec);
			eiInfo.set("matModel",matModel);
			eiInfo.set("unit",unit);
			eiInfo.set("price",price);
			// eiInfo.set("supplier",supplier);
			eiInfo.set("manufacturer",manufacturer);
			eiInfo.set("matTypeCode",matTypeCode);
			eiInfo.set("remark",remark);
			eiInfo.set("pictureUri",pictureUri);
			eiInfo.set("packfactor",packfactor);

			EiCommunicator.send("ACGM0104", "update", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus()=='-2'){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					window.parent['editMatPopDataWindow'].close();
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);

				}
			});
		});
	});

	//参数校验
	function validate(matCode,matName,matClassId,price){
		if(isEmpty(matCode)){
			NotificationUtil("物资编码不能为空", "error");
			return false;
		}
		if(isEmpty(matName)){
			NotificationUtil("物资名称不能为空", "error");
			return false;
		}
		if(isEmpty(matClassId)){
			NotificationUtil("物资分类能为空", "error");
			return false;
		}
		if (!isPriceNumber(price)){
			NotificationUtil("订购单价格式不正确", "error");
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

	function isPriceNumber(_keyword){
		if(_keyword == "0" || _keyword == "0." || _keyword == "0.0" || _keyword == "0.00"){
			_keyword = "0"; return true;
		}else{
			var index = _keyword.indexOf("0");
			var length = _keyword.length;
			if(index == 0 && length>1){/*0开头的数字串*/
				var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
				if(!reg.test(_keyword)){
					return false;
				}else{
					return true;
				}
			}else{/*非0开头的数字*/
				var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/;
				if(!reg.test(_keyword)){
					return false;
				}else{
					return true;
				}
			}
			return false;
		}
	}

	var uploading = false;
	$("#uploadButton").on("click", function () {

		var thisFile = $('#pictureFile')[0].files[0];
		var matCode = IPLAT.EFInput.value($("#matCode"));
		var preMatCode=IPLAT.EFInput.value($("#preMatCode"));
		if(matCode.replace(/\s*/g,"")==""){
			IPLAT.alert("物资编码不能为空");
			return;
		}
		// var fileType = thisFile.name.substring(thisFile.name.length - 3, thisFile.type.length);
		var fileType = thisFile.name.substring(thisFile.name.lastIndexOf("."));

		var formats=__ei.format.split(",");
		var flag=true;
		for(var i=0;i<formats.length;i++){
			if (fileType=="."+formats[i]){
				flag=false;
				break;
			}
		}

		if (flag) {

			IPLAT.alert("图片格式不正确,只支持"+__ei.format+"格式");
			return;
		}

		if(thisFile.size>1024*__ei.maxSize){
			IPLAT.alert("图片大小超过最大值"+__ei.maxSize+"kb");
			return;
		}

		// var fd = new FormData($('#pictureForm')[0]);
		var fd = new FormData();
		fd.append("file", $('#pictureFile')[0].files[0]);
		fd.append("matCode",matCode);
		if(preMatCode==matCode){
			//不用校验重复
			fd.append("type","1");
		}else {
			//需要校验重复
			fd.append("type","0");
		}
		// fd.append("type","1");
		// console.log(fd);

		if ($('#pictureFile')[0].files[0] == null) {
			IPLAT.alert("请选择上传图片");
			return;
		} else {
			$("#uploadButton").attr("disabled", "disabled");
		}

		if (uploading) {
			alert("图片正在上传中，请稍候");
			return;
		}

		$.ajax({
			// url: IPLATUI.CONTEXT_PATH + "/app/materialsPicUpload",
			url: IPLATUI.CONTEXT_PATH + "/frame/servlet/uploadImage",
			data: fd,
			type: "post",
			cache: false,
			processData: false,
			contentType: false,
			beforeSend: function () {
				uploading = true;
			},
			success: function (data) {
				uploading = false;
				if (data != undefined && data != null) {
					if (data.status ==-2) {
						IPLAT.alert(data.msg);
						$("#uploadButton").removeAttr("disabled");
						return;
					}

					var obj = document.getElementById('pictureFile');
					obj.outerHTML = obj.outerHTML;

					$('#uploadButton').attr("disabled", false);
					//$('#uploadButtonOne').removeAttr("disabled");
					//$('#uploadButtonOne').attr("disabled","");
					$('#pictureUri').val(data.msg);

					// var accessPath = IPLATUI.CONTEXT_PATH + "/" + data.msg+"?nowTime="+new Date().getTime();
					var accessPath =IPLATUI.CONTEXT_PATH +"/" + data;
					$("#imageShow").attr("src", accessPath);
					setTimeout(function(){$("#imageShow")[0].src="";$("#imageShow")[0].src=accessPath;},5000);
					setTimeout(function(){IPLAT.alert("图片上传成功");},2000);
					IPLAT.alert("图片上传成功");

				} else {
					NotificationUtil("图片上传失败", "error");
				}

			},
			error: function (data) {
				alert("上传出错")
			}
		});
	});
</script>
