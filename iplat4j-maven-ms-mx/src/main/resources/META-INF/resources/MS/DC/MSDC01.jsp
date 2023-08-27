<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<div class="col-md-3">
        <EF:EFRegion id="R1" title="区域名称" fitHeight="true">
            <EF:EFTree id="tree01" textField="text" valueField="label"
                       hasChildren="leaf" serviceName="MSDC03" methodName="queryTree">
            </EF:EFTree>
        </EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="inqu_status-0-code" cname="设备代码：" />
				<EF:EFInput ename="inqu_status-0-name" cname="设备名称：" />
                <EF:EFInput ename="inqu_status-0-param" cname="参数类型Tag：" />
                <EF:EFInput ename="inqu_status-0-parentId" cname="" hidden="true"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="设备分类列表">
			<EF:EFGrid blockId="result" autoDraw="no"  checkMode="single" >
                <EF:EFColumn ename="DC_id" cname="设备id" width="100" hidden="true"/>
                <EF:EFColumn ename="code" cname="设备代码" width="100" disabled="true"/>
                <EF:EFColumn ename="name" cname="设备名称" width="100" disabled="true"/>
                <EF:EFColumn ename="t_area_classify_id" cname="参数Tag" width="100" hidden="true"/>
                <EF:EFColumn ename="group_id" cname="分组" width="100"  hidden="true"/>
                <EF:EFColumn ename="weight" cname="权重" width="100" disabled="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
		<%--<video src="http://192.168.1.66:8081/stream" type="video/ogg" width="600" height="450" autoplay="autoplay" controls="controls" loop="loop"></video>--%>
			<%--<img style="-webkit-user-select: none;margin: auto;background-color: hsl(0, 0%, 25%);" src="http://121.36.98.137:5000/v/18">--%>
	</div>

<EF:EFWindow id="popData" url=" " lazyload="true" width="1020px"
	height="70%">
</EF:EFWindow>
<EF:EFWindow id="popData2" url=" " lazyload="true" width="25%"
             height="50%">
</EF:EFWindow>
<EF:EFWindow id="popData3" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>
<EF:EFWindow id="popData4" url=" " lazyload="true" width="60%"
             height="70%">
<%--	<script src="${ctx}/MS/DC/flv.min.js"></script>;--%>
<%--	<div>--%>
<%--	<video id="vVideo" width="600" height="500" controls />;--%>
<%--	</div>--%>

<%--	<script>--%>
<%--	//原生H5支持的媒体格式主要有MP4、OGG、WebM、M3U8--%>
<%--	if (flvjs.isSupported()) {--%>
<%--	var videoElement = document.getElementById('vVideo');--%>
<%--	var flvPlayer = flvjs.createPlayer({--%>
<%--	type: 'flv',--%>
<%--	url:'http://192.168.1.66:80/live?port=1935&app=live&stream=10240'--%>
<%--	});--%>
<%--	flvPlayer.attachMediaElement(videoElement);--%>
<%--	flvPlayer.load();--%>
<%--	flvPlayer.play();--%>
<%--	}--%>
<%--	</script>--%>

</EF:EFWindow>
<EF:EFWindow id="popData5" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>
<%--导入功能--%>
<EF:EFWindow id="execlImport" url="" lazyload="true" refresh="true" width="60%" height="60%">
	<EF:EFRegion id="sxUpload" title="设备配置和点位参数导入" style="display:none">
		<h4>设备配置和点位参数导入规则：111</h4>
		<li>参数项Tag（必填; ）</li>
		<li>参数项名称（必填; ）</li>
		<li>描述（非必填）</li>
		<li>参数项类型（必填; 0：通讯量；1：开关量；2:枚举量）</li>
		<li>参数所属分类名称（必填; 取值为系统中已录入的参数分类名称）</li>
		<li>所属设备名称（必填; 取值为系统中已录入的设备名称）</li>
		<li>数据格式（非必填; ）</li>
		<li>计量单位（非必填; ）</li>
		<li>计量单位名称（非必填; ）</li>
		<li>计量量纲（非必填; ）</li>
		<li>计量量纲名称（非必填; ）</li>
		<li>死区时间（非必填; 指定时间范围内不报警，单位秒）</li>
		<li>报警启用状态（必填; 0未启用：1：启用）</li>
		<li>参数启用状态（必填; 0：未启用；1：启用）</li>
		<li>真值标签（非必填; 1的替换文字）</li>
		<li>假值标签（非必填; 0的替换文字）</li>
		<li>是否记录日志（必填; 0：否；1：是）</li>
		<br/>
		<form id="excelForm"  enctype="multipart/form-data">
			文件上传<input id="excelFile" type="file" name="file" ><br />
		</form>
		<button id="download">模板下载</button>
	</EF:EFRegion>
	<div class="button-region" id="buttonDiv" style="float: right">
		<EF:EFButton cname="提交" ename="IMPORTSUBMIT" layout="0" ></EF:EFButton>
		<EF:EFButton cname="关闭" ename="IMPORTCLOSE" layout="0" ></EF:EFButton>
	</div>
</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	var datagrid = null;
	IPLATUI.EFGrid = {
		"result" : {
            onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}else{
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
	      toolbarConfig:{
		        hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,/*'delete': false,*/
				buttons:[{
			        name: "IMPORT",text: "导入",layout:"5",
					// click: function () {
					// 	var wgroupNum = IPLAT.EFSelect.value($("#wgroupNum"));
					// 	if(isEmpty(wgroupNum)){
					// 		NotificationUtil("请先选择维修科室", "error");
					// 		return;
					// 	}
					// 	personChooseWindowOpen(wgroupNum);
					// }
				}
					// {
					// 	name: "lookView",text: "查看视频",layout:"6",}
				]
	          }
		}
	}

	function popData(DC_id, type1,pid) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0101?initLoad&id=" + DC_id
                    + "&type1=" + type1+"&pid=" + pid;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	}
    function popData2(id) {
       var url = IPLATUI.CONTEXT_PATH + "/web/MSDC02?initLoad&id="+id;
       var popData2 = $("#popData2");
        popData2.data("kendoWindow").setOptions({
            open : function() {
                popData2.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popData2Window.open().center();
    }

    function popData3(DC_id, type1,pid) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0102?initLoad&id=" + DC_id
            + "&type1=" + type1+"&pid=" + pid;
        var popData3 = $("#popData3");
        popData3.data("kendoWindow").setOptions({
            open : function() {
                popData3.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popData3Window.open().center();
    }

    function popData4(DC_id, type1,pid) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSDC04?initLoad&id=" + DC_id
            + "&type1=" + type1+"&pid=" + pid;
        var popData4 = $("#popData4");
        popData4.data("kendoWindow").setOptions({
            open : function() {
                popData4.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popData4Window.open().center();
    }

    function popData5(id, type) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0103?initLoad&id=" + id
            + "&type=" + type;
        var popData5 = $("#popData5");
        popData5.data("kendoWindow").setOptions({
            open : function() {
                popData5.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popData5Window.open().center();
    };


	$(function(){
		// $(document).keydown(function(event){
		// 	console.log("1111");
		// 	if(event.keyCode ==13){
		// 		$(".k-grid-IMPORT").click();
		// 	}
		// });


        $("#inqu_status-0-parentId").css("display", "none");
        $("#VIDEO").css("display", "none");
        $("#VIEW").css("display", "none");
        $("#BINDMSPL").css("display", "none");
		//新增按钮
		$("#ADDDEPT").on("click", function(e) {
		    var parentId = $("#inqu_status-0-parentId").val();
		    if(parentId==''||parentId=='root') {
                IPLAT.alert("请选择左侧树状菜单并且不能选择根节点");
                return;
            }
            else {
                popData("", "adddept", $("#inqu_status-0-parentId").val());
            }
             });
          //  popData($("#inqu_status-0-parentId").val());
		//编辑按钮
		$("#EDIT").on("click", function(e) {
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行修改");
            } else {
                    popData(datagrid.id, "edit");
                 //   datagrid = null;
                }
		});
		//移动按钮
		$("#MOVE").on("click", function(e) {
            if ( $(".check-one").is(':checked')==false) {
				IPLAT.alert("请先选择一条记录进行移动");
			} else {
                popData2(datagrid.id);
           //     datagrid = null;
			}
		});
        //查看按钮
        $("#VIEW").on("click", function(e) {
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行查看");
            }else {
       //      var   popData4=document.getElementsByName("DC_id");
       //          var popData4 = $("#inqu_status-0-code").val();
       //          IPLAT.alert(popData4);
                popData3(datagrid.id,"edit","");
            //    datagrid = null;
            }
        });
        $("#DELETE").on("click", function(e) {
            if ($(".check-one").is(':checked') == false) {
                IPLAT.alert("请先选择一条记录进行删除");
            }
        });
        // //查看视频源按钮
        // $("#VIDEO").on("click", function(e) {
        //     if ($(".check-one").is(':checked') == false) {
        //         IPLAT.alert("请先选择一条视频源进行修改");
        //     } else {
        //         popData4(datagrid.id, "edit");
        //         datagrid = null;
        //     }
        // });

		//数据导入
		$("#IMPORT").on("click", function(e) {
			execlImportWindow.open().center()
			$("#sxupload").css("display","block");
		});

		//看视频
		$("#lookView").on("click", function(e) {
			popData4Window.open().center()
			//$("#sxupload").css("display","block");
		});

		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
            document.getElementById("inqu_status-0-code").value="";
            document.getElementById("inqu_status-0-name").value="";
            document.getElementById("inqu_status-0-param").value="";
	//		document.getElementById("inqu-trash").click();
            var str2 = $("#t_area_classify_id").val();
			resultGrid.dataSource.query($("#inqu_status-0-parentId").val());
		});
		//新增区域
        $("#INCREASE").on("click", function(e) {
            var popData4 = $("#inqu_status-0-parentId");
            console.log(popData4);
            IPLAT.alert(popData4);
            resultGrid.dataSource.query(1);
        });
        //点位绑定页面
        $("#BINDMSPL").on("click", function (e) {
            if (datagrid == null) {
                IPLAT.alert("请先选择一条记录进行绑定");
            } else {
                popData5(datagrid.id, "tab");
                //datagrid = null;
            }
        })

		//提交数据导入
		var uploading = false;
		$("#IMPORTSUBMIT").on("click", function (e) {
			// 防止连续提交
			$("#SUBMIT").attr("disabled",true);
			setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
			//参数处理
			var form =  new FormData();
			form.append("fileUpload",$('#excelFile')[0].files[0]);
			//数据校验
			if ($('#excelFile')[0].files[0] == null){
				NotificationUtil("请上传文件","error");
				return;
			}
			if(uploading){
				NotificationUtil("数据正在提交中，请不要重复点击提交","warning");
				return;
			}
			//数据提交
			$.ajax({
				url: IPLATUI.CONTEXT_PATH+"/msmxImport",
				type: 'POST',
				cache: false,
				processData: false,
				contentType: false,
				dataType : 'json',
				data:form,
				beforeSend: function(){ uploading = true; },
				success : function(data) {
					uploading = false;
					if(data.msg == "all"){
						NotificationUtil("数据导入成功","success");
						window['execlImportWindow'].close();
						// setTimeout(function() {
						// 	window.parent.location.reload()
						// }, 600);
					}else if (data.msg == "part"){
						NotificationUtil("导入数据存在问题，请查看返回文件","warning");
						downloadFileByBase64('data:application/xls;base64,'+data.base64, "ms_mx_error.xls");
						window['execlImportWindow'].close();
						// setTimeout(function() {
						// 	window.parent.location.reload()
						// }, 600);
					}else if (data.msg == "error"){
						NotificationUtil("数据导入失败","error");
						window['execlImportWindow'].close();
					}

				}
			});
		});

		//关闭数据导入窗口
		$("#IMPORTCLOSE").on("click", function (e) {
			execlImportWindow.close();
			$("#sxupload").css("display", "none");
		});

		//数据导入模板下载
		$("#download").click(function(){
			window.location.href =  IPLATUI.CONTEXT_PATH+"/msmxImport";
		});
	})

	//下载excel文件
	function downloadFileByBase64(base64,name){
		var myBlob = dataURLtoBlob(base64)
		var myUrl = URL.createObjectURL(myBlob)
		var failFile = document.createElement("a")//创建a标签
		failFile.setAttribute("href",myUrl)
		failFile.setAttribute("download",name)
		failFile.setAttribute("target","_blank")
		var clickEvent = document.createEvent("MouseEvents");
		clickEvent.initEvent("click", true, true);
		failFile.dispatchEvent(clickEvent);
	}

	//数据转换成Blob
	function dataURLtoBlob(dataurl) {
		var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
				bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
		while (n--) {
			u8arr[n] = bstr.charCodeAt(n);
		}
		return new Blob([u8arr], { type: mime });
	}

</script>