<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜品信息 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
	      <EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;"/>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-menuName" cname="菜品名称" colWidth="4" ratio="3:8"/>
			
<%-- 			<EF:EFSelect ename="inqu_status-0-canteenNum" cname="食堂名称" 
                <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
                optionLabel="请选择" colWidth="4" ratio="3:8" >
            </EF:EFSelect> --%>
            
<%-- 			<EF:EFSelect ename="inqu_status-0-menuTypeNum" cname="菜品类型" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="menuType" textField="typeName" valueField="typeCode" />
            </EF:EFSelect> --%>
            
            <EF:EFCascadeSelect ename="inqu_status-0-canteenNum" autoBind="true"
		        cname="食堂名称" textField="typeName" valueField="typeCode" 
		        resultId="canteenData" ratio="3:8" colWidth="4">
		        <EF:EFOption label="请选择" value=""/>
		    </EF:EFCascadeSelect>
            
            <EF:EFCascadeSelect ename="inqu_status-0-menuTypeNum" 
		        cascadeFrom="inqu_status-0-canteenNum"
		        cname="菜品类型" textField="typeName" valueField="typeCode"
		        methodName="getMenuTypeData" resultId="menuType" ratio="3:8" colWidth="4">
		        <EF:EFOption label="请选择" value=""/>
		    </EF:EFCascadeSelect>
            
            
		</div>
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-suitGroup" cname="适宜人群" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="orderCrowd" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-notSuitGroup" cname="不适宜人群" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOptions blockId="orderCrowd" textField="typeName" valueField="typeCode" />
            </EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="菜品状态" 
                optionLabel="请选择" colWidth="4" ratio="3:8">
                <EF:EFOption label="启用" value="1"/>
                <EF:EFOption label="停用" value="0"/>
            </EF:EFSelect>
		</div>
	</EF:EFRegion>
	   <!-- 隐藏一个MultiSelect用来取值 -->
    <div style="display:none;">
    <EF:EFMultiSelect ename="hiddenMultiSelect" cname="适宜人群" hidden="true"  >
        <EF:EFOptions blockId="orderCrowd" textField="typeName" valueField="typeCode"/>
    </EF:EFMultiSelect>
    </div>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="false" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="menuNum" cname="菜品编码"  width="100" hidden="true"/>
			<EF:EFColumn ename="canteenNum" cname="食堂编码" width="100" hidden="true"/>
			<EF:EFColumn ename="menuTypeNum" cname="菜品类型编码" width="100" hidden="true"/>
			<EF:EFColumn ename="statusCode" cname="状态编码" width="100" hidden="true" />
			<EF:EFColumn ename="operateCode" cname="菜品所属编号" width="100" hidden="true" />
			<EF:EFColumn ename="suitGroup" cname="适宜人群  " width="100" hidden="true"/>
            <EF:EFColumn ename="notSuitGroup" cname="不适宜人群 " width="100" hidden="true"/>
            <EF:EFColumn ename="nutrition" cname="营养价值 " width="100" hidden="true"/>
            <EF:EFColumn ename="heat" cname="热量 " width="100" hidden="true"/>
            <EF:EFColumn ename="comboRuleValue" cname="套餐类型 " width="100" hidden="true"/>
            <EF:EFColumn ename="menuDesc" cname="菜品备注" width="100" hidden="true"/>
            <EF:EFColumn ename="menuSupply" cname="菜品供应量" width="100" hidden="true"/>
			<!-- 展示列 -->
			<EF:EFColumn ename="menuPicUrl" cname="菜品图片" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="menuName" cname="菜品名称" width="150" align="center" readonly="true" />
			<EF:EFColumn ename="stmc" cname="食堂名称" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="menuTypeName" cname="菜品分类 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="syrq" cname="适宜人群  " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="bsyrq" cname="不适宜人群 " width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="menuPrice" cname="菜品单价 " format="{0:n}" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="dqzt" cname="菜品状态" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="operateName" cname="菜品所属" width="100" align="center" readonly="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="790px" height="600px" modal="true" url="" title="录入" style="display:none;">
		<EF:EFRegion id="edit" title="菜品信息" showClear="true" >
			<div class="row" >
<%--                 <EF:EFSelect ename="canteenNum" cname="食堂名称" optionLabel="请选择" colWidth="6" required="true">
                    <EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
                </EF:EFSelect> --%>
                
             <EF:EFCascadeSelect ename="canteenNum" autoBind="true" cname="食堂名称" textField="typeName" required="true"
                     valueField="typeCode" resultId="canteenData" colWidth="6">
		        <EF:EFOption label="请选择" value=""/>
		    </EF:EFCascadeSelect>
                
                
				<EF:EFInput ename="menuName" cname="菜品名称" colWidth="6" required="true" maxLength="200" />
            </div>
			<div class="row">
				<EF:EFCascadeSelect ename="menuTypeNum" cascadeFrom="canteenNum"
									cname="菜品类型" textField="typeName" valueField="typeCode" required="true"
									methodName="getMenuTypeDataWindow" resultId="menuType" colWidth="6">
					<EF:EFOption label="请选择" value=""/>
				</EF:EFCascadeSelect>
				<EF:EFInput ename="menuPrice" cname="菜品单价" colWidth="6" required="true" data-rules="non_negative_number" maxLength="11"/>
			</div>
			<div class="row" >
				<EF:EFSelect ename="statusCode" cname="是否启用" optionLabel="请选择" colWidth="6" required="true" defaultValue="1">
					<EF:EFOption label="启用" value="1"/>
					<EF:EFOption label="停用" value="0"/>
                </EF:EFSelect>
				<EF:EFSelect ename="operateCode" cname="菜品所属 " colWidth="6" required="true">
					<EF:EFOptions blockId="mealOperate" textField="typeName" valueField="typeCode" />
				</EF:EFSelect>

			</div>
			<div class="row" >
				<EF:EFInput ename="menuSupply" cname="供应量(份)" colWidth="6" required="true" data-rules="positive_integer" maxLength="10" />
				<EF:EFInput ename="menuFee" cname="打包费" colWidth="6" data-rules="non_negative_number" maxLength="11"/>
			</div>
			<div class="row" >
				<EF:EFMultiSelect ename="suitGroup" cname="适宜人群 " optionLabel="请选择" colWidth="6" >
					<EF:EFOptions blockId="orderCrowd" textField="typeName" valueField="typeCode" />
				</EF:EFMultiSelect>
				<EF:EFMultiSelect ename="notSuitGroup" cname="不适宜人群 " optionLabel="请选择" colWidth="6">
					<EF:EFOptions blockId="orderCrowd" textField="typeName" valueField="typeCode" />
				</EF:EFMultiSelect>
            </div>
			<div class="row" >
                <EF:EFInput ename="heat" cname="热量(KJ)" colWidth="6" data-rules="number" maxLength="10" />
				<EF:EFSelect ename="comboRuleValue" cname="菜品属性 " optionLabel="请选择" colWidth="6">
                    <EF:EFOptions blockId="comboRuleType" textField="typeName" valueField="typeCode" />
                </EF:EFSelect>
            </div>

			<div class="row" >
				<EF:EFInput ename="nutrition" cname="营养价值" type="textarea" ratio="2:10" colWidth="12" maxLength="200" />
            </div>
			<div class="row" >
                <EF:EFInput ename="descption" cname="菜品描述" type="textarea" ratio="2:10" colWidth="12"  maxLength="500" />
            </div>
            <div class="row" >
                <EF:EFInput ename="menuDesc" cname="菜品备注" type="textarea" ratio="2:10" colWidth="12"  maxLength="500" />
	        </div>
		</EF:EFRegion>
		<!-- 菜品组成 -->
		<EF:EFRegion id="composition " title="菜品组成" showClear="true" style="height:210px;">
		      <EF:EFGrid blockId="description" autoDraw="no" rowNo="false" autoBind="false" 
			      serviceName="SSBMCPXX02" queryMethod="query" insertMethod="insert" 
			      updateMethod="update" deleteMethod="delete" >
			    <!-- 隐藏列 -->
			    <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			    <EF:EFColumn ename="relationId" cname="关联id" width="100" hidden="true"/>
	            <!-- 展示列 -->
	            <EF:EFColumn ename="material" cname="材料" width="150" align="center" />
	            <EF:EFColumn ename="quantum" cname="份量" width="150" align="center" />
	            <EF:EFColumn ename="description" cname="单位" width="100" align="center" />
		      </EF:EFGrid>
		</EF:EFRegion>
		<!-- 图片展示 -->
		<EF:EFRegion id="" title="图片展示" showClear="true">
			<EF:EFRegion id="uploadDiv" head="hidden" style="width:400px;height:200px;float:left;">
				<%--文件上传组件EUDM01--%>
				<EF:EFUpload ename="file1" docTag="sc_bm_file" path="sc/img"/>
			</EF:EFRegion>
			<div id="showImg" style="width:300px;float:left;text-align: center;"></div>
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
	var defaultDishesNum = "";
	var defaultDishesList = _getEi().blocks['defaultDishesNum'].getMappedRows();
	for (var i = 0; i < defaultDishesList.length; i++) {
		var value = defaultDishesList[0]['value'];
		if(value == "defaultDishesNum"){
			defaultDishesNum = defaultDishesList[0]['label'];
		}
	}
	//图片上传组件
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
					//获取文件路径
					var eiInfo = new EiInfo();
					eiInfo.set("docId",e.response.docId);
					eiInfo.set("docTag",e.response.docTag);
					eiInfo.set("docUrl",e.response.docUrl);
					eiInfo.set("fileType",e.files[0]["extension"]);
					EiCommunicator.send("SSBMCPXX01", "uploadFile", eiInfo, {
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
		},
	    file1: {
	        showFileList : true,
	        loadComplete : function(e) {
	            var uploader = e.sender.uploader;
	            uploader.clearAllFiles(); // 清空所有历史
	        },
	        validation : {
	            allowedExtensions: [".jpg",".png",".jpeg"]
	        },
	        localization : {
	            invalidFileExtension: "请选择格式为jpg或png的图片！"
	        },
	        upload : function (e){
	        	//拦截默认操作
	            e.preventDefault();
	            var file = e.files[0].rawFile;
	            //把文件转成base64
	            imgCompress(file,0.5,function(base64){
	                //提交数据
	                var eiInfo = new EiInfo();
	                eiInfo.set("pics", base64);
	                //IPLATUI.CONTEXT_PATH
	                EiCommunicator.send("SSBMCPXX01","uploadPic",eiInfo,{
	                    onSuccess : function(ei) {
	                        //清空图片预览,只保留一张
	                        $("#showImg").empty();
	                        //预览图片
	                        var fileIdList = ei.extAttr.fileIdList;
	                        for (var i = 0; i < fileIdList.length; i++) {
	                            console.log(fileIdList[i]);
								var map = fileIdList[i];
	                            //var src = "${ctx}"+"/" +ei.extAttr.showPath+"/"+fileIdList[i]+".jpg";
	                            var src = map["impStr"];
	                            var fileId = map["picId"];
	                            
	                            $("#showImg").append("<div id='"+fileId+"_div' class='imgDiv'><img src='" +src+ "' id='"+ fileId +"' class='showImg'"+"></img></div>");
	                            //在图片上附加一个删除按钮
	                            $("#" + fileId+"_div").append(
                                    "<div class='deleteBtn'><button type='button' id='"+fileId+"_delete'>"
                                    +"<i class='k-icon'></i>"
                                    +"</button></div>" );
	                        }
	                    }
	                });
	            });
	        }
	    }
	}
	//下载补贴模板
	function downloadFile() {
		//获取文件路径
		var url = IPLATUI.CONTEXT_PATH + "/" + "MEAL/template/CPXX-Template.xls";
		const a = document.createElement('a'); // 创建a标签
		a.setAttribute('download', '');// download属性
		a.setAttribute('href', url);// href链接
		a.click();// 自执行点击事件
	}
	//上传文件
	function uploadFile() {
		//触发组件
		$("#file3").click();
	}
	var baseUrl = "${ctx}";
	IPLATUI.EFGrid = {
		"description": {
			pageable: false
		},
	    "result": {
	        columns :[{
	            field: "menuPicUrl",
	            template: "<div class='gridImgDiv'><img src='#=imgStr?imgStr:\"${ctx}/MEAL/image/noneImg.png\"#' class='gridImg'></img><div>"
	        }],  
	        dataBinding: function (e) {
	            //grid数据绑定时对属性进行处理
	            var canteenData = getBlocksMappedRows("canteenData");
	            for (var i = 0, length = e.items.length; i < length; i++) {
	                if(isAvailable(e.items[i].statusCode)){
	                    e.items[i].dqzt = e.items[i].statusCode == "0" ? "停用" : "在用";
	                }
	                for (var j = 0; j < canteenData.length; j++) {
	                    if(e.items[i].canteenNum == canteenData[j].typeCode){
	                        e.items[i].stmc = canteenData[j].typeName;
	                    }
	                }
	                if(isAvailable(e.items[i].suitGroup)){
	                    //为MultiSelect赋值
	                    $("#hiddenMultiSelect").data("kendoMultiSelect").value(e.items[i]["suitGroup"].split(","));
	                    $("#hiddenMultiSelect").data("kendoMultiSelect").trigger("change");
	                    //从MultiSelect取值，适宜人群
	                    e.items[i].syrq = _.map($("#hiddenMultiSelect").data("kendoMultiSelect").dataItems(), _.iteratee("textField")).join(",")
	                }
	                if(isAvailable(e.items[i].notSuitGroup)){
	                    //为MultiSelect赋值
	                    $("#hiddenMultiSelect").data("kendoMultiSelect").value(e.items[i]["notSuitGroup"].split(","));
	                    $("#hiddenMultiSelect").data("kendoMultiSelect").trigger("change");
	                    //从MultiSelect取值，不适宜人群
	                    e.items[i].bsyrq = _.map($("#hiddenMultiSelect").data("kendoMultiSelect").dataItems(), _.iteratee("textField")).join(",")
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
					text : "导入菜品",
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

	/* 按钮绑定事件 */
	$(function() {
	    //查询
	    $("#QUERY").on("click", function(e) {
	        refreshResultGrid();
	    });
	    //删除图片append追加的元素绑定点击事件
	    $("#showImg").on('click', '.deleteBtn',function(e){
	        //提交数据
	        var eiInfo = new EiInfo();
	        var imgId = this.parentNode.id.split("_")[0];
	        eiInfo.set("imgId", imgId);
	        EiCommunicator.send("SSBMCPXX01", "deletePic", eiInfo, {
	            onSuccess : function(ei) {
	                if(ei.extAttr.deleteNo == 1 || ei.extAttr.deleteNo == 0) {
	                    NotificationUtil("操作成功！", "success");
	                    //删除图片div
	                    $("#"+imgId+"_div").remove();
	                }else {
	                    NotificationUtil("操作失败！", "error");
	                }
	            }
	        });
	    });
	    //生效
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
	                    EiCommunicator.send("SSBMCPXX01", "effect", eiInfo, {
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
			IPLAT.EFInput.value($("#menuSupply"),defaultDishesNum);
	        //打开弹窗
	        shEditWindow.open().center();
	    });
	    //编辑
	    $("#EDIT1").on("click", function(e) {
	        submitType = "update";
	        shEditWindow.setOptions({"title":"编辑"});
	        var rows = resultGrid.getCheckedRows();
	        if(rows.length == 1){
				IPLAT.EFInput.value($("#menuSupply"),defaultDishesNum);
	            //打开弹窗
	            shEditWindow.open().center();
	            //为表单赋值
	            var value = rows[0];
	            IPLAT.EFSelect.value($("#canteenNum"),value["canteenNum"]);
	            IPLAT.EFInput.value($("#menuName"),value["menuName"]);
	            IPLAT.EFInput.value($("#menuPrice"),value["menuPrice"]);
				if(value["suitGroup"]){
					$("#suitGroup").data("kendoMultiSelect").value(value["suitGroup"].split(","));
					$("#suitGroup").data("kendoMultiSelect").trigger("change");
				}
				if(value["notSuitGroup"]){
					$("#notSuitGroup").data("kendoMultiSelect").value(value["notSuitGroup"].split(","));
					$("#notSuitGroup").data("kendoMultiSelect").trigger("change");
				}
	            IPLAT.EFSelect.value($("#menuTypeNum"),value["menuTypeNum"]);
	            IPLAT.EFInput.value($("#nutrition"),value["nutrition"]);
	            IPLAT.EFSelect.value($("#comboRuleValue"),value["comboRuleValue"]);
	            IPLAT.EFInput.value($("#heat"),value["heat"]);
	            IPLAT.EFSelect.value($("#operateCode"),value["operateCode"]);
	            IPLAT.EFSelect.value($("#statusCode"),value["statusCode"]);
	            IPLAT.EFInput.value($("#menuSupply"),value["menuSupply"]);
	            IPLAT.EFInput.value($("#descption"),value["descption"]);
	            IPLAT.EFInput.value($("#menuFee"),value["menuFee"]);
	            IPLAT.EFInput.value($("#menuDesc"),value["menuDesc"]);
	            //加载descriptionGrid菜品组成数据
	            var eiInfo = new EiInfo();
	            eiInfo.set("relationId", value["id"]);
	            EiCommunicator.send("SSBMCPXX02", "query", eiInfo, {
	                onSuccess : function(ei) {
	                    //为descriptionGrid赋值
	                    descriptionGrid.setEiInfo(ei)
	                }
	            });
	            //加载预览图片
	            var eiInfo = new EiInfo();
	            eiInfo.set("mealId", value["id"]);
	            EiCommunicator.send("SSBMCPXX01","getPic",eiInfo,{
	                onSuccess : function(ei) {
	                    //预览图片
	                    var mealPics = ei.extAttr.mealPics;
	                    for (var i = 0; i < mealPics.length; i++) {
	                        console.log(mealPics[i]);
	                       // var src = "${ctx}" +mealPics[i].fileUrl;
	                        var src = mealPics[i]["imgStr"];
	                        var fileId = mealPics[i].picId;
	                        //$("#showImg").append("<img src='" +src+ "' id='"+ mealPics[i].picId +"' class='showImg'"+"></img>");
	                        $("#showImg").append("<div id='"+fileId+"_div' class='imgDiv'><img src='" +src+ "' id='"+ fileId +"' class='showImg'"+"></img></div>");
	                        //在图片上附加一个删除按钮
	                        $("#" + fileId+"_div").append(
	                                "<div class='deleteBtn'><button type='button' id='"+fileId+"_delete'>"
	                                +"<i class='k-icon'></i>"
	                                +"</button></div>" );
	                    }
	                }
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
	    //提交
	    $("#submit").on("click", function(e) {
	    	//校验必填项
	        if (!editValidator.validate()) {
	        	/*WindowUtil({
                    'title' : '提醒',
                    'content' : "<div class='kendo-del-message'>请检查校验项</div>"
                });*/
	        	NotificationUtil("请检查校验项", "warning");
	            //校验不通过
	            return ;
	        }
	        var eiInfo = new EiInfo();
	        //获取编辑的值
	        var value = {
	                canteenNum : IPLAT.EFSelect.value($("#canteenNum")),
	                menuName : IPLAT.EFInput.value($("#menuName")),
	                menuPrice : IPLAT.EFInput.value($("#menuPrice")),
	                suitGroup :$("#suitGroup").data("kendoMultiSelect").value().join(","),
	                notSuitGroup :$("#notSuitGroup").data("kendoMultiSelect").value().join(","),
	                menuTypeNum : IPLAT.EFSelect.value($("#menuTypeNum")),
	                menuTypeName : IPLAT.EFSelect.text($("#menuTypeNum")),
	                nutrition : IPLAT.EFInput.value($("#nutrition")),
	                comboRuleValue : IPLAT.EFSelect.value($("#comboRuleValue")),
					heat : IPLAT.EFInput.value($("#heat")),
					menuSupply : IPLAT.EFInput.value($("#menuSupply")),
					operateCode : IPLAT.EFSelect.value($("#operateCode")),
					operateName : IPLAT.EFSelect.text($("#operateCode")),
					statusCode : IPLAT.EFSelect.value($("#statusCode")),
					menuSupply : IPLAT.EFInput.value($("#menuSupply")),
					descption : IPLAT.EFInput.value($("#descption")),
					menuFee : IPLAT.EFInput.value($("#menuFee")),
					menuDesc : IPLAT.EFInput.value($("#menuDesc"))
	        };
	        var selectRow = resultGrid.getCheckedRows()[0];
	        if(!selectRow){
	            selectRow = {};
	        }
	        //复制数据
	        $.extend(selectRow, value);
	        
	        var rows = [];rows[0] = selectRow;
	        eiInfo.set("submit", rows);
	        //菜品组成数据
	        eiInfo.set("description", descriptionGrid.getDataItems());
	        //图片数据
	        var imgArray = $(".showImg");
	        var pics = [];
	        for (var i = 0; i < imgArray.length; i++) {
	            pics.push(imgArray[i].id);
	        }
	        eiInfo.set("pics",pics);
	        //提交数据
	        EiCommunicator.send("SSBMCPXX01", submitType, eiInfo, {
	            onSuccess : function(ei) {
	                if(ei.status == 0) {
	                    NotificationUtil(ei.getMsg(), "error");
	                }else {
	                    NotificationUtil(ei.getMsg(), "success");
	                    //刷新grid
	                    refreshResultGrid();
	                    //关闭弹窗
	                    $("#cancel").click();
	                    
	                }
	            }
	        });
	    });
	    //删除菜品组成
	    $("#DELETE1").on("click", function(e) {
	        descriptionGrid.removeRows(descriptionGrid.getCheckedRows());
	    });
	    //取消
	    $("#cancel").on("click", function(e) {
	        window['shEditWindow'].close();
	    });
	    
	});
	// 弹窗关闭事件
    IPLATUI.EFWindow = {
        "shEdit": {
            close: function (e) {
                //EFRegion的id-trash
                $("#edit-trash").click();
                //清空descriptionGrid菜品组成
                descriptionGrid.removeRows(descriptionGrid.getDataItems());
                //清空图片预览
                $("#showImg").empty();
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
		//禁止上传组件多选
		$("#file1").removeAttr("multiple");
		//限制上传组件文件类型
		$("#file1").attr("accept",".jpg,.jpeg,.png");
        //页面加载时查询一次
        $("#QUERY").click();
	    //启用校验
	    editValidator = IPLAT.Validator({ id: "edit" });
	});
</script>