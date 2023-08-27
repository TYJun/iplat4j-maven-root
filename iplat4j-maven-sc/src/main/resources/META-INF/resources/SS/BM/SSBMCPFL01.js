IPLATUI.EFUpload = {
    "file3":{
        loadComplete: function(e) {
            var uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".xls"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function(e) {
            debugger;
            console.log(e);
        }
    }
}