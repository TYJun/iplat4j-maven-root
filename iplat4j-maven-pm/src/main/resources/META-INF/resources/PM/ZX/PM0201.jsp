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
		</div>
		<div class="row">
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
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-undertakeDeptNum" cname="承办科室" 
				popupTitle="科室选择" required="false" readOnly="true"
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
		</div>
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-projectProgress" cname="项目进度">
				<EF:EFCodeOption codeName="pm.projectProgress"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-startDate" cname="开始日期" required="true" role="date"/>
			<EF:EFDatePicker ename="inqu_status-0-finishDeadline" cname="完成期限" required="true" role="date"/>
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商" popupTitle="供应商选择" 
				popupType="ServiceGrid" serviceName="PM0103" methodName="querySupplier" resultId="supplier"
				valueField="supplierNum" textField="supplierName" readOnly="true"
				columnEnames="supplierNum,supplierName" columnCnames="供应商编码,供应商名称" />
			<EF:EFInput ename="inqu_status-0-winbidExpense" cname="中标费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
			<EF:EFInput ename="inqu_status-0-finishExpense" cname="结算费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-projectContent" cname="项目内容" type="textarea"/>
			<EF:EFInput ename="inqu_status-0-projectRemark" cname="备注" type="textarea" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="EXCUTE" layout="0" ></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
 		<div title="执行人员">
 			<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryStaff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="execStaffId" cname="执行人工号" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="execStaffName" cname="执行人姓名" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="number" cname="执行人联系电话" width="200" enable="false" align="center"/>
				<EF:EFColumn ename="deptNum" cname="执行人科室" width="200" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="deptName" cname="执行人科室" width="200" enable="false" align="center"/>
			</EF:EFGrid> 
		</div>
		<div title="知会人员">
			<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryKnow">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="notifyStaffId" cname="知会人工号" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="notifyStaffName" cname="知会人姓名" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="number" cname="知会人联系电话" width="200" enable="false" align="center"/>
				<EF:EFColumn ename="deptNum" cname="知会人科室" width="200" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="deptName" cname="知会人科室" width="200" enable="false" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="项目附件">
			<EF:EFGrid blockId="resultC" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="attachName" cname="附件名称" width="200" enable="false" align="center"/>
				<EF:EFColumn ename="attachSize" cname="附件大小" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="attachDesc" cname="附件说明" width="200" enable="false" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="甲供材料">
			<EF:EFGrid blockId="resultD" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryJStuff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="matTypeNum" cname="材料分类编号" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="matTypeName" cname="材料分类名称" width="150" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="matNum" cname="材料编码" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="matName" cname="材料名称" width="150" enable="false" align="center"/>
				<EF:EFColumn ename="spec" cname="规格" width="80" enable="false" align="center"/>
				<EF:EFColumn ename="unitNum" cname="单位" width="80" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="unitName" cname="单位名称" width="80" enable="false" align="center"/>
				<EF:EFColumn ename="price" cname="价格" width="80" readonly="true" enable="false" align="center"/>
				<EF:EFColumn ename="num" cname="数量" width="80" align="center"/>
				<EF:EFColumn ename="totalPrice" cname="总价" width="80" align="center"/>
				<EF:EFColumn ename="brand" hidden="true" cname="品牌" width="80" enable="false" align="center"/>
				<EF:EFColumn ename="supType" cname="供应方" width="150" align="center"/>
				<EF:EFColumn ename="fashion" hidden="true" cname="方式" width="80" align="center"/>
				<EF:EFColumn ename="remark" hidden="true" cname="备注" width="80" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="乙方清单">
			<EF:EFGrid blockId="resultE" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryYStuff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" align="center"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" align="center"/>
				<EF:EFColumn ename="matTypeNum" cname="材料分类编号" width="100" hidden="true" align="center"/>
				<EF:EFColumn ename="matTypeName" cname="材料分类名称" width="150" hidden="true" align="center"/>
				<EF:EFColumn ename="matNum" cname="材料编码" width="100" align="center"/>
				<EF:EFColumn ename="matName" cname="材料名称" width="150" align="center"/>
				<EF:EFColumn ename="spec" cname="规格" width="80" align="center"/>
				<EF:EFColumn ename="unitNum" cname="单位" width="80" hidden="true" align="center"/>
				<EF:EFColumn ename="unitName" cname="单位名称" width="80" align="center"/>
				<EF:EFColumn ename="price" cname="价格" width="80" align="center"/>
				<EF:EFColumn ename="num" cname="数量" width="80" align="center"/>
				<EF:EFColumn ename="totalPrice" cname="总价" width="80" align="center"/>
				<EF:EFColumn ename="brand" hidden="true" cname="品牌" width="80" align="center"/>
				<EF:EFColumn ename="supType" cname="供应方" width="150" align="center"/>
				<EF:EFColumn ename="fashion" hidden="true" cname="方式" width="80" align="center"/>
				<EF:EFColumn ename="remark" hidden="true" cname="备注" width="80" align="center"/>
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
	<!-- 材料弹出窗口 -->
	<EF:EFWindow id="stuffChoose" url="" lazyload="true" width="80%" height="80%"></EF:EFWindow>
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
						name: "ADDFILE",text: "上传",layout: "3",
						click: function () { fileChooseWindow.open().center() }
					},{
						name: "DELFILE",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultCGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultCGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的项目附件信息")
							}
						}
					},{
						name: "DOWNLOADFILE",text: "下载",layout: "3",
						click: function () { downLoadFile() }
					}]
				}
			},
			"resultD":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDJSTUFF",text: "新增",layout: "3",
						click: function () { stuffChooseWindowOpen("JSTUFF"); }
					},{
						name: "DELJSTUFF",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultDGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultDGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的甲供材料信息")
							}
						}
					},/* {
						name: "PRINTJSTUFF",text: "打印",layout: "3",
						click: function () { 
							var checkRows = resultDGrid.getCheckedRows();
							if (checkRows.length > 0) {
								
							} else {
								IPLAT.NotificationUtil("请选择需要打印的甲供材料信息")
							}
						}
					} */]
				},
				columns: [{
					field: "totalPrice",
					headerTemplate: "总价",
					template: function (item) {
						if ($.isNumeric(item['num']) && $.isNumeric(item['price'])) {
							// + 能够强制把其他类型转为数字类型
							if(+item['num'] > 0 && +item['price'] > 0) {
								return (+item['num'] * +item['price']) +"";
							} else {
								return '0';
							}
						} else {// 防止 num或price 不存在
							return '0';
						}
					}
				}],
			},
			"resultE":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDYSTUFF",text: "新增",layout: "3",
						click: function () { stuffChooseWindowOpen("YSTUFF"); }
					},{
						name: "DELYSTUFF",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultEGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultEGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的乙方材料信息")
							}
						}
					},/* {
						name: "PRINTYSTUFF",text: "打印",layout: "3",
						click: function () { 
							var checkRows = resultEGrid.getCheckedRows();
							if (checkRows.length > 0) {
								
							} else {
								IPLAT.NotificationUtil("请选择需要打印的乙供材料信息")
							}
						}
					},{
						name: "EXCELTEMPLATE",text: "Excel模板下载",layout: "3",
						click: function () { }
					},{
						name: "EXCELIMPORT",text: "Excel导入",layout: "3",
						click: function () { }
					} */]
				},
				columns: [{
					field: "totalPrice",
					headerTemplate: "总价",
					template: function (item) {
						if ($.isNumeric(item['num']) && $.isNumeric(item['price'])) {
							// + 能够强制把其他类型转为数字类型
							if(+item['num'] > 0 && +item['price'] > 0) {
								return (+item['num'] * +item['price']) +"";
							} else {
								return '0';
							}
						} else {// 防止 num或price 不存在
							return '0';
						}
					}
				}],
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
		$("#EXCUTE").on("click", function() {
			//获取参数
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			//获取tab数据
			var staffArray = resultAGrid.getDataItems();
			var knowArray = resultBGrid.getDataItems();
			var fileArray = resultCGrid.getDataItems();
			var jStuffArray = resultDGrid.getDataItems();
			var yStuffArray = resultEGrid.getDataItems();
			eiInfo.set("staffList", staffArray);
			eiInfo.set("knowList", knowArray);
			eiInfo.set("fileList", fileArray);
			eiInfo.set("jStuffList", jStuffArray);
			eiInfo.set("yStuffList", yStuffArray);
			//参数校验
			if(!validatePR(eiInfo)){ return; }
			//提交
			EiCommunicator.send("PM0201", "excuteProject", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.query(1);
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
	
	//材料选择窗口
	function stuffChooseWindowOpen(type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/PM0202?methodName=query&inqu_status-0-stuffType="+type;
		var popData = $("#stuffChoose");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开弹窗
		stuffChooseWindow.open().center();
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
			} else if ("KNOW" == personType) {
				model["notifyStaffId"] = model.workNo;
				model["notifyStaffName"] = model.name;
				//resultBGrid.addRows(model)
				clearRepeat(model,resultBGrid,"notifyStaffId");
			} else if ("JSTUFF" == personType){
				//resultDGrid.addRows(model)
				clearRepeat(model,resultDGrid,"matNum");
			} else {
				//resultEGrid.addRows(model)
				clearRepeat(model,resultEGrid,"matNum");
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
		if(eiInfo.get("inqu_status-0-finishDeadline").replace("-","") < eiInfo.get("inqu_status-0-startDate").replace("-","")){
			IPLAT.NotificationUtil("完成期限不能小于开始时间", "failure");
			return false;
		}
		 var startTime=kendo.toString($("#inqu_status-0-startDate").data("kendoDatePicker").value(), "yyyy-MM-dd");
		var endTime=kendo.toString($("#inqu_status-0-finishDeadline").data("kendoDatePicker").value(), "yyyy-MM-dd");
		//校验时间
		if (startTime == null || startTime == "") {
			NotificationUtil("请输入开始时间", "error");
			return false;
		}
		if (endTime == null || endTime == "") {
			NotificationUtil("请输入完成时间", "error");
			return false;
		}
		//甲供材料校验数量和价格
		var regst= /^\d*\.?\d+$/;
		if(eiInfo.get("jStuffList")!=null){
			for(var i=0;i<eiInfo.get("jStuffList").length;i++){
				if(!regst.test(eiInfo.get("jStuffList")[i].num)){
					NotificationUtil("请重新输入数量为正数", "error");
					return false;
					}
				if(!regst.test(eiInfo.get("jStuffList")[i].price)){
					NotificationUtil("请重新输入价格为正数", "error");
					return false;
					}
				}
			}
		//乙方清单校验数量和价格
		var regst= /^\d*\.?\d+$/;
		if(eiInfo.get("yStuffList")!=null){
			for(var i=0;i<eiInfo.get("yStuffList").length;i++){
				if(!regst.test(eiInfo.get("yStuffList")[i].num)){
					NotificationUtil("请重新输入数量为正数", "error");
					return false;
					}
				if(!regst.test(eiInfo.get("yStuffList")[i].price)){
					NotificationUtil("请重新输入价格为正数", "error");
					return false;
					}
				}
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

