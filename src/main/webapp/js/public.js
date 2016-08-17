window.toolkit = {
	/**
	 * 向一个url之后追加参数
	 * @param {String} url  需要追加参数的url
	 * @param {Object} data 需要追加的参数
	 * @return {String}     追加参数之后的url
	 * @example encodeRequestParam('a.html?pid=1', {name: 'xiaowang'}) => 'a.html?pid=1&name=xiaowang'
	 */
	encodeRequestParam: function (url, data) {
		var i
		var param = ''
		for (i in data) {
			param += '&' + i + '=' + encodeURIComponent(data[i])
		}
		if (url.indexOf('?') == -1) {
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
	queryUrl: function (url, key) {
		url = url.replace(/^[^?=]*\?/ig, '').split('#')[0];	//去除网址与hash信息
		var json = {};

		//考虑到key中可能有特殊符号如“[].”等，而[]却有是否被编码的可能，所以，牺牲效率以求严谨，就算传了key参数，也是全部解析url。
		url.replace(/(^|&)([^&=]+)=([^&]*)/g, function (a, b, key, value) {
			// 对url这样不可信的内容进行decode，可能会抛异常，try一下；另外为了得到最合适的结果，这里要分别try
			try {
				key = decodeURIComponent(key);
			} catch (ex) {
			}
			try {
				value = decodeURIComponent(value);
			} catch (ex) {
			}

			if (!(key in json)) {
				json[key] = /\[\]$/.test(key) ? [value] : value; //如果参数名以[]结尾，则当作数组
			} else if (json[key] instanceof Array) {
				json[key].push(value);
			} else {
				json[key] = [json[key], value];
			}
		});

		return key ? json[key] : json;
	},
	cookie: {
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
		},
		clear: function (key) {
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var value = toolkit.cookie.get(key);
			if (value != null) {
				document.cookie = key + "=1;expires=" + exp.toGMTString() + ';path=/';
			}
		}
	},
	/**
	 * 格式化时间戳为某种格式
	 * @param {Number} timestamp 时间戳
	 * @param {String} format    格式
	 * @return {String}
	 * @example parseDate(1438931394268, 'YYYY-MM-DD hh:mm:ss') => '2015-08-07 15:09:54'
	 */
	parseDate: function (timestamp, format) {
		var date = new Date(timestamp)
		var data = {
			year: date.getFullYear(),
			month: date.getMonth() + 1,
			day: date.getDate(),
			hour: date.getHours(),
			minute: date.getMinutes(),
			second: date.getSeconds()
		}
		if (!format) {
			return data
		}
		for (var i in data) {
			data[i] += ''
		}
		function addDigit(s, digit) {
			digit = digit || 2
			var len = s.length
			var diff = digit - len
			if (diff <= 0) {
				return s
			} else {
				while (diff--) {
					s = '0' + s
				}
				return s
			}
		}

		return format.replace(/(YYYY|YY|MM|M|DD|D|hh|h|mm|m|ss|s)/g, function (all) {
			return {
				YYYY: data.year,
				YY: data.year.slice(-2),
				MM: addDigit(data.month),
				M: data.month,
				DD: addDigit(data.day),
				D: data.day,
				hh: addDigit(data.hour),
				h: data.hour,
				mm: addDigit(data.minute),
				m: data.minute,
				ss: addDigit(data.second),
				s: data.second
			}[all]
		})
	},
	// parseSum: function(s, digit) {
	// 	digit = typeof digit === 'undefined' ? 2 : digit
	// 	function addDigit(s, digit) {
	// 		digit = digit || 2
	// 		var len = s.length
	// 		var diff = digit - len
	// 		if(diff <= 0) {
	// 			return s
	// 		} else {
	// 			while(diff--) {
	// 				s = '0' + s
	// 			}
	// 			return s
	// 		}
	// 	}
	// 	addDigit(s, digit)
	// 	return s.toString().replace(/(\d{1,3})(?=(\d{3})+(?:$|\D))/g, '$1,')
	// },

	// 在前端给某些值加*，例如18680888801 => 186****8801
	encrypt: {
		realname: function (s) {
			var prefix = s.slice(0, 1)
			var middle = s.slice(1)
			middle = middle.replace(/./g, '*')
			return prefix + middle
		},
		phone: function (s) {
			var prefix = s.slice(0, 3)
			var suffix = s.slice(-4)
			var middle = s.slice(3, -4)
			middle = middle.replace(/./g, '*')
			return prefix + middle + suffix
		},
		id: function (s) {
			var prefix = s.slice(0, 3)
			var suffix = s.slice(-4)
			var middle = s.slice(3, -4)
			middle = middle.replace(/./g, '*')
			return prefix + middle + suffix
		},
		email: function (s) {
			var arr = s.split('@')
			var prefix = arr[0].slice(0, 1)
			var middle = arr[0].slice(1)
			middle = middle.replace(/./g, '*')
			arr[0] = prefix + middle
			return arr.join('@')

		}
	}
}

// 拉取数据的方法
toolkit.request = function (url, data) {
	var REQUEST_URL_PREFIX = '/julicai'
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
	toolkit.storage.get('token', function (val) {
		REQUEST_DATA.head.token = val
	})

	var _url = REQUEST_URL_PREFIX + url;

	// 全网址的请求与聚理财的文章api不需要加julicai这个前缀
	if (/^https?:/.test(url) || /jlcweb/.test(url)) {
		_url = url
	}

	var _data = {}
	data = {
		body: data
	}

	$.extend(_data, REQUEST_DATA, data)
	_data = JSON.stringify(_data)

	util.loading.show()

	return $.ajax({
		type: 'POST',
		url: _url,
		dataType: 'json',
		data: {params: _data},
		error: function (xhr, errorType, error) {
			util.loading.hide()
			if (errorType === 'timeout') {
				util.prompt('数据请求超时，请稍后再试')
			} else if (errorType === 'parsererror') {
				util.prompt('服务器升级中，请稍后再试')
			} else if ((errorType === 'abort' || errorType === 'error') && xhr.status == 0) {
				util.prompt('世界上最遥远的距离，就是没有网络')
			} else {
				util.prompt('请求失败，请稍后再试')
			}
		},
		success: function () {
			util.loading.hide()
		}
	})
}
// 获取用户信息接口
toolkit.getUserInfo = function (callback) {
	var url = '/ws/api/user/500101?' + Date.now()
	toolkit.request(url).done(function (res) {
		callback && callback(res)
	})
}
// 注销方法
toolkit.logout = function () {
	util.dialog({
		content: '确定注销？',
		button: [
			{
				text: '取消'
			},
			{
				text: '确定',
				callback: function () {
					nativeController.webview.login('', '');
					toolkit.storage.clear('token')
					IS_APP && nativeController.webview.refreshMain()
					IS_APP && nativeController.webview.clear()

					!IS_APP && toolkit.jumpto('/finance-jlcres/more.html')
				}
			}
		]
	})
}

toolkit.completeFullUrl = function (url) {
	if (url.indexOf('/finance-jlcres/') >= 0 && url.indexOf('http://') < 0 && url.indexOf('https://') < 0) {
		url = window.location.origin + url
	}
	return url
}

// 检查权限，比如是否已登陆、是否已开户，如果没有，则进行相应的逻辑判断和页面跳转
toolkit.checkAuth = function (auth, targetUrl) {
	targetUrl = targetUrl || ''
	// 之所以不用toolkit.jumpto而写了一个gogogo，是因为每次jumpto方法都会调用checkAuth，checkAuth里面再调用jumpto，就死循环了..
	function gogogo(url) {
		url = toolkit.completeFullUrl(url)
		if (IS_APP) {
			nativeController.webview.open(url)
		} else {
			window.location.href = url
		}
	}

	function checkLogin(res, shouldCheckOpenAccount) {
		// 未登录
		if (res.rstcode === '111111') {
			var url = '/finance-jlcres/account/login.html'
			IS_APP && nativeController.webview.flow.start('login')
			url = toolkit.encodeRequestParam(url, {redirectUrl: targetUrl})
			gogogo(url)
			// 已登录
		} else if (res.rstcode === '000000') {
			if (!shouldCheckOpenAccount) {
				gogogo(targetUrl)
			}
		} else {
			util.prompt(res.rstmsg)
		}
	}

	function checkOpenAccount(res) {
		var body = res.body.zl_user
		var url = targetUrl
		// 已经开户
		if (body.isInvalidRegister == 0) {

		} else {

			if (body.fromStatus == 1 || body.fromStatus == 2) { // 老用户
				if (body.isSetTradePwd == 0) { // 未设置新交易密码
					if (body.fromStatus == 2) { // 已设置老交易密码
						// goto密码升级提示-goto众禄验证老交易密码-goto设置交易密码-通关
						url = '/finance-jlcres/account/remind-upgrade-trade-pwd-old.html'
					} else if (body.fromStatus == 1) { // 未设置老交易密码
						// goto设置交易密码-通关
						url = '/finance-jlcres/account/set-trade-pwd.html'
					}
				} else {
					// 通关了
				}
			} else { // 新用户
				if (body.isBindPhone == 1) { // 已经绑定手机
					if (body.isBindBankCard == 1) { // 已经绑卡
						if (body.isSetTradePwd == 0) { //未设置新交易密码
							// goto设置交易密码-通关
							url = '/finance-jlcres/account/set-trade-pwd.html'
							// toolkit.jumpto(url, target, true)
						} else { // 已设置老交易密码
							// 通关
						}
					} else { // 尚未绑卡
						// goto实名验证-todo
						url = '/finance-jlcres/account/open-account-identity.html'
					}
				} else { //未绑定手机
					// goto绑定手机-goto实名验证-todo
					url = '/finance-jlcres/account/complete-phone.html'
				}
			}
		}

		if (url !== targetUrl) {
			url = toolkit.encodeRequestParam(url, {redirectUrl: targetUrl})
			IS_APP && nativeController.webview.flow.start('openAccount')
		}
		gogogo(url)
	}


	toolkit.getUserInfo(function (res) {
		if (auth === 'login') {
			checkLogin(res, false)
		} else if (auth === 'openAccount') {
			checkLogin(res, true)
			checkOpenAccount(res)
		}
	})
}

//检查权限，比如是否已登陆、是否已开户，如果没有，则进行相应的逻辑判断和页面跳转
toolkit.checkAuth_ydb = function (auth, targetUrl) {
	targetUrl = targetUrl || ''
	// 之所以不用toolkit.jumpto而写了一个gogogo，是因为每次jumpto方法都会调用checkAuth，checkAuth里面再调用jumpto，就死循环了..
	function gogogo(url) {
		url = toolkit.completeFullUrl(url)
		if (IS_APP) {
			nativeController.webview.open(url)
		} else {
			window.location.href = url
		}
	}

	function checkLogin(res, shouldCheckOpenAccount) {
		// 未登录
		if (res.rstcode === '111111') {
			var url = '/finance-jlcres/account/login.html'
			IS_APP && nativeController.webview.flow.start('login')
			url = toolkit.encodeRequestParam(url, {redirectUrl: targetUrl})
			gogogo(url)
			// 已登录
		} else if (res.rstcode === '000000') {
			if (!shouldCheckOpenAccount) {
				gogogo(targetUrl)
			}
		} else {
			util.prompt(res.rstmsg)
		}
	}

	function checkYDBOpenAccount(res) {
		var body = res.body.p2p_user
		var url = targetUrl

		//邮箱注册用户
		if (body.bindPhone == 1) {
			if (body.bindBankCard == 1) {

			} else {
				//实名认证
				url = '/finance-jlcres/p2p/ydb/identity.html'
			}
		} else {
			// goto绑定手机-goto实名验证-todo
			url = '/finance-jlcres/p2p/ydb/register.html'
		}

		if (url !== targetUrl) {
			url = toolkit.encodeRequestParam(url, {redirectUrl: targetUrl})
			IS_APP && nativeController.webview.flow.start('openAccount')
		}
		gogogo(url)
	}


	toolkit.getUserInfo(function (res) {
		if (auth === 'login') {
			checkLogin(res, false)
		} else if (auth === 'openAccount') {
			checkLogin(res, true)
			checkYDBOpenAccount(res)
		}
	})
}

// 浏览器响应click时间是300ms之后，在300ms内连续点击，可以多次触发jumpto方法，因此限制为500ms内只相应一次jumto方法
var lastJumptoTimestamp = 0
toolkit.jumpto = function (url, target, shouldTakeRedirectUrl) {
	
	var curTimestamp = Date.now()
	if (curTimestamp - lastJumptoTimestamp < 500) {
		return
	}
	lastJumptoTimestamp = curTimestamp

	// 判断如果是本站地址，则补上location.origin，因为我们的路径都是相对站点根目录的绝对路径
	url = toolkit.completeFullUrl(url)

	// 如果需要把当前网址的redirectUrl参数带到下一个页面去
	var redirectUrl = toolkit.queryUrl(window.location.href, 'redirectUrl')
	if (shouldTakeRedirectUrl && redirectUrl) {
		url = toolkit.encodeRequestParam(url, {redirectUrl: redirectUrl})
	}

	// 判断是否需要权限
	var shouldGetAuth = false
		;
	(function () {
		var AUTH_LIST = {
				login: [
					'/finance-jlcres/more/account.html'
				],
				openAccount: [
					'/finance-jlcres/fund/',
					'/finance-jlcres/p2p/ydb/bankcard_list.html?type=zl'
				]
			}
			;
		(function () {
			var arr = AUTH_LIST.login
			for (var i = 0, len = arr.length; i < len; i++) {
				if (url.indexOf(arr[i]) >= 0) {
					toolkit.checkAuth('login', url)
					shouldGetAuth = true
					break
				}
			}
		})()
		;
		(function () {
			if (shouldGetAuth) {
				return
			}
			var arr = AUTH_LIST.openAccount
			for (var i = 0, len = arr.length; i < len; i++) {
				if (url.indexOf(arr[i]) >= 0) {
					toolkit.checkAuth('openAccount', url)
					shouldGetAuth = true
					break
				}
			}
		})()
	})()

	if (!shouldGetAuth) {
		if (IS_APP) {
			if (target === '_self') {
				window.location.href = url
			} else {
				nativeController.webview.open(url)
			}
		} else {
			window.location.href = url
		}
	}
}
var lastJumptoTimestamp = 0
//浏览器响应click时间是300ms之后，在300ms内连续点击，可以多次触发jumpto方法，因此限制为500ms内只相应一次jumto方法
toolkit.jumpto_ydb = function (url, target, shouldTakeRedirectUrl) {
	var curTimestamp = Date.now()
	if (curTimestamp - lastJumptoTimestamp < 500) {
		return
	}
	lastJumptoTimestamp = curTimestamp

	// 判断如果是本站地址，则补上location.origin，因为我们的路径都是相对站点根目录的绝对路径
	url = toolkit.completeFullUrl(url)

	// 如果需要把当前网址的redirectUrl参数带到下一个页面去
	var redirectUrl = toolkit.queryUrl(window.location.href, 'redirectUrl')
	if (shouldTakeRedirectUrl && redirectUrl) {
		url = toolkit.encodeRequestParam(url, {redirectUrl: redirectUrl})
	}


	// 判断是否需要权限
	var shouldGetAuth = false
		;
	(function () {
		var AUTH_LIST = {
				login: [
					'/finance-jlcres/more/account.html'
				],
				openAccount: [
					'/finance-jlcres/p2p/ydb/buy.html',
					'/finance-jlcres/p2p/ydb/buy_agreement.html',
					'/finance-jlcres/p2p/ydb/buy_disable.html',
					'/finance-jlcres/p2p/ydb/buy_fail.html',
					'/finance-jlcres/p2p/ydb/buy_succ.html',
					'/finance-jlcres/p2p/ydb/bankcard_list.html?type=p2p'

				]
			}
			;
		(function () {
			var arr = AUTH_LIST.login
			for (var i = 0, len = arr.length; i < len; i++) {
				if (url.indexOf(arr[i]) >= 0) {
					toolkit.checkAuth_ydb('login', url)
					shouldGetAuth = true
					break
				}
			}
		})()
		;
		(function () {
			if (shouldGetAuth) {
				return
			}
			var arr = AUTH_LIST.openAccount
			for (var i = 0, len = arr.length; i < len; i++) {
				if (url.indexOf(arr[i]) >= 0) {
					toolkit.checkAuth_ydb('openAccount', url)
					shouldGetAuth = true
					break
				}
			}
		})()
	})()

	if (!shouldGetAuth) {
		if (IS_APP) {
			if (target === '_self') {
				window.location.href = url
			} else {
				nativeController.webview.open(url)
			}
		} else {
			window.location.href = url
		}
	}
}
toolkit.storage = {
	get: function (key, callback) {
		var val = toolkit.cookie.get(key)
		callback && callback(val)
	},
	set: function (key, val) {
		toolkit.cookie.set(key, val, {expires: 1000 * 60 * 60 * 24 * 356})
	},
	clear: function (key) {
		toolkit.cookie.clear(key);
	}
}

toolkit.localStorage = {

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
			toolkit.cookie.set(key, val, {expires: 1000 * 60 * 60 * 24 * 356})
		}
	},
	remove: function (key) {
		key = 'sgj_' + key;
		if(isSupportedLocalStorage){
			localStorage.removeItem(key);
		}else{
			toolkit.cookie.clear(key);
		}
	},
	clear: function () {
		if(isSupportedLocalStorage){
			localStorage.clear();
		}else{
			// todo 
		}
	}
}

$('body').on('click', '[data-jumpto]', function (ev) {
	var _this = $(this)
	var url = $.trim(_this.attr('data-jumpto'))
	toolkit.jumpto(url)
})

// 组件类
window.widget = {
	// 验证码
	authcode: (function () {
		var item = $('.g-phone-authcode')
		var trigger = item.find('.btn')
		var loopId = -1
		var countDownTime = 60
		var defTxt = trigger.text()
		var clsDisabled = 'state-disabled'
		var timer = -1
		var state = 'default'

		function start() {
			state = 'counting'
			timer = countDownTime
			trigger.addClass(clsDisabled)
			trigger.text(timer-- + 's后重发')

			loopId = setInterval(function () {
				if (timer < 1) {
					stop()
				} else {
					console.log(timer);
					trigger.text(timer-- + 's后重发')
				}
			}, 1000)
		}

		function stop() {
			clearInterval(loopId)
			trigger.text(defTxt).removeClass(clsDisabled)
			state = 'default'
		}

		function checkState() {
			return state
		}

		return {
			start: start,
			stop: stop,
			checkState: checkState
		}
	})(),
	// 交易密码
	tradePwd: (function () {
		var elem = $('.g-trade-pwd')
		if (!elem.length) {
			return
		}

		var symbol = '&bull;'
		var ipt = elem.find('input')
		var faker = elem.find('.faker span')
		var lenCache = 0
		var loopId = -1

		function setFaker(len) {
			faker.each(function (index) {
				var _this = $(this)
				if (index <= len - 1) {
					_this.html(symbol)
				} else {
					_this.html('')
				}
			})
		}

		function checker() {
			var len = ipt.val().length
			if (len !== lenCache) {
				setFaker(len)
				lenCache = len
			}
		}

		// 兼容处理：在ios使用第三方输入法的时候，监听不到keyup/keydown/keypress事件
		ipt.on({
			focus: function () {
				loopId = setInterval(function () {
					checker()
				}, 50)
			},
			blur: function () {
				clearInterval(loopId)
				checker() // 由于研发那边也是通过定时器来获取长度，长度等于6即刻设置ipt.blur()；如果在前端js在定时器的间隔时间被blur掉，会导致用户录入5位，但是ui只显示6位的情况，因此，在blur的时候，手动进行一次检查
			}
		})

		function clear() {
			ipt.val('')
			faker.html('')
		}

		return {
			clear: clear
		}
	})()
}

// 输入框右侧的x
;
(function () {
	var trigger = $('[data-fn="clear"]')
	if (!trigger.length) {
		return
	}
	function checker(ipt, trigger) {
		if (ipt.val()) {
			// 这里不用trigger.show()的原因是，zepto会默认给show的元素“transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: inline;”，会覆盖掉css设置的transform属性
			trigger.css({
				display: 'block'
			})
		} else {
			trigger.css({
				display: 'none'
			})
		}

		ipt.on({
			blur: function () {
				trigger.css({
					display: 'none'
				})
			}
		})
	}


	trigger.each(function () {
		var _this = $(this)
		var ipt = _this.siblings('.ipt')
		var loopId = -1

		ipt.on({
			blur: function () {

				clearInterval(loopId)

				_this.css({
					display: 'none'
				})
			},
			focus: function () {
				loopId = setInterval(function () {
					checker(ipt, _this)
				}, 100)
			}
		})


	})

	trigger.on({
		tap: function () {
			var _this = $(this)
			var ipt = _this.siblings('.ipt')
			ipt.val('')
		}
	})
})()

// 密码输入框右侧的小眼睛
;
(function () {
	var trigger = $('[data-fn="toggle-pwd"]')
	if (!trigger.length) {
		return
	}

	function toggleIpt(trigger) {
		var ipt = trigger.siblings('.ipt')
		var curType = ipt.attr('type')
		if (curType === 'password') {
			ipt.attr('type', 'text')
			trigger.addClass('on')
		} else {
			ipt.attr('type', 'password')
			trigger.removeClass('on')
		}
	}

	trigger.on({
		tap: function () {
			var _this = $(this)
			toggleIpt(_this)
		}
	})
})()

// 防止页面底部的"工商银行保障资金安全"文字随着键盘的弹出会挤上去
var tipPosition = $('.g-tip-position');
var selfHeight = $(window).height();
var alterHeight = null;
$(window).resize(function(){
	alterHeight = $(window).height();
	var totalHeight = selfHeight - alterHeight + 'px';
	tipPosition.css('bottom','-' + totalHeight);
});
