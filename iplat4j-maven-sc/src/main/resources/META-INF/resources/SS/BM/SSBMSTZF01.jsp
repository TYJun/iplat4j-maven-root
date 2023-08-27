<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 食堂支付方式配置 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
<EF:EFRegion id="inqu" title="查询条件" showClear="true">
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-canteenType" cname="食堂类型" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="canteenType" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-orallyType" cname="入口类型"
                optionLabel="请选择" colWidth="4" ratio="3:8">
				<EF:EFOptions blockId="orallyType" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
        </div>
    </EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="orderNo" cname="排序" width="100" hidden="true"/>
			<EF:EFColumn ename="datagroupecode" cname="账套编码" width="100" hidden="true" />
			<EF:EFColumn ename="datagroupe" cname="账套" width="100" hidden="true" />
			<EF:EFColumn ename="registerTime" cname="登记时间" width="100" hidden="true" />
			<EF:EFColumn ename="status" cname="状态,00停用/01在用" width="100" hidden="true" />
			<EF:EFColumn ename="canteenType" cname="食堂分类" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="orally" cname="入口" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="payTypeName" cname="支付方式 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="stfl" cname="食堂分类" width="150" align="center" readonly="true" />
			<EF:EFColumn ename="payTypeNum" cname="支付编码 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="sfqy" cname="是否启用" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
</EF:EFPage>
<script type="text/javascript">
	//提交类型
	var submitType = "insert";
	var each = $.each;
    var _mixedHandler = function (models) {
        var locked = 0,
            unlocked = 0;
        each(models, function (i, model) {
            if (locked === 0) {
                locked = model.column_locked === "1" ? 1 : 0;
            }

            if (unlocked === 0) {
                unlocked = model.column_locked === "1" ? 0 : 1;
            }

            if (locked + unlocked === 2) {
                return false;
            }
        });
        return locked + unlocked === 2;
    };
	
	IPLATUI.EFGrid = {
		"result": {
			columns :[{
				field: "orally",
				template: "#=orally#端"
			}],
			dataBinding: function (e) {
				//grid数据绑定时对属性进行处理
				for (var i = 0, length = e.items.length; i < length; i++) {
					e.items[i].sfqy = e.items[i].status == "00" ? "停用" : "启用";
					//获取canteenType的mappedRows数据
                    var blocksMappedRows = getBlocksMappedRows("canteenType");
                    for (var j = 0; j < blocksMappedRows.length; j++) {
                        if(e.items[i].canteenType == blocksMappedRows[j].typeCode){
                            e.items[i].stfl = blocksMappedRows[j].typeName;
                        }
                    }
				}
			},
			toolbarConfig: {
                add: false, "delete": false, save: false, cancel: false,
                buttons: [ {
                    name: "_up",
                    attributes: {
                        style: "float:left",
                        title: "向上"
                    },
                    icon: "css:fa-arrow-up",
                    layout: IPLATUI.Config.Layout.ICON,
                    click: function () {
                        if (resultGrid.getCheckedRows().length === 0) {
                            WindowUtil({
                                'title': "向上移动列",
                                "content": "<div class='kendo-del-message'>没有选中的行</div>"
                            });
                        } else {

                              var lockedCount = _.filter(resultGrid.getDataItems(), {column_locked: "1"}).length;
                              each(resultGrid.getCheckedRows(), function (i, dataItem) {
                                  var index = resultGrid.dataSource.indexOf(dataItem);
                                  var newIndex = Math.max(lockedCount, index - 1);

                                  if (newIndex != index) {
                                      var data = resultGrid.dataSource.data();
                                      var upRow = data[newIndex];

                                      dataItem.set("orderNo", newIndex + 1);
                                      data[newIndex] = dataItem;
                                      data[newIndex + 1] = upRow;
                                      upRow.set("orderNo", newIndex + 2);


                                  } else {
                                       WindowUtil({
                                           'title': "向上移动列",
                                           "content": "<div class='kendo-del-message'>已经移动到了顶部</div>"
                                       });

                                      return false;
                                  }

                              });
                        }
                    }
                }, {
                    name: "_down",
                    icon: "css:fa-arrow-down",
                    layout: IPLATUI.Config.Layout.ICON,
                    attributes: {
                        style: "float:left",
                        title: "向下"
                    },
                    click: function () {
                        if (resultGrid.getCheckedRows().length === 0) {
                            WindowUtil({
                                'title': "向下移动列",
                                "content": "<div class='kendo-del-message'>没有选中的行</div>"
                            });
                        } else {

                                var lockedCount = _.filter(resultGrid.getDataItems(), {column_locked: "1"}).length;
                                each(resultGrid.getCheckedRows().reverse(), function (i, dataItem) {
                                    var index = resultGrid.dataSource.indexOf(dataItem);
                                    var newIndex = Math.min(resultGrid.getDataItems().length - 1, index + 1);;

                                    if (newIndex != index) {
                                        var data = resultGrid.dataSource.data();
                                        var downRow = data[newIndex];

                                        
                                        dataItem.set("orderNo", newIndex + 1);
                                        data[newIndex] = dataItem;
                                        data[newIndex - 1] = downRow;
                                        downRow.set("orderNo", newIndex);
                                    }
                                    else {
                                        WindowUtil({
                                            'title': "向下移动列",
                                            "content": "<div class='kendo-del-message'>已经移动到了底部</div>"
                                        });
                                        return false;

                                    }
                                });
                        }
                    }
                } , {
                    name:  "_cancel",
                    icon: "css:fa-times-circle-o",
                    text: "取消",
                    layout: "3",
                    attributes: {
                        style: "float: right"
                    },
                    click: function () {
                        resultGrid.cancelChanges();
                    }
                },
                {
                    name: "_refresh",
                    icon: "css:fa-refresh",
                    text: "保存并刷新",
                    layout: "3",
                    attributes: {
                        style: "float: right"
                    },
                    click: function () {
                        resultGrid.saveChanges();
                        
                        //调整排序
                        var rows = resultGrid.getDataItems();
                        var eiInfo = new EiInfo();
		                eiInfo.set("sort", rows);
		
                        //window.location.reload();
                        EiCommunicator.send("SSBMSTZF01", "saveSort", eiInfo, {
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
                    }
                } ]
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
            	WindowUtil({
                    //message: '<b>将会变更所选记录的启用状态！</br><font color="red">是否确定？</font></b>',
                    title: '提醒',
                    content: kendo.template($("#del-template").html())(
                            {message: "将会变更所选记录的启用状态！", ok: '确定', cancel: '取消'}
                        ),
                    ok: function (e) {
                    	//变更状态
                        for (var i = 0; i < rows.length; i++) {
                            if(rows[i].status == "01"){
                                rows[i].status = "00";
                                rows[i].sfqy = "停用";
                            }else if(rows[i].status == "00"){
                                rows[i].status = "01";
                                rows[i].sfqy = "启用";
                            }
                        }
                        //提交数据
                        var eiInfo = new EiInfo();
                        eiInfo.set("submit", rows);
                        EiCommunicator.send("SSBMSTZF01", "effect", eiInfo, {
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
                        this.data("kendoWindow").close();
                    },
                    cancel: function () {
                    	this.data("kendoWindow").close();
                    }
                });
            }else{
                /*IPLAT.alert({
                	title: '提醒',
                    message: '<b>请选择一条记录</b>',
                    okFn: function (e) {}
                    
                });
                WindowUtil({
                    'title': "提醒",
                    "content": "<div class='kendo-del-message'>请选择一条记录</div>"
                });*/
                
                NotificationUtil("请选择一条记录", "warning");
            }
		});
/*  		//上移
		$("#UP").on("click", function(e) {
			submitType = "up";
			var rows = resultGrid.getCheckedRows();
            if(rows.length == 1){
                saveSort(submitType,rows[0]);
            }else{
                IPLAT.alert({
                    message: '<b>请选择一条记录</b>',
                    title: '提醒',
                    okFn: function (e) {}
                });
            }
		});
		//下移
		$("#DOWN").on("click", function(e) {
			submitType = "down";
			var rows = resultGrid.getCheckedRows();
			if(rows.length == 1){
				saveSort(submitType,rows[0]);
			}else{
                IPLAT.alert({
                	title: '提醒',
                    message: '<b>请选择一条记录</b>',
                    okFn: function (e) {}
                });
            }
		});  */
	});

	$(function() {
		$("#EFFECT").css("float","left");
        //页面加载时查询一次
        refreshResultGrid();
	});
	
</script>