/**
 * 主入口,配置zepto,template,util,bridge库
 */
require.config({
	paths:{
		zepto :"zepto-1.1.6.min",
		template:"template",
		util:'util',
		bridge:'bridge'
	},
	shim: {
        'zepto': {
            exports: '$'
        }
	}
});

require(['zepto','template','util','bridge'],function($,template,util,bridge){
	var container = $("#tt");
	var html = template('tmpl',"template");
	container.html(html);
	alert($("#test").html());
	alert(util);
	util.prompt("12121");
	nativeController.webview.refreshMain();
})
