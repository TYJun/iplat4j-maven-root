<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="项目属性" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-projectStatus" cname="状态" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-projectNo" cname="项目号" readOnly="true"/>
			<EF:EFInput ename="inqu_status-0-projectName" cname="项目名称" required="true"/>
			<EF:EFSelect ename="inqu_status-0-projectProp" cname="项目性质" required="true">
				<EF:EFCodeOption codeName="pm.projectProp"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-projectTypeNum" cname="项目类型" required="true">
				<EF:EFCodeOption codeName="pm.projectType"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-projectPriNum" cname="项目优先级">
				<EF:EFCodeOption codeName="pm.projectPriority"/>
			</EF:EFSelect>
			<EF:EFPopupInput ename="inqu_status-0-contorId" cname="项目负责人" 
				popupTitle="人员选择" required="true" readOnly="true"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-undertakeDeptNum" cname="承办科室" 
				popupTitle="科室选择" required="true" readOnly="true"
				popupType="ServiceGrid" serviceName="PM0101" methodName="queryDept" resultId="dept"
				valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
			<EF:EFInput ename="inqu_status-0-projectObjectDeptName" cname="项目科室" readOnly="true"/>
			<EF:EFInput ename="inqu_status-0-projectObjectDeptNum" cname="项目科室" type="hidden"/>
			<EF:EFPopupInput ename="inqu_status-0-projectObjectCons" cname="项目联络人" 
				popupTitle="人员选择" required="false" readOnly="true"
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFSelect ename="inqu_status-0-projectProgress" cname="项目进度">
				<EF:EFCodeOption codeName="pm.projectProgress"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-startDate" cname="开始日期" />
			<EF:EFDatePicker ename="inqu_status-0-finishDeadline" cname="完成期限" />
			<EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商" popupTitle="供应商选择" 
				popupType="ServiceGrid" serviceName="PM0103" methodName="querySupplier" resultId="supplier"
				valueField="supplierNum" textField="supplierName" readOnly="true"
				columnEnames="supplierNum,supplierName" columnCnames="供应商编码,供应商名称" />
			<EF:EFInput ename="inqu_status-0-winbidExpense" cname="中标费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
			<EF:EFInput ename="inqu_status-0-finishExpense" cname="结算费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
			<EF:EFInput ename="inqu_status-0-projectContent" cname="项目内容" type="textarea"/>
			<EF:EFInput ename="inqu_status-0-projectRemark" cname="备注" type="textarea" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="ACCEPTPR" layout="0" ></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
 		<div title="执行人员">
 			<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryStaff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFColumn ename="execStaffId" cname="执行人工号" width="100" />
				<EF:EFColumn ename="execStaffName" cname="执行人姓名" width="100" />
				<EF:EFColumn ename="number" cname="执行人联系电话" width="200" />
				<EF:EFColumn ename="deptNum" cname="执行人科室" width="200" hidden="true" />
				<EF:EFColumn ename="deptName" cname="执行人科室" width="200" />
			</EF:EFGrid> 
		</div>
		<div title="知会人员">
			<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryKnow">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFColumn ename="notifyStaffId" cname="知会人工号" width="100" />
				<EF:EFColumn ename="notifyStaffName" cname="知会人姓名" width="100" />
				<EF:EFColumn ename="number" cname="知会人联系电话" width="200" />
				<EF:EFColumn ename="deptNum" cname="知会人科室" width="200" hidden="true"/>
				<EF:EFColumn ename="deptName" cname="知会人科室" width="200" />
			</EF:EFGrid>
		</div>
		<div title="项目附件">
			<EF:EFGrid blockId="resultC" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true"/>
				<EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true"/>
				<EF:EFColumn ename="attachName" cname="附件名称" width="200" />
				<EF:EFColumn ename="attachSize" cname="附件大小" width="100" />
				<EF:EFColumn ename="attachDesc" cname="附件说明" width="200" />
			</EF:EFGrid>
		</div>
		<div title="督办信息">
			<EF:EFGrid blockId="resultD" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryVia">
				<EF:EFColumn ename="id" cname="id" hidden="true" width="50" />
				<EF:EFColumn ename="projectPk" cname="projectPk" hidden="true" width="50" />
				<EF:EFPopupColumn ename="superviseMaker" cname="督办人工号" popupTitle="人员选择" popupWidth="350"
					popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" 
					resultId="person" valueField="workNo" textField="workNo" 
					columnEnames="workNo,name" columnCnames="工号,姓名"
 					backFillFieldIds="superviseMaker,superviseMakerName" backFillColumnIds="workNo,name"
 					resizable="true" />
 				<EF:EFColumn ename="superviseMakerName" cname="督办人姓名" width="50" />
				<EF:EFColumn ename="superviseInfo" cname="督办信息" width="100" />
				<EF:EFColumn ename="startTime" cname="开始日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="50" />
				<EF:EFColumn ename="endTime" cname="结束日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="50" />
			</EF:EFGrid> 
		</div>
		<div title="日程进程">
			<EF:EFGrid blockId="resultE" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryCed">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFColumn ename="processArrange" cname="进程安排" width="200" />
				<EF:EFColumn ename="expense" cname="费用" width="100" />
				<EF:EFColumn ename="startTime" cname="开始日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="100" />
				<EF:EFColumn ename="endTime" cname="结束日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="100" />
				<EF:EFComboColumn ename="schedule" cname="进度" width="100" >
					<EF:EFCodeOption codeName="pm.projectProgress"/>
				</EF:EFComboColumn>
				<EF:EFComboColumn ename="finishFlag" cname="是否完成" width="100" >
					<EF:EFOption label="否" value="0"/>
					<EF:EFOption label="是" value="1"/>
				</EF:EFComboColumn>
			</EF:EFGrid>
		</div>
		<div title="提交资料">
			<EF:EFGrid blockId="resultF" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryZiliao">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFPopupColumn ename="dataCode" cname="资料编码" popupWidth="350"
					codeName="pm.projectData" valueField="ITEM_CODE"
					columnEnames="ITEM_CODE,ITEM_CNAME"
					columnCnames="资料编码, 资料名称"
					backFillFieldIds="dataCode,dataName"
					backFillColumnIds="ITEM_CODE,ITEM_CNAME"
					popupType="DynamicGrid"/>
				<EF:EFColumn ename="dataName" cname="资料名称" width="200" />
			</EF:EFGrid>
		</div>
		<div title="验收参与人员">
			<EF:EFGrid blockId="resultG" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryAcceptStaff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFColumn ename="testStaffId" cname="验收参与人员工号" width="100" />
				<EF:EFColumn ename="testStaffName" cname="验收参与人员姓名" width="100" />
				<EF:EFColumn ename="contacttel" cname="验收参与人员联系电话" width="200" />
				<EF:EFColumn ename="deptNum" cname="验收参与人员科室" width="200" hidden="true"/>
				<EF:EFColumn ename="deptName" cname="验收参与人员科室" width="200" />
				<EF:EFColumn ename="remark" cname="备注" width="200" />
			</EF:EFGrid>
		</div>
	</EF:EFTab>

	<!-- 人员选择弹出窗口 -->
	<EF:EFWindow id="personChoose" url="" lazyload="true" width="50%" height="100%"></EF:EFWindow>
	
	<!-- 附件上传窗口 -->
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="projectFile" docTag="pr_file" path="project"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	//文件上传
	IPLATUI.EFUpload = {
		"projectFile":{
			showFileList:false,
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
				model["projectPk"] = "";
				model["attachId"] = e.response.docId;
				model["attachPath"] = e.response.docUrl;
				model["attachName"] = file.name;
				model["attachSize"] = file.size;
				model["attachDesc"] = "";
				resultCGrid.addRows(model);
				fileChooseWindow.close();
			},
		}
	}
	$(function() {
		var validator = IPLAT.Validator({id: "result"});//开启校验
		//表格按钮处理
		IPLATUI.EFGrid = {
			"resultA":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDSTAFF",text: "新增",layout:"3",
						click: function () { personChooseWindowOpen("STAFF"); }
					},{
						name: "DELSTAFF",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultAGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultAGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的执行人信息")
							}
						}
					}]
				}, 
			},
			"resultB":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDKNOW",text: "新增",layout: "3",
						click: function () { personChooseWindowOpen("KNOW"); }
					},{
						name: "DELKNOW",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultBGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultBGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的执行人信息")
							}
						}
					}]
				}
			},
			"resultC":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "addFile",text: "上传",layout: "3",
						click: function () { fileChooseWindow.open().center() }
					},{
						name: "delFile",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultCGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultCGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的项目附件信息")
							}
						}
					},{
						name: "downLoadFile",text: "下载",layout: "3",
						click: function () { downLoadFile() }
					}]
				}
			},
			"resultD":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDVIA",text: "新增",layout:"3",
						click: function () { resultDGrid.addRow() }
					},{
						name: "DELVIA",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultDGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultDGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的督办信息")
							}
						}
					}]
				}, 
			},
			"resultE":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDCED",text: "新增",layout:"3",
						click: function () { resultEGrid.addRow() }
					},{
						name: "DELCED",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultEGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultEGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的进程信息")
							}
						}
					}]
				}, 
			},
			"resultF":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDZILIAO",text: "新增",layout:"3",
						click: function () { resultFGrid.addRow() }
					},{
						name: "DELZILIAO",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultFGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultFGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的资料信息")
							}
						}
					}]
				}, 
			},
			"resultG":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDACCEPT",text: "新增",layout:"3",
						click: function () { personChooseWindowOpen("ACCEPT"); }
					},{
						name: "DELACCEPT",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultGGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultGGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的资料信息")
							}
						}
					}]
				}, 
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
		
		//保存
		$("#ACCEPTPR").on("click", function() {
			//获取参数
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			//获取tab数据
			var staffArray = resultAGrid.getDataItems();
			var knowArray = resultBGrid.getDataItems();
			var fileArray = resultCGrid.getDataItems();
			var viaArray = resultDGrid.getDataItems();
			var cedArray = resultEGrid.getDataItems();
			var ziliaoArray = resultFGrid.getDataItems();
			var acceptStaffArray = resultGGrid.getDataItems();
			eiInfo.set("staffList",staffArray);
			eiInfo.set("knowList",knowArray);
			eiInfo.set("fileList",fileArray);
			eiInfo.set("viaList",viaArray);
			eiInfo.set("cedList",cedArray);
			eiInfo.set("ziliaoList",ziliaoArray);
			eiInfo.set("acceptStaffList",acceptStaffArray);
			//参数校验
			if(!validatePR(eiInfo)){ return; }
			//提交
			EiCommunicator.send("PM0401", "saveProject", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		});
		
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow();
		});
	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['projectEditWindow'].close();
	}
	
	//打开人员选择窗口
	function personChooseWindowOpen(type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0104?methodName=query&inqu_status-0-personType="+type;
		var popData = $("#personChoose");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开弹窗
		personChooseWindow.open().center();
	}
	
	//接收子页面参数
	function addRows(personType,checkRows){
		for (var index in checkRows) {
			var model = checkRows[index];
			model["number"] = model.phone;
			if("STAFF" == personType){
				model["execStaffId"] = model.workNo;
				model["execStaffName"] = model.name;
				//resultAGrid.addRows(model)
				clearRepeat(model,resultAGrid,"execStaffId");
			} else if("KNOW" == personType) {
				model["notifyStaffId"] = model.workNo;
				model["notifyStaffName"] = model.name;
				//resultBGrid.addRows(model)
				clearRepeat(model,resultBGrid,"notifyStaffId");
			} else {
				model["contacttel"] = model.phone;
				model["testStaffId"] = model.workNo;
				model["testStaffName"] = model.name;
				model["remark"] = "";
				//resultGGrid.addRows(model)
				clearRepeat(model,resultGGrid,"testStaffId");
			}
		}
	}
	
	//处理重复数据
	function clearRepeat(model,grid,compareField){
		var list = grid.getDataItems();
		var isExist = false;
		for(var i in list) {
			var row = list[i];
			if(row[compareField] == model[compareField]){
				isExist = true;
			} 
		}
		if(!isExist){grid.addRows(model)}
	}
	
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "id": {type: "string"},
		        "projectPk": {type: "string"},
		        "attachId": {type: "string"},
		        "attachPath": {type: "string"},
		        "attachName": {type: "string"},
		        "attachSize": {type: "number"},
		        "attachDesc": {type: "string"}
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}
	
	//文件下载
	function downLoadFile(){
		var checkRows = resultCGrid.getCheckedRows();
		if (checkRows.length > 0) {
			for(var index in checkRows){
				var docId = checkRows[index].attachId;
	            var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
	            window.location.href = href;
			}
		} else {
			IPLAT.NotificationUtil("请选择需要下载的文件")
		}
	}
	
	//参数校验
	function validatePR(eiInfo){
		if(isEmpty(eiInfo.get("inqu_status-0-projectName"))){
			IPLAT.NotificationUtil("项目名称不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-projectProp"))){
			IPLAT.NotificationUtil("项目性质不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-projectTypeNum"))){
			IPLAT.NotificationUtil("项目类型不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-contorId"))){
			IPLAT.NotificationUtil("项目负责人不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-startDate"))){
			IPLAT.NotificationUtil("开始日期不能为空");
			return false;
		}
		if(isEmpty(eiInfo.get("inqu_status-0-finishDeadline"))){
			IPLAT.NotificationUtil("完成期限不能为空");
			return false;
		}
		//校验中标费用
		var regst= /^\d*\.?\d+$/;
		if(!regst.test(eiInfo.get("inqu_status-0-winbidExpense"))){
			IPLAT.NotificationUtil("请重新输入中标费用为正数");
			return false;
		}
		//校验结束费用
		if(!regst.test(eiInfo.get("inqu_status-0-finishExpense"))){
			IPLAT.NotificationUtil("请重新输入结束费用为正数");
			return false;
		}
		return true;
	}
	
	function isEmpty(str) { 
		if(str == undefined){
			return true;
		}
		if(str == null){
			return true;
		}
		if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		}
		return false;
	}
	
</script>

