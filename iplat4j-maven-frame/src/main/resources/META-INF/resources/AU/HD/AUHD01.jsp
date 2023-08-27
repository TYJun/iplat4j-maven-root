<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 加载application配置文件 读取医院名称 -->
<fmt:setBundle basename="application" var="app" />
<fmt:message key="hospitalName" var="hospitalName" bundle="${app}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="科室名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="deptName" valueField="label"
				hasChildren="leaf" serviceName="ACDE01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
			    <EF:EFInput ename="roleNum" cname="角色编码" />
				<EF:EFInput ename="roleName" cname="角色名称" />
				<EF:EFInput ename="curDeptId" cname="" type="hidden" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="角色列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="roleId" cname="角色id" width="100" hidden="true" />
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="roleNum" cname="角色编码" width="100" />
				<EF:EFColumn ename="roleName" cname="角色名称" width="100" />
				<EF:EFColumn ename="deptName" cname="所属科室" width="100" />
				<EF:EFColumn ename="deptId" cname="所属科室ID" width="100" hidden="true"  />
				<EF:EFColumn ename="remark" cname="备注" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="70%" height="80%"></EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;


	IPLATUI.EFTree = {
		"tree01": {
			ROOT: {
				label: "root",
				deptName: "${hospitalName == '???hospitalName???'? '根节点':hospitalName}",
				leaf: true
			},

			select: function(e) {
				var _data = this.dataItem(e.node);
				var curDeptId = _data.label;
				$("#curDeptId").val(curDeptId);
				resultGrid.dataSource.page(1);
			},
		}
	};



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
		
		//绑定角色按钮
		$("#BINDINGROLE").on("click", function (e) {
			
			if($("#curDeptId").val() == null || $("#curDeptId").val() == ""){
				IPLAT.alert("请先勾选左侧科室");
				return;
			}
			
    		var url = IPLATUI.CONTEXT_PATH + "/web/AUHD0101?deptId="+$("#curDeptId").val();
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

		
		//解除角色按钮
		$("#RELIEVEROLE").on("click", function(e) {
			
			if($("#curDeptId").val() == null || $("#curDeptId").val() == ""){
				IPLAT.alert("请先勾选左侧科室");
				return;
			}
		
			var checkRows = resultGrid.getCheckedRows();
			console.log(checkRows);
			if(checkRows.length<1){
				IPLAT.alert("请选择要解除角色的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].roleId;
			}

			var info=new EiInfo();
			info.set("list",arr);
			info.set("deptId",$("#curDeptId").val());

			IPLAT.confirm({
				message:"确定解除角色?",
				okFn:function(e){
					EiCommunicator.send("AUHD01", "relieveRole", info, {
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

