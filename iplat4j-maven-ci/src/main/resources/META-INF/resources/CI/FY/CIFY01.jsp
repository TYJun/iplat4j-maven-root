<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>食堂基本费用</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">

			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="查询日期起"
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="查询日期止 "
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="食堂基本费用信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" >
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>

			<EF:EFColumn ename="canteenName" cname="食堂名称" width="100" align="center"/>
			<EF:EFColumn ename="canteenType" cname="食堂分类" width="100" align="center"/>
			<EF:EFColumn ename="date" cname="日期" width="100" align="center"/>
			<EF:EFColumn ename="water" cname="水费(元)" width="100" align="center"/>
			<EF:EFColumn ename="electricity" cname="电费(元)" width="100" align="center"/>
			<EF:EFColumn ename="gas" cname="气费(元)" width="100" align="center"/>
			<EF:EFColumn ename="labour" cname="人工费(元)" width="100" align="center"/>
			<EF:EFColumn ename="additionalCosts" cname="额外费用(元)" width="100" align="center"/>
			<EF:EFColumn ename="memo" cname="备注" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>

	<%--文件上传组件--%>
	<EF:EFUpload ename="file" docTag="ci_fy_file" path="ci/file" style="display:none;"/>
</EF:EFPage>
<script type="text/javascript">
	//上传组件
	IPLATUI.EFUpload = {
		file:{
			validation:{
				allowedExtensions: [".xls",".xlsx"] //文件格式过滤
			},
			localization: {
				invalidFileExtension: "请按照模板类型上传xls文件"
			},
			loadComplete: function(e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles(); // 清空所有历史
			},
			success: function(e) {
				IPLAT.progress($("#result"),true);
				if(e.operation == "upload"){
					//获取文件路径
					var eiInfo = new EiInfo();
					eiInfo.set("docId",e.response.docId);
					eiInfo.set("docTag",e.response.docTag);
					eiInfo.set("docUrl",e.response.docUrl);
					eiInfo.set("fileType",e.files[0]["extension"]);
					EiCommunicator.send("CIFY01", "uploadFile", eiInfo, {
						onSuccess : function(ei) {
							console.log(ei);
							if(ei.status == 1){
								NotificationUtil(ei.msg, "success");
								//刷新grid
								resultGrid.dataSource.page(1);
							}else{
								NotificationUtil(ei.msg, "warning");
							}
							IPLAT.progress($("#result"),false);
						}
					});
				}
			}
		}
	}

	IPLATUI.EFGrid = {
		"result": {
			toolbarConfig : {
				buttons : [ {
					name : "exportTemplate",
					text : "模板下载",
					layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
					icon : "css:fa-file-archive-o",
					attributes : {
						style : "float:left;height:30px;"
					},
					click : downloadFile
				}, {
					name : "importType",
					text : "数据导入",
					layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
					icon : "css:fa-file-archive-o",
					attributes : {
						style : "float:left;height:30px;"
					},
					click : uploadFile
				} ]
			}
		}


	}

	//下载补贴模板
	function downloadFile() {
		//获取文件路径
		var url = IPLATUI.CONTEXT_PATH + "/template/STFY-template.xls";
		const a = document.createElement('a'); // 创建a标签
		a.setAttribute('download', '');// download属性
		a.setAttribute('href', url);// href链接
		a.click();// 自执行点击事件
	}
	//上传文件
	function uploadFile() {
		//触发组件
		$("#file").click();
	}


	$(function() {
		//隐藏上传组件
		$(".upload-file").css("display","none");
		//禁止上传组件多选
		$("#file").removeAttr("multiple");
		//限制上传组件文件类型
		$("#file").attr("accept",".xls,.xlsx");


		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.page(1);
		});

		//页面加载时查询一次n
		$("#QUERY").click();

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.page(1);
		});



	});


</script>

