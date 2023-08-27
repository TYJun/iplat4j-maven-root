/** 根据key获取initLoad中的指定Block*/
function getInitLoadBlocks(key) {
    if (key) {
        return _getEi().blocks[key];
    } else {
        return _getEi().blocks;
    }
}
/** 根据key获取initLoad中指定Block的map值*/
function getBlocksMappedRows(key) {
    return getInitLoadBlocks(key).getMappedRows();
}

/** 刷新resultGrid*/
function refreshResultGrid(i) {
    resultGrid.dataSource.page(i ? i : 1);
}

function getTody(){
    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();
   return s2;
}

function getTomorrow(){
    //明天的时间
    var day3 = new Date();
    day3.setTime(day3.getTime()+24*60*60*1000);
    var s3 = day3.getFullYear()+"-" + (day3.getMonth()+1) + "-" + day3.getDate();
    return s3;
}

function compareDateTime(startTime,endTime){
    if(isNaN(startTime)&&!isNaN(Date.parse(startTime))){
        if(isNaN(endTime)&&!isNaN(Date.parse(endTime))){
            var startTime= new Date(Date.parse(startTime));
            var endTime=new Date(Date.parse(endTime));
            return startTime > endTime;
        }else{
            return "非时间类型";
        }
    }else{
        return "非时间类型";
    }
}

//获取表头指定field的jq对象
function getGridHeader(key){
    var h = $(".k-header");
    var headers = [];
    for(var i=0; i< h.length; i++){
        if($(h[i]).attr("data-field") ){
            //例：key="week_"
            if($(h[i]).attr("data-field").startsWith(key)){
                headers.push($(h[i]));
            }
        }
    }
    return headers;
}
//渲染编辑器下拉框
function drawGridEditorSelect(selectId,options,container,dataSource){
    var input = $('<input id="'+selectId+'"/>');
    input.attr("name", options.field);
    input.appendTo(container);
    // 在 grid 的 cell 中创建下拉选择框
    input.kendoDropDownList({
        valuePrimitive: true,
        dataTextField: "textField",
        dataValueField: "valueField",
        dataSource: dataSource
    }).appendTo(container);
}

var weeksNumMap = {0:"周一",1:"周二",2:"周三",3:"周四",4:"周五",5:"周六",6:"周日"};