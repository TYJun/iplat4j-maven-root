<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    .two{
        margin-top: 50px;
    }
</style>
<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
        <div>
		<EF:EFInput ename="classifyName" cname="分类名称：" bindId="classifyName"
					colWidth="5" ratio="4:8" type="text" required="true"
					placeholder="请输入分类名称" />
		<EF:EFInput ename="classifyCode" cname="排序编号：" bindId="classifyCode"
					colWidth="5" ratio="4:8" type="text" required="true"
					placeholder="请输入排序编号"  oninput="value=value.replace(/[^\d]/g,'')" />
			<EF:EFSelect ename="usergroupId" cname="部门："
						 resultId="worktypeName" textField="groupCname"
						 valueField="groupId" serviceName="MSPL0201"
						 methodName="queryWorktype" optionLabel="请选择" colWidth="5"
						 ratio="4:8" filter="contains" bindId="usergroupId"
						 id="usergroupId">
			</EF:EFSelect>
        </div>
		<EF:EFInput ename="type" cname="" width="1" />
		<EF:EFInput ename="id" cname="" width="1" />
		<EF:EFInput ename="parentId" cname="" width="1" />
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>
<script type="text/javascript">
    var datagrid = null;

    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result" : {
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
    //弹窗
    function popData(id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSPL0202?initLoad&id=" + id;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popDataWindow.open().center();
    }
    $(function() {
		$("#type").css("display", "none");
		$("#id").css("display", "none");
		$("#parentId").css("display", "none");
		var type = $("#type").val();
        var parentId = $("#parentId").val();
        if (type=="addType") {
            $("#VIEW").css("display", "none");
        }
        if (parentId!= "root") {
            // $(".two").css("display", "none");
            $("#VIEW").css("display", "none");
        }
		$("#SUBMIT").on("click", function (e) {
            $("#SUBMIT").attr("disabled",true);
		    console.log($("#parentId").val());
			var node = $('#MSPL0201');
			var str = $("#classifyName").val();
			var str1 = $("#classifyCode").val();
			if(str == null || str ==""){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("分类名称不可为空")
			}else if(str1 == null || str1 ==""){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("分类编号不可为空")
			}else{
				IPLAT.submitNode(node, 'MSPL02', 'insert', {
					onSuccess : function(eiInfo) {
						if(eiInfo.getStatus()==0) {
							IPLAT.alert(eiInfo.getMsg());
                            $("#SUBMIT").attr("disabled",false);
						}else {
							window.parent.resultGrid.dataSource.page(1);
							window.parent['popDataWindow'].close();
                            window.parent.frames.reload();
						}
					},
					onFail : function(msg, status, e) {

					}
				});
			}
		});
        $("#VIEW").click(function() {
            var id = $("#id").val();
            popData(id);
		})
	});
</script>

