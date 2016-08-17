define(function () {
	// cookie的读、写方法
	var cookie = {
		get: function (key) {
			var a, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
			if (a = document.cookie.match(reg)) {
				return unescape(a[2]);
			} else {
				return "";
			}
		},
		set: function (key, val, options) {
			options = options || {};
			var expires = options.expires;

			if (typeof(expires) === "number") {
				expires = new Date();
				expires.setTime(expires.getTime() + options.expires);
			}

			document.cookie =
				key + "=" + escape(val)
				+ (expires ? ";expires=" + expires.toGMTString() : "")
				+ (options.path ? ";path=" + options.path : ";path=/")
				+ (options.domain ? "; domain=" + options.domain : "");
		}
	}

	var isSupportedLocalStorage = (function () {
	    try {
	      localStorage.setItem('test', 'test');
	      localStorage.removeItem('test');
	      return true;
	    } catch(e) {
	      return false;
	    }
	})();
	window.isSupportedLocalStorage = isSupportedLocalStorage

	var currentOS = (function () {
		var ua = window.navigator.userAgent
		if (/iPhone|iPad|iPod/i.test(ua)) {
			return 'ios'
		}
		if (/android/i.test(ua)) {
			return 'android'
		}
		return 'other'
	})();

	window.IS_APP = true
	// 为了方便在pc上调试，所以在浏览器里面如果cookie ispc=1，那么就认为是在pc里面了
	if (cookie.get('ispc') == 1) {
		IS_APP = false
	}

	window.IS_ANDROID = false;
	//如果是安卓设备，就显示系统设置（手势密码）
	if (currentOS === 'android') {
		IS_ANDROID = true;
	}

	// 调用客户端提供的方法入口
	function execCmd(data, callback) {
		if (currentOS === 'android') {
			HostApp.executeAndroidNative(data, function (res) {
				callback && callback(res)
			})
		} else if (currentOS === 'ios') {

			var randomFunctionName = 'func' + (new Date).getTime();
			window[randomFunctionName] = callback;
			var dataJson = JSON.parse(data);
			dataJson['callback'] = randomFunctionName;
			data = JSON.stringify(dataJson)

			// 不是直接location.href = uri，是因为在Ios浏览器里面连续短时间两次设置location.href，会导致浏览器认为应该location到后面那次，后面那次覆盖掉了前面那次请求
			var uri = 'julicai://' + encodeURIComponent(data)
			var iframe = document.createElement('iframe');
			iframe.setAttribute('src', uri);
			document.body.appendChild(iframe);
			iframe.parentNode.removeChild(iframe);
			iframe = null
			// window.location.href = uri
		}
	}

	window.nativeController = {
		webview: {
			open: function (uri) {
				var _data = {
					service: 'webview.create',
					data: {
						uri: uri
					},
					version: '1.0'
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			},
			goback: function () {
				var _data = {
					service: 'webview.goback',
					data: {
						url: ''
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			},
			// 刷新app四个根节点页面（首页、市场、资产、更多）的方法。使用场景是，比如登陆成功以后，当回到资产和更多的时候，如果页面不刷新，还是显示的未登录。
			refreshMain: function () {
				alert('refreshMain');
				var action = ['refresh_index', 'refresh_marke', 'refresh_assets', 'refresh_more', 'refresh_my']
				var _data = {
					service: 'webview.refresh_main',
					data: {
						'pageCode': action[0]
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
				
                var _data = {
					service: 'webview.refresh_main',
					data: {
						'pageCode': action[2]
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
				
				var _data = {
					service: 'webview.refresh_main',
					data: {
						'pageCode': action[4]
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			},

			login: function (name, pwd) {
				var _data = {
					service: 'go.set.login',
					data: {
						'userName': name,
						'password': pwd
					},
					version: '1.0'
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			},

			jumpsetPage: function () {
				var _data = {
					service: 'go.set.settingPage',
					data: {},
					version: '1.0'
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			},

			loginNewVersion: function (callback) {
				var _data = {
					service: 'is.has.loginNewVersion',
					data: {},
					version: '1.0'
				}
				_data = JSON.stringify(_data)
				execCmd(_data, callback)
			},

			phoneInfo: function (callback) {
				var _data = {
					service: 'system.phoneinfo',
					data: {},
					version: '1.0'
				}
				_data = JSON.stringify(_data)
				execCmd(_data, callback)
			},

			shareAll: function (title, message, url){
				var _data = {
					service: "system.shareAll",
					data: {
						title: title,
						content: message,
						linkUrl: url
					},
					version: '1.0'
			    }
			    _data = JSON.stringify(_data)
			    // util.prompt("title is: " + _data);
				execCmd(_data)
			},

			// 清除所有已经打开的webview，回到当前根节点页面。比如在市场里面，连续往里面点了很多层，调用这个方法，可以直接回到市场页。
			clear: function (callback) {
				var _data = {
					service: 'webview.clearView',
					data: {
						url: ''
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data, callback)
			},
			// 流程控制。比如在A页面调用此方法发起开户流程，连续走过了A-B-C-D-E，然后在E页面要结束掉此流程的话，会直接切回到A页面。
			flow: {
				start: function (id, callback) {
					var _data = {
						service: 'webview.startflow',
						data: {
							flowId: id
						},
						version: '1.0'
					}
					_data = JSON.stringify(_data)
					execCmd(_data, callback)
				},
				// url参数为：当结束掉该流程之后，需要跳转到的新的页面地址。本来这个open新页面动作可以放在callback里面，但是会造成，先结束流程该页面消失，然后再执行callback里面的打开新页面
				end: function (id, url, callback) {
					function completeFullUrl(url) {
						url = typeof(url) == 'undefined' ? '' : url;
						if (url.indexOf('/finance-jlcres/') >= 0 && url.indexOf('http://') < 0 && url.indexOf('https://') < 0) {
							url = window.location.origin + url
						}
						return url
					}

					url = completeFullUrl(url)

					var _data = {
						service: 'webview.endflow',
						data: {
							flowId: id,
							url: url
						},
						version: '1.0'
					}
					_data = JSON.stringify(_data)
					execCmd(_data, callback)
				}
			}
		},
		storage: {
			get: function (key, callback) {
				var _data = {
					service: 'storage.get',
					data: {
						key: key
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data, callback)
			},
			set: function (key, val) {
				var _data = {
					service: 'storage.set',
					data: {
						key: key,
						value: val
					}
				}
				_data = JSON.stringify(_data)
				execCmd(_data)
			}
		},
		init: {
			// 初始化topbar的方法
			topbar: function (cfg, callback) {
				var defaultCfg = {
					center: ['随管家'], // 此处如果数组长度为2，则显示副标题
					right: {
						title: '',
						callback: function () {
						}
					}
				}
				cfg = $.extend(defaultCfg, cfg)

				var callbackName = ''
				if (cfg.right.callback) {
					callbackName = 'nativeCallback' + parseInt(Math.random() * 100000000, 10)
					window[callbackName] = function (res) {
						cfg.right.callback(res)
					}
				}

				var _data = {
					service: 'init.topbar',
					data: {
						center: cfg.center,
						right: {
							title: cfg.right.title,
							callbackName: callbackName
						}
					}
				}

				_data = JSON.stringify(_data)
				execCmd(_data)
			}
		}
	}

	// 初始化topbar
	if (IS_APP) {
		// 在android里面，由于与服务端通信用的是服务端注入的HostApp对象，在js开始调用客户端init topbar的时候，可能HostApp对象还未被注入，因此设一个定时器来检测是否该对象已经注入，注入之后再InitTopbar
		if (currentOS === 'android') {
			var timeout = 5000
			var timer = 0
			var loopId = -1

			loopId = setInterval(function () {
				timer += 50
				if (typeof HostApp != 'undefined') {
					if (CONFIG_TOPBAR) {
						nativeController.init.topbar(CONFIG_TOPBAR)
					}
					clearInterval(loopId)
				}
				if (timer > timeout) {
					clearInterval(loopId)
				}
			}, 50)
		} else if (currentOS === 'ios' && CONFIG_TOPBAR) {
			nativeController.init.topbar(CONFIG_TOPBAR)
		}
	}
});














































