$(function() {
	let taskNo = IPLAT.EFInput.value($("#taskNo"));
	/**
	 * 加载图片
	 */
	loadImage(taskNo);
	// 回显赋值
	console.log(__ei);
	IPLAT.EFInput.value($("#taskId"), __ei.taskId); // 工单ID
	IPLAT.EFInput.value($("#taskNo"), __ei.taskNo); // 工单号
	IPLAT.EFInput.value($("#guaranteeNum"), __ei.reqStaffId); // 来电人工号
	IPLAT.EFInput.value($("#guaranteeName"), __ei.reqStaffName); // 来电人名称
	IPLAT.EFInput.value($("#reqTelNum"), __ei.reqTelNum); // 来电号码
	IPLAT.EFInput.value($("#reqDeptNum"), __ei.reqDeptNum); // 来电科室编码
	IPLAT.EFInput.value($("#reqDeptName"), __ei.reqDeptName); // 来电科室名称
	IPLAT.EFInput.value($("#building"), __ei.building); // 楼
	IPLAT.EFInput.value($("#floor"), __ei.floor); // 层
	IPLAT.EFInput.value($("#reqSpotNum"), __ei.reqSpotNum); //地点编码
	IPLAT.EFInput.value($("#reqSpotName"), __ei.reqSpotName); // 地点名称
	IPLAT.EFInput.value($("#comments"), __ei.comments); // 备注

	// 保洁确认提交按钮
	$("#SUBMIT").unbind('click').on('click', function() {
		// 防止连续提交
		$("#SUBMIT").attr("disabled", true);
		setTimeout(function() {
			$("#SUBMIT").attr("disabled", false);
		}, 5000);
		var eiInfo = new EiInfo();
		var changeStatusCode = '02'; /* 进入待完工状态 */
		var taskId = $("#taskId").val(); /* 工单id */
		var dataItems = resultItemGrid.getDataItems(); /* 保洁事项含执行人 */
		console.log(dataItems);

		eiInfo.set("taskId", taskId);
		eiInfo.set("statusCode", changeStatusCode);
		eiInfo.set("dataItems", dataItems);
		// 执行后台CSQR0101.comfirmMethod接口方法.
		EiCommunicator.send("CSQR0101", "comfirmMethod", eiInfo, {
			onSuccess : function(ei) {
				var popData = $("#popData", parent.document);
				popData.kendoWindow().data("kendoWindow").close();
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 600);
			}
		});
	});
});

IPLATUI.EFGrid = {
	"resultItem" : {
		/*
		 * columns :[{ field: "isEffective", template: "当前：#=isEffective#" }],
		 */
		columns : [ {
			// 当保洁确认子页面中对保洁事项的执行人列进行点击时触发的事件.
			field : "executeUserName", // popupColumn, 暂不支持 DynamicGrid
			enable : true,
			readonly : true,
			hidden : false,
			locked : false,
			editor : function(container, param) {
				// 设置产生弹框 model
				if (container.hasClass("fake-edit")) {
					container.removeClass("fake-edit");
				} else {
					editorModel = param.model;
					// 给班组编码传值.
					IPLAT.EFInput.value($("#wgroupNum"),
							editorModel.serviceDeptNum);
					// 打开弹窗时自动执行一次点击时间.
					$("#serachData").click();
					IPLAT.Popup.popupContainer({
						// 弹出ID为ef_popup_peoplegrid的弹窗.
						containerId : "ef_popup_peoplegrid",
						textElement : $(container),
						width : 600,
						height : 600,
						center : true,
						title : "选择执行人"
					});
				}
			}
		} ],

		loadComplete : function(e) {
			// 执行人查询按钮.
			$("#serachData").on("click", function() {
				personGrid.dataSource.page(1);
			});
			// 关闭 iframe 时候回填数据
			// 选择执行人弹窗的“确认”按钮被点击时出发的事件.
			$("#ef_popup_peoplegrid_button").on(
					"click",
					function(e) {
						// 获取personGrid所有选中的行数据.
						var checkRows = personGrid.getCheckedRows();
						var windowFrame = $("#ef_popup_peoplegrid").data(
								"kendoWindow");
						var workNo = "";
						var workName = "";
						console.log(checkRows);
						// 判断是否有选中行.
						if (checkRows && checkRows.length > 0) {
							// 循环遍历一下选中的行，有可能有多行，并对它们的工号和名称进行一个以“，”拼接.
							for (var i = 0; i < checkRows.length; i++) {
								// 当遍历最后一个值时，省略最后的"，"
								if (i == checkRows.length - 1) {
									workNo += checkRows[i]["workNo"];
									workName += checkRows[i]["name"];
								} else {
									workNo += checkRows[i]["workNo"] + ',';
									workName += checkRows[i]["name"] + ',';
								}
							}
							windowFrame.close();
							// 将获取对的值设置显示到resultItemGrid窗口的对应字段上.
							resultItemGrid.setCellValue(editorModel,
									"executeUserNo", workNo);
							resultItemGrid.setCellValue(editorModel,
									"executeUserName", workName);
						} else {
							NotificationUtil("无选择执行人！", "warning");
							windowFrame.close();
						}
					});
		}
	}
}

/**
 * 加载图片（报修图片、完工图片）
 * @param taskNo
 */
function loadImage(taskNo,type){
	var inInfo = new EiInfo();
	inInfo.set("taskNo", taskNo);
	inInfo.set("type", "RE");
	EiCommunicator.send("CSRE01", "showPic", inInfo, {

		onSuccess : function(ei) {
			var list = ei.get("list");
			console.log(list);
			$("#reqPic").html("");
			for (let i = 0; i < list.length; i++) {
				drawImage(list[i].base64, 1);
			}
		}
	});
}

/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(imageBase64, index) {
	if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
		return;
	}
	var img = $('<img style="width:100px;height:100px" class="picMaintain">');
	img.attr('src', "data:image/jpeg;base64," + imageBase64);
	// 报修
	if (index == 1) {
		var pic = $("#reqPic");
		pic.append(img);
	}
	// 完工
	if (index == 2) {
		var pic = $("#fsPic");
		pic.append(img);
	}
	// 签字
	if (index == 3) {
		var pic = $("#qmPic");
		pic.append(img);
	}
	// 退单
	if (index == 4) {
		var pic = $("#picRefundLab");
		pic.append(img);
	}
	$(".picMaintain").click(function() {
		var _this = $(this);// 将当前的pimg元素作为_this传入函数
		imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
	});
}
/**
 * 查看大图
 * @param outerdiv
 * @param innerdiv
 * @param bigimg
 * @param _this
 */
function imgShow(outerdiv, innerdiv, bigimg, _this){
	var src = _this.attr("src");// 获取当前点击的pimg元素中的src属性
	$(bigimg).attr("src", src);//设置#bigimg元素的src属性

	/*获取当前点击图片的真实大小，并显示弹出层及大图*/
	$("<img/>").attr("src", src).load(function(){
		var windowW = $(window).width();//获取当前窗口宽度
		var windowH = $(window).height();//获取当前窗口高度
		var realWidth = this.width;//获取图片真实宽度
		var realHeight = this.height;//获取图片真实高度
		var imgWidth, imgHeight;
		var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

		if(realHeight>windowH*scale) {//判断图片高度
			imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
			imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
			if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
				imgWidth = windowW*scale;//再对宽度进行缩放
			}
		} else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
			imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
			imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
		} else {//如果图片真实高度和宽度都符合要求，高宽不变
			imgWidth = realWidth;
			imgHeight = realHeight;
		}
		$(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

		var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
		var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
		$(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
		$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
	});

	$(outerdiv).click(function(){//再次点击淡出消失弹出层
		$(this).fadeOut("fast");
	});
}
