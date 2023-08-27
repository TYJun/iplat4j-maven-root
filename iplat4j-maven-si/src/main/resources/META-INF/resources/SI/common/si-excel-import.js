let excelImportConfig = {
    uploading: false,
    element: 'excelFile',
}

/**
 * 设置上传组件ID
 * @param element : string
 */
function setElement(element) {
    if(element) {
        excelImportConfig.element = element;
    }
}

/**
 * 导入Excel
 * @param url : string   : excel导入请求路径
 * @param errorFileName : string  : 错误文件名称
 * @param element : string  : 上传组件ID
 */
function siExcelImport(url, errorFileName, element) {
    setElement(element);

    //数据校验
    let file = $('#'+excelImportConfig.element)[0].files[0];
    if (!file){
        NotificationUtil("请上传文件","error");
        return;
    }
    if(excelImportConfig.uploading){
        NotificationUtil("数据正在提交中，请不要重复点击提交","warning");
        return;
    }
    excelImportConfig.uploading = true;
    //数据提交
    let form =  new FormData();
    form.append("excelFile", file);
    $.ajax({
        url: url,
        type: 'POST',
        data:form,
        cache: false,
        processData: false,
        contentType: false,
        dataType : 'json',
        success : function(data) {
            excelImportConfig.uploading = false;
            if(data.msg == "all"){
                NotificationUtil("数据导入成功","success");
                dealResult(data.id);
            }else if (data.msg == "part"){
                NotificationUtil("导入数据存在问题，请查看返回文件","warning");
                downloadFileByBase64('data:application/xls;base64,'+data.base64, errorFileName);
                dealResult(data.id);
            }else if (data.msg == "error"){
                NotificationUtil("数据导入失败","error");
                window['excelImportWindow'].close();
            }
        }
    });
}

/**
 * 将文件base64转成文件
 * @param base64
 * @param name
 */
function downloadFileByBase64(base64,name){
    let myBlob = dataURLtoBlob(base64)
    let myUrl = URL.createObjectURL(myBlob)
    let failFile = document.createElement("a")//创建a标签
    failFile.setAttribute("href",myUrl)
    failFile.setAttribute("download",name)
    failFile.setAttribute("target","_blank")
    let clickEvent = document.createEvent("MouseEvents");
    clickEvent.initEvent("click", true, true);
    failFile.dispatchEvent(clickEvent);
}

/**
 * 将base64转成数据流
 * @param dataUrl
 * @returns {Blob}
 */
function dataURLtoBlob(dataUrl) {
    let arr = dataUrl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        str = atob(arr[1]), n = str.length, u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = str.charCodeAt(n);
    }
    return new Blob([u8arr], { type: mime });
}

function dealResult (id) {
    if(id) { $("#id").val(id); }
    window['excelImportWindow'].close();
    setTimeout(function() {
        window.parent.location.reload()
    }, 600);
}