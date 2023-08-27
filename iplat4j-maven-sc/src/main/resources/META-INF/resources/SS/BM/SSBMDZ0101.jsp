<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFSelect ename="building" cname="楼"
					 resultId="building" textField="building" valueField="deptName"
					 serviceName="SSBMDZ01" methodName="queryBuilding" optionLabel="请选择"
					 colWidth="4" filter="contains">
		</EF:EFSelect>
		<EF:EFSelect ename="floor" cname="层"
					 resultId="floor" textField="floor" valueField="floor"
					 serviceName="SSBMDZ01" methodName="queryFloor" optionLabel="请选择"
					 colWidth="4" filter="contains">
		</EF:EFSelect>
		<EF:EFInput ename="address" cname="地址" />
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	$(function() {
		$("#SUMBMIT").click(function() {
			var node = $('#SSBMDZ0101');
			var building = $("#building").val();
			var floor = $("#floor").val();
			var address = $("#address").val();
			var inInfo = new EiInfo();
			inInfo.set("building", building);
			inInfo.set("floor", floor);
			inInfo.set("address", address);
			if (building == null || building == "") {
				IPLAT.alert("楼不能不可为空")
			} else if(floor == null || floor == ""){
				IPLAT.alert("层不能不可为空")
			}else if(address == null || address == ""){
				IPLAT.alert("地址不能不可为空")
			}else {
				IPLAT.submitNode(node, 'SSBMDZ0101', 'insert',{
					onSuccess : function(eiInfo) {
						if(eiInfo.getStatus()==-1){
							NotificationUtil(eiInfo.getMsg());
						}else{
							NotificationUtil("新增成功", "");
							window.parent.resultGrid.dataSource.page(1);
							//关闭弹窗
							window.parent['popDataWindow'].close();
						}
					},
					onFail : function(errorMsg, status, e) {
						NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
					}
				});
			}
		});
	});
</script>

