var alertflag = true;
var ajax = function (params) {
  var ispan = params.url.indexOf(localStorage.getItem("url")) != -1;
  var auth = localStorage.getItem("auth");
  var obj =
    auth && ispan
      ? {
          headers: {
            auth: auth,
            source: "app",
          },
          complete: function (XMLHttpRequest, status) {
            if (JSON.parse(XMLHttpRequest.responseText).respCode == 401) {
              if (alertflag) {
                alert("您的账户已在其他设备登录");
                localStorage.removeItem("auth");
                location.href = "login.html";
                alertFlag = false;
              }
            }
          },
        }
      : {
          complete: function (XMLHttpRequest, status) {
            JSON.parse(XMLHttpRequest.responseText).respCode == 401 &&
              (location.href = "login.html");
          },
        };
  var par = $.extend(false, {}, params);
  obj.success = function (data) {
    data.respCode != 401 && par.success(data);
  };
  params.success = undefined;
  $.ajax($.extend(false, obj, params));
};

(function () {
  var clic_cunt = 0;
  var timer = null;
  var flag = true;
  document.body.onclick = function () {
    console.dir(clic_cunt);
    timer && clearTimeout(timer);
    clic_cunt++;
    if (clic_cunt > 10 && flag) {
      timer && clearTimeout(timer);
      flag = false;
      var script = document.createElement("script");
      script.src = "./js/eruda.min.js";
      script.async = true;
      document.getElementsByTagName("head")[0].appendChild(script);
      script.onload = function () {
        eruda.init();
      };
    }
    timer = setTimeout(function () {
      clic_cunt = 0;
    }, 3000);
  };
})();
