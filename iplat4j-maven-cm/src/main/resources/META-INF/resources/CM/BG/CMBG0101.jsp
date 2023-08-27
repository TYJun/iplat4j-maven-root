<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
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
			<%-- <EF:EFPopupInput ename="inqu_status-0-surpNum" cname="供应商"
				popupTitle="供应商选择" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="querySupplier" resultId="supplier" readonly="true"
				valueField="surpNum" textField="surpName" colWidth="5"
				ratio="4:8" columnEnames="surpNum,surpName"
				columnCnames="供应商编码,供应商名称" /> --%>
			<EF:EFInput ename="inqu_status-0-surpNum" cname="供应商" colWidth="5"
				ratio="4:8" type="text" />
		</div>
		<div class="row">		
			<EF:EFSelect ename="inqu_status-0-currType" cname="币种" colWidth="5"
				ratio="4:8">
				<EF:EFOption label="人民币" value="rmb" />
			</EF:EFSelect>
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
			<EF:EFSelect ename="inqu_status-0-yesorno" cname="是否发送短信"
				colWidth="5" ratio="4:8">
				<EF:EFOption label="否" value="0" />
				<EF:EFOption label="是" value="1" />
			</EF:EFSelect>
		</div>
<%-- 		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div> --%>
	</EF:EFRegion>

	<!-- 页面分页 -->
	<EF:EFTab id="tab-tab_grid" active="0">
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
			<div class="col-md-8">
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
						<%--<EF:EFColumn ename="reallyPaymentTime" cname="实际付款时间" width="100" align="center" editType="date"
                                     displayType="date" dateFormat="yyyy-MM-dd"/>--%>
					</EF:EFGrid>
				</EF:EFRegion>
			</div>
			<div class="col-md-4">
				<EF:EFRegion id="result" title="合同付款记录">
					<EF:EFGrid blockId="resultC" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
							   checkMode="single,row" readonly="true" rowNo="true" isFloat="true"
							   queryMethod="queryCost">
						<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
						<EF:EFColumn ename="remark" cname="付款内容" width="100" align="center" enable="false"/>
						<EF:EFColumn ename="paymentTaxExcludeAmount" cname="付款金额(元)" width="100" align="center" enable="false"  format="{0:0.00}"/>
						<EF:EFColumn ename="paymentTime" cname="付款日期" width="100" align="center" enable="false"/>
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
	</EF:EFTab>
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
		}
	}
	
	/* 分页处理 */
	$(function() {
		var validator = IPLAT.Validator({
			id : "result"
		});
		//开启校验
		IPLATUI.EFGrid = {
			"resultA" : {
				toolbarConfig : {
					hidden : true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add : false,
					cancel : false,
					save : false,
					'delete' : false,
					buttons : [
						{
							name : "ADDSTAFF",
							text : "新增",
							layout : "3",
							click : function() {
								clauseChooseWindowOpen();
							}
						},
						{
							name : "DELSTAFF",
							text : "删除",
							layout : "3",
							click : function() {
								//选中的行信息
								var checkRows = resultAGrid.getCheckedRows();
								if (checkRows.length > 0) {
									resultAGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择需要删除的合同名称")
								}
							}
						}
					]
				},
			},
			"resultB":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[
						{
							name: "ADDCED",text: "新增",layout:"3",
							click: function () {
								resultBGrid.addRow()
							}
						},
						{
							name: "DELCED",text: "删除",layout: "3",
							click: function () { 
								var checkRows = resultBGrid.getCheckedRows();
								if (checkRows.length > 0) {
									resultBGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择需要删除的付款内容")
								}
							}
						}
					]
				},
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
			"resultC":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[
						{
							name: "ADDCED",text: "新增",layout:"3",
							click: function () {
								resultCGrid.addRow()
							}
						},
						{
							name: "DELCED",text: "删除",layout: "3",
							click: function () { 
								var checkRows = resultCGrid.getCheckedRows();
								if (checkRows.length > 0) {
									resultCGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择需要删除的费用内容")
								}
							}
						}
					]
				}, 
			},
			"resultD" : {
				toolbarConfig : {
					hidden : true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add : false,
					cancel : false,
					save : false,
					'delete' : false,
					buttons : [
						{
							name : "addFile",
							text : "上传",
							layout : "3",
							click : function() {
								fileChooseWindow.open().center()
							}
						},
						{
							name : "delFile",
							text : "删除",
							layout : "3",
							click : function() {
								var checkRows = resultDGrid.getCheckedRows();
								if (checkRows.length > 0) {
									resultDGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择需要删除的合同附件信息")
								}
							}
						},
						{
							name : "downLoadFile",
							text : "下载",
							layout : "3",
							click : function() {
								downLoadFile()
							}
						}
					]
				}
			},
			"resultE" : {
				toolbarConfig : {
					hidden : true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add : false,
					cancel : false,
					save : false,
					'delete' : false,
					buttons : [
						{
							name : "ADDMESSAGE",
							text : "新增",
							layout : "3",
							click : function() {
								personChooseWindowOpen();
							}
						},
						{
							name : "DELMESSAGE",
							text : "删除",
							layout : "3",
							click : function() {
								var checkRows = resultEGrid.getCheckedRows();
								console.log(checkRows);
								if (checkRows.length > 0) {
									resultEGrid.removeRows(checkRows);
								} else {
									IPLAT.NotificationUtil("请选择删除的联系人")
								}
							}
						}
					]
				},
			},
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
	
		//弹窗保存
		$("#SAVEPR").on("click", function() {
			// 开启校验
			var validator = IPLAT.Validator({
				id: "result"
			});
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			var contSignTime = eiInfo.get("inqu_status-0-contSignTime");
			var planTakeefTime = eiInfo.get("inqu_status-0-planTakeefTime");
			var planFinishTime = eiInfo.get("inqu_status-0-planFinishTime");
			if (contSignTime > planFinishTime){
				IPLAT.NotificationUtil("合同签订时间不能大于计划终止时间","warning");
				return false;
			}
			if (planTakeefTime > planFinishTime){
				IPLAT.NotificationUtil("计划生效时间不能大于计划终止时间","warning");
				return false;
			}
			if (validator.validate()) {
				//获取参数
				var eiInfo = new EiInfo();
				eiInfo.setByNode("result");
				//获取tab数据
				var hta=resultAGrid.getDataItems();
				var htb=resultBGrid.getDataItems();
				var htc=resultCGrid.getDataItems();
				var file=resultDGrid.getDataItems();
				var hte=resultEGrid.getDataItems();
				eiInfo.set("hta",hta)
			    eiInfo.set("htb",htb)
				eiInfo.set("htc",htc)
				eiInfo.set("hte",hte)
				eiInfo.set("file",file)
				//提交
				EiCommunicator.send("CMDJ0101", "saveContent", eiInfo, {
					onSuccess : function(ei) {
						closeCurrentWindow();
						IPLAT.NotificationUtil(ei.msg)
						window.parent["resultGrid"].dataSource.page(1);
					}
				})
			}
		});
	
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow();
		});
	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['popDataWindow'].close();
	}

	/* 合同条款内容弹窗 */
	function clauseChooseWindowOpen() {
		var url = IPLATUI.CONTEXT_PATH + "/web/CMDJ0102?initLoad";
		var popData = $("#clauseChoose");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开弹窗
		clauseChooseWindow.open().center();
	}
	
	/* 选择合同联系人弹窗 */
	function personChooseWindowOpen() {
		var url = IPLATUI.CONTEXT_PATH + "/web/CMDJ0103?initLoad";
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
</script>