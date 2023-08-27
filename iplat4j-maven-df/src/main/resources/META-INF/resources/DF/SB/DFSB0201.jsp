<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="检验登记">
	<EF:EFRegion id="inqu" title="检验信息">
		<div class="row">
			<EF:EFInput ename="taskNo" cname="工单号：" colWidth="4" ratio="4:8" readonly="true"/>
			<EF:EFPopupInput ename="machineId" cname="检验设备编码："  colWidth="4" ratio="4:8"
				popupTitle="设备信息选择" required="true" readonly="true"
				popupType="ServiceGrid" serviceName="DFSB02" methodName="queryMachineId" resultId="result"
				valueField="machineId" textField="machineCode"  
				columnEnames="machineCode,machineName,innerMachineCode"
				columnCnames="特种设备编码, 特种设备名称, 设备内部编码"
				backFillFieldIds="machineName,innerMachineCode"
				backFillColumnIds="machineName,innerMachineCode"/>
			<EF:EFInput ename="machineName" cname="检验设备名称：" colWidth="4" ratio="4:8" readonly="true"/>
			</div>
			<div class="row">
			<EF:EFInput ename="innerMachineCode" cname="内部设备编码：" colWidth="4" ratio="4:8"/>
			<EF:EFSelect ename="checkType" cname="检验类型：" colWidth="4"
			ratio="4:8" textField="label" valueField="value" required="true">
			 	<EF:EFOption label="请选择" value=""/>	
				<EF:EFOption label="年度检验" value="0"/>
				<EF:EFOption label="定期检查" value="1"/>
			</EF:EFSelect>
			<EF:EFSelect ename="statusCode" cname="工单状态"  colWidth="4" readonly="true"
			ratio="4:8" textField="label" valueField="value">
			 	<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="已审核" value="1"/>
				<EF:EFOption label="待审核" value="-1"/>
			</EF:EFSelect>
			</div>
			<div class="row">	
			<EF:EFDatePicker ename="thisCheckDate" cname="本次检验日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>	
			<EF:EFDatePicker ename="thisFinishDate" cname="本次检验完工日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>			
			<EF:EFDatePicker ename="nextCheckDate" cname="下次检验日：" role="date" colWidth="4" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" required="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
		<div title="文件列表">
			<EF:EFGrid blockId="FileResult" fitHeight="true" autoDraw="no">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="relateId" cname="附件id" enable="false" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="附件名称" enable="false"/>
				<EF:EFColumn ename="filePath" cname="附件路径" enable="false"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
	<%-- 附件上传窗口 --%>
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="projectFile" docTag="sb_file" path="file"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	//文件上传
	 IPLATUI.EFUpload = {
		"projectFile":{
			loadComplete: function(e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles();
			},
			validation: {
				allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp",".txt",".doc","docx",".xls",".xlsx",".ppt",".pptx",".pdf",".zip","rar",".7z"]
			},
			localization: {
				invalidFileExtension: "文件格式不支持, 上传失败"
			},
			success: function(e) {
				var file = e.files[0];
				var model = createModel(1);
				model["id"] = "";
				model["docId"] = e.response.docId;
				model["relateId"] = "";
				model["filePath"] = e.response.docUrl;
				model["fileName"] = file.name;
				FileResultGrid.addRows(model);
				fileChooseWindow.close();
			},
		}
	}
	$(function() {
		/*var validator = IPLAT.Validator({id: "result"});//开启校验*/
		//表格按钮处理
		IPLATUI.EFGrid = {
			"FileResult":{
				pageable : false,
				exportGrid : false,
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false,
					buttons:[{
						name: "addFile",text: "上传",layout: "3",
						click: function () { fileChooseWindow.open().center() }
					},{
						name: "delFile",text: "删除",layout: "3",
						click: function () {
							var checkRows = FileResultGrid.getCheckedRows();
							if (checkRows.length > 0) {
								FileResultGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的项目附件信息","failure")
							}
						}
					},{
						name: "downLoadFile",text: "下载",layout: "3",
						click: function () { downLoadFile() }
					}]
				}
			},
		}

		//使用 grid 的 refresh 方法来解决渲染异常
		IPLATUI.EFTab = {
			"tab-tab_grid": {
				show: function (e) {
					$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh()
				}
			}
		}

	})

	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
			id: "uploadId",
			fields: {
				"id": {type: "string"},
				"docId": {type: "string"},
				"relateId": {type: "string"},
				"filePath": {type: "string"},
				"fileName": {type: "string"},
			}
		});
		var model = new gridRow({uploadId:id});
		return model;
	}
	//文件下载
	function downLoadFile(){
		var checkRows = FileResultGrid.getCheckedRows();
		if (checkRows.length > 0) {
			for(var index in checkRows){
				var docId = checkRows[index].docId;
				var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
				window.location.href = href;
			}
		} else {
			IPLAT.NotificationUtil("请选择需要下载的文件")
		}
	}
</script>