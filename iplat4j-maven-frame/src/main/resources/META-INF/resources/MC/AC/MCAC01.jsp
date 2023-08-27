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
			    <EF:EFInput ename="appCode" cname="APP编码" />
				<EF:EFInput ename="appName" cname="APP名称" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="APP列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="appCode" cname="APP编码" width="100" />
				<EF:EFColumn ename="appName" cname="APP名称" width="100" />
				<EF:EFColumn ename="appKey" cname="AppKey" width="100" />
				<EF:EFColumn ename="appId" cname="AppID" width="100" />
				<EF:EFColumn ename="masterSecret" cname="MasterSecret" width="100" />
				<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " title="新增" lazyload="true" width="80%" height="50%"></EF:EFWindow>
<EF:EFWindow id="editPopData" url=" " title="编辑" lazyload="true" width="80%" height="50%"></EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
	
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
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
		
		//新增按钮
		$("#ADD").on("click", function (e) {
    		var url = IPLATUI.CONTEXT_PATH + "/web/MCAC0101";
        	var addPopData = $("#addPopData");
        	addPopData.data("kendoWindow").setOptions({
        		open : function() {
        			addPopData.data("kendoWindow").refresh({
        				url : url
        			});
        		}
        	});
        	// 打开弹窗
        	addPopDataWindow.open().center();
        });

		
		//修改按钮
		$("#EDIT").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行修改");
			}else {
				var url = IPLATUI.CONTEXT_PATH + "/web/MCAC0102?"+"id=" + datagrid.id;
				var editPopData = $("#editPopData");
				editPopData.data("kendoWindow").setOptions({
					open : function() {
						editPopData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				editPopDataWindow.open().center();
			}
		});
		
		
		
		//删除按钮
		$("#DELETE").on("click", function(e) {
		
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要删除的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			
			IPLAT.confirm({
				message:"确定删除选中行?",
				okFn:function(e){
					EiCommunicator.send("MCAC01", "delete", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
			
		});
	});
</script>

