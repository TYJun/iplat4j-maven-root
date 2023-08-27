
//<script type="text/javascript">
    /*附件上传*/
    IPLATUI.EFUpload = {
    "contentFile" : {
    loadComplete : function(e) {
    var uploader = e.sender.uploader;
    uploader.clearAllFiles();
},
    validation : {
    allowedExtensions : [ ".jpg", "jpeg", ".png", ".gif", ".bmp",
    ".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt",
    ".pptx", ".pdf", ".zip", "rar", ".7z" ]
},
    localization : {
    invalidFileExtension : "文件格式不支持, 上传失败"
},
    success : function(e) {
    var file = e.files[0];
    var model = createModel(1);
    model["id"] = "";
    model["contPk"]="";
    model["attachId"] = e.response.docId;
    model["attachPath"] = e.response.docUrl;
    model["attachName"] = file.name;
    model["attachSize"] = file.size;
    model["attachDesc"] = "";
    resultDGrid.addRows(model);
    fileChooseWindow.close();
},
}
}


    /*分页处理*/
    $(function() {
    var buttonList=[];
    if($("#inqu_status-0-type").val()=="add"){
    var addFile={
    name : "addFile",
    text : "上传",
    layout : "3",
    click : function() {
    fileChooseWindow.open().center()
}
}
    buttonList.push(addFile);
    var deleteFile={
    name: "delFile",
    text: "删除",
    layout: "3",
    click: function () {
    var checkRows = resultDGrid.getCheckedRows();
    if (checkRows.length > 0) {
    resultDGrid.removeRows(checkRows);
    fileList.push(checkRows[0]);
    console.log(fileList);
} else {
    IPLAT.NotificationUtil("请选择需要删除的合同附件信息")
}
}
}
    buttonList.push(deleteFile);
    var loadFile={
    name : "downLoadFile",
    text : "下载",
    layout : "3",
    click : function() {
    downLoadFile()
}
}
    buttonList.push(loadFile);
}else if($("#inqu_status-0-type").val()=="edit"){
    var addFile={
    name : "addFile",
    text : "上传",
    layout : "3",
    click : function() {
    fileChooseWindow.open().center()
}
}
    buttonList.push(addFile);
    var deleteFile={
    name: "delFile",
    text: "删除",
    layout: "3",
    click: function () {
    var checkRows = resultDGrid.getCheckedRows();
    if (checkRows.length > 0) {
    resultDGrid.removeRows(checkRows);
    fileList.push(checkRows[0]);
    console.log(fileList);
} else {
    IPLAT.NotificationUtil("请选择需要删除的合同附件信息")
}
}
}
    buttonList.push(deleteFile);
    var loadFile={
    name : "downLoadFile",
    text : "下载",
    layout : "3",
    click : function() {
    downLoadFile()
}
}
    buttonList.push(loadFile);
    var lookFile={
    name: "lookFile",
    text: "预览",
    layout: "3",
    click: function () {
    var checkRows = resultDGrid.getCheckedRows();
    if (checkRows.length > 0) {
    var docPath = checkRows[0].attachPath;
    var last = docPath.lastIndexOf("/");
    var first= docPath.indexOf("/");
    var fileName = docPath.substring(last + 1, docPath.length);
    var filePath = docPath.substring(0, first);
    if (docPath.indexOf(".pdf") >= 0) {
    window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName+"&filePath="+filePath);
}else{
    IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
    return;
}
} else {
    IPLAT.NotificationUtil("请选择需要预览的合同附件信息")
}
}
}
    buttonList.push(lookFile);
}else if($("#inqu_status-0-type").val()=="see"){
    var loadFile={
    name : "downLoadFile",
    text : "下载",
    layout : "3",
    click : function() {
    downLoadFile()
}
}
    buttonList.push(loadFile);
    var lookFile={
    name: "lookFile",
    text: "预览",
    layout: "3",
    click: function () {
    var checkRows = resultDGrid.getCheckedRows();
    if (checkRows.length > 0) {
    var docPath = checkRows[0].attachPath;
    var last = docPath.lastIndexOf("/");
    var first= docPath.indexOf("/");
    var fileName = docPath.substring(last + 1, docPath.length);
    var filePath = docPath.substring(0, first);
    if (docPath.indexOf(".pdf") >= 0) {
    window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName+"&filePath="+filePath);
}else{
    IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
    return;
}
} else {
    IPLAT.NotificationUtil("请选择需要预览的合同附件信息")
}
}
}
    buttonList.push(lookFile);
}

    var validator = IPLAT.Validator({
    id : "result"
});
    var fileList=[];
    //开启校验
    IPLATUI.EFGrid = {
    "resultA" : {
    toolbarConfig : {
    hidden : false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    add : false,
    cancel : false,
    save : false,
    'delete' : false,
    buttons : [
{
    name : "ADDSTAFF",
    text : "新增",
    layout : "3",
    click : function() {
    clauseChooseWindowOpen();
}
},
{
    name : "DELSTAFF",
    text : "删除",
    layout : "3",
    click : function() {
    //选中的行信息
    var checkRows = resultAGrid.getCheckedRows();
    if (checkRows.length > 0) {
    resultAGrid.removeRows(checkRows);
} else {
    IPLAT.NotificationUtil("请选择需要删除的合同名称")
}
}
}
    ]
},
},
    "resultB":{
    toolbarConfig:{
    pageable : false,
    hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    add: false,cancel: false,save: false,'delete': false,
    buttons:[
{
    name: "ADDCED",text: "新增",layout:"3",
    click: function () {
    resultBGrid.addRow()
}
},
{
    name: "DELCED",text: "删除",layout: "3",
    click: function () {
    var checkRows = resultBGrid.getCheckedRows();
    if (checkRows.length > 0) {
    resultBGrid.removeRows(checkRows);
} else {
    IPLAT.NotificationUtil("请选择需要删除的付款内容")
}
}
}
    ]
},
},
    "resultC":{
    //pageable : false,
    toolbarConfig:{
    pageable : false,
    hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    add: false,cancel: false,save: false,'delete': false,
    buttons:[
{
    name: "ADDCED",text: "新增",layout:"3",
    click: function () {
    resultCGrid.addRow()
}
},
{
    name: "DELCED",text: "删除",layout: "3",
    click: function () {
    var checkRows = resultCGrid.getCheckedRows();
    if (checkRows.length > 0) {
    resultCGrid.removeRows(checkRows);
} else {
    IPLAT.NotificationUtil("请选择需要删除的费用内容")
}
}
}
    ]
},
},
    "resultD" : {
    //pageable : false,
    toolbarConfig : {
    hidden : false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    add : false,
    cancel : false,
    save : false,
    'delete' : false,
    buttons : buttonList
}
},
    "resultE" : {
    //pageable : false,
    toolbarConfig : {
    hidden : false,//true 时，不显示功能按钮，但保留 setting 导出按钮
    add : false,
    cancel : false,
    save : false,
    'delete' : false,
    buttons : [
{
    name : "ADDMESSAGE",
    text : "新增",
    layout : "3",
    click : function() {
    personChooseWindowOpen();
}
},
{
    name : "DELMESSAGE",
    text : "删除",
    layout : "3",
    click : function() {
    var checkRows = resultEGrid.getCheckedRows();
    console.log(checkRows);
    if (checkRows.length > 0) {
    resultEGrid.removeRows(checkRows);
} else {
    IPLAT.NotificationUtil("请选择删除的执行人")
}
}
}
    ]
},
},
}
  //   /*渲染分页*/
  //     IPLATUI.EFTab = {
  //     "tab-tab_grid" : {
  //     select : function(e) {
  //     var grid = $(e.contentElement).find("div[data-role='grid']").data("kendoGrid")
  //     if (grid.getDataItems().length === 0) {
  //     grid.dataSource.page(1);
  // }
  // }
  // }
  // }

    //弹窗保存
    $("#SAVEPR").on("click", function() {
        // 开启校验

        var validator = IPLAT.Validator({
            id: "result"
        });
        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");
        // var contSignTime = eiInfo.get("inqu_status-0-contSignTime");
        // var planTakeefTime = eiInfo.get("inqu_status-0-planTakeefTime");
        // var planFinishTime = eiInfo.get("inqu_status-0-planFinishTime");
        // if (contSignTime > planFinishTime) {
        //     IPLAT.NotificationUtil("合同签订时间不能大于计划终止时间", "warning");
        //     return false;
        // }
        // if (planTakeefTime > planFinishTime) {
        //     IPLAT.NotificationUtil("计划生效时间不能大于计划终止时间", "warning");
        //     return false;
        // }



        if (validator.validate()) {
            //获取参数
            var eiInfo = new EiInfo();
            eiInfo.setByNode("result");

            //获取tab数据
            //var hta = resultAGrid.getDataItems();
            //var htb = resultBGrid.getDataItems();
            // for (var i = 0; i < htb.length; i++) {
            //     var planPaymentTime = kendo.toString(htb[i].planPaymentTime, "yyyy-MM-dd");
            //     htb[i].planPaymentTime = planPaymentTime;
            // }
            // var htc = resultCGrid.getDataItems();
            // var file = resultDGrid.getDataItems();
            var hte = resultEGrid.getDataItems();
            // var deleteFile = fileList;
            // eiInfo.set("hta", hta)
            // eiInfo.set("htb", htb)
            // eiInfo.set("htc", htc)
            eiInfo.set("hte", hte)
            // eiInfo.set("file", file)
            // eiInfo.set("deleteFile", deleteFile)
            //if(!validatePR(validator)){ return; }
            //提交
            EiCommunicator.send("DFBJ0101", "saveClean", eiInfo, {
                onSuccess: function (ei) {
                    closeCurrentWindow();
                    IPLAT.NotificationUtil(ei.msg)
                    window.parent["resultGrid"].dataSource.page(1);
                }
            })

         }

});

    //取消
    $("#CANCEL").on("click", function() {
    closeCurrentWindow();
});

})

    //关闭窗口
    function closeCurrentWindow() {
    window.parent['popDataWindow'].close();
}

    /*合同条款内容弹窗*/
//     function clauseChooseWindowOpen() {
//     var url = IPLATUI.CONTEXT_PATH + "/web/CMDJ0102?initLoad";
//     var popData = $("#clauseChoose");
//     popData.data("kendoWindow").setOptions({
//     open : function() {
//     popData.data("kendoWindow").refresh({
//     url : url,
// });
// }
// });
//     // 打开弹窗
//     clauseChooseWindow.open().center();
// }

    /* 选择合同联系人弹窗 */
    function personChooseWindowOpen() {
    var url = IPLATUI.CONTEXT_PATH + "/web/DFBJ0102?initLoad";
    var popData = $("#personChoose");
    popData.data("kendoWindow").setOptions({
    open : function() {
    popData.data("kendoWindow").refresh({
    url : url,
});
}
});
    // 打开弹窗
    personChooseWindow.open().center();
}

//     //文件下载
//     function downLoadFile(){
//     var checkRows = resultDGrid.getCheckedRows();
//     if (checkRows.length > 0) {
//     for(var index in checkRows){
//     var docId = checkRows[index].attachId;
//     var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
//     window.location.href = href;
// }
// } else {
//     IPLAT.NotificationUtil("请选择需要下载的文件")
// }
// }

    //子参数
    function addRows(checkRows,type){
    for (var index in checkRows) {
    var model = checkRows[index];
    if(type=="cont"){
    model["contTermName"] = model.contTermName;
    model["cotent"] = model.contTermContent;
    model["remark"] = model.remark;
    //resultAGrid.addRows(model)
    clearRepeat(model,resultAGrid,"contTermName");
}else{
    model["workNo"] = model.workNo;
    model["name"] = model.name;
    model["phone"] = model.number;
    //resultEGrid.addRows(model)
    clearRepeat(model,resultEGrid,"workNo");
}
}
}

    function clearRepeat(model,grid,compareField){
    var list = grid.getDataItems();
    var isExist = false;
    for(var i in list) {
    var row = list[i];
    if(row[compareField] == model[compareField]){
    isExist = true;
}
}
    if(!isExist){
    grid.addRows(model)
}
}

    //创建kendo.data.Model
    function createModel(id){
    var gridRow = kendo.data.Model.define({
    id: "uploadId",
    fields: {
    "id": {
    type:"string"
},
    "contPk": {
    type: "string"
},
    "attachId": {
    type: "string"
},
    "attachPath": {
    type: "string"
},
    "attachName": {
    type: "string"
},
    "attachSize": {
    type: "number"
},
    "attachDesc": {
    type: "string"
}
}
});
    var model = new gridRow({uploadId:id});
    return model;
}

    //创建kendo.data.Model

    function createModelProjectFile(id){
    var gridRow = kendo.data.Model.define({
    id: "uploadId",
    fields: {
    "id": {
    type:"string"
},
    "projectPk": {
    type: "string"
},
    "attachId": {
    type: "string"
},
    "attachPath": {
    type: "string"
},
    "attachName": {
    type: "string"
},
    "attachSize": {
    type: "number"
},
    "attachDesc": {
    type: "string"
},
    "recCreator": {
    type: "string"
},
    "recCreateTime": {
    type: "string"
}
}
});
    var model = new gridRow({uploadId:id});
    return model;
}

    //参数校验
    function validatePR(eiInfo) {
    if (isEmpty(eiInfo.get("inqu_status-0-contName"))) {
    IPLAT.NotificationUtil("合同名称不能为空");
    return false;
}
    if (isEmpty(eiInfo.get("inqu_status-0-planTakeefTime"))) {
    IPLAT.NotificationUtil("合同生效日期不能为空");
    return false;
}
    if (isEmpty(eiInfo.get("inqu_status-0-contAdmin"))) {
    IPLAT.NotificationUtil("合同管理员不能为空");
    return false;
}
    return true;
}







