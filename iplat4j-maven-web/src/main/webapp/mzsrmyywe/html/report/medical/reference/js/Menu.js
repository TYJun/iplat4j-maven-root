import Utils from './Utils.js';
var deptList = [];
Array.prototype.indexOf = function(val) { 
    for (var i = 0; i < this.length; i++) { 
        if (this[i] == val) return i; 
    } 
    return -1; 
};
Array.prototype.remove = function(val) { 
    var index = this.indexOf(val); 
    if (index > -1) { 
        this.splice(index, 1); 
    } 
};

/**
 * 根据用户工号、院区编号，获取科室集合
 */
 function getDeptList() {
	$.ajax({
		url: baseUrl + 'MobileAgentService',
		type: 'post',
		headers: {
			methodName: "getUserDepts",
			serviceName: "AUFW01",
		},
		data: {
			prames: JSON.stringify({
				workNo: localStorage.getItem("workNo"),//用户工号
				datagroupCode: localStorage.getItem("dataGroupCode")//院区编号
			})

		},
		success: function (data) {
			if (data.status == "0") {
                deptList =  data.attr.result;
			} else {
				mui.toast("获取数据失败");
			}
		}
	});
}

export default class Menu{
    constructor(_list){
        this.elem=this.createElem(_list);
    }
    createElem(_list){
        if(this.elem) return this.elem;
        //创建最外层ul容器
        let ul=Utils.createE("ul",{
            listStyle:"none",
            padding:"0px",
            margin:"0px",
            width:"100%",
            border:"1px solid #f5f5f5",
            color:"#333",
            fontSize:"14px",
            userSelect: "none"
        });
        //创建li列表
        this.createMenu(_list,ul);
        //ul监听点击事件
        ul.addEventListener("click",e=>this.clickHandler(e));
        return ul;
    }
    appendTo(parent){
        Utils.appendTo(this.elem,parent);
    }
    //创建一级菜单
    createMenu(_list,parent){
        for(let i=0;i<_list.length;i++){
            let li=Utils.createE("li",{
                background:"#f5f5f5",
                borderBottom:"1px solid #ddd",
                lineHeight:"30px",
                cursor:"pointer"
            },{
                textContent:_list[i].name
            })
            let span=Utils.createE("span",{
                display:"inline-block",
                width:"16px",
                height:"16px",
                background:"#ddd",
                lineHeight:"12px",
                textAlign:"center",
                float:"left",
                margin:"7px 4px 0px 4px"
            },{
                textContent:_list[i].category.length>0? "+" : "-"
            })
            let checkBox=Utils.createE("input",{
                display:"inline-block",
                width:"16px",
                height:"16px",
                background:"#ddd",
                lineHeight:"12px",
                textAlign:"center",
                float:"right",
                margin:"7px 4px 0px 4px"
            })
            checkBox.setAttribute("type","checkbox");
            checkBox.setAttribute("value","11");
            checkBox.setAttribute("name","科室1");
            Utils.appendTo(span,li);
            Utils.appendTo(checkBox,li);
            Utils.appendTo(li,parent);
            //创建子菜单
            this.createSubMenu(_list[i].category,li);
        }
    }
    //创建子菜单
    createSubMenu(_subList,parent){
        //如果当前菜单没有子菜单，则跳出
        if(_subList.length===0) return;
        let subUl=Utils.createE("ul",{
            listStyle:"none",
            background:"#fff",
            padding:"0px",
            margin:"0px",
            fontSize:"14px",
            display:"none"
        })
        for(let i=0;i<_subList.length;i++){
            let subLi=Utils.createE("li",{
                paddingLeft:"25px",
                position:"relative"
            })
            if(!_subList[i].category){
                //如果当前菜单没有子菜单，则创建a标签，进行跳转
                let subA=Utils.createE("a",{
                    color:"#333",
                    textDecoration:"none",
                    width:"100%",
                    display:"inline-block"
                },{
                    textContent:_subList[i].name,
                    href:_subList[i].href || "javascript:void(0)",
                    target:_subList[i].href ? "_blank" : "_self"
                })
                let checkBox=Utils.createE("input",{
                    display:"inline-block",
                    width:"16px",
                    height:"16px",
                    background:"#ddd",
                    lineHeight:"12px",
                    textAlign:"center",
                    float:"right",
                    margin:"7px 4px 0px 4px"
                })
                checkBox.setAttribute("type","checkbox");
                checkBox.setAttribute("value","11");
                checkBox.setAttribute("name","科室1");
                Utils.appendTo(checkBox,subA)
                Utils.appendTo(subA,subLi);
            }else{
                //如果当前菜单有子菜单，创建span标签
                let subSpan=Utils.createE("span",{
                    position:"absolute",
                    left:"10px",
                    top:"8px",
                    border: "1px solid #ccc",
                    display: "inline-block",
                    width: "10px",
                    height: "10px",
                    lineHeight:"8px"
                },{
                    textContent:_subList[i].category.length>0? "+" : "-"
                })
                subLi.textContent=_subList[i].name;
                let checkBox=Utils.createE("input",{
                    display:"inline-block",
                    width:"16px",
                    height:"16px",
                    background:"#ddd",
                    lineHeight:"12px",
                    textAlign:"center",
                    float:"right",
                    margin:"7px 4px 0px 4px"
                })
                checkBox.setAttribute("type","checkbox");
                checkBox.setAttribute("value","11");
                checkBox.setAttribute("name","科室1");
                
                Utils.appendTo(subSpan,subLi);
                Utils.appendTo(checkBox,subLi);
            }

            Utils.appendTo(subLi,subUl);
            //如果当前菜单没有子菜单，则跳过下面的执行
            if(!_subList[i].category) continue;
            //如果当前菜单有子菜单,将子菜单作为参数，进行递归
            this.createSubMenu(_subList[i].category,subLi);
        }
        Utils.appendTo(subUl,parent);
    }
    clickHandler(e){

        //选中父LI，子所有input设置选中
        if(e.target.nodeName!=="LI"){
            this.updateCheckStatus(e);
        }
        //判断选中状态
        // if(e.target.nodeName == "INPUT"){
        //     this.setCheckList(e)
        // }
        //如果当前点击的不是li标签或者span，直接跳出
        if(e.target.nodeName!=="LI" && e.target.nodeName!=="SPAN") return;
        let targ;
        //让targ等于当前点击的li标签
        if(e.target.nodeName==="SPAN") targ=e.target.parentElement;
        else targ=e.target;
        //如果当前点击Li下面没有子菜单，直接跳出
        if(targ.children.length<=1) return;
        //控制当前点击的Li下的ul显示隐藏
        if(!targ.bool) targ.lastElementChild.style.display="block";
        else targ.lastElementChild.style.display="none";
        targ.bool=!targ.bool;
        //改变span标签的内容
        this.changeSpan(targ);
        if(targ.lastElementChild.nodeName == "INPUT") targ.lastElementChild.style.display="block";
    }
    updateCheckStatus(e){
        var inpputList = e.target.parentNode.getElementsByTagName('INPUT');
        var flag = inpputList[0].checked;
        for(var i = 0; i< inpputList.length; i++){
            inpputList[i].checked = flag;
        }
    }
    setCheckList(e){
        if(e.target.checked){
            deptList.push(e.target.value);
        }else if(!e.target.checked){
            deptList.remove(e.target.value);
        }
    }
    changeSpan(elem){
        //改变span的显示内容
        if(elem.lastElementChild.style.display==="block"){
            elem.firstElementChild.textContent="-";
        }else{
            elem.firstElementChild.textContent="+";
        }
    }
}
