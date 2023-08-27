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
			    <EF:EFSelect ename="callModule" cname="调用模块" >
					<EF:EFOption label="--请选择--" value=""/>
<%--			  		<EF:EFOption label="维修" value="MT"/>--%>
<%--			  		<EF:EFOption label="医废" value="NB"/>--%>
<%--			  		<EF:EFOption label="订餐" value="OM"/>--%>
<%--			  		<EF:EFOption label="被服" value="BC"/>--%>
					<EF:EFTableOption schema="platSchema" tableName="tedpi02" textField="MODULE_CNAME_1"
									  valueField="MODULE_ENAME_1" condition="PROJECT_ENAME='WILP' "
					/>
		  		</EF:EFSelect>
		  		<EF:EFSelect ename="deliveryChannel" cname="发送渠道" >
		  			<EF:EFOption label="--请选择--" value=""/>
		  			<EF:EFOption label="推送" value="PS"/>
			  		<EF:EFOption label="短信" value="DX"/>
			  		<EF:EFOption label="钉钉" value="DD"/>
			  		<EF:EFOption label="企业微信" value="WX"/>
		  		</EF:EFSelect>
				<EF:EFDateSpan startName="sendingTimeS" startCname="发送时间大于"
							   endName="sendingTimeE" endCname="发送时间小于" role="datetime"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="消息列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="message" cname="消息内容" width="250" />
				<EF:EFColumn ename="callModuleText" cname="调用模块" width="80" />
				<EF:EFColumn ename="callModule" cname="调用模块" width="100" hidden="true" />
				<EF:EFColumn ename="deliveryChannelText" cname="发送渠道"  width="80" />
				<EF:EFColumn ename="deliveryChannel" cname="发送渠道"  width="100" hidden="true"/>
				<EF:EFColumn ename="sendingTime" cname="发送时间" width="100" />
				<EF:EFColumn ename="status" cname="阅读状态" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>



<script type="text/javascript">
	// var datagrid = null;
	//
	// //EFGrid单击事件，获取选中行数据（定义全部变量）
	// IPLATUI.EFGrid = {
	// 	"result": {
	//     	dataBound: function (e) {
	//     		var grid = e.sender;
	//     		var trs = grid.table.find("tr");
	//     		$.each(trs, function (i, tr) {
	//     			var isSuccess = e.sender.dataItems()[i].isSuccess;
	//     			if (isSuccess=='成功') {
	//     				var td = $(trs[i]).find('td').eq(0);
	//     				td.css('background','#9AFF02');
	//     				// 把操作字段 重新发送 超链接删除
	// 					var length = $(trs[i]).find('td').length;
	// 					td = $(trs[i]).find('td').eq(length - 1); // 最后一列
	// 					td[0].innerHTML = "";
	//     			}else{
	//     				var td = $(trs[i]).find('td').eq(0);
	//     				td.css('background','#FF9224');
	//     			}
	//
	//     		});
    // 		},
	// 		// 操作字段超链接
	// 		columns: [
	// 			{
	// 				field: "op",
	// 				title: "操作",
	// 				template: '<span><a href="javascript:void(0)" onclick="reSendText(\'#:templateId#\',\'#:templateCode#\',\'#:message#\',\'#:callModule#\',\'#:deliveryChannel#\',\'#:receiver#\');return false;">重新发送</a></span>',
	// 				width: 50
	// 			}
	// 		]
	// 	}
	// }

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

	// function reSendText(templateId,templateCode,message,callModule,deliveryChannel,receiver) {
	// 	IPLAT.confirm({
	// 		message:"确定重新发送选中行?",
	// 		okFn:function(e){
	// 			var info = new EiInfo();
	// 			info.set("templateCode", templateCode);
	// 			info.set("templateId", templateId);
	// 			info.set("message", message);
	// 			info.set("callModule", callModule);
	// 			info.set("deliveryChannel", deliveryChannel);
	// 			info.set("receiver", receiver);
	// 			EiCommunicator.send("MCMS01", "reSendText", info, {
	// 				onSuccess : function(ei) {
	// 					IPLAT.alert(ei.getMsg());
	// 					resultGrid.dataSource.page(1);
	// 				}
	// 			});
	// 		}
	// 	});
	// }

</script>

