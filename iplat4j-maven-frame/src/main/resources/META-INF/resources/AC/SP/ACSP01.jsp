<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 加载application配置文件 读取医院名称 -->
<fmt:setBundle basename="application" var="app" />
<fmt:message key="hospitalName" var="hospitalName" bundle="${app}" />
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="地点档案信息管理">
	<div class="row">
		<div class="col-md-3">
			<EF:EFRegion id="R1" title="科室名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="deptName" valueField="label"
				hasChildren="leaf" serviceName="ACDE01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
		</div>
		<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询区">
					<EF:EFInput colWidth="3" ename="curDeptId" cname="curDeptId" type="hidden"/>
					<EF:EFInput colWidth="3" ename="deptId" cname="科室ID" type="hidden" />
					
					<EF:EFInput colWidth="3" ename="deptName" cname="科室名称" />
					
					<EF:EFInput colWidth="3" ename="spotName" cname="地点名称" />
					<EF:EFInput colWidth="3" ename="curSpotId" cname="地点ID" type="hidden"/>
					<EF:EFInput colWidth="3" ename="spotId" cname="地点ID" type="hidden"/>
					<EF:EFInput colWidth="3" ename="building" cname="楼" />
					<EF:EFInput colWidth="3" ename="floor" cname="层" />
					<EF:EFInput colWidth="3" ename="room" cname="房间" />
					<EF:EFInput colWidth="3" ename="telNum" cname="电话" />
					<EF:EFSelect colWidth="3" ename="status" cname="地点状态" defaultValue="1">
						<EF:EFOption label="启用" value="1"></EF:EFOption>
						<EF:EFOption label="停用" value="0"></EF:EFOption>
					</EF:EFSelect>
				<EF:EFSelect colWidth="3" ename="status1" cname="电话状态" defaultValue="1" >
					<EF:EFOption label="启用" value="1"></EF:EFOption>
					<EF:EFOption label="停用" value="0"></EF:EFOption>
				</EF:EFSelect>
			</EF:EFRegion>
			<EF:EFRegion id="resultSpot" title="地点信息" >
				<EF:EFGrid blockId="resultSpot" autoDraw="no" serviceName="ACSP01" queryMethod="querySpot" readonly="true"  height="205px" >
					<EF:EFColumn ename="id" cname="主键"  hidden="true"/>
					<EF:EFColumn ename="deptId" cname="科室编码" hidden="true" />
					<EF:EFColumn ename="deptName" cname="科室名称" />
					<EF:EFColumn ename="spotId" cname="地点ID" hidden="true" />
					<EF:EFColumn ename="spotNum" cname="地点编号"  />
					<EF:EFColumn ename="spotName" cname="地点名称"  />
					<EF:EFColumn ename="building" cname="楼" width="60" />
					<EF:EFColumn ename="floor" cname="层" width="60"/>
					<EF:EFColumn ename="room" cname="房间" width="60" />
				<%-- 	<EF:EFColumn ename="jpSpotName" cname="地点名简称" /> --%>
					<EF:EFColumn ename="remark" cname="备注"  />
					<EF:EFColumn ename="status" cname="状态"  width="60" />
				</EF:EFGrid>

			</EF:EFRegion>
			
			<EF:EFRegion id="resultTele" title="电话信息" >
				<EF:EFGrid blockId="resultTele" autoDraw="no" serviceName="ACSP01"  queryMethod="queryTele" readonly="true" height="205px" >
					<EF:EFColumn ename="telId" cname="主键"  hidden="true"/>
					<EF:EFColumn ename="deptId" cname="科室ID"  hidden="true"/>
					<EF:EFColumn ename="deptName" cname="科室名称"  />
					<EF:EFColumn ename="spotId" cname="地点ID"  hidden="true"/>
					<EF:EFColumn ename="spotName" cname="地点名称"  />
					<EF:EFColumn ename="telNum" cname="电话号码" />
					<EF:EFColumn ename="remark" cname="备注" />
					<EF:EFColumn ename="status" cname="状态"  />
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="40%"  modal="true" />
			<!-- 文件上传窗口 -->
			<EF:EFWindow id="excelChoose" url="" lazyload="true" refresh="true" width="40%" height="50%">
				<EF:EFRegion id="ReUpload" title="导入数据">
					<%-- <EF:EFUpload ename="mtrePic" docTag="mt_fs_file" path="mt/img"/> --%>
					<li>地点导入规则：</li>
					<li>科室编码（必填；填写科室的编码；字符长度小于等于50）</li>
					<li>地点编码（必填；字符长度小于等于50；不可重复;系统自动生成规则是：SP00001，尽量保持类似）</li>
					<li>地点名称（必填；字符长度小于等于200）</li>
					<li>地点简拼（非必填；字符长度小于等于50）</li>
					<li>楼（必填；字符长度小于等于50）</li>
					<li>层（必填；字符长度小于等于50）</li>
					<li>房间（必填；字符长度小于等于50）</li>
					<li>备注（非必填；字符长度小于等于500）</li>
					<form id="excelForm"  enctype="multipart/form-data">
						数据上传：<input id="excelFile" type="file" name="file" ><br />
					</form>
					<button id="downloadspot">模板下载</button>
				</EF:EFRegion>
				<button class="i-btn-lg" style="float: right" id="uploadButton">提交</button>
			</EF:EFWindow>

			<EF:EFWindow id="popData2" url="" lazyload="true" width="80%" height="40%"  modal="true" />
			<!-- 文件上传窗口 -->
			<EF:EFWindow id="excelChoose2" url="" lazyload="true" refresh="true" width="40%" height="50%">
				<EF:EFRegion id="ReUpload2" title="导入数据">
					<%-- <EF:EFUpload ename="mtrePic" docTag="mt_fs_file" path="mt/img"/> --%>
					<li>电话导入规则：</li>
					<li>科室编码（必填；填写科室的编码；字符长度小于等于50）</li>
					<li>地点编码（必填；填写地点的编码；字符长度小于等于50）</li>
					<li>电话号码（必填；不可重复；字符长度小于等于50）</li>
					<li>备注（非必填；字符长度小于等于500）</li>
					<form id="excelForm2"  enctype="multipart/form-data">
						数据上传：<input id="excelFile2" type="file" name="file" ><br />
					</form>
					<button id="downloadtele">模板下载</button>
				</EF:EFRegion>
				<button class="i-btn-lg" style="float: right" id="uploadButton2">提交</button>
			</EF:EFWindow>
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript">
	var curDeptId 	= null; //当前选中部门编号
	var curDeptName = null; //当前选中部门名称

	var curSpotId = null; //当前选中地点编号
	var curSpotName = null; //当前选中地点名称

	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				deptName: "${hospitalName == '???hospitalName???'? '根节点':hospitalName}",
				leaf: true
			},

			select: function(e) {
				var _data = this.dataItem(e.node);
				console.log(_data);
				curDeptId = _data.id;
				curDeptName = _data.deptName;
				$('#curDeptId').val(curDeptId);
				resultSpotGrid.dataSource.page(1);
				if ($("#curDeptId").val() == "root"){
					// 如果点击根节点则视为全局搜索，去除 curDeptId value
					$("#curDeptId").attr("value", "");
				}
			},

		}

	};




	// 查询按钮
	$("#QUERY").on("click", function (e) {
		var telNum = IPLAT.EFInput.value($("#telNum"));
		console.log(telNum);
		if (isEmpty(telNum)){
			resultSpotGrid.dataSource.page(1);
		}else{
			resultTeleGrid.dataSource.page(1);
		}

	});

	// 地点编辑按钮
	$("#EDITSPOT").on("click", function (e) {


		var checkRows = resultSpotGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要编辑的地点","error");
			return;
		}

		$('#deptId').val(curDeptId);
		$('#deptName').val(curDeptName);
		popData("EDITSPOT");

	});

	// 地点添加按钮
	$("#ADDSPOT").on("click", function (e) {

		if(curDeptId == null){
			NotificationUtil("请先勾选左侧科室","error");
			return;
		}
		$('#deptId').val(curDeptId);
		$('#deptName').val(curDeptName);
		popData("ADDSPOT");
	});

	// 地点删除按钮
	/*$("#DELETESPOT").on("click", function (e) {
		var checkRows = resultSpotGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要删除的地点","error");
			return;
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].spotId;
		}
		var info=new EiInfo();
		info.set("list",arr);
		IPLAT.confirm({
			message:"确定删除选中行?",
			okFn:function(e){
				EiCommunicator.send("ACSP01", "deleteSpot", info, {
					onSuccess : function(ei) {
						if(ei.getStatus() == -1){
							NotificationUtil(ei.getMsg(),"error");
						}else{
							NotificationUtil(ei.getMsg(),"success");
							$('#deptId').val(curDeptId);
							$('#deptName').val(curDeptName);
							resultSpotGrid.dataSource.page(1);
							$('#deptId').val("");
							$('#deptName').val("");
						}


					}
				});
			}
		});

    });*/

	// 地点启用
	$("#STARTUSINGSPOT").click(function () {
		var checkRows = resultSpotGrid.getCheckedRows();

		if(checkRows.length<1){
			IPLAT.alert("请选择要启用的行");
			return;
		}
		// 检查勾选的是否包含已经启用的
		for (var i = 0; i < checkRows.length; i++) {
			if (checkRows[i].status === "启用"){
				IPLAT.alert("选中行中包含已启用地点！");
				return;
			}
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].spotId;
		}
		var info=new EiInfo();
		info.set("list",arr);
		info.set("status","1");
		EiCommunicator.send("ACSP01", "updateSpotStatus", info, {
			onSuccess : function(ei) {
				IPLAT.alert(ei.getMsg());
				resultSpotGrid.dataSource.page(1);
			}
		});
	});

	// 地点停用
	$("#STOPUSINGSPOT").on("click", function(e) {

		var checkRows = resultSpotGrid.getCheckedRows();

		if(checkRows.length<1){
			IPLAT.alert("请选择要停用的行");
			return;
		}
		// 检查勾选的是否包含已经停用的
		for (var i = 0; i < checkRows.length; i++) {
			if (checkRows[i].status === "停用"){
				IPLAT.alert("选中行中包含已停用地点！");
				return;
			}
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].spotId;
		}
		var info=new EiInfo();
		info.set("list",arr);
		info.set("status","0");
		EiCommunicator.send("ACSP01", "updateSpotStatus", info, {
			onSuccess : function(ei) {
				// NotificationUtil(ei.getMsg(), "success");
				IPLAT.alert(ei.getMsg());
				resultSpotGrid.dataSource.page(1);
			}
		});
	});




	//EXCEL 模板下载(地点)
	$("#downloadspot").click(function(){
		window.location.href =  IPLATUI.CONTEXT_PATH+"/excel?module=spot";
	});

	//EXCEL 模板下载(电话)
	$("#downloadtele").click(function(){
		window.location.href =  IPLATUI.CONTEXT_PATH+"/excel?module=tele";
	});

	//EXCEL 上载
	$("#UPLOADSPOT").click(function(){
		excelChooseWindow.open().center();
	});

	$("#UPLOADTELE").click(function(){
		excelChoose2Window.open().center();
	});

	//QR 下载
	$("#QRDOWNLOAD").click(function(){
		var checkRows = resultSpotGrid.getCheckedRows();
		if (checkRows.length === 0){
			IPLAT.confirm({
				message:"确定下载全部地点二维码?",
				okFn:function(e){
					window.location.href =  IPLATUI.CONTEXT_PATH+"/qrcode?module=spot";
				}
			});
		}else{
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].spotNum;
			}
			IPLAT.confirm({
				message:"确定下载选中地点二维码?",
				okFn:function(e){
					window.location.href =  IPLATUI.CONTEXT_PATH+"/qrcode?module=spot&ids="+arr;
				}
			});
		}

	});

	// 电话编辑按钮
	$("#EDITTELE").on("click", function (e) {
		var checkRows = resultSpotGrid.getCheckedRows();

		checkRows = resultTeleGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要编辑的电话","error");
			return;
		}
		popData("EDITTELE");
	});

	// 电话添加按钮
	$("#ADDTELE").on("click", function (e) {
		var checkRows = resultSpotGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请先勾选地点","error");
			return;
		}
		popData("ADDTELE");
	});

	// 电话删除按钮
	/*$("#DELETETELE").on("click", function (e) {
		var checkRows = resultTeleGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要删除的电话","error");
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
				EiCommunicator.send("ACSP01", "deleteTele", info, {
					onSuccess : function(ei) {
						NotificationUtil(ei.getMsg(), "success");
						$('#spotId').val(curSpotId);
						$('#spotName').val(curSpotName);
						resultTeleGrid.dataSource.page(1);
						$('#spotId').val("");
						$('#spotName').val("");
					}
				});
			}
		});
    });*/
	// 电话启用
	$("#STARTUSINGTELE").click(function () {
		var checkRows = resultTeleGrid.getCheckedRows();

		if(checkRows.length<1){
			IPLAT.alert("请选择要启用的行");
			return;
		}
		// 检查勾选的是否包含已经启用的
		for (var i = 0; i < checkRows.length; i++) {
			if (checkRows[i].status === "启用"){
				IPLAT.alert("选中行中包含已启用电话！");
				return;
			}
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].telId;
		}
		var info=new EiInfo();
		info.set("list",arr);
		info.set("status","1");
		EiCommunicator.send("ACSP01", "updateTeleStatus", info, {
			onSuccess : function(ei) {
				IPLAT.alert(ei.getMsg());
				resultTeleGrid.dataSource.page(1);
			}
		});
	});

	// 电话停用
	$("#STOPUSINGTELE").on("click", function(e) {

		var checkRows = resultTeleGrid.getCheckedRows();

		if(checkRows.length<1){
			IPLAT.alert("请选择要停用的行");
			return;
		}
		// 检查勾选的是否包含已经停用的
		for (var i = 0; i < checkRows.length; i++) {
			if (checkRows[i].status === "停用"){
				IPLAT.alert("选中行中包含已停用电话！");
				return;
			}
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].telId;
		}
		var info=new EiInfo();
		info.set("list",arr);
		info.set("status","0");
		EiCommunicator.send("ACSP01", "updateTeleStatus", info, {
			onSuccess : function(ei) {
				// NotificationUtil(ei.getMsg(), "success");
				IPLAT.alert(ei.getMsg());
				resultTeleGrid.dataSource.page(1);
			}
		});
	});





	IPLATUI.EFGrid = {
		"resultSpot": {
			onRowClick:function(e){
				var model = e.model;
				curDeptId = model.deptId;
				curDeptName = model.deptName;
				curSpotId = model.spotId;
				curSpotName = model.spotName;
				$("#curSpotId").val(model.spotId);
				resultTeleGrid.dataSource.page(1);

			},
			onCheckRow:function(e){
				if(e.checked){
					var model = e.model;
					curDeptId = model.deptId;
					curDeptName = model.deptName;
					curSpotId = model.spotId;
					curSpotName = model.spotName;
					$("#curSpotId").val(model.spotId);
					resultTeleGrid.dataSource.page(1);
				}else{
					curDeptId = null;
					curDeptName = null;
					curSpotId = null;
					curSpotName = null;
				}

			}

		},

	};


	/**
	 * 弹窗界面
	 */
	function popData(type) {
		if(type === "ADDSPOT"){
			var url = IPLATUI.CONTEXT_PATH + "/web/ACSP0101?deptId="+curDeptId+"&deptName="+curDeptName;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataWindow.open().center();
		}
		if(type === "EDITSPOT"){
			var row = resultSpotGrid.getCheckedRows()[0];
			var url = IPLATUI.CONTEXT_PATH + "/web/ACSP0102?deptId="+curDeptId+"&deptName="+curDeptName+
					"&spotName="+row.spotName+"&spotId="+row.spotId+"&spotNum="+row.spotNum+"&jpSpotName="+row.jpSpotName+"&building="+row.building+"&floor="+row.floor+"&room="+row.room+
					"&remark="+row.remark;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataWindow.open().center();
		}

		if(type === "ADDTELE"){
			var row = resultSpotGrid.getCheckedRows()[0];


			var url = IPLATUI.CONTEXT_PATH + "/web/ACSP0103?"+
					"deptId="+row.deptId+"&deptName="+row.deptName+
					"&spotId="+row.spotId+"&spotName="+row.spotName;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataWindow.open().center();
		}

		if(type === "EDITTELE"){
			var rowTele = resultTeleGrid.getCheckedRows()[0];

			var url = IPLATUI.CONTEXT_PATH + "/web/ACSP0104?"+
					"deptId="+rowTele.deptId+"&deptName="+rowTele.deptName+
					"&spotId="+rowTele.spotId+"&spotName="+rowTele.spotName+
					"&telNum="+rowTele.telNum+"&remark="+rowTele.remark+
					"&telId="+rowTele.telId;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataWindow.open().center();
		}
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
					window.resultSpotGrid.dataSource.page(1);
					window['excelChooseWindow'].close();
				}else if (data.msg == "part"){
					NotificationUtil("导入数据存在错误","warning");
					console.log(data);
					window.resultSpotGrid.dataSource.page(1);
					downloadFileByBase64('data:application/xls;base64,'+data.base64);
				}else if (data.msg == "error"){
					NotificationUtil("导入过程错误","error");
					// console.log(data);
					window['excelChooseWindow'].close();
				}
			}
		});
	});

	var uploading2 = false;
	$("#uploadButton2").on("click", function(){
		$("#uploadButton2").attr("disabled","disabled");
		var fd =  new FormData();
		fd.append("fileUpload",$('#excelFile2')[0].files[0]);
		if ($('#excelFile2')[0].files[0] == null){
			IPLAT.alert("请选择上传文件");
		}
		if(uploading2){
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
				uploading2 = true;
			},
			success : function(data) {
				uploading2 = false;
				if(data.msg == "all"){
					NotificationUtil("导入数据全部成功","success");
					window.resultTeleGrid.dataSource.page(1);
					window['excelChooseWindow2'].close();
				}else if (data.msg == "part"){
					NotificationUtil("导入数据存在错误","warning");
					console.log(data);
					window.resultTeleGrid.dataSource.page(1);
					downloadFileByBase64('data:application/xls;base64,'+data.base64);
				}else if (data.msg == "error"){
					NotificationUtil("导入过程错误","error");
					// console.log(data);
					window['excelChooseWindow2'].close();
				}
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