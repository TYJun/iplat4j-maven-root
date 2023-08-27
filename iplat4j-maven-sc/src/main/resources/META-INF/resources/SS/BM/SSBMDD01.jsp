<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐数据字典 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="订餐数据字典" showClear="true">
		<div class="row" style="height:1px;">
			<EF:EFInput ename="inqu_status-0-typegroupname" cname="数据字典分类:" />

		</div>
	</EF:EFRegion>
	<div class="col-md-4" style="padding-left:0px;">
		<EF:EFRegion id="result" title="数据字典分类" fitHeight="true">
			<EF:EFGrid blockId="result" autoDraw="no" refresh="true" checkMode="row">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" readonly="true"/>
				<EF:EFColumn ename="typegroupname" cname="字典名称" width="100" readonly="true" required="false"/>
				<EF:EFColumn ename="typegroupcode" cname="字典代码" width="100" readonly="true" required="false"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	<div class="col-md-8" style="padding-right:0px;">
		<EF:EFRegion id="result2" title="数据字典条目">
			<EF:EFGrid blockId="result2" autoDraw="no"  refresh="true" checkMode="row">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="typename" cname="数据项名称 " width="100" readonly=""/>
				<EF:EFColumn ename="typecode" cname="数据项代码" width="100" readonly=""/>
				<EF:EFColumn ename="paramdesc1" cname="说明1" width="100" readonly=""/>
				<EF:EFColumn ename="paramvalue1" cname="备注1" width="100" readonly=""/>
				<EF:EFColumn ename="paramdesc2" cname="说明2" width="100" readonly=""/>
				<EF:EFColumn ename="paramvalue2" cname="备注2" width="100" readonly=""/>
				<EF:EFColumn ename="paramdesc3" cname="说明3" width="100" readonly=""/>
				<EF:EFColumn ename="paramvalue3" cname="备注3" width="100" readonly=""/>
				<%-- <EF:EFComboColumn ename="status" cname="状态" width="100" align="center" defaultValue="1">
	                <EF:EFOption label="启用" value="1"/>
	                <EF:EFOption label="未启用" value="0"/>
	            </EF:EFComboColumn> --%>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>

</EF:EFPage>
<script type="text/javascript">
//声明一个控制点击的变量
var upLoadClicktag = 0;
var datagrid = null;

IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender,
						model = e.model, 
						$tr = e.tr,
						row = e.row;
						datagrid = model;
				}
				if(resultGrid.getCheckedRows().length == 1){
					$("#result2 #add").css("display","");
					$("#QUERY1").click();
				}else{
					$("#result2 #add").css("display","none");
				}
			}
		},
		"result2" : {
			/*columns :[{
                field: "status",
                template: "#=status==1?'启用':'未启用'#"
            }],  */
			onSave: function (e) {
				var checkRows = resultGrid.getCheckedRows();
		        if(checkRows.length == 0){
		            NotificationUtil("请先选择分类！", "error");
		            e.preventDefault();
		            return;
		        }
		        var checkRows2 = result2Grid.getCheckedRows();
		        for(var i=0;i<checkRows2.length;i++){
		            var checkRowsValue = checkRows2[i];
		            if(!checkRowsValue.get("typename") || !checkRowsValue.get("typecode")){
		                NotificationUtil("请填写数据项名称和数据项代码！", "error");
		                e.preventDefault();
		                return;
		            }
		        }
			}
		}
	}


$(function(){
	//默认隐藏新增按钮
	$("#result2 #add").css("display","none");
	//订餐数据字典分类击事件
	$("#QUERY1").on("click", function(e) {
		var checkRows = resultGrid.getCheckedRows(), eiInfo = new EiInfo();
		if(checkRows.length!=1){
        	/*WindowUtil({
        		'title' : '提醒',
        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
        	});*/
        	NotificationUtil("请选择一条记录", "warning");
        	return;
		}
		var listCheckRows = new Array();
		var map = new Map();
		//获取参数传递后端
		for(var i=0;i<checkRows.length;i++){
			var checkRowsValue = checkRows[i];
			var id =checkRowsValue.get("id");
			listCheckRows.push(id);
			}
			eiInfo.set("result", listCheckRows);
		//访问方法
		EiCommunicator.send("SSBMDD01", "queryMealType", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");

				//刷新页面
				setTimeout(function() {
					result2Grid.dataSource.page(1)
				}, 500); 
			}
		});
	}); 
	
	//数据字典分类击事件
	$("#SAVE1").on("click", function(e) {
		var btnNode = $(this);
		btnNode.attr("disabled", true);
		
		var checkRows = resultGrid.getCheckedRows(), eiInfo = new EiInfo();
		var listCheckRows = new Array();
		var map = new Map();
		//获取参数传递后端
		for(var i=0;i<checkRows.length;i++){
			var checkRowsValue = checkRows[i];
			var typegroupname =checkRowsValue.get("typegroupname");
			var typegroupcode = checkRowsValue.get("typegroupcode");
			map.typegroupname = typegroupname;
			map.typegroupcode = typegroupcode;
			listCheckRows.push(map);
			}
			eiInfo.set("result", listCheckRows);
		//访问方法
		EiCommunicator.send("SSBMDD01", "insertMealTypeGroup", eiInfo, {
			onSuccess : function(ei) {
				if(ei["status"] > 0){

					NotificationUtil(ei.getMsg(), "success");
					//刷新页面
					setTimeout(function() {
						$("#QUERY").click();
						//refreshResultGrid();
					}, 500);
				}else{
					NotificationUtil(ei.getMsg(), "error");
				}
				btnNode.attr("disabled", false);
			}
		});
	}); 

	
    $("#QUERY").on("click", function(e) {
        refreshResultGrid();
        
    });
    //页面加载时查询一次
    $("#QUERY").click();
	
})
	
</script>