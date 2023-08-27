<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	
	<div class="col-md-12">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<EF:EFInput ename="module" cname="模块" />
			<EF:EFInput ename="method" cname="方法名" />
			<EF:EFInput ename="className" cname="类名" />
			<EF:EFDateSpan startName="callTimeS"  endName="callTimeE"
						   startCname="调用时间大于" endCname="调用时间小于" role="datetime"/>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="日志列表">
			<EF:EFGrid blockId="result" autoDraw="no" >
				<EF:EFColumn ename="id" cname="主键" width="50" hidden="true" />
				<EF:EFColumn ename="statusText" cname="状态" width="50" align="center" />
				<EF:EFColumn ename="status" cname="状态" width="50" hidden="true"/>
				<EF:EFColumn ename="module" cname="模块" width="50" />
				<EF:EFColumn ename="className" cname="全限定名" width="100" />
				<EF:EFColumn ename="method" cname="方法名" width="65" />
				<EF:EFColumn ename="callTime" cname="调用时间" width="110" />
				<EF:EFColumn ename="errors" cname="错误信息" width="300" />
				<EF:EFColumn ename="op" cname="操作" width="50" align="center" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</div>
</EF:EFPage>

<EF:EFWindow id="lookOverPopData" url=" " title="查看参数" lazyload="true" width="60%" height="53%"></EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result": {
			dataBound: function (e) {
				var grid = e.sender;
				var trs = grid.table.find("tr");
				$.each(trs, function (i, tr) {
					var statusText = e.sender.dataItems()[i].statusText;
					if (statusText=='成功') {
						var td = $(trs[i]).find('td').eq(1);
						td.css('background','#9AFF02');
					}else{
						var td = $(trs[i]).find('td').eq(1);
						td.css('background','#FF9224');
					}
				});
			},
	
			// 操作字段超链接
		    columns: [
		          {
		              field: "op",
		              title: "操作",
		              template: '<span><a href="javascript:void(0)" onclick="lookOver(\'#:id#\');return false;">查看参数</a></span>',
		              width: 100
		          }
		    ]
	
		}
	}

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
	
	
	function lookOver(id){
		var url = IPLATUI.CONTEXT_PATH + "/web/LCLM0101?id="+id;
		var addPopData = $("#lookOverPopData");
		addPopData.data("kendoWindow").setOptions({
			open : function() {
				addPopData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		// 打开弹窗
		lookOverPopDataWindow.open().center();
	}

</script>

