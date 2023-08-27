<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result" title="修改供应商" fitHeight="true" >
		<EF:EFInput ename="id" cname="主键" type="hidden" />
		<div class="row">
			<EF:EFInput ename="supplierName" cname="供应商名称" required="true" maxLength="100" />
			<EF:EFInput ename="contacts" cname="联系人" maxLength="50" />
			<EF:EFSelect ename="supplierType" cname="供应商类型" >
				<EF:EFCodeOption codeName="wilp.ac.su.type" textField="label" valueField="value"/>
			</EF:EFSelect>
		</div>

		<div class="row" >
			<EF:EFInput ename="contactNumber" cname="联系电话" data-rules="number" maxLength="20"/>
			<EF:EFInput ename="contactEmail" cname="联系邮箱"  data-rules="email" maxLength="36" />
			<EF:EFInput ename="zipCode" cname="邮政编码" data-rules="postcode"/>

		</div>
		<div class="row">
			<EF:EFInput ename="legalRepresentative" cname="法人代表" maxLength="36"/>
			<EF:EFInput ename="icrNo" cname="工商注册号"  colWidth="4" maxLength="100"/>
			<EF:EFInput ename="abbreviation" cname="供应商简称"  colWidth="4" maxLength="36"/>
		</div>
		<div class="row">
			<EF:EFInput ename="hrpCode" cname="HRP编码"  colWidth="4" maxLength="50"/>
		</div>
		<hr/>
		<div class="row">
			<EF:EFInput ename="businessScope" cname="经营范围" type="textarea" placeholder="不能超过500字" rows="3" colWidth="12" ratio="1:11" maxLength="500" />
		</div>
		<div class="row">
			<EF:EFInput ename="contactAddress" cname="联系地址" type="textarea" placeholder="不能超过200字" rows="3" colWidth="12" ratio="1:11" maxLength="200"/>
		</div>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	var oldSupplierName = IPLAT.EFInput.value($("#supplierName"));
	$(function() {
		var validator = IPLAT.Validator({
			id: "result"
		});

		$("#SUBMIT").on("click", function (e) {
			//参数校验
			if(!validator.validate()){
				return;
			}

			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			eiInfo.setByNameValue("oldSupplierName", oldSupplierName);

			EiCommunicator.send("ACSU0102", "update", eiInfo, {
				onSuccess : function(ei) {
					if (ei.getStatus() === 0){
						NotificationUtil(ei.getMsg(), "success");
						window.parent['deitPopDataWindow'].close();
						window.parent.resultGrid.dataSource.page(1);
					}else{
						NotificationUtil(ei.getMsg(), "error");
					}
				}
			});
		});
	});
</script>
