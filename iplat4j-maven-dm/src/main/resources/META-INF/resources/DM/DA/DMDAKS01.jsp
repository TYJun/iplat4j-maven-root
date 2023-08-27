<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 科室信息获取 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptNum" cname="科室编号" colWidth="4" ratio="3:8">
			</EF:EFInput>
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" colWidth="4" ratio="3:8">
			</EF:EFInput>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="科室信息">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="true">
            <!-- 隐藏列 -->
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
            <!-- 展示列 -->
            <EF:EFColumn ename="deptNum" cname="科室编码" width="50" align="center" readonly="true"/>
            <EF:EFColumn ename="deptName" cname="科室名称" width="50" align="center" required="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	function setData(e){
		console.log(e);
	}
	
	function getData(){
		var rows = resultGrid.getCheckedRows();
		console.log(rows);
		return rows;
	}
	
	$(function(){
		//查询
        $("#QUERY").on("click", function(e) {
        	resultGrid.dataSource.page(1);
        });
		//刷新grid
        resultGrid.dataSource.page(1);
	});
</script>