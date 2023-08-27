<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 科室信息获取 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询" showClear="true">
	    <!-- 隐藏条件 -->
        <EF:EFInput ename="inqu_status-0-building" disabled="true" style="display:none;" hidden="true"/>
        <EF:EFInput ename="inqu_status-0-floor" disabled="true" style="display:none;" hidden="true"/>
        <EF:EFInput ename="inqu_status-0-canteenNum" disabled="true" style="display:none;" hidden="true"/>
		<div class="row">
            <EF:EFInput ename="inqu_status-0-room" cname="房间" colWidth="6" ratio="2:6">
            </EF:EFInput>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="科室信息">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="true" height="330px" checkMode="row">
            <!-- 展示列 -->
            <EF:EFColumn ename="spotNum" cname="地点编码" width="50" align="center" readonly="true" />
            <EF:EFColumn ename="spotName" cname="地点名称" width="50" align="center" readonly="true" />
            <EF:EFColumn ename="room" cname="房间" width="50" align="center" readonly="true" />
            <EF:EFColumn ename="deptNum" cname="科室编码" width="50" align="center" readonly="true" />
            <EF:EFColumn ename="deptName" cname="科室名称" width="50" align="center" readonly="true" />
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
    <EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
</EF:EFPage>
<script type="text/javascript">
	function setData(e){
		console.log(e);
		IPLAT.EFInput.value($("#inqu_status-0-building"),e.building);
        IPLAT.EFInput.value($("#inqu_status-0-floor"),e.floor);
        IPLAT.EFInput.value($("#inqu_status-0-canteenNum"),e.canteenNum);
        refreshResultGrid();
	}
	
	function getData(){
		var rows = resultGrid.getCheckedRows();
		return rows;
	}
    IPLATUI.EFGrid = {
        result: {
            toolbar: false,
            pageable: {
                pageSize: 1000
            }
        }
    }

	$(function(){
        //隐藏控件
        $("#inqu_status-0-building").closest(".col-md-4").attr("style","display:none;");
        $("#inqu_status-0-floor").closest(".col-md-4").attr("style","display:none;");
        $("#inqu_status-0-canteenNum").closest(".col-md-4").attr("style","display:none;");
		//提交
        $("#submit").on("click", function(e) {
        	parent.closeWindow("insert",getData());
        });
		//取消
        $("#cancel").on("click", function(e) {
        	parent.closeWindow("cancel");
        });
		//查询
        $("#QUERY").on("click", function(e) {
            refreshResultGrid();
        });
	});
</script>