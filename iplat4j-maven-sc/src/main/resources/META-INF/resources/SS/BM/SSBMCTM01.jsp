<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 床头码管理 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
<div class="row">
    <EF:EFRegion id="inqu" title="操作" showClear="true">
        <div class="row">
            <EF:EFCascadeSelect ename="inqu_status-0-building" autoBind="true"
		        cname="楼" textField="typeName" valueField="typeCode" 
		        methodName="queryBuilding" resultId="building" ratio="3:8" colWidth="3">
		        <EF:EFOption label="请选择" value=""/>
		    </EF:EFCascadeSelect>
		    
		    <EF:EFCascadeSelect ename="inqu_status-0-floor" 
		        cascadeFrom="inqu_status-0-building"
		        cname="层" textField="typeName" valueField="typeCode"
		        methodName="queryFloor" resultId="floor" ratio="3:8" colWidth="3">
		    </EF:EFCascadeSelect>
		    
		    <EF:EFSelect ename="inqu_status-0-deptCode" cname="科室 " 
              colWidth="3" ratio="3:8" textField="typeName" valueField="typeCode">
              <EF:EFOption label="请选择" value=""/>
            </EF:EFSelect>
            
			<%-- <EF:EFCascadeSelect ename="inqu_status-0-deptCode" 
                cascadeFrom="inqu_status-0-floor"
                cname="科室" textField="typeName" valueField="typeCode"
                methodName="queryDept" resultId="dept" ratio="3:8" colWidth="3">
            </EF:EFCascadeSelect> --%>
            
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row">
            <!-- 隐藏列 -->
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
                <EF:EFColumn ename="areaCode" cname="院区编码"  width="100" hidden="true"/>
                <EF:EFColumn ename="building" cname="楼编码"  width="100" hidden="true"/>
                <EF:EFColumn ename="floor" cname="层编码"  width="100" hidden="true"/>
                <EF:EFColumn ename="deptCode" cname="科室编码"  width="100" hidden="true"/>
                <!-- 展示列 -->
                <EF:EFColumn ename="areaName" cname="院区" width="150" align="center" readonly="true"/>
                <EF:EFColumn ename="buildingName" cname="楼" width="150" align="center" readonly="true" />
                <EF:EFColumn ename="floorName" cname="层" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="deptName" cname="病区" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="bedNo" cname="床位号/房间号" width="100" align="center" readonly="true"/>
                <EF:EFColumn ename="addNum" cname="地点编码" width="100" align="center" readonly="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
	<!-- 弹窗 -->
    <EF:EFWindow id="qrCode" width="70%" height="60%" modal="true" url="" title="查看二维码">
        <EF:EFRegion  title="" showClear="true">
            <div id="qrcode" style＝"letter-spacing:8px;">
            
            </div>
        </EF:EFRegion>
    </EF:EFWindow>
    <!-- 弹窗 -->
    <EF:EFWindow id="shEdit" width="550px" height="280px" modal="true" url="" title="录入">
        <EF:EFRegion id="edit" title="" showClear="true">
            <%-- <div class="row" >
                <EF:EFSelect ename="areaCode" cname="院区" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="hosArea" textField="typeName" valueField="paramValue1" />
                </EF:EFSelect>
            </div>
            <div class="row" >
                <EF:EFSelect ename="building" cname="楼" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="building" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div> --%>
            
            <div class="row" >
	            <EF:EFCascadeSelect ename="multiCode" autoBind="true" required="true"
	                cname="院区" textField="typeName" valueField="typeCode" 
	                methodName="queryHosArea" resultId="hosArea" colWidth="10">
	                <EF:EFOption label="请选择" value=""/>
	            </EF:EFCascadeSelect>
            </div>
            <div class="row" >
                <EF:EFCascadeSelect ename="deptCode" cname="病区" filter="contains" required="false"
                    cascadeFrom="multiCode" textField="deptName" valueField="deptCode"
                    methodName="queryQrArea" resultId="qrDept" colWidth="10">
                </EF:EFCascadeSelect>
            </div>
            <div class="row" >
	            <EF:EFCascadeSelect ename="building" cname="楼"  required="true"
	                cascadeFrom="multiCode" textField="typeName" valueField="typeCode"
	                methodName="queryBuildingByAreaCode" resultId="building" colWidth="10">
	            </EF:EFCascadeSelect>
            </div>
            <div class="row" >
                <EF:EFSelect ename="floor" cname="层" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="floor" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>
            <%-- <div class="row" >
                <EF:EFSelect ename="deptCode" cname="病区" optionLabel="请选择" colWidth="10" required="true"
                 filter="contains" >
                    <EF:EFOptions blockId="qrDept" textField="deptName" valueField="deptCode" />
                </EF:EFSelect>
            </div> --%>
            
            <div class="row">
                <EF:EFInput ename="bedNo" cname="床位号/房间号" colWidth="10" required="false" maxLength="30" />
            </div>
            <div class="row">
                <EF:EFInput ename="addNum" cname="地点编码" colWidth="10" required="false" maxLength="16" />
            </div>
        </EF:EFRegion>
        <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
        <EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
    </EF:EFWindow>
</EF:EFPage>
<style type="text/css">
    .divStyle p{
        text-align:center;
        font-size:20px;
        font-weight:bold;
        margin:5px;
        -webkit-margin-before: 0em;
        -webkit-margin-after: 0em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
    }
    
    .divStyle{
        margin:5px;
        float:left;
        text-align:center;
    }
</style>
<script type="text/javascript">
	//二维码前言
	var qrcodeSalt = "";
	//校验
	var editValidator = null;
	//提交类型
	var submitType = "insert";

	IPLATUI.EFGrid = {
	    "result": {
	        toolbarConfig: {
	            buttons: [
	                {
	                    name: "showQrcode",
	                    text: "查看二维码",
	                    layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
	                    icon: "css:fa-qrcode",
	                    attributes: {
	                        style: "float:left;height:30px;"
	                    },
	                    click: showQrcode
	                },
	                {
                        name: "exportQrcode",
                        text: "导出二维码",
                        layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
                        icon: "css:fa-file-archive-o",
                        attributes: {
                            style: "float:left;height:30px;"
                        },
                        click: exportFile
                    }
	            ]
	        }
	    },
	    "exportGrid": {
	    	exportServiceName: "SSBMCTM01",
	    	exportMethodName: "query",
	    }
	}
	//下拉框
	IPLATUI.EFCascadeSelect = {
		"inqu_status-0-floor" : {
			change : function(e){
				if(e.sender._old){
					var building = IPLAT.EFSelect.value($("#inqu_status-0-building"));
					var floor = e.sender._old;
                    //下拉选项变更时查询数据
			        var eiInfo = new EiInfo();
			        eiInfo.set("building", building);
			        eiInfo.set("floor", floor);
			        EiCommunicator.send("SSBMCTM01", "queryDept", eiInfo, {
			            onSuccess : function(ei) {
			                var rows = ei.blocks['dept'].getMappedRows();
			                console.log(rows);
			                for (var i = 0; i < rows.length; i++) {
			                    rows[i].textField = rows[i].typeName;
			                    rows[i].valueField = rows[i].typeCode;
			                }
			                var dataSource = new kendo.data.DataSource({ data: rows });
			                IPLAT.EFSelect.setDataSource($("#inqu_status-0-deptCode"), dataSource);
			            }
			        });
                }
			}
		},
		"inqu_status-0-building" : {
			change : function(e){
				if(e.sender._old){
					//楼被选择时科室可以被选择
					IPLAT.EFSelect.enable( $("#inqu_status-0-deptCode"), true );
                }else{
                	//楼未选择时科室不可被选择，且内容清空
                	IPLAT.EFSelect.enable( $("#inqu_status-0-deptCode"), false );
                	IPLAT.EFSelect.value( $("#inqu_status-0-deptCode"), null );
                }
			}
		}
	}

	
	//查看二维码
	function showQrcode(){
		qrCodeWindow.setOptions({"title":"查看二维码"});
        //打开弹窗
        qrCodeWindow.open().center();
        var selectRows = resultGrid.getCheckedRows();
        if(selectRows){
        	//展示二维码
            if(qrcodeSalt){
            	//var qrcodeSaltStr = qrcodeSalt[0].typeCode;
            	var parent = $("#qrcode");
	        	for (var i = 0; i < selectRows.length; i++) {
	        		var qrcodeSaltStr = qrcodeSalt[0].typeCode;
	        		qrcodeSaltStr = qrcodeSaltStr.replace("#addNum#", selectRows[i].addNum);
	        		qrcodeSaltStr = qrcodeSaltStr.replace("#areaName#", selectRows[i].areaName);
	        		qrcodeSaltStr = qrcodeSaltStr.replace("#areaCode#", selectRows[i].areaCode);
                    
	        		var id = selectRows[i].id,
	        		addNum = selectRows[i].addNum,
	        		buildingName = selectRows[i].buildingName,
	        		floorName = selectRows[i].floorName,
	                deptName = selectRows[i].deptName,
	                bedNo = selectRows[i].bedNo;
	                
		            var divContent = "<div id='qrcode"+i+"' class='divStyle'></div>";
		            var div = $(divContent);
		            div.appendTo(parent);
		            
		            $("#qrcode"+i).qrcode({
		                "render": "canvas",
		                "size": 200,
		                "color": "#000000",
		                "text": qrcodeSaltStr,
		                "logo":"images/icon6.png"
		            });
		            var width = $("#qrcode"+i).width();
		            var name = "<p tyle='width:"+width+"px;'>"+buildingName+" "+floorName+"</p>"
			            +"<p tyle='width:"+width+"px;'>"+deptName+" "+bedNo+"</p>";
		            var p = $(name);
		            p.appendTo(div);
				}
	       	}else{
	            NotificationUtil("请配置二维码前言！", "warning");
	        }
        }else{
        	NotificationUtil("请选择至少一条记录！", "warning");
        }
	}
	
	/* 按钮绑定事件 */
	$(function() {
		//初始设置科室为不可选
		IPLAT.EFSelect.enable( $("#inqu_status-0-deptCode"), false );
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
                IPLAT.EFSelect.value($("#multiCode"),value["multiCode"]);
                IPLAT.EFSelect.value($("#building"),value["building"]);
                IPLAT.EFSelect.value($("#floor"),value["floor"]);
                IPLAT.EFSelect.value($("#deptCode"),value["deptCode"]);
                IPLAT.EFInput.value($("#bedNo"),value["bedNo"]);
                IPLAT.EFInput.value($("#addNum"),value["addNum"]);
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
            var hosAreas = getBlocksMappedRows("hosArea");
            var areaCode = "";
            var multiCode = IPLAT.EFSelect.value($("#multiCode"));
            for (var i = 0; i < hosAreas.length; i++) {
            	var hosArea = hosAreas[i];
				if(multiCode == hosArea["typeCode"]){
					areaCode = hosArea["paramValue1"];
				}
			}
            //获取编辑的值
            var value = {
				multiCode : IPLAT.EFSelect.value($("#multiCode")),
           		areaCode : areaCode,
           		areaName : IPLAT.EFSelect.text($("#multiCode")),
           		building : IPLAT.EFSelect.value($("#building")),
           		buildingName : IPLAT.EFSelect.text($("#building")),
           		floor : IPLAT.EFSelect.value($("#floor")),
           		floorName : IPLAT.EFSelect.text($("#floor")),
           		deptCode : IPLAT.EFSelect.value($("#deptCode")),
           		deptName : IPLAT.EFSelect.text($("#deptCode")),
           		bedNo : IPLAT.EFInput.value($("#bedNo")),
           		addNum : IPLAT.EFInput.value($("#addNum"))
            };
            var selectRow = resultGrid.getCheckedRows()[0];
            if(!selectRow || selectRow.length < 1){
                selectRow = {};
            }else{
                selectRow = JSON.parse(JSON.stringify(resultGrid.getCheckedRows()[0]))
            }
            //复制数据
            $.extend(selectRow, value);
            //提交数据
            var rows = [];rows[0] = selectRow;
            eiInfo.set("submit", rows);
            EiCommunicator.send("SSBMCTM01", submitType, eiInfo, {
                onSuccess : function(ei) {
                    if(ei.status <= 0) {
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
        "qrCode": {
            close: function (e) {
                $("#qrcode").empty();
            }
        },
        "shEdit": {
            close: function (e) {
            	$("#edit-trash").click();
            }
        }
     }
   $(function(){
        //查询
        $("#QUERY").on("click", function(e) {
            refreshResultGrid();
        });
        refreshResultGrid();
        //启用校验
        editValidator = IPLAT.Validator({ id: "edit" });
        qrcodeSalt = getBlocksMappedRows("qrcodeSalt");
    });
</script>