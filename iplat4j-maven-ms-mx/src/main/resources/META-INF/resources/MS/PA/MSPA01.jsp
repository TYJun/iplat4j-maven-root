<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="系统分类" fitHeight="true">
			<EF:EFTree id="tree01" valueField="label" textField="text"
					   hasChildren="leaf" serviceName="MSPL02" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
			<EF:EFRegion id="inqu" title="查询条件" showClear="true">
				<div class="row">
					<EF:EFInput ename="inqu_status-0-Staffid" cname="工号" />
					<EF:EFInput ename="inqu_status-0-Name_" cname="姓名" />
					<EF:EFInput ename="inqu_status-0-parentId" cname="" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="联系人信息" >
				<EF:EFGrid blockId="result" autoDraw="no" checkMode="single">
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
					<EF:EFColumn ename="staffid" cname="工号" width="100" disabled="true"/>
					<EF:EFColumn ename="name_" cname="姓名" width="100" disabled="true"/>
					<EF:EFColumn ename="tel" cname="移动电话" width="100" />
					<EF:EFColumn ename="email" cname="电子邮件" width="100" />
					<EF:EFColumn ename="grade_filter" cname="报警类别" width="100" disabled="true"/>
					<EF:EFColumn ename="create_by" cname="创建人" width="100" disabled="true"/>
					<EF:EFColumn ename="create_date" cname="创建时间" width="100" disabled="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
</EF:EFPage>

<EF:EFWindow id="popData2" url=" " lazyload="true" width="960px"
			 height="700px">
</EF:EFWindow>
<EF:EFWindow id="popData3" url=" " lazyload="true" width="960px"
             height="700px">
</EF:EFWindow>
<script type="text/javascript">
    function detailed(){
        // 打开工作流节点人员选择弹窗
        popData2Window.open().center();
    }
	function popData3(id,tel,email) {
		var inInfo = new EiInfo();
		inInfo.set("id",id);
		inInfo.set("tel",tel);
		inInfo.set("email",email);
		if(!(/^1[3456789]\d{9}$/.test(tel))){
			IPLAT.alert("手机号码有误，请重填")
			return false;
		}
		EiCommunicator.send("MSPA0101","update", inInfo, {
			onSuccess: function(response){
				if (response.getMsg() == "修改成功") {
					IPLAT.alert("修改成功！！");
					resultGrid.dataSource.query(1);
				}
				if (response.getMsg() == "电话不可为空") {
					IPLAT.alert("电话不可为空！");
				}
				if(!(/^1[3456789]\d{9}$/.test(tel))){
					IPLAT.alert("手机号码有误，请重填");
				}
				if (response.getMsg() == "电话必须是11位") {
					IPLAT.alert("电话必须是11位！");
				}
				if (response.getMsg() == "邮箱不对") {
					IPLAT.alert("邮箱格式不正确！");
				}
			},
			onFail: function(errorMsg) {
				console.log(errorMsg);
			}
		}, {async: true});
	}
	var datagrid = null;
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
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
		}
	}
	//弹窗
	function popData(pid) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSPA0102?initLoad&pid=" + pid;
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

    //弹窗
    function popData4(workNo,name,contactTel,pid) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSPA0102?initLoad&staffid=" + workNo+
            "&name_=" + name + "&tel=" + contactTel+ "&pid="+pid;
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

	$(function() {
		//新增按钮
		$("#ADD").on("click", function(e) {
			popData($("#inqu_status-0-parentId").val());
		});

		//提交按鈕
		$("#SUBMIT").on("click",function (e){
			if ( $(".check-one").is(':checked')==false) {
				IPLAT.alert("请先选择一条记录进行修改");
			}else {
				popData3(datagrid.id,datagrid.tel,datagrid.email);
			}
		})

		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			var parentId=$("#inqu_status-0-parentId").val();
			document.getElementById("inqu-trash").click();
			$("#inqu_status-0-parentId").val(parentId);
			resultGrid.dataSource.query(1);
		});
	})
</script>
