export default class Utils{
    static createE(elem,style,prep){
        elem=document.createElement(elem);
        if(style) for(let prop in style) elem.style[prop]=style[prop];
        if(prep) for(let prop in prep) elem[prop]=prep[prop];
        return elem;
    }
    static appendTo(elem,parent){
        if (parent.constructor === String) parent = document.querySelector(parent);
        parent.appendChild(elem);
    }
    static randomNum(min,max){
        return Math.floor(Math.random*(max-min)+min);
    }
    static randomColor(alpha){
        alpha=alpha||Math.random().toFixed(1);
        if(isNaN(alpha)) alpha=1;
        if(alpha>1) alpha=1;
        if(alpha<0) alpha=0;
        let col="rgba(";
        for(let i=0;i<3;i++){
            col+=Utils.randomNum(0,256)+",";
        }
        col+=alpha+")";
        return col;
    }
    static insertCss(select,styles){
        if(document.styleSheets.length===0){
            let styleS=Utils.createE("style");
            Utils.appendTo(styleS,document.head);
        }
        let styleSheet=document.styleSheets[document.styleSheets.length-1];
        let str=select+"{";
        for(var prop in styles){
            str+=prop.replace(/[A-Z]/g,function(item){
                return "-"+item.toLocaleLowerCase();
            })+":"+styles[prop]+";";
        }
        str+="}"
        styleSheet.insertRule(str,styleSheet.cssRules.length);
    }
    static getIdElem(elem,obj){
        if(elem.id) obj[elem.id]=elem;
        if(elem.children.length===0) return obj;
        for(let i=0;i<elem.children.length;i++){
            Utils.getIdElem(elem.children[i],obj);
        }
    }
}
