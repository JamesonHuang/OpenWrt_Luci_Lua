/**
 * GridTab专用 js 文件
 */
// 页面加载完成后初始化页面事件
window.nova_init_hook_event = function(){
	var s = "MSIE", u = navigator.userAgent, i = -1;
	if ((i = u.indexOf(s)) >= 0) {
		var v = parseFloat(u.substr(i + s.length));
		if(v == 6){ try{ document.execCommand("BackgroundImageCache", false, true); } catch(e){} }
	}
	
	var tabctls = $$('ul[focus="tabControl"]');
	for(var i=0; i<tabctls.length; ++i){//处理所有标签容器
		var ul = tabctls;
		var li = Element.down(tabctls[i],'li');
		var switchtype = tabctls[i].attributes.getNamedItem('switchtype').nodeValue;
		while(!empty(li)){
			if(switchtype === 'auto'){
				Event.observe(li,'mouseover',attachTabMouseover);
				Event.observe(li,'mouseout',attachTabMouseOut);
			}else if(switchtype === 'click'){
				Event.observe(li,'click',attachTabClick);
			}else{
				Event.observe(li,'mouseover',attachTabEvent);
			}
			li = Element.next(li,'li');
		}
		if(switchtype === 'auto'){
			ul[0].id = 'thTabHeaderUl_'+i;//增加id标识，用于锁定标签
			topic_start_switch(i);
		}
	}
}

//事件处理
var attachTabMouseover = function(event){
	var event = event || window.event;
	var ul = Event.findElement(event, 'ul');
	attachTabEvent(event);
	var temp = ul.id;
	splits = temp.split('_');
	topic_stop_switch(splits[1]);
}
var attachTabMouseOut = function(event){
	var event = event || window.event;
	var ul = Event.findElement(event, 'ul');
	var temp = ul.id;
	splits = temp.split('_');
	topic_start_switch(splits[1]);
}
var attachTabClick = function(event){
	var event = event || window.event;
	var ul = Event.findElement(event, 'ul');
	attachTabEvent(event);
	var temp = ul.id;
	splits = temp.split('_');
	topic_stop_switch(splits[1]);
}
var attachTabEvent = function(event){
	var event = event || window.event;
	var culi = Event.findElement(event, 'li');
	// 如果已经是当前标签，则返回
	if ( culi && $(culi).hasClassName('current') ) {
		return false;
	}
	var ul = Event.findElement(event, 'ul');
	var li = Element.down(ul, 'li');
	//隐藏所有标签对应内容区
	while(!empty(li)){
		var splits = li.id.split('_');
		$('tab_'+splits[1]+'_'+splits[2]).hide();
		Element.removeClassName(li, 'current');
		li = Element.next(li, 'li');
	}
	//显示触发事件的标签及内容区
	Element.addClassName(culi, 'current');
	splits = culi.id.split('_');
	$('tab_'+splits[1]+'_'+splits[2]).show();
	$('tab_'+splits[1]+'_'+splits[2]).innerHTML.evalScripts();
	if($('tab_'+splits[1]+'_'+splits[2]+'tabarea')){
		$('tab_'+splits[1]+'_'+splits[2]+'tabarea').show();
		imgs = $A($('tab_'+splits[1]+'_'+splits[2]).getElementsByTagName('img'));
		imgs.each(function(o){
			Element.extend(o);
			if (o.readAttribute('_src')) {
				o.src = o.readAttribute('_src');
			}
		});
	}
}

//自动转换
var tabItvls = {};
function topic_start_switch(ii){
	tabItvls['intval'+ii] = self.setInterval('topic_tab_switch('+ii+')',30000);
}
function topic_stop_switch(ii){
	if(!empty(tabItvls['intval'+ii])) tabItvls['intval'+ii] = window.clearInterval(tabItvls['intval'+ii]);
}

//标签转换
function topic_tab_switch(ii){
	var tabctls = $$('ul[focus="tabControl"]');
	for(var i=0; i<tabctls.length; ++i){
		if(i === ii){//锁定所要操作的标签
			//找到当前li
			var li = Element.down(tabctls[i], 'li');
			while(!empty(li)){
				if(li.className == 'current') break;
				li = Element.next(li, 'li');
			}
			//把当前li对应的内容域隐藏
			var splits = li.id.split('_');
			$('tab_'+splits[1]+'_'+splits[2]).hide();
			Element.removeClassName(li, 'current');
			//取循环的下一个li
			li = Element.next(li, 'li');
			if(empty(li)){
				li = Element.down(tabctls[i], 'li');
			}
			//显示下一个li及对应内容区
			Element.addClassName(li, 'current');
			splits = li.id.split('_');
			$('tab_'+splits[1]+'_'+splits[2]).show();
			$('tab_'+splits[1]+'_'+splits[2]).innerHTML.evalScripts();
			if($('tab_'+splits[1]+'_'+splits[2]+'tabarea')){
				$('tab_'+splits[1]+'_'+splits[2]+'tabarea').show();
				imgs = $A($('tab_'+splits[1]+'_'+splits[2]).getElementsByTagName('img'));
				imgs.each(function(o){
					Element.extend(o);
					if (o.readAttribute('_src')) {
						o.src = o.readAttribute('_src');
					}
				});
			}
		}
	}
}
