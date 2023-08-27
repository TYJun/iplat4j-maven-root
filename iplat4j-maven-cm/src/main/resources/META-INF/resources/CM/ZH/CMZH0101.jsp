<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-contNo" cname="合同号" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
		</div>
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-contTermName" cname="合同生成方式" colWidth="5" ratio="4:8">
				<EF:EFOption label="手工生成" value="" />
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-contName" cname="合同名称" colWidth="5"
				ratio="4:8" type="text" required="true" />
		</div>
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-contTypeNum" cname="合同类型"
				colWidth="5" ratio="4:8" resultId="contTypeName"
				textField="contTypeName" valueField="contTypeNum"
				serviceName="CMDJ0101" methodName="getContTypeNum" optionLabel="请选择"
				filter="contains">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-payAgreNum" cname="付款协议"
				colWidth="5" ratio="4:8" resultId="payTypeName"
				textField="payTypeName" valueField="payTypeNum"
				serviceName="CMDJ0101" methodName="payAgreNum" optionLabel="请选择"
				filter="contains">
			</EF:EFSelect>
			
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-contSignTime" cname="合同签订日期"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
				bindId="contSignTime" colWidth="5" ratio="4:8" />
			<EF:EFDatePicker ename="inqu_status-0-planTakeefTime" cname="合同生效日期"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
				bindId="planTakeefTime" colWidth="5" ratio="4:8" />
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-planFinishTime" cname="合同终止日期"
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
				bindId="planFinishTime" colWidth="5" ratio="4:8" />
			<EF:EFPopupInput ename="inqu_status-0-surpNum" cname="供应商"
				popupTitle="供应商选择" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="querySupplier" resultId="supplier" readonly="true"
				valueField="surpNum" textField="surpName" colWidth="5"
				ratio="4:8" columnEnames="surpNum,surpName"
				columnCnames="供应商编码,供应商名称" />
			<%-- <EF:EFInput ename="inqu_status-0-surpNum" cname="供应商" colWidth="5"
				ratio="4:8" type="text" /> --%>
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-schedule" cname="合同进度计划" colWidth="5" ratio="4:8"
				center="true" readOnly="true" valueField="scheduleAutoNo" textField="scheduleName"
				containerId="ef_popup_scheduleAutoNo" popupWidth="1050" popupHeight="500"
				popupTitle="合同进度计划选择">
			</EF:EFPopupInput>
			<%--<EF:EFSelect ename="inqu_status-0-currType" cname="币种" colWidth="5"
				ratio="4:8">
				<EF:EFOption label="人民币" value="rmb" />
			</EF:EFSelect>--%>
			<EF:EFInput ename="inqu_status-0-quarPeriod" cname="质保期(月)"
				data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" 
				colWidth="5" ratio="4:8" type="text" />
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-contAdmin" cname="合同管理员" readonly="true"
				popupTitle="员工列表" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="queryAdmin" resultId="contAdmin" valueField="workNo"
				textField="name" colWidth="5" ratio="4:8" required="true"
				columnEnames="workNo,name" columnCnames="员工工号,员工姓名" />
			<EF:EFPopupInput ename="inqu_status-0-contDeptNum" cname="合同所属部门"
				popupTitle="合同所属部门" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="queryContCostNum" resultId="contDept" readonly="true"
				valueField="contDeptNum" textField="contDeptName" colWidth="5"
				ratio="4:8" columnEnames="contDeptNum,contDeptName"
				columnCnames="科室编码,科室名称" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-budget" cname="预算金额(元)" format="{0:0.00}"
				data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" colWidth="5"
				ratio="4:8" />
			<EF:EFInput ename="inqu_status-0-contFeeAmount" cname="合同总金额(元)" format="{0:0.00}"
				data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" colWidth="5"
				ratio="4:8" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-checkMoney" cname="审计后金额(元)" format="{0:0.00}"
				data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" colWidth="5"
				ratio="4:8" />
			<%-- <EF:EFSelect ename="inqu_status-0-yesorno" cname="是否发送短信"
				colWidth="5" ratio="4:8">
				<EF:EFOption label="否" value="0" />
				<EF:EFOption label="是" value="1" />
			</EF:EFSelect> --%>
			<EF:EFPopupInput ename="inqu_status-0-Pm" cname="工程项目"
							 popupTitle="工程项目" popupType="ServiceGrid" serviceName="CMDJ0101"
							 methodName="queryProject" resultId="Pm" readonly="true"
							 valueField="projectNo" textField="projectName" colWidth="5"
							 ratio="4:8" columnEnames="projectNo,projectName"
							 columnCnames="项目编码,项目名称" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<%-- 合同进度计划选择弹框 --%>
	<div id="ef_popup_scheduleAutoNo" style="display: none">
		<EF:EFRegion id="inquSchedule" title="查询条件">
			<div class="row">
				<EF:EFInput ename="inqu_status-0-scheduleAutoNo" cname="合同进度编码"/>
				<EF:EFInput ename="inqu_status-0-scheduleName" cname="合同进度名称"/>
			</div>
			<div div class="button-region" id="buttonDiv">
				<EF:EFButton ename="scheduleQuery" cname="查询"></EF:EFButton>
				<EF:EFButton ename="scheduleReset" cname="重置"></EF:EFButton>
				<EF:EFButton ename="scheduleSave" cname="确定" layout="20"></EF:EFButton>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="resultSchedule" title="结果集">
			<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
					   autoFit="true" checkMode="single,row" readonly="true" rowNo="true"
					   isFloat="true" serviceName="CMJD02" queryMethod="query">
				<EF:EFColumn ename="scheduleAutoNo" cname="合同进度编码" width="12" align="center" enable="false"/>
				<EF:EFColumn ename="scheduleName" cname="合同进度名称" width="13" align="center" enable="false"/>
				<EF:EFColumn ename="scheduleRemark" cname="合同进度备注" width="13" align="center" enable="false"/>
				<EF:EFColumn ename="nodeName" cname="合同节点名称" width="30" align="center" enable="false"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>

	<!-- 页面分页 -->
	<EF:EFTab id="tab-tab_grid" active="0">
		<div title="合同进度计划">
			<EF:EFGrid blockId="resultH" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" serviceName="CMDJ0101" queryMethod="queryNode">
				<EF:EFColumn ename="nodeAutoNo" cname="合同节点编码" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="nodeName" cname="合同节点名称" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="nodeRemark" cname="合同节点备注" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="nodeTime" cname="合同节点时间" width="100" align="center" editType="date" displayType="date"/>
				<EF:EFColumn ename="remark" cname="备注" width="100" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="合同条款内容">
			<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryCont">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="contTermName" cname="合同条款名称" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="remark" cname="合同条款内容" width="100" enable="false" align="center"/>
				<%-- <EF:EFColumn ename="remark" cname="备注" width="100" enable="false" align="center"/> --%>
			</EF:EFGrid>
		</div>
		<div title="合同付款计划">
			<div class="col-md-7">
				<EF:EFRegion id="result" title="合同付款计划">
					<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
							   checkMode="single,row" readonly="true" rowNo="true" isFloat="true"
							   queryMethod="queryPay">
						<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
						<EF:EFColumn ename="lastTime" cname="付款周期(月)" width="100" data-regex="/^[1-9]\d*|0$/"
									 data-errorprompt="请输入正确的月份" align="center"/>
						<EF:EFColumn ename="payRate" cname="付款比例(%)" width="100" data-regex="/^(?:0|[1-9][0-9]?|100)$/"
									 data-errorprompt="请输入0-100的数字" align="center" format="{0:n0}%"/>
						<EF:EFColumn ename="payAmount" cname="付款金额(元)" width="100" data-regex="/^[1-9]\d*|0$/"
									 data-errorprompt="请输入数字" align="center" format="{0:0.00}"/>
						<EF:EFColumn ename="remark" cname="备注" width="100" align="center"/>
						<EF:EFColumn ename="planPaymentTime" cname="预计付款时间" width="100" align="center" editType="date"
									 displayType="date" dateFormat="yyyy-MM-dd"/>
					</EF:EFGrid>
				</EF:EFRegion>
			</div>
			<div class="col-md-5">
				<EF:EFRegion id="result" title="合同付款记录">
					<EF:EFGrid blockId="resultC" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
							   checkMode="single,row" readonly="true" rowNo="true" isFloat="true"
							   queryMethod="queryCost">
						<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
						<EF:EFColumn ename="remark" cname="付款内容" width="100" align="center" enable="false"/>
						<EF:EFColumn ename="paymentTaxExcludeAmount" cname="付款金额(元)" width="100" align="center" enable="false" format="{0:0.00}"/>
						<EF:EFColumn ename="paymentTime" cname="付款日期" width="100" align="center" enable="false"/>
						<EF:EFColumn ename="paymentStatus" cname="付款状态" width="100" align="center" enable="false"/>
					</EF:EFGrid>
				</EF:EFRegion>
			</div>
		</div>
		<div title="合同记事内容">
			<EF:EFGrid blockId="resultD" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="contPk" cname="contPk" width="100" hidden="true" />
				<EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true"/>
				<EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true"/>
				<EF:EFColumn ename="attachName" cname="附件名称" width="200" />
				<EF:EFColumn ename="attachSize" cname="附件大小" width="100" />
				<EF:EFColumn ename="attachDesc" cname="附件说明" width="200" />
				<EF:EFColumn ename="recCreator" cname="上传人" width="200" />
				<EF:EFColumn ename="recCreateTime" cname="上传时间" width="200" />
			</EF:EFGrid>
		</div>
		<div title="文件删除记录">
			<EF:EFGrid blockId="resultF" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryDeleteFile">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="contPk" cname="contPk" width="100" hidden="true" />
				<EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true"/>
				<EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true"/>
				<EF:EFColumn ename="attachName" cname="附件名称" width="200" />
				<EF:EFColumn ename="attachSize" cname="附件大小" width="100" />
				<EF:EFColumn ename="attachDesc" cname="附件说明" width="200" />
				<EF:EFColumn ename="recRevisor" cname="删除人" width="200" />
				<EF:EFColumn ename="recReviseTime" cname="删除时间" width="200" />
			</EF:EFGrid>
		</div>
		<div title="合同联系人">
			<EF:EFGrid blockId="resultE" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryPerson">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="workNo" cname="工号" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="name" cname="短信人" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="contactTel" cname="号码" width="100" enable="false" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="关联工程项目附件">
			<EF:EFGrid blockId="resultG" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFileByProjectId">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="contPk" width="100" hidden="true" />
				<EF:EFColumn ename="attachId" cname="附件id" width="200" hidden="true"/>
				<EF:EFColumn ename="attachPath" cname="附件路径" width="200" hidden="true"/>
				<EF:EFColumn ename="attachName" cname="附件名称" width="200" />
				<EF:EFColumn ename="attachSize" cname="附件大小" width="100" />
				<EF:EFColumn ename="attachDesc" cname="附件说明" width="200" />
				<EF:EFColumn ename="recCreator" cname="上传人" width="200" />
				<EF:EFColumn ename="recCreateTime" cname="上传时间" width="200" />
			</EF:EFGrid>
		</div>

	</EF:EFTab>
	<EF:EFWindow id="invoice" url="" lazyload="true" width="90%" height="90%" title="发票"/>
	<EF:EFWindow id="payment" url="" lazyload="true" width="90%" height="90%" title="付款"/>
	<!-- 人员选择弹出窗口 -->
	<EF:EFWindow id="personChoose" url="" lazyload="true" width="60%" height="90%" title="人员选择"/>
	<!-- 合同条款弹窗 -->
	<EF:EFWindow id="clauseChoose" url="" lazyload="true" width="60%" height="90%" title="合同条款选择"/>
	<!-- 附件上传窗口 -->
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="contentFile" docTag="co_file" path="Content" />
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>

<script type="text/javascript">
	IPLATUI.EFSelect = {
		"inqu_status-0-payAgreNum": {
			change: function (e) {
				resultBGrid.dataSource.page(1);
				resultCGrid.dataSource.page(1);
			}
		}
	};
	/* 附件上传 */
	IPLATUI.EFUpload = {
		"contentFile" : {
			loadComplete : function(e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles();
			},
			validation : {
				allowedExtensions : [ ".jpg", "jpeg", ".png", ".gif", ".bmp",
						".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt",
						".pptx", ".pdf", ".zip", "rar", ".7z" ]
			},
			localization : {
				invalidFileExtension : "文件格式不支持, 上传失败"
			},
			success : function(e) {
				var file = e.files[0];
				var model = createModel(1);
				model["id"] = "";
				model["contPk"]="";
				model["attachId"] = e.response.docId;
				model["attachPath"] = e.response.docUrl;
				model["attachName"] = file.name;
				model["attachSize"] = file.size;
				model["attachDesc"] = "";
				resultDGrid.addRows(model);
				fileChooseWindow.close();
			},
		}
	} 

	/* 合同管理员自动带出科室 */
	IPLATUI.EFPopupInput = {
		"inqu_status-0-contAdmin":{
			backFill: function (e) {
				IPLAT.EFPopupInput.value($("#inqu_status-0-contDeptNum"),e.model.deptNum);
				IPLAT.EFPopupInput.text($("#inqu_status-0-contDeptNum"),e.model.deptName);
			}
		},
		/*工程项目自动带出附件*/
		"inqu_status-0-Pm":{
			backFill: function (e) {
				var info = new EiInfo();
				var gridResultG = $("#ef_grid_resultG").data("kendoGrid");
				gridResultG.removeRows(gridResultG.getDataItems());
				$("#inqu_status-0-projectId").val(e.model.pid);
				info.set("id",e.model.pid);
				EiCommunicator.send("CMDJ0101", "getProjectFile", info, {
					onSuccess : function(ei) {
						NotificationUtil(ei.getMsg(), "success");
						var param = ei.extAttr.param;

						for (var i = 0; i < param.length; i++) {

							var model = createModelProjectFile(i);
							var par = param[i];

							var id = par.id;
							var projectPk = par.projectPk;
							var attachId = par.attachId;
							var attachPath = par.attachPath;
							var attachName = par.attachName;
							var attachSize = par.attachSize;
							var attachDesc = par.attachDesc;
							var recCreator = par.recCreator;
							var recCreateTime = par.recCreateTime;

							//debugger;
							model["id"] = "id";
							model["projectPk"]="projectPk";
							model["attachId"] = attachId;
							model["attachPath"] = attachPath;
							model["attachName"] = attachName;
							model["attachSize"] = attachSize;
							model["attachDesc"] = attachDesc;
							model["recCreator"] = recCreator;
							model["recCreateTime"] = recCreateTime;
							gridResultG.addRows(model);
						}
					}
				});
			},
		}
	}
	
	/* 分页处理 */
	$(function() {
		// 付款
		var buttonFK = [];
		var buttonList=[];
		if($("#inqu_status-0-type").val()=="add"){
			var addFile={
				name : "addFile",
				text : "上传",
				layout : "3",
				click : function() {
					fileChooseWindow.open().center()
				}
			}
			buttonList.push(addFile);
			var deleteFile={
				name: "delFile",
				text: "删除",
				layout: "3",
				click: function () {
					var checkRows = resultDGrid.getCheckedRows();
					if (checkRows.length > 0) {
						resultDGrid.removeRows(checkRows);
						fileList.push(checkRows[0]);
						console.log(fileList);
					} else {
						IPLAT.NotificationUtil("请选择需要删除的合同附件信息")
					}
				}
			}
			buttonList.push(deleteFile);
			var loadFile={
						name : "downLoadFile",
						text : "下载",
						layout : "3",
						click : function() {
							downLoadFile()
						}
			}
			buttonList.push(loadFile);
		}else if($("#inqu_status-0-type").val()=="edit"){
			var addFile={
				name : "addFile",
				text : "上传",
				layout : "3",
				click : function() {
					fileChooseWindow.open().center()
				}
			}
			buttonList.push(addFile);
			var deleteFile={
				name: "delFile",
				text: "删除",
				layout: "3",
				click: function () {
					var checkRows = resultDGrid.getCheckedRows();
					if (checkRows.length > 0) {
						resultDGrid.removeRows(checkRows);
						fileList.push(checkRows[0]);
						console.log(fileList);
					} else {
						IPLAT.NotificationUtil("请选择需要删除的合同附件信息")
					}
				}
			}
			buttonList.push(deleteFile);
			var loadFile={
				name : "downLoadFile",
				text : "下载",
				layout : "3",
				click : function() {
					downLoadFile()
				}
			}
			buttonList.push(loadFile);
			var lookFile={
				name: "lookFile",
				text: "预览",
				layout: "3",
				click: function () {
					var checkRows = resultDGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var docPath = checkRows[0].attachPath;
						var last = docPath.lastIndexOf("/");
						var first= docPath.indexOf("/");
						var fileName = docPath.substring(last + 1, docPath.length);
						var filePath = docPath.substring(0, first);
						if (docPath.indexOf(".pdf") >= 0) {
							window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName+"&filePath="+filePath);
						}else{
							IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
							return;
						}
					} else {
						IPLAT.NotificationUtil("请选择需要预览的合同附件信息")
					}
				}
			}
			buttonList.push(lookFile);
		}else if($("#inqu_status-0-type").val()=="see"){
			var invoiceButton = {
				name: "invoiceButton",
				text: "发票",
				layout: "3",
				click: function () {
					invoiceWindowOpen();
				}
			}
			buttonFK.push(invoiceButton)
			var paymentButton = {
				name: "paymentButton",
				text: "付款",
				layout: "4",
				click: function () {
					paymentWindowOpen();
				}
			}
			buttonFK.push(paymentButton)
			var loadFile={
				name : "downLoadFile",
				text : "下载",
				layout : "3",
				click : function() {
					downLoadFile()
				}
			}
			buttonList.push(loadFile);
			var lookFile={
				name: "lookFile",
				text: "预览",
				layout: "3",
				click: function () {
					var checkRows = resultDGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var docPath = checkRows[0].attachPath;
						var last = docPath.lastIndexOf("/");
						var first= docPath.indexOf("/");
						var fileName = docPath.substring(last + 1, docPath.length);
						var filePath = docPath.substring(0, first);
						if (docPath.indexOf(".pdf") >= 0) {
							window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName+"&filePath="+filePath);
						}else{
							IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
							return;
						}
					} else {
						IPLAT.NotificationUtil("请选择需要预览的合同附件信息")
					}
				}
			}
			buttonList.push(lookFile);
		}

		var validator = IPLAT.Validator({
			id : "result"
		});
		var fileList=[];
		//开启校验
		IPLATUI.EFGrid = {
			"resultB" : {
				dataBinding: function (e) {
					for (var i = 0, length = e.items.length; i < length; i++){
						if(isAvailable(e.items[i].payAmount)){
							e.items[i].payAmount = parseFloat(e.items[i].payAmount);
						}
						if(isAvailable(e.items[i].payRate)){
							e.items[i].payRate = parseFloat(e.items[i].payRate);
						}
					}
				},
			},
			"resultC" : {
				//pageable : false,
				toolbarConfig: {
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,
					cancel: false,
					save: false,
					'delete': false,
					buttons: buttonFK,
				},
				dataBinding: function (e) {
					for (var i = 0, length = e.items.length; i < length; i++){
						if(isAvailable(e.items[i].paymentTaxExcludeAmount)){
							e.items[i].paymentTaxExcludeAmount = parseFloat(e.items[i].paymentTaxExcludeAmount);
						}
					}
				},
			},
			"resultD" : {
				//pageable : false,
				toolbarConfig : {
					hidden : false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add : false,
					cancel : false,
					save : false,
					'delete' : false,
					buttons : buttonList
				}
			}
		}
		/* 渲染分页 */
		IPLATUI.EFTab = {
			"tab-tab_grid" : {
				select : function(e) {
					var grid = $(e.contentElement).find("div[data-role='grid']").data("kendoGrid")
					if (grid.getDataItems().length === 0) {
						grid.dataSource.page(1);
					}
				}
			}
		}

	
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow();
		});

	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['popDataWindow'].close();
	}

	//文件下载
	function downLoadFile(){
		var checkRows = resultDGrid.getCheckedRows();
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
	
	//子参数
	function addRows(checkRows,type){
		for (var index in checkRows) {
			var model = checkRows[index];
			if(type=="cont"){
				model["contTermName"] = model.contTermName;
				model["cotent"] = model.contTermContent;
				model["remark"] = model.remark;
				//resultAGrid.addRows(model)
				clearRepeat(model,resultAGrid,"contTermName");
			}else{
				 model["workNo"] = model.workNo;
				 model["name"] = model.name;
				 model["phone"] = model.number; 
				 //resultEGrid.addRows(model)
				 clearRepeat(model,resultEGrid,"workNo");
			}
		}
	} 
 
	function clearRepeat(model,grid,compareField){
		var list = grid.getDataItems();
		var isExist = false;
		for(var i in list) {
			var row = list[i];
			if(row[compareField] == model[compareField]){
				isExist = true;
			} 
		}
		if(!isExist){
			grid.addRows(model)
		}
	}
 
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "id": {
		        	type:"string"
		        },
		        "contPk": {
		        	type: "string"
		        },
		        "attachId": {
		        	type: "string"
		        },
		        "attachPath": {
		        	type: "string"
		        },
		        "attachName": {
		        	type: "string"
		        },
		        "attachSize": {
		        	type: "number"
		        },
		        "attachDesc": {
		        	type: "string"
		        }
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}

	//创建kendo.data.Model
	function createModelProjectFile(id){
		var gridRow = kendo.data.Model.define({
			id: "uploadId",
			fields: {
				"id": {
					type:"string"
				},
				"projectPk": {
					type: "string"
				},
				"attachId": {
					type: "string"
				},
				"attachPath": {
					type: "string"
				},
				"attachName": {
					type: "string"
				},
				"attachSize": {
					type: "number"
				},
				"attachDesc": {
					type: "string"
				},
				"recCreator": {
					type: "string"
				},
				"recCreateTime": {
					type: "string"
				}
			}
		});
		var model = new gridRow({uploadId:id});
		return model;
	}

	//参数校验
	function validatePR(eiInfo) {
		if (isEmpty(eiInfo.get("inqu_status-0-contName"))) {
			IPLAT.NotificationUtil("合同名称不能为空");
			return false;
		}
		if (isEmpty(eiInfo.get("inqu_status-0-planTakeefTime"))) {
			IPLAT.NotificationUtil("合同生效日期不能为空");
			return false;
		}
		if (isEmpty(eiInfo.get("inqu_status-0-contAdmin"))) {
			IPLAT.NotificationUtil("合同管理员不能为空");
			return false;
		}
		return true;
	}

	/* 发票 */
	function invoiceWindowOpen() {
		var url = IPLATUI.CONTEXT_PATH + "/web/CMFP0101?initLoad&type=new";
		var popData = $("#invoice");
		popData.data("kendoWindow").setOptions({
			open: function () {
				popData.data("kendoWindow").refresh({
					url: url,
				});
			}
		});
		// 打开弹窗
		invoiceWindow.open().center();
	}

	/* 付款 */
	function paymentWindowOpen() {
		var url = IPLATUI.CONTEXT_PATH + "/web/CMFK0101?initLoad&type=new";
		var popData = $("#payment");
		popData.data("kendoWindow").setOptions({
			open: function () {
				popData.data("kendoWindow").refresh({
					url: url,
				});
			}
		});
		// 打开弹窗
		paymentWindow.open().center();
	}
</script>