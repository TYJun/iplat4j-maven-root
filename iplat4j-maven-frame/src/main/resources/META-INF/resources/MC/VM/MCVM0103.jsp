<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="curVarId" cname="当前变量ID"  type="hidden"/>
			<EF:EFInput ename="workNo" cname="工号" colWidth="3" />
			<EF:EFInput ename="name" cname="姓名" colWidth="3" />
			<EF:EFInput ename="deptName" cname="所属科室" colWidth="3" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员列表">

		<EF:EFGrid blockId="result" autoDraw="no" serviceName="ACPE01"  readonly="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
			<EF:EFColumn ename="workNo" cname="工号" width="70" />
			<EF:EFColumn ename="name" cname="姓名" width="100" />
			<EF:EFColumn ename="gender" cname="性别" width="50" />
			<EF:EFColumn ename="deptName" cname="所属科室" width="100" />
			<EF:EFColumn ename="contactTel" cname="联系电话" width="100" />
			<EF:EFColumn ename="staffType" cname="员工类型" width="100" />
			<EF:EFColumn ename="isService" cname="服务人员" width="100" />
			<EF:EFColumn ename="isStatus" cname="是否停用" width="100" />
			<EF:EFColumn ename="dataGroupName" cname="所属院区" width="100" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

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
		$("#SAVE").on("click", function (e) {
			var checkRows = resultGrid.getCheckedRows();
			if(checkRows.length<1){
				NotificationUtil("请勾选要添加的人员","error");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("curVarId",$("#curVarId").val());
			IPLAT.confirm({
				message:"确定提交选中行?",
				okFn:function(e){

					EiCommunicator.send("MCVM0103", "insert", info, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							window.parent.resultPerGrid.dataSource.page(1);
							window.parent['popDataPerWindow'].close();
						}
					});
				}
			});
		});
	});
</script>
