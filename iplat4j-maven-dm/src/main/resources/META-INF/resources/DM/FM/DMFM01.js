function popData(id, type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/DMFM0101?initLoad&id=" + id
        + "&type=" + type;
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

var datagrid = null;

//EFGrid单击事件，获取选中行数据（定义全部变量）
IPLATUI.EFGrid = {
    "result" : {
        toolbarConfig : {
            buttons : [ {
                name : "exportTemplate",
                text : "宿舍费用导入模板",
                layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
                icon : "css:fa-file-archive-o",
                attributes : {
                    //style : "float:left;height:30px;"
                },
                click : downloadFile
            }, {
                name : "importType",
                text : "宿舍费用导入",
                layout : "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
                icon : "css:fa-file-archive-o",
                attributes : {
                    //style : "float:left;height:30px;"
                },
                click : uploadFile
            } ]

        },

        onCheckRow : function(e) {
            if (!e.fake) {
                datagrid = null;
                var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                datagrid = model;
            }
        },
        loadComplete : function(grid) {
            //查询
            $("#QUERY").on("click", function(e) {
                resultGrid.dataSource.page(1);
            });

            $("#RESET").on("click", function (e) {
                document.getElementById("inqu-trash").click();
                resultGrid.dataSource.page(1);
            });

            //新增按钮
            $("#ADD").on("click", function(e) {
                popData("", "add");
                resultGrid.dataSource.page(1);
            });

            //编辑按钮
            $("#EDIT").on("click", function(e) {
                if (datagrid == null) {
                    IPLAT.alert("请先选择一条记录进行操作");
                } else {
                    console.log(datagrid.id)
                    popData(datagrid.id, "edit");
                    datagrid = null;
                }
                resultGrid.dataSource.page(1);
            });

            $("#FEEIMPORT").on("click", function(e) {
                var node = $('#DMFM01');
                IPLAT.submitNode(node, 'DMFM01', 'queryintelligentElecFee', {
                    onSuccess : function(eiInfo) {
                        NotificationUtil("新增成功", "");


                        //关闭弹窗
                        window.parent['popDataWindow'].close();

                    },
                    onFail : function(errorMsg, status, e) {
                        NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
                    }
                });
            });


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
                //获取文件路径
                var eiInfo = new EiInfo();
                eiInfo.set("docId",e.response.docId);
                eiInfo.set("docTag",e.response.docTag);
                eiInfo.set("docUrl",e.response.docUrl);
                eiInfo.set("fileType",e.files[0]["extension"]);
                EiCommunicator.send("DMFM01", "uploadFile", eiInfo, {
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

function downloadFile() {
    //获取文件路径
    var url = IPLATUI.CONTEXT_PATH +  "/" + "DM/template/dorms.xls";
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

$(function() {
    $(".upload-file3").css("display","none");
});