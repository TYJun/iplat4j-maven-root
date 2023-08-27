/**
 * @desc  上拉刷新，下拉加载
 * @param {Object} query 查询参数
 * @param {Number} pageNumber 当前页码
 * @param {Number} pageSize 每页条数
 * @author chenjing
 */
function pullrefresh(){
	mui.init({
	  pullRefresh: {
	    container: '#pullrefresh',
	    down: {
	      callback: pulldownRefresh,
	      contentrefresh: "正在刷新",
	    }
	    ,
	    up: {
	      contentrefresh: "加载中，请稍后...",
	      contentnomore: "暂无更多数据",
	      callback: pullupRefresh
	    }
	  }
	})
}


/**
 * @desc  时间戳转换日期格式方法
 * @author chenjing
 */
function formatDate(value){
  if (value == null) {
      return '';
  } else {
      let date = new Date(value);
      let y = date.getFullYear();// 年
      let MM = date.getMonth() + 1;// 月
      MM = MM < 10 ? ('0' + MM) : MM;
      let d = date.getDate();// 日
      d = d < 10 ? ('0' + d) : d;
      let h = date.getHours();// 时
      h = h < 10 ? ('0' + h) : h;
      let m = date.getMinutes();// 分
      m = m < 10 ? ('0' + m) : m;
      let s = date.getSeconds();// 秒
      s = s < 10 ? ('0' + s) : s;
      return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
  }
}

/**
 * @desc  扫码框的任意移动
 * @author chenjing
 */
function touchMove(id){
	 var flag = false;
     var cur = {
         x:0,
         y:0
     }
     var nx,ny,dx,dy,x,y;
     // 移动不能超出可视范围
     var right = window.innerWidth;
     var bottom = window.innerHeight;
     var down = function(){
         flag = true;
         var touch ;
         if(event.touches){
             touch = event.touches[0];
         }else {
             touch = event;
         }
         cur.x = touch.clientX;
         cur.y = touch.clientY;
         dx = div2.offsetLeft;
         dy = div2.offsetTop;
     }
     var move = function(){
         if(flag){
             var touch ;
             if(event.touches){
                 touch = event.touches[0];
             }else {
                 touch = event;
             }
             nx = touch.clientX - cur.x;
             ny = touch.clientY - cur.y;
             x = dx+nx;
             y = dy+ny;
             if(x<0){
             	x = 0
             }
             if(x>right-60){
             	x = right-60
             }
             if(y<48){
             	y = 48
             }
             if(y>bottom-60){
             	y = bottom-60
             }
             div2.style.left = x+"px";
             div2.style.top = y +"px";
             div2.style.bottom = '';
         }
     }
     //鼠标释放时候的函数
     var end = function(){
         flag = false;
     }
     var div2 = document.getElementById(id);
     div2.addEventListener("mousedown",function(){
         down();
     },false);
     div2.addEventListener("touchstart",function(){
         down();
     },false)
     div2.addEventListener("mousemove",function(){
         move();
     },false);
     div2.addEventListener("touchmove",function(){
         move();
     },false)
     document.body.addEventListener("mouseup",function(){
         end();
     },false);
     div2.addEventListener("touchend",function(){
         end();
     },false);
     // 初始化扫码框的位置
     div2.style.bottom = 110 +"px";
     div2.style.left = window.innerWidth - 80 +"px";
}