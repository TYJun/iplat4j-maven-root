<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">

		<EF:EFInput ename="spotId" type="hidden" />
		<div class="row">
			<EF:EFTreeInput ename="deptId" cname="所属科室" bindId="tree01" readonly="true"
							serviceName="ACDE01" methodName="queryTree"
							textField="deptName" valueField="label" hasChildren="leaf"
							root="{label: 'root',deptName: '根节点'}" popupTitile="上级分类" clear="true"
							ratio="4:8" required="true">
			</EF:EFTreeInput>
<%--			<EF:EFInput ename="deptId" cname="科室ID" --%>
<%--				colWidth="4"  type="hidden" required="true"--%>
<%--				readOnly="true"  />--%>
<%--			--%>
<%--			<EF:EFInput ename="deptName" cname="科室名称" --%>
<%--				colWidth="4"  type="text" required="true"--%>
<%--				readOnly="true" />--%>
			<EF:EFInput ename="spotNum" cname="地点编号" 
				colWidth="4"  type="text" required="true"
				readOnly="true" />
			<EF:EFInput ename="spotName" cname="地点名称" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入地点名称" />
		
			
		</div>
				
		<div class="row">
			<EF:EFInput ename="jpSpotName" cname="地点名简拼" 
				colWidth="4"  type="text"
				placeholder="请输入地点名简拼" />
			<EF:EFInput ename="building" cname="楼" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入楼号" />
			<EF:EFInput ename="floor" cname="层" 
				colWidth="4"  type="text" required="true"
				placeholder="请输入层号" />
			
		</div>
		<div class="row">
			<EF:EFInput ename="room" cname="房间" 
					colWidth="4"  type="text" required="true"
					placeholder="请输入房间号" />
			
			<EF:EFInput ename="remark" type="textarea" colWidth="4" cname="备注"/>
			
		</div>
	</EF:EFRegion>
</EF:EFPage>
