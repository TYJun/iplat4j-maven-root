<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜品分类 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
<link href="${ctx}/MEAL/common/css/common.css" rel="stylesheet">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
	   <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-mealTypeNum" cname="餐类编码" />
			<EF:EFInput ename="inqu_status-0-mealTypeName" cname="餐类名称" />
			<EF:EFSelect ename="inqu_status-0-spuerMealTypeNum" cname="食堂名称" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
		</div>
	</EF:EFRegion>

	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="true" readonly="false" checkMode="row" >
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="spuerMealTypeNum" cname="食堂编码" width="100" hidden="true"/>
			<EF:EFColumn ename="statusCode" cname="状态编码" width="100" hidden="true" />
			<!-- 展示列 -->
			<EF:EFColumn ename="mealTypeNum" cname="餐类编码" width="150" align="center" readonly="true"/>
			<EF:EFColumn ename="mealTypeName" cname="餐类名称" width="150" align="center" readonly="true" />
			<EF:EFColumn ename="spuerMealTypeName" cname="食堂名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="memo" cname="备注" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="dqzt" cname="当前状态" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="550px" height="270px" modal="true" url="" title="录入">
		<EF:EFRegion id="edit" title="" showClear="true">
			<div class="row" >
                <EF:EFSelect ename="spuerMealTypeNum" cname="食堂名称" optionLabel="请选择" colWidth="10" required="true">
                    <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>
			<div class="row" >
				<EF:EFSelect ename="statusCode" cname="当前状态" optionLabel="请选择" colWidth="10" required="true">
					<EF:EFOption label="在用" value="1"/>
					<EF:EFOption label="停用" value="0"/>
                </EF:EFSelect>
			</div>
			<div class="row">
				<EF:EFInput ename="mealTypeName" cname="餐类名称" colWidth="10" required="true" maxLength="50" />
			</div>
			<div class="row" >
                <EF:EFInput ename="memo" cname="备注" type="textarea" colWidth="10" maxLength="200" />
            </div>
		</EF:EFRegion>
		<EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
		<EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
	</EF:EFWindow>
	<%--文件上传组件--%>
	<EF:EFUpload ename="file3" docTag="sc_bm_file" path="sc/file" style="display:none;"/>
</EF:EFPage>
<script type="text/javascript">

	//提交类型
	var submitType = "insert";
	var editValidator = null;
	IPLATUI.EFGrid = {
	    "result": {
	        dataBinding: function (e) {
	            //grid数据绑定时对属性进行处理
	            for (var i = 0, length = e.items.length; i < length; i++) {
	                if(isAvailable(e.items[i].statusCode)){
	                    e.items[i].dqzt = e.items[i].statusCode == "0" ? "停用" : "在用";
	                }
	            }
	        },
            beforeRequest: function (e) {
                //查询之前添加参数，避免点击清除按钮后条件失效
                IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
            },
			toolbarConfig : {
				buttons : [ {
					name : "exportTemplate",
					text : "导出模板",
					layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
					icon : "css:fa-file-archive-o",
					attributes : {
						//style : "float:left;height:30px;"
					},
					click : downloadFile
				}, {
					name : "importType",
					text : "导入分类",
					layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
					icon : "css:fa-file-archive-o",
					attributes : {
						//style : "float:left;height:30px;"
					},
					click : uploadFile
				} ]
			}
	    }
	}
    IPLATUI.EFUpload = {
        file3:{
			validation:{
				allowedExtensions: [".xls",".xlsx"] //文件格式过滤
			},
			localization: {
				invalidFileExtension: "请按照模板类型上传xls文件"
			},
			loadComplete: function(e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles(); // 清空所有历史
			},
            success: function(e) {
				if(e.operation == "upload"){
					debugger;
					//获取文件路径
					var eiInfo = new EiInfo();
					eiInfo.set("docId",e.response.docId);
					eiInfo.set("docTag",e.response.docTag);
					eiInfo.set("docUrl",e.response.docUrl);
					eiInfo.set("fileType",e.files[0]["extension"]);
					EiCommunicator.send("SSBMCPFL01", "uploadFile", eiInfo, {
						onSuccess : function(ei) {
							console.log(ei);
							if(ei.status == 1){
								NotificationUtil(ei.msg, "success");
                                //刷新grid
                                refreshResultGrid();
							}else{
								NotificationUtil(ei.msg, "warning");
							}
						}
					});
				}
            }
        }
    }

	//下载补贴模板
	function downloadFile() {
		//获取文件路径
        var url = IPLATUI.CONTEXT_PATH + "/" + "MEAL/template/CPFL-Template.xls";
        const a = document.createElement('a'); // 创建a标签
        a.setAttribute('download', '');// download属性
        a.setAttribute('href', url);// href链接
        a.click();// 自执行点击事件
	}

	function uploadFile() {
		$("#file3").click();
	}

	/* 按钮绑定事件 */
	$(function() {
	    //查询
	    $("#QUERY").on("click", function(e) {
	        refreshResultGrid();
	    });
	    //启用 停用
	    $("#EFFECT").on("click", function(e) {
	        var rows = resultGrid.getCheckedRows();
	        if(rows.length >= 1){
	            IPLAT.confirm({
	                message: '<b>将会变更所选记录的启用状态！</br><font color="red">是否确定？</font></b>',
	                okFn: function (e) {
	                    //变更状态
	                    for (var i = 0; i < rows.length; i++) {
	                        if(rows[i].statusCode == "1"){
	                            rows[i].statusCode = "0";
	                            rows[i].dqzt = "停用";
	                        }else if(rows[i].statusCode == "0"){
	                            rows[i].statusCode = "1";
	                            rows[i].dqzt = "在用";
	                        }
	                    }
	                    //提交数据
	                    var eiInfo = new EiInfo();
	                    eiInfo.set("submit", rows);
	                    EiCommunicator.send("SSBMCPFL01", "update", eiInfo, {
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
	            IPLAT.EFSelect.value($("#spuerMealTypeNum"),value["spuerMealTypeNum"]);
	            IPLAT.EFInput.value($("#mealTypeName"),value["mealTypeName"]);
	            IPLAT.EFSelect.value($("#statusCode"),value["statusCode"]);
	            IPLAT.EFInput.value($("#memo"),value["memo"]);
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
	        		spuerMealTypeNum : IPLAT.EFSelect.value($("#spuerMealTypeNum")),
	        		spuerMealTypeName : IPLAT.EFSelect.text($("#spuerMealTypeNum")),
	        		mealTypeName : IPLAT.EFInput.value($("#mealTypeName")),
	        		statusCode : IPLAT.EFSelect.value($("#statusCode")),
	        		memo : IPLAT.EFInput.value($("#memo"))
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
	        EiCommunicator.send("SSBMCPFL01", submitType, eiInfo, {
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
        //登录用户
        IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
        //隐藏控件
        $("#inqu_status-0-userId").closest(".col-md-4").attr("style","display:none;");
		//隐藏上传组件
		$(".upload-file3").css("display","none");
		//禁止上传组件多选
		$("#file3").removeAttr("multiple");
		//限制上传组件文件类型
		$("#file3").attr("accept",".xls,.xlsx");
        //页面加载时查询一次
        $("#QUERY").click();
	    //启用校验
	    editValidator = IPLAT.Validator({ id: "edit" });
	});
</script>