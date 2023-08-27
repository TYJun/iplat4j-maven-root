<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="信息详情">
		<div class="row">
			<EF:EFInput ename="manId" cname="主键" readOnly="true"
						colWidth="4"  type="hidden"/>
			<EF:EFInput ename="manNo" cname="工号" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="manName" cname="姓名" readOnly="true"
						colWidth="4"  type="text"/>
			<EF:EFInput ename="sexMeaning" cname="性别"  readOnly="true"
						colWidth="4"  type="text"/>
		</div>
		<div class="row">
			<EF:EFInput ename="age" cname="年龄"  readOnly="true" colWidth="4"  type="text"/>
			<EF:EFInput ename="phone" cname="电话"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="maritalStatus" cname="婚姻状态"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="deptName" cname="部门科室"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="identityCard" cname="身份证号"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="educationBackground" cname="学历"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="academicYear" cname="学年制"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="employmentNature" cname="职工属性"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="isNetwork" cname="是否联网"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="isStay" cname="是否已办暂住证"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="permanentResidence" cname="户口所在地"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="hiredate" cname="入职时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="building" cname="宿舍楼" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="floor" cname="宿舍层" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="roomName" cname="宿舍总称" colWidth="4"  type="text" readonly="true"  />
		</div>
		<div class="row">
			<EF:EFInput ename="bedNo" cname="床位号" colWidth="4"  type="text" readonly="true"  />
			<EF:EFInput ename="rent" cname="房租" colWidth="4"  type="text" readonly="true" />
			<EF:EFInput ename="manageFee" cname="管理费" colWidth="4"  type="text" readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="actualRent" cname="实际月租"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualManageFee" cname="实际管理费"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="estimatedInDate" cname="预计入住时间"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualInDate" cname="实际入住时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="estimatedOutDate" cname="预计退宿时间"
						colWidth="4"  type="text" readOnly="true"/>
			<EF:EFInput ename="actualOutDate" cname="实际退宿时间"
						colWidth="4"  type="text" readOnly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="applyNote" cname="申请备注"
						colWidth="4" rows="3" type="textarea" readOnly="true"/>
			<EF:EFInput ename="dormNote" type="textarea" colWidth="4"
						rows="3" cname="宿舍备注" readonly="true" />
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="attachmentResult" title = "个人附件">
		<EF:EFGrid blockId="resultX" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" serviceName="DMFJ01" queryMethod="queryAttchmentFile">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="manId" cname="manId" width="100" hidden="true" enable="false"/>
			<EF:EFColumn ename="attachmentId" cname="附件id" width="200" hidden="true" enable="false"/>
			<EF:EFColumn ename="attachmentPath" cname="附件路径" width="200" hidden="true" enable="false"/>
			<EF:EFColumn ename="attachmentName" cname="附件名称" width="200" enable="false" align="center"/>
			<EF:EFColumn ename="attachmentSize" cname="附件大小(M)" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="attachmentDesc" cname="附件说明" width="200" align="center"/>
			<EF:EFColumn ename="recCreateName" cname="上传人" width="100" enable="false" align="center"/>
			<EF:EFColumn ename="recCreateTime" cname="上传时间" width="100" enable="false" align="center"/>
		</EF:EFGrid>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="保存" ename="SAVEATTACHMENT" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="operationResult" title = "操作履历">
		<EF:EFGrid blockId="operationItem" autoDraw="no" checkMode="row" serviceName="DMZH0101" queryMethod="queryDetailLCInfo">
			<EF:EFColumn ename="operationName" cname="操作人"  readonly="true" align="center"/>
			<EF:EFColumn ename="statusCodeMeaning" cname="操作类型"  readonly="true" align="center"/>
			<EF:EFColumn ename="operationTime" cname="操作时间" align="center" readonly="true"/>
			<EF:EFColumn ename="id" cname="id" align="center" readonly="true" hidden="true" />
		</EF:EFGrid>
	</EF:EFRegion>

	<EF:EFWindow id="attachmentChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="upload" title="附件上传">
			<EF:EFUpload ename="attachmentFile" docTag="dm_file" path="attachment"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	//文件上传
	IPLATUI.EFUpload = {
		"attachmentFile": {
			loadComplete: function (e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles();
			},
			validation: {
				allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp", ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".zip", "rar", ".7z"]
			},
			localization: {
				invalidFileExtension: "文件格式不支持, 上传失败"
			},
			success: function (e) {
				var file = e.files[0];
				var model = createModel(1);
				var fileSize = file.size / 1024 / 1024;
				fileSize = fileSize.toFixed(2);
				model["id"] = "";
				model["manId"] = "";
				model["attachmentId"] = e.response.docId;
				model["attachmentPath"] = e.response.docUrl;
				model["attachmentName"] = file.name;
				model["attachmentSize"] = fileSize;
				model["attachmentDesc"] = "";
				resultXGrid.addRows(model);
				attachmentChooseWindow.close();
			},
		},
	}

	//创建kendo.data.Model
	function createModel(id) {
		var gridRow = kendo.data.Model.define({
			id: "uploadId",
			fields: {
				"id": {type: "string"},
				"manId": {type: "string"},
				"attachmentId": {type: "string"},
				"attachmentPath": {type: "string"},
				"attachmentName": {type: "string"},
				"attachmentSize": {type: "number"},
				"attachmentDesc": {type: "string"}
			}
		});
		var model = new gridRow({uploadId: id});
		return model;
	}



    //文件下载
	function downLoadFile() {
		var checkRows = resultXGrid.getCheckedRows();
		if (checkRows.length > 0) {
			for (var index in checkRows) {
				var docId = checkRows[index].attachmentId;
				var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
				window.location.href = href;
			}
		} else {
			IPLAT.NotificationUtil("请选择需要下载的文件")
		}
	}

	$(function () {
		var fileList = [];
		//表格按钮处理
		IPLATUI.EFGrid = {
			"resultX": {
				toolbarConfig: {
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,
					cancel: false,
					save: false,
					'delete': false,
					buttons: [{
						name: "addFile", text: "上传", layout: "3",
						click: function () {
							attachmentChooseWindow.open().center()
						}
					}, {
						name: "delFile", text: "删除", layout: "3",
						click: function () {
							var checkRows = resultXGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultXGrid.removeRows(checkRows);
								fileList.push(checkRows[0]);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的个人附件", "warning")
							}
						}
					}, {
						name: "downLoadFile", text: "下载", layout: "3",
						click: function () {
							downLoadFile()
						}
					}]
				}
			},
		}
		//保存
		$("#SAVEATTACHMENT").on("click", function () {
			var roomName = IPLAT.EFInput.value($("#roomName")); /* 宿舍总称 */
			if (roomName == null || roomName == ""){
				IPLAT.NotificationUtil("当前人员还未选房入住！", "warning");
			}else {
				var manId = IPLAT.EFInput.value($("#manId")); /* 人员id */
				//获取参数
				var eiInfo = new EiInfo();
				//获取附件列表的数据
				var attachmentArray = resultXGrid.getDataItems();
				var deleteFile = fileList;
				console.log(attachmentArray);
				console.log(deleteFile);
				eiInfo.set("manId", manId);
				eiInfo.set("fileList", attachmentArray);
				eiInfo.set("deleteFile", deleteFile);
				//提交
				EiCommunicator.send("DMFJ01", "saveAttachment", eiInfo, {
					onSuccess: function (ei) {
						window.parent['popDatashowWindow'].close();
						IPLAT.NotificationUtil(ei.msg);
						setTimeout(function() {
							window.parent.location.reload()
						}, 1000);
					}
				})
			}
		});
	})


</script>