<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 食堂联络人信息 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
	<EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-canteenTypeNum" cname="食堂分类" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="canteenType" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-canteenName" cname="食堂名称" />
<%-- 			<EF:EFDatePicker format="yyyy-MM-dd" role="datetime" style="display:none;"
                ename="recCreateTime" cname="录入时间">
                </EF:EFDatePicker> --%>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" autoBind="false" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="datagroupCode" cname="院区编号" width="100" hidden="true"/>
			<EF:EFColumn ename="canteenTypeNum" cname="食堂分类编码" width="100" hidden="true" />
			<EF:EFColumn ename="operateCode" cname="业务编码" width="100" hidden="true" />
			<EF:EFColumn ename="isAutoSche" cname="是否自动排班" width="100" hidden="true" />
			<EF:EFColumn ename="mealNum" cname="餐次编码" width="100" hidden="true" />
			<EF:EFColumn ename="canteenStatus" cname="状态编码，1 - 启用 ； 0 - 禁用" width="100" hidden="true" />
			<EF:EFColumn ename="liaisonId" cname="联络员工号" width="100" hidden="true" />
			<EF:EFColumn ename="aaa" cname="隐藏列" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="yqmc" cname="院区" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="canteenName" cname="食堂名称" width="150" align="center" readonly="true" />
			<EF:EFColumn ename="canteenNum" cname="食堂编号" width="150" align="center" readonly="true" />
			<EF:EFColumn ename="canteenTypeName" cname="食堂分类" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="operateName" cname="食堂业务" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="zdpb" cname="是否自动排班" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="mealNumName" cname="服务餐次" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="canteenStatusName" cname="当前状态" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="liaisonName" cname="食堂联络人" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="telephone" cname="联系电话" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="shipFee" cname="配送费" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹出的 iframe -->
	<div id="ef_popup_iframe" style=" display: none">
		<div class="row" style="height: 90%">
			<iframe frameborder="0" title="" style="width:100%;height:100%;" 
			class="k-contentframe" src="${ctx}/web/SSBMTYRY01"></iframe>
		</div>
		<div class="k-window-save k-popup-save">
			<EF:EFButton ename="ef_popup_iframe_fillback" cname="确定" class="i-btn-wide window-btn">
			</EF:EFButton>
		</div>
	</div>
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="550px" height="465px" modal="true" url="" title="录入">
		<EF:EFRegion id="edit" title="" showClear="true">
			<div class="row">
				<EF:EFInput ename="canteenName" cname="食堂名称" colWidth="10" required="true" maxLength="200" />
			</div>
			<div class="row">
				<EF:EFPopupInput ename="liaisonId" colWidth="10" cname="食堂联络人 " 
				    popupHeight="565" popupWidth="550" required="true" maxLength="200"
					containerId="ef_popup_iframe" resizable="true" draggable="true"    
					popupTitle="人员选择" center="true" readonly="true">
				</EF:EFPopupInput>
			</div>
			<%-- <div class="row">
                <EF:EFInput ename="liaisonName" cname="食堂联络人" colWidth="10" required="true" maxLength="200" />
                <EF:EFButton ename="select" cname="选择"  class="k-button select-btn" style="float:left;"/>
                <EF:EFButton ename="clear" cname="清空"  class="k-button clear-btn" style="float:left;"/>
            </div> --%>
            <div class="row">
                <EF:EFInput ename="telephone" cname="联系电话" colWidth="10" required="true" data-rules="non_negative_integer" placeholder="请输入联系电话"  maxLength="11" />
            </div>
			<div class="row" >
				<EF:EFSelect ename="datagroupCode" cname="食堂院区" optionLabel="请选择" colWidth="10" required="true">
	                <EF:EFOptions blockId="hosArea" textField="typeName" valueField="typeCode" />
	            </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFSelect ename="canteenTypeNum" cname="食堂分类" optionLabel="请选择" colWidth="10" required="true">
	                <EF:EFOptions blockId="canteenType" textField="typeName" valueField="typeCode" />
	            </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFSelect ename="canteenStatus" cname="食堂状态" optionLabel="请选择" colWidth="10" required="true">
					<EF:EFOption label="启用" value="1"/>
					<EF:EFOption label="停用" value="0"/>
                </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFSelect ename="isAutoSche" cname="是否自动排班" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOption label="是" value="1"/>
                    <EF:EFOption label="否" value="0"/>
	            </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFSelect ename="operateCode" cname="食堂业务" optionLabel="请选择" colWidth="10" required="true">
					<EF:EFOptions blockId="mealOperate" textField="typeName" valueField="typeCode" />
	            </EF:EFSelect>
			</div>
			<div class="row" >
				<EF:EFMultiSelect ename="mealNum" cname="食堂服务餐次" colWidth="10" required="true"  >
					<EF:EFOptions blockId="mealNum" textField="typeName" valueField="typeCode"/>
				</EF:EFMultiSelect>
			</div>
			<div class="row">
				<EF:EFInput ename="shipFee" cname="配送费" colWidth="10" required="true" maxLength="200" />
			</div>
			<div class="row">
				<EF:EFInput ename="dishesNumber" cname="菜品数量" colWidth="10" required="true" maxLength="200" />
			</div>
		</EF:EFRegion>
		<EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
		<EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
	</EF:EFWindow>
	<EF:EFWindow id="popData" url=" " lazyload="true" width="60%" height="60%">
		<div class="k-window-save k-popup-save">
			<EF:EFButton ename="ef_popup_iframe_fillback1" cname="确定" class="i-btn-wide window-btn">
			</EF:EFButton>
		</div>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	//提交类型
	var submitType = "insert";
	var editValidator = null;
	//获取Blocks里的初始化数据
	var hosArea = getInitLoadBlocks("hosArea").rows;
	IPLATUI.EFGrid = {
		"result": {
			/* columns :[{
				field: "isEffective",
				template: "当前：#=isEffective#"
			}],  */
			columns:[{
				field: "canteenName", // popupColumn, 暂不支持 DynamicGrid
				enable: true,
				readonly: true,
				hidden: false,
				locked: false,
				editor: function (container,param) {
					console.log(container);
					console.log(param);
					// 设置产生弹框 model
					if (container.hasClass("fake-edit")) {
						container.removeClass("fake-edit");
					} else {
						editorModel = param.model;
						//popData();
						IPLAT.Popup.popupContainer({
							containerId: "ef_popup_iframe",
							textElement: $(container),
							width: 600,
							height:800,
							title: "详细说明"
						});
					}
				}
			}],
			dataBinding: function (e) {
				//获取hosArea的mappedRows数据
				var hosArearows = getBlocksMappedRows("hosArea");
				//grid数据绑定时对属性进行处理
				for (var i = 0, length = e.items.length; i < length; i++) {
					if(isAvailable(e.items[i].isAutoSche)){
						e.items[i].zdpb = e.items[i].isAutoSche == "0" ? "否" : "是";
						for (var j = 0; j < hosArearows.length; j++) {
							if(e.items[i].datagroupCode == hosArearows[j].typeCode){
								e.items[i].yqmc = hosArearows[j].typeName;
							}
						}
					}
				}
			},
            beforeRequest: function (e) {
                //查询之前添加参数，避免点击清除按钮后条件失效
                IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
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
                        eiInfo.set("submit", rows);
                        EiCommunicator.send("SSBMSTXX01", "update", eiInfo, {
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
                }); 
            	WindowUtil({
	        		'title' : '提醒',
	        		'content' : "<div class='kendo-del-message'>请选择一条记录</div>"
	        	});*/
                
            	NotificationUtil("请选择一条记录", "warning");
            }
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
				IPLAT.EFInput.value($("#canteenName"),value["canteenName"]);
				$("#liaisonId").val(value["liaisonId"]);
				$("#liaisonId_textField").val(value["liaisonName"]);
				IPLAT.EFInput.value($("#telephone"),value["telephone"]);
				IPLAT.EFSelect.value($("#datagroupCode"),value["datagroupCode"]);
				IPLAT.EFSelect.value($("#canteenTypeNum"),value["canteenTypeNum"]);
				IPLAT.EFSelect.value($("#canteenStatus"),value["canteenStatus"]);
				IPLAT.EFSelect.value($("#isAutoSche"),value["isAutoSche"]);
				IPLAT.EFSelect.value($("#operateCode"),value["operateCode"]);
				IPLAT.EFInput.value($("#dishesNumber"),value["dishesNumber"]);
				IPLAT.EFInput.value($("#shipFee"),value["shipFee"]);
				//IPLAT.EFSelect.value($("#mealNum"),value["mealNum"]);
				//为MultiSelect赋值
				$("#mealNum").data("kendoMultiSelect").value(value["mealNum"].split(","));
				$("#mealNum").data("kendoMultiSelect").trigger("change");
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
				canteenName : IPLAT.EFInput.value($("#canteenName")),
				liaisonId : $("#liaisonId").val(),
				liaisonName : $("#liaisonId_textField").val(),
				telephone : IPLAT.EFInput.value($("#telephone")),
				datagroupCode : IPLAT.EFSelect.value($("#datagroupCode")),
				canteenTypeNum : IPLAT.EFSelect.value($("#canteenTypeNum")),
				canteenTypeName : IPLAT.EFSelect.text($("#canteenTypeNum")),
				canteenStatus : IPLAT.EFSelect.value($("#canteenStatus")),
				canteenStatusName : IPLAT.EFSelect.text($("#canteenStatus")),
				isAutoSche : IPLAT.EFSelect.value($("#isAutoSche")),
				operateCode : IPLAT.EFSelect.value($("#operateCode")),
				operateName : IPLAT.EFSelect.text($("#operateCode")),
				dishesNumber : IPLAT.EFInput.value($("#dishesNumber")),
				shipFee : IPLAT.EFInput.value($("#shipFee")),
				mealNum : $("#mealNum").data("kendoMultiSelect").value().join(","),
				mealNumName : _.map($("#mealNum").data("kendoMultiSelect").dataItems(), _.iteratee("textField")).join(",")
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
            EiCommunicator.send("SSBMSTXX01", submitType, eiInfo, {
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
		  // 关闭 iframe 时候回填数据
	    $("#ef_popup_iframe_fillback").on("click",function (e) {
	        //debugger;
	       var checkRows = window.frames[0].resultGrid.getCheckedRows();
	       if(checkRows && checkRows.length > 0){
		       var values = [],texts = [];
		       for (var i = 0; i < checkRows.length; i++) {
		    	   values.push(checkRows[i]["workNo"]);
		    	   texts.push(checkRows[i]["name"]);
				}
		       $("#liaisonId").val(values);
		       $("#liaisonId_textField").val(texts);
		       var windowFrame = $("#ef_popup_iframe").data("kendoWindow");
	           windowFrame.close();
			   // resultGrid.setCellValue(editorModel,"canteenName",checkRows[0]["name"] + ',' + checkRows[1]["name"]);
			   // resultGrid.setCellValue(editorModel,"aaa",checkRows[0]["workNo"] + ',' + checkRows[1]["workNo"]);
	       }else{
	    	   NotificationUtil("请选择联络人！", "warning");
	       }
	    });
		  // 关闭 iframe 时候回填数据
	    $("#ef_popup_iframe_fillback1").on("click",function (e) {
	        //debugger;
	       var checkRows = window.frames[0].resultGrid.getCheckedRows();
	       if(checkRows && checkRows.length > 0){
		       var values = [],texts = [];
		       for (var i = 0; i < checkRows.length; i++) {
		    	   values.push(checkRows[i]["workNo"]);
		    	   texts.push(checkRows[i]["name"]);
				}
		       $("#liaisonId").val(values);
		       $("#liaisonId_textField").val(texts);
		       //var windowFrame = $("#ef_popup_iframe").data("kendoWindow");
	           popDataWindow.close();
			   // resultGrid.setCellValue(editorModel,"canteenName",checkRows[0]["name"] + ',' + checkRows[1]["name"]);
			   // resultGrid.setCellValue(editorModel,"aaa",checkRows[0]["workNo"] + ',' + checkRows[1]["workNo"]);
	       }else{
	    	   NotificationUtil("请选择联络人！", "warning");
	       }
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
		  //登录用户
	    IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
	    //隐藏控件
	    $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
        //页面加载时查询一次
        $("#QUERY").click();
        //启用校验
        editValidator = IPLAT.Validator({ id: "edit" });
	});
	//弹窗 
	function popData() {
		var popData = $("#popData");
		//为弹窗绑定属性
        var url = IPLATUI.CONTEXT_PATH + "/web/SSBMTYRY01";
        popData.data("kendoWindow").setOptions({
        	title : "人员选择",
            open : function(e) {
                popData.data("kendoWindow").refresh({
                    url : url
                });
            },
            close: function (e) {
            	var iframe = popData.find("iframe")[0].contentWindow;
                if(iframe){
                    var data = iframe.getData();
                    console.log(data);
                }
            }
        });
		// 打开弹窗
        popDataWindow.open().center();
        // 向子窗口中的 iframe 对象传值
        setTimeout(function(){
            var iframe = popData.find("iframe")[0].contentWindow;
            if(iframe){
                iframe.setData(111);
            }
        }, 500 );
	}
</script>