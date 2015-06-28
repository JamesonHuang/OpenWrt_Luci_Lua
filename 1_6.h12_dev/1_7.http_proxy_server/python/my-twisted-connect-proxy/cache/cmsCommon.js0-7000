(function(){
    OST = {};
    var osType = {
        isWin:'Win',
        isMac:'Mac',
        isSafari:'Safari',
        isChrome:'Chrome',
        isIPAD: 'iPad', 
        isIPHONE: 'iPhone', 
        isIPOD: 'iPod',
        isLEPAD: 'lepad_hls',
        isMIUI: 'MI-ONE',
        isAndroid:'Android',
        isAndroid4: 'Android 4.',
        isAndroid41: 'Android 4.1',
        isSonyDTV: "SonyDTV",
        isBlackBerry:"BlackBerry",
        isMQQBrowser:'MQQBrowser',
        isMobile:'Mobile'
    };
    for(var os in osType){
        if(navigator.userAgent.indexOf(osType[os]) !== -1){
            OST[os] = true;
        }else{
            OST[os] = false;
        }
    }
    OST.isIos = ((OST.isIPAD || OST.isIPHONE || OST.isIPOD) || OST.isMac );
    OST.isPhone = (OST.isIPHONE || OST.isIPOD || (OST.isAndroid&&OST.isMobile));
    OST.isPad = (OST.isIPAD || (OST.isAndroid && !OST.isMobile));
})();

var cms_request_ad = function(url){
	if(!url) return;
	url += "&aw=w";
	if(logPvid){ url += "&sid="+logPvid; };
	if(OST.isPad){ url += "&bt=pad"; }else if(OST.isPhone){ url += "&bt=phone"; };
	if(OST.isIos){ url += "&os=ios"; }else if(OST.isAndroid){ url += "&os=Android"; };
	Nova.addScript(url);
}