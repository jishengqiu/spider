define(['util'], function(util) {
	var storage ={
		get : function (key, callback) {
			var val = cookie.get(key);
			callback && callback(val);
		},
		set : function (key, val) {
			cookie.set(key, val, {expires: 1000 * 60 * 60 * 24 * 356});
		},
		clear : function (key) {
			cookie.clear(key);
		}
   };
   var cookie = {
		get: function(key) {
			var a, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
			if(a = document.cookie.match(reg)){
				return unescape(a[2]);
			}else{
				return "";
			}
		},
		set: function(key, val, options) {
			options = options || {};
			var expires = options.expires;

			if(typeof(expires) === "number"){
				expires = new Date();
				expires.setTime(expires.getTime() + options.expires);
			}

			document.cookie =
				key + "=" + escape(val)
				+ (expires ? ";expires=" + expires.toGMTString() : "")
				+ (options.path ? ";path=" + options.path : ";path=/")
				+ (options.domain ? "; domain=" + options.domain : "");
		}
	};
   var localStorage = {

		get: function (key) {
			key = 'sgj_' + key;
			if(isSupportedLocalStorage){
				return localStorage.getItem(key);
			}else{
				return toolkit.cookie.get(key);
			}
		},
		set: function (key, val) {
			key = 'sgj_' + key;
			if(isSupportedLocalStorage){
				localStorage.setItem(key, val);
			}else{
				this.cookie.set(key, val, {expires: 1000 * 60 * 60 * 24 * 356});
			}
		},
		remove: function (key) {
			key = 'sgj_' + key;
			if(isSupportedLocalStorage){
				localStorage.removeItem(key);
			}else{
				this.cookie.clear(key);
			}
		},
		clear: function () {
			if(isSupportedLocalStorage){
				localStorage.clear();
			}else{
				// todo 
			}
		}
	};
	return {
		storage:storage,
		cookie:cookie,
		localStorage:localStorage,
		/**
		 * AJAX 拉取数据
		 * @param {String} url 请求地址
		 * @param {Object} data 请求数据
		 */
		request: function(url, data) {
			var REQUEST_URL_PREFIX = '';
			var REQUEST_DATA = {
				head: {
					version: '',
					appversion: '',
					appname: '',
					clientId:'01',
					platform: '',
					token: ''
				},
				body: {}
			}
			storage.get('token', function (val) {
				REQUEST_DATA.head.token = val;
			})

			var _url = REQUEST_URL_PREFIX + url;

			// 全网址的请求与聚理财的文章api不需要加julicai这个前缀
			if (/^https?:/.test(url) || /jlcweb/.test(url)) {
				_url = url;
			}

			var _data = {};
			data = {
				body: data
			}

			$.extend(_data, REQUEST_DATA, data);
			_data = JSON.stringify(_data);

			util.loading.show();

			return $.ajax({
				type: 'POST',
				url: _url,
				dataType: 'json',
				data: {params: _data},
				error: function (xhr, errorType, error) {
					util.loading.hide();
					if (errorType === 'timeout') {
						util.prompt('数据请求超时，请稍后再试');
					} else if (errorType === 'parsererror') {
						util.prompt('服务器升级中，请稍后再试');
					} else if ((errorType === 'abort' || errorType === 'error') && xhr.status == 0) {
						util.prompt('世界上最遥远的距离，就是没有网络');
					} else {
						util.prompt('请求失败，请稍后再试');
					}
				},
				success: function () {
					util.loading.hide();
				}
			})
		},
		/**
		 * 向一个url之后追加参数
		 * @param {String} url  需要追加参数的url
		 * @param {Object} data 需要追加的参数
		 * @return {String}     追加参数之后的url
		 * @example encodeRequestParam('a.html?pid=1', {name: 'xiaowang'}) => 'a.html?pid=1&name=xiaowang'
		 */
		encodeRequestParam: function(url, data) {
			var i
			var param = ''
			for(i in data) {
				param += '&' + i + '=' + encodeURIComponent(data[i])
			}
			if(url.indexOf('?') == -1) {
				param = param.replace(/^\&/, '?')
			}
			return url + param
		},
		/** 
		 * 解析url或search字符串。
		 * @param {String} url url或search字符串
		 * @param {String} key (Optional) 参数名。
		 * @return {Json|String|Array|undefined} 如果key为空，则返回解析整个字符串得到的Json对象；否则返回参数值。有多个参数，或参数名带[]的，参数值为Array。
		 * @example queryUrl('a.html?pid=1&name=xiaowang', 'pid') => '1'
		 * @example queryUrl('a.html?pid=1&name=xiaowang') => {pid:'1',name:'xiaowang'}
		 */
		queryUrl: function(url, key) {
			url = url.replace(/^[^?=]*\?/ig, '').split('#')[0];	//去除网址与hash信息
			var json = {};
	
			//考虑到key中可能有特殊符号如“[].”等，而[]却有是否被编码的可能，所以，牺牲效率以求严谨，就算传了key参数，也是全部解析url。
			url.replace(/(^|&)([^&=]+)=([^&]*)/g, function (a, b, key , value){
				// 对url这样不可信的内容进行decode，可能会抛异常，try一下；另外为了得到最合适的结果，这里要分别try
				try {
					key = decodeURIComponent(key);
				} catch(ex) {}
				try {
					value = decodeURIComponent(value);
				} catch(ex) {}
	
				if(!(key in json)) {
					json[key] = /\[\]$/.test(key) ? [value] : value; //如果参数名以[]结尾，则当作数组
				} else if(json[key] instanceof Array) {
					json[key].push(value);
				} else {
					json[key] = [json[key], value];
				}
			});
	
			return key ? json[key] : json;
		},
	
		// 在前端给某些值加*，例如18680888801 => 186****8801
		encrypt: {
			realname: function(s) {
				var prefix = s.slice(0, 1)
				var middle = s.slice(1)
				middle = middle.replace(/./g, '*')
				return prefix + middle
			},
			phone: function(s) {
				var prefix = s.slice(0, 3);
				var suffix = s.slice(-4);
				var middle = s.slice(3, -4);
				middle = middle.replace(/./g, '*');
				return prefix + middle + suffix;
			},
			id: function(s) {
				var prefix = s.slice(0, 3);
				var suffix = s.slice(-4);
				var middle = s.slice(3, -4);
				middle = middle.replace(/./g, '*');
				return prefix + middle + suffix;
			},
			email: function(s) {
				var arr = s.split('@');
				var prefix = arr[0].slice(0, 1);
				var middle = arr[0].slice(1);
				middle = middle.replace(/./g, '*');
				arr[0] = prefix + middle;
				return  arr.join('@');
	
			}
		},
		//获取浏览器信息
		getBroswser: function() {
			var ua = navigator.userAgent.toLowerCase();
			try {
				if (ua.match(/msie ([\d.]+)/)) {
					return 'IE';
				}
			    if (ua.match(/firefox\/([\d.]+)/)) {
			        return 'Firefox';
			    }
			    if (ua.match(/chrome\/([\d.]+)/)) {
			        return 'Chrome';
			    }
			    if (ua.match(/version\/([\d.]+).*safari/)) {
			        return 'Safari';
			    }
			    if (ua.match(/opera.([\d.]+)/)) {
			        return 'Opera';
			    }
			    return 'other';
			} catch(e) {
				console.log(e);
				return 'other';
			}
		},
		//获取操作系统信息
		getOs: function() {
			var ua = navigator.userAgent.toLowerCase();
			if (/android/i.test(ua)) {
			   return 'android OS';
			}
			if (/ipad|iphone|mac/i.test(ua)){
			    return 'iPhone OS';
			}
			if (/win|wins|windows/i.test(ua)){
			    return 'Windows OS';
			}
			if (/linux/i.test(ua)){
			    return 'linux OS';
			}
			return 'Other OS';
		},

		str2Int: function(value) {
			return value ? parseInt(value) : 0;
		}
	};
});
