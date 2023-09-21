<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <div class="row">
        <div class="col-md-3">
            <EF:EFRegion id="R1" title="物资分类" fitHeight="true">
                <div class="row"  style="margin-left: 60%" >
                        <%--checked="checked"--%>
<%--                    <EF:EFInput type="checkbox" ename="showAllMatClass" cname="显示所有物资分类"/>--%>
<%--                        <input type="checkbox" id="showAllMatClass"> 显示全部物资分类--%>
                </div>
                <EF:EFTree id="tree01" textField="matClassName" valueField="label"
                           hasChildren="leaf" serviceName="ACGM01" methodName="queryTree"
                           root="{label: 'root',matClassName: '根节点'}">
                </EF:EFTree>
            </EF:EFRegion>

        </div>
        <div class="col-md-9">
            <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                <div class="row">
                    <EF:EFInput ename="matClassCode" cname="物资分类编码"/>
                    <EF:EFInput ename="matClassName" cname="物资分类名称"/>
                    <EF:EFInput ename="matCode" cname="物资编码"/>
                    <EF:EFInput ename="matName" cname="物资名称"/>
                    <EF:EFSelect ename="matTypeCode" cname="物资大类">
                        <EF:EFOption label="--请选择--" value=""></EF:EFOption>
                        <EF:EFCodeOption codeName="wilp.ac.gm.type" textField="label" valueField="value"/>
                    </EF:EFSelect>
                    <EF:EFSelect ename="status" cname="物资状态">
                        <EF:EFOption label="全部" value=""></EF:EFOption>
                        <EF:EFOption label="启用" value="1"></EF:EFOption>
                        <EF:EFOption label="停用" value="0"></EF:EFOption>
                    </EF:EFSelect>
                    <EF:EFInput ename="matClassId" cname="" type="hidden"/>
                </div>
            </EF:EFRegion>
            <EF:EFRegion id="result" title="物资列表">
                <EF:EFGrid blockId="result" autoDraw="no">
                    <EF:EFColumn ename="id" cname="主键" width="100" hidden="true"/>
                    <EF:EFColumn ename="matCode" cname="物资编码" width="100"/>
                    <EF:EFColumn ename="matName" cname="物资名称" width="100"/>
                    <EF:EFComboColumn ename="unit" cname="计量单位">
                        <EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>
                    </EF:EFComboColumn>
                    <EF:EFColumn ename="matClassId" cname="物资分类ID" width="100" hidden="true"/>
                    <EF:EFColumn ename="matClassName" cname="物资分类名" width="100"/>
                    <EF:EFColumn ename="matSpec" cname="物资规格" width="100"/>
                    <EF:EFColumn ename="matModel" cname="物资型号" width="100"/>
                    <EF:EFColumn ename="price" cname="最近订购单价(元)" width="120"/>
                    <EF:EFColumn ename="supplier" cname="供应商" width="100" hidden="true"/>
<%--                    <EF:EFColumn ename="supplierText" cname="供应商" width="100"/>--%>
                    <EF:EFColumn ename="manufacturer" cname="制造商" width="100"/>
                    <EF:EFComboColumn ename="matTypeCode" cname="物资大类">
                        <EF:EFCodeOption codeName="wilp.ac.gm.type" textField="label" valueField="value"/>
                    </EF:EFComboColumn>
                    <EF:EFColumn ename="remark" cname="备注" width="100"/>
                    <EF:EFColumn ename="statusText" cname="状态" width="100"/>
                    <EF:EFColumn ename="pictureUri" cname="图片地址" width="100" hidden="true"/>
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="80%" height="30%"></EF:EFWindow>
<EF:EFWindow id="deitPopData" url=" " lazyload="true" width="80%" height="30%"></EF:EFWindow>
<EF:EFWindow id="addMatPopData" url=" " lazyload="true" width="80%" height="65%"></EF:EFWindow>
<EF:EFWindow id="editMatPopData" url=" " lazyload="true" width="80%" height="65%"></EF:EFWindow>
<!-- 文件上传窗口 -->
<EF:EFWindow id="excelChoose" url="" lazyload="true" refresh="true" width="40%" height="50%">
    <EF:EFRegion id="ReUpload" title="导入数据">
        <%-- <EF:EFUpload ename="mtrePic" docTag="mt_fs_file" path="mt/img"/> --%>
        <li>物资编码：当填写时导入所填的值，不填时按照默认规则生成物资编码，字符长度小于等于255</li>
        <li>物资名称：必填，字符长度小于等于100</li>
        <li>物资分类编号：必填，字符长度小于等于36 ，参考界面生成的分类编码</li>
        <li>物资规格：非必填， 字符长度小于等于100</li>
        <li>物资型号：非必填， 字符长度小于等于100</li>
        <li>计量单位编码：非必填， 字符长度小于等于2 参考下表（可在小代码中单独配置）</li>
        <li>最近订购单价：非必填，整数部分长度小于等于255位 小数保留三位</li>
        <li>供应商：非必填， 字符长度小于等于255，如果填写请参考 供应商档案信息管理中的供应商名称</li>
        <li>制造商：非必填， 字符长度小于等于500</li>
        <li>物资大类编码：必填，字符长度小于等于10 ，参考在<a href="EDCM01">小代码</a>中的配置）</li>
        <li>备注：非必填，字符长度小于等于500</li>
        <form id="excelForm" enctype="multipart/form-data">
            数据上传：<input id="excelFile" type="file" name="file"><br/>
        </form>
        <button id="download">模板下载</button>
    </EF:EFRegion>
    <button class="i-btn-lg" style="float: right" id="uploadButton">提交</button>
</EF:EFWindow>
<script type="text/javascript">
    var datagrid = null;

    $("#uploadButton").on("click", function () {
        $("#uploadButton").attr("disabled", "disabled");
        var fd = new FormData();
        fd.append("fileUpload", $('#excelFile')[0].files[0]);
        if ($('#excelFile')[0].files[0] == null) {
            IPLAT.alert("请选择上传文件");
            $("#uploadButton").removeAttr("disabled");
        }

        $.ajax({
            url: IPLATUI.CONTEXT_PATH + "/excelACGM",
            type: 'POST',
            cache: false,
            processData: false,
            contentType: false,
            dataType: 'json',
            data: fd,

            success: function (data) {
                uploading = false;
                if (data.msg == "all") {
                    IPLAT.alert("导入数据全部成功");
                    window.resultGrid.dataSource.page(1);
                    window['excelChooseWindow'].close();
                } else if (data.msg == "part") {
                    IPLAT.alert("导入数据存在错误");
                    window['excelChooseWindow'].close();
                    window.resultGrid.dataSource.page(1);
                    downloadFileByBase64('data:application/xls;base64,' + data.base64);
                } else if (data.msg == "error") {
                    IPLAT.alert("导入过程错误");
                    // console.log(data);
                    window['excelChooseWindow'].close();
                }
            }
        });
    });
    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result": {
            onCheckRow: function (e) {
                if (!e.fake) {
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }
            }
        }
    }

    $(function () {
        // __eiInfo
        // var tag=1;
        //显示所有物资类别
        // $("[name=showAllMatClass]").on("change", function (e) {
            // var isChecked=$("[name=showAllMatClass]").attr("checked");
            // var isChecked=$("#showAllMatClass").attr("checked");

            // var isChecked=$("#showAllMatClass").val();
            // IPLAT.alert(isChecked);
            // console.log(tag++);
            // if($("[name=showAllMatClass]").attr("checked")==true){
            //     IPLAT.alert("true");
            // }else{
            //     IPLAT.alert("false");
            // }

        //     var eiInfo = new EiInfo();
        //     eiInfo.set("tag",tag++);
        //     EiCommunicator.send("MPCG0301", "purchaseOrderEditing", eiInfo, {
        //         onSuccess: function (ei) {
        //             if(ei.getStatus() === -1){
        //                 IPLAT.alert(ei.getMsg());
        //             } else if(ei.getStatus() === -2){
        //                 IPLAT.alert(ei.getMsg());
        //             }else if(ei.getStatus() === -3) {
        //                 IPLAT.alert(ei.getMsg());
        //             } else if(ei.getStatus() === -4){
        //                 IPLAT.alert(ei.getMsg());
        //             }
        //             else{
        //                 NotificationUtil("更改成功", "success");
        //                 window.parent['popDataWindow'].close();
        //                 window.parent.resultGrid.dataSource.page(1);
        //             }
        //
        //         }
        //     })
        // });

        //查询
        $("#QUERY").on("click", function (e) {
            resultGrid.dataSource.query(1);
        });

        //重置按钮
        $("#REQUERY").on("click", function (e) {
            document.getElementById("inqu-trash").click();
            resultGrid.dataSource.query(1);
        });

        //新增分类按钮
        $("#ADDCLASS").on("click", function (e) {
            var url = IPLATUI.CONTEXT_PATH + "/web/ACGM0101?" + "initLoad&matClassId=" + $("#matClassId").val();
            var addPopData = $("#addPopData");
            addPopData.data("kendoWindow").setOptions({
                open: function () {
                    addPopData.data("kendoWindow").refresh({
                        url: url
                    });
                }
            });
            // 打开弹窗
            addPopDataWindow.open().center();
        });


        //修改分类按钮
        $("#EDITCLASS").on("click", function (e) {
            if (!$("#matClassId").val()) {
                IPLAT.alert("请先选择一条物资分类再进行修改");
            } else {
                var url = IPLATUI.CONTEXT_PATH + "/web/ACGM0102?" + "initLoad&id=" + $("#matClassId").val();
                var deitPopData = $("#deitPopData");
                deitPopData.data("kendoWindow").setOptions({
                    open: function () {
                        deitPopData.data("kendoWindow").refresh({
                            url: url
                        });
                    }
                });
                deitPopDataWindow.open().center();
            }
        });

        //删除分类按钮
        // $("#DELETECLASS").on("click", function(e) {
        //
        // 	if($("#matClassId").val() == null){
        // 		IPLAT.alert("请先选择一条物资分类再进行删除");
        // 		return;
        // 	}
        //
        // 	var info=new EiInfo();
        // 	info.set("id",$("#matClassId").val());
        //
        // 	IPLAT.confirm({
        // 		message:"确定删除选中的物资分类?",
        // 		okFn:function(e){
        // 			EiCommunicator.send("ACGM01", "delete", info, {
        // 				onSuccess : function(ei) {
        // 					IPLAT.alert(ei.getMsg());
        // 					resultGrid.dataSource.page(1);
        // 				}
        // 			});
        // 		}
        // 	});
        //
        // });

        // 停用物资分类
        $("#STOPCLASS").on("click", function (e) {
            if (!$("#matClassId").val()) {
                IPLAT.alert("请先选择一条物资分类再进行停用");
                return;
            }
            var info = new EiInfo();
            info.set("matClassId", $("#matClassId").val());
            IPLAT.confirm({
                message: "确定停用选中的物资分类?",
                okFn: function (e) {
                    EiCommunicator.send("ACGM01", "stopMatClass", info, {
                        onSuccess: function (ei) {
                            IPLAT.alert(ei.getMsg());

                        }
                    });
                }
            });
        });

        //新增物资按钮
        $("#ADD").on("click", function (e) {
            var url = IPLATUI.CONTEXT_PATH + "/web/ACGM0103?initLoad&matClassId=" + $("#matClassId").val();
            var addMatPopData = $("#addMatPopData");
            addMatPopData.data("kendoWindow").setOptions({
                open: function () {
                    addMatPopData.data("kendoWindow").refresh({
                        url: url
                    });
                }
            });
            // 打开弹窗
            addMatPopDataWindow.open().center();
        });


        //修改物资按钮
        $("#EDIT").on("click", function (e) {

            var checkRows = resultGrid.getCheckedRows();

            if (checkRows.length < 1) {
                IPLAT.alert("请先选择一条物资再进行修改");
            } else {
                var url = IPLATUI.CONTEXT_PATH + "/web/ACGM0104?" + "initLoad&id=" + checkRows[0].id;
                var editMatPopData = $("#editMatPopData");
                editMatPopData.data("kendoWindow").setOptions({
                    open: function () {
                        editMatPopData.data("kendoWindow").refresh({
                            url: url
                        });
                    }
                });
                editMatPopDataWindow.open().center();
            }
        });

        //删除物资按钮
        // $("#DELETE").on("click", function(e) {
        // 	var checkRows = resultGrid.getCheckedRows();
        // 	if(checkRows.length<1){
        // 		IPLAT.alert("请先选择一条物资再进行删除");
        // 		return;
        // 	}
        // 	var arr=[];
        // 	for(var i=0;i<checkRows.length;i++){
        // 		arr[i]=checkRows[i].id;
        // 	}
        // 	var info=new EiInfo();
        // 	info.set("list",arr);
        //
        // 	IPLAT.confirm({
        // 		message:"确定删除选中的物资?",
        // 		okFn:function(e){
        // 			EiCommunicator.send("ACGM01", "deleteMat", info, {
        // 				onSuccess : function(ei) {
        // 					IPLAT.alert(ei.getMsg());
        // 					resultGrid.dataSource.page(1);
        // 				}
        // 			});
        // 		}
        // 	});
        // });

        // 启用物资
        $("#STARTMAT").on("click", function (e) {
            var checkRows = resultGrid.getCheckedRows();
            if (checkRows.length < 1) {
                IPLAT.alert("请先选择物资再进行启用");
                return;
            }
            var arr = [];
            for (var i = 0; i < checkRows.length; i++) {
                arr[i] = checkRows[i].id;
            }
            var info = new EiInfo();
            info.set("list", arr);
            info.set("status", "1");
            IPLAT.confirm({
                message: "确定启用选中的物资?",
                okFn: function (e) {
                    EiCommunicator.send("ACGM01", "updateMatStatus", info, {
                        onSuccess: function (ei) {
                            IPLAT.alert(ei.getMsg());
                            resultGrid.dataSource.page(1);
                        }
                    });
                }
            });
        });
        // 停用物资
        $("#STOPMAT").on("click", function (e) {
            var checkRows = resultGrid.getCheckedRows();
            if (checkRows.length < 1) {
                IPLAT.alert("请先选择物资再进行停用");
                return;
            }
            var arr = [];
            for (var i = 0; i < checkRows.length; i++) {
                arr[i] = checkRows[i].id;
            }
            var info = new EiInfo();
            info.set("list", arr);
            info.set("status", "0");
            IPLAT.confirm({
                message: "确定停用选中的物资?",
                okFn: function (e) {
                    EiCommunicator.send("ACGM01", "updateMatStatus", info, {
                        onSuccess: function (ei) {
                            IPLAT.alert(ei.getMsg());
                            resultGrid.dataSource.page(1);
                        }
                    });
                }
            });
        });

        //EXCEL 模板下载
        $("#download").click(function () {
            window.location.href = IPLATUI.CONTEXT_PATH + "/excelACGM";
        });

        //EXCEL 上载
        $("#UPLOAD").click(function () {
            excelChooseWindow.open().center()
        });

    });

    // base64 文件下载
    // function dataURLtoBlob(dataurl) {
    //     var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
    //         bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    //     while (n--) {
    //         u8arr[n] = bstr.charCodeAt(n);
    //     }
    //     return new Blob([u8arr], {type: mime});
    // }
    //
    // function File(url, name = 'error.xls') {
    //     var a = document.createElement("a")
    //     a.setAttribute("href", url)
    //     a.setAttribute("", name)
    //     a.setAttribute("target", "_blank")
    //     let clickEvent = document.createEvent("MouseEvents");
    //     clickEvent.initEvent("click", true, true);
    //     a.dispatchEvent(clickEvent);
    // }
    //
    // function FileByBase64(base64, name) {
    //     var myBlob = dataURLtoBlob(base64)
    //     var myUrl = URL.createObjectURL(myBlob)
    //     File(myUrl, name)
    // }

    // base64 文件下载
    function dataURLtoBlob(dataurl) {
        var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], { type: mime });
    }

    function downloadFile(url,name='error.xls'){
        var a = document.createElement("a")
        a.setAttribute("href",url)
        a.setAttribute("download",name)
        a.setAttribute("target","_blank")
        let clickEvent = document.createEvent("MouseEvents");
        clickEvent.initEvent("click", true, true);
        a.dispatchEvent(clickEvent);
    }

    function downloadFileByBase64(base64,name){
        var myBlob = dataURLtoBlob(base64)
        var myUrl = URL.createObjectURL(myBlob)
        downloadFile(myUrl,name)
    }


</script>

