define(function() {
	// loading
	function Loading() {
		var that = this
		that.init()
	}
	Loading.prototype = {
		constructor: Loading,
		init: function() {
			var that = this
			that._doCfg()

			return that
		},
		_doCfg: function() {
			var that = this
			that.settings = {
				dom: null,
				mask: null,
				style: null
			}
		},
		_initDom: function() {
			var that = this
			var settings = that.settings
			var style = '<style id="js-loading-style">' +
						'	#js-loading{display:none;position:fixed;z-index:2001;top:50%;left:50%;width:20px;height:20px;overflow:hidden;margin:-10px 0 0 -10px;background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoBAMAAAB+0KVeAAAAA3NCSVQICAjb4U/gAAAAMFBMVEX/////hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgT/hgTgHYtBAAAAEHRSTlMAESIzRFVmd4iZqrvM3e7/dpUBFQAAAAlwSFlzAAALEgAACxIB0t1+/AAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAF0SURBVCiRjZK9SgNBFIXv/kS0201UUob8NFZJJhFL0YmiVQxOBCvBZNMKilmsYqEMViIibjpFU4x9VPYV9hV8BB9BcSab3cxEC0+xc/ng3HvuZQFCpWqd03YZZGlVp9PlalkSXHZC2D2Z0ILDNYLuccQMjlolS0vtdV1aH8MNx2mM21B6GVZm0zmMTJuUHoyKarMdt9dceiFenZCoD9cipUX+JAiRE7v0XLjJjrxHWvg1QjIyNCm1wCC7ysbgenUwSUOFK14PFuTZQrPeDRRIUYUz3i0fnlGh6d0BJpYKde8ecE1loHkeYDwF4d9Q2Cv4j0EV/CvSAPJ4Ojx7hnm8qsI59ggGXlPhNrsGHa+r8IrxC01NSjDG4+QqJRkusQH/GgjJ0fvsQTwISaHSjI3S5BCKl9L7L8OwQCjuuu/7vbDKxXTL998iD0KobIOWP/N9/ygyJbNCvtDTZKag9lcQBK/yHZNZ2/r+CIbqbTUbPt+jf/cH5AxmYm6PrgoAAAAASUVORK5CYII=);background-repeat:no-repeat;background-position:0 0;background-size:100%;' +
						'		-webkit-animation:js-loading-rotate360 800ms linear infinite;' +
						'	}' +
						'	@-webkit-keyframes js-loading-rotate360{' +
						'		0%{-webkit-transform:rotate(0deg);}' +
						'		100%{-webkit-transform:rotate(360deg);}' +
						'	}' +
						'</style>'
			var html = ''
			html += '<div id="js-loading-mask" style="display:none;position:fixed;z-index:2000;top:0;left:0;width:100%;height:100%;overflow:hidden;background-color:#000;opacity:.4;"></div>'
			html += '<div id="js-loading"></div>'

			$('body').append(style + html)

			settings.mask = $('#js-loading-mask')
			settings.dom = $('#js-loading')
			settings.style = $('#js-loading-style')
		},
		show: function() {
			var that = this
			var settings = that.settings

			that._initDom()
			settings.mask.css({
				display: 'block'
			})
			settings.dom.css({
				display: 'block'
			})

			return that
		},
		hide: function() {
			var that = this
			var settings = that.settings
			settings.mask.remove()
			settings.dom.remove()
			settings.style.remove()

			return that
		}
	}



	function Prompt() {
		var that = this
		that.init()
	}
	Prompt.prototype = {
		constructor: Prompt,
		init: function() {
			var that = this
			that._doCfg()

			return that
		},
		_doCfg: function() {
			var that = this
			that.settings = {
				dom: null,
				timeoutId: -1
			}
		},
		_initDom: function() {
			var that = this
			var settings = that.settings
			var html = '<div id="js-prompt" style="position:fixed;z-index:1100;top:50%;left:50%;max-width:90%;padding:5px 10px;background-color:rgba(0,0,0,.6);border-radius:5px;color:#fff;font-size:16px;word-wrap:break-word;-webkit-transform:translate(-50%,-50%);"></div>'

			$('body').append(html)

			settings.dom = $('#js-prompt')
		},
		show: function(txt, timeout) {
			var that = this
			var settings = that.settings
			timeout = typeof timeout === 'undefined' ? 3000 : timeout

			that._initDom()
			settings.dom.text(txt)
			settings.dom.fadeIn(200)
			clearTimeout(settings.timeoutId)
			if(timeout > 0) {
				settings.timeoutId = setTimeout(function() {
					that.hide()
				}, timeout)
			}

			return that
		},
		hide: function() {
			var that = this
			var settings = that.settings

			settings.dom && settings.dom.remove && settings.dom.remove()

			return that
		}
	}

	function Popup() {
		var that = this
	}
	Popup.prototype = {
		constructor: Popup,
		init: function(cfg) {
			var that = this
			that._doCfg(cfg)

			return that
		},
		_doCfg: function(cfg) {
			var that = this
			that.settings = {
				mask: null,
				dom: cfg.element,
				triggerClose: cfg.triggerClose,
				triggerCloseCallback: cfg.triggerCloseCallback || null,
				staticTop: typeof cfg.staticTop === 'undefined' ? '' : cfg.staticTop
			}
			that.settings.dom = $(that.settings.dom)
			that.settings.triggerClose = that.settings.dom.find(that.settings.triggerClose)
		},
		_initDom: function() {
			var that = this
			var settings = that.settings
			var html = ''
			html += '<div id="js-popup-mask" style="displa:none;position:fixed;z-index:1000;top:0;left:0;width:100%;height:100%;overflow:hidden;background-color:#000;opacity:.6;"></div>'

			$('body').append(html)

			settings.mask = $('#js-popup-mask')

			if(settings.staticTop === '') {
				settings.dom.css({
					display: 'none',
					position: 'fixed',
					'z-index': '1001',
					top: '50%',
					left: '50%',
					'-webkit-transform': 'translate(-50%,-50%)'
				})
			} else {
				settings.dom.css({
					display: 'none',
					position: 'fixed',
					'z-index': '1001',
					top: settings.staticTop + 'px',
					left: '50%',
					'-webkit-transform': 'translateX(-50%)'
				})
			}
		},
		_bindEvent: function() {
			var that = this
			var settings = that.settings

			settings.triggerClose.on({
				click: function() {
					that.hide()
					settings.triggerCloseCallback && settings.triggerCloseCallback()
				}
			})
		},
		show: function() {
			var that = this
			var settings = that.settings

			that._initDom()
			that._bindEvent()
			settings.mask.css({
				display: 'block'
			})
			settings.dom.css({
				display: 'block'
			})

			return that
		},
		hide: function() {
			var that = this
			var settings = that.settings
			settings.mask.remove()
			settings.dom.css({
				display: 'none'
			})
		}
	}

	function Dialog() {}
	Dialog.prototype = {
		constructor: Dialog,
		init: function(cfg) {
			var that = this
			that._doCfg(cfg)

			return that
		},
		_doCfg: function(cfg) {
			var that = this
			that.settings = {
				mask: null,
				dom: null,
				button: [
					{
						text: '确定'
					}
				]
			}
			$.extend(that.settings, cfg)
		},
		_initDom: function() {
			var that = this
			var settings = that.settings

			var style = '<style>' +
						'#js-dialog{display:none;position:fixed;z-index:1002;top:50%;left:50%;width:90%;background-color:#f5f5f5;border-radius:5px;color:#495055;font-size:16px;line-height:1.5;-webkit-transform:translate(-50%,-50%);}' +
						'#js-dialog .hd{padding:1rem 0;color:#000;font-size:1.6rem;font-weight:normal;text-align:center;}' +
						'#js-dialog .bd{padding:2.3rem 2.5rem;color:#000;font-size:1.6rem;text-align:center;}' +
						'#js-dialog .ft{display:-webkit-box;border-top:1px solid #C7C7C7;}' +
						'#js-dialog .ft .btn{display:block;-webkit-box-flex:1;height:44px;border-right:1px solid rgba(170,170,170,0.6);color:#22affc;line-height:44px;text-align:center;width:50%;}' +
						'#js-dialog .ft .btn:last-child{border-right:none;}' +
						'</style>'
			var html = ''
			html += '<div id="js-dialog-mask" style="display:none;position:fixed;z-index:1000;top:0;left:0;width:100%;height:100%;overflow:hidden;background-color:#000;opacity:.6;"></div>'
			html += '<div id="js-dialog">'
			if(settings.title) {
			html +=	'	<div class="hd">' + settings.title + '</div>'
			}
			html += '	<div class="bd">'
			html += settings.content
			html += '	</div>' +
					'	<div class="ft">'
			settings.button.forEach(function(item) {
			html +=	'		<a class="btn" href="javascript:void(0)">' + item.text + '</a>'	
			})
			html += '	</div>' +
					'</div>'

			$('body').append(style, html)


			settings.mask = $('#js-dialog-mask')
			settings.dom = $('#js-dialog')

			// event
			settings.dom.find('.ft .btn').each(function(index) {
				var item = $(this)
				var callback = settings.button[index].callback || function() {
					that.hide()
				}
				
				item.on({
					click: function() {
						callback()
					}
				})
			})
		},
		show: function() {
			var that = this
			var settings = that.settings

			that._initDom()

			settings.mask.css({
				display: 'block'
			})
			settings.dom.css({
				display: 'block'
			})

			return that
		},
		hide: function() {
			var that = this
			var settings = that.settings
			settings.mask.remove()
			settings.dom.remove()
			return that
		}
	}

	//var util = {
	return{
		loading: (function() {
			var instance = new Loading()
			return instance
		})(),
		prompt: (function() {
			var instance = new Prompt()
			
			function action(txt, timeout) {
				if(txt) {
					instance.hide()
					instance.show(txt, timeout)
				}
				return instance
			}
			return action
		})(),
		popup: function(cfg) {
			var instance = new Popup()
			instance.init(cfg).show()
			return instance
		},
		dialog: function(cfg) {
			var instance = new Dialog()
			instance.init(cfg).show()
			return instance
		}
	}
	//}
	
	//window.util = util
});

/*
// loading显示隐藏
util.loading.show()
util.loading.hide()

// prompt弹出
util.prompt('密码格式错误')
// 自定义自动消失时间
util.prompt('密码格式错误', 5000)
// 设置不自动消失&手动消失
var p = util.prompt('密码格式错误', 0)
p.hide()


// popup弹窗
util.popup({
	element: '#popup-layer'
})

// dialog
// button参数可以不填，默认是“确定”，callback是点击后消失
util.dialog({
	title: '标题思密达',
	content: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容<br />内容内容内容内容内容',
	button: [
		{
			text: '确定1',
			callback: function() {
				alert('点击了确定')
			}
		},
		{
			text: '取消1',
			callback: function() {
				alert('点击了取消')
			}
		},
		{
			text: '关闭'
		}
	]
})
*/