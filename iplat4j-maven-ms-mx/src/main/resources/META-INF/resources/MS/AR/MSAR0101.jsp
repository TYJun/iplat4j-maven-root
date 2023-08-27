<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
        <div class="row" style="line-height: 50px">
            <EF:EFInput ename="code" cname="规则代码" bindId="code"
                        colWidth="5" ratio="4:8" type="text" readonly="true" />
            <EF:EFCascadeSelect required="true" ename="dimension" autoBind="true" bindId="dimension"
                        cname="计量量纲" textField="label" valueField="value"
                        methodName="getMap" serviceName="MSAR0101" resultId="parentMap" ratio="4:8" colWidth="5">
                <EF:EFOption label="请选择" value=""/>
            </EF:EFCascadeSelect>
        </div>
        <div class="row" style="line-height: 50px">
            <EF:EFCascadeSelect required="true" ename="unit" bindId="unit"
                                cascadeFrom="dimension"
                                cname="计量单位" textField="label" valueField="value"
                                methodName="getChildMap" serviceName="MSAR0101" resultId="childMap" ratio="4:8" colWidth="5">
            </EF:EFCascadeSelect>
            <EF:EFInput ename="description" required="true" cname="规则说明" bindId="description"
                        colWidth="5" ratio="4:8" type="text" />
        </div>
        <div class="row" style="line-height: 50px">
            <EF:EFInput ename="lower" cname="低报警（较重告警）" bindId="lower"
                        colWidth="5" ratio="4:8" type="number" />
            <EF:EFInput ename="lowerLower" cname="低低报警（严重告警）" bindId="lowerLower"
                    colWidth="5" ratio="4:8" type="number" />
        </div>
        <div class="row" style="line-height: 50px">
            <EF:EFInput ename="upper" cname="高报警（较重告警）" bindId="upper"
                        colWidth="5" ratio="4:8" type="number" />
            <EF:EFInput ename="upperUpper" cname="高高报警（严重告警）" bindId="upperUpper"
                    colWidth="5" ratio="4:8" type="number" />
        </div>
        <div class="row" style="line-height: 50px">
            <EF:EFInput ename="remarks" cname="备注" bindId="remarks"
                colWidth="5" ratio="4:8" type="text" />
        </div>
        <EF:EFInput ename="type" cname="" width="1" />
        <EF:EFInput ename="id" cname="" width="1" />
		<div style="height: 50px; line-height: 50px"></div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	$(function() {
		$("#type").css("display", "none");
		$("#parent").css("display", "none")
		$("#id").css("display", "none");
		var s = $("#type").val();
		$("#SUBMIT").click(function() {
            $("#SUBMIT").attr("disabled","true");
			var node = $('#MSAR0101');
			var str = $("#dimension").val();
			var str1 = $("#unit").val();
            var str2 = $("#description").val();
			//var str2 = $("#dataGroupName").val();
			if(str == null || str ==""){
                $("#SUBMIT").attr("disabled","false");
				IPLAT.alert("请选择计量量纲");
                return false;
			}
            if(str1 == null || str1 =="") {
                $("#SUBMIT").attr("disabled","false");
                IPLAT.alert("请选择计量单位");
                return false;
            }
            if(str2==null || str2=='') {
                $("#SUBMIT").attr("disabled","false");
                IPLAT.alert("规则说明不可为空");
                return false;
            }
            IPLAT.submitNode(node, 'MSAR01', 'insert', {
                onSuccess : function(eiInfo) {
                    if (eiInfo.getMsg() == "高报警不得大于高高报警") {
                        IPLAT.alert("高报警不得大于高高报警");
                        $("#SUBMIT").attr("disabled","false");
                        return false;
                    }
                    if (eiInfo.getMsg() == "低报警不得小于低低报警") {
                        IPLAT.alert("低报警不得小于低低报警");
                        $("#SUBMIT").attr("disabled","false");
                        return false;
                    }
                    if (eiInfo.getMsg() == "低报警不得大于高报警") {
                        IPLAT.alert("低报警不得大于高报警");
                        $("#SUBMIT").attr("disabled","false");
                        return false;
                    }
                    if (eiInfo.getMsg() == "新增成功"){
                     //   NotificationUtil("新增成功", "");
                        //关闭弹窗
                        window.parent.resultGrid.dataSource.page(1);
                        window.parent['popDataWindow'].close();
                    }
                    if (eiInfo.getMsg() == "修改成功"){
                    //    NotificationUtil("修改成功", "");
                        //关闭弹窗
                        window.parent.resultGrid.dataSource.page(1);
                        window.parent['popDataWindow'].close();
                    }
                },
                onFail : function(errorMsg, status, e) {
                    NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
                }
            });

			
		});
		
		$("#CANCEL").click(function() {
			window.parent['popDataWindow'].close();
		})
	});
</script>

