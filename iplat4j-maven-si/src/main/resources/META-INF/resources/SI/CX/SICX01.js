let choseItem; let webUrl = window.location.origin; webUrl = webUrl.substring(0,webUrl.lastIndexOf(":"));
$(function () {
    /**回车键查询**/
    keydown("inqu", "QUERY");

    $("#QUERY").on('click', function(e){ query();})

    $("#REQUERY").on('click', function(e){
        document.getElementById("inqu-trash").click();
        resetTimeForOneLevel(false);
        query();
    })
    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {label: "root", text: "报表列表", leaf: true},
            select: function(e) {
                choseItem = this.dataItem(e.node);
                openReport()
            },
        }
    };

    IPLATUI.EFTreeInput = {
        "matTypeId": {
            backFill: function (e) {
                $("#matTypeNum").val(e.node['matTypeNum']);
            },
        }
    }
    /**供应商自动补全**/
    $("#inqu_status-0-supplierName").kendoAutoComplete({
        dataSource: getDataSources("SITY02/selectSupplier", "supplier",[{name:'supplierName',id:"inqu_status-0-supplierName"}]),
        dataTextField: "supplierName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-supplierNum").val(dataItem.supplierCode);
        }
    });

    /**领用科室自动补全**/
    $("#inqu_status-0-deptName").kendoAutoComplete({
        dataSource: getDataSources("SITY02/selectBusinessDept", "b_dept",[{name:'deptName',id:"inqu_status-0-deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#inqu_status-0-deptNum").val(dataItem['deptNum']);
        }
    });

    setTimeout(function(){
        preventJump();
        openReport();
    },1000);
})

/**
 * 数据查询
 */
function query() {
    let wareHouseNo = IPLAT.EFSelect.value($("#wareHouseNo"));
    let wareHouseName = IPLAT.EFSelect.text($("#wareHouseNo"));
    let matTypeName = $("#matTypeId_textField").val();
    let matTypeNum = matTypeName.trim() ? $("#matTypeNum").val() : '';
    let beginTime = $("#beginTime").val();
    let endTime = $("#endTime").val();
    let classGroup = IPLAT.EFSelect.value($("#classGroup"));
    let classGroupName = IPLAT.EFSelect.text($("#classGroup"));
    let hasBack = IPLAT.EFSelect.value($("#hasBack"));
    let matName = $("#matName").val();
    let deptNum = __ei["deptNum"];
    let deptName = __ei["deptName"];
    let surpNum = '', userDeptNum = '';
    if($("#inqu_status-0-supplierName").val()) {
        surpNum = $("#inqu_status-0-supplierNum").val();
    }
    if($("#inqu_status-0-deptName").val()) {
        userDeptNum = $("#inqu_status-0-deptNum").val();
    }
    //拼接地址
    let url = `&wareHouseNo=${wareHouseNo}&wareHouseName=${wareHouseName}&matTypeNum=${matTypeNum}&matTypeName=${matTypeName}&beginTime=${beginTime}&endTime=${endTime}&deptNum=${deptNum}&deptName=${deptName}&classGroup=${classGroup}&classGroupName=${classGroupName}&hasBack=${hasBack}&surpNum=${surpNum}&userDeptNum=${userDeptNum}&matName=${matName}`;
    if(choseItem) {
        //url = webUrl + choseItem.url.replace(/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/, "") + url
        url = choseItem.url + url;
    } else {
        let items = $("#tree01").data("kendoTreeView").items();
        if(!items || items.length == 1) {return;}
        let html = items[1].innerHTML;
        let baseUrl = html.substring(html.indexOf("href")+6, html.indexOf(">")-1);
        // baseUrl = webUrl + baseUrl.replace(/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/, "");
        url = baseUrl +  url;
        choseItem = {text: items[1].innerText, url: baseUrl};
    }
    let reportURL = cjkEncode(url);
    document.getElementById("cxFrame").src = reportURL;

}

function openReport() {
    query()
    let titleSpan = $("#r2").find(".i-title")[0];
    titleSpan.innerHTML = choseItem.text;
    titleSpan.innerText = choseItem.text;
}

function preventJump() {
    let items = $("#tree01").data("kendoTreeView").items();
    for(let index = 1; index < items.length; index++) {
        let html = items[index].innerHTML;
        let newHtml = html.substring(0,2) + ' onclick="return false;" ' + html.substring(2);
        items[index].innerHTML = newHtml
    }
}

/**
 * url编译
 * @param url
 */
function cjkEncode(url) {
    if (url == null) {
        return "";
    }
    let newText = "";
    for (let i = 0; i < url.length; i++) {
        let code = url.charCodeAt (i);
        if (code >= 128 || code == 91 || code == 93) {  //91 is "[", 93 is "]".
            newText += "[" + code.toString(16) + "]";
        } else {
            newText += url.charAt(i);
        }
    }
    return newText;
}

