    var serverUrl = "http://appres.yyhq365.cn/";
    function onFileSystemSuccess(){
        var fileURL = cordova.file.externalDataDirectory;
        localStorage.setItem("fileURL", fileURL);
        var appURL = cordova.file.applicationDirectory;
        localStorage.setItem("appURL", appURL);

        $.ajax({
          url:serverUrl+'appPlatformControllder.do?queryAppVersion',
          type:'post',
          data:{
            appid:'com.bonawise.hospital',
            hospital:hosId,
            deviceType:'android'
          },
          success:function(data){
            console.log(data);
            var version_num = data.obj.version;
            var downloadUrl = serverUrl+data.obj.path;
            var fileTarget = fileURL+'html.zip';
            console.log(version_num);
            // if(version_num==undefined){
            //   localStorage.setItem("new_frame","false");
            //   return;
            // }
            if(version_num!=undefined&&version_num!=localStorage.getItem("version_num")){
              prepareDownload(version_num,downloadUrl,fileTarget);
            }
          }
        });
    }

    function onFileSystemFail(){
        $("#warn small").html("获取本地文件系统失败，请查看权限设置");
        $("#warn").fadeIn(500);
        setTimeout('$("#warn").fadeOut(500)',3000);
    }

    /**
     * 问题修复，不比较版本，直接下载最新资源包
     * @return {[type]} [description]
     */
    function solve_problem(){
      $.ajax({
          url:serverUrl+'appPlatformControllder.do?queryAppVersion',
          type:'post',
          data:{
            appid:'com.bonawise.hospital',
            hospital:hosId
          },
          success:function(data){
            var version_num = data.obj.version;
            var downloadUrl = serverUrl+data.obj.path;
            var fileTarget = localStorage.getItem("fileURL")+'html.zip';
            prepareDownload(version_num,downloadUrl,fileTarget);
          }
        });
    }

    var progress = mui('#progress');
    function prepareDownload(version_num,downloadUrl,fileTarget){
      mui(progress).progressbar().setProgress(0);
      $("#updated").show();
      $("#progress_desc").html("正在下载资源文件，请确保网络通畅");
      setTimeout("download('"+version_num+"','"+downloadUrl+"','"+fileTarget+"')",1000);
    }

    function download(version_num,downloadUrl,fileTarget) {
      var fileTransfer = new FileTransfer();
      var uri = encodeURI(downloadUrl);
      fileTransfer.download(
        uri,
        fileTarget,
        function(entry) {
          // alert("download complete: " + entry.toURL());
          mui(progress).progressbar().setProgress(0);
          $("#progress_desc").html("下载完成，正在解析资源包");
          $("#file_weight").html("");
          setTimeout("removefiles('"+version_num+"','"+fileTarget+"')",500);
        },
        function(error) {
          $("#reload").show();
          if(error.code=="1"){
            $("#progress_desc").html("下载失败，未能找到资源文件");
          }else if(error.code=="2"){
            $("#progress_desc").html("下载失败，无效的下载地址");
          }else if(error.code=="3"){
            $("#progress_desc").html("下载失败，连接异常");
          }else if(error.code=="4"){
            $("#progress_desc").html("下载失败，连接中断");
          }else if(error.code=="5"){
            $("#progress_desc").html("下载失败，资源文件无变动");
          }
          // alert("download error source " + error.source);
          // alert("download error target " + error.target);
          // alert("download error code" + error.code);
        },
        false, {
          headers: {
            "Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="
          }
        }
      );
      fileTransfer.onprogress = function(progressEvent) {
        if (progressEvent.lengthComputable) {
          var total_weight = Math.round(progressEvent.total/1000)+"KB";
          var load_weight = Math.round(progressEvent.loaded/1000)+"KB";
          $("#file_weight").html(load_weight +"\/"+ total_weight);
          var progress_num = Math.round((progressEvent.loaded / progressEvent.total) * 100);
          mui(progress).progressbar().setProgress(progress_num);
          // console.log(progressEvent.loaded / progressEvent.total);
        } else {
          loadingStatus.increment();
        }
      };
    }

    function removefiles(version_num, fileTarget) {
      window.resolveLocalFileSystemURL(cordova.file.externalDataDirectory, function(root) {
        root.getDirectory('html', {
          create: false
        }, function(fentry) {
          fentry.removeRecursively(function() {
            setTimeout("unzip('"+version_num+"','"+fileTarget+"')",1000);
          }, function(err) {
            setTimeout("unzip('"+version_num+"','"+fileTarget+"')",1000);
          });
        }, function(err) {
          setTimeout("unzip('"+version_num+"','"+fileTarget+"')",1000);
        });
      }, function(err) {
        setTimeout("unzip('"+version_num+"','"+fileTarget+"')",1000);
      });
    }

    function unzip(version_num,fileTarget) {
        zip.unzip(fileTarget, 
            localStorage.getItem("fileURL"), 
            function(data) {
              if(data=="0"){
                mui(progress).progressbar().setProgress(100);
                $("#progress_desc").html("解析完成");
                setTimeout('$("#updated").hide()',1000);
                localStorage.setItem("version_num",version_num);
                localStorage.setItem("new_frame","true");
              }else{
                $("#progress_desc").html("解析资源包时发生错误，请重新操作");
              }
            }, progressCallback);
    }

    var progressCallback = function(progressEvent) {
        // $("#file_weight").html(progressEvent.loaded +"\/"+ progressEvent.total);
        var progress_num = Math.round((progressEvent.loaded / progressEvent.total) * 100);
        mui(progress).progressbar().setProgress(progress_num);
        console.log("@@@@@@@@@@@"+progress_num);
        //$("#progressbar").html(Math.round((progressEvent.loaded / progressEvent.total) * 100));
    };

    mui("#updated").on('tap', 'button.mui-btn-outlined', function() {
      location.reload();
    })