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
			<EF:EFDatePicker ename="inqu_status-0-startDate" cname="开始日期" />
			<EF:EFDatePicker ename="inqu_status-0-finishDeadline" cname="完成期限" />
		</div>
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-supplierNum" cname="供应商" popupTitle="供应商选择" readOnly="true"
				popupType="ServiceGrid" serviceName="PM0103" methodName="querySupplier" resultId="supplier"
				valueField="supplierNum" textField="supplierName"
				columnEnames="supplierNum,supplierName" columnCnames="供应商编码,供应商名称" />
			<EF:EFInput ename="inqu_status-0-winbidExpense" cname="中标费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
			<EF:EFInput ename="inqu_status-0-finishExpense" cname="结算费用" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入数字" />
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-projectContent" cname="项目内容" type="textarea"/>
			<EF:EFInput ename="inqu_status-0-projectRemark" cname="备注" type="textarea" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="VIASAVE" layout="0" ></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_via">
 		<div title="督办信息">
			<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="false"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryVia">
				<EF:EFColumn ename="id" cname="id" hidden="true" width="50" />
				<EF:EFColumn ename="projectPk" cname="projectPk" hidden="true" width="50" />
				<EF:EFPopupColumn ename="superviseMaker" cname="督办人工号" popupTitle="人员选择" popupWidth="350"
					popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" width="50"
					resultId="person" valueField="workNo" textField="workNo" 
					columnEnames="workNo,name" columnCnames="工号,姓名"
 					backFillFieldIds="superviseMaker,superviseMakerNo,superviseMakerName" backFillColumnIds="workNo,workNo,name"
 					resizable="true" enable="false"/>
 				<EF:EFColumn ename="superviseMakerNo" cname="督办人工号" hidden="true" width="50" enable="false"/>
 				<EF:EFColumn ename="superviseMakerName" cname="督办人姓名" width="50" enable="false"/>
				<EF:EFColumn ename="superviseInfo" cname="督办信息" width="100" />
				<EF:EFColumn ename="startTime" cname="开始日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="50" />
				<EF:EFColumn ename="endTime" cname="结束日期" editType="date" dateFormat="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" width="50" />
			</EF:EFGrid> 
		</div>
	</EF:EFTab>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		var validator = IPLAT.Validator({id: "result"});//开启校验
		//表格按钮处理
		IPLATUI.EFGrid = {
			"resultA":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "ADDVIA",text: "新增",layout:"3",
						click: function () { resultAGrid.addRow()}
					},{
						name: "DELVIA",text: "删除",layout: "3",
						click: function () { 
							var checkRows = resultAGrid.getCheckedRows();
							if (checkRows.length > 0) {
								resultAGrid.removeRows(checkRows);
							} else {
								IPLAT.NotificationUtil("请选择需要删除的督办信息")
							}
						}
					}]
				},
				/* columns: [{
					field: "superviseMaker",
					headerTemplate: "督办人工号",
					editor: function (container, options) {
						$("input").attr("disabled", false);
				}
				}],  */
			}
		}
		
		//使用 grid 的 refresh 方法来解决渲染异常
		IPLATUI.EFTab = {
			"tab-tab_via": {
				show: function (e) {
					$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh()
				}
			}
		}
		
		//保存
		$("#VIASAVE").on("click", function() {
			debugger;
			//获取参数
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			//获取tab数据
			var viaArray = resultAGrid.getDataItems();
		    if(viaArray.length>0){
				 for(var i=0;i<viaArray.length;i++){
					  if(viaArray[i].startTime>viaArray[i].endTime){
						NotificationUtil("督办开始时间不能大于结束时间", "error");
						return false;
						}
						if(viaArray[i].superviseMakerName!=null){
							if(viaArray[i].superviseMaker!=viaArray[i].superviseMakerNo){
								NotificationUtil("请选择督办人工号,勿手填", "error");
								return false
								}
							} 
					}  
				}
			eiInfo.set("viaList",viaArray);
			//参数校验
			if(!validatePR(eiInfo)){ return; }
			//提交
			EiCommunicator.send("PM0301", "viaProject", eiInfo, {
				onSuccess : function(ei) {
				  if(ei.getStatus()==-10){
						NotificationUtil(ei.getMsg(), "error");
						return;
				  }else{
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.query(1);
				  }
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
		window.parent['viaWindow'].close();
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
		 if(eiInfo.get("inqu_status-0-startDate")>eiInfo.get("inqu_status-0-finishDeadline")){
			NotificationUtil("开始时间不能大于完成时间");
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

