<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-code" cname="规则代码" />
			<EF:EFInput ename="inqu_status-0-description" cname="规则说明" />
			<EF:EFInput ename="inqu_status-0-name" cname="参数项名称" />
			<EF:EFInput ename="inqu_status-0-tag" cname="参数项Tag" />
		</div>
	</EF:EFRegion>
<%--	<EF:EFRegion id="ReUpload" title="导入数据">--%>
<%--		&lt;%&ndash; <EF:EFUpload ename="mtrePic" docTag="mt_fs_file" path="mt/img"/> &ndash;%&gt;--%>
<%--		<form id="excelForm"  enctype="multipart/form-data">--%>
<%--			数据上传：<input id="excelFile" type="file" name="file" ><br />--%>
<%--		</form>--%>
<%--		<button id="download">模板下载</button>--%>
<%--		<button class="i-btn-lg" style="float: right" id="uploadButton">提交</button>--%>
<%--	</EF:EFRegion>--%>
	<EF:EFRegion id="ExcelImport" title="Excel导入">
		<EF:EFInput ename="fileForm" type="file"/>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="报警规则信息">
		<EF:EFGrid blockId="result" checkMode="single" autoDraw="no">
			<EF:EFColumn ename="id" cname="id" width="100"
				hidden="true" />
			<EF:EFColumn ename="code" cname="规则代码" width="100" disabled="true"/>
			<EF:EFColumn ename="description" cname="规则说明" width="200" disabled="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" width="200" disabled="true"/>
            <EF:EFColumn ename="dimensionName" cname="计量量纲" width="200" disabled="true"/>
			<EF:EFColumn ename="tmsCount" cname="关联参数量" width="200" disabled="true"/>
			<EF:EFColumn ename="remarks" cname="备注" width="100" disabled="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="70%"
	height="60%">
</EF:EFWindow>
<EF:EFWindow id="popData3" url=" " lazyload="true" width="60%"
			 height="70%">
</EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;

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

	IPLAT.FileUploader = function (options) {
		var element = $("#" + options.id);
		var saveUrl = IPLATUI.CONTEXT_PATH + "/MS/AR/MSAR0102.jsp?ename=" + options.ename + "&serviceName="
				+ options.serviceName + "&methodName=" + options.methodName; //jsp
		$.extend(options, {
			async: {
				saveUrl: saveUrl,
			},
			success:function (e) {
				if(e.getStatus()==-1 || e.getStatus()==-2) {
					// IPLAT.alert(e.msg);
					IPLAT.alert(eiInfo.getMsg())
				}else {
					resultGrid.dataSource.page(1);
				}


			}
		});
		return element.kendoUpload(options).data("kendoUpload");
	};

	IPLAT.FileUploader({
		id: "fileForm",
		ename: "fileForm",
		serviceName: "MSAR01",
		methodName: "importForm"
	});
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
			url: IPLATUI.CONTEXT_PATH+"/excelACGMS",
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
					// console.log(data);
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
	function popData(id, type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSAR0101?initLoad&id=" + id
				+ "&type=" + type;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	};
	function popDatas(id, type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSAR0103?initLoad&id=" + id
				+ "&type=" + type;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	};

	function popData3(id) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSAR0104?initLoad&id=" + id;
		var popData3 = $("#popData3");
		popData3.data("kendoWindow").setOptions({
			open : function() {
				popData3.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popData3Window.open().center();
	}
	$(function() {
		$("#excelimport").css("display", "none");

		resultGrid.dataSource.query(1);
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
		$("#ADD").on("click", function(e) {
			popData("", "add");
		});
        //新增按钮
        $("#DELETE").on("click", function(e) {
            if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行删除");
            }
        });

		//查看按钮
		$("#SEE").on("click", function(e) {
            if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行查看");
            } else {
				popData(datagrid.id, "see");
				//datagrid = null;
			}
		});
		$("#EXPORTQRCODE").on("click", function(e) {
			window.location.href =  IPLATUI.CONTEXT_PATH+"/excelACGMS";
		});
		$("#BINDMSPL").on("click", function (e) {
			if ( $(".check-one").is(':checked')==false){
				IPLAT.alert("请先选择一条记录进行绑定");
			} else {
				popDatas(datagrid.id, "tab");
				//datagrid = null;
			}
		})

		//查看按钮
		$("#VIEW").on("click", function(e) {
			if ($(".check-one").is(':checked')==false) {
				IPLAT.alert("请先选择一条记录进行修改");
			} else {
				popData3(datagrid.id);
			}
		});

	});
</script>
