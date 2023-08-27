<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result" title="新增人员" fitHeight="true" >
    
		<div class="row">
		    <EF:EFInput ename="workNo" cname="工号"    required="true"/>
			<EF:EFInput ename="name" cname="姓名"    required="true"/>
			<EF:EFSelect ename="gender" cname="性别" >
				<EF:EFOption label="男" value="1"/>
				<EF:EFOption label="女" value="0"/>
			</EF:EFSelect>

		</div>
		<div class="row">
			<EF:EFInput ename="idNo" cname="身份证号"   />
			<EF:EFInput ename="contactTel" cname="联系电话"   />
			<EF:EFTreeInput ename="deptName" cname="所属科室" bindId="tree01" readonly="true"
							serviceName="ACDE01" methodName="queryTree"
							textField="deptName" valueField="label" hasChildren="leaf"
							root="{label: 'root',deptName: '根节点'}" popupTitile="上级分类" clear="true"
							ratio="4:8" required="true">
			</EF:EFTreeInput>


			<%--<EF:EFSelect ename="staff" cname="员工类型：" resultId="staff"
				textField="staffName" valueField="staffType" serviceName="ACDE0101"
				methodName="queryStaff" optionLabel="请选择"   ratio="4:8"
				filter="contains" bindId="staff">
			</EF:EFSelect>--%>

		</div>
		<div class="row">

			<EF:EFSelect ename="type"  cname="员工类型" >
				<EF:EFOption label="请先择员工类型" value=""/>
				<EF:EFCodeOption codeName="wilp.ac.pe.type" textField="label" valueField="value"/>
			</EF:EFSelect>


			<EF:EFSelect ename="isService" cname="服务人员"    required="true">
					<EF:EFOption label="是" value="1"/>
					<EF:EFOption label="否" value="0"/>
			</EF:EFSelect>
		</div>

	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {

		$("#deptName").val(__ei.parentId);
		$("#deptName_textField").val(__ei.parentName);


		$("#SUBMIT").on("click", function (e) {
			
			var eiInfo = new EiInfo();
			
			var workNo = IPLAT.EFInput.value($("#workNo"));
			var name = IPLAT.EFInput.value($("#name"));
			var deptId = $("#deptName").val();
			var contactTel = IPLAT.EFInput.value($("#contactTel"));
			var idNo = IPLAT.EFInput.value($("#idNo"));
			var type = IPLAT.EFSelect.value($("#type"));
			var gender = IPLAT.EFSelect.value($("#gender"));
			var isService = IPLAT.EFSelect.value($("#isService"));
		
			//参数校验
			if(!validate(workNo,name,deptId,contactTel)){
				return;
			}
			
			eiInfo.set("workNo",workNo);
			eiInfo.set("name",name);
			eiInfo.set("deptId",deptId);
			eiInfo.set("contactTel",contactTel);
			eiInfo.set("idNo",idNo);
			eiInfo.set("type",type);
			eiInfo.set("gender",gender);
			eiInfo.set("isService",isService);
			
			EiCommunicator.send("ACPE0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					var addPopData = $("#addPopData", parent.document);
					NotificationUtil(ei.getMsg(), "success");
					console.log(ei.getStatus());
					if (ei.getStatus() == 0){
						addPopData.kendoWindow().data("kendoWindow").close();
						setTimeout(function() {
						window.parent.location.reload()
					}, 600);}
				}
			});
		});
	

	});
	
	//参数校验
	function validate(workNo,name,deptId,contactTel){
		if(isEmpty(workNo)){
			NotificationUtil("工号不能为空", "error");
			return false;
		} 
		if(isEmpty(name)){
			NotificationUtil("姓名不能为空", "error");
			return false;
		} 
		if(isEmpty(deptId)){
			NotificationUtil("所属科室不能为空", "error");
			return false;
		}
		// 手机号非必填，如果填了就验证合法性
		if(!isEmpty(contactTel)){
			if(!checkCellPhone(contactTel)){
				NotificationUtil("电话号码非法", "error");
				return false;
			}
		}
		return true;
	}

	function isEmpty(parameter){
		if(parameter == undefined || parameter == null){
			return true;
		} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		} else {
			return false;
		}
	}
	
	// 验证手机号合法性
	function checkCellPhone(contactTel){
		var reg=/^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/
		return reg.test(contactTel) ? true : false;
	}
	
	// 验证身份证合法性
	function testid(id) {
	   // 1 "验证通过!", 0 //校验不通过 // id为身份证号码
	    var format = /^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/;
	    //号码规则校验
	    if(!format.test(id)){
	      return false;
	    }
	    //区位码校验
	    //出生年月日校验  前正则限制起始年份为1900;
	    var year = id.substr(6,4),//身份证年
	      month = id.substr(10,2),//身份证月
	      date = id.substr(12,2),//身份证日
	      time = Date.parse(month+'-'+date+'-'+year),//身份证日期时间戳date
	      now_time = Date.parse(new Date()),//当前时间戳
	      dates = (new Date(year,month,0)).getDate();//身份证当月天数
	    if(time>now_time||date>dates){
	    	 return false;
	    }
	    //校验码判断
	    var c = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2);  //系数
	    var b = new Array('1','0','X','9','8','7','6','5','4','3','2'); //校验码对照表
	    var id_array = id.split("");
	    var sum = 0;
	    for(var k=0;k<17;k++){
	      sum+=parseInt(id_array[k])*parseInt(c[k]);
	    }
	    if(id_array[17].toUpperCase() != b[sum%11].toUpperCase()){
	    	 return false;
	    }
	    return true;
	}
</script>
