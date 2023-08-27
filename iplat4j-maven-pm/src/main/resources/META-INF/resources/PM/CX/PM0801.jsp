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
				popupTitle="人员选择" required="true" 
				popupType="ServiceGrid" serviceName="PM0103" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名,科室名称" />
			<EF:EFPopupInput ename="inqu_status-0-undertakeDeptNum" cname="承办科室" 
				popupTitle="科室选择" required="false" 
				popupType="ServiceGrid" serviceName="PM0101" methodName="queryDept" resultId="dept"
				valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
			<EF:EFInput ename="inqu_status-0-projectObjectDeptName" cname="项目科室" readOnly="true"/>
			<EF:EFInput ename="inqu_status-0-projectObjectDeptNum" cname="项目科室" type="hidden"/>
			<EF:EFPopupInput ename="inqu_status-0-projectObjectCons" cname="项目联络人" 
				popupTitle="人员选择" required="false" 
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
				valueField="supplierNum" textField="supplierName"
				columnEnames="supplierNum,supplierName" columnCnames="供应商编码,供应商名称" />
			<EF:EFInput ename="inqu_status-0-winbidExpense" cname="中标费用" />
			<EF:EFInput ename="inqu_status-0-finishExpense" cname="结算费用" />
			<EF:EFInput ename="inqu_status-0-projectContent" cname="项目内容" type="textarea"/>
			<EF:EFInput ename="inqu_status-0-projectRemark" cname="备注" type="textarea" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
 		<div title="执行人员">
 			<EF:EFGrid blockId="resultA" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
			<EF:EFGrid blockId="resultB" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
			<EF:EFGrid blockId="resultC" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
			<EF:EFGrid blockId="resultD" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
		<div title="甲供材料">
			<EF:EFGrid blockId="resultE" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryJStuff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true"/>
				<EF:EFColumn ename="matTypeNum" cname="材料分类编号" width="100" hidden="true"/>
				<EF:EFColumn ename="matTypeName" cname="材料分类名称" width="150" hidden="true"/>
				<EF:EFColumn ename="matNum" cname="材料编码" width="100" />
				<EF:EFColumn ename="matName" cname="材料名称" width="150" />
				<EF:EFColumn ename="spec" cname="规格" width="80" />
				<EF:EFColumn ename="unitNum" cname="单位" width="80" hidden="true"/>
				<EF:EFColumn ename="unitName" cname="单位名称" width="80" />
				<EF:EFColumn ename="price" cname="价格" width="80" />
				<EF:EFColumn ename="num" cname="数量" width="80" />
				<EF:EFColumn ename="totalPrice" cname="总价" width="80" />
				<EF:EFColumn ename="brand" cname="品牌" width="80" />
				<EF:EFColumn ename="supType" cname="供应方" width="80" hidden="true"/>
				<EF:EFColumn ename="fashion" cname="方式" width="80" />
				<EF:EFColumn ename="remark" cname="备注" width="80" />
			</EF:EFGrid>
		</div>
		<div title="乙方清单">
			<EF:EFGrid blockId="resultF" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryYStuff">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true"/>
				<EF:EFColumn ename="matTypeNum" cname="材料分类编号" width="100" hidden="true"/>
				<EF:EFColumn ename="matTypeName" cname="材料分类名称" width="150" hidden="true"/>
				<EF:EFColumn ename="matNum" cname="材料编码" width="100" />
				<EF:EFColumn ename="matName" cname="材料名称" width="150" />
				<EF:EFColumn ename="spec" cname="规格" width="80" />
				<EF:EFColumn ename="unitNum" cname="单位" width="80" hidden="true"/>
				<EF:EFColumn ename="unitName" cname="单位名称" width="80" />
				<EF:EFColumn ename="price" cname="价格" width="80" />
				<EF:EFColumn ename="num" cname="数量" width="80" />
				<EF:EFColumn ename="totalPrice" cname="总价" width="80" />
				<EF:EFColumn ename="brand" cname="品牌" width="80" />
				<EF:EFColumn ename="supType" cname="供应方" width="80" hidden="true"/>
				<EF:EFColumn ename="fashion" cname="方式" width="80" />
				<EF:EFColumn ename="remark" cname="备注" width="80" />
			</EF:EFGrid>
		</div>
		<div title="日程进程">
			<EF:EFGrid blockId="resultG" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
			<EF:EFGrid blockId="resultH" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
			checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryZiliao">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="projectPk" cname="projectPk" width="100" hidden="true" />
				<EF:EFPopupColumn ename="dataCode" cname="资料编码" popupWidth="350"
					codeName="pm.projectData"
					columnEnames="ITEM_CODE,ITEM_CNAME"
					columnCnames="资料编码, 资料名称"
					backFillFieldIds="dataCode,dataName"
					backFillColumnIds="ITEM_CODE,ITEM_CNAME"
					popupType="DynamicGrid"/>
				<EF:EFColumn ename="dataName" cname="资料名称" width="200" />
			</EF:EFGrid>
		</div>
		<div title="验收参与人员">
			<EF:EFGrid blockId="resultI" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
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
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		//表格按钮处理
		IPLATUI.EFGrid = {
			"resultA":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
				}, 
			},
			"resultB":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
				}
			},
			"resultC":{
				toolbarConfig:{
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "downLoadFile",text: "下载",layout: "3",
						click: function () { downLoadFile() }
					}]
				}
			},
			"resultD":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
				}
			},
			"resultE":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					/* add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "PRINTJSTUFF",text: "打印",layout: "3",
						click: function () { 
							var checkRows = resultEGrid.getCheckedRows();
							if (checkRows.length > 0) {
								
							} else {
								IPLAT.NotificationUtil("请选择需要打印的甲供材料信息")
							}
						}
					}] */
				}
			},
			"resultF":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
					/* add: false,cancel: false,save: false,'delete': false, 
					buttons:[{
						name: "PRINTYSTUFF",text: "打印",layout: "3",
						click: function () { 
							var checkRows = resultFGrid.getCheckedRows();
							if (checkRows.length > 0) {
								
							} else {
								IPLAT.NotificationUtil("请选择需要打印的乙供材料信息")
							}
						}
					}] */
				}
			},
			"resultG":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
				}
			},
			"resultH":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
				}
			},
			"resultI":{
				toolbarConfig:{
					hidden: true,//true 时，不显示功能按钮，但保留 setting 导出按钮
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
		
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow();
		});
	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['projectEditWindow'].close();
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
	
</script>

