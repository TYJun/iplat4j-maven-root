<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
	.table1 {
		border:1px solid #6495ed;
		border-collapse:collapse;
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
	}
	
	.B {
		float:rigth;
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
				<td colspan="2">项目过程中短信配置</td>
			</tr>
			<tr>
				<td id="t0" class="tEmpty">
					<input type="hidden" id="inqu_status-0-id" value="1"/>
					<input type="hidden" id="inqu_status-0-configType" value="projectNew"/>
					<input type="hidden" id="inqu_status-0-configTypeName" value="项目立项"/>
					<input type="hidden" id="inqu_status-0-statusCode" value="1"/>
					<input type="hidden" id="inqu_status-0-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-0-isRuning" name="renwu" class="statu" value="01" />项目立项
				</td>
				<td>
					<input type="text" value="$name$在系统中登记了项目名称为$project_name$的[招标项目]，项目号：$project_no$，请到系统的工程项目中查看并及时处理！"
						class="text" id="inqu_status-0-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-0-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person0" value="PFZR" />项目负责人
						<input type="checkbox" class="person0" value="PLLR" />项目联络人
						<input type="checkbox" class="person0" value="PZXR" />执行人
						<input type="checkbox" class="person0" value="PZHR" />知会人
						<input type="checkbox" class="person0" value="PDBR" />督办人
						<input type="checkbox" class="person0" value="PYSR" />验收参与人
						<input type="checkbox" class="person0" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person0" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person0" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person0" value="SJJSR" />审计接受人
						<input type="checkbox" class="person0" value="BGJFR" />报告交付人
						<input type="checkbox" class="person0" value="BGJSR" />报告接受人 
					</div>
					<div class="d1"></div>
				</td>
			</tr>
			<tr>
				<td id="t1" class="tEmpty">
					<input type="hidden" id="inqu_status-1-id" value="2"/>
					<input type="hidden" id="inqu_status-1-configType" value="projectExec"/>
					<input type="hidden" id="inqu_status-1-configTypeName" value="项目执行"/>
					<input type="hidden" id="inqu_status-1-statusCode" value="2"/>
					<input type="hidden" id="inqu_status-1-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-1-isRuning" name="renwu" class="statu" value="02" />项目执行
				</td>
				<td>
					<input type="text" value="项目名称为$project_name$，项目号：$project_no$的项目已开始执行，执行人员为$project_exec$。"
							class="text" id="inqu_status-1-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-1-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person1" value="PFZR" />项目负责人
						<input type="checkbox" class="person1" value="PLLR" />项目联络人
						<input type="checkbox" class="person1" value="PZXR" />执行人
						<input type="checkbox" class="person1" value="PZHR" />知会人
						<input type="checkbox" class="person1" value="PDBR" />督办人
						<input type="checkbox" class="person1" value="PYSR" />验收参与人
						<input type="checkbox" class="person1" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person1" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person1" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person1" value="SJJSR" />审计接受人
						<input type="checkbox" class="person1" value="BGJFR" />报告交付人
						<input type="checkbox" class="person1" value="BGJSR" />报告接受人 
					</div>
					<div class="d2"></div>
				</td>
			</tr>
			<tr>
				<td id="t2" class="tEmpty">
					<input type="hidden" id="inqu_status-2-id" value="3"/>
					<input type="hidden" id="inqu_status-2-configType" value="projectFinish"/>
					<input type="hidden" id="inqu_status-2-configTypeName" value="项目完工"/>
					<input type="hidden" id="inqu_status-2-statusCode" value="3"/>
					<input type="hidden" id="inqu_status-2-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-2-isRuning" name="renwu" class="statu" value="03" />项目完工
				</td>
				<td>
					<input type="text" value="项目名称为$project_name$，项目号：$project_no$的项目已完工。"
							class="text" id="inqu_status-2-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-2-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person2" value="PFZR" />项目负责人
						<input type="checkbox" class="person2" value="PLLR" />项目联络人
						<input type="checkbox" class="person2" value="PZXR" />执行人
						<input type="checkbox" class="person2" value="PZHR" />知会人
						<input type="checkbox" class="person2" value="PDBR" />督办人
						<input type="checkbox" class="person2" value="PYSR" />验收参与人
						<input type="checkbox" class="person2" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person2" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person2" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person2" value="SJJSR" />审计接受人
						<input type="checkbox" class="person2" value="BGJFR" />报告交付人
						<input type="checkbox" class="person2" value="BGJSR" />报告接受人 
					</div>
					<div class="d9"></div>
				</td>
			</tr>
			<tr>
				<td id="t3" class="tEmpty">
					<input type="hidden" id="inqu_status-3-id" value="4"/>
					<input type="hidden" id="inqu_status-3-configType" value="projectVia"/>
					<input type="hidden" id="inqu_status-3-configTypeName" value="项目督办"/>
					<input type="hidden" id="inqu_status-3-statusCode" value="4"/>
					<input type="hidden" id="inqu_status-3-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-3-isRuning" name="renwu" class="statu" value="03" />项目督办
				</td>
				<td>
					<input type="text" value="项目名为$project_name$，项目号：$project_no$的项目督办人变更为$project_via$，督办信息：$project_info$,督办时间从$start_time$到$end_time$。"
						class="text" id="inqu_status-3-smsTemp" /></td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-3-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person3" value="PFZR" />项目负责人
						<input type="checkbox" class="person3" value="PLLR" />项目联络人
						<input type="checkbox" class="person3" value="PZXR" />执行人
						<input type="checkbox" class="person3" value="PZHR" />知会人
						<input type="checkbox" class="person3" value="PDBR" />督办人
						<input type="checkbox" class="person3" value="PYSR" />验收参与人
						<input type="checkbox" class="person3" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person3" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person3" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person3" value="SJJSR" />审计接受人
						<input type="checkbox" class="person3" value="BGJFR" />报告交付人
						<input type="checkbox" class="person3" value="BGJSR" />报告接受人 
					</div>
					<div class="d3"></div>
				</td>
			</tr>
			<tr>
				<td id="t4" class="tEmpty">
					<input type="hidden" id="inqu_status-4-id" value="5"/>
					<input type="hidden" id="inqu_status-4-configType" value="projectAccept"/>
					<input type="hidden" id="inqu_status-4-configTypeName" value="项目验收"/>
					<input type="hidden" id="inqu_status-4-statusCode" value="5"/>
					<input type="hidden" id="inqu_status-4-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-4-isRuning" name="renwu" class="statu" value="04" />项目验收
				</td>
				<td>
					<input type="text" value="项目名为$project_name$，项目号：$project_no$的项目已通过验收！"
							class="text" id="inqu_status-4-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-4-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person4" value="PFZR" />项目负责人
						<input type="checkbox" class="person4" value="PLLR" />项目联络人
						<input type="checkbox" class="person4" value="PZXR" />执行人
						<input type="checkbox" class="person4" value="PZHR" />知会人
						<input type="checkbox" class="person4" value="PDBR" />督办人
						<input type="checkbox" class="person4" value="PYSR" />验收参与人
						<input type="checkbox" class="person4" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person4" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person4" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person4" value="SJJSR" />审计接受人
						<input type="checkbox" class="person4" value="BGJFR" />报告交付人
						<input type="checkbox" class="person4" value="BGJSR" />报告接受人 
					</div>
					<div class="d4"></div>
				</td>
			</tr>
			<tr>
				<td id="t5" class="tEmpty">
					<input type="hidden" id="inqu_status-5-id" value="6"/>
					<input type="hidden" id="inqu_status-5-configType" value="projectAchive"/>
					<input type="hidden" id="inqu_status-5-configTypeName" value="项目决算"/>
					<input type="hidden" id="inqu_status-5-statusCode" value="6"/>
					<input type="hidden" id="inqu_status-5-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-5-isRuning" name="renwu" class="statu" value="05" />项目决算
				</td>
				<td>
					<input type="text" value="项目名为$project_name$，项目号：$project_no$的项目已归档决算！"
							class="text" id="inqu_status-5-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-5-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person5" value="PFZR" />项目负责人
						<input type="checkbox" class="person5" value="PLLR" />项目联络人
						<input type="checkbox" class="person5" value="PZXR" />执行人
						<input type="checkbox" class="person5" value="PZHR" />知会人
						<input type="checkbox" class="person5" value="PDBR" />督办人
						<input type="checkbox" class="person5" value="PYSR" />验收参与人
						<input type="checkbox" class="person5" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person5" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person5" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person5" value="SJJSR" />审计接受人
						<input type="checkbox" class="person5" value="BGJFR" />报告交付人
						<input type="checkbox" class="person5" value="BGJSR" />报告接受人 
					</div>
					<div class="d5"></div>
				</td>
			</tr>
			<tr>
				<td id="t6" class="tEmpty">
					<input type="hidden" id="inqu_status-6-id" value="7"/>
					<input type="hidden" id="inqu_status-6-configType" value="projectAudit"/>
					<input type="hidden" id="inqu_status-6-configTypeName" value="项目审计"/>
					<input type="hidden" id="inqu_status-6-statusCode" value="7"/>
					<input type="hidden" id="inqu_status-6-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-6-isRuning" name="renwu" class="statu" value="06" />项目审计
				</td>
				<td>
					<input type="text" value="项目名为$project_name$，项目号：$project_no$的项目已通过审计！" 
							class="text" id="inqu_status-6-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-6-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person6" value="PFZR" />项目负责人
						<input type="checkbox" class="person6" value="PLLR" />项目联络人
						<input type="checkbox" class="person6" value="PZXR" />执行人
						<input type="checkbox" class="person6" value="PZHR" />知会人
						<input type="checkbox" class="person6" value="PDBR" />督办人
						<input type="checkbox" class="person6" value="PYSR" />验收参与人
						<input type="checkbox" class="person6" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person6" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person6" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person6" value="SJJSR" />审计接受人
						<input type="checkbox" class="person6" value="BGJFR" />报告交付人
						<input type="checkbox" class="person6" value="BGJSR" />报告接受人 
					</div>
					<div class="d6"></div>
				</td>
			</tr>
			<tr>
				<td id="t7" class="tEmpty">                             
					<input type="hidden" id="inqu_status-7-id" value="8"/>
					<input type="hidden" id="inqu_status-7-configType" value="projectFinal"/>
					<input type="hidden" id="inqu_status-7-configTypeName" value="项目完结"/>
					<input type="hidden" id="inqu_status-7-statusCode" value="8"/>
					<input type="hidden" id="inqu_status-7-lateDays" value="0"/>
					<input type="checkbox" id="inqu_status-7-isRuning" name="renwu" class="statu" value="07" />项目完结
				</td>
				<td>
					<input type="text" value="项目名为$project_name$，项目号：$project_no$的项目已完结！" 
							class="text" id="inqu_status-7-smsTemp" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-7-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person7" value="PFZR" />项目负责人
						<input type="checkbox" class="person7" value="PLLR" />项目联络人
						<input type="checkbox" class="person7" value="PZXR" />执行人
						<input type="checkbox" class="person7" value="PZHR" />知会人
						<input type="checkbox" class="person7" value="PDBR" />督办人
						<input type="checkbox" class="person7" value="PYSR" />验收参与人
						<input type="checkbox" class="person7" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person7" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person7" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person7" value="SJJSR" />审计接受人
						<input type="checkbox" class="person7" value="BGJFR" />报告交付人
						<input type="checkbox" class="person7" value="BGJSR" />报告接受人 
					</div>
					<div class="d7"></div>
				</td>
			</tr>
			<tr id="T2">
				<td colspan="2">项目超期短信配置</td>
			</tr>
			<tr>
				<td id="outTime">
					<input type="hidden" id="inqu_status-8-id" value="9"/>
					<input type="hidden" id="inqu_status-8-configType" value="overDue"/>
					<input type="hidden" id="inqu_status-8-configTypeName" value="项目超期"/>
					<input type="hidden" id="inqu_status-8-statusCode" value="9"/>
					<input type="checkbox" id="inqu_status-8-isRuning" name="time"/> 超期
					<input type="text" name="timeday" class="input" id="inqu_status-8-lateDays" />天
				</td>
				<td>
					<input type="text" value="截至今日，项目名为$project_name$，项目号：$project_no$的项目已超期$days$天，请及时督办!"
							class="text" id="inqu_status-8-smsTemp" name="sms" />
				</td>
			</tr>
			<tr>
				<td colspan="2" id="inqu_status-8-smsRecvCode">
					<div class="juese">
						<input type="checkbox" class="person8" value="PFZR" />项目负责人
						<input type="checkbox" class="person8" value="PLLR" />项目联络人
						<input type="checkbox" class="person8" value="PZXR" />执行人
						<input type="checkbox" class="person8" value="PZHR" />知会人
						<input type="checkbox" class="person8" value="PDBR" />督办人
						<input type="checkbox" class="person8" value="PYSR" />验收参与人
						<input type="checkbox" class="person8" value="YFTJR" />乙方提交人
						<input type="checkbox" class="person8" value="JFJSR" />甲方收件人
						<input type="checkbox" class="person8" value="ZLTJR" />资料提交人
						<input type="checkbox" class="person8" value="SJJSR" />审计接受人
						<input type="checkbox" class="person8" value="BGJFR" />报告交付人
						<input type="checkbox" class="person8" value="BGJSR" />报告接受人 
					</div>
					<div class="d8"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:right">
					<input type="button" id="SAVE" value="&nbsp保&nbsp存&nbsp" onclick="clickCheck()" style="cursor:pointer"/> 
					<input type="button" id="REFRESH" value="&nbsp刷&nbsp新&nbsp" onclick="fresh();" style="cursor:pointer;"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="B">
		<p class="Z">
			<span style="font-size:16px;color:red;">短信内容配置格式要求：</span><br>
			项目名：<span style="color:blue" class="Z">$project_name$</span><br>
			项目号：<span style="color:blue" class="Z">$project_no$</span><br>
			项目负责人：<span style="color:blue" class="Z">$project_contor$</span><br>
			项目联络人: <span style="color:blue" class="Z">$project_cons$</span><br><br>
			执行人：<span style="color:blue" class="Z">$project_exec$</span><br> 
			知会人：<span style="color:blue" class="Z">$project_know$</span><br> 
			督办人：<span style="color:blue" class="Z">$project_via$</span><br>
			验收参与人：<span style="color:blue" class="Z">$project_test_staff$</span><br>
			乙方提交人：<span style="color:blue" class="Z">$submit_maker$</span><br>
			甲方收件人：<span style="color:blue" class="Z">$accept_maker$</span><br>
			资料提交人：<span style="color:blue" class="Z">$ZLsubmit_maker$</span><br>
			审计接受人：<span style="color:blue" class="Z">$aduit_maker$</span><br>
			报告交付人：<span style="color:blue" class="Z">$report_submit_maker$</span><br>
			报告接受人：<span style="color:blue" class="Z">$report_accept_maker$</span><br>
		</p>
		<p class="Z">
			<!-- 督办信息：<span style="color:blue" class="Z">$project_info$</span><br>
			督办起始时间：<span style="color:blue" class="Z">$start_time$</span><br>
			督办结束时间：<span style="color:blue" class="Z">$end_time$</span><br> -->
			超期天数：<span style="color:blue" class="Z">$days$</span><br>
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
			EiCommunicator.send("PM09", "save", eiInfo, {
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
		EiCommunicator.send("PM09", "query", eiInfo, {
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
		if(!isEmpty(data.smsRecvCode)){
			var codes = data.smsRecvCode.split(",")
			var tags = $("input[class='person"+index+"']");
			for (var i = 0; i < tags.length; i++) {
				for (var j = 0; j < codes.length; j++) {
					if (tags[i].value == codes[j]) {
						tags[i].checked = true;
					}
				}
			}
		}
		//回显超期天数
		$("#inqu_status-"+index+"-lateDays").val(data.lateDays);
	}
	
	//封装参数
	function buildParam(){
		var array = new Array();
		for(var index=0; index<9; index++){
			var param = {
				"id" : $("#inqu_status-"+index+"-id").val(),	
				"configType" : $("#inqu_status-"+index+"-configType").val(),
				"configTypeName" : $("#inqu_status-"+index+"-configTypeName").val(),
				"smsTemp" : $("#inqu_status-"+index+"-smsTemp").val(),
				"statusCode" : $("#inqu_status-"+index+"-statusCode").val(),
				"lateDays" : $("#inqu_status-"+index+"-lateDays").val(),
				"isRuning" : getCheckValue("isRuning","",index),
				"smsRecvCode" : getCheckValue("","person",index)
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

