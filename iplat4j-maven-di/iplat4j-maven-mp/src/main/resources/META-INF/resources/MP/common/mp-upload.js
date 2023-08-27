class WilpUpload {
    constructor(config) {
        /**上传组件ID**/
        this.id = config.id ?? "file";
        /**附件表格的resultId*/
        this.gridId = config.gridId ?? "file";
        /**上传弹出窗ID**/
        this.uploadWindow = config.uploadWindow ?? "fileChoose";
        /**附件类型. file=常用格式文件、img=图片 **/
        this.fileType = config.fileType ?? "file";
        /**允许上传的文件的格式后缀**/
        this.mimeType = config.mimeType ?? this.fileType === "file" ? [".jpg", "jpeg", ".png", ".gif", ".bmp",".webp",
            ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".zip", "rar", ".7z"]
            : [".jpg", "jpeg", ".png", ".gif", ".bmp", ".webp"];
        /**违反上传校验规则错误提示*/
        this.notAllowMsg = config.notAllowMsg ?? "文件格式不支持, 上传失败";
        /**表格附加字段对象**/
        this.addGridRow = config.addGridRow ?? undefined;
        /**附件表格字段数组**/
        this.fieldList = ["docId", "fileName", "fileSize", "recCreateTime"];
        let gridFieldArray = this.addGridRow ? Object.keys(this.addGridRow) : [];
        this.fieldList.push.apply( this.fieldList, gridFieldArray);
    }

    /**
     * 构建表格附件上传
     * @returns {{}}
     */
    buildGridUpload() {
        let upload = {}, fileUpload = {}, _this = this;
        /**加载完成事件**/
        fileUpload['loadComplete'] = function (e) {
            let uploader = e.sender.uploader;
            uploader.clearAllFiles();
        }
        /**上传校验**/
        fileUpload['validation'] = {
            //格式校验
            allowedExtensions: _this.mimeType,
            //大小校验(单位:字节(B): 1KB = 1024B)
            //maxFileSize: 1024 * 1024 * 1024,
            //minFileSize: 300
        }
        /** 不支持格式提示信息**/
        fileUpload['localization'] = {
            invalidFileExtension: _this.notAllowMsg
        }
        /**上传成功回调**/
        fileUpload['success'] = function (e) {
            let row = createModel(_this.fieldList, e, _this.addGridRow);
            window[_this.gridId+"Grid"].addRows(row);
            window[_this.uploadWindow+"Window"].close();
        }
        /**返回**/
        upload[this.id] = fileUpload;
        return upload;
    }
}

/**
 * 创建并初始化Model
 * @param fieldList
 * @param e
 * @param addGridRow addGridRow
 * @returns {*}
 */
function createModel(fieldList, e, addGridRow) {
    let fields = {};
    for(let field of fieldList) {
        fields[field] = {type: "string"}
    }
    //定义model
    let GridRow = kendo.data.Model.define({
        id: "fileId",
        fields: fields
    });
    //初始化model
    return new GridRow(Object.assign({
        docId: e.response.docId,
        fileName: e.files[0].name,
        fileSize: e.files[0].size + "",
        recCreateTime: formatTime(e.response.uploadTime)
    }, addGridRow));
}

/**
 * 格式化时间
 * @param uploadTime
 * @returns {*}
 */
function formatTime(uploadTime) {
    return  uploadTime.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/g, '$1-$2-$3 $4:$5:$6');
}

/**
 * 下载文件
 * @param docId
 */
function downLoadFile(docId) {
    window.location.href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
}

/**
 * 显示图片
 * @param docId
 */
function viewImg(docId) {
    return IPLATUI.CONTEXT_PATH + "/ED/PC/EDPC05.jsp?docId=" + docId;
}