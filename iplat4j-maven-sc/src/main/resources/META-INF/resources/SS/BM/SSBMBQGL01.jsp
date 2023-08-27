<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 病区管理 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="病区名称" />
			
			<EF:EFInput ename="inqu_status-0-deptCode" cname="病区编码" />
			
			<EF:EFSelect ename="inqu_status-0-quoteAddress" cname="是否引用" width="100">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="引用" value="1" />
				<EF:EFOption label="未引用" value="0" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="quoteAddress" cname="quoteAddress" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="deptName" cname="病区名称" width="150" align="center" required="true" readonly="true"/>
			<EF:EFColumn ename="deptCode" cname="病区编码 " width="100" align="center" required="true" readonly="true" />
			<EF:EFColumn ename="datagroupName" cname="所属院区 " width="100" align="center" required="true" readonly="true" />
			<EF:EFColumn ename="sfqy" cname="是否引用" width="100" readonly="true" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="520px" height="200px" modal="true" url="" title="录入">
		<EF:EFRegion id="edit" title="" showClear="true">
			<div class="row">
				<EF:EFInput ename="deptName" cname="病区名称" colWidth="10" required="true" maxLength="50" />
			</div>
			<div class="row" >
				<EF:EFInput ename="deptCode" cname="病区编码" colWidth="10" required="true" maxLength="200"/>
			</div>
			<div class="row" >
				<EF:EFSelect ename="datagroupCode" cname="所属院区" optionLabel="请选择" colWidth="10" required="true" >
                    <EF:EFOptions blockId="canteenDataGroup" textField="datagroupName" valueField="datagroupCode" />
                </EF:EFSelect>
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
	var editValidator = null;
	//提交类型
    var submitType = "insert";
	
	IPLATUI.EFGrid = {
		"result": {
			dataBinding: function (e) {
				//grid数据绑定时对属性进行处理
				for (var i = 0, length = e.items.length; i < length; i++) {
					if(isAvailable(e.items[i].quoteAddress)){
						e.items[i].sfqy = e.items[i].quoteAddress == "0" ? "未引用" : "引用";
					}
				}
			},
			toolbarConfig: {
                buttons: [
                    {
                        name: "synchro",
                        text: "同步",
                        layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
                        icon: "css:fa-refresh",
                        attributes: {
                            style: "float:left;height:30px;"
                        },
                        click: synchroDept
                    }
                ]
            }
		}
	}
	//同步
	function synchroDept(){
		var eiInfo = new EiInfo();
        EiCommunicator.send("SSBMBQGL01", "synchroDept", eiInfo, {
            onSuccess : function(ei) {
                if(ei.status < 0) {
                    NotificationUtil(ei.getMsg(), "error");
                }else {
                    NotificationUtil(ei.getMsg(), "success");
                    //刷新grid
                    setTimeout(function(){
	                    refreshResultGrid();
                    },1500);
                }
            }
        });
		
	}
	/* 按钮绑定事件 */
	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
			refreshResultGrid();
		});
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
				IPLAT.EFInput.value($("#deptName"),value["deptName"]);
				IPLAT.EFInput.value($("#deptCode"),value["deptCode"]);
				IPLAT.EFSelect.value($("#datagroupCode"),value["datagroupCode"]);
			}else{
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
				deptName : IPLAT.EFInput.value($("#deptName")),
				datagroupCode : IPLAT.EFSelect.value($("#datagroupCode")),
				deptCode : IPLAT.EFInput.value($("#deptCode"))
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
            EiCommunicator.send("SSBMBQGL01", submitType, eiInfo, {
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
                IPLAT.EFSelect.value($("#datagroupCode"),null);
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