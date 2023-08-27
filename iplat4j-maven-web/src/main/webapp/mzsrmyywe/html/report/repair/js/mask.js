(function($) {
	//屏蔽，适合单个元素. 参数opticy: 不透明度 0 ~ 1.0   
	$.fn.mask = function(opaticy) {
		opaticy = opaticy || 0;
		var spec = opaticy * 100;
		var opaticyCSS = 'filter:alpha(opacity=' + spec + ');opacity: ' + opaticy + ';';
		var pHtml = '<p class="pMask" style="position: absolute; width: 100%; height: 100%; left: 0px; top: 0px; background: #fff; ' + opaticyCSS + '"> </p>';
		$(this).wrap('<span style="position: relative"></span>');
		$(this).parent().append(pHtml);
		$(this).data("mask", "true");
	}
	//取消屏蔽  
	$.fn.unmask = function() {
		$(this).parent().find(".pMask").remove();
		$(this).unwrap();
		$(this).data("mask", "false");
	}
})(jQuery);