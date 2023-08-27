<%@ page import="com.baosight.iplat4j.core.web.threadlocal.UserSession" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.checkbox-box{
		position: relative;
		left: 323px;
	}
	.row {
		position: relative;
		top: 16px;
		left: -100px;
	}
	#aaa {
		display: inline-block;
		position: absolute;
		left: 901px;
		top: 0px;
		z-index: 1;
		width: 80px;
	}
	body{
		width: 100%;
	}
</style>
<EF:EFPage >
	<EF:EFRegion id="result" title="新增" fitHeight="true" >
		<div class="row">
            <EF:EFInput ename="staffid" cname="员工工号(选填)" colWidth="5" bindId="staffid" />
            <div id="aaa"></div>
			    <EF:EFInput ename="name_" cname="姓名" colWidth="5" required="true"  bindId="name_"/>
			    <EF:EFInput ename="tel" cname="移动电话" colWidth="5"  required="true"  bindId="tel"/>
			    <EF:EFInput ename="email" cname="电子邮件(选填)" colWidth="5" />
			    <EF:EFInput ename="create_by" cname=""  />
			    <EF:EFInput ename="create_date" cname="" />
			    <EF:EFInput ename="pid" cname="" />
			    <EF:EFInput ename="grade_filter" cname="" />
			<div class="checkbox-box">
				<EF:EFInput  inline="true" value="1"  type="checkbox" ename="grade_filter" cname="低报警" colWidth="5"/>
				<EF:EFInput  inline="true" value="2" type="checkbox"  ename="grade_filter" cname="低低报警"  colWidth="5" />
				<EF:EFInput  inline="true" value="3"  type="checkbox" ename="grade_filter" cname="高报警"  colWidth="5" />
				<EF:EFInput  inline="true" value="4"  type="checkbox" ename="grade_filter" cname="高高报警" colWidth="5" />
			</div>
<%--			<EF:EFSelect   bindId="grade_filter" resultId="grade_filter" optionLabel="请选择" ename="grade_filter"  row="0" cname="报警等级过滤" >--%>
<%--				<EF:EFCodeOption codeName="znjk.ms.grade"  textField="label" valueField="value"/>--%>
<%--			</EF:EFSelect>--%>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData2" url=" " lazyload="true" width="600px"
			 height="500px">
</EF:EFWindow>
<script type="text/javascript">
	var datagrid = null;
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				console.log("1111111")
				console.log(e)
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
		}
	}
	$(function() {
		$("#pid").css("display", "none");
		$("#grade_filter").css("display", "none");
		$("#create_by").css("display", "none");
		$("#create_date").css("display", "none");
		$("#SUBMIT").click(function() {
            $("#SUBMIT").attr("disabled",true);
			var node = $('#MSPA0102');
			var name_ = $("#name_").val().trim();
			var tel = $("#tel").val().trim();
			var arr = Object.keys(tel);
			if(name_ == null || name_ ==""){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("姓名不可为空")
				return false;
			}else if(tel == null || tel ==""){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("移动电话不可为空")
				return false;
			}else if(arr.length!=11){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("移动电话必须11位！！")
				return false;
			}else if(!(/^1[3456789]\d{9}$/.test(tel))){
                $("#SUBMIT").attr("disabled",false);
				IPLAT.alert("手机号码有误，请重填")
				return false;
			}else {
				IPLAT.submitNode(node, 'MSPA0102', 'insert', {
					onSuccess: function (eiInfo) {
						if (eiInfo.getMsg() == "新增成功") {
							IPLAT.alert("新增成功！！");
							//关闭弹窗
							window.parent['popData2Window'].close();
							window.parent['resultGrid'].dataSource.query(1);
						}
						if (eiInfo.getMsg() == "新增失败") {
							IPLAT.alert("新增失败   员工工号相同！！");
							$("#SUBMIT").attr("disabled",false);
						}
						if (eiInfo.getMsg() == "复选框必选") {
							IPLAT.alert("复选框至少选择一个");
							$("#SUBMIT").attr("disabled",false);
						}
						if (eiInfo.getMsg() == "邮箱不对") {
							IPLAT.alert("邮箱格式不正确");
							$("#SUBMIT").attr("disabled",false);
						}
					},
					onFail: function (errorMsg) {
						NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
					}
				});
			}
		});

		$("#CANCEL").click(function() {
			window.parent['popData2Window'].close();
		})
		//插入
		$("#aaa").on("click", function(e) {
			popData2($("#pid").val());
		});
	})
	//弹窗
	function popData2(pid) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSPA0103?initLoad&pid="+pid;
		var popData2 = $("#popData2");
		popData2.data("kendoWindow").setOptions({
			open: function () {
				popData2.data("kendoWindow").refresh({
					url: url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popData2Window.open().center();
	}
</script>