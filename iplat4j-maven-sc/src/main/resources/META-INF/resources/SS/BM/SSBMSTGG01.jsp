<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 订餐公告 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-noticeTitle" cname="公告标题" />
			<EF:EFInput ename="inqu_status-0-noticeContent" cname="公告内容" />
			<%-- <EF:EFSelect ename="inqu_status-0-typeCode" cname="公告类型"
				resultId="typeName" textField="typeName" valueField="typeCode"
				serviceName="SSBMStgg01" methodName="getNoticeType" optionLabel="请选择"
				colWidth="4" ratio="3:8">
			</EF:EFSelect> --%>
			
			<EF:EFSelect ename="inqu_status-0-typeCode" cname="公告类型" 
				optionLabel="请选择" colWidth="4" ratio="3:8">
				<EF:EFOptions blockId="noticeType" textField="typeName" valueField="typeCode" />
			</EF:EFSelect>
			
			<%-- <EF:EFDatePicker format="yyyy-MM-dd" role="datetime"
				ename="recCreateTime" cname="录入时间">
			</EF:EFDatePicker> --%>
			<%-- <EF:EFSelect ename="inqu_status-0-stopFlag" cname="是否停用" width="100">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="是" value="Y" />
				<EF:EFOption label="否" value="N" />
			</EF:EFSelect> --%>
		</div>
		<%-- <EF:EFButton ename="window" cname="弹窗"  class="k-button window-btn" style="float:left;"/> --%>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="noticeCode" defaultValue="null"
				cname="noticeCode" width="100" hidden="true" />
			<EF:EFColumn ename="inputDate" cname="inputDate" width="100"
				hidden="true" />
			<EF:EFColumn ename="effectiveDate" defaultValue="null"
				cname="effectiveDate" width="100" hidden="true" />
			<%-- <EF:EFColumn ename="typeCode" cname="typeCode" width="100" hidden="true" /> --%>
			<EF:EFColumn ename="isEffective" cname="是否生效" width="100" hidden="true"/>
			<!-- 展示列 -->
			<EF:EFColumn ename="noticeTitle" cname="公告标题" width="150" align="center" required="true"/>
			<EF:EFColumn ename="noticeContent" cname="公告内容" width="400" align="center" required="true"/>
			<%-- <EF:EFColumn ename="typeName" cname="公告类型" width="100" align="center" readonly="true"/> --%>

			<EF:EFComboColumn ename="typeCode" cname="公告类型" width="100" align="center" readonly="true"
				valueField="typeCode" textField="typeName" blockName="noticeType" required="true">
			</EF:EFComboColumn>
<!-- editType="datetime"  -->
			<EF:EFColumn ename="recCreateTime" cname="录入时间" width="100" align="center" required="false" readonly="true" enable="false">

			</EF:EFColumn>
			<EF:EFColumn ename="sfqy" cname="是否生效" width="100" readonly="true" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="520px" height="200px" modal="true" url="" title="录入">
		<EF:EFRegion id="edit" title="" showClear="true">
			<div class="row">
				<EF:EFInput ename="noticeTitle" cname="公告标题" colWidth="10" required="true" maxLength="50" />
			</div>
			<div class="row" >
			    <EF:EFSelect ename="typeCode" cname="公告类型" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="noticeType" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
				<%-- <EF:EFSelect ename="typeCode" cname="公告类型"
	                resultId="typeName" textField="typeName" valueField="typeCode"
	                serviceName="SSBMSTGG01" methodName="getNoticeType" optionLabel="请选择"
	                colWidth="10"  required="true">
	            </EF:EFSelect> --%>
			</div>
			<div class="row" >
				<EF:EFInput ename="noticeContent" type="textarea" cname="公告内容" colWidth="10" required="true" maxLength="200"/>
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
	//获取Blocks里的noticeType
	var typeRow = getInitLoadBlocks("noticeType").rows;
	var editValidator = null;
	IPLATUI.EFGrid = {
		"result": {
			/* columns :[{
				field: "isEffective",
				template: "当前：#=isEffective#"
			}],  */
			//pageable: true,
			dataBinding: function (e) {
				//grid数据绑定时对属性进行处理
				for (var i = 0, length = e.items.length; i < length; i++) {
					if(isAvailable(e.items[i].isEffective)){
						e.items[i].sfqy = e.items[i].isEffective == "0" ? "否" : "是";
					}
				}
			}
		}
	}
	
	/* 按钮绑定事件 */
	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
			refreshResultGrid();
		});
		//生效
		$("#EFFECT").on("click", function(e) {
			var rows = resultGrid.getCheckedRows();
            if(rows.length >= 1){
            	IPLAT.confirm({
                    message: '<b>将会变更所选记录的生效状态！</br><font color="red">是否确定？</font></b>',
                    okFn: function (e) {
                    	//变更状态
                        for (var i = 0; i < rows.length; i++) {
                            if(rows[i].isEffective == "1"){
                                rows[i].isEffective = "0";
                                rows[i].sfqy = "否";
                            }else if(rows[i].isEffective == "0"){
                                rows[i].isEffective = "1";
                                rows[i].sfqy = "是";
                            }
                        }
                        //提交数据
                        var eiInfo = new EiInfo();
                        eiInfo.set("update", rows);
                        EiCommunicator.send("SSBMSTGG01", "update", eiInfo, {
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
                    },
                    title: '提醒'
                });
            }else{
                /* IPLAT.alert({
                    message: '<b>请选择一条记录</b>',
                    okFn: function (e) {},
                    title: '提醒'
                }); */
	        	/*WindowUtil({
	        		'title' : '提醒',
	        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
	        	});*/
	        	
	        	NotificationUtil("请选择一条记录", "warning");
            }
		});
		//编辑
		$("#EDIT").on("click", function(e) {
			shEditWindow.setOptions({"title":"编辑"});
			var rows = resultGrid.getCheckedRows();
			if(rows.length == 1){
				//打开弹窗
				shEditWindow.open().center();
				//为表单赋值
				var value = rows[0];
				IPLAT.EFInput.value($("#noticeTitle"),value["noticeTitle"]);
				IPLAT.EFInput.value($("#noticeContent"),value["noticeContent"]);
				IPLAT.EFSelect.value($("#typeCode"),value["typeCode"]);
			}else{
	        	/*WindowUtil({
	        		'title' : '提醒',
	        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
	        	});
                 IPLAT.alert({
                    message: '<b>请选择一条记录</b>',
                    okFn: function (e) {},
                    title: '提醒'
                }); */
                
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
				noticeTitle : IPLAT.EFInput.value($("#noticeTitle")),
				noticeContent : IPLAT.EFInput.value($("#noticeContent")),
				typeCode : IPLAT.EFSelect.value($("#typeCode")),
				typeName : IPLAT.EFSelect.text($("#typeCode"))
			};
			var selectRow = resultGrid.getCheckedRows()[0];
			//复制数据
			$.extend(selectRow, value);
			//提交数据
			var rows = [];rows[0] = selectRow;
            eiInfo.set("update", rows);
            EiCommunicator.send("SSBMSTGG01", "update", eiInfo, {
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
		//测试
		$("#TEST").on("click", function(e) {
			var eiInfo = new EiInfo();
			eiInfo.set("test", "test");
			EiCommunicator.send("SSBMSTGG01", "test", eiInfo, {
				onSuccess : function(ei) {
					IPLAT.alert("操作成功:" + ei.extAttr.OK);
				}
			})
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
	$("#window").on("click", function(e) {
        //popData();
    });
	//弹窗 
	function popData() {
		var popData = $("#popData");
		//为弹窗绑定属性
        var url = IPLATUI.CONTEXT_PATH + "/web/SSBMSTGG02";
        popData.data("kendoWindow").setOptions({
            open : function(e) {
                popData.data("kendoWindow").refresh({
                    url : url
                });
            },
            close: function (e) {
            }
        });
		// 打开弹窗
		var rows = resultGrid.getCheckedRows();
        if(rows.length != 1){
        	/* IPLAT.alert({
                message: '<b>请选择一条记录！</b>',
                okFn: function (e) {},
                title: '提醒'
            });
			WindowUtil({
        		'title' : '提醒',
        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
        	});*/
            
			NotificationUtil("请选择一条记录", "warning");
        }else{
            popDataWindow.open().center();
            // 向子窗口中的 iframe 对象传值
            setTimeout(function(){
	            var iframe = popData.find("iframe")[0].contentWindow;
	            if(iframe){
	                iframe.setData(rows);
	            }
            }, 500 );
        }
	}
</script>