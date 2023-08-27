
function exportFile(){
	var eiInfo = new EiInfo();
	var selectRows = resultGrid.getCheckedRows();
	var building = IPLAT.EFSelect.value($("#inqu_status-0-building"));
	var ids = [],flag = false,qrcodeSaltStr = "";
	if(qrcodeSalt){
		qrcodeSaltStr = qrcodeSalt[0].typeCode;
	}else{
        NotificationUtil("请配置二维码前言！", "warning");
        return;
    }
	
	eiInfo.set("qrcodeSalt",qrcodeSaltStr);
	if(selectRows && selectRows.length > 0){
		//有选择表格数据导出所选内容
		for (var i = 0; i < selectRows.length; i++) {
			ids.push("'" + selectRows[i].id + "'");
		}
		eiInfo.set("ids",ids.join(","));
		
		IPLAT.confirm({
            message: '<b>将会导出所选择的' +selectRows.length+ '条二维码信息！</br><font color="red">是否确定？</font></b>',
            title: '提醒',
            okFn: function (e) {
            	doExport(eiInfo);
            },
            cancelFn: function (e) {
            	return;
            }
		});
	}else if(building){
		var building = IPLAT.EFSelect.value($("#inqu_status-0-building"));
		var floor = IPLAT.EFSelect.value($("#inqu_status-0-floor"));
		var deptCode = IPLAT.EFSelect.value($("#inqu_status-0-deptCode"));
		var buildingName = IPLAT.EFSelect.text($("#inqu_status-0-building"));
		var floorName = IPLAT.EFSelect.text($("#inqu_status-0-building"));
		var deptName = IPLAT.EFSelect.text($("#inqu_status-0-building"));
		buildingName = buildingName == "请选择" ? "" : buildingName;
		floorName = floorName == "请选择" ? "" : floorName;
		deptName = deptName == "请选择" ? "" : deptName;
		
		var str = buildingName + floorName + deptName;
		
		eiInfo.set("building",building);
		eiInfo.set("floor",floor);
		eiInfo.set("dept",dept);
		IPLAT.confirm({
			message: '<b>将会导出' +str+ '的二维码信息！</br><font color="red">是否确定？</font></b>',
			title: '提醒',
			okFn: function (e) {
				doExport(eiInfo);
			},
			cancelFn: function (e) {
				return;
			}
		});
	}else{
		IPLAT.confirm({
			message: '<b>将会导出所有的二维码信息！</br><font color="red">是否确定？</font></b>',
			title: '提醒',
			okFn: function (e) {
				doExport(eiInfo);
			},
			cancelFn: function (e) {
				return;
			}
		});
	}
}

function doExport(eiInfo){
	//获取文件路径
	EiCommunicator.send("SSBMCTM01", "exportQr", eiInfo, {
		onSuccess : function(ei) {
			var rows = ei.blocks['dept'].getMappedRows();
			console.log(rows);
			//下载文件
			//window.open(IPLATUI.CONTEXT_PATH + "/" + rows[0].url);
			const a = document.createElement('a'); // 创建a标签
			a.setAttribute('download', '');// download属性
			a.setAttribute('href', IPLATUI.CONTEXT_PATH + "/" + rows[0].url);// href链接 
			a.click();// 自执行点击事件
		}
	});
}

//测试webSocket
var websocket ;
function WebSocketTest(){
	/*
    {
        name: "test",
        text: "测试",
        layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
        icon: "css:fa-qrcode",
        attributes: {
            style: "float:left;height:30px;"
        },
        click: WebSocketTest
    },
    {
        name: "send",
        text: "send",
        layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
        icon: "css:fa-qrcode",
        attributes: {
            style: "float:left;height:30px;"
        },
        click: send
    },
    {
        name: "closeWebSocket",
        text: "closeWebSocket",
        layout: "3",//layout 取 2 时，只显示图标，取 3 时，显示图标和文字
        icon: "css:fa-qrcode",
        attributes: {
            style: "float:left;height:30px;"
        },
        click: closeWebSocket
    },*/

	
   if ("WebSocket" in window) {
      
      // 打开一个 web socket
      websocket = new WebSocket("ws://localhost:8083/WILP/test");
       
    //连接发生错误的回调方法
      websocket.onerror = function() {
          setMessageInnerHTML("error");
      };

      //连接成功建立的回调方法
      websocket.onopen = function(event) {
          setMessageInnerHTML("open");
      }

      //接收到消息的回调方法
      websocket.onmessage = function(event) {
          setMessageInnerHTML(event.data);
      }

      //连接关闭的回调方法
      websocket.onclose = function() {
          setMessageInnerHTML("close");
      }

      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function() {
          websocket.close();
      }
   } 
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
	console.log(innerHTML);
}

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
	var message = '111';
    websocket.send(message);
}

