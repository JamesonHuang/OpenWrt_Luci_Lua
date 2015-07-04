/**
 * 视频组件hover遮罩效果
 * @param selector 需要hover效果的视频组件CSS选择器
 * @require Ani.js 动画类
 */
var VideoHover=function(){};
if(navigator.userAgent.indexOf('iPad') === -1){
    //遍历所有现存的视频组件
    VideoHover = function(selector) {  

        $$(selector).each(function(el, i){
            //视频组件
            var parent = el;
            //跳过首页第一个视频组件
            if(parent.hasClassName('focusVideo')){
                return;
            }

            if(parent.hasClassName('v')){
                parent.hoverClass = 'v-hover';
                parent.linkSel    = '.v-link a';
            } else {
                parent.hoverClass = 'p-hover';
                parent.linkSel    = '.p-link a';
            }

            //监听mouseenter事件
            //  取消下移动画，开始(重启)上移动画
            parent.observe('mouseenter',function(evt){
                parent.addClassName(parent.hoverClass);
            });

            //监听mouseleave事件
            //  取消下移动画，开始(重启)上移动画
            parent.observe('mouseleave',function(evt){
                parent.removeClassName(parent.hoverClass);
            });

            try{
                var meta=parent.select('.vb, .p-meta');
                meta[0]&&meta[0].observe('click',function(evt){
                    var target = evt.element(),
                        href   = target.getAttribute('href');
                    if(target.match('a') && href!="" && href!="#"){
                        ;
                    }else{
                        evt.stop();

                        var link=parent.select(parent.linkSel)[0];
                        if(link){
                            window.TUDO && window.TUDO.clickStat && window.TUDO.clickStat.send(evt, link);
                            link.click();
                        }
                        //parent.select('.v-link a').invoke('click');
                    }
                })
            }catch(e){
                debugger;
            }
        });
    };
}