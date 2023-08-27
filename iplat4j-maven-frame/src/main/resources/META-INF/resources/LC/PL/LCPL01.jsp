<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	
	<div class="col-md-12">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<EF:EFSelect ename="module" cname="模块" >
					<EF:EFOption label="--请选择--" value=""/>
					<EF:EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>
		  		</EF:EFSelect>
			<EF:EFInput ename="operator" cname="操作人" />
			<EF:EFInput ename="clientIp" cname="IP地址" />
			<EF:EFDateSpan startName="callTimeS"  endName="callTimeE"
						   startCname="操作时间起" endCname="操作时间止" role="datetime"/>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="日志列表">
			<EF:EFGrid blockId="result" autoDraw="no" >
				<EF:EFColumn ename="id" cname="主键" width="50" hidden="true" />
				<EF:EFComboColumn ename="module" cname="调用模块" width="50" >
					<EF:EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>
				</EF:EFComboColumn>
				<EF:EFColumn ename="operationDescribe" cname="操作描述" width="100" />
				<EF:EFColumn ename="operator" cname="操作人" width="50" />
				<EF:EFColumn ename="operationTime" cname="操作时间" width="70" />
				<EF:EFColumn ename="clientIp" cname="IP地址" width="65" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</div>
</EF:EFPage>


<script type="text/javascript">
	var datagrid = null;

	$(function() {

		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.query(1);
		});
	});
	

</script>

