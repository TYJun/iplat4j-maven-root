<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 通用人员信息获取 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="4" ratio="3:8">
			</EF:EFInput>
			<EF:EFInput ename="inqu_status-0-name" cname="姓名" colWidth="4" ratio="3:8">
			</EF:EFInput>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员信息">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="true" checkMode="row">
            <!-- 隐藏列 -->
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
            <EF:EFColumn ename="deptNum" cname="科室编码" width="100" hidden="true"/>
            <!-- 展示列 -->
            <EF:EFColumn ename="workNo" cname="工号" width="50" align="center" readonly="true"/>
            <EF:EFColumn ename="name" cname="姓名" width="50" align="center" required="true" />
            <EF:EFColumn ename="deptName" cname="所属科室" width="150" align="center" readonly="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	function setData(e){
		console.log(e);
	}
	
	function getData(){
		var rows = resultGrid.getCheckedRows();
		return rows;
	}
	
	$(function(){
		//查询
        $("#QUERY").on("click", function(e) {
            refreshResultGrid();
        });
		//刷新grid
		refreshResultGrid();
	});
</script>