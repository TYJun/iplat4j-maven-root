<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
	.table1 {
		border:1px solid #6495ed;
		border-collapse:collapse;
		height:600px;
	}

	.table1 td {
		line-height: 30px;
		border:1px solid #6495ed;
		width: 110px;
	}

	#t0,#t1,#t2,#t3,#t4,#t5,#t6,#t7 {
		text-align: center;
		color: #0000FF;
		font-size: 16px;
		font-family:Microsoft YaHei;
	}
	.tEmpty {}
	
	.tEmpty input[type=checkbox] {
	    margin: 0 3px 0 0;
	    line-height: normal;
	}
	
	#outTime {
		text-align: center;
		color: red;
		font-size: 16px;
		font-family:Microsoft YaHei;
	}

	.input {
		width: 25px;
		height: 20px;
	}
	
	#T1 td {
		background-color: #d2e8ff;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
	}
	
	#T2 td {
		background-color: #d2e8ff;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
	}
	
	.text {
		width: 649px;
		height: 30px;
	} 
	
	input[type="text"] {
    	margin: 5px 2px;
    	font-size: 13px;
    	color: #000000;
    	font-family:Microsoft YaHei;
	}
	
	.juese input[type=checkbox] {
	    margin: 0 3px 0 0;
	    line-height: normal;
	}

	.juese {
		float: left;
		margin-left:15px;
		font-size: 16px;
		color: #000000;
    	font-family:Microsoft YaHei;
	}
	
	.A{
		float:left;
		width: 780px;
		margin-left:120px;
		height:800px;
	}
	
	.B {
		float:rigth;
		height:800px;
	} 
	
	.Z{
		font-size: 14px;
		color: #000000;
	}
	
	#REFRESH{
		margin-left:8px;
		margin-right:8px;
	} 
	
	#SAVE,#REFRESH{
    	margin-top: 8px;
    	border: 1px solid #3088F4;
    	border-radius: 3px;
    	background-color: #3088F4;
    	min-width: 80px;
    	min-height: 24px;
    	margin-right:8px;
    	color: #FFFFFF;
    	line-height: 100%;
    	font-size: 14px;
    }
</style>
<EF:EFPage>
	<EF:EFRegion id="inqu" head="hidden" title="短信配置" showClear="false">
	<div class="A">
		<table class="table1">
			<tr id="T1">
				<td colspan="2">合同短信配置</td>
			</tr>
			<tr>
				<td id="t0" class="tEmpty">
					<input type="hidden" id="inqu_status-0-id" value="1"/>
					<input type="hidden" id="inqu_status-0-configType" value="cm1"/>
					<input type="hidden" id="inqu_status-0-configTypeName" value="cm1"/>
					<input type="hidden" id="inqu_status-0-statusCode" value="1"/>
					<input type="checkbox" id="inqu_status-0-isRuning" name="time"/> 离过期还有
					<input type="text" name="timeday" class="input" id="inqu_status-0-lateDays" />天发送
				</td>
				<td>
					&nbsp;时间<input type="text" name="timeday" class="input" id="inqu_status-0-time" />
						<input type="text" name="timeday" class="input" id="inqu_status-0-time1"/>点&nbsp;
					内容:<input type="text" value="您好,合同名称为$cont_name$,合同号为$cont_no$的合同,距合同终止日期只有$day$天"
						class="text" id="inqu_status-0-smsTemp" />
				</td>
			</tr>

			<tr>
				<td id="t1" class="tEmpty">
					<input type="hidden" id="inqu_status-1-id" value="2"/>
					<input type="hidden" id="inqu_status-1-configType" value="cm2"/>
					<input type="hidden" id="inqu_status-1-configTypeName" value="cm2"/>
					<input type="hidden" id="inqu_status-1-statusCode" value="2"/>
					<input type="checkbox" id="inqu_status-1-isRuning" name="time"/> 离过期还有
					<input type="text" name="timeday" class="input" id="inqu_status-1-lateDays" />天发送
				</td>
				<td>
					&nbsp;时间<input type="text" name="timeday" class="input" id="inqu_status-1-time" />
						<input type="text" name="timeday" class="input" id="inqu_status-1-time1" />点&nbsp;
					内容:<input type="text" value="您好,合同名称为$cont_name$,合同号为$cont_no$的合同,距合同终止日期只有$day$天"
							class="text" id="inqu_status-1-smsTemp" />
				</td>
			</tr>

			<tr>
				<td id="t2" class="tEmpty">
					<input type="hidden" id="inqu_status-2-id" value="3"/>
					<input type="hidden" id="inqu_status-2-configType" value="cm3"/>
					<input type="hidden" id="inqu_status-2-configTypeName" value="cm3"/>
					<input type="hidden" id="inqu_status-2-statusCode" value="3"/>
					<input type="checkbox" id="inqu_status-2-isRuning" name="time"/> 离过期还有
					<input type="text" name="timeday" class="input" id="inqu_status-2-lateDays" />天发送
				</td>
				<td>
					&nbsp;时间<input type="text" name="timeday" class="input" id="inqu_status-2-time" />
						<input type="text" name="timeday" class="input" id="inqu_status-2-time1" />点&nbsp;
					内容:<input type="text" value="您好,合同名称为$cont_name$,合同号为$cont_no$的合同,距合同终止日期只有$day$天"
							class="text" id="inqu_status-2-smsTemp" />
				</td>
			</tr>


			<tr id="T2">
				<td colspan="2">合同付款短信配置</td>
			</tr>
			<tr>
				<td id="outTime">
					<input type="hidden" id="inqu_status-3-id" value="4"/>
					<input type="hidden" id="inqu_status-3-configType" value="cm4"/>
					<input type="hidden" id="inqu_status-3-configTypeName" value="cm4"/>
					<input type="hidden" id="inqu_status-3-statusCode" value="4"/>
					<input type="checkbox" id="inqu_status-3-isRuning" name="time"/> 离过期还有
					<input type="text" name="timeday" class="input" id="inqu_status-3-lateDays" />天发送
				</td>
				<td>
					&nbsp;时间<input type="text" name="timeday" class="input" id="inqu_status-3-time" />
						<input type="text" name="timeday" class="input" id="inqu_status-3-time1" />点&nbsp;
					内容:<input type="text" value="您好,合同名称为$cont_name$,合同号为$cont_no$的合同,距合同付款日期只有$day$天"
							class="text" id="inqu_status-3-smsTemp" name="sms" />
				</td>
			</tr>

			<tr>
				<td colspan="2" style="text-align:right">
					<input type="button" id="SAVE" value="&nbsp保&nbsp存&nbsp"  style="cursor:pointer"/>
					<input type="button" id="REFRESH" value="&nbsp刷&nbsp新&nbsp"  style="cursor:pointer;"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="B">
		<p class="Z">
			<span style="font-size:16px;color:red;">短信内容配置格式要求：</span><br>
			合同名：<span style="color:blue" class="Z">$cont_name$</span><br>
			合同号：<span style="color:blue" class="Z">$cont_no$</span><br>
			合同到期天数：<span style="color:blue" class="Z">$day$</span><br>
		</p>
		<p class="Z">
			<!-- 督办信息：<span style="color:blue" class="Z">$project_info$</span><br>
			督办起始时间：<span style="color:blue" class="Z">$start_time$</span><br>
			督办结束时间：<span style="color:blue" class="Z">$end_time$</span><br> -->
<%--			超期天数：<span style="color:blue" class="Z">$days$</span><br>--%>
		</p>
	</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		dataReturn();//初始加载数据回显
		
		//保存
		$("#SAVE").on("click", function() {
			//参数封装
			var eiInfo = buildParam();
			//提交
			EiCommunicator.send("CM09", "save", eiInfo, {
				onSuccess : function(ei) {
					IPLAT.NotificationUtil(ei.msg)
					dataReturn();
				}
			})
			
		});
		
		//刷新
		$("#REFRESH").on("click", function(e) {
			dataReturn();
		});
		
	})
	
	//处理回显数据
	function dataReturn(){
		//获取参数
		var eiInfo = new EiInfo();
		//提交
		EiCommunicator.send("CM09", "query", eiInfo, {
			onSuccess : function(ei) {
				var dataList = ei.get("data");
				for(var index in dataList){
					dataReturnRow(index, dataList[index])
				}
			}
		})
	}
	
	//回显每一大项
	function dataReturnRow(index, data){
		//回显是否启用checkBox
		if(data.isRuning == "1"){
			$("#inqu_status-"+index+"-isRuning")[0].checked = true
		}
		//回显短信模板
		$("#inqu_status-"+index+"-smsTemp").val(data.smsTemp);
		//回显短信接收人
		// if(!isEmpty(data.smsRecvCode)){
		// 	var codes = data.smsRecvCode.split(",")
		// 	var tags = $("input[class='person"+index+"']");
		// 	for (var i = 0; i < tags.length; i++) {
		// 		for (var j = 0; j < codes.length; j++) {
		// 			if (tags[i].value == codes[j]) {
		// 				tags[i].checked = true;
		// 			}
		// 		}
		// 	}
		// }
		//回显超期天数
		$("#inqu_status-"+index+"-lateDays").val(data.lateDays);
		//回显时间
		$("#inqu_status-"+index+"-time").val(data.time);
		$("#inqu_status-"+index+"-time1").val(data.time1);
	}
	
	//封装参数
	function buildParam(){
		var array = new Array();
		for(var index=0; index<4; index++){
			var param = {
				"id" : $("#inqu_status-"+index+"-id").val(),	
				"configType" : $("#inqu_status-"+index+"-configType").val(),
				"configTypeName" : $("#inqu_status-"+index+"-configTypeName").val(),
				"smsTemp" : $("#inqu_status-"+index+"-smsTemp").val(),
				"statusCode" : $("#inqu_status-"+index+"-statusCode").val(),
				"lateDays" : $("#inqu_status-"+index+"-lateDays").val(),
				"isRuning" : getCheckValue("isRuning","",index),
				"time" : $("#inqu_status-"+index+"-time").val(),
				"time1" : $("#inqu_status-"+index+"-time1").val(),
			}
			array.push(param)
		}
		var eiInfo = new EiInfo();
		eiInfo.set("list", array);
		return eiInfo;
	}
	
	//获取复选框的值
	function getCheckValue(checkBoxId, checkBoxClass, index){
		var checkBox,values=[];
		if(checkBoxId != ""){
			checkBox = $("#inqu_status-"+index+"-isRuning");
			return checkBox[0].checked ? "1" : "0";
		}
		if(checkBoxClass != "") {
			checkBox = $("input[class='person"+index+"']");
			//遍历
			for(var i in checkBox) {
				if(checkBox[i].checked){
					values.push(checkBox[i].value);
				}
			}
			return values.join(",");
		}
	}
	
	function isEmpty(str) { 
		if(str == undefined){
			return true;
		}
		if(str == null){
			return true;
		}
		if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		}
		return false;
	}
</script>

