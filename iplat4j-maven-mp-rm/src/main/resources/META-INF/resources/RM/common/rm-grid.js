let dataUidList;
function WilpGrid(config) {
    /**Grid的resultId,默认为result**/
    this.resultId = isNotExist(config.resultId, "result");
    /**是否有操作类型参数,一般拼接到url后面(如: xx?xx=xx&type=add)**/
    this.isOpType = isNotExist(config.isOpType, true);
    /**是否详情功能,要求Grid的某列有displayType="url"**/
    this.detail = isNotExist(config.detail, true);
    /**Grid详情页面地址**/
    this.detailUrl = isNotExist(config.detailUrl, __ei.serviceName+"01?inqu_status-0-id=#:id#");
    this.detailWindow = isNotExist(config.detailWindow, "popData");
    /**是否有新增按钮(button的id为ADD)**/
    this.add = isNotExist(config.add, true);
    /**是否复制新增**/
    this.addCopy = isNotExist(config.addCopy, false);
    /**新增页面地址**/
    this.addUrl = isNotExist(config.addUrl, __ei.serviceName+"01?inqu_status-0-id=#:id#");
    /**是否有编辑按钮(button的id为EDIT)**/
    this.edit = isNotExist(config.edit, true);
    /**编辑页面地址**/
    this.editUrl = isNotExist(config.editUrl, __ei.serviceName+"01?inqu_status-0-id=#:id#");
    /**编辑配置**/
    this.editConfig = isNotExist(config.editConfig, {status: ["01","20"]});
    /**是否有删除按钮(button的id为DEL)**/
    this.del = isNotExist(config.del, true);
    /**
     * 删除配置：
     *    status: 数据可删除状态
     *    serviceName: 服务名
     *    methodName: 方法名
     **/
    this.delConfig = isNotExist(config.delConfig, {status: ["01"], serviceName: __ei.serviceName, methodName: "delete"});
    /**是否有提交按钮(button的id为SUBMIT)**/
    this.submit = isNotExist(config.submit, false);
    /**
     * 提交配置
     *    status: 数据可删除状态
     *    serviceName: 服务名
     *    methodName: 方法名
     **/
    this.submitConfig = isNotExist(config.submitConfig, { status: ["01"], backStatus: ["20"], serviceName: __ei.serviceName, methodName: "submit" });
    /**是否有撤回按钮(button的id为BACK)**/
    this.back = isNotExist(config.back, false);
    /**
     * 撤回配置
     *    status: 数据可删除状态
     *    serviceName: 服务名
     *    methodName: 方法名
     **/
    this.backConfig = isNotExist(config.backConfig, {status: ["10"], serviceName: __ei.serviceName, methodName: "withdraw"});
    /**是否展示Grid的分页**/
    this.showPage = isNotExist(config.showPage, true);
    /**Grid的自定义按钮配置**/
    this.toolbar = config.toolbar;
    /**
     * Grid的扩展按钮配置
     *      buttonId: 按钮ID
     *      call: 按钮调用的方法
     **/
    this.extentMethod = config.extentMethod;
    /**是否有行点击事件**/
    this.hasRowClick = isNotExist(config.hasRowClick, false);
    /**行点击事件配置
     *      gridId : 子表格ID
     *      paramName : 参数名称
     *      colName : 列表字段名称
     **/
    this.rowClickConfig = isNotExist(config.rowClickConfig, {});
    /**是否显示图片**/
    this.showImg = isNotExist(config.showImg, false);
    /**是否有单元格编辑事件**/
    this.hasAfterEditor = isNotExist(config.hasAfterEditor, false);
    /**
     * 单元格编辑后事件配置
     *      isShow: 是否存在
     *      call: 方法
     */
    this.afterEditConfig = isNotExist(config.afterEditConfig, {isShow: false})



    /**
     * 构建单元格点击事件
     * @param resultGrid : object : grid对象
     */
    this.buildCellClick = function(resultGrid) {
        let _this = this;
        if(this.detail || this.showImg) {
            resultGrid['onCellClick'] = function (e) {
                e.preventDefault();
                //查看详情
                if (_this.detail && e.td.context.className =="cell-url") {
                    let dataUid = e.model.uid;
                    dataUidList = $('[' + "data-uid=" + dataUid + ']' )
                    popData(_this.buildUrl(_this.detailUrl, e.model, _this.isOpType, "see"), _this.detailWindow);
                    //window[_this.resultId+"Grid"].unCheckAllRows();
                }
                //查看图片大图
                if(_this.showImg && e.field === "pictureUri") {
                    let img = e.model['pictureUri'];
                    WindowUtil({
                        windowId: "showMatImg",
                        title: "图片",
                      /*  height:"200px",
                        width: "500px",*/
                        draggable: true,
                        content: `<span style='padding:3px;display:inline-block;border:1px solid black;'>
                                       <img src='${IPLATUI.CONTEXT_PATH}${img}' ondblclick='closeWindowUtil()'>
                                 </span>`
                    });
                    window[_this.resultId+"Grid"].unCheckAllRows();
                }
            };
        }
    }

    /**
     * 单元格编辑后事件
     * @param resultGrid : object : grid对象
     */
    this.buildAfterEditor = function(resultGrid) {
        let _this = this;
        if(this.afterEditConfig.isShow) {
            resultGrid['afterEdit'] = function (e) {
                //e.preventDefault();
                _this.afterEditConfig.call(e);
            }
        }
    }

    /**
     * 构建行点击事件
     * @param resultGrid : object : grid对象
     */
    this.buildRowClick = function (resultGrid) {
        let _this = this;
        if(this.hasRowClick) {
            resultGrid['onRowClick'] = function (e) {
                e.preventDefault();
                $("#inqu_status-0-"+_this.rowClickConfig.paramName).val( e.model[_this.rowClickConfig.colName])
                window[_this.rowClickConfig.gridId+"Grid"].dataSource.page(1);
            }
        }
    }

    /**
     * 构建图片单元格
     * @param resultGrid : object : grid对象
     */
    this.buildImgCell = function(resultGrid) {
        if(this.showImg) {
            resultGrid['columns'] = [{
                field: "pictureUri",
                title: "图片",
                template: "<span ><img src='"+IPLATUI.CONTEXT_PATH+"#:pictureUri#' height='30' ></span>",
                //width:150
            }]
        }
    }

    /**
     * 构建Grid对象
     */
    this.buildGrid = function() {
        let grid = {},resultGrid = {}, _this = this;
        //单元格点击事件
        this.buildCellClick(resultGrid);
        //行点击事件
        this.buildRowClick(resultGrid)
        //显示图片
        this.buildImgCell(resultGrid);
        //功能事件
        resultGrid['loadComplete'] = function (e) {
            /**新增**/
            if(_this.add) {
                $("#ADD").on("click", function (e) {
                    let data = {};
                    if(_this.addCopy) {
                        let checkRows = window[_this.resultId+"Grid"].getCheckedRows();
                        data = checkRows.length > 0 ? checkRows[0] : {};
                    }
                    popData(_this.buildUrl(_this.addUrl, data, _this.isOpType, "add"));
                });
            }

            /**编辑**/
            if(_this.edit) {
                $("#EDIT").on("click", function (e) {
                    let checkRows = window[_this.resultId+"Grid"].getCheckedRows()
                    if (checkRows.length < 1) {
                        NotificationUtil("请选择需要编辑的记录", "error");
                    } else if (checkRows[0].statusCode && !_this.editConfig.status.includes(checkRows[0].statusCode)) { //存在状态,但状态不满足
                        NotificationUtil("已提交的记录无法编辑", "error");
                    } else {
                        popData(_this.buildUrl(_this.editUrl, checkRows[0], _this.isOpType, "edit"));
                    }
                });
            }

            /**删除**/
            if(_this.del) {
                $("#DEL").on("click", function (e) {
                    let checkRows = window[_this.resultId+"Grid"].getCheckedRows();
                    if (checkRows.length < 1) {
                        NotificationUtil("请先选择需要删除的记录", "error");
                    } else if (checkRows[0].statusCode && !_this.delConfig.status.includes(checkRows[0].statusCode)) { //存在状态,但状态不满足
                        NotificationUtil("该记录无法删除", "error");
                    } else {
                        IPLAT.confirm({
                            message: '<b>您确定要删除吗？</b>',
                            okFn: function (e) {
                                let eiInfo = new EiInfo();
                                eiInfo.set("id", checkRows[0].id);
                                EiCommunicator.send(_this.delConfig.serviceName, _this.delConfig.methodName, eiInfo, {
                                    onSuccess: function (ei) {
                                        if (ei.getStatus() == -1) {
                                            NotificationUtil(ei.getMsg(), "error");
                                            return;
                                        }
                                        NotificationUtil("删除成功");
                                        window[_this.resultId+"Grid"].dataSource.page(1);
                                    }
                                });
                            },
                            cancelFn: function (e) {
                            }
                        })
                    }
                });
            }

            /**提交**/
            if(_this.submit) {
                $("#SUBMIT").on("click", function (e) {
                    let checkRows = window[_this.resultId+"Grid"].getCheckedRows()
                    if (checkRows.length < 1) {
                        NotificationUtil("请先选择需要提交的记录", "error");
                    } else if (checkRows[0].statusCode && _this.submitConfig.backStatus.includes(checkRows[0].statusCode)){
                        NotificationUtil("请先编辑完善信息后再提交", "error");
                    } else if (checkRows[0].statusCode && !_this.submitConfig.status.includes(checkRows[0].statusCode)) { //存在状态,但状态不满足
                        NotificationUtil("已提交的记录无需再次提交", "error");
                    }else {
                        IPLAT.confirm({
                            message: '<b>提交后无法编辑,确定要提交吗？</b>',
                            okFn: function (e) {
                                let eiInfo = new EiInfo();
                                eiInfo.set("id", checkRows[0].id);
                                EiCommunicator.send(_this.submitConfig.serviceName, _this.submitConfig.methodName, eiInfo, {
                                    onSuccess: function (ei) {
                                        if (ei.getStatus() == -1) {
                                            NotificationUtil(ei.getMsg(), "error");
                                            return;
                                        }
                                        NotificationUtil("提交成功");
                                        window[_this.resultId+"Grid"].dataSource.page(1);
                                    }
                                });
                            },
                            cancelFn: function (e) {
                            }
                        })
                    }
                });
            }

            /**撤回**/
            if(_this.back) {
                $("#BACK").on("click", function (e) {
                    let checkRows = window[_this.resultId+"Grid"].getCheckedRows()
                    if (checkRows.length < 1) {
                        NotificationUtil("请先选择需要撤回的记录", "error");
                    } else if (checkRows[0].statusCode && !_this.backConfig.status.includes(checkRows[0].statusCode)) { //存在状态,但状态不满足
                        NotificationUtil("该记录无法撤回", "error");
                    } else {
                        let eiInfo = new EiInfo();
                        eiInfo.set("id", checkRows[0].id);
                        EiCommunicator.send(_this.backConfig.serviceName, _this.backConfig.methodName, eiInfo, {
                            onSuccess: function (ei) {
                                if (ei.getStatus() == -1) {
                                    NotificationUtil(ei.getMsg(), "error");
                                    return;
                                }
                                NotificationUtil("撤回成功");
                                window[_this.resultId+"Grid"].dataSource.page(1);
                            }
                        });
                    }
                });
            }

            //其他功能
            if(_this.extentMethod) {
                _this.extentMethod.forEach(ex => {
                    $("#"+ex['buttonId']).on("click", function(e){ ex.call()});
                })
            }
        }
        grid[_this.resultId] = resultGrid;
        return grid;
    }

    /**
     * 构建Grid对象
     */
    this.buildToolBarGrid = function () {
        let grid = {},resultGrid = {};
        //单元格点击事件
        this.buildCellClick(resultGrid);
        //单元格编辑后事件
        this.buildAfterEditor(resultGrid);
        //显示图片
        this.buildImgCell(resultGrid);
        if(!this.showPage) {
            resultGrid["pageable"] = false;
            resultGrid["exportGrid"] = false;
        }
        if(this.toolbar) {
            resultGrid["toolbarConfig"] = this.toolbar;
        }
        grid[this.resultId] = resultGrid;
        return grid;
    }

    /**
     * 构建url
     * @param url : string : url
     * @param data : object : 数据对象
     * @param isOpType : boolean : 是否有操作类型参数
     * @param type : string : 操作类型
     * @returns {string|string}
     */
    this.buildUrl = function (url, data, isOpType, type) {
        let reg = /([^?&=]+)=([^&?=]+)/ig, params = [];
        url.replace(reg, function (param, $1, $2) {
            params.push($2);
        });
        params.forEach(e => {
            if(data) {
                url = url.replace(e, data[e.substring(2,e.length-1)])
            } else {
                url = url.replace(e, "")
            }
        })
        if(isOpType) {
            url = url + (params.length > 0 ? "&" : "?") + "type=" + type;
        }
        return url;
    }
}

/**
 * 打开子页面弹窗
 * @param url : string 子页面地址
 * @param windowName : string 子页面窗口ID
 */
function popData(url, windowId="popData" ) {
    url = IPLATUI.CONTEXT_PATH + "/web/" + url;
    let popData = $("#"+windowId);
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        },
        close:function (e) {
            if(dataUidList!= undefined && dataUidList!= null){
                dataUidList[0].scrollIntoView()
            }

        }
    });
    // 打开弹窗
    window[windowId+"Window"].open().center();
}

/**
 * grid添加数据并去重
 * @param resultId : string 表格block的id
 * @param addBottom : boolean 是否将数据添加到表格底部
 * @param checkedRow : array 需要添加到表格的数据
 * @param addFields : array 需要额外添加的字段
 * @param args
 */
function distinctGridAdd(resultId, addBottom= false, checkRows, addFields, ...args) {
    let rows = window[resultId+"Grid"].getDataItems();
    if(rows && rows.length > 0 && args && args.length > 0) {
        for (let i = 0; i < checkRows.length; i++) {
            let model = checkRows[i],isExist = false;
            rows.forEach(e => {
                let isEqual = true;
                args.forEach(field => isEqual = isEqual && e[field] == model[field])
                isExist = isExist || isEqual;
            });
            if(!isExist) {
                if(addFields) {
                    addFields.forEach(field => model[field] = "")
                }
                window[resultId+"Grid"].addRows(model, addBottom, true);
            }
        }
    } else {
        if(addFields) {
            checkRows.forEach(row =>{
                addFields.forEach(field => row[field] = "")
            })
        }
        window[resultId+"Grid"].addRows(checkRows)
    }
}

/**
 * 设置日期初始值
 */
function resetTime(beginId, endId) {
    let lastDate = new Date();
    lastDate.setMonth(lastDate.getMonth()-1);
    $("#"+beginId).val(lastDate.Format("yyyy-MM-dd"));
    $("#"+endId).val(new Date().Format("yyyy-MM-dd"));
}

/**
 * 参数重置
 * @param eiInfo
 * @param hasTime
 * @param hasUser
 */
function resetParam(eiInfo, hasTime, hasUser) {
    $("#inqu_status-0-deptNum").val(eiInfo.get("inqu_status-0-deptNum"));
    $("#inqu_status-0-deptName").val(eiInfo.get("inqu_status-0-deptName"));
    if(hasUser) {
        $("#inqu_status-0-recCreatorName").val(eiInfo.get("inqu_status-0-recCreatorName"))
    }
    if(hasTime) {
        resetTime("inqu_status-0-beginTime", "inqu_status-0-endTime");
    }
}

/**
 * 重命名EiBlock
 * @param eiInfo : EiInfo EiInfo对象
 * @param blockName : string 原先EiBlock的ID
 * @param newBlockName : string 新的EiBlock的ID
 */
function renameBlock(eiInfo, blockName, newBlockName) {
    let blocks = eiInfo.getBlocks();
    let block = blocks[blockName];
    blocks[newBlockName] = block;
    delete blocks[blockName];
}

/**
 * 判断是否存在或是否为空
 * @param prop
 * @param defaultValue
 * @returns {*}
 */
function isNotExist(prop, defaultValue) {
    if(prop == undefined || prop == null) { return defaultValue;}
    return prop;
}

/**
 * 时间格式化
 * @param fmt ： 格式
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 双击关闭图片大图
 */
function closeWindowUtil () {
    $("#showMatImg").data("kendoWindow").close();
}



