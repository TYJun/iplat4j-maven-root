 var links = document.getElementsByTagName('link');
 function enableLinks(index){
   for(var i = 3;i < 5; i++){//循环查找4个link标签
   //disabled表示关闭，如果i不等于当前index，则disabled就是true，即关闭该link标签
   //.sheet表示样式表
      links[i].sheet.disabled = i!=index;
   }
 }

 //给div标签添加鼠标点击事件
 //event:事件对象
 function changeColor(event){
    var index = event.target.dataset.color;
   console.log(index);
   $('#theme').removeClass('choose')
   $('.changeTheme').slideUp()
   localStorage.setItem('changeColor',index);
   if(index == undefined){
      return;
   }else{
      //调用enableLinks()
      enableLinks(index);
   }
 }

if(localStorage.getItem('changeColor')== undefined || localStorage.getItem('changeColor')== null){
 	 enableLinks(3);
}else {
 	 enableLinks(localStorage.getItem('changeColor'));
}