$(document).ready(function(){

	$('#myModal').on('shown.bs.modal', function (e) {
		var video_play_div = $("#video_play");
		var thisTitle = $(e.relatedTarget).attr("title");
		$("#myModalLabel").html(thisTitle);
		
		if(!chkFlash()){
			video_play_div.html("<span style='text-align:center;display:block;font-size:18px;height:400px;margin-top:150px;'>您还没有安装flash播放器,请点击 <A href=\"http://www.adobe.com/go/getflash\" style=\"color:#55c5f3;\" target=_blank>这里</A> 安装</span>");
		}else{
			
			var thisSrc = $(e.relatedTarget).attr("data-src");
			video_play_div.html("<embed id=\"video\" flashvars=\"isAutoPlay=true\" src=\""+thisSrc+"\" allowFullScreen=\"true\" quality=\"high\" width=\"100%\" height=\"650\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>");
			
		}
	});
	
	$('#myModal').on('hidden.bs.modal', function (e) {
		var video_play_div = $("#video_play");
		if(!chkFlash()){
			video_play_div.html("<span style='text-align:center;display:block;font-size:18px;height:400px;margin-top:150px;'>您还没有安装flash播放器,请点击 <A href=\"http://www.adobe.com/go/getflash\" style=\"color:#55c5f3;\" target=_blank>这里</A> 安装</span>");
		}else{
			video_play_div.html("<span style='text-align:center;display:block;font-size:18px;height:400px;margin-top:150px;'>视频正在加载...</span>");
			
		}
	});

});

function chkFlash() {
    var isIE = (navigator.appVersion.indexOf("MSIE") >= 0);
    var hasFlash = true;
 
    if(isIE) {
        try{
            var objFlash = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
        } catch(e) {
            hasFlash = false;
        }
    } else {
        if(!navigator.plugins["Shockwave Flash"]) {
            hasFlash = false;
        }
    }
    return hasFlash;
}
