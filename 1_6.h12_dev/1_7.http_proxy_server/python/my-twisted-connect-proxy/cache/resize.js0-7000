(function() {
    if (navigator.userAgent.indexOf('iPad') !== -1) {
        var b = Element.extend(document.body);
        if (document.body.className.indexOf('yk-w970') != -1) {
            b.removeClassName('yk-w970');
        }
        document.body.className += ' yk-w1190';
        document.body.style.paddingRight = "50px";
        var vp = document.createElement('meta');
        vp.setAttribute('name', 'viewport');
        vp.setAttribute('content', 'width=1270px');
        document.head.appendChild(vp);
        return;
    }
    var fn = function() {
        var w = document.documentElement ? document.documentElement.clientWidth : document.body.clientWidth,
            r = 1255,
            b = Element.extend(document.body),
            classname = b.className;
        if (w < r) {
            b.addClassName('yk-w970').removeClassName('yk-w1190');
        } else {
            b.addClassName('yk-w1190').removeClassName('yk-w970');
        }
    }
    if (navigator.userAgent.indexOf('iPad') == -1) {
        if (window.addEventListener) {
            window.addEventListener('resize', function() {
                fn();
            });
        } else if (window.attachEvent) {
            window.attachEvent('onresize', function() {
                fn();
            });
        }
    }
    fn();
})();