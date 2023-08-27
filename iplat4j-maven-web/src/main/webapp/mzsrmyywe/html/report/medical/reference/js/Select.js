//下拉框的数据，股票代码/股票名称
var initlist = ['3001/股票1','3002/股票2','3003/股票3','3004/很长的很长的股票4','3005/最长最长最长最长最长最长的股票5','3006/test6','3007/一号#7','3008/最后一个股票-'];//
	
//多选下拉框所在的div
var selecteddiv = document.getElementById("selectdiv");

//鼠标是否在【多选下拉框div】上面（如果在div上面，需要控制鼠标的点击事件，不让div隐藏；否则要让该div隐藏）
var indiv = false;

//模糊查询input
var fuzzysearchinput = document.getElementById("fuzzysearch");

//选中的科室代码（需要传到后台的参数）
var selectedlist = [];
//选中的科室名称（展示在前台给业务员看的）
var selectednamelist = [];
//控制下拉框展示隐藏标识 true ：隐藏，false：展示
var dispalyFlag = true;
 function initSelect(){
    
    //动态创建所有的checkbox元素
    for(var i = 0; i < initlist.length; i++){
        var tmpdiv = document.createElement("div");
        var tmpinput = document.createElement("input");
        tmpinput.setAttribute("class","mycheckbox");
        tmpinput.setAttribute("name",initlist[i].dept_name);
        tmpinput.setAttribute("type","checkbox");
        tmpinput.setAttribute("onclick","mycheck(this)");
        tmpinput.setAttribute("value",initlist[i].dept_num);
        tmpinput.style="width: 15px;height: 14px;margin: 5px 5px 0px 5px;";
        
        var tmptext = document.createTextNode(initlist[i].dept_name);
        tmpdiv.appendChild(tmpinput);
        tmpdiv.appendChild(tmptext);
        selecteddiv.appendChild(tmpdiv);
    }
    
    //鼠标点击事件，如果点击在 selectedbutton，或者是在多选框div中的点击事件，不作处理。其他情况的点击事件，将多选空div隐藏
    document.onclick=function(event){
        if(event.target.id=="selectButton" || indiv){
            return;
        }
        selecteddiv.style.display="none";
        document.getElementById("fuzzysearchdiv").style.display="none";
        dispalyFlag = false;
    };
};

//点击selectButton，展示多选框
function myclick (){
    if(dispalyFlag){
        document.getElementById("fuzzysearchdiv").style.display="none";
        selecteddiv.style.display="none";
    }else{
        document.getElementById("fuzzysearchdiv").style.display="block";
        selecteddiv.style.display="block";
    }
    dispalyFlag = !dispalyFlag;
}

//鼠标进入多选框的div【selectdiv】
function mousein(){
    indiv = true;
}

//鼠标离开多选框的div【selectdiv】
function  mouseout(){
    indiv = false;
}

//checkbox的点击事件
function mycheck(obj){
    if(obj.checked){
        selectedlist.push(obj.value);
        selectednamelist.push(obj.nextSibling.nodeValue);
    }else{
        for(var i = 0; i < selectedlist.length; i++){
            if(selectedlist[i] == obj.value){
                selectedlist.splice(i,1);
                selectednamelist.splice(i,1);
            }
        }
    }
    document.getElementById("selectButton").value=selectednamelist;
}

//模糊查询
function myfuzzysearch(){
    var checkboxlist = document.getElementsByClassName("mycheckbox");
    for(var i = 0; i < checkboxlist.length; i++){
        if(checkboxlist[i].nextSibling.nodeValue.indexOf(fuzzysearchinput.value) == -1){
            checkboxlist[i].parentNode.style.display = "none";
        }else{
            checkboxlist[i].parentNode.style.display = "block";
        }
    }
}

function mysubmit(){
    alert(selectedlist);
}
