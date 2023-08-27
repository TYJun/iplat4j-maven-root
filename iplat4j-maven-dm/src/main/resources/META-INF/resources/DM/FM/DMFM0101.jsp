<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>

	<EF:EFRegion id="result" title="信息" >
		<EF:EFInput ename="id" type="hidden" />
		<!-- <div class="row"></div> -->
			<EF:EFInput ename="id" cname="id" type="hidden" />
		    <EF:EFPopupInput ename="roomId" cname="宿舍地址" colWidth="6"
		            popupType="ServiceGrid"
		            popupTitle="宿舍楼层信息"
		            required="true" 
                    serviceName="DMFM10" methodName="querySpot" resultId="spot" 
                    autofit="true"
                    valueField="roomId" textField="roomName"
                    columnEnames="roomName,buildingCode,floorCode,roomNo,rent" 
                    columnCnames="宿舍地址,楼,层,房间号,月租费(元)"
					filterPopupGrid="false"/>
            <EF:EFInput ename="roomName" colWidth="6" cname="宿舍地址"  center="true" type="hidden" />
			<EF:EFInput ename="buildingCode" colWidth="5" cname="楼"  center="true"/>
			<EF:EFInput ename="floorCode" colWidth="6" cname="层" />
			<EF:EFInput ename="roomNo" colWidth="5" cname="房间号"  center="true"/>			
			<EF:EFInput ename="rent" cname="月租费（元）" colWidth="6" />

			<EF:EFInput ename="creator" cname="创建人" colWidth="5" type="text"
				required="true" readonly="true" />
			<%--
			<EF:EFInput ename="lastElec" cname="上月用电量（度）" colWidth="6" type="text"
				required="true" readonly="false" /> --%>
			<EF:EFInput ename="lastElecfee" cname="上月电费（元）" colWidth="6"
				type="text" required="true" readonly="false" />
			<%--
			<EF:EFInput ename="lastWater" cname="上月用水量（吨）" colWidth="6" type="text"
				required="true" readonly="false" />
			--%>
			<EF:EFInput ename="lastWaterfee" cname="上月水费（元）" colWidth="5"
				type="text" required="true" readonly="false" />
			<%--
			<EF:EFInput ename="monthElec" cname="本月用电量（度）" colWidth="6" type="text"
				required="true" readonly="false" />
				--%>
			<EF:EFInput ename="elecFee" cname="本月电费（元）" colWidth="6" type="text"
				required="true" readonly="false" />
			<%--
			<EF:EFInput ename="monthWater" cname="本月用水量（吨）" colWidth="6" type="text"
				required="true" readonly="false" />
				--%>
			<EF:EFInput ename="waterFee" cname="本月水费（元）" colWidth="5" type="text"
				required="true" readonly="false" />

			<EF:EFInput ename="hospitalManageFee" cname="医院管理费（元）" colWidth="6" type="text"
					required="true" readonly="false" />
			<EF:EFInput ename="propertyManageFee" cname="物业管理费（元）" colWidth="5" type="text"
					required="true" readonly="false" />

			<EF:EFInput ename="networkFee" cname="网费（元）" colWidth="6" type="text"
					required="true" readonly="false" />

			<EF:EFInput ename="createrTime" cname="创建时间" colWidth="5" type="text"
				required="true" readonly="true" />
			<EF:EFInput ename="buiidtime" cname="生成时间" colWidth="6" type="text"
				required="true" readonly="true" />

			<EF:EFInput ename="remark" cname="备注" colWidth="5" type="text"/>
			<EF:EFInput ename="type" cname="" width="80%" />
		</div>
	</EF:EFRegion>

</EF:EFPage>

<script type="text/javascript">
	$(function() {
		console.log(__ei);
		$("#CANCEL").click(function() {
			window.parent['popDataWindow'].close();
		});

		IPLAT.EFPopupInput.text($('#roomId'),__ei.roomName);

		$("#type").css("display", "none");

		$("#SUBMIT").click(function() {

			$("#SUBMIT").attr("disabled",true);
			setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);

			var node = $('#DMFM0101');
			console.log(node);
			console.log($("#employeeId").val())
			debugger;
		/*	var eiInfo = new EiInfo();
			var type = IPLAT.EFInput.value($("#type"));
			var roomId = IPLAT.EFInput.value($("#roomId"));
			var lastElec = IPLAT.EFInput.text($("#lastElec"));
			var lastElecfee = IPLAT.EFInput.text($("#lastElecfee"));
			var lastWater = IPLAT.EFInput.value($("#lastWater"));
			var lastWaterfee = IPLAT.EFInput.value($("#lastWaterfee"));
			var monthElec = IPLAT.EFInput.value($("#monthElec"));
			var elecFee = IPLAT.EFInput.value($("#elecFee"));
			var monthWater = IPLAT.EFInput.value($("#monthWater"));
			var waterFee = IPLAT.EFInput.value($("#waterFee"));

			eiInfo.set("roomId",roomId);
			eiInfo.set("lastElec",lastElec);
			eiInfo.set("lastElecfee",lastElecfee);
			eiInfo.set("lastWater",lastWater);
			eiInfo.set("lastWaterfee",lastWaterfee);
			eiInfo.set("monthElec",monthElec);
			eiInfo.set("elecFee",elecFee);
			eiInfo.set("monthWater",monthWater);
			eiInfo.set("waterFee",waterFee);*/

			if(!validate(node)){
				return;
			}
			IPLAT.submitNode(node, 'DMFM0101', 'insert', {
					onSuccess : function(eiInfo) {
						NotificationUtil(eiInfo.getMsg());
						//关闭弹窗
						window.parent['popDataWindow'].close();
						//resultGrid.dataSource.page(1);
						setTimeout(function() {
							window.parent.location.reload()
						}, 600);
					},


					onFail : function(errorMsg, status, e) {
						NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
						//resultGrid.dataSource.page(1);
					}

				});
			//}

		});

	});
	function validate(node){
		if(isEmpty("node")){
			Notification("请检查信息是否填写完整","error");
            return  false;
		}
		return true;
	}

	function isEmpty(parameter){
		if(parameter == undefined || parameter == null){
			return true;
		/*} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;*/
		} else {
			return false;
		}
	}
	/**
	 * 测试专用
	 @parm vals 变量数组
	 */

	function printVal(vals) {
		for (var i = 0; i < vals.length; i++) {
			console.log(vals[i]);
		}
	}
</script>