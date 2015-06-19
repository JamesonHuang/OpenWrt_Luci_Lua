 (function() {
 var preSet = function() {
        var w = document.documentElement ? document.documentElement.clientWidth : document.body.clientWidth,
            R = 1400,
            r = 1075,
            b = Element.extend(document.body);
        if(w < R && w >= r){
            b.addClassName('body-offset-w970').removeClassName('body-offset-w1190');
        }else if(w >= R && lsidetooltype == "w"){
            b.addClassName('body-offset-w1190').removeClassName('body-offset-w970');
        }else if(w < r){
            b.addClassName('body-offset-mini').removeClassName('body-offset-w970').removeClassName('body-offset-w1190');
        }
    };
    preSet();
})();
