/**
 * [getQueryVariable 获取url指定参数]
 * @param  {[type]} variable [description]
 * @return {[type]}          [description]
 */
function GetQueryString(paramKey) {
  var reg = new RegExp("(^|&)" + paramKey + "=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r != null)
    return unescape(r[2]);
  else
    return null;
}

// 修改title
function setTitle(t) {
    document.title = t;
    var iframe = document.createElement('iframe')
    iframe.style.visibility = 'hidden'
    iframe.setAttribute('src', 'pic.ico')  
    var iframeCallback = function () {
    setTimeout(function () {
    iframe.removeEventListener('load', iframeCallback)
    document.body.removeChild(iframe)
    }, 0)}
    iframe.addEventListener('load', iframeCallback)
    document.body.appendChild(iframe)
}

/**
 *这个必须在文档加载时就触发，创建出来的新的history实体，用于
 */
function pushHistory() {
    var state = {
        title: "title",
        url: "#"
    };
    window.history.pushState(state, "title", "#");
}

// 浏览返回事件
function goBack(backSrc){
  pushHistory()
  window.addEventListener("popstate", function(e) {
      location.href = backSrc
  }, false);
}