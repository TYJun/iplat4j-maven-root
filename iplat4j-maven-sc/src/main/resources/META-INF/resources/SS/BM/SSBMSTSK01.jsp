<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 收款账号配置 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="companyid" cname="单位编码" width="100" hidden="true"/>
			<EF:EFColumn ename="modulecode" cname="模块编码，如职工订餐、病患订餐 "  width="100" hidden="true" />
			<EF:EFColumn ename="businesscode" cname="业务编码，如食堂编码" width="100" hidden="true" />
			<EF:EFColumn ename="partner" cname="合作者身份(PID)" width="100" hidden="true" />
			<EF:EFColumn ename="sellerId" cname="销售者" width="100" hidden="true" />
			<EF:EFColumn ename="checkKey" cname="安全校验码(Key)" width="100" hidden="true" />
			<EF:EFColumn ename="privateKey" cname="商户的私钥" width="100" hidden="true" />
			<EF:EFColumn ename="aliPublicKey" cname="支付宝的公钥" width="100" hidden="true" />
			<EF:EFColumn ename="creator" cname="creator" width="100" hidden="true" />
			<EF:EFColumn ename="createname" cname="createname" width="100" hidden="true" />
			<EF:EFColumn ename="createdate" cname="createdate" width="100" hidden="true" />
			<EF:EFColumn ename="updator" cname="updator" width="100" hidden="true" />
			<EF:EFColumn ename="updatedate" cname="updatedate" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="companyname" cname="单位名称" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="modulename" cname="食堂分类" width="150" align="center" required="true" />
			<EF:EFColumn ename="businessname" cname="食堂名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="remark" cname="备注" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="550px" height="425px" modal="true" url="" title="录入">
		<EF:EFRegion id="edit" title="" showClear="true">
			<div class="row">
				<EF:EFInput ename="companyid" cname="单位编码" colWidth="10" required="true" maxLength="64" />
			</div>
			<div class="row">
                <EF:EFInput ename="companyname" cname="单位名称" colWidth="10" required="true" maxLength="200" />
            </div>
            <div class="row">
                <EF:EFSelect ename="modulecode" cname="食堂分类" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="canteenType" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>
			<div class="row" >
				<EF:EFSelect ename="businesscode" cname="食堂名称" colWidth="10" required="true" 
					optionLabel="请选择" >
					<EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
	            </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFInput ename="partner" cname="合作者身份(PID)" colWidth="10" required="true" maxLength="200" />
			</div>
			<div class="row" >
				<EF:EFInput ename="sellerId" cname="销售者" colWidth="10" required="true" maxLength="200" />
			</div>
			<div class="row" >
				<EF:EFInput ename="checkKey" cname="安全校验码" colWidth="10" required="true" maxLength="200" />
			</div>
			<div class="row" >
				<EF:EFInput ename="privateKey" cname="商户私钥"  type="textarea" colWidth="10" required="true" maxLength="500" />
			</div>
			<div class="row" >
				<EF:EFInput ename="aliPublicKey" cname="支付宝的公钥" type="textarea" colWidth="10" required="true" maxLength="500" />
			</div>
			<div class="row" >
				<EF:EFInput ename="remark" cname="备注" type="textarea" colWidth="10" maxLength="500" />
			</div>
		</EF:EFRegion>
		<EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
		<EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
	</EF:EFWindow>
	<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
        height="60%">
    </EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	//提交类型
	var submitType = "insert";
	var editValidator = null;
	//下拉框联动
	IPLATUI.EFSelect = {
		"modulecode": {
			// 下拉选项改变之后触发
			change: function (e) {
				var typeRow = getBlocksMappedRows("canteenData");
				var data = [];
				for (var i = 0; i < typeRow.length; i++) {
					if(this._old == typeRow[i]["canteenTypeNum"]){
						data.push({
							textField : typeRow[i].typeName,
							valueField : typeRow[i].typeCode
						});
					}
				}
				//绑定数据
				var dataSource = new kendo.data.DataSource({ data : data });
				IPLAT.EFSelect.setDataSource($("#businesscode"), dataSource);
			}
		}
	}
	/* 按钮绑定事件 */
	$(function() {
		//录入
		$("#ADD").on("click", function(e) {
			submitType = "insert";
			shEditWindow.setOptions({"title":"录入"});
			//打开弹窗
            shEditWindow.open().center();
		});
		//编辑
		$("#EDIT").on("click", function(e) {
			submitType = "update";
			shEditWindow.setOptions({"title":"编辑"});
			var rows = resultGrid.getCheckedRows();
			if(rows.length == 1){
				//打开弹窗
				shEditWindow.open().center();
				//为表单赋值
				var value = rows[0];
				IPLAT.EFInput.value($("#companyid"),value["companyid"]);
				IPLAT.EFInput.value($("#companyname"),value["companyname"]);
				IPLAT.EFSelect.value($("#modulecode"),value["modulecode"]);
				IPLAT.EFSelect.value($("#businesscode"),value["businesscode"]);
				IPLAT.EFInput.value($("#partner"),value["partner"]);
				IPLAT.EFInput.value($("#sellerId"),value["sellerId"]);
				IPLAT.EFInput.value($("#checkKey"),value["checkKey"]);
				IPLAT.EFInput.value($("#privateKey"),value["privateKey"]);
				IPLAT.EFInput.value($("#aliPublicKey"),value["aliPublicKey"]);
				IPLAT.EFInput.value($("#remark"),value["remark"]);
			}else{
                /* IPLAT.alert({
                    message: '<b>请选择一条记录</b>',
                    okFn: function (e) {},
                    title: '提醒'
                }); 
				WindowUtil({
	        		'title' : '提醒',
	        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
	        	});*/
                
				NotificationUtil("请选择一条记录", "warning");
            }
		});
		//提交
		$("#submit").on("click", function(e) {
	        if (!editValidator.validate()) {
	            //校验不通过
	            return ;
	        }
			
			var eiInfo = new EiInfo();
			//获取编辑的值
			var value = {
				companyid : IPLAT.EFInput.value($("#companyid")),
				companyname : IPLAT.EFInput.value($("#companyname")),
				modulecode : IPLAT.EFSelect.value($("#modulecode")),
				modulename : IPLAT.EFSelect.text($("#modulecode")),
				businesscode : IPLAT.EFSelect.value($("#businesscode")),
				businessname : IPLAT.EFSelect.text($("#businesscode")),
				partner : IPLAT.EFInput.value($("#partner")),
				sellerId : IPLAT.EFInput.value($("#sellerId")),
				checkKey : IPLAT.EFInput.value($("#checkKey")),
				privateKey : IPLAT.EFInput.value($("#privateKey")),
				aliPublicKey : IPLAT.EFInput.value($("#aliPublicKey")),
				remark : IPLAT.EFInput.value($("#remark"))
			};
			var selectRow = resultGrid.getCheckedRows()[0];
			if(!selectRow){
				selectRow = {};
			}
			//复制数据
            $.extend(selectRow, value);
			//提交数据
			var rows = [];rows[0] = selectRow;
            eiInfo.set("submit", rows);
            EiCommunicator.send("SSBMSTSK01", submitType, eiInfo, {
                onSuccess : function(ei) {
                    if(ei.status == 0) {
                        NotificationUtil(ei.getMsg(), "error");
                    }else {
                        NotificationUtil(ei.getMsg(), "success");
                        //刷新grid
                        refreshResultGrid();
                    }
                }
            });
            //关闭弹窗
            $("#cancel").click();
		});
		//取消
		$("#cancel").on("click", function(e) {
			window['shEditWindow'].close();
		});
	});
	// 关闭事件
    IPLATUI.EFWindow = {
        "shEdit": {
            close: function (e) {
                //EFRegion的id-trash
                $("#edit-trash").click();
            }
        }
     }
	$(function() {
        //页面加载时查询一次
        refreshResultGrid();
        //启用校验
        editValidator = IPLAT.Validator({ id: "edit" });
	});
</script>