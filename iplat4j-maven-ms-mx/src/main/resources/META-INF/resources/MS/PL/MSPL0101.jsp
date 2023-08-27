<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
	#SUBMIT,#ADDTAB,#UP,#DELTAB {
		margin-top: 20px;
	}
</style>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-value" cname="参数项Tag" />
		</div>
	</EF:EFRegion>
	<div id="dyi">
		<EF:EFRegion id="result" title="参数项信息">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single">
				<EF:EFColumn ename="name" cname="参数项名称" width="200" disabled="true"/>
				<EF:EFColumn ename="value" cname="参数项Tag" width="200" disabled="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	<div id="der" style="display: none">
		<EF:EFRegion id="results" title="参数项信息" fitHeight="true">
			<div class="row" style="line-height: 50px">
				<EF:EFSelect ename="type" cname="参数项类型" colWidth="5" required="true" width="100">
                    <EF:EFOption label="请选择" value="root" />
                    <EF:EFOption label="开关量" value="1" />
                    <EF:EFOption label="枚举量" value="2" />
                    <EF:EFOption label="模拟量" value="3" />
				</EF:EFSelect>
				<EF:EFInput ename="tag" cname="参数项Tag" bindId="tag" colWidth="5"
							ratio="4:8" type="text" readonly="true"/>
				<div id="sfb" style="display: none">
				<EF:EFInput ename="significantBit" cname="有效位" colWidth="5" ratio="4:8" type="text"/>
				</div>
				<EF:EFSelect ename="isSync" cname="是否启用到大屏" colWidth="5" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="禁用" value="0" />
				</EF:EFSelect>
			</div>
			<div class="row" style="line-height: 50px">
				<EF:EFInput ename="name" cname="参数项名称" bindId="name" colWidth="5"
							ratio="4:8" type="text" required="true"/>
				<EF:EFInput ename="description" cname="参数项描述" bindId="description" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" style="line-height: 50px" id="truthValue">
				<EF:EFInput ename="trueValueLabel" cname="真值" bindId="trueValueLabel" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="falseValueLabel" cname="假值" bindId="falseValueLabel" colWidth="5"
							ratio="4:8" type="text"/>
                <EF:EFSelect ename="alarmValue" cname="报警值" colWidth="5" width="100">
                    <EF:EFOption label="请选择" value="" />
                    <EF:EFOption label="真值" value="1" />
                    <EF:EFOption label="假值" value="0" />
                </EF:EFSelect>
			</div>
			<div class="row" style="line-height: 50px" id="metering">
				<EF:EFCascadeSelect required="true" ename="dimension" autoBind="true" bindId="dimension"
									cname="计量量纲" textField="label" valueField="value"
									methodName="getMap" serviceName="MSAR0101" resultId="parentMap" ratio="4:8" colWidth="5">
					<EF:EFOption label="请选择" value=""/>
				</EF:EFCascadeSelect>
				<EF:EFCascadeSelect required="true" ename="unit" bindId="unit"
									cascadeFrom="dimension"
									cname="计量单位" textField="label" valueField="value"
									methodName="getChildMap" serviceName="MSAR0101" resultId="childMap" ratio="4:8" colWidth="5">
				</EF:EFCascadeSelect>
			</div>
			<div class="row" style="line-height: 50px" id="CallThePolice">
				<EF:EFSelect ename="alarmEnableStatus" cname="报警启用" colWidth="5" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="禁用" value="0" />
				</EF:EFSelect>
			</div>
			<div class="row" style="line-height: 50px" id="ParameterItem">
				<EF:EFSelect ename="paramEnableStatus" cname="参数项启用" colWidth="5" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="禁用" value="0" />
				</EF:EFSelect>
				<EF:EFInput ename="deadTime" cname="死区时间（秒）" bindId="deadTime" colWidth="5"
							ratio="4:8" type="Number" min="5"/>
			</div>
			<div class="row" style="line-height: 50px" id="Log">
				<EF:EFSelect ename="isWriteLog" cname="是否启用记录日志" colWidth="5" width="100">
					<EF:EFOption label="请选择" value="" />
					<EF:EFOption label="启用" value="1" />
					<EF:EFOption label="禁用" value="0" />
				</EF:EFSelect>
			</div>
			<div class="row" id="MJ1" style="line-height: 50px">
				<EF:EFInput ename="enumValue01" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue01Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" id="MJ2" style="line-height: 50px;">
				<EF:EFInput ename="enumValue02" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue02Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" id="MJ3" style="line-height: 50px">
				<EF:EFInput ename="enumValue03" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue03Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" id="MJ4" style="line-height: 50px">
				<EF:EFInput ename="enumValue04" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue04Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" id="MJ5" style="line-height: 50px">
				<EF:EFInput ename="enumValue05" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue05Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<div class="row" id="MJ6" style="line-height: 50px">
				<EF:EFInput ename="enumValue06" cname="枚举值" colWidth="5"
							ratio="4:8" type="text"/>
				<EF:EFInput ename="enumValue06Label" cname="显示名称" colWidth="5"
							ratio="4:8" type="text"/>
			</div>
			<EF:EFInput ename="types" cname="" width="1" />
			<EF:EFInput ename="id" cname="" width="1" />
			<EF:EFInput ename="parentId" cname="" width="1" />
		</EF:EFRegion>
	</div>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
			 height="70%">
</EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
    IPLATUI.EFSelect = {
        "type": {
            change: function (e) {
                if (this.value()==0){
                    $("#truthValue").css("display", "none");   //真假值
                    $("#metering").css("display", "none");    //计量隐藏
                    $("#CallThePolice").css("display", "block");  //报警
                    $("#ParameterItem").css("display", "block");  //参数项
					$("#sfb").css("display", "none");  //有效值
                    $("#Log").css("display", "block");  //日志
                    $("#MJ1").css("display", "none");  //枚举1
                    $("#MJ2").css("display", "none");  //枚举2
                    $("#MJ3").css("display", "none");  //枚举3
                    $("#MJ4").css("display", "none");  //枚举4
                    $("#MJ5").css("display", "none");  //枚举5
                    $("#MJ6").css("display", "none");  //枚举6
                }else if (this.value()==1){
                    $("#truthValue").css("display", "block");   //真假值
                    $("#metering").css("display", "none");    //计量隐藏
                    $("#CallThePolice").css("display", "block");  //报警
                    $("#ParameterItem").css("display", "block");  //参数项
					$("#sfb").css("display", "none");  //有效值
                    $("#Log").css("display", "block");  //日志
                    $("#MJ1").css("display", "none");  //枚举1
                    $("#MJ2").css("display", "none");  //枚举2
                    $("#MJ3").css("display", "none");  //枚举3
                    $("#MJ4").css("display", "none");  //枚举4
                    $("#MJ5").css("display", "none");  //枚举5
                    $("#MJ6").css("display", "none");  //枚举6
                }else if (this.value()==2){
                    $("#truthValue").css("display", "none");   //真假值
                    $("#metering").css("display", "none");    //计量隐藏
                    $("#CallThePolice").css("display", "block");  //报警
                    $("#ParameterItem").css("display", "block");  //参数项
					$("#sfb").css("display", "none");  //有效值
                    $("#Log").css("display", "block");  //日志
                    $("#MJ1").css("display", "block");  //枚举1
                    $("#MJ2").css("display", "block");  //枚举2
                    $("#MJ3").css("display", "block");  //枚举3
                    $("#MJ4").css("display", "block");  //枚举4
                    $("#MJ5").css("display", "block");  //枚举5
                    $("#MJ6").css("display", "block");  //枚举6
                }else if (this.value()==3){
                    $("#truthValue").css("display", "none");   //真假值
                    $("#metering").css("display", "block");    //计量隐藏
                    $("#CallThePolice").css("display", "block");  //报警
                    $("#ParameterItem").css("display", "block");  //参数项
					$("#sfb").css("display", "block");  //有效值
                    $("#Log").css("display", "block");  //日志
                    $("#MJ1").css("display", "none");  //枚举1
                    $("#MJ2").css("display", "none");  //枚举2
                    $("#MJ3").css("display", "none");  //枚举3
                    $("#MJ4").css("display", "none");  //枚举4
                    $("#MJ5").css("display", "none");  //枚举5
                    $("#MJ6").css("display", "none");  //枚举6
                }else {
                    console.log("2222222222222222");
                    $("#truthValue").css("display", "none");   //真假值
                    $("#metering").css("display", "none");    //计量隐藏
                    $("#CallThePolice").css("display", "none");  //报警
                    $("#ParameterItem").css("display", "none");  //参数项
					$("#sfb").css("display", "none");  //有效值
                    $("#Log").css("display", "block");  //日志
                    $("#MJ1").css("display", "none");  //枚举1
                    $("#MJ2").css("display", "none");  //枚举2
                    $("#MJ3").css("display", "none");  //枚举3
                    $("#MJ4").css("display", "none");  //枚举4
                    $("#MJ5").css("display", "none");  //枚举5
                    $("#MJ6").css("display", "none");  //枚举6
                }
            }
        }
    }
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
		//	pageable:false,
			checkMode:"single",
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}else{
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		},

	}
	$(function() {
        $("#truthValue").css("display", "none");   //真假值
        $("#metering").css("display", "none");    //计量隐藏
        $("#CallThePolice").css("display", "none");  //报警
        $("#ParameterItem").css("display", "none");  //参数项
        $("#Log").css("display", "block");  //日志
        $("#MJ1").css("display", "none");  //枚举1
        $("#MJ2").css("display", "none");  //枚举2
        $("#MJ3").css("display", "none");  //枚举3
        $("#MJ4").css("display", "none");  //枚举4
        $("#MJ5").css("display", "none");  //枚举5
        $("#MJ6").css("display", "none");  //枚举6
		//查询按钮
		$("#QUERY").on("click",function (e){
			resultGrid.dataSource.page(1);
		})

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			var parentId=$("#inqu_status-0-parentId").val();
			document.getElementById("inqu-trash").click();
			$("#inqu_status-0-parentId").val(parentId);
			resultGrid.dataSource.page(1);
		});

		$("#types").css("display", "none");
		$("#parentId").css("display", "none")
		$("#id").css("display", "none");
		var v = 1;
	    //下一步
        $("#NEXT").on("click", function (e) {
            if ($(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录");
            } else {
                $("#inqu").css("display", "none");
                $("#dyi").css("display","none");
                $("#der").css("display", "block");
                $("#name").val(datagrid.name);
                $("#tag").val(datagrid.value);
            }
        })
		$("#UP").on("click", function (e) {
            $("#inqu").css("display", "block");
			$("#dyi").css("display","block");
			$("#der").css("display", "none");
			$("#tag").val("");
		})
		$("#SUBMIT").on("click", function (e) {

            $("#SUBMIT").attr("disabled","true");
            var node = $('#MSPL0101');
			var type = $("#type").val();
            var name = $("#name").val();
			var dimension = $("#dimension").val();
			if(type=="root") {
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("请选择参数项类型");
				return false;
			}
            if(name=='') {
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("请输入参数项名称");
                return false;
            }
            if(type== "3" || type== "4") {
                if(dimension == "") {
                    $("#SUBMIT").attr("disabled",false);
                    IPLAT.alert("请选择计量量纲");
                    return false;
                }
            }
			IPLAT.submitNode(node, 'MSPL01', 'insert', {
				onSuccess : function(eiInfo) {
					NotificationUtil(eiInfo.getMsg(), "");
					//关闭弹窗
					setTimeout(function (){window.parent.resultGrid.dataSource.page(1)},1500) ;
					window.parent['popDataWindow'].close();

				},
				onFail : function(errorMsg, status, e) {
					NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
				}
			});

		})

	});
</script>

