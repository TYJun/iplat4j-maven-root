<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜品信息获取 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询" showClear="true">
	    <!-- 隐藏条件 -->
        <EF:EFInput ename="inqu_status-0-mealDate" disabled="true" style="display:none;float:left;"/>
        <EF:EFInput ename="inqu_status-0-canteenNum" disabled="true" style="display:none;float:left;"/>
		<div class="row">
	        <EF:EFSelect ename="inqu_status-0-menuTypeNum" cname="菜品分类 " optionLabel="请选择" 
	          colWidth="6" ratio="2:6" textField="typeName" valueField="typeCode">
            </EF:EFSelect>
		</div>
		<div class="row">
            <EF:EFInput ename="inqu_status-0-menuName" cname="菜品名称" colWidth="6" ratio="2:6">
            </EF:EFInput>
		</div>
        <EF:EFInput ename="inqu_status-0-mealNum" disabled="true" style="display:none;float:left;"/>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="菜品信息">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="true" height="330px" checkMode="row">
            <!-- 隐藏列 -->
            <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
            <EF:EFColumn ename="menuNum" cname="菜品id" width="100" hidden="true"/>
            <EF:EFColumn ename="menuTypeNum" cname="菜品分类id" width="100" hidden="true"/>
            <EF:EFColumn ename="menuSupply" cname="菜品供应量" width="100" hidden="true"/>
            <!-- 展示列 -->
            <EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" required="true"/>
            <EF:EFColumn ename="menuTypeName" cname="菜品分类" width="150" align="center" required="true" />
            <EF:EFColumn ename="menuPrice" cname="菜品单价" format="{0:n}" width="100" align="center" required="true"/>
            <EF:EFColumn ename="canteenName" cname="所属食堂" width="100" align="center" required="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
    <EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
</EF:EFPage>
<script type="text/javascript">
	function setData(e){
		console.log(e);
		IPLAT.EFInput.value($("#inqu_status-0-mealDate"),e.mealDate);
        IPLAT.EFInput.value($("#inqu_status-0-canteenNum"),e.canteenNum);
        IPLAT.EFInput.value($("#inqu_status-0-mealNum"),e.mealNum);
        //设置菜品分类下拉框数据源
        var eiInfo = new EiInfo();
        eiInfo.set("canteenNum", e.canteenNum);
        EiCommunicator.send("SSBMCPPB02", "queryMenuInfo", eiInfo, {
            onSuccess : function(ei) {
            	var rows = ei.blocks.menuType.getMappedRows();
            	console.log(rows);
            	for (var i = 0; i < rows.length; i++) {
					rows[i].textField = rows[i].typeName;
					rows[i].valueField = rows[i].typeCode;
				}
            	var dataSource = new kendo.data.DataSource({ data: rows });
            	IPLAT.EFSelect.setDataSource($("#inqu_status-0-menuTypeNum"), dataSource);
            }
        });
        
        refreshResultGrid();
	}
	
	function getData(){
		var rows = resultGrid.getCheckedRows();
		return rows;
	}
	
	$(function(){
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