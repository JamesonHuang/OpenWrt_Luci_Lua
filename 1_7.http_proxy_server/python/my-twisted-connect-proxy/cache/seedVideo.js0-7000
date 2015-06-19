(function(window) {
	if ((typeof window.SeedVideo === 'object') && window.SeedVideo) {
		return ;
	}

	var SeedVideo	= {
		cookie		: {},
		is_inited	: false
	};

	SeedVideo.init	= function() {
		if (SeedVideo.is_inited) {
			return ;
		}
	
		try {
			var cookie_advideo	= Nova.Cookie.get('advideo').evalJSON(true);
			if ((typeof cookie_advideo === 'object') && cookie_advideo) {
				SeedVideo.cookie	= cookie_advideo;
			}
		} catch (e) {}
	
		SeedVideo.is_inited	= true;
	}
	
	SeedVideo.set_last_index	= function (key, num) {
		var obj_first	= $(key + '_1');
		if (!obj_first) {
			return ;
		}
	
		var obj_tab_container	= obj_first.up('[contab="contab"]');
		if (obj_tab_container && (obj_tab_container.style.display === 'none')) {
			return ;
		}
		SeedVideo.cookie[key]	= !SeedVideo.cookie[key] ? Math.floor(num * Math.random() + 1) : ((SeedVideo.cookie[key] % num) + 1);
	
		var is_display_one	= false;
		var obj_advideo		= null;
		for (var i = 1; i <= num; ++i) {
			obj_advideo	= $(key + '_' + i);
			if (!obj_advideo) {
				continue;
			}

			if (i == SeedVideo.cookie[key]) {
				obj_advideo.style.display	= '';
				is_display_one				= true;
			} else {
				obj_advideo.style.display	= 'none';
			}
		}
		if (!is_display_one) {
			obj_first.style.display	= '';
		}
	}

	SeedVideo.save_cookie	= function() {
		Nova.Cookie.set('advideo', Object.toJSON(SeedVideo.cookie), 30);
	}

	window.SeedVideo	= SeedVideo;
	window.SeedVideo.init();
	Element.observe(window, 'load', function() {
		window.SeedVideo.save_cookie();
	});
})(window);