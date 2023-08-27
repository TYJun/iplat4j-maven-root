<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	
	<div class="col-md-12">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
			    <EF:EFInput ename="module" cname="模块" />
				<EF:EFInput ename="method" cname="方法名" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="module" cname="模块" width="50" />
				<EF:EFColumn ename="className" cname="全限定名" width="100" />
				<EF:EFColumn ename="method" cname="方法名" width="100" />
				<EF:EFColumn ename="isMonitoring" cname="是否监测" width="100" hidden="true" />
				<EF:EFColumn ename="isMonitoringText" cname="是否监测" width="50" />
				<EF:EFColumn ename="isParameter" cname="是否记录参数" width="50" hidden="true"/>
				<EF:EFColumn ename="isParameterText" cname="是否记录参数" width="50" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</div>
</EF:EFPage>



<script type="text/javascript">

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

		//开启监测按钮
		$("#ON").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();

			if(checkRows.length<1){
				IPLAT.alert("请选择要开启监测的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("status","1");
			EiCommunicator.send("LCMM01", "update", info, {
				onSuccess : function(ei) {
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});

		});

		//关闭监测按钮
		$("#OFF").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();

			if(checkRows.length<1){
				IPLAT.alert("请选择要关闭监测的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("status","0");
			EiCommunicator.send("LCMM01", "update", info, {
				onSuccess : function(ei) {
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});

		});


		// 是否记录参数
		//开启记录参数按钮
		$("#ONPARAM").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();

			if(checkRows.length<1){
				IPLAT.alert("请选择要开启记录参数的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("isParameter","1");
			EiCommunicator.send("LCMM01", "updateParam", info, {
				onSuccess : function(ei) {
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});

		});

		//关闭记录参数按钮
		$("#OFFPARAM").on("click", function(e) {
			var checkRows = resultGrid.getCheckedRows();

			if(checkRows.length<1){
				IPLAT.alert("请选择要关闭监测的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("isParameter","0");
			EiCommunicator.send("LCMM01", "updateParam", info, {
				onSuccess : function(ei) {
					IPLAT.alert(ei.getMsg());
					resultGrid.dataSource.page(1);
				}
			});

		});

	});

</script>

