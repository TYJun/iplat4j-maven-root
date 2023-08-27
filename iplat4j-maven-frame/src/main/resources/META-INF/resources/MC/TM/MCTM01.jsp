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
			    <EF:EFInput ename="templateCode" cname="模板编码" />
				<EF:EFInput ename="templateName" cname="模板名称" />
				<EF:EFSelect ename="callModule" cname="调用模块" >
					<EF:EFOption label="--请选择--" value=""/>
<%--					<EF:EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>--%>

					<EF:EFTableOption schema="platSchema" tableName="tedpi02" textField="MODULE_CNAME_1"
									  valueField="MODULE_ENAME_1" condition="PROJECT_ENAME='WILP' "
					/>
		  		</EF:EFSelect>
		  		<EF:EFSelect ename="deliveryChannel" cname="发送渠道" >
		  			<EF:EFOption label="--请选择--" value=""/>
			  		<EF:EFOption label="短信" value="DX"/>
					<EF:EFOption label="推送" value="PS"/>
					<EF:EFOption label="钉钉" value="DD"/>
					<EF:EFOption label="企业微信" value="WX"/>
		  		</EF:EFSelect>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="模板列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="templateCode" cname="模板编码" width="100" />
				<EF:EFColumn ename="templateName" cname="模板名称" width="100" />
<%--				<EF:EFColumn ename="callModule" cname="调用模块代码" width="100" />--%>
				<EF:EFColumn ename="callModuleName" cname="调用模块名称" width="100" />
<%--				<EF:EFComboColumn ename="callModule" cname="调用模块">--%>
<%--					<EF:EFCodeOption codeName="wilp.mc.tm.callModule" textField="label" valueField="value"/>--%>
<%--				</EF:EFComboColumn>--%>
				<EF:EFColumn ename="deliveryChannelText" cname="发送渠道"  width="100" />
				<EF:EFColumn ename="isCcText" cname="是否抄送"  width="100" />
				<EF:EFColumn ename="message" cname="消息模板"  width="200" />
				<EF:EFColumn ename="variableName" cname="变量"  width="100" />
				<EF:EFColumn ename="variableCode" cname="变量"  width="100" hidden="true" />
				<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" />
				<EF:EFColumn ename="op" cname="操作" width="100" align="center" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " title="新增" lazyload="true" width="80%" height="50%"></EF:EFWindow>
<EF:EFWindow id="deitPopData" url=" " title="编辑" lazyload="true" width="80%" height="50%"></EF:EFWindow>
<EF:EFWindow id="sendTextPopData" url=" " title="试发短信" lazyload="true" width="60%" height="53%"></EF:EFWindow>
<EF:EFWindow id="pushTextPopData" url=" " title="试推App" lazyload="true" width="60%" height="53%"></EF:EFWindow>
<EF:EFWindow id="pushDingPopData" url=" " title="试推钉钉" lazyload="true" width="60%" height="53%"></EF:EFWindow>
<EF:EFWindow id="pushWXPopData" url=" " title="试推企业微信" lazyload="true" width="60%" height="53%"></EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
	
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			dataBound:function (e) {
				var grid = e.sender;
				var trs = grid.table.find("tr");
				$.each(trs, function (i, tr) {
					var deliveryChannelText = e.sender.dataItems()[i].deliveryChannelText;
					var id = e.sender.dataItems()[i].id;

					var td = $(trs[i]).find('td').eq(10);
					var div = $(td).find('div').eq(10);
					if (deliveryChannelText === '短信') {
						div.append("HELLO");
						td.append('<span><a href="javascript:void(0)" onclick="sendText(\''+id+'\');return false;">试发短信</a></span>');

					} else if (deliveryChannelText === '推送') {

						td.append('<span><a href="javascript:void(0)" onclick="pushText(\''+id+'\');return false;">试推App</a></span>');

					}else if (deliveryChannelText === '钉钉') {

						td.append('<span><a href="javascript:void(0)" onclick="pushDing(\''+id+'\');return false;">试推钉钉</a></span>');

					}else if (deliveryChannelText === '企业微信') {

						td.append('<span><a href="javascript:void(0)" onclick="pushWX(\''+id+'\');return false;">试推企业微信</a></span>');

					}
				});
			},
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
		
			// 操作字段超链接
	        columns: [
	              {
	                  field: "op",
	                  title: "操作",
	                  template:'',
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
		
		//新增按钮
		$("#ADD").on("click", function (e) {
    		var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0101";
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
				var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0102?"+"id=" + datagrid.id;
				var deitPopData = $("#deitPopData");
				deitPopData.data("kendoWindow").setOptions({
					open : function() {
						deitPopData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				deitPopDataWindow.open().center();
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
					EiCommunicator.send("MCTM01", "delete", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
			
		});
	});

	
	function sendText(id){
		console.log(id);
		var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0103?id="+id;
		var addPopData = $("#sendTextPopData");
		addPopData.data("kendoWindow").setOptions({
			open : function() {
				addPopData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		// 打开弹窗
		sendTextPopDataWindow.open().center();
	}

	function pushText(id){
		var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0104?id="+id;
		var addPopData = $("#pushTextPopData");
		addPopData.data("kendoWindow").setOptions({
			open : function() {
				addPopData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		// 打开弹窗
		pushTextPopDataWindow.open().center();
	}
	
	function pushDing(id){
		var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0105?id="+id;
		var addPopData = $("#pushDingPopData");
		addPopData.data("kendoWindow").setOptions({
			open : function() {
				addPopData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		// 打开弹窗
		pushDingPopDataWindow.open().center();
	}
	
	function pushWX(id){
		var url = IPLATUI.CONTEXT_PATH + "/web/MCTM0106?id="+id;
		var addPopData = $("#pushWXPopData");
		addPopData.data("kendoWindow").setOptions({
			open : function() {
				addPopData.data("kendoWindow").refresh({
					url : url
				});
			}
		});
		// 打开弹窗
		pushWXPopDataWindow.open().center();
	}
	
</script>

