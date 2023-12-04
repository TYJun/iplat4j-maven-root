<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="">
	<EF:EFRegion id="dfsb" title="特种设备基本信息">
		<div class="row">
			<EF:EFInput ename="machineCode" cname="机房编码：" colWidth="4" ratio="3:8" />
			<EF:EFInput ename="machineName" cname="机房名称：" colWidth="4" ratio="3:8" required="true"/>
			<EF:EFInput ename="models" cname="规格型号：" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFTreeInput ename="machineTypeId" cname="设备分类："
				serviceName="DFFL10" methodName="queryDFFLTree" textField="classifyName"
				valueField="id" hasChildren="isLeaf" popupTitle="设备分类"
				root="{id: 'root',classifyName: '目录结构'}" clear="true" readonly="true"
				colWidth="4" ratio="3:8" required="true">
			</EF:EFTreeInput>
			<EF:EFInput ename="innerMachineCode" cname="内部设备编码：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="documentNo" cname="档案号：" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="workMedia" cname="工作介质：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="useCertNo" cname="使用证编号：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="useArea" cname="使用范围：" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="registerCode" cname="注册代码：" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="registerDate" cname="注册登记日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFDatePicker ename="outFactoryDate" cname="出厂日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="fixedTime" cname="安装日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFDatePicker ename="useTime" cname="使用日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFDatePicker ename="nonuseDate" cname="停用日期：" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>
		<div class="row">
			<%--<EF:EFPopupInput ename="managerManId" cname="管理员："   colWidth="4" ratio="3:8"
				popupTitle="管理员选择" required="false" readonly="true"
				popupType="ServiceGrid" serviceName="DFSB01" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="workName"
				columnEnames="workNum,workName" columnCnames="人员工号,人员姓名" />--%>
			<EF:EFPopupInput ename="managerManId" cname="管理员"  colWidth="4" ratio="3:8"
							 popupTitle="管理员选择" required="true" readOnly="true"
							 popupType="ServiceGrid" serviceName="DFSB01" methodName="queryPerson" resultId="person"
							 valueField="workNo" textField="name"
							 columnEnames="workNo,name,deptName" columnCnames="工号,姓名" />
			<EF:EFPopupInput ename="managerDeptId" cname="管理科室"  colWidth="4" ratio="3:8"
				popupTitle="科室选择" required="true" readonly="true"
				popupType="ServiceGrid" serviceName="DFSB01" methodName="queryDept" resultId="dept"
				valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
			<EF:EFPopupInput ename="useDeaprtId" cname="使用科室"  colWidth="4" ratio="3:8"
				popupTitle="科室选择" required="true"
				popupType="ServiceGrid" serviceName="DFSB01" methodName="queryDept" resultId="dept"
				valueField="deptNum" textField="deptName" readonly="true"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
		</div>
		<div class="row">
		<EF:EFPopupInput ename="chargeUserId" cname="项目负责人"  colWidth="4" ratio="3:8"
				popupTitle="人员选择" required="true" readOnly="true"
				popupType="ServiceGrid" serviceName="DFSB01" methodName="queryPerson" resultId="person"
				valueField="workNo" textField="name"
				columnEnames="workNo,name,deptName" columnCnames="工号,姓名" />
			<EF:EFPopupInput ename="fixedId" cname="安装地点：" colWidth="4" ratio="3:8" readonly="true"
				popupType="ServiceGrid" popupTitle="安装地点" required="true" serviceName="DFSB01" methodName="querySpot" resultId="spot" autofit="true"
				valueField="id" textField="spotName" columnEnames="spotName" columnCnames="地点"/>
			<EF:EFInput ename="relatedDevice" cname="关联设备：" colWidth="4" ratio="3:8"/>

		</div>
		<div class="row">
		 <EF:EFSelect ename="statusCode" cname="设备状态：" colWidth="4"
			ratio="3:8" textField="label" valueField="value" >
				<EF:EFOption label="新建" value="0"/>
				<EF:EFOption label="停用" value="-1"/>
				<EF:EFOption label="启用" value="1"/>
			</EF:EFSelect>
			<EF:EFInput ename="useFor" cname="用途：" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="needScan" cname="是否扫二维码：" colWidth="4"
			 ratio="3:8" textField="label" valueField="value" >
				<EF:EFOption label="否" value="N"/>
				<EF:EFOption label="是" value="Y"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion title="单位信息">
	<div class="row">
		<EF:EFInput ename="manufacturerName" cname="制造单位：" colWidth="5" ratio="4:8"/>
		<EF:EFInput ename="manufacturerCertno" cname="制造单位资格证号：" colWidth="5" ratio="4:8"/>
	</div>
	<div  class="row">
		<EF:EFInput ename="fixedUnit" cname="安装单位：" colWidth="5" ratio="4:8"/>
		<EF:EFInput ename="fixedCertno" cname="安装单位资格证号：" colWidth="5" ratio="4:8"/>
	</div>
	<div  class="row">
		<EF:EFInput ename="maintUnit" cname="维保单位：" colWidth="5" ratio="4:8"/>
		<EF:EFInput ename="maintCertno" cname="维保单位资格证号：" colWidth="5" ratio="4:8"/>
	</div>
	<div  class="row">
		<EF:EFInput ename="checkUnit" cname="检验单位：" colWidth="5" ratio="4:8"/>
		<EF:EFInput ename="checkCertno" cname="检验单位资格证号：" colWidth="5" ratio="4:8"/>
	</div>
	</EF:EFRegion>
	<EF:EFRegion title="检验日期信息">
<%--	<div class="row">--%>
<%--	<EF:EFDatePicker ename="thisCheckDate" cname="本年检验日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"--%>
<%--			parseFormats="['yyyy-mm-dd']" readonly="true" />--%>
<%--			<EF:EFDatePicker ename="thisFinishDate" cname="本年完工日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"--%>
<%--			parseFormats="['yyyy-mm-dd']" readonly="true" />--%>
<%--			<EF:EFInput ename="annualinspcycle" cname="周期（月）：" colWidth="3" ratio="4:8"/>--%>
<%--			<EF:EFDatePicker ename="nextCheckDate" cname="下年检验日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"--%>
<%--			parseFormats="['yyyy-mm-dd']" readonly="true" />--%>
<%--	</div>--%>
	<div class="row">
	<EF:EFDatePicker ename="thisExpiredDate" cname="本次定检日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" />
			<EF:EFDatePicker ename="thisChexpiredDate" cname="本次定检完工日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" />
			<EF:EFInput ename="regularinspcycle" cname="周期（月）：" colWidth="3" ratio="4:8"/>
			<EF:EFDatePicker ename="nextExpiredDate" cname="下年定检日：" role="date" colWidth="3" ratio="4:8" format="yyyy-MM-dd"
			parseFormats="['yyyy-mm-dd']" readonly="true" />
	</div>
	<div class="row">
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
<%--	  <div title="参数信息">--%>
<%--	     	<EF:EFGrid blockId="result" fitHeight="true" autoDraw="no" serviceName="DFFL10" queryMethod="queryParameter" hidden="true">--%>
<%--				<EF:EFColumn ename="id" cname="主键" hidden="true"/>--%>
<%--				<EF:EFColumn ename="paramKey" cname="参数编码" enable="false"/>--%>
<%--				<EF:EFColumn ename="paramName" cname="参数名称" enable="false"/>--%>
<%--				<EF:EFColumn ename="paramValue" cname="参数值" enable="false"/>--%>
<%--	 			<EF:EFColumn ename="paramUnit" cname="参数单位" enable="false"/>--%>
<%--				<EF:EFColumn ename="memo" cname="备注" enable="false"/>--%>
<%--		</EF:EFGrid>--%>
<%--	  </div>--%>
		<div title="设备零部件">
		   	<EF:EFGrid blockId="resultB" fitHeight="true" autoDraw="no">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="machineNames" cname="设备零部件名称" />
				<EF:EFColumn ename="certNo" cname="合格证编号" />
				<EF:EFColumn ename="models" cname="规格型号" />
	 			<EF:EFColumn ename="annualinspcycle" cname="周期" />
	 			<EF:EFColumn ename="thisCheckDate" cname="设备零部件本次检验日期" editType="datetime" dateFormat="yyyy-MM-dd HH:mm:ss"
							 parseFormats="['yyyy-MM-dd HH:mm:ss']" width="150" readonly="true" required="true"/>
	 			<EF:EFColumn ename="nextCheckDate" cname="设备零部件下次检验日期" editType="datetime" dateFormat="yyyy-MM-dd HH:mm:ss"
							 parseFormats="['yyyy-MM-dd HH:mm:ss']" readonly="true" required="true"/>
		</EF:EFGrid>
		</div>
		<div title="文件列表">
			<EF:EFGrid blockId="resultC" fitHeight="true" autoDraw="no">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="relateId" cname="附件id" enable="false" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="附件名称" enable="false"/>
	 			<EF:EFColumn ename="filePath" cname="附件路径" enable="false"/>
				</EF:EFGrid>
	  </div>
		<div title="图片列表">
			<EF:EFGrid blockId="resultD" fitHeight="true" autoDraw="no">
				<EF:EFColumn ename="id" cname="主键" hidden="true"/>
				<EF:EFColumn ename="relateId" cname="图片id" enable="false" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="图片名称" enable="false"/>
				<EF:EFColumn ename="filePath" cname="图片路径" enable="false"/>
			</EF:EFGrid>
		</div>
<%--	  <div title="图片列表">
		  <div class="row">
			  <div class="col-md-10">
				  <div class="form-group">
					  <label for="reqPic" class="col-xs-2 control-label">上传图片</label>
					  <div class="col-xs-10">
						  <span id="reqPic"></span>
						  <span><EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button"></EF:EFButton></span>
					  </div>
				  </div>
			  </div>
		  </div>
	  </div>--%>

	</EF:EFTab>
		<!-- 附件上传窗口 -->
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="uploadFile" title="文件上传">
			<EF:EFUpload ename="projects" docTag="sb_file" path="file" showFileList="true"/>
		</EF:EFRegion>
	</EF:EFWindow>
	<!-- 附件上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="uploadPic" title="图片上传">
			<EF:EFUpload ename="projectPic" docTag="sb_pic" path="pic" showFileList="true"/>
		</EF:EFRegion>
	</EF:EFWindow>
	</EF:EFPage>


