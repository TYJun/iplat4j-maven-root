<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜谱餐次排班 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
<div class="row">
	<div class="col-md-2">
		<EF:EFRegion id="tree" title="">
		<%-- <EF:EFTree id="menu" valueField="ename" textField="cname" hasChildren="leaf" 
                serviceName="SSBMCPPB01" methodName="getWeekDays" style="height:560px;"/> --%>
            <EF:EFOnceTree id="menu" textField="text" valueField="value" pid="pid"
				hasChildren="isLeaf" serviceName="SSBMCPPB01" methodName="getWeekDays"
				expandLevel="1" style="height:560px;">
			</EF:EFOnceTree>
		</EF:EFRegion>
	</div>
    <div class="col-md-10">
	    <EF:EFRegion id="inqu" title="操作" showClear="true">
           <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
	        <div class="row">
               <EF:EFCascadeSelect ename="inqu_status-0-canteenNum" autoBind="true"
			        cname="食堂名称" textField="typeName" valueField="typeCode" 
			        methodName="queryCanteenData" resultId="canteenData" ratio="3:8" colWidth="3">
			        <EF:EFOption label="请选择" value=""/>
			    </EF:EFCascadeSelect>
			    <EF:EFCascadeSelect ename="inqu_status-0-mealNum" 
			        cascadeFrom="inqu_status-0-canteenNum"
			        cname="餐次" textField="typeName" valueField="typeCode"
			        methodName="queryMealNum" resultId="mealNum" ratio="3:8" colWidth="3">
			    </EF:EFCascadeSelect>
				    
	            <EF:EFButton ename="add" cname="添加"  class="k-button window-btn operation-btn"/>
	            <EF:EFButton ename="copy" cname="复制"  class="k-button window-btn operation-btn"/>
	            <EF:EFDatePicker ename="copyDate" cname="" ratio="1:8"
	              format="yyyy-MM-dd" role="date" colWidth="2" style="float:right;"/>
	            <EF:EFInput ename="inqu_status-0-mealTypeNum" disabled="true" style="display:none;"/>
	            <EF:EFInput ename="inqu_status-0-mealDate" disabled="true" style="display:none;"/>
	        </div>
	    </EF:EFRegion>
	    <EF:EFRegion id="result" title="记录集">
	        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false"  checkMode="row">
	            <!-- 隐藏列 -->
	            <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
	            <EF:EFColumn ename="menuNum" cname="菜品编码" width="100" hidden="true"/>
	            <!-- 展示列 -->
	            <EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" readonly="true"/>
	            <EF:EFColumn ename="menuPrice" cname="菜品单价" format="{0:n}" width="150" align="center" required="true" />
	            <EF:EFColumn ename="priceAfterDiscount" cname="折后价" format="{0:n}" width="100" align="center" readonly="true"/>
	            <EF:EFColumn ename="surplus" cname="剩余份量" width="100" align="center" readonly="true"/>
	            <EF:EFColumn ename="mealTypeName" cname="菜品类型" width="100" align="center" readonly="true"/>
	        </EF:EFGrid>
	    </EF:EFRegion>
	    
	       <!-- 弹窗 -->
    <EF:EFWindow id="shEdit" width="790px" height="600px" modal="true" url="" title="添加" style="display:none;">
        <EF:EFRegion id="inqu2" title="查询" showClear="true">
	        <div class="row">
               <EF:EFSelect ename="menuTypeNum" cname="菜品分类 " optionLabel="请选择" colWidth="4" ratio="4:8" required="true">
                    <EF:EFOptions blockId="menuType" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
	            <EF:EFInput ename="menuName" cname="菜品名称" colWidth="4" ratio="4:8">
	            </EF:EFInput>
	            <EF:EFButton ename="queryMenu" cname="查询"  class="k-button window-btn" />
	            <!-- 隐藏条件 -->
	            <EF:EFInput ename="mealDate" disabled="true" style="display:none;"/>
	            <EF:EFInput ename="canteenNum" disabled="true" style="display:none;"/>
	            <EF:EFInput ename="mealNum" disabled="true" style="display:none;"/>
	        </div>
	    </EF:EFRegion>
            <!-- 菜品信息 -->
        <EF:EFRegion id="composition " title="菜品信息" showClear="true" style="height:425px;">
              <EF:EFGrid blockId="description" autoDraw="no" rowNo="false" autoBind="false" 
                  serviceName="SSBMCPPB01" queryMethod="queryMenuInfo">
                <!-- 隐藏列 -->
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
                <EF:EFColumn ename="menuNum" cname="关联id" width="100" hidden="true"/>
                <!-- 展示列 -->
                <EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" required="true"/>
                <EF:EFColumn ename="menuTypeName" cname="菜品分类" width="150" align="center" required="true" />
                <EF:EFColumn ename="menuPrice" cname="菜品单价" width="100" align="center" required="true"/>
                <EF:EFColumn ename="canteenName" cname="所属食堂" width="100" align="center" required="true"/>
              </EF:EFGrid>
        </EF:EFRegion>
        <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
        <EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
    </EF:EFWindow>
    <EF:EFWindow id="popData" url=" " lazyload="true" width="680px" height="625px">
    </EF:EFWindow>
  </div>

</EF:EFPage>
<script type="text/javascript">
//提交类型
var submitType = "insert";
	IPLATUI.EFGrid = {
	    "result": {
	        dataBinding: function (e) {
	        },
	        beforeRequest: function (e) {
	        	//查询之前添加参数，避免点击清除按钮后条件失效
	        	IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
	        }
	    },
	    "description": {
	    	beforeRequest: function (e) {
	    	}
	    }
	}
	IPLATUI.EFCascadeSelect = {
		"inqu_status-0-canteenNum" : {
			change : function(e){
				if(e.sender._old){
                    //下拉选项变更时查询数据
                    refreshResultGrid();
                }
			}
		},
		"inqu_status-0-mealNum" : {
			change : function(e){
				if(e.sender._old){
					//下拉选项变更时查询数据
					refreshResultGrid();
				}
			}
		}
	}
	// 弹窗关闭事件
    IPLATUI.EFWindow = {
        "shEdit": {
            close: function (e) {
                //EFRegion的id-trash
                $("#edit-trash").click();
            }
        }
    }
	/* 按钮绑定事件 */
	$(function() {
        //登录用户
        IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
		//设置按钮样式
		$("#delete").css("float","left");
		//$("#delete button").attr("class",$("#delete button").attr("class")+" window-btn");
		$(".operation-btn").css("float","left");
		//添加
        $("#add").on("click", function(e) {
        	var values = getWindowQueryInfo();
        	if(!values.mealDate){
        		NotificationUtil("请选择排班日期！", "warning");
        	}else if(!values.canteenNum){
        		NotificationUtil("请选择食堂！", "warning");
        	}else if(!values.mealNum){
                NotificationUtil("请选择餐次！", "warning");
            }else{
	        	//打开弹窗的方法
	        	popData();
            }
        });
		//复制
        $("#copy").on("click", function(e) {
        	var copyDate = kendo.toString($("#copyDate").data("kendoDatePicker").value(),"yyyy-MM-dd");
        	var values = getWindowQueryInfo();
            if(!values.mealDate){
                NotificationUtil("请选择排班日期！", "warning");
            }else if(!values.canteenNum){
            	NotificationUtil("请选择食堂！", "warning");
            }else if(!copyDate){
            	NotificationUtil("请选择复制日期！", "warning");
        	}else if(copyDate < values.mealDate){
        		NotificationUtil("复制日期应大于排班日期！", "warning");
        	}else{
        		IPLAT.confirm({
                    message: '<b>将会复制'+values.mealDate+'的排班到'+copyDate+'！</br><font color="red">是否确定？</font></b>',
                    title: '提醒',
                    okFn: function (e) {
                    	var eiInfo = new EiInfo();
                        eiInfo.set("mealDate",values.mealDate);
                        eiInfo.set("copyDate",copyDate);
                        eiInfo.set("canteenNum",values.canteenNum);
                        //提交数据,复制排班
                        EiCommunicator.send("SSBMCPPB01", "copyMenu", eiInfo, {
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
                });
        	}
        });
	});
	
	//关闭弹窗，供iframe调用
	function closeWindow(type,rows){
		submitType = type;
		if(submitType == "insert" && rows.length < 1){
            NotificationUtil("请选择菜品", "warning");
            return ;
        }
		popDataWindow.close();
	}
	
	/**获取弹窗查询条件*/
	function getWindowQueryInfo(){
		var mealDate = IPLAT.EFInput.value($("#inqu_status-0-mealDate"));
        var canteenNum = IPLAT.EFSelect.value($("#inqu_status-0-canteenNum"));
        var mealNum = IPLAT.EFSelect.value($("#inqu_status-0-mealNum"));
        //加载descriptionGrid菜品组成数据
        return params = {
        		'mealDate' : mealDate,
        		'canteenNum' : canteenNum,
        		'mealNum' : mealNum
        };
	}
	//弹窗 
    function popData() {
    	if(!mealDate){
            NotificationUtil("请选择排班日期", "warning");
            return;
        }else{
            if(!canteenNum || !mealNum){
                NotificationUtil("请选择食堂和餐次", "warning");
                return;
            }
        }
        //为弹窗绑定属性
        var url = IPLATUI.CONTEXT_PATH + "/web/SSBMCPPB02";
        $("#popData").data("kendoWindow").setOptions({
            title : "菜品信息",
            open : function(e) {
            	$("#popData").data("kendoWindow").refresh({
                    url : url
                });
            },
            close: function (e) {
            	//关闭弹窗获取iframe传值
                var iframe = popDataWindow.element.children("iframe")[0].contentWindow;
                if(iframe){
                    var data = iframe.getData();
                    if(submitType == "insert" && data.length > 0){
                    	var paramInfo = getWindowQueryInfo();
                        for (var i = 0; i < data.length; i++) {
                        	$.extend(data[i], paramInfo);
						}
                    	var eiInfo = new EiInfo();
                        eiInfo.set("submit",data);
                        console.log(eiInfo);
                        //提交数据
                        EiCommunicator.send("SSBMCPPB01", submitType, eiInfo, {
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
                }
            }
        });
        // 打开弹窗
        popDataWindow.open().center();
        // 向子窗口中的 iframe 对象传值
        setTimeout(function(){
            var iframe = popDataWindow.element.children("iframe")[0].contentWindow;
            if(iframe){
                iframe.setData(getWindowQueryInfo());
            }
        }, 1000 );
    }
</script>