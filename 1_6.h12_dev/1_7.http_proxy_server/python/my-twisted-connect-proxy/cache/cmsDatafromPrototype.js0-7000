(function(o){
	if (!o) {
		return false;
	}

	if (!o.Event) {
		return false;
	}

	o.nova_init_hook_data_from = function() {
		o.Event.observe(document, 'click',  function(ev) {
			if (!ev) {
				ev = o.event;
			}

			var target = ev.target || ev.srcElement;
			if(!target) {
				return ;
			}
			if (target.tagName !== 'A') {
				if (!target.parentNode || (target.parentNode.tagName !== 'A')) {
					return ;
				}
				target	= target.parentNode;
			}

			target	= Element.extend(target);
			CmsDatafromPrototype.addDataFrom(target);
		});
	} 
 })(window);

var CmsDatafromPrototype	= {
	getFromA: function() {
		//from:a
		var a	= 'y1';
		if (OST.isIPAD) {
			a	= 'y8';
		} else if (OST.isIPHONE) {
			a	= 'y9';
		} else if (OST.isPad) {
			a	= 'y10';
		} else if (OST.isPhone) {
			a	= 'y11';
		} else if (OST.isMobile) {
			a	= 'y7'
		} else if (OST.isWin || OST.isMac) {
			a	= 'y1';
		}

		return a;
	},

	getFromB: function() {
		//from:b
		var b	= pagetype + '-' + domain + '-' + pageurl + '-' + topicIdNum + '-' + pageIdNum;

		return b;
	},

	getFromC: function(obj) {
		if ((typeof obj !== 'object') || (typeof obj.ancestors !== 'function')) {
			return '';
		}

		var pids        = '';
		var oParents    = obj.ancestors();
		var oParent     = null;
		for (var i = 0; i < oParents.length; ++i) {
		    oParent = oParents[i];
		    if ((typeof oParent.id === 'undefined') || (oParent.id.substring(0, 2) !== 'm_')) {
		        continue;
		    }
		    pids	= oParent.id.substring(2) + '-' + pids;
		}
		if (pids !== '') {
		    return pids.substring(0, pids.length - 1);
		}

		return '';
	},

	cancatFromParams: function(obj) {
		var orginDatafrom	 = obj.readAttribute('data-from');
		if (empty(orginDatafrom)) {
			return false;
		}

		var a	= CmsDatafromPrototype.getFromA();
		var b	= CmsDatafromPrototype.getFromB();
		var c	= CmsDatafromPrototype.getFromC(obj);
		if (c === '') {
			return false;
		}

		return a + '.' + b + '.' + c + '.' + orginDatafrom;
	},

	addDataFrom: function(obj) {
		var href 		= obj.readAttribute('href');
		if(empty(href)) {
			return false;
		}

		if(href.indexOf('http') === 0 && href.indexOf('?from') === -1 && href.indexOf('&from') === -1) {
			var datafrom	= CmsDatafromPrototype.cancatFromParams(obj);
			if (!datafrom) {
				return false;
			}
			var connector	= (href.indexOf('?') !== -1) ? '&' : '?';
			obj.writeAttribute('href', href + connector + 'from=' + encodeURIComponent(datafrom));
		}
	}
};