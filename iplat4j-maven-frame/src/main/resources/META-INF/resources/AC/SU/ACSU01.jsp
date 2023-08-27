<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>


<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	<div class="col-md-12">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
			    <EF:EFInput ename="supplierName" cname="供应商名称" />
				<EF:EFInput ename="contactAddress" cname="联系地址" />
				<EF:EFInput ename="contactNumber" cname="联系电话" />
				<EF:EFSelect ename="status" cname="状态" >
					<EF:EFOption label="启用" value="1"></EF:EFOption>
					<EF:EFOption label="停用" value="0"></EF:EFOption>
				</EF:EFSelect>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="供应商列表">
			<EF:EFGrid blockId="result" autoDraw="no" >
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="supplierCode" cname="供应商编码" width="100" />
				<EF:EFColumn ename="supplierName" cname="供应商名称" width="100" />
				<EF:EFColumn ename="contacts" cname="联系人" width="100" />
				<EF:EFComboColumn ename="supplierType" cname="供应商类型">
					<EF:EFCodeOption codeName="wilp.ac.su.type" textField="label" valueField="value"/>
				</EF:EFComboColumn>
				<EF:EFColumn ename="contactNumber" cname="联系电话" width="100" />
				<EF:EFColumn ename="contactAddress" cname="联系地址" width="100" />
				<EF:EFColumn ename="contactEmail" cname="联系邮箱" width="100" />
				<EF:EFColumn ename="zipCode" cname="邮编" width="100" />
				<EF:EFColumn ename="legalRepresentative" cname="法人代表" width="100" />
				<EF:EFColumn ename="icrNo" cname="工商注册号" width="100" />
				<EF:EFColumn ename="businessScope" cname="经营范围" width="100" />
				<EF:EFColumn ename="abbreviation" cname="供应商简称" width="100" />
				<EF:EFColumn ename="hrpCode" cname="HRP编码" width="100" />
				<EF:EFColumn ename="status" cname="状态" width="100" hidden="true" />
				<EF:EFColumn ename="statusText" cname="状态" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="80%" height="60%"></EF:EFWindow>
<EF:EFWindow id="deitPopData" url=" " lazyload="true" width="80%" height="60%"></EF:EFWindow>
<!-- 文件上传窗口 -->
<EF:EFWindow id="excelChoose" url="" lazyload="true" refresh="true" width="40%" height="50%">
	<EF:EFRegion id="ReUpload" title="导入数据">
		<%-- <EF:EFUpload ename="mtrePic" docTag="mt_fs_file" path="mt/img"/> --%>
		<li>供应商名称 必填，唯一， 长度小于等于100</li>
		<li>供应商类型 必填，此项在<a href="EDCM01">小代码</a>中维护中的一个</li>
		<li>联系人 非必填，长度小于等于50</li>
		<li>联系电话 非必填，长度小于等于20</li>
		<li>联系地址 非必填，长度小于等于200</li>
		<li>联系邮箱 非必填，长度小于等于36</li>
		<li>邮编 非必填，长度小于等于255</li>
		<li>法人代表  非必填，长度小于等于36</li>
		<li>工商注册号 非必填，长度小于等于100</li>
		<li>经营范围 非必填，长度小于等于500</li>
		<li>供应商简称 非必填，长度小于等于36</li>
		<li>hrp编码 非必填，长度小于等于50</li>
		<form id="excelForm"  enctype="multipart/form-data">
			数据上传：<input id="excelFile" type="file" name="file" ><br />
		</form>
		<button id="download">模板下载</button>
	</EF:EFRegion>
		<button class="i-btn-lg" style="float: right" id="uploadButton">提交</button>
</EF:EFWindow>
<script type="text/javascript">
	var datagrid = null;

	var uploading = false;
	$("#uploadButton").on("click", function(){
		$("#uploadButton").attr("disabled","disabled");
		var fd =  new FormData();
		fd.append("fileUpload",$('#excelFile')[0].files[0]);
		if ($('#excelFile')[0].files[0] == null){
			IPLAT.alert("请选择上传文件");
		}
		if(uploading){
			alert("文件正在上传中，请稍候");
			return false;
		}
		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/excelACSU",
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
				if(data.msg == "all"){
					IPLAT.alert("导入数据全部成功");
					window.resultGrid.dataSource.page(1);
					window['excelChooseWindow'].close();
				}else if (data.msg == "part"){
					IPLAT.alert("导入数据存在错误");
					window['excelChooseWindow'].close();
					window.resultGrid.dataSource.page(1);
					downloadFileByBase64('data:application/xls;base64,'+data.base64);
				}else if (data.msg == "error"){
					IPLAT.alert("导入过程错误");
					// console.log(data);
					window['excelChooseWindow'].close();
				}
			}
		});
	});
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
	}

	$(function() {

		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.query(1);
		});
		
		//新增按钮
		$("#ADD").on("click", function (e) {

			var url = IPLATUI.CONTEXT_PATH + "/web/ACSU0101";
        	var addPopData = $("#addPopData");
        	addPopData.data("kendoWindow").setOptions({
        		open : function() {
        			addPopData.data("kendoWindow").refresh({
        				url : url
        			});
        		}
        	});
        	// 打开弹窗
        	addPopDataWindow.open().center();
        });

		
		//修改按钮
		$("#EDIT").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行修改");
			}else {
				var url = IPLATUI.CONTEXT_PATH + "/web/ACSU0102?"+"initLoad&id=" + datagrid.id;
				var deitPopData = $("#deitPopData");
				deitPopData.data("kendoWindow").setOptions({
					open : function() {
						deitPopData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				deitPopDataWindow.open().center();
			}
		});
		
		// 启用
		$("#STARTUSING").on("click",function (e) {
			var checkRows = resultGrid.getCheckedRows();
			if(checkRows.length<1){
				IPLAT.alert("请选择要启用的供应商！");
				return;
			}
			// 检查勾选的是否包含已经启用的
			for (var i = 0; i < checkRows.length; i++) {
				if (checkRows[i].status === "1"){
					IPLAT.alert("选中行中包含已启用的供应商！");
					return;
				}
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);

			IPLAT.confirm({
				message:"确定启用选中供应商?",
				okFn:function(e){
					EiCommunicator.send("ACSU01", "startUsing", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});

		})
		// 停用
		$("#STOPUSING").on("click",function (e) {
			var checkRows = resultGrid.getCheckedRows();
			if(checkRows.length<1){
				IPLAT.alert("请选择要停用的行");
				return;
			}
			// 检查勾选的是否包含已经停用的
			for (var i = 0; i < checkRows.length; i++) {
				if (checkRows[i].status === "0"){
					IPLAT.alert("选中行中包含已停用的供应商！");
					return;
				}
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);

			IPLAT.confirm({
				message:"确定停用选中行?",
				okFn:function(e){
					EiCommunicator.send("ACSU01", "stopUsing", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});

		})
		//EXCEL 模板下载
		$("#download").click(function(){
			window.location.href =  IPLATUI.CONTEXT_PATH+"/excelACSU";
		});

		//EXCEL 上载
		$("#UPLOAD").click(function(){
			excelChooseWindow.open().center()
		});
		//删除按钮（废弃）
		/*$("#DELETEDEPT").on("click", function(e) {
		
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要删除的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			
			IPLAT.confirm({
				message:"确定删除选中行?",
				okFn:function(e){
					EiCommunicator.send("ACDE01", "delete", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
			
		});*/


	});

	// base64 文件下载
	function dataURLtoBlob(dataurl) {
		var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
				bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
		while (n--) {
			u8arr[n] = bstr.charCodeAt(n);
		}
		return new Blob([u8arr], { type: mime });
	}

	function downloadFile(url,name='error.xls'){
		var a = document.createElement("a")
		a.setAttribute("href",url)
		a.setAttribute("download",name)
		a.setAttribute("target","_blank")
		let clickEvent = document.createEvent("MouseEvents");
		clickEvent.initEvent("click", true, true);
		a.dispatchEvent(clickEvent);
	}

	function downloadFileByBase64(base64,name){
		var myBlob = dataURLtoBlob(base64)
		var myUrl = URL.createObjectURL(myBlob)
		downloadFile(myUrl,name)
	}


</script>

