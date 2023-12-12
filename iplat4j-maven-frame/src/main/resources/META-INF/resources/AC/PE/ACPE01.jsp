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
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="科室名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="deptName" valueField="label"
				hasChildren="leaf" serviceName="ACDE01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
			
			    <EF:EFInput ename="workNo" cname="工号" />
				<EF:EFInput ename="name" cname="姓名" />
				<EF:EFInput ename="deptName" cname="所属科室" />
				<EF:EFSelect ename="status" cname="状态" defaultValue="启用">
					<EF:EFOption label="启用" value="启用"></EF:EFOption>
					<EF:EFOption label="停用" value="停用"></EF:EFOption>
				</EF:EFSelect>
				<EF:EFInput ename="parentId" cname="" type="hidden" />
				<EF:EFInput ename="parentName" cname="" type="hidden" />
				<EF:EFInput ename="post" cname="职务" />
				<EF:EFInput ename="ywDeptname" cname="关联业务科室" />
				<EF:EFInput ename="xzDeptname" cname="关联行政科室" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="人员列表">
			<EF:EFGrid blockId="result" autoDraw="no" >
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="workNo" cname="工号" width="60" />
				<EF:EFColumn ename="name" cname="姓名" width="100" />
				<EF:EFColumn ename="gender" cname="性别" width="40" />
				<EF:EFColumn ename="deptName" cname="所属科室" width="100" />
				<EF:EFColumn ename="contactTel" cname="联系电话" width="100" />
				<EF:EFComboColumn ename="staffType" cname="员工类型">
					<EF:EFCodeOption codeName="wilp.ac.pe.type" textField="label" valueField="value"/>
				</EF:EFComboColumn>

				<EF:EFColumn ename="isService" cname="服务人员" width="80" />
				<EF:EFColumn ename="sourceName" cname="出处" width="60" />
				<EF:EFColumn ename="status" cname="状态" width="60" />
				<EF:EFColumn ename="post" cname="职务" width="80" />
				<EF:EFColumn ename="ywDeptname" cname="关联业务科室" width="100" />
				<EF:EFColumn ename="xzDeptname" cname="关联行政科室" width="100" />
				<EF:EFColumn ename="userId" cname="微信登录号" width="80" />
				<%-- <EF:EFColumn ename="dataGroupName" cname="所属院区" width="100" /> --%>
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
		<li>人员导入规则：</li>
		<li>工号（必填；字符长度小于等于50；不可重复）</li>
		<li>姓名（必填；字符长度小于等于30）</li>
		<li>性别（必填；1：男 / 0：女；字符长度小于等于1）</li>
		<li>身份证号（非必填；字符长度小于等于32）</li>
		<li>联系电话（非必填；字符长度小于等于20）</li>
		<li>所属科室（必填；填写科室的编码；字符长度小于等于50）</li>
		<li>员工类型（必填；参考员工类型<a href="EDCM01">小代码</a>定义）</li>
		<li>服务人员(必填；1：是 / 0:否)</li>
		<form id="excelForm"  enctype="multipart/form-data">
			数据上传：<input id="excelFile" type="file" name="file" ><br />
		</form>
		<button id="download">模板下载</button>
	</EF:EFRegion>
	<button class="i-btn-lg" style="float: right" id="uploadButton">提交</button>
</EF:EFWindow>
<script type="text/javascript">

	$("#inqu_status-0-parentId").css("display", "none");

	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				deptName: "${hospitalName == '???hospitalName???'? '根节点':hospitalName}",
				leaf: true
			},

			select: function(e) {
				var _data = this.dataItem(e.node);
				var length = _data.children._data.length;
				var labelValue = _data.label;
				var typeValue = _data.type;
				var deptName = _data.deptName;
				$("#parentId").val(labelValue);
				$("#parentName").val(deptName);
				resultGrid.dataSource.page(1);
				if ($("#parentId").val() == "root"){
					// 如果点击根节点则视为全局搜索，去除 parentId
					$("#parentId").attr("value", "");
				}
			},

		}

	};

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
			url: IPLATUI.CONTEXT_PATH+"/excel",
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
					NotificationUtil("导入数据全部成功","success");
					window.resultGrid.dataSource.page(1);
					window['excelChooseWindow'].close();
				}else if (data.msg == "part"){
					NotificationUtil("导入数据存在错误","warning");
					console.log(data);
					window.resultGrid.dataSource.page(1);
					downloadFileByBase64('data:application/xls;base64,'+data.base64);
				}else if (data.msg == "error"){
					NotificationUtil("导入过程错误","error");
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
		$("#ADDPERL").on("click", function (e) {

			// 获取左侧选中科室信息 root 需要另外处理
			var pId = $("#parentId").val();
			var pName = $("#parentName").val();
			if(pId =="" && pName!=""){
				pId = "root";
			}


			var url = IPLATUI.CONTEXT_PATH + "/web/ACPE0101?"+"parentId="+pId+"&parentName="+pName;
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
		$("#EDITPERL").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行修改");
			}else {
				var url = IPLATUI.CONTEXT_PATH + "/web/ACPE0102?"+"initLoad&id=" + datagrid.id;
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
		
		
		//删除按钮
		$("#DELETEPERL").on("click", function(e) {
		
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
					EiCommunicator.send("ACPE01", "delete", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
			
			
		});
		
		
		//启用按钮
		$("#STARTUSING").on("click", function(e) {
			
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要启用的行");
				return;
			}
			// 检查勾选的是否包含已经启用的
			for (var i = 0; i < checkRows.length; i++) {
				if (checkRows[i].status === "启用"){
					IPLAT.alert("选中行中包含已启用人员！");
					return;
				}
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].workNo;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("status","1");
			EiCommunicator.send("ACPE01", "updateStatus", info, {
				onSuccess : function(ei) {
					// NotificationUtil(ei.getMsg(), "success");
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});
		});
		
		//停用按钮
		$("#STOPUSING").on("click", function(e) {
			
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要停用的行");
				return;
			}
			// 检查勾选的是否包含已经停用的
			for (var i = 0; i < checkRows.length; i++) {
				if (checkRows[i].status === "停用"){
					IPLAT.alert("选中行中包含已停用人员！");
					return;
				}
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].workNo;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("status","0");
			EiCommunicator.send("ACPE01", "updateStatus", info, {
				onSuccess : function(ei) {
					// NotificationUtil(ei.getMsg(), "success");
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});
		});

		// 同步钉钉人员
		$("#SYNCHRONIZATION").on("click",function (e) {
			var info=new EiInfo();
			IPLAT.confirm({
				message:"确定同步钉钉人员?",
				okFn:function(e){
					EiCommunicator.send("ACPE01", "synchronizationDingUser", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
	
		});
		
		// 同步企业微信人员
		$("#SYNCHRONIZATIONWX").on("click",function (e) {
			var info=new EiInfo();
			IPLAT.confirm({
				message:"确定同步企业微信人员?",
				okFn:function(e){
					EiCommunicator.send("ACPE01", "synchronizationWXUser", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
	
		});
	
		//一键生成用户
		$("#GENERATEUSERS").on("click", function(e) {
			
			var checkRows = resultGrid.getCheckedRows(), eiInfo = new EiInfo();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要执行的行");
				return;
			}
			
			var info=new EiInfo();
			info.set("list",checkRows);
			
			EiCommunicator.send("ACPE01", "generateUsers", info, {
				onSuccess : function(ei) {
					IPLAT.alert(ei.getMsg()+"请及时注册权限!");
					//resultGrid.dataSource.page(1);
				}
			});
		});
		//EXCEL 模板下载
		$("#download").click(function(){
			window.location.href =  IPLATUI.CONTEXT_PATH+"/excel?module=pers";
		});

		//EXCEL 上载
		$("#UPLOAD").click(function(){
			excelChooseWindow.open().center()
		});

		//QR 下载
		$("#QRDOWNLOAD").click(function(){
			var checkRows = resultGrid.getCheckedRows();
			if (checkRows.length === 0){
				IPLAT.confirm({
					message:"确定下载全部人员二维码?",
					okFn:function(e){
						window.location.href =  IPLATUI.CONTEXT_PATH+"/qrcode?module=perl";
					}
				});
			}else{
				var arr=[];
				for(var i=0;i<checkRows.length;i++){
					arr[i]=checkRows[i].workNo;
				}
				IPLAT.confirm({
					message:"确定下载选中人员二维码?",
					okFn:function(e){
						window.location.href =  IPLATUI.CONTEXT_PATH+"/qrcode?module=perl&ids="+arr;
					}
				});
			}

		});


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

