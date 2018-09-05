/*!
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * 
 * 通用公共方法
 * @author ThinkGem
 * @version 2014-4-29
 */

//自定义标题和内容确认对话框
function confirmself(mess,title, href, closed){
	top.$.jBox.confirm(mess,title,function(v,h,f){
		if(v=='ok'){
			if (typeof href == 'function') {
				href();
			}else{
				resetTip(); //loading();
				location = href;
			}
		}
	},{buttonsFocus:1, closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
	top.$('.jbox-body .jbox-icon').css('top','55px');
	return false;
}


/**
 * 模仿android里面的Toast效果，主要是用于在不打断程序正常执行的情况下显示提示数据
 * @param config
 * @return
 */
var Toast = function(config) {
	this.context = config.context == null ? $('body') : config.context;//上下文
	this.message = config.message;//显示内容
	this.time = config.time == null ? 3000 : config.time;//持续时间
	this.init();
}
var msgEntity;
Toast.prototype = {
	//初始化显示的位置内容等
	init : function() {
		$("#toastMessage").remove();
		//设置消息体
		var msgDIV = new Array();
		msgDIV.push('<div id="toastMessage">');
		msgDIV.push('<span>' + this.message + '</span>');
		msgDIV.push('</div>');
		msgEntity = $(msgDIV.join('')).appendTo(this.context);
		//设置消息样式
		/* var left = this.left == null ? this.context.width() / 2
				- msgEntity.find('span').width() / 2 : this.left;
		var top = this.top == null ? '20px' : this.top; */
		var left = window.innerWidth/2 - msgEntity.find('span').width() / 2;
		msgEntity.css({
			//position : 'absolute',
			'position' : 'fixed',
			'top' : '50%',
			'left' : left,
			'z-index' : '99',
			//'background-color' : 'black',
			'background-color' : 'rgba(0, 0, 0, 0.6)',
			'color' : 'white',
			'font-size' : '13px',
			'padding' : '10px',
			'border-radius' : '3px'
		//	'opicaty':'0.5'
		});
		msgEntity.hide();
	},
	//显示动画
	show : function() {
		msgEntity.fadeIn(this.time / 2);
		msgEntity.fadeOut(this.time/ 2);
	}
}

/*
 * 直接调用下面的函数，显示toast提示
 */
function freetekToast(message){
	new Toast({message:message}).show();
}

